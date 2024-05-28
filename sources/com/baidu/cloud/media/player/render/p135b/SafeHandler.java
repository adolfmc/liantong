package com.baidu.cloud.media.player.render.p135b;

import android.os.Handler;
import android.os.Looper;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.media.player.render.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SafeHandler extends Handler {

    /* renamed from: a */
    private static final SafeHandler f4401a = new SafeHandler();

    private SafeHandler() {
        super(Looper.getMainLooper());
    }

    /* renamed from: a */
    public static final SafeHandler m20029a() {
        return f4401a;
    }
}
