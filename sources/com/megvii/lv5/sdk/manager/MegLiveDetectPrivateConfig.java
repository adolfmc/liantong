package com.megvii.lv5.sdk.manager;

import java.io.Serializable;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MegLiveDetectPrivateConfig implements Serializable {
    private String language;
    private int[] livenessMegliveTypes;
    private String mediaResourcePath;
    private String modelPath;
    private String url;
    private String biztoken = "";
    private int mode = 1;
    private LivenessTypeE livenessType = LivenessTypeE.Meglive;
    private int livenessTimeout = 10;
    private int livenessActionCount = 3;
    private int flashLivenessTimeout = 120;
    private int flashColorCount = 4;
    private boolean isFlashLight = true;
    private int livenessMegliveType = 4;
    private Integer initiativeLivenessActionCount = null;
    private int initiativeLivenessTimeout = 10;
    private int initiativeLivenessFlashTimeout = 120;
    private int initiativeFlashColorCount = 4;
    private int distanceStepTimeout = 60;
    private int distanceFlashColorCount = 4;
    private int distanceFlashStepTimeout = 120;

    public String getBiztoken() {
        return this.biztoken;
    }

    public int getDistanceFlashColorCount() {
        return this.distanceFlashColorCount;
    }

    public int getDistanceFlashStepTimeout() {
        return this.distanceFlashStepTimeout;
    }

    public int getDistanceStepTimeout() {
        return this.distanceStepTimeout;
    }

    public int getFlashColorCount() {
        return this.flashColorCount;
    }

    public int getFlashLivenessTimeout() {
        return this.flashLivenessTimeout;
    }

    public int getInitiativeFlashColorCount() {
        return this.initiativeFlashColorCount;
    }

    public Integer getInitiativeLivenessActionCount() {
        return this.initiativeLivenessActionCount;
    }

    public int getInitiativeLivenessFlashTimeout() {
        return this.initiativeLivenessFlashTimeout;
    }

    public int getInitiativeLivenessTimeout() {
        return this.initiativeLivenessTimeout;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getLivenessActionCount() {
        return this.livenessActionCount;
    }

    public int getLivenessMegliveType() {
        return this.livenessMegliveType;
    }

    public int[] getLivenessMegliveTypes() {
        return this.livenessMegliveTypes;
    }

    public int getLivenessTimeout() {
        return this.livenessTimeout;
    }

    public LivenessTypeE getLivenessType() {
        return this.livenessType;
    }

    public String getMediaResourcePath() {
        return this.mediaResourcePath;
    }

    public int getMode() {
        return this.mode;
    }

    public String getModelPath() {
        return this.modelPath;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isFlashLight() {
        return this.isFlashLight;
    }

    public void setBiztoken(String str) {
        this.biztoken = str;
    }

    public void setDistanceFlashColorCount(int i) {
        if (i < 4 || i > 8) {
            return;
        }
        this.distanceFlashColorCount = i;
    }

    public void setDistanceFlashStepTimeout(int i) {
        if (i <= 0 || i > 180) {
            return;
        }
        this.distanceFlashStepTimeout = i;
    }

    public void setDistanceStepTimeout(int i) {
        if (i <= 0 || i > 180) {
            return;
        }
        this.distanceStepTimeout = i;
    }

    public void setFlashColorCount(int i) {
        if (i < 4 || i > 8) {
            return;
        }
        this.flashColorCount = i;
    }

    public void setFlashLight(boolean z) {
        this.isFlashLight = z;
    }

    public void setFlashLivenessTimeout(int i) {
        if (i <= 0 || i > 180) {
            return;
        }
        this.flashLivenessTimeout = i;
    }

    public void setInitiativeFlashColorCount(int i) {
        if (i < 4 || i > 8) {
            return;
        }
        this.initiativeFlashColorCount = i;
    }

    public void setInitiativeLivenessActionCount(Integer num) {
        if (num.intValue() <= 0 || num.intValue() > 4) {
            return;
        }
        this.initiativeLivenessActionCount = num;
    }

    public void setInitiativeLivenessFlashTimeout(int i) {
        if (i <= 0 || i > 180) {
            return;
        }
        this.initiativeLivenessFlashTimeout = i;
    }

    public void setInitiativeLivenessTimeout(int i) {
        if (i <= 0 || i > 60) {
            return;
        }
        this.initiativeLivenessTimeout = i;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setLivenessActionCount(int i) {
        if (i <= 0 || i > 3) {
            return;
        }
        this.livenessActionCount = i;
    }

    public void setLivenessMegliveType(int i) {
        this.livenessMegliveType = i;
    }

    public void setLivenessMegliveTypes(int[] iArr) {
        this.livenessMegliveTypes = iArr;
    }

    public void setLivenessTimeout(int i) {
        if (i <= 0 || i > 60) {
            return;
        }
        this.livenessTimeout = i;
    }

    public void setLivenessType(LivenessTypeE livenessTypeE) {
        this.livenessType = livenessTypeE;
    }

    public void setMediaResourcePath(String str) {
        this.mediaResourcePath = str;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setModelPath(String str) {
        this.modelPath = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
