# Android 移动应用开发基础教程 微课版

记录阅读 《Android 移动应用开发基础教程 微课版》 ISBN-978-7-115-47309-7 遇到的坑

- android studio version 4.0.1
- android SDK 10.0+

[git仓库](https://github.com/moshuying/androidDev.git)

相关文章
[Java http请求及常见数据交互格式处理](https://moshuying.top/2020/10/09/android-http-request/)
<!-- more -->

# 第一章 环境部署

jetbrains会帮你安好android studio

[下载jetbrains toolbox](https://www.jetbrains.com/toolbox-app/download/download-thanks.html)

安装好后列表里有Android studio 点击安装即可，也可以切换版本，我这里用的时4.0.1版

进入Android studio 后如果你没有安装 android sdk 会让你安装 sdk 这里可能需要科学上网，另外后面程序下载依赖也需要用到科学上网。

# gradle

## 引用包

```gradle
implementation 'com.google.android.material:material:1.2.1'
```

## 设置版本号

```gradle
defaultConfig {
    applicationId "com.example.moshuying"
    minSdkVersion 16
    targetSdkVersion 30
//  在这里修改版本号 versionCode 用来比较版本是否升级，后者用来给用户看的
    versionCode 1
    versionName "1.1.8"

    testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
}
```

# 第二章 核心组件-活动

## 活动是什么

在android中运行任何应用都会看到不同的界面，这些界面及在界面中完成的各种操作，都通过活动完成

活动具有一下特点

- 可以通过返回键退出活动
- 可通过home建返回桌面
- 可在活动中启动另一个界面，此时按返回键可返回前一个活动

一个应用通常包含多个活动，活动之间相对独立，包含多个活动的应用，需要为其指定一个“主”活动，即启动应用时首先打开的活动。

Android 允许启动其他应用中的活动

## 活动的基本操作

为活动绑定自定义视图

通常在活动的`onCreate()`方法中使用`setContentView()`方法来为活动绑定一个视图

## 启动另一个活动

启动活动使用的是`startActivity()`方法

```java
public class MainActivity extends AppCompatActivity {
    @Override
        Button btnStartAnother = (Button)findViewById(R.id.btnStartAnother);// 引用布局中的按钮
        btnStartAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BtnStartAnother.class));
            }
        });
}
```

结束活动则调用`finish()`,为按钮绑定事件监听器后点击按钮调用`finish()`即可实现点击按钮退出活动

```java
public class BtnStartAnother extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_start_activity);
        findViewById(R.id.destory).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}
```


## 在活动中使用Intent

### 显示Intent

直接忽略1-6步，手动创建java class后给class继承`Activity`后会自动引入相关包，后面步骤同理

### 隐式Intent

这一步不要被`android.support.v7.app.AppCompatActivity`吸引了注意力，后面的代码其实还是继承`AppCompatActivity`,参照上面手动输入即可，android studio会自动引入，我这边自动引入为`androidx.appcompat.app.AppCompatActivity`，如果过程中遇到问题，可以编辑`build.gradle`**注意这个文件有两个，选(Module:app)**在`dependencies`中加入一行`compile 'com.android.support:appcompat-v7:26.+'`然后android studio会报错，照着报错修复即可，这一步可能会操作到`Migrate to AndroidX`，android studio也会自动为你备份，不必担心。

**这一步我在代码中做了各种分享功能**，[相关解析在这里](/2020/10/03/android-document-sharing/)，[源代码参考链接](https://github.com/moshuying/androidDev/commit/8a91a9476387dcbc216f86e6ed553fecaa29f89c)

### 使用预定义操作

这里使用隐式Intent打开联系人信息的地方可能不太一样了，参考官网给出的[样例](https://developer.android.google.cn/training/contacts-provider/modify-data#java)

我的代码实现也是参考官网给出的样例,实现也很简单，这里代码去掉了注释，[完整源代码在这里](https://github.com/moshuying/androidDev/blob/8a91a9476387dcbc216f86e6ed553fecaa29f89c/app/src/main/java/com/example/moshuying/PredefinedOperation.java#L28)
```java
findViewById(R.id.editContacts).setOnClickListener(new View.OnClickListener() {
  @Override
  public void onClick(View v) {
    EditText emailAddress = (EditText) findViewById(R.id.email);
    EditText phoneNumber = (EditText) findViewById(R.id.phone);
    EditText name = (EditText) findViewById(R.id.name);
    Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
    intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, emailAddress.getText())
      .putExtra(ContactsContract.Intents.Insert.EMAIL_TYPE,ContactsContract.CommonDataKinds.Email.TYPE_WORK)
      .putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber.getText())
      .putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
      .putExtra(ContactsContract.Intents.Insert.PHONETIC_NAME,name.getText());
//     intent.setData(Uri.parse("content://contacts/people/1"));
    startActivity(intent);
  }
});
```
## 在活动之间传递数据

这一章节本身简单学习了活动的传递方式，重点了解如何在活动中传递数据。

书上的示例比较简单，new完intent后使用putExtra类似key,value的方式存进去就行。

```java
// 传递简单的数据
Intent intent = new Intent(JsonEdit.this,Auto.class);
Bundle bd = new Bundle();
TextView stringKey = findViewById(R.id.bundleKeyString);
String string = stringKey.getText().toString();
bd.putString("String",string);
TextView stringArray = findViewById(R.id.stringArray);
bd.putStringArray("StringArray",stringArray.getText().toString().split(","));
startActivity(intent);

// 传递json
final TextView editText = (TextView) findViewById(R.id.editTextTextMultiLine);
editText.setText("{\\"name\\":\\"moshuying\\",\\"url\\":\\"moshuying.top\\",\\"email\\":\\"1460083332@qq\\",\\"github\\":\\"moshuying.top\\"}");
// 这里踩了个小坑，从editText.getText()后还需要toString()才能正确获取
Intent intent  = new Intent(JsonEdit.this,Auto.class);
intent.putExtra("data",editText.getText().toString());
startActivity(intent);
```

当然在activity中传递数据的方法不止intent的，只是intent比较常用。

Activity之间传递数据的几种方式,参考自这篇[文章](https://blog.csdn.net/ahuier/article/details/8953017),文章比较老了，方法竟然还是这些，甚至阿里巴巴的一些应用也是这样的。

- 使用Intent
- 使用剪切板
- 使用静态变量


Intent可以传递哪些类型的数据，[来源](https://www.nowcoder.com/questionTerminal/c934814c65b049a6992bcbb246711022?from=14pdf)

1. 8种基本数据类型及其数组
2. String（String实现了 Serializable ）/CharSequence实例类型的数据及其数组
3. 实现了Parcelable的对象及其数组
4. 实现了 Serializable 的对象及其数组

看到这里的时候我想到能在activity中获取数据，就能用这些数据动态创建页面，所以顺便看了下动态创建元素的办法。

实际上非常简单,先声明一个LinearLayout

```xml
<LinearLayout
  android:id="@+id/auto_list_item"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:orientation="vertical"
  tools:ignore="MissingConstraints"/>
```

随后在对应的代码里获取到这个元素，再用`addView()`即可动态添加

```java
LinearLayout layout = findViewById(R.id.auto_list_item);
layout.setOrientation(LinearLayout.VERTICAL);
```

想到动态添加元素就觉得可以根据后台请求的数据来生成各种界面，于是就又去了解了一下Android的网络请求方法[相关解析](/2020/10/09/android-document-sharing/)，[关键代码](https://github.com/moshuying/androidDev/blob/b11d9aa6e8ad6390583fb1b3571283e432d9803f/app/src/main/java/com/example/moshuying/network/ProductEntry.java#L107)

既然都动态元素添加了，肯定会遇到添加的元素超过容器大小（超出屏幕）的情况，这时在对应的布局文件外面放一层`ScrollView`即可解决问题。
```xml
<ScrollView
  android:layout_width="match_parent"
  android:layout_height="match_parent"
  android:scrollbars="vertical"
  android:fadingEdge="vertical"
  >
  <LinearLayout
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      android:orientation="vertical">
    </LinearLayout>
</ScrollView>
```

### 获取活动返回的数据

这一小节有一小部分不太一样的地方，启动活动变成了`startActivityforResult`,活动返回时也要用`setResult`设置返回结果。

```java
// mainActivity 启动活动时传入的 `REQUEST_CODE` 值随意设置一个数字不要冲突就行
findViewById(R.id.getActivityBackData).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(JsonEdit.this,Auto.class);
            intent.putExtra("type","callback");
            intent.putExtra("request_code",REQUEST_CODE);
            startActivityForResult(intent,REQUEST_CODE);
        }
    });
// mainActivity设置响应返回值
@Override
protected void onActivityResult(int requestCode,int resultCode,Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    printState();
    if(requestCode == REQUEST_CODE){ // 返回的请求码与当前活动请求码一致时，才执行后继操作
        if(resultCode == RESULT_OK){ // RESULT_OK表示返回的活动已成功处理请求
            TextView textView = findViewById(R.id.showActivityBackData);
            textView.setText("从另一个Activity获取到返回值："+data.getStringExtra("data"));
        }
    }
}
// 启动的活动中注意要  `setResult`
protected void setCallback(){
    final EditText editText = new EditText(this);
    editText.setText("这里填入活动返回的值");
    linearLayout.addView(editText);

    Button button = new Button(this);
    button.setText("输入完成");
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent resintent = new Intent();
            resintent.putExtra("data",editText.getText().toString());
            setResult(RESULT_OK,resintent);
            finish();
        }
    });
    linearLayout.addView(button);
}

```
[完整代码]()

## 活动的生命周期

![这张图可能要时不时的看](/uploads/Android_Activity_Lifecycle.png)

这一章节有个检测活动生命周期的例子，这里我写了个直接获取的函数。

放到类里即可，记得把包名和类名换成你自己的即可
```java
private static final String packageName = "com.example.moshuying";
private static final String className = "JsonEdit";

private static void printState(){
    StackTraceElement[] stes = Thread.currentThread().getStackTrace();
    for (StackTraceElement ste : stes) {
        if ((ste.getClassName().equals(packageName+"."+className)) && (!ste.getMethodName().equals("printState"))) {
            System.out.println("正在执行" + ste.getClassName().replace(packageName+".","") + "." + ste.getMethodName()+"()");
        }
    }
}
```

随后在生命周期对应的地方（其实其他类中的函数都可以）调用一下即可

```java
@Override
protected void onDestroy(){
    super.onDestroy();
    printState();
}
```

最后的效果如图

![](/uploads/Snipaste_2020-10-09_21-09-41.png)

## 代码设置界面元素

比较喜欢用代码生成界面，免去一堆xml难以寻找自己定义的一些东西，同时debug起来也比较麻烦，这里总结一些代码操作ui元素的代码片段

```java
final MaterialTextView textView = new MaterialTextView(this);// 声明UI元素 （TextView button）
RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(MATCH_PARENT,WRAP_CONTENT); // 声明布局参数
layoutParams2.topMargin = 5; // 边距  [top|bottom|left|right]Margin
// 也可以一次性设置多个 RLayoutParams.setMargins(10,170,10,0);
layoutParams2.addRule(RelativeLayout.BELOW,password.getId());// 在某个元素下面
textView.setGravity(Gravity.CENTER_VERTICAL);
textView.setLayoutParams(layoutParams2);
textView.setId(View.generateViewId());// 不直接设置id 生成id 通过getid方法获取id
textView.setTextColor(Color.parseColor("#FF0000"));//直接书写颜色需要解析一下
// button.setBackgroundColor(getResources().getColor(R.color.back));  // 从xml中获取指定id的颜色值
// RelativeLayout.LayoutParams RlayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
// RlayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP); // 添加父级约束

// EditText editText2 = new EditText(this);
// editText2.setText("输入密码");
// editText2.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD); //设置edittext 输入类型

relativeLayout.addView(textView);
```

## 给某个活动设置主题

```xml
<activity android:name=".StartupMode" android:theme="@style/Theme.MaterialComponents"/>
<activity android:name=".StartupMode" android:theme="@style/Theme.MaterialComponents.Light"/>
```

## 创建弹出式对话框

```java
protected void createDialog(){
    MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
    builder.setTitle("布局设置");
    builder.setIcon(R.drawable.ic_learning_material);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);

    final EditText editText = new EditText(this);builder.setView(editText);
    MaterialTextView textView = new MaterialTextView(this);
    textView.setText("请在下面输入1、2或其他数据");

    layout.addView(textView);
    layout.addView(editText);

    builder.setView(layout);
    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            changeLayout(editText.getText().toString());
//                finish(); // 这里无需finish()点击取消什么的会自己关闭掉自己 finish()会退掉整个activity
        }
    });
    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
//                finish();
        }
    });
    builder.setCancelable(true);
    AlertDialog dialog = builder.create();
    dialog.setCanceledOnTouchOutside(true);
    dialog.show();
}
```

# 问题

## Android Studio --“Cannot resolve symbol” 解决办法

Android Studio 无法识别同一个 package 里的其他类，将其显示为红色，但是 compile 没有问题。鼠标放上去后显示 “Cannot resolve symbol XXX”，重启 Android Studio，重新 sync gradle，Clean build 都没有用。

多半是因为 Android Studio 之前发生了错误，某些 setting 出了问题。解决方法如下：

点击菜单中的 “File” -> “Invalidate Caches / Restart”，然后点击对话框中的 “Invalidate and Restart”，清空 cache 并且重启。语法就会正确的高亮了。

# 参考文章

  [maven google 官方库](https://maven.google.com/web/index.html#com.google.android.material:material)
  [material 文档](https://developer.android.google.cn/reference/com/google/android/material/packages)
  [Android Material Design全面解析（一）- MaterialButton篇](https://blog.csdn.net/qq_35775053/article/details/108531983)
  [设置TextView文字居中,代码实现android:layout_gravity](https://www.cnblogs.com/lijunamneg/archive/2013/04/18/3029356.html)
  [HTML 颜色名](https://htmlcolorcodes.com/zh/yanse-ming/)
  [Android 动态设置margin](https://blog.csdn.net/u014649598/article/details/45580461)
  [Android color(颜色) 在XML文件和java代码中](https://blog.csdn.net/chenxu6/article/details/41523943)
  [Android 显示、隐藏状态栏和导航栏](https://juejin.im/post/6844903807046909966)
  [Android修改APP版本号](https://blog.csdn.net/wumingxiaoqiang/article/details/52585530)
  [Android之设置EditText输入类型(setInputType()方法和android:inputType属性)](https://blog.csdn.net/u013059863/article/details/49914513)
  [Android Studio --“Cannot resolve symbol” 解决办法](https://blog.csdn.net/chaoyu168/article/details/51538996)