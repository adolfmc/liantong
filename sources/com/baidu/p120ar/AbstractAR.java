package com.baidu.p120ar;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import com.baidu.p120ar.arplay.core.pixel.PixelReadParams;
import com.baidu.p120ar.arplay.core.pixel.PixelRotation;
import com.baidu.p120ar.arrender.ARRenderer;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.detector.AbstractDetector;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.DetectorManager;
import com.baidu.p120ar.detector.FrameDetector;
import com.baidu.p120ar.detector.IDetector;
import com.baidu.p120ar.filter.ARFilterManager;
import com.baidu.p120ar.imu.IImu;
import com.baidu.p120ar.imu.ImuListener;
import com.baidu.p120ar.imu.ImuParams;
import com.baidu.p120ar.lua.EngineMsgBridge;
import com.baidu.p120ar.lua.EngineMsgListener;
import com.baidu.p120ar.lua.LuaMsgListener;
import com.baidu.p120ar.mdl.MdlConfig;
import com.baidu.p120ar.mdl.MdlConfigParams;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.AbstractAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractAR implements ARRenderer.InputSizeChangeListener {
    private static final String TAG = "AbstractAR";
    private ARFilterManager mARFilterManager;
    private ARRenderer mARRenderer;
    private JSONObject mAbilityScheme;
    private Context mContext;
    private DetectorManager mDetectorManager;
    private EngineMsgBridge mEngineMsgBridge;
    private String mFaceModelPath;
    private Handler mHandler;
    private IImu mIMUController;
    protected int mInputDegree;
    public int mInputHeight;
    public int mInputWidth;
    private MdlConfigParams mMdlConfigParams;
    protected int mOutputHeight;
    private ARRenderer.OutputSizeChangeListener mOutputSizeChangeListener;
    protected int mOutputWidth;
    private HashMap<String, DetectorCallback> mRequireDetectorCallbacks;
    private RequireDetectorListener mRequireDetectorListener;
    private List<IDetector> mDetectors = new ArrayList();
    private boolean mDetectSync = false;
    private List<String> mEnabledAbilitys = new CopyOnWriteArrayList();
    protected boolean mIsCameraInput = true;
    public boolean mIsFrontCamera = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.AbstractAR$RequireDetectorListener */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RequireDetectorListener {
        List<String> getAvailableDetectors();

        boolean requireStartDetector(String str, DetectorCallback detectorCallback, HashMap<String, Object> hashMap);

        boolean requireStopDetector(String str, DetectorCallback detectorCallback);
    }

    protected void afterMakeUpOff() {
    }

    protected void beforMakeUpOpen() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAlgoHandleDestory(long j) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCaseCreate(String str) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCaseDestroy() {
    }

    public void pause() {
    }

    public void resume() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setContextAndLooper(Context context, Looper looper) {
        this.mContext = context;
        this.mHandler = new Handler(looper);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setARManagers(DetectorManager detectorManager, ARRenderer aRRenderer, ARFilterManager aRFilterManager) {
        this.mDetectorManager = detectorManager;
        this.mARRenderer = aRRenderer;
        this.mARFilterManager = aRFilterManager;
        this.mInputWidth = aRRenderer.getDuMixInput().getInputWidth();
        this.mInputHeight = aRRenderer.getDuMixInput().getInputHeight();
        this.mOutputWidth = aRRenderer.getDuMixOutput().getOutputWidth();
        this.mOutputHeight = aRRenderer.getDuMixOutput().getOutputHeight();
        this.mInputDegree = aRRenderer.getDuMixInput().getInputDegree();
        this.mIsCameraInput = aRRenderer.getDuMixInput().isCameraInput();
        this.mIsFrontCamera = aRRenderer.getDuMixInput().isFrontCamera();
        this.mOutputSizeChangeListener = new ARRenderer.OutputSizeChangeListener() { // from class: com.baidu.ar.AbstractAR.1
            @Override // com.baidu.p120ar.arrender.ARRenderer.OutputSizeChangeListener
            public void outputSizeChange(int i, int i2) {
                AbstractAR abstractAR = AbstractAR.this;
                abstractAR.mOutputWidth = i;
                abstractAR.mOutputHeight = i2;
            }
        };
        aRRenderer.setOutputSizeChangeListener(this.mOutputSizeChangeListener);
        aRRenderer.setInputSizeChangeListener(this);
        if (isForceSync()) {
            this.mDetectSync = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEngineMsgBridge(EngineMsgBridge engineMsgBridge) {
        this.mEngineMsgBridge = engineMsgBridge;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setImuController(IImu iImu) {
        this.mIMUController = iImu;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRequireDetectorListener(RequireDetectorListener requireDetectorListener) {
        this.mRequireDetectorListener = requireDetectorListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setAbilityScheme(JSONObject jSONObject) {
        this.mAbilityScheme = jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAbilities(String str) {
        List<String> list = this.mEnabledAbilitys;
        if (list == null || list.contains(str)) {
            return;
        }
        this.mEnabledAbilitys.add(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAbilities(List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str : list) {
            List<String> list2 = this.mEnabledAbilitys;
            if (list2 != null && !list2.contains(str)) {
                this.mEnabledAbilitys.add(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeAbility(String str) {
        List<String> list = this.mEnabledAbilitys;
        if (list != null) {
            list.remove(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearAbilities() {
        List<String> list = this.mEnabledAbilitys;
        if (list != null) {
            list.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addDetectorCallback(String str, DetectorCallback detectorCallback) {
        if (this.mDetectors == null || TextUtils.isEmpty(str) || detectorCallback == null) {
            return;
        }
        for (IDetector iDetector : this.mDetectors) {
            if (iDetector != null && str.equals(iDetector.getName()) && (iDetector instanceof AbstractDetector)) {
                ((AbstractDetector) iDetector).addDetectorCallback(detectorCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeDetectorCallback(String str, DetectorCallback detectorCallback) {
        if (this.mDetectors == null || TextUtils.isEmpty(str) || detectorCallback == null) {
            return;
        }
        for (IDetector iDetector : this.mDetectors) {
            if (iDetector != null && str.equals(iDetector.getName()) && (iDetector instanceof AbstractDetector)) {
                ((AbstractDetector) iDetector).removeDetectorCallback(detectorCallback);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onCameraSwitch(boolean z) {
        PixelRotation pixelRotation;
        this.mIsFrontCamera = z;
        for (IDetector iDetector : this.mDetectors) {
            if ((iDetector instanceof FrameDetector) && this.mARRenderer != null) {
                PixelReadParams readParams = ((FrameDetector) iDetector).getReadParams();
                if (this.mIsCameraInput && readParams.getIsPortrait()) {
                    if (z) {
                        pixelRotation = PixelRotation.RotateRightFlipHorizontal;
                    } else {
                        pixelRotation = PixelRotation.RotateRight;
                    }
                    readParams.setPixelRotate(pixelRotation);
                    this.mARRenderer.updatePixelReader(readParams, pixelRotation);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean canRelease() {
        List<String> list = this.mEnabledAbilitys;
        if (list == null || list.size() <= 0) {
            for (IDetector iDetector : this.mDetectors) {
                if (iDetector != null && (iDetector instanceof AbstractDetector) && ((AbstractDetector) iDetector).hasExtraCallbacks()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void setup(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        String str = (String) hashMap.get("detect_sync");
        if (!TextUtils.isEmpty(str) && "sync".equals(str)) {
            this.mDetectSync = true;
        }
        if (isForceSync()) {
            this.mDetectSync = true;
        }
    }

    public void adjust(HashMap<String, Object> hashMap) {
        if (hashMap == null) {
            return;
        }
        String str = (String) hashMap.get("detect_sync");
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = false;
        if ("sync".equals(str)) {
            z = true;
        } else {
            "async".equals(str);
        }
        boolean z2 = isForceSync() ? true : z;
        if (z2 != this.mDetectSync) {
            this.mDetectSync = z2;
            for (IDetector iDetector : this.mDetectors) {
                if (iDetector != null && (iDetector instanceof FrameDetector)) {
                    ((FrameDetector) iDetector).setDetectSync(this.mDetectSync);
                }
                DetectorManager detectorManager = this.mDetectorManager;
                if (detectorManager != null) {
                    detectorManager.updateDetectorSync((FrameDetector) iDetector);
                }
            }
        }
    }

    public void setFaceModelPath(String str) {
        this.mFaceModelPath = str;
    }

    public String getFaceModelPath() {
        return this.mFaceModelPath;
    }

    public void setMdlConfigParams(MdlConfigParams mdlConfigParams) {
        this.mMdlConfigParams = mdlConfigParams;
    }

    public SparseArray<MdlConfig> getMdlConfigs() {
        MdlConfigParams mdlConfigParams = this.mMdlConfigParams;
        if (mdlConfigParams == null) {
            ARLog.m20419e("AbstractAR", "mMdlConfigParams is null.");
            return new SparseArray<>();
        }
        return mdlConfigParams.getConfigs();
    }

    @Override // com.baidu.p120ar.arrender.ARRenderer.InputSizeChangeListener
    public void onInputSizeChange(int i, int i2) {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer == null) {
            return;
        }
        this.mInputWidth = aRRenderer.getDuMixInput().getInputWidth();
        this.mInputHeight = this.mARRenderer.getDuMixInput().getInputHeight();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Context getContext() {
        return this.mContext;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<String> getEnabledAbilitys() {
        return this.mEnabledAbilitys;
    }

    public final IARRenderer getRenderer() {
        return this.mARRenderer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final ARFilterManager getFilterManager() {
        return this.mARFilterManager;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addDetector(IDetector iDetector, DetectorCallback detectorCallback) {
        if (iDetector == null) {
            ARLog.m20419e("AbstractAR", "addDetector error!!! detector is null!!!");
            return;
        }
        if ((iDetector instanceof FrameDetector) && this.mARRenderer != null) {
            this.mDetectors.add(iDetector);
            FrameDetector frameDetector = (FrameDetector) iDetector;
            frameDetector.setControlHandler(this.mHandler);
            frameDetector.setDetectSync(this.mDetectSync);
            PixelReadParams readParams = frameDetector.getReadParams();
            if (this.mIsCameraInput && readParams.getIsPortrait()) {
                if (this.mIsFrontCamera) {
                    readParams.setPixelRotate(PixelRotation.RotateRightFlipHorizontal);
                } else {
                    readParams.setPixelRotate(PixelRotation.RotateRight);
                }
            } else if (!this.mIsCameraInput) {
                int i = this.mInputDegree;
                if (i == 0) {
                    readParams.setPixelRotate(PixelRotation.FlipVertical);
                } else if (i == 90) {
                    readParams.setPixelRotate(PixelRotation.RotateRightFlipVertical);
                } else if (i == 180) {
                    readParams.setPixelRotate(PixelRotation.FlipHorizontal);
                } else if (i == 270) {
                    readParams.setPixelRotate(PixelRotation.RotateRightFlipHorizontal);
                }
            }
            this.mARRenderer.createPixelReader(readParams, frameDetector);
        }
        DetectorManager detectorManager = this.mDetectorManager;
        if (detectorManager != null) {
            detectorManager.addDetector(iDetector, detectorCallback);
        }
    }

    public final void removeDetector(IDetector iDetector) {
        ARRenderer aRRenderer;
        if (iDetector == null) {
            ARLog.m20419e("AbstractAR", "removeDetector error!!! detector is null!!!");
            return;
        }
        if ((iDetector instanceof FrameDetector) && (aRRenderer = this.mARRenderer) != null) {
            FrameDetector frameDetector = (FrameDetector) iDetector;
            aRRenderer.destroyPixelReader(frameDetector.getReadParams(), frameDetector);
        }
        DetectorManager detectorManager = this.mDetectorManager;
        if (detectorManager != null) {
            detectorManager.removeDetector(iDetector);
        }
        List<IDetector> list = this.mDetectors;
        if (list != null) {
            list.remove(iDetector);
        }
    }

    protected final void runOnControlThread(Runnable runnable) {
        Handler handler;
        if (runnable == null || (handler = this.mHandler) == null) {
            return;
        }
        handler.post(runnable);
    }

    public final void sendMsg2Lua(HashMap<String, Object> hashMap) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.sendMsg2Engine(1902, hashMap);
        }
    }

    public final void sendMsg2Engine(int i, HashMap<String, Object> hashMap) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.sendMsg2Engine(i, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addEngineMsgListener(EngineMsgListener engineMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.addEngineMsgListener(engineMsgListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void removeEngineMsgListener(EngineMsgListener engineMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge != null) {
            engineMsgBridge.removeEngineMsgListener(engineMsgListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addLuaMsgListener(LuaMsgListener luaMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge == null || engineMsgBridge.getLuaMsgBridge() == null) {
            return;
        }
        this.mEngineMsgBridge.getLuaMsgBridge().addLuaMsgListener(luaMsgListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void removeLuaMsgListener(LuaMsgListener luaMsgListener) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge == null || engineMsgBridge.getLuaMsgBridge() == null) {
            return;
        }
        this.mEngineMsgBridge.getLuaMsgBridge().removeLuaMsgListener(luaMsgListener);
    }

    protected final void onEngineMessage(int i, int i2, HashMap<String, Object> hashMap) {
        EngineMsgBridge engineMsgBridge = this.mEngineMsgBridge;
        if (engineMsgBridge == null || engineMsgBridge.getLuaMsgBridge() == null) {
            return;
        }
        this.mEngineMsgBridge.getLuaMsgBridge().onEngineMessage(i, i2, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean addImuListener(ImuParams imuParams, ImuListener imuListener) {
        IImu iImu = this.mIMUController;
        if (iImu != null) {
            return iImu.start(imuParams, imuListener);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void removeImuListener(ImuListener imuListener) {
        IImu iImu = this.mIMUController;
        if (iImu != null) {
            iImu.stop(imuListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setDetectSync(boolean z) {
        if (isForceSync()) {
            z = true;
        }
        if (z != this.mDetectSync) {
            this.mDetectSync = z;
            List<IDetector> list = this.mDetectors;
            if (list == null) {
                return;
            }
            for (IDetector iDetector : list) {
                if (iDetector != null && (iDetector instanceof FrameDetector)) {
                    ((FrameDetector) iDetector).setDetectSync(this.mDetectSync);
                }
                DetectorManager detectorManager = this.mDetectorManager;
                if (detectorManager != null) {
                    detectorManager.updateDetectorSync((FrameDetector) iDetector);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JSONObject getAbilityScheme() {
        return this.mAbilityScheme;
    }

    protected final List<String> getAvailableDetectors() {
        RequireDetectorListener requireDetectorListener = this.mRequireDetectorListener;
        if (requireDetectorListener != null) {
            return requireDetectorListener.getAvailableDetectors();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean requireStartDetector(String str, DetectorCallback detectorCallback, HashMap<String, Object> hashMap) {
        if (this.mRequireDetectorListener == null || TextUtils.isEmpty(str) || detectorCallback == null) {
            return false;
        }
        if (this.mRequireDetectorCallbacks == null) {
            this.mRequireDetectorCallbacks = new HashMap<>();
        }
        this.mRequireDetectorCallbacks.put(str, detectorCallback);
        return this.mRequireDetectorListener.requireStartDetector(str, detectorCallback, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean requireStopDetector(String str, DetectorCallback detectorCallback) {
        if (this.mRequireDetectorListener == null || TextUtils.isEmpty(str) || detectorCallback == null) {
            return false;
        }
        HashMap<String, DetectorCallback> hashMap = this.mRequireDetectorCallbacks;
        if (hashMap != null) {
            hashMap.remove(str);
        }
        return this.mRequireDetectorListener.requireStopDetector(str, detectorCallback);
    }

    public void release() {
        HashMap<String, DetectorCallback> hashMap = this.mRequireDetectorCallbacks;
        if (hashMap != null) {
            for (Map.Entry<String, DetectorCallback> entry : hashMap.entrySet()) {
                requireStopDetector(entry.getKey(), entry.getValue());
            }
            this.mRequireDetectorCallbacks.clear();
            this.mRequireDetectorCallbacks = null;
        }
        IDetector[] iDetectorArr = new IDetector[this.mDetectors.size()];
        this.mDetectors.toArray(iDetectorArr);
        for (IDetector iDetector : iDetectorArr) {
            removeDetector(iDetector);
        }
        this.mDetectors.clear();
        this.mDetectors = null;
        this.mDetectorManager = null;
        this.mIMUController = null;
        this.mARRenderer = null;
        this.mARFilterManager = null;
        this.mEngineMsgBridge = null;
        this.mAbilityScheme = null;
        this.mHandler = null;
        this.mContext = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isVideoProcess() {
        ARRenderer aRRenderer = this.mARRenderer;
        return (aRRenderer == null || aRRenderer.getDuMixInput() == null || this.mARRenderer.getDuMixInput().getInputTexture() == null) ? false : true;
    }

    private boolean isForceSync() {
        ARRenderer aRRenderer = this.mARRenderer;
        if (aRRenderer == null || aRRenderer.getDuMixInput() == null) {
            return false;
        }
        return this.mARRenderer.getDuMixInput().isSyncInputContent();
    }
}
