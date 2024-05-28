package com.mob.commons;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6031c;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.q */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5868q {

    /* renamed from: a */
    public static volatile String f14468a = null;

    /* renamed from: b */
    public static volatile String f14469b = null;

    /* renamed from: c */
    public static volatile String f14470c = null;

    /* renamed from: d */
    public static volatile String f14471d = null;

    /* renamed from: e */
    public static volatile InternationalDomain f14472e = null;

    /* renamed from: f */
    public static volatile boolean f14473f = false;

    /* renamed from: g */
    public static volatile boolean f14474g = false;

    /* renamed from: h */
    public static volatile boolean f14475h = true;

    /* renamed from: i */
    public static volatile boolean f14476i;

    /* renamed from: j */
    public static volatile String f14477j;

    /* renamed from: k */
    private static AtomicBoolean f14478k = new AtomicBoolean(false);

    /* renamed from: l */
    private static HashMap<String, HashMap<String, Object>> f14479l = new HashMap<>();

    /* renamed from: a */
    public static void m12207a(Context context) {
        try {
            if (f14478k.compareAndSet(false, true)) {
                try {
                    if (f14468a == null) {
                        String str = (String) MobMeta.get(null, "Mob-AppKey", String.class, null);
                        if (!TextUtils.isEmpty(str)) {
                            f14468a = str;
                            f14470c = str;
                            C5741aa.m12650a().m12621e(str);
                        } else {
                            String m12613k = C5741aa.m12650a().m12613k();
                            if (TextUtils.isEmpty(m12613k)) {
                                m12613k = C5879w.m12151i();
                            }
                            if (!TextUtils.isEmpty(m12613k)) {
                                f14470c = m12613k;
                                C5741aa.m12650a().m12621e(m12613k);
                            }
                        }
                    }
                    if (f14469b == null) {
                        String str2 = (String) MobMeta.get(null, "Mob-AppSecret", String.class, null);
                        if (TextUtils.isEmpty(str2)) {
                            str2 = (String) MobMeta.get(null, "Mob-AppSeret", String.class, null);
                        }
                        if (!TextUtils.isEmpty(str2)) {
                            f14469b = str2;
                            f14471d = str2;
                            C5741aa.m12650a().m12618f(str2);
                        } else {
                            String m12612l = C5741aa.m12650a().m12612l();
                            if (!TextUtils.isEmpty(m12612l)) {
                                f14471d = m12612l;
                            }
                        }
                    }
                } catch (Throwable unused) {
                }
                String str3 = (String) MobMeta.get(null, m12203b("006Mejdcce?cVchFd"), String.class, null);
                if (str3 != null) {
                    f14472e = InternationalDomain.domainOf(str3);
                }
                f14477j = (String) MobMeta.get(null, "Mob-OdVivoAppId", String.class, null);
                f14473f = ((Boolean) MobMeta.get(null, m12203b("009.gbdcedgjei!hhi+eg"), Boolean.TYPE, false)).booleanValue();
                f14474g = ((Boolean) MobMeta.get(null, m12203b("006Igbdcedgjfhgf"), Boolean.TYPE, false)).booleanValue();
                f14475h = ((Boolean) MobMeta.get(null, "Mob-elog", Boolean.TYPE, true)).booleanValue();
                f14476i = ((Boolean) MobMeta.get(null, "Mob-GPP", Boolean.TYPE, false)).booleanValue();
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    public static <T> T m12204a(java.lang.String r9, java.lang.Class<T> r10, com.mob.commons.MobProduct r11) {
        /*
            Method dump skipped, instructions count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5868q.m12204a(java.lang.String, java.lang.Class, com.mob.commons.MobProduct):java.lang.Object");
    }

    /* renamed from: a */
    public static <T> T m12205a(String str) {
        try {
            Bundle bundle = C6031c.m11708a(MobSDK.getContext()).m11704d().mo11581a(MobSDK.getContext().getPackageName(), 128).metaData;
            if (bundle != null) {
                T t = (T) bundle.get(str);
                if (m12203b("009;gbdcedgjei1hhiXeg").equals(str) && t != null && (t instanceof String)) {
                    return (T) Boolean.valueOf(m12203b("003Ucj$e^eg").equalsIgnoreCase(String.valueOf(t)));
                }
                if (t != null) {
                    return t;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    private static String m12206a(MobProduct mobProduct) {
        if (mobProduct != null) {
            try {
                String productTag = mobProduct.getProductTag();
                return "SHARESDK".equals(productTag) ? "ShareSDK.mt" : m12203b("006'digbdidiejhb").equals(productTag) ? "SMSSDK.mt" : "MOBLINK".equals(productTag) ? "MobLink.mt" : "MOBPUSH".equals(productTag) ? "MobPush.mt" : m12203b("009Adifffifhfffgdhekhj").equals(productTag) ? "SecVerify.mt" : "MobSDK.mt";
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return "MobSDK.mt";
            }
        }
        return "MobSDK.mt";
    }

    /* renamed from: b */
    public static String m12203b(String str) {
        return C5873u.m12180a(str, 98);
    }
}
