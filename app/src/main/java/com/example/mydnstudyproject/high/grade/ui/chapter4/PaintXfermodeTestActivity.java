package com.example.mydnstudyproject.high.grade.ui.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mydnstudyproject.R;

/**
 * https://www.jianshu.com/p/4cbeffbf2f19
 * https://www.jianshu.com/p/713584d018fc
 * 1.滤镜
 *      针对于图片的颜色进行处理
 *    1.1.图形概念
 *    1.2.颜色通道,颜色模式
 *    1.3.颜色矩阵
 *    1.4.矩阵运算
 * 2.xfermode  图像混合
 *    2.1.概念及使用
 *    我们能通过使用Xfermode能够完成图像组合的效果,
 *    2.2.模式分类
 * 	DTS
 * 	SRC
 * 	OTHER
 * 	在实际使用当中先画完目标图之后再进行xfermode设置，然后用原图去进行交汇然后他们两张图就会按照模式规则进行处理
 */
public class PaintXfermodeTestActivity extends AppCompatActivity implements View.OnClickListener{


    public static void startActicity(Context context){
        Intent intent = new Intent(context, PaintXfermodeTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //滤镜
        //setContentView(new FilterView(this));


        //xfermode
        //setContentView(R.layout.activity_paint_xfermode_test);

        //dts
//        setContentView(R.layout.activity_paint_xfermode_test1);
//        findViewById(R.id.rounddstinbtn).setOnClickListener(this);
//        findViewById(R.id.invertdstinbtn).setOnClickListener(this);
//        findViewById(R.id.irregularwavebtn).setOnClickListener(this);
//        findViewById(R.id.heartbitbtn).setOnClickListener(this);

        //other
//        setContentView(R.layout.activity_paint_xfermode_test2);
//        findViewById(R.id.lightbookview).setOnClickListener(this);
//        findViewById(R.id.twitterview).setOnClickListener(this);

        //src
        setContentView(R.layout.activity_paint_xfermode_test3);
        findViewById(R.id.roundsrcin).setOnClickListener(this);
        findViewById(R.id.invertsrcin).setOnClickListener(this);
        findViewById(R.id.eraserview).setOnClickListener(this);
        findViewById(R.id.guaguaview).setOnClickListener(this);
        findViewById(R.id.roundsrcatop).setOnClickListener(this);
        findViewById(R.id.invertsrcatop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        hideAllViews();
        switch (v.getId()) {
            //src
            case R.id.roundsrcin:
                findViewById(R.id.roundsrcin_view).setVisibility(View.VISIBLE);
                break;
            case R.id.invertsrcin:
                findViewById(R.id.invertsrcin_view).setVisibility(View.VISIBLE);
                break;
            case R.id.eraserview:
                findViewById(R.id.eraserview_view).setVisibility(View.VISIBLE);
                break;
            case R.id.guaguaview:
                findViewById(R.id.guaguaview_view).setVisibility(View.VISIBLE);
                break;
            case R.id.roundsrcatop:
                findViewById(R.id.roundsrcatop_view).setVisibility(View.VISIBLE);
                break;
            case R.id.invertsrcatop:
                findViewById(R.id.invertsrcatop_view).setVisibility(View.VISIBLE);
                break;

            //other
//            case R.id.lightbookview:
//                findViewById(R.id.lightbookview_view).setVisibility(View.VISIBLE);
//                break;
//            case R.id.twitterview:
//                findViewById(R.id.twitterview_view).setVisibility(View.VISIBLE);
//                break;

//            dts
//            case R.id.rounddstinbtn:
//                findViewById(R.id.roundimage).setVisibility(View.VISIBLE);
//                break;
//            case R.id.invertdstinbtn:
//                findViewById(R.id.invertimg).setVisibility(View.VISIBLE);
//                break;
//            case R.id.irregularwavebtn:
//                findViewById(R.id.irregularwaveview).setVisibility(View.VISIBLE);
//                break;
//            case R.id.heartbitbtn:
//                findViewById(R.id.heartbitview).setVisibility(View.VISIBLE);
//                break;
        }

    }

    private void hideAllViews() {
        //src
        findViewById(R.id.roundsrcin_view).setVisibility(View.GONE);
        findViewById(R.id.invertsrcin_view).setVisibility(View.GONE);
        findViewById(R.id.eraserview_view).setVisibility(View.GONE);
        findViewById(R.id.guaguaview_view).setVisibility(View.GONE);
        findViewById(R.id.roundsrcatop_view).setVisibility(View.GONE);
        findViewById(R.id.invertsrcatop_view).setVisibility(View.GONE);

//        other
//        findViewById(R.id.lightbookview_view).setVisibility(View.GONE);
//        findViewById(R.id.twitterview_view).setVisibility(View.GONE);

//        dts
//        findViewById(R.id.roundimage).setVisibility(View.GONE);
//        findViewById(R.id.invertimg).setVisibility(View.GONE);
//        findViewById(R.id.irregularwaveview).setVisibility(View.GONE);
//        findViewById(R.id.heartbitview).setVisibility(View.GONE);
    }
}
