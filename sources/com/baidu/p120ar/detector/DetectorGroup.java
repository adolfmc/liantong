package com.baidu.p120ar.detector;

import android.os.Looper;
import com.baidu.p120ar.utils.ARLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectorGroup */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectorGroup implements IDetector {
    public static final String TAG = "DetectorGroup";
    private DetectResultSync mDetectResultSync;
    private DetectorCallback mDetectorCallback;
    private List<IDetector> mDetectors = new CopyOnWriteArrayList();
    private List<IDetector> mDetectorsAdding = new CopyOnWriteArrayList();

    @Override // com.baidu.p120ar.detector.IDetector
    public String getName() {
        return "DetectorGroup";
    }

    public DetectorGroup(Looper looper) {
        this.mDetectResultSync = new DetectResultSync(looper);
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public void setup(DetectorCallback detectorCallback) {
        this.mDetectorCallback = detectorCallback;
        this.mDetectResultSync.setup(this.mDetectors, new DetectorCallback() { // from class: com.baidu.ar.detector.DetectorGroup.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                if (DetectorGroup.this.mDetectorCallback != null) {
                    DetectorGroup.this.mDetectorCallback.onSetup(resultModel);
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (DetectorGroup.this.mDetectorCallback != null) {
                    DetectorGroup.this.mDetectorCallback.onDetected(detectResult);
                }
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                if (DetectorGroup.this.mDetectorCallback != null) {
                    DetectorGroup.this.mDetectorCallback.onRelease(resultModel);
                }
            }
        });
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public void detect(DetectParams detectParams) {
        List<IDetector> list = this.mDetectorsAdding;
        if (list != null) {
            for (IDetector iDetector : list) {
                iDetector.detect(detectParams);
            }
        }
        List<IDetector> list2 = this.mDetectors;
        if (list2 != null) {
            for (IDetector iDetector2 : list2) {
                iDetector2.detect(detectParams);
            }
        }
    }

    public boolean contains(IDetector iDetector) {
        if (iDetector == null) {
            return false;
        }
        List<IDetector> list = this.mDetectors;
        if (list == null || !list.contains(iDetector)) {
            List<IDetector> list2 = this.mDetectorsAdding;
            return list2 != null && list2.contains(iDetector);
        }
        return true;
    }

    public void addDetector(IDetector iDetector, DetectorCallback detectorCallback) {
        if (iDetector != null) {
            this.mDetectorsAdding.add(iDetector);
            ARLog.m20421d("DetectorGroup", "addDetector detector = " + iDetector.getName());
            setupDetctor(iDetector, detectorCallback, false);
        }
    }

    public void removeDetector(IDetector iDetector) {
        List<IDetector> list = this.mDetectorsAdding;
        if (list != null && list.contains(iDetector)) {
            this.mDetectorsAdding.remove(iDetector);
            ARLog.m20421d("DetectorGroup", "removeDetector detector = " + iDetector.getName());
            iDetector.release();
            return;
        }
        List<IDetector> list2 = this.mDetectors;
        if (list2 == null || !list2.contains(iDetector)) {
            return;
        }
        this.mDetectors.remove(iDetector);
        iDetector.release();
    }

    public void addRunningDetector(IDetector iDetector, DetectorCallback detectorCallback) {
        if (iDetector != null) {
            this.mDetectorsAdding.add(iDetector);
            setupDetctor(iDetector, detectorCallback, true);
        }
    }

    public void removeRunningDetector(IDetector iDetector) {
        if (iDetector != null) {
            List<IDetector> list = this.mDetectorsAdding;
            if (list != null) {
                list.remove(iDetector);
            }
            List<IDetector> list2 = this.mDetectors;
            if (list2 != null) {
                list2.remove(iDetector);
            }
            DetectResultSync detectResultSync = this.mDetectResultSync;
            if (detectResultSync != null) {
                detectResultSync.onDetectorRelease(new ResultModel(iDetector.getName(), true));
            }
        }
    }

    public boolean isEmpty() {
        List<IDetector> list;
        List<IDetector> list2 = this.mDetectors;
        return (list2 == null || list2.size() <= 0) && ((list = this.mDetectorsAdding) == null || list.size() <= 0);
    }

    @Override // com.baidu.p120ar.detector.IDetector
    public synchronized void release() {
        if (this.mDetectors != null) {
            for (IDetector iDetector : this.mDetectors) {
                iDetector.release();
            }
            this.mDetectors.clear();
            this.mDetectors = null;
        }
        if (this.mDetectResultSync != null) {
            this.mDetectResultSync.release();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isDetectEnable() {
        /*
            r3 = this;
            java.util.List<com.baidu.ar.detector.IDetector> r0 = r3.mDetectors
            if (r0 == 0) goto L2c
            int r0 = r0.size()
            if (r0 <= 0) goto L2c
            java.util.List<com.baidu.ar.detector.IDetector> r0 = r3.mDetectors
            java.util.Iterator r0 = r0.iterator()
        L10:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L2c
            java.lang.Object r1 = r0.next()
            com.baidu.ar.detector.IDetector r1 = (com.baidu.p120ar.detector.IDetector) r1
            if (r1 == 0) goto L2a
            boolean r2 = r1 instanceof com.baidu.p120ar.detector.FrameDetector
            if (r2 == 0) goto L2a
            com.baidu.ar.detector.FrameDetector r1 = (com.baidu.p120ar.detector.FrameDetector) r1
            boolean r1 = r1.isDetectEnable()
            if (r1 != 0) goto L10
        L2a:
            r0 = 0
            goto L2d
        L2c:
            r0 = 1
        L2d:
            com.baidu.p120ar.detector.FrameDetector.setNeedDetect(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.detector.DetectorGroup.isDetectEnable():boolean");
    }

    private void setupDetctor(IDetector iDetector, final DetectorCallback detectorCallback, final boolean z) {
        ARLog.m20421d("DetectorGroup", "setupDetctor detector.getName() = " + iDetector.getName());
        DetectorCallback detectorCallback2 = new DetectorCallback() { // from class: com.baidu.ar.detector.DetectorGroup.2
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
                ARLog.m20421d("DetectorGroup", "setupDetctor result = " + resultModel.getDetectorName() + " * " + resultModel.isSuccess());
                if (DetectorGroup.this.mDetectorsAdding != null && DetectorGroup.this.mDetectorsAdding.size() > 0 && !resultModel.isSuccess()) {
                    IDetector iDetector2 = null;
                    for (IDetector iDetector3 : DetectorGroup.this.mDetectorsAdding) {
                        if (resultModel.getDetectorName() != null && resultModel.getDetectorName().equals(iDetector3.getName())) {
                            iDetector2 = iDetector3;
                        }
                    }
                    if (iDetector2 != null) {
                        DetectorGroup.this.mDetectorsAdding.remove(iDetector2);
                        ARLog.m20421d("DetectorGroup", "onSetup detector2Remove = " + iDetector2.getName());
                    }
                }
                if (DetectorGroup.this.mDetectResultSync != null) {
                    DetectorGroup.this.mDetectResultSync.onDetectorSetup(resultModel);
                }
                DetectorCallback detectorCallback3 = detectorCallback;
                if (detectorCallback3 == null || !z) {
                    return;
                }
                detectorCallback3.onSetup(resultModel);
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (detectResult != null) {
                    if (DetectorGroup.this.mDetectorsAdding != null && DetectorGroup.this.mDetectorsAdding.size() > 0) {
                        IDetector iDetector2 = null;
                        for (IDetector iDetector3 : DetectorGroup.this.mDetectorsAdding) {
                            if (DetectorGroup.this.isRightDetector(detectResult, iDetector3) && DetectorGroup.this.isRightTime(detectResult)) {
                                iDetector2 = iDetector3;
                            }
                        }
                        if (iDetector2 != null && DetectorGroup.this.mDetectors != null) {
                            DetectorGroup.this.mDetectors.add(iDetector2);
                            DetectorGroup.this.mDetectorsAdding.remove(iDetector2);
                            ARLog.m20421d("DetectorGroup", "onDetected detector2Add = " + iDetector2.getName());
                        }
                    }
                    if (DetectorGroup.this.mDetectResultSync != null) {
                        DetectorGroup.this.mDetectResultSync.updateDetectResult(detectResult);
                        return;
                    }
                    return;
                }
                ARLog.m20419e("DetectorGroup", "onDetected detectResult is NULLLLLL!!!");
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
                if (DetectorGroup.this.mDetectResultSync != null) {
                    DetectorGroup.this.mDetectResultSync.onDetectorRelease(resultModel);
                }
                DetectorCallback detectorCallback3 = detectorCallback;
                if (detectorCallback3 != null) {
                    detectorCallback3.onRelease(resultModel);
                }
            }
        };
        if (z) {
            if (iDetector instanceof FrameDetector) {
                ((FrameDetector) iDetector).setDetectorCallback(detectorCallback2);
                detectorCallback2.onSetup(new ResultModel(iDetector.getName(), true));
                return;
            }
            return;
        }
        iDetector.setup(detectorCallback2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRightDetector(DetectResult detectResult, IDetector iDetector) {
        return detectResult.getDetectorName() != null && detectResult.getDetectorName().equals(iDetector.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRightTime(DetectResult detectResult) {
        DetectResultSync detectResultSync = this.mDetectResultSync;
        return detectResultSync != null && detectResultSync.isRightTimeAddDetector(detectResult);
    }
}
