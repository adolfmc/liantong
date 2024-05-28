package com.mob.tools.log;

import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.mob.commons.C5860p;
import com.mob.commons.C5869r;
import com.mob.tools.proguard.ClassKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NLog implements ClassKeeper, PublicMemberKeeper {

    /* renamed from: a */
    private static final HashMap<String, NLog> f14988a = new HashMap<>();

    /* renamed from: b */
    private static final HashMap<String, String> f14989b = new HashMap<>();

    /* renamed from: c */
    private String f14990c;

    /* renamed from: d */
    private int f14991d;

    /* renamed from: e */
    private boolean f14992e;

    public NLog setCollector(LogCollector logCollector) {
        return this;
    }

    public static NLog getInstance(String str, int i, String str2) {
        NLog nLog;
        synchronized (f14988a) {
            nLog = f14988a.get(str);
            if (nLog == null) {
                nLog = new NLog(str, i);
                f14989b.put(str, str2);
                f14988a.put(str, nLog);
            }
        }
        return nLog;
    }

    @Deprecated
    public static NLog getInstance(String str) {
        NLog nLog;
        synchronized (f14988a) {
            nLog = f14988a.get(str);
            if (nLog == null) {
                nLog = new NLog(str, -1);
                f14989b.put(str, null);
                f14988a.put(str, nLog);
            }
        }
        return nLog;
    }

    public NLog() {
        this.f14992e = false;
        this.f14990c = null;
        this.f14991d = -1;
    }

    private NLog(String str, int i) {
        this.f14992e = false;
        this.f14990c = str;
        this.f14991d = i;
    }

    public static void setCollector(String str, LogCollector logCollector) {
        getInstance(str).setCollector(logCollector);
    }

    public static NLog getInstanceForSDK(String str, boolean z) {
        return getInstance(str);
    }

    /* renamed from: dg */
    public final void m11339dg() {
        this.f14992e = true;
    }

    /* renamed from: v */
    public final int m11329v(Throwable th) {
        return log(2, th);
    }

    /* renamed from: v */
    public final int m11330v(Object obj, Object... objArr) {
        return log(2, obj, objArr);
    }

    /* renamed from: v */
    public final int m11328v(Throwable th, Object obj, Object... objArr) {
        return log(2, th, obj, objArr);
    }

    /* renamed from: d */
    public final int m11341d(Throwable th) {
        return log(3, th);
    }

    /* renamed from: d */
    public final int m11342d(Object obj, Object... objArr) {
        return log(3, obj, objArr);
    }

    /* renamed from: d */
    public final int m11340d(Throwable th, Object obj, Object... objArr) {
        return log(3, th, obj, objArr);
    }

    /* renamed from: w */
    public final int m11325w(Throwable th) {
        return log(5, th);
    }

    /* renamed from: w */
    public final int m11327w(Object obj, Object... objArr) {
        return log(5, obj, objArr);
    }

    /* renamed from: w */
    public final int m11324w(Throwable th, Object obj, Object... objArr) {
        return log(5, th, obj, objArr);
    }

    /* renamed from: w */
    public final int m11326w(String str) {
        return log(5, str, new Object[0]);
    }

    /* renamed from: i */
    public final int m11332i(Throwable th) {
        return log(4, th);
    }

    /* renamed from: i */
    public final int m11331i(Throwable th, Object obj, Object... objArr) {
        return log(4, th, obj, objArr);
    }

    /* renamed from: i */
    public final int m11334i(Object obj, Object... objArr) {
        return log(4, obj, objArr);
    }

    /* renamed from: i */
    public final int m11333i(String str) {
        return log(4, str, new Object[0]);
    }

    /* renamed from: e */
    public final int m11336e(Throwable th) {
        return log(6, th);
    }

    /* renamed from: e */
    public final int m11335e(Throwable th, Object obj, Object... objArr) {
        return log(6, th, obj, objArr);
    }

    /* renamed from: e */
    public final int m11338e(Object obj, Object... objArr) {
        return log(6, obj, objArr);
    }

    /* renamed from: e */
    public final int m11337e(String str) {
        return log(6, str, new Object[0]);
    }

    public final int log(int i, Throwable th) {
        return m11346a(i, 0, m11345a(th));
    }

    public final int log(int i, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        return m11346a(i, 0, obj2);
    }

    public final int log(int i, Throwable th, Object obj, Object... objArr) {
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        if (objArr.length > 0) {
            obj2 = String.format(obj2, objArr);
        }
        sb.append(obj2);
        sb.append('\n');
        sb.append(m11345a(th));
        return m11346a(i, 0, sb.toString());
    }

    public final void error(Throwable th) {
        error(m11345a(th));
    }

    public final void error(String str) {
        m11337e(str);
    }

    public final void crash(Throwable th) {
        m11346a(6, 1, m11344b(th));
    }

    /* renamed from: a */
    private String m11345a(Throwable th) {
        try {
            return Log.getStackTraceString(th);
        } catch (Throwable th2) {
            if (th2 instanceof OutOfMemoryError) {
                return C5869r.m12200a("023^eeZfiMej7idc5dlekdj?dcf*ej0i3djdiAe@eeifededdf");
            }
            return th2.getMessage();
        }
    }

    /* renamed from: a */
    private int m11346a(int i, int i2, String str) {
        try {
            String str2 = Process.myPid() + "-" + Process.myTid() + "(" + Thread.currentThread().getName() + ") " + str;
            boolean z = this.f14992e;
            if (i2 == 1) {
                String str3 = this.f14990c;
                int i3 = this.f14991d;
                if (!f14989b.isEmpty()) {
                    NLog nLog = null;
                    Iterator<Map.Entry<String, String>> it = f14989b.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry<String, String> next = it.next();
                        if (next.getValue() != null && str.contains(next.getValue())) {
                            nLog = f14988a.get(next.getKey());
                            break;
                        }
                    }
                    if (nLog != null) {
                        str3 = nLog.f14990c;
                        i3 = nLog.f14991d;
                    }
                }
                C5860p.m12221a().m12218a(1, str3, i3, str2);
            }
            C5860p.m12221a().m12219a(i, str2);
            return 0;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: b */
    private String m11344b(Throwable th) {
        try {
            String name = th.getClass().getName();
            String m11343c = m11343c(th);
            String stackTraceElement = th.getStackTrace().length > 0 ? th.getStackTrace()[0].toString() : "";
            Throwable th2 = th;
            while (th2 != null && th2.getCause() != null) {
                th2 = th2.getCause();
            }
            if (th2 != null && th2 != th) {
                return name + ":" + m11343c + "\n" + stackTraceElement + "\n......\nCaused by:\n" + m11345a(th2);
            }
            return m11345a(th);
        } catch (Throwable unused) {
            return m11345a(th);
        }
    }

    /* renamed from: c */
    private static String m11343c(Throwable th) {
        String message = th.getMessage();
        if (TextUtils.isEmpty(message)) {
            return "";
        }
        if (message.length() <= 1000) {
            return message;
        }
        return message.substring(0, 1000) + "\n[Message over limit size:1000, cut!]";
    }
}
