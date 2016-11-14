package de.mp3rc.adapter;

import de.mp3rc.R;
import de.mp3rc.utils.CursorUtilities;
import de.mp3rc.utils.SettingsUtilities;
import de.mp3rc.utils.Utilities;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SongAdapter extends BaseAdapter {

	private CursorUtilities cu;
	public static Cursor SongCursor;
	private LayoutInflater inflater;
	
	public static class ViewHolder {
		public TextView title;
		public TextView duration;
		public int position;
	}

	public SongAdapter(Context mContext ,int id) {
		this.cu = new CursorUtilities(mContext);
		SongCursor = cu.getSongs(MediaStore.Audio.Media.ALBUM_ID+" = \""+id+"\" ");
		inflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return SongCursor.getCount();
	}

	@Override
	public Object getItem(int position) {
		if(SongCursor.moveToFirst() && SongCursor.moveToPosition(position))
			return SongCursor.getString(0);
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 View v = convertView;
		 ViewHolder holder;
	        if(v == null)
	        {
	        	v =inflater.inflate(R.layout.listview_item_detail, parent, false);
	        	holder = new ViewHolder();
	        	holder.title =(TextView) v.findViewById(R.id.title);
	        	holder.duration = (TextView) v.findViewById(R.id.duration);
	        	v.setTag(holder);
	        }else{
	        	holder = (ViewHolder) v.getTag();
	        }
	        SongCursor.moveToPosition(position);
	        holder.title.setText(SongCursor.getString(0));
	        int durInMilli = SongCursor.getInt(7);
	        holder.duration.setText(Utilities.milliSecondsToTimer(durInMilli));
	        
	        holder.title.setTextColor(SettingsUtilities.textColor);
	        holder.duration.setTextColor(SettingsUtilities.textColor);
		return v;
	}

}
