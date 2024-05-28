package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.sinovatech.unicom.p318ui.C9718R;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class PullToRefreshBase<T extends View> extends LinearLayout {
    static final float FRICTION = 2.0f;
    static final int MANUAL_REFRESHING = 3;
    public static final int MODE_BOTH = 3;
    public static final int MODE_PULL_DOWN_TO_REFRESH = 1;
    public static final int MODE_PULL_UP_TO_REFRESH = 2;
    static final int PULL_TO_REFRESH = 0;
    static final int REFRESHING = 2;
    static final int RELEASE_TO_REFRESH = 1;
    private int currentMode;
    private PullToRefreshBase<T>.SmoothScrollRunnable currentSmoothScrollRunnable;
    private boolean disableScrollingWhileRefreshing;
    private PullToRefreshLoadingLayout footerLayout;
    private final Handler handler;
    private int headerHeight;
    private PullToRefreshLoadingLayout headerLayout;
    private float initialMotionY;
    private boolean isBeingDragged;
    private boolean isPullToRefreshEnabled;
    private float lastMotionX;
    private float lastMotionY;
    private int mode;
    private OnRefreshListener onRefreshListener;
    T refreshableView;
    private int state;
    private int touchSlop;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnLastItemVisibleListener {
        void onLastItemVisible();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnRefreshListener {
        void onRefresh();
    }

    protected abstract T createRefreshableView(Context context, AttributeSet attributeSet);

    protected abstract boolean isReadyForPullDown();

    protected abstract boolean isReadyForPullUp();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public final class SmoothScrollRunnable implements Runnable {
        static final int ANIMATION_DURATION_MS = 190;
        static final int ANIMATION_FPS = 16;
        private final Handler handler;
        private final int scrollFromY;
        private final int scrollToY;
        private boolean continueRunning = true;
        private long startTime = -1;
        private int currentY = -1;
        private final Interpolator interpolator = new AccelerateDecelerateInterpolator();

        public SmoothScrollRunnable(Handler handler, int i, int i2) {
            this.handler = handler;
            this.scrollFromY = i;
            this.scrollToY = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.startTime == -1) {
                this.startTime = System.currentTimeMillis();
            } else {
                this.currentY = this.scrollFromY - Math.round((this.scrollFromY - this.scrollToY) * this.interpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.startTime) * 1000) / 190, 1000L), 0L)) / 1000.0f));
                PullToRefreshBase.this.setHeaderScroll(this.currentY);
            }
            if (!this.continueRunning || this.scrollToY == this.currentY) {
                return;
            }
            this.handler.postDelayed(this, 16L);
        }

        public void stop() {
            this.continueRunning = false;
            this.handler.removeCallbacks(this);
        }
    }

    public PullToRefreshBase(Context context) {
        super(context);
        this.isBeingDragged = false;
        this.state = 0;
        this.mode = 1;
        this.disableScrollingWhileRefreshing = true;
        this.isPullToRefreshEnabled = true;
        this.handler = new Handler();
        init(context, null);
    }

    public PullToRefreshBase(Context context, int i) {
        super(context);
        this.isBeingDragged = false;
        this.state = 0;
        this.mode = 1;
        this.disableScrollingWhileRefreshing = true;
        this.isPullToRefreshEnabled = true;
        this.handler = new Handler();
        this.mode = i;
        init(context, null);
    }

    public PullToRefreshBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isBeingDragged = false;
        this.state = 0;
        this.mode = 1;
        this.disableScrollingWhileRefreshing = true;
        this.isPullToRefreshEnabled = true;
        this.handler = new Handler();
        init(context, attributeSet);
    }

    public final T getAdapterView() {
        return this.refreshableView;
    }

    public final T getRefreshableView() {
        return this.refreshableView;
    }

    public final boolean isPullToRefreshEnabled() {
        return this.isPullToRefreshEnabled;
    }

    public final boolean isDisableScrollingWhileRefreshing() {
        return this.disableScrollingWhileRefreshing;
    }

    public final boolean isRefreshing() {
        int i = this.state;
        return i == 2 || i == 3;
    }

    public final void setDisableScrollingWhileRefreshing(boolean z) {
        this.disableScrollingWhileRefreshing = z;
    }

    public final void onRefreshComplete() {
        if (this.state != 0) {
            resetHeader();
        }
    }

    public final void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public final void setPullToRefreshEnabled(boolean z) {
        this.isPullToRefreshEnabled = z;
    }

    public void setReleaseLabel(String str) {
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
        if (pullToRefreshLoadingLayout != null) {
            pullToRefreshLoadingLayout.setReleaseLabel(str);
        }
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
        if (pullToRefreshLoadingLayout2 != null) {
            pullToRefreshLoadingLayout2.setReleaseLabel(str);
        }
    }

    public void setPullLabel(String str) {
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
        if (pullToRefreshLoadingLayout != null) {
            pullToRefreshLoadingLayout.setPullLabel(str);
        }
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
        if (pullToRefreshLoadingLayout2 != null) {
            pullToRefreshLoadingLayout2.setPullLabel(str);
        }
    }

    public void setRefreshingLabel(String str) {
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
        if (pullToRefreshLoadingLayout != null) {
            pullToRefreshLoadingLayout.setRefreshingLabel(str);
        }
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
        if (pullToRefreshLoadingLayout2 != null) {
            pullToRefreshLoadingLayout2.setRefreshingLabel(str);
        }
    }

    public final void setRefreshing() {
        setRefreshing(true);
    }

    public final void setRefreshing(boolean z) {
        if (isRefreshing()) {
            return;
        }
        setRefreshingInternal(z);
        this.state = 3;
    }

    public final boolean hasPullFromTop() {
        return this.currentMode != 2;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.isPullToRefreshEnabled) {
            if (isRefreshing() && this.disableScrollingWhileRefreshing) {
                return true;
            }
            if (motionEvent.getAction() != 0 || motionEvent.getEdgeFlags() == 0) {
                switch (motionEvent.getAction()) {
                    case 0:
                        if (isReadyForPull()) {
                            float y = motionEvent.getY();
                            this.initialMotionY = y;
                            this.lastMotionY = y;
                            return true;
                        }
                        break;
                    case 1:
                    case 3:
                        if (this.isBeingDragged) {
                            this.isBeingDragged = false;
                            if (this.state == 1 && this.onRefreshListener != null) {
                                setRefreshingInternal(true);
                                this.onRefreshListener.onRefresh();
                            } else {
                                smoothScrollTo(0);
                            }
                            return true;
                        }
                        break;
                    case 2:
                        if (this.isBeingDragged) {
                            this.lastMotionY = motionEvent.getY();
                            pullEvent();
                            return true;
                        }
                        break;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.isPullToRefreshEnabled) {
            if (isRefreshing() && this.disableScrollingWhileRefreshing) {
                return true;
            }
            int action = motionEvent.getAction();
            if (action == 3 || action == 1) {
                this.isBeingDragged = false;
                return false;
            } else if (action == 0 || !this.isBeingDragged) {
                if (action != 0) {
                    if (action == 2 && isReadyForPull()) {
                        float y = motionEvent.getY();
                        float f = y - this.lastMotionY;
                        float abs = Math.abs(f);
                        float abs2 = Math.abs(motionEvent.getX() - this.lastMotionX);
                        if (abs > this.touchSlop && abs > abs2) {
                            int i = this.mode;
                            if ((i == 1 || i == 3) && f >= 1.0E-4f && isReadyForPullDown()) {
                                this.lastMotionY = y;
                                this.isBeingDragged = true;
                                if (this.mode == 3) {
                                    this.currentMode = 1;
                                }
                            } else {
                                int i2 = this.mode;
                                if ((i2 == 2 || i2 == 3) && f <= 1.0E-4f && isReadyForPullUp()) {
                                    this.lastMotionY = y;
                                    this.isBeingDragged = true;
                                    if (this.mode == 3) {
                                        this.currentMode = 2;
                                    }
                                }
                            }
                        }
                    }
                } else if (isReadyForPull()) {
                    float y2 = motionEvent.getY();
                    this.initialMotionY = y2;
                    this.lastMotionY = y2;
                    this.lastMotionX = motionEvent.getX();
                    this.isBeingDragged = false;
                }
                return this.isBeingDragged;
            } else {
                return true;
            }
        }
        return false;
    }

    protected void addRefreshableView(Context context, T t) {
        addView(t, new LinearLayout.LayoutParams(-1, 0, 1.0f));
    }

    protected final int getCurrentMode() {
        return this.currentMode;
    }

    protected final PullToRefreshLoadingLayout getFooterLayout() {
        return this.footerLayout;
    }

    protected final PullToRefreshLoadingLayout getHeaderLayout() {
        return this.headerLayout;
    }

    protected final int getHeaderHeight() {
        return this.headerHeight;
    }

    protected final int getMode() {
        return this.mode;
    }

    protected void resetHeader() {
        this.state = 0;
        this.isBeingDragged = false;
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
        if (pullToRefreshLoadingLayout != null) {
            pullToRefreshLoadingLayout.reset();
        }
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
        if (pullToRefreshLoadingLayout2 != null) {
            pullToRefreshLoadingLayout2.reset();
        }
        smoothScrollTo(0);
    }

    protected void setRefreshingInternal(boolean z) {
        this.state = 2;
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
        if (pullToRefreshLoadingLayout != null) {
            pullToRefreshLoadingLayout.refreshing();
        }
        PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
        if (pullToRefreshLoadingLayout2 != null) {
            pullToRefreshLoadingLayout2.refreshing();
        }
        if (z) {
            smoothScrollTo(this.currentMode == 1 ? -this.headerHeight : this.headerHeight);
        }
    }

    protected final void setHeaderScroll(int i) {
        scrollTo(0, i);
    }

    protected final void smoothScrollTo(int i) {
        PullToRefreshBase<T>.SmoothScrollRunnable smoothScrollRunnable = this.currentSmoothScrollRunnable;
        if (smoothScrollRunnable != null) {
            smoothScrollRunnable.stop();
        }
        if (getScrollY() != i) {
            this.currentSmoothScrollRunnable = new SmoothScrollRunnable(this.handler, getScrollY(), i);
            this.handler.post(this.currentSmoothScrollRunnable);
        }
    }

    private void init(Context context, AttributeSet attributeSet) {
        int i;
        int i2;
        setOrientation(1);
        this.touchSlop = ViewConfiguration.getTouchSlop();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C9718R.styleable.PullToRefresh);
        if (obtainStyledAttributes.hasValue(3)) {
            this.mode = obtainStyledAttributes.getInteger(3, 1);
        }
        this.refreshableView = createRefreshableView(context, attributeSet);
        addRefreshableView(context, this.refreshableView);
        String string = context.getString(2131886551);
        String string2 = context.getString(2131886552);
        String string3 = context.getString(2131886553);
        int i3 = this.mode;
        if (i3 == 1 || i3 == 3) {
            i = 0;
            this.headerLayout = new PullToRefreshLoadingLayout(context, 1, string3, string, string2);
            addView(this.headerLayout, 0, new LinearLayout.LayoutParams(-1, -2));
            measureView(this.headerLayout);
            this.headerHeight = this.headerLayout.getMeasuredHeight();
        } else {
            i = 0;
        }
        int i4 = this.mode;
        if (i4 == 2 || i4 == 3) {
            this.footerLayout = new PullToRefreshLoadingLayout(context, 2, string3, string, string2);
            addView(this.footerLayout, new LinearLayout.LayoutParams(-1, -2));
            measureView(this.footerLayout);
            this.headerHeight = this.footerLayout.getMeasuredHeight();
        }
        if (obtainStyledAttributes.hasValue(2)) {
            int color = obtainStyledAttributes.getColor(2, -16777216);
            PullToRefreshLoadingLayout pullToRefreshLoadingLayout = this.headerLayout;
            if (pullToRefreshLoadingLayout != null) {
                pullToRefreshLoadingLayout.setTextColor(color);
            }
            PullToRefreshLoadingLayout pullToRefreshLoadingLayout2 = this.footerLayout;
            if (pullToRefreshLoadingLayout2 != null) {
                pullToRefreshLoadingLayout2.setTextColor(color);
                i2 = 1;
            } else {
                i2 = 1;
            }
        } else {
            i2 = 1;
        }
        if (obtainStyledAttributes.hasValue(i2)) {
            setBackgroundResource(obtainStyledAttributes.getResourceId(i2, -1));
        }
        if (obtainStyledAttributes.hasValue(i)) {
            this.refreshableView.setBackgroundResource(obtainStyledAttributes.getResourceId(i, -1));
        }
        obtainStyledAttributes.recycle();
        switch (this.mode) {
            case 2:
                setPadding(i, i, i, -this.headerHeight);
                break;
            case 3:
                int i5 = this.headerHeight;
                setPadding(i, -i5, i, -i5);
                break;
            default:
                setPadding(i, -this.headerHeight, i, i);
                break;
        }
        int i6 = this.mode;
        if (i6 != 3) {
            this.currentMode = i6;
        }
    }

    private void measureView(View view) {
        int makeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, makeMeasureSpec);
    }

    private boolean pullEvent() {
        int round;
        int scrollY = getScrollY();
        if (this.currentMode == 2) {
            round = Math.round(Math.max(this.initialMotionY - this.lastMotionY, 0.0f) / FRICTION);
        } else {
            round = Math.round(Math.min(this.initialMotionY - this.lastMotionY, 0.0f) / FRICTION);
        }
        setHeaderScroll(round);
        if (round != 0) {
            if (this.state == 0 && this.headerHeight < Math.abs(round)) {
                this.state = 1;
                switch (this.currentMode) {
                    case 1:
                        this.headerLayout.releaseToRefresh();
                        break;
                    case 2:
                        this.footerLayout.releaseToRefresh();
                        break;
                }
                return true;
            } else if (this.state == 1 && this.headerHeight >= Math.abs(round)) {
                this.state = 0;
                switch (this.currentMode) {
                    case 1:
                        this.headerLayout.pullToRefresh();
                        break;
                    case 2:
                        this.footerLayout.pullToRefresh();
                        break;
                }
                return true;
            }
        }
        return scrollY != round;
    }

    private boolean isReadyForPull() {
        switch (this.mode) {
            case 1:
                return isReadyForPullDown();
            case 2:
                return isReadyForPullUp();
            case 3:
                return isReadyForPullUp() || isReadyForPullDown();
            default:
                return false;
        }
    }

    @Override // android.view.View
    public void setLongClickable(boolean z) {
        getRefreshableView().setLongClickable(z);
    }
}
