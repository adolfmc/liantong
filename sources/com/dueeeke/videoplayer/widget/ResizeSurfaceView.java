package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ResizeSurfaceView extends SurfaceView {
    private int mVideoHeight;
    private int mVideoWidth;
    private int screenType;

    public ResizeSurfaceView(Context context) {
        super(context);
    }

    public ResizeSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        getHolder().setFixedSize(i, i2);
    }

    public void setScreenScale(int i) {
        this.screenType = i;
        requestLayout();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = getDefaultSize(this.mVideoHeight, i2);
        switch (this.screenType) {
            case 1:
                i2 = (defaultSize / 16) * 9;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 9) * 16;
                    i2 = defaultSize2;
                    break;
                } else {
                    i = defaultSize;
                    break;
                }
            case 2:
                i2 = (defaultSize / 4) * 3;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 3) * 4;
                    i2 = defaultSize2;
                    break;
                } else {
                    i = defaultSize;
                    break;
                }
            case 3:
                break;
            case 4:
                i = this.mVideoWidth;
                i2 = this.mVideoHeight;
                break;
            default:
                if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                    int mode = View.MeasureSpec.getMode(i);
                    i = View.MeasureSpec.getSize(i);
                    int mode2 = View.MeasureSpec.getMode(i2);
                    i2 = View.MeasureSpec.getSize(i2);
                    if (mode != 1073741824 || mode2 != 1073741824) {
                        if (mode != 1073741824) {
                            if (mode2 == 1073741824) {
                                int i3 = (this.mVideoWidth * i2) / this.mVideoHeight;
                                if (mode != Integer.MIN_VALUE || i3 <= i) {
                                    i = i3;
                                    break;
                                }
                            } else {
                                int i4 = this.mVideoWidth;
                                int i5 = this.mVideoHeight;
                                if (mode2 != Integer.MIN_VALUE || i5 <= i2) {
                                    i2 = i5;
                                } else {
                                    i4 = (i4 * i2) / i5;
                                }
                                if (mode == Integer.MIN_VALUE && i4 > i) {
                                    i2 = (this.mVideoHeight * i) / this.mVideoWidth;
                                    break;
                                } else {
                                    i = i4;
                                    break;
                                }
                            }
                        } else {
                            int i6 = (this.mVideoHeight * i) / this.mVideoWidth;
                            if (mode2 != Integer.MIN_VALUE || i6 <= i2) {
                                i2 = i6;
                                break;
                            }
                        }
                    } else {
                        int i7 = this.mVideoWidth;
                        int i8 = i7 * i2;
                        int i9 = this.mVideoHeight;
                        if (i8 >= i * i9) {
                            if (i7 * i2 > i * i9) {
                                i2 = (i9 * i) / i7;
                                break;
                            }
                        } else {
                            i = (i7 * i2) / i9;
                            break;
                        }
                    }
                } else {
                    i = defaultSize;
                    i2 = defaultSize2;
                    break;
                }
                break;
        }
        setMeasuredDimension(i, i2);
    }
}
