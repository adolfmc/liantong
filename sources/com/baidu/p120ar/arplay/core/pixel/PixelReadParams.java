package com.baidu.p120ar.arplay.core.pixel;

import android.graphics.Rect;
import com.baidu.p120ar.arplay.core.renderer.ARPRenderer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.PixelReadParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PixelReadParams {
    private static final int DEFAULT_CACHE_SIZE = 3;
    public static final String DEFAULT_FILTER_ID = "camera";
    private static final int DEFAULT_ROTATE_DEGREE = 0;
    private static final ScaleType DEFAULT_SCALE_TYPE = ScaleType.FIT_XY;
    public static final String TERMINAL_FILTER_ID = "target";
    private int mOutputHeight;
    private int mOutputWidth;
    private PixelType mPixelType;
    private Rect mReadRect;
    private ScaleType mScaleType = DEFAULT_SCALE_TYPE;
    private PixelRotation mPixelRotation = PixelRotation.NoRotation;
    private int mCacheSize = 3;
    private boolean mIsPortrait = false;
    private String mPreFilterID = "";
    private FrameType mFrameType = FrameType.STREAM_FRAME;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.pixel.PixelReadParams$ScaleType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ScaleType {
        FIT_XY,
        FIT_CENTER,
        CENTER_CROP,
        EQUAL_SCALE
    }

    public PixelReadParams(PixelType pixelType) {
        this.mPixelType = pixelType;
    }

    public PixelType getPixelType() {
        return this.mPixelType;
    }

    public void setPixelType(PixelType pixelType) {
        this.mPixelType = pixelType;
    }

    public int getOutputWidth() {
        return this.mOutputWidth;
    }

    public void setOutputWidth(int i) {
        this.mOutputWidth = i;
    }

    public int getOutputHeight() {
        return this.mOutputHeight;
    }

    public void setOutputHeight(int i) {
        this.mOutputHeight = i;
    }

    public int getAlgoWidth() {
        if (ARPRenderer.needRotate(this.mPixelRotation.getValue())) {
            return this.mOutputHeight;
        }
        return this.mOutputWidth;
    }

    public int getAlgoHeight() {
        if (ARPRenderer.needRotate(this.mPixelRotation.getValue())) {
            return this.mOutputWidth;
        }
        return this.mOutputHeight;
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public PixelRotation getPixelRotate() {
        return this.mPixelRotation;
    }

    public void setPixelRotate(PixelRotation pixelRotation) {
        this.mPixelRotation = pixelRotation;
    }

    public Rect getReadRect() {
        return this.mReadRect;
    }

    public void setReadRect(Rect rect) {
        this.mReadRect = rect;
    }

    public int getCacheSize() {
        return this.mCacheSize;
    }

    public void setCacheSize(int i) {
        this.mCacheSize = i;
    }

    public void setPreFilterID(String str) {
        this.mPreFilterID = str;
    }

    public String getPreFilterID() {
        return this.mPreFilterID;
    }

    public void setFrameType(FrameType frameType) {
        this.mFrameType = frameType;
    }

    public FrameType getFrameType() {
        return this.mFrameType;
    }

    public void setIsPortrait(boolean z) {
        this.mIsPortrait = z;
    }

    public boolean getIsPortrait() {
        return this.mIsPortrait;
    }

    public boolean equals(Object obj) {
        Rect rect;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PixelReadParams pixelReadParams = (PixelReadParams) obj;
        return this.mPixelType == pixelReadParams.mPixelType && this.mOutputWidth == pixelReadParams.mOutputWidth && this.mOutputHeight == pixelReadParams.mOutputHeight && this.mPixelRotation == pixelReadParams.mPixelRotation && this.mScaleType == pixelReadParams.mScaleType && ((this.mReadRect == null && pixelReadParams.mReadRect == null) || ((rect = this.mReadRect) != null && rect.equals(pixelReadParams.mReadRect)));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.pixel.PixelReadParams$FrameType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum FrameType {
        STREAM_FRAME(0),
        SINGLE_FRAME(1);
        
        private final int value;

        FrameType(int i) {
            this.value = i;
        }

        public static FrameType valueOf(int i) {
            if (i == STREAM_FRAME.getValue()) {
                return STREAM_FRAME;
            }
            if (i == SINGLE_FRAME.getValue()) {
                return SINGLE_FRAME;
            }
            return STREAM_FRAME;
        }

        public int getValue() {
            return this.value;
        }
    }
}
