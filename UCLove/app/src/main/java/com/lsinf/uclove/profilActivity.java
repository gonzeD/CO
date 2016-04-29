package com.lsinf.uclove;

import android.os.Bundle;

/**
 * Created by damien on 28/04/16.
 */
public class profilActivity extends baseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.profile_activity);
        createNavigationMenu();
    }
}
