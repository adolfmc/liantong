package com.huawei.hms.hatool;

import android.util.Pair;
import com.huawei.secure.android.common.encrypt.aes.AesCbc;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.n */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5008n {

    /* renamed from: a */
    public static final Charset f11469a = Charset.forName("UTF-8");

    /* renamed from: a */
    public static Pair<byte[], String> m14593a(String str) {
        if (str == null || str.length() < 32) {
            return new Pair<>(new byte[0], str);
        }
        String substring = str.substring(0, 32);
        return new Pair<>(HexUtil.hexStr2ByteArray(substring), str.substring(32));
    }

    /* renamed from: a */
    public static String m14592a(String str, String str2) {
        Pair<byte[], String> m14593a = m14593a(str);
        return new String(AesCbc.decrypt(HexUtil.hexStr2ByteArray((String) m14593a.second), HexUtil.hexStr2ByteArray(str2), (byte[]) m14593a.first), f11469a);
    }

    /* renamed from: a */
    public static String m14591a(byte[] bArr, String str) {
        if (bArr == null || bArr.length == 0 || str == null) {
            C5029v.m14458b("AesCipher", "cbc encrypt(byte) param is not right");
            return "";
        }
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        if (hexStr2ByteArray.length < 16) {
            C5029v.m14458b("AesCipher", "key length is not right");
            return "";
        }
        return HexUtil.byteArray2HexStr(AesCbc.encrypt(bArr, hexStr2ByteArray));
    }

    /* renamed from: b */
    public static String m14590b(String str, String str2) {
        return HexUtil.byteArray2HexStr(AesCbc.encrypt(str.getBytes(f11469a), HexUtil.hexStr2ByteArray(str2)));
    }
}
