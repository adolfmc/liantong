package com.baidu.p122b.p125c.p129d;

import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.d.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2413f {

    /* renamed from: h */
    private static final Map<String, byte[]> f4252h = Collections.synchronizedMap(new HashMap());

    /* renamed from: a */
    private final int f4253a;

    /* renamed from: b */
    private final int f4254b;

    /* renamed from: c */
    private SecureRandom f4255c;

    /* renamed from: d */
    private final int f4256d;

    /* renamed from: e */
    private MessageDigest f4257e;

    /* renamed from: f */
    private MessageDigest f4258f;

    /* renamed from: g */
    private byte[] f4259g;

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private C2413f(int i, int i2, SecureRandom secureRandom, OAEPParameterSpec oAEPParameterSpec) {
        this.f4253a = i;
        this.f4254b = i2;
        this.f4255c = secureRandom;
        if (i2 < 64) {
            throw new InvalidKeyException("Padded size must be at least 64");
        }
        switch (i) {
            case 1:
            case 2:
                i2 -= 11;
                break;
            case 3:
                break;
            case 4:
                String str = "SHA-1";
                String str2 = "SHA-1";
                byte[] bArr = null;
                if (oAEPParameterSpec != null) {
                    try {
                        str = oAEPParameterSpec.getDigestAlgorithm();
                        String mGFAlgorithm = oAEPParameterSpec.getMGFAlgorithm();
                        if (!mGFAlgorithm.equalsIgnoreCase("MGF1")) {
                            throw new InvalidAlgorithmParameterException("Unsupported MGF algo: " + mGFAlgorithm);
                        }
                        str2 = ((MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters()).getDigestAlgorithm();
                        PSource pSource = oAEPParameterSpec.getPSource();
                        String algorithm = pSource.getAlgorithm();
                        if (!algorithm.equalsIgnoreCase("PSpecified")) {
                            throw new InvalidAlgorithmParameterException("Unsupported pSource algo: " + algorithm);
                        }
                        bArr = ((PSource.PSpecified) pSource).getValue();
                    } catch (NoSuchAlgorithmException e) {
                        throw new InvalidKeyException("Digest SHA-1 not available", e);
                    }
                }
                this.f4257e = MessageDigest.getInstance(str);
                this.f4258f = MessageDigest.getInstance(str2);
                this.f4259g = m20222a(this.f4257e, bArr);
                this.f4256d = (i2 - 2) - (this.f4259g.length * 2);
                if (this.f4256d > 0) {
                    return;
                }
                throw new InvalidKeyException("Key is too short for encryption using OAEPPadding with " + str + " and MGF1" + str2);
            default:
                throw new InvalidKeyException("Invalid padding: " + i);
        }
        this.f4256d = i2;
    }

    /* renamed from: a */
    public static C2413f m20224a(int i, int i2, SecureRandom secureRandom) {
        return new C2413f(i, i2, secureRandom, null);
    }

    /* renamed from: a */
    public static C2413f m20223a(int i, int i2, SecureRandom secureRandom, OAEPParameterSpec oAEPParameterSpec) {
        return new C2413f(i, i2, secureRandom, oAEPParameterSpec);
    }

    /* renamed from: a */
    private void m20219a(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        byte[] bArr3 = new byte[4];
        byte[] bArr4 = new byte[20];
        while (i4 > 0) {
            this.f4258f.update(bArr, i, i2);
            this.f4258f.update(bArr3);
            try {
                this.f4258f.digest(bArr4, 0, bArr4.length);
                for (int i5 = 0; i5 < bArr4.length && i4 > 0; i5++) {
                    bArr2[i3] = (byte) (bArr2[i3] ^ bArr4[i5]);
                    i4--;
                    i3++;
                }
                if (i4 > 0) {
                    int length = bArr3.length;
                    while (true) {
                        length--;
                        byte b = (byte) (bArr3[length] + 1);
                        bArr3[length] = b;
                        if (b == 0 && length > 0) {
                        }
                    }
                }
            } catch (DigestException e) {
                throw new BadPaddingException(e.toString());
            }
        }
    }

    /* renamed from: a */
    private static byte[] m20222a(MessageDigest messageDigest, byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            String algorithm = messageDigest.getAlgorithm();
            byte[] bArr2 = f4252h.get(algorithm);
            if (bArr2 == null) {
                byte[] digest = messageDigest.digest();
                f4252h.put(algorithm, digest);
                return digest;
            }
            return bArr2;
        }
        return messageDigest.digest(bArr);
    }

    /* renamed from: c */
    private byte[] m20217c(byte[] bArr) {
        int i;
        int i2;
        int i3 = this.f4254b;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, 0, bArr2, i3 - bArr.length, bArr.length);
        int length = (this.f4254b - 3) - bArr.length;
        bArr2[0] = 0;
        int i4 = this.f4253a;
        bArr2[1] = (byte) i4;
        int i5 = -1;
        int i6 = 2;
        if (i4 != 1) {
            if (this.f4255c == null) {
                this.f4255c = C2409b.f4239a;
            }
            byte[] bArr3 = new byte[64];
            while (true) {
                int i7 = length - 1;
                if (length <= 0) {
                    break;
                }
                while (true) {
                    if (i5 < 0) {
                        this.f4255c.nextBytes(bArr3);
                        i5 = bArr3.length - 1;
                    }
                    i = i5 - 1;
                    i2 = bArr3[i5] & 255;
                    if (i2 != 0) {
                        break;
                    }
                    i5 = i;
                }
                bArr2[i6] = (byte) i2;
                i5 = i;
                length = i7;
                i6++;
            }
        } else {
            while (true) {
                int i8 = length - 1;
                if (length <= 0) {
                    break;
                }
                bArr2[i6] = -1;
                i6++;
                length = i8;
            }
        }
        return bArr2;
    }

    /* renamed from: d */
    private byte[] m20216d(byte[] bArr) {
        if (bArr[0] != 0) {
            throw new BadPaddingException("Data must start with zero");
        }
        int i = 2;
        if (bArr[1] != this.f4253a) {
            throw new BadPaddingException("Blocktype mismatch: " + ((int) bArr[1]));
        }
        while (true) {
            int i2 = i + 1;
            int i3 = bArr[i] & 255;
            if (i3 == 0) {
                int length = bArr.length - i2;
                if (length <= this.f4256d) {
                    byte[] bArr2 = new byte[length];
                    System.arraycopy(bArr, bArr.length - length, bArr2, 0, length);
                    return bArr2;
                }
                throw new BadPaddingException("Padding string too short");
            } else if (i2 == bArr.length) {
                throw new BadPaddingException("Padding string not terminated");
            } else {
                if (this.f4253a == 1 && i3 != 255) {
                    throw new BadPaddingException("Padding byte not 0xff: " + i3);
                }
                i = i2;
            }
        }
    }

    /* renamed from: e */
    private byte[] m20215e(byte[] bArr) {
        if (this.f4255c == null) {
            this.f4255c = C2409b.f4239a;
        }
        int length = this.f4259g.length;
        byte[] bArr2 = new byte[length];
        this.f4255c.nextBytes(bArr2);
        byte[] bArr3 = new byte[this.f4254b];
        System.arraycopy(bArr2, 0, bArr3, 1, length);
        int i = length + 1;
        int length2 = bArr3.length - i;
        int length3 = this.f4254b - bArr.length;
        System.arraycopy(this.f4259g, 0, bArr3, i, length);
        bArr3[length3 - 1] = 1;
        System.arraycopy(bArr, 0, bArr3, length3, bArr.length);
        m20219a(bArr3, 1, length, bArr3, i, length2);
        m20219a(bArr3, i, length2, bArr3, 1, length);
        return bArr3;
    }

    /* renamed from: f */
    private byte[] m20214f(byte[] bArr) {
        int length = this.f4259g.length;
        if (bArr[0] == 0) {
            int i = length + 1;
            int length2 = bArr.length - i;
            m20219a(bArr, i, length2, bArr, 1, length);
            m20219a(bArr, 1, length, bArr, i, length2);
            for (int i2 = 0; i2 < length; i2++) {
                if (this.f4259g[i2] != bArr[i + i2]) {
                    throw new BadPaddingException("lHash mismatch");
                }
            }
            int i3 = i + length;
            while (bArr[i3] == 0) {
                i3++;
                if (i3 >= bArr.length) {
                    throw new BadPaddingException("Padding string not terminated");
                }
            }
            int i4 = i3 + 1;
            if (bArr[i3] == 1) {
                int length3 = bArr.length - i4;
                byte[] bArr2 = new byte[length3];
                System.arraycopy(bArr, i4, bArr2, 0, length3);
                return bArr2;
            }
            throw new BadPaddingException("Padding string not terminated by 0x01 byte");
        }
        throw new BadPaddingException("Data must start with zero");
    }

    /* renamed from: a */
    public int m20225a() {
        return this.f4256d;
    }

    /* renamed from: a */
    public byte[] m20221a(byte[] bArr) {
        if (bArr.length <= this.f4256d) {
            switch (this.f4253a) {
                case 1:
                case 2:
                    return m20217c(bArr);
                case 3:
                    return bArr;
                case 4:
                    return m20215e(bArr);
                default:
                    throw new AssertionError();
            }
        }
        throw new BadPaddingException("Data must be shorter than " + (this.f4256d + 1) + " bytes");
    }

    /* renamed from: a */
    public byte[] m20220a(byte[] bArr, int i, int i2) {
        return m20221a(C2409b.m20237a(bArr, i, i2));
    }

    /* renamed from: b */
    public byte[] m20218b(byte[] bArr) {
        if (bArr.length != this.f4254b) {
            throw new BadPaddingException("Padded length must be " + this.f4254b);
        }
        switch (this.f4253a) {
            case 1:
            case 2:
                return m20216d(bArr);
            case 3:
                return bArr;
            case 4:
                return m20214f(bArr);
            default:
                throw new AssertionError();
        }
    }
}
