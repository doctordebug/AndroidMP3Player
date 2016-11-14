package de.mp3rc.utils;

import de.mp3rc.R;
import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.MediaStore;

public class CursorUtilities {
	
	private Context mcontext;
	private final String[] SongProj = {
			MediaStore.Audio.Media.DISPLAY_NAME,
			MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Video.Media.SIZE, 
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.ALBUM,
			MediaStore.Audio.Media.DURATION
			};
	private final String[] ArtistProj = {
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.ARTIST_ID,
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media.DISPLAY_NAME,
			MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Video.Media.SIZE
			};
	private final String[] AlbumProj = {
			MediaStore.Audio.Media.ALBUM ,
			MediaStore.Audio.Media.ALBUM_ID,
			MediaStore.Audio.Media.DATA,
			MediaStore.Audio.Media._ID,
			MediaStore.Audio.Media.ARTIST,
			MediaStore.Audio.Media.DISPLAY_NAME,
			MediaStore.Video.Media.SIZE
			};
	private String[] FileProj ={
			"DISTINCT "+MediaStore.Files.FileColumns.PARENT,
			MediaStore.Files.FileColumns.DATA,
			MediaStore.Files.FileColumns.MEDIA_TYPE,
			MediaStore.Files.FileColumns._ID,
			MediaStore.Files.FileColumns.TITLE
	};
	
	private String[] FileFolderProj ={
			MediaStore.Files.FileColumns.TITLE,
			MediaStore.Files.FileColumns.DATA,
			MediaStore.Files.FileColumns.PARENT,
			MediaStore.Files.FileColumns._ID,
			MediaStore.Files.FileColumns.MEDIA_TYPE,
			};

	public CursorUtilities (Context mcontext) {
		this.mcontext = mcontext;
	}

	public Cursor getSongs(String Song) {
		if(Song == null) return  mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, SongProj , null, null, null);
		return mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, SongProj , Song, null, null); 
	}

	@SuppressLint("InlinedApi")
	public Cursor getAlbums(String Album) {
		if(Album == null) return mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, AlbumProj, "1=1) GROUP BY ("+MediaStore.Audio.Media.ALBUM, null, null);
		return mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, AlbumProj, Album+") GROUP BY ("+MediaStore.Audio.Media.ALBUM, null, null);
	}

	public Cursor getVisibleAlbums(){
		int ids[] = getHiddenIDs();
		if(ids == null) return getAlbums(null);
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for(Integer i: ids){
			sb.append(MediaStore.Audio.Media.ALBUM_ID);
			sb.append(" != \"");
			sb.append(i);
			sb.append("\" AND ");
			j=i;
		}
		sb.append(MediaStore.Audio.Media.ALBUM_ID);
		sb.append(" != \"");
		sb.append(j);
		sb.append("\" ) GROUP BY (");
		sb.append(MediaStore.Audio.Media.ALBUM);
		//String where = "1=1) GROUP BY ("+MediaStore.Audio.Media.ALBUM;
		return mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, AlbumProj, sb.toString(), null, null);
	}
	
	public Cursor getArtists(String Artist) {
		if(Artist == null) return mcontext.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ArtistProj, "1=1) GROUP BY ("+MediaStore.Audio.Media.ARTIST, null, null);
		return null;
	}

	public Cursor getFolder(String Path) {
		Uri uri = MediaStore.Files.getContentUri("external");
		if(Path == null){
		String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + " = "+ MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO+") GROUP BY ("+MediaStore.Files.FileColumns.PARENT;
		return mcontext.getContentResolver().query(uri ,FileProj, selection, null, null);
		}
		return mcontext.getContentResolver().query(uri ,FileProj, Path, null, null);
	}
	
	public Cursor getFiles(String parent){
		Uri uri = MediaStore.Files.getContentUri("external");
		String selection = MediaStore.Files.FileColumns.MEDIA_TYPE + " = "+ MediaStore.Files.FileColumns.MEDIA_TYPE_AUDIO+" AND "+MediaStore.Files.FileColumns.PARENT+" = \""+parent+"\"";
		return mcontext.getContentResolver().query(uri ,FileFolderProj, selection, null, null);
	}

	public Cursor getFirstSong(){
		Cursor c =  getSongs(null);
		if(c.moveToFirst())return   c;
		return null;
	}
	
	public String getFirstSongName(){
		return getFirstSong().getString(0);
	}
	
	public String findArtWorkPathByAlbumId(int albumID) {
		if(albumID <0) return null;
		  Cursor cursor = mcontext.getContentResolver().query(ContentUris.withAppendedId(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumID),new String[] { MediaStore.Audio.AlbumColumns.ALBUM_ART },  null,  null, null); 
		  if (cursor.moveToFirst()) {  
			    String albumArtUri = cursor.getString(0);  
			    if(albumArtUri != null){ 
			    	cursor.close();
			    	return albumArtUri;
			    }
			}  
		  cursor.close();
		  return null;
	}
	
	public String findAlbumNameByAlbumId(int albumID) {
		if(albumID <0) return null;
		  Cursor cursor = mcontext.getContentResolver().query(ContentUris.withAppendedId(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, albumID),new String[] { MediaStore.Audio.AlbumColumns.ALBUM },  null,  null, null); 
		  if (cursor.moveToFirst()) {  
			    String albumName = cursor.getString(0);  
			    if(albumName != null){ 
			    	cursor.close();
			    	return albumName;
			    }
			}  
		  cursor.close();
		  return null;
	}
	
	public int[] getHiddenIDs() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mcontext);
		String values = prefs.getString(mcontext.getString(R.string.key_albums), null);
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

	public void printCursor(Cursor songCursor) {
		int i = 1;
		if(songCursor == null) System.out.println("Cursor is null!");
		if(!songCursor.moveToFirst()) System.out.println("! songCursormove to null");
		System.out.println(i+". ");
		for(int j = 0; j<songCursor.getColumnCount(); j++)
			System.out.print(songCursor.getString(j)+"|");
		while (songCursor.moveToNext()) {
			for(int j = 0; j<songCursor.getColumnCount(); j++)
				System.out.print(songCursor.getString(j)+"|");			
		}
	}

	public String findArtWorkPathByArtistId(int a_id) {
		if(a_id <0) return null;
		  Cursor cursor = getAlbums(MediaStore.Audio.Media.ARTIST_ID+" = \""+a_id+"\""); 
		  if (cursor.moveToFirst()) {  
			    int albumId = cursor.getInt(1);  
			    if(albumId > 0){ 
			    	cursor.close();
			    	return findArtWorkPathByAlbumId(albumId);
			    }
			}  
		  cursor.close();
		  return null;
	}
}
