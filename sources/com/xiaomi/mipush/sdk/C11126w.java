package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11471l;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11157an;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11395g;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.C11651z;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11409gk;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11541aj;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.w */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11126w {
    /* renamed from: a */
    public static void m4954a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mipush_extra", 0);
        long j = sharedPreferences.getLong("last_sync_info", -1L);
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long m2719a = C11537ah.m2715a(context).m2719a(EnumC11409gk.SyncInfoFrequency.m3637a(), 1209600);
        if (j == -1) {
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        } else if (Math.abs(currentTimeMillis - j) > m2719a) {
            m4952a(context, true);
            sharedPreferences.edit().putLong("last_sync_info", currentTimeMillis).commit();
        }
    }

    /* renamed from: a */
    public static void m4952a(final Context context, final boolean z) {
        C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.mipush.sdk.w.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractC11049b.m5282a("do sync info");
                C11430he c11430he = new C11430he(C11541aj.m2703a(), false);
                C11087b m5151a = C11087b.m5151a(context);
                c11430he.m3331c(EnumC11414gp.SyncInfo.f22745a);
                c11430he.m3335b(m5151a.m5156a());
                c11430he.m3327d(context.getPackageName());
                c11430he.f23010a = new HashMap();
                Map<String, String> map = c11430he.f23010a;
                Context context2 = context;
                AbstractC11471l.m2951a(map, "app_version", C11395g.m3717a(context2, context2.getPackageName()));
                Map<String, String> map2 = c11430he.f23010a;
                Context context3 = context;
                AbstractC11471l.m2951a(map2, "app_version_code", Integer.toString(C11395g.m3720a(context3, context3.getPackageName())));
                AbstractC11471l.m2951a(c11430he.f23010a, "push_sdk_vn", "5_9_9-C");
                AbstractC11471l.m2951a(c11430he.f23010a, "push_sdk_vc", Integer.toString(50909));
                AbstractC11471l.m2951a(c11430he.f23010a, "token", m5151a.m5143b());
                if (!C11469j.m2958d()) {
                    String m4757a = C11184bb.m4757a(C11455i.m3037c(context));
                    String m3033e = C11455i.m3033e(context);
                    if (!TextUtils.isEmpty(m3033e)) {
                        m4757a = m4757a + "," + m3033e;
                    }
                    if (!TextUtils.isEmpty(m4757a)) {
                        AbstractC11471l.m2951a(c11430he.f23010a, "imei_md5", m4757a);
                    }
                }
                C11157an.m4882a(context).m4880a(c11430he.f23010a);
                AbstractC11471l.m2951a(c11430he.f23010a, "reg_id", m5151a.m5138c());
                AbstractC11471l.m2951a(c11430he.f23010a, "reg_secret", m5151a.m5135d());
                AbstractC11471l.m2951a(c11430he.f23010a, "accept_time", MiPushClient.getAcceptTime(context).replace(",", "-"));
                if (z) {
                    AbstractC11471l.m2951a(c11430he.f23010a, "aliases_md5", C11126w.m4949c(MiPushClient.getAllAlias(context)));
                    AbstractC11471l.m2951a(c11430he.f23010a, "topics_md5", C11126w.m4949c(MiPushClient.getAllTopic(context)));
                    AbstractC11471l.m2951a(c11430he.f23010a, "accounts_md5", C11126w.m4949c(MiPushClient.getAllUserAccount(context)));
                } else {
                    AbstractC11471l.m2951a(c11430he.f23010a, "aliases", C11126w.m4948d(MiPushClient.getAllAlias(context)));
                    AbstractC11471l.m2951a(c11430he.f23010a, "topics", C11126w.m4948d(MiPushClient.getAllTopic(context)));
                    AbstractC11471l.m2951a(c11430he.f23010a, "user_accounts", C11126w.m4948d(MiPushClient.getAllUserAccount(context)));
                }
                C11118u.m5003a(context).m4986a((C11118u) c11430he, EnumC11404gf.Notification, false, (C11417gs) null);
            }
        });
    }

    /* renamed from: a */
    public static void m4953a(Context context, C11430he c11430he) {
        AbstractC11049b.m5282a("need to update local info with: " + c11430he.m3350a());
        String str = c11430he.m3350a().get("accept_time");
        if (str != null) {
            MiPushClient.removeAcceptTime(context);
            String[] split = str.split("-");
            if (split.length == 2) {
                MiPushClient.addAcceptTime(context, split[0], split[1]);
                if ("00:00".equals(split[0]) && "00:00".equals(split[1])) {
                    C11087b.m5151a(context).m5144a(true);
                } else {
                    C11087b.m5151a(context).m5144a(false);
                }
            }
        }
        String str2 = c11430he.m3350a().get("aliases");
        if (str2 != null) {
            MiPushClient.removeAllAliases(context);
            if (!"".equals(str2)) {
                for (String str3 : str2.split(",")) {
                    MiPushClient.addAlias(context, str3);
                }
            }
        }
        String str4 = c11430he.m3350a().get("topics");
        if (str4 != null) {
            MiPushClient.removeAllTopics(context);
            if (!"".equals(str4)) {
                for (String str5 : str4.split(",")) {
                    MiPushClient.addTopic(context, str5);
                }
            }
        }
        String str6 = c11430he.m3350a().get("user_accounts");
        if (str6 != null) {
            MiPushClient.removeAllAccounts(context);
            if ("".equals(str6)) {
                return;
            }
            for (String str7 : str6.split(",")) {
                MiPushClient.addAccount(context, str7);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static String m4949c(List<String> list) {
        String m4757a = C11184bb.m4757a(m4948d(list));
        return (TextUtils.isEmpty(m4757a) || m4757a.length() <= 4) ? "" : m4757a.substring(0, 4).toLowerCase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static String m4948d(List<String> list) {
        if (C11651z.m2259a(list)) {
            return "";
        }
        ArrayList<String> arrayList = new ArrayList(list);
        Collections.sort(arrayList, Collator.getInstance(Locale.CHINA));
        String str = "";
        for (String str2 : arrayList) {
            if (!TextUtils.isEmpty(str)) {
                str = str + ",";
            }
            str = str + str2;
        }
        return str;
    }
}
