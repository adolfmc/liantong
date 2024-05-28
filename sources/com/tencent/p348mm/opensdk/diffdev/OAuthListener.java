package com.tencent.p348mm.opensdk.diffdev;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.OAuthListener */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface OAuthListener {
    void onAuthFinish(OAuthErrCode oAuthErrCode, String str);

    void onAuthGotQrcode(String str, byte[] bArr);

    void onQrcodeScanned();
}
