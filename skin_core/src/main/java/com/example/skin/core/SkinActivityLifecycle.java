package com.example.skin.core;

import android.app.Activity;
import android.app.Application;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;


import androidx.core.view.LayoutInflaterCompat;

import com.example.skin.core.utils.SkinThemeUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class SkinActivityLifecycle implements Application.ActivityLifecycleCallbacks {

    private Map<Activity, SkinLayoutInflaterFactory> mLayoutInflaterFactories = new HashMap<>();


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.i("lvjie", "SkinActivityLifecycle-->onActivityCreated... start");
        /**
         *  更新状态栏
         */
        SkinThemeUtils.updateStatusBarColor(activity);
        /**
         * 更新字体
         */
        Typeface typeface = SkinThemeUtils.getSkinTypeface(activity);
        /**
         *  更新布局视图
         */
        //获得Activity的布局加载器
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        try {
            //Android 布局加载器 使用 mFactorySet 标记是否设置过Factory
            //如设置过抛出一次
            //设置 mFactorySet 标签为false
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(layoutInflater, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用factory2 设置布局加载工程
        SkinLayoutInflaterFactory skinLayoutInflaterFactory = new SkinLayoutInflaterFactory
                (activity, typeface);
        LayoutInflaterCompat.setFactory2(layoutInflater, skinLayoutInflaterFactory);
        mLayoutInflaterFactories.put(activity, skinLayoutInflaterFactory);
        SkinManager.getInstance().addObserver(skinLayoutInflaterFactory);

        Log.i("lvjie", "SkinActivityLifecycle-->onActivityCreated... end");
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        SkinLayoutInflaterFactory observer = mLayoutInflaterFactories.remove(activity);
        SkinManager.getInstance().deleteObserver(observer);
    }


    public void updateSkin(Activity activity) {
        SkinLayoutInflaterFactory skinLayoutInflaterFactory = mLayoutInflaterFactories.get(activity);
        skinLayoutInflaterFactory.update(null, null);
    }
}
