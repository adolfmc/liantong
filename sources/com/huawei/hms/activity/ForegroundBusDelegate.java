package com.huawei.hms.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.huawei.hms.activity.internal.BusResponseCallback;
import com.huawei.hms.activity.internal.BusResponseResult;
import com.huawei.hms.activity.internal.ForegroundBusResponseMgr;
import com.huawei.hms.activity.internal.ForegroundInnerHeader;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IntentUtil;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.UIUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ForegroundBusDelegate implements IBridgeActivityDelegate {
    public static final String HMS_FOREGROUND_REQ_BODY = "HMS_FOREGROUND_REQ_BODY";
    public static final String HMS_FOREGROUND_REQ_HEADER = "HMS_FOREGROUND_REQ_HEADER";
    public static final String HMS_FOREGROUND_REQ_INNER = "HMS_FOREGROUND_REQ_INNER";
    public static final String HMS_FOREGROUND_RESP_HEADER = "HMS_FOREGROUND_RESP_HEADER";
    public static final String INNER_PKG_NAME = "INNER_PACKAGE_NAME";

    /* renamed from: a */
    private RequestHeader f10894a;

    /* renamed from: b */
    private String f10895b;

    /* renamed from: c */
    private ForegroundInnerHeader f10896c = new ForegroundInnerHeader();

    /* renamed from: d */
    private ResponseHeader f10897d;

    /* renamed from: e */
    private WeakReference<Activity> f10898e;

    /* renamed from: f */
    private boolean f10899f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.activity.ForegroundBusDelegate$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4839b implements AvailableAdapter.AvailableCallBack {
        private C4839b() {
        }

        @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
        public void onComplete(int i) {
            if (i == 0) {
                ForegroundBusDelegate.this.m15319h();
                return;
            }
            HMSLog.m14110i("ForegroundBusDelegate", "version check failed");
            ForegroundBusDelegate.this.m15331a(0, "apk version is invalid");
        }
    }

    /* renamed from: b */
    private BusResponseCallback m15325b(String str) {
        return ForegroundBusResponseMgr.getInstance().get(str);
    }

    /* renamed from: c */
    private void m15324c() {
        if (this.f10894a != null) {
            m15327a("HMS_SDK_BASE_ACTIVITY_STARTED");
        }
    }

    /* renamed from: d */
    private void m15323d() {
        m15327a("HMS_SDK_BASE_START_CORE_ACTIVITY");
    }

    /* renamed from: e */
    private void m15322e() {
        if (m15320g() == null) {
            HMSLog.m14112e("ForegroundBusDelegate", "checkMinVersion failed, activity must not be null.");
        } else if (this.f10899f) {
            m15319h();
        } else if (!Util.isAvailableLibExist(m15320g().getApplicationContext())) {
            if (HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(m15320g().getApplicationContext(), this.f10896c.getApkVersion()) != 0) {
                HMSLog.m14112e("ForegroundBusDelegate", "checkMinVersion failed, and no available lib exists.");
                m15331a(0, "apk version is invalid");
                return;
            }
            m15319h();
        } else {
            C4839b c4839b = new C4839b();
            AvailableAdapter availableAdapter = new AvailableAdapter(this.f10896c.getApkVersion());
            int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(m15320g());
            if (isHuaweiMobileServicesAvailable == 0) {
                c4839b.onComplete(isHuaweiMobileServicesAvailable);
            } else if (availableAdapter.isUserResolvableError(isHuaweiMobileServicesAvailable)) {
                m15330a(m15320g(), availableAdapter, c4839b);
            } else {
                c4839b.onComplete(isHuaweiMobileServicesAvailable);
            }
        }
    }

    /* renamed from: f */
    private void m15321f() {
        Activity m15320g = m15320g();
        if (m15320g == null || m15320g.isFinishing()) {
            return;
        }
        m15320g.finish();
    }

    /* renamed from: g */
    private Activity m15320g() {
        WeakReference<Activity> weakReference = this.f10898e;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m15319h() {
        String hMSPackageNameForMultiService;
        HMSLog.m14110i("ForegroundBusDelegate", "startApkHubActivity");
        Activity m15320g = m15320g();
        if (m15320g == null) {
            HMSLog.m14112e("ForegroundBusDelegate", "startApkHubActivity but activity is null");
            return;
        }
        if (this.f10899f) {
            hMSPackageNameForMultiService = m15320g.getPackageName();
        } else {
            hMSPackageNameForMultiService = HMSPackageManager.getInstance(m15320g.getApplicationContext()).getHMSPackageNameForMultiService();
        }
        Intent intent = new Intent(this.f10896c.getAction());
        intent.putExtra(HMS_FOREGROUND_REQ_BODY, this.f10895b);
        try {
            intent.setPackage(hMSPackageNameForMultiService);
        } catch (IllegalArgumentException unused) {
            HMSLog.m14112e("ForegroundBusDelegate", "IllegalArgumentException when startApkHubActivity intent.setPackage");
        }
        intent.putExtra(BridgeActivity.EXTRA_IS_FULLSCREEN, UIUtil.isActivityFullscreen(m15320g));
        intent.setClassName(hMSPackageNameForMultiService, "com.huawei.hms.core.activity.UiJumpActivity");
        intent.putExtra(HMS_FOREGROUND_REQ_HEADER, this.f10894a.toJson());
        intent.putExtra("intent.extra.hms.core.DELEGATE_NAME", "com.huawei.hms.core.activity.ForegroundBus");
        try {
            m15326b();
            m15320g.startActivityForResult(intent, 431057);
        } catch (ActivityNotFoundException e) {
            HMSLog.m14111e("ForegroundBusDelegate", "Launch sign in Intent failed. hms is probably being updated：", e);
            m15331a(0, "launch bus intent failed");
        }
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public int getRequestCode() {
        return 431057;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityCreate(Activity activity) {
        String stringExtra;
        RequestHeader requestHeader;
        this.f10898e = new WeakReference<>(activity);
        Intent intent = activity.getIntent();
        try {
            stringExtra = intent.getStringExtra(HMS_FOREGROUND_REQ_HEADER);
            requestHeader = new RequestHeader();
            this.f10894a = requestHeader;
        } catch (Exception e) {
            HMSLog.m14112e("ForegroundBusDelegate", "ForegroundBusDelegate getStringExtra error:" + e.getMessage());
        }
        if (!requestHeader.fromJson(stringExtra)) {
            m15331a(0, "header is invalid");
            return;
        }
        this.f10895b = intent.getStringExtra(HMS_FOREGROUND_REQ_BODY);
        ForegroundInnerHeader foregroundInnerHeader = this.f10896c;
        if (foregroundInnerHeader == null) {
            m15331a(0, "inner header is invalid");
            return;
        }
        foregroundInnerHeader.fromJson(intent.getStringExtra(HMS_FOREGROUND_REQ_INNER));
        if (TextUtils.isEmpty(this.f10894a.getApiName())) {
            m15331a(0, "action is invalid");
            return;
        }
        m15333a();
        if (!TextUtils.isEmpty(intent.getStringExtra(INNER_PKG_NAME))) {
            HMSLog.m14110i("ForegroundBusDelegate", "isUseInnerHms: true");
            this.f10899f = true;
        }
        m15322e();
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeActivityDestroy() {
        m15324c();
        this.f10898e = null;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public boolean onBridgeActivityResult(int i, int i2, Intent intent) {
        if (i == 431057) {
            if (intent != null && intent.hasExtra(HMS_FOREGROUND_RESP_HEADER)) {
                String stringExtra = intent.getStringExtra(HMS_FOREGROUND_RESP_HEADER);
                ResponseHeader responseHeader = new ResponseHeader();
                this.f10897d = responseHeader;
                JsonUtil.jsonToEntity(stringExtra, responseHeader);
            }
            m15323d();
            BusResponseCallback m15325b = m15325b(this.f10896c.getResponseCallbackKey());
            if (m15325b == null) {
                m15332a(i2, intent);
                return true;
            }
            BusResponseResult succeedReturn = m15325b.succeedReturn(this.f10898e.get(), i2, intent);
            if (succeedReturn == null) {
                m15332a(i2, intent);
                return true;
            }
            m15332a(succeedReturn.getCode(), succeedReturn.getIntent());
            return true;
        }
        return false;
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onBridgeConfigurationChanged() {
    }

    @Override // com.huawei.hms.activity.IBridgeActivityDelegate
    public void onKeyUp(int i, KeyEvent keyEvent) {
    }

    /* renamed from: b */
    private void m15326b() {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.f10894a);
        mapFromForegroundRequestHeader.put("direction", "req");
        mapFromForegroundRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f10894a.getKitSdkVersion())));
        if (m15320g() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(m15320g().getApplicationContext(), "HMS_SDK_BASE_START_CORE_ACTIVITY", mapFromForegroundRequestHeader);
        }
    }

    /* renamed from: a */
    private void m15332a(int i, Intent intent) {
        HMSLog.m14110i("ForegroundBusDelegate", "succeedReturn");
        Activity m15320g = m15320g();
        if (m15320g == null) {
            return;
        }
        m15320g.setResult(i, IntentUtil.modifyIntentBehaviorsSafe(intent));
        m15321f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15331a(int i, String str) {
        HMSLog.m14112e("ForegroundBusDelegate", str);
        Activity m15320g = m15320g();
        if (m15320g == null) {
            return;
        }
        BusResponseCallback m15325b = m15325b(this.f10896c.getResponseCallbackKey());
        if (m15325b != null) {
            BusResponseResult innerError = m15325b.innerError(this.f10898e.get(), i, str);
            if (innerError == null) {
                m15320g.setResult(0);
            } else {
                m15320g.setResult(innerError.getCode(), IntentUtil.modifyIntentBehaviorsSafe(innerError.getIntent()));
            }
        } else {
            m15320g.setResult(0);
        }
        m15321f();
    }

    /* renamed from: a */
    private static void m15330a(Activity activity, AvailableAdapter availableAdapter, AvailableAdapter.AvailableCallBack availableCallBack) {
        if (activity == null) {
            HMSLog.m14110i("ForegroundBusDelegate", "null activity, could not start resolution intent");
        }
        availableAdapter.startResolution(activity, availableCallBack);
    }

    /* renamed from: a */
    private void m15333a() {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.f10894a);
        mapFromForegroundRequestHeader.put("direction", "req");
        mapFromForegroundRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f10894a.getKitSdkVersion())));
        if (m15320g() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(m15320g().getApplicationContext(), "HMS_SDK_BASE_ACTIVITY_STARTED", mapFromForegroundRequestHeader);
        }
    }

    /* renamed from: a */
    private void m15327a(String str) {
        Map<String, String> mapFromForegroundRequestHeader = HiAnalyticsUtil.getInstance().getMapFromForegroundRequestHeader(this.f10894a);
        mapFromForegroundRequestHeader.put("direction", "rsp");
        mapFromForegroundRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f10894a.getKitSdkVersion())));
        ResponseHeader responseHeader = this.f10897d;
        if (responseHeader != null) {
            mapFromForegroundRequestHeader.put("statusCode", String.valueOf(responseHeader.getStatusCode()));
            mapFromForegroundRequestHeader.put("result", String.valueOf(this.f10897d.getErrorCode()));
        }
        if (m15320g() != null) {
            HiAnalyticsUtil.getInstance().onNewEvent(m15320g().getApplicationContext(), str, mapFromForegroundRequestHeader);
        }
    }
}
