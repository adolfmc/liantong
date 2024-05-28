package com.heytap.mcssdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.heytap.mcssdk.p203a.C4712a;
import com.heytap.mcssdk.p204b.C4715a;
import com.heytap.mcssdk.p205c.C4719a;
import com.heytap.mcssdk.p205c.C4720b;
import com.heytap.mcssdk.p207e.C4733a;
import com.heytap.mcssdk.p207e.C4734b;
import com.heytap.mcssdk.p207e.InterfaceC4736d;
import com.heytap.mcssdk.p208f.C4737a;
import com.heytap.mcssdk.p208f.C4739b;
import com.heytap.mcssdk.p208f.InterfaceC4741c;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.mcssdk.utils.StatUtil;
import com.heytap.mcssdk.utils.Utils;
import com.heytap.msp.push.callback.ICallBackResultService;
import com.heytap.msp.push.callback.IGetAppNotificationCallBackService;
import com.heytap.msp.push.callback.ISetAppNotificationCallBackService;
import com.heytap.msp.push.mode.MessageStat;
import com.heytap.msp.push.statis.StatisticUtils;
import com.mcs.aidl.IMcsSdkService;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushService implements InterfaceC4714b {
    private static final int ANDROID_T_SDK_VERSION_CODE = 32;
    private static final String APP_PACKAGE = "appPackage";
    private static final String APP_VERSION_CODE = "versionCode";
    private static final String APP_VERSION_NAME = "versionName";
    private static final int DEFAULT_API_MAX_COUNT = 2;
    private static final String EVENT_ID = "eventID";
    private static final String EXTRA = "extra";
    private static final String GLOBAL_ID = "globalID";
    private static final int MAX_HOUR_IN_DAY = 23;
    private static final int MAX_MIN_IN_HOUR = 59;
    private static final int MCS_SUPPORT_VERSION = 1019;
    private static final String MESSAGE_ID = "messageID";
    private static final String MESSAGE_TYPE = "messageType";
    public static final String MINI_PROGRAM_PKG = "miniProgramPkg";
    private static final String NEW_MCS_RECEIVE_SDK_ACTION_Base64 = "Y29tLm1jcy5hY3Rpb24uUkVDRUlWRV9TREtfTUVTU0FHRQ==";
    private static final String PUSH_SDK_VERSION = "pushSdkVersion";
    private static final int SDK_INT_24 = 24;
    private static final String SUPPORT_OPEN_PUSH = "supportOpenPush";
    private static final int SYSTEM_UID = 1000;
    private static final String TAG = "PushService";
    private static final String TASK_ID = "taskID";
    private static final String TYPE = "type";
    private static boolean sIsNewMcsPkg;
    private static String sMcsPkgName;
    private ConcurrentHashMap<Integer, C4719a> mAppLimitMap;
    private String mAuthCode;
    private Context mContext;
    private ICallBackResultService mICallBackResultService;
    private IGetAppNotificationCallBackService mIGetAppNotificationCallBackService;
    private ISetAppNotificationCallBackService mISetAppNotificationCallBackService;
    private List<InterfaceC4736d> mParsers;
    private List<InterfaceC4741c> mProcessors;
    private String mRegisterID;
    private String mVerifyCode;
    private boolean needStaticRegister;
    private static final int[] OLD_MCS_PACKAGE = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115};
    private static final int[] OLD_MCS_RECEIVE_SDK_ACTION = {99, 111, 109, 46, 99, 111, 108, 111, 114, 111, 115, 46, 109, 99, 115, 115, 100, 107, 46, 97, 99, 116, 105, 111, 110, 46, 82, 69, 67, 69, 73, 86, 69, 95, 83, 68, 75, 95, 77, 69, 83, 83, 65, 71, 69};
    private static final int[] NEW_MCS_PACKAGE = {99, 111, 109, 46, 104, 101, 121, 116, 97, 112, 46, 109, 99, 115};
    private static String NEW_MCS_RECEIVE_SDK_ACTION = "";
    private static int sCount = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.heytap.mcssdk.PushService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4709a {

        /* renamed from: a */
        private static final PushService f10542a = new PushService();

        private C4709a() {
        }
    }

    private PushService() {
        this.mProcessors = new ArrayList();
        this.mParsers = new ArrayList();
        this.mRegisterID = null;
        this.needStaticRegister = true;
        synchronized (PushService.class) {
            if (sCount > 0) {
                throw new RuntimeException("PushService can't create again!");
            }
            sCount++;
        }
        addParser(new C4734b());
        addParser(new C4733a());
        addProcessor(new C4739b());
        addProcessor(new C4737a());
        this.mAppLimitMap = new ConcurrentHashMap<>();
    }

    private C4719a addCommandToMap(int i) {
        String str;
        if (!this.mAppLimitMap.containsKey(Integer.valueOf(i))) {
            C4719a c4719a = new C4719a(System.currentTimeMillis(), 1);
            this.mAppLimitMap.put(Integer.valueOf(i), c4719a);
            C4746d.m15494b("addCommandToMap :appBean is null");
            return c4719a;
        }
        C4719a c4719a2 = this.mAppLimitMap.get(Integer.valueOf(i));
        if (checkTimeNeedUpdate(c4719a2)) {
            c4719a2.m15572a(1);
            c4719a2.m15571a(System.currentTimeMillis());
            str = "addCommandToMap : appLimitBean.setCount(1)";
        } else {
            c4719a2.m15572a(c4719a2.m15570b() + 1);
            str = "addCommandToMap :appLimitBean.getCount() + 1";
        }
        C4746d.m15494b(str);
        return c4719a2;
    }

    private synchronized void addParser(InterfaceC4736d interfaceC4736d) {
        if (interfaceC4736d != null) {
            this.mParsers.add(interfaceC4736d);
        }
    }

    private synchronized void addProcessor(InterfaceC4741c interfaceC4741c) {
        if (interfaceC4741c != null) {
            this.mProcessors.add(interfaceC4741c);
        }
    }

    private boolean checkAll() {
        return checkContext() && checkRegisterID();
    }

    private boolean checkContext() {
        return this.mContext != null;
    }

    private boolean checkRegisterID() {
        return this.mRegisterID != null;
    }

    private boolean checkTimeNeedUpdate(C4719a c4719a) {
        long m15573a = c4719a.m15573a();
        long currentTimeMillis = System.currentTimeMillis();
        C4746d.m15494b("checkTimeNeedUpdate : lastedTime " + m15573a + " currentTime:" + currentTimeMillis);
        return currentTimeMillis - m15573a > 1000;
    }

    public static PushService getInstance() {
        return C4709a.f10542a;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0068, code lost:
        if ((r5 instanceof org.json.JSONObject) == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0085, code lost:
        if ((r5 instanceof org.json.JSONObject) != false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0087, code lost:
        r5 = r5.toString();
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x008c, code lost:
        r5 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0092, code lost:
        r0.putExtra(r7, r5);
        r0.putExtra("params", r6);
        r0.putExtra("appPackage", r4.mContext.getPackageName());
        r0.putExtra("appKey", r4.mAuthCode);
        r0.putExtra("appSecret", r4.mVerifyCode);
        r0.putExtra("registerID", r4.mRegisterID);
        r0.putExtra("sdkVersion", getSDKVersionName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00c3, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.Intent getIntent(int r5, java.lang.String r6, org.json.JSONObject r7) {
        /*
            r4 = this;
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.content.Context r1 = r4.mContext
            java.lang.String r1 = r4.getReceiveSdkAction(r1)
            r0.setAction(r1)
            android.content.Context r1 = r4.mContext
            java.lang.String r1 = r4.getMcsPackageName(r1)
            r0.setPackage(r1)
            java.lang.String r1 = "type"
            r0.putExtra(r1, r5)
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            java.lang.String r1 = "versionName"
            android.content.Context r2 = r4.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            android.content.Context r3 = r4.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.String r2 = com.heytap.mcssdk.utils.Utils.getVersionName(r2, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            r5.putOpt(r1, r2)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.String r1 = "versionCode"
            android.content.Context r2 = r4.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            android.content.Context r3 = r4.mContext     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.String r3 = r3.getPackageName()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            int r2 = com.heytap.mcssdk.utils.Utils.getVersionCode(r2, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            r5.putOpt(r1, r2)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            if (r7 == 0) goto L64
            java.util.Iterator r1 = r7.keys()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
        L50:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            if (r2 == 0) goto L64
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            java.lang.Object r3 = r7.get(r2)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            r5.putOpt(r2, r3)     // Catch: java.lang.Throwable -> L6b java.lang.Exception -> L81
            goto L50
        L64:
            java.lang.String r7 = "extra"
            boolean r1 = r5 instanceof org.json.JSONObject
            if (r1 != 0) goto L8c
            goto L87
        L6b:
            r6 = move-exception
            boolean r7 = r5 instanceof org.json.JSONObject
            if (r7 != 0) goto L75
            java.lang.String r5 = r5.toString()
            goto L7b
        L75:
            org.json.JSONObject r5 = (org.json.JSONObject) r5
            java.lang.String r5 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r5)
        L7b:
            java.lang.String r7 = "extra"
            r0.putExtra(r7, r5)
            throw r6
        L81:
            java.lang.String r7 = "extra"
            boolean r1 = r5 instanceof org.json.JSONObject
            if (r1 != 0) goto L8c
        L87:
            java.lang.String r5 = r5.toString()
            goto L92
        L8c:
            org.json.JSONObject r5 = (org.json.JSONObject) r5
            java.lang.String r5 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r5)
        L92:
            r0.putExtra(r7, r5)
            java.lang.String r5 = "params"
            r0.putExtra(r5, r6)
            java.lang.String r5 = "appPackage"
            android.content.Context r6 = r4.mContext
            java.lang.String r6 = r6.getPackageName()
            r0.putExtra(r5, r6)
            java.lang.String r5 = "appKey"
            java.lang.String r6 = r4.mAuthCode
            r0.putExtra(r5, r6)
            java.lang.String r5 = "appSecret"
            java.lang.String r6 = r4.mVerifyCode
            r0.putExtra(r5, r6)
            java.lang.String r5 = "registerID"
            java.lang.String r6 = r4.mRegisterID
            r0.putExtra(r5, r6)
            java.lang.String r5 = "sdkVersion"
            java.lang.String r6 = getSDKVersionName()
            r0.putExtra(r5, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.mcssdk.PushService.getIntent(int, java.lang.String, org.json.JSONObject):android.content.Intent");
    }

    private String getMcsPackageNameInner(Context context) {
        C4746d.m15493b(TAG, "getMcsPackageNameInner -- ");
        if (Build.VERSION.SDK_INT >= 24) {
            PackageManager packageManager = context.getPackageManager();
            try {
                try {
                    String string = Utils.getString(NEW_MCS_PACKAGE);
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(string, 0);
                    if (applicationInfo != null) {
                        boolean z = (applicationInfo.flags & 1) == 1;
                        boolean z2 = packageManager.getPackageUid(string, 0) == packageManager.getPackageUid("android", 0);
                        if (z || z2) {
                            return string;
                        }
                    }
                    return null;
                } catch (PackageManager.NameNotFoundException e) {
                    String str = TAG;
                    C4746d.m15481e(str, "NameNotFoundException in get mcs package name:" + e.getMessage());
                } catch (Exception e2) {
                    String str2 = TAG;
                    C4746d.m15481e(str2, "Error in get mcs package name:" + e2.getMessage());
                    return null;
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static int getSDKVersionCode() {
        return 3400;
    }

    public static String getSDKVersionName() {
        return "3.4.0";
    }

    private boolean isSupportPushInner(Context context) {
        if (this.mContext == null) {
            this.mContext = context.getApplicationContext();
        }
        String mcsPackageName = getMcsPackageName(this.mContext);
        boolean z = Utils.isExistPackage(this.mContext, mcsPackageName) && Utils.getVersionCode(this.mContext, mcsPackageName) >= 1019 && Utils.isSupportPush(this.mContext, mcsPackageName, SUPPORT_OPEN_PUSH);
        String str = TAG;
        C4746d.m15493b(str, "isSupportPushInner -- " + z);
        return z;
    }

    @Deprecated
    private static void onAppStart(Context context) {
        StatUtil.statisticMessage(context, new MessageStat(context.getPackageName(), "app_start", null));
    }

    private void startMcsService(int i, String str, JSONObject jSONObject) {
        if (checkCommandLimit(i)) {
            if (this.mICallBackResultService != null) {
                this.mICallBackResultService.onError(getErrorCode(i), "api_call_too_frequently", this.mContext.getPackageName(), getMiniProgramPkgFromJSON(jSONObject));
                return;
            }
            return;
        }
        try {
            this.mContext.startService(getIntent(i, str, jSONObject));
        } catch (Exception e) {
            C4746d.m15482e("startMcsService--Exception" + e.getMessage());
        }
    }

    private void startMcsService(int i, JSONObject jSONObject) {
        startMcsService(i, "", jSONObject);
    }

    public void bindMcsService(int i) {
        if (!checkCommandLimit(i)) {
            final Intent intent = getIntent(i, "", null);
            this.mContext.bindService(intent, new ServiceConnection() { // from class: com.heytap.mcssdk.PushService.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Bundle bundle = new Bundle();
                    bundle.putAll(intent.getExtras());
                    try {
                        IMcsSdkService.Stub.asInterface(iBinder).process(bundle);
                    } catch (Exception e) {
                        C4746d.m15494b("bindMcsService exception:" + e);
                    }
                    PushService.this.mContext.unbindService(this);
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                }
            }, 1);
            return;
        }
        ICallBackResultService iCallBackResultService = this.mICallBackResultService;
        if (iCallBackResultService != null) {
            iCallBackResultService.onError(getErrorCode(i), "api_call_too_frequently", this.mContext.getPackageName(), "");
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void cancelNotification(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12319, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    public boolean checkCommandLimit(int i) {
        return (i == 12291 || i == 12312 || addCommandToMap(i).m15570b() <= 2) ? false : true;
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void clearNotificationType() {
        clearNotificationType(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void clearNotificationType(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12308, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void clearNotifications() {
        clearNotifications(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void clearNotifications(JSONObject jSONObject) {
        if (checkContext()) {
            startMcsService(12311, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void disableAppNotificationSwitch(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        if (checkContext()) {
            this.mISetAppNotificationCallBackService = iSetAppNotificationCallBackService;
            startMcsService(12317, null);
        } else if (getPushCallback() != null) {
            this.mISetAppNotificationCallBackService.onSetAppNotificationSwitch(-2);
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void enableAppNotificationSwitch(ISetAppNotificationCallBackService iSetAppNotificationCallBackService) {
        if (checkContext()) {
            this.mISetAppNotificationCallBackService = iSetAppNotificationCallBackService;
            startMcsService(12316, null);
            return;
        }
        ISetAppNotificationCallBackService iSetAppNotificationCallBackService2 = this.mISetAppNotificationCallBackService;
        if (iSetAppNotificationCallBackService2 != null) {
            iSetAppNotificationCallBackService2.onSetAppNotificationSwitch(-2);
        }
    }

    public Map<Integer, C4719a> getAppLimitMap() {
        return this.mAppLimitMap;
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void getAppNotificationSwitch(IGetAppNotificationCallBackService iGetAppNotificationCallBackService) {
        if (checkContext()) {
            this.mIGetAppNotificationCallBackService = iGetAppNotificationCallBackService;
            startMcsService(12318, null);
            return;
        }
        IGetAppNotificationCallBackService iGetAppNotificationCallBackService2 = this.mIGetAppNotificationCallBackService;
        if (iGetAppNotificationCallBackService2 != null) {
            iGetAppNotificationCallBackService2.onGetAppNotificationSwitch(-2, 0);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getErrorCode(int i) {
        switch (i) {
            case 12289:
                return -1;
            case 12290:
                return -2;
            case 12291:
                return -14;
            default:
                switch (i) {
                    case 12298:
                        return -11;
                    case 12299:
                        return -3;
                    case 12300:
                        return -4;
                    default:
                        switch (i) {
                            case 12306:
                                return -10;
                            case 12307:
                                return -6;
                            case 12308:
                                return -7;
                            case 12309:
                                return -5;
                            case 12310:
                                return -8;
                            case 12311:
                                return -9;
                            case 12312:
                                return -13;
                            case 12313:
                                return -12;
                            default:
                                switch (i) {
                                    case 12316:
                                        return -15;
                                    case 12317:
                                        return -16;
                                    case 12318:
                                        return -17;
                                    default:
                                        return 0;
                                }
                        }
                }
        }
    }

    public String getMcsPackageName(Context context) {
        boolean z;
        if (sMcsPkgName == null) {
            String mcsPackageNameInner = getMcsPackageNameInner(context);
            if (mcsPackageNameInner == null) {
                sMcsPkgName = Utils.getString(OLD_MCS_PACKAGE);
                z = false;
            } else {
                sMcsPkgName = mcsPackageNameInner;
                z = true;
            }
            sIsNewMcsPkg = z;
        }
        return sMcsPkgName;
    }

    public String getMiniProgramPkgFromJSON(JSONObject jSONObject) {
        if (jSONObject == null) {
            return "";
        }
        try {
            try {
                return jSONObject.optString("miniProgramPkg");
            } catch (Exception e) {
                C4746d.m15494b("Error happened in getMiniProgramPkgFromJSON() :" + e.getMessage());
                return "";
            }
        } catch (Throwable unused) {
            return "";
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void getNotificationStatus() {
        getNotificationStatus(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void getNotificationStatus(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12309, jSONObject);
        } else if (getPushCallback() != null) {
            getPushCallback().onGetNotificationStatus(-2, 0);
        }
    }

    public List<InterfaceC4736d> getParsers() {
        return this.mParsers;
    }

    public List<InterfaceC4741c> getProcessors() {
        return this.mProcessors;
    }

    public ICallBackResultService getPushCallback() {
        return this.mICallBackResultService;
    }

    public IGetAppNotificationCallBackService getPushGetAppNotificationCallBack() {
        return this.mIGetAppNotificationCallBackService;
    }

    public ISetAppNotificationCallBackService getPushSetAppNotificationCallBack() {
        return this.mISetAppNotificationCallBackService;
    }

    public void getPushStatus() {
        if (checkAll()) {
            startMcsService(12306, null);
        } else if (getPushCallback() != null) {
            getPushCallback().onGetPushStatus(-2, 0);
        }
    }

    public int getPushVersionCode() {
        if (checkContext()) {
            Context context = this.mContext;
            return Utils.getVersionCode(context, getMcsPackageName(context));
        }
        return 0;
    }

    public String getPushVersionName() {
        if (checkContext()) {
            Context context = this.mContext;
            return Utils.getVersionName(context, getMcsPackageName(context));
        }
        return "";
    }

    public String getReceiveSdkAction(Context context) {
        if (sMcsPkgName == null) {
            getMcsPackageNameInner(context);
        }
        if (sIsNewMcsPkg) {
            if (TextUtils.isEmpty(NEW_MCS_RECEIVE_SDK_ACTION)) {
                NEW_MCS_RECEIVE_SDK_ACTION = new String(C4712a.m15605b(NEW_MCS_RECEIVE_SDK_ACTION_Base64));
            }
            return NEW_MCS_RECEIVE_SDK_ACTION;
        }
        return Utils.getString(OLD_MCS_RECEIVE_SDK_ACTION);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void getRegister() {
        getRegister(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void getRegister(JSONObject jSONObject) {
        if (checkContext()) {
            startMcsService(12289, jSONObject);
        } else if (getPushCallback() != null) {
            getPushCallback().onRegister(-2, null, null, null);
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public String getRegisterID() {
        return this.mRegisterID;
    }

    public PushService init(Context context, boolean z) {
        if (context != null) {
            innerInit(context);
            new C4715a().m15577a(this.mContext);
            C4746d.m15477f(z);
            return this;
        }
        throw new IllegalArgumentException("context can't be null");
    }

    public void innerInit(Context context) {
        boolean z;
        this.mContext = context.getApplicationContext();
        if (sMcsPkgName == null) {
            String mcsPackageNameInner = getMcsPackageNameInner(context);
            if (mcsPackageNameInner == null) {
                sMcsPkgName = Utils.getString(OLD_MCS_PACKAGE);
                z = false;
            } else {
                sMcsPkgName = mcsPackageNameInner;
                z = true;
            }
            sIsNewMcsPkg = z;
        }
    }

    public boolean isSupportPushByClient(Context context) {
        return isSupportPushInner(context);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void openNotificationSettings() {
        openNotificationSettings(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void openNotificationSettings(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12310, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void pausePush() {
        pausePush(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void pausePush(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12299, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void register(Context context, String str, String str2, ICallBackResultService iCallBackResultService) {
        register(context, str, str2, null, iCallBackResultService);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void register(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        if (context == null) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null, null, null);
                return;
            }
            return;
        }
        if (this.mContext == null) {
            this.mContext = context.getApplicationContext();
        }
        if (!Utils.isSupportPushByClient(this.mContext)) {
            if (iCallBackResultService != null) {
                iCallBackResultService.onRegister(-2, null, null, null);
                return;
            }
            return;
        }
        if (this.needStaticRegister) {
            C4746d.m15493b("registerAction:", "Will static push_register event :");
            StatisticUtils.statisticEvent(this.mContext, "push_register");
            this.needStaticRegister = false;
        }
        this.mAuthCode = str;
        this.mVerifyCode = str2;
        this.mICallBackResultService = iCallBackResultService;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.putOpt("appVersionCode", Integer.valueOf(Utils.getVersionCode(context)));
            jSONObject.putOpt("appVersionName", Utils.getVersionName(context));
        } catch (JSONException e) {
            C4746d.m15482e("register-Exception:" + e.getMessage());
        }
        startMcsService(12289, jSONObject);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT < 32) {
            if (checkContext()) {
                bindMcsService(12313);
                return;
            } else {
                C4746d.m15481e("mcssdk---", "please call the register first!");
                return;
            }
        }
        String str = TAG;
        C4746d.m15493b(str, "requestNotificationPermission() will return due to Android T device , current device Android SDK version code is :" + Build.VERSION.SDK_INT);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void resumePush() {
        resumePush(null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void resumePush(JSONObject jSONObject) {
        if (checkAll()) {
            startMcsService(12300, jSONObject);
        } else {
            C4746d.m15481e("mcssdk---", "please call the register first!");
        }
    }

    public void setAppKeySecret(String str, String str2) {
        this.mAuthCode = str;
        this.mVerifyCode = str2;
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void setNotificationType(int i) {
        setNotificationType(i, null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void setNotificationType(int i, JSONObject jSONObject) {
        if (!checkAll()) {
            C4746d.m15481e("mcssdk---", "please call the register first!");
            return;
        }
        startMcsService(12307, i + "", jSONObject);
    }

    public void setPushCallback(ICallBackResultService iCallBackResultService) {
        this.mICallBackResultService = iCallBackResultService;
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void setPushTime(List<Integer> list, int i, int i2, int i3, int i4) {
        setPushTime(list, i, i2, i3, i4, null);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void setPushTime(List<Integer> list, int i, int i2, int i3, int i4, JSONObject jSONObject) {
        if (!checkAll()) {
            if (getPushCallback() != null) {
                getPushCallback().onSetPushTime(-2, "please call the register first!");
            }
        } else if (list == null || list.size() <= 0 || i < 0 || i2 < 0 || i3 < i || i3 > 23 || i4 < i2 || i4 > 59) {
            throw new IllegalArgumentException("params are not all right,please check params");
        } else {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("weekDays", C4720b.m15566a(list));
                jSONObject2.put("startHour", i);
                jSONObject2.put("startMin", i2);
                jSONObject2.put("endHour", i3);
                jSONObject2.put("endMin", i4);
                startMcsService(12298, !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), jSONObject);
            } catch (JSONException e) {
                C4746d.m15481e("mcssdk---", e.getLocalizedMessage());
            }
        }
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void setRegisterID(String str) {
        this.mRegisterID = str;
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void unRegister() {
        unRegister(null);
    }

    public void unRegister(Context context, String str, String str2, JSONObject jSONObject, ICallBackResultService iCallBackResultService) {
        this.mAuthCode = str;
        this.mVerifyCode = str2;
        this.mContext = context.getApplicationContext();
        this.mICallBackResultService = iCallBackResultService;
        unRegister(jSONObject);
    }

    @Override // com.heytap.mcssdk.InterfaceC4714b
    public void unRegister(JSONObject jSONObject) {
        if (checkContext()) {
            startMcsService(12290, jSONObject);
        } else if (getPushCallback() != null) {
            getPushCallback().onUnRegister(-2, this.mContext.getPackageName(), getMiniProgramPkgFromJSON(jSONObject));
        }
    }
}
