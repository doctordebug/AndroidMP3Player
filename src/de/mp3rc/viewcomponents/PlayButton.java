package de.mp3rc.viewcomponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

public class PlayButton extends PlayerButton{

	private static final int WIDTH = 80;
	private static final int OFFSET_LEFT = 20;
	//BG-Color of Player
	private boolean play;
	private Paint paint;
	
	public PlayButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public PlayButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public PlayButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		buttons.add(this);
		paint = new Paint();
		play = false;
		setText("");
	}
	
	@SuppressLint("DrawAllocation")
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(WIDTH+OFFSET_LEFT);
		this.setHeight(HEIGHT);
		this.setBackgroundColor(bgColor);
		paint.setColor(mainColor);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		if(!play){
			Path path = new Path();
			path.setFillType(Path.FillType.EVEN_ODD);
			path.moveTo(OFFSET_LEFT,0);
			path.lineTo(OFFSET_LEFT, HEIGHT);
			path.lineTo(WIDTH+OFFSET_LEFT, (int)(HEIGHT/2));
			path.lineTo(OFFSET_LEFT,0);
			path.close();
			canvas.drawPath(path, paint);
		}else{
			canvas.drawRect(OFFSET_LEFT, 0, (int)(WIDTH/3)+OFFSET_LEFT, HEIGHT,paint);
			canvas.drawRect(OFFSET_LEFT+(int)(WIDTH/3)*2, 0, WIDTH+OFFSET_LEFT, HEIGHT,paint);
		}
	}

}
