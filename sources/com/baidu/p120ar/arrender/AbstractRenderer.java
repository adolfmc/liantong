package com.baidu.p120ar.arrender;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.EGLContext;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.baidu.p120ar.DuMixInput;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.DuMixStateListener;
import com.baidu.p120ar.arplay.component.ImuOrientationManager;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;
import com.baidu.p120ar.arplay.core.engine.ARPEngineParams;
import com.baidu.p120ar.arplay.core.engine.SourceInternalTexType;
import com.baidu.p120ar.arplay.core.pixel.PixelReadListener;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arplay.webview.GLWebViewManager;
import com.baidu.p120ar.bean.RotationType;
import com.baidu.p120ar.bean.Size;
import com.baidu.p120ar.libloader.ILibLoader;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.statistic.StatisticAbility;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.FileUtils;
import com.baidu.p120ar.utils.ScreenUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.lang.ref.SoftReference;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.ar.arrender.AbstractRenderer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractRenderer implements SurfaceTexture.OnFrameAvailableListener, IRendererLifecycle {
    static final String CONFIG_KEY_GRADING = "grading";
    private static final String TAG = "AbstractRenderer";
    String m3dShaderDBPath;
    ARPConfig mARPConfig;
    ARPEngine mARPEngine;
    private String mAbilitySchemeConfig;
    Context mContext;
    private int mDeviceGrade;
    DuMixInput mDuMixInput;
    DuMixOutput mDuMixOutput;
    DuMixStateListener mDuMixStateListener;
    EngineMsgBridge mEngineMsgBridge;
    private Size mEngineSize;
    GLWebViewManager mGLWebViewManager;
    ImuOrientationManager mImuOrientationManager;
    private RendererTarget mRendererTarget;
    boolean mSencePaused;
    protected EGLContext mSharedEGLContext;
    private boolean mInputSurfaceCreate = false;
    private boolean mOutputSurfaceCreate = false;
    private long mInputTextureHandle = 0;
    String mRenderPipeline = "filter_pipeline = function()\n\n    fm = ae.FilterManager:get_instance();\n\n    global_copy_filter = fm:create_filter(\"Tex2DFilter\", \"globalTex2DFilter\", true);\n    gl_makeup_filer = fm:create_filter(\"BeautyMakeupFilter\",\"globalBeautyMakeupFilter\",true);\n\n    skin_filter = fm:create_filter(\"SkinFilter\", \"globalSkinFilter\", true);\n    engine_filter = fm:create_filter(\"EngineFilter\", \"globalEngineFilter\", true);\n    fm:update_property_int(engine_filter, \"is_enable\", 0);\n    face_filter = fm:create_filter(\"FaceFilter\", \"globalFaceFilter\", true);\n    lut_filter = fm:create_filter(\"LUTFilter\", \"globalLutFilter\", true);\n    tune_color_filter = fm:create_filter(\"TuneColorFilter\", \"globalTuneColorFilter\", true);\n    fm:reset_pipeline();\n    fm:connect_filters_by_id(skin_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(skin_filter, global_copy_filter);\n    fm:connect_filters_by_id(global_copy_filter, gl_makeup_filer);\n    fm:connect_filters_by_id(gl_makeup_filer, face_filter);\n    fm:connect_filters_by_id(face_filter, tune_color_filter);\n    fm:connect_filters_by_id(tune_color_filter, engine_filter);\n    fm:connect_filters_by_id(engine_filter, lut_filter);\n\n    fm:connect_filter_to_camera(skin_filter);\n    fm:connect_filter_to_output(lut_filter);\n\nend\nfilter_pipeline()\n\n";
    volatile boolean mEngineCreate = false;
    String mCasePath = null;
    private boolean mEngineLoading = false;
    protected boolean mInterruptLoading = false;
    volatile boolean mReleasing = false;
    volatile boolean mAttached = false;
    private boolean mIsSwitchSize = false;

    public void changeInput(DuMixInput duMixInput) {
    }

    public void changeInputSize(int i, int i2) {
    }

    abstract void initRenderParams();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractRenderer(Context context, EngineMsgBridge engineMsgBridge, EGLContext eGLContext, String str) {
        this.m3dShaderDBPath = null;
        this.mSharedEGLContext = null;
        ARLog.m20421d("AbstractRenderer", "create start!!!");
        LibLoader.require("BARDumix");
        this.mContext = context;
        this.mEngineMsgBridge = engineMsgBridge;
        this.mSharedEGLContext = eGLContext;
        this.mImuOrientationManager = new ImuOrientationManager(context);
        this.mGLWebViewManager = GLWebViewManager.getInstance();
        this.mARPConfig = new ARPConfig(context);
        StatisticAbility.init(this.mEngineMsgBridge);
        this.m3dShaderDBPath = str;
        this.mARPEngine = ARPEngine.getInstance();
        this.mARPEngine.setContext(new SoftReference<>(context));
        ARLog.m20421d("AbstractRenderer", "create end!!!");
    }

    public void setup(DuMixInput duMixInput, DuMixOutput duMixOutput) {
        ARPEngine aRPEngine;
        ARLog.m20421d("AbstractRenderer", "setup() start");
        if (duMixInput == null || duMixOutput == null || (aRPEngine = this.mARPEngine) == null || aRPEngine.getARPRenderer() == null) {
            return;
        }
        this.mDuMixInput = duMixInput;
        this.mDuMixOutput = duMixOutput;
        initRenderParams();
        initAREngine(duMixInput);
        initInputSurface(duMixInput);
        initOutputSurface(duMixOutput, null);
        renderInput2Output();
        requireARPEngine2Load();
        ARLog.m20421d("AbstractRenderer", "setup() end");
    }

    public void resume() {
        ARLog.m20421d("AbstractRenderer", "resume()");
        try {
            this.mImuOrientationManager.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.resume();
        }
    }

    public void pause() {
        ARLog.m20421d("AbstractRenderer", "pause()");
        ImuOrientationManager imuOrientationManager = this.mImuOrientationManager;
        if (imuOrientationManager != null) {
            imuOrientationManager.release();
        }
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.pause();
        }
    }

    public void release() {
        ARLog.m20421d("AbstractRenderer", "release() start!!!");
        this.mReleasing = true;
        pause();
        GLWebViewManager gLWebViewManager = this.mGLWebViewManager;
        if (gLWebViewManager != null) {
            gLWebViewManager.release();
            this.mGLWebViewManager = null;
        }
        StatisticAbility.release(this.mEngineMsgBridge);
        stopARPEngine();
        this.mDuMixStateListener = null;
        releaseInput();
        this.mDuMixInput = null;
        releaseOutput();
        this.mDuMixOutput = null;
        this.mRendererTarget = null;
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.destroy();
            this.mARPEngine = null;
        }
        ARPEngine.releaseInstance();
        this.mContext = null;
        this.mSharedEGLContext = null;
        ARLog.m20421d("AbstractRenderer", "release() end!!!");
    }

    public void changeInput(Object obj, int i, int i2) {
        if (this.mDuMixInput == null || this.mARPEngine == null) {
            return;
        }
        releaseInput();
        this.mDuMixInput.setInputWidth(i);
        this.mDuMixInput.setInputHeight(i2);
        if (obj instanceof SurfaceTexture) {
            this.mDuMixInput.setInputSurface((SurfaceTexture) obj);
        } else if (obj instanceof Texture) {
            this.mDuMixInput.setInputTexture((Texture) obj);
        }
        initInputSurface(this.mDuMixInput);
        touchEngineToOutputSize(this.mIsSwitchSize);
    }

    public void changeOutputSize(int i, int i2) {
        DuMixOutput duMixOutput;
        RendererTarget rendererTarget;
        if (this.mDuMixInput == null || (duMixOutput = this.mDuMixOutput) == null) {
            return;
        }
        if (duMixOutput.getOutputHeight() == i2 && this.mDuMixOutput.getOutputWidth() == i) {
            return;
        }
        ARLog.m20421d("AbstractRenderer", "changeOutputSize() size = " + i + "x" + i2);
        Object outputSurface = this.mDuMixOutput.getOutputSurface();
        if (outputSurface instanceof SurfaceTexture) {
            ((SurfaceTexture) outputSurface).setDefaultBufferSize(i, i2);
        }
        this.mDuMixOutput.setOutputWidth(i);
        this.mDuMixOutput.setOutputHeight(i2);
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null && aRPEngine.getARPRenderer() != null && (rendererTarget = this.mRendererTarget) != null && !TextUtils.isEmpty(rendererTarget.getSurfaceHandle())) {
            this.mARPEngine.getARPRenderer().addOutputSurface(this.mRendererTarget.getSurface(), i, i2);
        }
        if (this.mARPEngine != null) {
            Size engineOutputSize = getEngineOutputSize(getEngineInputSize(this.mDuMixInput), this.mDuMixOutput);
            this.mARPEngine.setWindowSize(engineOutputSize.getWidth(), engineOutputSize.getHeight());
        }
        if (this.mDuMixOutput.isFitScreenAuto()) {
            setOutputRotation();
        }
    }

    public void changeOutput(Object obj, int i, int i2) {
        if (obj == null || i <= 0 || i2 <= 0 || this.mDuMixOutput == null || !this.mEngineCreate) {
            ARLog.m20419e("AbstractRenderer", "changeOutputSurface error!!!");
            return;
        }
        releaseOutput();
        if (obj instanceof SurfaceTexture) {
            this.mDuMixOutput.setOutputSurface((SurfaceTexture) obj);
        } else if (obj instanceof SurfaceHolder) {
            this.mDuMixOutput.setOutputSurface((SurfaceHolder) obj);
        } else if (obj instanceof Surface) {
            this.mDuMixOutput.setOutputSurface((Surface) obj);
        } else {
            ARLog.m20419e("AbstractRenderer", "changeOutputSurface error!!! As outputSurface is not a surface!!!");
            return;
        }
        this.mDuMixOutput.setOutputWidth(i);
        this.mDuMixOutput.setOutputHeight(i2);
        initOutputSurface(this.mDuMixOutput, obj);
    }

    public void changeOutput(DuMixOutput duMixOutput) {
        releaseOutput();
        this.mDuMixOutput = duMixOutput;
        initOutputSurface(duMixOutput, null);
    }

    public void createPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        ARPEngine aRPEngine;
        if (!this.mEngineCreate || (aRPEngine = this.mARPEngine) == null || aRPEngine.getARPRenderer() == null) {
            return;
        }
        ARRenderHelper.adaptSpecialPhonePixelRotate(this.mContext, this.mDuMixInput.isFrontCamera(), pixelReadParams);
        this.mARPEngine.getARPRenderer().createPixelReaderByPreFilterID(pixelReadParams, pixelReadListener);
    }

    public void updatePixelReader(PixelReadParams pixelReadParams, PixelRotation pixelRotation) {
        ARPEngine aRPEngine;
        if (!this.mEngineCreate || (aRPEngine = this.mARPEngine) == null || aRPEngine.getARPRenderer() == null) {
            return;
        }
        pixelReadParams.setPixelRotate(pixelRotation);
        ARRenderHelper.adaptSpecialPhonePixelRotate(this.mContext, this.mDuMixInput.isFrontCamera(), pixelReadParams);
        this.mARPEngine.getARPRenderer().setPixelReaderRotation(pixelReadParams, pixelReadParams.getPixelRotate());
    }

    public void destroyPixelReader(PixelReadParams pixelReadParams, PixelReadListener pixelReadListener) {
        ARPEngine aRPEngine;
        if (!this.mEngineCreate || (aRPEngine = this.mARPEngine) == null || aRPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().destroyPixelReaderByPreFilterID(pixelReadParams, pixelReadListener);
    }

    public void destroyAllPixelReaders() {
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine == null || aRPEngine.getARPRenderer() == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().destroyAllPixelReader();
    }

    public boolean isEngineLoading() {
        return this.mEngineLoading;
    }

    public void setInterruptLoading(boolean z) {
        this.mInterruptLoading = z;
    }

    public void setAbilityScheme(JSONObject jSONObject) {
        if (this.mARPEngine == null || jSONObject == null) {
            return;
        }
        this.mAbilitySchemeConfig = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        try {
            this.mARPEngine.setConfig("grading", this.mAbilitySchemeConfig);
        } catch (Throwable unused) {
        }
    }

    public void setLocalDeviceGrade(int i) {
        this.mDeviceGrade = i;
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            try {
                aRPEngine.setLocalDeviceGrade(i);
            } catch (Throwable unused) {
            }
        }
    }

    public void startARPEngine() {
        DuMixInput duMixInput;
        ARLog.m20421d("AbstractRenderer", "startARPEngine()");
        if (this.mARPEngine == null) {
            this.mARPEngine = ARPEngine.getInstance();
        }
        if (this.mARPEngine != null && (duMixInput = this.mDuMixInput) != null && this.mDuMixOutput != null) {
            Size engineInputSize = getEngineInputSize(duMixInput);
            Size engineOutputSize = getEngineOutputSize(engineInputSize, this.mDuMixOutput);
            ARPEngineParams aRPEngineParams = new ARPEngineParams();
            aRPEngineParams.setInputWidth(engineInputSize.getWidth());
            aRPEngineParams.setInputHeight(engineInputSize.getHeight());
            aRPEngineParams.setOutputWidth(engineOutputSize.getWidth());
            aRPEngineParams.setOutputHeight(engineOutputSize.getHeight());
            aRPEngineParams.setDensity(ScreenUtils.getScreenDensity(this.mContext));
            if (this.mDuMixInput.isCameraInput()) {
                aRPEngineParams.setIsFrontCamera(this.mDuMixInput.isFrontCamera());
            }
            aRPEngineParams.setShaderDBPath(this.m3dShaderDBPath);
            this.mARPEngine.createEngine(aRPEngineParams);
        }
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.resume();
        }
        ARPConfig aRPConfig = this.mARPConfig;
        if (aRPConfig != null) {
            aRPConfig.updateARPConfig2Engine();
        }
        try {
            if (this.mImuOrientationManager != null) {
                this.mImuOrientationManager.start();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public void stopARPEngine() {
        ARLog.m20421d("AbstractRenderer", "stopARPEngine()");
        if (this.mARPEngine != null && this.mEngineCreate) {
            this.mARPEngine.destroyEngine();
            this.mEngineCreate = false;
        } else {
            EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
            if (engineMsgBridge != null) {
                engineMsgBridge.handleMessage(7, 0, null);
            }
        }
        this.mARPConfig = null;
        ImuOrientationManager imuOrientationManager = this.mImuOrientationManager;
        if (imuOrientationManager != null) {
            imuOrientationManager.release();
        }
    }

    public void createCase(String str) {
        ARLog.m20421d("AbstractRenderer", "createCase() casePath = " + str);
        ARPConfig aRPConfig = this.mARPConfig;
        if (aRPConfig != null) {
            aRPConfig.updateARPConfig2Engine();
        }
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.loadCaseWithResPath(str);
            DuMixOutput duMixOutput = this.mDuMixOutput;
            if (duMixOutput != null) {
                changeOutputSize(duMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight());
            }
        }
        this.mCasePath = str;
        prepareWebViewResource(str);
    }

    public void touchEngineToOutputSize(boolean z) {
        DuMixInput duMixInput = this.mDuMixInput;
        if (duMixInput == null) {
            return;
        }
        this.mIsSwitchSize = z;
        if (z) {
            this.mEngineSize = ARRenderHelper.calResulotion(this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight(), this.mDuMixInput.getInputHeight(), this.mDuMixInput.getInputWidth());
            this.mARPEngine.setPreviewSize(this.mEngineSize.getWidth(), this.mEngineSize.getHeight());
            this.mARPEngine.setWindowSize(this.mDuMixOutput.getOutputWidth(), this.mDuMixOutput.getOutputHeight());
            return;
        }
        this.mEngineSize = null;
        Size engineInputSize = getEngineInputSize(duMixInput);
        Size engineOutputSize = getEngineOutputSize(engineInputSize, this.mDuMixOutput);
        this.mARPEngine.setPreviewSize(engineInputSize.getWidth(), engineInputSize.getHeight());
        this.mARPEngine.setWindowSize(engineOutputSize.getWidth(), engineOutputSize.getHeight());
    }

    public void destroyCase() {
        ARLog.m20421d("AbstractRenderer", "destroyCase()");
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null) {
            aRPEngine.unloadCase();
        }
        resetDefaultPipeLine();
        ARPEngine aRPEngine2 = this.mARPEngine;
        if (aRPEngine2 != null && aRPEngine2.getARPRenderer() != null) {
            this.mARPEngine.getARPRenderer().purgeMemory();
        }
        this.mCasePath = null;
        GLWebViewManager gLWebViewManager = this.mGLWebViewManager;
        if (gLWebViewManager != null) {
            gLWebViewManager.setResDir(null);
        }
    }

    public void setEngineCreate(boolean z) {
        this.mEngineCreate = z;
    }

    protected Size getEngineInputSize(DuMixInput duMixInput) {
        if (duMixInput == null) {
            ARLog.m20419e("AbstractRenderer", "getEngineInputSize duMixInput is null!!!");
            return null;
        }
        Size size = this.mEngineSize;
        if (size != null && size.getWidth() != 0 && this.mEngineSize.getHeight() != 0) {
            return this.mEngineSize;
        }
        Size size2 = new Size(duMixInput.getInputHeight(), duMixInput.getInputWidth());
        if (!duMixInput.isCameraInput() && (duMixInput.getRotationType() == RotationType.ROTATE_0 || duMixInput.getRotationType() == RotationType.ROTATE_180)) {
            size2.setWidth(duMixInput.getInputWidth());
            size2.setHeight(duMixInput.getInputHeight());
        }
        return size2;
    }

    protected Size getEngineOutputSize(Size size, DuMixOutput duMixOutput) {
        if (ScreenUtils.isScreenOrientationLandscape(this.mContext)) {
            return ARRenderHelper.calWindowSize(size.getWidth(), size.getHeight(), duMixOutput.getOutputHeight(), duMixOutput.getOutputWidth());
        }
        return ARRenderHelper.calWindowSize(size.getWidth(), size.getHeight(), duMixOutput.getOutputWidth(), duMixOutput.getOutputHeight());
    }

    private void initAREngine(DuMixInput duMixInput) {
        this.mARPEngine.getARPRenderer().setUpEGLEnv(this.mSharedEGLContext);
        this.mARPEngine.getARPRenderer().setCameraFace(duMixInput.isFrontCamera());
        PixelRotation inputPixelRotation = ARRenderHelper.getInputPixelRotation(duMixInput.isFitCameraAuto(), duMixInput.getRotationType(), duMixInput.getMirriorType());
        SourceInternalTexType sourceInternalTexType = SourceInternalTexType.INTERNAL_OES_TEX;
        if ((duMixInput.getInputTexture() != null && duMixInput.getInputTexture().getType() == 3553) || (duMixInput.getInputSurface() != null && !duMixInput.isCameraInput())) {
            sourceInternalTexType = SourceInternalTexType.INTERNAL_2D_TEX;
        }
        if (duMixInput.isSyncInputContent()) {
            this.mARPEngine.getARPRenderer().createSyncInputSource(inputPixelRotation, sourceInternalTexType);
        } else {
            this.mARPEngine.getARPRenderer().createInputSource(inputPixelRotation, sourceInternalTexType);
        }
        Size engineInputSize = getEngineInputSize(this.mDuMixInput);
        this.mARPEngine.setPreviewSize(engineInputSize.getWidth(), engineInputSize.getHeight());
    }

    void initInputSurface(DuMixInput duMixInput) {
        if (duMixInput.getInputTexture() != null) {
            if (duMixInput.getInputTexture().getId() == -1) {
                long createTexture = this.mARPEngine.getARPRenderer().createTexture(duMixInput.getInputTexture().getType(), duMixInput.getInputWidth(), duMixInput.getInputHeight());
                duMixInput.getInputTexture().setHandle(createTexture);
                duMixInput.getInputTexture().setId(this.mARPEngine.getARPRenderer().getTextureId(createTexture));
            }
            this.mARPEngine.getARPRenderer().setInputTexture(duMixInput.getInputTexture().getType(), duMixInput.getInputTexture().getId(), duMixInput.getInputWidth(), duMixInput.getInputHeight());
            return;
        }
        int i = duMixInput.isCameraInput() ? 36197 : 3553;
        if (duMixInput.getInputSurface() == null) {
            duMixInput.setInputSurface(createInputSurface(i, duMixInput.getInputWidth(), duMixInput.getInputHeight()));
            this.mInputSurfaceCreate = true;
            return;
        }
        attachInputSurface(duMixInput.getInputSurface(), i, duMixInput.getInputWidth(), duMixInput.getInputHeight());
    }

    void initOutputSurface(DuMixOutput duMixOutput, Object obj) {
        Surface outputSurface;
        String addOutputSurface;
        if (duMixOutput.getOutputTexture() != null && duMixOutput.getOutputTexture().getId() != -1) {
            addOutputSurface = this.mARPEngine.getARPRenderer().addOutputTarget(duMixOutput.getOutputTexture().getType(), duMixOutput.getOutputTexture().getId(), duMixOutput.getOutputWidth(), duMixOutput.getOutputHeight(), ARRenderHelper.getPixelRotation(this.mDuMixOutput.getRotationType(), this.mDuMixOutput.getMirriorType()));
            outputSurface = null;
        } else {
            outputSurface = getOutputSurface(duMixOutput, obj);
            addOutputSurface = this.mARPEngine.getARPRenderer().addOutputSurface(outputSurface, duMixOutput.getOutputWidth(), duMixOutput.getOutputHeight(), ARRenderHelper.getPixelRotation(duMixOutput.getRotationType(), duMixOutput.getMirriorType()), ARRenderHelper.getOutputFillMode(duMixOutput.getScaleType()));
        }
        this.mRendererTarget = new RendererTarget(duMixOutput);
        this.mRendererTarget.setSurface(outputSurface);
        this.mRendererTarget.setSurfaceHandle(addOutputSurface);
        if (duMixOutput.isFitScreenAuto()) {
            setOutputRotation();
        }
        Size engineOutputSize = getEngineOutputSize(getEngineInputSize(this.mDuMixInput), this.mDuMixOutput);
        this.mARPEngine.setWindowSize(engineOutputSize.getWidth(), engineOutputSize.getHeight());
    }

    private SurfaceTexture createInputSurface(int i, int i2, int i3) {
        this.mInputTextureHandle = this.mARPEngine.getARPRenderer().createTexture(i, i2, i3);
        int textureId = this.mARPEngine.getARPRenderer().getTextureId(this.mInputTextureHandle);
        SurfaceTexture surfaceTexture = new SurfaceTexture(textureId);
        surfaceTexture.setDefaultBufferSize(i2, i3);
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mARPEngine.getARPRenderer().setInputTexture(i, textureId, i2, i3);
        this.mAttached = true;
        return surfaceTexture;
    }

    private void attachInputSurface(final SurfaceTexture surfaceTexture, int i, int i2, int i3) {
        final int textureId = this.mARPEngine.getARPRenderer().getTextureId(this.mARPEngine.getARPRenderer().createTexture(i, i2, i3));
        surfaceTexture.setOnFrameAvailableListener(this);
        this.mARPEngine.getARPRenderer().setInputTexture(i, textureId, i2, i3);
        try {
            surfaceTexture.detachFromGLContext();
        } catch (Exception unused) {
            ARLog.m20413w("AbstractRenderer", "attachInputSurface surfaceTexture.detachFromGLContext() fail!!!");
        }
        this.mARPEngine.getARPRenderer().runSyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arrender.AbstractRenderer.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    surfaceTexture.attachToGLContext(textureId);
                    AbstractRenderer.this.mAttached = true;
                    surfaceTexture.updateTexImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        surfaceTexture.setDefaultBufferSize(i2, i3);
        DuMixStateListener duMixStateListener = this.mDuMixStateListener;
        if (duMixStateListener != null) {
            duMixStateListener.onInputSurfaceTextureAttach(surfaceTexture);
        }
    }

    private Surface getOutputSurface(DuMixOutput duMixOutput, Object obj) {
        Object outputSurface = duMixOutput.getOutputSurface();
        if (obj == null) {
            obj = outputSurface;
        }
        if (obj == null) {
            createOutputSurface();
            this.mOutputSurfaceCreate = true;
        }
        Surface surface = null;
        if (obj instanceof SurfaceHolder) {
            SurfaceHolder surfaceHolder = (SurfaceHolder) obj;
            surface = surfaceHolder.getSurface();
            duMixOutput.setOutputSurface(surfaceHolder);
        } else if (obj instanceof SurfaceTexture) {
            SurfaceTexture surfaceTexture = (SurfaceTexture) obj;
            surfaceTexture.setDefaultBufferSize(duMixOutput.getOutputWidth(), duMixOutput.getOutputHeight());
            surface = new Surface(surfaceTexture);
            duMixOutput.setOutputSurface(surfaceTexture);
        } else if (obj instanceof Surface) {
            surface = (Surface) obj;
            duMixOutput.setOutputSurface(surface);
        }
        if (surface == null) {
            ARLog.m20419e("AbstractRenderer", "initOutputSurface outputSurface error!!!");
        }
        return surface;
    }

    private void createOutputSurface() {
        this.mARPEngine.getARPRenderer().runSyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arrender.AbstractRenderer.2
            @Override // java.lang.Runnable
            public void run() {
                int createTexture = (int) AbstractRenderer.this.mARPEngine.getARPRenderer().createTexture(3553, AbstractRenderer.this.mDuMixOutput.getOutputWidth(), AbstractRenderer.this.mDuMixOutput.getOutputHeight());
                ARLog.m20421d("AbstractRenderer", "setup outputTextureId = " + createTexture);
                AbstractRenderer.this.mDuMixOutput.setOutputSurface(new SurfaceTexture(createTexture));
            }
        });
    }

    void requireARPEngine2Load() {
        this.mEngineLoading = true;
        LibLoader.require("BARDumix");
        LibLoader.setLibReadyListener("BARDumix", new ILibLoader.ReadyListener() { // from class: com.baidu.ar.arrender.AbstractRenderer.3
            @Override // com.baidu.p120ar.libloader.ILibLoader.ReadyListener
            public void onReady() {
                AbstractRenderer.this.mEngineLoading = false;
                if (AbstractRenderer.this.mInterruptLoading) {
                    return;
                }
                AbstractRenderer.this.onARPEngineLoaded();
            }
        });
    }

    void onARPEngineLoaded() {
        if (this.mARPEngine != null) {
            if (!TextUtils.isEmpty(this.mAbilitySchemeConfig)) {
                try {
                    this.mARPEngine.setConfig("grading", this.mAbilitySchemeConfig);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                try {
                    this.mARPEngine.setLocalDeviceGrade(this.mDeviceGrade);
                } catch (Throwable th2) {
                    th2.printStackTrace();
                }
            }
        }
        resetDefaultPipeLine();
        startARPEngine();
    }

    private void renderInput2Output() {
        this.mARPEngine.getARPRenderer().connectCameraWithTarget();
    }

    private void resetDefaultPipeLine() {
        this.mARPEngine.getARPRenderer().runLuaScriptStr(this.mRenderPipeline);
    }

    private void releaseInput() {
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine != null && aRPEngine.getARPRenderer() != null) {
            this.mARPEngine.getARPRenderer().runSyncOnRenderContext(new Runnable() { // from class: com.baidu.ar.arrender.AbstractRenderer.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (AbstractRenderer.this.mDuMixInput == null || AbstractRenderer.this.mDuMixInput.getInputSurface() == null) {
                            return;
                        }
                        AbstractRenderer.this.mAttached = false;
                        AbstractRenderer.this.mDuMixInput.getInputSurface().detachFromGLContext();
                    } catch (Exception unused) {
                        ARLog.m20417i("AbstractRenderer", "releaseInput() surfaceTexture.detachFromGLContext() fail!!!");
                    }
                }
            });
        }
        DuMixInput duMixInput = this.mDuMixInput;
        if (duMixInput != null && duMixInput.getInputSurface() != null) {
            this.mDuMixInput.getInputSurface().setOnFrameAvailableListener(null);
            if (this.mInputSurfaceCreate) {
                this.mDuMixInput.getInputSurface().release();
            }
        }
        ARPEngine aRPEngine2 = this.mARPEngine;
        if (aRPEngine2 == null || aRPEngine2.getARPRenderer() == null || this.mInputTextureHandle <= 0) {
            return;
        }
        this.mARPEngine.getARPRenderer().destroyTexture(this.mInputTextureHandle);
    }

    private void releaseOutput() {
        DuMixOutput duMixOutput = this.mDuMixOutput;
        if (duMixOutput != null && duMixOutput.getOutputSurface() != null && this.mOutputSurfaceCreate) {
            ((SurfaceTexture) this.mDuMixOutput.getOutputSurface()).release();
        }
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine == null || aRPEngine.getARPRenderer() == null || this.mRendererTarget == null) {
            return;
        }
        this.mARPEngine.getARPRenderer().removeOutputTargetByAddr(this.mRendererTarget.getSurfaceHandle());
    }

    private void setOutputRotation() {
        ARPEngine aRPEngine = this.mARPEngine;
        if (aRPEngine == null || aRPEngine.getARPRenderer() == null || this.mRendererTarget == null) {
            return;
        }
        if (ScreenUtils.isScreenOrientationLandscape(this.mContext)) {
            this.mARPEngine.getARPRenderer().updateOutputSurfaceRotation(this.mRendererTarget.getSurfaceHandle(), PixelRotation.RotateLeft);
        } else {
            this.mARPEngine.getARPRenderer().updateOutputSurfaceRotation(this.mRendererTarget.getSurfaceHandle(), PixelRotation.NoRotation);
        }
    }

    private void prepareWebViewResource(String str) {
        GLWebViewManager gLWebViewManager = this.mGLWebViewManager;
        if (gLWebViewManager != null) {
            gLWebViewManager.setResDir(str);
            File file = new File(str, "res/webview");
            if (file.exists()) {
                File file2 = new File(this.mContext.getFilesDir(), "ar/res/webview");
                FileUtils.ensureDir(file2);
                FileUtils.copyDir(file, file2, true);
            }
        }
    }
}
