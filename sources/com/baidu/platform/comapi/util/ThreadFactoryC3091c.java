package com.baidu.platform.comapi.util;

import java.util.concurrent.ThreadFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class ThreadFactoryC3091c implements ThreadFactory {

    /* renamed from: a */
    private String f8065a;

    public ThreadFactoryC3091c(String str) {
        this.f8065a = "BaiduMapSDK-" + str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, this.f8065a);
    }
}
