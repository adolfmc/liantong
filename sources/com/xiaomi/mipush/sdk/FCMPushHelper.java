package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11434hi;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.service.AbstractC11555an;
import com.xiaomi.push.service.C11602l;
import com.xiaomi.push.service.C11621u;
import com.xiaomi.push.service.C11635x;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FCMPushHelper {
    public static void reportFCMMessageDelete() {
        MiTinyDataClient.upload(C11094f.m5080c(EnumC11090d.ASSEMBLE_PUSH_FCM), "fcm", 1L, "some fcm messages was deleted ");
    }

    public static void uploadToken(Context context, String str) {
        C11094f.m5095a(context, EnumC11090d.ASSEMBLE_PUSH_FCM, str);
    }

    public static void clearToken(Context context) {
        C11094f.m5098a(context, EnumC11090d.ASSEMBLE_PUSH_FCM);
    }

    public static boolean isFCMSwitchOpen(Context context) {
        return C11094f.m5097a(context, EnumC11090d.ASSEMBLE_PUSH_FCM) && MiPushClient.getOpenFCMPush(context);
    }

    public static void notifyFCMNotificationCome(Context context, Map<String, String> map) {
        PushMessageReceiver m5103a;
        String str = map.get("pushMsg");
        if (TextUtils.isEmpty(str) || (m5103a = C11094f.m5103a(context)) == null) {
            return;
        }
        m5103a.onNotificationMessageArrived(context, C11094f.m5087a(str));
    }

    public static Map<String, String> notifyFCMPassThoughMessageCome(Context context, Map<String, String> map) {
        PushMessageReceiver m5103a;
        String str = map.get("pushMsg");
        if (!TextUtils.isEmpty(str) && (m5103a = C11094f.m5103a(context)) != null) {
            m5103a.onReceivePassThroughMessage(context, C11094f.m5087a(str));
        }
        String str2 = map.get("mipushContainer");
        if (!TextUtils.isEmpty(str2)) {
            try {
                byte[] decode = Base64.decode(str2, 2);
                m5209a(context, C11621u.m2383a(decode));
                m5208a(context, decode);
            } catch (Throwable th) {
                AbstractC11049b.m5279a("fcm notify notification error ", th);
            }
            return m5211a(context);
        }
        return new HashMap();
    }

    /* renamed from: a */
    private static void m5209a(Context context, C11427hb c11427hb) {
        try {
            MiPushMessage generateMessage = PushMessageHelper.generateMessage((C11434hi) C11113r.m5039a(context, c11427hb), c11427hb.m3388a(), false);
            PushMessageReceiver m5103a = C11094f.m5103a(context);
            if (m5103a != null) {
                m5103a.onNotificationMessageArrived(context, generateMessage);
            }
        } catch (Throwable th) {
            AbstractC11049b.m5279a("fcm broadcast notification come error ", th);
        }
    }

    /* renamed from: a */
    private static Map<String, String> m5211a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("actionType", String.valueOf(EnumC11404gf.AckMessage.m3684a()));
        hashMap.put("deviceStatus", String.valueOf((int) C11441hp.m3088a(context, context.getPackageName())));
        hashMap.put("mat", Long.toString(System.currentTimeMillis()));
        return hashMap;
    }

    public static void convertMessage(Intent intent) {
        C11094f.m5091a(intent);
    }

    /* renamed from: a */
    private static void m5208a(Context context, byte[] bArr) {
        boolean m5008a = C11118u.m5003a(context).m5008a();
        boolean z = !"com.xiaomi.xmsf".equals(context.getPackageName());
        boolean m5210a = m5210a(context);
        boolean z2 = false;
        if (m5008a && z && m5210a) {
            bArr = C11602l.m2518a(bArr, C11087b.m5151a(context).m5135d());
            if (bArr == null) {
                AbstractC11049b.m5282a("fcm message encrypt failed");
            } else {
                String encodeToString = Base64.encodeToString(bArr, 2);
                if (TextUtils.isEmpty(encodeToString)) {
                    AbstractC11049b.m5282a("fcm message buf base64 encode failed");
                } else {
                    Intent intent = new Intent(AbstractC11555an.f23588n);
                    intent.setPackage("com.xiaomi.xmsf");
                    intent.setClassName("com.xiaomi.xmsf", "com.xiaomi.push.service.XMPushService");
                    intent.putExtra("ext_fcm_container_buffer", encodeToString);
                    intent.putExtra("mipush_app_package", context.getPackageName());
                    context.startService(intent);
                    AbstractC11049b.m5282a("fcm message reroute to xmsf");
                    z2 = true;
                }
            }
        } else {
            AbstractC11049b.m5282a(String.format("xmsf can not receive fcm msg - shouldUseMIUIPush=%s;isNotXmsf=%s;xmsfSupport=%s", Boolean.valueOf(m5008a), Boolean.valueOf(z), Boolean.valueOf(m5210a)));
        }
        if (z2) {
            return;
        }
        AbstractC11049b.m5274b("fcm message post local");
        C11635x.m2335a(context, C11621u.m2383a(bArr), bArr);
    }

    /* renamed from: a */
    private static boolean m5210a(Context context) {
        return ((long) C11469j.m2964b(context)) >= 50002000 && m5207b(context);
    }

    /* renamed from: b */
    private static boolean m5207b(Context context) {
        return context.getSharedPreferences("mipush_extra", 0).getBoolean("is_xmsf_sup_decrypt", false);
    }

    public static void persistIfXmsfSupDecrypt(Context context) {
        context.getSharedPreferences("mipush_extra", 0).edit().putBoolean("is_xmsf_sup_decrypt", ((long) C11469j.m2964b(context)) >= 50002000).apply();
    }
}
