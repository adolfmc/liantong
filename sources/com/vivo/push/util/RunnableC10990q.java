package com.vivo.push.util;

import android.content.Context;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import com.vivo.push.p373f.OnNotificationArrivedReceiveTask;
import java.util.List;

/* compiled from: ImageDownTask.java */
/* renamed from: com.vivo.push.util.q */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10990q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ List f21238a;

    /* renamed from: b */
    final /* synthetic */ ImageDownTask f21239b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10990q(ImageDownTask imageDownTask, List list) {
        this.f21239b = imageDownTask;
        this.f21238a = list;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InsideNotificationItem insideNotificationItem;
        long j;
        Context context;
        InsideNotificationItem insideNotificationItem2;
        long j2;
        int i;
        NotifyArriveCallbackByUser notifyArriveCallbackByUser;
        OnNotificationArrivedReceiveTask.InterfaceC10956a interfaceC10956a;
        insideNotificationItem = this.f21239b.f21232b;
        if (insideNotificationItem != null) {
            SharePreferenceManager m5455b = SharePreferenceManager.m5455b();
            j = this.f21239b.f21233c;
            m5455b.m5418a("com.vivo.push.notify_key", j);
            context = this.f21239b.f21231a;
            List list = this.f21238a;
            insideNotificationItem2 = this.f21239b.f21232b;
            j2 = this.f21239b.f21233c;
            i = this.f21239b.f21235e;
            notifyArriveCallbackByUser = this.f21239b.f21236f;
            interfaceC10956a = this.f21239b.f21237g;
            NotifyAdapterUtil.pushNotification(context, list, insideNotificationItem2, j2, i, notifyArriveCallbackByUser, interfaceC10956a);
        }
    }
}
