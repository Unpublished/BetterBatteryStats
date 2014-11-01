/*
 * Copyright (C) 2011-2014 asksven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.asksven.betterbatterystats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asksven.betterbatterystats.R;

public class AboutActivity extends BaseActivity
{

    private static final String TAG = "AboutStatsActivity";
    public static final String MARKET_LINK ="market://details?id=com.asksven.betterbatterystats";
    public static final String TWITTER_LINK ="https://twitter.com/#!/asksven";
    

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		String theme = sharedPrefs.getString("theme", "1");
		if (theme.equals("1"))
		{
			this.setTheme(R.style.Theme_Bbs);
		}
		else
		{
			this.setTheme(R.style.Theme_Bbs);
		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		//toolbar.setLogo(R.drawable.ic_launcher);
		toolbar.setTitle(getString(R.string.label_about));

	    setSupportActionBar(toolbar);
	    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        
        // retrieve the version name and display it
        try
        {
        	PackageInfo pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        	TextView versionTextView = (TextView) findViewById(R.id.textViewVersion);
        	versionTextView.setText(pinfo.versionName);
        	
        	TextView editionTextView = (TextView) findViewById(R.id.textViewEdition);
        	if (pinfo.packageName.endsWith("_xdaedition"))
        	{
        		editionTextView.setText("xda edition");
        	}
        	else
        	{
        		editionTextView.setText("");
        	}
        }
        catch (Exception e)
        {
        	Log.e(TAG, "An error occured retrieveing the version info: " + e.getMessage());
        }
        
        final Button buttonRate = (Button) findViewById(R.id.buttonRate);
        buttonRate.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                openURL(MARKET_LINK);
            }
        });
        final Button buttonFollow = (Button) findViewById(R.id.buttonTwitter);
        buttonFollow.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                openURL(TWITTER_LINK);
            }
        });

        
    }   
    
    public void openURL( String inURL )
    {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

        startActivity( browse );
    }
}