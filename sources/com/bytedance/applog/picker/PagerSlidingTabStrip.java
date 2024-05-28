package com.bytedance.applog.picker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.p083v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.C3527R;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.Locale;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PagerSlidingTabStrip extends HorizontalScrollView {

    /* renamed from: A */
    public int f8720A;

    /* renamed from: B */
    public Typeface f8721B;

    /* renamed from: C */
    public int f8722C;

    /* renamed from: D */
    public int f8723D;

    /* renamed from: E */
    public Locale f8724E;

    /* renamed from: a */
    public LinearLayout.LayoutParams f8725a;

    /* renamed from: b */
    public LinearLayout.LayoutParams f8726b;

    /* renamed from: c */
    public final C3668d f8727c;

    /* renamed from: d */
    public ViewPager.OnPageChangeListener f8728d;

    /* renamed from: e */
    public LinearLayout f8729e;

    /* renamed from: f */
    public ViewPager f8730f;

    /* renamed from: g */
    public int f8731g;

    /* renamed from: h */
    public int f8732h;

    /* renamed from: i */
    public int f8733i;

    /* renamed from: j */
    public float f8734j;

    /* renamed from: k */
    public Paint f8735k;

    /* renamed from: l */
    public Paint f8736l;

    /* renamed from: m */
    public int f8737m;

    /* renamed from: n */
    public int f8738n;

    /* renamed from: o */
    public int f8739o;

    /* renamed from: p */
    public boolean f8740p;

    /* renamed from: q */
    public boolean f8741q;

    /* renamed from: r */
    public int f8742r;

    /* renamed from: s */
    public int f8743s;

    /* renamed from: t */
    public int f8744t;

    /* renamed from: u */
    public int f8745u;

    /* renamed from: v */
    public int f8746v;

    /* renamed from: w */
    public int f8747w;

    /* renamed from: x */
    public int f8748x;

    /* renamed from: y */
    public int f8749y;

    /* renamed from: z */
    public int f8750z;

    /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ViewTreeObserver$OnGlobalLayoutListenerC3665a implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserver$OnGlobalLayoutListenerC3665a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        @SuppressLint({"NewApi"})
        public void onGlobalLayout() {
            if (Build.VERSION.SDK_INT < 16) {
                PagerSlidingTabStrip.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            } else {
                PagerSlidingTabStrip.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
            PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip.f8733i = pagerSlidingTabStrip.f8730f.getCurrentItem();
            PagerSlidingTabStrip pagerSlidingTabStrip2 = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip2.f8727c.onPageSelected(pagerSlidingTabStrip2.f8733i);
            PagerSlidingTabStrip pagerSlidingTabStrip3 = PagerSlidingTabStrip.this;
            PagerSlidingTabStrip.m17155a(pagerSlidingTabStrip3, pagerSlidingTabStrip3.f8733i, 0);
        }
    }

    @NBSInstrumented
    /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class View$OnClickListenerC3666b implements View.OnClickListener {

        /* renamed from: a */
        public final /* synthetic */ int f8752a;

        public View$OnClickListenerC3666b(int i) {
            this.f8752a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            PagerSlidingTabStrip.this.f8730f.setCurrentItem(this.f8752a);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC3667c {
        /* renamed from: a */
        int m17154a(int i);
    }

    @NBSInstrumented
    /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3668d implements ViewPager.OnPageChangeListener {
        public /* synthetic */ C3668d(ViewTreeObserver$OnGlobalLayoutListenerC3665a viewTreeObserver$OnGlobalLayoutListenerC3665a) {
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
                PagerSlidingTabStrip.m17155a(pagerSlidingTabStrip, pagerSlidingTabStrip.f8730f.getCurrentItem(), 0);
            }
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSlidingTabStrip.this.f8728d;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrollStateChanged(i);
            }
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            if (PagerSlidingTabStrip.this.f8729e.getChildCount() <= i) {
                return;
            }
            PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip.f8733i = i;
            pagerSlidingTabStrip.f8734j = f;
            PagerSlidingTabStrip.m17155a(pagerSlidingTabStrip, i, (int) (pagerSlidingTabStrip.f8729e.getChildAt(i).getWidth() * f));
            PagerSlidingTabStrip.this.invalidate();
            ViewPager.OnPageChangeListener onPageChangeListener = PagerSlidingTabStrip.this.f8728d;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageScrolled(i, f, i2);
            }
        }

        @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            NBSActionInstrumentation.onPageSelectedEnter(i, this);
            PagerSlidingTabStrip pagerSlidingTabStrip = PagerSlidingTabStrip.this;
            View childAt = pagerSlidingTabStrip.f8729e.getChildAt(pagerSlidingTabStrip.f8732h);
            View childAt2 = PagerSlidingTabStrip.this.f8729e.getChildAt(i);
            if ((childAt instanceof TextView) && (childAt2 instanceof TextView)) {
                TextView textView = (TextView) childAt;
                textView.setTypeface(PagerSlidingTabStrip.this.f8721B, 0);
                textView.setTextColor(PagerSlidingTabStrip.this.f8750z);
                TextView textView2 = (TextView) childAt2;
                textView2.setTypeface(PagerSlidingTabStrip.this.f8721B, 0);
                textView2.setTextColor(PagerSlidingTabStrip.this.f8720A);
            }
            PagerSlidingTabStrip pagerSlidingTabStrip2 = PagerSlidingTabStrip.this;
            pagerSlidingTabStrip2.f8732h = i;
            ViewPager.OnPageChangeListener onPageChangeListener = pagerSlidingTabStrip2.f8728d;
            if (onPageChangeListener != null) {
                onPageChangeListener.onPageSelected(i);
            }
            NBSActionInstrumentation.onPageSelectedExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$e */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3669e extends View.BaseSavedState {
        public static final Parcelable.Creator<C3669e> CREATOR = new C3670a();

        /* renamed from: a */
        public int f8755a;

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.bytedance.applog.picker.PagerSlidingTabStrip$e$a */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public static class C3670a implements Parcelable.Creator<C3669e> {
            @Override // android.os.Parcelable.Creator
            public C3669e createFromParcel(Parcel parcel) {
                return new C3669e(parcel, null);
            }

            @Override // android.os.Parcelable.Creator
            public C3669e[] newArray(int i) {
                return new C3669e[i];
            }
        }

        public /* synthetic */ C3669e(Parcel parcel, ViewTreeObserver$OnGlobalLayoutListenerC3665a viewTreeObserver$OnGlobalLayoutListenerC3665a) {
            super(parcel);
            this.f8755a = parcel.readInt();
        }

        public C3669e(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f8755a);
        }
    }

    public PagerSlidingTabStrip(Context context) {
        this(context, null);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagerSlidingTabStrip(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f8727c = new C3668d(null);
        this.f8732h = 0;
        this.f8733i = 0;
        this.f8734j = 0.0f;
        this.f8737m = -10066330;
        this.f8738n = 436207616;
        this.f8739o = 436207616;
        this.f8740p = false;
        this.f8741q = true;
        this.f8742r = 52;
        this.f8743s = 8;
        this.f8744t = 2;
        this.f8745u = 12;
        this.f8746v = 0;
        this.f8747w = 24;
        this.f8748x = 1;
        this.f8749y = 13;
        this.f8750z = -10066330;
        this.f8720A = 16119260;
        this.f8721B = null;
        this.f8722C = 0;
        this.f8723D = C3527R.C3529drawable.picker_background_tab;
        setFillViewport(true);
        setWillNotDraw(false);
        this.f8729e = new LinearLayout(context);
        this.f8729e.setOrientation(0);
        this.f8729e.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.f8729e);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f8742r = (int) TypedValue.applyDimension(1, this.f8742r, displayMetrics);
        this.f8743s = (int) TypedValue.applyDimension(1, this.f8743s, displayMetrics);
        this.f8744t = (int) TypedValue.applyDimension(1, this.f8744t, displayMetrics);
        this.f8745u = (int) TypedValue.applyDimension(1, this.f8745u, displayMetrics);
        this.f8746v = (int) TypedValue.applyDimension(1, this.f8746v, displayMetrics);
        this.f8747w = (int) TypedValue.applyDimension(1, this.f8747w, displayMetrics);
        this.f8748x = (int) TypedValue.applyDimension(1, this.f8748x, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C3527R.styleable.PagerSlidingTabStrip);
        this.f8749y = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsTabTextSize, this.f8749y);
        this.f8750z = obtainStyledAttributes.getColor(C3527R.styleable.PagerSlidingTabStrip_pstsDefTextColor, this.f8750z);
        this.f8720A = obtainStyledAttributes.getColor(C3527R.styleable.PagerSlidingTabStrip_pstsSelTextColor, this.f8720A);
        this.f8746v = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsIndicatorPaddingLeftRight, this.f8746v);
        this.f8737m = obtainStyledAttributes.getColor(C3527R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, this.f8737m);
        this.f8743s = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, this.f8743s);
        this.f8738n = obtainStyledAttributes.getColor(C3527R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, this.f8738n);
        this.f8744t = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, this.f8744t);
        this.f8748x = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsDividerWidth, this.f8748x);
        this.f8739o = obtainStyledAttributes.getColor(C3527R.styleable.PagerSlidingTabStrip_pstsDividerColor, this.f8739o);
        this.f8745u = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsDividerPadding, this.f8745u);
        this.f8747w = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, this.f8747w);
        this.f8723D = obtainStyledAttributes.getResourceId(C3527R.styleable.PagerSlidingTabStrip_pstsTabBackground, this.f8723D);
        this.f8740p = obtainStyledAttributes.getBoolean(C3527R.styleable.PagerSlidingTabStrip_pstsShouldExpand, this.f8740p);
        this.f8742r = obtainStyledAttributes.getDimensionPixelSize(C3527R.styleable.PagerSlidingTabStrip_pstsScrollOffset, this.f8742r);
        this.f8741q = obtainStyledAttributes.getBoolean(C3527R.styleable.PagerSlidingTabStrip_pstsTextAllCaps, this.f8741q);
        obtainStyledAttributes.recycle();
        this.f8735k = new Paint();
        this.f8735k.setAntiAlias(true);
        this.f8735k.setStyle(Paint.Style.FILL);
        this.f8736l = new Paint();
        this.f8736l.setAntiAlias(true);
        this.f8736l.setStrokeWidth(this.f8748x);
        this.f8725a = new LinearLayout.LayoutParams(-2, -1);
        this.f8726b = new LinearLayout.LayoutParams(0, -1, 1.0f);
        if (this.f8724E == null) {
            this.f8724E = getResources().getConfiguration().locale;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ void m17155a(PagerSlidingTabStrip pagerSlidingTabStrip, int i, int i2) {
        if (pagerSlidingTabStrip.f8731g == 0) {
            return;
        }
        int left = pagerSlidingTabStrip.f8729e.getChildAt(i).getLeft() + i2;
        if (i > 0 || i2 > 0) {
            left -= pagerSlidingTabStrip.f8742r;
        }
        if (left != pagerSlidingTabStrip.f8722C) {
            pagerSlidingTabStrip.f8722C = left;
            pagerSlidingTabStrip.scrollTo(left, 0);
        }
    }

    /* renamed from: a */
    public final void m17156a(int i, View view) {
        view.setFocusable(true);
        view.setOnClickListener(new View$OnClickListenerC3666b(i));
        int i2 = this.f8740p ? 0 : this.f8747w;
        view.setPadding(i2, 0, i2, 0);
        this.f8729e.addView(view, i, this.f8740p ? this.f8726b : this.f8725a);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (isInEditMode() || this.f8731g == 0) {
            return;
        }
        int height = getHeight();
        this.f8735k.setColor(this.f8737m);
        View childAt = this.f8729e.getChildAt(this.f8733i);
        float left = childAt.getLeft();
        float right = childAt.getRight();
        if (this.f8734j > 0.0f && (i = this.f8733i) < this.f8731g - 1) {
            View childAt2 = this.f8729e.getChildAt(i + 1);
            float f = this.f8734j;
            float f2 = 1.0f - f;
            left = (left * f2) + (childAt2.getLeft() * f);
            right = (f2 * right) + (f * childAt2.getRight());
        }
        float f3 = this.f8746v;
        float f4 = height;
        canvas.drawRect(left + f3, height - this.f8743s, right - f3, f4, this.f8735k);
        this.f8735k.setColor(this.f8738n);
        canvas.drawRect(0.0f, height - this.f8744t, this.f8729e.getWidth(), f4, this.f8735k);
        this.f8736l.setColor(this.f8739o);
        for (int i2 = 0; i2 < this.f8731g - 1; i2++) {
            View childAt3 = this.f8729e.getChildAt(i2);
            canvas.drawLine(childAt3.getRight(), this.f8745u, childAt3.getRight(), height - this.f8745u, this.f8736l);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        C3669e c3669e = (C3669e) parcelable;
        super.onRestoreInstanceState(c3669e.getSuperState());
        this.f8733i = c3669e.f8755a;
        requestLayout();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public Parcelable onSaveInstanceState() {
        C3669e c3669e = new C3669e(super.onSaveInstanceState());
        c3669e.f8755a = this.f8733i;
        return c3669e;
    }

    public void setViewPager(ViewPager viewPager) {
        this.f8730f = viewPager;
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        viewPager.setOnPageChangeListener(this.f8727c);
        m17157a();
    }

    /* renamed from: a */
    public void m17157a() {
        this.f8729e.removeAllViews();
        this.f8731g = this.f8730f.getAdapter().getCount();
        for (int i = 0; i < this.f8731g; i++) {
            if (this.f8730f.getAdapter() instanceof InterfaceC3667c) {
                int m17154a = ((InterfaceC3667c) this.f8730f.getAdapter()).m17154a(i);
                ImageButton imageButton = new ImageButton(getContext());
                imageButton.setImageResource(m17154a);
                m17156a(i, imageButton);
            } else {
                String charSequence = this.f8730f.getAdapter().getPageTitle(i).toString();
                TextView textView = new TextView(getContext());
                textView.setText(charSequence);
                textView.setGravity(17);
                textView.setSingleLine();
                m17156a(i, textView);
            }
        }
        for (int i2 = 0; i2 < this.f8731g; i2++) {
            View childAt = this.f8729e.getChildAt(i2);
            childAt.setBackgroundResource(this.f8723D);
            if (childAt instanceof TextView) {
                TextView textView2 = (TextView) childAt;
                textView2.setTextSize(0, this.f8749y);
                textView2.setTypeface(this.f8721B, 0);
                textView2.setTextColor(this.f8750z);
                if (this.f8741q) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        textView2.setAllCaps(true);
                    } else {
                        textView2.setText(textView2.getText().toString().toUpperCase(this.f8724E));
                    }
                }
            }
        }
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC3665a());
    }
}
