package com.weishu.upf.dynamic_proxy_hook.app2.hook;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ContextInvocation implements InvocationHandler {
    private static final String TAG = "ContextInvocation";
    private Context mContext;

    public ContextInvocation(Context context) {
        mContext = context;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".equals(method)){
            Log.d(TAG, "执行了startActivity, 参数如下: \n intent = [" +
                    ((args != null && args.length > 0) ?  args[0] : null) + "]");
        }
        return method.invoke(mContext, args);
    }
}
