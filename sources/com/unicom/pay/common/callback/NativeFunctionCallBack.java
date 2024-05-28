package com.unicom.pay.common.callback;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface NativeFunctionCallBack {
    void aliPay(Context context, String str, DataCallback dataCallback);

    void getCode(String str, String str2, String str3, String str4, DataCallback dataCallback);

    void loadUrl(String str, boolean z);

    void openWebview(Context context, String str);

    void openWebview(Context context, String str, String str2);

    void reloadUrl();

    void trendsEvent(String str, String str2, String str3, String str4);

    void unionPay(Context context, String str, DataCallback dataCallback);
}
