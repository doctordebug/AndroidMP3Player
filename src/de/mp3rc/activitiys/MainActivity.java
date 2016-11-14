package de.mp3rc.activitiys;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import de.mp3rc.R;
import de.mp3rc.adapter.TabsAdapter;
import de.mp3rc.fragments.album_fragment;
import de.mp3rc.fragments.artists_fragment;
import de.mp3rc.playLogic.PlayService;
import de.mp3rc.playLogic.PlayService.LocalBinder;
import de.mp3rc.utils.ScreenUtilities;
import de.mp3rc.utils.SettingsUtilities;
import de.mp3rc.viewcomponents.PlayerButton;

public class MainActivity extends FragmentActivity implements TabListener{

	private static final double PLAYER_HEIGHT = 2 * PlayerButton.HEIGHT ;
	public LinearLayout p_view;
	public static PlayService player;
	private boolean bounded;
	
	private ServiceConnection conn = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {
			player = null;
			bounded = false;
		}
		public void onServiceConnected(ComponentName name, IBinder service) {
			bounded = true;
			LocalBinder binder = (LocalBinder) service;
			player = binder.getServiceInstance();
		}
	};
	
	private ViewPager viewPager;
	private ActionBar actionBar;
	private TabsAdapter mAdapter;
	private int colCount;
	private ScreenUtilities su;
	
	protected void onStart() {
		super.onStart();
		//load Settings
		SettingsUtilities.refreshGUI(this);
		//load Songs
		Intent i = new Intent(this, PlayService.class);
		bindService(i, conn, BIND_AUTO_CREATE);
	}
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_main);
		su = new ScreenUtilities(getWindowManager().getDefaultDisplay());

		setUpPlayer();
		setSettings();
		setUpFragmentHolder();
    }
   
    
    
	private void setUpFragmentHolder() {
		 // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        //TODO: colcount
        mAdapter = new TabsAdapter(getSupportFragmentManager(), getApplicationContext(),(int)(su.getScreenWidth()/colCount));
        viewPager.setAdapter(mAdapter);
 
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayUseLogoEnabled(false);
        // Adding Tabs
        for (String tab_name : TabsAdapter.getTabNames()) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	 
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
            public void onPageScrolled(int arg0, float arg1, int arg2) {}
            public void onPageScrollStateChanged(int arg0) {}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_preferences:
			Intent settings = new Intent(this, SettingsActivity.class);
			startActivity(settings);
			return true;
		case R.id.opt_visualizer:
			Intent visualizer = new Intent(this, VisualizerActivity.class);
			startActivity(visualizer);
			return true;
		case R.id.opt_eq:
			startActivity(new Intent(this, EQActivity.class));
			return true;
		case R.id.opt_music:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	@Override
	protected void onStop() {
		super.onStop();
		if(bounded){
			unbindService(conn);
			bounded = false;
		}
	}

	private void setUpPlayer() {
		//Setup the height
		p_view = (LinearLayout)findViewById(R.id.playerBackground);
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)p_view.getLayoutParams();
		params.height =(int)(PLAYER_HEIGHT);
		p_view.setLayoutParams(params);
		
	}

	public void playAction(View btn){
		System.out.println("PLAY");
	}
	public void nextAction(View btn){
		System.out.println("NEXT");
	}
	public void prevAction(View btn){
		System.out.println("PREV");
	}
	public void repeatAction(View btn){
		System.out.println("REP");
	}
	public void shuffleAction(View btn){
		System.out.println("SHUFFLE");
	}
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());			
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setSettings();
	}

	private void setSettings() {
		this.colCount = SettingsUtilities.columnCount;
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.playerBackground);
		ll.setBackgroundColor(SettingsUtilities.bg1Color);
		if(album_fragment.getAlbumGrid() != null)
			album_fragment.getAlbumGrid().setBackgroundColor(SettingsUtilities.bg2Color);
		if(artists_fragment.getArtistView() != null)
			artists_fragment.getArtistView().setBackgroundColor(SettingsUtilities.bg2Color);
			
		
	}
	
}
