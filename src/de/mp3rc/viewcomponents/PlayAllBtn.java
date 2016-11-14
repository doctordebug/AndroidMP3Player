package de.mp3rc.viewcomponents;

import de.mp3rc.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

public class PlayAllBtn extends PlayerButton {

	private Paint paint;
	private int OFFSET = 10;

	public PlayAllBtn(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public PlayAllBtn(Context context, AttributeSet attrs) {
		super(context,attrs);
		init();
	}
	public PlayAllBtn(Context context) {
		super(context);
		init();
	}

	private void init() {
		buttons.add(this);
		paint = new Paint();
		setText("");	

	}
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(HEIGHT*2+10);
		this.setHeight(HEIGHT/2);
		this.setBackgroundColor(bgColor);
		paint.setColor(mainColor);
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
		
		Path path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(OFFSET ,0);
		path.lineTo(OFFSET, HEIGHT/2);
		path.lineTo(HEIGHT/2, (int)(HEIGHT/4));
		path.lineTo(OFFSET,0);
		path.close();
		canvas.drawPath(path, paint);
		
		paint.setTextSize(HEIGHT/3+OFFSET/2);
		paint.setStrokeWidth(2);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawText(getContext().getString(R.string.playAllBtn), HEIGHT/2+OFFSET, HEIGHT/2-OFFSET, paint);
	}
	
}
