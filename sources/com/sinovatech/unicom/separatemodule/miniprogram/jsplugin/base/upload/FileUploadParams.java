package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileUploadParams {
    private int timeOut;
    private String uploadId = "";
    private String url = "";
    private String filePath = "";
    private String fileKeyName = "";
    private long uploadStartPosition = -1;
    private long uploadLength = -1;
    private Map<String, String> formData = new HashMap();
    private Map<String, String> header = new HashMap();

    public String getUploadId() {
        return this.uploadId;
    }

    public void setUploadId(String str) {
        this.uploadId = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getFileKeyName() {
        return this.fileKeyName;
    }

    public void setFileKeyName(String str) {
        this.fileKeyName = str;
    }

    public long getUploadStartPosition() {
        return this.uploadStartPosition;
    }

    public void setUploadStartPosition(long j) {
        this.uploadStartPosition = j;
    }

    public long getUploadLength() {
        return this.uploadLength;
    }

    public void setUploadLength(long j) {
        this.uploadLength = j;
    }

    public Map<String, String> getFormData() {
        return this.formData;
    }

    public void setFormData(Map<String, String> map) {
        this.formData = map;
    }

    public Map<String, String> getHeader() {
        return this.header;
    }

    public void setHeader(Map<String, String> map) {
        this.header = map;
    }

    public int getTimeOut() {
        return this.timeOut;
    }

    public void setTimeOut(int i) {
        this.timeOut = i;
    }

    public String toString() {
        return "FileUploadParams{uploadId='" + this.uploadId + "', url='" + this.url + "', filePath='" + this.filePath + "', fileKeyName='" + this.fileKeyName + "', uploadStartPosition=" + this.uploadStartPosition + ", uploadLength=" + this.uploadLength + ", timeOut=" + this.timeOut + ", formData=" + this.formData + ", header=" + this.header + '}';
    }
}
