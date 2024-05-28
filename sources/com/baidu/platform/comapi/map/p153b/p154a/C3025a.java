package com.baidu.platform.comapi.map.p153b.p154a;

import android.graphics.Point;
import android.view.MotionEvent;
import com.baidu.mapsdkplatform.comapi.map.C2948x;
import com.baidu.platform.comapi.map.InterfaceC3010al;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.p153b.C3020a;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3025a {

    /* renamed from: a */
    private long f7792a = 0;

    /* renamed from: b */
    private boolean f7793b = false;

    /* renamed from: c */
    private C3020a.C3021a f7794c;

    /* renamed from: d */
    private MapController f7795d;

    /* renamed from: e */
    private InterfaceC3026a f7796e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.map.b.a.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3026a {
        /* renamed from: a */
        boolean mo17884a(C3025a c3025a);
    }

    public C3025a(InterfaceC3026a interfaceC3026a, MapController mapController) {
        this.f7796e = interfaceC3026a;
        this.f7795d = mapController;
    }

    /* renamed from: a */
    private void m17915a() {
        this.f7793b = false;
        this.f7794c = null;
        this.f7792a = 0L;
    }

    /* renamed from: b */
    private void m17911b(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 2 || this.f7794c == null) {
            return;
        }
        C3020a.C3021a m17914a = C3020a.C3021a.m17914a(motionEvent);
        C3020a.C3021a c3021a = new C3020a.C3021a(this.f7794c.f7783a, m17914a.f7783a);
        C3020a.C3021a c3021a2 = new C3020a.C3021a(this.f7794c.f7784b, m17914a.f7784b);
        int i = (Math.abs(c3021a.m17912b()) > 20.0d ? 1 : (Math.abs(c3021a.m17912b()) == 20.0d ? 0 : -1));
        boolean z = i < 0 && Math.abs(c3021a2.m17912b()) < 20.0d;
        boolean z2 = System.currentTimeMillis() - this.f7792a < 200;
        if (this.f7795d != null) {
            float x = motionEvent.getX(1) - motionEvent.getX(0);
            float y = motionEvent.getY(1) - motionEvent.getY();
            Point point = new Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
            Point point2 = new Point((int) (motionEvent.getRawX() + x), (int) (motionEvent.getRawY() + y));
            this.f7795d.getMapView();
            if (z && z2 && this.f7793b) {
                List<InterfaceC3010al> listeners = this.f7795d.getListeners();
                C2948x mapStatusInner = this.f7795d.getMapStatusInner();
                if (listeners != null) {
                    for (int i2 = 0; i2 < listeners.size(); i2++) {
                        InterfaceC3010al interfaceC3010al = listeners.get(i2);
                        if (interfaceC3010al != null && interfaceC3010al.mo17952d(point, point2, mapStatusInner)) {
                            return;
                        }
                    }
                }
                this.f7796e.mo17884a(this);
            }
        }
    }

    /* renamed from: c */
    private void m17909c(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() != 2) {
            return;
        }
        this.f7794c = C3020a.C3021a.m17914a(motionEvent);
        this.f7793b = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0014  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m17913a(android.view.MotionEvent r3) {
        /*
            r2 = this;
            int r0 = r3.getAction()
            if (r0 == 0) goto L18
            switch(r0) {
                case 5: goto L14;
                case 6: goto Ld;
                default: goto L9;
            }
        L9:
            switch(r0) {
                case 261: goto L14;
                case 262: goto Ld;
                default: goto Lc;
            }
        Lc:
            goto L1e
        Ld:
            r2.m17911b(r3)
            r2.m17915a()
            goto L1e
        L14:
            r2.m17909c(r3)
            goto L1e
        L18:
            long r0 = java.lang.System.currentTimeMillis()
            r2.f7792a = r0
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.p153b.p154a.C3025a.m17913a(android.view.MotionEvent):void");
    }
}
