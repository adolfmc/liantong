package com.xiaomi.push.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11647w;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.xiaomi.push.service.ae */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11529ae {

    /* renamed from: a */
    private static long f23480a;

    /* renamed from: a */
    private static boolean f23481a;

    /* renamed from: b */
    private static long f23482b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.ae$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11532b {

        /* renamed from: a */
        public long f23485a;

        /* renamed from: a */
        public Bitmap f23486a;

        public C11532b(Bitmap bitmap, long j) {
            this.f23486a = bitmap;
            this.f23485a = j;
        }
    }

    /* renamed from: a */
    public static C11532b m2779a(Context context, String str, boolean z) {
        Bitmap m2774b;
        ByteArrayInputStream byteArrayInputStream = null;
        C11532b c11532b = new C11532b(null, 0L);
        try {
            try {
                m2774b = m2774b(context, str);
            } catch (Exception e) {
                e = e;
            }
            if (m2774b != null) {
                c11532b.f23486a = m2774b;
                C11647w.m2274a((Closeable) null);
                return c11532b;
            }
            C11531a m2777a = m2777a(str, z);
            if (m2777a != null) {
                c11532b.f23485a = m2777a.f23483a;
                byte[] bArr = m2777a.f23484a;
                if (bArr != null) {
                    if (z) {
                        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                        try {
                            int m2781a = m2781a(context, byteArrayInputStream2);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = m2781a;
                            c11532b.f23486a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                            byteArrayInputStream = byteArrayInputStream2;
                        } catch (Exception e2) {
                            e = e2;
                            byteArrayInputStream = byteArrayInputStream2;
                            AbstractC11049b.m5276a(e);
                            C11647w.m2274a((Closeable) byteArrayInputStream);
                            return c11532b;
                        } catch (Throwable th) {
                            th = th;
                            byteArrayInputStream = byteArrayInputStream2;
                            C11647w.m2274a((Closeable) byteArrayInputStream);
                            throw th;
                        }
                    } else {
                        c11532b.f23486a = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                    }
                }
                m2778a(context, m2777a.f23484a, str);
                C11647w.m2274a((Closeable) byteArrayInputStream);
                return c11532b;
            }
            C11647w.m2274a((Closeable) null);
            return c11532b;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.ae$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11531a {

        /* renamed from: a */
        int f23483a;

        /* renamed from: a */
        byte[] f23484a;

        public C11531a(byte[] bArr, int i) {
            this.f23484a = bArr;
            this.f23483a = i;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e4, code lost:
        if (r1 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00e6, code lost:
        r1.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0103, code lost:
        if (r1 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0106, code lost:
        return null;
     */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0108: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:64:0x0108 */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.xiaomi.push.service.C11529ae.C11531a m2777a(java.lang.String r10, boolean r11) {
        /*
            Method dump skipped, instructions count: 274
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11529ae.m2777a(java.lang.String, boolean):com.xiaomi.push.service.ae$a");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* renamed from: a */
    public static Bitmap m2780a(Context context, String str) {
        InputStream inputStream;
        InputStream inputStream2;
        int m2781a;
        Uri parse = Uri.parse(str);
        try {
            try {
                inputStream = context.getContentResolver().openInputStream(parse);
                try {
                    m2781a = m2781a((Context) context, inputStream);
                    inputStream2 = context.getContentResolver().openInputStream(parse);
                } catch (IOException e) {
                    e = e;
                    inputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                    context = 0;
                    C11647w.m2274a((Closeable) context);
                    C11647w.m2274a((Closeable) inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                inputStream2 = null;
                inputStream = null;
            } catch (Throwable th2) {
                th = th2;
                context = 0;
                inputStream = null;
            }
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = m2781a;
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream2, null, options);
                C11647w.m2274a((Closeable) inputStream2);
                C11647w.m2274a((Closeable) inputStream);
                return decodeStream;
            } catch (IOException e3) {
                e = e3;
                AbstractC11049b.m5276a(e);
                C11647w.m2274a((Closeable) inputStream2);
                C11647w.m2274a((Closeable) inputStream);
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    private static int m2781a(Context context, InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            AbstractC11049b.m5282a("decode dimension failed for bitmap.");
            return 1;
        }
        int round = Math.round((context.getResources().getDisplayMetrics().densityDpi / 160.0f) * 48.0f);
        if (options.outWidth <= round || options.outHeight <= round) {
            return 1;
        }
        return Math.min(options.outWidth / round, options.outHeight / round);
    }

    /* renamed from: b */
    private static synchronized Bitmap m2774b(Context context, String str) {
        Bitmap bitmap;
        File file;
        synchronized (C11529ae.class) {
            FileInputStream fileInputStream = null;
            Bitmap bitmap2 = null;
            try {
                file = new File(m2783a(context), C11184bb.m4757a(str));
            } catch (Throwable th) {
                th = th;
                bitmap = null;
            }
            if (!file.exists()) {
                C11647w.m2274a((Closeable) null);
                return null;
            } else if (System.currentTimeMillis() - file.lastModified() > 1209600000) {
                AbstractC11049b.m5282a("The pic cache has expired.");
                C11647w.m2274a((Closeable) null);
                return null;
            } else {
                FileInputStream fileInputStream2 = new FileInputStream(file);
                try {
                    bitmap2 = BitmapFactory.decodeStream(fileInputStream2);
                    file.setLastModified(System.currentTimeMillis());
                    C11647w.m2274a((Closeable) fileInputStream2);
                    bitmap = bitmap2;
                } catch (Throwable th2) {
                    Bitmap bitmap3 = bitmap2;
                    fileInputStream = fileInputStream2;
                    th = th2;
                    bitmap = bitmap3;
                    AbstractC11049b.m5268d("Load bmp from cache error: " + th);
                    C11647w.m2274a((Closeable) fileInputStream);
                    return bitmap;
                }
                return bitmap;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.io.OutputStream, java.io.FileOutputStream] */
    /* renamed from: a */
    private static void m2778a(Context context, byte[] bArr, String str) {
        if (bArr == null) {
            AbstractC11049b.m5282a("cannot save small icon cause bitmap is null");
            return;
        }
        m2782a(context);
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                File file = new File(m2783a(context));
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, C11184bb.m4757a((String) str));
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                str = new FileOutputStream(file2);
                try {
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(str);
                    try {
                        bufferedOutputStream2.write(bArr);
                        bufferedOutputStream2.flush();
                        f23480a += file2.length();
                        if (f23482b <= 0) {
                            f23482b = file2.lastModified();
                        } else {
                            f23482b = Math.min(f23482b, file2.lastModified());
                        }
                        C11647w.m2274a(bufferedOutputStream2);
                        str = str;
                    } catch (Exception e) {
                        e = e;
                        bufferedOutputStream = bufferedOutputStream2;
                        AbstractC11049b.m5268d("Save pic error: " + e);
                        C11647w.m2274a(bufferedOutputStream);
                        str = str;
                        C11647w.m2274a((Closeable) str);
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        C11647w.m2274a(bufferedOutputStream);
                        C11647w.m2274a((Closeable) str);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                str = 0;
            } catch (Throwable th2) {
                th = th2;
                str = 0;
            }
            C11647w.m2274a((Closeable) str);
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    private static synchronized void m2782a(Context context) {
        long j;
        synchronized (C11529ae.class) {
            m2775b(context);
            if (f23480a >= 62914560 || System.currentTimeMillis() - f23482b >= 1209600000) {
                File file = new File(m2783a(context));
                if (!file.exists()) {
                    AbstractC11049b.m5282a("The pic cache dir do not exists.");
                    return;
                }
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    m2776a(listFiles);
                    long j2 = f23480a;
                    int length = listFiles.length - 1;
                    while (true) {
                        if (length < 0) {
                            j = 0;
                            break;
                        }
                        File file2 = listFiles[length];
                        if (file2 != null) {
                            if (j2 <= 31457280 && System.currentTimeMillis() - file2.lastModified() <= 864000000) {
                                j = file2.lastModified();
                                break;
                            }
                            j2 -= file2.length();
                            file2.delete();
                        }
                        length--;
                    }
                    f23480a = Math.max(j2, 0L);
                    f23482b = j;
                } else {
                    AbstractC11049b.m5282a("The pic cache file list is null.");
                }
            }
        }
    }

    /* renamed from: b */
    private static synchronized void m2775b(Context context) {
        synchronized (C11529ae.class) {
            if (f23481a) {
                return;
            }
            f23480a = 0L;
            f23482b = 0L;
            File file = new File(m2783a(context));
            if (!file.exists()) {
                f23481a = true;
                AbstractC11049b.m5274b("Init pic cache finish.");
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    f23480a += file2.length();
                    if (f23482b <= 0) {
                        f23482b = file2.lastModified();
                    } else {
                        f23482b = Math.min(f23482b, file2.lastModified());
                    }
                }
            }
            f23481a = true;
            AbstractC11049b.m5274b("Init pic cache finish.");
        }
    }

    /* renamed from: a */
    private static void m2776a(File[] fileArr) {
        if (fileArr != null) {
            try {
                if (fileArr.length > 1) {
                    Arrays.sort(fileArr, new Comparator<File>() { // from class: com.xiaomi.push.service.ae.1
                        @Override // java.util.Comparator
                        /* renamed from: a */
                        public int compare(File file, File file2) {
                            if (file == file2) {
                                return 0;
                            }
                            if (file == null) {
                                return 1;
                            }
                            if (file2 == null) {
                                return -1;
                            }
                            int i = ((file.lastModified() - file2.lastModified()) > 0L ? 1 : ((file.lastModified() - file2.lastModified()) == 0L ? 0 : -1));
                            if (i == 0) {
                                return 0;
                            }
                            return i < 0 ? 1 : -1;
                        }
                    });
                }
            } catch (Throwable th) {
                AbstractC11049b.m5268d("Sort pic cache error: " + th);
            }
        }
    }

    /* renamed from: a */
    private static String m2783a(Context context) {
        return context.getCacheDir().getPath() + File.separator + "mipush_icon";
    }
}
