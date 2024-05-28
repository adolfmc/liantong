package com.baidu.p120ar.auth;

import com.baidu.p120ar.auth.impl.AsyncAuthenticator;
import com.baidu.p120ar.auth.impl.Authenticator;
import com.baidu.p120ar.auth.impl.OfflineAuthenticator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.ARAuthAPI */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARAuthAPI {
    public static IAuthenticator getAsyncAuthenticator(String str, String str2, String str3) {
        return AsyncAuthenticator.getInstance(str, str2, str3);
    }

    public static IAuthenticator getAuthenticator() {
        return Authenticator.getInstance();
    }

    public static IOfflineAuthenticator getOfflineAuthenticator() {
        return OfflineAuthenticator.getInstance();
    }
}
