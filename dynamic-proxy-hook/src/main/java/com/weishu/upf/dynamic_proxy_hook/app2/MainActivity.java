package com.weishu.upf.dynamic_proxy_hook.app2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.weishu.upf.dynamic_proxy_hook.app2.hook.HookHelper;

/**
 * @author weishu
 * @date 16/1/28
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: 16/1/28 支持Activity直接跳转请在这里Hook
        // 家庭作业,留给读者完成.
        doHomeWork();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Button tv = new Button(this);
        tv.setText("测试界面");

        Button btnStartActivity = new Button(this);
        btnStartActivity.setText("通过Activity.startActivity启动页面");
        linearLayout.addView(tv);
        linearLayout.addView(btnStartActivity);


        setContentView(linearLayout);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.parse("http://www.baidu.com"));

                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                getApplicationContext().startActivity(intent);
            }
        });


        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));

                // 注意这里使用的ApplicationContext 启动的Activity
                // 因为Activity对象的startActivity使用的并不是ContextImpl的mInstrumentation
                // 而是自己的mInstrumentation, 如果你需要这样, 可以自己Hook
                // 比较简单, 直接替换这个Activity的此字段即可.
                startActivity(intent);
            }
        });
    }

    private void doHomeWork(){
        try {
            HookHelper.hookActivity(MainActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        try {
            // 在这里进行Hook
            HookHelper.attachContext();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
