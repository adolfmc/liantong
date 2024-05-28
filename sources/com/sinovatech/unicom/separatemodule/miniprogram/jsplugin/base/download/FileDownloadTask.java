package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileDownloadTask {
    public static final String COMPLETED = "COMPLETED";
    public static final String ERROR = "ERROR";
    public static final String INITED = "INITED";
    public static final String PAUSED = "PAUSED";
    public static final String RUNNING = "RUNNING";
    public static final String WAITING = "WAITING";
    private String errorMsg;
    private String fileContentType;
    private String fileName;
    private String filePath;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18570id;
    private String speed;
    private String taskStatus;
    private String tempFilePath;
    private long totalLength;
    private long totalOffset;
    private String url;

    public FileDownloadTask() {
        this.url = "";
        this.tempFilePath = "";
        this.filePath = "";
        this.totalLength = 0L;
        this.totalOffset = 0L;
        this.speed = "";
        this.taskStatus = "INITED";
        this.errorMsg = "";
        this.fileName = "";
        this.fileContentType = "";
    }

    public FileDownloadTask(String str, String str2, long j, long j2, String str3, String str4) {
        this.url = "";
        this.tempFilePath = "";
        this.filePath = "";
        this.totalLength = 0L;
        this.totalOffset = 0L;
        this.speed = "";
        this.taskStatus = "INITED";
        this.errorMsg = "";
        this.fileName = "";
        this.fileContentType = "";
        this.url = str;
        this.filePath = str2;
        this.totalLength = j;
        this.totalOffset = j2;
        this.speed = str3;
        this.taskStatus = str4;
    }

    public String getTempFilePath() {
        return this.tempFilePath;
    }

    public void setTempFilePath(String str) {
        this.tempFilePath = str;
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

    public long getTotalLength() {
        return this.totalLength;
    }

    public void setTotalLength(long j) {
        this.totalLength = j;
    }

    public long getTotalOffset() {
        return this.totalOffset;
    }

    public void setTotalOffset(long j) {
        this.totalOffset = j;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public long getId() {
        return this.f18570id;
    }

    public void setId(long j) {
        this.f18570id = j;
    }

    public String getFileContentType() {
        return this.fileContentType;
    }

    public void setFileContentType(String str) {
        this.fileContentType = str;
    }
}
