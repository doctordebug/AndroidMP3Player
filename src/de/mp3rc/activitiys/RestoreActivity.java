package de.mp3rc.activitiys;

import de.mp3rc.R;
import de.mp3rc.adapter.AlbumAdapter;
import de.mp3rc.adapter.ListAdapter;
import de.mp3rc.adapter.ListAdapter.ViewHolder;
import de.mp3rc.utils.ScreenUtilities;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class RestoreActivity extends Activity {

	private ScreenUtilities su;
	private ListAdapter la = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restore);
		ListView list = (ListView)findViewById(R.id.restoreList);
		int[] arr = getIDs();
		su = new ScreenUtilities(getWindowManager().getDefaultDisplay());
		if(arr != null){
			la = new ListAdapter(arr,this,(int)(su.getScreenWidth()/6));
			list.setAdapter(la);
			}
		else{
			Toast.makeText(this, R.string.noalbumshidden, Toast.LENGTH_LONG).show();
		}
	}

	public void restoreAction(View v){
		for(ViewHolder vh: ListAdapter.holderSet){
			if(vh.cbox.isChecked()){
				System.out.println(vh.id);
				removeIdFromPrefs(vh.id);
				vh.cbox.setChecked(false);
			}
		}
	}
	
	private void removeIdFromPrefs(int id) {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String values = prefs.getString(getString(R.string.key_albums), null);
		System.out.println("before: "+values);
		if(values == null)
			return;
		String[] strValues = values.split(";");
		StringBuilder sb = new StringBuilder();
		for(String str: strValues){
			try{
				int strId = Integer.parseInt(str);
				if(id != strId){
					sb.append(str);
					sb.append(";");
				}
			}catch(Exception e){ continue;}
		}
		System.out.println("after: " +sb.toString());
		
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(getString(R.string.key_albums), sb.toString());
		editor.commit();
		
		ListAdapter.ids = getIDs();
		if(la != null) la.notifyDataSetChanged();
		AlbumAdapter.datachanged = true;
	}

	public int[] getIDs() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		String values = prefs.getString(getString(R.string.key_albums), null);
		if(values == null)
			return null;
		String[] strValues = values.split(";");
		int[] result = new int[strValues.length];
		for(int i=0; i<result.length ;i++){
			System.err.println(result[i]);
			try{
				result[i] = Integer.parseInt(strValues[i]);
				}catch(Exception e){
					continue;
				}
			}
		return result;
	}
}
