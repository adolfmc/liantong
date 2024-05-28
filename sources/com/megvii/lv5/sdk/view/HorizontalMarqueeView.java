package com.megvii.lv5.sdk.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import com.megvii.lv5.C5388b3;
import com.megvii.lv5.sdk.C5559R;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HorizontalMarqueeView extends HorizontalScrollView {

    /* renamed from: a */
    public int f13591a;

    /* renamed from: b */
    public int f13592b;

    /* renamed from: c */
    public int f13593c;

    /* renamed from: d */
    public int f13594d;

    /* renamed from: e */
    public String f13595e;

    /* renamed from: f */
    public float f13596f;

    /* renamed from: g */
    public TextView f13597g;

    /* renamed from: h */
    public int f13598h;

    /* renamed from: i */
    public boolean f13599i;

    /* renamed from: j */
    public int f13600j;

    /* renamed from: k */
    public Paint f13601k;

    /* renamed from: l */
    public ObjectAnimator f13602l;

    /* renamed from: m */
    public ObjectAnimator f13603m;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.HorizontalMarqueeView$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class View$OnTouchListenerC5605a implements View.OnTouchListener {
        public View$OnTouchListenerC5605a(HorizontalMarqueeView horizontalMarqueeView) {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return true;
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.HorizontalMarqueeView$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class ViewTreeObserver$OnGlobalLayoutListenerC5606b implements ViewTreeObserver.OnGlobalLayoutListener {
        public ViewTreeObserver$OnGlobalLayoutListenerC5606b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            HorizontalMarqueeView horizontalMarqueeView = HorizontalMarqueeView.this;
            horizontalMarqueeView.f13600j = C5388b3.f12387b - C5388b3.m13608a(horizontalMarqueeView.getContext(), 39.0f);
            String str = "onGlobalLayout: mWidth = " + HorizontalMarqueeView.this.f13600j;
            HorizontalMarqueeView.this.m13030b();
            HorizontalMarqueeView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.HorizontalMarqueeView$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class RunnableC5607c implements Runnable {
        public RunnableC5607c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            HorizontalMarqueeView.this.m13031a(false);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.view.HorizontalMarqueeView$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5608d extends AnimatorListenerAdapter {
        public C5608d() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            super.onAnimationEnd(animator);
            HorizontalMarqueeView.this.f13603m.start();
        }
    }

    public HorizontalMarqueeView(Context context) {
        this(context, null);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalMarqueeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f13596f = 0.4f;
        this.f13599i = false;
        this.f13602l = null;
        this.f13603m = null;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5559R.styleable.Megvii_HorizontaMarqueeView);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == C5559R.styleable.Megvii_HorizontaMarqueeView_color) {
                this.f13591a = obtainStyledAttributes.getColor(index, 0);
            } else if (index == C5559R.styleable.Megvii_HorizontaMarqueeView_size) {
                this.f13592b = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == C5559R.styleable.Megvii_HorizontaMarqueeView_text_background) {
                this.f13593c = obtainStyledAttributes.getColor(index, -1);
            } else if (index == C5559R.styleable.Megvii_HorizontaMarqueeView_desc) {
                this.f13595e = obtainStyledAttributes.getString(index);
            } else if (index == C5559R.styleable.Megvii_HorizontaMarqueeView_marqueebackground) {
                this.f13594d = obtainStyledAttributes.getColor(index, -1);
            }
        }
        obtainStyledAttributes.recycle();
        m13032a();
    }

    /* renamed from: a */
    public final void m13032a() {
        this.f13601k = new Paint();
        setHorizontalScrollBarEnabled(false);
        this.f13597g = new TextView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.f13597g.setTextSize(2, this.f13592b);
        this.f13597g.setTextColor(this.f13591a);
        this.f13597g.setText(this.f13595e);
        this.f13597g.setMaxLines(1);
        this.f13597g.setPadding(C5388b3.m13608a(getContext(), 4.0f), 0, C5388b3.m13608a(getContext(), 43.0f), 0);
        this.f13597g.setBackgroundColor(this.f13593c);
        layoutParams.gravity = 17;
        this.f13597g.setLayoutParams(layoutParams);
        addView(this.f13597g);
        setBackgroundColor(this.f13594d);
        setOnTouchListener(new View$OnTouchListenerC5605a(this));
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver$OnGlobalLayoutListenerC5606b());
    }

    /* renamed from: a */
    public void m13031a(boolean z) {
        m13030b();
        if (z) {
            postDelayed(new RunnableC5607c(), 1000L);
        } else if (!this.f13599i || getWidth() == 0 || getHeight() == 0) {
        } else {
            String str = "creatCurrentAnimation: contentWidth = " + this.f13598h;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.f13597g, "translationX", 0.0f, -this.f13598h);
            ofFloat.setDuration(this.f13598h / this.f13596f);
            ofFloat.setInterpolator(new LinearInterpolator());
            this.f13602l = ofFloat;
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.f13597g, "translationX", getWidth(), -this.f13598h);
            ofFloat2.setDuration((this.f13598h + getWidth()) / this.f13596f);
            ofFloat2.setInterpolator(new LinearInterpolator());
            ofFloat2.setRepeatCount(-1);
            this.f13603m = ofFloat2;
            this.f13602l.addListener(new C5608d());
            this.f13602l.start();
        }
    }

    /* renamed from: b */
    public final void m13030b() {
        int i;
        this.f13601k.setTextSize(this.f13597g.getTextSize());
        this.f13601k.setTypeface(this.f13597g.getTypeface());
        this.f13598h = ((int) this.f13601k.measureText(this.f13597g.getText().toString())) + this.f13597g.getPaddingLeft() + this.f13597g.getPaddingRight();
        String str = "run:  = contentWidth = " + this.f13598h + "     ，getWidth() = " + this.f13600j;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        if (this.f13598h > this.f13600j) {
            this.f13599i = true;
            i = 19;
        } else {
            this.f13599i = false;
            i = 17;
        }
        layoutParams.gravity = i;
        this.f13597g.setLayoutParams(layoutParams);
        requestLayout();
    }

    /* renamed from: c */
    public void m13029c() {
        ObjectAnimator objectAnimator = this.f13602l;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.f13602l.cancel();
        }
        ObjectAnimator objectAnimator2 = this.f13603m;
        if (objectAnimator2 != null && objectAnimator2.isRunning()) {
            this.f13603m.cancel();
        }
        this.f13597g.setTranslationX(0.0f);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setAnimSpeed(float f) {
        this.f13596f = f;
    }

    public void setMarqueeBackground(int i) {
        setBackgroundColor(i);
    }

    public void setMarqueeTextColor(int i) {
        TextView textView = this.f13597g;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    public void setMarqueeTextSize(int i) {
        TextView textView = this.f13597g;
        if (textView != null) {
            textView.setTextSize(2, i);
        }
    }

    public void setMarqueeTv(String str) {
        TextView textView = this.f13597g;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
