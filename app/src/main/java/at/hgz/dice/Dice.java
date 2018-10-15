package at.hgz.dice;

public enum Dice {

	/**
	 * 2
	 */
	COIN {
		private int[] images = {
				R.drawable.ic_w2d1,
				R.drawable.ic_w2d2,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w2;
		}
		@Override
		public String getText() {
			return "Coin";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return toInt(d) == 0 ? "Head" : "Tail";
		}
		@Override
		public String getContentDescription() { return "@string/coin"; }
		private int toInt(double d) {
			return (int) (d * 2);
		}
	},
	
	/**
	 * 4
	 */
	TETRAHEDRON {
		private int[] images = {
				R.drawable.ic_w4d1,
				R.drawable.ic_w4d2,
				R.drawable.ic_w4d3,
				R.drawable.ic_w4d4,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w4;
		}
		@Override
		public String getText() {
			return "1 ... 4";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return "" + (1 + toInt(d));
		}
		@Override
		public String getContentDescription() { return "@string/tetrahedron"; }
		private int toInt(double d) {
			return (int) (d * 4);
		}
	},
	
	/**
	 * 6
	 */
	CUBE {
		private int[] images = {
				R.drawable.ic_w6d1,
				R.drawable.ic_w6d2,
				R.drawable.ic_w6d3,
				R.drawable.ic_w6d4,
				R.drawable.ic_w6d5,
				R.drawable.ic_w6d6,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w6;
		}
		@Override
		public String getText() {
			return "1 ... 6";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return "" + (1 + toInt(d)); 
		}
		@Override
		public String getContentDescription() { return "@string/cube"; }
		private int toInt(double d) {
			return (int) (d * 6);
		}
	},
	
	/**
	 * 8
	 */
	OCTAHEDRON {
		private int[] images = {
				R.drawable.ic_w8d1,
				R.drawable.ic_w8d2,
				R.drawable.ic_w8d3,
				R.drawable.ic_w8d4,
				R.drawable.ic_w8d5,
				R.drawable.ic_w8d6,
				R.drawable.ic_w8d7,
				R.drawable.ic_w8d8,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w8;
		}
		@Override
		public String getText() {
			return "1 ... 8";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return "" + (1 + toInt(d));
		}
		@Override
		public String getContentDescription() { return "@string/octahedron"; }
		private int toInt(double d) {
			return (int) (d * 8);
		}
	},
	
	/**
	 * 10
	 */
	PENTAGONAL_TRAPEZOHEDRON {
		@Override
		public int getImage() {
			// TODO Auto-generated method stub
			throw new RuntimeException("Not implemented!");
		}
		@Override
		public String getText() {
			// TODO Auto-generated method stub
			throw new RuntimeException("Not implemented!");
		}
		@Override
		public int getImage(double d) {
			// TODO Auto-generated method stub
			throw new RuntimeException("Not implemented!");
		}
		@Override
		public String getText(double d) {
			// TODO Auto-generated method stub
			throw new RuntimeException("Not implemented!");
		}
		@Override
		public String getContentDescription() { return "@string/pentagonal_trapezohedron"; }
	},
	
	/**
	 * 12
	 */
	DODECAHEDRON {
		private int[] images = {
				R.drawable.ic_w12d1,
				R.drawable.ic_w12d2,
				R.drawable.ic_w12d3,
				R.drawable.ic_w12d4,
				R.drawable.ic_w12d5,
				R.drawable.ic_w12d6,
				R.drawable.ic_w12d7,
				R.drawable.ic_w12d8,
				R.drawable.ic_w12d9,
				R.drawable.ic_w12d10,
				R.drawable.ic_w12d11,
				R.drawable.ic_w12d12,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w12;
		}
		@Override
		public String getText() {
			return "1 ... 12";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return "" + (1 + toInt(d));
		}
		@Override
		public String getContentDescription() { return "@string/dodecahedron"; }
		private int toInt(double d) {
			return (int) (d * 12);
		}
	},
	
	/**
	 * 20
	 */
	ICOSAHEDRON {
		private int[] images = {
				R.drawable.ic_w20d1,
				R.drawable.ic_w20d2,
				R.drawable.ic_w20d3,
				R.drawable.ic_w20d4,
				R.drawable.ic_w20d5,
				R.drawable.ic_w20d6,
				R.drawable.ic_w20d7,
				R.drawable.ic_w20d8,
				R.drawable.ic_w20d9,
				R.drawable.ic_w20d10,
				R.drawable.ic_w20d11,
				R.drawable.ic_w20d12,
				R.drawable.ic_w20d13,
				R.drawable.ic_w20d14,
				R.drawable.ic_w20d15,
				R.drawable.ic_w20d16,
				R.drawable.ic_w20d17,
				R.drawable.ic_w20d18,
				R.drawable.ic_w20d19,
				R.drawable.ic_w20d20,
		};
		@Override
		public int getImage() {
			return R.drawable.ic_w20;
		}
		@Override
		public String getText() {
			return "1 ... 20";
		}
		@Override
		public int getImage(double d) {
			return images[toInt(d)];
		}
		@Override
		public String getText(double d) {
			return "" + (1 + toInt(d));
		}
		@Override
		public String getContentDescription() { return "@string/icosahedron"; }
		private int toInt(double d) {
			return (int) (d * 20);
		}
	};
	
	public boolean isImage() {
		return true;
	}
	
	public boolean isText() {
		return false;
	}
	
	public abstract int getImage();
	
	public abstract String getText();
	
	public abstract int getImage(double d);
	
	public abstract String getText(double d);

	public abstract String getContentDescription();
}
