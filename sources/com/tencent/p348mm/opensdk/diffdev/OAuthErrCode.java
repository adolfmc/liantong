package com.tencent.p348mm.opensdk.diffdev;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.OAuthErrCode */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum OAuthErrCode {
    WechatAuth_Err_OK(0),
    WechatAuth_Err_NormalErr(-1),
    WechatAuth_Err_NetworkErr(-2),
    WechatAuth_Err_JsonDecodeErr(-3),
    WechatAuth_Err_Cancel(-4),
    WechatAuth_Err_Timeout(-5),
    WechatAuth_Err_Auth_Stopped(-6);
    
    private int code;

    OAuthErrCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "OAuthErrCode:" + this.code;
    }
}
