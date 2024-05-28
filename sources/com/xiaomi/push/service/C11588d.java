package com.xiaomi.push.service;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import com.xiaomi.push.C11469j;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11588d {

    /* renamed from: a */
    private static List<C11589a> f23673a = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m2561a(Context context, StatusBarNotification statusBarNotification, int i) {
        if (!C11469j.m2972a(context) || i <= 0 || statusBarNotification == null || Build.VERSION.SDK_INT < 20) {
            return;
        }
        m2560a(new C11589a(statusBarNotification.getKey(), SystemClock.elapsedRealtime(), i, C11534ag.m2742a(statusBarNotification.getNotification())));
    }

    /* renamed from: a */
    private static void m2560a(C11589a c11589a) {
        f23673a.add(c11589a);
        m2562a();
    }

    /* renamed from: a */
    private static void m2562a() {
        for (int size = f23673a.size() - 1; size >= 0; size--) {
            C11589a c11589a = f23673a.get(size);
            if (SystemClock.elapsedRealtime() - c11589a.f23675a > 5000) {
                f23673a.remove(c11589a);
            }
        }
        if (f23673a.size() > 10) {
            f23673a.remove(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.d$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11589a {

        /* renamed from: a */
        public final int f23674a;

        /* renamed from: a */
        public final long f23675a;

        /* renamed from: a */
        public final String f23676a;

        /* renamed from: a */
        public final Notification.Action[] f23677a;

        C11589a(String str, long j, int i, Notification.Action[] actionArr) {
            this.f23676a = str;
            this.f23675a = j;
            this.f23674a = i;
            this.f23677a = actionArr;
        }
    }
}
