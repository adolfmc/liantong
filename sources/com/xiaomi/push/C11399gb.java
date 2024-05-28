package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.gb */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11399gb {

    /* renamed from: a */
    private static boolean f22423a;

    /* renamed from: a */
    public static void m3698a(Context context, InterfaceC11403ge interfaceC11403ge) {
        C11134ae.m4937a(context).m4928a(new RunnableC11400a(context, interfaceC11403ge));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.gb$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class RunnableC11400a implements Runnable {

        /* renamed from: a */
        private Context f22424a;

        /* renamed from: a */
        private InterfaceC11403ge f22425a;

        public RunnableC11400a(Context context, InterfaceC11403ge interfaceC11403ge) {
            this.f22425a = interfaceC11403ge;
            this.f22424a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            C11399gb.m3694c(this.f22424a, this.f22425a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00c9  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m3694c(android.content.Context r7, com.xiaomi.push.InterfaceC11403ge r8) {
        /*
            boolean r0 = com.xiaomi.push.C11399gb.f22423a
            if (r0 != 0) goto Leb
            r0 = 1
            com.xiaomi.push.C11399gb.f22423a = r0
            java.io.File r0 = new java.io.File
            java.io.File r1 = r7.getFilesDir()
            java.lang.String r2 = "tiny_data.data"
            r0.<init>(r1, r2)
            boolean r1 = r0.exists()
            if (r1 != 0) goto L1f
            java.lang.String r7 = "TinyData no ready file to get data."
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5282a(r7)
            return
        L1f:
            m3699a(r7)
            byte[] r1 = com.xiaomi.push.service.C11579ba.m2585a(r7)
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.io.File r4 = r7.getFilesDir()     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.lang.String r5 = "tiny_data.lock"
            r3.<init>(r4, r5)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            com.xiaomi.push.C11647w.m2272a(r3)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.lang.String r5 = "rw"
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L7d java.lang.Exception -> L80
            java.nio.channels.FileChannel r3 = r4.getChannel()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.nio.channels.FileLock r2 = r3.lock()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.io.File r3 = new java.io.File     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            r5.<init>()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.io.File r6 = r7.getFilesDir()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            r5.append(r6)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.lang.String r6 = "/tdReadTemp"
            r5.append(r6)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.lang.String r6 = "/"
            r5.append(r6)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.lang.String r6 = "tiny_data.data"
            r5.append(r6)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            r3.<init>(r5)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            r0.renameTo(r3)     // Catch: java.lang.Exception -> L7b java.lang.Throwable -> Ld6
            if (r2 == 0) goto L95
            boolean r0 = r2.isValid()
            if (r0 == 0) goto L95
            r2.release()     // Catch: java.io.IOException -> L79
            goto L95
        L79:
            r0 = move-exception
            goto L92
        L7b:
            r0 = move-exception
            goto L82
        L7d:
            r7 = move-exception
            r4 = r2
            goto Ld7
        L80:
            r0 = move-exception
            r4 = r2
        L82:
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r0)     // Catch: java.lang.Throwable -> Ld6
            if (r2 == 0) goto L95
            boolean r0 = r2.isValid()
            if (r0 == 0) goto L95
            r2.release()     // Catch: java.io.IOException -> L91
            goto L95
        L91:
            r0 = move-exception
        L92:
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r0)
        L95:
            com.xiaomi.push.C11647w.m2274a(r4)
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = r7.getFilesDir()
            r2.append(r3)
            java.lang.String r3 = "/tdReadTemp"
            r2.append(r3)
            java.lang.String r3 = "/"
            r2.append(r3)
            java.lang.String r3 = "tiny_data.data"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 != 0) goto Lc9
            java.lang.String r7 = "TinyData no ready file to get data."
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5282a(r7)
            return
        Lc9:
            m3697a(r7, r8, r0, r1)
            r8 = 0
            com.xiaomi.push.C11398ga.m3700a(r8)
            m3696b(r7)
            com.xiaomi.push.C11399gb.f22423a = r8
            return
        Ld6:
            r7 = move-exception
        Ld7:
            if (r2 == 0) goto Le7
            boolean r8 = r2.isValid()
            if (r8 == 0) goto Le7
            r2.release()     // Catch: java.io.IOException -> Le3
            goto Le7
        Le3:
            r8 = move-exception
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r8)
        Le7:
            com.xiaomi.push.C11647w.m2274a(r4)
            throw r7
        Leb:
            java.lang.String r7 = "TinyData extractTinyData is running"
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5282a(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11399gb.m3694c(android.content.Context, com.xiaomi.push.ge):void");
    }

    /* renamed from: a */
    private static void m3697a(Context context, InterfaceC11403ge interfaceC11403ge, File file, byte[] bArr) {
        BufferedInputStream bufferedInputStream;
        int m2260a;
        ArrayList arrayList = new ArrayList();
        byte[] bArr2 = new byte[4];
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                int i = 0;
                int i2 = 0;
                while (true) {
                    try {
                        int read = bufferedInputStream.read(bArr2);
                        if (read == -1) {
                            break;
                        } else if (read != 4) {
                            AbstractC11049b.m5268d("TinyData read from cache file failed cause lengthBuffer error. size:" + read);
                            break;
                        } else {
                            m2260a = C11650y.m2260a(bArr2);
                            if (m2260a < 1 || m2260a > 30720) {
                                break;
                            }
                            byte[] bArr3 = new byte[m2260a];
                            int read2 = bufferedInputStream.read(bArr3);
                            if (read2 != m2260a) {
                                AbstractC11049b.m5268d("TinyData read from cache file failed cause buffer size not equal length. size:" + read2 + "__length:" + m2260a);
                                break;
                            }
                            byte[] m3410a = C11425h.m3410a(bArr, bArr3);
                            if (m3410a != null && m3410a.length != 0) {
                                C11408gj c11408gj = new C11408gj();
                                C11441hp.m3084a(c11408gj, m3410a);
                                c11408gj.m3664a("item_size", String.valueOf(m3410a.length));
                                arrayList.add(c11408gj);
                                i++;
                                i2 += m3410a.length;
                                if (i >= 8 || i2 >= 30720) {
                                    C11401gc.m3692a(context, interfaceC11403ge, arrayList);
                                    arrayList.clear();
                                    i = 0;
                                    i2 = 0;
                                }
                            }
                            AbstractC11049b.m5268d("TinyData read from cache file failed cause decrypt fail");
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedInputStream2 = bufferedInputStream;
                        AbstractC11049b.m5276a(e);
                        C11647w.m2274a((Closeable) bufferedInputStream2);
                        return;
                    } catch (Throwable th) {
                        th = th;
                        C11647w.m2274a((Closeable) bufferedInputStream);
                        throw th;
                    }
                }
                AbstractC11049b.m5268d("TinyData read from cache file failed cause lengthBuffer < 1 || too big. length:" + m2260a);
                C11401gc.m3692a(context, interfaceC11403ge, arrayList);
                if (file != null && file.exists() && !file.delete()) {
                    AbstractC11049b.m5282a("TinyData delete reading temp file failed");
                }
                C11647w.m2274a((Closeable) bufferedInputStream);
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = bufferedInputStream2;
        }
    }

    /* renamed from: a */
    private static void m3699a(Context context) {
        File file = new File(context.getFilesDir() + "/tdReadTemp");
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* renamed from: b */
    private static void m3696b(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences("mipush_extra", 4).edit();
        edit.putLong("last_tiny_data_upload_timestamp", System.currentTimeMillis() / 1000);
        edit.commit();
    }
}
