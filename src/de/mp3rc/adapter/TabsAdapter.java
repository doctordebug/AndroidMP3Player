package de.mp3rc.adapter;

import de.mp3rc.fragments.album_fragment;
import de.mp3rc.fragments.artists_fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class TabsAdapter extends FragmentStatePagerAdapter{

	private final static String[] TABNAMES  	= {"Albums", "Artists"};
	private album_fragment 		album_frag; 	
	private artists_fragment 	artist_frag	= 	new artists_fragment();
	private album_fragment 		default_frag = 	new album_fragment();
	private int width;
	
	public TabsAdapter(FragmentManager fm, Context c, int w) {
		super(fm);
		this.width = w;
	}
	
	@Override
	public Fragment getItem(int index) {
		switch (index) {
        case 0:
        	album_frag 	= 	new album_fragment();
        	Bundle args = new Bundle();
        	args.putInt("width", (int)(width/2));
        	album_frag.setArguments(args);
            return album_frag;
        case 1:
        	return artist_frag;
        }
		return  default_frag;
	}

	@Override
	public int getCount() {
		return TABNAMES.length;
	}

	public static String[] getTabNames() {
		return TABNAMES;
	}
}
