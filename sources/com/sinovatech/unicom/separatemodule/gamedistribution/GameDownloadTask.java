package com.sinovatech.unicom.separatemodule.gamedistribution;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GameDownloadTask {
    public static final String CANCEL = "CANCEL";
    public static final String COMPLETED = "COMPLETED";
    public static final String ERROR = "ERROR";
    public static final String INSTALLED = "INSTALLED";
    public static final String PENDING = "PENDING";
    public static final String RUNNING = "RUNNING";
    public static final String UNKOWN = "UNKOWN";
    private String downloadUrl;
    private String errorMsg = "";
    private String mainTitle;
    private String pkgName;
    private String readableTotalLength;
    private String readableTotalOffset;
    private String speed;
    private String taskStatus;
    private long totalLength;
    private long totalOffset;

    public GameDownloadTask(String str, String str2, String str3, long j, long j2, String str4, String str5, String str6, String str7) {
        this.mainTitle = "";
        this.pkgName = "";
        this.downloadUrl = "";
        this.totalLength = 0L;
        this.totalOffset = 0L;
        this.readableTotalLength = "";
        this.readableTotalOffset = "";
        this.speed = "";
        this.taskStatus = "UNKOWN";
        this.mainTitle = str;
        this.pkgName = str2;
        this.downloadUrl = str3;
        this.totalLength = j;
        this.totalOffset = j2;
        this.readableTotalLength = str4;
        this.readableTotalOffset = str5;
        this.speed = str6;
        this.taskStatus = str7;
    }

    public String getMainTitle() {
        return this.mainTitle;
    }

    public void setMainTitle(String str) {
        this.mainTitle = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getDownloadUrl() {
        return this.downloadUrl;
    }

    public void setDownloadUrl(String str) {
        this.downloadUrl = str;
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

    public String getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(String str) {
        this.taskStatus = str;
    }

    public String getReadableTotalLength() {
        return this.readableTotalLength;
    }

    public void setReadableTotalLength(String str) {
        this.readableTotalLength = str;
    }

    public String getReadableTotalOffset() {
        return this.readableTotalOffset;
    }

    public void setReadableTotalOffset(String str) {
        this.readableTotalOffset = str;
    }

    public String getSpeed() {
        return this.speed;
    }

    public void setSpeed(String str) {
        this.speed = str;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String str) {
        this.errorMsg = str;
    }
}
