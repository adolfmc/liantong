package com.networkbench.nbslens.nbsnativecrashlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.networkbench.agent.impl.crash.NativeCrashInterface;
import java.io.File;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"StaticFieldLeak"})
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NativeHandler {

    /* renamed from: a */
    private static final NativeHandler f17542a = new NativeHandler();

    /* renamed from: c */
    private Context f17544c;

    /* renamed from: d */
    private boolean f17545d;

    /* renamed from: e */
    private InterfaceC6792f f17546e;

    /* renamed from: f */
    private boolean f17547f;

    /* renamed from: g */
    private boolean f17548g;

    /* renamed from: h */
    private InterfaceC6792f f17549h;

    /* renamed from: b */
    private long f17543b = 15000;

    /* renamed from: i */
    private boolean f17550i = false;

    private static native int nativeInit(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, boolean z, boolean z2, int i2, int i3, int i4, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i5, String[] strArr, boolean z8, boolean z9, int i6, int i7, int i8, boolean z10, boolean z11);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m8469b() {
    }

    private NativeHandler() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static NativeHandler m8473a() {
        return f17542a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m8472a(Context context, String str, String str2, String str3, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i4, String[] strArr, InterfaceC6792f interfaceC6792f, boolean z8, boolean z9, boolean z10, int i5, int i6, int i7, boolean z11, boolean z12, InterfaceC6792f interfaceC6792f2) {
        try {
            System.loadLibrary("nbscrash");
            this.f17544c = context;
            this.f17545d = z2;
            this.f17546e = interfaceC6792f;
            this.f17547f = z8;
            this.f17548g = z10;
            this.f17549h = interfaceC6792f2;
            this.f17543b = z9 ? 15000L : 30000L;
            try {
                if (nativeInit(Build.VERSION.SDK_INT, Build.VERSION.RELEASE, C6805n.m8379b(), Build.MANUFACTURER, Build.BRAND, Build.MODEL, Build.FINGERPRINT, str, str2, context.getApplicationInfo().nativeLibraryDir, str3, z, z2, i, i2, i3, z3, z4, z5, z6, z7, i4, strArr, z8, z9, i5, i6, i7, z11, z12) != 0) {
                    NBSNativeCrash.m8519d().mo8434e("nbscrash", "NativeHandler init failed");
                    return -3;
                }
                this.f17550i = true;
                return 0;
            } catch (Throwable th) {
                NBSNativeCrash.m8519d().mo8433e("nbscrash", "NativeHandler init failed", th);
                return -3;
            }
        } catch (Throwable th2) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "NativeHandler System.loadLibrary failed", th2);
            return -2;
        }
    }

    /* renamed from: a */
    private static String m8470a(boolean z, String str) {
        try {
            for (Map.Entry<Thread, StackTraceElement[]> entry : Thread.getAllStackTraces().entrySet()) {
                Thread key = entry.getKey();
                if ((z && key.getName().equals("main")) || (!z && key.getName().contains(str))) {
                    StringBuilder sb = new StringBuilder();
                    StackTraceElement[] value = entry.getValue();
                    for (StackTraceElement stackTraceElement : value) {
                        sb.append("    at ");
                        sb.append(stackTraceElement.toString());
                        sb.append("\n");
                    }
                    return sb.toString();
                }
            }
            return null;
        } catch (Exception e) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "NativeHandler getStacktraceByThreadName failed", e);
            return null;
        }
    }

    private static void crashCallback(String str, String str2, boolean z, boolean z2, String str3) {
        InterfaceC6794h m8519d = NBSNativeCrash.m8519d();
        m8519d.mo8440b("nbscrash", "logPath:" + str + ", emergency:" + str2 + ", dumpJavaStacktrace:" + z + ", isMainThread:" + z2 + ", threadName:" + str3);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                String m8470a = m8470a(z2, str3);
                InterfaceC6794h m8519d2 = NBSNativeCrash.m8519d();
                m8519d2.mo8440b("nbscrash", "in java stacktrace:" + m8470a);
                if (!TextUtils.isEmpty(m8470a)) {
                    C6798l.m8411a(str, "java stacktrace", m8470a);
                }
            }
            C6798l.m8411a(str, "nbstag", "yes");
        }
        InterfaceC6792f interfaceC6792f = m8473a().f17546e;
        if (interfaceC6792f != null) {
            try {
                interfaceC6792f.m8444a(str, str2);
            } catch (Exception e) {
                NBSNativeCrash.m8519d().mo8435d("nbscrash", "NativeHandler native crash callback.onCrash failed", e);
            }
        }
        if (!m8473a().f17545d) {
            C6776a.m8468a().m8464b();
        }
        m8471a(str, z2);
    }

    /* renamed from: a */
    private static void m8471a(String str, boolean z) {
        try {
            C6796j c6796j = new C6796j(C6802m.m8395a(str), z);
            String jsonArray = c6796j.m8423b().toString();
            InterfaceC6794h m8519d = NBSNativeCrash.m8519d();
            m8519d.mo8440b("nbscrash", "store info:" + jsonArray);
            NativeCrashInterface.saveFeature();
            try {
                NativeCrashInterface.storeUUID(c6796j.m8426a());
            } catch (Throwable unused) {
            }
            NativeCrashInterface.storeCrashInfo(System.currentTimeMillis() + "", jsonArray);
        } catch (Throwable th) {
            NBSNativeCrash.m8519d().mo8433e("nbscrash", "error saveInfo:", th);
        }
    }

    private static void traceCallback(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        C6798l.m8411a(str, "memory info", C6805n.m8377c());
        C6798l.m8411a(str, "foreground", C6776a.m8468a().m8463c() ? "yes" : "no");
        if (m8473a().f17548g && !C6805n.m8383a(m8473a().f17544c, m8473a().f17543b)) {
            C6781e.m8462a().m8460a(new File(str));
        } else if (C6781e.m8462a().m8452c()) {
            String str3 = str.substring(0, str.length() - 15) + ".anr.nbscrash";
            File file = new File(str);
            if (!file.renameTo(new File(str3))) {
                C6781e.m8462a().m8460a(file);
                return;
            }
            InterfaceC6792f interfaceC6792f = m8473a().f17549h;
            if (interfaceC6792f != null) {
                try {
                    interfaceC6792f.m8444a(str3, str2);
                } catch (Exception e) {
                    NBSNativeCrash.m8519d().mo8435d("nbscrash", "NativeHandler ANR callback.onCrash failed", e);
                }
            }
        }
    }
}
