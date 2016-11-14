package de.mp3rc.adapter;

import de.mp3rc.R;
import de.mp3rc.fragments.SquareImageItem;
import de.mp3rc.utils.CursorUtilities;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {

	public static class ViewHolder {
		public TextView text;
		public SquareImageItem image;
		public int position;
	}

	private LayoutInflater inflater;
	private CursorUtilities cu;
	public Cursor albumCursor;
	public static boolean datachanged = false;
	private int size;
	

	public AlbumAdapter(Context mContext, int width) {
    	inflater = LayoutInflater.from(mContext);
		cu = new CursorUtilities(mContext);
		//albumCursor = cu.getAlbums(null);
		albumCursor = cu.getVisibleAlbums();
		this.size = width;
	}
	
   	@Override
	public int getCount() {
		return albumCursor.getCount();
	}

	@Override
	public String getItem(int position) {
		if(albumCursor.moveToFirst() && albumCursor.moveToPosition(position))
			return albumCursor.getString(1);
		return null;
	}

	public String getAlbumName(int position) {
		if(albumCursor.moveToFirst() && albumCursor.moveToPosition(position))
			return albumCursor.getString(0);
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
        String path =  cu.findArtWorkPathByAlbumId(getAlbumID(position));

        if(v == null){
        	v =inflater.inflate(R.layout.gridview_item, parent, false);
        	holder = new ViewHolder();
			holder.text = (TextView) v.findViewById(R.id.text);
			holder.image = (SquareImageItem) v.findViewById(R.id.picture);
			holder.position = position;
			v.setTag(holder);
        }else{
        	holder = (ViewHolder) v.getTag();
        }
        holder.image.id = albumCursor.getInt(1);
        holder.image.name = albumCursor.getString(5);
        
        if(path != null){
        	//holder.image.setImageBitmap(BitmapFactory.decodeFile(path));
        	new ImageLoader(this.size).load(path, holder.image);
        }else{
        	holder.image.setImageResource(R.drawable.ic_launcher);
        }
        holder.text.setText(getAlbumName(position));
        return v;
	}
	
	private int getAlbumID(int position) {
		if(albumCursor.moveToFirst() && albumCursor.moveToPosition(position))
			return albumCursor.getInt(1);
		return -1;
	}
	
}
