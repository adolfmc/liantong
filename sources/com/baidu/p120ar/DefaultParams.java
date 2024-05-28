package com.baidu.p120ar;

import android.opengl.EGLContext;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.DefaultParams */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DefaultParams {
    private String m3dShaderDBPath;
    private String mFaceAlgoModelPath;
    private String mFetchUrl;
    private String mMdlAlgoModelPath;
    private boolean mUseInputSizeInEngine = true;
    private boolean mUseBeautyFilter = true;
    private boolean mUseFaceFilter = true;
    private boolean mUseMakeupFilter = true;
    private boolean mUseTextureIO = false;
    private EGLContext mShareContext = null;
    private String mRenderPipeline = null;
    private JSONObject mGradingConfig = null;
    private boolean mLogEnable = true;
    private boolean mUserPlayAudio = false;
    private boolean mRecordAutoCrop = true;

    public String getFaceAlgoModelPath() {
        return this.mFaceAlgoModelPath;
    }

    public void setFaceAlgoModelPath(String str) {
        this.mFaceAlgoModelPath = str;
    }

    public String getFetchUrl() {
        return this.mFetchUrl;
    }

    public void setFetchUrl(String str) {
        this.mFetchUrl = str;
    }

    public String getMdlAlgoModelPath() {
        return this.mMdlAlgoModelPath;
    }

    public void setMdlAlgoModelPath(String str) {
        this.mMdlAlgoModelPath = str;
    }

    public boolean isUseInputSizeInEngine() {
        return this.mUseInputSizeInEngine;
    }

    public void setUseInputSizeInEngine(boolean z) {
        this.mUseInputSizeInEngine = z;
    }

    public boolean isUseBeautyFilter() {
        return this.mUseBeautyFilter;
    }

    public void setUseBeautyFilter(boolean z) {
        this.mUseBeautyFilter = z;
    }

    public boolean isUseFaceFilter() {
        return this.mUseFaceFilter;
    }

    public void setUseFaceFilter(boolean z) {
        this.mUseFaceFilter = z;
    }

    public boolean isUseMakeupFilter() {
        return this.mUseMakeupFilter;
    }

    public void setUseMakeupFilter(boolean z) {
        this.mUseMakeupFilter = z;
    }

    public boolean isUseTextureIO() {
        return this.mUseTextureIO;
    }

    public void setUseTextureIO(boolean z) {
        this.mUseTextureIO = z;
    }

    public EGLContext getShareContext() {
        return this.mShareContext;
    }

    public void setShareContext(EGLContext eGLContext) {
        this.mShareContext = eGLContext;
    }

    public String getRenderPipeline() {
        return this.mRenderPipeline;
    }

    public void setRenderPipeline(String str) {
        this.mRenderPipeline = str;
    }

    public void setGradingConfig(JSONObject jSONObject) {
        this.mGradingConfig = jSONObject;
    }

    public JSONObject getGradingConfig() {
        return this.mGradingConfig;
    }

    public boolean isLogEnable() {
        return this.mLogEnable;
    }

    public void enableLog(boolean z) {
        this.mLogEnable = z;
    }

    public boolean isUserPlayAudio() {
        return this.mUserPlayAudio;
    }

    public void setUserPlayAudio(boolean z) {
        this.mUserPlayAudio = z;
    }

    public boolean isRecordAutoCrop() {
        return this.mRecordAutoCrop;
    }

    public void setRecordAutoCrop(boolean z) {
        this.mRecordAutoCrop = z;
    }

    public void set3dShaderPath(String str) {
        this.m3dShaderDBPath = str;
    }

    public String get3dShaderDBPath() {
        return this.m3dShaderDBPath;
    }
}
