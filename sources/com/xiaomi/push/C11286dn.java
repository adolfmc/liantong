package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.service.C11537ah;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.dn */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11286dn extends C11134ae.AbstractRunnableC11137a {

    /* renamed from: a */
    private Context f21872a;

    /* renamed from: a */
    private SharedPreferences f21873a;

    /* renamed from: a */
    private C11537ah f21874a;

    @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
    /* renamed from: a */
    public String mo2289a() {
        return "1";
    }

    public C11286dn(Context context) {
        this.f21872a = context;
        this.f21873a = context.getSharedPreferences("mipush_extra", 0);
        this.f21874a = C11537ah.m2715a(context);
    }

    @Override // java.lang.Runnable
    public void run() {
        File file = new File(this.f21872a.getFilesDir(), "push_cdata.data");
        if (!C11169au.m4834c(this.f21872a)) {
            if (file.length() > 1863680) {
                file.delete();
            }
        } else if (!m4337a() && file.exists()) {
            List<C11412gn> m4335a = m4335a(file);
            if (!C11651z.m2259a(m4335a)) {
                int size = m4335a.size();
                if (size > 4000) {
                    m4335a = m4335a.subList(size - 4000, size);
                }
                C11423gy c11423gy = new C11423gy();
                c11423gy.m3435a(m4335a);
                byte[] m2267a = C11647w.m2267a(C11441hp.m3085a(c11423gy));
                C11430he c11430he = new C11430he("-1", false);
                c11430he.m3331c(EnumC11414gp.DataCollection.f22745a);
                c11430he.m3338a(m2267a);
                InterfaceC11276df m4363a = C11277dg.m4362a().m4363a();
                if (m4363a != null) {
                    m4363a.mo4364a(c11430he, EnumC11404gf.Notification, null);
                }
                m4338a();
            }
            file.delete();
        }
    }

    /* renamed from: a */
    private boolean m4337a() {
        if (C11169au.m4833d(this.f21872a)) {
            return false;
        }
        if ((C11169au.m4831f(this.f21872a) || C11169au.m4832e(this.f21872a)) && !m4333c()) {
            return true;
        }
        return (C11169au.m4830g(this.f21872a) && !m4334b()) || C11169au.m4829h(this.f21872a);
    }

    /* renamed from: b */
    private boolean m4334b() {
        if (this.f21874a.m2716a(EnumC11409gk.Upload3GSwitch.m3637a(), true)) {
            return Math.abs((System.currentTimeMillis() / 1000) - this.f21873a.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max(86400, this.f21874a.m2719a(EnumC11409gk.Upload3GFrequency.m3637a(), 432000)));
        }
        return false;
    }

    /* renamed from: c */
    private boolean m4333c() {
        if (this.f21874a.m2716a(EnumC11409gk.Upload4GSwitch.m3637a(), true)) {
            return Math.abs((System.currentTimeMillis() / 1000) - this.f21873a.getLong("last_upload_data_timestamp", -1L)) > ((long) Math.max(86400, this.f21874a.m2719a(EnumC11409gk.Upload4GFrequency.m3637a(), 259200)));
        }
        return false;
    }

    /* renamed from: a */
    private void m4338a() {
        SharedPreferences.Editor edit = this.f21873a.edit();
        edit.putLong("last_upload_data_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }

    /* renamed from: a */
    private List<C11412gn> m4335a(File file) {
        FileLock fileLock;
        RandomAccessFile randomAccessFile;
        InterfaceC11276df m4363a = C11277dg.m4362a().m4363a();
        String mo4365a = m4363a == null ? "" : m4363a.mo4365a();
        FileInputStream fileInputStream = null;
        if (TextUtils.isEmpty(mo4365a)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        byte[] bArr = new byte[4];
        synchronized (C11280dj.f21863a) {
            try {
                File file2 = new File(this.f21872a.getFilesDir(), "push_cdata.lock");
                C11647w.m2272a(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    try {
                        FileInputStream fileInputStream2 = new FileInputStream(file);
                        while (fileInputStream2.read(bArr) == 4) {
                            try {
                                int m2260a = C11650y.m2260a(bArr);
                                byte[] bArr2 = new byte[m2260a];
                                if (fileInputStream2.read(bArr2) != m2260a) {
                                    break;
                                }
                                byte[] m4358a = C11279di.m4358a(mo4365a, bArr2);
                                if (m4358a != null && m4358a.length != 0) {
                                    C11412gn c11412gn = new C11412gn();
                                    C11441hp.m3084a(c11412gn, m4358a);
                                    arrayList.add(c11412gn);
                                    m4336a(c11412gn);
                                }
                            } catch (Exception unused) {
                                fileInputStream = fileInputStream2;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException unused2) {
                                    }
                                }
                                C11647w.m2274a((Closeable) fileInputStream);
                                C11647w.m2274a(randomAccessFile);
                                return arrayList;
                            } catch (Throwable th) {
                                th = th;
                                fileInputStream = fileInputStream2;
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException unused3) {
                                    }
                                }
                                C11647w.m2274a((Closeable) fileInputStream);
                                C11647w.m2274a(randomAccessFile);
                                throw th;
                            }
                        }
                        if (fileLock != null && fileLock.isValid()) {
                            try {
                                fileLock.release();
                            } catch (IOException unused4) {
                            }
                        }
                        C11647w.m2274a((Closeable) fileInputStream2);
                    } catch (Exception unused5) {
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception unused6) {
                    fileLock = null;
                } catch (Throwable th3) {
                    th = th3;
                    fileLock = null;
                }
            } catch (Exception unused7) {
                fileLock = null;
                randomAccessFile = null;
            } catch (Throwable th4) {
                th = th4;
                fileLock = null;
                randomAccessFile = null;
            }
            C11647w.m2274a(randomAccessFile);
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m4336a(C11412gn c11412gn) {
        if (c11412gn.f22672a != EnumC11406gh.AppInstallList || c11412gn.f22673a.startsWith("same_")) {
            return;
        }
        SharedPreferences.Editor edit = this.f21873a.edit();
        edit.putLong("dc_job_result_time_4", c11412gn.f22671a);
        edit.putString("dc_job_result_4", C11184bb.m4757a(c11412gn.f22673a));
        edit.commit();
    }
}
