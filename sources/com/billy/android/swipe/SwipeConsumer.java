package com.billy.android.swipe;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AbsSeekBar;
import com.billy.android.swipe.calculator.SwipeDistanceCalculator;
import com.billy.android.swipe.internal.ScrimView;
import com.billy.android.swipe.internal.SwipeHelper;
import com.billy.android.swipe.internal.ViewCompat;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.billy.android.swipe.listener.SwipeListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class SwipeConsumer {
    public static int DEFAULT_OPEN_DISTANCE_IN_DP = 150;
    public static final int DIRECTION_ALL = 15;
    public static final int DIRECTION_BOTTOM = 8;
    public static final int DIRECTION_HORIZONTAL = 3;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_TOP = 4;
    public static final int DIRECTION_VERTICAL = 12;
    public static final float PROGRESS_CLOSE = 0.0f;
    public static final float PROGRESS_OPEN = 1.0f;
    public static final int RELEASE_MODE_AUTO_CLOSE = 1;
    public static final int RELEASE_MODE_AUTO_OPEN = 2;
    public static final int RELEASE_MODE_AUTO_OPEN_CLOSE = 3;
    public static final int RELEASE_MODE_HOLE_OPEN = 4;
    public static final int RELEASE_MODE_NONE = 0;
    protected int mCachedSwipeDistanceX;
    protected int mCachedSwipeDistanceY;
    protected int mCurDisplayDistanceX;
    protected int mCurDisplayDistanceY;
    protected int mCurSwipeDistanceX;
    protected int mCurSwipeDistanceY;
    protected int mDirection;
    protected boolean mDisableSwipeOnSettling;
    protected int mEdgeSize;
    protected int mHeight;
    protected Interpolator mInterpolator;
    protected Integer mMaxSettleDuration;
    protected int mOpenDistance;
    protected boolean mOpenDistanceSpecified;
    protected float mProgress;
    protected SwipeDistanceCalculator mSwipeDistanceCalculator;
    protected SwipeHelper mSwipeHelper;
    protected int mSwipeMaxDistance;
    protected int mSwipeOpenDistance;
    protected volatile boolean mSwiping;
    protected Object mTag;
    protected int mWidth;
    public SmartSwipeWrapper mWrapper;
    private int mEnableDirection = 0;
    private int mLockDirection = 0;
    private float mSensitivity = 1.0f;
    protected int mReleaseMode = 1;
    protected final List<SwipeListener> mListeners = new CopyOnWriteArrayList();
    protected float mOverSwipeFactor = 0.0f;
    protected int mEnableNested = 255;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: as */
    public <T extends SwipeConsumer> T m17389as(Class<T> cls) {
        return this;
    }

    public void dispatchDraw(Canvas canvas) {
    }

    protected void initChildrenFormXml() {
    }

    protected abstract void onDisplayDistanceChanged(int i, int i2, int i3, int i4);

    public void onDraw(Canvas canvas) {
    }

    public boolean onLayout(boolean z, int i, int i2, int i3, int i4) {
        return false;
    }

    public boolean tryAcceptSettling(int i, float f, float f2) {
        if (isNestedAndDisabled(i, this.mDirection)) {
            return false;
        }
        return ((this.mDisableSwipeOnSettling && getDragState() == 2) || !isDirectionEnable(this.mDirection) || isDirectionLocked(this.mDirection)) ? false : true;
    }

    public boolean tryAcceptMoving(int i, float f, float f2, float f3, float f4) {
        int calSwipeDirection = calSwipeDirection(i, f, f2, f3, f4);
        boolean z = calSwipeDirection != 0;
        if (z) {
            this.mDirection = calSwipeDirection;
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x00c5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int calSwipeDirection(int r15, float r16, float r17, float r18, float r19) {
        /*
            Method dump skipped, instructions count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.billy.android.swipe.SwipeConsumer.calSwipeDirection(int, float, float, float, float):int");
    }

    protected boolean isNestedAndDisabled(int i, int i2) {
        return (i == -2 && !isNestedScrollEnable(i2)) || (i == -3 && !isNestedFlyEnable(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canChildScroll(ViewGroup viewGroup, int i, int i2, float f, float f2, float f3, float f4) {
        SwipeConsumer swipeConsumer;
        View findTopChildUnder = findTopChildUnder(viewGroup, (int) f, (int) f2);
        boolean z = false;
        if (findTopChildUnder instanceof SmartSwipeWrapper) {
            SmartSwipeWrapper smartSwipeWrapper = (SmartSwipeWrapper) findTopChildUnder;
            SwipeHelper swipeHelper = smartSwipeWrapper.mHelper;
            if (swipeHelper != null && (swipeConsumer = swipeHelper.getSwipeConsumer()) != null) {
                if (swipeConsumer.calSwipeDirection(i2, f, f2, f3, f4) != 0 && swipeConsumer.getProgress() < 1.0f) {
                    z = true;
                }
            } else {
                Iterator<SwipeConsumer> it = smartSwipeWrapper.getAllConsumers().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    SwipeConsumer next = it.next();
                    if (next != null && next.calSwipeDirection(i2, f, f2, f3, f4) != 0) {
                        z = true;
                        break;
                    }
                }
            }
        } else if (findTopChildUnder != null) {
            if (i != 4 && i != 8) {
                switch (i) {
                    case 1:
                    case 2:
                        if (findTopChildUnder instanceof AbsSeekBar) {
                            AbsSeekBar absSeekBar = (AbsSeekBar) findTopChildUnder;
                            int progress = absSeekBar.getProgress();
                            int min = Build.VERSION.SDK_INT >= 26 ? absSeekBar.getMin() : 0;
                            int max = absSeekBar.getMax();
                            if ((f3 > 0.0f && progress < max) || (f3 < 0.0f && progress > min)) {
                                z = true;
                                break;
                            }
                        } else {
                            z = findTopChildUnder.canScrollHorizontally(f3 > 0.0f ? -1 : 1);
                            break;
                        }
                        break;
                }
            } else {
                int i3 = f4 > 0.0f ? -1 : 1;
                if (findTopChildUnder instanceof AbsListView) {
                    z = ViewCompat.canListViewScrollVertical((AbsListView) findTopChildUnder, i3);
                } else {
                    z = findTopChildUnder.canScrollVertically(i3);
                }
            }
        }
        return (z || !(findTopChildUnder instanceof ViewGroup)) ? z : canChildScroll((ViewGroup) findTopChildUnder, i, i2, f - findTopChildUnder.getLeft(), f2 - findTopChildUnder.getTop(), f3, f4);
    }

    public void onSwipeAccepted(int i, boolean z, float f, float f2) {
        this.mSwiping = true;
        ViewParent parent = this.mWrapper.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        if (this.mCurSwipeDistanceX != 0 || this.mCurSwipeDistanceY != 0) {
            this.mCachedSwipeDistanceX = this.mCurSwipeDistanceX;
            this.mCachedSwipeDistanceY = this.mCurSwipeDistanceY;
        }
        this.mSwipeOpenDistance = getSwipeOpenDistance();
        float f3 = this.mOverSwipeFactor;
        if (f3 > 0.0f) {
            this.mSwipeMaxDistance = (int) (this.mSwipeOpenDistance * (f3 + 1.0f));
        } else {
            this.mSwipeMaxDistance = this.mSwipeOpenDistance;
        }
        notifySwipeStart();
    }

    public void onSwipeReleased(float f, float f2) {
        ViewParent parent = this.mWrapper.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        notifySwipeRelease(f, f2);
        if (this.mProgress >= 1.0f && (this.mReleaseMode & 4) == 4) {
            smoothSlideTo(1.0f);
            return;
        }
        switch (this.mReleaseMode & 3) {
            case 1:
                if (this.mProgress >= 1.0f) {
                    onOpened();
                }
                smoothSlideTo(0.0f);
                return;
            case 2:
                smoothSlideTo(1.0f);
                return;
            case 3:
                smoothOpenOrClose(f, f2);
                return;
            default:
                return;
        }
    }

    protected void smoothOpenOrClose(float f, float f2) {
        int i = this.mDirection;
        boolean z = false;
        if (i == 4) {
            int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            if (i2 > 0 || (i2 == 0 && this.mProgress > 0.5f)) {
                z = true;
            }
        } else if (i != 8) {
            switch (i) {
                case 1:
                    int i3 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                    if (i3 > 0 || (i3 == 0 && this.mProgress > 0.5f)) {
                        z = true;
                        break;
                    }
                case 2:
                    if (f < 0.0f || (f == 0.0f && this.mProgress > 0.5f)) {
                        z = true;
                        break;
                    }
                    break;
            }
        } else if (f2 < 0.0f || (f2 == 0.0f && this.mProgress > 0.5f)) {
            z = true;
        }
        smoothSlideTo(z ? 1.0f : 0.0f);
    }

    public void setCurrentStateAsClosed() {
        onClosed();
        reset();
    }

    protected void notifySwipeOpened() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeOpened(this.mWrapper, this, this.mDirection);
            }
        }
    }

    protected void notifySwipeClosed() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeClosed(this.mWrapper, this, this.mDirection);
            }
        }
    }

    protected void notifyAttachToWrapper() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onConsumerAttachedToWrapper(this.mWrapper, this);
            }
        }
    }

    protected void notifyDetachFromWrapper() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onConsumerDetachedFromWrapper(this.mWrapper, this);
            }
        }
    }

    protected void notifySwipeStateChanged(int i) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeStateChanged(this.mWrapper, this, i, this.mDirection, this.mProgress);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifySwipeStart() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeStart(this.mWrapper, this, this.mDirection);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifySwipeRelease(float f, float f2) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeRelease(this.mWrapper, this, this.mDirection, this.mProgress, f, f2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void notifySwipeProgress(boolean z) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeProcess(this.mWrapper, this, this.mDirection, z, this.mProgress);
            }
        }
    }

    public int getHorizontalRange(float f, float f2) {
        if (this.mCurSwipeDistanceX != 0 || ((f > 0.0f && isLeftEnable() && !isLeftLocked()) || (f < 0.0f && isRightEnable() && !isRightLocked()))) {
            return getSwipeOpenDistance();
        }
        return 0;
    }

    public int getVerticalRange(float f, float f2) {
        if (this.mCurSwipeDistanceY != 0 || ((f2 > 0.0f && isTopEnable() && !isTopLocked()) || (f2 < 0.0f && isBottomEnable() && !isBottomLocked()))) {
            return getSwipeOpenDistance();
        }
        return 0;
    }

    public int clampDistanceHorizontal(int i, int i2) {
        int i3 = this.mCachedSwipeDistanceX;
        if (i3 != 0) {
            i += i3;
            this.mCachedSwipeDistanceX = 0;
        }
        if ((this.mDirection & 1) > 0 && isLeftEnable()) {
            return SmartSwipe.ensureBetween(i, 0, this.mSwipeMaxDistance);
        }
        if ((this.mDirection & 2) <= 0 || !isRightEnable()) {
            return 0;
        }
        return SmartSwipe.ensureBetween(i, -this.mSwipeMaxDistance, 0);
    }

    public int clampDistanceVertical(int i, int i2) {
        int i3 = this.mCachedSwipeDistanceY;
        if (i3 != 0) {
            i += i3;
            this.mCachedSwipeDistanceY = 0;
        }
        if ((this.mDirection & 4) > 0 && isTopEnable()) {
            return SmartSwipe.ensureBetween(i, 0, this.mSwipeMaxDistance);
        }
        if ((this.mDirection & 8) <= 0 || !isBottomEnable()) {
            return 0;
        }
        return SmartSwipe.ensureBetween(i, -this.mSwipeMaxDistance, 0);
    }

    public void onSwipeDistanceChanged(int i, int i2, int i3, int i4) {
        if (getOpenDistance() <= 0) {
            return;
        }
        float f = this.mProgress;
        if (i != this.mCurSwipeDistanceX || i2 != this.mCurSwipeDistanceY) {
            this.mCurSwipeDistanceX = i;
            this.mCurSwipeDistanceY = i2;
            int i5 = this.mSwipeOpenDistance;
            if (i5 <= 0) {
                this.mProgress = 0.0f;
            } else {
                int i6 = this.mDirection;
                if (i6 != 4 && i6 != 8) {
                    switch (i6) {
                        case 1:
                        case 2:
                            this.mProgress = Math.abs(this.mCurSwipeDistanceX / i5);
                            break;
                    }
                } else {
                    this.mProgress = Math.abs(this.mCurSwipeDistanceY / this.mSwipeOpenDistance);
                }
            }
            int i7 = this.mDirection;
            if ((i7 & 3) > 0) {
                SwipeDistanceCalculator swipeDistanceCalculator = this.mSwipeDistanceCalculator;
                if (swipeDistanceCalculator != null) {
                    i = swipeDistanceCalculator.calculateSwipeDistance(i, this.mProgress);
                }
                i3 = i - this.mCurDisplayDistanceX;
                this.mCurDisplayDistanceX = i;
                i4 = 0;
            } else if ((i7 & 12) > 0) {
                SwipeDistanceCalculator swipeDistanceCalculator2 = this.mSwipeDistanceCalculator;
                if (swipeDistanceCalculator2 != null) {
                    i2 = swipeDistanceCalculator2.calculateSwipeDistance(i2, this.mProgress);
                }
                i4 = i2 - this.mCurDisplayDistanceY;
                this.mCurDisplayDistanceY = i2;
                i3 = 0;
            }
            onDisplayDistanceChanged(this.mCurDisplayDistanceX, this.mCurDisplayDistanceY, i3, i4);
        }
        if (this.mProgress != f) {
            notifySwipeProgress(getDragState() == 2);
        }
    }

    public void onAttachToWrapper(SmartSwipeWrapper smartSwipeWrapper, SwipeHelper swipeHelper) {
        this.mWrapper = smartSwipeWrapper;
        if (this.mOpenDistance == 0) {
            this.mOpenDistance = SmartSwipe.dp2px(DEFAULT_OPEN_DISTANCE_IN_DP, smartSwipeWrapper.getContext());
        }
        this.mSwipeHelper = swipeHelper;
        Integer num = this.mMaxSettleDuration;
        if (num != null) {
            this.mSwipeHelper.setMaxSettleDuration(num.intValue());
        }
        if (this.mWrapper.isInflateFromXml()) {
            initChildrenFormXml();
        }
        notifyAttachToWrapper();
    }

    public void onDetachFromWrapper() {
        notifyDetachFromWrapper();
        reset();
    }

    public void onMeasure(int i, int i2) {
        this.mWidth = this.mWrapper.getMeasuredWidth();
        this.mHeight = this.mWrapper.getMeasuredHeight();
    }

    public boolean isViewUnder(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }

    public View findTopChildUnder(ViewGroup viewGroup, int i, int i2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom() && childAt.getVisibility() == 0 && (!(childAt instanceof ScrimView) || childAt.isFocusable() || childAt.isClickable())) {
                return childAt;
            }
        }
        return null;
    }

    public void onStateChanged(int i) {
        notifySwipeStateChanged(i);
        if (i == 0) {
            this.mSwiping = false;
            float f = this.mProgress;
            if (f >= 1.0f) {
                onOpened();
            } else if (f <= 0.0f) {
                onClosed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onOpened() {
        notifySwipeOpened();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onClosed() {
        notifySwipeClosed();
        this.mDirection = 0;
    }

    protected void reset() {
        this.mDirection = 0;
        this.mProgress = 0.0f;
        this.mCurDisplayDistanceX = 0;
        this.mCurSwipeDistanceX = 0;
        this.mCachedSwipeDistanceX = 0;
        this.mCurDisplayDistanceY = 0;
        this.mCurSwipeDistanceY = 0;
        this.mCachedSwipeDistanceY = 0;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public SwipeConsumer setInterpolator(Interpolator interpolator) {
        SmartSwipeWrapper smartSwipeWrapper;
        this.mInterpolator = interpolator;
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null && (smartSwipeWrapper = this.mWrapper) != null) {
            swipeHelper.setInterpolator(smartSwipeWrapper.getContext(), interpolator);
        }
        return this;
    }

    public float getSensitivity() {
        return this.mSensitivity;
    }

    public SwipeConsumer setSensitivity(float f) {
        if (f > 0.0f) {
            this.mSensitivity = f;
            SwipeHelper swipeHelper = this.mSwipeHelper;
            if (swipeHelper != null) {
                swipeHelper.setSensitivity(f);
            }
        }
        return this;
    }

    public int getReleaseMode() {
        return this.mReleaseMode;
    }

    public SwipeConsumer setReleaseMode(int i) {
        this.mReleaseMode = i;
        return this;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public SwipeConsumer setEdgeSize(int i) {
        this.mEdgeSize = i;
        return this;
    }

    public SmartSwipeWrapper getWrapper() {
        return this.mWrapper;
    }

    public SwipeHelper getSwipeHelper() {
        return this.mSwipeHelper;
    }

    public int getDragState() {
        return this.mSwipeHelper.getDragState();
    }

    public float getProgress() {
        return this.mProgress;
    }

    public boolean isSwiping() {
        return this.mSwiping;
    }

    public int getSwipeOpenDistance() {
        SwipeDistanceCalculator swipeDistanceCalculator = this.mSwipeDistanceCalculator;
        if (swipeDistanceCalculator != null) {
            return swipeDistanceCalculator.calculateSwipeOpenDistance(this.mOpenDistance);
        }
        return this.mOpenDistance;
    }

    public int getOpenDistance() {
        return this.mOpenDistance;
    }

    public SwipeConsumer setOpenDistance(int i) {
        this.mOpenDistance = i;
        this.mOpenDistanceSpecified = true;
        return this;
    }

    public SwipeConsumer removeAllListeners() {
        this.mListeners.clear();
        return this;
    }

    public SwipeConsumer removeListener(SwipeListener swipeListener) {
        this.mListeners.remove(swipeListener);
        return this;
    }

    public SwipeConsumer addListener(SwipeListener swipeListener) {
        if (swipeListener != null && !this.mListeners.contains(swipeListener)) {
            this.mListeners.add(swipeListener);
            SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
            if (smartSwipeWrapper != null) {
                swipeListener.onConsumerAttachedToWrapper(smartSwipeWrapper, this);
            }
        }
        return this;
    }

    public SwipeConsumer setSwipeDistanceCalculator(SwipeDistanceCalculator swipeDistanceCalculator) {
        this.mSwipeDistanceCalculator = swipeDistanceCalculator;
        return this;
    }

    public SwipeDistanceCalculator getSwipeDistanceCalculator() {
        return this.mSwipeDistanceCalculator;
    }

    public boolean isDisableSwipeOnSetting() {
        return this.mDisableSwipeOnSettling;
    }

    public SwipeConsumer setDisableSwipeOnSettling(boolean z) {
        this.mDisableSwipeOnSettling = z;
        return this;
    }

    public float getOverSwipeFactor() {
        return this.mOverSwipeFactor;
    }

    public SwipeConsumer setOverSwipeFactor(float f) {
        if (f >= 0.0f) {
            this.mOverSwipeFactor = f;
        }
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public SwipeConsumer setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public Integer getMaxSettleDuration() {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null) {
            return Integer.valueOf(swipeHelper.getMaxSettleDuration());
        }
        return this.mMaxSettleDuration;
    }

    public SwipeConsumer setMaxSettleDuration(int i) {
        this.mMaxSettleDuration = Integer.valueOf(i);
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null) {
            swipeHelper.setMaxSettleDuration(i);
        }
        return this;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public SwipeConsumer setWidth(int i) {
        this.mWidth = i;
        return this;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public SwipeConsumer setHeight(int i) {
        this.mHeight = i;
        return this;
    }

    public SwipeConsumer setLeftOpen() {
        return open(false, 1);
    }

    public SwipeConsumer setRightOpen() {
        return open(false, 2);
    }

    public SwipeConsumer setTopOpen() {
        return open(false, 4);
    }

    public SwipeConsumer setBottomOpen() {
        return open(false, 8);
    }

    public SwipeConsumer smoothLeftOpen() {
        return open(true, 1);
    }

    public SwipeConsumer smoothRightOpen() {
        return open(true, 2);
    }

    public SwipeConsumer smoothTopOpen() {
        return open(true, 4);
    }

    public SwipeConsumer smoothBottomOpen() {
        return open(true, 8);
    }

    public SwipeConsumer open(boolean z, int i) {
        int i2 = this.mDirection;
        if (i2 == 0) {
            if (!isDirectionEnable(i)) {
                return this;
            }
            this.mDirection = i;
            onSwipeAccepted(0, true, 0.0f, 0.0f);
        } else if (i2 != i || this.mProgress == 1.0f) {
            return this;
        }
        if (!isDirectionLocked(this.mDirection)) {
            final int i3 = this.mDirection;
            lockDirection(i3);
            addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SwipeConsumer.1
                @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i4) {
                    SwipeConsumer.this.unlockDirection(i3);
                    SwipeConsumer.this.removeListener(this);
                }
            });
        }
        slideTo(z, 1.0f);
        return this;
    }

    public SwipeConsumer close() {
        return close(false);
    }

    public SwipeConsumer smoothClose() {
        return close(true);
    }

    public SwipeConsumer close(boolean z) {
        if (this.mDirection != 0 && this.mProgress != 0.0f) {
            onSwipeAccepted(0, true, 0.0f, 0.0f);
            this.mCachedSwipeDistanceX = 0;
            this.mCachedSwipeDistanceY = 0;
            if (!isDirectionLocked(this.mDirection)) {
                lockDirection(this.mDirection);
                addListener(new SimpleSwipeListener() { // from class: com.billy.android.swipe.SwipeConsumer.2
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        SwipeConsumer.this.unlockDirection(i);
                        SwipeConsumer.this.removeListener(this);
                    }
                });
            }
            if (z) {
                smoothSlideTo(0, 0);
            } else {
                smoothSlideTo(0, 0, 0, 0);
            }
        }
        return this;
    }

    public SwipeConsumer smoothSlideTo(float f) {
        slideTo(true, f);
        return this;
    }

    public SwipeConsumer slideTo(boolean z, float f) {
        int ensureBetween = (int) (this.mSwipeOpenDistance * SmartSwipe.ensureBetween(f, 0.0f, 1.0f));
        int i = this.mDirection;
        int i2 = 0;
        if (i != 4) {
            if (i != 8) {
                switch (i) {
                    case 1:
                        i2 = ensureBetween;
                        ensureBetween = 0;
                        break;
                    case 2:
                        i2 = -ensureBetween;
                        ensureBetween = 0;
                        break;
                    default:
                        ensureBetween = 0;
                        break;
                }
            } else {
                ensureBetween = -ensureBetween;
            }
        }
        if (z) {
            smoothSlideTo(i2, ensureBetween);
        } else {
            smoothSlideTo(i2, ensureBetween, i2, ensureBetween);
        }
        return this;
    }

    public SwipeConsumer addToExclusiveGroup(SwipeConsumerExclusiveGroup swipeConsumerExclusiveGroup) {
        if (swipeConsumerExclusiveGroup != null) {
            swipeConsumerExclusiveGroup.add(this);
        }
        return this;
    }

    public int getDirection() {
        return this.mDirection;
    }

    public SwipeConsumer enableDirection(int i, boolean z) {
        if (z) {
            return enableDirection(i);
        }
        return disableDirection(i);
    }

    public SwipeConsumer enableLeft() {
        return enableDirection(1);
    }

    public SwipeConsumer enableRight() {
        return enableDirection(2);
    }

    public SwipeConsumer enableTop() {
        return enableDirection(4);
    }

    public SwipeConsumer enableBottom() {
        return enableDirection(8);
    }

    public SwipeConsumer enableHorizontal() {
        return enableDirection(3);
    }

    public SwipeConsumer enableVertical() {
        return enableDirection(12);
    }

    public SwipeConsumer enableAllDirections() {
        return enableDirection(15);
    }

    public SwipeConsumer disableLeft() {
        return disableDirection(1);
    }

    public SwipeConsumer disableRight() {
        return disableDirection(2);
    }

    public SwipeConsumer disableTop() {
        return disableDirection(4);
    }

    public SwipeConsumer disableBottom() {
        return disableDirection(8);
    }

    public SwipeConsumer disableHorizontal() {
        return disableDirection(3);
    }

    public SwipeConsumer disableVertical() {
        return disableDirection(12);
    }

    public SwipeConsumer disableAllDirections() {
        return disableDirection(15);
    }

    public SwipeConsumer enableDirection(int i) {
        this.mEnableDirection = i | this.mEnableDirection;
        return this;
    }

    public SwipeConsumer disableDirection(int i) {
        if ((this.mDirection & i) != 0) {
            close();
        }
        this.mEnableDirection = (~i) & this.mEnableDirection;
        return this;
    }

    public boolean isDirectionEnable(int i) {
        return i != 0 && (this.mEnableDirection & i) == i;
    }

    public boolean isAllDirectionsEnable() {
        return (this.mEnableDirection & 15) == 15;
    }

    public boolean isVerticalEnable() {
        return (this.mEnableDirection & 12) == 12;
    }

    public boolean isHorizontalEnable() {
        return (this.mEnableDirection & 3) == 3;
    }

    public boolean isLeftEnable() {
        return (this.mEnableDirection & 1) != 0;
    }

    public boolean isRightEnable() {
        return (this.mEnableDirection & 2) != 0;
    }

    public boolean isTopEnable() {
        return (this.mEnableDirection & 4) != 0;
    }

    public boolean isBottomEnable() {
        return (this.mEnableDirection & 8) != 0;
    }

    public SwipeConsumer lockDirection(int i, boolean z) {
        if (z) {
            return lockDirection(i);
        }
        return unlockDirection(i);
    }

    public SwipeConsumer lockLeft() {
        return lockDirection(1);
    }

    public SwipeConsumer lockRight() {
        return lockDirection(2);
    }

    public SwipeConsumer lockTop() {
        return lockDirection(4);
    }

    public SwipeConsumer lockBottom() {
        return lockDirection(8);
    }

    public SwipeConsumer lockHorizontal() {
        return lockDirection(3);
    }

    public SwipeConsumer lockVertical() {
        return lockDirection(12);
    }

    public SwipeConsumer lockAllDirections() {
        return lockDirection(15);
    }

    public SwipeConsumer unlockLeft() {
        return unlockDirection(1);
    }

    public SwipeConsumer unlockRight() {
        return unlockDirection(2);
    }

    public SwipeConsumer unlockTop() {
        return unlockDirection(4);
    }

    public SwipeConsumer unlockBottom() {
        return unlockDirection(8);
    }

    public SwipeConsumer unlockHorizontal() {
        return unlockDirection(3);
    }

    public SwipeConsumer unlockVertical() {
        return unlockDirection(12);
    }

    public SwipeConsumer unlockAllDirections() {
        return unlockDirection(15);
    }

    public SwipeConsumer lockDirection(int i) {
        this.mLockDirection = i | this.mLockDirection;
        return this;
    }

    public SwipeConsumer unlockDirection(int i) {
        this.mLockDirection = (~i) & this.mLockDirection;
        return this;
    }

    public boolean isDirectionLocked(int i) {
        return i != 0 && (this.mLockDirection & i) == i;
    }

    public boolean isAllDirectionsLocked() {
        return (this.mLockDirection & 15) == 15;
    }

    public boolean isVerticalLocked() {
        return (this.mLockDirection & 12) == 12;
    }

    public boolean isHorizontalLocked() {
        return (this.mLockDirection & 3) == 3;
    }

    public boolean isLeftLocked() {
        return (this.mLockDirection & 1) != 0;
    }

    public boolean isRightLocked() {
        return (this.mLockDirection & 2) != 0;
    }

    public boolean isTopLocked() {
        return (this.mLockDirection & 4) != 0;
    }

    public boolean isBottomLocked() {
        return (this.mLockDirection & 8) != 0;
    }

    public SwipeConsumer enableNestedScrollLeft(boolean z) {
        return enableNestedScroll(1, z);
    }

    public SwipeConsumer enableNestedScrollRight(boolean z) {
        return enableNestedScroll(2, z);
    }

    public SwipeConsumer enableNestedScrollTop(boolean z) {
        return enableNestedScroll(4, z);
    }

    public SwipeConsumer enableNestedScrollBottom(boolean z) {
        return enableNestedScroll(8, z);
    }

    public SwipeConsumer enableNestedScrollHorizontal(boolean z) {
        return enableNestedScroll(3, z);
    }

    public SwipeConsumer enableNestedScrollVertical(boolean z) {
        return enableNestedScroll(12, z);
    }

    public SwipeConsumer enableNestedScrollAllDirections(boolean z) {
        return enableNestedScroll(15, z);
    }

    private SwipeConsumer enableNestedScroll(int i, boolean z) {
        if (z) {
            this.mEnableNested = i | this.mEnableNested;
        } else {
            this.mEnableNested = (~i) & this.mEnableNested;
        }
        return this;
    }

    public boolean isNestedScrollEnable(int i) {
        return (this.mEnableNested & i) == i;
    }

    public SwipeConsumer enableNestedFlyLeft(boolean z) {
        return enableNestedFly(1, z);
    }

    public SwipeConsumer enableNestedFlyRight(boolean z) {
        return enableNestedFly(2, z);
    }

    public SwipeConsumer enableNestedFlyTop(boolean z) {
        return enableNestedFly(4, z);
    }

    public SwipeConsumer enableNestedFlyBottom(boolean z) {
        return enableNestedFly(8, z);
    }

    public SwipeConsumer enableNestedFlyHorizontal(boolean z) {
        return enableNestedFly(3, z);
    }

    public SwipeConsumer enableNestedFlyVertical(boolean z) {
        return enableNestedFly(12, z);
    }

    public SwipeConsumer enableNestedFlyAllDirections(boolean z) {
        return enableNestedFly(15, z);
    }

    private SwipeConsumer enableNestedFly(int i, boolean z) {
        if (z) {
            this.mEnableNested = (i << 4) | this.mEnableNested;
        } else {
            this.mEnableNested = (~(i << 4)) & this.mEnableNested;
        }
        return this;
    }

    public boolean isNestedFlyEnable(int i) {
        return ((this.mEnableNested >> 4) & i) == i;
    }

    public boolean isVerticalDirection() {
        return (this.mDirection & 12) > 0;
    }

    public boolean isHorizontalDirection() {
        return (this.mDirection & 3) > 0;
    }

    public void smoothSlideTo(int i, int i2, int i3, int i4) {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper == null || this.mWrapper == null) {
            return;
        }
        swipeHelper.smoothSlideTo(i, i2, i3, i4);
        ViewCompat.postInvalidateOnAnimation(this.mWrapper);
    }

    public void smoothSlideTo(int i, int i2) {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper == null || this.mWrapper == null) {
            return;
        }
        swipeHelper.smoothSlideTo(i, i2);
        ViewCompat.postInvalidateOnAnimation(this.mWrapper);
    }

    public boolean isOpened() {
        return getDragState() == 0 && this.mProgress >= 1.0f;
    }

    public boolean isClosed() {
        return getDragState() == 0 && this.mProgress <= 0.0f;
    }

    public <T extends SwipeConsumer> T addConsumer(T t) {
        SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
        return smartSwipeWrapper != null ? (T) smartSwipeWrapper.addConsumer(t) : t;
    }
}
