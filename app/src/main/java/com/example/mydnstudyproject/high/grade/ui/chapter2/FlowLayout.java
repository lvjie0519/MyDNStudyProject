package com.example.mydnstudyproject.high.grade.ui.chapter2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

import com.example.mydnstudyproject.utils.DisplayUtil;

public class FlowLayout extends ViewGroup {


    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 1、这里的宽高具体是指哪里的宽高？
     *  可以查看 ViewGroup中的 getChildMeasureSpec， 最终结构与父View模式和自身View模式有关系
     *
     * 2、父view为LinearLayout  onMeasure 为什么会执行两次？ 父view为RelativeLayout onMeasure 为什么会执行四次？
     *  https://www.jianshu.com/p/733c7e9fb284
     *  onMeasure  onLayout onDraw 执行几次与系统版本有关， 不同版本执行次数不一样，具体需要看ViewRootImpl#performTraversals()
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        Log.i("lvjie", "onMeasure widthMode="+widthMode+"  widthSize="+ DisplayUtil.px2dip(getContext(), widthSize)
                +"  heightMode="+heightMode+"  heightSize="+DisplayUtil.px2dip(getContext(), heightSize));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i("lvjie", "onLayout changed="+changed+" l="+l+"  t="+t
                +"  r="+r+"  b="+b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("lvjie", "onDraw...");
    }
}
