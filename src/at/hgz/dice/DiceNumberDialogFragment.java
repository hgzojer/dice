package at.hgz.dice;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

public abstract class DiceNumberDialogFragment extends DialogFragment {
	
	private int value;
	
	private NumberPicker diceNumberPicker;

	public DiceNumberDialogFragment(int value) {
		this.value = value;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
               .setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       returnValue(diceNumberPicker.getValue());
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                	   DiceNumberDialogFragment.this.getDialog().cancel();
                   }
               });      
        return builder.create();
    }
	
	protected abstract void returnValue(int value);

}
