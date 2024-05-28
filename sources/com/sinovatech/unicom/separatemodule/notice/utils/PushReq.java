package com.sinovatech.unicom.separatemodule.notice.utils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushReq {
    private ReqBody reqBody;
    private ReqHead reqHead;

    public ReqHead getReqHead() {
        return this.reqHead;
    }

    public void setReqHead(ReqHead reqHead) {
        this.reqHead = reqHead;
    }

    public ReqBody getReqBody() {
        return this.reqBody;
    }

    public void setReqBody(ReqBody reqBody) {
        this.reqBody = reqBody;
    }

    public String toString() {
        return this.reqHead + "#" + this.reqBody;
    }
}
