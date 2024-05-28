package com.crb.jscard.http.bean;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ApplyReq {
    private String city_id;
    private List<Command> commands;
    private String iccid;
    private String order_id;
    private String session;
    private String step;
    private int type;

    public String getCity_id() {
        return this.city_id;
    }

    public List<Command> getCommands() {
        return this.commands;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getOrder_id() {
        return this.order_id;
    }

    public String getSession() {
        return this.session;
    }

    public String getStep() {
        return this.step;
    }

    public int getType() {
        return this.type;
    }

    public void setCity_id(String str) {
        this.city_id = str;
    }

    public void setCommands(List<Command> list) {
        this.commands = list;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public void setOrder_id(String str) {
        this.order_id = str;
    }

    public void setSession(String str) {
        this.session = str;
    }

    public void setStep(String str) {
        this.step = str;
    }

    public void setType(int i) {
        this.type = i;
    }
}
