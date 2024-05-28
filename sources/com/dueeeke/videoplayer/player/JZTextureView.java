package com.dueeeke.videoplayer.player;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JZTextureView extends TextureView {
    protected static final String TAG = "JZResizeTextureView";
    public int currentVideoHeight;
    public int currentVideoWidth;

    public JZTextureView(Context context) {
        super(context);
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
    }

    public JZTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
        this.currentVideoWidth = 0;
        this.currentVideoHeight = 0;
    }

    public void setVideoSize(int i, int i2) {
        if (this.currentVideoWidth == i && this.currentVideoHeight == i2) {
            return;
        }
        this.currentVideoWidth = i;
        this.currentVideoHeight = i2;
        requestLayout();
    }

    @Override // android.view.View
    public void setRotation(float f) {
        if (f != getRotation()) {
            super.setRotation(f);
            requestLayout();
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        Log.i(TAG, "onMeasure  [" + hashCode() + "] ");
        int rotation = (int) getRotation();
        int i8 = this.currentVideoWidth;
        int i9 = this.currentVideoHeight;
        int measuredHeight = ((View) getParent()).getMeasuredHeight();
        int measuredWidth = ((View) getParent()).getMeasuredWidth();
        if (measuredWidth != 0 && measuredHeight != 0 && i8 != 0 && i9 != 0) {
            if (rotation == 90 || rotation == 270) {
                measuredWidth = measuredHeight;
                measuredHeight = measuredWidth;
            }
            i9 = (i8 * measuredHeight) / measuredWidth;
        }
        if (rotation == 90 || rotation == 270) {
            i3 = i;
            i4 = i2;
        } else {
            i4 = i;
            i3 = i2;
        }
        int defaultSize = getDefaultSize(i8, i4);
        int defaultSize2 = getDefaultSize(i9, i3);
        if (i8 <= 0 || i9 <= 0) {
            i5 = defaultSize2;
            i6 = defaultSize;
        } else {
            int mode = View.MeasureSpec.getMode(i4);
            i6 = View.MeasureSpec.getSize(i4);
            int mode2 = View.MeasureSpec.getMode(i3);
            i5 = View.MeasureSpec.getSize(i3);
            Log.i(TAG, "widthMeasureSpec  [" + View.MeasureSpec.toString(i4) + "]");
            Log.i(TAG, "heightMeasureSpec [" + View.MeasureSpec.toString(i3) + "]");
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i10 = i8 * i5;
                int i11 = i6 * i9;
                if (i10 < i11) {
                    i6 = i10 / i9;
                } else if (i10 > i11) {
                    i5 = i11 / i8;
                }
            } else if (mode == 1073741824) {
                int i12 = (i6 * i9) / i8;
                if (mode2 != Integer.MIN_VALUE || i12 <= i5) {
                    i5 = i12;
                } else {
                    i6 = (i5 * i8) / i9;
                }
            } else if (mode2 == 1073741824) {
                i7 = (i5 * i8) / i9;
                if (mode == Integer.MIN_VALUE && i7 > i6) {
                    i5 = (i6 * i9) / i8;
                }
                i6 = i7;
            } else {
                if (mode2 != Integer.MIN_VALUE || i9 <= i5) {
                    i7 = i8;
                    i5 = i9;
                } else {
                    i7 = (i5 * i8) / i9;
                }
                if (mode == Integer.MIN_VALUE && i7 > i6) {
                    i5 = (i6 * i9) / i8;
                }
                i6 = i7;
            }
        }
        if (rotation == 90 || rotation == 270) {
            int i13 = measuredWidth;
            measuredWidth = measuredHeight;
            measuredHeight = i13;
        }
        double d = i9 / i8;
        double d2 = measuredHeight;
        double d3 = measuredWidth;
        double d4 = d2 / d3;
        if (d > d4) {
            i5 = (int) ((d3 / i6) * i5);
            i6 = measuredWidth;
        } else if (d < d4) {
            i6 = (int) ((d2 / i5) * i6);
            i5 = measuredHeight;
        }
        setMeasuredDimension(i6, i5);
    }
}
