package de.mp3rc.utils;

import de.mp3rc.R;
import de.mp3rc.fragments.album_fragment;
import de.mp3rc.fragments.artists_fragment;
import de.mp3rc.viewcomponents.Colors;
import de.mp3rc.viewcomponents.PlayerButton;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

public class SettingsUtilities {

	public static int columnCount = 3;
	public static int bg1Color = Color.BLACK;
	public static int bg2Color = Color.GRAY;
	public static int btnColor = Color.WHITE;
	public static int textColor = Color.WHITE;
	
	private static SharedPreferences prefs = null;
	private static Context context;
	
	public static void refreshGUI(Context mContext){
		context = mContext;
		prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
		setRows(prefs);
		setColors(prefs);
	}
	
	
	private static void setRows(SharedPreferences prefs) {
		String rowcount = prefs.getString(context.getApplicationContext().getString(R.string.key_rowcount), "2");
		int design_key = Integer.parseInt(rowcount);
		if(album_fragment.getAlbumGrid() != null){
			album_fragment.getAlbumGrid().setNumColumns(design_key);
		}else{
			album_fragment.num = design_key;
		}
		if(artists_fragment.getArtistView() != null){
			artists_fragment.getArtistView().setNumColumns(design_key);
		}else{
			artists_fragment.num = design_key;
		}
		columnCount = design_key;
	}
	
	private static void setColors(SharedPreferences prefs) {
			String design = prefs.getString(context.getApplicationContext().getString(R.string.key_design), "1");
			int design_key = Integer.parseInt(design);
			bg1Color = Colors.getDesign(design_key)[0];
			btnColor = Colors.getDesign(design_key)[1];
			bg2Color = Colors.getDesign(design_key)[2];
			textColor = Colors.getDesign(design_key)[3];
			PlayerButton.changeDesign(design_key);
		return;
		}
	}
