package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* compiled from: OnDelTagsReceiveTask.java */
/* renamed from: com.vivo.push.f.n */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10953n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f20999a;

    /* renamed from: b */
    final /* synthetic */ List f21000b;

    /* renamed from: c */
    final /* synthetic */ List f21001c;

    /* renamed from: d */
    final /* synthetic */ String f21002d;

    /* renamed from: e */
    final /* synthetic */ OnDelTagsReceiveTask f21003e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10953n(OnDelTagsReceiveTask onDelTagsReceiveTask, int i, List list, List list2, String str) {
        this.f21003e = onDelTagsReceiveTask;
        this.f20999a = i;
        this.f21000b = list;
        this.f21001c = list2;
        this.f21002d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f21003e.f20972b;
        context = this.f21003e.f21149a;
        pushMessageCallback.onDelAlias(context, this.f20999a, this.f21000b, this.f21001c, this.f21002d);
    }
}
