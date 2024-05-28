package com.chinaunicon.jtwifilib.jtcommon.model;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtNetConfig {
    private List<JtNetPremission> cmd;
    private String download;
    private String isLatest;
    private String isSuceess;
    private String version;

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getDownload() {
        return this.download;
    }

    public void setDownload(String str) {
        this.download = str;
    }

    public String getIsSuceess() {
        return this.isSuceess;
    }

    public void setIsSuceess(String str) {
        this.isSuceess = str;
    }

    public List<JtNetPremission> getCmd() {
        return this.cmd;
    }

    public void setCmd(List<JtNetPremission> list) {
        this.cmd = list;
    }

    public String getIsLatest() {
        return this.isLatest;
    }

    public void setIsLatest(String str) {
        this.isLatest = str;
    }
}
