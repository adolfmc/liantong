package com.example.asus.detectionandalign;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import com.example.asus.detectionandalign.listener.ResultListener;
import com.example.asus.detectionandalign.ocr.ManualOcrActivity;
import com.example.asus.detectionandalign.p193a.C4259b;
import com.example.asus.detectionandalign.p193a.C4269c;
import com.example.asus.detectionandalign.utils.C4293e;
import comp.android.app.face.p381sz.camera.JCameraView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DetectionAuthentic {
    public static final int DETECT_ABNORMAL = 536;
    public static final int DETECT_CHECKING = 537;
    public static final int DETECT_COUNT_DOWN_TEXT = 8773;
    public static final int DETECT_DISMISS_DIALOG = 8807;
    public static final int DETECT_FACE_SCREEN = 546;
    public static final int DETECT_FACING_VIEWFINDER = 544;
    public static final int DETECT_FACING_VIEWFINDER1 = 8705;
    public static final int DETECT_FINISH = 8756;
    public static final int DETECT_HINT = 8808;
    public static final int DETECT_KEEP_ON = 533;
    public static final int DETECT_LOG = 535;
    public static final int DETECT_NON_LIVING_BODY = 534;
    public static final int DETECT_NON_LIVING_BODY2 = 545;
    public static final int DETECT_RESET_COUNTDOWN = 532;
    public static final int DETECT_STOP_COUNT_DOWN_TIMER = 8790;
    public static final String FRAME = "frame";
    public static final int GENERATED_ANEW_RECORD = 8498;
    public static final int GENERATED_FAILED = 529;
    public static final int GENERATED_FINISH_RECORD = 8497;
    public static final int GENERATED_PACKET = 519;
    public static final int GENERATED_START_RECORD = 528;
    public static final int GENERATED_STOP_RECORD = 531;
    public static final int IDENTITY_CARD_BACK = 2;
    public static final int IDENTITY_CARD_FRAMELESS = 3;
    public static final int IDENTITY_CARD_FRONT = 1;
    private static final String TAG = "DetectionAuthentic";
    private static DetectionAuthentic detectionAuthentic;
    private static boolean isInitMode;
    public static byte[] liveness_bin;
    private C4259b livenessAsyncTask;
    public com.example.landmarksdk.faceRecognition mFace = new com.example.landmarksdk.faceRecognition();
    private ResultListener resultListener;
    private C4269c startActionAsyncTask;

    public DetectionAuthentic(ResultListener resultListener, Context context) {
        this.resultListener = resultListener;
    }

    public static synchronized DetectionAuthentic getInstance(Context context, ResultListener resultListener) {
        synchronized (DetectionAuthentic.class) {
            if (detectionAuthentic == null) {
                detectionAuthentic = new DetectionAuthentic(resultListener, context);
            }
            if (!isInitMode) {
                isInitMode = true;
                if (!detectionAuthentic.initModel(context)) {
                    detectionAuthentic.onSDKUsingFail("初始化模型失败", "0001");
                    detectionAuthentic = null;
                    return detectionAuthentic;
                }
            }
            return detectionAuthentic;
        }
    }

    private void release() {
        detectionAuthentic = null;
    }

    public void autenticateToActionSetting(Context context) {
        try {
            context.startActivity(new Intent(context, SettingActionActivity.class));
        } catch (Exception e) {
            Log.e(TAG, "无法开始设置...", e);
        }
    }

    public void autenticateToCaptureAction(Context context, int i, int i2, ActionConfig actionConfig) {
        if (!C4293e.m15951a(0)) {
            detectionAuthentic.onSDKUsingFail("不能重复启动", "2008");
            return;
        }
        try {
            Intent intent = new Intent(context, StartActionNew.class);
            intent.putExtra("TotalActions", i);
            intent.putExtra("TimeoutMs", i2);
            intent.putExtra("ActionConfiguration", actionConfig);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "无法开始活体检测...", e);
        }
    }

    public void autenticateToLivenessDetection(Context context, int i, int i2, int i3, int i4, LivenessConfig livenessConfig) {
        if (!C4293e.m15951a(0)) {
            detectionAuthentic.onSDKUsingFail("不能重复启动", "2008");
            return;
        }
        try {
            Intent intent = new Intent(context, StartLivenessNew.class);
            intent.putExtra("TotalActions", i);
            intent.putExtra("TimeoutMs", i2);
            intent.putExtra("TotalPictures", i3);
            intent.putExtra("LivenessConfiguration", livenessConfig);
            intent.putExtra("countDown", i4);
            context.startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "无法开始活体检测...", e);
        }
    }

    public void autenticateToSetting(Context context) {
        try {
            context.startActivity(new Intent(context, SettingActivity.class));
        } catch (Exception e) {
            Log.e(TAG, "无法开始设置...", e);
        }
    }

    public void autenticateToShotIdCard(Context context, int i) {
        if (!C4293e.m15951a(0)) {
            detectionAuthentic.onSDKUsingFail("不能重复启动", "2008");
            return;
        }
        Intent intent = new Intent(context, ManualOcrActivity.class);
        intent.putExtra(FRAME, i);
        context.startActivity(intent);
    }

    public void captureSuccess(Bitmap bitmap) {
        ResultListener resultListener = this.resultListener;
        if (resultListener != null) {
            resultListener.captureSuccess(bitmap);
        }
    }

    public String getActionHint(String str) {
        return this.startActionAsyncTask.m16043e(str);
    }

    public void getActionInstance(Activity activity, Handler handler, DetectionAuthentic detectionAuthentic2, int i, int i2) {
        this.startActionAsyncTask = C4269c.m16092a(activity, handler, detectionAuthentic2, i, i2);
    }

    public String getCurrentAction() {
        return this.startActionAsyncTask.m16059c();
    }

    public void getLivenessBinAsserts(Context context, String str) {
        InputStream open = context.getAssets().open(str);
        liveness_bin = new byte[open.available()];
        open.read(liveness_bin);
        open.close();
    }

    public void getLivenessInstance(Context context, Handler handler, DetectionAuthentic detectionAuthentic2, int i, int i2, int i3, int i4) {
        this.livenessAsyncTask = C4259b.m16205a(context, handler, detectionAuthentic2, i, i2, i3, i4);
    }

    public boolean initModel(Context context) {
        try {
            getLivenessBinAsserts(context, "liveness_20190614_encrypt.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bArr = liveness_bin;
        if (bArr != null) {
            return this.mFace.modelInit(context, bArr);
        }
        return false;
    }

    public void onActionDestroy() {
        this.startActionAsyncTask.m16094a();
    }

    public void onActionImageCaptured(String str) {
        this.resultListener.onFaceImageCaptured(str);
    }

    public void onLivenessDestroy() {
        this.livenessAsyncTask.m16207a();
    }

    public void onLivenessImageCaptured(String str) {
        this.resultListener.onFaceImageCaptured(str);
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
    }

    public void onSDKUsingFail(String str, String str2) {
        ResultListener resultListener = this.resultListener;
        if (resultListener != null) {
            resultListener.onSDKUsingFail(str, str2);
        }
    }

    public void releaseDetectionAuthentic() {
        release();
    }

    public void setAction(List<String> list) {
        this.startActionAsyncTask.m16078a(list);
    }

    public void setActionCachePathFailedLiveness(boolean z) {
        this.startActionAsyncTask.m16061b(z);
    }

    public void setActionEnfiladeParam(float f) {
        this.startActionAsyncTask.m16069b(f);
    }

    public void setActionIsTailor4_3(boolean z) {
        this.startActionAsyncTask.m16053c(z);
    }

    public void setActionList(List<Double> list) {
        this.startActionAsyncTask.m16062b(list);
    }

    public void setActionLivenessCheck(boolean z) {
        this.startActionAsyncTask.m16042e(z);
    }

    public void setActionLivenessParam(float f) {
        this.startActionAsyncTask.m16093a(f);
    }

    public void setActionPreKjnovaClipper(boolean z) {
        this.startActionAsyncTask.m16046d(z);
    }

    public void setActionVerifyAnimation(boolean z) {
        this.startActionAsyncTask.m16077a(z);
    }

    public void setCachePathMedia(String str) {
        this.livenessAsyncTask.m16165c(str);
    }

    public void setLivenessCachePathFailedLiveness(boolean z) {
        this.livenessAsyncTask.m16184a(z);
    }

    public void setLivenessEnfiladeParam(float f) {
        this.livenessAsyncTask.m16180b(f);
    }

    public void setLivenessIsTailor4_3(boolean z) {
        this.livenessAsyncTask.m16158d(z);
    }

    public void setLivenessParam(float f) {
        this.livenessAsyncTask.m16206a(f);
    }

    public void setLivenessPreKjnovaClipper(boolean z) {
        this.livenessAsyncTask.m16171b(z);
    }

    public void setLivenessReturnType(boolean z) {
        this.livenessAsyncTask.m16164c(z);
    }

    public void startingActionTest(byte[] bArr, int i, int i2) {
        this.startActionAsyncTask.m16060b(bArr, i, i2);
    }

    public void startingActionTest(byte[] bArr, JCameraView jCameraView) {
        this.startActionAsyncTask.m16076a(bArr, jCameraView);
    }

    public void startingLivenessTest(byte[] bArr, int i, int i2) {
        this.livenessAsyncTask.m16170b(bArr, i, i2);
    }

    public void startingLivenessTest(byte[] bArr, JCameraView jCameraView) {
        this.livenessAsyncTask.m16183a(bArr, jCameraView);
    }

    public void videoLivenessCut(File file) {
        this.livenessAsyncTask.m16187a(file);
    }
}
