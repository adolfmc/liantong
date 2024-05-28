package com.baidu.p120ar;

import android.graphics.SurfaceTexture;
import com.baidu.p120ar.arrender.Texture;
import com.baidu.p120ar.bean.MirriorType;
import com.baidu.p120ar.bean.RotationType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.DuMixInput */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuMixInput {
    private boolean mCameraInput;
    private boolean mFitCameraAuto;
    private boolean mFrontCamera;
    private int mInputHeight;
    private SurfaceTexture mInputSurface;
    private Texture mInputTexture;
    private int mInputWidth;
    private MirriorType mMirriorType;
    private RotationType mRotationType;
    private boolean mSingleFrame;
    private boolean mSyncInputContent;

    public DuMixInput() {
        this.mInputWidth = 0;
        this.mInputHeight = 0;
        this.mCameraInput = true;
        this.mFrontCamera = true;
        this.mFitCameraAuto = true;
        this.mRotationType = RotationType.ROTATE_90;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mSyncInputContent = false;
        this.mSingleFrame = false;
    }

    public DuMixInput(int i, int i2) {
        this.mInputWidth = 0;
        this.mInputHeight = 0;
        this.mCameraInput = true;
        this.mFrontCamera = true;
        this.mFitCameraAuto = true;
        this.mRotationType = RotationType.ROTATE_90;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mSyncInputContent = false;
        this.mSingleFrame = false;
        this.mInputWidth = i;
        this.mInputHeight = i2;
    }

    public DuMixInput(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mInputWidth = 0;
        this.mInputHeight = 0;
        this.mCameraInput = true;
        this.mFrontCamera = true;
        this.mFitCameraAuto = true;
        this.mRotationType = RotationType.ROTATE_90;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mSyncInputContent = false;
        this.mSingleFrame = false;
        this.mInputSurface = surfaceTexture;
        this.mInputWidth = i;
        this.mInputHeight = i2;
    }

    public DuMixInput(Texture texture, int i, int i2) {
        this.mInputWidth = 0;
        this.mInputHeight = 0;
        this.mCameraInput = true;
        this.mFrontCamera = true;
        this.mFitCameraAuto = true;
        this.mRotationType = RotationType.ROTATE_90;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mSyncInputContent = false;
        this.mSingleFrame = false;
        this.mInputTexture = texture;
        this.mInputWidth = i;
        this.mInputHeight = i2;
    }

    public SurfaceTexture getInputSurface() {
        return this.mInputSurface;
    }

    public void setInputSurface(SurfaceTexture surfaceTexture) {
        this.mInputSurface = surfaceTexture;
    }

    public Texture getInputTexture() {
        return this.mInputTexture;
    }

    public void setInputTexture(Texture texture) {
        this.mInputTexture = texture;
    }

    public int getInputWidth() {
        return this.mInputWidth;
    }

    public void setInputWidth(int i) {
        this.mInputWidth = i;
    }

    public int getInputHeight() {
        return this.mInputHeight;
    }

    public void setInputHeight(int i) {
        this.mInputHeight = i;
    }

    public boolean isCameraInput() {
        return this.mCameraInput;
    }

    public void setCameraInput(boolean z) {
        this.mCameraInput = z;
    }

    public boolean isFrontCamera() {
        return this.mFrontCamera;
    }

    public void setFrontCamera(boolean z) {
        this.mFrontCamera = z;
    }

    public boolean isFitCameraAuto() {
        return this.mFitCameraAuto;
    }

    public void setFitCameraAuto(boolean z) {
        this.mFitCameraAuto = z;
    }

    public RotationType getRotationType() {
        return this.mRotationType;
    }

    public void setRotationType(RotationType rotationType) {
        this.mRotationType = rotationType;
    }

    public MirriorType getMirriorType() {
        return this.mMirriorType;
    }

    public void setMirriorType(MirriorType mirriorType) {
        this.mMirriorType = mirriorType;
    }

    public int getInputDegree() {
        return this.mRotationType.getDegree();
    }

    public void setInputDegree(int i) {
        int i2 = ((i % 360) + 360) % 360;
        if (i == 0) {
            this.mRotationType = RotationType.ROTATE_0;
        } else if (i == 90) {
            this.mRotationType = RotationType.ROTATE_90;
        } else if (i == 180) {
            this.mRotationType = RotationType.ROTATE_180;
        } else if (i != 270) {
        } else {
            this.mRotationType = RotationType.ROTATE_270;
        }
    }

    public boolean isSyncInputContent() {
        return this.mSyncInputContent;
    }

    public void setSyncInputContent(boolean z) {
        this.mSyncInputContent = z;
    }

    public boolean isSingleFrame() {
        return this.mSingleFrame;
    }

    public void setSingleFrame(boolean z) {
        this.mSingleFrame = z;
        if (z) {
            this.mSyncInputContent = true;
            this.mCameraInput = false;
            this.mFrontCamera = false;
        }
    }
}
