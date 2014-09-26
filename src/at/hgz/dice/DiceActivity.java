package at.hgz.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DiceActivity extends FragmentActivity {
	
	private static int CHOOSE_DICE = 1;

	private static final String RESULTS = "results";

	private List<Result> results = new ArrayList<Result>(2);

	private ResultsArrayAdapter adapter;
	
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dice);

		//listView = (ListView) findViewById(android.R.id.list);
		listView = (ListView) findViewById(R.id.resultslist);
		listView.setEmptyView(findViewById(R.id.empty_item));
		adapter = new ResultsArrayAdapter(this, R.layout.results_item, results);
		listView.setAdapter(adapter);

		if (savedInstanceState != null) {
			String resultString = savedInstanceState.getString(RESULTS);
			if (resultString.length() > 0) {
				resultString = resultString.substring(0, resultString.length() - 1);
				for (String result : resultString.split("\0")) {
					results.add(Result.valueOf(result));
				}
			}
			adapter.notifyDataSetChanged();
		} else {
			addDice();
			addDice();
		}
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> l, View v, final int position, long id) {
				DialogFragment newFragment = new DiceNumberDialogFragment(results.get(position).getValues().length) {
					@Override
					protected void returnValue(int newLength) {
						double[] values = results.get(position).getValues();
						int oldLength = values.length;
						values = Arrays.copyOf(values, newLength);
						for (int i = oldLength; i < newLength; i++) {
							values[i] = Math.random();
						}
						results.get(position).setValues(values);
					}
				};
				newFragment.show(getSupportFragmentManager(), "diceNumber");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.dice_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.add_dice:
			addDice();
			return true;
		case R.id.add_other_dice:
			addOtherDice();
			return true;
		case R.id.remove_dice:
			removeDice();
			return true;
		case R.id.action_dice:
			diceAll();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		StringBuilder sb = new StringBuilder();
		for (Result result : results) {
			sb.append(result.toString());
			sb.append('\0');
		}
		savedInstanceState.putString(RESULTS, sb.toString());
	}

	private void addDice() {
		results.add(dice(results.size()));
		adapter.notifyDataSetChanged();
		listView.setSelection(results.size() - 1);
	}

	private void addOtherDice() {
		Intent intent = new Intent(this, ChooseDiceActivity.class);
		startActivityForResult(intent, CHOOSE_DICE);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CHOOSE_DICE) {
			if (resultCode == RESULT_OK) {
				String s = data.getStringExtra(ChooseDiceActivity.SELECTED_DICE);
				results.add(new Result(Dice.valueOf(s), new double[] {0}));
				results.set(results.size() - 1, dice(results.size()));
				adapter.notifyDataSetChanged();
				listView.setSelection(results.size() - 1);
			}
		}
	}

	private void removeDice() {
		if (results.size() > 0) {
			results.remove(results.size() - 1);
		}
		adapter.notifyDataSetChanged();
		listView.setSelection(results.size() - 1);
	}

	private void diceAll() {
		for (int i = 0; i < results.size(); i++) {
			results.set(i, dice(i));
		}
		adapter.notifyDataSetChanged();
	}

	private Result dice(int i) {
		Dice dice;
		int count;
		if (i >= results.size() || results.get(i) == null) {
			if (i == 0) {
				dice = Dice.CUBE;
			} else {
				dice = results.get(i - 1).getDice();
			}
			count = 1;
		} else {
			dice = results.get(i).getDice();
			count = results.get(i).getValues().length;
		}
		double[] d = new double[count];
		for (int j = 0; j < count; j++) {
			d[j] = Math.random();
		}
		return new Result(dice, d);
	}
	
	private static class ResultsArrayAdapter extends ArrayAdapter<Result> {

		static class ViewHolder {
			public ImageView resultsImage;
			public TextView resultsItem;
			public Result result;
		}

		public ResultsArrayAdapter(Context context, int resource, List<Result> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Result result = getItem(position);

			// reuse views
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.results_item, parent, false);
				// configure view holder
				ViewHolder vh = new ViewHolder();
				vh.resultsImage = (ImageView) convertView.findViewById(R.id.resultsImage);
				vh.resultsItem = (TextView) convertView.findViewById(R.id.resultsItem);
				convertView.setTag(vh);
			}

			// fill data
			ViewHolder vh = (ViewHolder) convertView.getTag();
			vh.result = result;
			if (vh.result.isImage()) {
				vh.resultsImage.setImageResource(vh.result.getImage(0));
				vh.resultsImage.setVisibility(View.VISIBLE);
			} else {
				vh.resultsImage.setVisibility(View.GONE);
			}
			if (vh.result.isText()) {
				vh.resultsItem.setText(vh.result.getText(0));
				vh.resultsItem.setVisibility(View.VISIBLE);
			} else {
				vh.resultsItem.setVisibility(View.GONE);
			}

			return convertView;
		}
	}

}
