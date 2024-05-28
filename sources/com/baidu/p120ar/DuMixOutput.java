package com.baidu.p120ar;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.baidu.p120ar.arrender.Texture;
import com.baidu.p120ar.bean.MirriorType;
import com.baidu.p120ar.bean.RotationType;
import com.baidu.p120ar.bean.ScaleType;
import com.baidu.p120ar.bean.Watermark;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.DuMixOutput */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DuMixOutput {
    private boolean mFitScreenAuto;
    private MirriorType mMirriorType;
    private boolean mNeedDetach;
    private int mOutputFPS;
    private int mOutputHeight;
    private Object mOutputObject;
    private int mOutputWidth;
    private RotationType mRotationType;
    private ScaleType mScaleType;
    private int mScreenOrientation;
    private Watermark mWatermark;

    public DuMixOutput() {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
    }

    public DuMixOutput(int i, int i2) {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public DuMixOutput(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
        this.mOutputObject = surfaceTexture;
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public DuMixOutput(SurfaceHolder surfaceHolder, int i, int i2) {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
        this.mOutputObject = surfaceHolder;
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public DuMixOutput(Surface surface, int i, int i2) {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
        this.mOutputObject = surface;
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public DuMixOutput(Texture texture, int i, int i2) {
        this.mOutputObject = null;
        this.mNeedDetach = false;
        this.mScaleType = ScaleType.CENTER_CROP;
        this.mFitScreenAuto = true;
        this.mScreenOrientation = 1;
        this.mRotationType = RotationType.ROTATE_0;
        this.mMirriorType = MirriorType.NO_MIRRIOR;
        this.mOutputFPS = 0;
        this.mOutputObject = texture;
        this.mOutputWidth = i;
        this.mOutputHeight = i2;
    }

    public Object getOutputSurface() {
        return this.mOutputObject;
    }

    public Texture getOutputTexture() {
        Object obj = this.mOutputObject;
        if (obj == null || !(obj instanceof Texture)) {
            return null;
        }
        return (Texture) obj;
    }

    public void setOutputSurface(SurfaceTexture surfaceTexture) {
        this.mOutputObject = surfaceTexture;
    }

    public void setOutputSurface(SurfaceHolder surfaceHolder) {
        this.mOutputObject = surfaceHolder;
    }

    public void setOutputSurface(Surface surface) {
        this.mOutputObject = surface;
    }

    public void setOutputTexture(Texture texture) {
        this.mOutputObject = texture;
    }

    public boolean isNeedDetach() {
        return this.mNeedDetach;
    }

    public void setNeedDetach(boolean z) {
        this.mNeedDetach = z;
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

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public boolean isFitScreenAuto() {
        return this.mFitScreenAuto;
    }

    public void setFitScreenAuto(boolean z) {
        this.mFitScreenAuto = z;
    }

    public int getScreenOrientation() {
        return this.mScreenOrientation;
    }

    public void setScreenOrientation(int i) {
        this.mScreenOrientation = i;
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

    public int getOutputFPS() {
        return this.mOutputFPS;
    }

    public void setOutputFPS(int i) {
        this.mOutputFPS = i;
    }

    public Watermark getWatermark() {
        return this.mWatermark;
    }

    public void setWatermark(Watermark watermark) {
        this.mWatermark = watermark;
    }
}
