package com.p281qq.p282e.comm.managers.plugin;

import android.text.TextUtils;
import com.p281qq.p282e.comm.managers.plugin.C6880c;
import com.p281qq.p282e.comm.util.GDTLogger;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* renamed from: com.qq.e.comm.managers.plugin.g */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class C6886g {

    /* renamed from: a */
    private final File f17951a;

    /* renamed from: b */
    private final File f17952b;

    /* renamed from: c */
    private String f17953c;

    /* renamed from: d */
    private int f17954d;

    /* renamed from: e */
    private String f17955e;

    public C6886g(File file, File file2) {
        this.f17951a = file;
        this.f17952b = file2;
    }

    /* renamed from: a */
    private String m8251a(File file) throws IOException {
        BufferedReader bufferedReader = null;
        if (file != null) {
            try {
                if (file.exists()) {
                    try {
                        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                        try {
                            StringBuilder sb = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader2.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                sb.append(readLine);
                            }
                            String sb2 = sb.toString();
                            try {
                                bufferedReader2.close();
                            } catch (Exception unused) {
                                GDTLogger.m8235d("Exception while close bufferreader");
                            }
                            return sb2;
                        } catch (IOException e) {
                            throw e;
                        } catch (Throwable th) {
                            bufferedReader = bufferedReader2;
                            th = th;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception unused2) {
                                    GDTLogger.m8235d("Exception while close bufferreader");
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        throw e2;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m8252a() {
        int i;
        try {
            if (this.f17952b.exists() && this.f17951a.exists()) {
                String m8251a = m8251a(this.f17952b);
                this.f17955e = m8251a;
                if (TextUtils.isEmpty(m8251a)) {
                    return false;
                }
                String[] split = this.f17955e.split("#####");
                if (split.length == 2) {
                    String str = split[1];
                    try {
                        i = Integer.parseInt(split[0]);
                    } catch (Throwable unused) {
                        i = 0;
                    }
                    if (C6880c.C6882b.f17949a.m8257a(str, this.f17951a)) {
                        this.f17953c = str;
                        this.f17954d = i;
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable unused2) {
            GDTLogger.m8235d("Exception while checking plugin");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m8250a(File file, File file2) {
        return (file.equals(this.f17951a) || C6887h.m8243a(this.f17951a, file)) && (file2.equals(this.f17952b) || C6887h.m8243a(this.f17952b, file2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public String m8249b() {
        return this.f17953c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m8248c() {
        return this.f17954d;
    }

    /* renamed from: d */
    public String m8247d() {
        return this.f17955e;
    }
}
