package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity;

import android.net.Uri;
import android.support.p083v4.provider.DocumentFile;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileModel {
    private String createTime;
    private DocumentFile documentFile;
    private String fileName;
    private String fileSize;
    private boolean isDataFile;
    private boolean isXuanZhong;
    private int nums;
    private String type;
    private Uri uri;
    private String videoTime;
    private String yearAndMonthStr;

    public String getVideoTime() {
        return this.videoTime;
    }

    public void setVideoTime(String str) {
        this.videoTime = str;
    }

    public boolean isXuanZhong() {
        return this.isXuanZhong;
    }

    public void setXuanZhong(boolean z) {
        this.isXuanZhong = z;
    }

    public int getNums() {
        return this.nums;
    }

    public void setNums(int i) {
        this.nums = i;
    }

    public boolean isDataFile() {
        return this.isDataFile;
    }

    public void setDataFile(boolean z) {
        this.isDataFile = z;
    }

    public String getYearAndMonthStr() {
        return this.yearAndMonthStr;
    }

    public void setYearAndMonthStr(String str) {
        this.yearAndMonthStr = str;
    }

    public DocumentFile getDocumentFile() {
        return this.documentFile;
    }

    public void setDocumentFile(DocumentFile documentFile) {
        this.documentFile = documentFile;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(String str) {
        this.fileSize = str;
    }

    public String toString() {
        return "FileModel{documentFile=" + this.documentFile + ", fileName='" + this.fileName + "', type='" + this.type + "', uri=" + this.uri + ", fileSize='" + this.fileSize + "', createTime='" + this.createTime + "', yearAndMonthStr='" + this.yearAndMonthStr + "', isDataFile=" + this.isDataFile + ", nums=" + this.nums + '}';
    }
}
