package de.mp3rc.viewcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import de.mp3rc.R;
import de.mp3rc.viewcomponents.PlayerButton;


public class AddButton extends PlayerButton implements OnClickListener {

	private Paint paint;
	private int OFFSET = 10;
	private int HEIGHT = PlayerButton.HEIGHT + 20;
	
	public AddButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public AddButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public AddButton(Context context) {
		super(context);
		init();
	}

	private void init() {
		buttons.add(this);
		paint = new Paint();
		setText("");
		setOnClickListener(this);
		}		

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setWidth(HEIGHT*2+10);
		this.setHeight(HEIGHT/2);
		this.setBackgroundColor(bgColor);
		paint.setColor(mainColor);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		canvas.drawLine(HEIGHT/4 , OFFSET, HEIGHT/4, HEIGHT/2-OFFSET, paint);
		canvas.drawLine(OFFSET, HEIGHT/4, HEIGHT/2-OFFSET, HEIGHT/4, paint);
		paint.setTextSize(HEIGHT/3);
		paint.setStrokeWidth(2);
		paint.setStyle(Paint.Style.FILL);
		canvas.drawText(getContext().getString(R.string.enqueueBtn), HEIGHT/2, HEIGHT/2-OFFSET, paint);
	}
	@Override
	public void onClick(View v) {
		System.out.println("Adding");
		/*if(SongAdapter.SongCursor.moveToFirst());
			System.out.println(SongAdapter.SongCursor.getString(1));
			while (SongAdapter.SongCursor.moveToNext()) {
				System.out.println(SongAdapter.SongCursor.getString(1));				
			}*/
	}
	
}
