package com.baidu.p120ar.face;

import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.face.models.FaceModelConfig;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.FaceDefaultConfig */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceDefaultConfig {
    public static final int ALGO_IMAGE_HEIGHT = 320;
    public static final int ALGO_IMAGE_WIDTH = 180;
    public static final boolean DEFAULT_FACE_ANIMOJI = false;
    public static final boolean DEFAULT_FACE_EXPRESSION = false;
    public static final boolean DEFAULT_FACE_EYE = true;
    public static final boolean DEFAULT_FACE_EYE_LOW = false;
    public static final boolean DEFAULT_FACE_HEAD_POSE = false;
    public static final boolean DEFAULT_FACE_MOUTH = true;
    public static final boolean DEFAULT_FACE_SKELETON = false;
    public static final boolean DEFAULT_FACE_TRIGGER = false;
    public static final int HIGH_DEVICE_MAX_FACE_NUM = 4;
    public static final int LOW_DEVICE_MAX_FACE_NUM = 1;
    public static final int MEDIUM_DEVICE_MAX_FACE_NUM = 1;
    public static final int STICKER_NONE_CONFIG_FACE_NUM = 1;
    private static final String TAG = "FaceDefaultConfig";
    private boolean needHeadPose = false;
    private boolean needSkeleton = false;
    private boolean needTriggers = false;
    private boolean needExpression = false;
    private boolean needAnimoji = false;
    private boolean needEye = true;
    private boolean needMouth = true;
    private int algoImageWidth = 180;
    private int algoImageHeight = 320;
    private boolean needCheckMakeup = true;
    private boolean enableSyncRender = false;
    private int maxFaceNumNoSticker = 4;
    private int maxFaceSupportNum = this.maxFaceNumNoSticker;

    public void updateNeedEyeAdaptToDevice(int i) {
        this.needEye = i != 0;
    }

    public void resetSize() {
        this.algoImageWidth = 180;
        this.algoImageHeight = 320;
    }

    public void setDefaultFaceConfig(int i) {
        this.needHeadPose = false;
        this.needSkeleton = false;
        this.needTriggers = false;
        this.needExpression = false;
        this.needAnimoji = false;
        this.maxFaceSupportNum = this.maxFaceNumNoSticker;
        this.needEye = i != 0;
        this.needMouth = true;
    }

    public void setOrFaceConfig(int i) {
        boolean z = false;
        this.needHeadPose = this.needHeadPose;
        this.needSkeleton = this.needSkeleton;
        this.needTriggers = this.needTriggers;
        this.needExpression = this.needExpression;
        this.needAnimoji = this.needAnimoji;
        if (this.needEye || i != 0) {
            z = true;
        }
        this.needEye = z;
        boolean z2 = this.needMouth;
        this.needMouth = true;
    }

    public void setSingleFace() {
        this.maxFaceSupportNum = 1;
    }

    public FaceModelConfig.DeviceModel configLevel(FaceModelConfig.ModelConfig modelConfig) {
        FaceModelConfig.DeviceModel deviceModel;
        switch (modelConfig.mCurrentDeviceLevel) {
            case 0:
                this.maxFaceNumNoSticker = 1;
                deviceModel = modelConfig.mLowDeviceModel;
                break;
            case 1:
                this.maxFaceNumNoSticker = 1;
                deviceModel = modelConfig.mMediumDeviceModel;
                break;
            case 2:
                this.maxFaceNumNoSticker = 4;
                deviceModel = modelConfig.mHighDeviceModel;
                break;
            default:
                ARLog.m20419e(TAG, "createFaceParams() device not support!!!");
                deviceModel = null;
                break;
        }
        this.maxFaceSupportNum = this.maxFaceNumNoSticker;
        return deviceModel;
    }

    public void parseFaceParamsFromJson(String str, int i) {
        this.needHeadPose = FaceUtil.getNeedHeadPoseFromJson(str, false);
        this.needSkeleton = FaceUtil.getNeedSkeletonFromJson(str, false);
        this.needTriggers = FaceUtil.getNeedTriggersFromJson(str, false);
        this.needExpression = FaceUtil.getNeedExpressionFromJson(str, false);
        this.needAnimoji = FaceUtil.getNeedAnimojiFromJson(str, false);
        this.needEye = FaceUtil.getNeedHeadEyeFromJson(str, i != 0);
        this.maxFaceSupportNum = FaceUtil.getMaxFaceSupportNumFromJson(str, 1);
        if (this.maxFaceSupportNum <= 1 || ARAuth.checkFeatureAuth(1210)) {
            return;
        }
        this.maxFaceSupportNum = 1;
    }

    public boolean isNeedHeadPose() {
        return this.needHeadPose;
    }

    public void setNeedHeadPose(boolean z) {
        this.needHeadPose = z;
    }

    public boolean isNeedSkeleton() {
        return this.needSkeleton;
    }

    public void setNeedSkeleton(boolean z) {
        this.needSkeleton = z;
    }

    public boolean isNeedTriggers() {
        return this.needTriggers;
    }

    public void setNeedTriggers(boolean z) {
        this.needTriggers = z;
    }

    public boolean isNeedExpression() {
        return this.needExpression;
    }

    public void setNeedExpression(boolean z) {
        this.needExpression = z;
    }

    public boolean isNeedAnimoji() {
        return this.needAnimoji;
    }

    public void setNeedAnimoji(boolean z) {
        this.needAnimoji = z;
    }

    public boolean isNeedEye() {
        return this.needEye;
    }

    public void setNeedEye(boolean z) {
        this.needEye = z;
    }

    public boolean isNeedMouth() {
        return this.needMouth;
    }

    public void setNeedMouth(boolean z) {
        this.needMouth = z;
    }

    public int getAlgoImageWidth() {
        return this.algoImageWidth;
    }

    public void setAlgoImageWidth(int i) {
        this.algoImageWidth = i;
    }

    public int getAlgoImageHeight() {
        return this.algoImageHeight;
    }

    public void setAlgoImageHeight(int i) {
        this.algoImageHeight = i;
    }

    public boolean isNeedCheckMakeup() {
        return this.needCheckMakeup;
    }

    public void setNeedCheckMakeup(boolean z) {
        this.needCheckMakeup = z;
    }

    public boolean isEnableSyncRender() {
        return this.enableSyncRender;
    }

    public void setEnableSyncRender(boolean z) {
        this.enableSyncRender = z;
    }

    public int getMaxFaceNumNoSticker() {
        return this.maxFaceNumNoSticker;
    }

    public void setMaxFaceNumNoSticker(int i) {
        this.maxFaceNumNoSticker = i;
    }

    public int getMaxFaceSupportNum() {
        return this.maxFaceSupportNum;
    }

    public void setMaxFaceSupportNum(int i) {
        this.maxFaceSupportNum = i;
    }
}
