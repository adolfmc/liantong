package com.baidu.p120ar.detector;

import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.AbstractDetector */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractDetector implements IDetector {
    public DetectorCallback mDetectorCallback;
    private CopyOnWriteArrayList<DetectorCallback> mExtraCallbacks = new CopyOnWriteArrayList<>();
    private DetectorCallback mRealCallback;
    private ResultModel mSetupResult;

    @Override // com.baidu.p120ar.detector.IDetector
    public void setup(DetectorCallback detectorCallback) {
        this.mRealCallback = detectorCallback;
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.detector.AbstractDetector.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                AbstractDetector.this.mSetupResult = resultModel;
                if (AbstractDetector.this.mRealCallback != null) {
                    AbstractDetector.this.mRealCallback.onSetup(resultModel);
                }
                if (AbstractDetector.this.mExtraCallbacks == null || AbstractDetector.this.mExtraCallbacks.size() <= 0) {
                    return;
                }
                Iterator it = AbstractDetector.this.mExtraCallbacks.iterator();
                while (it.hasNext()) {
                    ((DetectorCallback) it.next()).onSetup(resultModel);
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (AbstractDetector.this.mExtraCallbacks != null && AbstractDetector.this.mExtraCallbacks.size() > 0) {
                    Iterator it = AbstractDetector.this.mExtraCallbacks.iterator();
                    while (it.hasNext()) {
                        DetectorCallback detectorCallback2 = (DetectorCallback) it.next();
                        if (detectResult.getResultHandle() > 0) {
                            AlgoHandleAdapter.increaseHandleReference(detectResult.getResultHandle());
                        }
                        detectorCallback2.onDetected(detectResult);
                    }
                }
                if (AbstractDetector.this.mRealCallback != null) {
                    AbstractDetector.this.mRealCallback.onDetected(detectResult);
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                if (AbstractDetector.this.mRealCallback != null) {
                    AbstractDetector.this.mRealCallback.onRelease(resultModel);
                }
                if (AbstractDetector.this.mExtraCallbacks == null || AbstractDetector.this.mExtraCallbacks.size() <= 0) {
                    return;
                }
                Iterator it = AbstractDetector.this.mExtraCallbacks.iterator();
                while (it.hasNext()) {
                    ((DetectorCallback) it.next()).onRelease(resultModel);
                }
            }
        };
    }

    public void addDetectorCallback(DetectorCallback detectorCallback) {
        CopyOnWriteArrayList<DetectorCallback> copyOnWriteArrayList = this.mExtraCallbacks;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.add(detectorCallback);
            ResultModel resultModel = this.mSetupResult;
            if (resultModel != null) {
                detectorCallback.onSetup(resultModel);
            }
        }
    }

    public void removeDetectorCallback(DetectorCallback detectorCallback) {
        CopyOnWriteArrayList<DetectorCallback> copyOnWriteArrayList = this.mExtraCallbacks;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(detectorCallback);
        }
    }

    public boolean hasExtraCallbacks() {
        CopyOnWriteArrayList<DetectorCallback> copyOnWriteArrayList = this.mExtraCallbacks;
        return copyOnWriteArrayList != null && copyOnWriteArrayList.size() > 0;
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public void release() {
        CopyOnWriteArrayList<DetectorCallback> copyOnWriteArrayList = this.mExtraCallbacks;
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.clear();
            this.mExtraCallbacks = null;
        }
    }

    public DetectorCallback getDetectorCallback() {
        return this.mRealCallback;
    }

    public void setDetectorCallback(DetectorCallback detectorCallback) {
        this.mRealCallback = detectorCallback;
    }
}
