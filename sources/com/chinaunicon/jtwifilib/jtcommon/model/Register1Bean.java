package com.chinaunicon.jtwifilib.jtcommon.model;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Register1Bean {
    private String CmdType;
    private String FailReason;
    private String SequenceId;
    private String Status;
    private String msg;

    public String toString() {
        return "Register1Bean{CmdType='" + this.CmdType + "', SequenceId='" + this.SequenceId + "', Status='" + this.Status + "', FailReason='" + this.FailReason + "'}";
    }

    public String getCmdType() {
        return this.CmdType;
    }

    public void setCmdType(String str) {
        this.CmdType = str;
    }

    public String getSequenceId() {
        return this.SequenceId;
    }

    public void setSequenceId(String str) {
        this.SequenceId = str;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String str) {
        this.Status = str;
    }

    public String getFailReason() {
        return this.FailReason;
    }

    public void setFailReason(String str) {
        this.FailReason = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
