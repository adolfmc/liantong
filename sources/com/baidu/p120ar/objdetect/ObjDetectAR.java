package com.baidu.p120ar.objdetect;

import android.graphics.PointF;
import android.os.Bundle;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.objdetect.ObjDetectAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ObjDetectAR extends AbstractAR {
    private DetectorCallback mDetectorCallback;
    private ObjDetectDetector mObjDetector;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        this.mObjDetector = new ObjDetectDetector();
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.objdetect.ObjDetectAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                float[] objData = ((ObjDetectResult) detectResult).getObjData();
                ObjDetectAR objDetectAR = ObjDetectAR.this;
                objDetectAR.sendMsg2Lua(objDetectAR.parseObjectDetectResult(objData));
            }
        };
        addDetector(this.mObjDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        this.mObjDetector.enableMdl(new Bundle());
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        ObjDetectDetector objDetectDetector = this.mObjDetector;
        if (objDetectDetector != null) {
            objDetectDetector.disableMdl();
            removeDetector(this.mObjDetector);
        }
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap parseObjectDetectResult(float[] fArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event_name", "object_detect");
        int i = (int) fArr[0];
        ArrayList arrayList = new ArrayList();
        IARRenderer renderer = getRenderer();
        if (renderer == null) {
            hashMap.put("event_data", arrayList);
            return hashMap;
        }
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (i2 * 7) + 1;
            HashMap hashMap2 = new HashMap();
            PointF pointF = new PointF(fArr[i3 + 3], fArr[i3 + 4]);
            renderer.convertAlgo2ScreenPoint(pointF, true);
            PointF pointF2 = new PointF(fArr[i3 + 5], fArr[i3 + 6]);
            renderer.convertAlgo2ScreenPoint(pointF2, true);
            hashMap2.put("object_id", Float.valueOf(fArr[i3]));
            hashMap2.put("category", 3);
            hashMap2.put("score", Float.valueOf(fArr[i3 + 2]));
            hashMap2.put("x1", Float.valueOf(pointF.x));
            hashMap2.put("y1", Float.valueOf(pointF.y));
            hashMap2.put("x2", Float.valueOf(pointF2.x));
            hashMap2.put("y2", Float.valueOf(pointF2.y));
            arrayList.add(hashMap2);
        }
        hashMap.put("event_data", arrayList);
        return hashMap;
    }
}
