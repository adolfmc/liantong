package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.util.C2052n;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.util.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2042e {

    /* renamed from: a */
    public static final String f3873a = "failed";

    /* renamed from: b */
    public static final String f3874b = "scheme_failed";

    /* renamed from: c */
    private Activity f3875c;

    /* renamed from: d */
    private IAlixPay f3876d;

    /* renamed from: f */
    private boolean f3878f;

    /* renamed from: g */
    private InterfaceC2043a f3879g;

    /* renamed from: e */
    private final Object f3877e = IAlixPay.class;

    /* renamed from: h */
    private ServiceConnection f3880h = new ServiceConnectionC2044f(this);

    /* renamed from: i */
    private String f3881i = null;

    /* renamed from: j */
    private IRemoteServiceCallback f3882j = new BinderC2046h(this);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.util.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2043a {
        /* renamed from: a */
        void mo20694a();

        /* renamed from: b */
        void mo20693b();
    }

    public C2042e(Activity activity, InterfaceC2043a interfaceC2043a) {
        this.f3875c = activity;
        this.f3879g = interfaceC2043a;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0055 A[Catch: Throwable -> 0x0060, TryCatch #0 {Throwable -> 0x0060, blocks: (B:3:0x0003, B:7:0x0017, B:9:0x001f, B:11:0x0025, B:14:0x002c, B:18:0x0037, B:20:0x003b, B:23:0x0048, B:25:0x0051, B:27:0x0055, B:28:0x0059, B:24:0x004d, B:6:0x0015), top: B:35:0x0003 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m20700a(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r1 = 0
            com.alipay.sdk.data.a r2 = com.alipay.sdk.data.C2006a.m20868g()     // Catch: java.lang.Throwable -> L60
            java.util.List r2 = r2.m20869f()     // Catch: java.lang.Throwable -> L60
            com.alipay.sdk.data.a r3 = com.alipay.sdk.data.C2006a.m20868g()     // Catch: java.lang.Throwable -> L60
            boolean r3 = r3.f3732q     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L15
            if (r2 != 0) goto L17
        L15:
            java.util.List<com.alipay.sdk.data.a$a> r2 = com.alipay.sdk.app.C1997i.f3583a     // Catch: java.lang.Throwable -> L60
        L17:
            android.app.Activity r3 = r5.f3875c     // Catch: java.lang.Throwable -> L60
            com.alipay.sdk.util.n$a r2 = com.alipay.sdk.util.C2052n.m20669a(r3, r2)     // Catch: java.lang.Throwable -> L60
            if (r2 == 0) goto L5d
            boolean r3 = r2.m20641a()     // Catch: java.lang.Throwable -> L60
            if (r3 != 0) goto L5d
            boolean r3 = r2.m20640b()     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L2c
            goto L5d
        L2c:
            android.content.pm.PackageInfo r3 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            boolean r3 = com.alipay.sdk.util.C2052n.m20668a(r3)     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L37
            java.lang.String r6 = "failed"
            return r6
        L37:
            android.content.pm.PackageInfo r3 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L4d
            java.lang.String r3 = "com.eg.android.AlipayGphone"
            android.content.pm.PackageInfo r4 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            java.lang.String r4 = r4.packageName     // Catch: java.lang.Throwable -> L60
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L48
            goto L4d
        L48:
            android.content.pm.PackageInfo r3 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            java.lang.String r0 = r3.packageName     // Catch: java.lang.Throwable -> L60
            goto L51
        L4d:
            java.lang.String r0 = com.alipay.sdk.util.C2052n.m20674a()     // Catch: java.lang.Throwable -> L60
        L51:
            android.content.pm.PackageInfo r3 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            if (r3 == 0) goto L59
            android.content.pm.PackageInfo r3 = r2.f3908a     // Catch: java.lang.Throwable -> L60
            int r1 = r3.versionCode     // Catch: java.lang.Throwable -> L60
        L59:
            r5.m20701a(r2)     // Catch: java.lang.Throwable -> L60
            goto L68
        L5d:
            java.lang.String r6 = "failed"
            return r6
        L60:
            r2 = move-exception
            java.lang.String r3 = "biz"
            java.lang.String r4 = "CheckClientSignEx"
            com.alipay.sdk.app.statistic.C2000a.m20898a(r3, r4, r2)
        L68:
            java.lang.String r6 = r5.m20697a(r6, r0, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.C2042e.m20700a(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private void m20701a(C2052n.C2053a c2053a) throws InterruptedException {
        PackageInfo packageInfo;
        if (c2053a == null || (packageInfo = c2053a.f3908a) == null) {
            return;
        }
        String str = packageInfo.packageName;
        Intent intent = new Intent();
        intent.setClassName(str, "com.alipay.android.app.TransProcessPayActivity");
        try {
            this.f3875c.startActivity(intent);
        } catch (Throwable th) {
            C2000a.m20898a("biz", "StartLaunchAppTransEx", th);
        }
        Thread.sleep(200L);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:60:0x01bc
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    private java.lang.String m20697a(java.lang.String r13, java.lang.String r14, int r15) {
        /*
            Method dump skipped, instructions count: 541
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.C2042e.m20697a(java.lang.String, java.lang.String, int):java.lang.String");
    }

    /* renamed from: a */
    private static boolean m20699a(String str, Context context) {
        try {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.setClassName(str, "com.alipay.android.msp.ui.views.MspContainerActivity");
            if (intent.resolveActivityInfo(context.getPackageManager(), 0) == null) {
                C2000a.m20899a("biz", "BSPDetectFail", "");
                return false;
            }
            return true;
        } catch (Throwable th) {
            C2000a.m20898a("biz", "BSPDetectFail", th);
            return false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ee, code lost:
        if (r9 != null) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00f0, code lost:
        r9.setRequestedOrientation(0);
        r7.f3878f = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x012e, code lost:
        if (r9 != null) goto L48;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m20698a(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.C2042e.m20698a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public void m20705a() {
        this.f3875c = null;
    }
}
