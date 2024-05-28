package com.vivo.push.p373f;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.p368b.OnAppReceiveCommand;
import com.vivo.push.sdk.PushMessageCallback;

/* compiled from: OnBindAppReceiveTask.java */
/* renamed from: com.vivo.push.f.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10951i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f20991a;

    /* renamed from: b */
    final /* synthetic */ OnAppReceiveCommand f20992b;

    /* renamed from: c */
    final /* synthetic */ OnBindAppReceiveTask f20993c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10951i(OnBindAppReceiveTask onBindAppReceiveTask, String str, OnAppReceiveCommand onAppReceiveCommand) {
        this.f20993c = onBindAppReceiveTask;
        this.f20991a = str;
        this.f20992b = onAppReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        if (!TextUtils.isEmpty(this.f20991a)) {
            PushMessageCallback pushMessageCallback = this.f20993c.f20972b;
            context2 = this.f20993c.f21149a;
            pushMessageCallback.onReceiveRegId(context2, this.f20991a);
        }
        PushMessageCallback pushMessageCallback2 = this.f20993c.f20972b;
        context = this.f20993c.f21149a;
        pushMessageCallback2.onBind(context, this.f20992b.m5769i(), this.f20992b.m5800d());
    }
}
