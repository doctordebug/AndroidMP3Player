package de.mp3rc.viewcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class RepeatButton extends PlayerButton{
	
	private static final int WIDTH 	=	40;
	private static final int HEIGHT =	40;
	//BG-Color of Player
	private Paint paint;
	private boolean repeat;
	
	public RepeatButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public RepeatButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public RepeatButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		setText("");
		repeat = false;
		PlayerButton.buttons.add(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setBackgroundColor(bgColor);
		paint.setTextSize(HEIGHT);
		if(!repeat)
			paint.setColor(mainColor);
		else
			paint.setColor(mainColor);
		canvas.drawText("R", WIDTH, HEIGHT, paint);
	}
	
}
