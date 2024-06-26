package com.baidu.p120ar.arplay.core.renderer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.graphics.Rect;
import android.opengl.EGLContext;
import android.opengl.Matrix;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle;
import com.baidu.p120ar.arplay.core.engine.SourceInternalTexType;
import com.baidu.p120ar.arplay.core.engine.rotate.OrientationManager;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.pixel.PixelType;
import com.baidu.p120ar.arplay.util.LogUtil;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.renderer.ARPRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPRenderer implements IBaseLifeCycle, IARPRenderer {
    private static final int MAX_FRAME_COUNT = 100;
    private static final String TAG = "ARPRenderer";
    private boolean hasSetup;
    private String mDefaultLuaPath;
    private final Queue<Runnable> mDrawQueue;
    private boolean mFrontCamera;
    private OnNeedCacheFrameListener mIsNeedCacheFrameListener;
    private OnRenderFinishedListener mOnRenderFinishedListener;
    private OnRenderStartedListener mOnRenderStartedListener;
    private HashMap<String, List<PixelReadListener>> mPixelListenerHash;
    private boolean hasSetupPipeline = false;
    private long mLastFramePTS = -1;
    private long mTotalFrameTimeInMS = 0;
    private int mTotalFrameCount = 0;
    private int mInputTexWidth = 720;
    private int mInputTexHeight = 1280;
    private SoftReference<Context> softContext = null;
    private TakePictureCallback mTakePictureCallback = null;
    private int mRotation = 0;
    private boolean mIsCutSnapShot = false;
    private boolean mNeedSyncRender = false;
    private int mCutSnapStartX = 0;
    private int mCutSnapStartY = 0;
    private int mCutSnapWidth = 0;
    private int mCutSnapHeight = 0;
    private long mLastAlgoPTS = -1;
    private Object[] mRunnableLock = new Object[0];
    private Object[] mPipelineLock = new Object[0];
    private float[] texMatrix = new float[16];

    private native String nativeAddOutputSurface(Surface surface, int i, int i2, int i3, int i4);

    private native String nativeAddOutputTexture(int i, int i2, int i3, int i4, int i5, int i6);

    private native void nativeAdjustFilterWithAssetTextureParam(String str, String str2, Bitmap bitmap);

    private native String nativeAdjustFilterWithCasePathParam(String str);

    private native void nativeAdjustFilterWithFloatArrayParam(String str, String str2, float[] fArr);

    private native void nativeAdjustFilterWithFloatParam(String str, String str2, float f);

    private native void nativeAdjustFilterWithIntParam(String str, String str2, int i);

    private native String nativeAdjustFilterWithJsonPathParam(String str);

    private native void nativeAdjustFilterWithStringParam(String str, String str2, String str3);

    private native void nativeBindTargetSurface(Surface surface);

    private native void nativeClearCaptureData();

    private native void nativeConnectCameraWithTarget();

    private native void nativeContextDestroy();

    private native boolean nativeContextInit(long j, String str);

    private native void nativeContextPurge();

    private static native void nativeCopyBytebuffer(ByteBuffer byteBuffer, byte[] bArr, int i, int i2);

    private native void nativeCreateInputSource(int i, int i2);

    private native void nativeCreatePixelReaderByPreFilterID(int i, int i2, int i3, int i4, float f, float f2, String str, int i5);

    private native void nativeCreateSyncInputSource(int i, int i2);

    private native void nativeDestroyAllPixelReader();

    private native void nativeDestroyInputSource();

    private native void nativeDestroyPixelReaderByPreFilterID(int i, int i2, int i3, int i4, float f, float f2, String str, int i5);

    private native void nativeDisableCaseLutTexture();

    private native void nativeDisableFilterByAuthCode(int i);

    private native long nativeFetchTexture(int i, int i2, int i3);

    private native long nativeGetEGLContext();

    private native int nativeGetTextureId(long j);

    private native void nativeLoadDefaultFilterLuaPath(String str);

    private native void nativePauseRender();

    private native void nativeReleaseEffectFilterRegisterHelper();

    private native void nativeReleasePipeline();

    private native void nativeRemoveOutputAllTarget();

    private native void nativeRemoveOutputTargetByAddr(String str);

    private native void nativeResumeRender();

    private native void nativeReturnTexture(long j);

    private native void nativeRunLuaScriptStr(String str);

    private native void nativeSetAlgoPts(long j);

    private native void nativeSetAuthPic(Bitmap bitmap, float[] fArr);

    private native void nativeSetCaptureData(int i);

    private native void nativeSetInputSourceRotation(int i);

    private native void nativeSetInputTexture(int i, int i2, int i3, int i4);

    private native void nativeSetIsDumpAlgoPixel(boolean z);

    private native void nativeSetIsRender(boolean z);

    private native void nativeSetPixelReaderRotation(int i);

    private native void nativeSetPixelReaderRotationByPixelInfo(int i, int i2, int i3, int i4, float f, float f2, String str, int i5, int i6);

    private native void nativeSetSnapShotPic(Bitmap bitmap, int i, int i2);

    private native void nativeSetSourceSyncProperty(boolean z);

    private native void nativeSwapBuffer();

    private native void nativeUpdateInputTexture(long j);

    private native void nativeUpdateOutputSurfaceRotation(String str, int i);

    private native void nativeUpdateTextureMatrix(float[] fArr);

    private native void nativeUploadPixelToTextureFromPath(long j, String str);

    public native void nativeRunSyncOnIOContext(Runnable runnable);

    public native void nativeRunSyncOnRenderContext(Runnable runnable);

    public ARPRenderer() {
        this.hasSetup = false;
        Matrix.setIdentityM(this.texMatrix, 0);
        this.hasSetup = false;
        this.mDrawQueue = new LinkedList();
        this.mPixelListenerHash = new HashMap<>();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public synchronized boolean setUpEGLEnv(EGLContext eGLContext) {
        if (this.hasSetup) {
            return true;
        }
        if (prepareGLContext(eGLContext)) {
            this.hasSetup = true;
            this.mLastFramePTS = -1L;
            this.mTotalFrameTimeInMS = 0L;
            this.mTotalFrameCount = 0;
            this.hasSetupPipeline = false;
            return true;
        }
        return false;
    }

    public int getCameraPreviewWidth() {
        return this.mInputTexWidth;
    }

    public int getCameraPreviewHeight() {
        return this.mInputTexHeight;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void createInputSource(PixelRotation pixelRotation, SourceInternalTexType sourceInternalTexType) {
        String str = TAG;
        Log.d(str, "calling nativeCreateInputSource: " + sourceInternalTexType);
        nativeCreateInputSource(pixelRotation.getValue(), sourceInternalTexType.getValue());
    }

    public void setInputSourceRotation(PixelRotation pixelRotation) {
        nativeSetInputSourceRotation(pixelRotation.getValue());
    }

    public void setAuthPic(Bitmap bitmap, float[] fArr) {
        nativeSetAuthPic(bitmap, fArr);
    }

    public long getNativeEGLContextHandle() {
        return nativeGetEGLContext();
    }

    @SuppressLint({"NewApi"})
    private boolean prepareGLContext(EGLContext eGLContext) {
        long j;
        Log.d(TAG, "calling nativeContextInit");
        if (eGLContext != null) {
            j = Build.VERSION.SDK_INT > 20 ? eGLContext.getNativeHandle() : eGLContext.getHandle();
        } else {
            j = 0;
        }
        boolean nativeContextInit = nativeContextInit(j, "5.5.0");
        Log.d(TAG, "end of prepareGLContext");
        return nativeContextInit;
    }

    private void destroyGLContext() {
        Log.d(TAG, "calling nativeDestroyInputSource");
        nativeDestroyInputSource();
        Log.d(TAG, "calling nativeRemoveOutputAllTarget");
        nativeRemoveOutputAllTarget();
        if (this.hasSetupPipeline) {
            Log.d(TAG, "calling nativeReleasePipeline");
            nativeReleasePipeline();
        }
        Log.d(TAG, "calling nativeContextDestroy");
        nativeContextDestroy();
        Log.d(TAG, "end of destroyGLContext");
        releaseEffectFilterRegisterHelper();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public long createTexture(int i, int i2, int i3) {
        return nativeFetchTexture(i, i2, i3);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void uploadPixelToTextureFromPath(long j, String str) {
        nativeUploadPixelToTextureFromPath(j, str);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public int getTextureId(long j) {
        return nativeGetTextureId(j);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void createSyncInputSource(PixelRotation pixelRotation, SourceInternalTexType sourceInternalTexType) {
        String str = TAG;
        Log.d(str, "calling createSyncInputSource: " + sourceInternalTexType);
        nativeCreateSyncInputSource(pixelRotation.getValue(), sourceInternalTexType.getValue());
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void destroyTexture(long j) {
        nativeReturnTexture(j);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setSourceSyncProperty(boolean z) {
        this.mNeedSyncRender = z;
        nativeSetSourceSyncProperty(z);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setInputTexture(int i, int i2, int i3, int i4) {
        Log.d(TAG, String.format("setInputTexture: %d %d [%dx%d]", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)));
        this.mInputTexWidth = i3;
        this.mInputTexHeight = i4;
        nativeSetInputTexture(i, i2, i3, i4);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputSurface(Surface surface, int i, int i2) {
        return !this.hasSetup ? "" : addOutputSurface(surface, i, i2, PixelRotation.NoRotation, OutputFillMode.KeepRatioCrop);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputSurface(Surface surface, int i, int i2, PixelRotation pixelRotation) {
        return !this.hasSetup ? "" : addOutputSurface(surface, i, i2, pixelRotation, OutputFillMode.KeepRatioCrop);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputSurface(Surface surface, int i, int i2, PixelRotation pixelRotation, OutputFillMode outputFillMode) {
        if (this.hasSetup) {
            Log.d(TAG, String.format("addOutputSurface[%s | %dx%d %s %s]", surface, Integer.valueOf(i), Integer.valueOf(i2), pixelRotation, outputFillMode));
            return nativeAddOutputSurface(surface, i, i2, pixelRotation.getValue(), outputFillMode.getValue());
        }
        return "";
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void updateOutputSurfaceRotation(String str, PixelRotation pixelRotation) {
        nativeUpdateOutputSurfaceRotation(str, pixelRotation.getValue());
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputTarget(int i, int i2, int i3, int i4) {
        return addOutputTarget(i, i2, i3, i4, PixelRotation.NoRotation);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputTarget(int i, int i2, int i3, int i4, PixelRotation pixelRotation) {
        return addOutputTarget(i, i2, i3, i4, pixelRotation, OutputFillMode.KeepRatioCrop);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String addOutputTarget(int i, int i2, int i3, int i4, PixelRotation pixelRotation, OutputFillMode outputFillMode) {
        Log.d(TAG, String.format("test addOutputTarget: %d [%dx%d] %s %s", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), pixelRotation, outputFillMode));
        return nativeAddOutputTexture(i, i2, i3, i4, pixelRotation.getValue(), outputFillMode.getValue());
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void removeAllOutputTarget() {
        nativeRemoveOutputAllTarget();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void removeOutputTargetByAddr(String str) {
        nativeRemoveOutputTargetByAddr(str);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void bindTargetSurface(Surface surface) {
        nativeBindTargetSurface(surface);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void swapBuffer() {
        nativeSwapBuffer();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void connectCameraWithTarget() {
        nativeConnectCameraWithTarget();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void purgeMemory() {
        nativeContextPurge();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void resume() {
        Log.d(TAG, "ARPFilter resume: ");
        nativeResumeRender();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void pause() {
        Log.d(TAG, "ARPFilter pause: ");
        nativePauseRender();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public void destroy() {
        if (this.hasSetup) {
            ARPMessage.getInstance().release();
            this.mDrawQueue.clear();
            Log.d(TAG, "ARPFilter destroy: ");
            destroyGLContext();
            this.mOnRenderStartedListener = null;
            this.mOnRenderFinishedListener = null;
            this.mIsNeedCacheFrameListener = null;
            SnapShot.destroyCache();
            this.hasSetup = false;
            this.mNeedSyncRender = false;
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public synchronized void render(long j) {
        if (this.mTotalFrameCount > 0) {
            this.mTotalFrameTimeInMS += System.currentTimeMillis() - this.mLastFramePTS;
        }
        this.mLastFramePTS = System.currentTimeMillis();
        int i = this.mTotalFrameCount;
        this.mTotalFrameCount = i + 1;
        if (i == 100) {
            LogUtil.m20424d(TAG, String.format("Average Frame Time: %.2f, FPS: %.2f", Float.valueOf(((float) this.mTotalFrameTimeInMS) / 100.0f), Float.valueOf((1000.0f / ((float) this.mTotalFrameTimeInMS)) * 100.0f)));
            this.mTotalFrameCount = 0;
            this.mTotalFrameTimeInMS = 0L;
        }
        if (this.hasSetup) {
            nativeUpdateInputTexture(j);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setInputMatrix(float[] fArr) {
        System.arraycopy(fArr, 0, this.texMatrix, 0, 16);
        nativeUpdateTextureMatrix(this.texMatrix);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void onFrameRenderStarted(long j) {
        OnRenderStartedListener onRenderStartedListener = this.mOnRenderStartedListener;
        if (onRenderStartedListener != null) {
            onRenderStartedListener.onRenderStarted(j);
        }
        runAllDrawQueue();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void onFrameRenderFinished(long j) {
        OnRenderFinishedListener onRenderFinishedListener = this.mOnRenderFinishedListener;
        if (onRenderFinishedListener != null) {
            onRenderFinishedListener.onRenderFinished(j);
        }
    }

    public static boolean needRotate(int i) {
        return i == PixelRotation.RotateLeft.getValue() || i == PixelRotation.RotateRight.getValue() || i == PixelRotation.RotateRightFlipVertical.getValue() || i == PixelRotation.RotateRightFlipHorizontal.getValue();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setOnRenderStartedListener(OnRenderStartedListener onRenderStartedListener) {
        this.mOnRenderStartedListener = onRenderStartedListener;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setOnRenderFinishedListener(OnRenderFinishedListener onRenderFinishedListener) {
        this.mOnRenderFinishedListener = onRenderFinishedListener;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setOnNeedCacheFrameListener(OnNeedCacheFrameListener onNeedCacheFrameListener) {
        this.mIsNeedCacheFrameListener = onNeedCacheFrameListener;
    }

    public void disableFilterByAuthCode(int i) {
        nativeDisableFilterByAuthCode(i);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setCameraFace(boolean z) {
        this.mFrontCamera = z;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public boolean isFrontCamera() {
        return this.mFrontCamera;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setContext(SoftReference<Context> softReference) {
        if (softReference != null) {
            this.softContext = softReference;
        }
    }

    private void runAllDrawQueue() {
        Queue<Runnable> queue = this.mDrawQueue;
        if (queue == null) {
            return;
        }
        synchronized (queue) {
            while (!this.mDrawQueue.isEmpty()) {
                this.mDrawQueue.poll().run();
            }
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void runSyncOnRenderContext(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        String str = TAG;
        LogUtil.m20424d(str, "runSyncOnRenderContext: " + Thread.currentThread().getId());
        synchronized (this.mRunnableLock) {
            nativeRunSyncOnRenderContext(runnable);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void runSyncOnIOContext(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        String str = TAG;
        LogUtil.m20424d(str, "runSyncOnRenderContext: " + Thread.currentThread().getId());
        synchronized (this.mRunnableLock) {
            nativeRunSyncOnIOContext(runnable);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void runAsyncOnRenderContext(Runnable runnable) {
        Queue<Runnable> queue = this.mDrawQueue;
        if (queue == null || runnable == null) {
            return;
        }
        synchronized (queue) {
            this.mDrawQueue.add(runnable);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void clearAllAsyncRenderTask() {
        Queue<Runnable> queue = this.mDrawQueue;
        if (queue == null) {
            return;
        }
        synchronized (queue) {
            if (!this.mDrawQueue.isEmpty()) {
                this.mDrawQueue.clear();
            }
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void cancelAysncRenderTask(Runnable runnable) {
        Queue<Runnable> queue = this.mDrawQueue;
        if (queue == null || runnable == null) {
            return;
        }
        synchronized (queue) {
            if (!this.mDrawQueue.isEmpty()) {
                this.mDrawQueue.remove(runnable);
            }
        }
    }

    private String getPixelReadParamHash(PixelReadParams pixelReadParams) {
        if (pixelReadParams == null) {
            return null;
        }
        int outputWidth = pixelReadParams.getOutputWidth();
        int outputHeight = pixelReadParams.getOutputHeight();
        PixelType pixelType = pixelReadParams.getPixelType();
        String preFilterID = pixelReadParams.getPreFilterID();
        preFilterID = (preFilterID.equals("") || preFilterID.equals("camera")) ? "camera" : "camera";
        int value = pixelReadParams.getFrameType().getValue();
        return Integer.toString(outputWidth) + Integer.toString(outputHeight) + Integer.toString(pixelType.getValue()) + preFilterID + value;
    }

    private void multiplyPointWithMatrix(PointF pointF, float[] fArr) {
        if (fArr == null || fArr.length < 6) {
            return;
        }
        pointF.set((fArr[0] * pointF.x) + (fArr[4] * pointF.y), (fArr[1] * pointF.x) + (fArr[5] * pointF.y));
    }

    private boolean needSwapResolution() {
        PointF pointF = new PointF(1.0f, 0.0f);
        PointF pointF2 = new PointF(0.0f, 1.0f);
        multiplyPointWithMatrix(pointF, this.texMatrix);
        multiplyPointWithMatrix(pointF2, this.texMatrix);
        return ((double) Math.abs(pointF.x)) < 1.0E-6d && ((double) Math.abs(Math.abs(pointF.y) - 1.0f)) < 1.0E-6d && ((double) Math.abs(Math.abs(pointF2.x) - 1.0f)) < 1.0E-6d && ((double) Math.abs(pointF2.y)) < 1.0E-6d;
    }

    private int getInputWidth(PixelReadParams pixelReadParams) {
        int i = this.mInputTexWidth;
        return pixelReadParams.getPreFilterID().equals("") || pixelReadParams.getPreFilterID().equals("camera") ? needRotate(pixelReadParams.getPixelRotate().getValue()) ? this.mInputTexHeight : i : needSwapResolution() ? this.mInputTexHeight : i;
    }

    private int getInputHeight(PixelReadParams pixelReadParams) {
        int i = this.mInputTexHeight;
        return pixelReadParams.getPreFilterID().equals("") || pixelReadParams.getPreFilterID().equals("camera") ? needRotate(pixelReadParams.getPixelRotate().getValue()) ? this.mInputTexWidth : i : needSwapResolution() ? this.mInputTexWidth : i;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void createPixelReaderByPreFilterID(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        if (pixelReadParams == null || pixelReadListener == null) {
            return;
        }
        int outputWidth = pixelReadParams.getOutputWidth();
        int outputHeight = pixelReadParams.getOutputHeight();
        PixelRotation pixelRotate = pixelReadParams.getPixelRotate();
        PixelType pixelType = pixelReadParams.getPixelType();
        int value = pixelReadParams.getFrameType().getValue();
        float inputWidth = outputWidth / getInputWidth(pixelReadParams);
        float inputHeight = outputHeight / getInputHeight(pixelReadParams);
        String pixelReadParamHash = getPixelReadParamHash(pixelReadParams);
        synchronized (this.mPipelineLock) {
            if (this.mPixelListenerHash.containsKey(pixelReadParamHash)) {
                this.mPixelListenerHash.get(pixelReadParamHash).add(pixelReadListener);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(pixelReadListener);
            this.mPixelListenerHash.put(pixelReadParamHash, arrayList);
            nativeCreatePixelReaderByPreFilterID(outputWidth, outputHeight, pixelRotate.getValue(), pixelType.getValue(), inputWidth, inputHeight, pixelReadParams.getPreFilterID(), value);
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void destroyPixelReaderByPreFilterID(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        if (pixelReadParams == null || pixelReadListener == null) {
            return;
        }
        String pixelReadParamHash = getPixelReadParamHash(pixelReadParams);
        synchronized (this.mPipelineLock) {
            if (this.mPixelListenerHash.containsKey(pixelReadParamHash)) {
                List<PixelReadListener> list = this.mPixelListenerHash.get(pixelReadParamHash);
                if (list.size() > 1) {
                    list.remove(pixelReadListener);
                    return;
                }
                this.mPixelListenerHash.remove(pixelReadParamHash);
                int outputWidth = pixelReadParams.getOutputWidth();
                int outputHeight = pixelReadParams.getOutputHeight();
                nativeDestroyPixelReaderByPreFilterID(outputWidth, outputHeight, pixelReadParams.getPixelRotate().getValue(), pixelReadParams.getPixelType().getValue(), outputWidth / getInputWidth(pixelReadParams), outputHeight / getInputHeight(pixelReadParams), pixelReadParams.getPreFilterID(), pixelReadParams.getFrameType().getValue());
            }
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void destroyAllPixelReader() {
        if (this.mPixelListenerHash == null) {
            return;
        }
        synchronized (this.mPipelineLock) {
            this.mPixelListenerHash.clear();
        }
        nativeDestroyAllPixelReader();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setPixelReaderRotation(PixelRotation pixelRotation) {
        nativeSetPixelReaderRotation(pixelRotation.getValue());
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setPixelReaderRotation(PixelReadParams pixelReadParams, PixelRotation pixelRotation) {
        if (pixelReadParams == null) {
            return;
        }
        String pixelReadParamHash = getPixelReadParamHash(pixelReadParams);
        synchronized (this.mPipelineLock) {
            if (this.mPixelListenerHash.containsKey(pixelReadParamHash)) {
                int outputWidth = pixelReadParams.getOutputWidth();
                int outputHeight = pixelReadParams.getOutputHeight();
                nativeSetPixelReaderRotationByPixelInfo(outputWidth, outputHeight, pixelReadParams.getPixelRotate().getValue(), pixelReadParams.getPixelType().getValue(), outputWidth / getInputWidth(pixelReadParams), outputHeight / getInputHeight(pixelReadParams), pixelReadParams.getPreFilterID(), pixelRotation.getValue(), pixelReadParams.getFrameType().getValue());
            }
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void onSnapShotFinished(Bitmap bitmap, long j) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), (android.graphics.Matrix) null, false);
        if (this.mIsCutSnapShot) {
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, this.mCutSnapStartX, this.mCutSnapStartY, this.mCutSnapWidth, this.mCutSnapHeight, (android.graphics.Matrix) null, false);
            if (createBitmap != null && !createBitmap.equals(createBitmap2) && !createBitmap.isRecycled()) {
                createBitmap.recycle();
            }
            createBitmap = createBitmap2;
        }
        TakePictureCallback takePictureCallback = this.mTakePictureCallback;
        if (takePictureCallback != null) {
            takePictureCallback.onPictureTake(true, createBitmap, j);
        }
    }

    private void snapShot(TakePictureCallback takePictureCallback, int i, int i2, int i3) {
        this.mTakePictureCallback = takePictureCallback;
        this.mRotation = i3;
        nativeSetSnapShotPic(SnapShot.getCacheBitmap(i, i2), i, i2);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void getSnapShot(TakePictureCallback takePictureCallback, int i, int i2, int i3) {
        this.mIsCutSnapShot = false;
        snapShot(takePictureCallback, i, i2, i3);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void getSnapShot(TakePictureCallback takePictureCallback, int i, int i2, int i3, Rect rect) {
        this.mIsCutSnapShot = true;
        this.mCutSnapStartX = rect.left;
        this.mCutSnapStartY = rect.top;
        this.mCutSnapWidth = rect.right - rect.left;
        this.mCutSnapHeight = rect.bottom - rect.top;
        snapShot(takePictureCallback, i, i2, i3);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public boolean pixelReadCallback(ByteBuffer byteBuffer, long j, int i, int i2, int i3, int i4, int i5, String str, int i6, int i7) {
        PixelReadParams pixelReadParams = new PixelReadParams(PixelType.valueOf(i4));
        pixelReadParams.setOutputWidth(i);
        pixelReadParams.setOutputHeight(i2);
        pixelReadParams.setPreFilterID(str);
        pixelReadParams.setFrameType(PixelReadParams.FrameType.values()[i6]);
        String pixelReadParamHash = getPixelReadParamHash(pixelReadParams);
        Boolean bool = false;
        OnNeedCacheFrameListener onNeedCacheFrameListener = this.mIsNeedCacheFrameListener;
        if (onNeedCacheFrameListener != null && this.mNeedSyncRender && j != this.mLastAlgoPTS) {
            bool = Boolean.valueOf(onNeedCacheFrameListener.isNeedCacheFrame(j));
        }
        this.mLastAlgoPTS = j;
        if (this.mPixelListenerHash.containsKey(pixelReadParamHash)) {
            FramePixels framePixels = new FramePixels(PixelType.values()[i4], byteBuffer, i, i2);
            framePixels.setCameraFrame(true);
            framePixels.setFrontCamera(this.mFrontCamera);
            framePixels.setOrientation(OrientationManager.getGlobalOrientation());
            framePixels.setTimestamp(j);
            framePixels.setPixelLength(i5);
            framePixels.setTextureID(i7);
            framePixels.setFrameType(PixelReadParams.FrameType.values()[i6]);
            List<PixelReadListener> list = this.mPixelListenerHash.get(pixelReadParamHash);
            if (list != null) {
                for (PixelReadListener pixelReadListener : list) {
                    if (pixelReadListener != null) {
                        pixelReadListener.onPixelRead(framePixels);
                    }
                }
            }
        }
        return bool.booleanValue();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void loadDefaultFilterLuaPath(String str) {
        ARPMessage.getInstance().setUp();
        synchronized (this.mPipelineLock) {
            nativeLoadDefaultFilterLuaPath(str);
            this.hasSetupPipeline = true;
            this.mDefaultLuaPath = str;
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void runLuaScriptStr(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ARPMessage.getInstance().setUp();
        synchronized (this.mPipelineLock) {
            nativeRunLuaScriptStr(str);
            this.hasSetupPipeline = true;
        }
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public String getDefaultLuaPath() {
        return this.mDefaultLuaPath;
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setAlgoPts(long j) {
        nativeSetAlgoPts(j);
    }

    public void disableCaseLutTexture() {
        nativeDisableCaseLutTexture();
    }

    public void adjustFilterWithFloatParam(String str, String str2, float f, long j) {
        nativeAdjustFilterWithFloatParam(str, str2, f);
    }

    public void adjustFilterWithIntParam(String str, String str2, int i, long j) {
        nativeAdjustFilterWithIntParam(str, str2, i);
    }

    public void adjustFilterWithFloatArrayParam(String str, String str2, float[] fArr, long j) {
        nativeAdjustFilterWithFloatArrayParam(str, str2, fArr);
    }

    public void adjustFilterWithStringParam(String str, String str2, String str3, long j) {
        SoftReference<Context> softReference;
        if (str3.contains("android_asset") && (softReference = this.softContext) != null && softReference.get() != null && this.softContext.get().getAssets() != null) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(this.softContext.get().getAssets().open(str3.substring(str3.lastIndexOf("android_asset") + 13 + 1)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                String str4 = "texture_width";
                String str5 = "texture_height";
                String str6 = "texture_byte_array";
                if (str2.contains("/")) {
                    String substring = str2.substring(0, str2.lastIndexOf("/") + 1);
                    str4 = substring + "texture_width";
                    str5 = substring + "texture_height";
                    str6 = substring + "texture_byte_array";
                }
                nativeAdjustFilterWithFloatParam(str, str4, bitmap.getWidth());
                nativeAdjustFilterWithFloatParam(str, str5, bitmap.getHeight());
                nativeAdjustFilterWithAssetTextureParam(str, str6, bitmap);
                bitmap.recycle();
                return;
            }
            return;
        }
        nativeAdjustFilterWithStringParam(str, str2, str3);
    }

    public String adjustFilterWithJsonPathParam(String str) {
        return nativeAdjustFilterWithJsonPathParam(str);
    }

    public String adjustFilterWithCasePathParam(String str) {
        return nativeAdjustFilterWithCasePathParam(str);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setIsDumpAlgoPixel(boolean z) {
        nativeSetIsDumpAlgoPixel(z);
    }

    public static void copyNativeBytebuffer(ByteBuffer byteBuffer, byte[] bArr, int i, int i2) {
        nativeCopyBytebuffer(byteBuffer, bArr, i, i2);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setCaptureData(int i) {
        nativeSetCaptureData(i);
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void clearCaptureData() {
        nativeClearCaptureData();
    }

    @Override // com.baidu.p120ar.arplay.core.renderer.IARPRenderer
    public void setIsRender(boolean z) {
        nativeSetIsRender(z);
    }

    public void releaseEffectFilterRegisterHelper() {
        nativeReleaseEffectFilterRegisterHelper();
    }
}
