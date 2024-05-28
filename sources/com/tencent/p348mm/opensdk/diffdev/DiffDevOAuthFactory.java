package com.tencent.p348mm.opensdk.diffdev;

import com.tencent.p348mm.opensdk.diffdev.p351a.C10374a;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.diffdev.DiffDevOAuthFactory */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DiffDevOAuthFactory {
    public static final int MAX_SUPPORTED_VERSION = 1;
    private static final String TAG = "MicroMsg.SDK.DiffDevOAuthFactory";
    public static final int VERSION_1 = 1;
    private static IDiffDevOAuth v1Instance;

    private DiffDevOAuthFactory() {
    }

    public static IDiffDevOAuth getDiffDevOAuth() {
        return getDiffDevOAuth(1);
    }

    public static IDiffDevOAuth getDiffDevOAuth(int i) {
        C10384Log.m6208v("MicroMsg.SDK.DiffDevOAuthFactory", "getDiffDevOAuth, version = " + i);
        if (i > 1) {
            C10384Log.m6210e("MicroMsg.SDK.DiffDevOAuthFactory", "getDiffDevOAuth fail, unsupported version = " + i);
            return null;
        } else if (i != 1) {
            return null;
        } else {
            if (v1Instance == null) {
                v1Instance = new C10374a();
            }
            return v1Instance;
        }
    }
}
