package p000;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.crb.jscard.entity.CardResult;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: l  reason: case insensitive filesystem */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class SmartCard {
    @NonNull

    /* renamed from: a */
    public static AbstractC12227j f24911a;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SmartCard.java */
    /* renamed from: l$b */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class C12289b {

        /* renamed from: a */
        public static final SmartCard f24912a = new SmartCard();
    }

    /* renamed from: b */
    public static void m1868b() {
        f24911a.mo1831b();
    }

    /* renamed from: c */
    public static void m1867c() {
        f24911a.mo1830c();
    }

    /* renamed from: d */
    public static SmartCard m1866d() {
        return C12289b.f24912a;
    }

    /* renamed from: a */
    public void m1870a() {
        f24911a.mo1833a();
    }

    public SmartCard() {
        if (Build.VERSION.SDK_INT >= 28) {
            f24911a = new SmartCardByGoogle();
        } else {
            f24911a = new SmartCardBySimalliance();
        }
    }

    @WorkerThread
    /* renamed from: a */
    public static CardResult m1869a(@NonNull String str) {
        PreconditionUtil.m13a(str, "command must not be null");
        PreconditionUtil.m12a(str.length() > 6, "command format error");
        return f24911a.mo1832a(str.toUpperCase());
    }
}
