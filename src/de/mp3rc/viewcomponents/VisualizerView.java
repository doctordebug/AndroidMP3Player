package de.mp3rc.viewcomponents;

import de.mp3rc.utils.SettingsUtilities;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class VisualizerView extends View {


	
	public static VisualizerView instance;
	
    public VisualizerView(Context context) {
        super(context);
        init();
    }

    public VisualizerView(Context con, AttributeSet att, int defStyle){
    	super(con, att, defStyle);
    	init();
    }
    
    public VisualizerView(Context con, AttributeSet att){
    	super(con, att);
    	init();
    }
    
    private void init() {
    	instance = this;
    }

    public void refresh(){
    	this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
    	super.onDraw(canvas);
    	Paint paint = new Paint();
    	setBackgroundColor(SettingsUtilities.bg1Color);
    	paint.setColor(SettingsUtilities.textColor);
    	canvas.drawRect(0, 0, 150, 150, paint);
   
    }
   
}