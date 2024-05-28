package com.baidu.p120ar.auth.impl;

import android.content.Context;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.auth.IAuthenticateCallback;
import com.baidu.p120ar.auth.IAuthenticator;
import com.baidu.p120ar.auth.IDuMixAuthCallback;
import com.baidu.p120ar.libloader.LibLoader;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.impl.Authenticator */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Authenticator implements IAuthenticator {
    private static volatile Authenticator sInstance;

    public static Authenticator getInstance() {
        if (sInstance == null) {
            sInstance = new Authenticator();
        }
        return sInstance;
    }

    private Authenticator() {
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void init(Context context, byte[] bArr) {
        init(context, bArr, null);
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void init(final Context context, byte[] bArr, final IAuthenticateCallback iAuthenticateCallback) {
        LibLoader.load(context, null);
        final List[] listArr = {ARAuth.checkAuth(context, bArr, new IDuMixAuthCallback() { // from class: com.baidu.ar.auth.impl.Authenticator.1
            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onResult(boolean z) {
                if (z) {
                    ARAuth.doAuth(context, null);
                } else {
                    listArr[0].clear();
                }
                IAuthenticateCallback iAuthenticateCallback2 = iAuthenticateCallback;
                if (iAuthenticateCallback2 != null) {
                    iAuthenticateCallback2.onResult(z, listArr[0]);
                }
            }

            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onAvailFeaturesUpdate(List<Integer> list) {
                listArr[0] = list;
                IAuthenticateCallback iAuthenticateCallback2 = iAuthenticateCallback;
                if (iAuthenticateCallback2 != null) {
                    iAuthenticateCallback2.onAvailFeaturesChanged(list);
                }
            }

            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onFeatureRejected(int i) {
                IAuthenticateCallback iAuthenticateCallback2 = iAuthenticateCallback;
                if (iAuthenticateCallback2 != null) {
                    iAuthenticateCallback2.onFeatureRejected(i);
                }
            }
        })};
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void release() {
        ARAuth.release();
    }
}
