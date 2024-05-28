package p001a.p058b.p062b.p063a.p064a.p081k;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.k.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C0774f {

    /* renamed from: a */
    public static final InterfaceC3321a f2390a = C0749a.f2299a;

    /* JADX WARN: Removed duplicated region for block: B:16:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0066  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m22238a(android.content.Context r5) {
        /*
            android.net.NetworkInfo r0 = m22232e(r5)     // Catch: java.lang.SecurityException -> L92
            if (r0 != 0) goto L9
            java.lang.String r5 = "none"
            return r5
        L9:
            boolean r1 = m22237a(r0)
            if (r1 != 0) goto L12
            java.lang.String r5 = "none"
            return r5
        L12:
            int r1 = r0.getType()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L1f
            switch(r1) {
                case 2: goto L1f;
                case 3: goto L1f;
                case 4: goto L1f;
                case 5: goto L1f;
                default: goto L1d;
            }
        L1d:
            r1 = r3
            goto L20
        L1f:
            r1 = r2
        L20:
            if (r1 == 0) goto L66
            java.lang.String r0 = "phone"
            java.lang.Object r5 = r5.getSystemService(r0)
            android.telephony.TelephonyManager r5 = (android.telephony.TelephonyManager) r5
            java.lang.String r5 = r5.getNetworkOperatorName()
            java.lang.String r0 = android.os.Build.PRODUCT
            java.lang.String r1 = "google_sdk"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L58
            java.lang.String r0 = android.os.Build.PRODUCT
            java.lang.String r1 = "sdk"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L58
            java.lang.String r0 = android.os.Build.PRODUCT
            java.lang.String r1 = "sdk_x86"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L58
            java.lang.String r0 = android.os.Build.FINGERPRINT
            java.lang.String r1 = "generic"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L57
            goto L58
        L57:
            r2 = r3
        L58:
            java.lang.String r0 = "Android"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L65
            if (r2 == 0) goto L65
            java.lang.String r5 = "wifi"
        L65:
            return r5
        L66:
            boolean r5 = m22235b(r0)
            if (r5 == 0) goto L70
            java.lang.String r5 = "wifi"
            return r5
        L70:
            com.baidu.uaq.agent.android.logging.a r5 = p001a.p058b.p062b.p063a.p064a.p081k.C0774f.f2390a
            r1 = 2
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r4 = r0.getTypeName()
            r1[r3] = r4
            int r0 = r0.getType()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1[r2] = r0
            java.lang.String r0 = "Unknown network type: {0} [{1}]"
            java.lang.String r0 = java.text.MessageFormat.format(r0, r1)
            r5.warning(r0)
            java.lang.String r5 = "unknown"
            return r5
        L92:
            java.lang.String r5 = "unknown"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p081k.C0774f.m22238a(android.content.Context):java.lang.String");
    }

    /* renamed from: a */
    public static boolean m22237a(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0062 A[RETURN] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m22236b(android.content.Context r1) {
        /*
            android.net.NetworkInfo r1 = m22232e(r1)     // Catch: java.lang.SecurityException -> L66
            if (r1 != 0) goto L9
            java.lang.String r1 = "none"
            return r1
        L9:
            boolean r0 = m22237a(r1)
            if (r0 != 0) goto L12
            java.lang.String r1 = "none"
            return r1
        L12:
            boolean r0 = m22235b(r1)
            if (r0 == 0) goto L1c
            java.lang.String r1 = "wifi"
            return r1
        L1c:
            int r0 = r1.getType()
            if (r0 == 0) goto L27
            switch(r0) {
                case 2: goto L27;
                case 3: goto L27;
                case 4: goto L27;
                case 5: goto L27;
                default: goto L25;
            }
        L25:
            r0 = 0
            goto L28
        L27:
            r0 = 1
        L28:
            if (r0 == 0) goto L62
            int r1 = r1.getSubtype()
            switch(r1) {
                case 1: goto L5f;
                case 2: goto L5c;
                case 3: goto L59;
                case 4: goto L56;
                case 5: goto L53;
                case 6: goto L50;
                case 7: goto L4d;
                case 8: goto L4a;
                case 9: goto L47;
                case 10: goto L44;
                case 11: goto L41;
                case 12: goto L3e;
                case 13: goto L3b;
                case 14: goto L38;
                case 15: goto L35;
                default: goto L31;
            }
        L31:
            java.lang.String r1 = "unknown"
            goto L61
        L35:
            java.lang.String r1 = "HSPAP"
            goto L61
        L38:
            java.lang.String r1 = "HRPD"
            goto L61
        L3b:
            java.lang.String r1 = "LTE"
            goto L61
        L3e:
            java.lang.String r1 = "EVDO rev B"
            goto L61
        L41:
            java.lang.String r1 = "IDEN"
            goto L61
        L44:
            java.lang.String r1 = "HSPA"
            goto L61
        L47:
            java.lang.String r1 = "HSUPA"
            goto L61
        L4a:
            java.lang.String r1 = "HSDPA"
            goto L61
        L4d:
            java.lang.String r1 = "1xRTT"
            goto L61
        L50:
            java.lang.String r1 = "EVDO rev A"
            goto L61
        L53:
            java.lang.String r1 = "EVDO rev 0"
            goto L61
        L56:
            java.lang.String r1 = "CDMA"
            goto L61
        L59:
            java.lang.String r1 = "UMTS"
            goto L61
        L5c:
            java.lang.String r1 = "EDGE"
            goto L61
        L5f:
            java.lang.String r1 = "GPRS"
        L61:
            return r1
        L62:
            java.lang.String r1 = "unknown"
            return r1
        L66:
            java.lang.String r1 = "unknown"
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p001a.p058b.p062b.p063a.p064a.p081k.C0774f.m22236b(android.content.Context):java.lang.String");
    }

    /* renamed from: b */
    public static boolean m22235b(NetworkInfo networkInfo) {
        int type = networkInfo.getType();
        return type == 1 || type == 9 || type == 6 || type == 7;
    }

    /* renamed from: c */
    public static boolean m22234c(Context context) {
        try {
            NetworkInfo m22232e = m22232e(context);
            return m22237a(m22232e) && m22232e.getType() != 1;
        } catch (SecurityException unused) {
            return false;
        }
    }

    /* renamed from: d */
    public static boolean m22233d(Context context) {
        try {
            NetworkInfo m22232e = m22232e(context);
            return m22232e != null && m22232e.getType() == 1;
        } catch (SecurityException unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static NetworkInfo m22232e(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        try {
            return connectivityManager.getActiveNetworkInfo();
        } catch (SecurityException e) {
            f2390a.warning("Cannot determine network state. Enable android.permission.ACCESS_NETWORK_STATE in your manifest.");
            throw e;
        }
    }
}
