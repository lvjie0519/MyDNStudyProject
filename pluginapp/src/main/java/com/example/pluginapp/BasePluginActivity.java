package com.example.pluginapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.commonplugin.PluginActivityInterface;

public abstract class BasePluginActivity implements PluginActivityInterface {

    protected Activity mHostActivity;

    @Override
    public void attach(Activity proxyActivity) {
        this.mHostActivity = proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if(this.mHostActivity != null){
            this.mHostActivity.setContentView(view);
        }
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return mHostActivity.findViewById(id);
    }

    @Override
    public void startActivity(Intent intent) {
        mHostActivity.startActivity(intent);
    }

    @Override
    public void setContentView(int layoutResID) {
        if(this.mHostActivity != null){
            this.mHostActivity.setContentView(layoutResID);
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackPressed() {

    }
}
