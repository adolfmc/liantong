package com.baidu.p120ar.auth;

import android.content.Context;
import com.baidu.p120ar.auth.IAuthGuard;
import com.baidu.p120ar.utils.UiThreadUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.PackageAuthGuard */
/* loaded from: E:\10201592_dexfile_execute.dex */
class PackageAuthGuard implements IAuthGuard {
    private long mExpiredTimestamp;
    private final List<String> mRespectPackages = new ArrayList();

    public boolean checkAuth(Context context, String[] strArr) {
        return true;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void setValidFeatureCodeUpdateListener(IAuthGuard.FeatureCodeUpdateListener featureCodeUpdateListener) {
    }

    public PackageAuthGuard(AuthSetting authSetting) {
        if (authSetting != null) {
            if (authSetting.respectPackageMd5s != null) {
                this.mRespectPackages.addAll(authSetting.respectPackageMd5s);
            }
            this.mExpiredTimestamp = authSetting.expiredTimestamp;
        }
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void doAuth(Context context, final IAuthCallback iAuthCallback) {
        final String[] strArr = new String[1];
        final boolean checkAuth = checkAuth(context, strArr);
        UiThreadUtils.postDelayed(new Runnable() { // from class: com.baidu.ar.auth.PackageAuthGuard.1
            @Override // java.lang.Runnable
            public void run() {
                IAuthCallback iAuthCallback2 = iAuthCallback;
                if (iAuthCallback2 != null) {
                    if (checkAuth) {
                        iAuthCallback2.onSuccess();
                    } else {
                        iAuthCallback2.onError(strArr[0], 0);
                    }
                }
            }
        }, 0L);
    }
}
