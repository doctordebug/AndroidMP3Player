package de.mp3rc.viewcomponents;

import java.util.HashSet;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.Button;

public class PlayerButton extends Button {

	public static HashSet<PlayerButton> buttons = new HashSet<PlayerButton>();
	public static int bgColor = Color.WHITE;
	public static int mainColor = Color.BLACK;
	public static int HEIGHT = 80;
	
	public PlayerButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	public PlayerButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public PlayerButton(Context context) {
		super(context);
	}

	public static void changeDesign(int number){
		bgColor = Colors.getDesign(number)[0];
		mainColor = Colors.getDesign(number)[1];
		for(PlayerButton pb: buttons){
			pb.invalidate();
		}
	}
	
}
