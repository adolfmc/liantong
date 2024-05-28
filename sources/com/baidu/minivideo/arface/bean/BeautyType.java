package com.baidu.minivideo.arface.bean;

import com.baidu.p120ar.filter.FilterParam;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum BeautyType {
    lutFile(FilterParam.LutFilter.lutFile),
    lutIntensity(FilterParam.LutFilter.lutIntensity),
    whitenFile(FilterParam.SkinFilter.whitenFile),
    blackEyeCircle(FilterParam.SkinFilter.blackEyeCircle),
    laughLine(FilterParam.SkinFilter.laughLine),
    lipsMask(FilterParam.MakeupFilter.lipsMask),
    lips(FilterParam.MakeupFilter.lips),
    cheeks(FilterParam.MakeupFilter.cheeks),
    highlight(FilterParam.MakeupFilter.highlight),
    eyeliner(FilterParam.MakeupFilter.eyeliner),
    eyeshadow(FilterParam.MakeupFilter.eyeshadow),
    eyeball(FilterParam.MakeupFilter.eyeball),
    eyelash(FilterParam.MakeupFilter.eyelash),
    eyebrow(FilterParam.MakeupFilter.eyebrow),
    whiten(0.0f, FilterParam.SkinFilter.whiten),
    smooth(0.0f, FilterParam.SkinFilter.smooth),
    eye(0.0f, FilterParam.FaceFilter.eye),
    thinFace(0.0f, FilterParam.FaceFilter.thinFace),
    threeCounts(0.5f, FilterParam.FaceFilter.threeCounts),
    chinHeight(0.5f, FilterParam.FaceFilter.chinHeight),
    noseWidth(0.0f, FilterParam.FaceFilter.noseWidth),
    noseLength(0.5f, FilterParam.FaceFilter.noseLength),
    eyeDistance(0.5f, FilterParam.FaceFilter.eyeDistance),
    mouthWidth(0.5f, FilterParam.FaceFilter.mouthWidth),
    eyebrowDistance(0.5f, FilterParam.FaceFilter.eyebrowDistance),
    upCount(0.5f, FilterParam.FaceFilter.upCount),
    middleCount(0.5f, FilterParam.FaceFilter.middleCount),
    downCount(0.5f, FilterParam.FaceFilter.downCount),
    faceWidth(0.0f, FilterParam.FaceFilter.faceWidth),
    jawAngleWidth(0.0f, FilterParam.FaceFilter.jawAngleWidth),
    eyeAngle(0.5f, FilterParam.FaceFilter.eyeAngle),
    cheekboneWidth(0.0f, FilterParam.FaceFilter.cheekboneWidth),
    beautyDebugDraw(-1.0f, FilterParam.FaceFilter.beautyDebugDrawMode),
    beautyJsonPath("", FilterParam.FaceFilter.beautyJsonPath),
    globalScaleValue(FilterParam.FaceFilter.globalScaleValue);
    
    public String icon;
    public String path;
    public FilterParam type;
    public float value;

    BeautyType(float f, FilterParam filterParam) {
        this.value = f;
        this.type = filterParam;
    }

    BeautyType(String str, FilterParam filterParam) {
        this.path = str;
        this.type = filterParam;
    }

    BeautyType(FilterParam filterParam) {
        this.type = filterParam;
    }

    public String getTypeValue() {
        return this.type.getParamName();
    }
}
