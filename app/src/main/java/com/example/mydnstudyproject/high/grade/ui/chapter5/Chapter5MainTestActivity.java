package com.example.mydnstudyproject.high.grade.ui.chapter5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mydnstudyproject.R;

public class Chapter5MainTestActivity extends Activity {

    private ImageView iv;
    private int[] mImgIds = new int[] { //7个
            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline,

            R.mipmap.avft,
            R.mipmap.box_stack,
            R.mipmap.bubble_frame,
            R.mipmap.bubbles,
            R.mipmap.bullseye,
            R.mipmap.circle_filled,
            R.mipmap.circle_outline
    };
    private int[] mImgIds_active = new int[] {
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active,
            R.mipmap.avft_active, R.mipmap.box_stack_active, R.mipmap.bubble_frame_active,
            R.mipmap.bubbles_active, R.mipmap.bullseye_active, R.mipmap.circle_filled_active,
            R.mipmap.circle_outline_active
    };

    public Drawable[] revealDrawables;
    protected int level = 5000;
    private GallaryHorizonalScrollView hzv;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, Chapter5MainTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(new MyView(this));

//        setContentView(R.layout.activity_chapter5_main_test);
//        initData();
//        initView();
    }

    private void initData(){
        revealDrawables = new Drawable[mImgIds.length];
    }

    private void initView()
    {
        for (int i = 0; i < mImgIds.length; i++)
        {
            RevealDrawable rd = new RevealDrawable(
                    getResources().getDrawable(mImgIds[i]),
                    getResources().getDrawable(mImgIds_active[i]),
                    RevealDrawable.HORIZONTAL);
            revealDrawables[i] = rd;
        }
        hzv = (GallaryHorizonalScrollView)findViewById(R.id.hsv);
        hzv.addImageViews(revealDrawables);
    }
}
