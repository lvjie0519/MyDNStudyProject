package com.example.mydnstudyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mydnstudyproject.framework.chapter10.SkinMainTestActivity;
import com.example.mydnstudyproject.high.grade.ui.UIMainActivity;
import com.example.mydnstudyproject.interview.zly.ZhongNanYuanMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openHighGradeUIActivity(View view) {
        UIMainActivity.startActicity(this);
    }

    public void openZhongNanYuanActivity(View view) {
        ZhongNanYuanMainActivity.startActivity(this);
    }

    public void openSkinTestActivity(View view) {
        SkinMainTestActivity.startActivity(this);
    }
}
