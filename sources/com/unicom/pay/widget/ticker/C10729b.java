package com.unicom.pay.widget.ticker;

import android.graphics.Canvas;
import android.graphics.Paint;

/* renamed from: com.unicom.pay.widget.ticker.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10729b {

    /* renamed from: a */
    public C10727a[] f20549a;

    /* renamed from: b */
    public final C10730c f20550b;

    /* renamed from: c */
    public char f20551c = 0;

    /* renamed from: d */
    public char f20552d = 0;

    /* renamed from: e */
    public char[] f20553e;

    /* renamed from: f */
    public int f20554f;

    /* renamed from: g */
    public int f20555g;

    /* renamed from: h */
    public int f20556h;

    /* renamed from: i */
    public float f20557i;

    /* renamed from: j */
    public float f20558j;

    /* renamed from: k */
    public float f20559k;

    /* renamed from: l */
    public float f20560l;

    /* renamed from: m */
    public float f20561m;

    /* renamed from: n */
    public float f20562n;

    /* renamed from: o */
    public float f20563o;

    /* renamed from: p */
    public float f20564p;

    /* renamed from: q */
    public int f20565q;

    public C10729b(C10727a[] c10727aArr, C10730c c10730c) {
        this.f20549a = c10727aArr;
        this.f20550b = c10730c;
    }

    /* renamed from: a */
    public final void m6027a() {
        float m6023a = this.f20550b.m6023a(this.f20552d);
        float f = this.f20560l;
        float f2 = this.f20561m;
        if (f != f2 || f2 == m6023a) {
            return;
        }
        this.f20561m = m6023a;
        this.f20560l = m6023a;
        this.f20562n = m6023a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0062, code lost:
        if (((r2 - r8) + r9) < r3) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006f, code lost:
        if (((r2 - r9) + r8) < r3) goto L24;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m6026a(char r11) {
        /*
            Method dump skipped, instructions count: 196
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.ticker.C10729b.m6026a(char):void");
    }

    /* renamed from: a */
    public final boolean m6025a(Canvas canvas, Paint paint, char[] cArr, int i, float f) {
        if (i < 0 || i >= cArr.length) {
            return false;
        }
        canvas.drawText(cArr, i, 1, 0.0f, f, paint);
        return true;
    }
}
