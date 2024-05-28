package org.p415a.p418b;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bz */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12466bz {

    /* renamed from: a */
    private static final long f25244a = Runtime.getRuntime().maxMemory();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1694a(int i) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m1693a(InputStream inputStream) {
        if (inputStream instanceof AbstractC12464bx) {
            return ((AbstractC12464bx) inputStream).mo1698a();
        }
        if (inputStream instanceof C12559k) {
            return ((C12559k) inputStream).m1657a();
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
        long j = f25244a;
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static int m1692b(int i) {
        if (i >= 31) {
            if (i < 128) {
                return 2;
            }
            byte[] bArr = new byte[5];
            int length = bArr.length - 1;
            bArr[length] = (byte) (i & 127);
            do {
                i >>= 7;
                length--;
                bArr[length] = (byte) ((i & 127) | 128);
            } while (i > 127);
            return 1 + (bArr.length - length);
        }
        return 1;
    }
}
