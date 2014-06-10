package com.example.recentsmenu;

import java.io.OutputStream;

import android.content.Context;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

public class Setactiv extends PreferenceActivity implements OnPreferenceChangeListener, OnPreferenceClickListener{

	CheckBoxPreference mHideRecents;
	ListPreference mColumn, mItem;
	Preference mColor;
    Context mContext;

	public void onCreate(Bundle ofChocolateCake) {
		super.onCreate(ofChocolateCake);
		mContext = this.getApplicationContext();
		addPreferencesFromResource(R.xml.preferences);

		mHideRecents = (CheckBoxPreference) findPreference("hide_samsung_recents");
		mHideRecents.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference what_pref, Object newValue) {
		// TODO Auto-generated method stub
		//Check what preference changed
		if (what_pref == mHideRecents) {
			int getNum = (Boolean) newValue? 1:0 ;
			Settings.System.putInt(mContext.getContentResolver(), "hide_samsung_recents", getNum);
		}			
		return true;
	}

	@Override
	public boolean onPreferenceClick(Preference what_pref) {
		return false;
	}
}