package at.hgz.dice;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ChooseDiceActivity extends ListActivity {
	
	public static final String SELECTED_DICE = "selectedDice";

	private List<Dice> dice = new ArrayList<Dice>(1);

	private DiceArrayAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dice);
		
		dice.add(Dice.COIN);
		dice.add(Dice.TETRAHEDRON);
		dice.add(Dice.CUBE);
		dice.add(Dice.OCTAHEDRON);
		dice.add(Dice.DODECAHEDRON);
		dice.add(Dice.ICOSAHEDRON);

		adapter = new DiceArrayAdapter(this, R.layout.dice_item, dice);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent resultData = new Intent();
		resultData.putExtra(SELECTED_DICE, "" + dice.get(position).toString());
		setResult(Activity.RESULT_OK, resultData);
		finish();
	}

	private static class DiceArrayAdapter extends ArrayAdapter<Dice> {

		static class ViewHolder {
			public ImageView resultsImage;
			public TextView resultsItem;
			public Dice dice;
		}

		public DiceArrayAdapter(Context context, int resource, List<Dice> objects) {
			super(context, resource, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Dice dice = getItem(position);

			// reuse views
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.dice_item, parent, false);
				// configure view holder
				ViewHolder vh = new ViewHolder();
				vh.resultsImage = (ImageView) convertView.findViewById(R.id.resultsImage);
				vh.resultsItem = (TextView) convertView.findViewById(R.id.resultsItem);
				convertView.setTag(vh);
			}

			// fill data
			ViewHolder vh = (ViewHolder) convertView.getTag();
			vh.dice = dice;
			if (vh.dice.isImage()) {
				vh.resultsImage.setImageResource(vh.dice.getImage());
				vh.resultsImage.setVisibility(View.VISIBLE);
				vh.resultsImage.setContentDescription(getContext().getString(vh.dice.getContentDescriptionId()));
			} else {
				vh.resultsImage.setVisibility(View.GONE);
				vh.resultsImage.setContentDescription(null);
			}
			if (vh.dice.isText()) {
				vh.resultsItem.setText(vh.dice.getText());
				vh.resultsItem.setVisibility(View.VISIBLE);
			} else {
				vh.resultsItem.setVisibility(View.GONE);
			}

			return convertView;
		}
	}
}
