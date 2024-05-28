package com.taisys.oti;

import android.annotation.SuppressLint;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"NewApi"})
/* renamed from: com.taisys.oti.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10349d {

    /* renamed from: a */
    public static final String f19905a = "02810000";

    /* renamed from: b */
    public static final String f19906b = "000000108021000008";

    /* renamed from: c */
    public static final String f19907c = "02810000028102000000108021000008";

    /* renamed from: a */
    public static byte[] m6278a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(C10350e.m6270a("02810000"));
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(C10350e.m6270a("000000108021000008"));
            byteArrayOutputStream.write((byte) bArr2.length);
            byteArrayOutputStream.write(bArr2);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return bArr3;
        }
    }

    /* renamed from: a */
    public static byte[] m6282a(SmsMessage smsMessage, String str) {
        byte[] bArr;
        if (smsMessage == null) {
            return null;
        }
        byte[] bArr2 = null;
        try {
            bArr = smsMessage.getUserData();
        } catch (Exception e) {
            e.printStackTrace();
            bArr = bArr2;
        }
        if (bArr == null) {
            C10350e.m6257d("userData is null");
        } else {
            C10350e.m6257d("userData = " + C10350e.m6262b(bArr));
        }
        if (bArr == null || !C10350e.m6262b(bArr).startsWith(str)) {
            byte[] pdu = smsMessage.getPdu();
            C10350e.m6257d(C10350e.m6262b(pdu));
            if (pdu != null) {
                String m6262b = C10350e.m6262b(pdu);
                if (m6262b.startsWith("02810000028102000000108021000008")) {
                    String substring = m6262b.substring(32);
                    if (!TextUtils.isEmpty(substring)) {
                        byte b = C10350e.m6270a(substring)[0];
                        bArr2 = C10350e.m6270a(substring.substring(2, (b > 0 ? b + 1 : b + 257) * 2));
                    }
                }
            }
            if (bArr2 == null) {
                C10350e.m6257d("get message is null");
            }
            return bArr2;
        }
        return null;
    }

    /* renamed from: a */
    public static byte[] m6281a(List list, String str) {
        byte[] m6282a;
        byte[] bArr = null;
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                SmsMessage smsMessage = (SmsMessage) list.get(i2);
                if (smsMessage != null && (m6282a = m6282a(smsMessage, str)) != null) {
                    arrayList.add(m6282a);
                    i += m6282a.length;
                }
            }
            if (arrayList.size() > 0) {
                byte[] bArr2 = new byte[i];
                int i3 = 0;
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    byte[] bArr3 = (byte[]) arrayList.get(i4);
                    System.arraycopy(bArr3, 0, bArr2, i3, bArr3.length);
                    i3 += bArr3.length;
                }
                return bArr2;
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public static final String m6280a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length);
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                stringBuffer.append(0);
            }
            stringBuffer.append(hexString.toUpperCase());
        }
        return stringBuffer.toString();
    }

    /* renamed from: b */
    public static byte[] m6277b(byte[] bArr) throws Exception {
        return m6279a(bArr, false, '?');
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* renamed from: a */
    public static byte[] m6279a(byte[] bArr, boolean z, char c) throws Exception {
        ?? r7;
        byte[] bArr2 = {1, 3, 7, 15, 31, 63, Byte.MAX_VALUE};
        byte b = (byte) c;
        byte[] bArr3 = new byte[bArr.length - (bArr.length / 8)];
        int length = bArr.length;
        int i = 0;
        byte b2 = 0;
        int i2 = 0;
        while (i < length) {
            byte b3 = bArr[i];
            if (b3 > Byte.MAX_VALUE) {
                if (!z) {
                    throw new Exception("Invalid character detected: " + String.format("%2X", Byte.valueOf(b3)));
                }
                b3 = b;
            }
            if (b2 == 7) {
                r7 = 0;
            } else {
                bArr3[i2] = (byte) (b3 >> b2);
                r7 = b2 + 1;
                i2++;
            }
            i++;
            b2 = r7;
        }
        byte[] bArr4 = new byte[bArr3.length];
        int i3 = 0;
        int i4 = 1;
        int i5 = 1;
        for (byte b4 : bArr) {
            if (i4 == 8) {
                i4 = 1;
            } else if (i5 != bArr.length) {
                bArr4[i3] = (byte) (((bArr[i5] & bArr2[i4 - 1]) << (8 - i4)) | bArr3[i3]);
                i4++;
                i3++;
            } else {
                bArr4[i3] = bArr3[i3];
            }
            i5++;
        }
        return bArr4;
    }
}
