package com.example.mydnstudyproject.framework.chapter.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydnstudyproject.R;

public class PluginTestActivity extends AppCompatActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PluginTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_test);
    }

    public void loadPluginApk(View view) {
        String fileName = "plugin.apk";
        boolean success1 = PluginManager.getInstance().downloadPluginApk(fileName);
        boolean success2 = PluginManager.getInstance().loadPluginApk(fileName);
        if(success1 && success2){
            Toast.makeText(this, "加载成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "加载失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickInPlugin(View view) {
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("className", PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }
}
