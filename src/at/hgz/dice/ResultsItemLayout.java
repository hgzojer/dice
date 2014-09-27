package at.hgz.dice;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ResultsItemLayout extends ViewGroup {
	
	private int childWidth;
	private int childHeight;

	public ResultsItemLayout(Context context) {
		super(context);
	}

	public ResultsItemLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ResultsItemLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int wspec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.AT_MOST);
		int hspec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.AT_MOST);
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			v.measure(wspec, hspec);
			childWidth = v.getMeasuredWidth();
			childHeight = v.getMeasuredHeight();
		}
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int width = r - l;
		int column = 0;
		int row = 0;
		for (int i = 0; i < getChildCount(); i++) {
			View v = getChildAt(i);
			if (column > 0) {
				if ((column + 1) * childWidth > width) {
					column = 0;
					row++;
				}
			}
			v.layout(column * childWidth, row * childHeight, (column + 1) * childWidth, (row + 1) * childHeight);
		}
	}

}
