package de.mp3rc.viewcomponents;

import android.graphics.Color;

public class Colors {

	
	/*
	<string name="design1">Black-White</string>
	<string name="design2">Green-White</string>
	<string name="design3">Pink-White</string>
	<string name="design4">Black-Red</string>
	<string name="design5">White-Red</string>
	<string name="design6">Black-Pink</string>
	<string name="design7">White-Red</string>
	*/
	
	private static int 	black = Color.BLACK,
						white = Color.WHITE,
						green = Color.GREEN,
						red = Color.RED,
						pink = Color.argb(255, 255, 105, 180);

	
	/**
	 * @param number design_key
	 * @return [bg_color][btn_color][bg_color_sec]
	 */
	public static int[] getDesign(int number){
		int[] design = null;
		switch (number) {
		case 1:
			design = new int[] {black, white, changeBgCol(black),white};
			break;
		case 2:
			design = new int[] {green, white, changeBgCol(green), white};
			break;
		case 3:
			design = new int[] {pink, white, changeBgCol(pink), white};
			break;
		case 4:
			design = new int[] {black, red, changeBgCol(black), red};
			break;
		case 5:
			design = new int[] {white, red, changeBgCol(white), white};
			break;
		case 6:
			design = new int[] {black, pink, changeBgCol(black), pink};
		case 7:
			design = new int[] {white, red, changeBgCol(white), red};
		default:
			design = new int[] {black, white, changeBgCol(black), white};
			break;
		}
		
		return design;
	}


	private static int changeBgCol(int col) {
		int blue = Color.blue(col);
		int red = Color.red(col);
		int green = Color.green(col);
		return Color.argb(125, red, green, blue);
	}

}
