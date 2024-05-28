package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class XiaoheitiaoEvent extends EventMessage<String> {
    private String cancel;
    private String confirm;
    private String msg;
    private String title;

    public XiaoheitiaoEvent(int i) {
        super(i);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getCancel() {
        return this.cancel;
    }

    public void setCancel(String str) {
        this.cancel = str;
    }

    public String getConfirm() {
        return this.confirm;
    }

    public void setConfirm(String str) {
        this.confirm = str;
    }
}
