package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* compiled from: OnSetTagsReceiveTask.java */
/* renamed from: com.vivo.push.f.ad */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10946ad implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f20979a;

    /* renamed from: b */
    final /* synthetic */ List f20980b;

    /* renamed from: c */
    final /* synthetic */ List f20981c;

    /* renamed from: d */
    final /* synthetic */ String f20982d;

    /* renamed from: e */
    final /* synthetic */ OnSetTagsReceiveTask f20983e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10946ad(OnSetTagsReceiveTask onSetTagsReceiveTask, int i, List list, List list2, String str) {
        this.f20983e = onSetTagsReceiveTask;
        this.f20979a = i;
        this.f20980b = list;
        this.f20981c = list2;
        this.f20982d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f20983e.f20972b;
        context = this.f20983e.f21149a;
        pushMessageCallback.onSetAlias(context, this.f20979a, this.f20980b, this.f20981c, this.f20982d);
    }
}
