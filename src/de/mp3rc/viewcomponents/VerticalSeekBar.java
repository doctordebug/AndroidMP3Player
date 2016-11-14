package de.mp3rc.viewcomponents;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

public class VerticalSeekBar extends SeekBar{

	private int frequenz = 0; 
	private int bandStart = 0;
	private int bandEnd = 0;
	private short band = 0;
	
    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    protected void onDraw(Canvas c) {
        c.rotate(-90);
        c.translate(-getHeight(), 0);
        super.onDraw(c);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                setProgress(getMax() - (int) (getMax() * event.getY() / getHeight()));
                onSizeChanged(getWidth(), getHeight(), 0, 0);
                break;

            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

	public int getFrequenz() {
		return frequenz;
	}

	public void setFrequenz(int frequenz) {
		this.frequenz = frequenz;
	}

	public int getBandStart() {
		return bandStart;
	}

	public void setBandStart(int bandStart) {
		this.bandStart = bandStart;
	}

	public int getBandEnd() {
		return bandEnd;
	}

	public void setBandEnd(int bandEnd) {
		this.bandEnd = bandEnd;
	}

	public short getBand() {
		return band;
	}

	public void setBand(short band) {
		this.band = band;
	}


}
