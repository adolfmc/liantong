package com.huawei.hms.adapter;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.activity.BridgeActivity;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.p213ui.BaseResolutionAdapter;
import com.huawei.hms.adapter.p213ui.UpdateAdapter;
import com.huawei.hms.adapter.sysobs.ApkResolutionFailedManager;
import com.huawei.hms.adapter.sysobs.SystemManager;
import com.huawei.hms.adapter.sysobs.SystemObserver;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.ResponseWrap;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IntentUtil;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.ResolutionFlagUtil;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BaseAdapter {

    /* renamed from: a */
    private WeakReference<ApiClient> f10922a;

    /* renamed from: b */
    private WeakReference<Activity> f10923b;

    /* renamed from: c */
    private BaseCallBack f10924c;

    /* renamed from: d */
    private String f10925d;

    /* renamed from: e */
    private String f10926e;

    /* renamed from: f */
    private Parcelable f10927f;

    /* renamed from: g */
    private BaseCallBack f10928g;

    /* renamed from: h */
    private String f10929h;

    /* renamed from: i */
    private Context f10930i;

    /* renamed from: j */
    private RequestHeader f10931j = new RequestHeader();

    /* renamed from: k */
    private ResponseHeader f10932k = new ResponseHeader();

    /* renamed from: l */
    private SystemObserver f10933l;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface BaseCallBack {
        void onComplete(String str, String str2, Parcelable parcelable);

        void onError(String str);
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class BaseRequestResultCallback implements ResultCallback<ResolveResult<CoreBaseResponse>> {

        /* renamed from: a */
        private AtomicBoolean f10937a = new AtomicBoolean(true);

        public BaseRequestResultCallback() {
        }

        /* renamed from: a */
        private void m15265a(String str, BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse, int i) {
            if ("intent".equals(str)) {
                Activity m15281c = BaseAdapter.this.m15281c();
                HMSLog.m14110i("BaseAdapter", "activity is: " + m15281c);
                if (m15281c != null && !m15281c.isFinishing()) {
                    PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
                    if (pendingIntent != null) {
                        if (!Util.isAvailableLibExist(BaseAdapter.this.f10930i)) {
                            baseCallBack.onError(BaseAdapter.this.m15288b(-9));
                            return;
                        } else {
                            BaseAdapter.this.m15309a(m15281c, pendingIntent, coreBaseResponse);
                            return;
                        }
                    }
                    Intent intent = coreBaseResponse.getIntent();
                    if (intent != null) {
                        if (!Util.isAvailableLibExist(BaseAdapter.this.f10930i)) {
                            baseCallBack.onError(BaseAdapter.this.m15288b(-9));
                            return;
                        } else {
                            BaseAdapter.this.m15309a(m15281c, intent, coreBaseResponse);
                            return;
                        }
                    } else if (i == 2) {
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseCallBack.onError(baseAdapter.m15288b(baseAdapter.f10932k.getErrorCode()));
                        return;
                    } else {
                        HMSLog.m14112e("BaseAdapter", "hasResolution is true but NO_SOLUTION");
                        baseCallBack.onError(BaseAdapter.this.m15288b(-4));
                        return;
                    }
                }
                HMSLog.m14112e("BaseAdapter", "activity null");
                BaseAdapter.this.m15302a(baseCallBack, coreBaseResponse);
            } else if (!"installHMS".equals(str)) {
                BaseAdapter.this.m15302a(baseCallBack, coreBaseResponse);
            } else {
                HMSLog.m14110i("BaseAdapter", "has resolutin: installHMS");
                m15266a(baseCallBack, coreBaseResponse);
            }
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        public void onResult(ResolveResult<CoreBaseResponse> resolveResult) {
            BaseCallBack m15289b = BaseAdapter.this.m15289b();
            if (m15289b == null) {
                HMSLog.m14112e("BaseAdapter", "onResult baseCallBack null");
            } else if (resolveResult == null) {
                HMSLog.m14112e("BaseAdapter", "result null");
                m15289b.onError(BaseAdapter.this.m15288b(-1));
            } else {
                CoreBaseResponse value = resolveResult.getValue();
                if (value == null) {
                    HMSLog.m14112e("BaseAdapter", "response null");
                    m15289b.onError(BaseAdapter.this.m15288b(-1));
                } else if (!TextUtils.isEmpty(value.getJsonHeader())) {
                    JsonUtil.jsonToEntity(value.getJsonHeader(), BaseAdapter.this.f10932k);
                    if (this.f10937a.compareAndSet(true, false)) {
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseAdapter.m15307a(baseAdapter.f10930i, BaseAdapter.this.f10932k);
                    }
                    String resolution = BaseAdapter.this.f10932k.getResolution();
                    int statusCode = BaseAdapter.this.f10932k.getStatusCode();
                    HMSLog.m14110i("BaseAdapter", "api is: " + BaseAdapter.this.f10932k.getApiName() + ", resolution: " + resolution + ", status_code: " + statusCode);
                    m15265a(resolution, m15289b, value, statusCode);
                } else {
                    HMSLog.m14112e("BaseAdapter", "jsonHeader null");
                    m15289b.onError(BaseAdapter.this.m15288b(-1));
                }
            }
        }

        /* renamed from: a */
        private void m15266a(final BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
            if (Util.isAvailableLibExist(BaseAdapter.this.f10930i)) {
                Activity m15281c = BaseAdapter.this.m15281c();
                if (m15281c != null && !m15281c.isFinishing()) {
                    HMSLog.m14110i("BaseAdapter", "start handleSolutionForHMS");
                    AvailableAdapter availableAdapter = new AvailableAdapter(10000000);
                    availableAdapter.setCalledBySolutionInstallHms(true);
                    availableAdapter.startResolution(m15281c, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.adapter.BaseAdapter.BaseRequestResultCallback.1
                        @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                        public void onComplete(int i) {
                            HMSLog.m14110i("BaseAdapter", "complete handleSolutionForHMS, result: " + i);
                            if (i == 0) {
                                HMSPackageManager.getInstance(BaseAdapter.this.f10930i).resetMultiServiceState();
                                BaseAdapter baseAdapter = BaseAdapter.this;
                                baseCallBack.onError(baseAdapter.m15310a(11, baseAdapter.m15311a(11)).toJson());
                                BaseAdapter.this.m15267i();
                                return;
                            }
                            BaseAdapter baseAdapter2 = BaseAdapter.this;
                            baseCallBack.onError(baseAdapter2.m15310a(i, baseAdapter2.m15311a(i)).toJson());
                        }
                    });
                    return;
                }
                HMSLog.m14112e("BaseAdapter", "activity is null");
                try {
                    if (BaseAdapter.this.f10930i != null && AvailableUtil.isInstallerLibExist(BaseAdapter.this.f10930i)) {
                        HMSLog.m14110i("BaseAdapter", "pass installHMS intent");
                        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(BaseAdapter.this.f10930i, UpdateAdapter.class.getName());
                        intentStartBridgeActivity.putExtra("update_version", 10000000);
                        intentStartBridgeActivity.putExtra("installHMS", "installHMS");
                        coreBaseResponse.setIntent(intentStartBridgeActivity);
                        BaseAdapter.this.m15302a(baseCallBack, coreBaseResponse);
                    } else {
                        HMSLog.m14110i("BaseAdapter", "pass ACTIVITY_NULL error");
                        BaseAdapter baseAdapter = BaseAdapter.this;
                        baseCallBack.onError(baseAdapter.m15310a(-3, baseAdapter.m15311a(-3)).toJson());
                    }
                    return;
                } catch (RuntimeException unused) {
                    HMSLog.m14112e("BaseAdapter", "handleSolutionForHms pass result failed");
                    return;
                }
            }
            HMSLog.m14110i("BaseAdapter", "handleSolutionForHms: no Available lib exist");
            baseCallBack.onError(BaseAdapter.this.m15288b(-9));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.adapter.BaseAdapter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C4844a extends PendingResultImpl<ResolveResult<CoreBaseResponse>, CoreBaseResponse> {
        public C4844a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public ResolveResult<CoreBaseResponse> onComplete(CoreBaseResponse coreBaseResponse) {
            ResolveResult<CoreBaseResponse> resolveResult = new ResolveResult<>(coreBaseResponse);
            resolveResult.setStatus(Status.SUCCESS);
            return resolveResult;
        }
    }

    public BaseAdapter(ApiClient apiClient) {
        this.f10922a = new WeakReference<>(apiClient);
        this.f10930i = apiClient.getContext().getApplicationContext();
        HMSLog.m14110i("BaseAdapter", "In constructor, WeakReference is " + this.f10922a);
    }

    /* renamed from: h */
    private void m15268h() {
        if (this.f10925d == null || this.f10928g == null) {
            return;
        }
        this.f10932k = null;
        this.f10932k = new ResponseHeader();
        baseRequest(m15276d(), m15274e(), m15272f(), m15312a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m15267i() {
        if (this.f10930i == null) {
            HMSLog.m14112e("BaseAdapter", "sendBroadcastAfterResolutionHms, context is null");
            return;
        }
        Intent intent = new Intent("com.huawei.hms.core.action.SESSION_INVALID");
        try {
            intent.setPackage(this.f10930i.getPackageName());
            this.f10930i.sendBroadcast(intent);
        } catch (IllegalArgumentException unused) {
            HMSLog.m14112e("BaseAdapter", "IllegalArgumentException when sendBroadcastAfterResolutionHms intent.setPackage");
        }
    }

    public void baseRequest(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        m15290a(str, str2, parcelable, baseCallBack);
        if (this.f10922a == null) {
            HMSLog.m14112e("BaseAdapter", "client is null");
            baseCallBack.onError(m15288b(-2));
            return;
        }
        this.f10924c = baseCallBack;
        JsonUtil.jsonToEntity(str, this.f10931j);
        CoreBaseRequest coreBaseRequest = new CoreBaseRequest();
        coreBaseRequest.setJsonObject(str2);
        coreBaseRequest.setJsonHeader(str);
        coreBaseRequest.setParcelable(parcelable);
        String apiName = this.f10931j.getApiName();
        if (TextUtils.isEmpty(apiName)) {
            HMSLog.m14112e("BaseAdapter", "get uri null");
            baseCallBack.onError(m15288b(-5));
            return;
        }
        String transactionId = this.f10931j.getTransactionId();
        this.f10929h = transactionId;
        if (TextUtils.isEmpty(transactionId)) {
            HMSLog.m14112e("BaseAdapter", "get transactionId null");
            baseCallBack.onError(m15288b(-6));
            return;
        }
        HMSLog.m14110i("BaseAdapter", "in baseRequest + uri is :" + apiName + ", transactionId is : " + this.f10929h);
        m15308a(this.f10930i, this.f10931j);
        m15292a(this.f10922a.get(), apiName, coreBaseRequest).setResultCallback(new BaseRequestResultCallback());
    }

    /* renamed from: d */
    private String m15276d() {
        return this.f10925d;
    }

    /* renamed from: e */
    private String m15274e() {
        return this.f10926e;
    }

    /* renamed from: f */
    private Parcelable m15272f() {
        return this.f10927f;
    }

    /* renamed from: g */
    private void m15270g() {
        this.f10933l = new SystemObserver() { // from class: com.huawei.hms.adapter.BaseAdapter.2
            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onNoticeResult(int i) {
                return false;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onSolutionResult(Intent intent, String str) {
                if (!TextUtils.isEmpty(str)) {
                    if (str.equals(BaseAdapter.this.f10929h)) {
                        HMSLog.m14110i("BaseAdapter", "onSolutionResult + id is :" + str);
                        BaseCallBack m15289b = BaseAdapter.this.m15289b();
                        if (m15289b == null) {
                            HMSLog.m14112e("BaseAdapter", "onResult baseCallBack null");
                            return true;
                        } else if (intent != null) {
                            if (BaseAdapter.this.m15286b(intent, m15289b) || BaseAdapter.this.m15305a(intent, m15289b)) {
                                return true;
                            }
                            HMSLog.m14112e("BaseAdapter", "onComplete for on activity result");
                            BaseAdapter.this.m15279c(intent, m15289b);
                            return true;
                        } else {
                            HMSLog.m14112e("BaseAdapter", "onSolutionResult but data is null");
                            String m15288b = BaseAdapter.this.m15288b(-7);
                            BaseAdapter baseAdapter = BaseAdapter.this;
                            baseAdapter.m15306a(baseAdapter.f10930i, BaseAdapter.this.f10932k, 0L);
                            m15289b.onError(m15288b);
                            return true;
                        }
                    }
                    return false;
                }
                HMSLog.m14112e("BaseAdapter", "onSolutionResult but id is null");
                BaseCallBack m15289b2 = BaseAdapter.this.m15289b();
                if (m15289b2 != null) {
                    m15289b2.onError(BaseAdapter.this.m15288b(-6));
                    return true;
                }
                HMSLog.m14112e("BaseAdapter", "onSolutionResult baseCallBack null");
                return true;
            }

            @Override // com.huawei.hms.adapter.sysobs.SystemObserver
            public boolean onUpdateResult(int i) {
                return false;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public Activity m15281c() {
        if (this.f10923b == null) {
            HMSLog.m14110i("BaseAdapter", "activityWeakReference is " + this.f10923b);
            return null;
        }
        ApiClient apiClient = this.f10922a.get();
        if (apiClient == null) {
            HMSLog.m14110i("BaseAdapter", "tmpApi is null");
            return null;
        }
        HMSLog.m14110i("BaseAdapter", "activityWeakReference has " + this.f10923b.get());
        return Util.getActiveActivity(this.f10923b.get(), apiClient.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public BaseCallBack m15289b() {
        BaseCallBack baseCallBack = this.f10924c;
        if (baseCallBack == null) {
            HMSLog.m14112e("BaseAdapter", "callback null");
            return null;
        }
        return baseCallBack;
    }

    /* renamed from: b */
    private void m15287b(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put("direction", "req");
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_START_RESOLUTION", mapFromRequestHeader);
    }

    /* renamed from: a */
    private PendingResult<ResolveResult<CoreBaseResponse>> m15292a(ApiClient apiClient, String str, CoreBaseRequest coreBaseRequest) {
        return new C4844a(apiClient, str, coreBaseRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15302a(BaseCallBack baseCallBack, CoreBaseResponse coreBaseResponse) {
        HMSLog.m14110i("BaseAdapter", "baseCallBack.onComplete");
        PendingIntent pendingIntent = coreBaseResponse.getPendingIntent();
        if (pendingIntent != null) {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), pendingIntent);
            return;
        }
        Intent modifyIntentBehaviorsSafe = IntentUtil.modifyIntentBehaviorsSafe(coreBaseResponse.getIntent());
        if (modifyIntentBehaviorsSafe != null) {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), modifyIntentBehaviorsSafe);
        } else {
            baseCallBack.onComplete(coreBaseResponse.getJsonHeader(), coreBaseResponse.getJsonBody(), null);
        }
    }

    public BaseAdapter(ApiClient apiClient, Activity activity) {
        this.f10922a = new WeakReference<>(apiClient);
        this.f10923b = new WeakReference<>(activity);
        this.f10930i = activity.getApplicationContext();
        HMSLog.m14110i("BaseAdapter", "In constructor, activityWeakReference is " + this.f10923b + ", activity is " + this.f10923b.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public String m15288b(int i) {
        m15280c(i);
        return this.f10932k.toJson();
    }

    /* renamed from: c */
    private void m15280c(int i) {
        this.f10932k.setTransactionId(this.f10931j.getTransactionId());
        this.f10932k.setAppID(this.f10931j.getAppID());
        this.f10932k.setApiName(this.f10931j.getApiName());
        this.f10932k.setSrvName(this.f10931j.getSrvName());
        this.f10932k.setPkgName(this.f10931j.getPkgName());
        this.f10932k.setStatusCode(1);
        this.f10932k.setErrorCode(i);
        this.f10932k.setErrorReason("Core error");
    }

    /* renamed from: b */
    private void m15282b(String str) {
        this.f10926e = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m15286b(Intent intent, BaseCallBack baseCallBack) {
        if (intent.hasExtra("kit_update_result")) {
            int intExtra = intent.getIntExtra("kit_update_result", 0);
            HMSLog.m14110i("BaseAdapter", "kit_update_result is " + intExtra);
            if (intExtra == 1) {
                HMSLog.m14112e("BaseAdapter", "kit update success,replay request");
                m15268h();
            } else {
                HMSLog.m14112e("BaseAdapter", "kit update failed");
                baseCallBack.onError(m15310a(-10, m15311a(intExtra)).toJson());
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m15279c(Intent intent, BaseCallBack baseCallBack) {
        long j;
        String stringExtra = intent.getStringExtra("json_header");
        String stringExtra2 = intent.getStringExtra("json_body");
        Object infoFromJsonobject = JsonUtil.getInfoFromJsonobject(stringExtra, "status_code");
        Object infoFromJsonobject2 = JsonUtil.getInfoFromJsonobject(stringExtra, "error_code");
        if (intent.hasExtra("HMS_FOREGROUND_RES_UI")) {
            Object infoFromJsonobject3 = JsonUtil.getInfoFromJsonobject(intent.getStringExtra("HMS_FOREGROUND_RES_UI"), "uiDuration");
            if (infoFromJsonobject3 instanceof Long) {
                j = ((Long) infoFromJsonobject3).longValue();
                if (!(infoFromJsonobject instanceof Integer) && (infoFromJsonobject2 instanceof Integer)) {
                    int intValue = ((Integer) infoFromJsonobject).intValue();
                    m15288b(((Integer) infoFromJsonobject2).intValue());
                    this.f10932k.setStatusCode(intValue);
                    m15306a(this.f10930i, this.f10932k, j);
                } else {
                    m15288b(-8);
                    m15306a(this.f10930i, this.f10932k, j);
                }
                baseCallBack.onComplete(stringExtra, stringExtra2, null);
            }
        }
        j = 0;
        if (!(infoFromJsonobject instanceof Integer)) {
        }
        m15288b(-8);
        m15306a(this.f10930i, this.f10932k, j);
        baseCallBack.onComplete(stringExtra, stringExtra2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15309a(Activity activity, Parcelable parcelable, CoreBaseResponse coreBaseResponse) {
        HMSLog.m14110i("BaseAdapter", "startResolution");
        RequestHeader requestHeader = this.f10931j;
        if (requestHeader != null) {
            m15287b(this.f10930i, requestHeader);
        }
        if (this.f10933l == null) {
            m15270g();
        }
        SystemManager.getSystemNotifier().registerObserver(this.f10933l);
        if (Build.VERSION.SDK_INT >= 29) {
            m15293a(coreBaseResponse);
        }
        Intent intentStartBridgeActivity = BridgeActivity.getIntentStartBridgeActivity(activity, BaseResolutionAdapter.class.getName());
        Bundle bundle = new Bundle();
        bundle.putParcelable("resolution", parcelable);
        intentStartBridgeActivity.putExtras(bundle);
        intentStartBridgeActivity.putExtra("transaction_id", this.f10929h);
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        intentStartBridgeActivity.putExtra("resolution_flag", time);
        ResolutionFlagUtil.getInstance().saveResolutionFlag(this.f10929h, time);
        activity.startActivity(intentStartBridgeActivity);
    }

    /* renamed from: a */
    private void m15293a(final CoreBaseResponse coreBaseResponse) {
        HMSLog.m14110i("BaseAdapter", "postResolutionTimeoutHandle");
        ApkResolutionFailedManager.getInstance().postTask(this.f10929h, new Runnable() { // from class: com.huawei.hms.adapter.BaseAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                HMSLog.m14110i("BaseAdapter", "postResolutionTimeoutHandle handle");
                SystemManager.getSystemNotifier().unRegisterObserver(BaseAdapter.this.f10933l);
                ApkResolutionFailedManager.getInstance().removeValueOnly(BaseAdapter.this.f10929h);
                BaseCallBack m15289b = BaseAdapter.this.m15289b();
                if (m15289b != null) {
                    BaseAdapter.this.m15302a(m15289b, coreBaseResponse);
                } else {
                    HMSLog.m14112e("BaseAdapter", "timeoutRunnable callBack is null");
                }
            }
        });
    }

    /* renamed from: a */
    private void m15308a(Context context, RequestHeader requestHeader) {
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getInstance().getMapFromRequestHeader(requestHeader);
        mapFromRequestHeader.put("direction", "req");
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(requestHeader.getKitSdkVersion())));
        mapFromRequestHeader.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_CALL_AIDL", mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15307a(Context context, ResponseHeader responseHeader) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put("direction", "rsp");
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f10931j.getKitSdkVersion())));
        mapFromRequestHeader.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_CALL_AIDL", mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15306a(Context context, ResponseHeader responseHeader, long j) {
        HiAnalyticsUtil.getInstance();
        Map<String, String> mapFromRequestHeader = HiAnalyticsUtil.getMapFromRequestHeader(responseHeader);
        mapFromRequestHeader.put("direction", "rsp");
        mapFromRequestHeader.put("waitTime", String.valueOf(j));
        mapFromRequestHeader.put("version", HiAnalyticsUtil.versionCodeToName(String.valueOf(this.f10931j.getKitSdkVersion())));
        mapFromRequestHeader.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
        HiAnalyticsUtil.getInstance().onNewEvent(context, "HMS_SDK_BASE_START_RESOLUTION", mapFromRequestHeader);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public ResponseWrap m15310a(int i, String str) {
        m15280c(i);
        ResponseWrap responseWrap = new ResponseWrap(this.f10932k);
        responseWrap.setBody(str);
        return responseWrap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m15311a(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
        } catch (JSONException e) {
            HMSLog.m14112e("BaseAdapter", "buildBodyStr failed: " + e.getMessage());
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    private void m15291a(String str) {
        this.f10925d = str;
    }

    /* renamed from: a */
    private void m15304a(Parcelable parcelable) {
        this.f10927f = parcelable;
    }

    /* renamed from: a */
    private BaseCallBack m15312a() {
        return this.f10928g;
    }

    /* renamed from: a */
    private void m15303a(BaseCallBack baseCallBack) {
        this.f10928g = baseCallBack;
    }

    /* renamed from: a */
    private void m15290a(String str, String str2, Parcelable parcelable, BaseCallBack baseCallBack) {
        m15291a(str);
        m15282b(str2);
        m15304a(parcelable);
        m15303a(baseCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m15305a(Intent intent, BaseCallBack baseCallBack) {
        if (intent.hasExtra("privacy_statement_confirm_result")) {
            int intExtra = intent.getIntExtra("privacy_statement_confirm_result", 1001);
            if (intExtra == 1001) {
                HMSLog.m14110i("BaseAdapter", "privacy_statement_confirm_result agreed: " + intExtra + ", replay request");
                m15268h();
                return true;
            }
            HMSLog.m14110i("BaseAdapter", "privacy_statement_confirm_result rejected: " + intExtra);
            baseCallBack.onError(m15310a(907135705, m15311a(907135705)).toJson());
            return true;
        }
        return false;
    }
}
