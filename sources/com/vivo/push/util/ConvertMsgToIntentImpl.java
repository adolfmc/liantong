package com.vivo.push.util;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import java.util.List;
import java.util.Map;

/* renamed from: com.vivo.push.util.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ConvertMsgToIntentImpl extends BaseConvertMsgToIntent {

    /* renamed from: e */
    private int f21214e = 0;

    @Override // com.vivo.push.util.BaseConvertMsgToIntent
    /* renamed from: a */
    protected final int mo5403a() {
        return this.f21214e;
    }

    @Override // com.vivo.push.util.BaseConvertMsgToIntent
    /* renamed from: a */
    protected final Intent mo5401a(Context context, InsideNotificationItem insideNotificationItem, NotifyArriveCallbackByUser notifyArriveCallbackByUser) {
        return m5395b(context, insideNotificationItem, notifyArriveCallbackByUser);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.util.BaseConvertMsgToIntent
    /* renamed from: a */
    public final PendingIntent mo5402a(Context context, Intent intent) {
        return PendingIntent.getActivity(context, (int) SystemClock.uptimeMillis(), intent, 201326592);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00e2 A[Catch: Exception -> 0x0168, TryCatch #1 {Exception -> 0x0168, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0011, B:9:0x002d, B:34:0x0125, B:36:0x012f, B:38:0x0146, B:11:0x0034, B:12:0x0055, B:14:0x0076, B:20:0x009c, B:21:0x00b2, B:22:0x00bf, B:24:0x00c9, B:26:0x00d5, B:31:0x00e2, B:32:0x010a, B:33:0x011c, B:39:0x015e, B:15:0x007d, B:17:0x0096), top: B:46:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x010a A[Catch: Exception -> 0x0168, TryCatch #1 {Exception -> 0x0168, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0011, B:9:0x002d, B:34:0x0125, B:36:0x012f, B:38:0x0146, B:11:0x0034, B:12:0x0055, B:14:0x0076, B:20:0x009c, B:21:0x00b2, B:22:0x00bf, B:24:0x00c9, B:26:0x00d5, B:31:0x00e2, B:32:0x010a, B:33:0x011c, B:39:0x015e, B:15:0x007d, B:17:0x0096), top: B:46:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x012f A[Catch: Exception -> 0x0168, TryCatch #1 {Exception -> 0x0168, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0011, B:9:0x002d, B:34:0x0125, B:36:0x012f, B:38:0x0146, B:11:0x0034, B:12:0x0055, B:14:0x0076, B:20:0x009c, B:21:0x00b2, B:22:0x00bf, B:24:0x00c9, B:26:0x00d5, B:31:0x00e2, B:32:0x010a, B:33:0x011c, B:39:0x015e, B:15:0x007d, B:17:0x0096), top: B:46:0x0003, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0146 A[Catch: Exception -> 0x0168, TryCatch #1 {Exception -> 0x0168, blocks: (B:3:0x0003, B:5:0x0009, B:8:0x0011, B:9:0x002d, B:34:0x0125, B:36:0x012f, B:38:0x0146, B:11:0x0034, B:12:0x0055, B:14:0x0076, B:20:0x009c, B:21:0x00b2, B:22:0x00bf, B:24:0x00c9, B:26:0x00d5, B:31:0x00e2, B:32:0x010a, B:33:0x011c, B:39:0x015e, B:15:0x007d, B:17:0x0096), top: B:46:0x0003, inners: #0 }] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.content.Intent m5395b(android.content.Context r9, com.vivo.push.model.InsideNotificationItem r10, com.vivo.push.model.NotifyArriveCallbackByUser r11) {
        /*
            Method dump skipped, instructions count: 400
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.ConvertMsgToIntentImpl.m5395b(android.content.Context, com.vivo.push.model.InsideNotificationItem, com.vivo.push.model.NotifyArriveCallbackByUser):android.content.Intent");
    }

    /* renamed from: a */
    private Intent m5397a(String str, String str2, InsideNotificationItem insideNotificationItem, Context context) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            parseUri.setSelector(null);
            parseUri.setPackage(str2);
            parseUri.setFlags(335544320);
            m5398a(parseUri, insideNotificationItem.getParams());
            int m5399a = m5399a(parseUri, str2);
            if (m5399a > 0) {
                this.f21214e = m5399a;
                LogUtil.m5343c(context, " 落地页未找到，通知不展示：  " + this.f21214e);
                return null;
            }
            return parseUri;
        } catch (Exception e) {
            LogUtil.m5353a("AndroidTwelveNotifyClickIntentParam", "sendFakeNoticeToClient open activity error : ".concat(String.valueOf(str)), e);
            this.f21214e = 2158;
            return null;
        }
    }

    /* renamed from: a */
    private static Intent m5396a(Map<String, String> map, String str, Context context) {
        Intent intent = new Intent();
        intent.setPackage(str);
        try {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(50);
            if (runningTasks != null) {
                for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
                    ComponentName componentName = runningTaskInfo.topActivity;
                    if (componentName.getPackageName().equals(str)) {
                        LogUtil.m5341d("AndroidTwelveNotifyClickIntentParam", "sendFakeNoticeToClient topClassName=" + componentName.getClassName());
                        intent.setComponent(componentName);
                        intent.setFlags(335544320);
                        m5398a(intent, map);
                        return intent;
                    }
                }
            }
        } catch (Exception e) {
            LogUtil.m5353a("AndroidTwelveNotifyClickIntentParam", "sendFakeNoticeToClient ActivityManager", e);
        }
        try {
            intent = context.getPackageManager().getLaunchIntentForPackage(str);
            if (intent != null) {
                intent.setFlags(335544320);
                m5398a(intent, map);
            } else {
                LogUtil.m5354a("AndroidTwelveNotifyClickIntentParam", "sendFakeNoticeToClient LaunchIntent is null");
            }
        } catch (Exception e2) {
            LogUtil.m5354a("AndroidTwelveNotifyClickIntentParam", "sendFakeNoticeToClient LaunchIntent Exception" + e2.getMessage());
        }
        return intent;
    }

    /* renamed from: a */
    private static Intent m5398a(Intent intent, Map<String, String> map) {
        if (map == null || map.entrySet() == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        return intent;
    }

    /* renamed from: b */
    private static Intent m5393b(Intent intent, Map<String, String> map) {
        if (map == null || map.entrySet() == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null && !intent.hasExtra(entry.getKey())) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        return intent;
    }

    /* renamed from: a */
    private int m5399a(Intent intent, String str) {
        int m5400a = m5400a(intent);
        LogUtil.m5341d("AndroidTwelveNotifyClickIntentParam", "checkSkipContentParameterLegal canfindactivity code : ".concat(String.valueOf(m5400a)));
        if (m5400a != 0) {
            return m5400a;
        }
        int m5394b = m5394b(intent, str);
        LogUtil.m5341d("AndroidTwelveNotifyClickIntentParam", "checkSkipContentParameterLegal packagefit code : ".concat(String.valueOf(m5394b)));
        if (m5394b != 0) {
            return m5394b;
        }
        return 0;
    }

    /* renamed from: a */
    private int m5400a(Intent intent) {
        if (intent.resolveActivityInfo(this.f21200c.getPackageManager(), 65536) == null) {
            LogUtil.m5354a("AndroidTwelveNotifyClickIntentParam", "activity is null  ");
            LogUtil.m5343c(this.f21200c, " 跳转参数对应的Activity找不到 通知不展示   2162");
            return 2162;
        }
        return 0;
    }

    /* renamed from: b */
    private int m5394b(Intent intent, String str) {
        if (intent == null || TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder("checkSkipContentPackageFit intent = : ");
            Object obj = intent;
            if (intent == null) {
                obj = "";
            }
            sb.append(obj);
            sb.append(" mPkgName = ");
            sb.append(str);
            LogUtil.m5354a("AndroidTwelveNotifyClickIntentParam", sb.toString());
            return 2158;
        }
        try {
            String packageName = intent.getComponent() != null ? intent.getComponent().getPackageName() : intent.getPackage();
            if (TextUtils.isEmpty(packageName) || TextUtils.equals(str, packageName)) {
                return 0;
            }
            LogUtil.m5341d("AndroidTwelveNotifyClickIntentParam", "activity component error : local pkgName is " + str + "; but remote pkgName is " + packageName);
            Context context = this.f21200c;
            LogUtil.m5356a(context, " 跳转参数对应的包名不是当前应用包名    local pkgName is " + str + "; but remote pkgName is " + packageName + " code =2813");
            return 2813;
        } catch (Exception e) {
            LogUtil.m5354a("AndroidTwelveNotifyClickIntentParam", "checkSkipContentPackage open activity error :  error " + e.getMessage());
            return 2158;
        }
    }
}
