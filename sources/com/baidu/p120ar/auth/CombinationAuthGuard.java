package com.baidu.p120ar.auth;

import android.content.Context;
import com.baidu.p120ar.auth.IAuthGuard;
import com.baidu.p120ar.utils.UiThreadUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.CombinationAuthGuard */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CombinationAuthGuard implements IAuthGuard {
    private IAuthGuard[] mGuards;
    private volatile boolean mIsAccept;
    private volatile boolean mIsChecking = false;

    public CombinationAuthGuard(IAuthGuard... iAuthGuardArr) {
        this.mGuards = iAuthGuardArr;
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void setValidFeatureCodeUpdateListener(IAuthGuard.FeatureCodeUpdateListener featureCodeUpdateListener) {
        IAuthGuard[] iAuthGuardArr = this.mGuards;
        if (iAuthGuardArr != null) {
            for (IAuthGuard iAuthGuard : iAuthGuardArr) {
                iAuthGuard.setValidFeatureCodeUpdateListener(featureCodeUpdateListener);
            }
        }
    }

    @Override // com.baidu.p120ar.auth.IAuthGuard
    public void doAuth(Context context, final IAuthCallback iAuthCallback) {
        synchronized (this) {
            if (this.mIsChecking) {
                return;
            }
            IAuthGuard[] iAuthGuardArr = this.mGuards;
            if (iAuthGuardArr != null && iAuthGuardArr.length > 0) {
                this.mIsChecking = true;
                final int[] iArr = {0, iAuthGuardArr.length};
                for (IAuthGuard iAuthGuard : iAuthGuardArr) {
                    iAuthGuard.doAuth(context, new IAuthCallback() { // from class: com.baidu.ar.auth.CombinationAuthGuard.1
                        @Override // com.baidu.p120ar.auth.IAuthCallback
                        public void onSuccess() {
                            synchronized (this) {
                                if (!CombinationAuthGuard.this.mIsAccept && CombinationAuthGuard.this.mIsChecking) {
                                    int[] iArr2 = iArr;
                                    iArr2[0] = iArr2[0] + 1;
                                    if (iArr[0] == iArr[1]) {
                                        CombinationAuthGuard.this.mIsAccept = true;
                                        if (iAuthCallback != null) {
                                            iAuthCallback.onSuccess();
                                        }
                                    }
                                }
                            }
                        }

                        @Override // com.baidu.p120ar.auth.IAuthCallback
                        public void onError(String str, int i) {
                            synchronized (this) {
                                boolean z = CombinationAuthGuard.this.mIsChecking;
                                CombinationAuthGuard.this.mIsChecking = false;
                                CombinationAuthGuard.this.setValidFeatureCodeUpdateListener(null);
                                if (z && iAuthCallback != null) {
                                    iAuthCallback.onError(str, i);
                                }
                            }
                        }
                    });
                }
                return;
            }
            UiThreadUtils.postDelayed(new Runnable() { // from class: com.baidu.ar.auth.CombinationAuthGuard.2
                @Override // java.lang.Runnable
                public void run() {
                    IAuthCallback iAuthCallback2 = iAuthCallback;
                    if (iAuthCallback2 != null) {
                        iAuthCallback2.onError("无效的鉴权组合方式", 0);
                    }
                }
            }, 0L);
        }
    }
}
