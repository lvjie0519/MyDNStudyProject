package com.example.mydnstudyproject.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static boolean copyFile(String srcPath, String desPath){

        if(TextUtils.isEmpty(srcPath) || TextUtils.isEmpty(desPath)){
            return false;
        }

        File srcFile = new File(srcPath);
        if(!srcFile.exists() || !srcFile.isFile()){
            return false;
        }

        File desFile = new File(desPath);
        if(!desFile.exists()){
            try {
                desFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int byteread = 0;
            inStream = new FileInputStream(srcFile); //读入原文件
            fs = new FileOutputStream(desFile);
            byte[] buffer = new byte[1024];
            while ( (byteread = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteread);
            }
        } catch (IOException e) {
            return false;
        } finally {
            if(inStream != null){
                try {
                    inStream.close();
                } catch (IOException e) {
                }
            }
            if(fs != null){
                try {
                    fs.flush();
                    fs.close();
                } catch (IOException e) {
                }
            }
        }

        return true;
    }

    public static boolean copyAssetFile(Context context, String assetFileName, String desPathName){
        if(context == null){
            return false;
        }

        if(TextUtils.isEmpty(desPathName)){
            return false;
        }

        boolean result = true;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            File outFile =new File(desPathName);
            if(outFile.exists()){
                outFile.delete();
                outFile.createNewFile();
            }else{
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();
            }

            is=context.getAssets().open(assetFileName);
            fos = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int byteCount;
            while ((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }finally {

            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                }
            }

            if(fos != null){
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                }
            }
        }

        return result;
    }

}
