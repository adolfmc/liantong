package com.billy.android.swipe.consumer;

import android.view.View;
import com.billy.android.swipe.SmartSwipe;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SlidingConsumer extends DrawerConsumer {
    public static final float FACTOR_COVER = 0.0f;
    public static final float FACTOR_FOLLOW = 1.0f;
    protected boolean mDrawerExpandable;
    protected int mDrawerH;
    protected int mDrawerW;
    protected boolean mEdgeAffinity;
    protected float mRelativeMoveFactor = 0.5f;

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDetachFromWrapper() {
        View[] viewArr;
        super.onDetachFromWrapper();
        for (View view : this.mDrawerViews) {
            if (view != null) {
                view.scrollTo(0, 0);
            }
        }
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            contentView.layout(0, 0, this.mWidth, this.mHeight);
        }
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    protected void calculateDrawerDirectionInitPosition(int i, int i2, int i3) {
        this.mDrawerW = i2;
        this.mDrawerH = i3;
        int i4 = (int) ((this.mOpenDistance * (1.0f - this.mRelativeMoveFactor)) + 0.5f);
        if (i == 4) {
            this.f8212l = 0;
            this.f8213r = this.mWidth;
            this.f8214t = (-i3) + i4;
            if (this.mEdgeAffinity && this.f8214t > 0) {
                this.f8214t = 0;
            }
            this.f8211b = this.f8214t + i3;
        } else if (i != 8) {
            switch (i) {
                case 1:
                    this.f8212l = (-i2) + i4;
                    if (this.mEdgeAffinity && this.f8212l > 0) {
                        this.f8212l = 0;
                    }
                    this.f8213r = this.f8212l + i2;
                    this.f8214t = 0;
                    this.f8211b = i3;
                    return;
                case 2:
                    this.f8212l = this.mWidth - i4;
                    this.f8213r = this.f8212l + i2;
                    this.f8214t = 0;
                    this.f8211b = i3;
                    if (!this.mEdgeAffinity || this.f8213r >= this.mWidth) {
                        return;
                    }
                    this.f8213r = this.mWidth;
                    this.f8212l = this.f8213r - i2;
                    return;
                default:
                    return;
            }
        } else {
            this.f8212l = 0;
            this.f8213r = this.mWidth;
            this.f8214t = this.mHeight - i4;
            this.f8211b = this.f8214t + i3;
            if (!this.mEdgeAffinity || this.f8211b >= this.mHeight) {
                return;
            }
            this.f8211b = this.mHeight;
            this.f8214t = this.f8211b - i3;
        }
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer, com.billy.android.swipe.SwipeConsumer
    public void onDisplayDistanceChanged(int i, int i2, int i3, int i4) {
        layoutChildren();
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    protected void orderChildren() {
        View contentView = this.mWrapper.getContentView();
        if (contentView != null) {
            contentView.bringToFront();
        }
        if (this.mScrimView != null) {
            this.mScrimView.bringToFront();
        }
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    protected void layoutContentView(View view) {
        if (view != null) {
            view.layout(this.mCurDisplayDistanceX, this.mCurDisplayDistanceY, this.mWidth + this.mCurDisplayDistanceX, this.mHeight + this.mCurDisplayDistanceY);
        }
    }

    @Override // com.billy.android.swipe.consumer.DrawerConsumer
    protected void layoutDrawerView() {
        int i;
        int i2;
        int i3;
        View contentView = this.mWrapper.getContentView();
        View view = this.mCurDrawerView;
        if (contentView == null || view == null || view.getVisibility() != 0) {
            return;
        }
        int i4 = (int) ((this.mCurDisplayDistanceX * this.mRelativeMoveFactor) + (this.mCurDisplayDistanceX > 0 ? 0.5f : -0.5f));
        int i5 = (int) ((this.mCurDisplayDistanceY * this.mRelativeMoveFactor) + (this.mCurDisplayDistanceY <= 0 ? -0.5f : 0.5f));
        int i6 = this.f8212l;
        int i7 = this.f8214t;
        int i8 = this.f8213r;
        int i9 = this.f8211b;
        int i10 = this.mDirection;
        int i11 = 0;
        if (i10 == 4) {
            i7 = this.f8214t + i5;
            i9 = contentView.getTop();
            if (!this.mDrawerExpandable) {
                if (!this.mEdgeAffinity && i9 > (i2 = this.mDrawerH)) {
                    i7 = i9 - i2;
                } else if (this.mEdgeAffinity && i7 > 0) {
                    i7 = 0;
                }
                int i12 = i9 - i7;
                int i13 = this.mDrawerH;
                if (i12 > i13) {
                    i9 = i7 + i13;
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (i7 > 0) {
                i = 0;
                i7 = 0;
            } else {
                i = 0;
            }
        } else if (i10 != 8) {
            switch (i10) {
                case 1:
                    i6 = this.f8212l + i4;
                    i8 = contentView.getLeft();
                    if (!this.mDrawerExpandable) {
                        if (!this.mEdgeAffinity && i8 > (i3 = this.mDrawerW)) {
                            i6 = i8 - i3;
                        } else if (this.mEdgeAffinity && i6 > 0) {
                            i6 = 0;
                        }
                        int i14 = i8 - i6;
                        int i15 = this.mDrawerW;
                        if (i14 <= i15) {
                            i = 0;
                            break;
                        } else {
                            i8 = i6 + i15;
                            i = 0;
                            break;
                        }
                    } else if (i6 <= 0) {
                        i = 0;
                        break;
                    } else {
                        i = 0;
                        i6 = 0;
                        break;
                    }
                    break;
                case 2:
                    int right = contentView.getRight();
                    int i16 = this.f8213r + i4;
                    if (!this.mDrawerExpandable) {
                        if (!this.mEdgeAffinity && this.mDrawerW + right < this.mWidth) {
                            i16 = right + this.mDrawerW;
                        } else if (this.mEdgeAffinity && i16 < this.mWidth) {
                            i16 = this.mWidth;
                        }
                        int i17 = i16 - right;
                        int i18 = this.mDrawerW;
                        if (i17 > i18) {
                            i6 = i16 - i18;
                            i8 = i16;
                        } else {
                            i6 = right;
                            i8 = i16;
                        }
                    } else if (i16 < this.mWidth) {
                        i6 = right;
                        i8 = this.mWidth;
                    } else {
                        i6 = right;
                        i8 = i16;
                    }
                    i11 = Math.max((int) ((this.mOpenDistance + this.mCurDisplayDistanceX) * (1.0f - this.mRelativeMoveFactor)), 0);
                    i = 0;
                    break;
                default:
                    i = 0;
                    break;
            }
        } else {
            int bottom = contentView.getBottom();
            int i19 = this.f8211b + i5;
            if (!this.mDrawerExpandable) {
                if (!this.mEdgeAffinity && this.mDrawerH + bottom < this.mHeight) {
                    i19 = this.mDrawerH + bottom;
                } else if (this.mEdgeAffinity && i19 < this.mHeight) {
                    i19 = this.mHeight;
                }
                int i20 = i19 - bottom;
                int i21 = this.mDrawerH;
                if (i20 > i21) {
                    i7 = i19 - i21;
                    i9 = i19;
                } else {
                    i7 = bottom;
                    i9 = i19;
                }
            } else if (i19 < this.mHeight) {
                i7 = bottom;
                i9 = this.mHeight;
            } else {
                i7 = bottom;
                i9 = i19;
            }
            i = Math.max((int) ((this.mOpenDistance + this.mCurDisplayDistanceY) * (1.0f - this.mRelativeMoveFactor)), 0);
        }
        view.layout(i6, i7, i8, i9);
        view.scrollTo(i11, i);
    }

    public float getRelativeMoveFactor() {
        return this.mRelativeMoveFactor;
    }

    public SlidingConsumer setRelativeMoveFactor(float f) {
        this.mRelativeMoveFactor = SmartSwipe.ensureBetween(f, 0.0f, 1.0f);
        return this;
    }

    public boolean isEdgeAffinity() {
        return this.mEdgeAffinity;
    }

    public SlidingConsumer setEdgeAffinity(boolean z) {
        this.mEdgeAffinity = z;
        return this;
    }

    public boolean isDrawerExpandable() {
        return this.mDrawerExpandable;
    }

    public SlidingConsumer setDrawerExpandable(boolean z) {
        this.mDrawerExpandable = z;
        return this;
    }
}
