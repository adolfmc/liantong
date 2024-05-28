package com.networkbench.agent.impl.harvest.p261b;

import android.content.SharedPreferences;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ConfigurationName;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6458a {

    /* renamed from: f */
    private static String f16327f = "NBS_Init_State_" + ConfigurationName.processName;

    /* renamed from: a */
    public static String f16322a = "customError";

    /* renamed from: b */
    public static String f16323b = "crash";

    /* renamed from: c */
    public static String f16324c = "customAction";

    /* renamed from: d */
    public static String f16325d = "isIntDcSucceed";

    /* renamed from: e */
    public static boolean f16326e = true;

    /* renamed from: a */
    public static void m9944a(String str, int i) {
        try {
            if (!f16326e) {
                C6396h.m10125q("SDK已经init成功了  , 不再修改崩溃文件 ,  NBSInitState isContinueAdd : " + f16326e);
                return;
            }
            SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f16327f, 0).edit();
            edit.putInt(str, i);
            edit.commit();
            if (str.startsWith("isInt")) {
                C6396h.m10125q("putValueInSp    isIntDcSucceed: " + i);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: b */
    public static void m9942b(String str, int i) {
        try {
            SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f16327f, 0).edit();
            edit.putInt(str, i);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static synchronized int m9945a(String str) {
        int i;
        synchronized (C6458a.class) {
            i = C6638h.m8963w().m9076K().getSharedPreferences(f16327f, 0).getInt(str, 1);
        }
        return i;
    }

    /* renamed from: a */
    public static synchronized void m9946a() {
        synchronized (C6458a.class) {
            SharedPreferences.Editor edit = C6638h.m8963w().m9076K().getSharedPreferences(f16327f, 0).edit();
            edit.clear();
            edit.commit();
            m9942b(f16325d, 0);
            C6396h.m10125q(" NBSAgent.getImpl().getSaveState().getFeatures() : " + NBSAgent.getImpl().m9147p().m8866H());
            m9944a(ConfigurationName.oldFeatures, NBSAgent.getImpl().m9147p().m8866H());
            C6396h.m10125q(" NBSInitState clean : ");
            f16326e = false;
        }
    }

    /* renamed from: b */
    public static synchronized int m9943b() {
        int m9945a;
        synchronized (C6458a.class) {
            m9945a = m9945a(ConfigurationName.oldFeatures);
        }
        return m9945a;
    }
}
