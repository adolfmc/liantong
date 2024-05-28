package com.baidu.p120ar.face;

import android.graphics.PointF;
import android.text.TextUtils;
import com.baidu.p120ar.face.algo.FAUFaceBox;
import com.baidu.p120ar.face.algo.FAUPoint2D;
import com.baidu.p120ar.face.algo.FaceAlgoData;
import com.baidu.p120ar.face.algo.FaceFrame;
import com.baidu.p120ar.face.detector.FaceAlgoLoader;
import com.baidu.p120ar.face.detector.FaceResultModel;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.FaceUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceUtil {
    public static final int ALGO_MODEL_LEVEL_HIGH = 2;
    public static final int ALGO_MODEL_LEVEL_LOW = 0;
    public static final int ALGO_MODEL_LEVEL_MEDIUM = 1;
    public static final String LUA_PARAM_DUMOJI = "need_dumoji";
    public static final String LUA_PARAM_EXPRESSION = "need_expression";
    public static final String LUA_PARAM_EYE = "need_refine_eyes";
    public static final String LUA_PARAM_FACE_NUM = "prefer_max_face_count";
    public static final String LUA_PARAM_HEAD_POSE = "need_head_pose";
    public static final String LUA_PARAM_MODEL = "face_model";
    public static final String LUA_PARAM_MODEL_VALUE_MAKEUP = "model_for_makeup";
    public static final String LUA_PARAM_MODEL_VALUE_V3 = "v3";
    public static final String LUA_PARAM_MODEL_VALUE_V6 = "v6";
    public static final String LUA_PARAM_MOUTH = "need_refine_mouth";
    public static final String LUA_PARAM_SKELETON = "need_skeleton";
    public static final String LUA_PARAM_TRIGGER = "need_triggers";
    private static final float V3_TRACK_MOUTH_THRES = 0.1f;
    private static final float V3_TRACK_SMOOTH_ALPHA = 0.01f;
    private static final float V3_TRACK_SMOOTH_THRES = 1.0f;
    private static final float V6_TRACK_MOUTH_THRES = 0.1f;
    private static final float V6_TRACK_SMOOTH_ALPHA = 0.01f;
    private static final float V6_TRACK_SMOOTH_THRES = 1.0f;

    public static int getOrientation(int i) {
        if (i != -90) {
            if (i != 0) {
                if (i != 90) {
                    return i != 180 ? -1 : 2;
                }
                return 1;
            }
            return 0;
        }
        return 3;
    }

    public static boolean getNeedHeadPoseFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            return new JSONObject(string).getBoolean("needHeadPose");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getNeedSkeletonFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            return new JSONObject(string).getBoolean("needSkeleton");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getNeedTriggersFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            return new JSONObject(string).getBoolean("needTriggers");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getNeedExpressionFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return new JSONObject(string).getBoolean("needExpression");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getNeedAnimojiFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return new JSONObject(string).getBoolean("needDumoji");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static boolean getNeedHeadEyeFromJson(String str, boolean z) {
        try {
            String string = new JSONObject(str).getString("assetAttributes");
            if (TextUtils.isEmpty(string)) {
                return true;
            }
            return new JSONObject(string).getBoolean("needRefineEyes");
        } catch (JSONException e) {
            e.printStackTrace();
            return z;
        }
    }

    public static int getMaxFaceSupportNumFromJson(String str, int i) {
        try {
            int i2 = new JSONObject(str).getInt("maxFaceSupport");
            if (i2 <= 1) {
                return 1;
            }
            return i2;
        } catch (JSONException e) {
            e.printStackTrace();
            return i;
        }
    }

    public static int getDeviceModelLevel(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str) || str.equals(str4)) {
            return 0;
        }
        if (str.equals(str3)) {
            return 1;
        }
        return str.equals(str2) ? 2 : 0;
    }

    public static int[] getDiffList(int[] iArr, int[] iArr2) {
        boolean z;
        if (iArr == null || iArr.length == 0) {
            return iArr2;
        }
        if (iArr2 == null || iArr2.length == 0) {
            return iArr;
        }
        if (iArr.length <= iArr2.length) {
            if (iArr.length < iArr2.length) {
                iArr2 = iArr;
                iArr = iArr2;
            } else {
                iArr = null;
                iArr2 = null;
            }
        }
        if (iArr == null || iArr2 == null) {
            return null;
        }
        int[] iArr3 = new int[iArr.length - iArr2.length];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = 0;
            while (true) {
                if (i3 >= iArr2.length) {
                    z = false;
                    break;
                } else if (iArr[i2] == iArr2[i3]) {
                    z = true;
                    break;
                } else {
                    i3++;
                }
            }
            if (!z) {
                iArr3[i] = iArr[i2];
                i++;
            }
        }
        return iArr3;
    }

    public static FaceResultData getFaceResultData(FaceResultModel faceResultModel) {
        FaceResultData faceResultData = new FaceResultData();
        faceResultData.setTimestamp(faceResultModel.getTimestamp());
        faceResultData.setTracked(faceResultModel.isTracked());
        faceResultData.setFrontCamera(faceResultModel.isFrontCamera());
        if (faceResultModel.getFaceAlgoData() != null && faceResultModel.getFaceAlgoData().getFaceFrame() != null) {
            FaceFrame faceFrame = faceResultModel.getFaceAlgoData().getFaceFrame();
            if (faceFrame.getFaceBoxes() != null && faceFrame.getFaceBoxes().size() > 0) {
                List<FAUFaceBox> faceBoxes = faceFrame.getFaceBoxes();
                float[] fArr = new float[faceBoxes.size() * 4];
                for (int i = 0; i < faceBoxes.size(); i++) {
                    FAUFaceBox fAUFaceBox = faceBoxes.get(i);
                    int i2 = i * 4;
                    fArr[i2] = fAUFaceBox.getX();
                    fArr[i2 + 1] = fAUFaceBox.getY();
                    fArr[i2 + 2] = fAUFaceBox.getWidth();
                    fArr[i2 + 3] = fAUFaceBox.getHeight();
                }
                faceResultData.setFaceBoxes(fArr);
            }
            List<FAUPoint2D[]> trackedPointsList = faceFrame.getTrackedPointsList();
            if (trackedPointsList != null && trackedPointsList.size() > 0 && trackedPointsList.get(0) != null) {
                ArrayList arrayList = new ArrayList();
                for (FAUPoint2D[] fAUPoint2DArr : trackedPointsList) {
                    for (FAUPoint2D fAUPoint2D : fAUPoint2DArr) {
                        arrayList.add(new PointF(fAUPoint2D.getX(), fAUPoint2D.getY()));
                    }
                }
                faceResultData.setFacePoints(arrayList);
            }
            faceResultData.setTriggers(faceFrame.getTriggersList());
            if (faceFrame.getHeadPoses() != null) {
                faceResultData.setHeadPoses(new ArrayList(faceFrame.getHeadPoses()));
            }
        }
        return faceResultData;
    }

    public static boolean checkModelPathIllegal(String str, String str2, String str3, String[] strArr, String str4, String str5, String str6, String str7) {
        if (TextUtils.isEmpty(str)) {
            ARLog.m20419e("bdar-face", "error: imbinModelPath is null!");
        } else if (TextUtils.isEmpty(str2)) {
            ARLog.m20419e("bdar-face", "error: detectModelPath is null!");
        } else if (TextUtils.isEmpty(str3)) {
            ARLog.m20419e("bdar-face", "error: anglePath is null!");
        } else if (strArr == null || strArr.length < 3) {
            ARLog.m20419e("bdar-face", "error: trackModelPath is null, or length < 3!");
        } else if (TextUtils.isEmpty(strArr[0])) {
            ARLog.m20419e("bdar-face", "error: lite trackModelPath is null!");
        } else if (TextUtils.isEmpty(strArr[1])) {
            ARLog.m20419e("bdar-face", "error: medium trackModelPath is null!");
        } else if (TextUtils.isEmpty(strArr[2])) {
            ARLog.m20419e("bdar-face", "error: heavy trackModelPath is null!");
        } else if (TextUtils.isEmpty(str4)) {
            ARLog.m20419e("bdar-face", "error: eyesModelPath is null!");
        } else if (TextUtils.isEmpty(str5)) {
            ARLog.m20419e("bdar-face", "error: irisModelPath is null!");
        } else if (TextUtils.isEmpty(str6)) {
            ARLog.m20419e("bdar-face", "error: expressionModelPath is null!");
        } else if (!TextUtils.isEmpty(str7)) {
            return false;
        } else {
            ARLog.m20419e("bdar-face", "error: mouthModelPath is null!");
        }
        return true;
    }

    public static int processLuaMsg(HashMap<String, Object> hashMap, FaceAlgoLoader faceAlgoLoader) {
        String str = (String) hashMap.get("ability_name");
        String str2 = (String) hashMap.get("param_name");
        if ("ability_operation".equals((String) hashMap.get("event_name")) && "ability_face_model".equals(str)) {
            ARLog.m20418i("bdar-face lua message:" + hashMap.toString());
            if ("face_model".equals(str2)) {
                String str3 = (String) hashMap.get("param_value");
                if ("v3".equals(str3) || "model_for_makeup".equals(str3)) {
                    faceAlgoLoader.setTrackMode(2);
                    faceAlgoLoader.setTrackingSmooth(0.01f, 1.0f);
                    faceAlgoLoader.setMouthThreshold(0.1f);
                    return 2;
                } else if ("v6".equals(str3)) {
                    faceAlgoLoader.setTrackMode(0);
                    faceAlgoLoader.setTrackingSmooth(0.01f, 1.0f);
                    faceAlgoLoader.setMouthThreshold(0.1f);
                    return 0;
                } else {
                    return -1;
                }
            } else if ("need_refine_eyes".equals(str2)) {
                faceAlgoLoader.setEyeMode(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_refine_mouth".equals(str2)) {
                faceAlgoLoader.setMouthMode(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_head_pose".equals(str2)) {
                faceAlgoLoader.setNeedHeadPose(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_skeleton".equals(str2)) {
                faceAlgoLoader.setNeedSkeleton(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_triggers".equals(str2)) {
                faceAlgoLoader.setNeedTriggers(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_expression".equals(str2)) {
                faceAlgoLoader.setExpressionMode(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("need_dumoji".equals(str2)) {
                faceAlgoLoader.setAnimojiMode(((Float) hashMap.get("param_value")).floatValue() == 1.0f);
                return -1;
            } else if ("prefer_max_face_count".equals(str2)) {
                faceAlgoLoader.setTargetDetectFaceNum((int) ((Float) hashMap.get("param_value")).floatValue());
                return -1;
            } else {
                return -1;
            }
        }
        return -1;
    }

    public static FaceResultModel formFaceModel(FaceAlgoData faceAlgoData, long j, long j2, long j3, ByteBuffer byteBuffer, long j4, boolean z) {
        FaceResultModel faceResultModel = new FaceResultModel(j4);
        faceResultModel.setFaceAlgoData(faceAlgoData);
        faceResultModel.setFaceHandle(j2);
        faceResultModel.setTimeCost(j);
        faceResultModel.setCameraData(byteBuffer);
        faceResultModel.setDataHandle(j3);
        faceResultModel.setFrontCamera(z);
        if (faceAlgoData != null && faceAlgoData.getFaceFrame() != null) {
            faceResultModel.setResult(true);
            if (faceAlgoData.getFaceFrame().getProcessResult() == 200) {
                faceResultModel.setTracked(true);
            } else {
                faceResultModel.setTracked(false);
            }
        }
        return faceResultModel;
    }

    public static void forceHighLevelMode(FaceAlgoLoader faceAlgoLoader) {
        faceAlgoLoader.setTrackMode(2);
        faceAlgoLoader.setTrackingSmooth(0.01f, 1.0f);
        faceAlgoLoader.setMouthThreshold(0.1f);
    }

    public static void forceLowLevelMode(FaceAlgoLoader faceAlgoLoader) {
        faceAlgoLoader.setTrackMode(0);
        faceAlgoLoader.setTrackingSmooth(0.01f, 1.0f);
        faceAlgoLoader.setMouthThreshold(0.1f);
    }
}
