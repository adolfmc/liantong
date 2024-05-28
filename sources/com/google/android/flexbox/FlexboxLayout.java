package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import cn.ltzf.passguard.C1730a;
import com.google.android.flexbox.C4455a;
import com.unicom.pay.C10531R;
import java.util.ArrayList;
import java.util.List;
import p001a.C0776c;
import p001a.InterfaceC0095a;
import p001a.InterfaceC0701b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FlexboxLayout extends ViewGroup implements InterfaceC0095a {

    /* renamed from: a */
    public int f10370a;

    /* renamed from: b */
    public int f10371b;

    /* renamed from: c */
    public int f10372c;

    /* renamed from: d */
    public int f10373d;

    /* renamed from: e */
    public int f10374e;

    /* renamed from: f */
    public int f10375f;
    @Nullable

    /* renamed from: g */
    public Drawable f10376g;
    @Nullable

    /* renamed from: h */
    public Drawable f10377h;

    /* renamed from: i */
    public int f10378i;

    /* renamed from: j */
    public int f10379j;

    /* renamed from: k */
    public int f10380k;

    /* renamed from: l */
    public int f10381l;

    /* renamed from: m */
    public int[] f10382m;

    /* renamed from: n */
    public SparseIntArray f10383n;

    /* renamed from: o */
    public C4455a f10384o;

    /* renamed from: p */
    public List<C0776c> f10385p;

    /* renamed from: q */
    public C4455a.C4456a f10386q;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.FlexboxLayout$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4446a extends ViewGroup.MarginLayoutParams implements InterfaceC0701b {
        public static final Parcelable.Creator<C4446a> CREATOR = new C4447a();

        /* renamed from: a */
        public int f10387a;

        /* renamed from: b */
        public float f10388b;

        /* renamed from: c */
        public float f10389c;

        /* renamed from: d */
        public int f10390d;

        /* renamed from: e */
        public float f10391e;

        /* renamed from: f */
        public int f10392f;

        /* renamed from: g */
        public int f10393g;

        /* renamed from: h */
        public int f10394h;

        /* renamed from: i */
        public int f10395i;

        /* renamed from: j */
        public boolean f10396j;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.google.android.flexbox.FlexboxLayout$a$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class C4447a implements Parcelable.Creator<C4446a> {
            @Override // android.os.Parcelable.Creator
            public final C4446a createFromParcel(Parcel parcel) {
                return new C4446a(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final C4446a[] newArray(int i) {
                return new C4446a[i];
            }
        }

        public C4446a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f10387a = 1;
            this.f10388b = 0.0f;
            this.f10389c = 1.0f;
            this.f10390d = -1;
            this.f10391e = -1.0f;
            this.f10394h = 16777215;
            this.f10395i = 16777215;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C10531R.styleable.FlexboxLayout_Layout);
            this.f10387a = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_Layout_layout_order, 1);
            this.f10388b = obtainStyledAttributes.getFloat(C10531R.styleable.FlexboxLayout_Layout_layout_flexGrow, 0.0f);
            this.f10389c = obtainStyledAttributes.getFloat(C10531R.styleable.FlexboxLayout_Layout_layout_flexShrink, 1.0f);
            this.f10390d = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_Layout_layout_alignSelf, -1);
            this.f10391e = obtainStyledAttributes.getFraction(C10531R.styleable.FlexboxLayout_Layout_layout_flexBasisPercent, 1, 1, -1.0f);
            this.f10392f = obtainStyledAttributes.getDimensionPixelSize(C10531R.styleable.FlexboxLayout_Layout_layout_minWidth, 0);
            this.f10393g = obtainStyledAttributes.getDimensionPixelSize(C10531R.styleable.FlexboxLayout_Layout_layout_minHeight, 0);
            this.f10394h = obtainStyledAttributes.getDimensionPixelSize(C10531R.styleable.FlexboxLayout_Layout_layout_maxWidth, 16777215);
            this.f10395i = obtainStyledAttributes.getDimensionPixelSize(C10531R.styleable.FlexboxLayout_Layout_layout_maxHeight, 16777215);
            this.f10396j = obtainStyledAttributes.getBoolean(C10531R.styleable.FlexboxLayout_Layout_layout_wrapBefore, false);
            obtainStyledAttributes.recycle();
        }

        public C4446a(Parcel parcel) {
            super(0, 0);
            this.f10387a = 1;
            this.f10388b = 0.0f;
            this.f10389c = 1.0f;
            this.f10390d = -1;
            this.f10391e = -1.0f;
            this.f10394h = 16777215;
            this.f10395i = 16777215;
            this.f10387a = parcel.readInt();
            this.f10388b = parcel.readFloat();
            this.f10389c = parcel.readFloat();
            this.f10390d = parcel.readInt();
            this.f10391e = parcel.readFloat();
            this.f10392f = parcel.readInt();
            this.f10393g = parcel.readInt();
            this.f10394h = parcel.readInt();
            this.f10395i = parcel.readInt();
            this.f10396j = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }

        public C4446a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f10387a = 1;
            this.f10388b = 0.0f;
            this.f10389c = 1.0f;
            this.f10390d = -1;
            this.f10391e = -1.0f;
            this.f10394h = 16777215;
            this.f10395i = 16777215;
        }

        public C4446a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f10387a = 1;
            this.f10388b = 0.0f;
            this.f10389c = 1.0f;
            this.f10390d = -1;
            this.f10391e = -1.0f;
            this.f10394h = 16777215;
            this.f10395i = 16777215;
        }

        public C4446a(C4446a c4446a) {
            super((ViewGroup.MarginLayoutParams) c4446a);
            this.f10387a = 1;
            this.f10388b = 0.0f;
            this.f10389c = 1.0f;
            this.f10390d = -1;
            this.f10391e = -1.0f;
            this.f10394h = 16777215;
            this.f10395i = 16777215;
            this.f10387a = c4446a.f10387a;
            this.f10388b = c4446a.f10388b;
            this.f10389c = c4446a.f10389c;
            this.f10390d = c4446a.f10390d;
            this.f10391e = c4446a.f10391e;
            this.f10392f = c4446a.f10392f;
            this.f10393g = c4446a.f10393g;
            this.f10394h = c4446a.f10394h;
            this.f10395i = c4446a.f10395i;
            this.f10396j = c4446a.f10396j;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: a */
        public final int mo15660a() {
            return this.f10395i;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: b */
        public final int mo15659b() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: c */
        public final float mo15658c() {
            return this.f10391e;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: d */
        public final int mo15657d() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: e */
        public final int mo15656e() {
            return this.f10393g;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: f */
        public final int mo15655f() {
            return this.f10392f;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: g */
        public final int mo15654g() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: h */
        public final int mo15653h() {
            return this.f10390d;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: i */
        public final boolean mo15652i() {
            return this.f10396j;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: j */
        public final float mo15651j() {
            return this.f10388b;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: k */
        public final float mo15650k() {
            return this.f10389c;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: l */
        public final int mo15649l() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: m */
        public final int mo15648m() {
            return this.f10394h;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: n */
        public final int mo15647n() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: o */
        public final int mo15646o() {
            return this.f10387a;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: p */
        public final int mo15645p() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f10387a);
            parcel.writeFloat(this.f10388b);
            parcel.writeFloat(this.f10389c);
            parcel.writeInt(this.f10390d);
            parcel.writeFloat(this.f10391e);
            parcel.writeInt(this.f10392f);
            parcel.writeInt(this.f10393g);
            parcel.writeInt(this.f10394h);
            parcel.writeInt(this.f10395i);
            parcel.writeByte(this.f10396j ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }
    }

    public FlexboxLayout(Context context) {
        this(context, null);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FlexboxLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f10375f = -1;
        this.f10384o = new C4455a(this);
        this.f10385p = new ArrayList();
        this.f10386q = new C4455a.C4456a();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C10531R.styleable.FlexboxLayout, i, 0);
        this.f10370a = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_flexDirection, 0);
        this.f10371b = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_flexWrap, 0);
        this.f10372c = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_justifyContent, 0);
        this.f10373d = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_alignItems, 4);
        this.f10374e = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_alignContent, 5);
        this.f10375f = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_maxLine, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(C10531R.styleable.FlexboxLayout_dividerDrawable);
        if (drawable != null) {
            setDividerDrawableHorizontal(drawable);
            setDividerDrawableVertical(drawable);
        }
        Drawable drawable2 = obtainStyledAttributes.getDrawable(C10531R.styleable.FlexboxLayout_dividerDrawableHorizontal);
        if (drawable2 != null) {
            setDividerDrawableHorizontal(drawable2);
        }
        Drawable drawable3 = obtainStyledAttributes.getDrawable(C10531R.styleable.FlexboxLayout_dividerDrawableVertical);
        if (drawable3 != null) {
            setDividerDrawableVertical(drawable3);
        }
        int i2 = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_showDivider, 0);
        if (i2 != 0) {
            this.f10379j = i2;
            this.f10378i = i2;
        }
        int i3 = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_showDividerVertical, 0);
        if (i3 != 0) {
            this.f10379j = i3;
        }
        int i4 = obtainStyledAttributes.getInt(C10531R.styleable.FlexboxLayout_showDividerHorizontal, 0);
        if (i4 != 0) {
            this.f10378i = i4;
        }
        obtainStyledAttributes.recycle();
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15695a(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15687a(View view) {
        return 0;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15686a(View view, int i, int i2) {
        int i3;
        int i4;
        if (mo15698a()) {
            i3 = m15709a(i, i2) ? 0 + this.f10381l : 0;
            if ((this.f10379j & 4) <= 0) {
                return i3;
            }
            i4 = this.f10381l;
        } else {
            i3 = m15709a(i, i2) ? 0 + this.f10380k : 0;
            if ((this.f10378i & 4) <= 0) {
                return i3;
            }
            i4 = this.f10380k;
        }
        return i3 + i4;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final View mo15697a(int i) {
        return m15701c(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0059, code lost:
        if (r1 < r4) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008c, code lost:
        if (r3 < r8) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ab  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void m15708a(int r8, int r9, int r10, int r11) {
        /*
            r7 = this;
            int r0 = android.view.View.MeasureSpec.getMode(r9)
            int r1 = android.view.View.MeasureSpec.getSize(r9)
            int r2 = android.view.View.MeasureSpec.getMode(r10)
            int r3 = android.view.View.MeasureSpec.getSize(r10)
            switch(r8) {
                case 0: goto L3d;
                case 1: goto L3d;
                case 2: goto L2a;
                case 3: goto L2a;
                default: goto L13;
            }
        L13:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Invalid flex direction: "
            r10.append(r11)
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r9.<init>(r8)
            throw r9
        L2a:
            int r8 = r7.getLargestMainSize()
            int r4 = r7.getSumOfCrossSize()
            int r5 = r7.getPaddingLeft()
            int r5 = r5 + r4
            int r4 = r7.getPaddingRight()
            int r4 = r4 + r5
            goto L4f
        L3d:
            int r8 = r7.getSumOfCrossSize()
            int r4 = r7.getPaddingTop()
            int r4 = r4 + r8
            int r8 = r7.getPaddingBottom()
            int r8 = r8 + r4
            int r4 = r7.getLargestMainSize()
        L4f:
            r5 = 1073741824(0x40000000, float:2.0)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r6) goto L78
            if (r0 == 0) goto L73
            if (r0 != r5) goto L5c
            if (r1 >= r4) goto L82
            goto L7a
        L5c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Unknown width mode is set: "
            r9.append(r10)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L73:
            int r9 = android.view.View.resolveSizeAndState(r4, r9, r11)
            goto L86
        L78:
            if (r1 >= r4) goto L81
        L7a:
            r0 = 16777216(0x1000000, float:2.3509887E-38)
            int r11 = android.view.View.combineMeasuredStates(r11, r0)
            goto L82
        L81:
            r1 = r4
        L82:
            int r9 = android.view.View.resolveSizeAndState(r1, r9, r11)
        L86:
            if (r2 == r6) goto Lab
            if (r2 == 0) goto La6
            if (r2 != r5) goto L8f
            if (r3 >= r8) goto Lb5
            goto Lad
        L8f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Unknown height mode is set: "
            r9.append(r10)
            r9.append(r2)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        La6:
            int r8 = android.view.View.resolveSizeAndState(r8, r10, r11)
            goto Lb9
        Lab:
            if (r3 >= r8) goto Lb4
        Lad:
            r8 = 256(0x100, float:3.59E-43)
            int r11 = android.view.View.combineMeasuredStates(r11, r8)
            goto Lb5
        Lb4:
            r3 = r8
        Lb5:
            int r8 = android.view.View.resolveSizeAndState(r3, r10, r11)
        Lb9:
            r7.setMeasuredDimension(r9, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.m15708a(int, int, int, int):void");
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15692a(int i, View view) {
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15691a(C0776c c0776c) {
        int i;
        int i2;
        if (mo15698a()) {
            if ((this.f10379j & 4) <= 0) {
                return;
            }
            i = c0776c.f2397e;
            i2 = this.f10381l;
        } else if ((this.f10378i & 4) <= 0) {
            return;
        } else {
            i = c0776c.f2397e;
            i2 = this.f10380k;
        }
        c0776c.f2397e = i + i2;
        c0776c.f2398f += i2;
    }

    /* renamed from: a */
    public final void m15707a(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.f10376g;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, i3 + i, this.f10380k + i2);
        this.f10376g.draw(canvas);
    }

    /* renamed from: a */
    public final void m15706a(Canvas canvas, boolean z, boolean z2) {
        int paddingLeft = getPaddingLeft();
        int max = Math.max(0, (getWidth() - getPaddingRight()) - paddingLeft);
        int size = this.f10385p.size();
        for (int i = 0; i < size; i++) {
            C0776c c0776c = this.f10385p.get(i);
            for (int i2 = 0; i2 < c0776c.f2400h; i2++) {
                int i3 = c0776c.f2407o + i2;
                View m15701c = m15701c(i3);
                if (m15701c != null && m15701c.getVisibility() != 8) {
                    C4446a c4446a = (C4446a) m15701c.getLayoutParams();
                    if (m15709a(i3, i2)) {
                        m15703b(canvas, z ? m15701c.getRight() + ((ViewGroup.MarginLayoutParams) c4446a).rightMargin : (m15701c.getLeft() - ((ViewGroup.MarginLayoutParams) c4446a).leftMargin) - this.f10381l, c0776c.f2394b, c0776c.f2399g);
                    }
                    if (i2 == c0776c.f2400h - 1 && (this.f10379j & 4) > 0) {
                        m15703b(canvas, z ? (m15701c.getLeft() - ((ViewGroup.MarginLayoutParams) c4446a).leftMargin) - this.f10381l : m15701c.getRight() + ((ViewGroup.MarginLayoutParams) c4446a).rightMargin, c0776c.f2394b, c0776c.f2399g);
                    }
                }
            }
            if (m15700d(i)) {
                m15707a(canvas, paddingLeft, z2 ? c0776c.f2396d : c0776c.f2394b - this.f10380k, max);
            }
            if (m15699e(i) && (this.f10378i & 4) > 0) {
                m15707a(canvas, paddingLeft, z2 ? c0776c.f2394b - this.f10380k : c0776c.f2396d, max);
            }
        }
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15685a(View view, int i, int i2, C0776c c0776c) {
        int i3;
        int i4;
        if (m15709a(i, i2)) {
            if (mo15698a()) {
                i3 = c0776c.f2397e;
                i4 = this.f10381l;
            } else {
                i3 = c0776c.f2397e;
                i4 = this.f10380k;
            }
            c0776c.f2397e = i3 + i4;
            c0776c.f2398f += i4;
        }
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final boolean mo15698a() {
        int i = this.f10370a;
        return i == 0 || i == 1;
    }

    /* renamed from: a */
    public final boolean m15709a(int i, int i2) {
        boolean z;
        int i3 = 1;
        while (true) {
            if (i3 > i2) {
                z = true;
                break;
            }
            View m15701c = m15701c(i - i3);
            if (m15701c != null && m15701c.getVisibility() != 8) {
                z = false;
                break;
            }
            i3++;
        }
        return z ? mo15698a() ? (this.f10379j & 1) != 0 : (this.f10378i & 1) != 0 : mo15698a() ? (this.f10379j & 2) != 0 : (this.f10378i & 2) != 0;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.f10383n == null) {
            this.f10383n = new SparseIntArray(getChildCount());
        }
        C4455a c4455a = this.f10384o;
        SparseIntArray sparseIntArray = this.f10383n;
        int flexItemCount = c4455a.f10454a.getFlexItemCount();
        List<C4455a.C4457b> m15640a = c4455a.m15640a(flexItemCount);
        C4455a.C4457b c4457b = new C4455a.C4457b();
        if (view == null || !(layoutParams instanceof InterfaceC0701b)) {
            c4457b.f10462b = 1;
        } else {
            c4457b.f10462b = ((InterfaceC0701b) layoutParams).mo15646o();
        }
        if (i == -1 || i == flexItemCount || i >= c4455a.f10454a.getFlexItemCount()) {
            c4457b.f10461a = flexItemCount;
        } else {
            c4457b.f10461a = i;
            for (int i2 = i; i2 < flexItemCount; i2++) {
                ((C4455a.C4457b) ((ArrayList) m15640a).get(i2)).f10461a++;
            }
        }
        ((ArrayList) m15640a).add(c4457b);
        this.f10382m = c4455a.m15634a(flexItemCount + 1, m15640a, sparseIntArray);
        super.addView(view, i, layoutParams);
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: b */
    public final int mo15680b(int i, int i2, int i3) {
        return ViewGroup.getChildMeasureSpec(i, i2, i3);
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: b */
    public final View mo15681b(int i) {
        return getChildAt(i);
    }

    /* renamed from: b */
    public final void m15703b(Canvas canvas, int i, int i2, int i3) {
        Drawable drawable = this.f10377h;
        if (drawable == null) {
            return;
        }
        drawable.setBounds(i, i2, this.f10381l + i, i3 + i2);
        this.f10377h.draw(canvas);
    }

    /* renamed from: b */
    public final void m15702b(Canvas canvas, boolean z, boolean z2) {
        int paddingTop = getPaddingTop();
        int max = Math.max(0, (getHeight() - getPaddingBottom()) - paddingTop);
        int size = this.f10385p.size();
        for (int i = 0; i < size; i++) {
            C0776c c0776c = this.f10385p.get(i);
            for (int i2 = 0; i2 < c0776c.f2400h; i2++) {
                int i3 = c0776c.f2407o + i2;
                View m15701c = m15701c(i3);
                if (m15701c != null && m15701c.getVisibility() != 8) {
                    C4446a c4446a = (C4446a) m15701c.getLayoutParams();
                    if (m15709a(i3, i2)) {
                        m15707a(canvas, c0776c.f2393a, z2 ? m15701c.getBottom() + ((ViewGroup.MarginLayoutParams) c4446a).bottomMargin : (m15701c.getTop() - ((ViewGroup.MarginLayoutParams) c4446a).topMargin) - this.f10380k, c0776c.f2399g);
                    }
                    if (i2 == c0776c.f2400h - 1 && (this.f10378i & 4) > 0) {
                        m15707a(canvas, c0776c.f2393a, z2 ? (m15701c.getTop() - ((ViewGroup.MarginLayoutParams) c4446a).topMargin) - this.f10380k : m15701c.getBottom() + ((ViewGroup.MarginLayoutParams) c4446a).bottomMargin, c0776c.f2399g);
                    }
                }
            }
            if (m15700d(i)) {
                m15703b(canvas, z ? c0776c.f2395c : c0776c.f2393a - this.f10381l, paddingTop, max);
            }
            if (m15699e(i) && (this.f10379j & 4) > 0) {
                m15703b(canvas, z ? c0776c.f2393a - this.f10381l : c0776c.f2395c, paddingTop, max);
            }
        }
    }

    /* renamed from: c */
    public final View m15701c(int i) {
        if (i >= 0) {
            int[] iArr = this.f10382m;
            if (i >= iArr.length) {
                return null;
            }
            return getChildAt(iArr[i]);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C4446a;
    }

    /* renamed from: d */
    public final boolean m15700d(int i) {
        boolean z;
        if (i < 0 || i >= this.f10385p.size()) {
            return false;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (this.f10385p.get(i2).m22230a() > 0) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        return z ? mo15698a() ? (this.f10378i & 1) != 0 : (this.f10379j & 1) != 0 : mo15698a() ? (this.f10378i & 2) != 0 : (this.f10379j & 2) != 0;
    }

    /* renamed from: e */
    public final boolean m15699e(int i) {
        if (i < 0 || i >= this.f10385p.size()) {
            return false;
        }
        do {
            i++;
            if (i >= this.f10385p.size()) {
                return mo15698a() ? (this.f10378i & 4) != 0 : (this.f10379j & 4) != 0;
            }
        } while (this.f10385p.get(i).m22230a() <= 0);
        return false;
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C4446a(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C4446a ? new C4446a((C4446a) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C4446a((ViewGroup.MarginLayoutParams) layoutParams) : new C4446a(layoutParams);
    }

    @Override // p001a.InterfaceC0095a
    public int getAlignContent() {
        return this.f10374e;
    }

    @Override // p001a.InterfaceC0095a
    public int getAlignItems() {
        return this.f10373d;
    }

    @Nullable
    public Drawable getDividerDrawableHorizontal() {
        return this.f10376g;
    }

    @Nullable
    public Drawable getDividerDrawableVertical() {
        return this.f10377h;
    }

    @Override // p001a.InterfaceC0095a
    public int getFlexDirection() {
        return this.f10370a;
    }

    @Override // p001a.InterfaceC0095a
    public int getFlexItemCount() {
        return getChildCount();
    }

    public List<C0776c> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.f10385p.size());
        for (C0776c c0776c : this.f10385p) {
            if (c0776c.m22230a() != 0) {
                arrayList.add(c0776c);
            }
        }
        return arrayList;
    }

    @Override // p001a.InterfaceC0095a
    public List<C0776c> getFlexLinesInternal() {
        return this.f10385p;
    }

    @Override // p001a.InterfaceC0095a
    public int getFlexWrap() {
        return this.f10371b;
    }

    public int getJustifyContent() {
        return this.f10372c;
    }

    @Override // p001a.InterfaceC0095a
    public int getLargestMainSize() {
        int i = Integer.MIN_VALUE;
        for (C0776c c0776c : this.f10385p) {
            i = Math.max(i, c0776c.f2397e);
        }
        return i;
    }

    @Override // p001a.InterfaceC0095a
    public int getMaxLine() {
        return this.f10375f;
    }

    public int getShowDividerHorizontal() {
        return this.f10378i;
    }

    public int getShowDividerVertical() {
        return this.f10379j;
    }

    @Override // p001a.InterfaceC0095a
    public int getSumOfCrossSize() {
        int size = this.f10385p.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            C0776c c0776c = this.f10385p.get(i2);
            if (m15700d(i2)) {
                i += mo15698a() ? this.f10380k : this.f10381l;
            }
            if (m15699e(i2)) {
                i += mo15698a() ? this.f10380k : this.f10381l;
            }
            i += c0776c.f2399g;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0041, code lost:
        if (r5.f10371b == 2) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x004b, code lost:
        if (r5.f10371b == 2) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x004d, code lost:
        r3 = true;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onDraw(android.graphics.Canvas r6) {
        /*
            r5 = this;
            android.graphics.drawable.Drawable r0 = r5.f10377h
            if (r0 != 0) goto L9
            android.graphics.drawable.Drawable r0 = r5.f10376g
            if (r0 != 0) goto L9
            return
        L9:
            int r0 = r5.f10378i
            if (r0 != 0) goto L12
            int r0 = r5.f10379j
            if (r0 != 0) goto L12
            return
        L12:
            int r0 = android.support.p083v4.view.ViewCompat.getLayoutDirection(r5)
            int r1 = r5.f10370a
            r2 = 2
            r3 = 0
            r4 = 1
            switch(r1) {
                case 0: goto L44;
                case 1: goto L3a;
                case 2: goto L2c;
                case 3: goto L1f;
                default: goto L1e;
            }
        L1e:
            goto L51
        L1f:
            if (r0 != r4) goto L22
            r3 = r4
        L22:
            int r0 = r5.f10371b
            if (r0 != r2) goto L28
            r3 = r3 ^ 1
        L28:
            r5.m15702b(r6, r3, r4)
            goto L51
        L2c:
            if (r0 != r4) goto L2f
            goto L30
        L2f:
            r4 = r3
        L30:
            int r0 = r5.f10371b
            if (r0 != r2) goto L36
            r4 = r4 ^ 1
        L36:
            r5.m15702b(r6, r4, r3)
            goto L51
        L3a:
            if (r0 == r4) goto L3e
            r0 = r4
            goto L3f
        L3e:
            r0 = r3
        L3f:
            int r1 = r5.f10371b
            if (r1 != r2) goto L4e
            goto L4d
        L44:
            if (r0 != r4) goto L48
            r0 = r4
            goto L49
        L48:
            r0 = r3
        L49:
            int r1 = r5.f10371b
            if (r1 != r2) goto L4e
        L4d:
            r3 = r4
        L4e:
            r5.m15706a(r6, r0, r3)
        L51:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onDraw(android.graphics.Canvas):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
        if (r0 != 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0048, code lost:
        if (r0 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004a, code lost:
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004c, code lost:
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004d, code lost:
        m15705a(r1, r9, r10, r11, r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
        return;
     */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLayout(boolean r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            int r0 = android.support.p083v4.view.ViewCompat.getLayoutDirection(r7)
            int r1 = r7.f10370a
            r2 = 2
            r3 = 0
            r4 = 1
            switch(r1) {
                case 0: goto L48;
                case 1: goto L45;
                case 2: goto L2f;
                case 3: goto L21;
                default: goto Lc;
            }
        Lc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Invalid flex direction is set: "
            java.lang.StringBuilder r1 = cn.ltzf.passguard.C1730a.m22016a(r1)
            int r2 = r7.f10370a
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L21:
            if (r0 != r4) goto L24
            r3 = r4
        L24:
            int r0 = r7.f10371b
            if (r0 != r2) goto L2c
            r0 = r3 ^ 1
            r1 = r0
            goto L2d
        L2c:
            r1 = r3
        L2d:
            r2 = 1
            goto L3c
        L2f:
            if (r0 != r4) goto L32
            r3 = r4
        L32:
            int r0 = r7.f10371b
            if (r0 != r2) goto L3a
            r0 = r3 ^ 1
            r1 = r0
            goto L3b
        L3a:
            r1 = r3
        L3b:
            r2 = 0
        L3c:
            r0 = r7
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            r0.m15704a(r1, r2, r3, r4, r5, r6)
            goto L55
        L45:
            if (r0 == r4) goto L4c
            goto L4a
        L48:
            if (r0 != r4) goto L4c
        L4a:
            r1 = r4
            goto L4d
        L4c:
            r1 = r3
        L4d:
            r0 = r7
            r2 = r9
            r3 = r10
            r4 = r11
            r5 = r12
            r0.m15705a(r1, r2, r3, r4, r5)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00b5  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onMeasure(int r14, int r15) {
        /*
            Method dump skipped, instructions count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayout.onMeasure(int, int):void");
    }

    public void setAlignContent(int i) {
        if (this.f10374e != i) {
            this.f10374e = i;
            requestLayout();
        }
    }

    public void setAlignItems(int i) {
        if (this.f10373d != i) {
            this.f10373d = i;
            requestLayout();
        }
    }

    public void setDividerDrawable(Drawable drawable) {
        setDividerDrawableHorizontal(drawable);
        setDividerDrawableVertical(drawable);
    }

    public void setDividerDrawableHorizontal(@Nullable Drawable drawable) {
        if (drawable == this.f10376g) {
            return;
        }
        this.f10376g = drawable;
        if (drawable != null) {
            this.f10380k = drawable.getIntrinsicHeight();
        } else {
            this.f10380k = 0;
        }
        if (this.f10376g == null && this.f10377h == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
        requestLayout();
    }

    public void setDividerDrawableVertical(@Nullable Drawable drawable) {
        if (drawable == this.f10377h) {
            return;
        }
        this.f10377h = drawable;
        if (drawable != null) {
            this.f10381l = drawable.getIntrinsicWidth();
        } else {
            this.f10381l = 0;
        }
        if (this.f10376g == null && this.f10377h == null) {
            setWillNotDraw(true);
        } else {
            setWillNotDraw(false);
        }
        requestLayout();
    }

    public void setFlexDirection(int i) {
        if (this.f10370a != i) {
            this.f10370a = i;
            requestLayout();
        }
    }

    @Override // p001a.InterfaceC0095a
    public void setFlexLines(List<C0776c> list) {
        this.f10385p = list;
    }

    public void setFlexWrap(int i) {
        if (this.f10371b != i) {
            this.f10371b = i;
            requestLayout();
        }
    }

    public void setJustifyContent(int i) {
        if (this.f10372c != i) {
            this.f10372c = i;
            requestLayout();
        }
    }

    public void setMaxLine(int i) {
        if (this.f10375f != i) {
            this.f10375f = i;
            requestLayout();
        }
    }

    public void setShowDivider(int i) {
        setShowDividerVertical(i);
        setShowDividerHorizontal(i);
    }

    public void setShowDividerHorizontal(int i) {
        if (i != this.f10378i) {
            this.f10378i = i;
            requestLayout();
        }
    }

    public void setShowDividerVertical(int i) {
        if (i != this.f10379j) {
            this.f10379j = i;
            requestLayout();
        }
    }

    /* renamed from: a */
    public final void m15705a(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        float f2;
        float f3;
        int m22230a;
        int i6;
        int i7;
        float f4;
        float f5;
        int i8;
        C4455a c4455a;
        int round;
        int measuredWidth;
        int measuredHeight;
        View view;
        C0776c c0776c;
        int i9;
        int i10;
        int i11;
        C0776c c0776c2;
        View view2;
        int i12;
        int i13;
        int m22230a2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int i14 = i3 - i;
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        int paddingTop = getPaddingTop();
        int size = this.f10385p.size();
        int i15 = paddingBottom;
        for (int i16 = 0; i16 < size; i16++) {
            C0776c c0776c3 = this.f10385p.get(i16);
            if (m15700d(i16)) {
                int i17 = this.f10380k;
                i15 -= i17;
                paddingTop += i17;
            }
            int i18 = 1;
            switch (this.f10372c) {
                case 0:
                    f = paddingLeft;
                    i5 = i14 - paddingRight;
                    f2 = i5;
                    f3 = 0.0f;
                    break;
                case 1:
                    int i19 = c0776c3.f2397e;
                    f = (i14 - i19) + paddingRight;
                    i5 = i19 - paddingLeft;
                    f2 = i5;
                    f3 = 0.0f;
                    break;
                case 2:
                    float f6 = (i14 - c0776c3.f2397e) / 2.0f;
                    f = f6 + paddingLeft;
                    f2 = (i14 - paddingRight) - f6;
                    f3 = 0.0f;
                    break;
                case 3:
                    f = paddingLeft;
                    f3 = (i14 - c0776c3.f2397e) / (c0776c3.m22230a() != 1 ? m22230a - 1 : 1.0f);
                    f2 = i14 - paddingRight;
                    break;
                case 4:
                    int m22230a3 = c0776c3.m22230a();
                    f3 = m22230a3 != 0 ? (i14 - c0776c3.f2397e) / m22230a3 : 0.0f;
                    float f7 = f3 / 2.0f;
                    f2 = (i14 - paddingRight) - f7;
                    f = paddingLeft + f7;
                    break;
                case 5:
                    f3 = c0776c3.m22230a() != 0 ? (i14 - c0776c3.f2397e) / (m22230a2 + 1) : 0.0f;
                    f = paddingLeft + f3;
                    f2 = (i14 - paddingRight) - f3;
                    break;
                default:
                    StringBuilder m22016a = C1730a.m22016a("Invalid justifyContent is set: ");
                    m22016a.append(this.f10372c);
                    throw new IllegalStateException(m22016a.toString());
            }
            float max = Math.max(f3, 0.0f);
            int i20 = 0;
            while (i20 < c0776c3.f2400h) {
                int i21 = c0776c3.f2407o + i20;
                View m15701c = m15701c(i21);
                if (m15701c == null || m15701c.getVisibility() == 8) {
                    i6 = i20;
                    i7 = i18;
                } else {
                    C4446a c4446a = (C4446a) m15701c.getLayoutParams();
                    float f8 = f + ((ViewGroup.MarginLayoutParams) c4446a).leftMargin;
                    float f9 = f2 - ((ViewGroup.MarginLayoutParams) c4446a).rightMargin;
                    if (m15709a(i21, i20)) {
                        int i22 = this.f10381l;
                        float f10 = i22;
                        i8 = i22;
                        f4 = f8 + f10;
                        f5 = f9 - f10;
                    } else {
                        f4 = f8;
                        f5 = f9;
                        i8 = 0;
                    }
                    int i23 = (i20 != c0776c3.f2400h - i18 || (this.f10379j & 4) <= 0) ? 0 : this.f10381l;
                    if (this.f10371b == 2) {
                        if (z) {
                            c4455a = this.f10384o;
                            round = Math.round(f5) - m15701c.getMeasuredWidth();
                            view = m15701c;
                            c0776c = c0776c3;
                            i6 = i20;
                            i9 = i15 - m15701c.getMeasuredHeight();
                            i7 = i18;
                            measuredWidth = Math.round(f5);
                        } else {
                            i6 = i20;
                            i7 = i18;
                            c4455a = this.f10384o;
                            round = Math.round(f4);
                            i9 = i15 - m15701c.getMeasuredHeight();
                            measuredWidth = m15701c.getMeasuredWidth() + Math.round(f4);
                            view = m15701c;
                            c0776c = c0776c3;
                        }
                        measuredHeight = i15;
                    } else {
                        i6 = i20;
                        i7 = i18;
                        if (z) {
                            c4455a = this.f10384o;
                            round = Math.round(f5) - m15701c.getMeasuredWidth();
                            measuredWidth = Math.round(f5);
                        } else {
                            c4455a = this.f10384o;
                            round = Math.round(f4);
                            measuredWidth = m15701c.getMeasuredWidth() + Math.round(f4);
                        }
                        measuredHeight = m15701c.getMeasuredHeight() + paddingTop;
                        view = m15701c;
                        c0776c = c0776c3;
                        i9 = paddingTop;
                    }
                    c4455a.m15630a(view, c0776c, round, i9, measuredWidth, measuredHeight);
                    float measuredWidth2 = m15701c.getMeasuredWidth() + max + ((ViewGroup.MarginLayoutParams) c4446a).rightMargin + f4;
                    float measuredWidth3 = f5 - ((m15701c.getMeasuredWidth() + max) + ((ViewGroup.MarginLayoutParams) c4446a).leftMargin);
                    if (z) {
                        i10 = 0;
                        i11 = 0;
                        c0776c2 = c0776c3;
                        view2 = m15701c;
                        i12 = i23;
                        i13 = i8;
                    } else {
                        i10 = 0;
                        i11 = 0;
                        c0776c2 = c0776c3;
                        view2 = m15701c;
                        i12 = i8;
                        i13 = i23;
                    }
                    c0776c2.m22229a(view2, i12, i10, i13, i11);
                    f = measuredWidth2;
                    f2 = measuredWidth3;
                }
                i20 = i6 + 1;
                i18 = i7;
            }
            int i24 = c0776c3.f2399g;
            paddingTop += i24;
            i15 -= i24;
        }
    }

    /* renamed from: a */
    public final void m15704a(boolean z, boolean z2, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        float f2;
        float f3;
        int m22230a;
        int i6;
        int i7;
        float f4;
        float f5;
        int i8;
        C4455a c4455a;
        int round;
        int measuredWidth;
        int measuredHeight;
        boolean z3;
        View view;
        C0776c c0776c;
        int i9;
        int i10;
        int i11;
        C0776c c0776c2;
        View view2;
        int i12;
        int i13;
        int m22230a2;
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingRight = getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i14 = i4 - i2;
        int i15 = (i3 - i) - paddingRight;
        int size = this.f10385p.size();
        int i16 = paddingLeft;
        for (int i17 = 0; i17 < size; i17++) {
            C0776c c0776c3 = this.f10385p.get(i17);
            if (m15700d(i17)) {
                int i18 = this.f10381l;
                i16 += i18;
                i15 -= i18;
            }
            int i19 = 1;
            switch (this.f10372c) {
                case 0:
                    f = paddingTop;
                    i5 = i14 - paddingBottom;
                    f2 = i5;
                    f3 = 0.0f;
                    break;
                case 1:
                    int i20 = c0776c3.f2397e;
                    f = (i14 - i20) + paddingBottom;
                    i5 = i20 - paddingTop;
                    f2 = i5;
                    f3 = 0.0f;
                    break;
                case 2:
                    float f6 = (i14 - c0776c3.f2397e) / 2.0f;
                    f = f6 + paddingTop;
                    f2 = (i14 - paddingBottom) - f6;
                    f3 = 0.0f;
                    break;
                case 3:
                    f = paddingTop;
                    f3 = (i14 - c0776c3.f2397e) / (c0776c3.m22230a() != 1 ? m22230a - 1 : 1.0f);
                    f2 = i14 - paddingBottom;
                    break;
                case 4:
                    int m22230a3 = c0776c3.m22230a();
                    f3 = m22230a3 != 0 ? (i14 - c0776c3.f2397e) / m22230a3 : 0.0f;
                    float f7 = f3 / 2.0f;
                    f2 = (i14 - paddingBottom) - f7;
                    f = paddingTop + f7;
                    break;
                case 5:
                    f3 = c0776c3.m22230a() != 0 ? (i14 - c0776c3.f2397e) / (m22230a2 + 1) : 0.0f;
                    f = paddingTop + f3;
                    f2 = (i14 - paddingBottom) - f3;
                    break;
                default:
                    StringBuilder m22016a = C1730a.m22016a("Invalid justifyContent is set: ");
                    m22016a.append(this.f10372c);
                    throw new IllegalStateException(m22016a.toString());
            }
            float max = Math.max(f3, 0.0f);
            int i21 = 0;
            while (i21 < c0776c3.f2400h) {
                int i22 = c0776c3.f2407o + i21;
                View m15701c = m15701c(i22);
                if (m15701c == null || m15701c.getVisibility() == 8) {
                    i6 = i21;
                    i7 = i19;
                } else {
                    C4446a c4446a = (C4446a) m15701c.getLayoutParams();
                    float f8 = f + ((ViewGroup.MarginLayoutParams) c4446a).topMargin;
                    float f9 = f2 - ((ViewGroup.MarginLayoutParams) c4446a).bottomMargin;
                    if (m15709a(i22, i21)) {
                        int i23 = this.f10380k;
                        float f10 = i23;
                        i8 = i23;
                        f4 = f8 + f10;
                        f5 = f9 - f10;
                    } else {
                        f4 = f8;
                        f5 = f9;
                        i8 = 0;
                    }
                    int i24 = (i21 != c0776c3.f2400h - i19 || (this.f10378i & 4) <= 0) ? 0 : this.f10380k;
                    if (!z) {
                        i6 = i21;
                        i7 = i19;
                        if (z2) {
                            c4455a = this.f10384o;
                            round = Math.round(f5) - m15701c.getMeasuredHeight();
                            measuredWidth = m15701c.getMeasuredWidth() + i16;
                            measuredHeight = Math.round(f5);
                        } else {
                            c4455a = this.f10384o;
                            round = Math.round(f4);
                            measuredWidth = m15701c.getMeasuredWidth() + i16;
                            measuredHeight = m15701c.getMeasuredHeight() + Math.round(f4);
                        }
                        z3 = false;
                        view = m15701c;
                        c0776c = c0776c3;
                        i9 = i16;
                    } else if (z2) {
                        c4455a = this.f10384o;
                        z3 = true;
                        view = m15701c;
                        c0776c = c0776c3;
                        i6 = i21;
                        i9 = i15 - m15701c.getMeasuredWidth();
                        i7 = i19;
                        round = Math.round(f5) - m15701c.getMeasuredHeight();
                        measuredWidth = i15;
                        measuredHeight = Math.round(f5);
                    } else {
                        i6 = i21;
                        i7 = i19;
                        c4455a = this.f10384o;
                        i9 = i15 - m15701c.getMeasuredWidth();
                        round = Math.round(f4);
                        measuredHeight = m15701c.getMeasuredHeight() + Math.round(f4);
                        z3 = true;
                        view = m15701c;
                        c0776c = c0776c3;
                        measuredWidth = i15;
                    }
                    c4455a.m15629a(view, c0776c, z3, i9, round, measuredWidth, measuredHeight);
                    float measuredHeight2 = m15701c.getMeasuredHeight() + max + ((ViewGroup.MarginLayoutParams) c4446a).bottomMargin + f4;
                    float measuredHeight3 = f5 - ((m15701c.getMeasuredHeight() + max) + ((ViewGroup.MarginLayoutParams) c4446a).topMargin);
                    if (z2) {
                        i10 = 0;
                        i11 = 0;
                        c0776c2 = c0776c3;
                        view2 = m15701c;
                        i12 = i24;
                        i13 = i8;
                    } else {
                        i10 = 0;
                        i11 = 0;
                        c0776c2 = c0776c3;
                        view2 = m15701c;
                        i12 = i8;
                        i13 = i24;
                    }
                    c0776c2.m22229a(view2, i10, i12, i11, i13);
                    f = measuredHeight2;
                    f2 = measuredHeight3;
                }
                i21 = i6 + 1;
                i19 = i7;
            }
            int i25 = c0776c3.f2399g;
            i16 += i25;
            i15 -= i25;
        }
    }
}
