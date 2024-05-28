package com.app.hubert.guide.model;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import com.app.hubert.guide.model.HighLight;
import com.app.hubert.guide.util.LogUtil;
import com.app.hubert.guide.util.ViewUtils;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class HighlightView implements HighLight {
    private View mHole;
    private HighlightOptions options;
    private int padding;
    private RectF rectF;
    private int round;
    private HighLight.Shape shape;

    public HighlightView(View view, HighLight.Shape shape, int i, int i2) {
        this.mHole = view;
        this.shape = shape;
        this.round = i;
        this.padding = i2;
    }

    public void setOptions(HighlightOptions highlightOptions) {
        this.options = highlightOptions;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public HighLight.Shape getShape() {
        return this.shape;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public int getRound() {
        return this.round;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public HighlightOptions getOptions() {
        return this.options;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public float getRadius() {
        View view = this.mHole;
        if (view == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        return Math.max(view.getWidth() / 2, this.mHole.getHeight() / 2) + this.padding;
    }

    @Override // com.app.hubert.guide.model.HighLight
    public RectF getRectF(View view) {
        if (this.mHole == null) {
            throw new IllegalArgumentException("the highlight view is null!");
        }
        if (this.rectF == null) {
            this.rectF = fetchLocation(view);
        } else {
            HighlightOptions highlightOptions = this.options;
            if (highlightOptions != null && highlightOptions.fetchLocationEveryTime) {
                this.rectF = fetchLocation(view);
            }
        }
        LogUtil.m20451i(this.mHole.getClass().getSimpleName() + "'s location:" + this.rectF);
        return this.rectF;
    }

    private RectF fetchLocation(View view) {
        RectF rectF = new RectF();
        Rect locationInView = ViewUtils.getLocationInView(view, this.mHole);
        rectF.left = locationInView.left - this.padding;
        rectF.top = locationInView.top - this.padding;
        rectF.right = locationInView.right + this.padding;
        rectF.bottom = locationInView.bottom + this.padding;
        return rectF;
    }
}
