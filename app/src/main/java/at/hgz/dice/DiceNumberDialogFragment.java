package at.hgz.dice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public class DiceNumberDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {
	
	public static final String VALUE = "value";
	
	public static final String POSITION = "position";
	
	private NumberPicker diceNumberPicker;
	
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		int value;
		if (savedInstanceState != null) {
			value = savedInstanceState.getInt(VALUE);
		} else {
			value = getArguments().getInt(VALUE);
		}
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.dialog_dice_number, null);
        diceNumberPicker = (NumberPicker) view.findViewById(R.id.diceNumberPicker);
        diceNumberPicker.setMinValue(1);
        diceNumberPicker.setMaxValue(1000);
        diceNumberPicker.setWrapSelectorWheel(false);
        diceNumberPicker.setValue(value);
		builder.setView(view)
        // set title
        		.setTitle(R.string.number_of_dice)
        // Add action buttons
               .setPositiveButton(R.string.set, this)
               .setNegativeButton(R.string.cancel, null);      
        return builder.create();
    }

	private int getNewLength() {
		return diceNumberPicker.getValue();
	}

	@Override
	public void onClick(DialogInterface dialog, int clickPosition) {
		DiceActivity activity = (DiceActivity) getActivity();
		int position = getArguments().getInt(POSITION);
		activity.selectNumber(getNewLength(), position);
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt(VALUE, getNewLength());
	}

}
