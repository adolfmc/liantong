package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11176aw;
import com.xiaomi.push.C11183ba;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11476p;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.C11480s;
import com.xiaomi.push.service.C11537ah;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11094f {
    /* renamed from: a */
    public static void m5095a(Context context, EnumC11090d enumC11090d, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int m5096a = m5096a(context, enumC11090d, str);
        if (m5096a != 0) {
            AbstractC11049b.m5282a("ASSEMBLE_PUSH : send token upload, check:" + m5096a);
            m5092a(context, str);
            EnumC11125v m5077a = C11097g.m5077a(enumC11090d);
            if (m5077a == null) {
                return;
            }
            C11118u.m5003a(context).m4980a((String) null, m5077a, enumC11090d, "upload");
            return;
        }
        AbstractC11049b.m5282a("ASSEMBLE_PUSH : do not need to send token");
    }

    /* renamed from: a */
    private static int m5096a(Context context, EnumC11090d enumC11090d, String str) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String m5090a = m5090a(enumC11090d);
        String string = sharedPreferences.getString(m5090a, "");
        String m5138c = C11087b.m5151a(context).m5138c();
        String string2 = sharedPreferences.getString("last_check_token", "");
        if (TextUtils.isEmpty(m5090a)) {
            AbstractC11049b.m5282a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
            return 0;
        } else if (TextUtils.isEmpty(string)) {
            return 1;
        } else {
            if (string.equals(str)) {
                if (TextUtils.equals(m5138c, string2)) {
                    if (m5089a(enumC11090d)) {
                        if (m5104a() != sharedPreferences.getInt(m5083b(enumC11090d), 0)) {
                            return 4;
                        }
                    }
                    return 0;
                }
                return 3;
            }
            return 2;
        }
    }

    /* renamed from: a */
    static String m5100a(Context context, EnumC11090d enumC11090d) {
        return m5094a(context, enumC11090d, false);
    }

    /* renamed from: a */
    public static boolean m5088a(C11427hb c11427hb, EnumC11090d enumC11090d) {
        if (c11427hb == null || c11427hb.m3388a() == null || c11427hb.m3388a().m3558a() == null) {
            return false;
        }
        return (enumC11090d == EnumC11090d.ASSEMBLE_PUSH_FCM ? "FCM" : "").equalsIgnoreCase(c11427hb.m3388a().m3558a().get("assemble_push_type"));
    }

    /* renamed from: a */
    public static byte[] m5093a(Context context, C11427hb c11427hb, EnumC11090d enumC11090d) {
        if (m5088a(c11427hb, enumC11090d)) {
            return C11183ba.m4760a(m5100a(context, enumC11090d));
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m5102a(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        String m5090a = m5090a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
        String m5090a2 = m5090a(EnumC11090d.ASSEMBLE_PUSH_FCM);
        if (!TextUtils.isEmpty(sharedPreferences.getString(m5090a, "")) && TextUtils.isEmpty(sharedPreferences.getString(m5090a2, ""))) {
            z = true;
        }
        if (z) {
            C11118u.m5003a(context).m5004a(2, m5090a);
        }
    }

    /* renamed from: a */
    public static void m5098a(Context context, EnumC11090d enumC11090d) {
        String m5090a = m5090a(enumC11090d);
        if (TextUtils.isEmpty(m5090a)) {
            return;
        }
        C11476p.m2938a(context.getSharedPreferences("mipush_extra", 0).edit().putString(m5090a, ""));
    }

    /* renamed from: a */
    public static boolean m5097a(Context context, EnumC11090d enumC11090d) {
        if (C11097g.m5076a(enumC11090d) != null) {
            return C11537ah.m2715a(context).m2716a(C11097g.m5076a(enumC11090d).m3637a(), true);
        }
        return false;
    }

    /* renamed from: a */
    public static void m5086a(String str, int i) {
        MiTinyDataClient.upload("hms_push_error", str, 1L, "error code = " + i);
    }

    /* renamed from: a */
    public static boolean m5101a(Context context) {
        if (context == null) {
            return false;
        }
        return C11169au.m4849a(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static synchronized void m5079d(Context context, EnumC11090d enumC11090d, String str) {
        synchronized (C11094f.class) {
            String m5090a = m5090a(enumC11090d);
            if (TextUtils.isEmpty(m5090a)) {
                AbstractC11049b.m5282a("ASSEMBLE_PUSH : can not find the key of token used in sp file");
                return;
            }
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString(m5090a, str).putString("last_check_token", C11087b.m5151a(context).m5138c());
            if (m5089a(enumC11090d)) {
                edit.putInt(m5083b(enumC11090d), m5104a());
            }
            edit.putString("syncingToken", "");
            C11476p.m2938a(edit);
            AbstractC11049b.m5282a("ASSEMBLE_PUSH : update sp file success!  " + str);
        }
    }

    /* renamed from: b */
    public static void m5084b(final Context context, final EnumC11090d enumC11090d, final String str) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.mipush.sdk.f.1
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String str2 = "";
                String[] split = str.split("~");
                int length = split.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    String str3 = split[i];
                    if (!TextUtils.isEmpty(str3) && str3.startsWith("token:")) {
                        str2 = str3.substring(str3.indexOf(":") + 1);
                        break;
                    }
                    i++;
                }
                if (!TextUtils.isEmpty(str2)) {
                    AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive correct token");
                    C11094f.m5079d(context, enumC11090d, str2);
                    C11094f.m5102a(context);
                    return;
                }
                AbstractC11049b.m5282a("ASSEMBLE_PUSH : receive incorrect token");
            }
        });
    }

    /* renamed from: a */
    private static synchronized void m5092a(Context context, String str) {
        synchronized (C11094f.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 0).edit();
            edit.putString("syncingToken", str);
            edit.apply();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static synchronized String m5094a(Context context, EnumC11090d enumC11090d, boolean z) {
        synchronized (C11094f.class) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
            if (z) {
                String string = sharedPreferences.getString("syncingToken", "");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
            String m5090a = m5090a(enumC11090d);
            if (TextUtils.isEmpty(m5090a)) {
                return "";
            }
            return sharedPreferences.getString(m5090a, "");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static PushMessageReceiver m5103a(Context context) {
        ResolveInfo resolveInfo;
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                Iterator<ResolveInfo> it = queryBroadcastReceivers.iterator();
                while (it.hasNext()) {
                    resolveInfo = it.next();
                    if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(context.getPackageName())) {
                        break;
                    }
                }
            }
            resolveInfo = null;
            if (resolveInfo != null) {
                return (PushMessageReceiver) C11479r.m2929a(context, resolveInfo.activityInfo.name).newInstance();
            }
            return null;
        } catch (Exception e) {
            AbstractC11049b.m5268d(e.toString());
            return null;
        }
    }

    /* renamed from: a */
    public static void m5091a(Intent intent) {
        Bundle extras;
        if (intent == null || (extras = intent.getExtras()) == null || !extras.containsKey("pushMsg")) {
            return;
        }
        intent.putExtra("key_message", m5087a(extras.getString("pushMsg")));
    }

    /* renamed from: b */
    public static void m5085b(Context context) {
        C11091e.m5114a(context).register();
    }

    /* renamed from: c */
    public static void m5082c(Context context) {
        C11091e.m5114a(context).unregister();
    }

    /* renamed from: a */
    public static MiPushMessage m5087a(String str) {
        MiPushMessage miPushMessage = new MiPushMessage();
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("messageId")) {
                    miPushMessage.setMessageId(jSONObject.getString("messageId"));
                }
                if (jSONObject.has("description")) {
                    miPushMessage.setDescription(jSONObject.getString("description"));
                }
                if (jSONObject.has("title")) {
                    miPushMessage.setTitle(jSONObject.getString("title"));
                }
                if (jSONObject.has("content")) {
                    miPushMessage.setContent(jSONObject.getString("content"));
                }
                if (jSONObject.has("passThrough")) {
                    miPushMessage.setPassThrough(jSONObject.getInt("passThrough"));
                }
                if (jSONObject.has("notifyType")) {
                    miPushMessage.setNotifyType(jSONObject.getInt("notifyType"));
                }
                if (jSONObject.has("messageType")) {
                    miPushMessage.setMessageType(jSONObject.getInt("messageType"));
                }
                if (jSONObject.has("alias")) {
                    miPushMessage.setAlias(jSONObject.getString("alias"));
                }
                if (jSONObject.has("topic")) {
                    miPushMessage.setTopic(jSONObject.getString("topic"));
                }
                if (jSONObject.has("user_account")) {
                    miPushMessage.setUserAccount(jSONObject.getString("user_account"));
                }
                if (jSONObject.has("notifyId")) {
                    miPushMessage.setNotifyId(jSONObject.getInt("notifyId"));
                }
                if (jSONObject.has("category")) {
                    miPushMessage.setCategory(jSONObject.getString("category"));
                }
                if (jSONObject.has("isNotified")) {
                    miPushMessage.setNotified(jSONObject.getBoolean("isNotified"));
                }
                if (jSONObject.has("extra")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("extra");
                    Iterator<String> keys = jSONObject2.keys();
                    HashMap hashMap = new HashMap();
                    while (keys != null && keys.hasNext()) {
                        String next = keys.next();
                        hashMap.put(next, jSONObject2.getString(next));
                    }
                    if (hashMap.size() > 0) {
                        miPushMessage.setExtra(hashMap);
                    }
                }
            } catch (Exception e) {
                AbstractC11049b.m5268d(e.toString());
            }
        }
        return miPushMessage;
    }

    /* renamed from: a */
    public static HashMap<String, String> m5099a(Context context, EnumC11090d enumC11090d) {
        HashMap<String, String> hashMap = new HashMap<>();
        String str = null;
        ApplicationInfo applicationInfo = null;
        switch (enumC11090d) {
            case ASSEMBLE_PUSH_HUAWEI:
                try {
                    applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                } catch (Exception e) {
                    AbstractC11049b.m5268d(e.toString());
                }
                str = new C11480s.C11481a(":", "~").m2919a("brand", EnumC11112q.HUAWEI.name()).m2919a("token", m5094a(context, enumC11090d, true)).m2919a("package_name", context.getPackageName()).m2919a("app_id", Integer.valueOf(applicationInfo != null ? applicationInfo.metaData.getInt("com.huawei.hms.client.appid") : -1)).toString();
                break;
            case ASSEMBLE_PUSH_FCM:
                C11480s.C11481a m2919a = new C11480s.C11481a(":", "~").m2919a("brand", EnumC11112q.FCM.name()).m2919a("token", m5094a(context, enumC11090d, false)).m2919a("package_name", context.getPackageName());
                int m5104a = m5104a();
                if (m5104a != 0) {
                    m2919a.m2919a("version", Integer.valueOf(m5104a));
                } else {
                    m2919a.m2919a("version", 50909);
                }
                str = m2919a.toString();
                break;
            case ASSEMBLE_PUSH_COS:
                str = new C11480s.C11481a(":", "~").m2919a("brand", EnumC11112q.OPPO.name()).m2919a("token", m5094a(context, enumC11090d, true)).m2919a("package_name", context.getPackageName()).toString();
                break;
            case ASSEMBLE_PUSH_FTOS:
                C11480s.C11481a m2919a2 = new C11480s.C11481a(":", "~").m2919a("brand", EnumC11112q.VIVO.name()).m2919a("token", m5094a(context, enumC11090d, true)).m2919a("package_name", context.getPackageName());
                int m5104a2 = m5104a();
                if (m5104a2 != 0) {
                    m2919a2.m2919a("version", Integer.valueOf(m5104a2));
                }
                str = m2919a2.toString();
                break;
        }
        hashMap.put("RegInfo", str);
        return hashMap;
    }

    /* renamed from: a */
    public static int m5104a() {
        Integer num = (Integer) C11176aw.m4811a("com.xiaomi.assemble.control.AssembleConstants", "ASSEMBLE_VERSION_CODE");
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    /* renamed from: a */
    public static boolean m5089a(EnumC11090d enumC11090d) {
        return enumC11090d == EnumC11090d.ASSEMBLE_PUSH_FTOS || enumC11090d == EnumC11090d.ASSEMBLE_PUSH_FCM;
    }

    /* renamed from: a */
    public static String m5090a(EnumC11090d enumC11090d) {
        switch (enumC11090d) {
            case ASSEMBLE_PUSH_HUAWEI:
                return "hms_push_token";
            case ASSEMBLE_PUSH_FCM:
                return "fcm_push_token_v2";
            case ASSEMBLE_PUSH_COS:
                return "cos_push_token";
            case ASSEMBLE_PUSH_FTOS:
                return "ftos_push_token";
            default:
                return null;
        }
    }

    /* renamed from: b */
    public static String m5083b(EnumC11090d enumC11090d) {
        return m5090a(enumC11090d) + "_version";
    }

    /* renamed from: c */
    public static String m5080c(EnumC11090d enumC11090d) {
        switch (enumC11090d) {
            case ASSEMBLE_PUSH_HUAWEI:
                return "hms_push_error";
            case ASSEMBLE_PUSH_FCM:
                return "fcm_push_error";
            case ASSEMBLE_PUSH_COS:
                return "cos_push_error";
            case ASSEMBLE_PUSH_FTOS:
                return "ftos_push_error";
            default:
                return null;
        }
    }
}
