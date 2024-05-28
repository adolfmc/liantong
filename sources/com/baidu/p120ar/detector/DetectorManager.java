package com.baidu.p120ar.detector;

import android.os.Looper;
import android.text.TextUtils;
import com.baidu.p120ar.arplay.core.renderer.OnNeedCacheFrameListener;
import com.baidu.p120ar.arrender.ARRenderer;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectorManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectorManager {
    private static final String TAG = "DetectorManager";
    private ARRenderer mARRenderer;
    private boolean mLastDetectEnable;
    private DetectorCallback mSyncCallback;
    private DetectorGroup mSyncDetectorGroup;
    private ConcurrentHashMap<String, DetectorCallback> mSyncCallbackMap = new ConcurrentHashMap<>();
    private List<IDetector> mAsyncDetetors = Collections.synchronizedList(new ArrayList());
    private ConcurrentHashMap<String, DetectorCallback> mAsyncCallbackMap = new ConcurrentHashMap<>();
    private long mLastTimestamp = 0;
    private OnNeedCacheFrameListener mOnNeedCacheFrameListener = new OnNeedCacheFrameListener() { // from class: com.baidu.ar.detector.DetectorManager.1
        @Override // com.baidu.p120ar.arplay.core.renderer.OnNeedCacheFrameListener
        public boolean isNeedCacheFrame(long j) {
            if (DetectorManager.this.mSyncDetectorGroup == null) {
                return false;
            }
            if (DetectorManager.this.mLastTimestamp != j) {
                DetectorManager detectorManager = DetectorManager.this;
                detectorManager.mLastDetectEnable = detectorManager.mSyncDetectorGroup.isDetectEnable();
            }
            DetectorManager.this.mLastTimestamp = j;
            return DetectorManager.this.mLastDetectEnable;
        }
    };

    public DetectorManager(ARRenderer aRRenderer, Looper looper) {
        this.mARRenderer = aRRenderer;
        this.mARRenderer.setCacheFrameListener(this.mOnNeedCacheFrameListener);
        this.mSyncDetectorGroup = new DetectorGroup(looper);
        this.mSyncCallback = new DetectorCallback() { // from class: com.baidu.ar.detector.DetectorManager.2
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                ARLog.m20421d("DetectorManager", "mSyncDetectorGroup onSetup result = " + resultModel.isSuccess());
                DetectorManager.this.callbackSetupResult(resultModel);
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                DetectorManager.this.callbackDetectResult(detectResult);
                if (DetectorManager.this.mARRenderer != null) {
                    DetectorManager.this.mARRenderer.setSyncFrameTimestamp(detectResult.getTimestamp());
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                ARLog.m20421d("DetectorManager", "mSyncDetectorGroup onRelease result = " + resultModel.isSuccess());
                DetectorManager.this.callbackReleaseResult(resultModel);
            }
        };
        this.mSyncDetectorGroup.setup(this.mSyncCallback);
    }

    public synchronized void addDetector(IDetector iDetector, DetectorCallback detectorCallback) {
        if (iDetector == null) {
            return;
        }
        if ((iDetector instanceof FrameDetector) && ((FrameDetector) iDetector).isDetectSync()) {
            if (this.mSyncDetectorGroup.isEmpty()) {
                this.mARRenderer.enableSyncRender(true);
            }
            if (!this.mSyncDetectorGroup.contains(iDetector)) {
                this.mSyncDetectorGroup.addDetector(iDetector, detectorCallback);
                this.mSyncCallbackMap.put(iDetector.getName(), detectorCallback);
            }
        } else if (this.mAsyncDetetors != null && !this.mAsyncDetetors.contains(iDetector)) {
            iDetector.setup(detectorCallback);
            this.mAsyncDetetors.add(iDetector);
            this.mAsyncCallbackMap.put(iDetector.getName(), detectorCallback);
        }
    }

    public synchronized void replaceDetector(IDetector iDetector, DetectorCallback detectorCallback) {
        removeAllDetector();
        addDetector(iDetector, detectorCallback);
    }

    public synchronized void removeDetector(IDetector iDetector) {
        if (iDetector == null) {
            ARLog.m20419e("DetectorManager", "handleRemoveDetector detector is NULLLLL!!!");
            return;
        }
        if (this.mAsyncDetetors != null && this.mAsyncDetetors.contains(iDetector)) {
            iDetector.release();
            this.mAsyncDetetors.remove(iDetector);
        } else if (this.mSyncDetectorGroup != null && this.mSyncDetectorGroup.contains(iDetector)) {
            this.mSyncDetectorGroup.removeDetector(iDetector);
            if (this.mSyncDetectorGroup.isEmpty()) {
                this.mARRenderer.enableSyncRender(false);
            }
        }
    }

    public synchronized void removeAllDetector() {
        if (this.mAsyncDetetors != null) {
            for (IDetector iDetector : this.mAsyncDetetors) {
                iDetector.release();
            }
            this.mAsyncDetetors.clear();
            this.mAsyncDetetors = null;
        }
        if (this.mSyncDetectorGroup != null) {
            this.mSyncDetectorGroup.release();
            if (this.mARRenderer != null) {
                this.mARRenderer.enableSyncRender(false);
            }
            this.mSyncDetectorGroup = null;
        }
        if (this.mSyncCallbackMap != null) {
            this.mSyncCallbackMap.clear();
            this.mSyncCallbackMap = null;
        }
        if (this.mAsyncCallbackMap != null) {
            this.mAsyncCallbackMap.clear();
            this.mAsyncCallbackMap = null;
        }
        this.mSyncCallback = null;
    }

    public synchronized void updateDetectorSync(FrameDetector frameDetector) {
        if (frameDetector != null) {
            if (!TextUtils.isEmpty(frameDetector.getName())) {
                if (frameDetector.isDetectSync() && this.mAsyncDetetors.contains(frameDetector)) {
                    if (this.mSyncDetectorGroup.isEmpty()) {
                        this.mARRenderer.enableSyncRender(true);
                    }
                    this.mAsyncDetetors.remove(frameDetector);
                    this.mSyncDetectorGroup.addRunningDetector(frameDetector, this.mAsyncCallbackMap.get(frameDetector.getName()));
                    DetectorCallback remove = this.mAsyncCallbackMap.remove(frameDetector.getName());
                    if (remove != null) {
                        this.mSyncCallbackMap.put(frameDetector.getName(), remove);
                    }
                } else if (!frameDetector.isDetectSync() && this.mSyncDetectorGroup.contains(frameDetector)) {
                    this.mSyncDetectorGroup.removeRunningDetector(frameDetector);
                    this.mAsyncDetetors.add(frameDetector);
                    frameDetector.setDetectorCallback(this.mSyncCallbackMap.get(frameDetector.getName()));
                    DetectorCallback remove2 = this.mSyncCallbackMap.remove(frameDetector.getName());
                    if (remove2 != null) {
                        this.mAsyncCallbackMap.put(frameDetector.getName(), remove2);
                    }
                    if (this.mSyncDetectorGroup.isEmpty()) {
                        this.mARRenderer.enableSyncRender(false);
                    }
                }
            }
        }
    }

    public void release() {
        removeAllDetector();
        this.mARRenderer = null;
        this.mOnNeedCacheFrameListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void callbackSetupResult(ResultModel resultModel) {
        if (resultModel != null) {
            if (resultModel.getDetectorName() != "DetectorGroup") {
                if (this.mSyncCallbackMap != null && this.mSyncCallbackMap.get(resultModel.getDetectorName()) != null) {
                    this.mSyncCallbackMap.get(resultModel.getDetectorName()).onSetup(resultModel);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void callbackDetectResult(DetectResult detectResult) {
        if (detectResult != null) {
            if (detectResult instanceof DetectResultGroup) {
                Iterator<DetectResult> it = ((DetectResultGroup) detectResult).getDetectResults().iterator();
                while (it.hasNext()) {
                    DetectResult next = it.next();
                    if (next != null && !TextUtils.isEmpty(next.getDetectorName()) && this.mSyncCallbackMap != null && this.mSyncCallbackMap.get(next.getDetectorName()) != null) {
                        this.mSyncCallbackMap.get(next.getDetectorName()).onDetected(next);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void callbackReleaseResult(ResultModel resultModel) {
        if (resultModel != null) {
            if (resultModel.getDetectorName() != "DetectorGroup") {
                if (this.mSyncCallbackMap != null && this.mSyncCallbackMap.get(resultModel.getDetectorName()) != null) {
                    this.mSyncCallbackMap.get(resultModel.getDetectorName()).onRelease(resultModel);
                }
            }
        }
    }
}
