package com.mob.mgs.impl;

import android.content.Context;
import android.content.Intent;
import com.mob.mgs.OnAppActiveListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.mgs.impl.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5997g {

    /* renamed from: a */
    private static OnAppActiveListener f14770a = null;

    /* renamed from: b */
    private static int f14771b = 1;

    /* renamed from: c */
    private static volatile boolean f14772c;

    /* renamed from: com.mob.mgs.impl.g$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C59981 extends AbstractC6003h {

        /* renamed from: a */
        final /* synthetic */ Intent f14773a;

        /* renamed from: b */
        final /* synthetic */ boolean f14774b;

        /* renamed from: c */
        final /* synthetic */ Context f14775c;

        C59981(Intent intent, boolean z, Context context) {
            this.f14773a = intent;
            this.f14774b = z;
            this.f14775c = context;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00c5 A[Catch: Throwable -> 0x00f8, TryCatch #0 {Throwable -> 0x00f8, blocks: (B:2:0x0000, B:11:0x00a2, B:13:0x00aa, B:15:0x00b2, B:18:0x00c1, B:20:0x00c5, B:22:0x00cb, B:23:0x00ce, B:25:0x00d4, B:26:0x00dc, B:28:0x00e2, B:16:0x00b8, B:17:0x00bd), top: B:33:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:25:0x00d4 A[Catch: Throwable -> 0x00f8, TryCatch #0 {Throwable -> 0x00f8, blocks: (B:2:0x0000, B:11:0x00a2, B:13:0x00aa, B:15:0x00b2, B:18:0x00c1, B:20:0x00c5, B:22:0x00cb, B:23:0x00ce, B:25:0x00d4, B:26:0x00dc, B:28:0x00e2, B:16:0x00b8, B:17:0x00bd), top: B:33:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00e2 A[Catch: Throwable -> 0x00f8, TRY_LEAVE, TryCatch #0 {Throwable -> 0x00f8, blocks: (B:2:0x0000, B:11:0x00a2, B:13:0x00aa, B:15:0x00b2, B:18:0x00c1, B:20:0x00c5, B:22:0x00cb, B:23:0x00ce, B:25:0x00d4, B:26:0x00dc, B:28:0x00e2, B:16:0x00b8, B:17:0x00bd), top: B:33:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r0v5, types: [com.mob.mgs.impl.g$1$2] */
        @Override // com.mob.tools.utils.AbstractC6218i
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void mo10997a() throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 257
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mob.mgs.impl.C5997g.C59981.mo10997a():void");
        }
    }

    /* renamed from: a */
    public static void m11845a(Context context, Intent intent, boolean z) {
        new C59981(intent, z, context).start();
    }

    /* renamed from: a */
    public static void m11844a(OnAppActiveListener onAppActiveListener) {
        f14770a = onAppActiveListener;
    }
}
