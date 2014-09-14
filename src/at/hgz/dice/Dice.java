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
		private int toInt(double d) {
			return (int) (d * 2);
		}
	},
	
	/**
	 * 4
	 */
	TETRAHEDRON {
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
		private int toInt(double d) {
			return (int) (d * 6);
		}
	},
	
	/**
	 * 8
	 */
	OCTAHEDRON {
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
	},
	
	/**
	 * 12
	 */
	DODECAHEDRON {
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
	},
	
	/**
	 * 20
	 */
	ICOSAHEDRON {
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
}
