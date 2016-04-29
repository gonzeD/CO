package com.lsinf.uclove;

import android.os.Bundle;

/**
 * Created by damien on 28/04/16.
 */
public class preferenceActivity extends baseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_activity);
        createNavigationMenu();
    }
}
