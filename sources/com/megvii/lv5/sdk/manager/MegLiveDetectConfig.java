package com.megvii.lv5.sdk.manager;

import android.media.projection.MediaProjection;
import java.io.Serializable;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MegLiveDetectConfig implements Serializable {
    private String bizToken;
    private String host;
    private boolean isShowLogo;
    private String language;
    private MediaProjection mediaProjection;
    private String modelPath;
    private String path1;
    private String path2;
    private boolean autoAdjustVolume = false;
    private int suggestVolume = 50;
    private int verticalDetection = 3;
    private int config_re_request = 2;
    private int config_max_time = 2;

    public String getBizToken() {
        return this.bizToken;
    }

    public int getConfig_max_time() {
        if (this.config_max_time <= 0) {
            this.config_max_time = 2;
        }
        return this.config_max_time;
    }

    public int getConfig_re_request() {
        if (this.config_re_request <= 0) {
            this.config_re_request = 2;
        }
        return this.config_re_request;
    }

    public String getHost() {
        return this.host;
    }

    public String getLanguage() {
        return this.language;
    }

    public MediaProjection getMediaProjection() {
        return this.mediaProjection;
    }

    public String getModelPath() {
        return this.modelPath;
    }

    public String getPath1() {
        return this.path1;
    }

    public String getPath2() {
        return this.path2;
    }

    public int getSuggestVolume() {
        return this.suggestVolume;
    }

    public int getVerticalDetection() {
        return this.verticalDetection;
    }

    public boolean isAutoAdjustVolume() {
        return this.autoAdjustVolume;
    }

    public boolean isShowLogo() {
        return this.isShowLogo;
    }

    public void setAutoAdjustVolume(boolean z) {
        this.autoAdjustVolume = z;
    }

    public void setBizToken(String str) {
        this.bizToken = str;
    }

    public void setConfig_max_time(int i) {
        this.config_max_time = i;
    }

    public void setConfig_re_request(int i) {
        this.config_re_request = i;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setMediaProjection(MediaProjection mediaProjection) {
        this.mediaProjection = mediaProjection;
    }

    public void setModelPath(String str) {
        this.modelPath = str;
    }

    public void setPath1(String str) {
        this.path1 = str;
    }

    public void setPath2(String str) {
        this.path2 = str;
    }

    public void setShowLogo(boolean z) {
        this.isShowLogo = z;
    }

    public void setSuggestVolume(int i) {
        this.suggestVolume = i;
    }

    public void setVerticalDetection(int i) {
        this.verticalDetection = i;
    }
}
