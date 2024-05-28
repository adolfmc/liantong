package com.unicom.pay.widget.ticker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import cn.ltzf.passguard.C1730a;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import p479t0.C14163a;
import p479t0.C14164b;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TickerView extends View {

    /* renamed from: t */
    public static final AccelerateDecelerateInterpolator f20512t = new AccelerateDecelerateInterpolator();

    /* renamed from: a */
    public final TextPaint f20513a;

    /* renamed from: b */
    public final C10730c f20514b;

    /* renamed from: c */
    public final C14164b f20515c;

    /* renamed from: d */
    public final ValueAnimator f20516d;

    /* renamed from: e */
    public C10725d f20517e;

    /* renamed from: f */
    public C10725d f20518f;

    /* renamed from: g */
    public final Rect f20519g;

    /* renamed from: h */
    public String f20520h;

    /* renamed from: i */
    public int f20521i;

    /* renamed from: j */
    public int f20522j;

    /* renamed from: k */
    public int f20523k;

    /* renamed from: l */
    public int f20524l;

    /* renamed from: m */
    public float f20525m;

    /* renamed from: n */
    public int f20526n;

    /* renamed from: o */
    public long f20527o;

    /* renamed from: p */
    public long f20528p;

    /* renamed from: q */
    public Interpolator f20529q;

    /* renamed from: r */
    public boolean f20530r;

    /* renamed from: s */
    public String f20531s;

    /* renamed from: com.unicom.pay.widget.ticker.TickerView$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10722a implements ValueAnimator.AnimatorUpdateListener {
        public C10722a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            TickerView.this.f20515c.m91a(valueAnimator.getAnimatedFraction());
            TickerView.this.m6034a();
            TickerView.this.invalidate();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.ticker.TickerView$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class RunnableC10723b implements Runnable {
        public RunnableC10723b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            TickerView tickerView = TickerView.this;
            AccelerateDecelerateInterpolator accelerateDecelerateInterpolator = TickerView.f20512t;
            tickerView.m6029d();
        }
    }

    /* renamed from: com.unicom.pay.widget.ticker.TickerView$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C10724c extends AnimatorListenerAdapter {

        /* renamed from: a */
        public final /* synthetic */ Runnable f20534a;

        public C10724c(Runnable runnable) {
            this.f20534a = runnable;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            TickerView.this.f20515c.m90b();
            TickerView.this.m6034a();
            TickerView.this.invalidate();
            if (Build.VERSION.SDK_INT >= 26) {
                this.f20534a.run();
            } else {
                TickerView.this.post(this.f20534a);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.ticker.TickerView$d */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class C10725d {

        /* renamed from: a */
        public final String f20536a;

        /* renamed from: b */
        public final long f20537b;

        /* renamed from: c */
        public final long f20538c;

        /* renamed from: d */
        public final Interpolator f20539d;

        public C10725d(String str, long j, long j2, Interpolator interpolator) {
            this.f20536a = str;
            this.f20537b = j;
            this.f20538c = j2;
            this.f20539d = interpolator;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.unicom.pay.widget.ticker.TickerView$e */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum EnumC10726e {
        ANY,
        UP,
        DOWN
    }

    public TickerView(Context context) {
        super(context);
        TextPaint textPaint = new TextPaint(1);
        this.f20513a = textPaint;
        C10730c c10730c = new C10730c(textPaint);
        this.f20514b = c10730c;
        this.f20515c = new C14164b(c10730c);
        this.f20516d = ValueAnimator.ofFloat(1.0f);
        this.f20519g = new Rect();
        m6033a(context, null, 0, 0);
    }

    public TickerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TextPaint textPaint = new TextPaint(1);
        this.f20513a = textPaint;
        C10730c c10730c = new C10730c(textPaint);
        this.f20514b = c10730c;
        this.f20515c = new C14164b(c10730c);
        this.f20516d = ValueAnimator.ofFloat(1.0f);
        this.f20519g = new Rect();
        m6033a(context, attributeSet, 0, 0);
    }

    public TickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TextPaint textPaint = new TextPaint(1);
        this.f20513a = textPaint;
        C10730c c10730c = new C10730c(textPaint);
        this.f20514b = c10730c;
        this.f20515c = new C14164b(c10730c);
        this.f20516d = ValueAnimator.ofFloat(1.0f);
        this.f20519g = new Rect();
        m6033a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public TickerView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        TextPaint textPaint = new TextPaint(1);
        this.f20513a = textPaint;
        C10730c c10730c = new C10730c(textPaint);
        this.f20514b = c10730c;
        this.f20515c = new C14164b(c10730c);
        this.f20516d = ValueAnimator.ofFloat(1.0f);
        this.f20519g = new Rect();
        m6033a(context, attributeSet, i, i2);
    }

    /* JADX WARN: Type inference failed for: r7v2, types: [java.util.HashSet, java.util.Set, java.util.Set<java.lang.Character>] */
    private void setTextInternal(String str) {
        char[] cArr;
        C14164b c14164b;
        int i;
        this.f20520h = str;
        char c = 0;
        char[] charArray = str == null ? new char[0] : str.toCharArray();
        C14164b c14164b2 = this.f20515c;
        if (c14164b2.f27690c != null) {
            int i2 = 0;
            while (i2 < c14164b2.f27688a.size()) {
                C10729b c10729b = c14164b2.f27688a.get(i2);
                c10729b.m6027a();
                if (c10729b.f20560l > 0.0f) {
                    i2++;
                } else {
                    c14164b2.f27688a.remove(i2);
                }
            }
            int size = c14164b2.f27688a.size();
            char[] cArr2 = new char[size];
            for (int i3 = 0; i3 < size; i3++) {
                cArr2[i3] = c14164b2.f27688a.get(i3).f20551c;
            }
            ?? r7 = c14164b2.f27691d;
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            int i5 = 0;
            while (true) {
                char c2 = i4 == size ? (char) 1 : c;
                char c3 = i5 == charArray.length ? (char) 1 : c;
                if (c2 != 0 && c3 != 0) {
                    break;
                } else if (c2 != 0) {
                    C14163a.m94a(arrayList, charArray.length - i5, 1);
                    break;
                } else if (c3 != 0) {
                    C14163a.m94a(arrayList, size - i4, 2);
                    break;
                } else {
                    boolean contains = r7.contains(Character.valueOf(cArr2[i4]));
                    boolean contains2 = r7.contains(Character.valueOf(charArray[i5]));
                    if (contains && contains2) {
                        int m93a = C14163a.m93a(cArr2, i4 + 1, (Set<Character>) r7);
                        int m93a2 = C14163a.m93a(charArray, i5 + 1, (Set<Character>) r7);
                        int i6 = m93a - i4;
                        int i7 = m93a2 - i5;
                        int max = Math.max(i6, i7);
                        if (i6 == i7) {
                            C14163a.m94a(arrayList, max, c);
                            cArr = charArray;
                            c14164b = c14164b2;
                            i = size;
                        } else {
                            int i8 = i6 + 1;
                            int i9 = i7 + 1;
                            int[][] iArr = (int[][]) Array.newInstance(int.class, i8, i9);
                            for (int i10 = 0; i10 < i8; i10++) {
                                iArr[i10][0] = i10;
                            }
                            char c4 = 0;
                            int i11 = 0;
                            while (i11 < i9) {
                                iArr[c4][i11] = i11;
                                i11++;
                                c4 = 0;
                            }
                            for (int i12 = 1; i12 < i8; i12++) {
                                int i13 = 1;
                                while (i13 < i9) {
                                    int i14 = i12 - 1;
                                    int i15 = i13 - 1;
                                    C14164b c14164b3 = c14164b2;
                                    iArr[i12][i13] = Math.min(iArr[i14][i13] + 1, Math.min(iArr[i12][i15] + 1, iArr[i14][i15] + (cArr2[i14 + i4] == charArray[i15 + i5] ? 0 : 1)));
                                    i13++;
                                    c14164b2 = c14164b3;
                                    charArray = charArray;
                                    size = size;
                                }
                            }
                            cArr = charArray;
                            c14164b = c14164b2;
                            i = size;
                            ArrayList arrayList2 = new ArrayList(max * 2);
                            int i16 = i8 - 1;
                            int i17 = i9 - 1;
                            while (true) {
                                if (i16 <= 0 && i17 <= 0) {
                                    break;
                                }
                                if (i16 != 0) {
                                    if (i17 != 0) {
                                        int i18 = i17 - 1;
                                        int i19 = iArr[i16][i18];
                                        int i20 = i16 - 1;
                                        int i21 = iArr[i20][i17];
                                        int i22 = iArr[i20][i18];
                                        if (i19 >= i21 || i19 >= i22) {
                                            if (i21 >= i22) {
                                                arrayList2.add(0);
                                                i16--;
                                                i17--;
                                            }
                                        }
                                    }
                                    arrayList2.add(2);
                                    i16--;
                                }
                                arrayList2.add(1);
                                i17--;
                            }
                            for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                                arrayList.add((Integer) arrayList2.get(size2));
                            }
                        }
                        i4 = m93a;
                        i5 = m93a2;
                        c14164b2 = c14164b;
                        charArray = cArr;
                        size = i;
                        c = 0;
                    } else {
                        char[] cArr3 = charArray;
                        C14164b c14164b4 = c14164b2;
                        int i23 = size;
                        if (contains) {
                            arrayList.add(1);
                            i5++;
                        } else if (contains2) {
                            arrayList.add(2);
                            i4++;
                        } else {
                            arrayList.add(0);
                            i4++;
                            i5++;
                            c = 0;
                            c14164b2 = c14164b4;
                            charArray = cArr3;
                            size = i23;
                        }
                        c14164b2 = c14164b4;
                        charArray = cArr3;
                        size = i23;
                        c = 0;
                    }
                }
            }
            int size3 = arrayList.size();
            int[] iArr2 = new int[size3];
            for (int i24 = c; i24 < arrayList.size(); i24++) {
                iArr2[i24] = ((Integer) arrayList.get(i24)).intValue();
            }
            int i25 = c;
            int i26 = i25;
            int i27 = i26;
            while (i25 < size3) {
                int i28 = iArr2[i25];
                if (i28 != 0) {
                    if (i28 == 1) {
                        c14164b2.f27688a.add(i26, new C10729b(c14164b2.f27690c, c14164b2.f27689b));
                    } else if (i28 != 2) {
                        StringBuilder m22016a = C1730a.m22016a("Unknown action: ");
                        m22016a.append(iArr2[i25]);
                        throw new IllegalArgumentException(m22016a.toString());
                    } else {
                        c14164b2.f27688a.get(i26).m6026a(c);
                        i26++;
                        i25++;
                    }
                }
                c14164b2.f27688a.get(i26).m6026a(charArray[i27]);
                i26++;
                i27++;
                i25++;
            }
            setContentDescription(str);
            return;
        }
        throw new IllegalStateException("Need to call #setCharacterLists first.");
    }

    /* renamed from: a */
    public final void m6034a() {
        boolean z = this.f20521i != m6031b();
        boolean z2 = this.f20522j != getPaddingBottom() + (getPaddingTop() + ((int) this.f20514b.f20568c));
        if (z || z2) {
            requestLayout();
        }
    }

    /* renamed from: b */
    public final int m6031b() {
        float f;
        if (this.f20530r) {
            f = this.f20515c.m92a();
        } else {
            C14164b c14164b = this.f20515c;
            float f2 = 0.0f;
            int size = c14164b.f27688a.size();
            for (int i = 0; i < size; i++) {
                C10729b c10729b = c14164b.f27688a.get(i);
                c10729b.m6027a();
                f2 += c10729b.f20562n;
            }
            f = f2;
        }
        return getPaddingRight() + getPaddingLeft() + ((int) f);
    }

    /* renamed from: c */
    public final void m6030c() {
        this.f20514b.m6024a();
        m6034a();
        invalidate();
    }

    /* renamed from: d */
    public final void m6029d() {
        C10725d c10725d = this.f20518f;
        this.f20517e = c10725d;
        this.f20518f = null;
        if (c10725d == null) {
            return;
        }
        setTextInternal(c10725d.f20536a);
        this.f20516d.setStartDelay(c10725d.f20537b);
        this.f20516d.setDuration(c10725d.f20538c);
        this.f20516d.setInterpolator(c10725d.f20539d);
        this.f20516d.start();
    }

    public boolean getAnimateMeasurementChange() {
        return this.f20530r;
    }

    public long getAnimationDelay() {
        return this.f20527o;
    }

    public long getAnimationDuration() {
        return this.f20528p;
    }

    public Interpolator getAnimationInterpolator() {
        return this.f20529q;
    }

    public int getGravity() {
        return this.f20523k;
    }

    public String getText() {
        return this.f20520h;
    }

    public int getTextColor() {
        return this.f20524l;
    }

    public float getTextSize() {
        return this.f20525m;
    }

    public Typeface getTypeface() {
        return this.f20513a.getTypeface();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        float m92a = this.f20515c.m92a();
        float f = this.f20514b.f20568c;
        int i = this.f20523k;
        Rect rect = this.f20519g;
        int width = rect.width();
        int height = rect.height();
        float f2 = (i & 16) == 16 ? rect.top + ((height - f) / 2.0f) : 0.0f;
        float f3 = (i & 1) == 1 ? ((width - m92a) / 2.0f) + rect.left : 0.0f;
        if ((i & 48) == 48) {
            f2 = 0.0f;
        }
        if ((i & 80) == 80) {
            f2 = rect.top + (height - f);
        }
        if ((i & 8388611) == 8388611) {
            f3 = 0.0f;
        }
        if ((i & 8388613) == 8388613) {
            f3 = (width - m92a) + rect.left;
        }
        canvas.translate(f3, f2);
        canvas.clipRect(0.0f, 0.0f, m92a, f);
        canvas.translate(0.0f, this.f20514b.f20569d);
        C14164b c14164b = this.f20515c;
        TextPaint textPaint = this.f20513a;
        int size = c14164b.f27688a.size();
        for (int i2 = 0; i2 < size; i2++) {
            C10729b c10729b = c14164b.f27688a.get(i2);
            if (c10729b.m6025a(canvas, textPaint, c10729b.f20553e, c10729b.f20556h, c10729b.f20557i)) {
                int i3 = c10729b.f20556h;
                if (i3 >= 0) {
                    c10729b.f20551c = c10729b.f20553e[i3];
                }
                c10729b.f20563o = c10729b.f20557i;
            }
            c10729b.m6025a(canvas, textPaint, c10729b.f20553e, c10729b.f20556h + 1, c10729b.f20557i - c10729b.f20558j);
            c10729b.m6025a(canvas, textPaint, c10729b.f20553e, c10729b.f20556h - 1, c10729b.f20557i + c10729b.f20558j);
            c10729b.m6027a();
            canvas.translate(c10729b.f20560l, 0.0f);
        }
        canvas.restore();
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        this.f20521i = m6031b();
        this.f20522j = getPaddingBottom() + getPaddingTop() + ((int) this.f20514b.f20568c);
        setMeasuredDimension(View.resolveSize(this.f20521i, i), View.resolveSize(this.f20522j, i2));
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f20519g.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
    }

    public void setAnimateMeasurementChange(boolean z) {
        this.f20530r = z;
    }

    public void setAnimationDelay(long j) {
        this.f20527o = j;
    }

    public void setAnimationDuration(long j) {
        this.f20528p = j;
    }

    public void setAnimationInterpolator(Interpolator interpolator) {
        this.f20529q = interpolator;
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.Map<java.lang.Character, java.lang.Integer>, java.util.HashMap] */
    public void setCharacterLists(String... strArr) {
        C14164b c14164b = this.f20515c;
        c14164b.getClass();
        c14164b.f27690c = new C10727a[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            c14164b.f27690c[i] = new C10727a(strArr[i]);
        }
        c14164b.f27691d = new HashSet();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            c14164b.f27691d.addAll(c14164b.f27690c[i2].f20546c.keySet());
        }
        Iterator<C10729b> it = c14164b.f27688a.iterator();
        while (it.hasNext()) {
            it.next().f20549a = c14164b.f27690c;
        }
        String str = this.f20531s;
        if (str != null) {
            m6032a(str, false);
            this.f20531s = null;
        }
    }

    public void setGravity(int i) {
        if (this.f20523k != i) {
            this.f20523k = i;
            invalidate();
        }
    }

    public void setPaintFlags(int i) {
        this.f20513a.setFlags(i);
        m6030c();
    }

    public void setPreferredScrollingDirection(EnumC10726e enumC10726e) {
        this.f20514b.f20570e = enumC10726e;
    }

    public void setText(String str) {
        m6032a(str, !TextUtils.isEmpty(this.f20520h));
    }

    public void setTextColor(int i) {
        if (this.f20524l != i) {
            this.f20524l = i;
            this.f20513a.setColor(i);
            invalidate();
        }
    }

    public void setTextSize(float f) {
        if (this.f20525m != f) {
            this.f20525m = f;
            this.f20513a.setTextSize(f);
            m6030c();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x000f, code lost:
        if (r0 == 2) goto L3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTypeface(android.graphics.Typeface r3) {
        /*
            r2 = this;
            int r0 = r2.f20526n
            r1 = 3
            if (r0 != r1) goto La
        L5:
            android.graphics.Typeface r3 = android.graphics.Typeface.create(r3, r1)
            goto L12
        La:
            r1 = 1
            if (r0 != r1) goto Le
            goto L5
        Le:
            r1 = 2
            if (r0 != r1) goto L12
            goto L5
        L12:
            android.text.TextPaint r0 = r2.f20513a
            r0.setTypeface(r3)
            r2.m6030c()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.ticker.TickerView.setTypeface(android.graphics.Typeface):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x00e2, code lost:
        if (isInEditMode() != false) goto L34;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0132  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m6033a(android.content.Context r11, android.util.AttributeSet r12, int r13, int r14) {
        /*
            Method dump skipped, instructions count: 337
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.pay.widget.ticker.TickerView.m6033a(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    /* renamed from: a */
    public final void m6032a(String str, boolean z) {
        if (TextUtils.equals(str, this.f20520h)) {
            return;
        }
        if (!z && this.f20516d.isRunning()) {
            this.f20516d.cancel();
            this.f20518f = null;
            this.f20517e = null;
        }
        if (z) {
            this.f20518f = new C10725d(str, this.f20527o, this.f20528p, this.f20529q);
            if (this.f20517e == null) {
                m6029d();
                return;
            }
            return;
        }
        setTextInternal(str);
        this.f20515c.m91a(1.0f);
        this.f20515c.m90b();
        m6034a();
        invalidate();
    }
}
