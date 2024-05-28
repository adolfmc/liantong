package com.google.android.flexbox;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.view.MarginLayoutParamsCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import cn.ltzf.passguard.C1730a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import p001a.C0776c;
import p001a.InterfaceC0095a;
import p001a.InterfaceC0701b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.google.android.flexbox.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C4455a {

    /* renamed from: f */
    public static final /* synthetic */ boolean f10453f = !C4455a.class.desiredAssertionStatus();

    /* renamed from: a */
    public final InterfaceC0095a f10454a;

    /* renamed from: b */
    public boolean[] f10455b;
    @Nullable

    /* renamed from: c */
    public int[] f10456c;
    @Nullable

    /* renamed from: d */
    public long[] f10457d;
    @Nullable

    /* renamed from: e */
    public long[] f10458e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4456a {

        /* renamed from: a */
        public List<C0776c> f10459a;

        /* renamed from: b */
        public int f10460b;

        /* renamed from: a */
        public final void m15617a() {
            this.f10459a = null;
            this.f10460b = 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4457b implements Comparable<C4457b> {

        /* renamed from: a */
        public int f10461a;

        /* renamed from: b */
        public int f10462b;

        @Override // java.lang.Comparable
        public final int compareTo(@NonNull C4457b c4457b) {
            C4457b c4457b2 = c4457b;
            int i = this.f10462b;
            int i2 = c4457b2.f10462b;
            return i != i2 ? i - i2 : this.f10461a - c4457b2.f10461a;
        }

        public final String toString() {
            StringBuilder m22016a = C1730a.m22016a("Order{order=");
            m22016a.append(this.f10462b);
            m22016a.append(", index=");
            m22016a.append(this.f10461a);
            m22016a.append('}');
            return m22016a.toString();
        }
    }

    public C4455a(InterfaceC0095a interfaceC0095a) {
        this.f10454a = interfaceC0095a;
    }

    /* renamed from: a */
    public final int m15635a(int i, InterfaceC0701b interfaceC0701b, int i2) {
        int mo15656e;
        InterfaceC0095a interfaceC0095a = this.f10454a;
        int mo15695a = interfaceC0095a.mo15695a(i, interfaceC0701b.mo15659b() + interfaceC0701b.mo15645p() + this.f10454a.getPaddingBottom() + interfaceC0095a.getPaddingTop() + i2, interfaceC0701b.mo15647n());
        int size = View.MeasureSpec.getSize(mo15695a);
        if (size > interfaceC0701b.mo15660a()) {
            mo15656e = interfaceC0701b.mo15660a();
        } else if (size >= interfaceC0701b.mo15656e()) {
            return mo15695a;
        } else {
            mo15656e = interfaceC0701b.mo15656e();
        }
        return View.MeasureSpec.makeMeasureSpec(mo15656e, View.MeasureSpec.getMode(mo15695a));
    }

    /* renamed from: a */
    public final int m15633a(long j) {
        return (int) (j >> 32);
    }

    /* renamed from: a */
    public final List<C0776c> m15626a(List<C0776c> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        C0776c c0776c = new C0776c();
        c0776c.f2399g = (i - i2) / 2;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            if (i3 == 0) {
                arrayList.add(c0776c);
            }
            arrayList.add(list.get(i3));
            if (i3 == list.size() - 1) {
                arrayList.add(c0776c);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public final void m15639a(int i, int i2, int i3) {
        int mode;
        int size;
        int flexDirection = this.f10454a.getFlexDirection();
        switch (flexDirection) {
            case 0:
            case 1:
                mode = View.MeasureSpec.getMode(i2);
                size = View.MeasureSpec.getSize(i2);
                break;
            case 2:
            case 3:
                int mode2 = View.MeasureSpec.getMode(i);
                size = View.MeasureSpec.getSize(i);
                mode = mode2;
                break;
            default:
                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
        }
        List<C0776c> flexLinesInternal = this.f10454a.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.f10454a.getSumOfCrossSize() + i3;
            int i4 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).f2399g = size - i3;
            } else if (flexLinesInternal.size() >= 2) {
                switch (this.f10454a.getAlignContent()) {
                    case 1:
                        int i5 = size - sumOfCrossSize;
                        C0776c c0776c = new C0776c();
                        c0776c.f2399g = i5;
                        flexLinesInternal.add(0, c0776c);
                        return;
                    case 2:
                        break;
                    case 3:
                        if (sumOfCrossSize >= size) {
                            return;
                        }
                        float size2 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                        ArrayList arrayList = new ArrayList();
                        int size3 = flexLinesInternal.size();
                        float f = 0.0f;
                        while (i4 < size3) {
                            arrayList.add(flexLinesInternal.get(i4));
                            if (i4 != flexLinesInternal.size() - 1) {
                                C0776c c0776c2 = new C0776c();
                                if (i4 == flexLinesInternal.size() - 2) {
                                    c0776c2.f2399g = Math.round(f + size2);
                                    f = 0.0f;
                                } else {
                                    c0776c2.f2399g = Math.round(size2);
                                }
                                int i6 = c0776c2.f2399g;
                                float f2 = (size2 - i6) + f;
                                if (f2 > 1.0f) {
                                    c0776c2.f2399g = i6 + 1;
                                    f2 -= 1.0f;
                                } else if (f2 < -1.0f) {
                                    c0776c2.f2399g = i6 - 1;
                                    f2 += 1.0f;
                                }
                                arrayList.add(c0776c2);
                                f = f2;
                            }
                            i4++;
                        }
                        this.f10454a.setFlexLines(arrayList);
                        return;
                    case 4:
                        if (sumOfCrossSize < size) {
                            int size4 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                            ArrayList arrayList2 = new ArrayList();
                            C0776c c0776c3 = new C0776c();
                            c0776c3.f2399g = size4;
                            for (C0776c c0776c4 : flexLinesInternal) {
                                arrayList2.add(c0776c3);
                                arrayList2.add(c0776c4);
                                arrayList2.add(c0776c3);
                            }
                            this.f10454a.setFlexLines(arrayList2);
                            return;
                        }
                        break;
                    case 5:
                        if (sumOfCrossSize >= size) {
                            return;
                        }
                        float size5 = (size - sumOfCrossSize) / flexLinesInternal.size();
                        int size6 = flexLinesInternal.size();
                        float f3 = 0.0f;
                        while (i4 < size6) {
                            C0776c c0776c5 = flexLinesInternal.get(i4);
                            float f4 = c0776c5.f2399g + size5;
                            if (i4 == flexLinesInternal.size() - 1) {
                                f4 += f3;
                                f3 = 0.0f;
                            }
                            int round = Math.round(f4);
                            float f5 = (f4 - round) + f3;
                            if (f5 > 1.0f) {
                                round++;
                                f5 -= 1.0f;
                            } else if (f5 < -1.0f) {
                                round--;
                                f5 += 1.0f;
                            }
                            f3 = f5;
                            c0776c5.f2399g = round;
                            i4++;
                        }
                        return;
                    default:
                        return;
                }
                this.f10454a.setFlexLines(m15626a(flexLinesInternal, size, sumOfCrossSize));
            }
        }
    }

    /* renamed from: a */
    public final void m15638a(int i, int i2, int i3, View view) {
        long[] jArr = this.f10457d;
        if (jArr != null) {
            jArr[i] = (i2 & 4294967295L) | (i3 << 32);
        }
        long[] jArr2 = this.f10458e;
        if (jArr2 != null) {
            jArr2[i] = (view.getMeasuredWidth() & 4294967295L) | (view.getMeasuredHeight() << 32);
        }
    }

    /* renamed from: a */
    public final void m15636a(int i, int i2, C0776c c0776c, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        int max;
        double d;
        int i9;
        double d2;
        float f = c0776c.f2402j;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 < (i5 = c0776c.f2397e)) {
            return;
        }
        float f3 = (i3 - i5) / f;
        c0776c.f2397e = i4 + c0776c.f2398f;
        if (!z) {
            c0776c.f2399g = Integer.MIN_VALUE;
        }
        int i10 = 0;
        boolean z2 = false;
        int i11 = 0;
        float f4 = 0.0f;
        while (i10 < c0776c.f2400h) {
            int i12 = c0776c.f2407o + i10;
            View mo15697a = this.f10454a.mo15697a(i12);
            if (mo15697a == null || mo15697a.getVisibility() == 8) {
                i6 = i5;
            } else {
                InterfaceC0701b interfaceC0701b = (InterfaceC0701b) mo15697a.getLayoutParams();
                int flexDirection = this.f10454a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int i13 = i5;
                    int measuredWidth = mo15697a.getMeasuredWidth();
                    long[] jArr = this.f10458e;
                    if (jArr != null) {
                        measuredWidth = (int) jArr[i12];
                    }
                    int measuredHeight = mo15697a.getMeasuredHeight();
                    long[] jArr2 = this.f10458e;
                    if (jArr2 != null) {
                        i6 = i13;
                        measuredHeight = m15633a(jArr2[i12]);
                    } else {
                        i6 = i13;
                    }
                    if (this.f10455b[i12] || interfaceC0701b.mo15651j() <= 0.0f) {
                        i7 = measuredWidth;
                        i8 = measuredHeight;
                    } else {
                        float mo15651j = (interfaceC0701b.mo15651j() * f3) + measuredWidth;
                        if (i10 == c0776c.f2400h - 1) {
                            mo15651j += f4;
                            f4 = 0.0f;
                        }
                        int round = Math.round(mo15651j);
                        if (round > interfaceC0701b.mo15648m()) {
                            round = interfaceC0701b.mo15648m();
                            this.f10455b[i12] = true;
                            c0776c.f2402j -= interfaceC0701b.mo15651j();
                            z2 = true;
                        } else {
                            float f5 = (mo15651j - round) + f4;
                            double d3 = f5;
                            if (d3 > 1.0d) {
                                round++;
                                d = d3 - 1.0d;
                            } else {
                                if (d3 < -1.0d) {
                                    round--;
                                    d = d3 + 1.0d;
                                }
                                f4 = f5;
                            }
                            f5 = (float) d;
                            f4 = f5;
                        }
                        int m15635a = m15635a(i2, interfaceC0701b, c0776c.f2405m);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        mo15697a.measure(makeMeasureSpec, m15635a);
                        i7 = mo15697a.getMeasuredWidth();
                        i8 = mo15697a.getMeasuredHeight();
                        m15638a(i12, makeMeasureSpec, m15635a, mo15697a);
                        this.f10454a.mo15692a(i12, mo15697a);
                    }
                    max = Math.max(i11, this.f10454a.mo15687a(mo15697a) + interfaceC0701b.mo15659b() + interfaceC0701b.mo15645p() + i8);
                    c0776c.f2397e = interfaceC0701b.mo15657d() + interfaceC0701b.mo15654g() + i7 + c0776c.f2397e;
                } else {
                    int measuredHeight2 = mo15697a.getMeasuredHeight();
                    long[] jArr3 = this.f10458e;
                    if (jArr3 != null) {
                        measuredHeight2 = m15633a(jArr3[i12]);
                    }
                    int measuredWidth2 = mo15697a.getMeasuredWidth();
                    long[] jArr4 = this.f10458e;
                    if (jArr4 != null) {
                        measuredWidth2 = (int) jArr4[i12];
                    }
                    if (this.f10455b[i12] || interfaceC0701b.mo15651j() <= f2) {
                        i9 = i5;
                    } else {
                        float mo15651j2 = (interfaceC0701b.mo15651j() * f3) + measuredHeight2;
                        if (i10 == c0776c.f2400h - 1) {
                            mo15651j2 += f4;
                            f4 = f2;
                        }
                        int round2 = Math.round(mo15651j2);
                        if (round2 > interfaceC0701b.mo15660a()) {
                            round2 = interfaceC0701b.mo15660a();
                            this.f10455b[i12] = true;
                            c0776c.f2402j -= interfaceC0701b.mo15651j();
                            i9 = i5;
                            z2 = true;
                        } else {
                            float f6 = (mo15651j2 - round2) + f4;
                            i9 = i5;
                            double d4 = f6;
                            if (d4 > 1.0d) {
                                round2++;
                                d2 = d4 - 1.0d;
                            } else if (d4 < -1.0d) {
                                round2--;
                                d2 = d4 + 1.0d;
                            } else {
                                f4 = f6;
                            }
                            f4 = (float) d2;
                        }
                        int m15622b = m15622b(i, interfaceC0701b, c0776c.f2405m);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        mo15697a.measure(m15622b, makeMeasureSpec2);
                        measuredWidth2 = mo15697a.getMeasuredWidth();
                        int measuredHeight3 = mo15697a.getMeasuredHeight();
                        m15638a(i12, m15622b, makeMeasureSpec2, mo15697a);
                        this.f10454a.mo15692a(i12, mo15697a);
                        measuredHeight2 = measuredHeight3;
                    }
                    max = Math.max(i11, this.f10454a.mo15687a(mo15697a) + interfaceC0701b.mo15657d() + interfaceC0701b.mo15654g() + measuredWidth2);
                    c0776c.f2397e = interfaceC0701b.mo15659b() + interfaceC0701b.mo15645p() + measuredHeight2 + c0776c.f2397e;
                    i6 = i9;
                }
                c0776c.f2399g = Math.max(c0776c.f2399g, max);
                i11 = max;
            }
            i10++;
            i5 = i6;
            f2 = 0.0f;
        }
        int i14 = i5;
        if (!z2 || i14 == c0776c.f2397e) {
            return;
        }
        m15636a(i, i2, c0776c, i3, i4, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m15632a(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            a.b r0 = (p001a.InterfaceC0701b) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.mo15655f()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.mo15655f()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.mo15648m()
            if (r1 <= r3) goto L26
            int r1 = r0.mo15648m()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.mo15656e()
            if (r2 >= r5) goto L32
            int r2 = r0.mo15656e()
            goto L3e
        L32:
            int r5 = r0.mo15660a()
            if (r2 <= r5) goto L3d
            int r2 = r0.mo15660a()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.m15638a(r8, r1, r0, r7)
            a.a r0 = r6.f10454a
            r0.mo15692a(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.C4455a.m15632a(android.view.View, int):void");
    }

    /* renamed from: a */
    public final void m15631a(View view, int i, int i2) {
        InterfaceC0701b interfaceC0701b = (InterfaceC0701b) view.getLayoutParams();
        int min = Math.min(Math.max(((i - interfaceC0701b.mo15654g()) - interfaceC0701b.mo15657d()) - this.f10454a.mo15687a(view), interfaceC0701b.mo15655f()), interfaceC0701b.mo15648m());
        long[] jArr = this.f10458e;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? m15633a(jArr[i2]) : view.getMeasuredHeight(), 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec2, makeMeasureSpec);
        m15638a(i2, makeMeasureSpec2, makeMeasureSpec, view);
        this.f10454a.mo15692a(i2, view);
    }

    /* renamed from: a */
    public final void m15630a(View view, C0776c c0776c, int i, int i2, int i3, int i4) {
        int mo15659b;
        int mo15659b2;
        int mo15645p;
        int mo15645p2;
        int measuredHeight;
        int i5;
        InterfaceC0701b interfaceC0701b = (InterfaceC0701b) view.getLayoutParams();
        int alignItems = this.f10454a.getAlignItems();
        if (interfaceC0701b.mo15653h() != -1) {
            alignItems = interfaceC0701b.mo15653h();
        }
        int i6 = c0776c.f2399g;
        switch (alignItems) {
            case 0:
            case 4:
                if (this.f10454a.getFlexWrap() == 2) {
                    mo15659b = i2 - interfaceC0701b.mo15659b();
                    mo15659b2 = interfaceC0701b.mo15659b();
                    i5 = i4 - mo15659b2;
                    view.layout(i, mo15659b, i3, i5);
                    return;
                }
                mo15645p = interfaceC0701b.mo15645p() + i2;
                mo15645p2 = interfaceC0701b.mo15645p() + i4;
                break;
            case 1:
                if (this.f10454a.getFlexWrap() == 2) {
                    mo15659b = interfaceC0701b.mo15645p() + view.getMeasuredHeight() + (i2 - i6);
                    measuredHeight = view.getMeasuredHeight() + (i4 - i6);
                    i4 = interfaceC0701b.mo15645p();
                    i5 = i4 + measuredHeight;
                    view.layout(i, mo15659b, i3, i5);
                    return;
                }
                int i7 = i2 + i6;
                mo15645p = (i7 - view.getMeasuredHeight()) - interfaceC0701b.mo15659b();
                mo15645p2 = i7 - interfaceC0701b.mo15659b();
                break;
            case 2:
                int mo15645p3 = ((interfaceC0701b.mo15645p() + (i6 - view.getMeasuredHeight())) - interfaceC0701b.mo15659b()) / 2;
                int i8 = this.f10454a.getFlexWrap() != 2 ? i2 + mo15645p3 : i2 - mo15645p3;
                view.layout(i, i8, i3, view.getMeasuredHeight() + i8);
                return;
            case 3:
                if (this.f10454a.getFlexWrap() != 2) {
                    measuredHeight = Math.max(c0776c.f2404l - view.getBaseline(), interfaceC0701b.mo15645p());
                    mo15659b = i2 + measuredHeight;
                    i5 = i4 + measuredHeight;
                    view.layout(i, mo15659b, i3, i5);
                    return;
                }
                mo15659b2 = Math.max(view.getBaseline() + (c0776c.f2404l - view.getMeasuredHeight()), interfaceC0701b.mo15659b());
                mo15659b = i2 - mo15659b2;
                i5 = i4 - mo15659b2;
                view.layout(i, mo15659b, i3, i5);
                return;
            default:
                return;
        }
        view.layout(i, mo15645p, i3, mo15645p2);
    }

    /* renamed from: a */
    public final void m15629a(View view, C0776c c0776c, boolean z, int i, int i2, int i3, int i4) {
        int mo15657d;
        int mo15657d2;
        InterfaceC0701b interfaceC0701b = (InterfaceC0701b) view.getLayoutParams();
        int alignItems = this.f10454a.getAlignItems();
        if (interfaceC0701b.mo15653h() != -1) {
            alignItems = interfaceC0701b.mo15653h();
        }
        int i5 = c0776c.f2399g;
        switch (alignItems) {
            case 0:
            case 3:
            case 4:
                if (!z) {
                    view.layout(interfaceC0701b.mo15654g() + i, i2, interfaceC0701b.mo15654g() + i3, i4);
                    return;
                }
                mo15657d = i - interfaceC0701b.mo15657d();
                mo15657d2 = i3 - interfaceC0701b.mo15657d();
                break;
            case 1:
                if (!z) {
                    mo15657d = ((i + i5) - view.getMeasuredWidth()) - interfaceC0701b.mo15657d();
                    i3 = (i3 + i5) - view.getMeasuredWidth();
                    mo15657d2 = i3 - interfaceC0701b.mo15657d();
                    break;
                } else {
                    int mo15654g = interfaceC0701b.mo15654g();
                    view.layout(interfaceC0701b.mo15654g() + view.getMeasuredWidth() + (i - i5), i2, mo15654g + view.getMeasuredWidth() + (i3 - i5), i4);
                    return;
                }
            case 2:
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int marginStart = ((MarginLayoutParamsCompat.getMarginStart(marginLayoutParams) + (i5 - view.getMeasuredWidth())) - MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams)) / 2;
                if (!z) {
                    mo15657d = i + marginStart;
                    mo15657d2 = i3 + marginStart;
                    break;
                } else {
                    mo15657d = i - marginStart;
                    mo15657d2 = i3 - marginStart;
                    break;
                }
            default:
                return;
        }
        view.layout(mo15657d, i2, mo15657d2, i4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x00b2, code lost:
        if (m15637a(r11, r14, r9) != false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x020b, code lost:
        if (r2 < (r7 + r14)) goto L132;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02ce  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x029c  */
    /* JADX WARN: Type inference failed for: r3v17, types: [java.util.List<java.lang.Integer>, java.util.ArrayList] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m15628a(com.google.android.flexbox.C4455a.C4456a r28, int r29, int r30, int r31, int r32, int r33, @android.support.annotation.Nullable java.util.List<p001a.C0776c> r34) {
        /*
            Method dump skipped, instructions count: 961
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.C4455a.m15628a(com.google.android.flexbox.a$a, int, int, int, int, int, java.util.List):void");
    }

    /* renamed from: a */
    public final void m15627a(List<C0776c> list, int i) {
        boolean z = f10453f;
        if (!z && this.f10456c == null) {
            throw new AssertionError();
        }
        if (!z && this.f10457d == null) {
            throw new AssertionError();
        }
        int i2 = this.f10456c[i];
        if (i2 == -1) {
            i2 = 0;
        }
        for (int size = list.size() - 1; size >= i2; size--) {
            list.remove(size);
        }
        int[] iArr = this.f10456c;
        int length = iArr.length - 1;
        if (i > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i, length, -1);
        }
        long[] jArr = this.f10457d;
        int length2 = jArr.length - 1;
        if (i > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i, length2, 0L);
        }
    }

    /* renamed from: a */
    public final boolean m15637a(int i, int i2, C0776c c0776c) {
        return i == i2 - 1 && c0776c.m22230a() != 0;
    }

    /* renamed from: a */
    public final int[] m15634a(int i, List<C4457b> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i];
        int i2 = 0;
        for (C4457b c4457b : list) {
            int i3 = c4457b.f10461a;
            iArr[i2] = i3;
            sparseIntArray.append(i3, c4457b.f10462b);
            i2++;
        }
        return iArr;
    }

    /* renamed from: b */
    public final int m15622b(int i, InterfaceC0701b interfaceC0701b, int i2) {
        int mo15655f;
        InterfaceC0095a interfaceC0095a = this.f10454a;
        int mo15680b = interfaceC0095a.mo15680b(i, interfaceC0701b.mo15657d() + interfaceC0701b.mo15654g() + this.f10454a.getPaddingRight() + interfaceC0095a.getPaddingLeft() + i2, interfaceC0701b.mo15649l());
        int size = View.MeasureSpec.getSize(mo15680b);
        if (size > interfaceC0701b.mo15648m()) {
            mo15655f = interfaceC0701b.mo15648m();
        } else if (size >= interfaceC0701b.mo15655f()) {
            return mo15680b;
        } else {
            mo15655f = interfaceC0701b.mo15655f();
        }
        return View.MeasureSpec.makeMeasureSpec(mo15655f, View.MeasureSpec.getMode(mo15680b));
    }

    /* renamed from: b */
    public final void m15625b(int i) {
        int[] copyOf;
        int[] iArr = this.f10456c;
        if (iArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new int[i];
        } else if (iArr.length >= i) {
            return;
        } else {
            int length = iArr.length * 2;
            if (length >= i) {
                i = length;
            }
            copyOf = Arrays.copyOf(iArr, i);
        }
        this.f10456c = copyOf;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
        if (r1 >= r0) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000d, code lost:
        if (r0 < 10) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x000f, code lost:
        r0 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0010, code lost:
        r11.f10455b = new boolean[r0];
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m15624b(int r12, int r13, int r14) {
        /*
            r11 = this;
            a.a r0 = r11.f10454a
            int r0 = r0.getFlexItemCount()
            boolean[] r1 = r11.f10455b
            r2 = 0
            if (r1 != 0) goto L15
            r1 = 10
            if (r0 >= r1) goto L10
        Lf:
            r0 = r1
        L10:
            boolean[] r0 = new boolean[r0]
            r11.f10455b = r0
            goto L21
        L15:
            int r3 = r1.length
            if (r3 >= r0) goto L1e
            int r1 = r1.length
            int r1 = r1 * 2
            if (r1 < r0) goto L10
            goto Lf
        L1e:
            java.util.Arrays.fill(r1, r2)
        L21:
            a.a r0 = r11.f10454a
            int r0 = r0.getFlexItemCount()
            if (r14 < r0) goto L2a
            return
        L2a:
            a.a r0 = r11.f10454a
            int r0 = r0.getFlexDirection()
            a.a r1 = r11.f10454a
            int r1 = r1.getFlexDirection()
            r3 = 1073741824(0x40000000, float:2.0)
            switch(r1) {
                case 0: goto L70;
                case 1: goto L70;
                case 2: goto L52;
                case 3: goto L52;
                default: goto L3b;
            }
        L3b:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = "Invalid flex direction: "
            r13.append(r14)
            r13.append(r0)
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        L52:
            int r0 = android.view.View.MeasureSpec.getMode(r13)
            int r1 = android.view.View.MeasureSpec.getSize(r13)
            if (r0 != r3) goto L5d
            goto L63
        L5d:
            a.a r0 = r11.f10454a
            int r1 = r0.getLargestMainSize()
        L63:
            a.a r0 = r11.f10454a
            int r0 = r0.getPaddingTop()
            a.a r3 = r11.f10454a
            int r3 = r3.getPaddingBottom()
            goto L8e
        L70:
            int r0 = android.view.View.MeasureSpec.getMode(r12)
            int r1 = android.view.View.MeasureSpec.getSize(r12)
            if (r0 != r3) goto L7b
            goto L82
        L7b:
            a.a r0 = r11.f10454a
            int r0 = r0.getLargestMainSize()
            r1 = r0
        L82:
            a.a r0 = r11.f10454a
            int r0 = r0.getPaddingLeft()
            a.a r3 = r11.f10454a
            int r3 = r3.getPaddingRight()
        L8e:
            int r3 = r3 + r0
            int[] r0 = r11.f10456c
            if (r0 == 0) goto L95
            r2 = r0[r14]
        L95:
            a.a r14 = r11.f10454a
            java.util.List r14 = r14.getFlexLinesInternal()
            int r0 = r14.size()
        L9f:
            if (r2 >= r0) goto Lc2
            java.lang.Object r4 = r14.get(r2)
            r7 = r4
            a.c r7 = (p001a.C0776c) r7
            int r4 = r7.f2397e
            if (r4 >= r1) goto Lb6
            r10 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r8 = r1
            r9 = r3
            r4.m15636a(r5, r6, r7, r8, r9, r10)
            goto Lbf
        Lb6:
            r10 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r8 = r1
            r9 = r3
            r4.m15623b(r5, r6, r7, r8, r9, r10)
        Lbf:
            int r2 = r2 + 1
            goto L9f
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.C4455a.m15624b(int, int, int):void");
    }

    /* renamed from: b */
    public final void m15623b(int i, int i2, C0776c c0776c, int i3, int i4, boolean z) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int max;
        int i10 = c0776c.f2397e;
        float f = c0776c.f2403k;
        float f2 = 0.0f;
        if (f <= 0.0f || i3 > i10) {
            return;
        }
        float f3 = (i10 - i3) / f;
        c0776c.f2397e = i4 + c0776c.f2398f;
        if (!z) {
            c0776c.f2399g = Integer.MIN_VALUE;
        }
        int i11 = 0;
        boolean z2 = false;
        int i12 = 0;
        float f4 = 0.0f;
        while (i11 < c0776c.f2400h) {
            int i13 = c0776c.f2407o + i11;
            View mo15697a = this.f10454a.mo15697a(i13);
            if (mo15697a == null || mo15697a.getVisibility() == 8) {
                i5 = i10;
                i6 = i11;
            } else {
                InterfaceC0701b interfaceC0701b = (InterfaceC0701b) mo15697a.getLayoutParams();
                int flexDirection = this.f10454a.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i5 = i10;
                    int i14 = i11;
                    int measuredWidth = mo15697a.getMeasuredWidth();
                    long[] jArr = this.f10458e;
                    if (jArr != null) {
                        measuredWidth = (int) jArr[i13];
                    }
                    int measuredHeight = mo15697a.getMeasuredHeight();
                    long[] jArr2 = this.f10458e;
                    if (jArr2 != null) {
                        i7 = i14;
                        measuredHeight = m15633a(jArr2[i13]);
                    } else {
                        i7 = i14;
                    }
                    if (this.f10455b[i13] || interfaceC0701b.mo15650k() <= 0.0f) {
                        i6 = i7;
                        i8 = measuredWidth;
                        i9 = measuredHeight;
                    } else {
                        float mo15650k = measuredWidth - (interfaceC0701b.mo15650k() * f3);
                        i6 = i7;
                        if (i6 == c0776c.f2400h - 1) {
                            mo15650k += f4;
                            f4 = 0.0f;
                        }
                        int round = Math.round(mo15650k);
                        if (round < interfaceC0701b.mo15655f()) {
                            round = interfaceC0701b.mo15655f();
                            this.f10455b[i13] = true;
                            c0776c.f2403k -= interfaceC0701b.mo15650k();
                            z2 = true;
                        } else {
                            float f5 = (mo15650k - round) + f4;
                            double d = f5;
                            if (d > 1.0d) {
                                round++;
                                f5 -= 1.0f;
                            } else if (d < -1.0d) {
                                round--;
                                f5 += 1.0f;
                            }
                            f4 = f5;
                        }
                        int m15635a = m15635a(i2, interfaceC0701b, c0776c.f2405m);
                        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(round, 1073741824);
                        mo15697a.measure(makeMeasureSpec, m15635a);
                        i8 = mo15697a.getMeasuredWidth();
                        i9 = mo15697a.getMeasuredHeight();
                        m15638a(i13, makeMeasureSpec, m15635a, mo15697a);
                        this.f10454a.mo15692a(i13, mo15697a);
                    }
                    max = Math.max(i12, this.f10454a.mo15687a(mo15697a) + interfaceC0701b.mo15659b() + interfaceC0701b.mo15645p() + i9);
                    c0776c.f2397e = interfaceC0701b.mo15657d() + interfaceC0701b.mo15654g() + i8 + c0776c.f2397e;
                } else {
                    int measuredHeight2 = mo15697a.getMeasuredHeight();
                    long[] jArr3 = this.f10458e;
                    if (jArr3 != null) {
                        measuredHeight2 = m15633a(jArr3[i13]);
                    }
                    int measuredWidth2 = mo15697a.getMeasuredWidth();
                    long[] jArr4 = this.f10458e;
                    if (jArr4 != null) {
                        measuredWidth2 = (int) jArr4[i13];
                    }
                    if (this.f10455b[i13] || interfaceC0701b.mo15650k() <= f2) {
                        i5 = i10;
                        i6 = i11;
                    } else {
                        float mo15650k2 = measuredHeight2 - (interfaceC0701b.mo15650k() * f3);
                        if (i11 == c0776c.f2400h - 1) {
                            mo15650k2 += f4;
                            f4 = f2;
                        }
                        int round2 = Math.round(mo15650k2);
                        if (round2 < interfaceC0701b.mo15656e()) {
                            round2 = interfaceC0701b.mo15656e();
                            this.f10455b[i13] = true;
                            c0776c.f2403k -= interfaceC0701b.mo15650k();
                            i5 = i10;
                            i6 = i11;
                            z2 = true;
                        } else {
                            float f6 = (mo15650k2 - round2) + f4;
                            i5 = i10;
                            i6 = i11;
                            double d2 = f6;
                            if (d2 > 1.0d) {
                                round2++;
                                f6 -= 1.0f;
                            } else if (d2 < -1.0d) {
                                round2--;
                                f6 += 1.0f;
                            }
                            f4 = f6;
                        }
                        int m15622b = m15622b(i, interfaceC0701b, c0776c.f2405m);
                        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(round2, 1073741824);
                        mo15697a.measure(m15622b, makeMeasureSpec2);
                        measuredWidth2 = mo15697a.getMeasuredWidth();
                        int measuredHeight3 = mo15697a.getMeasuredHeight();
                        m15638a(i13, m15622b, makeMeasureSpec2, mo15697a);
                        this.f10454a.mo15692a(i13, mo15697a);
                        measuredHeight2 = measuredHeight3;
                    }
                    max = Math.max(i12, this.f10454a.mo15687a(mo15697a) + interfaceC0701b.mo15657d() + interfaceC0701b.mo15654g() + measuredWidth2);
                    c0776c.f2397e = interfaceC0701b.mo15659b() + interfaceC0701b.mo15645p() + measuredHeight2 + c0776c.f2397e;
                }
                c0776c.f2399g = Math.max(c0776c.f2399g, max);
                i12 = max;
            }
            i11 = i6 + 1;
            i10 = i5;
            f2 = 0.0f;
        }
        int i15 = i10;
        if (!z2 || i15 == c0776c.f2397e) {
            return;
        }
        m15623b(i, i2, c0776c, i3, i4, true);
    }

    /* renamed from: b */
    public final void m15621b(View view, int i, int i2) {
        InterfaceC0701b interfaceC0701b = (InterfaceC0701b) view.getLayoutParams();
        int min = Math.min(Math.max(((i - interfaceC0701b.mo15645p()) - interfaceC0701b.mo15659b()) - this.f10454a.mo15687a(view), interfaceC0701b.mo15656e()), interfaceC0701b.mo15660a());
        long[] jArr = this.f10458e;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? (int) jArr[i2] : view.getMeasuredWidth(), 1073741824);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(min, 1073741824);
        view.measure(makeMeasureSpec, makeMeasureSpec2);
        m15638a(i2, makeMeasureSpec, makeMeasureSpec2, view);
        this.f10454a.mo15692a(i2, view);
    }

    /* renamed from: c */
    public final void m15620c(int i) {
        long[] copyOf;
        long[] jArr = this.f10457d;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new long[i];
        } else if (jArr.length >= i) {
            return;
        } else {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            copyOf = Arrays.copyOf(jArr, i);
        }
        this.f10457d = copyOf;
    }

    /* renamed from: d */
    public final void m15619d(int i) {
        long[] copyOf;
        long[] jArr = this.f10458e;
        if (jArr == null) {
            if (i < 10) {
                i = 10;
            }
            copyOf = new long[i];
        } else if (jArr.length >= i) {
            return;
        } else {
            int length = jArr.length * 2;
            if (length >= i) {
                i = length;
            }
            copyOf = Arrays.copyOf(jArr, i);
        }
        this.f10458e = copyOf;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List<java.lang.Integer>, java.util.ArrayList] */
    /* renamed from: e */
    public final void m15618e(int i) {
        View mo15697a;
        if (i >= this.f10454a.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.f10454a.getFlexDirection();
        if (this.f10454a.getAlignItems() != 4) {
            for (C0776c c0776c : this.f10454a.getFlexLinesInternal()) {
                Iterator it = c0776c.f2406n.iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) it.next();
                    View mo15697a2 = this.f10454a.mo15697a(num.intValue());
                    switch (flexDirection) {
                        case 0:
                        case 1:
                            m15621b(mo15697a2, c0776c.f2399g, num.intValue());
                            break;
                        case 2:
                        case 3:
                            m15631a(mo15697a2, c0776c.f2399g, num.intValue());
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                    }
                }
            }
            return;
        }
        int[] iArr = this.f10456c;
        List<C0776c> flexLinesInternal = this.f10454a.getFlexLinesInternal();
        int size = flexLinesInternal.size();
        for (int i2 = iArr != null ? iArr[i] : 0; i2 < size; i2++) {
            C0776c c0776c2 = flexLinesInternal.get(i2);
            int i3 = c0776c2.f2400h;
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = c0776c2.f2407o + i4;
                if (i4 < this.f10454a.getFlexItemCount() && (mo15697a = this.f10454a.mo15697a(i5)) != null && mo15697a.getVisibility() != 8) {
                    InterfaceC0701b interfaceC0701b = (InterfaceC0701b) mo15697a.getLayoutParams();
                    if (interfaceC0701b.mo15653h() == -1 || interfaceC0701b.mo15653h() == 4) {
                        switch (flexDirection) {
                            case 0:
                            case 1:
                                m15621b(mo15697a, c0776c2.f2399g, i5);
                                break;
                            case 2:
                            case 3:
                                m15631a(mo15697a, c0776c2.f2399g, i5);
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid flex direction: " + flexDirection);
                        }
                    }
                }
            }
        }
    }

    @NonNull
    /* renamed from: a */
    public final List<C4457b> m15640a(int i) {
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            C4457b c4457b = new C4457b();
            c4457b.f10462b = ((InterfaceC0701b) this.f10454a.mo15681b(i2).getLayoutParams()).mo15646o();
            c4457b.f10461a = i2;
            arrayList.add(c4457b);
        }
        return arrayList;
    }
}
