package de.mp3rc.playLogic;


public class Logic {

	private static PlayList pl = PlayList.getInstance();
	private String CurrentSong=null;
	
	public void playSong(){
		pl.setPlaying(true);
		if(CurrentSong == null) return;
		System.out.println("playing "+CurrentSong);
	}
	
	
	public void playNextSong() {
		if(pl.isRepeat()){
			playSong();
			return;
			}
		CurrentSong = pl.getNextSong();
		playSong();
	}

	public void playPrevSong(){
		if(pl.isRepeat()){
			playSong();
			return;
			}
		CurrentSong = pl.getPrevSong();
		playSong();
	}
	
	public void pauseSong(){
		if(pl.isPlaying()){
			System.out.println("Pause Song: "+CurrentSong);
			pl.setPlaying(false);
			}
		else{ 
			System.out.println("Continue Song:"+CurrentSong);
			pl.setPlaying(true);
		}
	}
	
	public void stopSong(){
		System.out.println("Stop Song"+CurrentSong);
		//seek to 0
		pl.setPlaying(false);
	}
}
