package com.example.mydnstudyproject.high.grade.ui.chapter3;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mydnstudyproject.R;

/**
 * 高级渲染
 *    渲染概念：
 *
 *    1.着色器：
 *
 * Shader  ---着色器，Canvas的drawXXXX这个方法是画具体的形状，画笔的shader定义的就是图形的着色和外观
 *
 *
 * BitmapShader--位图图像渲染，用BitMap对绘制的图形进行渲染着色，简单来说是用图片对图形进行贴图
 * 		圆形头像，放大镜效果
 *
 * 	TileMode 拉伸形式
 * 		CLAMP --是拉伸最后一个像素铺满
 * 		MIRROR ---是横向纵向不足处不断翻转镜像平铺
 * 		REPEAT ---类似电脑壁纸，横向纵向不足的重复放置
 *
 * LinearGradient--线性渲染
 * 		霓虹灯文字，倒影图片
 *
 * SweepGradient----渐变渲染/梯度渲染
 *
 * 		雷达扫描效果
 *
 * RadialGradient----环形渲染
 * 		水波纹效果
 *
 * ComposeShader----组合渲染
 *
 * 		可以自己发挥想象去组合一下
 *
 *
 * 作业：  水波纹效果
 *
 * 	倒影图片
 *
 * 	雷达效果
 */
public class GradientTestActivity extends Activity {

    public static void startActicity(Context context){
        Intent intent = new Intent(context, GradientTestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new RadarGradientView(this));
        setContentView(new ZoomImageView(this));
//        setContentView(new MyGradientView(this));
//        setContentView(R.layout.activity_gradient_test);
    }
}
