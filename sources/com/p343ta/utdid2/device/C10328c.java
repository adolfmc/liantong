package com.p343ta.utdid2.device;

import android.content.Context;
import android.os.Binder;
import android.provider.Settings;
import android.text.TextUtils;
import com.p343ta.utdid2.p344a.p345a.C10305b;
import com.p343ta.utdid2.p344a.p345a.C10310d;
import com.p343ta.utdid2.p344a.p345a.C10311e;
import com.p343ta.utdid2.p344a.p345a.C10312f;
import com.p343ta.utdid2.p344a.p345a.C10315g;
import com.p343ta.utdid2.p346b.p347a.C10321c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.ta.utdid2.device.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10328c {

    /* renamed from: a */
    private C10321c f19777a;

    /* renamed from: a */
    private C10329d f19778a;

    /* renamed from: b */
    private C10321c f19779b;

    /* renamed from: i */
    private String f19782i;

    /* renamed from: j */
    private String f19783j;
    private Context mContext;

    /* renamed from: e */
    private static final Object f19775e = new Object();

    /* renamed from: a */
    private static C10328c f19774a = null;

    /* renamed from: k */
    private static final String f19776k = ".UTSystemConfig" + File.separator + "Global";

    /* renamed from: h */
    private String f19781h = null;

    /* renamed from: b */
    private Pattern f19780b = Pattern.compile("[^0-9a-zA-Z=/+]+");

    private C10328c(Context context) {
        this.mContext = null;
        this.f19778a = null;
        this.f19782i = "xx_utdid_key";
        this.f19783j = "xx_utdid_domain";
        this.f19777a = null;
        this.f19779b = null;
        this.mContext = context;
        this.f19779b = new C10321c(context, f19776k, "Alvin2", false, true);
        this.f19777a = new C10321c(context, ".DataStorage", "ContextData", false, true);
        this.f19778a = new C10329d();
        this.f19782i = String.format("K_%d", Integer.valueOf(C10315g.m6436a(this.f19782i)));
        this.f19783j = String.format("D_%d", Integer.valueOf(C10315g.m6436a(this.f19783j)));
    }

    /* renamed from: c */
    private void m6369c() {
        C10321c c10321c = this.f19779b;
        if (c10321c != null) {
            if (C10315g.m6435a(c10321c.getString("UTDID2"))) {
                String string = this.f19779b.getString("UTDID");
                if (!C10315g.m6435a(string)) {
                    m6366f(string);
                }
            }
            boolean z = false;
            if (!C10315g.m6435a(this.f19779b.getString("DID"))) {
                this.f19779b.remove("DID");
                z = true;
            }
            if (!C10315g.m6435a(this.f19779b.getString("EI"))) {
                this.f19779b.remove("EI");
                z = true;
            }
            if (!C10315g.m6435a(this.f19779b.getString("SI"))) {
                this.f19779b.remove("SI");
                z = true;
            }
            if (z) {
                this.f19779b.commit();
            }
        }
    }

    /* renamed from: a */
    public static C10328c m6372a(Context context) {
        if (context != null && f19774a == null) {
            synchronized (f19775e) {
                if (f19774a == null) {
                    f19774a = new C10328c(context);
                    f19774a.m6369c();
                }
            }
        }
        return f19774a;
    }

    /* renamed from: f */
    private void m6366f(String str) {
        C10321c c10321c;
        if (m6371b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (str.length() != 24 || (c10321c = this.f19779b) == null) {
                return;
            }
            c10321c.putString("UTDID2", str);
            this.f19779b.commit();
        }
    }

    /* renamed from: g */
    private void m6364g(String str) {
        C10321c c10321c;
        if (str == null || (c10321c = this.f19777a) == null || str.equals(c10321c.getString(this.f19782i))) {
            return;
        }
        this.f19777a.putString(this.f19782i, str);
        this.f19777a.commit();
    }

    /* renamed from: h */
    private void m6362h(String str) {
        if (m6367f() && m6371b(str)) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length()) {
                String str2 = null;
                try {
                    str2 = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
                } catch (Exception unused) {
                }
                if (m6371b(str2)) {
                    return;
                }
                try {
                    Settings.System.putString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk", str);
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* renamed from: i */
    private void m6360i(String str) {
        String str2;
        try {
            str2 = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused) {
            str2 = null;
        }
        if (str.equals(str2)) {
            return;
        }
        try {
            Settings.System.putString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp", str);
        } catch (Exception unused2) {
        }
    }

    /* renamed from: j */
    private void m6359j(String str) {
        if (!m6367f() || str == null) {
            return;
        }
        m6360i(str);
    }

    /* renamed from: g */
    private String m6365g() {
        C10321c c10321c = this.f19779b;
        if (c10321c != null) {
            String string = c10321c.getString("UTDID2");
            if (C10315g.m6435a(string) || this.f19778a.m6358c(string) == null) {
                return null;
            }
            return string;
        }
        return null;
    }

    /* renamed from: b */
    private boolean m6371b(String str) {
        if (str != null) {
            if (str.endsWith("\n")) {
                str = str.substring(0, str.length() - 1);
            }
            if (24 == str.length() && !this.f19780b.matcher(str).find()) {
                return true;
            }
        }
        return false;
    }

    public synchronized String getValue() {
        if (this.f19781h != null) {
            return this.f19781h;
        }
        return m6363h();
    }

    /* renamed from: h */
    public synchronized String m6363h() {
        this.f19781h = m6361i();
        if (!TextUtils.isEmpty(this.f19781h)) {
            return this.f19781h;
        }
        try {
            byte[] m6368c = m6368c();
            if (m6368c != null) {
                this.f19781h = C10305b.encodeToString(m6368c, 2);
                m6366f(this.f19781h);
                String m6357c = this.f19778a.m6357c(m6368c);
                if (m6357c != null) {
                    m6359j(m6357c);
                    m6364g(m6357c);
                }
                return this.f19781h;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: i */
    public synchronized String m6361i() {
        String str;
        String string;
        try {
            string = Settings.System.getString(this.mContext.getContentResolver(), "mqBRboGZkQPcAkyk");
        } catch (Exception unused) {
        }
        if (m6371b(string)) {
            return string;
        }
        C10330e c10330e = new C10330e();
        boolean z = false;
        try {
            str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
        } catch (Exception unused2) {
            str = null;
        }
        if (C10315g.m6435a(str)) {
            z = true;
        } else {
            String m6354e = c10330e.m6354e(str);
            if (m6371b(m6354e)) {
                m6362h(m6354e);
                return m6354e;
            }
            String m6355d = c10330e.m6355d(str);
            if (m6371b(m6355d)) {
                String m6358c = this.f19778a.m6358c(m6355d);
                if (!C10315g.m6435a(m6358c)) {
                    m6359j(m6358c);
                    try {
                        str = Settings.System.getString(this.mContext.getContentResolver(), "dxCRMxhQkdGePGnp");
                    } catch (Exception unused3) {
                    }
                }
            }
            String m6356d = this.f19778a.m6356d(str);
            if (m6371b(m6356d)) {
                this.f19781h = m6356d;
                m6366f(m6356d);
                m6364g(str);
                m6362h(this.f19781h);
                return this.f19781h;
            }
        }
        String m6365g = m6365g();
        if (m6371b(m6365g)) {
            String m6358c2 = this.f19778a.m6358c(m6365g);
            if (z) {
                m6359j(m6358c2);
            }
            m6362h(m6365g);
            m6364g(m6358c2);
            this.f19781h = m6365g;
            return m6365g;
        }
        String string2 = this.f19777a.getString(this.f19782i);
        if (!C10315g.m6435a(string2)) {
            String m6355d2 = c10330e.m6355d(string2);
            if (!m6371b(m6355d2)) {
                m6355d2 = this.f19778a.m6356d(string2);
            }
            if (m6371b(m6355d2)) {
                String m6358c3 = this.f19778a.m6358c(m6355d2);
                if (!C10315g.m6435a(m6355d2)) {
                    this.f19781h = m6355d2;
                    if (z) {
                        m6359j(m6358c3);
                    }
                    m6366f(this.f19781h);
                    return this.f19781h;
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    private byte[] m6368c() throws Exception {
        String str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nextInt = new Random().nextInt();
        byte[] bytes = C10310d.getBytes((int) (System.currentTimeMillis() / 1000));
        byte[] bytes2 = C10310d.getBytes(nextInt);
        byteArrayOutputStream.write(bytes, 0, 4);
        byteArrayOutputStream.write(bytes2, 0, 4);
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(0);
        try {
            str = C10311e.m6444a(this.mContext);
        } catch (Exception unused) {
            str = "" + new Random().nextInt();
        }
        byteArrayOutputStream.write(C10310d.getBytes(C10315g.m6436a(str)), 0, 4);
        byteArrayOutputStream.write(C10310d.getBytes(C10315g.m6436a(m6370b(byteArrayOutputStream.toByteArray()))));
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static String m6370b(byte[] bArr) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(C10312f.m6438a(new byte[]{69, 114, 116, -33, 125, -54, -31, 86, -11, 11, -78, -96, -17, -99, 64, 23, -95, -126, -82, -64, 113, 116, -16, -103, 49, -30, 9, -39, 33, -80, -68, -78, -117, 53, 30, -122, 64, -104, 74, -49, 106, 85, -38, -93}), mac.getAlgorithm()));
        return C10305b.encodeToString(mac.doFinal(bArr), 2);
    }

    /* renamed from: f */
    private boolean m6367f() {
        return this.mContext.checkPermission("android.permission.WRITE_SETTINGS", Binder.getCallingPid(), Binder.getCallingUid()) == 0;
    }
}
