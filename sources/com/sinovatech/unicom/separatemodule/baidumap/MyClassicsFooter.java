package com.sinovatech.unicom.separatemodule.baidumap;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.util.SmartUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyClassicsFooter extends LinearLayout implements RefreshFooter {
    private ProgressDrawable mProgressDrawable;
    private ImageView mProgressView;

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    @NonNull
    public View getView() {
        return this;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onHorizontalDrag(float f, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onInitialized(@NonNull RefreshKernel refreshKernel, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onMoving(boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onReleased(@NonNull RefreshLayout refreshLayout, int i, int i2) {
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshFooter
    public boolean setNoMoreData(boolean z) {
        return false;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void setPrimaryColors(int... iArr) {
    }

    public MyClassicsFooter(Context context) {
        this(context, null);
    }

    public MyClassicsFooter(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MyClassicsFooter(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView(context);
    }

    private void initView(Context context) {
        setGravity(17);
        this.mProgressDrawable = new ProgressDrawable();
        this.mProgressView = new ImageView(context);
        this.mProgressView.setImageDrawable(this.mProgressDrawable);
        addView(this.mProgressView, SmartUtil.dp2px(20.0f), SmartUtil.dp2px(20.0f));
        addView(new Space(context), SmartUtil.dp2px(20.0f), SmartUtil.dp2px(20.0f));
        setMinimumHeight(SmartUtil.dp2px(60.0f));
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    @NonNull
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int i, int i2) {
        this.mProgressDrawable.start();
    }

    @Override // com.scwang.smartrefresh.layout.api.RefreshInternal
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean z) {
        this.mProgressDrawable.stop();
        return 500;
    }

    @Override // com.scwang.smartrefresh.layout.listener.OnStateChangedListener
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState refreshState, @NonNull RefreshState refreshState2) {
        switch (refreshState2) {
            case PullDownToRefresh:
            case Refreshing:
            default:
                return;
            case ReleaseToRefresh:
                this.mProgressView.setVisibility(0);
                return;
        }
    }
}
