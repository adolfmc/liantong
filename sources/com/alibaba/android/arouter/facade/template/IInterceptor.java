package com.alibaba.android.arouter.facade.template;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IInterceptor extends IProvider {
    void process(Postcard postcard, InterceptorCallback interceptorCallback);
}
