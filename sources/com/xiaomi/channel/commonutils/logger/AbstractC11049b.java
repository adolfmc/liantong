package com.xiaomi.channel.commonutils.logger;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.xiaomi.push.C11469j;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.channel.commonutils.logger.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC11049b {

    /* renamed from: a */
    private static int f21275a = 2;

    /* renamed from: a */
    private static Context f21276a;

    /* renamed from: a */
    private static boolean f21282a;

    /* renamed from: b */
    private static boolean f21284b;

    /* renamed from: a */
    private static String f21279a = "XMPush-" + Process.myPid();

    /* renamed from: a */
    private static LoggerInterface f21277a = new C11050a();

    /* renamed from: a */
    private static final HashMap<Integer, Long> f21280a = new HashMap<>();

    /* renamed from: b */
    private static final HashMap<Integer, String> f21283b = new HashMap<>();

    /* renamed from: a */
    private static final Integer f21278a = -1;

    /* renamed from: a */
    private static AtomicInteger f21281a = new AtomicInteger(1);

    /* renamed from: a */
    private static boolean m5293a() {
        return false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.channel.commonutils.logger.b$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C11050a implements LoggerInterface {

        /* renamed from: a */
        private String f21285a = AbstractC11049b.f21279a;

        C11050a() {
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void setTag(String str) {
            this.f21285a = str;
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void log(String str) {
            Log.v(this.f21285a, str);
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void log(String str, Throwable th) {
            Log.v(this.f21285a, str, th);
        }
    }

    /* renamed from: a */
    public static void m5286a(LoggerInterface loggerInterface) {
        f21277a = loggerInterface;
    }

    /* renamed from: a */
    private static boolean m5291a(int i) {
        return i >= f21275a || m5293a();
    }

    /* renamed from: a */
    public static void m5282a(String str) {
        if (m5291a(2)) {
            m5290a(2, m5283a(str));
        }
    }

    /* renamed from: a */
    public static void m5280a(String str, String str2) {
        if (m5291a(2)) {
            m5290a(2, m5273b(str, str2));
        }
    }

    /* renamed from: a */
    public static void m5277a(String str, Object... objArr) {
        if (m5291a(2)) {
            m5290a(2, m5278a(str, objArr));
        }
    }

    /* renamed from: b */
    public static void m5274b(String str) {
        if (m5291a(0)) {
            m5290a(0, m5283a(str));
        }
    }

    /* renamed from: c */
    public static void m5270c(String str) {
        if (m5291a(0)) {
            m5290a(1, m5283a(str));
        }
    }

    /* renamed from: b */
    public static void m5272b(String str, String str2) {
        if (m5291a(1)) {
            m5290a(1, m5273b(str, str2));
        }
    }

    /* renamed from: b */
    public static void m5271b(String str, Object... objArr) {
        if (m5291a(1)) {
            m5290a(1, m5278a(str, objArr));
        }
    }

    /* renamed from: a */
    public static void m5279a(String str, Throwable th) {
        if (m5291a(4)) {
            m5289a(4, m5283a(str), th);
        }
    }

    /* renamed from: a */
    public static void m5276a(Throwable th) {
        if (m5291a(4)) {
            m5288a(4, th);
        }
    }

    /* renamed from: d */
    public static void m5268d(String str) {
        if (m5291a(4)) {
            m5290a(4, m5283a(str));
        }
    }

    /* renamed from: c */
    public static void m5269c(String str, String str2) {
        if (m5291a(4)) {
            m5290a(4, m5273b(str, str2));
        }
    }

    /* renamed from: a */
    public static Integer m5284a(String str) {
        if (f21275a <= 1) {
            Integer valueOf = Integer.valueOf(f21281a.incrementAndGet());
            f21280a.put(valueOf, Long.valueOf(System.currentTimeMillis()));
            f21283b.put(valueOf, str);
            LoggerInterface loggerInterface = f21277a;
            loggerInterface.log(str + " starts");
            return valueOf;
        }
        return f21278a;
    }

    /* renamed from: a */
    public static void m5285a(Integer num) {
        if (f21275a > 1 || !f21280a.containsKey(num)) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - f21280a.remove(num).longValue();
        LoggerInterface loggerInterface = f21277a;
        loggerInterface.log(f21283b.remove(num) + " ends in " + currentTimeMillis + " ms");
    }

    /* renamed from: a */
    public static void m5290a(int i, String str) {
        if (i >= f21275a) {
            f21277a.log(str);
        } else if (m5293a()) {
            Log.d("MyLog", "-->log(" + i + "): " + str);
        }
    }

    /* renamed from: a */
    public static void m5288a(int i, Throwable th) {
        if (i >= f21275a) {
            f21277a.log("", th);
        } else if (m5293a()) {
            Log.w("MyLog", "-->log(" + i + "): ", th);
        }
    }

    /* renamed from: a */
    public static void m5289a(int i, String str, Throwable th) {
        if (i >= f21275a) {
            f21277a.log(str, th);
        } else if (m5293a()) {
            Log.w("MyLog", "-->log(" + i + "): " + str, th);
        }
    }

    /* renamed from: a */
    private static String m5283a(String str) {
        return m5275b() + str;
    }

    /* renamed from: b */
    private static String m5273b(String str, String str2) {
        return m5275b() + m5281a(str, str2);
    }

    /* renamed from: a */
    private static String m5278a(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Tid:");
        sb.append(Thread.currentThread().getId());
        sb.append("] ");
        sb.append("[");
        sb.append(str);
        sb.append("] ");
        if (objArr != null && objArr.length > 0) {
            for (Object obj : objArr) {
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m5281a(String str, String str2) {
        return "[" + str + "] " + str2;
    }

    /* renamed from: b */
    private static String m5275b() {
        return "[Tid:" + Thread.currentThread().getId() + "] ";
    }

    /* renamed from: a */
    public static void m5292a(int i) {
        if (i < 0 || i > 5) {
            m5290a(2, "set log level as " + i);
        }
        f21275a = i;
    }

    /* renamed from: a */
    public static int m5295a() {
        return f21275a;
    }

    /* renamed from: a */
    public static void m5287a(Context context) {
        f21276a = context;
        if (C11469j.m2972a(context)) {
            f21282a = true;
        }
        if (C11469j.m2974a()) {
            f21284b = true;
        }
    }

    /* renamed from: e */
    public static void m5266e(String str) {
        if (f21282a) {
            m5282a(str);
            return;
        }
        Log.w(f21279a, m5283a(str));
        if (f21284b) {
            return;
        }
        m5282a(str);
    }

    /* renamed from: d */
    public static void m5267d(String str, String str2) {
        if (f21282a) {
            m5280a(str, str2);
            return;
        }
        Log.w(f21279a, m5273b(str, str2));
        if (f21284b) {
            return;
        }
        m5280a(str, str2);
    }
}
