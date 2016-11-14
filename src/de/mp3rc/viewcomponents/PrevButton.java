package de.mp3rc.viewcomponents;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

public class PrevButton extends PlayerButton{
	
	private static final int WIDTH 	=	160;
	private static final int HEIGHT = (int) (PlayerButton.HEIGHT * 0.7);
	private static final int BUFFER_TOP =	 (int) (PlayerButton.HEIGHT * 0.15);
	private Paint paint;
	
	public PrevButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public PrevButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public PrevButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		setText("");
		PlayerButton.buttons.add(this);
	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(WIDTH);
		this.setHeight(HEIGHT+(2* BUFFER_TOP));
		this.setBackgroundColor(bgColor);
		paint.setColor(mainColor);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		Path path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(0,(int)(HEIGHT/2)+ BUFFER_TOP);
		path.lineTo((int)(WIDTH/2),  BUFFER_TOP);
		path.lineTo((int)(WIDTH/2), (int)(HEIGHT)+ BUFFER_TOP);
		path.lineTo(0,(int)(HEIGHT/2)+ BUFFER_TOP);
		path.moveTo((int)(WIDTH/2),(int)(HEIGHT/2)+ BUFFER_TOP);
		path.lineTo(WIDTH, HEIGHT+ BUFFER_TOP);
		path.lineTo(WIDTH, + BUFFER_TOP);
		path.lineTo((int)(WIDTH/2),(int)(HEIGHT/2)+ BUFFER_TOP);
		path.close();
		canvas.drawPath(path, paint);
		
	}
	
}
