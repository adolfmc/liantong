package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.mediaplayer;

import android.webkit.WebView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MediaPlayerEntity {
    private String audioIconUrl;
    private String closeDuration;
    private String icon;
    private String iconTargetUrl;
    private boolean isFinish = true;
    private boolean isMediaExist;
    private boolean isStart;
    private String playIconUrl;
    private String playSong;
    private String playStatus;

    /* renamed from: wv */
    private WebView f18577wv;

    public String getPlayStatus() {
        return this.playStatus;
    }

    public void setPlayStatus(String str) {
        this.playStatus = str;
    }

    public String getPlaySong() {
        return this.playSong;
    }

    public void setPlaySong(String str) {
        this.playSong = str;
    }

    public WebView getWv() {
        return this.f18577wv;
    }

    public void setWv(WebView webView) {
        this.f18577wv = webView;
    }

    public boolean isStart() {
        return this.isStart;
    }

    public void setStart(boolean z) {
        this.isStart = z;
    }

    public boolean isMediaExist() {
        return this.isMediaExist;
    }

    public void setMediaExist(boolean z) {
        this.isMediaExist = z;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public String getIconTargetUrl() {
        return this.iconTargetUrl;
    }

    public void setIconTargetUrl(String str) {
        this.iconTargetUrl = str;
    }

    public String getPlayIconUrl() {
        return this.playIconUrl;
    }

    public void setPlayIconUrl(String str) {
        this.playIconUrl = str;
    }

    public String getCloseDuration() {
        return this.closeDuration;
    }

    public void setCloseDuration(String str) {
        this.closeDuration = str;
    }

    public String getAudioIconUrl() {
        return this.audioIconUrl;
    }

    public void setAudioIconUrl(String str) {
        this.audioIconUrl = str;
    }

    public boolean isFinish() {
        return this.isFinish;
    }

    public void setFinish(boolean z) {
        this.isFinish = z;
    }
}
