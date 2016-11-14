package de.mp3rc.viewcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class PlayerSeekBar extends SeekBar {

	public PlayerSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public PlayerSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public PlayerSeekBar(Context context) {
		super(context);
	}

	
	@Override
	protected synchronized void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	public void setDesign(int i){
		setBackgroundColor(Colors.getDesign(i)[0]);
		invalidate();
	}
	
}
