package de.mp3rc.activitiys;

import de.mp3rc.R;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class SettingsActivity extends PreferenceActivity{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 // Display the fragment as the main content.
    
		FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        PreferenceFragment_1 mPrefsFragment = new PreferenceFragment_1();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
	}

	 public static class PreferenceFragment_1 extends PreferenceFragment
	    {
	        @Override
	        public void onCreate(final Bundle savedInstanceState)
	        {
	            super.onCreate(savedInstanceState);
	            addPreferencesFromResource(R.xml.pref_screen);
	            Preference manageAlbums = (Preference)findPreference(getString(R.string.key_albums));
	            manageAlbums.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
					public boolean onPreferenceClick(Preference preference) {
						Intent i = new Intent(getActivity().getApplicationContext(), RestoreActivity.class); 
						startActivity(i);
						return false;
					}
				});
	        }
	        
	        
	    }


}
