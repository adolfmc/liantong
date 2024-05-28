package com.sinovatech.unicom.hub.media.gallery;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MediaEntity implements Comparable<MediaEntity> {
    private String bucketId;
    private String bucketName;
    private long createDate;
    private int height;
    private String imagePath;
    private double latitude;
    private long length;
    private double longitude;
    private long mediaId;
    private String mediaType;
    private String mimeType;
    private long modifiedDate;
    private int orientation;
    private String originalPath;
    private String title;
    private long videoDuration;
    private String videoThumbnail;
    private int width;

    public long getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(long j) {
        this.mediaId = j;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getOriginalPath() {
        return this.originalPath;
    }

    public void setOriginalPath(String str) {
        this.originalPath = str;
    }

    public long getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(long j) {
        this.createDate = j;
    }

    public long getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(long j) {
        this.modifiedDate = j;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public void setMimeType(String str) {
        this.mimeType = str;
    }

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

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public void setOrientation(int i) {
        this.orientation = i;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long j) {
        this.length = j;
    }

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

    public String getVideoThumbnail() {
        return this.videoThumbnail;
    }

    public void setVideoThumbnail(String str) {
        this.videoThumbnail = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String str) {
        this.imagePath = str;
    }

    public long getVideoDuration() {
        return this.videoDuration;
    }

    public void setVideoDuration(long j) {
        this.videoDuration = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(MediaEntity mediaEntity) {
        return getModifiedDate() >= mediaEntity.getModifiedDate() ? 1 : -1;
    }
}
