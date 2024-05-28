package com.scwang.smartrefresh.layout.header;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.scwang.smartrefresh.layout.C6929R;
import com.scwang.smartrefresh.layout.api.OnTwoLevelListener;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.InternalAbstract;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TwoLevelHeader extends InternalAbstract implements RefreshHeader {
    protected boolean mEnablePullToCloseTwoLevel;
    protected boolean mEnableTwoLevel;
    protected int mFloorDuration;
    protected float mFloorRage;
    protected int mHeaderHeight;
    protected float mMaxRage;
    protected float mPercent;
    protected RefreshInternal mRefreshHeader;
    protected RefreshKernel mRefreshKernel;
    protected float mRefreshRage;
    protected int mSpinner;
    protected OnTwoLevelListener mTwoLevelListener;

    public TwoLevelHeader(@NonNull Context context) {
        this(context, null);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TwoLevelHeader(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPercent = 0.0f;
        this.mMaxRage = 2.5f;
        this.mFloorRage = 1.9f;
        this.mRefreshRage = 1.0f;
        this.mEnableTwoLevel = true;
        this.mEnablePullToCloseTwoLevel = true;
        this.mFloorDuration = 1000;
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C6929R.styleable.TwoLevelHeader);
        this.mMaxRage = obtainStyledAttributes.getFloat(C6929R.styleable.TwoLevelHeader_srlMaxRage, this.mMaxRage);
        this.mFloorRage = obtainStyledAttributes.getFloat(C6929R.styleable.TwoLevelHeader_srlFloorRage, this.mFloorRage);
        this.mRefreshRage = obtainStyledAttributes.getFloat(C6929R.styleable.TwoLevelHeader_srlRefreshRage, this.mRefreshRage);
        this.mFloorDuration = obtainStyledAttributes.getInt(C6929R.styleable.TwoLevelHeader_srlFloorDuration, this.mFloorDuration);
        this.mEnableTwoLevel = obtainStyledAttributes.getBoolean(C6929R.styleable.TwoLevelHeader_srlEnableTwoLevel, this.mEnableTwoLevel);
        this.mEnablePullToCloseTwoLevel = obtainStyledAttributes.getBoolean(C6929R.styleable.TwoLevelHeader_srlEnablePullToCloseTwoLevel, this.mEnablePullToCloseTwoLevel);
        obtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            }
            View childAt = getChildAt(i);
            if (childAt instanceof RefreshHeader) {
                this.mRefreshHeader = (RefreshHeader) childAt;
                this.mWrappedInternal = (RefreshInternal) childAt;
                bringChildToFront(childAt);
                break;
            }
            i++;
        }
        if (this.mRefreshHeader == null) {
            setRefreshHeader(new ClassicsHeader(getContext()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mSpinnerStyle = SpinnerStyle.MatchLayout;
        if (this.mRefreshHeader == null) {
            setRefreshHeader(new ClassicsHeader(getContext()));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mSpinnerStyle = SpinnerStyle.FixedBehind;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null) {
            if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                refreshInternal.getView().measure(i, i2);
                super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i), refreshInternal.getView().getMeasuredHeight());
                return;
            }
            super.onMeasure(i, i2);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract
    public boolean equals(Object obj) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        return (refreshInternal != null && refreshInternal.equals(obj)) || super.equals(obj);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal == null) {
            return;
        }
        if (((i2 + i) * 1.0f) / i != this.mMaxRage && this.mHeaderHeight == 0) {
            this.mHeaderHeight = i;
            this.mRefreshHeader = null;
            refreshKernel.getRefreshLayout().setHeaderMaxDragRate(this.mMaxRage);
            this.mRefreshHeader = refreshInternal;
        }
        if (this.mRefreshKernel == null && refreshInternal.getSpinnerStyle() == SpinnerStyle.Translate && !isInEditMode()) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) refreshInternal.getView().getLayoutParams();
            marginLayoutParams.topMargin -= i;
            refreshInternal.getView().setLayoutParams(marginLayoutParams);
        }
        this.mHeaderHeight = i;
        this.mRefreshKernel = refreshKernel;
        refreshKernel.requestFloorDuration(this.mFloorDuration);
        refreshKernel.requestNeedTouchEventFor(this, !this.mEnablePullToCloseTwoLevel);
        refreshInternal.onInitialized(refreshKernel, i, i2);
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (refreshInternal != null) {
            refreshInternal.onStateChanged(refreshLayout, refreshState, refreshState2);
            switch (refreshState2) {
                case TwoLevelReleased:
                    if (refreshInternal.getView() != this) {
                        refreshInternal.getView().animate().alpha(0.0f).setDuration(this.mFloorDuration / 2);
                    }
                    RefreshKernel refreshKernel = this.mRefreshKernel;
                    if (refreshKernel != null) {
                        OnTwoLevelListener onTwoLevelListener = this.mTwoLevelListener;
                        refreshKernel.startTwoLevel(onTwoLevelListener == null || onTwoLevelListener.onTwoLevel(refreshLayout));
                        return;
                    }
                    return;
                case TwoLevel:
                default:
                    return;
                case TwoLevelFinish:
                    if (refreshInternal.getView() != this) {
                        refreshInternal.getView().animate().alpha(1.0f).setDuration(this.mFloorDuration / 2);
                        return;
                    }
                    return;
                case PullDownToRefresh:
                    if (refreshInternal.getView().getAlpha() != 0.0f || refreshInternal.getView() == this) {
                        return;
                    }
                    refreshInternal.getView().setAlpha(1.0f);
                    return;
            }
        }
    }

    @Override // com.scwang.smartrefresh.layout.internal.InternalAbstract, com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onMoving(boolean z, float f, int i, int i2, int i3) {
        moveSpinner(i);
        RefreshInternal refreshInternal = this.mRefreshHeader;
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshInternal != null) {
            refreshInternal.onMoving(z, f, i, i2, i3);
        }
        if (z) {
            float f2 = this.mPercent;
            float f3 = this.mFloorRage;
            if (f2 < f3 && f >= f3 && this.mEnableTwoLevel) {
                refreshKernel.setState(RefreshState.ReleaseToTwoLevel);
            } else if (this.mPercent >= this.mFloorRage && f < this.mRefreshRage) {
                refreshKernel.setState(RefreshState.PullDownToRefresh);
            } else {
                float f4 = this.mPercent;
                float f5 = this.mFloorRage;
                if (f4 >= f5 && f < f5) {
                    refreshKernel.setState(RefreshState.ReleaseToRefresh);
                }
            }
            this.mPercent = f;
        }
    }

    protected void moveSpinner(int i) {
        RefreshInternal refreshInternal = this.mRefreshHeader;
        if (this.mSpinner == i || refreshInternal == null) {
            return;
        }
        this.mSpinner = i;
        SpinnerStyle spinnerStyle = refreshInternal.getSpinnerStyle();
        if (spinnerStyle == SpinnerStyle.Translate) {
            refreshInternal.getView().setTranslationY(i);
        } else if (spinnerStyle == SpinnerStyle.Scale) {
            View view = refreshInternal.getView();
            view.layout(view.getLeft(), view.getTop(), view.getRight(), view.getTop() + Math.max(0, i));
        }
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    public TwoLevelHeader setRefreshHeader(RefreshHeader refreshHeader, int i, int i2) {
        if (refreshHeader != null) {
            RefreshInternal refreshInternal = this.mRefreshHeader;
            if (refreshInternal != null) {
                removeView(refreshInternal.getView());
            }
            if (refreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                addView(refreshHeader.getView(), 0, new RelativeLayout.LayoutParams(i, i2));
            } else {
                addView(refreshHeader.getView(), i, i2);
            }
            this.mRefreshHeader = refreshHeader;
            this.mWrappedInternal = refreshHeader;
        }
        return this;
    }

    public TwoLevelHeader setMaxRage(float f) {
        if (this.mMaxRage != f) {
            this.mMaxRage = f;
            RefreshKernel refreshKernel = this.mRefreshKernel;
            if (refreshKernel != null) {
                this.mHeaderHeight = 0;
                refreshKernel.getRefreshLayout().setHeaderMaxDragRate(this.mMaxRage);
            }
        }
        return this;
    }

    public TwoLevelHeader setEnablePullToCloseTwoLevel(boolean z) {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        this.mEnablePullToCloseTwoLevel = z;
        if (refreshKernel != null) {
            refreshKernel.requestNeedTouchEventFor(this, !z);
        }
        return this;
    }

    public TwoLevelHeader setFloorRage(float f) {
        this.mFloorRage = f;
        return this;
    }

    public TwoLevelHeader setRefreshRage(float f) {
        this.mRefreshRage = f;
        return this;
    }

    public TwoLevelHeader setEnableTwoLevel(boolean z) {
        this.mEnableTwoLevel = z;
        return this;
    }

    public TwoLevelHeader setFloorDuration(int i) {
        this.mFloorDuration = i;
        return this;
    }

    public TwoLevelHeader setOnTwoLevelListener(OnTwoLevelListener onTwoLevelListener) {
        this.mTwoLevelListener = onTwoLevelListener;
        return this;
    }

    public TwoLevelHeader finishTwoLevel() {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            refreshKernel.finishTwoLevel();
        }
        return this;
    }

    public TwoLevelHeader openTwoLevel(boolean z) {
        RefreshKernel refreshKernel = this.mRefreshKernel;
        if (refreshKernel != null) {
            OnTwoLevelListener onTwoLevelListener = this.mTwoLevelListener;
            refreshKernel.startTwoLevel(!z || onTwoLevelListener == null || onTwoLevelListener.onTwoLevel(refreshKernel.getRefreshLayout()));
        }
        return this;
    }
}
