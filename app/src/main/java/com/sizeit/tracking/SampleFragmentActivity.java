package com.sizeit.tracking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.sizeit.tracking.fragment.SampleFragment;

public class SampleFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_fragment_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SampleFragment.newInstance())
                    .commitNow();
        }
    }
}