package com.baidu.platform.comapi.p149b;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.util.SysOSUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.b.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2984c {

    /* renamed from: a */
    public static final C2984c f7532a = new C2984c();

    /* renamed from: b */
    private final SharedPreferences f7533b = C2981b.m18068d().getSharedPreferences("engine_resource_sp", 0);

    private C2984c() {
    }

    /* renamed from: a */
    private String m18060a() {
        String outputDirPath = SysOSUtil.getInstance().getOutputDirPath();
        File file = new File(outputDirPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return outputDirPath;
    }

    /* renamed from: a */
    private static void m18057a(File file) {
        if (file == null) {
            return;
        }
        if (!file.isFile() && (!file.exists() || file.list() == null || file.list().length != 0)) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int i = 0; i < listFiles.length; i++) {
                    m18057a(listFiles[i]);
                    listFiles[i].delete();
                }
            }
            if (!file.exists()) {
                return;
            }
        }
        file.delete();
    }

    /* renamed from: a */
    private void m18055a(String str) {
        if (this.f7533b == null) {
            return;
        }
        File file = new File(str, "shader/");
        if (!file.exists()) {
            this.f7533b.edit().putString("fingerprint", Build.FINGERPRINT).commit();
            return;
        }
        String string = this.f7533b.getString("fingerprint", "");
        if (TextUtils.isEmpty(string) || !Build.FINGERPRINT.equals(string)) {
            m18057a(file);
            if (file.exists()) {
                return;
            }
            this.f7533b.edit().putString("fingerprint", Build.FINGERPRINT).commit();
        }
    }

    /* renamed from: a */
    private boolean m18059a(AssetManager assetManager, byte[] bArr, String str, String str2) {
        InputStream inputStream = null;
        try {
            if (TextUtils.isEmpty(str) || !str.endsWith(".dir")) {
                inputStream = assetManager.open(str);
                File file = new File(str2);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.isDirectory()) {
                    parentFile.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                C2982a.m18063a(inputStream, new FileOutputStream(file), bArr);
            } else {
                String substring = str.substring(0, str.indexOf(".dir"));
                String substring2 = str2.substring(0, str2.indexOf(".dir"));
                String[] list = assetManager.list(substring);
                if (list != null && list.length > 0) {
                    File file2 = new File(substring2);
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.mkdirs();
                    for (String str3 : list) {
                        if (!TextUtils.isEmpty(str3)) {
                            m18059a(assetManager, bArr, substring + "/" + str3, substring2 + "/" + str3);
                        }
                    }
                }
            }
            C2982a.m18064a(inputStream);
            return true;
        } catch (Exception unused) {
            C2982a.m18064a(null);
            return false;
        } catch (Throwable th) {
            C2982a.m18064a(null);
            throw th;
        }
    }

    /* renamed from: a */
    private boolean m18056a(File file, byte[] bArr) {
        FileInputStream fileInputStream;
        if (file != null && file.exists() && bArr != null) {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (IOException unused) {
                fileInputStream = null;
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
            try {
                byte[] bArr2 = new byte[fileInputStream.available()];
                fileInputStream.read(bArr2);
                if (Arrays.equals(bArr2, bArr)) {
                    C2982a.m18064a(fileInputStream);
                    return false;
                }
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
                C2982a.m18064a(fileInputStream);
                throw th;
            }
            C2982a.m18064a(fileInputStream);
        }
        return true;
    }

    /* renamed from: b */
    private boolean m18054b(File file, byte[] bArr) {
        FileOutputStream fileOutputStream;
        if (file != null && bArr != null) {
            FileOutputStream fileOutputStream2 = null;
            try {
                if (file.exists()) {
                    file.delete();
                }
                file.createNewFile();
                fileOutputStream = new FileOutputStream(file);
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                fileOutputStream.write(bArr);
                C2982a.m18064a(fileOutputStream);
                return true;
            } catch (Exception unused2) {
                fileOutputStream2 = fileOutputStream;
                C2982a.m18064a(fileOutputStream2);
                return false;
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream2 = fileOutputStream;
                C2982a.m18064a(fileOutputStream2);
                throw th;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0061 A[Catch: all -> 0x0083, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0008, B:6:0x0013, B:8:0x001d, B:10:0x002e, B:12:0x004c, B:18:0x0057, B:19:0x005b, B:21:0x0061), top: B:29:0x0001 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void m18058a(com.baidu.platform.comapi.p149b.InterfaceC2983b r13) {
        /*
            r12 = this;
            monitor-enter(r12)
            android.content.Context r0 = com.baidu.platform.comapi.C2981b.m18068d()     // Catch: java.lang.Throwable -> L83
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = r12.m18060a()     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L83
            java.io.File r5 = new java.io.File     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L83
            java.lang.String r6 = "/ver.dat"
            r5.<init>(r4, r6)     // Catch: java.lang.Throwable -> L51 java.lang.Throwable -> L83
            byte[] r1 = r13.mo18062a()     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L83
            boolean r6 = r12.m18056a(r5, r1)     // Catch: java.lang.Throwable -> L52 java.lang.Throwable -> L83
            if (r6 == 0) goto L4c
            android.content.res.AssetManager r0 = r0.getAssets()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            java.lang.String[] r13 = r13.mo18061b()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r7 = 65536(0x10000, float:9.18355E-41)
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            int r8 = r13.length     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r9 = r3
            r3 = r2
        L2c:
            if (r3 >= r8) goto L4b
            r9 = r13[r3]     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r10.<init>()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r10.append(r4)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            java.lang.String r11 = "/"
            r10.append(r11)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r10.append(r9)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            boolean r9 = r12.m18059a(r0, r7, r9, r10)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            int r3 = r3 + 1
            goto L2c
        L4b:
            r3 = r9
        L4c:
            r12.m18055a(r4)     // Catch: java.lang.Throwable -> L53 java.lang.Throwable -> L83
            r2 = r3
            goto L53
        L51:
            r5 = r1
        L52:
            r6 = r3
        L53:
            if (r6 == 0) goto L5b
            if (r2 == 0) goto L5b
            boolean r2 = r12.m18054b(r5, r1)     // Catch: java.lang.Throwable -> L83
        L5b:
            boolean r13 = com.baidu.mapapi.OpenLogUtil.isMapLogEnable()     // Catch: java.lang.Throwable -> L83
            if (r13 == 0) goto L81
            com.baidu.mapsdkplatform.comapi.commonutils.b r13 = com.baidu.mapsdkplatform.comapi.commonutils.C2898b.m18459a()     // Catch: java.lang.Throwable -> L83
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L83
            r0.<init>()     // Catch: java.lang.Throwable -> L83
            java.lang.String r1 = "initEngineRes firstInit = "
            r0.append(r1)     // Catch: java.lang.Throwable -> L83
            r0.append(r6)     // Catch: java.lang.Throwable -> L83
            java.lang.String r1 = "; isInitSucceed = "
            r0.append(r1)     // Catch: java.lang.Throwable -> L83
            r0.append(r2)     // Catch: java.lang.Throwable -> L83
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L83
            r13.m18457a(r0)     // Catch: java.lang.Throwable -> L83
        L81:
            monitor-exit(r12)
            return
        L83:
            r13 = move-exception
            monitor-exit(r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.comapi.p149b.C2984c.m18058a(com.baidu.platform.comapi.b.b):void");
    }
}
