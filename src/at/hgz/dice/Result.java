package at.hgz.dice;

public class Result {

	private Dice dice;
	
	private double value;

	public Result(Dice dice, double value) {
		this.dice = dice;
		this.value = value;
	}

	public Dice getDice() {
		return dice;
	}

	public void setDice(Dice dice) {
		this.dice = dice;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getText() {
		return dice.getText(value);
	}
	
	public int getImage() {
		return dice.getImage(value);
	}
	
	public boolean isText() {
		return dice.isText();
	}
	
	public boolean isImage() {
		return dice.isImage();
	}
	
	public String toString() {
		return dice.toString() + "\u0001" + Double.doubleToRawLongBits(value);
	}
	
	public static Result valueOf(String s) {
		String[] arr = s.split("\u0001");
		Dice dice = Dice.valueOf(arr[0]);
		double value = Double.longBitsToDouble(Long.parseLong(arr[1]));
		return new Result(dice, value);
	}
}
