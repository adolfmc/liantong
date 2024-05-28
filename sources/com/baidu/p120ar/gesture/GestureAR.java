package com.baidu.p120ar.gesture;

import android.graphics.PointF;
import android.os.Bundle;
import com.baidu.p120ar.AbstractAR;
import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.detector.DetectResult;
import com.baidu.p120ar.detector.DetectorCallback;
import com.baidu.p120ar.detector.ResultModel;
import com.baidu.p120ar.statistic.StatisticApi;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.gesture.GestureAR */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class GestureAR extends AbstractAR {
    public static final int MDL_GESTURE_STATUS_DETECTED = 5002;
    public static final String SDK_TO_LUA_GESTURE_RESULT_BODY = "gesture_result";
    public static final String SDK_TO_LUA_GESTURE_RESULT_COUNT = "gesture_count";
    public static final String SDK_TO_LUA_GESTURE_RESULT_FTX = "ftx";
    public static final String SDK_TO_LUA_GESTURE_RESULT_FTY = "fty";
    public static final String SDK_TO_LUA_GESTURE_RESULT_JNTX = "jntx";
    public static final String SDK_TO_LUA_GESTURE_RESULT_JNTY = "jnty";
    public static final String SDK_TO_LUA_GESTURE_RESULT_RESERVED = "reserved";
    public static final String SDK_TO_LUA_GESTURE_RESULT_SCORE = "score";
    public static final String SDK_TO_LUA_GESTURE_RESULT_TJNTX = "tjntx";
    public static final String SDK_TO_LUA_GESTURE_RESULT_TJNTY = "tjnty";
    public static final String SDK_TO_LUA_GESTURE_RESULT_TYPE = "type";
    public static final String SDK_TO_LUA_GESTURE_RESULT_X1 = "x1";
    public static final String SDK_TO_LUA_GESTURE_RESULT_X2 = "x2";
    public static final String SDK_TO_LUA_GESTURE_RESULT_Y1 = "y1";
    public static final String SDK_TO_LUA_GESTURE_RESULT_Y2 = "y2";
    private static final String TAG = "GestureAR";
    private DetectorCallback mDetectorCallback;
    private GestureDetector mGestureDetector;

    @Override // com.baidu.p120ar.AbstractAR
    public void setup(HashMap<String, Object> hashMap) {
        super.setup(hashMap);
        this.mGestureDetector = new GestureDetector();
        this.mDetectorCallback = new DetectorCallback() { // from class: com.baidu.ar.gesture.GestureAR.1
            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onRelease(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onSetup(ResultModel resultModel) {
            }

            @Override // com.baidu.p120ar.detector.DetectorCallback
            public void onDetected(DetectResult detectResult) {
                float[] gesture = ((GestureResult) detectResult).getGesture();
                GestureAR gestureAR = GestureAR.this;
                gestureAR.sendMsg2Lua(gestureAR.parseGestureResult(gesture));
            }
        };
        addDetector(this.mGestureDetector, this.mDetectorCallback);
        ARMdlManager.getInstance().setConfigs(getContext(), getMdlConfigs());
        Bundle bundle = new Bundle();
        handGestureExtraData(bundle, hashMap);
        this.mGestureDetector.enableMdl(bundle);
        StatisticApi.onEvent("paddle_gesture_open");
    }

    @Override // com.baidu.p120ar.AbstractAR
    public void release() {
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.disableMdl();
            removeDetector(this.mGestureDetector);
        }
        super.release();
    }

    private void handGestureExtraData(Bundle bundle, HashMap<String, Object> hashMap) {
        bundle.putString("function_type", MsgParamsUtil.obj2String(hashMap.get("function_type"), "gesture"));
        Map map = (Map) hashMap.get("extra_data");
        if (map == null || map.isEmpty()) {
            return;
        }
        if (map.containsKey("force_ft_pose_flag")) {
            bundle.putInt("force_ft_pose_flag", MsgParamsUtil.obj2Int(map.get("force_ft_pose_flag"), 0));
        }
        if (map.containsKey("det_thresh")) {
            bundle.putFloat("det_thresh", MsgParamsUtil.obj2Float(map.get("det_thresh"), 0.25f));
        }
        if (map.containsKey("first_cls_thresh")) {
            bundle.putFloat("first_cls_thresh", MsgParamsUtil.obj2Float(map.get("first_cls_thresh"), 0.75f));
        }
        if (map.containsKey("second_cls_thresh")) {
            bundle.putFloat("second_cls_thresh", MsgParamsUtil.obj2Float(map.get("second_cls_thresh"), 0.5f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HashMap parseGestureResult(float[] fArr) {
        HashMap hashMap = new HashMap();
        int length = fArr.length / 13;
        hashMap.put("id", 5002);
        IARRenderer renderer = getRenderer();
        int i = 0;
        if (renderer == null) {
            hashMap.put("gesture_count", 0);
            return hashMap;
        }
        hashMap.put("gesture_count", Integer.valueOf(length));
        while (i < length) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            int i2 = i + 1;
            sb.append(i2);
            String concat = "gesture_result".concat(sb.toString());
            int i3 = i * 13;
            PointF pointF = new PointF(fArr[i3 + 3], fArr[i3 + 4]);
            renderer.convertAlgo2ScreenPoint(pointF, true);
            PointF pointF2 = new PointF(fArr[i3 + 5], fArr[i3 + 6]);
            renderer.convertAlgo2ScreenPoint(pointF2, true);
            PointF pointF3 = new PointF(fArr[i3 + 7], fArr[i3 + 8]);
            renderer.convertAlgo2ScreenPoint(pointF3, true);
            PointF pointF4 = new PointF(fArr[i3 + 9], fArr[i3 + 10]);
            renderer.convertAlgo2ScreenPoint(pointF4, true);
            PointF pointF5 = new PointF(fArr[i3 + 11], fArr[i3 + 12]);
            renderer.convertAlgo2ScreenPoint(pointF5, true);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("reserved", Float.valueOf(fArr[i3]));
            hashMap2.put("type", Float.valueOf(fArr[i3 + 1]));
            hashMap2.put("score", Float.valueOf(fArr[i3 + 2]));
            hashMap2.put("x1", Float.valueOf(pointF.x));
            hashMap2.put("y1", Float.valueOf(pointF.y));
            hashMap2.put("x2", Float.valueOf(pointF2.x));
            hashMap2.put("y2", Float.valueOf(pointF2.y));
            hashMap2.put("ftx", Float.valueOf(pointF3.x));
            hashMap2.put("fty", Float.valueOf(pointF3.y));
            hashMap2.put("jntx", Float.valueOf(pointF4.x));
            hashMap2.put("jnty", Float.valueOf(pointF4.y));
            hashMap2.put("tjntx", Float.valueOf(pointF5.x));
            hashMap2.put("tjnty", Float.valueOf(pointF5.y));
            hashMap.put(concat, hashMap2);
            i = i2;
        }
        return hashMap;
    }
}
