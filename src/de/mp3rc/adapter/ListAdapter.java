package de.mp3rc.adapter;

import java.util.Arrays;
import java.util.HashSet;

import de.mp3rc.R;
import de.mp3rc.utils.CursorUtilities;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;

public class ListAdapter extends BaseAdapter {
	
	public static class ViewHolder {
		public CheckBox cbox;
		public ImageView image;
		public String path;
		public int id;
		public int position;
	}

	public static int[] ids;
	private LayoutInflater inflater;
	private CursorUtilities cu;
	private int size;
	public static HashSet<ViewHolder> holderSet = new HashSet<ViewHolder>(); 

	public ListAdapter(int[] idsIn, Context context, int size){
		ListAdapter.ids = idsIn;
		System.out.println(Arrays.toString(ids));
		inflater = LayoutInflater.from(context);
		cu = new CursorUtilities(context);
		this.size = size;
	}
	
	@Override
	public int getCount() {
		return ids.length;
	}

	@Override
	public Object getItem(int position) {
		return ids[position];
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
	        	v =inflater.inflate(R.layout.listview_item_restore, parent, false);
	        	holder = new ViewHolder();
	        	holder.cbox =(CheckBox) v.findViewById(R.id.title);
	        	holder.image =(ImageView) v.findViewById(R.id.img);
	        	holder.id = ids[position];
	        	v.setTag(holder);
	        	holderSet.add(holder);
	        }else{
	        	holder = (ViewHolder) v.getTag();
	        }
	    holder.cbox.setText(cu.findAlbumNameByAlbumId(ids[position]));
       	new ImageLoader(size).load(cu.findArtWorkPathByAlbumId(ids[position]),holder.image);
		return v;
	}


	}

