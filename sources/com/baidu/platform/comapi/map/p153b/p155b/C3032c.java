package com.baidu.platform.comapi.map.p153b.p155b;

import android.util.Pair;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.MapStatus;
import com.baidu.platform.comapi.map.p153b.C3020a;
import com.baidu.platform.comapi.map.p153b.p154a.C3027b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3032c extends AbstractC3030a {

    /* renamed from: b */
    private boolean f7815b;

    /* renamed from: c */
    private long f7816c;

    /* renamed from: d */
    private MapController f7817d;

    public C3032c(MapController mapController) {
        super(mapController);
        this.f7815b = true;
        this.f7817d = mapController;
    }

    /* renamed from: a */
    private void m17894a(double d, MapStatus mapStatus) {
        if (this.f7816c == 0) {
            this.f7816c = System.currentTimeMillis();
        }
        long currentTimeMillis = System.currentTimeMillis();
        this.f7816c = currentTimeMillis;
        if (currentTimeMillis - this.f7816c > 50 || Math.abs(d) < 4.0d) {
            return;
        }
        mapStatus.overlooking = d > 0.0d ? mapStatus.overlooking - 4 : (int) (mapStatus.overlooking + 2.0d);
    }

    @Override // com.baidu.platform.comapi.map.p153b.p155b.AbstractC3030a
    /* renamed from: a */
    public void mo17888a(C3027b c3027b, Pair<C3020a.C3024d, C3020a.C3024d> pair) {
        MapStatus mapStatus = this.f7817d.getMapStatus();
        if (mapStatus.bOverlookSpringback) {
            mapStatus.overlooking = mapStatus.overlooking > 0 ? 0 : mapStatus.minOverlooking;
            this.f7817d.setMapStatusWithAnimation(mapStatus, 200);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0031, code lost:
        if (r4 != 0.0d) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x003e, code lost:
        if (java.lang.Math.abs(r2) > java.lang.Math.abs(r4)) goto L3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0041, code lost:
        m17894a(r4, r1);
     */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    @Override // com.baidu.platform.comapi.map.p153b.p155b.AbstractC3030a
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void mo17887a(com.baidu.platform.comapi.map.p153b.p154a.C3027b r11, android.view.MotionEvent r12) {
        /*
            r10 = this;
            com.baidu.platform.comapi.map.b.a$a r0 = r11.f7798b
            com.baidu.platform.comapi.map.b.a$a r11 = r11.f7799c
            com.baidu.platform.comapi.map.MapController r1 = r10.f7817d
            com.baidu.platform.comapi.map.MapStatus r1 = r1.getMapStatus()
            com.baidu.platform.comapi.map.b.a$b r2 = r11.f7783a
            double r2 = r2.f7786b
            com.baidu.platform.comapi.map.b.a$b r4 = r0.f7783a
            double r4 = r4.f7786b
            double r2 = r2 - r4
            com.baidu.platform.comapi.map.b.a$b r11 = r11.f7784b
            double r4 = r11.f7786b
            com.baidu.platform.comapi.map.b.a$b r11 = r0.f7784b
            double r6 = r11.f7786b
            double r4 = r4 - r6
            double r6 = r2 * r4
            r8 = 0
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 <= 0) goto L28
        L24:
            r10.m17894a(r2, r1)
            goto L44
        L28:
            if (r11 != 0) goto L34
            int r11 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r11 == 0) goto L2f
            goto L24
        L2f:
            int r11 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r11 == 0) goto L44
            goto L41
        L34:
            double r6 = java.lang.Math.abs(r2)
            double r8 = java.lang.Math.abs(r4)
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 <= 0) goto L41
            goto L24
        L41:
            r10.m17894a(r4, r1)
        L44:
            r11 = 1
            float r0 = r12.getX(r11)
            r2 = 0
            float r3 = r12.getX(r2)
            float r0 = r0 - r3
            float r11 = r12.getY(r11)
            float r3 = r12.getY(r2)
            float r11 = r11 - r3
            android.graphics.Point r3 = new android.graphics.Point
            float r4 = r12.getRawX()
            int r4 = (int) r4
            float r5 = r12.getRawY()
            int r5 = (int) r5
            r3.<init>(r4, r5)
            android.graphics.Point r4 = new android.graphics.Point
            float r5 = r12.getRawX()
            float r5 = r5 + r0
            int r0 = (int) r5
            float r12 = r12.getRawY()
            float r12 = r12 + r11
            int r11 = (int) r12
            r4.<init>(r0, r11)
            com.baidu.platform.comapi.map.MapController r11 = r10.f7817d
            java.util.List r11 = r11.getListeners()
            if (r11 == 0) goto L9f
            com.baidu.platform.comapi.map.MapController r12 = r10.f7817d
            com.baidu.mapsdkplatform.comapi.map.x r12 = r12.getMapStatusInner()
            r0 = r2
        L87:
            int r5 = r11.size()
            if (r0 >= r5) goto L9f
            java.lang.Object r5 = r11.get(r0)
            com.baidu.platform.comapi.map.al r5 = (com.baidu.platform.comapi.map.InterfaceC3010al) r5
            if (r5 == 0) goto L9c
            boolean r5 = r5.mo17956c(r3, r4, r12)
            if (r5 == 0) goto L9c
            return
        L9c:
            int r0 = r0 + 1
            goto L87
        L9f:
            com.baidu.platform.comapi.map.MapController r11 = r10.f7817d
            r11.setMapStatus(r1)
            boolean r11 = r10.f7815b
            if (r11 == 0) goto Lb3
            r10.f7815b = r2
            com.baidu.platform.comapi.map.MapController r11 = r10.f7817d
            com.baidu.platform.comapi.map.b.d r11 = r11.getGestureMonitor()
            r11.m17875d()
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.map.p153b.p155b.C3032c.mo17887a(com.baidu.platform.comapi.map.b.a.b, android.view.MotionEvent):void");
    }
}
