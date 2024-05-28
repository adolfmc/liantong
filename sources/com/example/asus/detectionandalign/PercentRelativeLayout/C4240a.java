package com.example.asus.detectionandalign.PercentRelativeLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p083v4.view.MarginLayoutParamsCompat;
import android.support.p083v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.example.asus.detectionandalign.C4243R;

/* renamed from: com.example.asus.detectionandalign.PercentRelativeLayout.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4240a {

    /* renamed from: a */
    private final ViewGroup f9750a;

    /* renamed from: com.example.asus.detectionandalign.PercentRelativeLayout.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4241a {

        /* renamed from: i */
        public float f9759i;

        /* renamed from: a */
        public float f9751a = -1.0f;

        /* renamed from: b */
        public float f9752b = -1.0f;

        /* renamed from: c */
        public float f9753c = -1.0f;

        /* renamed from: d */
        public float f9754d = -1.0f;

        /* renamed from: e */
        public float f9755e = -1.0f;

        /* renamed from: f */
        public float f9756f = -1.0f;

        /* renamed from: g */
        public float f9757g = -1.0f;

        /* renamed from: h */
        public float f9758h = -1.0f;

        /* renamed from: j */
        final ViewGroup.MarginLayoutParams f9760j = new ViewGroup.MarginLayoutParams(0, 0);

        /* renamed from: a */
        public void m16283a(ViewGroup.LayoutParams layoutParams) {
            layoutParams.width = this.f9760j.width;
            layoutParams.height = this.f9760j.height;
        }

        /* renamed from: a */
        public void m16282a(ViewGroup.LayoutParams layoutParams, int i, int i2) {
            this.f9760j.width = layoutParams.width;
            this.f9760j.height = layoutParams.height;
            boolean z = true;
            boolean z2 = layoutParams.width == 0 && this.f9751a < 0.0f;
            if (layoutParams.height != 0 || this.f9752b >= 0.0f) {
                z = false;
            }
            float f = this.f9751a;
            if (f >= 0.0f) {
                layoutParams.width = (int) (i * f);
            }
            float f2 = this.f9752b;
            if (f2 >= 0.0f) {
                layoutParams.height = (int) (i2 * f2);
            }
            if (this.f9759i >= 0.0f) {
                if (z2) {
                    layoutParams.width = (int) (layoutParams.height * this.f9759i);
                }
                if (z) {
                    layoutParams.height = (int) (layoutParams.width / this.f9759i);
                }
            }
            if (Log.isLoggable("PercentLayout", 3)) {
                Log.d("PercentLayout", "after fillLayoutParams: (" + layoutParams.width + ", " + layoutParams.height + ")");
            }
        }

        /* renamed from: a */
        public void m16281a(ViewGroup.MarginLayoutParams marginLayoutParams) {
            m16283a((ViewGroup.LayoutParams) marginLayoutParams);
            marginLayoutParams.leftMargin = this.f9760j.leftMargin;
            marginLayoutParams.topMargin = this.f9760j.topMargin;
            marginLayoutParams.rightMargin = this.f9760j.rightMargin;
            marginLayoutParams.bottomMargin = this.f9760j.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, MarginLayoutParamsCompat.getMarginStart(this.f9760j));
            MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, MarginLayoutParamsCompat.getMarginEnd(this.f9760j));
        }

        /* renamed from: a */
        public void m16280a(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2) {
            m16282a((ViewGroup.LayoutParams) marginLayoutParams, i, i2);
            this.f9760j.leftMargin = marginLayoutParams.leftMargin;
            this.f9760j.topMargin = marginLayoutParams.topMargin;
            this.f9760j.rightMargin = marginLayoutParams.rightMargin;
            this.f9760j.bottomMargin = marginLayoutParams.bottomMargin;
            MarginLayoutParamsCompat.setMarginStart(this.f9760j, MarginLayoutParamsCompat.getMarginStart(marginLayoutParams));
            MarginLayoutParamsCompat.setMarginEnd(this.f9760j, MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams));
            float f = this.f9753c;
            if (f >= 0.0f) {
                marginLayoutParams.leftMargin = (int) (i * f);
            }
            float f2 = this.f9754d;
            if (f2 >= 0.0f) {
                marginLayoutParams.topMargin = (int) (i2 * f2);
            }
            float f3 = this.f9755e;
            if (f3 >= 0.0f) {
                marginLayoutParams.rightMargin = (int) (i * f3);
            }
            float f4 = this.f9756f;
            if (f4 >= 0.0f) {
                marginLayoutParams.bottomMargin = (int) (i2 * f4);
            }
            float f5 = this.f9757g;
            if (f5 >= 0.0f) {
                MarginLayoutParamsCompat.setMarginStart(marginLayoutParams, (int) (i * f5));
            }
            float f6 = this.f9758h;
            if (f6 >= 0.0f) {
                MarginLayoutParamsCompat.setMarginEnd(marginLayoutParams, (int) (i * f6));
            }
            if (Log.isLoggable("PercentLayout", 3)) {
                Log.d("PercentLayout", "after fillMarginLayoutParams: (" + marginLayoutParams.width + ", " + marginLayoutParams.height + ")");
            }
        }

        public String toString() {
            return String.format("PercentLayoutInformation width: %f height %f, margins (%f, %f,  %f, %f, %f, %f)", Float.valueOf(this.f9751a), Float.valueOf(this.f9752b), Float.valueOf(this.f9753c), Float.valueOf(this.f9754d), Float.valueOf(this.f9755e), Float.valueOf(this.f9756f), Float.valueOf(this.f9757g), Float.valueOf(this.f9758h));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.example.asus.detectionandalign.PercentRelativeLayout.a$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4242b {
        /* renamed from: a */
        C4241a mo16279a();
    }

    public C4240a(ViewGroup viewGroup) {
        this.f9750a = viewGroup;
    }

    /* renamed from: a */
    public static C4241a m16288a(Context context, AttributeSet attributeSet) {
        C4241a c4241a;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C4243R.styleable.PercentLayout_Layout);
        float fraction = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_widthPercent, 1, 1, -1.0f);
        if (fraction != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent width: " + fraction);
            }
            c4241a = new C4241a();
            c4241a.f9751a = fraction;
        } else {
            c4241a = null;
        }
        float fraction2 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_heightPercent, 1, 1, -1.0f);
        if (fraction2 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent height: " + fraction2);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9752b = fraction2;
        }
        float fraction3 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginPercent, 1, 1, -1.0f);
        if (fraction3 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent margin: " + fraction3);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9753c = fraction3;
            c4241a.f9754d = fraction3;
            c4241a.f9755e = fraction3;
            c4241a.f9756f = fraction3;
        }
        float fraction4 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginLeftPercent, 1, 1, -1.0f);
        if (fraction4 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent left margin: " + fraction4);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9753c = fraction4;
        }
        float fraction5 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginTopPercent, 1, 1, -1.0f);
        if (fraction5 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent top margin: " + fraction5);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9754d = fraction5;
        }
        float fraction6 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginRightPercent, 1, 1, -1.0f);
        if (fraction6 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent right margin: " + fraction6);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9755e = fraction6;
        }
        float fraction7 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginBottomPercent, 1, 1, -1.0f);
        if (fraction7 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent bottom margin: " + fraction7);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9756f = fraction7;
        }
        float fraction8 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginStartPercent, 1, 1, -1.0f);
        if (fraction8 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent start margin: " + fraction8);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9757g = fraction8;
        }
        float fraction9 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_marginEndPercent, 1, 1, -1.0f);
        if (fraction9 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "percent end margin: " + fraction9);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9758h = fraction9;
        }
        float fraction10 = obtainStyledAttributes.getFraction(C4243R.styleable.PercentLayout_Layout_layout_aspectRatio, 1, 1, -1.0f);
        if (fraction10 != -1.0f) {
            if (Log.isLoggable("PercentLayout", 2)) {
                Log.v("PercentLayout", "aspect ratio: " + fraction10);
            }
            if (c4241a == null) {
                c4241a = new C4241a();
            }
            c4241a.f9759i = fraction10;
        }
        obtainStyledAttributes.recycle();
        if (Log.isLoggable("PercentLayout", 3)) {
            Log.d("PercentLayout", "constructed: " + c4241a);
        }
        return c4241a;
    }

    /* renamed from: a */
    public static void m16286a(ViewGroup.LayoutParams layoutParams, TypedArray typedArray, int i, int i2) {
        layoutParams.width = typedArray.getLayoutDimension(i, 0);
        layoutParams.height = typedArray.getLayoutDimension(i2, 0);
    }

    /* renamed from: a */
    private static boolean m16287a(View view, C4241a c4241a) {
        return (ViewCompat.getMeasuredWidthAndState(view) & (-16777216)) == 16777216 && c4241a.f9751a >= 0.0f && c4241a.f9760j.width == -2;
    }

    /* renamed from: b */
    private static boolean m16284b(View view, C4241a c4241a) {
        return (ViewCompat.getMeasuredHeightAndState(view) & (-16777216)) == 16777216 && c4241a.f9752b >= 0.0f && c4241a.f9760j.height == -2;
    }

    /* renamed from: a */
    public void m16290a() {
        int childCount = this.f9750a.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f9750a.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable("PercentLayout", 3)) {
                Log.d("PercentLayout", "should restore " + childAt + " " + layoutParams);
            }
            if (layoutParams instanceof InterfaceC4242b) {
                C4241a mo16279a = ((InterfaceC4242b) layoutParams).mo16279a();
                if (Log.isLoggable("PercentLayout", 3)) {
                    Log.d("PercentLayout", "using " + mo16279a);
                }
                if (mo16279a != null) {
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        mo16279a.m16281a((ViewGroup.MarginLayoutParams) layoutParams);
                    } else {
                        mo16279a.m16283a(layoutParams);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void m16289a(int i, int i2) {
        if (Log.isLoggable("PercentLayout", 3)) {
            Log.d("PercentLayout", "adjustChildren: " + this.f9750a + " widthMeasureSpec: " + View.MeasureSpec.toString(i) + " heightMeasureSpec: " + View.MeasureSpec.toString(i2));
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int childCount = this.f9750a.getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = this.f9750a.getChildAt(i3);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable("PercentLayout", 3)) {
                Log.d("PercentLayout", "should adjust " + childAt + " " + layoutParams);
            }
            if (layoutParams instanceof InterfaceC4242b) {
                C4241a mo16279a = ((InterfaceC4242b) layoutParams).mo16279a();
                if (Log.isLoggable("PercentLayout", 3)) {
                    Log.d("PercentLayout", "using " + mo16279a);
                }
                if (mo16279a != null) {
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        mo16279a.m16280a((ViewGroup.MarginLayoutParams) layoutParams, size, size2);
                    } else {
                        mo16279a.m16282a(layoutParams, size, size2);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    public boolean m16285b() {
        C4241a mo16279a;
        int childCount = this.f9750a.getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.f9750a.getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (Log.isLoggable("PercentLayout", 3)) {
                Log.d("PercentLayout", "should handle measured state too small " + childAt + " " + layoutParams);
            }
            if ((layoutParams instanceof InterfaceC4242b) && (mo16279a = ((InterfaceC4242b) layoutParams).mo16279a()) != null) {
                if (m16287a(childAt, mo16279a)) {
                    layoutParams.width = -2;
                    z = true;
                }
                if (m16284b(childAt, mo16279a)) {
                    layoutParams.height = -2;
                    z = true;
                }
            }
        }
        if (Log.isLoggable("PercentLayout", 3)) {
            Log.d("PercentLayout", "should trigger second measure pass: " + z);
        }
        return z;
    }
}
