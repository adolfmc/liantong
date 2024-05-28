package com.baidu.p120ar.arplay.core.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import com.baidu.p120ar.arplay.core.engine.ARPContent;
import com.baidu.p120ar.arplay.core.engine.ARPDataInteraction;
import com.baidu.p120ar.arplay.core.engine.engine3d.AbstractARPEngine3D;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPEngine3D;
import com.baidu.p120ar.arplay.core.engine.engine3d.IARPScene;
import com.baidu.p120ar.arplay.core.renderer.ARPFilter;
import com.baidu.p120ar.arplay.core.renderer.ARPRenderer;
import com.baidu.p120ar.arplay.core.renderer.IARPRenderer;
import com.baidu.p120ar.arplay.util.LogUtil;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.ARPEngine */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPEngine implements IARPLifeCycle {
    private static final String ENGINE_3D_CLASS_NAME = "com.baidu.ar.arplay.core.engine3d.ARPEngine3D";
    private static final String LOWEST_VERSION_KEY = "compatible_version";
    private static final String TAG = "ARPEngine";
    private static ARPEngine self;
    private AbstractARPEngine3D mARPEngine3D;
    private ARPEngineParams mARPEngineParams;
    boolean mScenePausedByUser;
    private boolean mIsInitNative = false;
    private volatile boolean mIsEngineCreated = false;
    private boolean mIsPaused = false;
    private ARPContent mARPContent = new ARPContent();
    private ARPFilter mARPFilter = new ARPFilter();
    private ARPRenderer mARPRenderer = new ARPRenderer();
    private ARPDataInteraction mARPDataInteraction = new ARPDataInteraction();

    public static native boolean libraryHasLoaded();

    private native boolean nativeSetup(Object obj, String str);

    native boolean nativeCreateApp(int i, int i2, int i3, int i4, float f, String str);

    native void nativeDestroyEngine();

    native boolean nativeIsAppControllerInterrupt();

    native void nativeOnPause();

    native void nativeOnResume();

    native void nativeSetConfig(String str, String str2);

    native void nativeSetEngineBlendState(int i);

    native void nativeSetLocalDeviceGrade(int i);

    private ARPEngine() {
        this.mARPContent.registerCaseLoadListener(this.mARPDataInteraction);
        initEngine3DInstance();
    }

    private void initEngine3DInstance() {
        try {
            Object newInstance = Class.forName("com.baidu.ar.arplay.core.engine3d.ARPEngine3D").newInstance();
            if (newInstance instanceof IARPEngine3D) {
                this.mARPEngine3D = (AbstractARPEngine3D) newInstance;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    public static synchronized ARPEngine getInstance() {
        ARPEngine aRPEngine;
        synchronized (ARPEngine.class) {
            if (self == null) {
                self = new ARPEngine();
            }
            aRPEngine = self;
        }
        return aRPEngine;
    }

    public static synchronized void releaseInstance() {
        synchronized (ARPEngine.class) {
            if (self != null) {
                self.destroy();
            }
            self = null;
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IARPLifeCycle
    public boolean createEngine(ARPEngineParams aRPEngineParams) {
        synchronized (this) {
            boolean z = false;
            if (aRPEngineParams == null) {
                return false;
            }
            this.mARPEngineParams = aRPEngineParams;
            if (!this.mIsInitNative) {
                z = nativeSetup(new WeakReference(this), "5.5.0");
                this.mIsInitNative = true;
            }
            if (!this.mIsEngineCreated) {
                z = createApp(this.mARPEngineParams.getInputWidth(), this.mARPEngineParams.getInputHeight(), this.mARPEngineParams.getOutputWidth(), this.mARPEngineParams.getOutputHeight(), this.mARPEngineParams.getDensity(), this.mARPEngineParams.getShaderDBPath());
                if (this.mARPDataInteraction != null) {
                    this.mARPDataInteraction.setup();
                }
            }
            if (this.mARPEngine3D != null) {
                this.mARPEngine3D.setIsActiveByARPlayVersionCase(isDriverdByARPVersion());
            }
            this.mARPContent.setIsFrontCamera(this.mARPEngineParams.isIsFrontCamera());
            return z;
        }
    }

    public void setIsFrontCamera(boolean z) {
        this.mARPContent.setIsFrontCamera(z);
    }

    private boolean createApp(int i, int i2, int i3, int i4, float f, String str) {
        LogUtil.m20423e("ARPEngine", "createApp [width*height]: [" + i + "*" + i2 + "]");
        boolean nativeCreateApp = nativeCreateApp(i, i2, i3, i4, f, str);
        this.mIsEngineCreated = true;
        ARPContent aRPContent = this.mARPContent;
        if (aRPContent != null) {
            aRPContent.setEngineCreated(this.mIsEngineCreated);
        }
        return nativeCreateApp;
    }

    public void setConfig(String str, String str2) {
        LogUtil.m20423e("ARPEngine", "syncServerConfig :" + str2);
        nativeSetConfig(str, str2);
    }

    public void setLocalDeviceGrade(int i) {
        LogUtil.m20423e("ARPEngine", "setLocalDeviceGrade :" + i);
        nativeSetLocalDeviceGrade(i);
    }

    public synchronized int loadCaseWithResPath(String str) {
        if (this.mARPContent == null || this.mARPRenderer == null) {
            return -1;
        }
        return this.mARPContent.loadCaseWithResPath(str, this.mARPRenderer.getCameraPreviewWidth(), this.mARPRenderer.getCameraPreviewHeight());
    }

    public synchronized void loadTempleteWithResPath(String str) {
        if (this.mARPContent != null && this.mARPRenderer != null) {
            this.mARPContent.loadTempleteWithResPath(str, this.mARPRenderer.getCameraPreviewWidth(), this.mARPRenderer.getCameraPreviewHeight());
        }
    }

    public synchronized void onCaseLoadCompleted(Map map) {
        if (map == null) {
            return;
        }
        if (this.mARPContent != null && this.mARPContent.checkValid(ARPContent.CaseAction.OnCaseLoaded)) {
            if (this.mARPRenderer != null) {
                this.mARPRenderer.clearAllAsyncRenderTask();
            }
            this.mARPContent.onCaseLoadCompleted(map);
        }
        if (this.mIsPaused) {
            nativeOnPause();
        }
    }

    public void onCaseUnloadCompleted() {
        ARPContent aRPContent = this.mARPContent;
        if (aRPContent != null) {
            aRPContent.onCaseUnloadCompleted();
        }
    }

    public synchronized void onTempleLoadCompleted(Map map) {
        if (map == null) {
            return;
        }
        if (this.mARPContent != null) {
            if (this.mARPRenderer != null) {
                this.mARPRenderer.clearAllAsyncRenderTask();
            }
            this.mARPContent.onTempleLoadCompleted(map);
        }
    }

    public void setWindowSize(int i, int i2) {
        ARPContent aRPContent = this.mARPContent;
        if (aRPContent != null) {
            aRPContent.setWindowSize(i, i2);
        }
    }

    public float[] getWindowSize() {
        ARPContent aRPContent = this.mARPContent;
        if (aRPContent != null) {
            return aRPContent.getWindowSize();
        }
        return new float[0];
    }

    public void setPreviewSize(int i, int i2) {
        ARPContent aRPContent = this.mARPContent;
        if (aRPContent != null) {
            aRPContent.setPreviewSize(i, i2);
        }
    }

    public synchronized void unloadCase() {
        if (this.mARPContent != null && this.mARPContent.checkValid(ARPContent.CaseAction.UnloadCase)) {
            if (this.mARPRenderer != null) {
                this.mARPRenderer.clearAllAsyncRenderTask();
            }
            this.mARPContent.unloadCase();
        }
    }

    public synchronized void unloadTemplete() {
        LogUtil.m20423e("ARPEngine", "unloadTemplete");
        this.mARPContent.unloadTemplete();
    }

    public synchronized void destroyEngine() {
        LogUtil.m20423e("ARPEngine", "destroyEngine");
        this.mIsEngineCreated = false;
        if (this.mARPFilter != null) {
            this.mARPFilter.destroy();
        }
        if (this.mARPContent != null) {
            this.mARPContent.setEngineCreated(this.mIsEngineCreated);
            this.mARPContent.destroy();
        }
        if (this.mARPEngine3D != null) {
            this.mARPEngine3D.destroy();
        }
        nativeSetEngineBlendState(0);
        nativeDestroyEngine();
        if (this.mARPDataInteraction != null) {
            this.mARPDataInteraction.destroy();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public synchronized void destroy() {
        LogUtil.m20423e("ARPEngine", "destroy");
        if (this.mARPRenderer != null) {
            this.mARPRenderer.destroy();
        }
    }

    public boolean isEngineCanAccess() {
        return this.mIsEngineCreated && this.mARPContent.isCaseCreated();
    }

    public boolean isAppControllerInterrupt() {
        return nativeIsAppControllerInterrupt();
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public synchronized void pause() {
        this.mIsPaused = true;
        if (isEngineCanAccess()) {
            nativeOnPause();
        }
        if (this.mARPRenderer != null) {
            this.mARPRenderer.pause();
        }
    }

    public boolean isPaused() {
        return this.mIsPaused;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle
    public synchronized void resume() {
        if (!this.mScenePausedByUser) {
            resumeScene();
        }
        if (this.mARPRenderer != null) {
            this.mARPRenderer.resume();
        }
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IARPLifeCycle
    public synchronized void resumeScene() {
        this.mIsPaused = false;
        if (isEngineCanAccess()) {
            nativeOnResume();
        }
        this.mScenePausedByUser = false;
    }

    @Override // com.baidu.p120ar.arplay.core.engine.IARPLifeCycle
    public synchronized void pauseScene() {
        this.mIsPaused = true;
        if (isEngineCanAccess()) {
            nativeOnPause();
        }
        this.mScenePausedByUser = true;
    }

    public void sceneWorldPositionToOrigin() {
        AbstractARPEngine3D abstractARPEngine3D = this.mARPEngine3D;
        if (abstractARPEngine3D != null) {
            abstractARPEngine3D.sceneWorldPositionToOrigin();
        }
    }

    public void sceneRotateToCamera() {
        AbstractARPEngine3D abstractARPEngine3D = this.mARPEngine3D;
        if (abstractARPEngine3D != null) {
            abstractARPEngine3D.sceneRotateToCamera();
        }
    }

    public IARPScene getCurrentScene() {
        AbstractARPEngine3D abstractARPEngine3D;
        if (isEngineCanAccess() && (abstractARPEngine3D = this.mARPEngine3D) != null) {
            return abstractARPEngine3D.getCurrentScene();
        }
        return null;
    }

    public void initWorldAxis() {
        AbstractARPEngine3D abstractARPEngine3D = this.mARPEngine3D;
        if (abstractARPEngine3D != null) {
            abstractARPEngine3D.initWorldAxis();
        }
    }

    public void updateNodeUniform(String str, HashMap<String, Object> hashMap) {
        AbstractARPEngine3D abstractARPEngine3D;
        if (!isEngineCanAccess() || isAppControllerInterrupt() || (abstractARPEngine3D = this.mARPEngine3D) == null) {
            return;
        }
        abstractARPEngine3D.updateNodeUniform(str, hashMap);
    }

    public boolean isDriverdByARPVersion() {
        String str;
        Object sharedEnvironmentValue = ARPScriptEnvironment.getInstance().getSharedEnvironmentValue("caseinfo");
        if (sharedEnvironmentValue != null && (sharedEnvironmentValue instanceof HashMap)) {
            HashMap hashMap = (HashMap) sharedEnvironmentValue;
            return (hashMap.get("compatible_version") instanceof String) && (str = (String) hashMap.get("compatible_version")) != "" && str.compareTo("2.0.0") >= 0;
        }
        return false;
    }

    public synchronized void setHtmlUpdateCallback(ARPDataInteraction.HtmlUpdateCallback htmlUpdateCallback) {
        if (this.mARPDataInteraction != null) {
            this.mARPDataInteraction.setHtmlUpdateCallback(htmlUpdateCallback);
        }
    }

    public synchronized void setVideoUpdateCallback(ARPDataInteraction.VideoUpdateCallback videoUpdateCallback) {
        if (this.mARPDataInteraction != null) {
            this.mARPDataInteraction.setVideoUpdateCallback(videoUpdateCallback);
        }
    }

    public void onTouchUpdate(int i, float f, float f2, float f3, float f4, long j, int i2, float f5) {
        ARPDataInteraction aRPDataInteraction;
        if (isEngineCanAccess() && (aRPDataInteraction = this.mARPDataInteraction) != null) {
            aRPDataInteraction.onTouchUpdate(i, f, f2, f3, f4, j, i2, f5);
        }
    }

    public void onGestureUpdate(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9) {
        ARPDataInteraction aRPDataInteraction;
        if (isEngineCanAccess() && (aRPDataInteraction = this.mARPDataInteraction) != null) {
            aRPDataInteraction.onGestureUpdate(i, j, i2, f, f2, f3, f4, i3, f5, f6, f7, f8, i4, f9);
        }
    }

    public void onGestureUpdateWithScaleFinish(int i, long j, int i2, float f, float f2, float f3, float f4, int i3, float f5, float f6, float f7, float f8, int i4, float f9, boolean z) {
        ARPDataInteraction aRPDataInteraction;
        if (isEngineCanAccess() && (aRPDataInteraction = this.mARPDataInteraction) != null) {
            aRPDataInteraction.onGestureUpdateWithScaleFinish(i, j, i2, f, f2, f3, f4, i3, f5, f6, f7, f8, i4, f9, z);
        }
    }

    public void initDataStore(SharedPreferences sharedPreferences) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.initDataStore(sharedPreferences);
        }
    }

    public void clearARMemory() {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.clearARMemory();
        }
    }

    public void setInteraction(ARPDataInteraction.IInteraction iInteraction) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.setInteraction(iInteraction);
        }
    }

    public synchronized void setAlgoDataHandle(long j) {
        if (this.mARPDataInteraction != null) {
            this.mARPDataInteraction.setAlgoDataHandle(j);
        }
    }

    public void addAlgoType(int[] iArr, int i) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.addAlgoType(iArr, i);
        }
    }

    public void removeAlgoType(int[] iArr) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.removeAlgoType(iArr);
        }
    }

    public void clearAlgoCache() {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.clearAlgoCache();
        }
    }

    public void setFaceLandMarkFrameAcheMode(int i) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.setFaceLandMarkFrameAcheMode(i);
        }
    }

    public void updateAlgoDataToNode(int i, int i2, byte[] bArr) {
        ARPDataInteraction aRPDataInteraction;
        LogUtil.m20424d("ARPEngine", "updateAlgoDataToNode");
        if (isEngineCanAccess() && (aRPDataInteraction = this.mARPDataInteraction) != null) {
            aRPDataInteraction.updateAlgoDataToNode(i, i2, bArr);
        }
    }

    public long mockFaceAlgoHandle(long j, float[] fArr) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            return aRPDataInteraction.mockFaceAlgoHandle(j, fArr);
        }
        return -1L;
    }

    public void destroyMockAlgoHandle(long j) {
        ARPDataInteraction aRPDataInteraction = this.mARPDataInteraction;
        if (aRPDataInteraction != null) {
            aRPDataInteraction.destroyMockAlgoHandle(j);
        }
    }

    public void setEngineBlendState(int i) {
        nativeSetEngineBlendState(i);
    }

    public void setContext(SoftReference<Context> softReference) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.setContext(softReference);
        }
    }

    public void setAuthPic(Bitmap bitmap, float[] fArr) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.setAuthPic(bitmap, fArr);
        }
    }

    public void setWatermark(String str, Bitmap bitmap, float[] fArr) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.setWatermark(str, bitmap, fArr);
        }
    }

    public void disableFilterByAuthCode(int i) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.disableFilterByAuthCode(i);
        }
    }

    public void disableCaseLutTexture() {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.disableCaseLutTexture();
        }
    }

    public void adjustFilterWithFloatParam(String str, String str2, float f, long j) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.adjustFilterWithFloatParam(str, str2, f, j);
        }
    }

    public void adjustFilterWithIntParam(String str, String str2, int i, long j) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.adjustFilterWithIntParam(str, str2, i, j);
        }
    }

    public void adjustFilterWithFloatArrayParam(String str, String str2, float[] fArr, long j) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.adjustFilterWithFloatArrayParam(str, str2, fArr, j);
        }
    }

    public void adjustFilterWithStringParam(String str, String str2, String str3, long j) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.adjustFilterWithStringParam(str, str2, str3, j);
        }
    }

    public String adjustFilterWithJsonPathParam(String str) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            return aRPFilter.adjustFilterWithJsonPathParam(str);
        }
        return null;
    }

    public String adjustFilterWithCasePathParam(String str) {
        ARPFilter aRPFilter = this.mARPFilter;
        if (aRPFilter != null) {
            aRPFilter.adjustFilterWithCasePathParam(str);
            return null;
        }
        return null;
    }

    public IARPRenderer getARPRenderer() {
        return this.mARPRenderer;
    }
}
