package com.chinaunicon.jtwifilib.jtcommon.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtChallengeCode extends JtBaseModel {
    private String ChallengeCode;
    private String ChallengeCode1;
    private String FailReason;
    private String MAC;
    private String Model;
    private String PhoneAppURL;
    private String SequenceId;
    private String Status;
    private String Vendor;

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

    public String getMAC() {
        return this.MAC;
    }

    public void setMAC(String str) {
        this.MAC = str;
    }

    public String getVendor() {
        return this.Vendor;
    }

    public void setVendor(String str) {
        this.Vendor = str;
    }

    public String getModel() {
        return this.Model;
    }

    public void setModel(String str) {
        this.Model = str;
    }

    public String getPhoneAppURL() {
        return this.PhoneAppURL;
    }

    public void setPhoneAppURL(String str) {
        this.PhoneAppURL = str;
    }

    public String getChallengeCode() {
        return this.ChallengeCode;
    }

    public void setChallengeCode(String str) {
        this.ChallengeCode = str;
    }

    public String getChallengeCode1() {
        return this.ChallengeCode1;
    }

    public void setChallengeCode1(String str) {
        this.ChallengeCode1 = str;
    }

    public String getFailReason() {
        return this.FailReason;
    }

    public void setFailReason(String str) {
        this.FailReason = str;
    }
}
