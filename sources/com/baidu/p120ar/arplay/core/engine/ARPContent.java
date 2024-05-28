package com.baidu.p120ar.arplay.core.engine;

import com.baidu.p120ar.arplay.component.AudioPlayerManager;
import com.baidu.p120ar.arplay.component.VideoPlayerManager;
import com.baidu.p120ar.arplay.util.LogUtil;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arplay.webview.GLWebViewManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.ARPContent */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARPContent {
    private CaseState mCaseState = CaseState.EUninit;
    private volatile boolean mIsEngineCreated = false;
    private volatile boolean mIsTempleteCreating = false;
    private volatile boolean mIsTempleteCreated = false;
    private volatile boolean mIsTempleteDestoring = true;
    private volatile boolean mIsTempleteDestoryed = false;
    private long mStartTime = 0;
    private boolean mIsFrontCamera = true;
    private int mPreviewWidth = 0;
    private int mPreviewHeight = 0;
    private int mWindowWidth = 720;
    private int mWindowHeight = 1280;
    private List<ARPCaseLoadListener> mCaseLoadListenerList = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPContent$ARPCaseLoadListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ARPCaseLoadListener {
        void onCaseLoaded(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPContent$CaseAction */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CaseAction {
        LoadCase,
        UnloadCase,
        OnCaseLoaded,
        OnCaseUnloaded
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.core.engine.ARPContent$CaseState */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum CaseState {
        EUninit,
        ECreating,
        ECreated,
        EDestroying
    }

    native void nativeLoadCase(String str, int i, int i2);

    native void nativeLoadTemplete(String str, int i, int i2);

    native void nativeSetPreviewSize(int i, int i2);

    native void nativeSetWindowSize(int i, int i2);

    native void nativeUnloadCase();

    native void nativeUnloadTemplete();

    public void setIsFrontCamera(boolean z) {
        this.mIsFrontCamera = z;
    }

    public void setEngineCreated(boolean z) {
        this.mIsEngineCreated = z;
    }

    public boolean isCaseCreated() {
        return this.mCaseState == CaseState.ECreated;
    }

    public void registerCaseLoadListener(ARPCaseLoadListener aRPCaseLoadListener) {
        this.mCaseLoadListenerList.add(aRPCaseLoadListener);
    }

    public void unregisterCaseLoadListener(ARPCaseLoadListener aRPCaseLoadListener) {
        this.mCaseLoadListenerList.remove(aRPCaseLoadListener);
    }

    public void notifyCaseLoadListener() {
        for (int i = 0; i < this.mCaseLoadListenerList.size(); i++) {
            this.mCaseLoadListenerList.get(i).onCaseLoaded(isCaseCreated());
        }
    }

    public void clearAllCaseLoadListener() {
        this.mCaseLoadListenerList.clear();
    }

    public void setWindowSize(int i, int i2) {
        this.mWindowWidth = i;
        this.mWindowHeight = i2;
        if (isEngineCanAccess()) {
            nativeSetWindowSize(i, i2);
        }
    }

    public float[] getWindowSize() {
        return new float[]{this.mWindowWidth, this.mWindowHeight};
    }

    public void setPreviewSize(int i, int i2) {
        this.mPreviewWidth = i;
        this.mPreviewHeight = i2;
        if (isEngineCanAccess()) {
            nativeSetPreviewSize(i, i2);
        }
    }

    public boolean isEngineCanAccess() {
        return this.mIsEngineCreated && this.mCaseState == CaseState.ECreated;
    }

    public int loadCaseWithResPath(String str, int i, int i2) {
        int i3;
        if (!this.mIsEngineCreated || this.mCaseState == CaseState.ECreating) {
            return -1;
        }
        this.mCaseState = CaseState.ECreating;
        notifyCaseLoadListener();
        ARPScriptEnvironment.getInstance().setDataPipKV("camera_position", Integer.valueOf(this.mIsFrontCamera ? 1 : 0));
        this.mStartTime = System.currentTimeMillis();
        int i4 = this.mPreviewWidth;
        if (i4 > 0) {
            i = i4;
        }
        this.mPreviewWidth = i;
        int i5 = this.mPreviewHeight;
        if (i5 <= 0) {
            i5 = i2;
        }
        this.mPreviewHeight = i5;
        int i6 = this.mWindowWidth;
        if (i6 > 0 && (i3 = this.mWindowHeight) > 0) {
            nativeSetWindowSize(i6, i3);
        }
        nativeLoadCase(str, this.mPreviewWidth, this.mPreviewHeight);
        return 0;
    }

    public void loadTempleteWithResPath(String str, int i, int i2) {
        if (this.mIsTempleteCreating) {
            return;
        }
        if (!this.mIsTempleteDestoring) {
            unloadTemplete();
        }
        this.mIsTempleteCreating = true;
        this.mIsTempleteDestoring = false;
        this.mIsTempleteDestoryed = false;
        this.mIsTempleteCreated = false;
        ARPScriptEnvironment.getInstance().setDataPipKV("camera_position", Integer.valueOf(this.mIsFrontCamera ? 1 : 0));
        this.mStartTime = System.currentTimeMillis();
        nativeLoadTemplete(str, i, i2);
    }

    public void onCaseLoadCompleted(Map map) {
        if (this.mIsEngineCreated && this.mCaseState == CaseState.ECreating && map != null) {
            int obj2Int = MsgParamsUtil.obj2Int(map.get("case_id"), 0);
            LogUtil.m20423e("ARPEngine", "caseId : " + obj2Int);
            LogUtil.m20423e("ARPEngine", "caseId : " + obj2Int + " cost: " + (System.currentTimeMillis() - this.mStartTime));
            this.mCaseState = CaseState.ECreated;
            notifyCaseLoadListener();
        }
    }

    public void onCaseUnloadCompleted() {
        if (this.mIsEngineCreated && this.mCaseState == CaseState.EDestroying) {
            this.mCaseState = CaseState.EUninit;
        }
    }

    public boolean checkValid(CaseAction caseAction) {
        switch (caseAction) {
            case UnloadCase:
                if (!this.mIsEngineCreated || this.mCaseState == CaseState.EDestroying || this.mCaseState == CaseState.EUninit) {
                    return false;
                }
                break;
            case OnCaseLoaded:
                if (!this.mIsEngineCreated || this.mCaseState != CaseState.ECreating) {
                    return false;
                }
                break;
        }
        return true;
    }

    public void onTempleLoadCompleted(Map map) {
        if (map == null) {
            return;
        }
        int obj2Int = MsgParamsUtil.obj2Int(map.get("case_id"), 0);
        LogUtil.m20423e("ARPEngine", "caseId : " + obj2Int);
        LogUtil.m20423e("ARPEngine", "onTempleLoadCompleted : " + obj2Int + " cost: " + (System.currentTimeMillis() - this.mStartTime));
        this.mIsTempleteCreated = true;
        this.mIsTempleteCreating = false;
    }

    public void unloadCase() {
        if (!this.mIsEngineCreated || this.mCaseState == CaseState.EDestroying || this.mCaseState == CaseState.EUninit) {
            return;
        }
        LogUtil.m20423e("ARPEngine", "unloadCase");
        this.mCaseState = CaseState.EDestroying;
        notifyCaseLoadListener();
        this.mPreviewWidth = 0;
        this.mPreviewHeight = 0;
        nativeUnloadCase();
        releaseComponents();
        LogUtil.m20423e("ARPEngine", "unloadCase finished");
    }

    public void unloadTemplete() {
        LogUtil.m20423e("ARPEngine", "unloadTemplete");
        this.mIsTempleteDestoring = true;
        this.mIsTempleteCreated = false;
        nativeUnloadTemplete();
        LogUtil.m20423e("ARPEngine", "unloadTemplete finished");
        this.mIsTempleteDestoryed = true;
    }

    private void releaseComponents() {
        AudioPlayerManager.getInstance().releaseMediaPlayer();
        VideoPlayerManager.getInstance().release();
        GLWebViewManager.getInstance().release();
        ARPScriptEnvironment.getInstance().release();
    }

    public void destroy() {
        this.mCaseState = CaseState.EUninit;
        notifyCaseLoadListener();
        clearAllCaseLoadListener();
        this.mIsEngineCreated = false;
        this.mIsTempleteCreating = false;
        this.mIsTempleteCreated = false;
        this.mIsTempleteDestoring = false;
        this.mIsTempleteDestoryed = false;
        releaseComponents();
    }
}
