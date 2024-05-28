package com.baidu.p120ar.auth.impl;

import android.content.Context;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.auth.IOfflineAuthenticator;
import com.baidu.p120ar.libloader.LibLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.impl.OfflineAuthenticator */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OfflineAuthenticator implements IOfflineAuthenticator {
    private static volatile OfflineAuthenticator sIntance;

    public static OfflineAuthenticator getInstance() {
        if (sIntance == null) {
            sIntance = new OfflineAuthenticator();
        }
        return sIntance;
    }

    private OfflineAuthenticator() {
    }

    @Override // com.baidu.p120ar.auth.IOfflineAuthenticator
    public boolean checkLicense(Context context, byte[] bArr) {
        LibLoader.load(context, null);
        return ARAuth.checkOfflineLicenseAuth(context, bArr);
    }
}
