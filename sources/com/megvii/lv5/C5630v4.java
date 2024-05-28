package com.megvii.lv5;

import android.os.SystemClock;
import android.text.TextUtils;
import com.megvii.lv5.InterfaceC5509m3;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.v4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5630v4 implements InterfaceC5509m3 {

    /* renamed from: a */
    public final Map<String, C5631a> f13769a = new LinkedHashMap(16, 0.75f, true);

    /* renamed from: b */
    public long f13770b = 0;

    /* renamed from: c */
    public final File f13771c;

    /* renamed from: d */
    public final int f13772d;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.v4$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5632b extends FilterInputStream {

        /* renamed from: a */
        public final long f13781a;

        /* renamed from: b */
        public long f13782b;

        public C5632b(InputStream inputStream, long j) {
            super(inputStream);
            this.f13781a = j;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() {
            int read = super.read();
            if (read != -1) {
                this.f13782b++;
            }
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            int read = super.read(bArr, i, i2);
            if (read != -1) {
                this.f13782b += read;
            }
            return read;
        }
    }

    public C5630v4(File file, int i) {
        this.f13771c = file;
        this.f13772d = i;
    }

    /* renamed from: a */
    public static void m12955a(OutputStream outputStream, int i) {
        outputStream.write((i >> 0) & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write((i >> 24) & 255);
    }

    /* renamed from: a */
    public static void m12954a(OutputStream outputStream, long j) {
        outputStream.write((byte) (j >>> 0));
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    /* renamed from: a */
    public static void m12953a(OutputStream outputStream, String str) {
        byte[] bytes = str.getBytes("UTF-8");
        m12954a(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    /* renamed from: b */
    public static long m12949b(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            long j = ((read & 255) << 0) | 0;
            int read2 = inputStream.read();
            if (read2 != -1) {
                long j2 = j | ((read2 & 255) << 8);
                int read3 = inputStream.read();
                if (read3 != -1) {
                    long j3 = j2 | ((read3 & 255) << 16);
                    int read4 = inputStream.read();
                    if (read4 != -1) {
                        long j4 = j3 | ((read4 & 255) << 24);
                        int read5 = inputStream.read();
                        if (read5 != -1) {
                            long j5 = j4 | ((read5 & 255) << 32);
                            int read6 = inputStream.read();
                            if (read6 != -1) {
                                long j6 = j5 | ((read6 & 255) << 40);
                                int read7 = inputStream.read();
                                if (read7 != -1) {
                                    long j7 = j6 | ((read7 & 255) << 48);
                                    int read8 = inputStream.read();
                                    if (read8 != -1) {
                                        return ((read8 & 255) << 56) | j7;
                                    }
                                    throw new EOFException();
                                }
                                throw new EOFException();
                            }
                            throw new EOFException();
                        }
                        throw new EOFException();
                    }
                    throw new EOFException();
                }
                throw new EOFException();
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    /* renamed from: a */
    public synchronized InterfaceC5509m3.C5510a m12952a(String str) {
        C5631a c5631a = this.f13769a.get(str);
        if (c5631a == null) {
            return null;
        }
        File m12948b = m12948b(str);
        try {
            C5632b c5632b = new C5632b(new BufferedInputStream(new FileInputStream(m12948b)), m12948b.length());
            try {
                C5631a m12946a = C5631a.m12946a(c5632b);
                if (!TextUtils.equals(str, m12946a.f13774b)) {
                    C5440f4.m13536a("%s: key=%s, found=%s", m12948b.getAbsolutePath(), str, m12946a.f13774b);
                    C5631a remove = this.f13769a.remove(str);
                    if (remove != null) {
                        this.f13770b -= remove.f13773a;
                    }
                    return null;
                }
                return c5631a.m12944a(m12957a(c5632b, c5632b.f13781a - c5632b.f13782b));
            } finally {
                c5632b.close();
            }
        } catch (IOException e) {
            C5440f4.m13536a("%s: %s", m12948b.getAbsolutePath(), e.toString());
            synchronized (this) {
                boolean delete = m12948b(str).delete();
                C5631a remove2 = this.f13769a.remove(str);
                if (remove2 != null) {
                    this.f13770b -= remove2.f13773a;
                }
                if (!delete) {
                    C5440f4.m13536a("Could not delete cache entry for key=%s, filename=%s", str, m12947c(str));
                }
                return null;
            }
        }
    }

    /* renamed from: a */
    public final void m12950a(String str, C5631a c5631a) {
        if (this.f13769a.containsKey(str)) {
            this.f13770b += c5631a.f13773a - this.f13769a.get(str).f13773a;
        } else {
            this.f13770b += c5631a.f13773a;
        }
        this.f13769a.put(str, c5631a);
    }

    /* renamed from: b */
    public File m12948b(String str) {
        return new File(this.f13771c, m12947c(str));
    }

    /* renamed from: c */
    public final String m12947c(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(str.substring(0, length).hashCode());
        return valueOf + String.valueOf(str.substring(length).hashCode());
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.v4$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5631a {

        /* renamed from: a */
        public long f13773a;

        /* renamed from: b */
        public final String f13774b;

        /* renamed from: c */
        public final String f13775c;

        /* renamed from: d */
        public final long f13776d;

        /* renamed from: e */
        public final long f13777e;

        /* renamed from: f */
        public final long f13778f;

        /* renamed from: g */
        public final long f13779g;

        /* renamed from: h */
        public final Map<String, String> f13780h;

        public C5631a(String str, String str2, long j, long j2, long j3, long j4, Map<String, String> map) {
            this.f13774b = str;
            this.f13775c = "".equals(str2) ? null : str2;
            this.f13776d = j;
            this.f13777e = j2;
            this.f13778f = j3;
            this.f13779g = j4;
            this.f13780h = map;
        }

        /* renamed from: a */
        public static C5631a m12946a(C5632b c5632b) {
            C5632b c5632b2 = c5632b;
            if (C5630v4.m12956a(c5632b) == 538247942) {
                String str = new String(C5630v4.m12957a(c5632b2, C5630v4.m12949b(c5632b)), "UTF-8");
                String str2 = new String(C5630v4.m12957a(c5632b2, C5630v4.m12949b(c5632b)), "UTF-8");
                long m12949b = C5630v4.m12949b(c5632b);
                long m12949b2 = C5630v4.m12949b(c5632b);
                long m12949b3 = C5630v4.m12949b(c5632b);
                long m12949b4 = C5630v4.m12949b(c5632b);
                int m12956a = C5630v4.m12956a(c5632b);
                Map emptyMap = m12956a == 0 ? Collections.emptyMap() : new HashMap(m12956a);
                int i = 0;
                while (i < m12956a) {
                    emptyMap.put(new String(C5630v4.m12957a(c5632b2, C5630v4.m12949b(c5632b)), "UTF-8").intern(), new String(C5630v4.m12957a(c5632b2, C5630v4.m12949b(c5632b)), "UTF-8").intern());
                    i++;
                    m12956a = m12956a;
                    c5632b2 = c5632b;
                }
                return new C5631a(str, str2, m12949b, m12949b2, m12949b3, m12949b4, emptyMap);
            }
            throw new IOException();
        }

        /* renamed from: a */
        public InterfaceC5509m3.C5510a m12944a(byte[] bArr) {
            InterfaceC5509m3.C5510a c5510a = new InterfaceC5509m3.C5510a();
            c5510a.f12887a = bArr;
            c5510a.f12888b = this.f13775c;
            c5510a.f12889c = this.f13776d;
            c5510a.f12890d = this.f13777e;
            c5510a.f12891e = this.f13778f;
            c5510a.f12892f = this.f13779g;
            c5510a.f12893g = this.f13780h;
            return c5510a;
        }

        /* renamed from: a */
        public boolean m12945a(OutputStream outputStream) {
            try {
                C5630v4.m12955a(outputStream, 538247942);
                C5630v4.m12953a(outputStream, this.f13774b);
                String str = this.f13775c;
                if (str == null) {
                    str = "";
                }
                C5630v4.m12953a(outputStream, str);
                C5630v4.m12954a(outputStream, this.f13776d);
                C5630v4.m12954a(outputStream, this.f13777e);
                C5630v4.m12954a(outputStream, this.f13778f);
                C5630v4.m12954a(outputStream, this.f13779g);
                Map<String, String> map = this.f13780h;
                if (map != null) {
                    C5630v4.m12955a(outputStream, map.size());
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        C5630v4.m12953a(outputStream, entry.getKey());
                        C5630v4.m12953a(outputStream, entry.getValue());
                    }
                } else {
                    C5630v4.m12955a(outputStream, 0);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                C5440f4.m13536a("%s", e.toString());
                return false;
            }
        }
    }

    /* renamed from: a */
    public synchronized void m12951a(String str, InterfaceC5509m3.C5510a c5510a) {
        long length = c5510a.f12887a.length;
        if (this.f13770b + length >= this.f13772d) {
            if (C5440f4.f12600a) {
                C5440f4.m13535b("Pruning old cache entries.", new Object[0]);
            }
            long j = this.f13770b;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            Iterator<Map.Entry<String, C5631a>> it = this.f13769a.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                C5631a value = it.next().getValue();
                if (m12948b(value.f13774b).delete()) {
                    this.f13770b -= value.f13773a;
                } else {
                    String str2 = value.f13774b;
                    C5440f4.m13536a("Could not delete cache entry for key=%s, filename=%s", str2, m12947c(str2));
                }
                it.remove();
                i++;
                if (((float) (this.f13770b + length)) < this.f13772d * 0.9f) {
                    break;
                }
            }
            if (C5440f4.f12600a) {
                C5440f4.m13535b("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.f13770b - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
            }
        }
        File m12948b = m12948b(str);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(m12948b));
            C5631a c5631a = new C5631a(str, c5510a.f12888b, c5510a.f12889c, c5510a.f12890d, c5510a.f12891e, c5510a.f12892f, c5510a.f12893g);
            c5631a.f13773a = c5510a.f12887a.length;
            if (!c5631a.m12945a(bufferedOutputStream)) {
                bufferedOutputStream.close();
                C5440f4.m13536a("Failed to write header for %s", m12948b.getAbsolutePath());
                throw new IOException();
            }
            bufferedOutputStream.write(c5510a.f12887a);
            bufferedOutputStream.close();
            m12950a(str, c5631a);
        } catch (IOException unused) {
            if (m12948b.delete()) {
                return;
            }
            C5440f4.m13536a("Could not clean up file %s", m12948b.getAbsolutePath());
        }
    }

    /* renamed from: a */
    public static int m12956a(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            int i = (read << 0) | 0;
            int read2 = inputStream.read();
            if (read2 != -1) {
                int i2 = i | (read2 << 8);
                int read3 = inputStream.read();
                if (read3 != -1) {
                    int i3 = i2 | (read3 << 16);
                    int read4 = inputStream.read();
                    if (read4 != -1) {
                        return (read4 << 24) | i3;
                    }
                    throw new EOFException();
                }
                throw new EOFException();
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    /* renamed from: a */
    public static byte[] m12957a(C5632b c5632b, long j) {
        long j2 = c5632b.f13781a - c5632b.f13782b;
        if (j >= 0 && j <= j2) {
            int i = (int) j;
            if (i == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(c5632b).readFully(bArr);
                return bArr;
            }
        }
        throw new IOException("streamToBytes length=" + j + ", maxLength=" + j2);
    }
}
