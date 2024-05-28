package com.baidu.p122b.p132f;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.f.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2422a {

    /* renamed from: a */
    private String f4273a;

    /* renamed from: b */
    private boolean f4274b;

    /* renamed from: c */
    private boolean f4275c;

    public C2422a(String str, boolean z, boolean z2) {
        this.f4273a = str;
        this.f4274b = z;
        this.f4275c = z2;
    }

    /* renamed from: a */
    private static int m20181a(int i) {
        switch (i) {
            case 1:
                return 6;
            case 2:
                return 4;
            case 3:
                return 3;
            case 4:
                return 1;
            case 5:
                return 0;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    public String m20180a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < (bArr.length + 4) / 5; i++) {
            short[] sArr = new short[5];
            int[] iArr = new int[8];
            int i2 = 5;
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = (i * 5) + i3;
                if (i4 < bArr.length) {
                    sArr[i3] = (short) (bArr[i4] & 255);
                } else {
                    sArr[i3] = 0;
                    i2--;
                }
            }
            int m20181a = m20181a(i2);
            iArr[0] = (byte) ((sArr[0] >> 3) & 31);
            iArr[1] = (byte) (((sArr[0] & 7) << 2) | ((sArr[1] >> 6) & 3));
            iArr[2] = (byte) ((sArr[1] >> 1) & 31);
            iArr[3] = (byte) (((sArr[1] & 1) << 4) | ((sArr[2] >> 4) & 15));
            iArr[4] = (byte) (((sArr[2] & 15) << 1) | (1 & (sArr[3] >> 7)));
            iArr[5] = (byte) ((sArr[3] >> 2) & 31);
            iArr[6] = (byte) (((sArr[4] >> 5) & 7) | ((sArr[3] & 3) << 3));
            iArr[7] = (byte) (sArr[4] & 31);
            for (int i5 = 0; i5 < iArr.length - m20181a; i5++) {
                char charAt = this.f4273a.charAt(iArr[i5]);
                if (this.f4275c) {
                    charAt = Character.toLowerCase(charAt);
                }
                byteArrayOutputStream.write(charAt);
            }
            if (this.f4274b) {
                for (int length = iArr.length - m20181a; length < iArr.length; length++) {
                    byteArrayOutputStream.write(61);
                }
            }
        }
        return new String(byteArrayOutputStream.toByteArray());
    }
}
