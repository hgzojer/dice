package at.hgz.dice;

import java.util.Arrays;

public class Result {

	private Dice dice;
	
	private double[] values;

	public Result(Dice dice, double[] values) {
		this.dice = dice;
		setValues1(values);
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		setValues1(values);
	}

	private void setValues1(double[] values) {
		this.values = Arrays.copyOf(values, values.length);
		Arrays.sort(this.values);
		for (int i = 0; i < this.values.length / 2; i++) {
			int j = this.values.length - i - 1;
			double swap = this.values[i];
			this.values[i] = this.values[j];
			this.values[j] = swap;
		}
	}

	public String getText(int i) {
		return dice.getText(values[i]);
	}
	
	public int getImage(int i) {
		return dice.getImage(values[i]);
	}
	
	public boolean isText() {
		return dice.isText();
	}
	
	public boolean isImage() {
		return dice.isImage();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(dice.toString());
		sb.append('\u0001');
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				sb.append('\u0002');
			}
			sb.append(Double.doubleToRawLongBits(values[i]));
		}
		return sb.toString();
	}
	
	public static Result valueOf(String s) {
		String[] arr = s.split("\u0001");
		Dice dice = Dice.valueOf(arr[0]);
		String valuesString = arr[1];
		String[] valuesArr = valuesString.split("\u0002");
		double[] values = new double[valuesArr.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = Double.longBitsToDouble(Long.parseLong(valuesArr[i]));
		}
		return new Result(dice, values);
	}
}
