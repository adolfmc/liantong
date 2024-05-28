package com.baidu.p120ar.face.attributes;

import android.content.Context;
import android.text.TextUtils;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.face.FaceResultData;
import com.baidu.p120ar.face.algo.FAUFaceBox;
import com.baidu.p120ar.face.algo.FaceAlgoData;
import com.baidu.p120ar.face.algo.FaceFrame;
import com.baidu.p120ar.face.detector.FaceResultModel;
import com.baidu.p120ar.utils.ARLog;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.attributes.FaceAttributesManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceAttributesManager {
    private static final String ASSETS_PATH = "file:///android_asset/";
    private static final String TAG = "FaceAttributesManager";
    private HashMap<Integer, float[]> genderMap = new HashMap<>();
    private boolean hasInit = false;
    private IARRenderer renderer;

    public FaceAttributesManager(IARRenderer iARRenderer) {
        this.renderer = iARRenderer;
    }

    public int init(Context context, String str) {
        int initGenderDetect;
        if (TextUtils.isEmpty(str)) {
            ARLog.m20419e("FaceAttributesManager", "gender model path is empty.");
            return -1;
        }
        if (this.hasInit) {
            return 0;
        }
        if (FaceAttributesJni.loadSuccess) {
            if (str.startsWith("file:///android_asset/")) {
                String replace = str.replace("file:///android_asset/", "");
                if (!checkAssetFile(context, replace)) {
                    return -1;
                }
                FaceAttributesJni.setAssetManager(context.getAssets());
                initGenderDetect = FaceAttributesJni.initGenderDetectFromAssets(replace);
            } else if (!checkLocalFile(str)) {
                return -1;
            } else {
                initGenderDetect = FaceAttributesJni.initGenderDetect(str);
            }
            this.hasInit = initGenderDetect == 0;
            return initGenderDetect;
        }
        return -1;
    }

    public void reset() {
        this.genderMap.clear();
    }

    public void release() {
        this.renderer = null;
        if (this.hasInit) {
            this.hasInit = false;
            FaceAttributesJni.releaseGenderDetect();
        }
    }

    public void syncDetectGender(FaceResultModel faceResultModel, FaceResultData faceResultData, int i, int i2) {
        if (checkToDetectGender(faceResultModel)) {
            FaceAlgoData faceAlgoData = faceResultModel.getFaceAlgoData();
            FaceFrame faceFrame = faceAlgoData == null ? null : faceAlgoData.getFaceFrame();
            if (faceFrame == null) {
                ARLog.m20421d("FaceAttributesManager", "faceFrame == null.");
                return;
            }
            List<FAUFaceBox> faceBoxes = faceFrame.getFaceBoxes();
            int[] faceIDList = faceFrame.getFaceIDList();
            if (faceBoxes == null || faceBoxes.size() == 0 || faceIDList == null || faceIDList.length == 0) {
                ARLog.m20421d("FaceAttributesManager", "fauFaceBoxes is empty.");
                return;
            }
            faceResultData.setFaceIds(faceIDList);
            int i3 = faceIDList[0];
            float[] fArr = this.genderMap.get(Integer.valueOf(i3));
            if (fArr != null) {
                faceResultData.setGenders(fArr);
                return;
            }
            float[] detectGender = detectGender(faceResultModel.getCameraData(), faceBoxes.get(0), i, i2);
            if (detectGender == null || !this.hasInit) {
                return;
            }
            this.genderMap.put(Integer.valueOf(i3), detectGender);
            faceResultData.setGenders(detectGender);
            IARRenderer iARRenderer = this.renderer;
            if (iARRenderer != null) {
                iARRenderer.setEnvironmentDataPipKV("face_gender_predict", Float.valueOf(detectGender[0]));
            }
        }
    }

    private static float[] detectGender(ByteBuffer byteBuffer, FAUFaceBox fAUFaceBox, int i, int i2) {
        float[] fArr = new float[2];
        if (FaceAttributesJni.predictGenderDetect(byteBuffer, i, i2, new float[]{fAUFaceBox.getX(), fAUFaceBox.getY(), fAUFaceBox.getWidth(), fAUFaceBox.getHeight(), fAUFaceBox.getAngle()}, fArr) == 0) {
            return fArr;
        }
        return null;
    }

    private boolean checkToDetectGender(FaceResultModel faceResultModel) {
        if (this.hasInit) {
            if (faceResultModel.isTracked()) {
                return true;
            }
            ARLog.m20421d("FaceAttributesManager", "faceResult.isTracked == false.");
            return false;
        }
        return false;
    }

    private boolean checkAssetFile(Context context, String str) {
        try {
            context.getAssets().open(str).close();
            return true;
        } catch (Exception unused) {
            ARLog.m20419e("FaceAttributesManager", "open asset model path error:" + str);
            return false;
        }
    }

    private boolean checkLocalFile(String str) {
        if (new File(str).exists()) {
            return true;
        }
        ARLog.m20419e("FaceAttributesManager", "open model path error:" + str);
        return false;
    }
}
