package com.crb.jscard.http.bean;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ApplyResp {
    private String code;
    private List<Command> commands;
    private String info;
    private String session;
    private String step;

    public String getCode() {
        return this.code;
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public String getInfo() {
        return this.info;
    }

    public String getSession() {
        return this.session;
    }

    public String getStep() {
        return this.step;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setCommands(List<Command> list) {
        this.commands = list;
    }

    public void setInfo(String str) {
        this.info = str;
    }

    public void setSession(String str) {
        this.session = str;
    }

    public void setStep(String str) {
        this.step = str;
    }
}
