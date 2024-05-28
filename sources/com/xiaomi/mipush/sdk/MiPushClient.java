package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.manager.C11053a;
import com.xiaomi.clientreport.manager.ClientReportClient;
import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.AbstractC11471l;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11157an;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11261cx;
import com.xiaomi.push.C11277dg;
import com.xiaomi.push.C11278dh;
import com.xiaomi.push.C11301dq;
import com.xiaomi.push.C11302dr;
import com.xiaomi.push.C11303ds;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11424gz;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11431hf;
import com.xiaomi.push.C11435hj;
import com.xiaomi.push.C11437hl;
import com.xiaomi.push.C11439hn;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11472m;
import com.xiaomi.push.C11476p;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.EnumC11317ed;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.EnumC11418gt;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11541aj;
import com.xiaomi.push.service.receivers.NetworkStatusReceiver;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class MiPushClient {
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_SET_ACCEPT_TIME = "accept-time";
    public static final String COMMAND_SET_ACCOUNT = "set-account";
    public static final String COMMAND_SET_ALIAS = "set-alias";
    public static final String COMMAND_SUBSCRIBE_TOPIC = "subscribe-topic";
    public static final String COMMAND_UNREGISTER = "unregister";
    public static final String COMMAND_UNSET_ACCOUNT = "unset-account";
    public static final String COMMAND_UNSET_ALIAS = "unset-alias";
    public static final String COMMAND_UNSUBSCRIBE_TOPIC = "unsubscibe-topic";
    public static final String PREF_EXTRA = "mipush_extra";
    private static Context sContext;
    private static long sCurMsgId = System.currentTimeMillis();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ICallbackResult<R> {
        void onResult(R r);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface UPSRegisterCallBack extends ICallbackResult<TokenResult> {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface UPSTurnCallBack extends ICallbackResult<CodeResult> {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface UPSUnRegisterCallBack extends ICallbackResult<TokenResult> {
    }

    @Deprecated
    public static void syncAssembleCOSPushToken(Context context) {
    }

    @Deprecated
    public static void syncAssembleFTOSPushToken(Context context) {
    }

    @Deprecated
    public static void syncAssemblePushToken(Context context) {
    }

    public static boolean shouldUseMIUIPush(Context context) {
        return C11118u.m5003a(context).m5008a();
    }

    public static void registerPush(Context context, String str, String str2) {
        registerPush(context, str, str2, new PushConfiguration());
    }

    public static void registerPush(Context context, String str, String str2, String str3) {
        registerPush(context, str, str2, new PushConfiguration(), str3, null);
    }

    public static void registerPush(Context context, String str, String str2, PushConfiguration pushConfiguration) {
        registerPush(context, str, str2, pushConfiguration, null, null);
    }

    private static void registerPush(Context context, final String str, final String str2, PushConfiguration pushConfiguration, final String str3, final ICallbackResult iCallbackResult) {
        checkNotNull(context, "context");
        checkNotNull(str, "appID");
        checkNotNull(str2, "appToken");
        sContext = context.getApplicationContext();
        if (sContext == null) {
            sContext = context;
        }
        Context context2 = sContext;
        C11479r.m2931a(context2);
        if (!NetworkStatusReceiver.m2410a()) {
            registerNetworkReceiver(sContext);
        }
        C11091e.m5114a(sContext).m5113a(pushConfiguration);
        C11134ae.m4937a(context2).m4928a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.1
            @Override // java.lang.Runnable
            public void run() {
                MiPushClient.initialize(MiPushClient.sContext, str, str2, null, str3, iCallbackResult);
            }
        });
    }

    private static void registerNetworkReceiver(Context context) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addCategory("android.intent.category.DEFAULT");
            C11472m.m2949a(context.getApplicationContext(), new NetworkStatusReceiver(null), intentFilter, 2);
        } catch (Throwable th) {
            AbstractC11049b.m5282a("dynamic register network status receiver failed:" + th);
        }
        C11169au.m4851a(sContext);
    }

    @Deprecated
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback) {
        initialize(context, str, str2, miPushClientCallback, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initialize(Context context, String str, String str2, MiPushClientCallback miPushClientCallback, String str3, ICallbackResult iCallbackResult) {
        try {
            AbstractC11049b.m5287a(context.getApplicationContext());
            AbstractC11049b.m5266e("sdk_version = 5_9_9-C");
            C11157an.m4882a(context).m4883a();
            C11261cx.m4403a(context);
            if (miPushClientCallback != null) {
                PushMessageHandler.m5167a(miPushClientCallback);
            }
            if (iCallbackResult != null) {
                PushMessageHandler.m5168a(iCallbackResult);
            }
            if (C11479r.m2930a(sContext)) {
                C11105m.m5059a(sContext);
            }
            boolean z = C11087b.m5151a(sContext).m5157a() != Constants.m5212a();
            if (!z && !shouldSendRegRequest(sContext)) {
                C11118u.m5003a(sContext).m5009a();
                AbstractC11049b.m5282a("Could not send  register message within 5s repeatly .");
                return;
            }
            if (!z && C11087b.m5151a(sContext).m5147a(str, str2) && !C11087b.m5151a(sContext).m5130f()) {
                if (1 == PushMessageHelper.getPushMode(sContext)) {
                    checkNotNull(miPushClientCallback, "callback");
                    miPushClientCallback.onInitializeResult(0L, null, C11087b.m5151a(sContext).m5138c());
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(C11087b.m5151a(sContext).m5138c());
                    PushMessageHelper.sendCommandMessageBroadcast(sContext, PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_REGISTER.f22058a, arrayList, 0L, null, null, null));
                }
                C11118u.m5003a(sContext).m5009a();
                if (C11087b.m5151a(sContext).m5154a()) {
                    C11430he c11430he = new C11430he();
                    c11430he.m3335b(C11087b.m5151a(sContext).m5156a());
                    c11430he.m3331c(EnumC11414gp.ClientInfoUpdate.f22745a);
                    c11430he.m3344a(C11541aj.m2703a());
                    c11430he.f23010a = new HashMap();
                    c11430he.f23010a.put("app_version", C11395g.m3717a(sContext, sContext.getPackageName()));
                    c11430he.f23010a.put("app_version_code", Integer.toString(C11395g.m3720a(sContext, sContext.getPackageName())));
                    c11430he.f23010a.put("push_sdk_vn", "5_9_9-C");
                    c11430he.f23010a.put("push_sdk_vc", Integer.toString(50909));
                    String m5133e = C11087b.m5151a(sContext).m5133e();
                    if (!TextUtils.isEmpty(m5133e)) {
                        c11430he.f23010a.put("deviceid", m5133e);
                    }
                    C11118u.m5003a(sContext).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null);
                    C11118u.m5003a(sContext).m5002a(sContext);
                }
                if (!AbstractC11471l.m2952a(sContext, "update_devId", false)) {
                    updateImeiOrOaid();
                    AbstractC11471l.m2953a(sContext, "update_devId", true);
                }
                if (shouldUseMIUIPush(sContext) && shouldPullNotification(sContext)) {
                    C11430he c11430he2 = new C11430he();
                    c11430he2.m3335b(C11087b.m5151a(sContext).m5156a());
                    c11430he2.m3331c(EnumC11414gp.PullOfflineMessage.f22745a);
                    c11430he2.m3344a(C11541aj.m2703a());
                    c11430he2.m3340a(false);
                    C11118u.m5003a(sContext).m4985a((C11118u) c11430he2, EnumC11404gf.Notification, false, (C11417gs) null, false);
                    addPullNotificationTime(sContext);
                }
            } else {
                String m4758a = C11184bb.m4758a(6);
                C11087b.m5151a(sContext).m5155a();
                C11087b.m5151a(sContext).m5153a(Constants.m5212a());
                C11087b.m5151a(sContext).m5146a(str, str2, m4758a);
                MiTinyDataClient.C11077a.m5198a().m5188b("com.xiaomi.xmpushsdk.tinydataPending.appId");
                clearExtras(sContext);
                clearNotification(context);
                C11431hf c11431hf = new C11431hf();
                c11431hf.m3305a(C11541aj.m2701b());
                c11431hf.m3300b(str);
                c11431hf.m3289e(str2);
                c11431hf.m3292d(sContext.getPackageName());
                c11431hf.m3286f(m4758a);
                c11431hf.m3295c(C11395g.m3717a(sContext, sContext.getPackageName()));
                c11431hf.m3301b(C11395g.m3720a(sContext, sContext.getPackageName()));
                c11431hf.m3280h("5_9_9-C");
                c11431hf.m3309a(50909);
                c11431hf.m3308a(EnumC11418gt.Init);
                if (!TextUtils.isEmpty(str3)) {
                    c11431hf.m3283g(str3);
                }
                if (!C11469j.m2958d()) {
                    String m3034d = C11455i.m3034d(sContext);
                    if (!TextUtils.isEmpty(m3034d)) {
                        c11431hf.m3277i(C11184bb.m4757a(m3034d) + "," + C11455i.m3032f(sContext));
                    }
                }
                int m3056a = C11455i.m3056a();
                if (m3056a >= 0) {
                    c11431hf.m3296c(m3056a);
                }
                C11118u.m5003a(sContext).m4990a(c11431hf, z);
                sContext.getSharedPreferences("mipush_extra", 4).getBoolean("mipush_registed", true);
            }
            addRegRequestTime(sContext);
            scheduleOcVersionCheckJob();
            scheduleDataCollectionJobs(sContext);
            initEventPerfLogic(sContext);
            C11126w.m4954a(sContext);
            if (!sContext.getPackageName().equals("com.xiaomi.xmsf")) {
                if (Logger.getUserLogger() != null) {
                    Logger.setLogger(sContext, Logger.getUserLogger());
                }
                AbstractC11049b.m5292a(2);
            }
            operateSyncAction(context);
        } catch (Throwable th) {
            AbstractC11049b.m5276a(th);
        }
    }

    private static void scheduleOcVersionCheckJob() {
        C11134ae.m4937a(sContext).m4933a(new C11110o(sContext), C11537ah.m2715a(sContext).m2719a(EnumC11409gk.OcVersionCheckFrequency.m3637a(), 86400), 5);
    }

    private static void scheduleDataCollectionJobs(Context context) {
        if (C11537ah.m2715a(sContext).m2716a(EnumC11409gk.DataCollectionSwitch.m3637a(), getDefaultSwitch())) {
            C11277dg.m4362a().m4361a(new C11101i(context));
            C11134ae.m4937a(sContext).m4927a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.2
                @Override // java.lang.Runnable
                public void run() {
                    C11278dh.m4360a(MiPushClient.sContext);
                }
            }, 10);
        }
    }

    private static boolean getDefaultSwitch() {
        return C11469j.m2965b();
    }

    private static void updateImeiOrOaid() {
        new Thread(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.3
            @Override // java.lang.Runnable
            public void run() {
                String m3037c;
                if (C11469j.m2958d()) {
                    return;
                }
                if (C11455i.m3037c(MiPushClient.sContext) != null || C11157an.m4882a(MiPushClient.sContext).mo4862a()) {
                    C11430he c11430he = new C11430he();
                    c11430he.m3335b(C11087b.m5151a(MiPushClient.sContext).m5156a());
                    c11430he.m3331c(EnumC11414gp.ClientInfoUpdate.f22745a);
                    c11430he.m3344a(C11541aj.m2703a());
                    c11430he.m3341a(new HashMap());
                    String str = "";
                    if (!TextUtils.isEmpty(C11455i.m3037c(MiPushClient.sContext))) {
                        str = "" + C11184bb.m4757a(m3037c);
                    }
                    String m3033e = C11455i.m3033e(MiPushClient.sContext);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(m3033e)) {
                        str = str + "," + m3033e;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        c11430he.m3350a().put("imei_md5", str);
                    }
                    C11157an.m4882a(MiPushClient.sContext).m4880a(c11430he.m3350a());
                    int m3056a = C11455i.m3056a();
                    if (m3056a >= 0) {
                        c11430he.m3350a().put("space_id", Integer.toString(m3056a));
                    }
                    C11118u.m5003a(MiPushClient.sContext).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null);
                }
            }
        }).start();
    }

    public static void awakeApps(final Context context, final String[] strArr) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.mipush.sdk.MiPushClient.4
            @Override // java.lang.Runnable
            public void run() {
                String[] strArr2;
                PackageInfo packageInfo;
                try {
                    for (String str : strArr) {
                        if (!TextUtils.isEmpty(str) && (packageInfo = context.getPackageManager().getPackageInfo(str, 4)) != null) {
                            MiPushClient.awakePushServiceByPackageInfo(context, packageInfo);
                        }
                    }
                } catch (Throwable th) {
                    AbstractC11049b.m5276a(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void awakePushServiceByPackageInfo(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr = packageInfo.services;
        if (serviceInfoArr != null) {
            for (ServiceInfo serviceInfo : serviceInfoArr) {
                if (serviceInfo.exported && serviceInfo.enabled && "com.xiaomi.mipush.sdk.PushMessageHandler".equals(serviceInfo.name) && !context.getPackageName().equals(serviceInfo.packageName)) {
                    try {
                        Thread.sleep(((long) ((Math.random() * 2.0d) + 1.0d)) * 1000);
                        Intent intent = new Intent();
                        intent.setClassName(serviceInfo.packageName, serviceInfo.name);
                        intent.setAction("com.xiaomi.mipush.sdk.WAKEUP");
                        intent.putExtra("waker_pkgname", context.getPackageName());
                        PushMessageHandler.m5175a(context, intent);
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                }
            }
        }
    }

    public static List<String> getAllAlias(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("alias_")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllTopic(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("topic_") && !str.contains("**ALL**")) {
                arrayList.add(str.substring(6));
            }
        }
        return arrayList;
    }

    public static List<String> getAllUserAccount(Context context) {
        ArrayList arrayList = new ArrayList();
        for (String str : context.getSharedPreferences("mipush_extra", 0).getAll().keySet()) {
            if (str.startsWith("account_")) {
                arrayList.add(str.substring(8));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void clearExtras(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.clear();
        edit.commit();
    }

    private static void clearExtrasForInitialize(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        Iterator<String> it = getAllAlias(context).iterator();
        while (it.hasNext()) {
            edit.remove("alias_" + it.next());
        }
        Iterator<String> it2 = getAllUserAccount(context).iterator();
        while (it2.hasNext()) {
            edit.remove("account_" + it2.next());
        }
        Iterator<String> it3 = getAllTopic(context).iterator();
        while (it3.hasNext()) {
            edit.remove("topic_" + it3.next());
        }
        edit.remove("accept_time");
        edit.commit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void reInitialize(Context context, EnumC11418gt enumC11418gt) {
        AbstractC11049b.m5266e("re-register reason: " + enumC11418gt);
        String m4758a = C11184bb.m4758a(6);
        String m5156a = C11087b.m5151a(context).m5156a();
        String m5143b = C11087b.m5151a(context).m5143b();
        C11087b.m5151a(context).m5155a();
        clearExtrasForInitialize(context);
        clearNotification(context);
        C11087b.m5151a(context).m5153a(Constants.m5212a());
        C11087b.m5151a(context).m5146a(m5156a, m5143b, m4758a);
        C11431hf c11431hf = new C11431hf();
        c11431hf.m3305a(C11541aj.m2701b());
        c11431hf.m3300b(m5156a);
        c11431hf.m3289e(m5143b);
        c11431hf.m3286f(m4758a);
        c11431hf.m3292d(context.getPackageName());
        c11431hf.m3295c(C11395g.m3717a(context, context.getPackageName()));
        c11431hf.m3301b(C11395g.m3720a(context, context.getPackageName()));
        c11431hf.m3280h("5_9_9-C");
        c11431hf.m3309a(50909);
        c11431hf.m3308a(enumC11418gt);
        int m3056a = C11455i.m3056a();
        if (m3056a >= 0) {
            c11431hf.m3296c(m3056a);
        }
        C11118u.m5003a(context).m4990a(c11431hf, false);
    }

    @Deprecated
    public static void reportMessageClicked(Context context, String str) {
        reportMessageClicked(context, str, null, null);
    }

    public static void reportMessageClicked(Context context, MiPushMessage miPushMessage) {
        C11417gs c11417gs = new C11417gs();
        c11417gs.m3552a(miPushMessage.getMessageId());
        c11417gs.m3543b(miPushMessage.getTopic());
        c11417gs.m3532d(miPushMessage.getDescription());
        c11417gs.m3536c(miPushMessage.getTitle());
        c11417gs.m3537c(miPushMessage.getNotifyId());
        c11417gs.m3555a(miPushMessage.getNotifyType());
        c11417gs.m3544b(miPushMessage.getPassThrough());
        c11417gs.m3550a(miPushMessage.getExtra());
        reportMessageClicked(context, miPushMessage.getMessageId(), c11417gs, null);
    }

    static void reportMessageClicked(Context context, String str, C11417gs c11417gs, String str2) {
        C11430he c11430he = new C11430he();
        if (TextUtils.isEmpty(str2)) {
            if (C11087b.m5151a(context).m5141b()) {
                c11430he.m3335b(C11087b.m5151a(context).m5156a());
            } else {
                AbstractC11049b.m5268d("do not report clicked message");
                return;
            }
        } else {
            c11430he.m3335b(str2);
        }
        c11430he.m3331c("bar:click");
        c11430he.m3344a(str);
        c11430he.m3340a(false);
        C11118u.m5003a(context).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, c11417gs);
    }

    static void reportIgnoreRegMessageClicked(Context context, String str, C11417gs c11417gs, String str2, String str3) {
        C11430he c11430he = new C11430he();
        if (TextUtils.isEmpty(str3)) {
            AbstractC11049b.m5268d("do not report clicked message");
            return;
        }
        c11430he.m3335b(str3);
        c11430he.m3331c("bar:click");
        c11430he.m3344a(str);
        c11430he.m3340a(false);
        C11118u.m5003a(context).m4983a(c11430he, EnumC11404gf.Notification, false, true, c11417gs, true, str2, str3);
    }

    public static void setLocalNotificationType(Context context, int i) {
        C11118u.m5003a(context).m4972b(i & (-1));
    }

    public static void clearLocalNotificationType(Context context) {
        C11118u.m5003a(context).m4958f();
    }

    public static void unregisterPush(Context context) {
        C11094f.m5082c(context);
        C11537ah.m2715a(context).m2721a();
        if (C11087b.m5151a(context).m5141b()) {
            C11437hl c11437hl = new C11437hl();
            c11437hl.m3155a(C11541aj.m2703a());
            c11437hl.m3152b(C11087b.m5151a(context).m5156a());
            c11437hl.m3149c(C11087b.m5151a(context).m5138c());
            c11437hl.m3145e(C11087b.m5151a(context).m5143b());
            c11437hl.m3147d(context.getPackageName());
            C11118u.m5003a(context).m4989a(c11437hl);
            PushMessageHandler.m5179a();
            PushMessageHandler.m5165b();
            C11087b.m5151a(context).m5142b();
            clearLocalNotificationType(context);
            clearNotification(context);
            clearExtras(context);
        }
    }

    public static void disablePush(Context context) {
        C11118u.m5003a(context).m4977a(true);
    }

    public static void enablePush(Context context) {
        C11118u.m5003a(context).m4977a(false);
    }

    public static void syncAssembleFCMPushToken(Context context) {
        C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_FCM_TOKEN, EnumC11090d.ASSEMBLE_PUSH_FCM, "");
    }

    public static void setAlias(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, EnumC11317ed.COMMAND_SET_ALIAS.f22058a, str, str2);
    }

    public static void unsetAlias(Context context, String str, String str2) {
        setCommand(context, EnumC11317ed.COMMAND_UNSET_ALIAS.f22058a, str, str2);
    }

    public static void setUserAccount(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        setCommand(context, EnumC11317ed.COMMAND_SET_ACCOUNT.f22058a, str, str2);
    }

    public static void unsetUserAccount(Context context, String str, String str2) {
        setCommand(context, EnumC11317ed.COMMAND_UNSET_ACCOUNT.f22058a, str, str2);
    }

    public static void subscribe(Context context, String str, String str2) {
        if (TextUtils.isEmpty(C11087b.m5151a(context).m5156a()) || TextUtils.isEmpty(str)) {
            return;
        }
        if (Math.abs(System.currentTimeMillis() - topicSubscribedTime(context, str)) > 86400000) {
            C11435hj c11435hj = new C11435hj();
            String m2703a = C11541aj.m2703a();
            c11435hj.m3187a(m2703a);
            c11435hj.m3185b(C11087b.m5151a(context).m5156a());
            c11435hj.m3183c(str);
            c11435hj.m3181d(context.getPackageName());
            c11435hj.m3179e(str2);
            AbstractC11049b.m5266e("cmd:" + EnumC11317ed.COMMAND_SUBSCRIBE_TOPIC + ", " + m2703a);
            C11118u.m5003a(context).m4988a((C11118u) c11435hj, EnumC11404gf.Subscription, (C11417gs) null);
        } else if (1 == PushMessageHelper.getPushMode(context)) {
            PushMessageHandler.m5170a(context, str2, 0L, null, str);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_SUBSCRIBE_TOPIC.f22058a, arrayList, 0L, null, null, null));
        }
    }

    public static void unsubscribe(Context context, String str, String str2) {
        if (C11087b.m5151a(context).m5141b()) {
            if (topicSubscribedTime(context, str) < 0) {
                AbstractC11049b.m5282a("Don't cancel subscribe for " + C11184bb.m4754a(str, 3) + " is unsubscribed");
                return;
            }
            C11439hn c11439hn = new C11439hn();
            String m2703a = C11541aj.m2703a();
            c11439hn.m3117a(m2703a);
            c11439hn.m3115b(C11087b.m5151a(context).m5156a());
            c11439hn.m3113c(str);
            c11439hn.m3111d(context.getPackageName());
            c11439hn.m3109e(str2);
            AbstractC11049b.m5266e("cmd:" + EnumC11317ed.COMMAND_UNSUBSCRIBE_TOPIC + ", " + m2703a);
            C11118u.m5003a(context).m4988a((C11118u) c11439hn, EnumC11404gf.UnSubscription, (C11417gs) null);
        }
    }

    public static void pausePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 0, 0, str);
    }

    public static void resumePush(Context context, String str) {
        setAcceptTime(context, 0, 0, 23, 59, str);
    }

    public static void clearNotification(Context context, int i) {
        C11118u.m5003a(context).m5007a(i);
    }

    public static void clearNotification(Context context, String str, String str2) {
        C11118u.m5003a(context).m4978a(str, str2);
    }

    public static void removeWindow(Context context) {
        C11118u.m5003a(context).m4960e();
    }

    public static void clearNotification(Context context) {
        C11118u.m5003a(context).m5007a(-1);
    }

    public static void reportAppRunInBackground(Context context, boolean z) {
        if (C11087b.m5151a(context).m5141b()) {
            EnumC11414gp enumC11414gp = z ? EnumC11414gp.APP_SLEEP : EnumC11414gp.APP_WAKEUP;
            C11430he c11430he = new C11430he();
            c11430he.m3335b(C11087b.m5151a(context).m5156a());
            c11430he.m3331c(enumC11414gp.f22745a);
            c11430he.m3327d(context.getPackageName());
            c11430he.m3344a(C11541aj.m2703a());
            c11430he.m3340a(false);
            C11118u.m5003a(context).m4985a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null, false);
        }
    }

    public static String getRegId(Context context) {
        if (C11087b.m5151a(context).m5136c()) {
            return C11087b.m5151a(context).m5138c();
        }
        return null;
    }

    public static String getAppRegion(Context context) {
        if (C11087b.m5151a(context).m5136c()) {
            return C11087b.m5151a(context).m5131f();
        }
        return null;
    }

    protected static void setCommand(Context context, String str, String str2, String str3) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
        }
        if (EnumC11317ed.COMMAND_SET_ALIAS.f22058a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - aliasSetTime(context, str2)) < 86400000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.m5169a(context, str3, str, 0L, null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_SET_ALIAS.f22058a, arrayList, 0L, null, str3, null));
            }
        } else if (EnumC11317ed.COMMAND_UNSET_ALIAS.f22058a.equalsIgnoreCase(str) && aliasSetTime(context, str2) < 0) {
            AbstractC11049b.m5282a("Don't cancel alias for " + C11184bb.m4754a(arrayList.toString(), 3) + " is unseted");
        } else if (EnumC11317ed.COMMAND_SET_ACCOUNT.f22058a.equalsIgnoreCase(str) && Math.abs(System.currentTimeMillis() - accountSetTime(context, str2)) < 3600000) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.m5169a(context, str3, str, 0L, null, arrayList);
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_SET_ACCOUNT.f22058a, arrayList, 0L, null, str3, null));
            }
        } else if (EnumC11317ed.COMMAND_UNSET_ACCOUNT.f22058a.equalsIgnoreCase(str) && accountSetTime(context, str2) < 0) {
            AbstractC11049b.m5282a("Don't cancel account for " + C11184bb.m4754a(arrayList.toString(), 3) + " is unseted");
        } else {
            setCommand(context, str, arrayList, str3);
        }
    }

    protected static void setCommand(Context context, String str, ArrayList<String> arrayList, String str2) {
        if (TextUtils.isEmpty(C11087b.m5151a(context).m5156a())) {
            return;
        }
        C11424gz c11424gz = new C11424gz();
        String m2703a = C11541aj.m2703a();
        c11424gz.m3429a(m2703a);
        c11424gz.m3425b(C11087b.m5151a(context).m5156a());
        c11424gz.m3422c(str);
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            c11424gz.m3428a(it.next());
        }
        c11424gz.m3417e(str2);
        c11424gz.m3419d(context.getPackageName());
        AbstractC11049b.m5266e("cmd:" + str + ", " + m2703a);
        C11118u.m5003a(context).m4988a((C11118u) c11424gz, EnumC11404gf.Command, (C11417gs) null);
    }

    public static void setAcceptTime(Context context, int i, int i2, int i3, int i4, String str) {
        if (i < 0 || i >= 24 || i3 < 0 || i3 >= 24 || i2 < 0 || i2 >= 60 || i4 < 0 || i4 >= 60) {
            throw new IllegalArgumentException("the input parameter is not valid.");
        }
        long rawOffset = ((TimeZone.getTimeZone("GMT+08").getRawOffset() - TimeZone.getDefault().getRawOffset()) / 1000) / 60;
        long j = ((((i * 60) + i2) + rawOffset) + 1440) % 1440;
        long j2 = ((((i3 * 60) + i4) + rawOffset) + 1440) % 1440;
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j / 60), Long.valueOf(j % 60)));
        arrayList.add(String.format("%1$02d:%2$02d", Long.valueOf(j2 / 60), Long.valueOf(j2 % 60)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i), Integer.valueOf(i2)));
        arrayList2.add(String.format("%1$02d:%2$02d", Integer.valueOf(i3), Integer.valueOf(i4)));
        if (acceptTimeSet(context, (String) arrayList.get(0), (String) arrayList.get(1))) {
            if (1 == PushMessageHelper.getPushMode(context)) {
                PushMessageHandler.m5169a(context, str, EnumC11317ed.COMMAND_SET_ACCEPT_TIME.f22058a, 0L, null, arrayList2);
                return;
            } else {
                PushMessageHelper.sendCommandMessageBroadcast(context, PushMessageHelper.generateCommandMessage(EnumC11317ed.COMMAND_SET_ACCEPT_TIME.f22058a, arrayList2, 0L, null, null, null));
                return;
            }
        }
        setCommand(context, EnumC11317ed.COMMAND_SET_ACCEPT_TIME.f22058a, arrayList, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenHmsPush(Context context) {
        checkNotNull(context, "context");
        return C11091e.m5114a(context).m5105b(EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenFCMPush(Context context) {
        checkNotNull(context, "context");
        return C11091e.m5114a(context).m5105b(EnumC11090d.ASSEMBLE_PUSH_FCM);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenOPPOPush(Context context) {
        checkNotNull(context, "context");
        return C11091e.m5114a(context).m5105b(EnumC11090d.ASSEMBLE_PUSH_COS);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean getOpenVIVOPush(Context context) {
        return C11091e.m5114a(context).m5105b(EnumC11090d.ASSEMBLE_PUSH_FTOS);
    }

    private static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException("param " + str + " is not nullable");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    @Deprecated
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class MiPushClientCallback {
        private String category;

        public void onCommandResult(String str, long j, String str2, List<String> list) {
        }

        public void onInitializeResult(long j, String str, String str2) {
        }

        public void onReceiveMessage(MiPushMessage miPushMessage) {
        }

        public void onReceiveMessage(String str, String str2, String str3, boolean z) {
        }

        public void onSubscribeResult(long j, String str, String str2) {
        }

        public void onUnsubscribeResult(long j, String str, String str2) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public String getCategory() {
            return this.category;
        }

        protected void setCategory(String str) {
            this.category = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void addAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("alias_" + str, System.currentTimeMillis()).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAlias(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("alias_" + str).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAllAliases(Context context) {
        synchronized (MiPushClient.class) {
            for (String str : getAllAlias(context)) {
                removeAlias(context, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void addAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("account_" + str, System.currentTimeMillis()).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAccount(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("account_" + str).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAllAccounts(Context context) {
        synchronized (MiPushClient.class) {
            for (String str : getAllUserAccount(context)) {
                removeAccount(context, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void addTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putLong("topic_" + str, System.currentTimeMillis()).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeTopic(Context context, String str) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("topic_" + str).commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAllTopics(Context context) {
        synchronized (MiPushClient.class) {
            for (String str : getAllTopic(context)) {
                removeTopic(context, str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void addAcceptTime(Context context, String str, String str2) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString("accept_time", str + "," + str2);
            C11476p.m2938a(edit);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void removeAcceptTime(Context context) {
        synchronized (MiPushClient.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.remove("accept_time");
            C11476p.m2938a(edit);
        }
    }

    public static long topicSubscribedTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("topic_" + str, -1L);
    }

    public static long accountSetTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("account_" + str, -1L);
    }

    public static long aliasSetTime(Context context, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        return sharedPreferences.getLong("alias_" + str, -1L);
    }

    private static boolean acceptTimeSet(Context context, String str, String str2) {
        String acceptTime = getAcceptTime(context);
        return TextUtils.equals(acceptTime, str + "," + str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getAcceptTime(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getString("accept_time", "00:00-23:59");
    }

    private static void addPullNotificationTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_pull_notification", System.currentTimeMillis());
        C11476p.m2938a(edit);
    }

    private static boolean shouldPullNotification(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_pull_notification", -1L)) > 300000;
    }

    private static void addRegRequestTime(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
        edit.putLong("last_reg_request", System.currentTimeMillis());
        C11476p.m2938a(edit);
    }

    private static boolean shouldSendRegRequest(Context context) {
        return Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_reg_request", -1L)) > 5000;
    }

    private static void initEventPerfLogic(final Context context) {
        C11303ds.m4121a(new C11303ds.InterfaceC11304a() { // from class: com.xiaomi.mipush.sdk.MiPushClient.5
            @Override // com.xiaomi.push.C11303ds.InterfaceC11304a
            public void uploader(Context context2, C11408gj c11408gj) {
                MiTinyDataClient.upload(context2, c11408gj);
            }
        });
        Config m4130a = C11303ds.m4130a(context);
        C11053a.m5261a(context).m5251a("5_9_9-C");
        ClientReportClient.init(context, m4130a, new C11301dq(context), new C11302dr(context));
        C11086a.m5158a(context);
        C11103k.m5060a(context, m4130a);
        C11537ah.m2715a(context).m2711a(new C11537ah.AbstractRunnableC11538a(100, "perf event job update") { // from class: com.xiaomi.mipush.sdk.MiPushClient.6
            @Override // com.xiaomi.push.service.C11537ah.AbstractRunnableC11538a
            public void onCallback() {
                C11303ds.m4129a(context);
            }
        });
    }

    @Deprecated
    public static void registerCrashHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    private static void operateSyncAction(Context context) {
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.DISABLE_PUSH))) {
            disablePush(sContext);
        }
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.ENABLE_PUSH))) {
            enablePush(sContext);
        }
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.UPLOAD_HUAWEI_TOKEN))) {
            C11118u.m5003a(sContext).m4980a((String) null, EnumC11125v.UPLOAD_HUAWEI_TOKEN, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, "init");
        }
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.UPLOAD_FCM_TOKEN))) {
            syncAssembleFCMPushToken(sContext);
        }
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.UPLOAD_COS_TOKEN))) {
            C11118u.m5003a(sContext).m4980a((String) null, EnumC11125v.UPLOAD_COS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_COS, "init");
        }
        if ("syncing".equals(C11111p.m5047a(sContext).m5046a(EnumC11125v.UPLOAD_FTOS_TOKEN))) {
            C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_FTOS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_FTOS, "init");
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class TokenResult {
        private String token = null;
        private long resultCode = -1;

        /* JADX INFO: Access modifiers changed from: protected */
        public void setToken(String str) {
            this.token = str;
        }

        public String getToken() {
            return this.token;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setResultCode(long j) {
            this.resultCode = j;
        }

        public long getResultCode() {
            return this.resultCode;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class CodeResult {
        private long resultCode = -1;

        protected void setResultCode(long j) {
            this.resultCode = j;
        }

        public long getResultCode() {
            return this.resultCode;
        }
    }

    public static void registerToken(Context context, String str, String str2, String str3, UPSRegisterCallBack uPSRegisterCallBack) {
        registerPush(context, str, str2, new PushConfiguration(), null, uPSRegisterCallBack);
    }

    public static void unRegisterToken(Context context, UPSUnRegisterCallBack uPSUnRegisterCallBack) {
        unregisterPush(context);
        if (uPSUnRegisterCallBack != null) {
            TokenResult tokenResult = new TokenResult();
            tokenResult.setToken(null);
            tokenResult.getToken();
            tokenResult.setResultCode(0L);
            tokenResult.getResultCode();
            uPSUnRegisterCallBack.onResult(tokenResult);
        }
    }

    public static void turnOnPush(Context context, UPSTurnCallBack uPSTurnCallBack) {
        enablePush(context);
        if (uPSTurnCallBack != null) {
            CodeResult codeResult = new CodeResult();
            codeResult.setResultCode(0L);
            codeResult.getResultCode();
            uPSTurnCallBack.onResult(codeResult);
        }
    }

    public static void turnOffPush(Context context, UPSTurnCallBack uPSTurnCallBack) {
        disablePush(context);
        if (uPSTurnCallBack != null) {
            CodeResult codeResult = new CodeResult();
            codeResult.setResultCode(0L);
            codeResult.getResultCode();
            uPSTurnCallBack.onResult(codeResult);
        }
    }
}
