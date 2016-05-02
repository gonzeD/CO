package com.lsinf.uclove;

import android.os.Bundle;

/**
 * Created by damien on 20/04/16.
 */
public class SettingsActivity extends baseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_activity);
         createNavigationMenu();
    }
}
