package cn.sharesdk.framework.utils;

import cn.sharesdk.framework.ShareSDK;
import com.mob.commons.logcollector.DefaultLogsCollector;
import com.mob.tools.log.NLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SSDKLog {

    /* renamed from: a */
    private static SSDKLog f2942a;

    /* renamed from: b */
    private static NLog f2943b;

    /* renamed from: c */
    private final int f2944c = -1;

    /* renamed from: a */
    private boolean m21745a(NLog nLog) {
        return nLog != null;
    }

    private SSDKLog() {
        m21746a();
    }

    /* renamed from: a */
    public static NLog m21746a() {
        f2943b = NLog.getInstance("SHARESDK");
        DefaultLogsCollector.get().addSDK("SHARESDK", ShareSDK.SDK_VERSION_CODE);
        return f2943b;
    }

    /* renamed from: b */
    public static SSDKLog m21740b() {
        if (f2942a == null) {
            f2942a = new SSDKLog();
        }
        return f2942a;
    }

    /* renamed from: a */
    public final int m21742a(Throwable th) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(3, th);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public final int m21744a(Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(3, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public final int m21741a(Throwable th, Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(3, th, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public final int m21737b(Throwable th) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(5, th);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public final int m21739b(Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(5, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public final int m21736b(Throwable th, Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(5, th, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: a */
    public final int m21743a(String str) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(5, str, new Object[0]);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: c */
    public final int m21734c(Throwable th) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(4, th);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: c */
    public final int m21735c(Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(4, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: b */
    public final int m21738b(String str) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(4, str, new Object[0]);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: d */
    public final int m21732d(Throwable th) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(6, th);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    /* renamed from: d */
    public final int m21733d(Object obj, Object... objArr) {
        try {
            if (m21745a(f2943b)) {
                return f2943b.log(6, obj, objArr);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
