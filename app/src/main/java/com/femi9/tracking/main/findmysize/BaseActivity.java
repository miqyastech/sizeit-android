package com.femi9.tracking.main.findmysize;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

abstract public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void start(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity));
    }

    public void startWithClearStack(Class<? extends Activity> activity) {
        startActivity(new Intent(this, activity).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        this.finish();
    }
}