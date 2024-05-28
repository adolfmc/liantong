package com.baidu.p120ar.arrender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.os.Looper;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.DuMixStateListener;
import com.baidu.p120ar.ILuaApplicationState;
import com.baidu.p120ar.ability.AbilityData;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPCamera;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPNode;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene;
import com.baidu.p120ar.arplay.core.engine.rotate.Orientation;
import com.baidu.p120ar.arplay.core.engine.rotate.OrientationManager;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.core.renderer.OnNeedCacheFrameListener;
import com.baidu.p120ar.arplay.core.renderer.TakePictureCallback;
import com.baidu.p120ar.arplay.representation.Matrixf4x4;
import com.baidu.p120ar.arplay.representation.Quaternion;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arplay.representation.Vector4f;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.imu.Coordinate;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.steploading.StepLoadingModule;
import com.baidu.p120ar.utils.ARLog;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arrender.ARRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARRenderer extends ARRendererBase implements OrientationManager.OrientationListener, IARRenderer {
    private static boolean PROFILE_LOG = true;
    private static final int PROFILE_LOG_SIZE = 50;
    private static final int SWITCH_CAMERA_SKIP_FRAME_NUM = 3;
    private static final String TAG = "ARRenderer";
    private ARRenderFpsCallback mARRenderFpsCallback;
    private long mDrawFrameTime;
    private List<String> mEnabledAbilities;
    private Runnable mFieldOfViewRunnable;
    protected FilterNodeData mFilterNodeData;
    protected Runnable mFilterNodeRunnbale;
    private float mFov;
    private int mFrameIndex;
    private InputSizeChangeListener mInputSizeChangeListener;
    private long mIntervalSum;
    private OutputSizeChangeListener mOutputSizeChangeListener;
    private RenderCameraData mRenderCameraData;
    private Runnable mRenderCameraRunnable;
    private RenderNodeData mRenderNodeData;
    private Runnable mRenderNodeRunnable;
    private int mSkipCount;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arrender.ARRenderer$InputSizeChangeListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InputSizeChangeListener {
        void onInputSizeChange(int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arrender.ARRenderer$OutputSizeChangeListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OutputSizeChangeListener {
        void outputSizeChange(int i, int i2);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void addFrameRenderListener(FrameRenderListener frameRenderListener) {
        super.addFrameRenderListener(frameRenderListener);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void addOutputSurface(DuMixOutput duMixOutput) {
        super.addOutputSurface(duMixOutput);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void cancelAysncRenderTask(Runnable runnable) {
        super.cancelAysncRenderTask(runnable);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void changeInput(DuMixInput duMixInput) {
        super.changeInput(duMixInput);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void changeInputSize(int i, int i2) {
        super.changeInputSize(i, i2);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void changeOutput(DuMixOutput duMixOutput) {
        super.changeOutput(duMixOutput);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void changeOutput(Object obj, int i, int i2) {
        super.changeOutput(obj, i, i2);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void createCase(String str) {
        super.createCase(str);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void createPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        super.createPixelReader(pixelReadParams, pixelReadListener);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void destroyAllPixelReaders() {
        super.destroyAllPixelReaders();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void destroyCase() {
        super.destroyCase();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void destroyPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        super.destroyPixelReader(pixelReadParams, pixelReadListener);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IARRenderer
    public /* bridge */ /* synthetic */ ILuaApplicationState getLuaApplicationState() {
        return super.getLuaApplicationState();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase
    public /* bridge */ /* synthetic */ StepLoadingModule getStepLoadingModule() {
        return super.getStepLoadingModule();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ boolean isEngineLoading() {
        return super.isEngineLoading();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, android.graphics.SurfaceTexture.OnFrameAvailableListener
    public /* bridge */ /* synthetic */ void onFrameAvailable(SurfaceTexture surfaceTexture) {
        super.onFrameAvailable(surfaceTexture);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, android.view.View.OnTouchListener
    public /* bridge */ /* synthetic */ boolean onTouch(View view, MotionEvent motionEvent) {
        return super.onTouch(view, motionEvent);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void pause() {
        super.pause();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void removeFrameRenderListener(FrameRenderListener frameRenderListener) {
        super.removeFrameRenderListener(frameRenderListener);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void removeOutputSurface(DuMixOutput duMixOutput) {
        super.removeOutputSurface(duMixOutput);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void render() {
        super.render();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void resume() {
        super.resume();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void runAsyncOnRenderContext(Runnable runnable) {
        super.runAsyncOnRenderContext(runnable);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void runSyncOnRenderContext(Runnable runnable) {
        super.runSyncOnRenderContext(runnable);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ void setAbilityScheme(JSONObject jSONObject) {
        super.setAbilityScheme(jSONObject);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void setCameraSwitchListener(CameraSwitchListener cameraSwitchListener) {
        super.setCameraSwitchListener(cameraSwitchListener);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void setDefaultPipeLine(String str) {
        super.setDefaultPipeLine(str);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ void setEngineCreate(boolean z) {
        super.setEngineCreate(z);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void setInputMatrix(float[] fArr) {
        super.setInputMatrix(fArr);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ void setInterruptLoading(boolean z) {
        super.setInterruptLoading(z);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ void setLocalDeviceGrade(int i) {
        super.setLocalDeviceGrade(i);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.IRenderer
    public /* bridge */ /* synthetic */ void setStateListener(DuMixStateListener duMixStateListener) {
        super.setStateListener(duMixStateListener);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void setup(DuMixInput duMixInput, DuMixOutput duMixOutput) {
        super.setup(duMixInput, duMixOutput);
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void startARPEngine() {
        super.startARPEngine();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void stopARPEngine() {
        super.stopARPEngine();
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer
    public /* bridge */ /* synthetic */ void touchEngineToOutputSize(boolean z) {
        super.touchEngineToOutputSize(z);
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public /* bridge */ /* synthetic */ void updatePixelReader(PixelReadParams pixelReadParams, PixelRotation pixelRotation) {
        super.updatePixelReader(pixelReadParams, pixelRotation);
    }

    public ARRenderer(Context context, Looper looper, EngineMsgBridge engineMsgBridge, String str) {
        this(context, looper, engineMsgBridge, null, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ARRenderer(Context context, Looper looper, EngineMsgBridge engineMsgBridge, EGLContext eGLContext, String str) {
        super(context, looper, engineMsgBridge, eGLContext, str);
        this.mSkipCount = 0;
        this.mFrameIndex = 0;
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void release() {
        this.mEnabledAbilities = null;
        this.mOutputSizeChangeListener = null;
        this.mInputSizeChangeListener = null;
        this.mARRenderFpsCallback = null;
        super.release();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arplay.core.renderer.OnRenderStartedListener
    public void onRenderStarted(long j) {
        ARRenderFpsCallback aRRenderFpsCallback = this.mARRenderFpsCallback;
        if (aRRenderFpsCallback != null && (aRRenderFpsCallback.listenType() & 1) != 0) {
            this.mARRenderFpsCallback.onRenderStarted();
        }
        if (PROFILE_LOG) {
            if (this.mDrawFrameTime != 0) {
                ARLog.m20421d("profile_frame_interval", "= " + (System.currentTimeMillis() - this.mDrawFrameTime));
                int i = this.mFrameIndex;
                if (i == 50) {
                    int i2 = (int) (50000 / this.mIntervalSum);
                    ARLog.m20421d("profile_frame_fps_avg", "= " + i2);
                    ARLog.m20421d("profile_frame_interval_avg", "= " + (this.mIntervalSum / 50));
                    this.mFrameIndex = 0;
                    this.mIntervalSum = 0L;
                    ARRenderFpsCallback aRRenderFpsCallback2 = this.mARRenderFpsCallback;
                    if (aRRenderFpsCallback2 != null && (aRRenderFpsCallback2.listenType() & 4) != 0) {
                        this.mARRenderFpsCallback.renderFps(i2);
                    }
                } else {
                    this.mFrameIndex = i + 1;
                    this.mIntervalSum += System.currentTimeMillis() - this.mDrawFrameTime;
                }
            }
            this.mDrawFrameTime = System.currentTimeMillis();
        }
        super.onRenderStarted(j);
        checkSkipEngineRender();
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arplay.core.renderer.OnRenderFinishedListener
    public void onRenderFinished(long j) {
        ARRenderFpsCallback aRRenderFpsCallback = this.mARRenderFpsCallback;
        if (aRRenderFpsCallback != null && (aRRenderFpsCallback.listenType() & 2) != 0) {
            this.mARRenderFpsCallback.onRenderFinished();
        }
        super.onRenderFinished(j);
        if (PROFILE_LOG) {
            ARLog.m20421d("profile_frame_time_cpu", "= " + (System.currentTimeMillis() - this.mDrawFrameTime));
        }
    }

    public DuMixInput getDuMixInput() {
        return this.mDuMixInput;
    }

    public DuMixOutput getDuMixOutput() {
        return this.mDuMixOutput;
    }

    public void setOutputSizeChangeListener(OutputSizeChangeListener outputSizeChangeListener) {
        this.mOutputSizeChangeListener = outputSizeChangeListener;
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase, com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void changeOutputSize(int i, int i2) {
        super.changeOutputSize(i, i2);
        OutputSizeChangeListener outputSizeChangeListener = this.mOutputSizeChangeListener;
        if (outputSizeChangeListener != null) {
            outputSizeChangeListener.outputSizeChange(i, i2);
        }
    }

    public void setInputSizeChangeListener(InputSizeChangeListener inputSizeChangeListener) {
        this.mInputSizeChangeListener = inputSizeChangeListener;
    }

    @Override // com.baidu.p120ar.arrender.AbstractRenderer, com.baidu.p120ar.arrender.IRendererLifecycle
    public void changeInput(Object obj, int i, int i2) {
        super.changeInput(obj, i, i2);
        InputSizeChangeListener inputSizeChangeListener = this.mInputSizeChangeListener;
        if (inputSizeChangeListener != null) {
            inputSizeChangeListener.onInputSizeChange(i, i2);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setEnabledAbilities(List<String> list) {
        this.mEnabledAbilities = list;
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateRenderNodeData(RenderNodeData renderNodeData, boolean z) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || renderNodeData == null || isAbilityDataUnuseable(renderNodeData)) {
            ARLog.m20419e("ARRenderer", "updateRenderNodeData error!!!");
        } else if (z) {
            this.mRenderNodeData = renderNodeData;
            if (this.mRenderNodeRunnable == null) {
                this.mRenderNodeRunnable = new Runnable() { // from class: com.baidu.ar.arrender.ARRenderer.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ARRenderer.this.mARPEngine.updateAlgoDataToNode(ARRenderer.this.mRenderNodeData.getWidth(), ARRenderer.this.mRenderNodeData.getHeight(), ARRenderer.this.mRenderNodeData.getMaskData());
                    }
                };
            }
            this.mARPEngine.getARPRenderer().cancelAysncRenderTask(this.mRenderNodeRunnable);
            this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(this.mRenderNodeRunnable);
        } else {
            this.mARPEngine.updateAlgoDataToNode(renderNodeData.getWidth(), renderNodeData.getHeight(), renderNodeData.getMaskData());
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateFilterData(FilterData filterData) {
        float floatValue;
        if (this.mARPEngine == null || filterData == null || isAbilityDataUnuseable(filterData) || this.mInterruptLoading) {
            ARLog.m20419e("ARRenderer", "updateFilterData error!!!");
            return;
        }
        switch (filterData.getAdjustValueType()) {
            case INT:
                this.mARPEngine.adjustFilterWithIntParam(filterData.getFilterName(), filterData.getAdjustKey(), ((Integer) filterData.getAdjustValue()).intValue(), filterData.getTimestamp());
                return;
            case FLOAT:
                Object adjustValue = filterData.getAdjustValue();
                if (adjustValue instanceof Double) {
                    floatValue = (float) ((Double) adjustValue).doubleValue();
                } else {
                    floatValue = ((Float) adjustValue).floatValue();
                }
                this.mARPEngine.adjustFilterWithFloatParam(filterData.getFilterName(), filterData.getAdjustKey(), floatValue, filterData.getTimestamp());
                return;
            case FLOAT_ARRAY:
                this.mARPEngine.adjustFilterWithFloatArrayParam(filterData.getFilterName(), filterData.getAdjustKey(), (float[]) filterData.getAdjustValue(), filterData.getTimestamp());
                return;
            case STRING:
                this.mARPEngine.adjustFilterWithStringParam(filterData.getFilterName(), filterData.getAdjustKey(), (String) filterData.getAdjustValue(), filterData.getTimestamp());
                return;
            default:
                ARLog.m20419e("ARRenderer", "updateFilterData filterData.getAdjustValueType() error!!!");
                return;
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public String updateFilterCase(String str) {
        if (this.mARPEngine != null) {
            return this.mARPEngine.adjustFilterWithCasePathParam(str);
        }
        return null;
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void clearCaseLutFilter() {
        if (this.mARPEngine != null) {
            this.mARPEngine.disableCaseLutTexture();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateFilterNodeData(FilterNodeData filterNodeData) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || filterNodeData == null) {
            ARLog.m20419e("ARRenderer", "updateFilterNodeData error!!!");
            return;
        }
        this.mFilterNodeData = filterNodeData;
        if (this.mFilterNodeRunnbale == null) {
            this.mFilterNodeRunnbale = new Runnable() { // from class: com.baidu.ar.arrender.ARRenderer.2
                @Override // java.lang.Runnable
                public void run() {
                    if (ARRenderer.this.mFilterNodeData == null || TextUtils.isEmpty(ARRenderer.this.mFilterNodeData.getNodeName()) || ARRenderer.this.mFilterNodeData.getValueMap() == null || ARRenderer.this.mARPEngine == null) {
                        return;
                    }
                    ARRenderer aRRenderer = ARRenderer.this;
                    if (aRRenderer.isAbilityDataUnuseable(aRRenderer.mFilterNodeData)) {
                        return;
                    }
                    ARRenderer.this.mARPEngine.updateNodeUniform(ARRenderer.this.mFilterNodeData.getNodeName(), ARRenderer.this.mFilterNodeData.getValueMap());
                }
            };
        }
        this.mARPEngine.getARPRenderer().cancelAysncRenderTask(this.mFilterNodeRunnbale);
        this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(this.mFilterNodeRunnbale);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateRenderCameraData(RenderCameraData renderCameraData) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null || renderCameraData == null || isAbilityDataUnuseable(renderCameraData)) {
            ARLog.m20419e("ARRenderer", "updateRenderCameraData error!!!");
            return;
        }
        this.mRenderCameraData = renderCameraData;
        if (this.mRenderCameraRunnable == null) {
            this.mRenderCameraRunnable = new Runnable() { // from class: com.baidu.ar.arrender.ARRenderer.3
                @Override // java.lang.Runnable
                public void run() {
                    IARPCamera activeCamera;
                    IARPScene currentScene = ARRenderer.this.mARPEngine.getCurrentScene();
                    if (currentScene == null || (activeCamera = currentScene.getActiveCamera()) == null) {
                        return;
                    }
                    activeCamera.setViewMatrix(ARRenderer.this.mRenderCameraData.getMatrix());
                }
            };
        }
        this.mARPEngine.getARPRenderer().cancelAysncRenderTask(this.mRenderCameraRunnable);
        this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(this.mRenderCameraRunnable);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void enableSyncRender(boolean z) {
        ARLog.m20417i("ARRenderer", "enableSyncRender enable = " + z);
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().setSourceSyncProperty(z);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void enableSyncFaceLandmark(boolean z) {
        if (this.mARPEngine != null) {
            this.mARPEngine.setFaceLandMarkFrameAcheMode(z ? 1 : 0);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setCacheFrameListener(OnNeedCacheFrameListener onNeedCacheFrameListener) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().setOnNeedCacheFrameListener(onNeedCacheFrameListener);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setSyncFrameTimestamp(long j) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().setAlgoPts(j);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setAlgoHandleData(long j, String str) {
        if (this.mARPEngine == null || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        AbilityData abilityData = new AbilityData();
        abilityData.setAbilityName(str);
        abilityData.setTimestamp(AlgoHandleAdapter.getHandleTimeStamp(j));
        abilityData.setFrontCameraData(AlgoHandleAdapter.getHandleIsFront(j));
        abilityData.setSyncWithCamera(AlgoHandleAdapter.getHandleEnableSync(j));
        if (isAbilityDataUnuseable(abilityData)) {
            return;
        }
        this.mARPEngine.setAlgoDataHandle(j);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void addAlgoCache(int i, boolean z) {
        if (this.mARPEngine == null || i < 0) {
            return;
        }
        ARLog.m20421d("ARRenderer", "addAlgoCache type = " + i + " && sync = " + z);
        this.mARPEngine.addAlgoType(new int[]{i}, z ? 1 : 0);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void removeAlgoCache(int i) {
        if (this.mARPEngine == null || i < 0) {
            return;
        }
        ARLog.m20421d("ARRenderer", "removeAlgoCache type = " + i);
        this.mARPEngine.removeAlgoType(new int[]{i});
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void clearAlgoCache() {
        if (this.mARPEngine != null) {
            this.mARPEngine.clearAlgoCache();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateDeviceOrientation() {
        onRotateOrientation(OrientationManager.getGlobalOrientation());
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public boolean set3DModelVisible(boolean z) {
        if (this.mARPEngine == null || this.mARPEngine.getCurrentScene() == null) {
            return false;
        }
        return this.mARPEngine.getCurrentScene().setVisible(z);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public String getCurrentCasePath() {
        return this.mCasePath;
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void resumeScene() {
        ARLog.m20421d("ARRenderer", "resumeScene()");
        if (this.mARPEngine != null) {
            this.mARPEngine.resumeScene();
            this.mSencePaused = false;
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void pauseScene() {
        ARLog.m20421d("ARRenderer", "pauseScene()");
        if (this.mARPEngine != null) {
            this.mSencePaused = true;
            this.mARPEngine.pauseScene();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setTouchEnable(boolean z) {
        if (this.mARPTouchInput != null) {
            this.mARPTouchInput.setUserInteractionEnabled(z);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void getSnapShot(TakePictureCallback takePictureCallback) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().getSnapShot(takePictureCallback, this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight(), OrientationManager.getGlobalOrientation().getDegree());
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setGLWebViewUseable(Context context, ViewGroup viewGroup) {
        if (this.mGLWebViewManager != null) {
            this.mGLWebViewManager.initialGLWebViewContext(context, viewGroup, this);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setNativeWebViewUseable(Context context, ViewGroup viewGroup) {
        if (this.mGLWebViewManager != null) {
            this.mGLWebViewManager.initialNativeWebViewContext(context, viewGroup, null);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void render(long j) {
        if (this.mARPEngine == null || !this.mEngineCreate) {
            return;
        }
        this.mARPEngine.getARPRenderer().render(j);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setFieldOfView(float f) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mFov = f;
        if (this.mFieldOfViewRunnable == null) {
            this.mFieldOfViewRunnable = new Runnable() { // from class: com.baidu.ar.arrender.ARRenderer.4
                @Override // java.lang.Runnable
                public void run() {
                    IARPCamera activeCamera;
                    IARPScene currentScene = ARRenderer.this.mARPEngine.getCurrentScene();
                    if (currentScene == null || (activeCamera = currentScene.getActiveCamera()) == null) {
                        return;
                    }
                    activeCamera.setFieldOfView(ARRenderer.this.mFov);
                }
            };
        }
        this.mARPEngine.getARPRenderer().cancelAysncRenderTask(this.mFieldOfViewRunnable);
        this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(this.mFieldOfViewRunnable);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setOffScreenGuideWork(boolean z) {
        IARPScene currentScene;
        if (this.mARPEngine == null || (currentScene = this.mARPEngine.getCurrentScene()) == null) {
            return;
        }
        currentScene.setOffScreenGuideWork(z);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public float[] location2ScreenPoint(float[] fArr) {
        IARPScene currentScene;
        if (this.mARPEngine != null && (currentScene = this.mARPEngine.getCurrentScene()) != null) {
            return currentScene.sceneProject(fArr);
        }
        return new float[0];
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void sceneWorldPositionToOrigin() {
        if (this.mARPEngine != null) {
            this.mARPEngine.sceneWorldPositionToOrigin();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void sceneRotateToCamera() {
        if (this.mARPEngine != null) {
            this.mARPEngine.sceneRotateToCamera();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public boolean isDriverdByARPVersion() {
        if (this.mARPEngine != null) {
            return this.mARPEngine.isDriverdByARPVersion();
        }
        return false;
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void sceneRelocate() {
        IARPScene currentScene;
        if (this.mARPEngine == null || (currentScene = this.mARPEngine.getCurrentScene()) == null) {
            return;
        }
        currentScene.relocate();
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setRootNodeEulerAngle(float f, float f2, float f3) {
        IARPNode rootNode;
        if (this.mARPEngine == null) {
            return;
        }
        Vector3f vector3f = new Vector3f((float) ((f * 3.141592653589793d) / 180.0d), (float) ((f2 * 3.141592653589793d) / 180.0d), (float) ((f3 * 3.141592653589793d) / 180.0d));
        IARPScene currentScene = this.mARPEngine.getCurrentScene();
        if (currentScene == null || (rootNode = currentScene.getRootNode()) == null) {
            return;
        }
        rootNode.setEulerAnges(vector3f);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setRootNodeRotation(float f, float f2, float f3) {
        IARPNode rootNode;
        if (this.mARPEngine == null) {
            return;
        }
        Quaternion quaternion = new Quaternion();
        Quaternion quaternion2 = new Quaternion();
        quaternion2.setAxisAngle(new Vector3f(0.0f, 0.0f, 1.0f), f);
        Quaternion quaternion3 = new Quaternion();
        quaternion3.setAxisAngle(new Vector3f(1.0f, 0.0f, 0.0f), f2);
        Quaternion quaternion4 = new Quaternion();
        quaternion4.setAxisAngle(new Vector3f(0.0f, 0.0f, 1.0f), f3);
        quaternion.multiplyByQuat(quaternion2);
        quaternion.multiplyByQuat(quaternion3);
        quaternion.multiplyByQuat(quaternion4);
        Vector4f vector4f = new Vector4f();
        quaternion.toAxisAngle(vector4f);
        vector4f.setW((float) Math.toRadians(vector4f.getW()));
        IARPScene currentScene = this.mARPEngine.getCurrentScene();
        if (currentScene == null || (rootNode = currentScene.getRootNode()) == null) {
            return;
        }
        rootNode.setRotation(vector4f);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void calibrationTouchAngle() {
        if (this.mImuOrientationManager != null) {
            this.mImuOrientationManager.calibrationTouchAngle();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setImuType(Coordinate coordinate) {
        if (this.mImuOrientationManager != null) {
            this.mImuOrientationManager.setImuType(coordinate.getTypeValue());
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void initWorldAxis() {
        if (this.mARPEngine != null) {
            this.mARPEngine.initWorldAxis();
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public Matrixf4x4 getInitialTransform() {
        IARPScene currentScene;
        IARPCamera activeCamera;
        if (this.mARPEngine == null || (currentScene = this.mARPEngine.getCurrentScene()) == null || (activeCamera = currentScene.getActiveCamera()) == null) {
            return null;
        }
        return activeCamera.getInitialTransform();
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void updateTransforms(Matrixf4x4 matrixf4x4) {
        IARPScene currentScene;
        if (this.mARPEngine == null || (currentScene = this.mARPEngine.getCurrentScene()) == null) {
            return;
        }
        currentScene.getActiveCamera().setTransform(matrixf4x4);
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setInteractionCallback(ARPDataInteraction.IInteraction iInteraction) {
        if (this.mARPEngine != null) {
            this.mARPEngine.setInteraction(iInteraction);
        }
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void setEnvironmentDataPipKV(final String str, final Object obj) {
        if (this.mARPEngine == null || this.mARPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().runAsyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arrender.ARRenderer.5
            @Override // java.lang.Runnable
            public void run() {
                if (ARRenderer.this.mARPConfig != null) {
                    ARRenderer.this.mARPConfig.setDataPipKV(str, obj);
                }
            }
        });
    }

    @Override // com.baidu.p120ar.arrender.IARRenderer
    public void convertAlgo2ScreenPoint(PointF pointF, boolean z) {
        ARRenderHelper.convertAlgo2ScreenPoint(pointF, z, this.mDuMixInput, this.mDuMixOutput, this.isActivityLandscape);
    }

    @Override // com.baidu.p120ar.arplay.core.engine.rotate.OrientationManager.OrientationListener
    public void onRotateOrientation(Orientation orientation) {
        if (this.mEngineMsgBridge != null) {
            ARLog.m20421d("ARRenderer", "sendOrientation2Render orientation = " + orientation);
            this.mEngineMsgBridge.sendMsg2Engine(4001, ARRenderHelper.getOrientationLuaMsg(orientation));
        }
    }

    public void setAuthTip(Bitmap bitmap, float f, float f2, float f3, float f4) {
        if (this.mARPEngine != null) {
            this.mARPEngine.setAuthPic(bitmap, new float[]{f, f2, f3, f4});
        }
    }

    @Override // com.baidu.p120ar.arrender.ARRendererBase
    protected void onCameraSwitch(boolean z) {
        ARLog.m20421d("ARRenderer", "onCameraSwitch front = " + z);
        super.onCameraSwitch(z);
        if (this.mARPEngine != null && this.mARPEngine.getARPRenderer() != null) {
            this.mARPEngine.getARPRenderer().setCameraFace(z);
        }
        if (this.mARPEngine != null) {
            this.mARPEngine.setIsFrontCamera(z);
        }
        if (this.mDuMixInput != null && this.mDuMixInput.isCameraInput()) {
            this.mDuMixInput.setFrontCamera(z);
        }
        sendCameraOpenMsg2Lua(!z);
        updateDeviceOrientation();
        this.mSkipCount = 3;
    }

    protected boolean isAbilityDataUnuseable(AbilityData abilityData) {
        return !abilityData.isControllData() && (isAbilityClose(abilityData) || isCameraFaceWrong(abilityData));
    }

    private void checkSkipEngineRender() {
        int i;
        if (!this.mEngineCreate || (i = this.mSkipCount) < 0) {
            return;
        }
        if (i == 3) {
            this.mARPEngine.setEngineBlendState(0);
        } else if (i == 0) {
            this.mARPEngine.setEngineBlendState(1);
        }
        this.mSkipCount--;
    }

    private void sendCameraOpenMsg2Lua(boolean z) {
        if (this.mEngineMsgBridge == null) {
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 10200);
        hashMap.put("front_camera", Integer.valueOf(!z ? 1 : 0));
        this.mEngineMsgBridge.sendMsg2Engine(1902, hashMap);
    }

    private boolean isAbilityClose(AbilityData abilityData) {
        List<String> list = this.mEnabledAbilities;
        return list == null || !list.contains(abilityData.getAbilityName());
    }

    private boolean isCameraFaceWrong(AbilityData abilityData) {
        return abilityData.isSyncWithCamera() && abilityData.isFrontCameraData() != this.mCameraFaceFront;
    }

    public void setARRenderFpsCallback(ARRenderFpsCallback aRRenderFpsCallback) {
        this.mARRenderFpsCallback = aRRenderFpsCallback;
    }
}
