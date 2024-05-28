package com.github.lzyzsd.jsbridge;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DefaultHandler implements BridgeHandler {
    String TAG = "DefaultHandler";

    @Override // com.github.lzyzsd.jsbridge.BridgeHandler
    public void handler(String str, CallBackFunction callBackFunction) {
        if (callBackFunction != null) {
            callBackFunction.onCallBack("DefaultHandler response data");
        }
    }
}
