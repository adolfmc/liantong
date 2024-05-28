package com.alipay.sdk.tid;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.data.C2009c;
import com.alipay.sdk.packet.C2021b;
import com.alipay.sdk.packet.impl.C2027c;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.util.C2037a;
import com.alipay.sdk.util.C2040c;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TidHelper {
    public static Tid loadTID(Context context) {
        m20766a(context);
        Tid m20765a = m20765a(context, C2035b.m20758a(context));
        if (m20765a == null) {
            C2040c.m20714b("TidHelper.loadTID", "TidHelper:::loadTID > null");
        } else {
            C2040c.m20714b("TidHelper.loadTID", "TidHelper:::loadTID > " + m20765a.toString());
        }
        return m20765a;
    }

    public static synchronized Tid loadOrCreateTID(Context context) {
        synchronized (TidHelper.class) {
            C2040c.m20714b("TidHelper", "TidHelper.loadOrCreateTID");
            if (context == null) {
                C2000a.m20900a(context, "tid", "tid_context_null", "");
            }
            m20766a(context);
            Tid loadTID = loadTID(context);
            if (Tid.isEmpty(loadTID)) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    return null;
                }
                try {
                    loadTID = m20764b(context);
                } catch (Throwable unused) {
                }
            }
            return loadTID;
        }
    }

    public static synchronized String getTIDValue(Context context) {
        String tid;
        synchronized (TidHelper.class) {
            Tid loadOrCreateTID = loadOrCreateTID(context);
            tid = Tid.isEmpty(loadOrCreateTID) ? "" : loadOrCreateTID.getTid();
        }
        return tid;
    }

    public static boolean resetTID(Context context) throws Exception {
        C2040c.m20714b("TidHelper.resetTID", "resetTID");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new Exception("不能在主线程中调用此方法");
        }
        m20766a(context);
        clearTID(context);
        Tid tid = null;
        try {
            tid = m20764b(context);
        } catch (Throwable unused) {
        }
        return !Tid.isEmpty(tid);
    }

    public static void clearTID(Context context) {
        C2035b.m20758a(context).m20748g();
    }

    public static String getIMEI(Context context) {
        m20766a(context);
        return C2037a.m20728a(context).m20726b();
    }

    public static String getIMSI(Context context) {
        m20766a(context);
        return C2037a.m20728a(context).m20729a();
    }

    public static String getVirtualImei(Context context) {
        m20766a(context);
        return C2009c.m20855b().m20852c();
    }

    public static String getVirtualImsi(Context context) {
        m20766a(context);
        return C2009c.m20855b().m20850d();
    }

    /* renamed from: a */
    private static void m20766a(Context context) {
        if (context == null) {
            return;
        }
        C2033b.m20772a().m20771a(context, C2009c.m20855b());
    }

    /* renamed from: b */
    private static Tid m20764b(Context context) throws Exception {
        try {
            C2021b a = new C2027c().m20802a(context);
            if (a != null) {
                JSONObject jSONObject = new JSONObject(a.m20814b());
                C2035b m20758a = C2035b.m20758a(context);
                String optString = jSONObject.optString("tid");
                String string = jSONObject.getString("client_key");
                if (!TextUtils.isEmpty(optString) && !TextUtils.isEmpty(string)) {
                    m20758a.m20757a(optString, string);
                }
                return m20765a(context, m20758a);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static Tid m20765a(Context context, C2035b c2035b) {
        if (c2035b == null || c2035b.m20750e()) {
            return null;
        }
        return new Tid(c2035b.m20759a(), c2035b.m20754b(), c2035b.m20746i().longValue());
    }

    public static Tid loadLocalTid(Context context) {
        C2035b m20758a = C2035b.m20758a(context);
        if (m20758a.m20747h()) {
            return null;
        }
        return new Tid(m20758a.m20759a(), m20758a.m20754b(), m20758a.m20746i().longValue());
    }
}
