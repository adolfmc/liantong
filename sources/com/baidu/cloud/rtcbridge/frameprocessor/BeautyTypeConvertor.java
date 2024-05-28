package com.baidu.cloud.rtcbridge.frameprocessor;

import com.baidu.minivideo.arface.bean.BeautyType;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BeautyTypeConvertor {
    public static RtcBeautyType convert2RtcBeautyType(BeautyType beautyType) {
        switch (beautyType) {
            case lutFile:
                return RtcBeautyType.lutFile;
            case lutIntensity:
                return RtcBeautyType.lutIntensity;
            case whitenFile:
                return RtcBeautyType.whitenFile;
            case laughLine:
                return RtcBeautyType.laughLine;
            case blackEyeCircle:
                return RtcBeautyType.blackEyeCircle;
            case lipsMask:
                return RtcBeautyType.lipsMask;
            case lips:
                return RtcBeautyType.lips;
            case cheeks:
                return RtcBeautyType.cheeks;
            case highlight:
                return RtcBeautyType.highlight;
            case eyeliner:
                return RtcBeautyType.eyeliner;
            case eyeshadow:
                return RtcBeautyType.eyeshadow;
            case eyeball:
                return RtcBeautyType.eyeball;
            case eyelash:
                return RtcBeautyType.eyelash;
            case eyebrow:
                return RtcBeautyType.eyebrow;
            case whiten:
                return RtcBeautyType.whiten;
            case smooth:
                return RtcBeautyType.smooth;
            case eye:
                return RtcBeautyType.eye;
            case thinFace:
                return RtcBeautyType.thinFace;
            case threeCounts:
                return RtcBeautyType.threeCounts;
            case chinHeight:
                return RtcBeautyType.chinHeight;
            case noseWidth:
                return RtcBeautyType.noseWidth;
            case noseLength:
                return RtcBeautyType.noseLength;
            case eyeDistance:
                return RtcBeautyType.eyeDistance;
            case mouthWidth:
                return RtcBeautyType.mouthWidth;
            case eyebrowDistance:
                return RtcBeautyType.eyebrowDistance;
            case upCount:
                return RtcBeautyType.upCount;
            case middleCount:
                return RtcBeautyType.middleCount;
            case downCount:
                return RtcBeautyType.downCount;
            case faceWidth:
                return RtcBeautyType.faceWidth;
            case jawAngleWidth:
                return RtcBeautyType.jawAngleWidth;
            case eyeAngle:
                return RtcBeautyType.eyeAngle;
            case cheekboneWidth:
                return RtcBeautyType.cheekboneWidth;
            case beautyDebugDraw:
                return RtcBeautyType.beautyDebugDraw;
            case beautyJsonPath:
                return RtcBeautyType.beautyJsonPath;
            case globalScaleValue:
                return RtcBeautyType.globalScaleValue;
            default:
                return null;
        }
    }

    public static BeautyType convert2BeautyType(RtcBeautyType rtcBeautyType) {
        switch (rtcBeautyType) {
            case lutFile:
                return BeautyType.lutFile;
            case lutIntensity:
                return BeautyType.lutIntensity;
            case whitenFile:
                return BeautyType.whitenFile;
            case laughLine:
                return BeautyType.laughLine;
            case blackEyeCircle:
                return BeautyType.blackEyeCircle;
            case lipsMask:
                return BeautyType.lipsMask;
            case lips:
                return BeautyType.lips;
            case cheeks:
                return BeautyType.cheeks;
            case highlight:
                return BeautyType.highlight;
            case eyeliner:
                return BeautyType.eyeliner;
            case eyeshadow:
                return BeautyType.eyeshadow;
            case eyeball:
                return BeautyType.eyeball;
            case eyelash:
                return BeautyType.eyelash;
            case eyebrow:
                return BeautyType.eyebrow;
            case whiten:
                return BeautyType.whiten;
            case smooth:
                return BeautyType.smooth;
            case eye:
                return BeautyType.eye;
            case thinFace:
                return BeautyType.thinFace;
            case threeCounts:
                return BeautyType.threeCounts;
            case chinHeight:
                return BeautyType.chinHeight;
            case noseWidth:
                return BeautyType.noseWidth;
            case noseLength:
                return BeautyType.noseLength;
            case eyeDistance:
                return BeautyType.eyeDistance;
            case mouthWidth:
                return BeautyType.mouthWidth;
            case eyebrowDistance:
                return BeautyType.eyebrowDistance;
            case upCount:
                return BeautyType.upCount;
            case middleCount:
                return BeautyType.middleCount;
            case downCount:
                return BeautyType.downCount;
            case faceWidth:
                return BeautyType.faceWidth;
            case jawAngleWidth:
                return BeautyType.jawAngleWidth;
            case eyeAngle:
                return BeautyType.eyeAngle;
            case cheekboneWidth:
                return BeautyType.cheekboneWidth;
            case beautyDebugDraw:
                return BeautyType.beautyDebugDraw;
            case beautyJsonPath:
                return BeautyType.beautyJsonPath;
            case globalScaleValue:
                return BeautyType.globalScaleValue;
            default:
                return null;
        }
    }
}
