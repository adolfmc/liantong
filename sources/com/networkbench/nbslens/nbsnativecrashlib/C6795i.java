package com.networkbench.nbslens.nbsnativecrashlib;

import android.annotation.SuppressLint;
import android.os.Process;
import android.text.TextUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"StaticFieldLeak"})
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6795i implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private static final C6795i f17592a = new C6795i();

    /* renamed from: c */
    private int f17594c;

    /* renamed from: d */
    private String f17595d;

    /* renamed from: e */
    private String f17596e;

    /* renamed from: f */
    private String f17597f;

    /* renamed from: g */
    private boolean f17598g;

    /* renamed from: h */
    private String f17599h;

    /* renamed from: i */
    private int f17600i;

    /* renamed from: j */
    private int f17601j;

    /* renamed from: k */
    private int f17602k;

    /* renamed from: l */
    private boolean f17603l;

    /* renamed from: m */
    private boolean f17604m;

    /* renamed from: n */
    private boolean f17605n;

    /* renamed from: o */
    private int f17606o;

    /* renamed from: p */
    private String[] f17607p;

    /* renamed from: q */
    private InterfaceC6792f f17608q;

    /* renamed from: b */
    private final Date f17593b = new Date();

    /* renamed from: r */
    private Thread.UncaughtExceptionHandler f17609r = null;

    private C6795i() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C6795i m8432a() {
        return f17592a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m8431a(int i, String str, String str2, String str3, String str4, boolean z, int i2, int i3, int i4, boolean z2, boolean z3, boolean z4, int i5, String[] strArr, InterfaceC6792f interfaceC6792f) {
        this.f17594c = i;
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        this.f17595d = str;
        this.f17596e = str2;
        this.f17597f = str3;
        this.f17598g = z;
        this.f17599h = str4;
        this.f17600i = i2;
        this.f17601j = i3;
        this.f17602k = i4;
        this.f17603l = z2;
        this.f17604m = z3;
        this.f17605n = z4;
        this.f17606o = i5;
        this.f17607p = strArr;
        this.f17608q = interfaceC6792f;
        this.f17609r = Thread.getDefaultUncaughtExceptionHandler();
        try {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "JavaCrashHandler setDefaultUncaughtExceptionHandler failed", e);
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f17609r;
        if (uncaughtExceptionHandler != null) {
            Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        }
        try {
            m8429a(thread, th);
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "JavaCrashHandler handleException failed", e);
        }
        if (this.f17598g) {
            Thread.UncaughtExceptionHandler uncaughtExceptionHandler2 = this.f17609r;
            if (uncaughtExceptionHandler2 != null) {
                uncaughtExceptionHandler2.uncaughtException(thread, th);
                return;
            }
            return;
        }
        C6776a.m8468a().m8464b();
        Process.killProcess(this.f17594c);
        System.exit(10);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x012a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0131 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m8429a(java.lang.Thread r11, java.lang.Throwable r12) {
        /*
            Method dump skipped, instructions count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.nbslens.nbsnativecrashlib.C6795i.m8429a(java.lang.Thread, java.lang.Throwable):void");
    }

    /* renamed from: a */
    private String m8427a(Date date, Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        return C6805n.m8380a(this.f17593b, date, "java", this.f17596e, this.f17597f) + "pid: " + this.f17594c + ", tid: " + Process.myTid() + ", name: " + thread.getName() + "  >>> " + this.f17595d + " <<<\n\njava stacktrace:\n" + stringWriter2 + "\n";
    }

    /* renamed from: a */
    private String m8430a(Thread thread) {
        ArrayList<Pattern> arrayList;
        if (this.f17607p != null) {
            arrayList = new ArrayList<>();
            for (String str : this.f17607p) {
                try {
                    arrayList.add(Pattern.compile(str));
                } catch (Exception e) {
                    NBSNativeCrash.m8519d().mo8435d("nbscrash", "JavaCrashHandler pattern compile failed", e);
                }
            }
        } else {
            arrayList = null;
        }
        StringBuilder sb = new StringBuilder();
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
            Thread key = entry.getKey();
            StackTraceElement[] value = entry.getValue();
            if (!key.getName().equals(thread.getName()) && (arrayList == null || m8428a(arrayList, key.getName()))) {
                i2++;
                int i4 = this.f17606o;
                if (i4 <= 0 || i < i4) {
                    sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
                    sb.append("pid: ");
                    sb.append(this.f17594c);
                    sb.append(", tid: ");
                    sb.append(key.getId());
                    sb.append(", name: ");
                    sb.append(key.getName());
                    sb.append("  >>> ");
                    sb.append(this.f17595d);
                    sb.append(" <<<\n");
                    sb.append("\n");
                    sb.append("java stacktrace:\n");
                    for (StackTraceElement stackTraceElement : value) {
                        sb.append("    at ");
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    sb.append("\n");
                    i++;
                } else {
                    i3++;
                }
            }
        }
        if (allStackTraces.size() > 1) {
            if (i == 0) {
                sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
            }
            sb.append("total JVM threads (exclude the crashed thread): ");
            sb.append(allStackTraces.size() - 1);
            sb.append("\n");
            if (arrayList != null) {
                sb.append("JVM threads matched whitelist: ");
                sb.append(i2);
                sb.append("\n");
            }
            if (this.f17606o > 0) {
                sb.append("JVM threads ignored by max count limit: ");
                sb.append(i3);
                sb.append("\n");
            }
            sb.append("dumped JVM threads:");
            sb.append(i);
            sb.append("\n");
            sb.append("+++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++ +++\n");
        }
        return sb.toString();
    }

    /* renamed from: a */
    private boolean m8428a(ArrayList<Pattern> arrayList, String str) {
        Iterator<Pattern> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }
}
