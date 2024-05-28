package com.app.hubert.guide.model;

import android.graphics.RectF;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.util.LogUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RelativeGuide {
    public int gravity;
    public HighLight highLight;
    @LayoutRes
    public int layout;
    public int padding;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    @interface LimitGravity {
    }

    protected void offsetMargin(MarginInfo marginInfo, ViewGroup viewGroup, View view) {
    }

    @Deprecated
    protected void onLayoutInflated(View view) {
    }

    protected void onLayoutInflated(View view, Controller controller) {
    }

    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class MarginInfo {
        public int bottomMargin;
        public int gravity;
        public int leftMargin;
        public int rightMargin;
        public int topMargin;

        public String toString() {
            return "MarginInfo{leftMargin=" + this.leftMargin + ", topMargin=" + this.topMargin + ", rightMargin=" + this.rightMargin + ", bottomMargin=" + this.bottomMargin + ", gravity=" + this.gravity + '}';
        }
    }

    public RelativeGuide(@LayoutRes int i, int i2) {
        this.layout = i;
        this.gravity = i2;
    }

    public RelativeGuide(@LayoutRes int i, int i2, int i3) {
        this.layout = i;
        this.gravity = i2;
        this.padding = i3;
    }

    public final View getGuideLayout(ViewGroup viewGroup, Controller controller) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.layout, viewGroup, false);
        onLayoutInflated(inflate);
        onLayoutInflated(inflate, controller);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) inflate.getLayoutParams();
        MarginInfo marginInfo = getMarginInfo(this.gravity, viewGroup, inflate);
        LogUtil.m20453e(marginInfo.toString());
        offsetMargin(marginInfo, viewGroup, inflate);
        layoutParams.gravity = marginInfo.gravity;
        layoutParams.leftMargin += marginInfo.leftMargin;
        layoutParams.topMargin += marginInfo.topMargin;
        layoutParams.rightMargin += marginInfo.rightMargin;
        layoutParams.bottomMargin += marginInfo.bottomMargin;
        inflate.setLayoutParams(layoutParams);
        return inflate;
    }

    private MarginInfo getMarginInfo(int i, ViewGroup viewGroup, View view) {
        MarginInfo marginInfo = new MarginInfo();
        RectF rectF = this.highLight.getRectF(viewGroup);
        if (i == 3) {
            marginInfo.gravity = 5;
            marginInfo.rightMargin = (int) ((viewGroup.getWidth() - rectF.left) + this.padding);
            marginInfo.topMargin = (int) rectF.top;
        } else if (i == 5) {
            marginInfo.leftMargin = (int) (rectF.right + this.padding);
            marginInfo.topMargin = (int) rectF.top;
        } else if (i == 48) {
            marginInfo.gravity = 80;
            marginInfo.bottomMargin = (int) ((viewGroup.getHeight() - rectF.top) + this.padding);
            marginInfo.leftMargin = (int) rectF.left;
        } else if (i == 80) {
            marginInfo.topMargin = (int) (rectF.bottom + this.padding);
            marginInfo.leftMargin = (int) rectF.left;
        }
        return marginInfo;
    }
}
