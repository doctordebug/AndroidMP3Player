package de.mp3rc.playLogic;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;


public class PlayService extends Service {

	IBinder mBinder = new LocalBinder();
	public static MediaPlayer mp = new MediaPlayer();
	
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
    public class LocalBinder extends Binder {
        public PlayService getServiceInstance() {
            return PlayService.this;
        }
    }
	
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("Service on Create");
	}

	public void dosth(String string){
		System.out.println("dosth in Service: "+string);
	}
	
	public void onDestroy() {
        super.onDestroy();
	}

}
