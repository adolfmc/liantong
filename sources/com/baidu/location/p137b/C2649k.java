package com.baidu.location.p137b;

import com.baidu.location.p140e.C2734j;
import com.baidu.location.p140e.C2735k;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.b.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2649k {

    /* renamed from: a */
    private static C2649k f5278a = null;

    /* renamed from: b */
    private static String f5279b = "Temp_in.dat";

    /* renamed from: c */
    private static File f5280c = new File(C2734j.f5740a, f5279b);

    /* renamed from: d */
    private static StringBuffer f5281d = null;

    /* renamed from: e */
    private static boolean f5282e = true;

    /* renamed from: f */
    private static int f5283f = 0;

    /* renamed from: g */
    private static int f5284g = 0;

    /* renamed from: h */
    private static long f5285h = 0;

    /* renamed from: i */
    private static long f5286i = 0;

    /* renamed from: j */
    private static long f5287j = 0;

    /* renamed from: k */
    private static double f5288k = 0.0d;

    /* renamed from: l */
    private static double f5289l = 0.0d;

    /* renamed from: m */
    private static int f5290m = 0;

    /* renamed from: n */
    private static int f5291n = 0;

    /* renamed from: o */
    private static int f5292o = 0;

    /* renamed from: a */
    public static String m19443a() {
        RandomAccessFile randomAccessFile;
        int readInt;
        int readInt2;
        int readInt3;
        int i;
        File file = f5280c;
        if (file != null && file.exists()) {
            try {
                randomAccessFile = new RandomAccessFile(f5280c, "rw");
                randomAccessFile.seek(0L);
                readInt = randomAccessFile.readInt();
                readInt2 = randomAccessFile.readInt();
                readInt3 = randomAccessFile.readInt();
            } catch (IOException unused) {
            }
            if (!m19442a(readInt, readInt2, readInt3)) {
                randomAccessFile.close();
                m19440c();
                return null;
            }
            if (readInt2 != 0 && readInt2 != readInt3) {
                long j = ((readInt2 - 1) * 1024) + 12 + 0;
                randomAccessFile.seek(j);
                int readInt4 = randomAccessFile.readInt();
                byte[] bArr = new byte[readInt4];
                randomAccessFile.seek(j + 4);
                for (int i2 = 0; i2 < readInt4; i2++) {
                    bArr[i2] = randomAccessFile.readByte();
                }
                String str = new String(bArr);
                int i3 = 1;
                if (readInt < C2735k.f5788ae) {
                    i = readInt2 + 1;
                } else {
                    if (readInt2 != C2735k.f5788ae) {
                        i3 = 1 + readInt2;
                    }
                    i = i3;
                }
                randomAccessFile.seek(4L);
                randomAccessFile.writeInt(i);
                randomAccessFile.close();
                return str;
            }
            randomAccessFile.close();
            return null;
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m19442a(int i, int i2, int i3) {
        int i4;
        return i >= 0 && i <= C2735k.f5788ae && i2 >= 0 && i2 <= (i4 = i + 1) && i3 >= 1 && i3 <= i4 && i3 <= C2735k.f5788ae;
    }

    /* renamed from: b */
    private static void m19441b() {
        f5282e = true;
        f5281d = null;
        f5283f = 0;
        f5284g = 0;
        f5285h = 0L;
        f5286i = 0L;
        f5287j = 0L;
        f5288k = 0.0d;
        f5289l = 0.0d;
        f5290m = 0;
        f5291n = 0;
        f5292o = 0;
    }

    /* renamed from: c */
    private static boolean m19440c() {
        if (f5280c.exists()) {
            f5280c.delete();
        }
        if (!f5280c.getParentFile().exists()) {
            f5280c.getParentFile().mkdirs();
        }
        try {
            f5280c.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(f5280c, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            m19441b();
            return f5280c.exists();
        } catch (IOException unused) {
            return false;
        }
    }
}
