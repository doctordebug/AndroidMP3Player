package de.mp3rc.adapter;

import de.mp3rc.R;
import de.mp3rc.utils.CursorUtilities;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArtistAdapter extends BaseAdapter {

	
	public static class ViewHolder {
		public TextView text;
		public ImageView image;
		public String path;
		public int position;
	}

	private LayoutInflater inflater;
	private CursorUtilities cu;
	private Cursor artistCursor;
	

	public ArtistAdapter(Context mContext) {
    	inflater = LayoutInflater.from(mContext);
		cu = new CursorUtilities(mContext);
		artistCursor = cu.getArtists(null);
	}
	
   	@Override
	public int getCount() {
		return artistCursor.getCount();
	}

	@Override
	public String getItem(int position) {
		if(artistCursor.moveToFirst() && artistCursor.moveToPosition(position))
			return artistCursor.getString(2);
		return null;
	}

	public String getArtistName(int position) {
		if(artistCursor.moveToFirst() && artistCursor.moveToPosition(position))
			return artistCursor.getString(0);
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
        	v =inflater.inflate(R.layout.gridview_item, parent, false);
        	holder = new ViewHolder();
			holder.text = (TextView) v.findViewById(R.id.text);
			holder.image = (ImageView) v.findViewById(R.id.picture);
			holder.position = position;
			v.setTag(holder);
        }else{
        	holder = (ViewHolder) v.getTag();
        }
        String path = cu.findArtWorkPathByArtistId(getArtistID(position));
        if(path != null)
        	new ImageLoader(400).load(path, holder.image);
        else
        	holder.image.setImageResource(R.drawable.ic_launcher);
        holder.text.setText(getArtistName(position));
        return v;
	}
	
	private int getArtistID(int position) {
		if(artistCursor.moveToFirst() && artistCursor.moveToPosition(position))
			return artistCursor.getInt(1);
		return -1;
	}
	
}
