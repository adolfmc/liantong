package com.p281qq.p282e.ads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.p281qq.p282e.comm.C6871a;
import com.p281qq.p282e.comm.managers.C6873b;
import com.p281qq.p282e.comm.p283pi.POFactory;
import com.p281qq.p282e.comm.util.GDTLogger;

/* renamed from: com.qq.e.ads.AbstractAD */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AbstractAD<T> {

    /* renamed from: a */
    public T f17738a;

    /* renamed from: e */
    private volatile boolean f17742e;

    /* renamed from: c */
    private volatile boolean f17740c = false;

    /* renamed from: d */
    private volatile boolean f17741d = false;

    /* renamed from: b */
    private final Handler f17739b = new Handler(Looper.getMainLooper());

    /* renamed from: b */
    private void m8338b(final Context context, final String str, final String str2) {
        this.f17742e = true;
        if (C6873b.m8276b().m8274d()) {
            final String m8280a = C6873b.m8276b().m8280a();
            if (C6871a.m8283a(context)) {
                this.f17741d = true;
                C6873b.f17916g.execute(new Runnable() { // from class: com.qq.e.ads.AbstractAD.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            final POFactory pOFactory = C6873b.m8276b().m8275c().getPOFactory();
                            AbstractAD.this.f17739b.post(new Runnable() { // from class: com.qq.e.ads.AbstractAD.1.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        if (pOFactory != null) {
                                            AbstractAD.this.f17738a = (T) AbstractAD.this.mo8297a(context, pOFactory, m8280a, str, str2);
                                            AbstractAD.this.f17740c = true;
                                            if (AbstractAD.this.f17738a == null) {
                                                AbstractAD.this.m8345a(200103);
                                            } else {
                                                AbstractAD.this.mo8294a((AbstractAD) AbstractAD.this.f17738a);
                                            }
                                        } else {
                                            AbstractAD.this.f17740c = true;
                                            AbstractAD.this.m8345a(200102);
                                        }
                                    } catch (Throwable th) {
                                        GDTLogger.m8233e("初始化错误：初始化广告实例时发生异常", th);
                                        AbstractAD.this.f17740c = true;
                                        AbstractAD.this.m8345a(2001);
                                    }
                                }
                            });
                        } catch (Throwable th) {
                            GDTLogger.m8233e("初始化错误：初始化插件时发生异常", th);
                            AbstractAD.this.f17740c = true;
                            AbstractAD.this.m8345a(200102);
                        }
                    }
                });
                return;
            }
            GDTLogger.m8234e("Manifest文件中Activity/Service/Permission的声明有问题或者Permission权限未授予");
            m8345a(4002);
            return;
        }
        m8345a(2003);
    }

    /* renamed from: a */
    protected abstract T mo8297a(Context context, POFactory pOFactory, String str, String str2, String str3);

    /* renamed from: a */
    public final void m8345a(final int i) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            mo8292b(i);
        } else {
            this.f17739b.post(new Runnable() { // from class: com.qq.e.ads.AbstractAD.2
                @Override // java.lang.Runnable
                public void run() {
                    AbstractAD.this.mo8292b(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m8344a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            m8338b(context, str, "");
            return;
        }
        GDTLogger.m8234e("初始化错误：参数错误context或posId为空");
        m8345a(2001);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m8343a(Context context, String str, String str2) {
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            m8338b(context, str, str2);
            return;
        }
        GDTLogger.m8234e("初始化错误：参数错误，context、posId、token 不可为空");
        m8345a(2001);
    }

    /* renamed from: a */
    protected abstract void mo8294a(T t);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m8340a(String str) {
        GDTLogger.m8234e(getClass().getSimpleName() + ":调用方法 " + str + "异常，广告实例还未初始化");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean m8346a() {
        return this.f17742e && this.f17741d;
    }

    /* renamed from: b */
    protected abstract void mo8292b(int i);

    /* renamed from: b */
    public final boolean m8339b() {
        return this.f17740c;
    }
}
