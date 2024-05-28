package com.vivo.push.p373f;

import android.content.Context;
import com.vivo.push.sdk.PushMessageCallback;
import java.util.List;

/* compiled from: OnSetTagsReceiveTask.java */
/* renamed from: com.vivo.push.f.ac */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10945ac implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f20974a;

    /* renamed from: b */
    final /* synthetic */ List f20975b;

    /* renamed from: c */
    final /* synthetic */ List f20976c;

    /* renamed from: d */
    final /* synthetic */ String f20977d;

    /* renamed from: e */
    final /* synthetic */ OnSetTagsReceiveTask f20978e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10945ac(OnSetTagsReceiveTask onSetTagsReceiveTask, int i, List list, List list2, String str) {
        this.f20978e = onSetTagsReceiveTask;
        this.f20974a = i;
        this.f20975b = list;
        this.f20976c = list2;
        this.f20977d = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        PushMessageCallback pushMessageCallback = this.f20978e.f20972b;
        context = this.f20978e.f21149a;
        pushMessageCallback.onSetTags(context, this.f20974a, this.f20975b, this.f20976c, this.f20977d);
    }
}
