package de.mp3rc.playLogic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class PlayList {
	
	public static TreeMap<Integer, String> Playlist= new TreeMap<Integer,String>(); 
	public static HashMap<Integer, String> ShuffleList = new HashMap<Integer,String>();
	public static TreeMap<Integer, String> AllSongs= new TreeMap<Integer,String>(); 
	
	public static int songIndex = 0;
	
	private boolean isPlaying = false;
	private boolean isRepeat = false;
	private boolean isShuffle = false;
	private static PlayList instance = null;
	
	public static PlayList getInstance(){
		if(instance == null) return new PlayList();
		return instance;
	}
	
	private PlayList(){
		instance = this;
		getAllSongs();
	}
	
	private void getAllSongs() {
		for(int i = 0; i< 100; i++){
			AllSongs.put(i, "Song"+i);
			Playlist.put(i, "Song"+i);
			ShuffleList.put(i, "Song"+i);
		}
		shuffleList();
	}
	
	private void shuffleList() {
		ArrayList<Integer> vs = new ArrayList<Integer>(ShuffleList.keySet());
		Collections.shuffle(vs);
		for(int i = 0;i<vs.size();i++){
			ShuffleList.put(i, ShuffleList.get(vs.get(i)));
		}
	}


	public void addSongToListend(String path) {
		Playlist.put(Playlist.size()+1,path);
	}
	
	public String getNextSong(){
		incSongIndex();
		if(isShuffle){ 
			return ShuffleList.get(songIndex);
			}
		return Playlist.get(songIndex);
	}

	public String getPrevSong() {
		decSongIndex();
		if(isShuffle){ 
			return ShuffleList.get(songIndex);
			}
		return Playlist.get(songIndex);
	}
	
	private void incSongIndex() {
		songIndex = (songIndex+1)%Playlist.size(); 
		
	}
	private void decSongIndex() {
		songIndex--;
		if(songIndex<0)
			songIndex = Playlist.size()-1;
		
	}

	public static void clearList() {
		Playlist.clear();
	}

	public void moveAfterFirst(String path){

	}


	public boolean isEmpty() {
		return Playlist.isEmpty();
	}

	public String getRandomSong() {
		if(!isEmpty()){
			int i =(int) (Math.random() * Playlist.size());
			System.out.println("return RandomSong: "+Playlist.get(i));
			return Playlist.get(i);
		}
		return null;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public boolean isRepeat() {
		return isRepeat;
	}

	public void setRepeat(boolean isRepeat) {
		this.isRepeat = isRepeat;
	}

	public boolean isShuffle() {
		return isShuffle;
	}

	public void setShuffle(boolean isShuffle) {
		this.isShuffle = isShuffle;
	}

}

