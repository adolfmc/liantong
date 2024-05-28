package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity;

import android.net.Uri;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaExtraBean {
    public int duration;
    public String imagePath;
    public long size;
    public Uri thumbPath;
    public String localPath = "";
    public String title = "";

    public Uri getThumbPath() {
        return this.thumbPath;
    }

    public void setThumbPath(Uri uri) {
        this.thumbPath = uri;
    }

    public String getLocalPath() {
        return this.localPath;
    }

    public void setLocalPath(String str) {
        this.localPath = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public String toString() {
        return "MediaExtraBean{thumbPath=" + this.thumbPath + ", localPath='" + this.localPath + "', title='" + this.title + "', duration=" + this.duration + ", size=" + this.size + ", imagePath='" + this.imagePath + "'}";
    }
}
