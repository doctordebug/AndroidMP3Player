package de.mp3rc.viewcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class ShuffleButton extends PlayerButton{
	
	private static final int WIDTH 	=	40;
	private static final int HEIGHT =	40;
	private boolean shuffle;
	private Paint paint;
	
	public ShuffleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public ShuffleButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public ShuffleButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		shuffle=false;
		setText("");
		PlayerButton.buttons.add(this);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT);
		this.setBackgroundColor(bgColor);
		paint.setTextSize(HEIGHT);
		if(!shuffle)
			paint.setColor(mainColor);
		else
			paint.setColor(mainColor);
		canvas.drawText("S", WIDTH, HEIGHT, paint);
	}
	


}
