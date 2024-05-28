package com.baidu.p120ar.face.models;

import android.text.TextUtils;
import com.baidu.p120ar.abilityscheme.AbilitySchemeDefaultConfig;
import com.baidu.p120ar.utils.ARLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.models.FaceModelConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceModelConfig {
    private static final int[] FACE_DETECT_RATE_DEFAULT = {3, 10, 10, 10, 10};
    private static final String JSON_KEY_SERVER_ALGO = "algo";
    private static final String JSON_KEY_SERVER_ALGO_FACE = "algo_face";
    private static final String JSON_KEY_SERVER_ALGO_FACE_DETECT_RATE = "detect_rate";
    private static final String JSON_KEY_SERVER_ALGO_FACE_LEVEL = "level";
    private static final String JSON_KEY_SERVER_CUSTOM = "customize";
    private static final String JSON_VALUE_SERVER_HIGH_DEVICE = "high";
    private static final String JSON_VALUE_SERVER_LOW_DEVICE = "low";
    private static final String JSON_VALUE_SERVER_MEDIUM_DEVICE = "medium";
    private static final String JSON_VALUE_SERVER_MEDIUM_LOW_DEVICE = "mediumlow";
    private static final String MODEL_FILE_CONFIG = "{\n  \"detect_model\": \"detect/0966_0843_int8_5x5_120x120_afteryy_model.nb\",\n  \"animate\": \"animate/skeletonModel_v3.1.11.bin\",\n  \"expression\": \"animate/enc_exp_v2.3.0.lite.bin\",\n  \"track1_heavy\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n  \"track1_medium\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n  \"track1_lite\": \"track/enc_v45ps_hd_v10_e350_int16_lite\",\n  \"mouth\": \"track/1021enc_mouth_4_10_int16_lite\",\n  \"gender\": \"attributes/gender_v1_opt_20201204.nb\",\n  \"high_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  },\n  \"medium_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  },\n  \"low_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v45ps_hd_v10_e350_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  }\n}";
    private static final String TAG = "FaceModelConfig";
    private String mFaceModelDir;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.face.models.FaceModelConfig$ModelConfig */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class ModelConfig {
        public int mCurrentDeviceLevel;
        public String mDetectModel;
        public int[] mDetectRate;
        public String mExpression;
        public String mGender;
        public DeviceModel mHighDeviceModel;
        public String mImbin;
        public DeviceModel mLowDeviceModel;
        public DeviceModel mMediumDeviceModel;
        public String mMouth;
        public String mTrack1Heavy;
        public String mTrack1Lite;
        public String mTrack1Medium;

        public ModelConfig() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.face.models.FaceModelConfig$DeviceModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class DeviceModel {
        public String mTrackParam0;
        public String mTrackParam1;
        public String mTrackParam2;
        public String mTrackParam3;
        public String mTrackingMouthThreshold;
        public String mTrackingSmoothAlpha;
        public String mTrackingSmoothThreshold;

        public DeviceModel() {
        }
    }

    public ModelConfig getFaceModelConfig(String str, JSONObject jSONObject) {
        this.mFaceModelDir = str;
        return parseConfig("{\n  \"detect_model\": \"detect/0966_0843_int8_5x5_120x120_afteryy_model.nb\",\n  \"animate\": \"animate/skeletonModel_v3.1.11.bin\",\n  \"expression\": \"animate/enc_exp_v2.3.0.lite.bin\",\n  \"track1_heavy\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n  \"track1_medium\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n  \"track1_lite\": \"track/enc_v45ps_hd_v10_e350_int16_lite\",\n  \"mouth\": \"track/1021enc_mouth_4_10_int16_lite\",\n  \"gender\": \"attributes/gender_v1_opt_20201204.nb\",\n  \"high_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  },\n  \"medium_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v3ps_v2_hd_e352_20210615_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  },\n  \"low_device_model\": {\n    \"track_param_0\": \"track/1021enc_mv8_point3_int16_lite\",\n    \"track_param_1\": \"track/enc_v45ps_hd_v10_e350_int16_lite\",\n    \"track_param_2\": \"track/1021enc_eye_4_10_int16_lite\",\n    \"track_param_3\": \"track/1021enc_iris_4_10_int16_lite\",\n    \"trackingSmoothAlpha\": \"0.01\",\n    \"trackingMouthThreshold\": \"-1.0\",\n    \"trackingSmoothThreshold\": \"1\"\n  }\n}", jSONObject);
    }

    private ModelConfig parseConfig(String str, JSONObject jSONObject) {
        ModelConfig modelConfig = new ModelConfig();
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            modelConfig.mDetectModel = this.mFaceModelDir + jSONObject2.getString("detect_model");
            modelConfig.mImbin = this.mFaceModelDir + jSONObject2.getString("animate");
            modelConfig.mExpression = this.mFaceModelDir + jSONObject2.getString("expression");
            modelConfig.mTrack1Heavy = this.mFaceModelDir + jSONObject2.getString("track1_heavy");
            modelConfig.mTrack1Medium = this.mFaceModelDir + jSONObject2.getString("track1_medium");
            modelConfig.mTrack1Lite = this.mFaceModelDir + jSONObject2.getString("track1_lite");
            modelConfig.mMouth = this.mFaceModelDir + jSONObject2.getString("mouth");
            modelConfig.mGender = this.mFaceModelDir + jSONObject2.getString("gender");
            modelConfig.mHighDeviceModel = parseDeviceModel(jSONObject2.getJSONObject("high_device_model"));
            modelConfig.mMediumDeviceModel = parseDeviceModel(jSONObject2.getJSONObject("medium_device_model"));
            modelConfig.mLowDeviceModel = parseDeviceModel(jSONObject2.getJSONObject("low_device_model"));
            ARLog.m20422d("low_device_model = " + modelConfig.mLowDeviceModel);
            if (jSONObject == null) {
                modelConfig.mCurrentDeviceLevel = AbilitySchemeDefaultConfig.getDeviceLevelByDefaultCpuList();
                modelConfig.mDetectRate = FACE_DETECT_RATE_DEFAULT;
            } else {
                applyAbilityByServerInfo(modelConfig, jSONObject);
            }
            return modelConfig;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DeviceModel parseDeviceModel(JSONObject jSONObject) {
        DeviceModel deviceModel = new DeviceModel();
        try {
            deviceModel.mTrackParam0 = jSONObject.getString("track_param_0");
            if (!TextUtils.isEmpty(deviceModel.mTrackParam0)) {
                deviceModel.mTrackParam0 = this.mFaceModelDir + deviceModel.mTrackParam0;
            } else {
                deviceModel.mTrackParam0 = "";
            }
            deviceModel.mTrackParam1 = jSONObject.getString("track_param_1");
            if (!TextUtils.isEmpty(deviceModel.mTrackParam1)) {
                deviceModel.mTrackParam1 = this.mFaceModelDir + deviceModel.mTrackParam1;
            } else {
                deviceModel.mTrackParam1 = "";
            }
            deviceModel.mTrackParam2 = jSONObject.getString("track_param_2");
            if (!TextUtils.isEmpty(deviceModel.mTrackParam2)) {
                deviceModel.mTrackParam2 = this.mFaceModelDir + deviceModel.mTrackParam2;
            } else {
                deviceModel.mTrackParam2 = "";
            }
            deviceModel.mTrackParam3 = jSONObject.getString("track_param_3");
            if (!TextUtils.isEmpty(deviceModel.mTrackParam3)) {
                deviceModel.mTrackParam3 = this.mFaceModelDir + deviceModel.mTrackParam3;
            } else {
                deviceModel.mTrackParam3 = "";
            }
            deviceModel.mTrackingSmoothAlpha = jSONObject.getString("trackingSmoothAlpha");
            deviceModel.mTrackingSmoothThreshold = jSONObject.getString("trackingSmoothThreshold");
            deviceModel.mTrackingMouthThreshold = jSONObject.getString("trackingMouthThreshold");
            return deviceModel;
        } catch (JSONException e) {
            ARLog.m20419e("FaceModelConfig", "parse DeviceModel error");
            e.printStackTrace();
            return null;
        }
    }

    private void applyAbilityByServerInfo(ModelConfig modelConfig, JSONObject jSONObject) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        JSONObject optJSONObject3 = jSONObject.optJSONObject("customize");
        modelConfig.mCurrentDeviceLevel = 0;
        modelConfig.mDetectRate = FACE_DETECT_RATE_DEFAULT;
        if (optJSONObject3 == null || (optJSONObject = optJSONObject3.optJSONObject("algo")) == null || (optJSONObject2 = optJSONObject.optJSONObject("algo_face")) == null) {
            return;
        }
        String optString = optJSONObject2.optString("level");
        if (!TextUtils.isEmpty(optString)) {
            if (optString.equals("high")) {
                modelConfig.mCurrentDeviceLevel = 2;
            } else if (optString.equals("medium")) {
                modelConfig.mCurrentDeviceLevel = 1;
            } else if (optString.equals("mediumlow")) {
                modelConfig.mCurrentDeviceLevel = 0;
            } else if (optString.equals("low")) {
                modelConfig.mCurrentDeviceLevel = 0;
            }
        }
        JSONArray optJSONArray = optJSONObject2.optJSONArray("detect_rate");
        if (optJSONArray == null || optJSONArray.length() < 5) {
            return;
        }
        try {
            int[] iArr = new int[5];
            for (int i = 0; i < 5; i++) {
                iArr[i] = optJSONArray.getInt(i) < FACE_DETECT_RATE_DEFAULT[i] ? FACE_DETECT_RATE_DEFAULT[i] : optJSONArray.getInt(i);
            }
            modelConfig.mDetectRate = iArr;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
