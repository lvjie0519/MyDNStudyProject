package com.example.mydnstudyproject.high.grade.ui.chapter3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 *
 */

public class LinearGradientTextView extends AppCompatTextView {
    private TextPaint mPaint;


    //LinearGradient线性渲染，   X,Y,X1,Y1四个参数只定位效果，不定位位置
    private LinearGradient mLinearGradient ;
    private Matrix mMatrix;

    private float mTranslate;
    private float DELTAX = 20;

    public LinearGradientTextView(Context context) {
        super(context);
    }

    public LinearGradientTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    int row;
    int curRow = 1;
    /**
     * This is called during layout when the size of this view has changed. If
     * you were just added to the view hierarchy, you're called with the old
     * values of 0.
     *
     * @param w Current width of this view.
     * @param h Current height of this view.
     * @param oldw Old width of this view.
     * @param oldh Old height of this view.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Log.i("lvjie", "onSizeChanged...");

        // 拿到TextView的画笔
        mPaint = getPaint();
        String text = getText().toString();
        float textWidth = mPaint.measureText(text);      // 测量文字的宽度
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float height = fontMetrics.bottom - fontMetrics.top;    // 测量文字的高度
        float size = getTextSize();         // 文字的大小 px
        row = (int) (height / size);        // 文字显示了几行?  并不是


        // 3个文字的宽度
        int gradientSize = (int) (textWidth / text.length() * 3);

        /**
         * Create a shader that draws a linear gradient along a line.
         *
         * @param x0           The x-coordinate for the start of the gradient line
         * @param y0           The y-coordinate for the start of the gradient line
         * @param x1           The x-coordinate for the end of the gradient line
         * @param y1           The y-coordinate for the end of the gradient line
         * @param colors       The colors to be distributed along the gradient line
         * @param positions    May be null. The relative positions [0..1] of
         *                     each corresponding color in the colors array. If this is null,
         *                     the the colors are distributed evenly along the gradient line.
         * @param tile         The Shader tiling mode
         */
        // 从左边-gradientSize开始，即左边距离文字gradientSize开始渐变
        mLinearGradient = new LinearGradient(-gradientSize, size * curRow, 0, size * curRow, new int[]{
                0x22ffffff, 0xffffffff, 0x22ffffff}, null, Shader.TileMode.CLAMP
        );

        mPaint.setShader(mLinearGradient);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("lvjie", "onDraw...");

        mTranslate += DELTAX;
        float textWidth = getPaint().measureText(getText().toString());
        //到底部进行返回
        if(mTranslate > textWidth + 1 || mTranslate < 1){
            mTranslate = 0;
            curRow++;
            if(curRow > row){
                curRow = 0;
            }
        }

        mMatrix = new Matrix();
        mMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
        postInvalidateDelayed(50);

    }
}
