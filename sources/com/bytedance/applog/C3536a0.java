package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.a0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3536a0 {

    /* renamed from: a */
    public static volatile InterfaceC3662p2 f8340a;

    /* renamed from: b */
    public static C3620l1 f8341b;
    @Nullable

    /* renamed from: c */
    public static C3726x f8342c;

    /* renamed from: d */
    public static final Object f8343d = new Object();

    /* renamed from: e */
    public static Boolean f8344e;

    /* renamed from: a */
    public static InterfaceC3662p2 m17345a(Context context, C3726x c3726x) {
        if (f8340a == null) {
            synchronized (C3536a0.class) {
                if (f8340a == null) {
                    if (context == null) {
                        throw new IllegalArgumentException("context == null");
                    }
                    f8342c = c3726x;
                    m17346a(context);
                    if (m17343b(context)) {
                        try {
                            f8340a = (InterfaceC3662p2) Class.forName("com.bytedance.applog.manager.newuser.DeviceParamsProvider").getConstructor(Context.class, C3620l1.class, C3726x.class).newInstance(context, f8341b, c3726x);
                            C3704u2.m17108a("DeviceRegisterParameterFactory create new user device param provider success", (Throwable) null);
                        } catch (Exception e) {
                            e.printStackTrace();
                            C3704u2.m17108a("DeviceRegisterParameterFactoryclass com.bytedance.applog.manager.newuser.DeviceParamsProvider not fount", e);
                        }
                    }
                    if (f8340a == null) {
                        f8340a = new C3603i2(context, c3726x, f8341b);
                    }
                }
            }
        }
        return f8340a;
    }

    /* renamed from: a */
    public static CharSequence m17344a(Context context, boolean z) {
        return C3609j2.m17257a(context.getPackageName() + "." + z);
    }

    /* renamed from: a */
    public static void m17346a(Context context) {
        if (f8341b == null) {
            f8341b = new C3620l1(context);
        }
    }

    /* renamed from: b */
    public static boolean m17343b(Context context) {
        boolean booleanValue;
        boolean booleanValue2;
        if (context == null || m17347a()) {
            return false;
        }
        synchronized (f8343d) {
            if (f8344e == null) {
                Boolean m17339d = m17339d(context);
                if (m17339d != null) {
                    if (m17341c(context) != m17339d) {
                        m17342b(context, m17339d.booleanValue());
                    }
                    booleanValue2 = m17339d.booleanValue();
                } else {
                    booleanValue2 = m17341c(context).booleanValue();
                }
                f8344e = Boolean.valueOf(booleanValue2);
                C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserMode() returned: " + f8344e, (Throwable) null);
            }
            booleanValue = f8344e.booleanValue();
        }
        return booleanValue;
    }

    /* renamed from: c */
    public static Boolean m17341c(Context context) {
        String str = null;
        C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeAccount", (Throwable) null);
        m17346a(context);
        try {
            str = f8341b.mo17065b("new_user");
        } catch (Exception e) {
            e.printStackTrace();
            C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeAccount", e);
        }
        return Boolean.valueOf(str);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0061 -> B:19:0x0062). Please submit an issue!!! */
    /* renamed from: d */
    public static Boolean m17339d(Context context) {
        Boolean bool;
        ClipboardManager clipboardManager;
        ClipData primaryClip;
        C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeClipboard", (Throwable) null);
        try {
            clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
        } catch (Throwable th) {
            th.printStackTrace();
            C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeClipboard failed", th);
        }
        if (clipboardManager != null && (primaryClip = clipboardManager.getPrimaryClip()) != null && primaryClip.getItemCount() > 0) {
            CharSequence text = primaryClip.getItemAt(0).getText();
            C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeClipboard clipboard text=" + ((Object) text), (Throwable) null);
            if (m17344a(context, true).equals(text)) {
                bool = true;
            } else if (m17344a(context, false).equals(text)) {
                bool = false;
            }
            C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeClipboard result=" + bool, (Throwable) null);
            return bool;
        }
        bool = null;
        C3704u2.m17108a("DeviceRegisterParameterFactory#isNewUserModeClipboard result=" + bool, (Throwable) null);
        return bool;
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: b */
    public static void m17342b(Context context, boolean z) {
        C3704u2.m17108a("DeviceRegisterParameterFactory#saveNewUserModeToAccount open=" + z, (Throwable) null);
        m17346a(context);
        try {
            f8341b.mo17067a("new_user", String.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static boolean m17347a() {
        C3726x c3726x = f8342c;
        return c3726x == null || !"local_test".equals(c3726x.f8897b.getChannel());
    }

    @SuppressLint({"MissingPermission"})
    /* renamed from: c */
    public static void m17340c(Context context, boolean z) {
        if (context == null || m17347a()) {
            return;
        }
        m17342b(context, z);
        C3704u2.m17108a("DeviceRegisterParameterFactory#saveNewUserModeToClipboard open=" + z, (Throwable) null);
        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService("clipboard");
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("", m17344a(context, z)));
                C3704u2.m17108a("DeviceRegisterParameterFactory#saveNewUserModeToClipboard success", (Throwable) null);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            C3704u2.m17108a("DeviceRegisterParameterFactory#saveNewUserModeToClipboard failed", th);
        }
        synchronized (f8343d) {
            f8344e = Boolean.valueOf(z);
        }
    }
}
