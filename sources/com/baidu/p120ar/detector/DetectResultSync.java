package com.baidu.p120ar.detector;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.utils.ARLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.detector.DetectResultSync */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DetectResultSync {
    private static final int CACHE_COUNT_MAX = 180;
    private static final String TAG = "DetectResultSync";
    private DetectorCallback mCallback;
    private List<IDetector> mDetectors;
    private long mLastTimestamp;
    private int mMaxCacheSize;
    private HashMap<String, Boolean> mReleaseResultMap;
    private HashMap<String, Boolean> mSetupResultMap;
    private Handler mSyncHandler;
    private Looper mSyncLooper;
    private ArrayList<DetectResultGroup> mWaitSyncList;

    public DetectResultSync(Looper looper) {
        this.mWaitSyncList = new ArrayList<>();
        this.mSetupResultMap = new HashMap<>();
        this.mReleaseResultMap = new HashMap<>();
        this.mLastTimestamp = 0L;
        this.mMaxCacheSize = 180;
        this.mSyncLooper = looper;
    }

    public DetectResultSync(Looper looper, int i) {
        this.mWaitSyncList = new ArrayList<>();
        this.mSetupResultMap = new HashMap<>();
        this.mReleaseResultMap = new HashMap<>();
        this.mLastTimestamp = 0L;
        this.mMaxCacheSize = i;
    }

    public void setup(List<IDetector> list, DetectorCallback detectorCallback) {
        Looper looper;
        this.mDetectors = list;
        this.mCallback = detectorCallback;
        if (this.mSyncHandler != null || (looper = this.mSyncLooper) == null) {
            return;
        }
        this.mSyncHandler = new SyncHandler(looper);
    }

    public synchronized void onDetectorSetup(ResultModel resultModel) {
        ARLog.m20421d("DetectResultSync", "DetectorGroup onDetectorSetup result = " + resultModel.getDetectorName());
        if (this.mSyncHandler != null) {
            this.mSyncHandler.sendMessage(this.mSyncHandler.obtainMessage(1001, resultModel));
        }
    }

    public synchronized void updateDetectResult(DetectResult detectResult) {
        if (this.mSyncHandler != null) {
            this.mSyncHandler.sendMessage(this.mSyncHandler.obtainMessage(1002, detectResult));
        }
    }

    public synchronized void onDetectorRelease(ResultModel resultModel) {
        ARLog.m20421d("DetectResultSync", "DetectorGroup onDetectorRelease result = " + resultModel.getDetectorName());
        if (this.mSyncHandler != null) {
            this.mSyncHandler.sendMessage(this.mSyncHandler.obtainMessage(1003, resultModel));
        }
    }

    public synchronized boolean isRightTimeAddDetector(DetectResult detectResult) {
        if (this.mWaitSyncList.size() != 0) {
            if (this.mWaitSyncList.get(0).getTimestamp() < detectResult.getTimestamp()) {
                return false;
            }
        }
        return true;
    }

    public void release() {
        Handler handler = this.mSyncHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1004));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.detector.DetectResultSync$SyncHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class SyncHandler extends Handler {
        public static final int MSG_ON_DETECTOR_RELEASE = 1003;
        public static final int MSG_ON_DETECTOR_SETUP = 1001;
        public static final int MSG_RELEASE = 1004;
        public static final int MSG_UPDATE_DETECT_RESULT = 1002;

        public SyncHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    DetectResultSync.this.handleOnSetup((ResultModel) message.obj);
                    return;
                case 1002:
                    DetectResultSync.this.handleUpdate((DetectResult) message.obj);
                    return;
                case 1003:
                    DetectResultSync.this.handleOnRelease((ResultModel) message.obj);
                    return;
                case 1004:
                    DetectResultSync.this.handleRelease();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnSetup(ResultModel resultModel) {
        this.mSetupResultMap.put(resultModel.getDetectorName(), Boolean.valueOf(resultModel.isSuccess()));
        Iterator<IDetector> it = this.mDetectors.iterator();
        boolean z = true;
        boolean z2 = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IDetector next = it.next();
            if (this.mSetupResultMap.get(next.getName()) == null) {
                z = false;
                break;
            } else if (!this.mSetupResultMap.get(next.getName()).booleanValue()) {
                z2 = false;
            }
        }
        DetectorCallback detectorCallback = this.mCallback;
        if (detectorCallback != null) {
            detectorCallback.onSetup(resultModel);
            if (z) {
                this.mCallback.onSetup(new ResultModel("DetectorGroup", z2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdate(DetectResult detectResult) {
        HashMap<String, Boolean> hashMap;
        if (this.mWaitSyncList == null || (hashMap = this.mSetupResultMap) == null || hashMap.isEmpty()) {
            return;
        }
        DetectResultGroup add2ExistDetectResultGroup = add2ExistDetectResultGroup(detectResult);
        if (add2ExistDetectResultGroup == null) {
            add2ExistDetectResultGroup = add2NewDetectResultGroup(detectResult);
        }
        prepareDetectResultGroup(add2ExistDetectResultGroup);
    }

    private DetectResultGroup add2ExistDetectResultGroup(DetectResult detectResult) {
        Iterator<DetectResultGroup> it = this.mWaitSyncList.iterator();
        while (it.hasNext()) {
            DetectResultGroup next = it.next();
            if (next.getTimestamp() == detectResult.getTimestamp()) {
                next.addDetectResult(detectResult);
                return next;
            }
        }
        return null;
    }

    private DetectResultGroup add2NewDetectResultGroup(DetectResult detectResult) {
        if (this.mWaitSyncList.size() >= this.mMaxCacheSize) {
            ARLog.m20419e("DetectResultSync", "add2NewDetectResultGroup detectResult list for sync is fulllll!!!");
            return null;
        }
        DetectResultGroup detectResultGroup = new DetectResultGroup();
        detectResultGroup.setTimestamp(detectResult.getTimestamp());
        detectResultGroup.addDetectResult(detectResult);
        this.mWaitSyncList.add(detectResultGroup);
        return detectResultGroup;
    }

    private void prepareDetectResultGroup(DetectResultGroup detectResultGroup) {
        ArrayList<DetectResultGroup> arrayList;
        List<IDetector> list;
        if (detectResultGroup == null || (arrayList = this.mWaitSyncList) == null || arrayList.size() == 0 || (list = this.mDetectors) == null || list.size() == 0 || this.mCallback == null) {
            return;
        }
        boolean z = true;
        Iterator<IDetector> it = this.mDetectors.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (!detectResultGroup.isDetectResultContain(it.next().getName())) {
                z = false;
                break;
            }
        }
        if (!z || detectResultGroup.getTimestamp() == this.mLastTimestamp) {
            return;
        }
        this.mCallback.onDetected(detectResultGroup);
        this.mLastTimestamp = detectResultGroup.getTimestamp();
        ArrayList<DetectResultGroup> arrayList2 = this.mWaitSyncList;
        arrayList2.subList(0, arrayList2.indexOf(detectResultGroup)).clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnRelease(ResultModel resultModel) {
        this.mReleaseResultMap.put(resultModel.getDetectorName(), Boolean.valueOf(resultModel.isSuccess()));
        Iterator<IDetector> it = this.mDetectors.iterator();
        boolean z = true;
        boolean z2 = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            IDetector next = it.next();
            if (this.mReleaseResultMap.get(next.getName()) == null) {
                z = false;
                break;
            } else if (!this.mReleaseResultMap.get(next.getName()).booleanValue()) {
                z2 = false;
            }
        }
        DetectorCallback detectorCallback = this.mCallback;
        if (detectorCallback != null) {
            detectorCallback.onRelease(resultModel);
            if (z) {
                this.mCallback.onRelease(new ResultModel("DetectorGroup", z2));
                clearAllData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleRelease() {
        clearAllData();
        this.mSyncHandler.removeCallbacksAndMessages(null);
        this.mSyncHandler = null;
    }

    private void clearAllData() {
        List<IDetector> list = this.mDetectors;
        if (list != null) {
            list.clear();
        }
        ArrayList<DetectResultGroup> arrayList = this.mWaitSyncList;
        if (arrayList != null) {
            arrayList.clear();
        }
        HashMap<String, Boolean> hashMap = this.mSetupResultMap;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<String, Boolean> hashMap2 = this.mReleaseResultMap;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
    }
}
