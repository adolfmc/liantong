package com.bytedance.android.live.saas.middleware.alog;

import android.content.Intent;
import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ILogProtocol {
    void bundle(int i, String str, Bundle bundle);

    void changeLevel(int i);

    /* renamed from: d */
    void m17382d(String str, String str2);

    void destroy();

    /* renamed from: e */
    void m17381e(String str, String str2);

    /* renamed from: e */
    void m17380e(String str, String str2, Throwable th);

    /* renamed from: e */
    void m17379e(String str, Throwable th);

    void flush();

    void forceLogSharding();

    void header(int i, String str, String str2);

    /* renamed from: i */
    void m17378i(String str, String str2);

    void intent(int i, String str, Intent intent);

    void json(int i, String str, String str2);

    void release();

    void stacktrace(int i, String str, StackTraceElement[] stackTraceElementArr);

    void thread(int i, String str, Thread thread);

    void throwable(int i, String str, String str2, Throwable th);

    void throwable(int i, String str, Throwable th);

    /* renamed from: v */
    void m17377v(String str, String str2);

    /* renamed from: w */
    void m17376w(String str, String str2);

    /* renamed from: w */
    void m17375w(String str, String str2, Throwable th);

    /* renamed from: w */
    void m17374w(String str, Throwable th);
}
