package at.hgz.dice;

public enum Dice {

	/**
	 * 2
	 */
	COIN(2, "Coin", R.string.coin, R.drawable.ic_w2) {
		private int[] images = {
				R.drawable.ic_w2d1,
				R.drawable.ic_w2d2,
		};
		@Override
		protected int[] getImages() { return images; }
		@Override
		public String getText(double d) {
			return toInt(d) == 0 ? "Head" : "Tail";
		}
		@Override
		public int getContentDescriptionId(double d) { return toInt(d) == 0 ? R.string.head : R.string.tail; };
	},
	
	/**
	 * 4
	 */
	TETRAHEDRON(4, "1 ... 4", R.string.tetrahedron, R.drawable.ic_w4) {
		private int[] images = {
				R.drawable.ic_w4d1,
				R.drawable.ic_w4d2,
				R.drawable.ic_w4d3,
				R.drawable.ic_w4d4,
		};
		@Override
		protected int[] getImages() { return images; }
	},
	
	/**
	 * 6
	 */
	CUBE(6, "1 ... 6", R.string.cube, R.drawable.ic_w6) {
		private int[] images = {
				R.drawable.ic_w6d1,
				R.drawable.ic_w6d2,
				R.drawable.ic_w6d3,
				R.drawable.ic_w6d4,
				R.drawable.ic_w6d5,
				R.drawable.ic_w6d6,
		};
		@Override
		protected int[] getImages() { return images; }
	},
	
	/**
	 * 8
	 */
	OCTAHEDRON(8, "1 ... 8", R.string.octahedron, R.drawable.ic_w8) {
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
		protected int[] getImages() { return images; }
	},
	
	/**
	 * 10
	 */
	PENTAGONAL_TRAPEZOHEDRON(10, "1 ... 10", R.string.pentagonal_trapezohedron, -1) {
		@Override
		protected int[] getImages() {
			throw new RuntimeException("Not implemented!");
		}
	},
	
	/**
	 * 12
	 */
	DODECAHEDRON(12, "1 ... 12", R.string.dodecahedron, R.drawable.ic_w12) {
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
		protected int[] getImages() { return images; }
	},
	
	/**
	 * 20
	 */
	ICOSAHEDRON(20, "1 ... 20", R.string.icosahedron, R.drawable.ic_w20) {
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
		protected int[] getImages() { return images; }
	};

	private static final int[] faces = {
			R.string.face_1,
			R.string.face_2,
			R.string.face_3,
			R.string.face_4,
			R.string.face_5,
			R.string.face_6,
			R.string.face_7,
			R.string.face_8,
			R.string.face_9,
			R.string.face_10,
			R.string.face_11,
			R.string.face_12,
			R.string.face_13,
			R.string.face_14,
			R.string.face_15,
			R.string.face_16,
			R.string.face_17,
			R.string.face_18,
			R.string.face_19,
			R.string.face_20,
	};

	private final int numberOfFaces;

	private final String text;

	private final int contentDescriptionId;

	private final int image;

	Dice(int numberOfFaces, String text, int contentDescriptionId, int image) {
		this.numberOfFaces = numberOfFaces;
		this.text = text;
		this.contentDescriptionId = contentDescriptionId;
		this.image = image;
	}

	public int getNumberOfFaces() { return numberOfFaces; }

	public int getImage() { return image; };

	public String getText() { return text; };

	public int getContentDescriptionId() { return contentDescriptionId; };

	public boolean isImage() {
		return true;
	}
	
	public boolean isText() {
		return false;
	}

	public int getImage(double d) {
		return getImages()[toInt(d)];
	}

	public String getText(double d) {
		return "" + (1 + toInt(d));
	}

	public int getContentDescriptionId(double d) { return faces[toInt(d)]; };

	protected abstract int[] getImages();

	protected int toInt(double d) { return (int) (d * getNumberOfFaces()); };
}
