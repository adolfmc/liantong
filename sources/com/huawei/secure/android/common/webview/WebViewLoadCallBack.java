package com.huawei.secure.android.common.webview;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface WebViewLoadCallBack {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum ErrorCode {
        HTTP_URL,
        URL_NOT_IN_WHITE_LIST,
        OTHER
    }

    void onCheckError(String str, ErrorCode errorCode);
}
