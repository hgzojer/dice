package at.hgz.dice;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DiceActivity extends ListActivity {

	private static final String RESULTS = "results";

	private List<Result> results = new ArrayList<Result>(2);

	private ResultsArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dice);

		adapter = new ResultsArrayAdapter(this, R.layout.results_item, results);
		setListAdapter(adapter);

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
		setSelection(results.size() - 1);
	}

	private void removeDice() {
		if (results.size() > 0) {
			results.remove(results.size() - 1);
		}
		adapter.notifyDataSetChanged();
		setSelection(results.size() - 1);
	}

	private void diceAll() {
		for (int i = 0; i < results.size(); i++) {
			results.set(i, dice(i));
		}
		adapter.notifyDataSetChanged();
	}

	private Result dice(int i) {
		double d = Math.random();
		Dice dice;
		if (results.size() >= i || results.get(i) == null) {
			dice = Dice.CUBE;
		} else {
			dice = results.get(i).getDice();
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
				vh.resultsImage.setImageResource(vh.result.getImage());
				vh.resultsImage.setVisibility(View.VISIBLE);
			} else {
				vh.resultsImage.setVisibility(View.GONE);
			}
			if (vh.result.isText()) {
				vh.resultsItem.setText(vh.result.getText());
				vh.resultsItem.setVisibility(View.VISIBLE);
			} else {
				vh.resultsItem.setVisibility(View.GONE);
			}

			return convertView;
		}
	}

}
