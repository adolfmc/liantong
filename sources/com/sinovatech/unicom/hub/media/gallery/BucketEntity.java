package com.sinovatech.unicom.hub.media.gallery;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BucketEntity {
    private String bucketId;
    private String bucketName;
    private List<MediaEntity> mediaList;

    public String getBucketId() {
        return this.bucketId;
    }

    public void setBucketId(String str) {
        this.bucketId = str;
    }

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String str) {
        this.bucketName = str;
    }

    public List<MediaEntity> getMediaList() {
        return this.mediaList;
    }

    public void setMediaList(List<MediaEntity> list) {
        this.mediaList = list;
    }
}
