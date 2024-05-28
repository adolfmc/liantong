package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p086v7.widget.LinearSmoothScroller;
import android.support.p086v7.widget.OrientationHelper;
import android.support.p086v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import cn.ltzf.passguard.C1730a;
import com.google.android.flexbox.C4455a;
import java.util.ArrayList;
import java.util.List;
import p001a.C0776c;
import p001a.InterfaceC0095a;
import p001a.InterfaceC0701b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements InterfaceC0095a, RecyclerView.SmoothScroller.ScrollVectorProvider {

    /* renamed from: a */
    public int f10399a;

    /* renamed from: b */
    public int f10400b;

    /* renamed from: c */
    public int f10401c;

    /* renamed from: e */
    public boolean f10403e;

    /* renamed from: f */
    public boolean f10404f;

    /* renamed from: i */
    public RecyclerView.Recycler f10407i;

    /* renamed from: j */
    public RecyclerView.State f10408j;

    /* renamed from: k */
    public C4452d f10409k;

    /* renamed from: m */
    public OrientationHelper f10411m;

    /* renamed from: n */
    public OrientationHelper f10412n;

    /* renamed from: o */
    public C4453e f10413o;

    /* renamed from: u */
    public final Context f10419u;

    /* renamed from: v */
    public View f10420v;

    /* renamed from: z */
    public static final /* synthetic */ boolean f10398z = !FlexboxLayoutManager.class.desiredAssertionStatus();

    /* renamed from: y */
    public static final Rect f10397y = new Rect();

    /* renamed from: d */
    public int f10402d = -1;

    /* renamed from: g */
    public List<C0776c> f10405g = new ArrayList();

    /* renamed from: h */
    public final C4455a f10406h = new C4455a(this);

    /* renamed from: l */
    public C4449b f10410l = new C4449b();

    /* renamed from: p */
    public int f10414p = -1;

    /* renamed from: q */
    public int f10415q = Integer.MIN_VALUE;

    /* renamed from: r */
    public int f10416r = Integer.MIN_VALUE;

    /* renamed from: s */
    public int f10417s = Integer.MIN_VALUE;

    /* renamed from: t */
    public SparseArray<View> f10418t = new SparseArray<>();

    /* renamed from: w */
    public int f10421w = -1;

    /* renamed from: x */
    public C4455a.C4456a f10422x = new C4455a.C4456a();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C4449b {

        /* renamed from: i */
        public static final /* synthetic */ boolean f10423i = !FlexboxLayoutManager.class.desiredAssertionStatus();

        /* renamed from: a */
        public int f10424a;

        /* renamed from: b */
        public int f10425b;

        /* renamed from: c */
        public int f10426c;

        /* renamed from: d */
        public int f10427d;

        /* renamed from: e */
        public boolean f10428e;

        /* renamed from: f */
        public boolean f10429f;

        /* renamed from: g */
        public boolean f10430g;

        public C4449b() {
            this.f10427d = 0;
        }

        /* renamed from: a */
        public static void m15663a(C4449b c4449b) {
            int startAfterPadding;
            OrientationHelper orientationHelper;
            if (!FlexboxLayoutManager.this.mo15698a()) {
                FlexboxLayoutManager flexboxLayoutManager = FlexboxLayoutManager.this;
                if (flexboxLayoutManager.f10403e) {
                    if (c4449b.f10428e) {
                        orientationHelper = flexboxLayoutManager.f10411m;
                        startAfterPadding = orientationHelper.getEndAfterPadding();
                        c4449b.f10426c = startAfterPadding;
                    }
                    startAfterPadding = flexboxLayoutManager.getWidth() - FlexboxLayoutManager.this.f10411m.getStartAfterPadding();
                    c4449b.f10426c = startAfterPadding;
                }
            }
            if (c4449b.f10428e) {
                orientationHelper = FlexboxLayoutManager.this.f10411m;
                startAfterPadding = orientationHelper.getEndAfterPadding();
                c4449b.f10426c = startAfterPadding;
            }
            startAfterPadding = FlexboxLayoutManager.this.f10411m.getStartAfterPadding();
            c4449b.f10426c = startAfterPadding;
        }

        /* renamed from: b */
        public static void m15661b(C4449b c4449b) {
            FlexboxLayoutManager flexboxLayoutManager;
            int i;
            FlexboxLayoutManager flexboxLayoutManager2;
            int i2;
            c4449b.f10424a = -1;
            c4449b.f10425b = -1;
            c4449b.f10426c = Integer.MIN_VALUE;
            boolean z = false;
            c4449b.f10429f = false;
            c4449b.f10430g = false;
            if (!FlexboxLayoutManager.this.mo15698a() ? !((i = (flexboxLayoutManager = FlexboxLayoutManager.this).f10400b) != 0 ? i != 2 : flexboxLayoutManager.f10399a != 3) : !((i2 = (flexboxLayoutManager2 = FlexboxLayoutManager.this).f10400b) != 0 ? i2 != 2 : flexboxLayoutManager2.f10399a != 1)) {
                z = true;
            }
            c4449b.f10428e = z;
        }

        public final String toString() {
            StringBuilder m22016a = C1730a.m22016a("AnchorInfo{mPosition=");
            m22016a.append(this.f10424a);
            m22016a.append(", mFlexLinePosition=");
            m22016a.append(this.f10425b);
            m22016a.append(", mCoordinate=");
            m22016a.append(this.f10426c);
            m22016a.append(", mPerpendicularCoordinate=");
            m22016a.append(this.f10427d);
            m22016a.append(", mLayoutFromEnd=");
            m22016a.append(this.f10428e);
            m22016a.append(", mValid=");
            m22016a.append(this.f10429f);
            m22016a.append(", mAssignedFromSavedState=");
            m22016a.append(this.f10430g);
            m22016a.append('}');
            return m22016a.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4450c extends RecyclerView.LayoutParams implements InterfaceC0701b {
        public static final Parcelable.Creator<C4450c> CREATOR = new C4451a();

        /* renamed from: a */
        public float f10432a;

        /* renamed from: b */
        public float f10433b;

        /* renamed from: c */
        public int f10434c;

        /* renamed from: d */
        public float f10435d;

        /* renamed from: e */
        public int f10436e;

        /* renamed from: f */
        public int f10437f;

        /* renamed from: g */
        public int f10438g;

        /* renamed from: h */
        public int f10439h;

        /* renamed from: i */
        public boolean f10440i;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$c$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class C4451a implements Parcelable.Creator<C4450c> {
            @Override // android.os.Parcelable.Creator
            public final C4450c createFromParcel(Parcel parcel) {
                return new C4450c(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final C4450c[] newArray(int i) {
                return new C4450c[i];
            }
        }

        public C4450c() {
            super(-2, -2);
            this.f10432a = 0.0f;
            this.f10433b = 1.0f;
            this.f10434c = -1;
            this.f10435d = -1.0f;
            this.f10438g = 16777215;
            this.f10439h = 16777215;
        }

        public C4450c(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f10432a = 0.0f;
            this.f10433b = 1.0f;
            this.f10434c = -1;
            this.f10435d = -1.0f;
            this.f10438g = 16777215;
            this.f10439h = 16777215;
        }

        public C4450c(Parcel parcel) {
            super(-2, -2);
            this.f10432a = 0.0f;
            this.f10433b = 1.0f;
            this.f10434c = -1;
            this.f10435d = -1.0f;
            this.f10438g = 16777215;
            this.f10439h = 16777215;
            this.f10432a = parcel.readFloat();
            this.f10433b = parcel.readFloat();
            this.f10434c = parcel.readInt();
            this.f10435d = parcel.readFloat();
            this.f10436e = parcel.readInt();
            this.f10437f = parcel.readInt();
            this.f10438g = parcel.readInt();
            this.f10439h = parcel.readInt();
            this.f10440i = parcel.readByte() != 0;
            ((RecyclerView.LayoutParams) this).bottomMargin = parcel.readInt();
            ((RecyclerView.LayoutParams) this).leftMargin = parcel.readInt();
            ((RecyclerView.LayoutParams) this).rightMargin = parcel.readInt();
            ((RecyclerView.LayoutParams) this).topMargin = parcel.readInt();
            ((RecyclerView.LayoutParams) this).height = parcel.readInt();
            ((RecyclerView.LayoutParams) this).width = parcel.readInt();
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: a */
        public final int mo15660a() {
            return this.f10439h;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: b */
        public final int mo15659b() {
            return ((RecyclerView.LayoutParams) this).bottomMargin;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: c */
        public final float mo15658c() {
            return this.f10435d;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: d */
        public final int mo15657d() {
            return ((RecyclerView.LayoutParams) this).rightMargin;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: e */
        public final int mo15656e() {
            return this.f10437f;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: f */
        public final int mo15655f() {
            return this.f10436e;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: g */
        public final int mo15654g() {
            return ((RecyclerView.LayoutParams) this).leftMargin;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: h */
        public final int mo15653h() {
            return this.f10434c;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: i */
        public final boolean mo15652i() {
            return this.f10440i;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: j */
        public final float mo15651j() {
            return this.f10432a;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: k */
        public final float mo15650k() {
            return this.f10433b;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: l */
        public final int mo15649l() {
            return ((RecyclerView.LayoutParams) this).width;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: m */
        public final int mo15648m() {
            return this.f10438g;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: n */
        public final int mo15647n() {
            return ((RecyclerView.LayoutParams) this).height;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: o */
        public final int mo15646o() {
            return 1;
        }

        @Override // p001a.InterfaceC0701b
        /* renamed from: p */
        public final int mo15645p() {
            return ((RecyclerView.LayoutParams) this).topMargin;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.f10432a);
            parcel.writeFloat(this.f10433b);
            parcel.writeInt(this.f10434c);
            parcel.writeFloat(this.f10435d);
            parcel.writeInt(this.f10436e);
            parcel.writeInt(this.f10437f);
            parcel.writeInt(this.f10438g);
            parcel.writeInt(this.f10439h);
            parcel.writeByte(this.f10440i ? (byte) 1 : (byte) 0);
            parcel.writeInt(((RecyclerView.LayoutParams) this).bottomMargin);
            parcel.writeInt(((RecyclerView.LayoutParams) this).leftMargin);
            parcel.writeInt(((RecyclerView.LayoutParams) this).rightMargin);
            parcel.writeInt(((RecyclerView.LayoutParams) this).topMargin);
            parcel.writeInt(((RecyclerView.LayoutParams) this).height);
            parcel.writeInt(((RecyclerView.LayoutParams) this).width);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4452d {

        /* renamed from: a */
        public int f10441a;

        /* renamed from: b */
        public boolean f10442b;

        /* renamed from: c */
        public int f10443c;

        /* renamed from: d */
        public int f10444d;

        /* renamed from: e */
        public int f10445e;

        /* renamed from: f */
        public int f10446f;

        /* renamed from: g */
        public int f10447g;

        /* renamed from: h */
        public int f10448h = 1;

        /* renamed from: i */
        public int f10449i = 1;

        /* renamed from: j */
        public boolean f10450j;

        /* renamed from: a */
        public static boolean m15644a(C4452d c4452d, RecyclerView.State state, List list) {
            int i;
            int i2 = c4452d.f10444d;
            return i2 >= 0 && i2 < state.getItemCount() && (i = c4452d.f10443c) >= 0 && i < list.size();
        }

        public final String toString() {
            StringBuilder m22016a = C1730a.m22016a("LayoutState{mAvailable=");
            m22016a.append(this.f10441a);
            m22016a.append(", mFlexLinePosition=");
            m22016a.append(this.f10443c);
            m22016a.append(", mPosition=");
            m22016a.append(this.f10444d);
            m22016a.append(", mOffset=");
            m22016a.append(this.f10445e);
            m22016a.append(", mScrollingOffset=");
            m22016a.append(this.f10446f);
            m22016a.append(", mLastScrollDelta=");
            m22016a.append(this.f10447g);
            m22016a.append(", mItemDirection=");
            m22016a.append(this.f10448h);
            m22016a.append(", mLayoutDirection=");
            m22016a.append(this.f10449i);
            m22016a.append('}');
            return m22016a.toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$e */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C4453e implements Parcelable {
        public static final Parcelable.Creator<C4453e> CREATOR = new C4454a();

        /* renamed from: a */
        public int f10451a;

        /* renamed from: b */
        public int f10452b;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.google.android.flexbox.FlexboxLayoutManager$e$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public class C4454a implements Parcelable.Creator<C4453e> {
            @Override // android.os.Parcelable.Creator
            public final C4453e createFromParcel(Parcel parcel) {
                return new C4453e(parcel);
            }

            @Override // android.os.Parcelable.Creator
            public final C4453e[] newArray(int i) {
                return new C4453e[i];
            }
        }

        public C4453e() {
        }

        public C4453e(Parcel parcel) {
            this.f10451a = parcel.readInt();
            this.f10452b = parcel.readInt();
        }

        public C4453e(C4453e c4453e) {
            this.f10451a = c4453e.f10451a;
            this.f10452b = c4453e.f10452b;
        }

        /* renamed from: a */
        public static boolean m15641a(C4453e c4453e, int i) {
            int i2 = c4453e.f10451a;
            return i2 >= 0 && i2 < i;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            StringBuilder m22016a = C1730a.m22016a("SavedState{mAnchorPosition=");
            m22016a.append(this.f10451a);
            m22016a.append(", mAnchorOffset=");
            m22016a.append(this.f10452b);
            m22016a.append('}');
            return m22016a.toString();
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.f10451a);
            parcel.writeInt(this.f10452b);
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        int i3;
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        int i4 = properties.orientation;
        if (i4 != 0) {
            if (i4 == 1) {
                i3 = properties.reverseLayout ? 3 : 2;
                m15666g(i3);
            }
        } else if (properties.reverseLayout) {
            m15666g(1);
        } else {
            i3 = 0;
            m15666g(i3);
        }
        m15665h(1);
        m15667f(4);
        setAutoMeasureEnabled(true);
        this.f10419u = context;
    }

    public static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 <= 0 || i == i3) {
            if (mode == Integer.MIN_VALUE) {
                return size >= i;
            } else if (mode != 0) {
                return mode == 1073741824 && size == i;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i, layoutParams.width) && isMeasurementUpToDate(view.getHeight(), i2, layoutParams.height)) ? false : true;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15695a(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    /* renamed from: a */
    public final int m15693a(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int endAfterPadding;
        if (!mo15698a() && this.f10403e) {
            int startAfterPadding = i - this.f10411m.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = m15694a(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.f10411m.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -m15694a(-endAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (endAfterPadding = this.f10411m.getEndAfterPadding() - i3) <= 0) {
            return i2;
        }
        this.f10411m.offsetChildren(endAfterPadding);
        return endAfterPadding + i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x036e, code lost:
        m15689a(r32, r34);
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0375, code lost:
        return r20 - r34.f10441a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0359, code lost:
        r3 = r34.f10441a - r24;
        r34.f10441a = r3;
        r4 = r34.f10446f;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0363, code lost:
        if (r4 == Integer.MIN_VALUE) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0365, code lost:
        r4 = r4 + r24;
        r34.f10446f = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0369, code lost:
        if (r3 >= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x036b, code lost:
        r34.f10446f = r4 + r3;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m15690a(android.support.p086v7.widget.RecyclerView.Recycler r32, android.support.p086v7.widget.RecyclerView.State r33, com.google.android.flexbox.FlexboxLayoutManager.C4452d r34) {
        /*
            Method dump skipped, instructions count: 886
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.m15690a(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, com.google.android.flexbox.FlexboxLayoutManager$d):int");
    }

    /* renamed from: a */
    public final int m15688a(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        m15675c();
        View m15674c = m15674c(itemCount);
        View m15670d = m15670d(itemCount);
        if (state.getItemCount() == 0 || m15674c == null || m15670d == null) {
            return 0;
        }
        return Math.min(this.f10411m.getTotalSpace(), this.f10411m.getDecoratedEnd(m15670d) - this.f10411m.getDecoratedStart(m15674c));
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15687a(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (mo15698a()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return rightDecorationWidth + leftDecorationWidth;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final int mo15686a(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (mo15698a()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return bottomDecorationHeight + topDecorationHeight;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final View mo15697a(int i) {
        return mo15681b(i);
    }

    /* renamed from: a */
    public final View m15684a(View view, C0776c c0776c) {
        boolean mo15698a = mo15698a();
        int i = c0776c.f2400h;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (!this.f10403e || mo15698a) {
                    if (this.f10411m.getDecoratedStart(view) <= this.f10411m.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.f10411m.getDecoratedEnd(view) >= this.f10411m.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15692a(int i, View view) {
        this.f10418t.put(i, view);
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15691a(C0776c c0776c) {
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final void mo15685a(View view, int i, int i2, C0776c c0776c) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        calculateItemDecorationsForChild(view, f10397y);
        if (mo15698a()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        int i3 = bottomDecorationHeight + topDecorationHeight;
        c0776c.f2397e += i3;
        c0776c.f2398f += i3;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: a */
    public final boolean mo15698a() {
        int i = this.f10399a;
        return i == 0 || i == 1;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: b */
    public final int mo15680b(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    /* renamed from: b */
    public final int m15679b(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int startAfterPadding;
        if (mo15698a() || !this.f10403e) {
            int startAfterPadding2 = i - this.f10411m.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -m15694a(startAfterPadding2, recycler, state);
        } else {
            int endAfterPadding = this.f10411m.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = m15694a(-endAfterPadding, recycler, state);
        }
        int i3 = i + i2;
        if (!z || (startAfterPadding = i3 - this.f10411m.getStartAfterPadding()) <= 0) {
            return i2;
        }
        this.f10411m.offsetChildren(-startAfterPadding);
        return i2 - startAfterPadding;
    }

    /* renamed from: b */
    public final int m15678b(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View m15674c = m15674c(itemCount);
        View m15670d = m15670d(itemCount);
        if (state.getItemCount() != 0 && m15674c != null && m15670d != null) {
            if (!f10398z && this.f10406h.f10456c == null) {
                throw new AssertionError();
            }
            int position = getPosition(m15674c);
            int position2 = getPosition(m15670d);
            int abs = Math.abs(this.f10411m.getDecoratedEnd(m15670d) - this.f10411m.getDecoratedStart(m15674c));
            int[] iArr = this.f10406h.f10456c;
            int i = iArr[position];
            if (i != 0 && i != -1) {
                return Math.round((i * (abs / ((iArr[position2] - i) + 1))) + (this.f10411m.getStartAfterPadding() - this.f10411m.getDecoratedStart(m15674c)));
            }
        }
        return 0;
    }

    @Override // p001a.InterfaceC0095a
    /* renamed from: b */
    public final View mo15681b(int i) {
        View view = this.f10418t.get(i);
        return view != null ? view : this.f10407i.getViewForPosition(i);
    }

    /* renamed from: b */
    public final View m15677b(View view, C0776c c0776c) {
        boolean mo15698a = mo15698a();
        int childCount = (getChildCount() - c0776c.f2400h) - 1;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (!this.f10403e || mo15698a) {
                    if (this.f10411m.getDecoratedEnd(view) >= this.f10411m.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.f10411m.getDecoratedStart(view) <= this.f10411m.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    /* renamed from: b */
    public final void m15682b() {
        this.f10405g.clear();
        C4449b.m15661b(this.f10410l);
        this.f10410l.f10427d = 0;
    }

    /* renamed from: c */
    public final int m15672c(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View m15674c = m15674c(itemCount);
        View m15670d = m15670d(itemCount);
        if (state.getItemCount() == 0 || m15674c == null || m15670d == null) {
            return 0;
        }
        if (f10398z || this.f10406h.f10456c != null) {
            View m15696a = m15696a(0, getChildCount());
            return (int) ((Math.abs(this.f10411m.getDecoratedEnd(m15670d) - this.f10411m.getDecoratedStart(m15674c)) / ((m15671d() - (m15696a == null ? -1 : getPosition(m15696a))) + 1)) * state.getItemCount());
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public final View m15674c(int i) {
        if (f10398z || this.f10406h.f10456c != null) {
            View m15673c = m15673c(0, getChildCount(), i);
            if (m15673c == null) {
                return null;
            }
            int i2 = this.f10406h.f10456c[getPosition(m15673c)];
            if (i2 == -1) {
                return null;
            }
            return m15684a(m15673c, this.f10405g.get(i2));
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public final void m15675c() {
        OrientationHelper createVerticalHelper;
        if (this.f10411m != null) {
            return;
        }
        if (!mo15698a() ? this.f10400b == 0 : this.f10400b != 0) {
            this.f10411m = OrientationHelper.createHorizontalHelper(this);
            createVerticalHelper = OrientationHelper.createVerticalHelper(this);
        } else {
            this.f10411m = OrientationHelper.createVerticalHelper(this);
            createVerticalHelper = OrientationHelper.createHorizontalHelper(this);
        }
        this.f10412n = createVerticalHelper;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        return !mo15698a() || getWidth() > this.f10420v.getWidth();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        return mo15698a() || getHeight() > this.f10420v.getHeight();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof C4450c;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        return m15688a(state);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollOffset(RecyclerView.State state) {
        m15678b(state);
        return m15678b(state);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollRange(RecyclerView.State state) {
        return m15672c(state);
    }

    @Override // android.support.p086v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public final PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        return mo15698a() ? new PointF(0.0f, i2) : new PointF(i2, 0.0f);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        return m15688a(state);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollOffset(RecyclerView.State state) {
        return m15678b(state);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollRange(RecyclerView.State state) {
        return m15672c(state);
    }

    /* renamed from: d */
    public final int m15671d() {
        View m15696a = m15696a(getChildCount() - 1, -1);
        if (m15696a == null) {
            return -1;
        }
        return getPosition(m15696a);
    }

    /* renamed from: d */
    public final View m15670d(int i) {
        if (f10398z || this.f10406h.f10456c != null) {
            View m15673c = m15673c(getChildCount() - 1, -1, i);
            if (m15673c == null) {
                return null;
            }
            return m15677b(m15673c, this.f10405g.get(this.f10406h.f10456c[getPosition(m15673c)]));
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    public final int m15668e(int i) {
        int i2;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        m15675c();
        boolean mo15698a = mo15698a();
        int width = mo15698a ? this.f10420v.getWidth() : this.f10420v.getHeight();
        int width2 = mo15698a ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int abs = Math.abs(i);
            if (i < 0) {
                return -Math.min((width2 + this.f10410l.f10427d) - width, abs);
            }
            i2 = this.f10410l.f10427d;
            if (i2 + i <= 0) {
                return i;
            }
        } else if (i > 0) {
            return Math.min((width2 - this.f10410l.f10427d) - width, i);
        } else {
            i2 = this.f10410l.f10427d;
            if (i2 + i >= 0) {
                return i;
            }
        }
        return -i2;
    }

    /* renamed from: e */
    public final void m15669e() {
        int heightMode = mo15698a() ? getHeightMode() : getWidthMode();
        this.f10409k.f10442b = heightMode == 0 || heightMode == Integer.MIN_VALUE;
    }

    /* renamed from: f */
    public final void m15667f(int i) {
        if (this.f10401c != 4) {
            removeAllViews();
            m15682b();
            this.f10401c = 4;
            requestLayout();
        }
    }

    /* renamed from: g */
    public final void m15666g(int i) {
        if (this.f10399a != i) {
            removeAllViews();
            this.f10399a = i;
            this.f10411m = null;
            this.f10412n = null;
            m15682b();
            requestLayout();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new C4450c();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C4450c(context, attributeSet);
    }

    @Override // p001a.InterfaceC0095a
    public final int getAlignContent() {
        return 5;
    }

    @Override // p001a.InterfaceC0095a
    public final int getAlignItems() {
        return this.f10401c;
    }

    @Override // p001a.InterfaceC0095a
    public final int getFlexDirection() {
        return this.f10399a;
    }

    @Override // p001a.InterfaceC0095a
    public final int getFlexItemCount() {
        return this.f10408j.getItemCount();
    }

    @Override // p001a.InterfaceC0095a
    public final List<C0776c> getFlexLinesInternal() {
        return this.f10405g;
    }

    @Override // p001a.InterfaceC0095a
    public final int getFlexWrap() {
        return this.f10400b;
    }

    @Override // p001a.InterfaceC0095a
    public final int getLargestMainSize() {
        if (this.f10405g.size() == 0) {
            return 0;
        }
        int i = Integer.MIN_VALUE;
        int size = this.f10405g.size();
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, this.f10405g.get(i2).f2397e);
        }
        return i;
    }

    @Override // p001a.InterfaceC0095a
    public final int getMaxLine() {
        return this.f10402d;
    }

    @Override // p001a.InterfaceC0095a
    public final int getSumOfCrossSize() {
        int size = this.f10405g.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.f10405g.get(i2).f2399g;
        }
        return i;
    }

    /* renamed from: h */
    public final void m15665h(int i) {
        int i2 = this.f10400b;
        if (i2 != 1) {
            if (i2 == 0) {
                removeAllViews();
                m15682b();
            }
            this.f10400b = 1;
            this.f10411m = null;
            this.f10412n = null;
            requestLayout();
        }
    }

    /* renamed from: i */
    public final void m15664i(int i) {
        View m15696a = m15696a(0, getChildCount());
        int position = m15696a == null ? -1 : getPosition(m15696a);
        int m15671d = m15671d();
        if (i >= m15671d) {
            return;
        }
        int childCount = getChildCount();
        this.f10406h.m15620c(childCount);
        this.f10406h.m15619d(childCount);
        this.f10406h.m15625b(childCount);
        if (!f10398z && this.f10406h.f10456c == null) {
            throw new AssertionError();
        }
        if (i >= this.f10406h.f10456c.length) {
            return;
        }
        this.f10421w = i;
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        if (position > i || i > m15671d) {
            this.f10414p = getPosition(childAt);
            if (mo15698a() || !this.f10403e) {
                this.f10415q = this.f10411m.getDecoratedStart(childAt) - this.f10411m.getStartAfterPadding();
            } else {
                this.f10415q = this.f10411m.getEndPadding() + this.f10411m.getDecoratedEnd(childAt);
            }
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.f10420v = (View) recyclerView.getParent();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        m15664i(i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        m15664i(Math.min(i, i2));
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        m15664i(i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        m15664i(i);
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated(RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        m15664i(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0051, code lost:
        if (r18.f10400b == 2) goto L222;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005d, code lost:
        if (r18.f10400b == 2) goto L222;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005f, code lost:
        r4 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0061, code lost:
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0062, code lost:
        r18.f10404f = r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0294  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x02ad  */
    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLayoutChildren(android.support.p086v7.widget.RecyclerView.Recycler r19, android.support.p086v7.widget.RecyclerView.State r20) {
        /*
            Method dump skipped, instructions count: 1194
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):void");
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.f10413o = null;
        this.f10414p = -1;
        this.f10415q = Integer.MIN_VALUE;
        this.f10421w = -1;
        C4449b.m15661b(this.f10410l);
        this.f10418t.clear();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof C4453e) {
            this.f10413o = (C4453e) parcelable;
            requestLayout();
        }
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        C4453e c4453e = this.f10413o;
        if (c4453e != null) {
            return new C4453e(c4453e);
        }
        C4453e c4453e2 = new C4453e();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            c4453e2.f10451a = getPosition(childAt);
            c4453e2.f10452b = this.f10411m.getDecoratedStart(childAt) - this.f10411m.getStartAfterPadding();
        } else {
            c4453e2.f10451a = -1;
        }
        return c4453e2;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!mo15698a()) {
            int m15694a = m15694a(i, recycler, state);
            this.f10418t.clear();
            return m15694a;
        }
        int m15668e = m15668e(i);
        this.f10410l.f10427d += m15668e;
        this.f10412n.offsetChildren(-m15668e);
        return m15668e;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        this.f10414p = i;
        this.f10415q = Integer.MIN_VALUE;
        C4453e c4453e = this.f10413o;
        if (c4453e != null) {
            c4453e.f10451a = -1;
        }
        requestLayout();
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (mo15698a()) {
            int m15694a = m15694a(i, recycler, state);
            this.f10418t.clear();
            return m15694a;
        }
        int m15668e = m15668e(i);
        this.f10410l.f10427d += m15668e;
        this.f10412n.offsetChildren(-m15668e);
        return m15668e;
    }

    @Override // p001a.InterfaceC0095a
    public final void setFlexLines(List<C0776c> list) {
        this.f10405g = list;
    }

    @Override // android.support.p086v7.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    /* renamed from: b */
    public final void m15676b(C4449b c4449b, boolean z, boolean z2) {
        C4452d c4452d;
        int startAfterPadding;
        if (z2) {
            m15669e();
        } else {
            this.f10409k.f10442b = false;
        }
        if (!mo15698a() && this.f10403e) {
            c4452d = this.f10409k;
            startAfterPadding = (this.f10420v.getWidth() - c4449b.f10426c) - this.f10411m.getStartAfterPadding();
        } else {
            c4452d = this.f10409k;
            startAfterPadding = c4449b.f10426c - this.f10411m.getStartAfterPadding();
        }
        c4452d.f10441a = startAfterPadding;
        C4452d c4452d2 = this.f10409k;
        c4452d2.f10444d = c4449b.f10424a;
        c4452d2.f10448h = 1;
        c4452d2.f10449i = -1;
        c4452d2.f10445e = c4449b.f10426c;
        c4452d2.f10446f = Integer.MIN_VALUE;
        int i = c4449b.f10425b;
        c4452d2.f10443c = i;
        if (!z || i <= 0) {
            return;
        }
        int size = this.f10405g.size();
        int i2 = c4449b.f10425b;
        if (size > i2) {
            C4452d c4452d3 = this.f10409k;
            c4452d3.f10443c--;
            c4452d3.f10444d -= this.f10405g.get(i2).f2400h;
        }
    }

    /* renamed from: c */
    public final View m15673c(int i, int i2, int i3) {
        m15675c();
        if (this.f10409k == null) {
            this.f10409k = new C4452d();
        }
        int startAfterPadding = this.f10411m.getStartAfterPadding();
        int endAfterPadding = this.f10411m.getEndAfterPadding();
        View view = null;
        int i4 = i2 > i ? 1 : -1;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else if (this.f10411m.getDecoratedStart(childAt) >= startAfterPadding && this.f10411m.getDecoratedEnd(childAt) <= endAfterPadding) {
                    return childAt;
                } else {
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    /* renamed from: a */
    public final View m15696a(int i, int i2) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int width = getWidth() - getPaddingRight();
            int height = getHeight() - getPaddingBottom();
            int decoratedLeft = getDecoratedLeft(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).leftMargin;
            int decoratedTop = getDecoratedTop(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).topMargin;
            int decoratedRight = getDecoratedRight(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int decoratedBottom = getDecoratedBottom(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            boolean z = false;
            boolean z2 = decoratedLeft >= width || decoratedRight >= paddingLeft;
            boolean z3 = decoratedTop >= height || decoratedBottom >= paddingTop;
            if (z2 && z3) {
                z = true;
            }
            if (z) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    /* renamed from: a */
    public final int m15694a(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        m15675c();
        this.f10409k.f10450j = true;
        boolean z = !mo15698a() && this.f10403e;
        int i3 = -1;
        int i4 = (!z ? i > 0 : i < 0) ? -1 : 1;
        int abs = Math.abs(i);
        if (f10398z || this.f10406h.f10456c != null) {
            this.f10409k.f10449i = i4;
            boolean mo15698a = mo15698a();
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
            int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
            boolean z2 = !mo15698a && this.f10403e;
            if (i4 == 1) {
                View childAt = getChildAt(getChildCount() - 1);
                this.f10409k.f10445e = this.f10411m.getDecoratedEnd(childAt);
                int position = getPosition(childAt);
                View m15677b = m15677b(childAt, this.f10405g.get(this.f10406h.f10456c[position]));
                C4452d c4452d = this.f10409k;
                c4452d.f10448h = 1;
                int i5 = position + 1;
                c4452d.f10444d = i5;
                int[] iArr = this.f10406h.f10456c;
                c4452d.f10443c = iArr.length <= i5 ? -1 : iArr[i5];
                if (z2) {
                    c4452d.f10445e = this.f10411m.getDecoratedStart(m15677b);
                    this.f10409k.f10446f = this.f10411m.getStartAfterPadding() + (-this.f10411m.getDecoratedStart(m15677b));
                    C4452d c4452d2 = this.f10409k;
                    int i6 = c4452d2.f10446f;
                    if (i6 < 0) {
                        i6 = 0;
                    }
                    c4452d2.f10446f = i6;
                } else {
                    c4452d.f10445e = this.f10411m.getDecoratedEnd(m15677b);
                    this.f10409k.f10446f = this.f10411m.getDecoratedEnd(m15677b) - this.f10411m.getEndAfterPadding();
                }
                int i7 = this.f10409k.f10443c;
                if ((i7 == -1 || i7 > this.f10405g.size() - 1) && this.f10409k.f10444d <= this.f10408j.getItemCount()) {
                    int i8 = abs - this.f10409k.f10446f;
                    this.f10422x.m15617a();
                    if (i8 > 0) {
                        if (mo15698a) {
                            this.f10406h.m15628a(this.f10422x, makeMeasureSpec, makeMeasureSpec2, i8, this.f10409k.f10444d, -1, this.f10405g);
                        } else {
                            this.f10406h.m15628a(this.f10422x, makeMeasureSpec2, makeMeasureSpec, i8, this.f10409k.f10444d, -1, this.f10405g);
                        }
                        this.f10406h.m15624b(makeMeasureSpec, makeMeasureSpec2, this.f10409k.f10444d);
                        this.f10406h.m15618e(this.f10409k.f10444d);
                    }
                }
            } else {
                View childAt2 = getChildAt(0);
                this.f10409k.f10445e = this.f10411m.getDecoratedStart(childAt2);
                int position2 = getPosition(childAt2);
                View m15684a = m15684a(childAt2, this.f10405g.get(this.f10406h.f10456c[position2]));
                C4452d c4452d3 = this.f10409k;
                c4452d3.f10448h = 1;
                int i9 = this.f10406h.f10456c[position2];
                if (i9 == -1) {
                    i9 = 0;
                }
                if (i9 > 0) {
                    c4452d3 = this.f10409k;
                    i3 = position2 - this.f10405g.get(i9 - 1).f2400h;
                }
                c4452d3.f10444d = i3;
                C4452d c4452d4 = this.f10409k;
                c4452d4.f10443c = i9 > 0 ? i9 - 1 : 0;
                if (z2) {
                    c4452d4.f10445e = this.f10411m.getDecoratedEnd(m15684a);
                    this.f10409k.f10446f = this.f10411m.getDecoratedEnd(m15684a) - this.f10411m.getEndAfterPadding();
                    C4452d c4452d5 = this.f10409k;
                    int i10 = c4452d5.f10446f;
                    if (i10 < 0) {
                        i10 = 0;
                    }
                    c4452d5.f10446f = i10;
                } else {
                    c4452d4.f10445e = this.f10411m.getDecoratedStart(m15684a);
                    this.f10409k.f10446f = this.f10411m.getStartAfterPadding() + (-this.f10411m.getDecoratedStart(m15684a));
                }
            }
            C4452d c4452d6 = this.f10409k;
            int i11 = c4452d6.f10446f;
            c4452d6.f10441a = abs - i11;
            int m15690a = m15690a(recycler, state, c4452d6) + i11;
            if (m15690a < 0) {
                return 0;
            }
            if (z) {
                if (abs > m15690a) {
                    i2 = (-i4) * m15690a;
                }
                i2 = i;
            } else {
                if (abs > m15690a) {
                    i2 = i4 * m15690a;
                }
                i2 = i;
            }
            this.f10411m.offsetChildren(-i2);
            this.f10409k.f10447g = i2;
            return i2;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public final void m15689a(RecyclerView.Recycler recycler, C4452d c4452d) {
        if (c4452d.f10450j) {
            if (c4452d.f10449i == -1) {
                if (c4452d.f10446f < 0) {
                    return;
                }
                if (!f10398z && this.f10406h.f10456c == null) {
                    throw new AssertionError();
                }
                this.f10411m.getEnd();
                int childCount = getChildCount();
                if (childCount == 0) {
                    return;
                }
                int i = childCount - 1;
                int i2 = this.f10406h.f10456c[getPosition(getChildAt(i))];
                if (i2 == -1) {
                    return;
                }
                C0776c c0776c = this.f10405g.get(i2);
                int i3 = childCount;
                int i4 = i;
                while (i4 >= 0) {
                    View childAt = getChildAt(i4);
                    int i5 = c4452d.f10446f;
                    if (!(mo15698a() || !this.f10403e ? this.f10411m.getDecoratedStart(childAt) >= this.f10411m.getEnd() - i5 : this.f10411m.getDecoratedEnd(childAt) <= i5)) {
                        break;
                    }
                    if (c0776c.f2407o == getPosition(childAt)) {
                        if (i2 <= 0) {
                            break;
                        }
                        i2 += c4452d.f10449i;
                        c0776c = this.f10405g.get(i2);
                        i3 = i4;
                    }
                    i4--;
                }
                i4 = i3;
                while (i >= i4) {
                    removeAndRecycleViewAt(i, recycler);
                    i--;
                }
            } else if (c4452d.f10446f < 0) {
            } else {
                if (!f10398z && this.f10406h.f10456c == null) {
                    throw new AssertionError();
                }
                int childCount2 = getChildCount();
                if (childCount2 == 0) {
                    return;
                }
                int i6 = this.f10406h.f10456c[getPosition(getChildAt(0))];
                if (i6 == -1) {
                    return;
                }
                C0776c c0776c2 = this.f10405g.get(i6);
                int i7 = -1;
                int i8 = 0;
                while (i8 < childCount2) {
                    View childAt2 = getChildAt(i8);
                    int i9 = c4452d.f10446f;
                    if (!(mo15698a() || !this.f10403e ? this.f10411m.getDecoratedEnd(childAt2) <= i9 : this.f10411m.getEnd() - this.f10411m.getDecoratedStart(childAt2) <= i9)) {
                        break;
                    }
                    if (c0776c2.f2408p == getPosition(childAt2)) {
                        if (i6 >= this.f10405g.size() - 1) {
                            break;
                        }
                        i6 += c4452d.f10449i;
                        c0776c2 = this.f10405g.get(i6);
                        i7 = i8;
                    }
                    i8++;
                }
                i8 = i7;
                while (i8 >= 0) {
                    removeAndRecycleViewAt(i8, recycler);
                    i8--;
                }
            }
        }
    }

    /* renamed from: a */
    public final void m15683a(C4449b c4449b, boolean z, boolean z2) {
        C4452d c4452d;
        int endAfterPadding;
        int i;
        if (z2) {
            m15669e();
        } else {
            this.f10409k.f10442b = false;
        }
        if (!mo15698a() && this.f10403e) {
            c4452d = this.f10409k;
            endAfterPadding = c4449b.f10426c - getPaddingRight();
        } else {
            c4452d = this.f10409k;
            endAfterPadding = this.f10411m.getEndAfterPadding() - c4449b.f10426c;
        }
        c4452d.f10441a = endAfterPadding;
        C4452d c4452d2 = this.f10409k;
        c4452d2.f10444d = c4449b.f10424a;
        c4452d2.f10448h = 1;
        c4452d2.f10449i = 1;
        c4452d2.f10445e = c4449b.f10426c;
        c4452d2.f10446f = Integer.MIN_VALUE;
        c4452d2.f10443c = c4449b.f10425b;
        if (!z || this.f10405g.size() <= 1 || (i = c4449b.f10425b) < 0 || i >= this.f10405g.size() - 1) {
            return;
        }
        C4452d c4452d3 = this.f10409k;
        c4452d3.f10443c++;
        c4452d3.f10444d += this.f10405g.get(c4449b.f10425b).f2400h;
    }
}
