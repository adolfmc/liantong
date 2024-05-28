package com.p281qq.p282e.ads.dfa;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.p281qq.p282e.comm.C6871a;
import com.p281qq.p282e.comm.managers.C6873b;
import com.p281qq.p282e.comm.p283pi.DFA;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.qq.e.ads.dfa.GDTApkManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GDTApkManager {

    /* renamed from: a */
    private DFA f17789a;

    /* renamed from: b */
    private boolean f17790b = false;

    /* renamed from: c */
    private boolean f17791c = false;

    /* renamed from: d */
    private AtomicInteger f17792d = new AtomicInteger(0);

    /* renamed from: e */
    private Context f17793e;

    public GDTApkManager(Context context, IGDTApkListener iGDTApkListener) {
        if (C6873b.m8276b().m8274d()) {
            m8325a(context, C6873b.m8276b().m8280a(), iGDTApkListener);
        }
    }

    /* renamed from: a */
    private void m8325a(Context context, String str, final IGDTApkListener iGDTApkListener) {
        if (TextUtils.isEmpty(str) || context == null) {
            GDTLogger.m8234e("初始化错误：GDTApkManager 构造失败，Context和appID不能为空");
        } else if (!C6871a.m8283a(context)) {
            GDTLogger.m8234e("初始化错误：必需的 Activity/Service/Permission 没有在AndroidManifest.xml中声明");
        } else {
            this.f17790b = true;
            this.f17793e = context;
            C6873b.f17916g.execute(new Runnable() { // from class: com.qq.e.ads.dfa.GDTApkManager.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        final POFactory pOFactory = C6873b.m8276b().m8275c().getPOFactory();
                        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.qq.e.ads.dfa.GDTApkManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    if (pOFactory != null) {
                                        GDTApkManager.this.f17789a = C6873b.m8276b().m8275c().getPOFactory().getGDTApkDelegate(iGDTApkListener);
                                        GDTApkManager.this.f17791c = true;
                                        while (GDTApkManager.this.f17792d.getAndDecrement() > 0) {
                                            GDTApkManager.this.loadGDTApk();
                                        }
                                    }
                                } finally {
                                    try {
                                    } finally {
                                    }
                                }
                            }
                        });
                    } catch (Throwable th) {
                        GDTLogger.m8233e("初始化错误：初始化时发生异常", th);
                    }
                }
            });
        }
    }

    public final void loadGDTApk() {
        if (this.f17790b) {
            if (!this.f17791c) {
                this.f17792d.incrementAndGet();
                return;
            }
            DFA dfa = this.f17789a;
            if (dfa != null) {
                dfa.loadGDTApk();
            } else {
                GDTLogger.m8234e("调用loadGDTApk失败，实例未被正常初始化");
            }
        }
    }

    public final void startInstall(GDTApk gDTApk) {
        DFA dfa = this.f17789a;
        if (dfa != null) {
            dfa.startInstall(this.f17793e, gDTApk);
        }
    }
}
