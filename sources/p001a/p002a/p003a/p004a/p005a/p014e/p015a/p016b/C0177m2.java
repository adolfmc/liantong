package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.m2 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0177m2 {

    /* renamed from: a */
    public static final long f214a = Runtime.getRuntime().maxMemory();

    /* renamed from: a */
    public static int m24099a(int i) {
        int i2 = 1;
        if (i > 127) {
            int i3 = 1;
            while (true) {
                i >>>= 8;
                if (i == 0) {
                    break;
                }
                i3++;
            }
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                i2++;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public static int m24098a(InputStream inputStream) {
        if (inputStream instanceof AbstractC0169k2) {
            return ((AbstractC0169k2) inputStream).mo24110a();
        }
        if (inputStream instanceof C0162j) {
            return ((C0162j) inputStream).m24128b();
        }
        if (inputStream instanceof ByteArrayInputStream) {
            return ((ByteArrayInputStream) inputStream).available();
        }
        if (inputStream instanceof FileInputStream) {
            try {
                FileChannel channel = ((FileInputStream) inputStream).getChannel();
                long size = channel != null ? channel.size() : 2147483647L;
                if (size < 2147483647L) {
                    return (int) size;
                }
            } catch (IOException unused) {
            }
        }
        long j = f214a;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    /* renamed from: b */
    public static int m24097b(int i) {
        if (i >= 31) {
            if (i < 128) {
                return 2;
            }
            byte[] bArr = new byte[5];
            int i2 = 4;
            do {
                i >>= 7;
                i2--;
                bArr[i2] = (byte) ((i & 127) | 128);
            } while (i > 127);
            return 1 + (5 - i2);
        }
        return 1;
    }
}
