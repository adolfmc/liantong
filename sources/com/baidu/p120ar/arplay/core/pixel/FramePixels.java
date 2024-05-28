package com.baidu.p120ar.arplay.core.pixel;

import com.baidu.p120ar.arplay.core.engine.rotate.Orientation;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.renderer.ARPRenderer;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.pixel.FramePixels */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FramePixels {
    private int mHeight;
    private Orientation mOrientation;
    private PixelType mPixelType;
    private ByteBuffer mPixelsAddress;
    private long mTimestamp;
    private int mWidth;
    private boolean mCameraFrame = false;
    private boolean mFrontCamera = false;
    private int mDegree = 90;
    private int mPixelLength = 0;
    private int mTextureID = -1;
    private PixelReadParams.FrameType mFrameType = PixelReadParams.FrameType.STREAM_FRAME;

    public FramePixels(PixelType pixelType, ByteBuffer byteBuffer, int i, int i2) {
        this.mPixelType = pixelType;
        this.mWidth = i;
        this.mHeight = i2;
        this.mPixelsAddress = byteBuffer;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public PixelType getPixelType() {
        return this.mPixelType;
    }

    public void setPixelType(PixelType pixelType) {
        this.mPixelType = pixelType;
    }

    public ByteBuffer getPixelsAddress() {
        return this.mPixelsAddress;
    }

    public void setPixelsAddress(ByteBuffer byteBuffer) {
        this.mPixelsAddress = byteBuffer;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public void setWidth(int i) {
        this.mWidth = i;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public void setHeight(int i) {
        this.mHeight = i;
    }

    public boolean isCameraFrame() {
        return this.mCameraFrame;
    }

    public void setCameraFrame(boolean z) {
        this.mCameraFrame = z;
    }

    public boolean isFrontCamera() {
        return this.mFrontCamera;
    }

    public void setFrontCamera(boolean z) {
        this.mFrontCamera = z;
    }

    public int getDegree() {
        return this.mDegree;
    }

    public void setDegree(int i) {
        this.mDegree = i;
    }

    public Orientation getOrientation() {
        return this.mOrientation;
    }

    public void setOrientation(Orientation orientation) {
        this.mOrientation = orientation;
    }

    public void setTextureID(int i) {
        this.mTextureID = i;
    }

    public int getTextureID() {
        return this.mTextureID;
    }

    public void setFrameType(PixelReadParams.FrameType frameType) {
        this.mFrameType = frameType;
    }

    public PixelReadParams.FrameType getFrameType() {
        return this.mFrameType;
    }

    public PixelRotation getSegOrientation() {
        PixelRotation pixelRotation = PixelRotation.NoRotation;
        switch (this.mOrientation) {
            case PORTRAIT:
                return isFrontCamera() ? PixelRotation.FlipHorizontal : PixelRotation.NoRotation;
            case PORTRAIT_REVERSE:
                return isFrontCamera() ? PixelRotation.FlipVertical : PixelRotation.Rotate180;
            case LANDSCAPE:
                return isFrontCamera() ? PixelRotation.RotateRightFlipVertical : PixelRotation.RotateLeft;
            case LANDSCAPE_REVERSE:
                return isFrontCamera() ? PixelRotation.RotateRightFlipHorizontal : PixelRotation.RotateRight;
            default:
                return isFrontCamera() ? PixelRotation.FlipHorizontal : PixelRotation.NoRotation;
        }
    }

    public byte[] getPixelData() {
        int i = this.mPixelLength;
        byte[] bArr = new byte[i];
        ARPRenderer.copyNativeBytebuffer(this.mPixelsAddress, bArr, 0, i);
        return bArr;
    }

    public void setPixelLength(int i) {
        this.mPixelLength = i;
    }

    public int getPixelLength() {
        return this.mPixelLength;
    }
}
