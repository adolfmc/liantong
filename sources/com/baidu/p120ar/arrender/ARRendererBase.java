package com.baidu.p120ar.arrender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.opengl.Matrix;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.DuMixStateListener;
import com.baidu.p120ar.ILuaApplicationState;
import com.baidu.p120ar.arplay.core.engine.ARPTouchInput;
import com.baidu.p120ar.arplay.core.renderer.OnRenderFinishedListener;
import com.baidu.p120ar.arplay.core.renderer.OnRenderStartedListener;
import com.baidu.p120ar.bean.RotationType;
import com.baidu.p120ar.bean.ScaleType;
import com.baidu.p120ar.bean.Size;
import com.baidu.p120ar.bean.StorageType;
import com.baidu.p120ar.bean.Watermark;
import com.baidu.p120ar.lua.CaseLuaApplicationStateListener;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.shake.ShakeModule;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.steploading.StepLoadingModule;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.BitmapUtils;
import com.baidu.p120ar.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARRendererBase */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class ARRendererBase extends AbstractRenderer implements View.OnTouchListener, OnRenderFinishedListener, OnRenderStartedListener, IRenderer {
    private static final int SINGLE_FRAME_RENDER_COUNT = 5;
    private static final String TAG = "ARRendererBase";
    protected boolean isActivityLandscape;
    ARPTouchInput mARPTouchInput;
    boolean mCameraFaceFront;
    private CameraSwitchListener mCameraSwitchListener;
    private ConcurrentHashMap<DuMixOutput, RendererTarget> mExtraOutputs;
    private FPSHelper mFPSHelper;
    private FrameCapture mFrameCapture;
    private List<FrameRenderListener> mFrameRenderListeners;
    float[] mInputMatrix;
    private float[] mLastMatrix;
    private CaseLuaApplicationStateListener mLuaState;
    private float[] mRenderMatrix;
    private ShakeModule mShakeModule;
    private StepLoadingModule mStepLoadingMod;
    private boolean mTouchSizeSet;

    public ARRendererBase(Context context, Looper looper, EngineMsgBridge engineMsgBridge, EGLContext eGLContext, String str) {
        super(context, engineMsgBridge, eGLContext, str);
        this.mTouchSizeSet = false;
        this.mFrameRenderListeners = Collections.synchronizedList(new ArrayList());
        this.isActivityLandscape = false;
        this.mCameraFaceFront = false;
        this.mInputMatrix = new float[16];
        this.mARPTouchInput = new ARPTouchInput(Looper.getMainLooper());
        this.mShakeModule = new ShakeModule(context);
        this.mShakeModule.init(this.mEngineMsgBridge);
        this.mLuaState = new CaseLuaApplicationStateListener();
        this.mLuaState.init(this.mEngineMsgBridge);
        this.mStepLoadingMod = new StepLoadingModule(context);
        this.mStepLoadingMod.init(this.mEngineMsgBridge);
        this.mExtraOutputs = new ConcurrentHashMap<>();
        this.isActivityLandscape = ScreenUtils.isScreenOrientationLandscape(this.mContext);
        this.mARPTouchInput.setScreenOrientationLandscape(this.isActivityLandscape);
        this.mFrameCapture = new FrameCapture(looper, engineMsgBridge.getLuaMsgBridge(), this.mARPEngine.getARPRenderer());
        Matrix.setIdentityM(this.mInputMatrix, 0);
        this.mARPEngine.getARPRenderer().setOnRenderStartedListener(this);
        this.mARPEngine.getARPRenderer().setOnRenderFinishedListener(this);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    void initRenderParams() {
        if (this.mDuMixOutput.getOutputFPS() > 0) {
            this.mFPSHelper = new FPSHelper(this.mDuMixOutput.getOutputFPS());
        }
        FrameCapture frameCapture = this.mFrameCapture;
        if (frameCapture != null) {
            frameCapture.setup(this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight());
        }
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void resume() {
        ARLog.m20421d("ARRendererBase", "resume()");
        super.resume();
        ARPTouchInput aRPTouchInput = this.mARPTouchInput;
        if (aRPTouchInput != null) {
            aRPTouchInput.onResume();
        }
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void pause() {
        ARLog.m20421d("ARRendererBase", "pause()");
        ARPTouchInput aRPTouchInput = this.mARPTouchInput;
        if (aRPTouchInput != null) {
            aRPTouchInput.onPause();
        }
        super.pause();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void release() {
        ShakeModule shakeModule = this.mShakeModule;
        if (shakeModule != null) {
            shakeModule.release();
            this.mShakeModule = null;
        }
        ConcurrentHashMap<DuMixOutput, RendererTarget> concurrentHashMap = this.mExtraOutputs;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
            this.mExtraOutputs = null;
        }
        List<FrameRenderListener> list = this.mFrameRenderListeners;
        if (list != null) {
            list.clear();
            this.mFrameRenderListeners = null;
        }
        ARPTouchInput aRPTouchInput = this.mARPTouchInput;
        if (aRPTouchInput != null) {
            aRPTouchInput.release();
            this.mARPTouchInput = null;
        }
        FrameCapture frameCapture = this.mFrameCapture;
        if (frameCapture != null) {
            frameCapture.release();
            this.mFrameCapture = null;
        }
        CaseLuaApplicationStateListener caseLuaApplicationStateListener = this.mLuaState;
        if (caseLuaApplicationStateListener != null) {
            caseLuaApplicationStateListener.release();
            this.mLuaState = null;
        }
        StepLoadingModule stepLoadingModule = this.mStepLoadingMod;
        if (stepLoadingModule != null) {
            stepLoadingModule.release();
            this.mStepLoadingMod = null;
        }
        this.mFPSHelper = null;
        this.mCameraSwitchListener = null;
        this.mInputMatrix = null;
        this.mRenderMatrix = null;
        this.mLastMatrix = null;
        super.release();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void changeOutputSize(int i, int i2) {
        super.changeOutputSize(i, i2);
        this.mTouchSizeSet = false;
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void changeOutput(Object obj, int i, int i2) {
        super.changeOutput(obj, i, i2);
        this.mTouchSizeSet = false;
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void changeOutput(DuMixOutput duMixOutput) {
        super.changeOutput(duMixOutput);
        this.mTouchSizeSet = false;
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void startARPEngine() {
        ARLog.m20421d("ARRendererBase", "startARPEngine()");
        super.startARPEngine();
        ARPTouchInput aRPTouchInput = this.mARPTouchInput;
        if (aRPTouchInput != null) {
            aRPTouchInput.setEnginSoLoaded(true);
        }
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void createCase(String str) {
        ARLog.m20421d("ARRendererBase", "createCase() casePath = " + str);
        super.createCase(str);
        CaseLuaApplicationStateListener caseLuaApplicationStateListener = this.mLuaState;
        if (caseLuaApplicationStateListener != null) {
            caseLuaApplicationStateListener.resetState();
        }
        StepLoadingModule stepLoadingModule = this.mStepLoadingMod;
        if (stepLoadingModule != null) {
            stepLoadingModule.switchCase(str);
        }
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void destroyCase() {
        ARLog.m20421d("ARRendererBase", "destroyCase()");
        super.destroyCase();
        FrameCapture frameCapture = this.mFrameCapture;
        if (frameCapture != null) {
            frameCapture.removeCaseCaptureListener();
            this.mFrameCapture.clearCaptureCache();
        }
        ShakeModule shakeModule = this.mShakeModule;
        if (shakeModule != null) {
            shakeModule.reset();
        }
    }

    public void onRenderStarted(long j) {
        boolean z;
        List<FrameRenderListener> list = this.mFrameRenderListeners;
        if (list != null) {
            for (FrameRenderListener frameRenderListener : list) {
                frameRenderListener.onRenderStarted(j);
            }
        }
        if (this.mDuMixInput != null && this.mDuMixInput.getInputSurface() != null) {
            this.mDuMixInput.getInputSurface().updateTexImage();
            this.mDuMixInput.getInputSurface().getTransformMatrix(this.mInputMatrix);
        }
        if (this.mDuMixInput == null || !this.mDuMixInput.isCameraInput() || this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        boolean z2 = true;
        if (this.mLastMatrix == null) {
            this.mLastMatrix = new float[16];
            float[] fArr = this.mInputMatrix;
            System.arraycopy(fArr, 0, this.mLastMatrix, 0, fArr.length);
            this.mCameraFaceFront = this.mDuMixInput.isFrontCamera();
            z = true;
        } else {
            z = false;
        }
        if (this.mRenderMatrix == null) {
            this.mRenderMatrix = new float[16];
        }
        if (Arrays.equals(this.mInputMatrix, this.mLastMatrix)) {
            z2 = false;
        } else {
            this.mCameraFaceFront = !this.mCameraFaceFront;
            float[] fArr2 = this.mInputMatrix;
            System.arraycopy(fArr2, 0, this.mLastMatrix, 0, fArr2.length);
        }
        if (z || z2) {
            ARRenderHelper.updateInputMatrix(this.mContext, this.mRenderMatrix, this.mCameraFaceFront);
            this.mARPEngine.getARPRenderer().setInputMatrix(this.mRenderMatrix);
            onCameraSwitch(this.mCameraFaceFront);
        }
    }

    public void onRenderFinished(long j) {
        List<FrameRenderListener> list = this.mFrameRenderListeners;
        if (list != null) {
            for (FrameRenderListener frameRenderListener : list) {
                frameRenderListener.onRenderFinished(j);
            }
        }
    }

    public void render() {
        if (this.mDuMixInput != null && this.mDuMixInput.isSingleFrame()) {
            updateForAlgoData();
        }
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().render(System.currentTimeMillis());
    }

    public void setInputMatrix(float[] fArr) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().setInputMatrix(fArr);
    }

    public void setDefaultPipeLine(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mRenderPipeline = str;
        } else {
            this.mRenderPipeline = "filter_pipeline = function()\n\n    fm = ae.FilterManager:get_instance();\n\n    global_copy_filter = fm:create_filter(\"Tex2DFilter\", \"globalTex2DFilter\", true);\n    gl_makeup_filer = fm:create_filter(\"BeautyMakeupFilter\",\"globalBeautyMakeupFilter\",true);\n\n    skin_filter = fm:create_filter(\"SkinFilter\", \"globalSkinFilter\", true);\n    engine_filter = fm:create_filter(\"EngineFilter\", \"globalEngineFilter\", true);\n    fm:update_property_int(engine_filter, \"is_enable\", 0);\n    face_filter = fm:create_filter(\"FaceFilter\", \"globalFaceFilter\", true);\n    lut_filter = fm:create_filter(\"LUTFilter\", \"globalLutFilter\", true);\n    tune_color_filter = fm:create_filter(\"TuneColorFilter\", \"globalTuneColorFilter\", true);\n    fm:reset_pipeline();\n    fm:connect_filters_by_id(skin_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(skin_filter, global_copy_filter);\n    fm:connect_filters_by_id(global_copy_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(gl_makeup_filer, face_filter);\n    fm:connect_filters_by_id(face_filter, tune_color_filter);\n    fm:connect_filters_by_id(tune_color_filter, engine_filter);\n    fm:connect_filters_by_id(engine_filter, lut_filter);\n\n    fm:connect_filter_to_camera(skin_filter);\n    fm:connect_filter_to_output(lut_filter);\n\nend\nfilter_pipeline()\n\n";
        }
    }

    public void addOutputSurface(DuMixOutput duMixOutput) {
        if (duMixOutput == null || duMixOutput.getOutputSurface() == null || this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || !(duMixOutput.getOutputSurface() instanceof Surface)) {
            ARLog.m20419e("ARRendererBase", "addOutputSurface duMixOutput is error!!!");
            return;
        }
        ARLog.m20421d("ARRendererBase", "addOutputSurface() surface = " + duMixOutput.getOutputSurface().hashCode() + " & width*height = " + duMixOutput.getOutputWidth() + "*" + duMixOutput.getOutputHeight() + " & rotation = " + duMixOutput.getRotationType() + " & mode = " + duMixOutput.getScaleType());
        String addOutputSurface = this.mARPEngine.getARPRenderer().addOutputSurface((Surface) duMixOutput.getOutputSurface(), duMixOutput.getOutputWidth(), duMixOutput.getOutputHeight(), ARRenderHelper.getPixelRotation(duMixOutput.getRotationType(), duMixOutput.getMirriorType()), ARRenderHelper.getOutputFillMode(duMixOutput.getScaleType()));
        setWatermark(addOutputSurface, this.mDuMixInput, duMixOutput);
        RendererTarget rendererTarget = new RendererTarget(duMixOutput);
        rendererTarget.setSurfaceHandle(addOutputSurface);
        ConcurrentHashMap<DuMixOutput, RendererTarget> concurrentHashMap = this.mExtraOutputs;
        if (concurrentHashMap != null) {
            concurrentHashMap.put(duMixOutput, rendererTarget);
        }
    }

    public void removeOutputSurface(DuMixOutput duMixOutput) {
        if (duMixOutput == null || duMixOutput.getOutputSurface() == null || !(duMixOutput.getOutputSurface() instanceof Surface)) {
            ARLog.m20419e("ARRendererBase", "removeOutputSurface duMixOutput is error!!!");
        } else if (this.mExtraOutputs != null) {
            ARLog.m20421d("ARRendererBase", "removeOutputSurface() surface = " + duMixOutput.getOutputSurface().hashCode());
            RendererTarget remove = this.mExtraOutputs.remove(duMixOutput);
            if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || remove == null || TextUtils.isEmpty(remove.getSurfaceHandle())) {
                return;
            }
            this.mARPEngine.getARPRenderer().removeOutputTargetByAddr(remove.getSurfaceHandle());
        }
    }

    public void addFrameRenderListener(FrameRenderListener frameRenderListener) {
        List<FrameRenderListener> list;
        if (frameRenderListener == null || (list = this.mFrameRenderListeners) == null) {
            return;
        }
        list.add(frameRenderListener);
    }

    public void removeFrameRenderListener(FrameRenderListener frameRenderListener) {
        List<FrameRenderListener> list;
        if (frameRenderListener == null || (list = this.mFrameRenderListeners) == null) {
            return;
        }
        list.remove(frameRenderListener);
    }

    public void setCameraSwitchListener(CameraSwitchListener cameraSwitchListener) {
        this.mCameraSwitchListener = cameraSwitchListener;
    }

    public void setStateListener(DuMixStateListener duMixStateListener) {
        this.mDuMixStateListener = duMixStateListener;
    }

    public void runAsyncOnRenderContext(Runnable runnable) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || runnable == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(runnable);
    }

    public void runSyncOnRenderContext(Runnable runnable) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || runnable == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().runSyncOnRenderContext(runnable);
    }

    public void cancelAysncRenderTask(Runnable runnable) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || runnable == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().cancelAysncRenderTask(runnable);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || this.mReleasing) {
            return;
        }
        FPSHelper fPSHelper = this.mFPSHelper;
        if (fPSHelper != null && !fPSHelper.needDraw()) {
            this.mARPEngine.getARPRenderer().runSyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arrender.ARRendererBase.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ARRendererBase.this.mDuMixInput == null || ARRendererBase.this.mDuMixInput.getInputSurface() == null) {
                        return;
                    }
                    ARRendererBase.this.mDuMixInput.getInputSurface().updateTexImage();
                }
            });
        } else if (this.mAttached) {
            StatisticApi.getPerformanceApi().onFrameIn();
            this.mARPEngine.getARPRenderer().render(surfaceTexture.getTimestamp());
            StatisticApi.getPerformanceApi().onFrameOut();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        ARPTouchInput aRPTouchInput = this.mARPTouchInput;
        if (aRPTouchInput == null) {
            return false;
        }
        if (!this.mTouchSizeSet) {
            if (view != null) {
                aRPTouchInput.setScreenWidthHight(view.getWidth(), view.getHeight());
            } else if (this.mDuMixOutput != null) {
                this.mARPTouchInput.setScreenWidthHight(this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight());
            }
            this.mTouchSizeSet = true;
        }
        this.mARPTouchInput.onTouchEvent(motionEvent);
        return true;
    }

    public StepLoadingModule getStepLoadingModule() {
        return this.mStepLoadingMod;
    }

    public ILuaApplicationState getLuaApplicationState() {
        return this.mLuaState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCameraSwitch(boolean z) {
        CameraSwitchListener cameraSwitchListener = this.mCameraSwitchListener;
        if (cameraSwitchListener != null) {
            cameraSwitchListener.onCameraSwitch(z);
        }
    }

    private void updateForAlgoData() {
        if (this.mARPEngine != null && this.mARPEngine.getARPRenderer() != null) {
            this.mARPEngine.getARPRenderer().setIsRender(false);
        }
        for (int i = 0; i < 5; i++) {
            if (this.mARPEngine != null && this.mARPEngine.getARPRenderer() != null) {
                this.mARPEngine.getARPRenderer().render(System.currentTimeMillis());
            }
        }
        if (this.mARPEngine != null && this.mARPEngine.getARPRenderer() != null) {
            this.mARPEngine.getARPRenderer().setIsRender(true);
        }
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().render(System.currentTimeMillis());
    }

    private void setWatermark(String str, DuMixInput duMixInput, DuMixOutput duMixOutput) {
        if (duMixOutput == null || duMixOutput.getWatermark() == null) {
            return;
        }
        initWatermarkBitmap(duMixOutput.getWatermark());
        if (duMixOutput.getWatermark().getBitmap() == null) {
            ARLog.m20419e("ARRendererBase", "setWatermark error!!! As no watermark image!!!");
            return;
        }
        initRenderRect(duMixInput, duMixOutput);
        if (this.mARPEngine == null || duMixOutput.getWatermark().getRenderRect() == null || duMixOutput.getWatermark().getRenderRect().length != 4) {
            return;
        }
        this.mARPEngine.setWatermark(str, duMixOutput.getWatermark().getBitmap(), duMixOutput.getWatermark().getRenderRect());
    }

    private void initWatermarkBitmap(Watermark watermark) {
        if (watermark.getBitmap() == null && !TextUtils.isEmpty(watermark.getFilePath())) {
            Bitmap bitmap = null;
            if (watermark.getStorageType() == StorageType.SDCARD) {
                bitmap = BitmapFactory.decodeFile(watermark.getFilePath());
            } else if (watermark.getStorageType() == StorageType.ASSETS) {
                bitmap = BitmapUtils.getAssetsBitmap(this.mContext, watermark.getFilePath());
            }
            watermark.setBitmap(bitmap);
        }
        if (watermark.getBitmap() == null || watermark.getRotationType() == RotationType.ROTATE_0) {
            return;
        }
        watermark.setBitmap(BitmapUtils.rotateBitmap(watermark.getBitmap(), watermark.getRotationType().getDegree()));
    }

    private void initRenderRect(DuMixInput duMixInput, DuMixOutput duMixOutput) {
        int i;
        Watermark watermark = duMixOutput.getWatermark();
        if (watermark.getRenderRect() != null || watermark.getStartPoint() == null) {
            return;
        }
        int outputWidth = duMixOutput.getOutputWidth();
        int outputHeight = duMixOutput.getOutputHeight();
        if (duMixOutput.getRotationType() != RotationType.ROTATE_90 && duMixOutput.getRotationType() != RotationType.ROTATE_270) {
            outputHeight = outputWidth;
            outputWidth = outputHeight;
        }
        int i2 = 0;
        if (duMixOutput.getScaleType() == ScaleType.CENTER_CROP && duMixInput != null) {
            int inputWidth = duMixInput.getInputWidth();
            int inputHeight = duMixInput.getInputHeight();
            if (duMixInput.getRotationType() == RotationType.ROTATE_90 || duMixInput.getRotationType() == RotationType.ROTATE_270) {
                inputHeight = inputWidth;
                inputWidth = inputHeight;
            }
            new Size(outputHeight, outputWidth);
            Size calWindowSize = ARRenderHelper.calWindowSize(inputWidth, inputHeight, outputHeight, outputWidth);
            if (calWindowSize.getWidth() != inputWidth) {
                i2 = (outputHeight - calWindowSize.getWidth()) / 2;
                i = 0;
            } else if (calWindowSize.getHeight() != inputHeight) {
                i = (outputWidth - calWindowSize.getHeight()) / 2;
            }
            watermark.setRenderRect(calRenderRect(watermark, outputHeight, outputWidth, i2, i));
        }
        i = 0;
        watermark.setRenderRect(calRenderRect(watermark, outputHeight, outputWidth, i2, i));
    }

    private float[] calRenderRect(Watermark watermark, float f, float f2, float f3, float f4) {
        float[] fArr = new float[4];
        Point startPoint = watermark.getStartPoint();
        float width = watermark.getBitmap().getWidth() * watermark.getScale();
        float height = watermark.getBitmap().getHeight() * watermark.getScale();
        switch (watermark.getCoordinateType()) {
            case LEFT_TOP:
                fArr[0] = (startPoint.x + f3) / f;
                fArr[1] = (startPoint.y + f4) / f2;
                break;
            case LEFT_BOTTOM:
                fArr[0] = (startPoint.x + f3) / f;
                fArr[1] = (((f2 - f4) - startPoint.y) - height) / f2;
                break;
            case RIGHT_TOP:
                fArr[0] = (((f - f3) - startPoint.x) - width) / f;
                fArr[1] = (startPoint.y + f4) / f2;
                break;
            case RIGHT_BOTTOM:
                fArr[0] = (((f - f3) - startPoint.x) - width) / f;
                fArr[1] = (((f2 - f4) - startPoint.y) - height) / f2;
                break;
        }
        fArr[2] = width / f;
        fArr[3] = height / f2;
        return fArr;
    }
}
