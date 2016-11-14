package de.mp3rc.fragments;

import java.util.Arrays;

import de.mp3rc.R;
import de.mp3rc.adapter.AlbumAdapter;
import de.mp3rc.utils.CursorUtilities;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.GridView;

public class album_fragment extends Fragment {

    private static GridView gv;
	public static int num =2 ;
	private AlbumAdapter aa;
	private CursorUtilities cu;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragement_port, container, false);
        cu = new CursorUtilities(getActivity().getApplicationContext());
        aa = new AlbumAdapter(rootView.getContext(),getArguments().getInt("width"));
        gv = (GridView)rootView.findViewById(R.id.albumGrid);
        gv.setNumColumns(num);
		gv.setAdapter(aa);
		registerForContextMenu(gv);
        return rootView;
    }
	
	@Override
	public void onResume() {
		if(AlbumAdapter.datachanged){
			aa.albumCursor = cu.getVisibleAlbums();
			aa.notifyDataSetChanged();
			AlbumAdapter.datachanged = false;
			}
		super.onResume();
	}
	
	public static GridView getAlbumGrid(){
		return gv;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if(v.getId() == R.id.albumGrid)
			getActivity().getMenuInflater().inflate(R.menu.mp3_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		final long position = info.position;  
		String data = aa.getItem((int)position);
		switch (item.getItemId()) {
		case R.id.play:
			System.out.println("play album");
			break;
		case R.id.hide:
			System.out.println("hide stuff");
			hideAlbum(Integer.parseInt(data));
			break;
		case R.id.change_picture:
			System.out.println("change pic");
			break;

		default:
			break;
		}
		
		return super.onContextItemSelected(item);
	}

	private void hideAlbum(int albumID) {
		System.out.println(albumID);
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
		String values = prefs.getString(getString(R.string.key_albums), null);
		
		values = addValue(values,albumID);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(getString(R.string.key_albums), values);
		editor.commit();
		
		aa.albumCursor = cu.getVisibleAlbums();
		aa.notifyDataSetChanged();
	}

	private String addValue(String values, int albumID) {
		if(values == null) return albumID+";";
		String[] arr = values.split(";");
		if(Arrays.asList(arr).contains(albumID+""))
			return values;
		else
			return values+albumID+";";
	}
}
