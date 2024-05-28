package com.baidu.mapapi;

import android.app.Application;
import android.content.Context;
import com.baidu.p166vi.VIContext;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JNIInitializer {

    /* renamed from: a */
    private static Context f5839a;

    public static Context getCachedContext() {
        return f5839a;
    }

    public static void setContext(Application application) {
        if (application == null) {
            throw new RuntimeException();
        }
        if (f5839a == null) {
            f5839a = application;
        }
        VIContext.init(application);
    }
}
