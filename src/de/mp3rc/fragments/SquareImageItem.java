package de.mp3rc.fragments;

import de.mp3rc.activitiys.DetailActivity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SquareImageItem extends ImageView implements OnClickListener, OnLongClickListener{

	private Context mContext;
	public int id ;
	public String name = "<unknown Album>";
	
	public SquareImageItem(Context context) {
        super(context);
        init(context);

    }

    private void init(Context c) {
        mContext = c;
        setOnClickListener(this);
        setOnLongClickListener(this);
    }

	public SquareImageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SquareImageItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //WICHTIG: macht die ganze sache Quadratisch
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }

	@Override
	public void onClick(View v) {
			Intent i = new Intent(mContext, DetailActivity.class);
			i.putExtra("_ID", id);
			i.putExtra("_NAME", name);
			System.err.println("ID: "+id+"  Name"+name);
			mContext.startActivity(i);
	}

	@Override
	public boolean onLongClick(View v) {
		System.out.println("long Click");
		//LongClick wird in Activity definiert
		return false;
	}

}