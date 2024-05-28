package com.huawei.hms.base.log;

import android.os.Process;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* renamed from: com.huawei.hms.base.log.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class LogRecord {

    /* renamed from: b */
    private String f11055b;

    /* renamed from: c */
    private String f11056c;

    /* renamed from: d */
    private int f11057d;

    /* renamed from: g */
    private String f11060g;

    /* renamed from: h */
    private int f11061h;

    /* renamed from: i */
    private int f11062i;

    /* renamed from: j */
    private int f11063j;

    /* renamed from: a */
    private final StringBuilder f11054a = new StringBuilder();

    /* renamed from: e */
    private long f11058e = 0;

    /* renamed from: f */
    private long f11059f = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LogRecord(int i, String str, int i2, String str2) {
        this.f11056c = "HMS";
        this.f11063j = i;
        this.f11055b = str;
        this.f11057d = i2;
        if (str2 != null) {
            this.f11056c = str2;
        }
        m15168b();
    }

    /* renamed from: a */
    public static String m15172a(int i) {
        switch (i) {
            case 3:
                return "D";
            case 4:
                return "I";
            case 5:
                return "W";
            case 6:
                return "E";
            default:
                return String.valueOf(i);
        }
    }

    /* renamed from: b */
    private LogRecord m15168b() {
        this.f11058e = System.currentTimeMillis();
        Thread currentThread = Thread.currentThread();
        this.f11059f = currentThread.getId();
        this.f11061h = Process.myPid();
        StackTraceElement[] stackTrace = currentThread.getStackTrace();
        int length = stackTrace.length;
        int i = this.f11063j;
        if (length > i) {
            StackTraceElement stackTraceElement = stackTrace[i];
            this.f11060g = stackTraceElement.getFileName();
            this.f11062i = stackTraceElement.getLineNumber();
        }
        return this;
    }

    /* renamed from: c */
    public String m15166c() {
        StringBuilder sb = new StringBuilder();
        m15167b(sb);
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        m15167b(sb);
        m15170a(sb);
        return sb.toString();
    }

    /* renamed from: a */
    public <T> LogRecord m15171a(T t) {
        this.f11054a.append(t);
        return this;
    }

    /* renamed from: a */
    public LogRecord m15169a(Throwable th) {
        m15171a((LogRecord) '\n').m15171a((LogRecord) Log.getStackTraceString(th));
        return this;
    }

    /* renamed from: a */
    public String m15173a() {
        StringBuilder sb = new StringBuilder();
        m15170a(sb);
        return sb.toString();
    }

    /* renamed from: a */
    private StringBuilder m15170a(StringBuilder sb) {
        sb.append(' ');
        sb.append(this.f11054a.toString());
        return sb;
    }

    /* renamed from: b */
    private StringBuilder m15167b(StringBuilder sb) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        sb.append('[');
        sb.append(simpleDateFormat.format(Long.valueOf(this.f11058e)));
        String m15172a = m15172a(this.f11057d);
        sb.append(' ');
        sb.append(m15172a);
        sb.append('/');
        sb.append(this.f11056c);
        sb.append('/');
        sb.append(this.f11055b);
        sb.append(' ');
        sb.append(this.f11061h);
        sb.append(':');
        sb.append(this.f11059f);
        sb.append(' ');
        sb.append(this.f11060g);
        sb.append(':');
        sb.append(this.f11062i);
        sb.append(']');
        return sb;
    }
}
