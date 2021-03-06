package com.example.mydnstudyproject.high.grade.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydnstudyproject.R;
import com.example.mydnstudyproject.high.grade.ui.chapter2.MeasureDrawTestActivity;
import com.example.mydnstudyproject.high.grade.ui.chapter3.GradientTestActivity;
import com.example.mydnstudyproject.high.grade.ui.chapter4.PaintXfermodeTestActivity;
import com.example.mydnstudyproject.high.grade.ui.chapter5.Chapter5MainTestActivity;

public class UIMainActivity extends AppCompatActivity {

    public static void startActicity(Context context){
        Intent intent = new Intent(context, UIMainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uimain);


    }

    public void openMeasureDrawTestActivity(View view) {
        MeasureDrawTestActivity.startActicity(this);
    }

    public void openGradientTestActivity(View view) {
        GradientTestActivity.startActicity(this);
    }

    public void openPaintXfermodeTestActivity(View view) {
        PaintXfermodeTestActivity.startActicity(this);
    }

    public void openCanvasTestActivity(View view) {
        Chapter5MainTestActivity.startActivity(this);
    }
}
