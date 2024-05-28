package p000;

import java.io.OutputStream;

/* renamed from: c0  reason: case insensitive filesystem */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class BASE64Encoder extends CharacterEncoder {

    /* renamed from: b */
    public static final char[] f2536b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

    @Override // p000.CharacterEncoder
    /* renamed from: a */
    public int mo2118a() {
        return 3;
    }

    @Override // p000.CharacterEncoder
    /* renamed from: a */
    public void mo2113a(OutputStream outputStream, byte[] bArr, int i, int i2) {
        if (i2 == 1) {
            byte b = bArr[i];
            char[] cArr = f2536b;
            outputStream.write(cArr[(b >>> 2) & 63]);
            outputStream.write(cArr[((b << 4) & 48) + 0]);
            outputStream.write(61);
            outputStream.write(61);
        } else if (i2 == 2) {
            byte b2 = bArr[i];
            byte b3 = bArr[i + 1];
            char[] cArr2 = f2536b;
            outputStream.write(cArr2[(b2 >>> 2) & 63]);
            outputStream.write(cArr2[((b2 << 4) & 48) + ((b3 >>> 4) & 15)]);
            outputStream.write(cArr2[((b3 << 2) & 60) + 0]);
            outputStream.write(61);
        } else {
            byte b4 = bArr[i];
            byte b5 = bArr[i + 1];
            byte b6 = bArr[i + 2];
            char[] cArr3 = f2536b;
            outputStream.write(cArr3[(b4 >>> 2) & 63]);
            outputStream.write(cArr3[((b4 << 4) & 48) + ((b5 >>> 4) & 15)]);
            outputStream.write(cArr3[((b5 << 2) & 60) + ((b6 >>> 6) & 3)]);
            outputStream.write(cArr3[b6 & 63]);
        }
    }

    @Override // p000.CharacterEncoder
    /* renamed from: b */
    public int mo2111b() {
        return 57;
    }
}
