package com.example.mydnstudyproject.framework.chapter.plugin;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.example.mydnstudyproject.utils.FileUtils;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    private static PluginManager INSTANCE;

    private Context mAppContext;
    private String mBasePluginApkPath;      // apk 存放在手机的目录

    private DexClassLoader mDexClassLoader;
    private PackageInfo mPackageInfo;
    private Resources mResources;

    private PluginManager(){}

    public static PluginManager getInstance(){
        if(INSTANCE == null){
            synchronized (PluginManager.class){
                if(INSTANCE == null){
                    INSTANCE = new PluginManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context){
        mAppContext = context.getApplicationContext();
        mBasePluginApkPath = context.getFilesDir()+ File.separator+"plugin";
    }

    public boolean downloadPluginApk(String apkName){
        return FileUtils.copyAssetFile(mAppContext, apkName, mBasePluginApkPath+ File.separator+apkName);
    }

    public boolean loadPluginApk(String apkName){
        String filePath = mBasePluginApkPath+ File.separator+apkName;

        PackageManager packageManager = mAppContext.getPackageManager();
        mPackageInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);

        // activity
        String dex = mAppContext.getFilesDir()+ File.separator+"dex";
        mDexClassLoader = new DexClassLoader(filePath, dex, null, mAppContext.getClassLoader());

        // resource
        try {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
            addAssetPath.invoke(assetManager, filePath);
            mResources = new Resources(assetManager, mAppContext.getResources().getDisplayMetrics(), mAppContext.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String getBasePluginApkPath() {
        return mBasePluginApkPath;
    }

    public PackageInfo getPackageInfo() {
        return mPackageInfo;
    }

    public DexClassLoader getDexClassLoader() {
        return mDexClassLoader;
    }

    public Resources getResources() {
        return mResources;
    }
}
