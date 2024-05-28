package org.p415a.p445f;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import org.p415a.p424c.C12577a;
import org.p415a.p448g.p449a.C12959a;
import org.p415a.p448g.p449a.C12961c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12953x {

    /* renamed from: a */
    private static String f26233a = "BC";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.a.f.x$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C12954a extends BufferedInputStream {
        C12954a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.BufferedInputStream, java.io.FilterInputStream, java.io.InputStream
        public synchronized int available() {
            int available;
            available = super.available();
            if (available < 0) {
                available = Integer.MAX_VALUE;
            }
            return available;
        }
    }

    /* renamed from: a */
    public static InputStream m444a(InputStream inputStream) {
        if (!inputStream.markSupported()) {
            inputStream = new C12954a(inputStream);
        }
        inputStream.mark(60);
        int read = inputStream.read();
        if ((read & 128) != 0) {
            inputStream.reset();
            return inputStream;
        } else if (!m446a(read)) {
            inputStream.reset();
            return new C12577a(inputStream);
        } else {
            byte[] bArr = new byte[60];
            bArr[0] = (byte) read;
            int i = 1;
            int i2 = 1;
            while (i != 60) {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    break;
                } else if (!m446a(read2)) {
                    inputStream.reset();
                    return new C12577a(inputStream);
                } else {
                    if (read2 != 10 && read2 != 13) {
                        bArr[i2] = (byte) read2;
                        i2++;
                    }
                    i++;
                }
            }
            inputStream.reset();
            if (i < 4) {
                return new C12577a(inputStream);
            }
            byte[] bArr2 = new byte[8];
            System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
            try {
                return (C12959a.m426b(bArr2)[0] & 128) != 0 ? new C12577a(inputStream, false) : new C12577a(inputStream);
            } catch (C12961c e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /* renamed from: a */
    private static boolean m446a(int i) {
        return (i >= 65 && i <= 90) || (i >= 97 && i <= 122) || ((i >= 48 && i <= 57) || i == 43 || i == 47 || i == 13 || i == 10);
    }

    /* renamed from: a */
    public static byte[] m445a(int i, SecureRandom secureRandom) {
        int i2 = 256;
        int i3 = 128;
        switch (i) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 7:
            case 11:
                break;
            case 2:
            case 8:
            case 12:
                i3 = 192;
                break;
            case 6:
                i2 = 64;
            case 9:
            case 10:
            case 13:
                i3 = i2;
                break;
            default:
                throw new C12934f("unknown symmetric algorithm: " + i);
        }
        byte[] bArr = new byte[(i3 + 7) / 8];
        secureRandom.nextBytes(bArr);
        return bArr;
    }
}
