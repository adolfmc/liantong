package com.baidu.platform.comapi.map.p153b;

import android.view.MotionEvent;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.p153b.p154a.C3025a;
import com.baidu.platform.comapi.map.p153b.p154a.C3027b;
import com.baidu.platform.comapi.map.p153b.p155b.C3031b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3029b {

    /* renamed from: a */
    C3025a.InterfaceC3026a f7804a = new C3034c(this);

    /* renamed from: b */
    private MapController f7805b;

    /* renamed from: c */
    private C3027b f7806c;

    /* renamed from: d */
    private C3025a f7807d;

    public C3029b(MapController mapController) {
        this.f7805b = mapController;
        this.f7806c = new C3027b(new C3031b(mapController));
        this.f7807d = new C3025a(this.f7804a, mapController);
    }

    /* renamed from: a */
    public void m17902a(MotionEvent motionEvent) {
        this.f7806c.m17907a(motionEvent);
        this.f7807d.m17913a(motionEvent);
    }
}
