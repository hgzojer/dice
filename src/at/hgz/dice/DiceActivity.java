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
import android.widget.TextView;

public class DiceActivity extends ListActivity {

	private List<String> results = new ArrayList<String>(2);
	
	private ResultsArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dice);
		
		adapter = new ResultsArrayAdapter(this, R.layout.results_item, results);
		setListAdapter(adapter);

		addDice();
		addDice();
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

	private void addDice() {
		results.add(dice(results.size()));
		adapter.notifyDataSetChanged();
	}

	private void removeDice() {
		if (results.size() > 0) {
			results.remove(results.size() - 1);
		}
		adapter.notifyDataSetChanged();
	}

	private void diceAll() {
		for (int i = 0; i < results.size(); i++) {
			results.set(i, dice(i));
		}
		adapter.notifyDataSetChanged();
	}

	private String dice(int i) {
		int ret = 1 + (int) (Math.random() * 6);
		return "" + ret;
	}

	private static class ResultsArrayAdapter extends ArrayAdapter<String> {

		static class ViewHolder {
			public TextView resultsItem;
			public String result;
		}

		public ResultsArrayAdapter(Context context, int resource,
				List<String> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			String result = getItem(position);
			
			// reuse views
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.results_item, parent, false);
				// configure view holder
				ViewHolder vh = new ViewHolder();
				vh.resultsItem = (TextView) convertView
						.findViewById(R.id.resultsItem);
				convertView.setTag(vh);
			}

			// fill data
			ViewHolder vh = (ViewHolder) convertView.getTag();
			vh.result = result;
			vh.resultsItem.setText(vh.result);

			return convertView;
		}
	}

}
