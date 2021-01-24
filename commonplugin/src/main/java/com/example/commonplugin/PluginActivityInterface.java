package com.example.commonplugin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public interface PluginActivityInterface {

    public void attach(Activity proxyActivity);

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(@NonNull Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackPressed();

    public void setContentView(View view);

    public void setContentView(@LayoutRes int layoutResID);

    public <T extends View> T findViewById(int id);

    public void startActivity(Intent intent);

}
