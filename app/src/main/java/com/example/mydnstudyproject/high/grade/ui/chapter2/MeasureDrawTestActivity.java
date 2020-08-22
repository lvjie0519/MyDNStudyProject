package com.example.mydnstudyproject.high.grade.ui.chapter2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mydnstudyproject.R;

public class MeasureDrawTestActivity extends Activity {

    public static void startActicity(Context context){
        Intent intent = new Intent(context, MeasureDrawTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_draw_test);
    }
}
