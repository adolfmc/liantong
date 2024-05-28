package com.sinovatech.unicom.separatemodule.notice.utils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushResp {
    private RespBody respBody;
    private RespHead respHead;

    public RespBody getRespBody() {
        return this.respBody;
    }

    public void setRespBody(RespBody respBody) {
        this.respBody = respBody;
    }

    public RespHead getRespHead() {
        return this.respHead;
    }

    public void setRespHead(RespHead respHead) {
        this.respHead = respHead;
    }

    public String toString() {
        return this.respHead + "#" + this.respBody;
    }
}
