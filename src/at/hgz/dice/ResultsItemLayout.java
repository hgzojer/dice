package at.hgz.dice;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class ResultsItemLayout extends ViewGroup {
	
	private int childWidth;
	private int childHeight;

	public ResultsItemLayout(Context context) {
		super(context);
		calculateChildWidthHeight();
	}

	public ResultsItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		calculateChildWidthHeight();
	}

	public ResultsItemLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		calculateChildWidthHeight();
	}

	private void calculateChildWidthHeight() {
		BitmapFactory.Options dimensions = new BitmapFactory.Options(); 
		dimensions.inJustDecodeBounds = true;
		/*Bitmap mBitmap =*/ BitmapFactory.decodeResource(getResources(), R.drawable.ic_w6d6, dimensions);
		childWidth = dimensions.outWidth;
		childHeight = dimensions.outHeight;
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
		int childrenPerRow = displayMetrics.widthPixels / childWidth;
		int rows = (getChildCount() + childrenPerRow - 1) / childrenPerRow;
		int desiredWidth = childrenPerRow * childWidth;
	    int desiredHeight = rows * childHeight;
	    int widthMode = MeasureSpec.getMode(widthMeasureSpec);
	    int widthSize = MeasureSpec.getSize(widthMeasureSpec);
	    int heightMode = MeasureSpec.getMode(heightMeasureSpec);
	    int heightSize = MeasureSpec.getSize(heightMeasureSpec);
	    int width;
	    int height;
	    if (widthMode == MeasureSpec.EXACTLY) {
	        width = widthSize;
	    } else if (widthMode == MeasureSpec.AT_MOST) {
	        width = Math.min(desiredWidth, widthSize);
	    } else {
	        width = desiredWidth;
	    }
	    if (heightMode == MeasureSpec.EXACTLY) {
	        height = heightSize;
	    } else if (heightMode == MeasureSpec.AT_MOST) {
	        height = Math.min(desiredHeight, heightSize);
	    } else {
	        height = desiredHeight;
	    }
		int wspec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
		int hspec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.measure(wspec, hspec);
		}
	    setMeasuredDimension(width, height);		
	    
//		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//		widthSize = 400;
//		heightSize = 200;
//		int wspec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.AT_MOST);
//		int hspec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.AT_MOST);
//		for (int i = 0; i < getChildCount(); i++) {
//			View v = getChildAt(i);
//			v.measure(wspec, hspec);
////			childWidth = /*200;*/ v.getMeasuredWidth();
////			childHeight = /*200;*/ v.getMeasuredHeight();
//		}
//		setMeasuredDimension(widthSize, heightSize);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int width = r - l;
		int column = 0;
		int row = 0;
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.layout(column * childWidth, row * childHeight, (column + 1) * childWidth, (row + 1) * childHeight);
			column++;
			if ((column + 1) * childWidth > width) {
				column = 0;
				row++;
			}
		}
	}

}
