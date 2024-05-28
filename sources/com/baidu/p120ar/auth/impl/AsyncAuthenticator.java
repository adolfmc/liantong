package com.baidu.p120ar.auth.impl;

import android.content.Context;
import com.baidu.p120ar.auth.ARAuth;
import com.baidu.p120ar.auth.AuthJni;
import com.baidu.p120ar.auth.IAuthenticateCallback;
import com.baidu.p120ar.auth.IAuthenticator;
import com.baidu.p120ar.libloader.LibLoader;
import com.baidu.p120ar.utils.AsyncWorker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.impl.AsyncAuthenticator */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsyncAuthenticator implements IAuthenticator {
    private static volatile AsyncAuthenticator sInstance;
    private String mApiKey;
    private String mAppId;
    private String mSecretKey;
    private AsyncWorker mWorker;

    private AsyncAuthenticator(String str, String str2, String str3) {
        this.mAppId = str;
        this.mApiKey = str2;
        this.mSecretKey = str3;
    }

    public static AsyncAuthenticator getInstance(String str, String str2, String str3) {
        if (sInstance == null) {
            sInstance = new AsyncAuthenticator(str, str2, str3);
        } else {
            sInstance.mAppId = str;
            sInstance.mApiKey = str2;
            sInstance.mSecretKey = str3;
        }
        return sInstance;
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void init(Context context, byte[] bArr) {
        init(context, bArr, null);
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void init(final Context context, final byte[] bArr, final IAuthenticateCallback iAuthenticateCallback) {
        LibLoader.load(context, null);
        ARAuth.setAuthLicense(bArr, this.mAppId, this.mApiKey, this.mSecretKey);
        if (this.mWorker == null) {
            this.mWorker = new AsyncWorker("AsyncAuthenticator");
            this.mWorker.start();
        }
        AuthJni.init();
        this.mWorker.execute(new Runnable() { // from class: com.baidu.ar.auth.impl.AsyncAuthenticator.1
            @Override // java.lang.Runnable
            public void run() {
                AsyncAuthenticator.this.execute(context, bArr, iAuthenticateCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void execute(Context context, byte[] bArr, IAuthenticateCallback iAuthenticateCallback) {
        Authenticator.getInstance().init(context, bArr, iAuthenticateCallback);
    }

    @Override // com.baidu.p120ar.auth.IAuthenticator
    public void release() {
        ARAuth.release();
    }
}
