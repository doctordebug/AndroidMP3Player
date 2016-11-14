package de.mp3rc.utils;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

public class ScreenUtilities {
	
	private Display display;

	public ScreenUtilities(Display display){
		this.display = display;
	}
	
	public int getScreenHeight() {
		int height = 0;
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
		    height = getScreenHeight_newApi();
		}else{
			height = getScreenHeight_oldApi();
		}
		return height;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private int getScreenHeight_newApi() {
		Point size = new Point();
		display.getSize(size);
		return size.y;
		}

	@SuppressWarnings("deprecation")
	private int getScreenHeight_oldApi() {
		return display.getHeight();  
	}

	public int getScreenWidth() {
		int height = 0;
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)    {
		    height = getScreenWidth_newApi();
		}else{
			height = getScreenWidth_oldApi();
		}
		return height;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private int getScreenWidth_newApi() {
		Point size = new Point();
		display.getSize(size);
		return size.x;
		}

	@SuppressWarnings("deprecation")
	private int getScreenWidth_oldApi() {
		return display.getWidth();  
	}

}
