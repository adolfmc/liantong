package com.baidu.p122b.p131e;

import android.content.Context;
import com.baidu.p122b.p125c.p127b.C2405c;
import com.baidu.p122b.p132f.C2424c;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.e.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2419a {

    /* renamed from: a */
    private Context f4262a;

    /* renamed from: b */
    private C2420a f4263b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.e.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public final class C2420a {

        /* renamed from: b */
        private File f4265b;

        /* renamed from: c */
        private String f4266c;

        /* renamed from: d */
        private C2420a f4267d;

        /* renamed from: e */
        private boolean f4268e;

        C2420a(File file) {
            this.f4268e = false;
            this.f4268e = true;
            this.f4265b = file;
            this.f4266c = file.getName();
        }

        C2420a(String str, C2420a c2420a) {
            this.f4268e = false;
            this.f4266c = str;
            this.f4267d = c2420a;
            this.f4268e = false;
        }

        /* renamed from: a */
        public C2420a m20198a(File file) {
            if (this.f4268e) {
                throw new IllegalStateException("isolate session is not support");
            }
            ArrayList arrayList = new ArrayList();
            C2420a c2420a = this;
            do {
                arrayList.add(c2420a.m20193c());
                c2420a = c2420a.m20192d();
            } while (c2420a != null);
            int size = arrayList.size() - 1;
            while (size >= 0) {
                size--;
                file = new File(file, (String) arrayList.get(size));
            }
            return new C2420a(file);
        }

        /* renamed from: a */
        public C2420a m20197a(String str) {
            return new C2420a(str, this);
        }

        /* renamed from: a */
        public String m20195a(String str, boolean z) {
            return C2419a.m20202a(m20194b(), str, "UTF-8", z);
        }

        /* renamed from: a */
        public void m20199a() {
            m20194b().mkdirs();
        }

        /* renamed from: a */
        public boolean m20196a(String str, String str2, boolean z) {
            return C2419a.m20203a(m20194b(), str, str2, "UTF-8", z);
        }

        /* renamed from: b */
        public File m20194b() {
            File file = this.f4265b;
            if (file != null) {
                return file;
            }
            C2420a c2420a = this.f4267d;
            File file2 = c2420a == null ? new File(C2419a.this.m20205a(), this.f4266c) : new File(c2420a.m20194b(), this.f4266c);
            this.f4265b = file2;
            return file2;
        }

        /* renamed from: c */
        public String m20193c() {
            return this.f4266c;
        }

        /* renamed from: d */
        public C2420a m20192d() {
            return this.f4267d;
        }
    }

    public C2419a(Context context) {
        this.f4262a = context;
        m20200c().mkdirs();
    }

    /* renamed from: a */
    public static String m20202a(File file, String str, String str2, boolean z) {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        m20204a(file);
        File file2 = new File(file, str);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                fileInputStream = new FileInputStream(file2);
            } catch (Exception unused) {
                fileInputStream = null;
            } catch (Throwable th2) {
                fileInputStream = null;
                th = th2;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                if (z) {
                    byteArray = new C2405c().m20245b(byteArray);
                }
                String str3 = new String(byteArray, str2);
                C2424c.m20179a(fileInputStream);
                C2424c.m20179a(byteArrayOutputStream);
                return str3;
            } catch (Exception unused2) {
                C2424c.m20179a(fileInputStream);
                C2424c.m20179a(byteArrayOutputStream);
                return "";
            } catch (Throwable th3) {
                th = th3;
                C2424c.m20179a(fileInputStream);
                C2424c.m20179a(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception unused3) {
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            byteArrayOutputStream = null;
        }
    }

    /* renamed from: a */
    public static void m20204a(File file) {
        file.mkdirs();
    }

    /* renamed from: a */
    public static boolean m20203a(File file, String str, String str2, String str3, boolean z) {
        FileOutputStream fileOutputStream;
        Throwable th;
        m20204a(file);
        try {
            fileOutputStream = new FileOutputStream(new File(file, str));
        } catch (Exception unused) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            fileOutputStream = null;
            th = th2;
        }
        try {
            fileOutputStream.write(z ? new C2405c().m20246a(str2.getBytes()) : str2.getBytes(str3));
            C2424c.m20179a(fileOutputStream);
            return true;
        } catch (Exception unused2) {
            C2424c.m20179a(fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            C2424c.m20179a(fileOutputStream);
            throw th;
        }
    }

    /* renamed from: c */
    private File m20200c() {
        return new File(m20205a(), ".cesium");
    }

    /* renamed from: a */
    public File m20205a() {
        return new File(this.f4262a.getApplicationInfo().dataDir);
    }

    /* renamed from: b */
    public synchronized C2420a m20201b() {
        if (this.f4263b == null) {
            this.f4263b = new C2420a(".cesium", null);
        }
        return this.f4263b;
    }
}
