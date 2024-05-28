package com.baidu.p120ar.auth;

import android.content.Context;
import android.graphics.Bitmap;
import com.baidu.p120ar.callback.ICallbackWith;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.IAuthFacade */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAuthFacade {
    List<Integer> checkAuth(Context context, byte[] bArr, IDuMixAuthCallback iDuMixAuthCallback);

    List<Integer> checkAuth(Context context, byte[] bArr, ICallbackWith<List<Integer>> iCallbackWith, ICallbackWith<Integer> iCallbackWith2);

    boolean checkFeatureAuth(int i);

    boolean checkOfflineLicenseAuth(Context context, byte[] bArr);

    Bitmap createTipBitmap(Context context);

    void doAuth(Context context, IAuthCallback iAuthCallback);

    boolean enableFeature(int i);

    boolean isShowAuthTip();

    void loadAuthInfo(Context context);

    void receiveAuthFailMessage(int i);

    void release();

    void setAuthLicense(byte[] bArr, String str, String str2, String str3);
}
