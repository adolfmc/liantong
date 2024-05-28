package com.baidu.p120ar.auth;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.IAuthenticator */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAuthenticator {
    void init(Context context, byte[] bArr);

    void init(Context context, byte[] bArr, IAuthenticateCallback iAuthenticateCallback);

    void release();
}
