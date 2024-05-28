package com.liang.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.AbsListView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.liang.jtablayout.badge.Badge;
import com.liang.jtablayoutx.C5196R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BadgeView extends AppCompatTextView implements Badge {
    private boolean canDrag;
    private DragView dragView;
    private int mBackgroundColor;
    private boolean mInitBackgroundFlag;
    private OnDragListener mOnDragListener;
    private int mStroke;
    private int mStrokeColor;

    /* renamed from: p */
    private int[] f12200p;
    private int padding;

    /* renamed from: r */
    private int f12201r;
    private ViewGroup scrollParent;

    /* renamed from: x */
    private int f12202x;

    /* renamed from: y */
    private int f12203y;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnDragListener {
        void onDragOut();
    }

    public OnDragListener getDragListener() {
        return this.mOnDragListener;
    }

    public void setDragListener(OnDragListener onDragListener) {
        this.mOnDragListener = onDragListener;
    }

    public BadgeView(Context context) {
        this(context, null);
    }

    public BadgeView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgeView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.padding = 1;
        this.canDrag = false;
        this.f12200p = new int[2];
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5196R.styleable.BadgeView, i, 0);
        this.mStrokeColor = obtainStyledAttributes.getColor(C5196R.styleable.BadgeView_badgeStrokeColor, -1);
        this.mStroke = obtainStyledAttributes.getDimensionPixelSize(C5196R.styleable.BadgeView_badgeStrokeWidth, dip2px(getContext(), 1.0f));
        this.mBackgroundColor = obtainStyledAttributes.getColor(C5196R.styleable.BadgeView_badgeBackgroundColor, -65536);
        obtainStyledAttributes.recycle();
        initBadge();
    }

    public void setPadding(int i) {
        this.padding = i;
    }

    private void initBadge() {
        setGravity(17);
        setVisibility(8);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.liang.widget.BadgeView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (BadgeView.this.mInitBackgroundFlag) {
                    return true;
                }
                BadgeView badgeView = BadgeView.this;
                badgeView.setBackgroundDrawable(badgeView.createStateListDrawable());
                BadgeView.this.refreshPadding();
                BadgeView.this.mInitBackgroundFlag = true;
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPadding() {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int dip2px = dip2px(getContext(), this.padding);
        int length = getText().length();
        if (length == 1) {
            int i = measuredWidth - measuredHeight;
            int floor = (int) Math.floor(i / 2.0f);
            if (i < 0) {
                int i2 = dip2px - floor;
                setPadding(i2, dip2px, i2, dip2px);
            } else {
                setPadding(Math.max(getPaddingLeft(), dip2px), dip2px, Math.max(getPaddingRight(), dip2px), dip2px);
            }
        }
        if (length > 1) {
            float f = dip2px;
            setPadding((int) ((getTextSize() / 2.0f) + f), dip2px, (int) (f + (getTextSize() / 2.0f)), dip2px);
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i) {
        this.mBackgroundColor = i;
        setBackgroundDrawable(createStateListDrawable());
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        this.mInitBackgroundFlag = false;
    }

    public void setStroke(int i, @ColorInt int i2) {
        this.mStroke = i;
        this.mStrokeColor = i2;
        setBackgroundDrawable(createStateListDrawable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StateListDrawable createStateListDrawable() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(this.mBackgroundColor);
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(50.0f);
        gradientDrawable.setStroke(this.mStroke, this.mStrokeColor);
        stateListDrawable.addState(View.EMPTY_STATE_SET, gradientDrawable);
        return stateListDrawable;
    }

    @Override // android.support.p086v7.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        if (getText().toString().trim().isEmpty()) {
            int dip2px = dip2px(getContext(), 10.0f);
            setMeasuredDimension(dip2px, dip2px);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void show(String str) {
        setText(str);
        if (getVisibility() == 0) {
            return;
        }
        setVisibility(0);
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), C5196R.anim.badge_view_show);
        loadAnimation.setInterpolator(new OvershootInterpolator());
        startAnimation(loadAnimation);
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void hide() {
        if (getVisibility() == 8) {
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), C5196R.anim.badge_view_hide);
        startAnimation(loadAnimation);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.liang.widget.BadgeView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                BadgeView.this.setVisibility(8);
            }
        });
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void setBadgeTextSize(float f) {
        setTextSize(f);
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void setBadgeBackgroundColor(int i) {
        this.mBackgroundColor = i;
        setBackgroundDrawable(createStateListDrawable());
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void setBadgeStroke(int i, int i2) {
        this.mStroke = i;
        this.mStrokeColor = i2;
        setBackgroundDrawable(createStateListDrawable());
    }

    @Override // com.liang.jtablayout.badge.Badge
    public void setBadgeTextColor(int i) {
        setTextColor(i);
    }

    public int dip2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    @Override // android.widget.TextView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewGroup viewGroup;
        if (this.canDrag && (viewGroup = (ViewGroup) getRootView()) != null) {
            switch (motionEvent.getAction()) {
                case 0:
                    viewGroup.getLocationOnScreen(this.f12200p);
                    this.scrollParent = getScrollParent();
                    ViewGroup viewGroup2 = this.scrollParent;
                    if (viewGroup2 != null) {
                        viewGroup2.requestDisallowInterceptTouchEvent(true);
                    }
                    int[] iArr = new int[2];
                    getLocationOnScreen(iArr);
                    this.f12202x = (iArr[0] + (getWidth() / 2)) - this.f12200p[0];
                    this.f12203y = (iArr[1] + (getHeight() / 2)) - this.f12200p[1];
                    this.f12201r = (getWidth() + getHeight()) / 4;
                    this.dragView = new DragView(getContext());
                    this.dragView.setLayoutParams(new ViewGroup.LayoutParams(viewGroup.getWidth(), viewGroup.getHeight()));
                    setDrawingCacheEnabled(true);
                    this.dragView.catchBitmap = getDrawingCache();
                    this.dragView.setLocation(this.f12202x, this.f12203y, this.f12201r, motionEvent.getRawX() - this.f12200p[0], motionEvent.getRawY() - this.f12200p[1]);
                    viewGroup.addView(this.dragView);
                    setVisibility(4);
                    break;
                case 1:
                case 3:
                    ViewGroup viewGroup3 = this.scrollParent;
                    if (viewGroup3 != null) {
                        viewGroup3.requestDisallowInterceptTouchEvent(false);
                    }
                    if (!this.dragView.broken) {
                        this.dragView.cancel();
                        break;
                    } else if (this.dragView.nearby) {
                        this.dragView.cancel();
                        break;
                    } else {
                        this.dragView.broken();
                        break;
                    }
                case 2:
                    this.dragView.refrashXY(motionEvent.getRawX() - this.f12200p[0], motionEvent.getRawY() - this.f12200p[1]);
                    break;
            }
            return true;
        }
        return false;
    }

    private ViewGroup getScrollParent() {
        View view = this;
        do {
            try {
                view = (View) view.getParent();
                if (view == null) {
                    return null;
                }
                if ((view instanceof AbsListView) || (view instanceof ScrollView)) {
                    break;
                }
            } catch (ClassCastException unused) {
                return null;
            }
        } while (!(view instanceof ViewPager));
        return (ViewGroup) view;
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class DragView extends View {
        private boolean broken;
        private int brokenProgress;

        /* renamed from: c1 */
        private Circle f12204c1;

        /* renamed from: c2 */
        private Circle f12205c2;
        private Bitmap catchBitmap;
        private int maxDistance;
        private boolean nearby;
        private boolean out;
        private Paint paint;
        private Path path;

        public DragView(Context context) {
            super(context);
            this.path = new Path();
            this.maxDistance = 8;
            init();
        }

        public void init() {
            this.paint = new Paint();
            this.paint.setColor(BadgeView.this.mBackgroundColor);
            this.paint.setAntiAlias(true);
            setBackgroundColor(-256);
        }

        public void setLocation(float f, float f2, float f3, float f4, float f5) {
            this.broken = false;
            this.f12204c1 = new Circle(f, f2, f3);
            this.f12205c2 = new Circle(f4, f5, f3);
        }

        public void refrashXY(float f, float f2) {
            Circle circle = this.f12205c2;
            circle.f12207x = f;
            circle.f12208y = f2;
            float f3 = 10;
            this.f12204c1.f12206r = (float) (((this.f12205c2.f12206r * this.f12205c2.f12206r) * f3) / (this.f12204c1.getDistance(circle) + (this.f12205c2.f12206r * f3)));
            Log.i("info", "c1: " + this.f12204c1.f12206r);
            invalidate();
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawColor(0);
            if (this.out) {
                float f = (this.f12205c2.f12206r / 2.0f) + (this.f12205c2.f12206r * 4.0f * (this.brokenProgress / 100.0f));
                Log.i("info", "dr" + f);
                canvas.drawCircle(this.f12205c2.f12207x, this.f12205c2.f12208y, this.f12205c2.f12206r / ((float) (this.brokenProgress + 1)), this.paint);
                canvas.drawCircle(this.f12205c2.f12207x - f, this.f12205c2.f12208y - f, this.f12205c2.f12206r / ((float) (this.brokenProgress + 2)), this.paint);
                canvas.drawCircle(this.f12205c2.f12207x + f, this.f12205c2.f12208y - f, this.f12205c2.f12206r / ((float) (this.brokenProgress + 2)), this.paint);
                canvas.drawCircle(this.f12205c2.f12207x - f, this.f12205c2.f12208y + f, this.f12205c2.f12206r / (this.brokenProgress + 2), this.paint);
                canvas.drawCircle(this.f12205c2.f12207x + f, this.f12205c2.f12208y + f, this.f12205c2.f12206r / (this.brokenProgress + 2), this.paint);
                return;
            }
            Bitmap bitmap = this.catchBitmap;
            if (bitmap != null) {
                if (bitmap == null || !bitmap.isRecycled()) {
                    canvas.drawBitmap(this.catchBitmap, this.f12205c2.f12207x - this.f12205c2.f12206r, this.f12205c2.f12208y - this.f12205c2.f12206r, this.paint);
                    this.path.reset();
                    float f2 = this.f12205c2.f12207x - this.f12204c1.f12207x;
                    float f3 = -(this.f12205c2.f12208y - this.f12204c1.f12208y);
                    double sqrt = Math.sqrt((f2 * f2) + (f3 * f3));
                    double d = f3 / sqrt;
                    double d2 = f2 / sqrt;
                    this.nearby = sqrt < ((double) (this.f12205c2.f12206r * ((float) this.maxDistance)));
                    if (this.nearby && !this.broken) {
                        canvas.drawCircle(this.f12204c1.f12207x, this.f12204c1.f12208y, this.f12204c1.f12206r, this.paint);
                        this.path.moveTo((float) (this.f12204c1.f12207x - (this.f12204c1.f12206r * d)), (float) (this.f12204c1.f12208y - (this.f12204c1.f12206r * d2)));
                        this.path.lineTo((float) (this.f12204c1.f12207x + (this.f12204c1.f12206r * d)), (float) (this.f12204c1.f12208y + (this.f12204c1.f12206r * d2)));
                        this.path.quadTo((this.f12204c1.f12207x + this.f12205c2.f12207x) / 2.0f, (this.f12204c1.f12208y + this.f12205c2.f12208y) / 2.0f, (float) (this.f12205c2.f12207x + (this.f12205c2.f12206r * d)), (float) (this.f12205c2.f12208y + (this.f12205c2.f12206r * d2)));
                        this.path.lineTo((float) (this.f12205c2.f12207x - (this.f12205c2.f12206r * d)), (float) (this.f12205c2.f12208y - (this.f12205c2.f12206r * d2)));
                        this.path.quadTo((this.f12204c1.f12207x + this.f12205c2.f12207x) / 2.0f, (this.f12204c1.f12208y + this.f12205c2.f12208y) / 2.0f, (float) (this.f12204c1.f12207x - (this.f12204c1.f12206r * d)), (float) (this.f12204c1.f12208y - (this.f12204c1.f12206r * d2)));
                        canvas.drawPath(this.path, this.paint);
                        return;
                    }
                    this.broken = true;
                }
            }
        }

        public void cancel() {
            AnimatorSet animatorSet = new AnimatorSet();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.f12205c2.f12207x, this.f12204c1.f12207x);
            long j = 150;
            ofFloat.setDuration(j);
            ofFloat.setInterpolator(new OvershootInterpolator(2.0f));
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.BadgeView.DragView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DragView.this.f12205c2.f12207x = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    DragView.this.invalidate();
                }
            });
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(this.f12205c2.f12208y, this.f12204c1.f12208y);
            ofFloat2.setDuration(j);
            ofFloat2.setInterpolator(new OvershootInterpolator(2.0f));
            ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.BadgeView.DragView.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DragView.this.f12205c2.f12208y = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    DragView.this.invalidate();
                }
            });
            animatorSet.playTogether(ofFloat, ofFloat2);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.liang.widget.BadgeView.DragView.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ((ViewGroup) DragView.this.getParent()).removeView(DragView.this);
                    BadgeView.this.setVisibility(0);
                }
            });
            animatorSet.start();
        }

        public void broken() {
            this.out = true;
            ValueAnimator ofInt = ValueAnimator.ofInt(0, 100);
            ofInt.setDuration(500);
            ofInt.setInterpolator(new LinearInterpolator());
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.liang.widget.BadgeView.DragView.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    DragView.this.brokenProgress = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    DragView.this.invalidate();
                }
            });
            ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.liang.widget.BadgeView.DragView.5
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    ((ViewGroup) DragView.this.getParent()).removeView(DragView.this);
                }
            });
            ofInt.start();
            if (BadgeView.this.mOnDragListener != null) {
                BadgeView.this.mOnDragListener.onDragOut();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class Circle {

            /* renamed from: r */
            float f12206r;

            /* renamed from: x */
            float f12207x;

            /* renamed from: y */
            float f12208y;

            public Circle(float f, float f2, float f3) {
                this.f12207x = f;
                this.f12208y = f2;
                this.f12206r = f3;
            }

            public double getDistance(Circle circle) {
                float f = this.f12207x - circle.f12207x;
                float f2 = this.f12208y - circle.f12208y;
                return Math.sqrt((f * f) + (f2 * f2));
            }
        }
    }
}
