package com.froad.libreadcard.bankcard;

import android.nfc.tech.IsoDep;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Locale;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PBOCUtil {
    private static final String TAG = "PBOCUtil";
    private IsoDep mIsoDep;
    private HashMap<String, byte[]> mTlvMap = new HashMap<>();

    public PBOCUtil(IsoDep isoDep) {
        this.mIsoDep = isoDep;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String bytesToString(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            str = str + String.format("%02X", Byte.valueOf(b));
        }
        return str;
    }

    private int pbocCmdGPO(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
        allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
        byte[] pbocSendApdu = pbocSendApdu(allocate.array());
        if (pbocSendApdu == null) {
            return -1;
        }
        return pbocParseTlv(pbocSendApdu);
    }

    private int pbocCmdGenAC(byte[] bArr, byte b) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
        allocate.put(Byte.MIN_VALUE).put((byte) -82).put(b).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
        byte[] pbocSendApdu = pbocSendApdu(allocate.array());
        if (pbocSendApdu == null) {
            return -1;
        }
        return pbocParseTlv(pbocSendApdu);
    }

    private byte[] pbocCmdGetData(byte[] bArr, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 3);
        allocate.put(Byte.MIN_VALUE).put((byte) -54).put(bArr).put((byte) 0);
        byte[] pbocSendApdu = pbocSendApdu(allocate.array());
        if (pbocSendApdu == null) {
            return null;
        }
        if (!z || pbocParseTlv(pbocSendApdu) >= 0) {
            return pbocSendApdu;
        }
        return null;
    }

    private int pbocCmdSelect(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
        allocate.put((byte) 0).put((byte) -92).put((byte) 4).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
        byte[] pbocSendApdu = pbocSendApdu(allocate.array());
        if (pbocSendApdu == null) {
            return -1;
        }
        return pbocParseTlv(pbocSendApdu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] pbocGenDol(byte[] bArr) {
        int i;
        int length = bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(4096);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = (bArr[i2] & 31) == 31 ? 2 : 1;
            int i5 = i2 + i4;
            if (i5 >= length) {
                String.format(Locale.CHINA, "off[%d], dol_len[%d] too small", Integer.valueOf(i2), Integer.valueOf(length));
                return null;
            }
            byte[] bArr2 = new byte[i4];
            System.arraycopy(bArr, i2, bArr2, 0, i4);
            bytesToString(bArr2);
            int i6 = (bArr[i5] & 128) != 0 ? (bArr[i5] & 127) + 1 : 1;
            int i7 = i5 + i6;
            if (i7 > length && (i7 != length || bArr[i5] != 0)) {
                String.format(Locale.CHINA, "off[%d], dol_len[%d] too small", Integer.valueOf(i5), Integer.valueOf(length));
                return null;
            }
            byte[] bArr3 = new byte[i6];
            System.arraycopy(bArr, i5, bArr3, 0, i6);
            bytesToString(bArr3);
            if (i6 == 1) {
                i = bArr3[0] & 255;
            } else {
                int i8 = 0;
                for (int i9 = 1; i9 < i6; i9++) {
                    i8 = (i8 << 8) + (bArr3[i9] & 255);
                }
                i = i8;
            }
            byte[] bArr4 = this.mTlvMap.get(bytesToString(bArr2));
            if (bArr4 == null) {
                String str = "get Tag[" + bytesToString(bArr2) + "] failed";
                return null;
            } else if (bArr4.length != i) {
                String.format(Locale.CHINA, "invalid val, val.length[%08x] val_len[%08x]", Integer.valueOf(bArr4.length), Integer.valueOf(i));
                return null;
            } else {
                allocate.put(bArr4);
                i3 += bArr4.length;
                i2 = i7;
            }
        }
        byte[] bArr5 = new byte[i3];
        allocate.flip();
        allocate.get(bArr5);
        return bArr5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int pbocParseTlv(byte[] bArr) {
        int i;
        int length = bArr.length - 2;
        if (length < 3) {
            String str = "data length too small: " + length;
            return -1;
        }
        int i2 = 0;
        while (i2 < length) {
            int i3 = (bArr[i2] & 31) == 31 ? 2 : 1;
            int i4 = i2 + i3;
            if (i4 >= length) {
                String.format(Locale.CHINA, "off[%d], data_len[%d] too small", Integer.valueOf(i2), Integer.valueOf(length));
                return -1;
            }
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i2, bArr2, 0, i3);
            bytesToString(bArr2);
            int i5 = (bArr[i4] & 128) != 0 ? (bArr[i4] & 127) + 1 : 1;
            int i6 = i4 + i5;
            if (i6 >= length && (i6 != length || bArr[i4] != 0)) {
                String.format(Locale.CHINA, "off[%d], data_len[%d] too small", Integer.valueOf(i4), Integer.valueOf(length));
                return -1;
            }
            byte[] bArr3 = new byte[i5];
            System.arraycopy(bArr, i4, bArr3, 0, i5);
            bytesToString(bArr3);
            if (i5 == 1) {
                i = bArr3[0] & 255;
            } else {
                int i7 = 0;
                for (int i8 = 1; i8 < i5; i8++) {
                    i7 = (i7 << 8) + (bArr3[i8] & 255);
                }
                i = i7;
            }
            int i9 = i6 + i;
            if (i9 > length) {
                String.format(Locale.CHINA, "off[%d], data_len[%d] too small", Integer.valueOf(i6), Integer.valueOf(length));
                return -1;
            }
            byte[] bArr4 = new byte[i];
            System.arraycopy(bArr, i6, bArr4, 0, i);
            bytesToString(bArr4);
            i2 = (bArr2[0] & 32) != 32 ? i9 : i6;
            this.mTlvMap.put(bytesToString(bArr2), bArr4);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] pbocSendApdu(byte[] bArr) {
        try {
            byte[] transceive = this.mIsoDep.transceive(bArr);
            String str = bytesToString(bArr) + "[" + bytesToString(transceive) + "]";
            if (transceive.length == 2) {
                byte b = transceive[0];
                if (b == 97) {
                    bArr = new byte[]{0, -64, 0, 0, 0};
                } else if (b != 108) {
                    return null;
                }
                bArr[4] = transceive[1];
                return this.mIsoDep.transceive(bArr);
            }
            return transceive;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] stringToBytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public String[] parseBankCardInfo(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        String[] strArr = new String[3];
        if (this.mTlvMap.containsKey("57")) {
            String bytesToString = bytesToString(this.mTlvMap.get("57"));
            if (bytesToString.contains("D")) {
                String[] split = bytesToString.split("D");
                if (split.length < 2) {
                    return null;
                }
                strArr[0] = split[0];
                String str = split[1];
                if (str.length() <= 4) {
                    strArr[1] = str;
                } else {
                    strArr[1] = str.substring(0, 4);
                }
                strArr[2] = BankInfoUtil.getInstance().getBankName(split[0]);
                return strArr;
            }
        }
        return null;
    }

    public int pbocAppInit() {
        byte[] pbocGenDol;
        byte[] bArr;
        byte[] bArr2 = this.mTlvMap.get("9F38");
        if (bArr2 == null || (pbocGenDol = pbocGenDol(bArr2)) == null) {
            return -1;
        }
        String str = "dol_test.length: " + pbocGenDol.length;
        ByteBuffer allocate = ByteBuffer.allocate(pbocGenDol.length + 2);
        allocate.put((byte) -125).put((byte) pbocGenDol.length).put(pbocGenDol);
        if (pbocCmdGPO(allocate.array()) >= 0 && (bArr = this.mTlvMap.get("80")) != null) {
            int length = bArr.length - 2;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, 2, bArr3, 0, length);
            for (int i = 0; i + 3 < length; i += 4) {
                for (int i2 = bArr3[i + 1]; i2 <= bArr3[i + 2]; i2++) {
                    if (pbocCmdReadRecord(i2, bArr3[i], true) == null) {
                        return -1;
                    }
                }
            }
            return 0;
        }
        return -1;
    }

    public int pbocAppSelect() {
        byte[] bArr;
        if (pbocCmdSelect("1PAY.SYS.DDF01".getBytes()) < 0) {
            return -1;
        }
        byte[] bArr2 = this.mTlvMap.get("88");
        return ((bArr2 == null || pbocCmdReadRecord(1, (byte) (bArr2[0] << 3), true) != null) && (bArr = this.mTlvMap.get("4F")) != null && pbocCmdSelect(bArr) >= 0) ? 0 : -1;
    }

    public int pbocAppSelectNFC() {
        byte[] bArr;
        if (pbocCmdSelect("2PAY.SYS.DDF01".getBytes()) < 0) {
            return -1;
        }
        byte[] bArr2 = this.mTlvMap.get("88");
        return ((bArr2 == null || pbocCmdReadRecord(1, (byte) (bArr2[0] << 3), true) != null) && (bArr = this.mTlvMap.get("4F")) != null && pbocCmdSelect(bArr) >= 0) ? 0 : -1;
    }

    public byte[] pbocCmdReadRecord(int i, byte b, boolean z) {
        ByteBuffer allocate = ByteBuffer.allocate(5);
        allocate.put((byte) 0).put((byte) -78).put((byte) i).put((byte) (b | 4)).put((byte) 0);
        byte[] pbocSendApdu = pbocSendApdu(allocate.array());
        if (pbocSendApdu == null) {
            return null;
        }
        if (!z || pbocParseTlv(pbocSendApdu) >= 0) {
            return pbocSendApdu;
        }
        return null;
    }

    public String[] pbocGetTransDetail() {
        int i;
        int i2;
        String str;
        HashMap hashMap = new HashMap();
        hashMap.put("9A", "交易日期");
        hashMap.put("9F21", "交易时间");
        hashMap.put("9F02", "授权金额");
        hashMap.put("9F03", "其它金额");
        hashMap.put("9F1A", "终端国家代码");
        hashMap.put("5F2A", "交易货币代码");
        hashMap.put("9F4E", "商户名称");
        hashMap.put("9C", "交易类型");
        hashMap.put("9F36", "应用交易计数器(ATC)");
        byte[] bArr = this.mTlvMap.get("9F4D");
        Object obj = null;
        if (bArr == null) {
            return null;
        }
        String str2 = "9F4D: " + bytesToString(bArr);
        int i3 = 1;
        pbocCmdGetData(stringToBytes("9F4F"), true);
        byte[] bArr2 = this.mTlvMap.get("9F4F");
        if (bArr2 == null) {
            return null;
        }
        int i4 = bArr[1];
        byte[][] bArr3 = new byte[i4];
        int i5 = 0;
        int i6 = 0;
        while (true) {
            if (i6 >= i4) {
                break;
            }
            int i7 = i6 + 1;
            bArr3[i6] = pbocCmdReadRecord(i7, (byte) (bArr[0] << 3), false);
            if (bArr3[i6] == null) {
                String str3 = "transDetail ends to " + i6;
                break;
            }
            String str4 = "trans: " + bytesToString(bArr3[i6]);
            i6 = i7;
        }
        String[] strArr = new String[i6];
        int i8 = 0;
        while (i8 < i6) {
            int length = bArr2.length;
            String str5 = "";
            int i9 = i5;
            int i10 = i9;
            while (i9 < length) {
                int i11 = (bArr2[i9] & 31) == 31 ? 2 : i3;
                int i12 = i9 + i11;
                if (i12 >= length) {
                    Locale locale = Locale.CHINA;
                    Object[] objArr = new Object[2];
                    objArr[i5] = Integer.valueOf(i9);
                    objArr[i3] = Integer.valueOf(length);
                    String.format(locale, "off[%d], dol_len[%d] too small", objArr);
                    return null;
                }
                byte[] bArr4 = new byte[i11];
                System.arraycopy(bArr2, i9, bArr4, i5, i11);
                bytesToString(bArr4);
                int i13 = (bArr2[i12] & 128) != 0 ? (bArr2[i12] & 127) + i3 : i3;
                int i14 = i12 + i13;
                if (i14 > length && (i14 != length || bArr2[i12] != 0)) {
                    Locale locale2 = Locale.CHINA;
                    Object[] objArr2 = new Object[2];
                    objArr2[i5] = Integer.valueOf(i12);
                    objArr2[i3] = Integer.valueOf(length);
                    String.format(locale2, "off[%d], dol_len[%d] too small", objArr2);
                    return null;
                }
                byte[] bArr5 = new byte[i13];
                System.arraycopy(bArr2, i12, bArr5, i5, i13);
                bytesToString(bArr5);
                if (i13 == 1) {
                    i = bArr5[i5] & 255;
                } else {
                    int i15 = i5;
                    for (int i16 = 1; i16 < i13; i16++) {
                        i15 = (i15 << 8) + (bArr5[i16] & 255);
                    }
                    i = i15;
                }
                int i17 = i10 + i;
                if (i17 > bArr3[i8].length) {
                    String.format(Locale.CHINA, "val_off[%d] + val_len[%d] > transDetailArray[%d].length[%d]", Integer.valueOf(i10), Integer.valueOf(i), Integer.valueOf(i8), Integer.valueOf(bArr3[i8].length));
                    return null;
                }
                byte[] bArr6 = new byte[i];
                byte[] bArr7 = bArr2;
                System.arraycopy(bArr3[i8], i10, bArr6, 0, i);
                if (bytesToString(bArr4).equals("9F4E")) {
                    int i18 = 0;
                    while (i18 < i && bArr6[i18] != 0) {
                        i18++;
                    }
                    try {
                        i2 = 0;
                        try {
                            str = new String(bArr6, 0, i18, "gb2312");
                        } catch (UnsupportedEncodingException e) {
                            e = e;
                            e.printStackTrace();
                            str = "";
                            str5 = str5 + ((String) hashMap.get(bytesToString(bArr4))) + "[" + str + "]|";
                            i10 = i17;
                            i9 = i14;
                            i5 = i2;
                            obj = null;
                            bArr2 = bArr7;
                            i3 = 1;
                        }
                    } catch (UnsupportedEncodingException e2) {
                        e = e2;
                        i2 = 0;
                    }
                    str5 = str5 + ((String) hashMap.get(bytesToString(bArr4))) + "[" + str + "]|";
                } else {
                    i2 = 0;
                    str5 = str5 + ((String) hashMap.get(bytesToString(bArr4))) + "[" + bytesToString(bArr6) + "]|";
                }
                i10 = i17;
                i9 = i14;
                i5 = i2;
                obj = null;
                bArr2 = bArr7;
                i3 = 1;
            }
            strArr[i8] = str5;
            i8++;
            i3 = 1;
        }
        return strArr;
    }

    public void pbocInsertTLV(String str, String str2) {
        HashMap<String, byte[]> hashMap = this.mTlvMap;
        if (hashMap != null) {
            hashMap.put(str, stringToBytes(str2));
        }
    }

    public int pbocTermActAnalyze() {
        byte[] pbocGenDol;
        byte[] bArr = this.mTlvMap.get("8C");
        return (bArr == null || (pbocGenDol = pbocGenDol(bArr)) == null || pbocCmdGenAC(pbocGenDol, Byte.MIN_VALUE) < 0) ? -1 : 0;
    }
}
