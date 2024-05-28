package com.tencent.p348mm.sdk.openapi;

import android.content.Context;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.openapi.WXAPIFactory */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXAPIFactory {
    private static final String TAG = "MicroMsg.PaySdk.WXFactory";

    private WXAPIFactory() {
        throw new RuntimeException(getClass().getSimpleName() + " should not be instantiated");
    }

    public static IWXAPI createWXAPI(Context context, String str) {
        return createWXAPI(context, str, false);
    }

    public static IWXAPI createWXAPI(Context context, String str, boolean z) {
        C10393b.m6191c("MicroMsg.PaySdk.WXFactory", "createWXAPI, appId = " + str + ", checkSignature = " + z);
        return new WXApiImplV10(context, str, z);
    }
}
