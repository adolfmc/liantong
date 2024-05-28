package cn.finalteam.galleryfinal.model;

import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PhotoFolderInfo implements Serializable {
    private PhotoInfo coverPhoto;
    private int folderId;
    private String folderName;
    private List<PhotoInfo> photoList;

    public int getFolderId() {
        return this.folderId;
    }

    public void setFolderId(int i) {
        this.folderId = i;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public PhotoInfo getCoverPhoto() {
        return this.coverPhoto;
    }

    public void setCoverPhoto(PhotoInfo photoInfo) {
        this.coverPhoto = photoInfo;
    }

    public List<PhotoInfo> getPhotoList() {
        return this.photoList;
    }

    public void setPhotoList(List<PhotoInfo> list) {
        this.photoList = list;
    }
}
