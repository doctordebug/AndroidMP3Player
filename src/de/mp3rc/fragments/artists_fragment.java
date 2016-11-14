package de.mp3rc.fragments;

import de.mp3rc.R;
import de.mp3rc.adapter.ArtistAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class artists_fragment extends Fragment {
	
    private static GridView gv;
	public static int num =2 ;
    
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.home_fragement_port, container, false);
        gv = (GridView) rootView.findViewById(R.id.albumGrid);
        gv.setNumColumns(num);
		gv.setAdapter(new ArtistAdapter(rootView.getContext()));
        return rootView;
    }
    
    public static GridView getArtistView() {
    	return gv;
		
	}
    
}
