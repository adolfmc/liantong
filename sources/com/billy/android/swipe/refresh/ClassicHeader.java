package com.billy.android.swipe.refresh;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.billy.android.swipe.C3336R;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeRefresh;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ClassicHeader extends RelativeLayout implements SmartSwipeRefresh.SmartSwipeRefreshHeader {
    public ObjectAnimator animator;
    public ImageView mProgressImageView;
    public int mStrResId;
    public TextView mTitleTextView;

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public View getView() {
        return this;
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onReset() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onStartDragging() {
    }

    public ClassicHeader(Context context) {
        super(context);
        if (isInEditMode()) {
            onInit(false);
        }
    }

    public ClassicHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (isInEditMode()) {
            onInit(false);
        }
    }

    public ClassicHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (isInEditMode()) {
            onInit(false);
        }
    }

    @TargetApi(21)
    public ClassicHeader(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (isInEditMode()) {
            onInit(false);
        }
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onInit(boolean z) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (z) {
            LayoutInflater.from(getContext()).inflate(C3336R.C3338layout.ssr_classic_header_footer_horizontal, this);
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(SmartSwipe.dp2px(60, getContext()), -1);
            }
        } else {
            LayoutInflater.from(getContext()).inflate(C3336R.C3338layout.ssr_classic_header_footer, this);
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-1, SmartSwipe.dp2px(60, getContext()));
            }
        }
        setLayoutParams(layoutParams);
        if (getBackground() == null) {
            setBackgroundColor(-1118482);
        }
        this.mProgressImageView = (ImageView) findViewById(C3336R.C3337id.ssr_classics_progress);
        this.mProgressImageView.setVisibility(8);
        this.mTitleTextView = (TextView) findViewById(C3336R.C3337id.ssr_classics_title);
        this.mTitleTextView.setText(C3336R.string.ssr_header_pulling);
        this.animator = ObjectAnimator.ofFloat(this.mProgressImageView, "rotation", 0.0f, 3600.0f);
        this.animator.setDuration(5000L);
        this.animator.setInterpolator(null);
        this.animator.setRepeatCount(-1);
        this.animator.setRepeatMode(1);
    }

    public void cancelAnimation() {
        this.animator.cancel();
        this.mProgressImageView.setVisibility(8);
    }

    public void showAnimation() {
        this.animator.start();
        this.mProgressImageView.setVisibility(0);
    }

    public void onProgress(boolean z, float f) {
        if (z) {
            setText(f >= 1.0f ? C3336R.string.ssr_header_release : C3336R.string.ssr_header_pulling);
        } else if (f <= 0.0f) {
            cancelAnimation();
        }
    }

    public long onFinish(boolean z) {
        cancelAnimation();
        setText(z ? C3336R.string.ssr_header_finish : C3336R.string.ssr_header_failed);
        return 500L;
    }

    public void onDataLoading() {
        showAnimation();
        setText(C3336R.string.ssr_footer_refreshing);
    }

    public void setText(int i) {
        TextView textView;
        if (this.mStrResId == i || (textView = this.mTitleTextView) == null) {
            return;
        }
        this.mStrResId = i;
        textView.setText(i);
    }
}
