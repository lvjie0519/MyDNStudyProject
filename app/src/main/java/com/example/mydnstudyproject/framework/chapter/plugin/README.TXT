插件化

插件化：动态加载技术

动态加载技术：应用程序运行时，动态加载程序中原本不存在的可执行文件并允许这些文件里的代码逻辑。

插件化作用：主要用来解决应用越来越大以及模块的解耦。
    1、65536的限制；

实现方案：
    1、插桩式；
    2、Hook技术；
    3、反射；

Android 9.0开始， 系统有限制反射， 所以反射的方式基本已经被淘汰，现在大部分都采用前两种方案。

1、插桩式
    1.1 利用DexClassLoader 来加载 插件 apk 里面的 dex 文件
    1.2 通过反射来new 一个 Resources类，该类用来加载 插件 apk 里面的资源
    1.3 宿主实现了一个ProxyActivity 代理类， 每次启动插件里面的activity，实际是启动ProxyActivity，
        然后通过反射new一个插件的activity，把ProxyActivity 传递给插件的activity，插件activity执行生命周期的过程
        其实就是ProxyActivity执行生命周期的过程；

    1.4 DexClassLoader 与 PathClassLoader区别？
        DexClassLoader 可以加载 已安装的、sdk卡的 dex文件
        PathClassLoader 只能加载已安装的 dex文件

2、Hook技术
    钩子函数实际上也是以恶搞处理消息的程序段，通过系统调用，把它挂入系统，在系统没有调用该函数之前，钩子程序就先捕获该消息，
    这样钩子函数先得到控制全。
    1）需要找到hook点；（通常都是静态方法返）
    2）将hook方法放到系统之外执行;

    通俗的说，通过反射获取系统的对象，然后再通过反射修改系统对象的数据，也可以通过动态代理替换系统里面的对象，当系统调用到替换的对象是，对
    部分方法进行一个拦截处理；

native 的插件化方案，多多少少都需要用到反射，获取系统对象或调用系统隐藏方法等等，Android P（9.0）后，Google对反射做了限制，不能调用部分
隐藏的api，添加了灰黑名单，那么Google是如何做到这种限制的呢？

主要是在反射过程中做了拦截。

参考文档：https://www.cnblogs.com/renhui/p/14214996.html

通过反射或者JNI访问非公开接口时会触发警告/异常等，那么不妨跟踪一下反射的流程，看看系统到底在哪一步做的限制。
我们从 java.lang.Class.getDeclaredMethod(String) 看起，这个方法在Java层最终调用到了 getDeclaredMethodInternal
这个native方法，看一下这个方法的源码：

static jobject Class_getDeclaredMethodInternal(JNIEnv* env, jobject javaThis, jstring name, jobjectArray args) {
  ScopedFastNativeObjectAccess soa(env);
  StackHandleScope<1> hs(soa.Self());
  DCHECK_EQ(Runtime::Current()->GetClassLinker()->GetImagePointerSize(), kRuntimePointerSize);
  DCHECK(!Runtime::Current()->IsActiveTransaction());
  Handle<mirror::Method> result = hs.NewHandle(
      mirror::Class::GetDeclaredMethodInternal<kRuntimePointerSize, false>(
          soa.Self(),
          DecodeClass(soa, javaThis),
          soa.Decode<mirror::String>(name),
          soa.Decode<mirror::ObjectArray<mirror::Class>>(args)));
  if (result == nullptr || ShouldBlockAccessToMember(result->GetArtMethod(), soa.Self())) {
    return nullptr;
  }
  return soa.AddLocalReference<jobject>(result.Get());
}

注意那个 ShouldBlockAccessToMember 调用了吗？如果它返回false，那么直接返回nullptr，上层就会抛 NoSuchMethodXXX 异常；
也就触发系统的限制了。于是我们继续跟踪这个方法，这个方法的实现在 java_lang_Class.cc，源码如下：

ALWAYS_INLINE static bool ShouldBlockAccessToMember(T* member, Thread* self)
    REQUIRES_SHARED(Locks::mutator_lock_) {
  hiddenapi::Action action = hiddenapi::GetMemberAction(
      member, self, IsCallerTrusted, hiddenapi::kReflection);
  if (action != hiddenapi::kAllow) {
    hiddenapi::NotifyHiddenApiListener(member);
  }
  return action == hiddenapi::kDeny;
}

毫无疑问，我们应该继续看 hidden_api.cc 里面的 GetMemberAction方法 ：