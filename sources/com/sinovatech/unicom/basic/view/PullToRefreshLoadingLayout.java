package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PullToRefreshLoadingLayout extends FrameLayout {
    static final int DEFAULT_ROTATION_ANIMATION_DURATION = 150;
    private final ImageView headerImage;
    private final ProgressBar headerProgress;
    private final TextView headerText;
    private String pullLabel;
    private String refreshingLabel;
    private String releaseLabel;
    private final Animation resetRotateAnimation;
    private final Animation rotateAnimation;

    public void pullToRefresh() {
    }

    public void refreshing() {
    }

    public void releaseToRefresh() {
    }

    public void reset() {
    }

    public PullToRefreshLoadingLayout(Context context, int i, String str, String str2, String str3) {
        super(context);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(2131493402, this);
        this.headerText = (TextView) viewGroup.findViewById(2131298241);
        this.headerImage = (ImageView) viewGroup.findViewById(2131298239);
        this.headerProgress = (ProgressBar) viewGroup.findViewById(2131298240);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        this.rotateAnimation = new RotateAnimation(0.0f, -180.0f, 1, 0.5f, 1, 0.5f);
        this.rotateAnimation.setInterpolator(linearInterpolator);
        this.rotateAnimation.setDuration(150L);
        this.rotateAnimation.setFillAfter(true);
        this.resetRotateAnimation = new RotateAnimation(-180.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        this.resetRotateAnimation.setInterpolator(linearInterpolator);
        this.resetRotateAnimation.setDuration(150L);
        this.resetRotateAnimation.setFillAfter(true);
        this.releaseLabel = str;
        this.pullLabel = str2;
        this.refreshingLabel = str3;
    }

    public void setPullLabel(String str) {
        this.pullLabel = str;
    }

    public void setRefreshingLabel(String str) {
        this.refreshingLabel = str;
    }

    public void setReleaseLabel(String str) {
        this.releaseLabel = str;
    }

    public void setTextColor(int i) {
        this.headerText.setTextColor(i);
    }
}
