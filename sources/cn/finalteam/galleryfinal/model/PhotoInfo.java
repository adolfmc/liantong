package cn.finalteam.galleryfinal.model;

import android.text.TextUtils;
import java.io.Serializable;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoInfo implements Serializable {
    private int height;
    private int photoId;
    private String photoPath;
    private int width;

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String getPhotoPath() {
        return this.photoPath;
    }

    public void setPhotoPath(String str) {
        this.photoPath = str;
    }

    public int getPhotoId() {
        return this.photoId;
    }

    public void setPhotoId(int i) {
        this.photoId = i;
    }

    public boolean equals(Object obj) {
        PhotoInfo photoInfo;
        if (obj == null || !(obj instanceof PhotoInfo) || (photoInfo = (PhotoInfo) obj) == null) {
            return false;
        }
        return TextUtils.equals(photoInfo.getPhotoPath(), getPhotoPath());
    }
}
