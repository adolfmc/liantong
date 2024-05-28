package com.baidu.p120ar.arplay.core.engine3d;

import android.util.Log;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine3d.ARPCamera */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPCamera extends ARPNode implements IARPCamera {
    private static ARPCamera mARPCamera;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine3d.ARPCamera$ARPCameraType */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class ARPCameraType {
        public static final int ORTHOGRAPHIC = 2;
        public static final int PERSPECTIVE = 1;
    }

    static native void nativeDestory();

    native long nativeCheckSceneCameraValid();

    native float nativeGetFieldOfView(long j);

    native float[] nativeGetViewMatrix(long j);

    native float nativeGetZFar(long j);

    native float nativeGetZNear(long j);

    native void nativeSetFieldOfView(long j, float f);

    native void nativeSetViewMatrix(long j, float[] fArr);

    native void nativeSetZFar(long j, float f);

    native void nativeSetZNear(long j, float f);

    private ARPCamera() {
    }

    public static ARPCamera getDefaultCamera() {
        ARPCamera aRPCamera;
        ARPCamera aRPCamera2 = mARPCamera;
        if (aRPCamera2 == null) {
            synchronized (ARPCamera.class) {
                if (mARPCamera == null) {
                    mARPCamera = new ARPCamera();
                }
                mARPCamera.checkSceneCameraValid();
                aRPCamera = mARPCamera;
            }
            return aRPCamera;
        }
        aRPCamera2.checkSceneCameraValid();
        return mARPCamera;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public void setViewMatrix(float[] fArr) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
            return;
        }
        this.lock.lock();
        nativeSetViewMatrix(this.mNativeNodeAddr, fArr);
        this.lock.unlock();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public float[] getViewMatrix() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
            return null;
        }
        this.lock.lock();
        float[] nativeGetViewMatrix = nativeGetViewMatrix(this.mNativeNodeAddr);
        this.lock.unlock();
        return nativeGetViewMatrix;
    }

    private void checkSceneCameraValid() {
        bindInternal(nativeCheckSceneCameraValid());
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public void setFieldOfView(float f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
        } else {
            nativeSetFieldOfView(this.mNativeNodeAddr, f);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public float getFieldOfView() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
            return 0.0f;
        }
        return nativeGetFieldOfView(this.mNativeNodeAddr);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public void setZNear(float f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
        } else {
            nativeSetZNear(this.mNativeNodeAddr, f);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public float getZNear() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
            return 0.0f;
        }
        return nativeGetZNear(this.mNativeNodeAddr);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public void setZFar(float f) {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
        } else {
            nativeSetZFar(this.mNativeNodeAddr, f);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera
    public float getZFar() {
        if (this.mNativeNodeAddr == -1) {
            Log.e(ARPCamera.class.getSimpleName(), "node addr is error");
            return 0.0f;
        }
        return nativeGetZFar(this.mNativeNodeAddr);
    }

    public static void destory() {
        ARPCamera aRPCamera = mARPCamera;
        if (aRPCamera != null) {
            aRPCamera.mNativeNodeAddr = -1L;
            mARPCamera = null;
            nativeDestory();
        }
    }
}
