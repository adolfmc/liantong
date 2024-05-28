package com.liang.jtablayout.indicator;

import android.graphics.drawable.Drawable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class Indicator {
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private boolean transitionScroll;
    private float widthScale = 0.5f;
    private int width = 0;
    private int height = 0;
    private int color = 0;
    private int animationDuration = 0;
    private int margin = 0;
    private int gravity = 0;

    public abstract Drawable getIndicator();

    public int getGravity() {
        return this.gravity;
    }

    public Indicator setGravity(int i) {
        this.gravity = i;
        return this;
    }

    public float getWidthScale() {
        return this.widthScale;
    }

    public Indicator setWidthScale(float f) {
        this.widthScale = f;
        return this;
    }

    public int getWidth() {
        return this.width;
    }

    public Indicator setWidth(int i) {
        this.width = i;
        return this;
    }

    public int getHeight() {
        return this.height;
    }

    public Indicator setHeight(int i) {
        this.height = i;
        return this;
    }

    public int getColor() {
        return this.color;
    }

    public Indicator setColor(int i) {
        this.color = i;
        return this;
    }

    public int getAnimationDuration() {
        return this.animationDuration;
    }

    public Indicator setAnimationDuration(int i) {
        this.animationDuration = i;
        return this;
    }

    public int getMargin() {
        return this.margin;
    }

    public Indicator setMargin(int i) {
        this.margin = i;
        return this;
    }

    public boolean isTransitionScroll() {
        return this.transitionScroll;
    }

    public Indicator setTransitionScroll(boolean z) {
        this.transitionScroll = z;
        return this;
    }

    public boolean isFullWidth() {
        return this.widthScale <= 0.0f && this.width <= 0;
    }
}
