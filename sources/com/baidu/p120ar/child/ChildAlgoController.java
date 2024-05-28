package com.baidu.p120ar.child;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.child.detector.ChildCameraDetectResult;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.ChildAlgoController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ChildAlgoController {
    private ChildAlgoCallback mChildAlgoCallback;
    private Handler mChildFaceHandler;
    private ChildMsgHandler mChildHandleCallback;
    private CropAlgo mCropAlgoAlgoClient;
    private int mInputHeight;
    private int mInputWidth;
    private boolean mIsInit;
    private boolean mEnableChild = true;
    private HandlerThread mAlgoThread = new HandlerThread("ChildAlgoController");

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.child.ChildAlgoController$ChildAlgoCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface ChildAlgoCallback {
        void onChildAlgoResult(long j, byte[] bArr, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.child.ChildAlgoController$ChildHandlerListenr */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface ChildHandlerListenr {
        void handleMessage(Message message);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.child.ChildAlgoController$ChildMsgHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class ChildMsgHandler implements ChildHandlerListenr {
        ChildMsgHandler() {
        }

        @Override // com.baidu.p120ar.child.ChildAlgoController.ChildHandlerListenr
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1002:
                    ChildCropModel childCropModel = (ChildCropModel) message.obj;
                    if (childCropModel != null) {
                        ChildCameraDetectResult childCameraDetectResult = childCropModel.getChildCameraDetectResult();
                        long faceHandle = childCropModel.getFaceResultModel().getFaceHandle();
                        ChildFaceCropInputModel childFaceCropInputModel = new ChildFaceCropInputModel();
                        childFaceCropInputModel.setAngle(childCameraDetectResult.getDegree());
                        childFaceCropInputModel.setCameraData(childCameraDetectResult.getDatas());
                        childFaceCropInputModel.setFaceBoxList(childCropModel.getFaceResultModel().getFaceList());
                        childFaceCropInputModel.setTrackingPoints(childCropModel.getFaceResultModel().getTrackingPoints());
                        childFaceCropInputModel.setFrameIndex(childCropModel.getFrameSecret());
                        childFaceCropInputModel.setHandle(faceHandle);
                        childFaceCropInputModel.setFront(childCropModel.getChildCameraDetectResult().isFront());
                        childFaceCropInputModel.setWidth(ChildAlgoController.this.mInputWidth);
                        childFaceCropInputModel.setHeight(ChildAlgoController.this.mInputHeight);
                        byte[] cropFace = ChildAlgoController.this.cropFace(childFaceCropInputModel);
                        ChildAlgoController.this.mIsInit = true;
                        if (ChildAlgoController.this.mChildAlgoCallback != null) {
                            ChildAlgoController.this.mChildAlgoCallback.onChildAlgoResult(faceHandle, cropFace, childCameraDetectResult.getDegree());
                            return;
                        }
                        return;
                    }
                    return;
                case 1003:
                    ChildAlgoController.this.clear();
                    if (ChildAlgoController.this.mChildFaceHandler != null) {
                        ChildAlgoController.this.mChildFaceHandler.removeCallbacksAndMessages(null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public ChildAlgoController(int i, int i2) {
        this.mInputHeight = i2;
        this.mInputWidth = i;
        this.mAlgoThread.start();
        if (this.mChildHandleCallback == null) {
            this.mChildHandleCallback = new ChildMsgHandler();
        }
        this.mChildFaceHandler = new ChildBoyAlgoHandler(this.mAlgoThread.getLooper(), this.mChildHandleCallback);
        if (this.mCropAlgoAlgoClient == null) {
            this.mCropAlgoAlgoClient = new CropAlgo();
        }
    }

    public void requestWriteFaceToHandle() {
        Handler handler = this.mChildFaceHandler;
        if (handler != null) {
            handler.removeMessages(1004);
            Handler handler2 = this.mChildFaceHandler;
            handler2.sendMessage(handler2.obtainMessage(1004));
        }
    }

    public void requestClear() {
        Handler handler = this.mChildFaceHandler;
        if (handler != null) {
            handler.removeMessages(1003);
            Handler handler2 = this.mChildFaceHandler;
            handler2.sendMessage(handler2.obtainMessage(1003));
        }
    }

    public void requestCropFace(ChildCropModel childCropModel) {
        Handler handler = this.mChildFaceHandler;
        if (handler == null || !this.mEnableChild) {
            return;
        }
        handler.removeMessages(1002);
        Handler handler2 = this.mChildFaceHandler;
        handler2.sendMessage(handler2.obtainMessage(1002, childCropModel));
    }

    public void setChildAlgoCallback(ChildAlgoCallback childAlgoCallback) {
        this.mChildAlgoCallback = childAlgoCallback;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.child.ChildAlgoController$ChildBoyAlgoHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class ChildBoyAlgoHandler extends Handler {
        public static final int MSG_REQUEST_CHILD_ALGO_CLEAR = 1003;
        public static final int MSG_REQUEST_CHILD_ALGO_CROP = 1002;
        public static final int MSG_REQUEST_CHILD_WRITE_FACE = 1004;
        private ChildHandlerListenr mCommonHandlerListener;

        public ChildBoyAlgoHandler(Looper looper, ChildHandlerListenr childHandlerListenr) {
            super(looper);
            this.mCommonHandlerListener = childHandlerListenr;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ChildHandlerListenr childHandlerListenr = this.mCommonHandlerListener;
            if (childHandlerListenr != null) {
                childHandlerListenr.handleMessage(message);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] cropFace(ChildFaceCropInputModel childFaceCropInputModel) {
        if (childFaceCropInputModel.getHandle() <= 0 || checkArrayIsNull(childFaceCropInputModel.getFaceBoxList()) || checkArrayIsNull(childFaceCropInputModel.getTrackingPoints())) {
            return null;
        }
        return this.mCropAlgoAlgoClient.nativeCorpFace(childFaceCropInputModel);
    }

    private boolean checkArrayIsNull(float[] fArr) {
        return fArr == null || fArr.length <= 0;
    }

    public float[] getTrackingPoints(long j) {
        if (j <= 0) {
            return null;
        }
        return this.mCropAlgoAlgoClient.nativeTrackingPoints(j);
    }

    public float[] getFaceBoxList(long j) {
        if (j <= 0) {
            return null;
        }
        return this.mCropAlgoAlgoClient.nativeGetFaceBoxList(j);
    }

    public void writeTypeToHandle(long j) {
        if (j <= 0) {
            return;
        }
        this.mCropAlgoAlgoClient.nativeWriteTypeToHandle(j);
    }

    public long writeFaceToHandle(long j, byte[] bArr) {
        if (j <= 0) {
            return 0L;
        }
        return this.mCropAlgoAlgoClient.nativeWriteFaceDataToHandel(j, bArr);
    }

    public long writeFilterDataToHandle(long j, byte[] bArr, int i, int i2, float f) {
        if (j <= 0) {
            return 0L;
        }
        return this.mCropAlgoAlgoClient.nativeWriteCameraDataToHandel(j, bArr, i, i2, f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clear() {
        CropAlgo cropAlgo;
        HandlerThread handlerThread = this.mAlgoThread;
        if (handlerThread != null) {
            handlerThread.getLooper().quit();
            this.mAlgoThread = null;
        }
        if (this.mIsInit && (cropAlgo = this.mCropAlgoAlgoClient) != null) {
            cropAlgo.nativeClear();
            this.mCropAlgoAlgoClient = null;
        }
        this.mIsInit = false;
        this.mChildAlgoCallback = null;
        if (this.mChildFaceHandler != null) {
            this.mChildFaceHandler = null;
        }
    }
}
