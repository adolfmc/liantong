package com.sinovatech.unicom.separatemodule.wechatpay;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WxPayMessage extends EventMessage {
    private int code;
    private String msg;
    private int wechatCode;

    public WxPayMessage(int i) {
        super(i);
    }

    public int getWechatCode() {
        return this.wechatCode;
    }

    public void setWechatCode(int i) {
        this.wechatCode = i;
    }

    @Override // com.sinovatech.unicom.common.EventMessage
    public int getCode() {
        return this.code;
    }

    @Override // com.sinovatech.unicom.common.EventMessage
    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }
}
