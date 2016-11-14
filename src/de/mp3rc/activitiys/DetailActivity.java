package de.mp3rc.activitiys;

import de.mp3rc.R;
import de.mp3rc.adapter.ImageLoader;
import de.mp3rc.adapter.SongAdapter;
import de.mp3rc.utils.CursorUtilities;
import de.mp3rc.utils.ScreenUtilities;
import de.mp3rc.utils.SettingsUtilities;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DetailActivity extends Activity {

	private ListView SongList;
	private CursorUtilities cu;
	private ScreenUtilities screenUtils;
	private ImageView albumView;
	private RelativeLayout bgLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		
		cu = new CursorUtilities(this);
		screenUtils = new ScreenUtilities(getWindowManager().getDefaultDisplay());
		
		albumView = (ImageView) findViewById(R.id.detail_image);
		bgLayout = (RelativeLayout)findViewById(R.id.detail_layout);
		bgLayout.setBackgroundColor(SettingsUtilities.bg1Color);

		int id = getIntent().getExtras().getInt("_ID");
		String path = cu.findArtWorkPathByAlbumId(id);
		if(path != null)
			new ImageLoader(screenUtils.getScreenWidth()).load(path, albumView);
		
		setTitle(cu.findAlbumNameByAlbumId(id));
		setTitleColor(SettingsUtilities.textColor);
		
		SongList = (ListView)findViewById(R.id.songList);
		SongList.setAdapter(new SongAdapter(getApplicationContext(),id));
		SongList.setBackgroundColor(SettingsUtilities.bg1Color);
		
	}
}
