package com.example.mydnstudyproject;

import android.app.Application;

import com.example.mydnstudyproject.framework.chapter.plugin.PluginManager;
import com.example.mydnstudyproject.interview.zly.db.AppDatabase;
import com.example.skin.core.SkinManager;
import com.raizlabs.android.dbflow.config.DatabaseConfig;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        FlowManager.init(new FlowConfig.Builder(this)
//                .addDatabaseConfig(DatabaseConfig.builder(AppDatabase.class)
//                        .databaseName("AppDatabase")
//                        .build())
//                .build());
        FlowManager.init(new FlowConfig.Builder(this).build());

        SkinManager.init(this);
        // 插件化初始化
        PluginManager.getInstance().init(this);
    }
}
