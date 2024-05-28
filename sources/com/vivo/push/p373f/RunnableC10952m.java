package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* compiled from: OnDelTagsReceiveTask.java */
/* renamed from: com.vivo.push.f.m */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10952m implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f20994a;

    /* renamed from: b */
    final /* synthetic */ List f20995b;

    /* renamed from: c */
    final /* synthetic */ List f20996c;

    /* renamed from: d */
    final /* synthetic */ String f20997d;

    /* renamed from: e */
    final /* synthetic */ OnDelTagsReceiveTask f20998e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10952m(OnDelTagsReceiveTask onDelTagsReceiveTask, int i, List list, List list2, String str) {
        this.f20998e = onDelTagsReceiveTask;
        this.f20994a = i;
        this.f20995b = list;
        this.f20996c = list2;
        this.f20997d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f20998e.f20972b;
        context = this.f20998e.f21149a;
        pushMessageCallback.onDelTags(context, this.f20994a, this.f20995b, this.f20996c, this.f20997d);
    }
}
