package com.example.mydnstudyproject.framework.chapter.plugin;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.commonplugin.PluginActivityInterface;

import java.lang.reflect.Constructor;

public class ProxyActivity extends Activity {

    // 插件的一个class全类名
    private String mPluginClassName;

    private PluginActivityInterface mPluginActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPluginClassName = getIntent().getStringExtra("className");

        try {
            Class mClass = getClassLoader().loadClass(mPluginClassName);
            Constructor mConstructor = mClass.getConstructor(new Class[]{});
            Object object = mConstructor.newInstance(new Object[]{});
            mPluginActivity = (PluginActivityInterface) object;
        } catch (Exception e) {
            e.printStackTrace();
        }

        mPluginActivity.attach(this);
        mPluginActivity.onCreate(savedInstanceState);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent target = new Intent(this, ProxyActivity.class);
        target.putExtra("className", intent.getComponent().getClassName());
        super.startActivity(target);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPluginActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPluginActivity.onResume();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        mPluginActivity.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPluginActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPluginActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPluginActivity.onDestroy();
    }

}
