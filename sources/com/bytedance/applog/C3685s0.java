package com.bytedance.applog;

import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.bytedance.applog.C3565d1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.bytedance.applog.s0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3685s0 implements Handler.Callback {

    /* renamed from: b */
    public InterfaceC3686a f8798b;

    /* renamed from: c */
    public C3672q0 f8799c;

    /* renamed from: g */
    public boolean f8803g;

    /* renamed from: a */
    public ArrayList<C3672q0> f8797a = new ArrayList<>();

    /* renamed from: d */
    public List<C3565d1> f8800d = new ArrayList(2);

    /* renamed from: e */
    public boolean f8801e = false;

    /* renamed from: f */
    public int f8802f = 0;

    /* renamed from: h */
    public Rect f8804h = new Rect();

    /* renamed from: i */
    public Handler f8805i = new Handler(Looper.getMainLooper(), this);

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.s0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3686a {
        /* renamed from: a */
        void mo17113a(C3672q0 c3672q0, List<C3565d1> list, List<C3672q0> list2);
    }

    /* renamed from: a */
    public final void m17124a() {
        if (this.f8801e && this.f8802f == 0) {
            InterfaceC3686a interfaceC3686a = this.f8798b;
            if (interfaceC3686a != null) {
                interfaceC3686a.mo17113a(this.f8799c, this.f8800d, this.f8797a);
            }
            this.f8801e = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e8, code lost:
        r3 = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x013d  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m17123a(android.view.View r9, com.bytedance.applog.C3672q0 r10) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3685s0.m17123a(android.view.View, com.bytedance.applog.q0):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0107  */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean handleMessage(android.os.Message r18) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3685s0.handleMessage(android.os.Message):boolean");
    }

    /* renamed from: a */
    public final void m17121a(InterfaceC3686a interfaceC3686a, Looper looper, boolean z) {
        this.f8798b = interfaceC3686a;
        this.f8803g = z;
        this.f8805i = new Handler(looper, this);
        C3560c2.m17317b();
        for (View view : C3560c2.m17319a()) {
            m17123a(view, null);
        }
        this.f8801e = true;
        m17124a();
    }

    /* renamed from: a */
    public final void m17122a(C3565d1.C3566a c3566a, String str, List<C3565d1.C3567b> list) {
        Iterator<C3565d1.C3567b> it = list.iterator();
        while (it.hasNext()) {
            C3565d1.C3567b next = it.next();
            C3565d1.C3566a c3566a2 = next.f8415b;
            int i = c3566a2.f8412c;
            int i2 = c3566a2.f8413d;
            Iterator<C3565d1.C3567b> it2 = it;
            C3672q0 c3672q0 = new C3672q0(new C3654o1(str, next.f8416c, i, i2, i / 2, i2 / 2, new ArrayList(), (ArrayList) next.f8418e, (ArrayList) next.f8424k));
            c3672q0.f8768C = new int[2];
            int[] iArr = c3672q0.f8768C;
            iArr[0] = c3566a.f8410a;
            iArr[1] = c3566a.f8411b;
            c3672q0.f8769D = c3566a.f8412c;
            c3672q0.f8770E = c3566a.f8413d;
            c3672q0.f8766A = next.f8419f;
            c3672q0.f8772x = new int[2];
            int[] iArr2 = c3672q0.f8772x;
            C3565d1.C3566a c3566a3 = next.f8415b;
            iArr2[0] = c3566a3.f8410a;
            iArr2[1] = c3566a3.f8411b;
            c3672q0.f8773y = i;
            c3672q0.f8774z = i2;
            c3672q0.f8675v = true;
            this.f8797a.add(c3672q0);
            List<C3565d1.C3567b> list2 = next.f8421h;
            if (list2 == null || list2.size() <= 0) {
                it = it2;
            } else {
                m17122a(c3566a, str, next.f8421h);
                it = it2;
            }
        }
    }
}
