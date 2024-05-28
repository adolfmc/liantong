package com.baidu.p120ar.pose;

import android.graphics.PointF;
import android.os.Bundle;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pose.PoseAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PoseAR extends AbstractAR {
    private static final int ALGO_IMAGE_HEIGHT = 180;
    private static final int ALGO_IMAGE_WIDTH = 320;
    public static final String MDL_MAGIC_FILTER_CUTOFFSLOPE = "cutoffSlope";
    public static final String MDL_MAGIC_FILTER_MIN_CUTOFFFREQ = "min_cutofffreq";
    public static final String MDL_START_POSE_FUN_EVENT_TYPE_KEY = "event_type";
    private DetectorCallback mDetectorCallback;
    private PoseDetector mPoseDetector;
    private int mAlgoImageWidth = 320;
    private int mAlgoImageHeight = 180;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        this.mPoseDetector = new PoseDetector();
        recalculateAlgoImageSize();
        this.mPoseDetector.setReadParams(this.mAlgoImageWidth, this.mAlgoImageHeight);
        this.mPoseDetector.setInputType(this.mIsCameraInput);
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.pose.PoseAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                PoseAR poseAR = PoseAR.this;
                poseAR.sendMsg2Lua(poseAR.parsePoseResult(((PoseResult) detectResult).getPoses()));
            }
        };
        addDetector(this.mPoseDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        ARMdlManager.getInstance().setCacheDir(getContext());
        Bundle bundle = new Bundle();
        handlePoseData(bundle, hashMap);
        this.mPoseDetector.enableMdl(bundle);
        IARRenderer renderer = getRenderer();
        if (renderer != null) {
            renderer.set3DModelVisible(true);
            renderer.setFieldOfView(calculateFov());
        }
    }

    @Override // com.baidu.p120ar.AbstractAR, com.baidu.p120ar.arrender.ARRenderer.InputSizeChangeListener
    public void onInputSizeChange(int i, int i2) {
        super.onInputSizeChange(i, i2);
    }

    private void recalculateAlgoImageSize() {
        int i;
        int i2 = this.mInputWidth;
        int i3 = this.mInputHeight;
        float f = i2;
        float f2 = i3;
        int i4 = 180;
        if (Float.compare((1.0f * f) / f2, 1.7777778f) == 0) {
            i = 320;
        } else if (i2 > i3) {
            i = (int) (f * (180.0f / f2));
        } else {
            i4 = (int) (f2 * (180.0f / f));
            i = 180;
        }
        this.mAlgoImageWidth = i;
        this.mAlgoImageHeight = i4;
    }

    private float calculateFov() {
        int i;
        if (this.mInputWidth == 0 || this.mInputHeight == 0) {
            return 56.144978f;
        }
        int i2 = this.mInputDegree;
        if (i2 == 90 || i2 == 270) {
            i = this.mInputWidth;
        } else {
            i = this.mInputHeight;
        }
        return (float) (((Math.atan2(i * 0.5f, Math.max(this.mInputWidth, this.mInputHeight) * 0.94375f) * 2.0d) * 180.0d) / 3.141592653589793d);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        PoseDetector poseDetector = this.mPoseDetector;
        if (poseDetector != null) {
            poseDetector.disableMdl();
            removeDetector(this.mPoseDetector);
        }
        super.release();
    }

    private void handlePoseData(Bundle bundle, HashMap<String, Object> hashMap) {
        bundle.putString("event_type", (String) hashMap.get("event_type"));
        HashMap hashMap2 = (HashMap) hashMap.get("algo_map");
        float[] fArr = null;
        if (hashMap2 != null) {
            r1 = hashMap2.containsKey("cutoffSlope") ? ((Float) hashMap2.get("cutoffSlope")).floatValue() : 1.0f;
            if (hashMap2.containsKey("min_cutofffreq")) {
                String[] split = ((String) hashMap2.get("min_cutofffreq")).split(",");
                fArr = new float[split.length];
                for (int i = 0; i < split.length; i++) {
                    fArr[i] = Float.parseFloat(split[i]);
                }
            }
        }
        bundle.putFloat("cutoffSlope", r1);
        bundle.putFloatArray("min_cutofffreq", fArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap parsePoseResult(float[] fArr) {
        Vector3f vector3f;
        HashMap hashMap = new HashMap();
        hashMap.put("event_name", "body_tracking_data");
        IARRenderer renderer = getRenderer();
        if (renderer == null) {
            return hashMap;
        }
        boolean z = this.mIsCameraInput;
        if (fArr != null && fArr.length > 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < 18; i++) {
                int i2 = i * 3;
                PointF pointF = new PointF(fArr[i2], fArr[i2 + 1]);
                renderer.convertAlgo2ScreenPoint(pointF, z);
                if (this.mIsCameraInput) {
                    vector3f = new Vector3f(pointF.x, pointF.y, fArr[i2 + 2]);
                } else {
                    vector3f = new Vector3f(pointF.y, pointF.x, fArr[i2 + 2]);
                }
                arrayList2.add(vector3f);
            }
            arrayList.add(arrayList2);
            hashMap.put("event_data", arrayList);
        }
        return hashMap;
    }
}
