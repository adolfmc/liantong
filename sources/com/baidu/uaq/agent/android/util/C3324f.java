package com.baidu.uaq.agent.android.util;

import android.content.Context;
import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import p001a.p002a.p003a.p004a.outline;
import p001a.p058b.p062b.p063a.p064a.p067c.p069b.C0735a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.uaq.agent.android.util.f */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C3324f {

    /* renamed from: a */
    public static final InterfaceC3321a f8166a = C0749a.f2299a;

    /* renamed from: b */
    public static String f8167b = null;

    /* renamed from: c */
    public final String f8168c;

    /* renamed from: d */
    public final ArrayList<String> f8169d = new ArrayList<>();

    /* renamed from: e */
    public Context f8170e;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.uaq.agent.android.util.f$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface InterfaceC3325a {

        /* renamed from: a */
        public static final String f8171a;

        /* renamed from: b */
        public static final String f8172b;

        static {
            StringBuilder m24437a = outline.m24437a("\"uploadSource\":");
            m24437a.append(UAQ.getInstance().getConfig().getSourceType());
            m24437a.append(",");
            f8171a = m24437a.toString();
            StringBuilder m24437a2 = outline.m24437a("{");
            m24437a2.append(f8171a);
            m24437a2.append("\"version\":1,\"value\":[");
            f8172b = m24437a2.toString();
        }
    }

    public C3324f(Context context, String str) {
        this.f8170e = context;
        this.f8168c = str;
        if (!this.f8169d.isEmpty()) {
            this.f8169d.clear();
        }
        File file = new File(m17418b());
        m17415d(this.f8168c);
        String[] list = file.list();
        if (list == null || list.length == 0) {
            return;
        }
        for (String str2 : list) {
            if (!this.f8169d.contains(m17418b() + str2) && str2.contains(this.f8168c)) {
                this.f8169d.add(m17418b() + str2);
            }
        }
        Collections.reverse(this.f8169d);
    }

    /* renamed from: a */
    public ArrayList<String> m17423a() {
        Collections.sort(this.f8169d);
        return this.f8169d;
    }

    /* renamed from: a */
    public final void m17422a(File file, String str) {
        String absolutePath = file.getAbsolutePath();
        int lastIndexOf = absolutePath.lastIndexOf(File.separator) + 1;
        if (lastIndexOf < 0 || lastIndexOf > absolutePath.length()) {
            f8166a.warning("in toPersistentFile, StringIndexOutOfBoundsException happened!");
            return;
        }
        String str2 = absolutePath.substring(0, lastIndexOf) + this.f8168c + "_" + System.currentTimeMillis();
        file.renameTo(new File(str2));
        this.f8169d.add(str2);
        m17419a(this.f8168c, str, file);
    }

    /* renamed from: a */
    public final void m17419a(String str, String str2, File file) {
        if (str.equals("APMPerformanceConfigurationName")) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(str2.getBytes());
                fileOutputStream.write(",".getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
                C0735a.m22302a(e);
                return;
            }
        }
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream2);
            InterfaceC3321a interfaceC3321a = f8166a;
            StringBuilder sb = new StringBuilder();
            sb.append("writeToDefaultCustomFile log size:");
            sb.append(str2.length());
            interfaceC3321a.mo17428D(sb.toString());
            dataOutputStream.writeLong(str2.length());
            dataOutputStream.writeChars(str2);
            dataOutputStream.flush();
            dataOutputStream.close();
            fileOutputStream2.flush();
            fileOutputStream2.close();
        } catch (IOException e2) {
            e2.printStackTrace();
            C0735a.m22302a(e2);
        }
    }

    /* renamed from: a */
    public boolean m17421a(String str) {
        File file = new File(str);
        this.f8169d.remove(str);
        if (file.exists()) {
            return file.delete();
        }
        return true;
    }

    /* renamed from: b */
    public final String m17418b() {
        if (f8167b == null) {
            f8167b = this.f8170e.getFilesDir().getAbsolutePath() + "/apm/";
        }
        return f8167b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m17417b(java.lang.String r7) {
        /*
            r6 = this;
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            boolean r2 = r1.exists()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            if (r2 != 0) goto L2d
            java.lang.String r1 = r6.f8168c     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            boolean r1 = r7.contains(r1)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            if (r1 != 0) goto L2a
            com.baidu.uaq.agent.android.logging.a r1 = com.baidu.uaq.agent.android.util.C3324f.f8166a     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            r2.<init>()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            java.lang.String r3 = "log file not exists: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            r2.append(r7)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            java.lang.String r7 = r2.toString()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            r1.error(r7)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
        L2a:
            return r0
        L2b:
            r7 = move-exception
            goto L6b
        L2d:
            java.io.FileReader r7 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L56
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
            r1.<init>()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
            r2 = 1024(0x400, float:1.435E-42)
            char[] r2 = new char[r2]     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
        L3b:
            int r3 = r7.read(r2)     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
            r4 = -1
            if (r3 == r4) goto L47
            r4 = 0
            r1.append(r2, r4, r3)     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
            goto L3b
        L47:
            java.lang.String r0 = r1.toString()     // Catch: java.io.IOException -> L54 java.lang.Throwable -> L67
            r7.close()     // Catch: java.io.IOException -> L4f
            goto L53
        L4f:
            r7 = move-exception
            r7.printStackTrace()
        L53:
            return r0
        L54:
            r1 = move-exception
            goto L59
        L56:
            r7 = move-exception
            r1 = r7
            r7 = r0
        L59:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L66
            r7.close()     // Catch: java.io.IOException -> L62
            goto L66
        L62:
            r7 = move-exception
            r7.printStackTrace()
        L66:
            return r0
        L67:
            r0 = move-exception
            r5 = r0
            r0 = r7
            r7 = r5
        L6b:
            if (r0 == 0) goto L75
            r0.close()     // Catch: java.io.IOException -> L71
            goto L75
        L71:
            r0 = move-exception
            r0.printStackTrace()
        L75:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.uaq.agent.android.util.C3324f.m17417b(java.lang.String):java.lang.String");
    }

    /* renamed from: c */
    public ArrayList<String> m17416c(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(str);
                try {
                    int available = fileInputStream2.available();
                    if (available > 0) {
                        int i = 0;
                        while (i < available) {
                            DataInputStream dataInputStream = new DataInputStream(fileInputStream2);
                            long readLong = dataInputStream.readLong();
                            int i2 = (int) readLong;
                            char[] cArr = new char[i2];
                            for (int i3 = 0; i3 < readLong; i3++) {
                                cArr[i3] = dataInputStream.readChar();
                            }
                            arrayList.add(new String(cArr, 0, i2));
                            i = (int) (i + 8 + readLong);
                        }
                    }
                    try {
                        fileInputStream2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return arrayList;
                } catch (EOFException unused) {
                    fileInputStream = fileInputStream2;
                    f8166a.mo17428D("read end");
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    return arrayList;
                } catch (IOException unused2) {
                    fileInputStream = fileInputStream2;
                    f8166a.mo17428D("read end");
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (EOFException unused3) {
            } catch (IOException unused4) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: d */
    public final boolean m17415d(String str) {
        String m17418b = m17418b();
        try {
            File file = new File(m17418b);
            if (!file.exists()) {
                file.mkdirs();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(m17418b);
            sb.append(str);
            sb.append("_ini");
            File file2 = new File(sb.toString());
            if (file2.exists()) {
                return true;
            }
            file2.createNewFile();
            if (this.f8169d.contains(file2.getAbsolutePath())) {
                return true;
            }
            this.f8169d.add(file2.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            C0735a.m22302a(e);
            return false;
        }
    }

    /* renamed from: a */
    public void m17420a(String str, String str2) {
        File file = new File(m17418b() + str + "_ini");
        if (!file.exists() && !m17415d(str)) {
            file = null;
        }
        if (file == null) {
            return;
        }
        if (file.length() + str2.length() <= 10240) {
            m17419a(str, str2, file);
            return;
        }
        if (this.f8169d.size() >= 10) {
            Collections.sort(this.f8169d);
            String remove = this.f8169d.remove(0);
            InterfaceC3321a interfaceC3321a = f8166a;
            interfaceC3321a.mo17428D("expire with file:  " + remove);
            File file2 = new File(remove);
            if (file2.exists()) {
                file2.delete();
            }
        }
        m17422a(file, str2);
    }
}
