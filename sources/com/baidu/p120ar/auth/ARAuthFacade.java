package com.baidu.p120ar.auth;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.baidu.p120ar.auth.IAuthGuard;
import com.baidu.p120ar.bean.DuMixARConfig;
import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.utils.ARLog;
import com.baidu.p120ar.utils.AsyncWorker;
import com.baidu.p120ar.utils.UiThreadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.ARAuthFacade */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARAuthFacade implements IAuthFacade {
    public static final int AUTH_TYPE_AIP = 1;
    public static final int AUTH_TYPE_AR_SERVER = 2;
    public static final int AUTH_TYPE_PACKAGE = 4;
    private byte[] mARLicenseData;
    private AsyncWorker mAuthWorker;
    private volatile FeaturesAuthState sAuthState;
    private IAuthCallback sCallback;
    private AuthSetting sSetting;
    private boolean sIsLoaded = false;
    private final List<IDuMixAuthCallback> sAuthCallbacks = new ArrayList();
    private List<IAuthCallback> sWaitingCallbacks = new ArrayList();
    private boolean mIsFromLicenseData = false;
    private boolean mAppIdIsReady = false;

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public void setAuthLicense(byte[] bArr, String str, String str2, String str3) {
        this.mARLicenseData = bArr;
        DuMixARConfig.setAppId(str);
        DuMixARConfig.setAPIKey(str2);
        DuMixARConfig.setSecretKey(str3);
        this.mAppIdIsReady = !TextUtils.isEmpty(str2);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public void loadAuthInfo(Context context) {
        if (this.mAppIdIsReady) {
            return;
        }
        loadLicenseInfo(context);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public boolean checkOfflineLicenseAuth(Context context, byte[] bArr) {
        if (this.sAuthState != null) {
            return this.sAuthState.isRunning() || !this.sAuthState.isFailed();
        } else if (bArr == null || context == null) {
            return false;
        } else {
            this.mIsFromLicenseData = true;
            AuthSetting readLicense = readLicense(bArr);
            this.sIsLoaded = true;
            if (readLicense == null) {
                this.sAuthState = new FeaturesAuthState(null);
                ARLog.m20419e("ARAuth", "invalid license data");
                this.sAuthState.setAuthFail("invalid license data");
                return false;
            }
            IAuthGuard createGuard = createGuard(readLicense);
            if (createGuard instanceof PackageAuthGuard) {
                AuthSetting authSetting = this.sSetting;
                this.sAuthState = new FeaturesAuthState(authSetting != null ? authSetting.features : null);
                String[] strArr = new String[1];
                boolean checkAuth = ((PackageAuthGuard) createGuard).checkAuth(context, strArr);
                if (checkAuth) {
                    this.sAuthState.setAuthPass();
                } else {
                    this.sAuthState.setAuthFail(strArr[0]);
                    ARLog.m20419e("ARAuth", strArr[0]);
                }
                return checkAuth;
            }
            return false;
        }
    }

    private void loadLicenseInfo(Context context) {
        if (!this.sIsLoaded) {
            AuthSetting authSetting = null;
            byte[] bArr = this.mARLicenseData;
            if (bArr != null && bArr.length > 0) {
                authSetting = readLicense(bArr);
            } else {
                try {
                    authSetting = AuthLicense.loadLicense(context);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.sSetting = authSetting;
        }
        this.sIsLoaded = true;
        AuthSetting authSetting2 = this.sSetting;
        if (authSetting2 == null || TextUtils.isEmpty(authSetting2.respectAppId)) {
            return;
        }
        DuMixARConfig.setAppId(this.sSetting.respectAppId);
        DuMixARConfig.setAPIKey(this.sSetting.respectApiKey);
        DuMixARConfig.setSecretKey("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IDuMixAuthCallback[] getAuthCallbacks() {
        IDuMixAuthCallback[] iDuMixAuthCallbackArr = new IDuMixAuthCallback[this.sAuthCallbacks.size()];
        this.sAuthCallbacks.toArray(iDuMixAuthCallbackArr);
        return iDuMixAuthCallbackArr;
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public List<Integer> checkAuth(Context context, byte[] bArr, final ICallbackWith<List<Integer>> iCallbackWith, final ICallbackWith<Integer> iCallbackWith2) {
        return checkAuth(context, bArr, (iCallbackWith == null && iCallbackWith2 == null) ? null : new IDuMixAuthCallback() { // from class: com.baidu.ar.auth.ARAuthFacade.1
            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onAvailFeaturesUpdate(List<Integer> list) {
                ICallbackWith iCallbackWith3 = iCallbackWith;
                if (iCallbackWith3 != null) {
                    iCallbackWith3.run(list);
                }
            }

            @Override // com.baidu.p120ar.auth.IDuMixAuthCallback
            public void onFeatureRejected(int i) {
                ICallbackWith iCallbackWith3 = iCallbackWith2;
                if (iCallbackWith3 != null) {
                    iCallbackWith3.run(Integer.valueOf(i));
                }
            }
        });
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public List<Integer> checkAuth(Context context, byte[] bArr, IDuMixAuthCallback iDuMixAuthCallback) {
        if (this.sAuthState != null) {
            if (iDuMixAuthCallback != null) {
                if (this.sAuthState.isRunning()) {
                    this.sAuthCallbacks.add(iDuMixAuthCallback);
                } else if (this.sAuthState.isFailed()) {
                    iDuMixAuthCallback.onResult(false);
                } else {
                    iDuMixAuthCallback.onResult(true);
                }
            }
            AuthSetting authSetting = this.sSetting;
            return authSetting != null ? authSetting.features : new ArrayList();
        } else if (bArr == null) {
            throw new IllegalStateException("license数据不能为空");
        } else {
            this.mIsFromLicenseData = true;
            if (iDuMixAuthCallback != null) {
                this.sAuthCallbacks.add(iDuMixAuthCallback);
            }
            AuthSetting readLicense = readLicense(bArr);
            this.sIsLoaded = true;
            final Runnable runnable = new Runnable() { // from class: com.baidu.ar.auth.ARAuthFacade.2
                @Override // java.lang.Runnable
                public void run() {
                    IDuMixAuthCallback[] authCallbacks;
                    for (IDuMixAuthCallback iDuMixAuthCallback2 : ARAuthFacade.this.getAuthCallbacks()) {
                        iDuMixAuthCallback2.onAvailFeaturesUpdate(new ArrayList());
                        iDuMixAuthCallback2.onResult(false);
                        iDuMixAuthCallback2.onFeatureRejected(0);
                    }
                }
            };
            runAuthInner(context.getApplicationContext(), new IAuthCallback() { // from class: com.baidu.ar.auth.ARAuthFacade.3
                @Override // com.baidu.p120ar.auth.IAuthCallback
                public void onSuccess() {
                    for (IDuMixAuthCallback iDuMixAuthCallback2 : ARAuthFacade.this.getAuthCallbacks()) {
                        iDuMixAuthCallback2.onResult(true);
                    }
                }

                @Override // com.baidu.p120ar.auth.IAuthCallback
                public void onError(String str, int i) {
                    Runnable runnable2 = runnable;
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, runnable, new ICallbackWith<List<Integer>>() { // from class: com.baidu.ar.auth.ARAuthFacade.4
                @Override // com.baidu.p120ar.callback.ICallbackWith
                public void run(List<Integer> list) {
                    for (IDuMixAuthCallback iDuMixAuthCallback2 : ARAuthFacade.this.getAuthCallbacks()) {
                        iDuMixAuthCallback2.onAvailFeaturesUpdate(list);
                    }
                }
            });
            return this.sSetting != null ? readLicense.features : new ArrayList();
        }
    }

    private AuthSetting readLicense(byte[] bArr) {
        AuthSetting authSetting = new AuthSetting();
        try {
            AuthLicense.readLicense(bArr, authSetting);
            this.sSetting = authSetting;
        } catch (Exception e) {
            e.printStackTrace();
            this.sSetting = null;
        }
        return authSetting;
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public void doAuth(Context context, final IAuthCallback iAuthCallback) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        this.sCallback = iAuthCallback;
        if (!this.sIsLoaded) {
            loadLicenseInfo(applicationContext);
        }
        runAuthInner(applicationContext, iAuthCallback, new Runnable() { // from class: com.baidu.ar.auth.ARAuthFacade.5
            @Override // java.lang.Runnable
            public void run() {
                if (iAuthCallback != null) {
                    String str = "请检查license文件";
                    if (ARAuthFacade.this.mIsFromLicenseData) {
                        str = "请检查传入checkAuth方法的arLicense数据";
                    } else if (!AipTokenWrapper.isSupportAip()) {
                        str = "请检查dumixar.license文件";
                    }
                    iAuthCallback.onError(str, 0);
                }
            }
        }, null);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public boolean checkFeatureAuth(int i) {
        boolean isFeatureGranted = this.sAuthState != null ? this.sAuthState.isFeatureGranted(i) : true;
        if (!isFeatureGranted) {
            notifyFeatureRejected(i);
        }
        if (isFeatureGranted) {
            HashMap hashMap = new HashMap();
            hashMap.put("feature_code", String.valueOf(i));
            StatisticApi.onEventDebounce("feature_auth", 200L, hashMap);
        }
        return isFeatureGranted;
    }

    private void notifyFeatureRejected(final int i) {
        UiThreadUtils.postDelayed(new Runnable() { // from class: com.baidu.ar.auth.ARAuthFacade.6
            @Override // java.lang.Runnable
            public void run() {
                if (ARAuthFacade.this.sCallback != null) {
                    ARAuthFacade.this.sCallback.onError("未授权功能: " + i, i);
                }
                for (IDuMixAuthCallback iDuMixAuthCallback : ARAuthFacade.this.getAuthCallbacks()) {
                    iDuMixAuthCallback.onFeatureRejected(i);
                }
            }
        }, 0L);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public boolean enableFeature(int i) {
        if (this.sAuthState != null) {
            return this.sAuthState.isFeatureGranted(i);
        }
        return true;
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public boolean isShowAuthTip() {
        AuthSetting authSetting = this.sSetting;
        return authSetting == null || !authSetting.noAuthTip;
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public Bitmap createTipBitmap(Context context) {
        return AuthTipTextImage.createTipImage(context);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public void receiveAuthFailMessage(int i) {
        notifyFeatureRejected(i);
    }

    @Override // com.baidu.p120ar.auth.IAuthFacade
    public void release() {
        AsyncWorker asyncWorker = this.mAuthWorker;
        if (asyncWorker != null) {
            asyncWorker.stop();
            this.mAuthWorker = null;
        }
        this.sIsLoaded = false;
        this.sAuthState = null;
        this.sCallback = null;
        this.sAuthCallbacks.clear();
        this.sWaitingCallbacks.clear();
    }

    private void runAuthInner(Context context, IAuthCallback iAuthCallback, Runnable runnable, ICallbackWith<List<Integer>> iCallbackWith) {
        if (this.sAuthState != null) {
            if (this.sAuthState.isRunning()) {
                if (iAuthCallback != null) {
                    this.sWaitingCallbacks.add(iAuthCallback);
                    return;
                }
                return;
            } else if (this.sAuthState.isFailed()) {
                this.sAuthState.notifyAvailFeatures();
                if (iAuthCallback != null) {
                    iAuthCallback.onError(this.sAuthState.getError(), 0);
                    return;
                }
                return;
            } else {
                this.sAuthState.notifyAvailFeatures();
                if (iAuthCallback != null) {
                    iAuthCallback.onSuccess();
                    return;
                }
                return;
            }
        }
        AuthSetting authSetting = this.sSetting;
        this.sAuthState = new FeaturesAuthState(authSetting != null ? authSetting.features : null);
        AuthSetting authSetting2 = this.sSetting;
        if (authSetting2 != null ? executeAuth(context, iAuthCallback, authSetting2, iCallbackWith) : false) {
            return;
        }
        processAuthError("未知鉴权方式", this.sSetting, context);
        if (runnable != null) {
            UiThreadUtils.postDelayed(runnable, 0L);
        }
    }

    private boolean executeAuth(final Context context, final IAuthCallback iAuthCallback, final AuthSetting authSetting, final ICallbackWith<List<Integer>> iCallbackWith) {
        final IAuthGuard createGuard = createGuard(authSetting);
        if (createGuard != null) {
            createGuard.setValidFeatureCodeUpdateListener(new IAuthGuard.FeatureCodeUpdateListener() { // from class: com.baidu.ar.auth.ARAuthFacade.7
                @Override // com.baidu.p120ar.auth.IAuthGuard.FeatureCodeUpdateListener
                public void onUpdate(Set<Integer> set) {
                    if (ARAuthFacade.this.sAuthState != null) {
                        ARAuthFacade.this.sAuthState.updateAvailFeatures(set);
                        ICallbackWith iCallbackWith2 = iCallbackWith;
                        if (iCallbackWith2 != null) {
                            iCallbackWith2.run(ARAuthFacade.this.sAuthState.getAvailFeatures());
                        }
                    }
                }
            });
            if (this.mAuthWorker == null) {
                this.mAuthWorker = new AsyncWorker("ARAuthWorker");
                this.mAuthWorker.start();
            }
            this.mAuthWorker.execute(new Runnable() { // from class: com.baidu.ar.auth.ARAuthFacade.8
                @Override // java.lang.Runnable
                public void run() {
                    createGuard.doAuth(context, new IAuthCallback() { // from class: com.baidu.ar.auth.ARAuthFacade.8.1
                        @Override // com.baidu.p120ar.auth.IAuthCallback
                        public void onSuccess() {
                            if (ARAuthFacade.this.sAuthState != null) {
                                ARAuthFacade.this.sAuthState.setAuthPass();
                            }
                            if (iAuthCallback != null) {
                                iAuthCallback.onSuccess();
                            }
                            if (ARAuthFacade.this.sWaitingCallbacks.isEmpty()) {
                                return;
                            }
                            for (IAuthCallback iAuthCallback2 : ARAuthFacade.this.sWaitingCallbacks) {
                                iAuthCallback2.onSuccess();
                            }
                        }

                        @Override // com.baidu.p120ar.auth.IAuthCallback
                        public void onError(String str, int i) {
                            ARAuthFacade.this.processAuthError(str, authSetting, context);
                            if (iAuthCallback != null) {
                                iAuthCallback.onError(str, i);
                            }
                            if (ARAuthFacade.this.sWaitingCallbacks.isEmpty()) {
                                return;
                            }
                            for (IAuthCallback iAuthCallback2 : ARAuthFacade.this.sWaitingCallbacks) {
                                iAuthCallback2.onError(str, i);
                            }
                        }
                    });
                }
            });
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processAuthError(String str, AuthSetting authSetting, Context context) {
        if (this.sAuthState != null) {
            this.sAuthState.setAuthFail(str);
        }
        if (context != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("pkg", context.getPackageName());
            if (authSetting != null) {
                hashMap.put("authtype", String.valueOf(authSetting.authType));
                hashMap.put("r_appid", authSetting.respectAppId);
            }
            hashMap.put("event_param", str);
            StatisticApi.onEvent("event_auth_fail", hashMap);
        }
    }

    private IAuthGuard createGuard(AuthSetting authSetting) {
        int i = authSetting.authType & 2;
        boolean z = (authSetting.authType & 4) > 0;
        int i2 = authSetting.authType & 1;
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add(new PackageAuthGuard(authSetting));
        }
        if (arrayList.size() > 1) {
            return new CombinationAuthGuard((IAuthGuard[]) arrayList.toArray(new IAuthGuard[arrayList.size()]));
        }
        if (arrayList.size() == 1) {
            return (IAuthGuard) arrayList.get(0);
        }
        return null;
    }
}
