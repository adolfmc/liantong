package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.arrender.RenderFaceData;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.databasic.AlgoHandleAdapter;
import com.baidu.p120ar.databasic.AlgoHandleController;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.face.FaceResultData;
import com.baidu.p120ar.face.FaceUtil;
import com.baidu.p120ar.face.detector.FaceResult;
import com.baidu.p120ar.face.detector.FaceResultModel;
import com.baidu.p120ar.lua.LuaMsgListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.FamilyWithChildAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FamilyWithChildAR extends AbstractAR implements ICapture {
    private static final String LUA_FILTER_ID = "filter_id";
    private static final String LUA_GET_PIXEL_FRAME = "get_pixel_frame";
    private static final String LUA_NEED_FACE = "need_face";
    private ICaptureAbilityListener mAbilityListener;
    private AlgoHandleController mAlgoHandleController;
    private AsyncWorker mAsyncWorker;
    private DetectorCallback mCameraDetectorCallback;
    private ICallbackWith<ICaptureResult> mCaptureCallback;
    private CaptureResult mCaptureResult;
    private CaptureDetector mDetectorCamera;
    private CaptureDetector mDetectorOutput;
    private DetectorCallback mFaceDetectCallback;
    private LuaMsgListener mLuaMsgListener;
    private DetectorCallback mOutputDetectorCallback;
    private int mCaptureImageWidth = 720;
    private int mCaptureImageHeight = 1280;
    private volatile boolean mIsCapturing = false;
    private boolean mIsFaceDataBack = false;
    private boolean mIsFaceListening = false;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        if (this.mInputWidth > this.mInputHeight) {
            this.mCaptureImageWidth = this.mInputHeight;
            this.mCaptureImageHeight = this.mInputWidth;
        } else {
            this.mCaptureImageWidth = this.mInputWidth;
            this.mCaptureImageHeight = this.mInputHeight;
        }
        this.mAlgoHandleController = new AlgoHandleController();
        this.mLuaMsgListener = new LuaMsgListener() { // from class: com.baidu.ar.capture.FamilyWithChildAR.1
            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public List<String> getMsgKeyListened() {
                return Arrays.asList("event_name");
            }

            @Override // com.baidu.p120ar.lua.LuaMsgListener
            public void onLuaMessage(HashMap<String, Object> hashMap2) {
                String str = (String) hashMap2.get("event_name");
                if ("get_pixel_frame".equals(str)) {
                    FamilyWithChildAR.this.startDetector((String) hashMap2.get("filter_id"));
                    FamilyWithChildAR.this.startCapture(null);
                } else if (!"need_face".equals(str) || FamilyWithChildAR.this.mAbilityListener == null) {
                } else {
                    FamilyWithChildAR.this.mAbilityListener.onOpen();
                }
            }
        };
        addLuaMsgListener(this.mLuaMsgListener);
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.addAlgoCache(23, false);
        }
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        ICaptureAbilityListener iCaptureAbilityListener = this.mAbilityListener;
        if (iCaptureAbilityListener != null) {
            iCaptureAbilityListener.onClose();
        }
        this.mAbilityListener = null;
        LuaMsgListener luaMsgListener = this.mLuaMsgListener;
        if (luaMsgListener != null) {
            removeLuaMsgListener(luaMsgListener);
            this.mLuaMsgListener = null;
        }
        stopDetector();
        AsyncWorker asyncWorker = this.mAsyncWorker;
        if (asyncWorker != null) {
            asyncWorker.stop();
            this.mAsyncWorker = null;
        }
        this.mCaptureCallback = null;
        AlgoHandleController algoHandleController = this.mAlgoHandleController;
        if (algoHandleController != null) {
            algoHandleController.release();
            this.mAlgoHandleController = null;
        }
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.removeAlgoCache(23);
        }
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startDetector(String str) {
        if (this.mDetectorCamera == null) {
            this.mDetectorCamera = new CaptureDetector("camera", this.mCaptureImageWidth, this.mCaptureImageHeight);
            this.mCameraDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.capture.FamilyWithChildAR.2
                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onRelease(ResultModel resultModel) {
                }

                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onSetup(ResultModel resultModel) {
                }

                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onDetected(DetectResult detectResult) {
                    if (FamilyWithChildAR.this.mIsCapturing && (detectResult instanceof CaptureDetectResult)) {
                        FamilyWithChildAR.this.syncCameraPhoto((CaptureDetectResult) detectResult);
                    }
                }
            };
            addDetector(this.mDetectorCamera, this.mCameraDetectorCallback);
        }
        CaptureDetector captureDetector = this.mDetectorOutput;
        if (captureDetector != null && !captureDetector.getPreFilter().equals(str)) {
            removeDetector(this.mDetectorOutput);
            this.mDetectorOutput = null;
        }
        if (this.mDetectorOutput == null) {
            if (TextUtils.isEmpty(str)) {
                str = "target";
            }
            this.mDetectorOutput = new CaptureDetector(str, this.mCaptureImageWidth, this.mCaptureImageHeight);
            this.mOutputDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.capture.FamilyWithChildAR.3
                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onRelease(ResultModel resultModel) {
                }

                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onSetup(ResultModel resultModel) {
                }

                @Override // com.baidu.p120ar.detector.DetectorCallback
                public void onDetected(DetectResult detectResult) {
                    if (FamilyWithChildAR.this.mIsCapturing && (detectResult instanceof CaptureDetectResult)) {
                        FamilyWithChildAR.this.syncOutputPhoto((CaptureDetectResult) detectResult);
                    }
                }
            };
            addDetector(this.mDetectorOutput, this.mOutputDetectorCallback);
        }
        if (this.mIsFaceListening) {
            return;
        }
        this.mIsFaceListening = true;
        this.mFaceDetectCallback = new DetectorCallback() { // from class: com.baidu.ar.capture.FamilyWithChildAR.4
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                if (FamilyWithChildAR.this.mIsCapturing && (detectResult instanceof FaceResult)) {
                    FamilyWithChildAR.this.syncFaceData((FaceResult) detectResult);
                }
                if (FamilyWithChildAR.this.mAlgoHandleController != null) {
                    FamilyWithChildAR.this.mAlgoHandleController.destroyHandle(detectResult.getResultHandle());
                } else {
                    AlgoHandleAdapter.destroyHandle(detectResult.getResultHandle());
                }
            }
        };
        requireStartDetector("FaceDetector", this.mFaceDetectCallback, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopDetector() {
        this.mIsFaceListening = false;
        DetectorCallback detectorCallback = this.mFaceDetectCallback;
        if (detectorCallback != null) {
            requireStopDetector("FaceDetector", detectorCallback);
            this.mFaceDetectCallback = null;
        }
        CaptureDetector captureDetector = this.mDetectorCamera;
        if (captureDetector != null) {
            removeDetector(captureDetector);
        }
        CaptureDetector captureDetector2 = this.mDetectorOutput;
        if (captureDetector2 != null) {
            removeDetector(captureDetector2);
        }
        this.mDetectorOutput = null;
        this.mDetectorCamera = null;
        this.mCameraDetectorCallback = null;
        this.mOutputDetectorCallback = null;
    }

    private boolean trySyncCaptureResult(DetectResult detectResult) {
        CaptureResult captureResult = this.mCaptureResult;
        if (captureResult != null) {
            if (captureResult.getTimestamp() == detectResult.getTimestamp()) {
                return true;
            }
            if (detectResult.getTimestamp() <= this.mCaptureResult.getTimestamp()) {
                return false;
            }
            this.mCaptureResult = null;
            CaptureDetector captureDetector = this.mDetectorCamera;
            if (captureDetector != null) {
                captureDetector.setNeedData(true);
            }
            CaptureDetector captureDetector2 = this.mDetectorOutput;
            if (captureDetector2 != null) {
                captureDetector2.setNeedData(true);
            }
            this.mIsFaceDataBack = false;
        }
        if (this.mCaptureResult == null) {
            this.mCaptureResult = new CaptureResult();
            this.mCaptureResult.timestamp = detectResult.getTimestamp();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncCameraPhoto(CaptureDetectResult captureDetectResult) {
        Log.d("ChildLook", "camera time: " + captureDetectResult.getTimestamp());
        CaptureResult captureResult = this.mCaptureResult;
        if ((captureResult == null || captureResult.originPhotoData == null) && trySyncCaptureResult(captureDetectResult) && this.mCaptureResult != null) {
            CaptureDetector captureDetector = this.mDetectorCamera;
            if (captureDetector != null) {
                captureDetector.setNeedData(false);
            }
            this.mCaptureResult.originPhotoData = captureDetectResult.getData();
            tryCallbackResult();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncOutputPhoto(CaptureDetectResult captureDetectResult) {
        Log.d("ChildLook", "output time: " + captureDetectResult.getTimestamp());
        CaptureResult captureResult = this.mCaptureResult;
        if ((captureResult == null || captureResult.outputPhotoData == null) && trySyncCaptureResult(captureDetectResult) && this.mCaptureResult != null) {
            CaptureDetector captureDetector = this.mDetectorOutput;
            if (captureDetector != null) {
                captureDetector.setNeedData(false);
            }
            this.mCaptureResult.outputPhotoData = captureDetectResult.getData();
            tryCallbackResult();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncFaceData(FaceResult faceResult) {
        FaceResultModel faceResultModel;
        if (this.mIsFaceDataBack) {
            return;
        }
        Log.d("ChildLook", "face time: " + faceResult.getTimestamp());
        if (!trySyncCaptureResult(faceResult) || (faceResultModel = faceResult.getFaceResultModel()) == null) {
            return;
        }
        this.mIsFaceDataBack = true;
        FaceResultData faceResultData = FaceUtil.getFaceResultData(faceResultModel);
        RenderFaceData renderFaceData = (RenderFaceData) faceResult.getResultData();
        if (faceResultData != null && renderFaceData != null) {
            faceResultData.setAlgoImageWidth(renderFaceData.getAlgoInputWidth());
            faceResultData.setAlgoImageHeight(renderFaceData.getAlgoInputHeight());
        }
        CaptureResult captureResult = this.mCaptureResult;
        if (captureResult != null) {
            captureResult.faceData = faceResultData;
        }
        tryCallbackResult();
    }

    private void tryCallbackResult() {
        CaptureResult captureResult;
        if (!this.mIsCapturing || (captureResult = this.mCaptureResult) == null || this.mCaptureCallback == null || captureResult.originPhotoData == null || this.mCaptureResult.outputPhotoData == null || !this.mIsFaceDataBack) {
            return;
        }
        setCapturing(false);
        CaptureResult captureResult2 = this.mCaptureResult;
        captureResult2.photoWidth = this.mCaptureImageWidth;
        captureResult2.photoHeight = this.mCaptureImageHeight;
        sendTask(new Runnable() { // from class: com.baidu.ar.capture.FamilyWithChildAR.5
            @Override // java.lang.Runnable
            public void run() {
                FamilyWithChildAR.this.stopDetector();
                FamilyWithChildAR.this.mCaptureCallback.run(FamilyWithChildAR.this.mCaptureResult);
                FamilyWithChildAR.this.mCaptureResult = null;
            }
        });
        this.mIsFaceDataBack = false;
    }

    private void sendTask(Runnable runnable) {
        if (this.mAsyncWorker == null) {
            this.mAsyncWorker = new AsyncWorker("FamilyWithChildAR");
            this.mAsyncWorker.start();
        }
        this.mAsyncWorker.execute(runnable);
    }

    private void setCapturing(boolean z) {
        CaptureDetector captureDetector = this.mDetectorCamera;
        if (captureDetector == null || this.mDetectorOutput == null) {
            return;
        }
        this.mIsCapturing = z;
        captureDetector.setNeedData(z);
        this.mDetectorOutput.setNeedData(z);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void setAbilityListener(ICaptureAbilityListener iCaptureAbilityListener) {
        this.mAbilityListener = iCaptureAbilityListener;
        if (iCaptureAbilityListener != null) {
            iCaptureAbilityListener.onOpen();
        }
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void setCaptureCallback(ICallbackWith<ICaptureResult> iCallbackWith) {
        this.mCaptureCallback = iCallbackWith;
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void capture(ICallbackWith<ICaptureResult> iCallbackWith) {
        startDetector(null);
        startCapture(iCallbackWith);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCapture(ICallbackWith<ICaptureResult> iCallbackWith) {
        if (iCallbackWith != null) {
            this.mCaptureCallback = iCallbackWith;
        }
        if (this.mIsCapturing) {
            return;
        }
        this.mIsFaceDataBack = false;
        this.mCaptureResult = null;
        setCapturing(true);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void sendImageToLua(Bitmap... bitmapArr) {
        if (bitmapArr == null || bitmapArr.length <= 0 || getRenderer() == null) {
            return;
        }
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        long createImagesHandle = ImageHandleUtil.createImagesHandle(this.mAlgoHandleController, bitmapArr, 23);
        getRenderer().setAlgoHandleData(createImagesHandle, "ability_capture");
        onAlgoHandleDestory(createImagesHandle);
    }

    @Override // com.baidu.p120ar.capture.ICapture
    public void sendBase64ImageToLua(String... strArr) {
        if (strArr == null || strArr.length <= 0 || getRenderer() == null) {
            return;
        }
        if (this.mAlgoHandleController == null) {
            this.mAlgoHandleController = new AlgoHandleController();
        }
        long createBase64ImagesHandle = ImageHandleUtil.createBase64ImagesHandle(this.mAlgoHandleController, strArr, 23);
        getRenderer().setAlgoHandleData(createBase64ImagesHandle, "ability_capture");
        onAlgoHandleDestory(createBase64ImagesHandle);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void onAlgoHandleDestory(long j) {
        AlgoHandleController algoHandleController;
        super.onAlgoHandleDestory(j);
        if (j <= 0 || (algoHandleController = this.mAlgoHandleController) == null || algoHandleController.getHandleType(j) != 23) {
            return;
        }
        this.mAlgoHandleController.destroyHandle(j);
    }
}
