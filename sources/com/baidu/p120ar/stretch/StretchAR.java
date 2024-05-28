package com.baidu.p120ar.stretch;

import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arplay.representation.Vector3f;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.stretch.StretchAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class StretchAR extends AbstractAR {
    private DetectorCallback mDetectorCallback;
    private StretchDetector mStretchDetector;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        this.mStretchDetector = new StretchDetector();
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.stretch.StretchAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                StretchAR stretchAR = StretchAR.this;
                stretchAR.sendMsg2Lua(stretchAR.parsePoseResult(((StretchResult) detectResult).getPoses()));
            }
        };
        addDetector(this.mStretchDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        this.mStretchDetector.enableMdl(null);
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        StretchDetector stretchDetector = this.mStretchDetector;
        if (stretchDetector != null) {
            stretchDetector.disableMdl();
            removeDetector(this.mStretchDetector);
        }
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap parsePoseResult(float[] fArr) {
        HashMap hashMap = new HashMap();
        hashMap.put("event_name", "body_tracking_data");
        if (fArr != null && fArr.length > 0) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (int i = 0; i < 18; i++) {
                int i2 = i * 3;
                arrayList2.add(new Vector3f(fArr[i2], fArr[i2 + 1], fArr[i2 + 2]));
            }
            arrayList.add(arrayList2);
            hashMap.put("event_data", arrayList);
        }
        return hashMap;
    }
}
