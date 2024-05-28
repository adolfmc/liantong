package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.SecureRandom;

/* renamed from: a.a.a.a.a.e.a.c.l0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0553b implements InterfaceC0552a {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public int mo22849a(byte[] bArr, int i) {
        byte length = (byte) (bArr.length - i);
        while (i < bArr.length) {
            bArr[i] = length;
            i++;
        }
        return length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public String mo22852a() {
        return "PKCS7";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public void mo22851a(SecureRandom secureRandom) {
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a
    /* renamed from: a */
    public int mo22850a(byte[] bArr) {
        int i = bArr[bArr.length - 1] & 255;
        if (i > bArr.length || i == 0) {
            throw new InvalidCipherTextException("pad block corrupted");
        }
        for (int i2 = 1; i2 <= i; i2++) {
            if (bArr[bArr.length - i2] != i) {
                throw new InvalidCipherTextException("pad block corrupted");
            }
        }
        return i;
    }
}
