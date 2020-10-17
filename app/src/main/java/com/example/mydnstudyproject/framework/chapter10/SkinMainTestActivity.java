package com.example.mydnstudyproject.framework.chapter10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydnstudyproject.R;

public class SkinMainTestActivity extends Activity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SkinMainTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_main_test);
    }

    public void onClickOpenSkinSettingPage(View view) {
        SkinTestOneActivity.startActivity(this);
    }
}
