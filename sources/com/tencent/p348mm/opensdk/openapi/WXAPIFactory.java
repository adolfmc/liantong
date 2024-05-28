package com.tencent.p348mm.opensdk.openapi;

import android.content.Context;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.openapi.WXAPIFactory */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WXAPIFactory {
    private static final String TAG = "MicroMsg.PaySdk.WXFactory";

    private WXAPIFactory() {
        throw new RuntimeException(WXAPIFactory.class.getSimpleName() + " should not be instantiated");
    }

    public static IWXAPI createWXAPI(Context context, String str) {
        return createWXAPI(context, str, true);
    }

    public static IWXAPI createWXAPI(Context context, String str, boolean z) {
        C10384Log.m6211d("MicroMsg.PaySdk.WXFactory", "createWXAPI, appId = " + str + ", checkSignature = " + z);
        return createWXAPI(context, str, z, 2);
    }

    public static IWXAPI createWXAPI(Context context, String str, boolean z, int i) {
        C10384Log.m6211d("MicroMsg.PaySdk.WXFactory", "createWXAPI, appId = " + str + ", checkSignature = " + z + ", launchMode = " + i);
        return new WXApiImplV10(context, str, z, i);
    }
}
