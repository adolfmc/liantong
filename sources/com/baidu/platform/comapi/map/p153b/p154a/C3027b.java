package com.baidu.platform.comapi.map.p153b.p154a;

import android.util.Pair;
import android.view.MotionEvent;
import com.baidu.platform.comapi.map.p153b.C3020a;
import com.baidu.platform.comapi.map.p153b.C3038f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3027b {

    /* renamed from: a */
    public C3020a.C3021a f7797a;

    /* renamed from: b */
    public C3020a.C3021a f7798b;

    /* renamed from: c */
    public C3020a.C3021a f7799c;

    /* renamed from: d */
    public MotionEvent f7800d;

    /* renamed from: f */
    private InterfaceC3028a f7802f;

    /* renamed from: e */
    public C3038f f7801e = new C3038f();

    /* renamed from: g */
    private boolean f7803g = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3028a {
        /* renamed from: a */
        boolean mo17899a(C3027b c3027b);

        /* renamed from: a */
        boolean mo17898a(C3027b c3027b, MotionEvent motionEvent);

        /* renamed from: b */
        boolean mo17897b(C3027b c3027b);
    }

    public C3027b(InterfaceC3028a interfaceC3028a) {
        this.f7802f = interfaceC3028a;
    }

    /* renamed from: a */
    private void m17908a() {
        this.f7801e.m17871a();
        this.f7797a = null;
        this.f7798b = null;
        this.f7799c = null;
        this.f7803g = true;
        this.f7802f.mo17899a(this);
    }

    /* renamed from: b */
    private void m17906b() {
        this.f7801e.m17869b();
        this.f7803g = false;
        this.f7802f.mo17897b(this);
    }

    /* renamed from: b */
    private void m17905b(MotionEvent motionEvent) {
        this.f7801e.m17870a(motionEvent);
        Pair<C3020a.C3024d, C3020a.C3024d> m17868c = this.f7801e.m17868c();
        if (motionEvent.getPointerCount() == 2) {
            if (Math.abs(((C3020a.C3024d) m17868c.first).f7790a) > 0.0d || Math.abs(((C3020a.C3024d) m17868c.first).f7791b) > 0.0d || Math.abs(((C3020a.C3024d) m17868c.second).f7790a) > 0.0d || Math.abs(((C3020a.C3024d) m17868c.second).f7791b) > 0.0d) {
                m17904c(motionEvent);
                this.f7802f.mo17898a(this, motionEvent);
            }
        }
    }

    /* renamed from: c */
    private void m17904c(MotionEvent motionEvent) {
        C3020a.C3021a m17914a = C3020a.C3021a.m17914a(motionEvent);
        C3020a.C3021a c3021a = this.f7799c;
        if (c3021a == null) {
            c3021a = m17914a;
        }
        this.f7798b = c3021a;
        this.f7799c = m17914a;
        if (this.f7797a == null) {
            this.f7797a = m17914a;
        }
    }

    /* renamed from: a */
    public void m17907a(MotionEvent motionEvent) {
        this.f7800d = motionEvent;
        switch (motionEvent.getAction()) {
            case 2:
                if (this.f7803g) {
                    m17905b(motionEvent);
                    return;
                } else if (motionEvent.getPointerCount() != 2) {
                    return;
                }
                break;
            case 5:
            default:
                return;
            case 6:
            case 262:
                if (this.f7803g) {
                    m17906b();
                    return;
                }
                return;
            case 261:
                if (this.f7803g) {
                    return;
                }
                break;
        }
        m17908a();
    }
}
