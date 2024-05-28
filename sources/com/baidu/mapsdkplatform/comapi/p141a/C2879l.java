package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2879l extends AbstractC2870c {

    /* renamed from: a */
    private Animator f7110a = null;

    /* renamed from: b */
    private long f7111b = 0;

    /* renamed from: c */
    private Interpolator f7112c = null;

    /* renamed from: d */
    private TypeEvaluator f7113d = null;

    /* renamed from: e */
    private Animation.AnimationListener f7114e = null;

    /* renamed from: f */
    private int f7115f = 1;

    /* renamed from: g */
    private int f7116g = 0;

    /* renamed from: h */
    private Object[] f7117h;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(11)
    /* renamed from: com.baidu.mapsdkplatform.comapi.a.l$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2880a implements TypeEvaluator {
        public C2880a() {
        }

        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f, Object obj, Object obj2) {
            LatLng latLng = (LatLng) obj;
            LatLng latLng2 = (LatLng) obj2;
            double d = f;
            return new LatLng(latLng.latitude + (d * (latLng2.latitude - latLng.latitude)), latLng.longitude + ((latLng2.longitude - latLng.longitude) * d));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @TargetApi(11)
    /* renamed from: com.baidu.mapsdkplatform.comapi.a.l$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2881b implements TypeEvaluator {
        public C2881b() {
        }

        @Override // android.animation.TypeEvaluator
        public Object evaluate(float f, Object obj, Object obj2) {
            Point point = (Point) obj;
            Point point2 = (Point) obj2;
            return new Point((int) (point.x + ((point2.x - point.x) * f)), (int) (point.y + (f * (point2.y - point.y))));
        }
    }

    public C2879l(Point... pointArr) {
        this.f7117h = pointArr;
    }

    public C2879l(LatLng... latLngArr) {
        this.f7117h = latLngArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    @android.annotation.TargetApi(11)
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.ObjectAnimator m18507a(com.baidu.mapapi.map.Marker r4) {
        /*
            r3 = this;
            boolean r0 = r4.isFixed()
            r1 = 0
            if (r0 == 0) goto L25
            java.lang.Object[] r0 = r3.f7117h
            r0 = r0[r1]
            boolean r0 = r0 instanceof android.graphics.Point
            if (r0 == 0) goto L1d
            java.lang.String r0 = "fixedScreenPosition"
            com.baidu.mapsdkplatform.comapi.a.l$b r1 = new com.baidu.mapsdkplatform.comapi.a.l$b
            r1.<init>()
        L16:
            java.lang.Object[] r2 = r3.f7117h
            android.animation.ObjectAnimator r4 = android.animation.ObjectAnimator.ofObject(r4, r0, r1, r2)
            goto L40
        L1d:
            java.lang.ClassCastException r4 = new java.lang.ClassCastException
            java.lang.String r0 = "BDMapSDKException: if the marker is fixed on screen, the parameters of Transformation must be android.graphics.Point"
            r4.<init>(r0)
            throw r4
        L25:
            java.lang.Object[] r0 = r3.f7117h
            r1 = r0[r1]
            boolean r1 = r1 instanceof com.baidu.mapapi.model.LatLng
            if (r1 == 0) goto L5b
            android.animation.TypeEvaluator r1 = r3.f7113d
            if (r1 == 0) goto L38
            java.lang.String r2 = "position"
            android.animation.ObjectAnimator r4 = android.animation.ObjectAnimator.ofObject(r4, r2, r1, r0)
            goto L40
        L38:
            java.lang.String r0 = "position"
            com.baidu.mapsdkplatform.comapi.a.l$a r1 = new com.baidu.mapsdkplatform.comapi.a.l$a
            r1.<init>()
            goto L16
        L40:
            if (r4 == 0) goto L5a
            int r0 = r3.f7116g
            r4.setRepeatCount(r0)
            int r0 = r3.m18502c()
            r4.setRepeatMode(r0)
            long r0 = r3.f7111b
            r4.setDuration(r0)
            android.view.animation.Interpolator r0 = r3.f7112c
            if (r0 == 0) goto L5a
            r4.setInterpolator(r0)
        L5a:
            return r4
        L5b:
            java.lang.ClassCastException r4 = new java.lang.ClassCastException
            java.lang.String r0 = "BDMapSDKException: if the marker isn't fixed on screen, the parameters of Transformation must be Latlng"
            r4.<init>(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.p141a.C2879l.m18507a(com.baidu.mapapi.map.Marker):android.animation.ObjectAnimator");
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18514a() {
        Animator animator = this.f7110a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18513a(int i) {
        this.f7115f = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18512a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f7111b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    protected void mo18511a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new C2882m(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18510a(TypeEvaluator typeEvaluator) {
        this.f7113d = typeEvaluator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18509a(Interpolator interpolator) {
        this.f7112c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18508a(Animation.AnimationListener animationListener) {
        this.f7114e = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18506a(Marker marker, Animation animation) {
        this.f7110a = m18507a(marker);
        mo18511a(this.f7110a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: b */
    public void mo18504b() {
        Animator animator = this.f7110a;
        if (animator != null) {
            animator.cancel();
            this.f7110a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: b */
    public void mo18503b(int i) {
        if (i > 0 || i == -1) {
            this.f7116g = i;
        }
    }

    /* renamed from: c */
    public int m18502c() {
        return this.f7115f;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: c */
    public void mo18501c(int i) {
    }
}
