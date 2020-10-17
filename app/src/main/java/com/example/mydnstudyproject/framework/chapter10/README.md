# 动态换肤（module： skin_core）
## QQ、美团、网易云动态式换肤架构分析

## 手写网易云可动态替换的换肤框架（字体、状态栏换肤、自定义控件、fragment换肤）


# 换肤模式
## 内置换肤
在APK包中存在多种资源图片、颜色等用于换肤时候切换；
一般用于没有其他需求的日间/夜间模式APP；

## 动态换肤
通过运行时动态加载皮肤包

### 换肤流程
采集需要换肤的控件 --> 加载皮肤包 --> 所有可能被换肤的控件

### 换肤原理
android解析xml创建view的步骤：
setContentView -> window.setContentView()(实现类是PhoneWindow)
->mLayoutInflater.inflate() -> inflate -> rInflate … ->createViewFromTag()

```
View createViewFromTag(View parent, String name, Context context, AttributeSet attrs,
            boolean ignoreThemeAttr) {
        ......
        try {
            View view;
            if (mFactory2 != null) {
                view = mFactory2.onCreateView(parent, name, context, attrs);
            } else if (mFactory != null) {
                view = mFactory.onCreateView(name, context, attrs);
            } else {
                view = null;
            }

            if (view == null && mPrivateFactory != null) {
                view = mPrivateFactory.onCreateView(parent, name, context, attrs);
            }

            if (view == null) {
                final Object lastContext = mConstructorArgs[0];
                mConstructorArgs[0] = context;
                try {
                    if (-1 == name.indexOf('.')) {
                        view = onCreateView(parent, name, attrs);
                    } else {
                        view = createView(name, null, attrs);
                    }
                } finally {
                    mConstructorArgs[0] = lastContext;
                }
            }

            return view;
        } catch (InflateException e) {
            throw e;

        }
    }
```

inflate最终调用了createViewFromTag方法来创建View,在这之中用到了factory，
如果factory存在就用factory创建对象，如果不存在就由系统自己去创建。
我们只需要实现我们的Factory然后设置给mFactory2就可以采集到所有的View了，这里是一个Hook点。

所以我们复写了Factory的onCreateView之后，就可以不通过系统层而是自己截获从xml映射的View进行
相关View创建的操作，包括对View的属性进行设置（比如背景色，字体大小，颜色等）以实现换肤的效果。
如果onCreateView返回null的话，会将创建View的操作交给Activity默认实现的Factory的onCreateView处理。

1.使用ActivityLifecycle，尽可能少的去侵入代码，在onActivityCreated中监听每个activity的创建;
2.在 SkinLayoutInflaterFactory中将每个创建的view进行筛选采集;
3.SkinAttribute,将属性符合的view保存起来;


## 其他

从以下日志，可以看到， 注册监听activity回调 优先于 activity执行;
```
2020-10-17 21:15:48.366 25109-25109/com.example.mydnstudyproject I/lvjie: SkinActivityLifecycle-->onActivityCreated... start
2020-10-17 21:15:48.367 25109-25109/com.example.mydnstudyproject I/lvjie: SkinActivityLifecycle-->onActivityCreated... end
2020-10-17 21:15:48.367 25109-25109/com.example.mydnstudyproject I/lvjie: SkinTestOneActivity-->onCreate... before setContentView...
2020-10-17 21:15:48.369 25109-25109/com.example.mydnstudyproject I/lvjie: parent is null...
2020-10-17 21:15:48.370 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.LinearLayout
2020-10-17 21:15:48.370 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.LinearLayout
2020-10-17 21:15:48.373 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.FrameLayout
2020-10-17 21:15:48.374 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.LinearLayout
2020-10-17 21:15:48.375 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.LinearLayout
2020-10-17 21:15:48.378 25109-25109/com.example.mydnstudyproject I/lvjie: parent is android.widget.LinearLayout
2020-10-17 21:15:48.380 25109-25109/com.example.mydnstudyproject I/lvjie: SkinTestOneActivity-->onCreate... after setContentView...
```


# 参考链接
https://blog.csdn.net/hxl517116279/article/details/96581407

