package com.p343ta.utdid2.p346b.p347a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.p343ta.utdid2.p344a.p345a.C10315g;
import com.p343ta.utdid2.p346b.p347a.InterfaceC10318b;
import java.io.File;
import java.util.Map;

/* renamed from: com.ta.utdid2.b.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10321c {

    /* renamed from: a */
    private SharedPreferences f19742a;

    /* renamed from: a */
    private InterfaceC10318b f19744a;

    /* renamed from: a */
    private C10322d f19745a;

    /* renamed from: b */
    private String f19746b;

    /* renamed from: c */
    private String f19747c;

    /* renamed from: f */
    private boolean f19748f;

    /* renamed from: g */
    private boolean f19749g;

    /* renamed from: h */
    private boolean f19750h;

    /* renamed from: i */
    private boolean f19751i;
    private Context mContext;

    /* renamed from: a */
    private SharedPreferences.Editor f19741a = null;

    /* renamed from: a */
    private InterfaceC10318b.InterfaceC10319a f19743a = null;

    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x016c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x018a A[Catch: Exception -> 0x0198, TRY_LEAVE, TryCatch #2 {Exception -> 0x0198, blocks: (B:81:0x0186, B:83:0x018a), top: B:94:0x0186 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public C10321c(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            Method dump skipped, instructions count: 409
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p343ta.utdid2.p346b.p347a.C10321c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    /* renamed from: a */
    private C10322d m6426a(String str) {
        File m6425a = m6425a(str);
        if (m6425a != null) {
            this.f19745a = new C10322d(m6425a.getAbsolutePath());
            return this.f19745a;
        }
        return null;
    }

    /* renamed from: a */
    private File m6425a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            File file = new File(String.format("%s%s%s", externalStorageDirectory.getAbsolutePath(), File.separator, str));
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }

    /* renamed from: a */
    private void m6428a(SharedPreferences sharedPreferences, InterfaceC10318b interfaceC10318b) {
        InterfaceC10318b.InterfaceC10319a mo6415a;
        if (sharedPreferences == null || interfaceC10318b == null || (mo6415a = interfaceC10318b.mo6415a()) == null) {
            return;
        }
        mo6415a.mo6399b();
        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                mo6415a.mo6401a(key, (String) value);
            } else if (value instanceof Integer) {
                mo6415a.mo6403a(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                mo6415a.mo6402a(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                mo6415a.mo6404a(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                mo6415a.mo6400a(key, ((Boolean) value).booleanValue());
            }
        }
        try {
            mo6415a.commit();
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private void m6427a(InterfaceC10318b interfaceC10318b, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (interfaceC10318b == null || sharedPreferences == null || (edit = sharedPreferences.edit()) == null) {
            return;
        }
        edit.clear();
        for (Map.Entry<String, ?> entry : interfaceC10318b.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                edit.putString(key, (String) value);
            } else if (value instanceof Integer) {
                edit.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof Long) {
                edit.putLong(key, ((Long) value).longValue());
            } else if (value instanceof Float) {
                edit.putFloat(key, ((Float) value).floatValue());
            } else if (value instanceof Boolean) {
                edit.putBoolean(key, ((Boolean) value).booleanValue());
            }
        }
        edit.commit();
    }

    /* renamed from: c */
    private boolean m6423c() {
        InterfaceC10318b interfaceC10318b = this.f19744a;
        if (interfaceC10318b != null) {
            boolean mo6408b = interfaceC10318b.mo6408b();
            if (!mo6408b) {
                commit();
            }
            return mo6408b;
        }
        return false;
    }

    /* renamed from: b */
    private void m6424b() {
        InterfaceC10318b interfaceC10318b;
        SharedPreferences sharedPreferences;
        if (this.f19741a == null && (sharedPreferences = this.f19742a) != null) {
            this.f19741a = sharedPreferences.edit();
        }
        if (this.f19750h && this.f19743a == null && (interfaceC10318b = this.f19744a) != null) {
            this.f19743a = interfaceC10318b.mo6415a();
        }
        m6423c();
    }

    public void putString(String str, String str2) {
        if (C10315g.m6435a(str) || str.equals("t")) {
            return;
        }
        m6424b();
        SharedPreferences.Editor editor = this.f19741a;
        if (editor != null) {
            editor.putString(str, str2);
        }
        InterfaceC10318b.InterfaceC10319a interfaceC10319a = this.f19743a;
        if (interfaceC10319a != null) {
            interfaceC10319a.mo6401a(str, str2);
        }
    }

    public void remove(String str) {
        if (C10315g.m6435a(str) || str.equals("t")) {
            return;
        }
        m6424b();
        SharedPreferences.Editor editor = this.f19741a;
        if (editor != null) {
            editor.remove(str);
        }
        InterfaceC10318b.InterfaceC10319a interfaceC10319a = this.f19743a;
        if (interfaceC10319a != null) {
            interfaceC10319a.mo6405a(str);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|(4:3|(1:7)|8|(9:10|11|(1:15)|16|17|18|19|(4:21|(2:23|(2:25|(3:27|(1:29)(1:31)|30))(3:32|33|(2:35|(1:37))))|40|(3:46|47|(1:49)))|52))|57|11|(2:13|15)|16|17|18|19|(0)|52) */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0037, code lost:
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0038, code lost:
        r2.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean commit() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.f19741a
            r3 = 0
            if (r2 == 0) goto L20
            boolean r4 = r5.f19751i
            if (r4 != 0) goto L16
            android.content.SharedPreferences r4 = r5.f19742a
            if (r4 == 0) goto L16
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L16:
            android.content.SharedPreferences$Editor r0 = r5.f19741a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L20
            r0 = r3
            goto L21
        L20:
            r0 = 1
        L21:
            android.content.SharedPreferences r1 = r5.f19742a
            if (r1 == 0) goto L31
            android.content.Context r1 = r5.mContext
            if (r1 == 0) goto L31
            java.lang.String r2 = r5.f19746b
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f19742a = r1
        L31:
            r1 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch: java.lang.Exception -> L37
            goto L3b
        L37:
            r2 = move-exception
            r2.printStackTrace()
        L3b:
            boolean r2 = com.p343ta.utdid2.p344a.p345a.C10315g.m6435a(r1)
            if (r2 != 0) goto Laa
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L88
            com.ta.utdid2.b.a.b r2 = r5.f19744a
            if (r2 != 0) goto L79
            java.lang.String r2 = r5.f19747c
            com.ta.utdid2.b.a.d r2 = r5.m6426a(r2)
            if (r2 == 0) goto L88
            java.lang.String r4 = r5.f19746b
            com.ta.utdid2.b.a.b r2 = r2.m6418a(r4, r3)
            r5.f19744a = r2
            boolean r2 = r5.f19751i
            if (r2 != 0) goto L69
            android.content.SharedPreferences r2 = r5.f19742a
            com.ta.utdid2.b.a.b r4 = r5.f19744a
            r5.m6428a(r2, r4)
            goto L70
        L69:
            com.ta.utdid2.b.a.b r2 = r5.f19744a
            android.content.SharedPreferences r4 = r5.f19742a
            r5.m6427a(r2, r4)
        L70:
            com.ta.utdid2.b.a.b r2 = r5.f19744a
            com.ta.utdid2.b.a.b$a r2 = r2.mo6415a()
            r5.f19743a = r2
            goto L88
        L79:
            com.ta.utdid2.b.a.b$a r2 = r5.f19743a     // Catch: java.lang.Exception -> L87
            if (r2 == 0) goto L88
            com.ta.utdid2.b.a.b$a r2 = r5.f19743a     // Catch: java.lang.Exception -> L87
            boolean r2 = r2.commit()     // Catch: java.lang.Exception -> L87
            if (r2 != 0) goto L88
            r0 = r3
            goto L88
        L87:
            r0 = r3
        L88:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L9c
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto Laa
            com.ta.utdid2.b.a.b r1 = r5.f19744a
            if (r1 == 0) goto Laa
        L9c:
            com.ta.utdid2.b.a.d r1 = r5.f19745a     // Catch: java.lang.Exception -> Laa
            if (r1 == 0) goto Laa
            com.ta.utdid2.b.a.d r1 = r5.f19745a     // Catch: java.lang.Exception -> Laa
            java.lang.String r2 = r5.f19746b     // Catch: java.lang.Exception -> Laa
            com.ta.utdid2.b.a.b r1 = r1.m6418a(r2, r3)     // Catch: java.lang.Exception -> Laa
            r5.f19744a = r1     // Catch: java.lang.Exception -> Laa
        Laa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p343ta.utdid2.p346b.p347a.C10321c.commit():boolean");
    }

    public String getString(String str) {
        m6423c();
        SharedPreferences sharedPreferences = this.f19742a;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString(str, "");
            if (!C10315g.m6435a(string)) {
                return string;
            }
        }
        InterfaceC10318b interfaceC10318b = this.f19744a;
        return interfaceC10318b != null ? interfaceC10318b.getString(str, "") : "";
    }
}
