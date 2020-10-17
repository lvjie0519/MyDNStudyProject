package com.example.mydnstudyproject.framework.chapter10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.framework.chapter10.skin.Skin;
import com.example.mydnstudyproject.framework.chapter10.skin.SkinUtils;
import com.example.skin.core.SkinManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SkinTestOneActivity extends Activity {

    /**
     * 从服务器拉取的皮肤表
     */
    private List<Skin> skins = new ArrayList<>();

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SkinTestOneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("lvjie", "SkinTestOneActivity-->onCreate... before setContentView...");
        setContentView(R.layout.activity_skin_test_one);

        skins.add(new Skin("d61a53df84e96a8122dc9a06796dcd95", "1111111.skin", "app_skin-debug" +
                ".apk"));

        Log.i("lvjie", "SkinTestOneActivity-->onCreate... after setContentView...");
    }

    public void onClickSkinChange(View view) {

        //使用第0个皮肤
        Skin skin = skins.get(0);
        selectSkin(skin);
        //换肤
        SkinManager.getInstance().loadSkin(skin.path);
    }

    public void onClickSkinRestore(View view) {
        SkinManager.getInstance().loadSkin(null);
    }

    /**
     * 下载皮肤包, 将文件拷贝到 skin.getSkinFile 目录下的文件
     */
    private void selectSkin(Skin skin) {
        File theme = new File(getFilesDir(), "theme");
        // 防止 theme 是文件，不是文件夹
        if (theme.exists() && theme.isFile()) {
            theme.delete();
        }
        theme.mkdirs();
        File skinFile = skin.getSkinFile(theme);
        if (skinFile.exists()) {
            Log.e("SkinActivity", "皮肤已存在,开始换肤");
            return;
        }
        Log.e("SkinActivity", "皮肤不存在,开始下载");
        FileOutputStream fos = null;
        InputStream is = null;
        //临时文件
        File tempSkin = new File(skinFile.getParentFile(), skin.name + ".temp");
        try {
            fos = new FileOutputStream(tempSkin);
            //假设下载皮肤包, 从asserts下取文件
            is = getAssets().open(skin.url);
            byte[] bytes = new byte[10240];
            int len;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            //下载成功，将皮肤包信息insert已下载数据库
            Log.e("SkinActivity", "皮肤包下载完成开始校验");
            //皮肤包的md5校验 防止下载文件损坏(但是会减慢速度。从数据库查询已下载皮肤表数据库中保留md5字段)
            if (TextUtils.equals(SkinUtils.getSkinMD5(tempSkin), skin.md5)) {
                Log.e("SkinActivity", "校验成功,修改文件名。");
                tempSkin.renameTo(skinFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            tempSkin.delete();
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
