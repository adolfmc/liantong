package com.sinovatech.unicom.separatemodule.notice.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AesUtil {

    /* renamed from: iv */
    private static final String f18591iv = "00e5d201c2c2acbff8154861242ba0c4";
    private static final String key = "6206c34e2186e752c74e6df32ab8fa5b";

    public static String aesEncrypt(String str) {
        try {
            return EncodeUtils.hexEncode(Cryptos.aesEncrypt(str.getBytes(), EncodeUtils.hexDecode("6206c34e2186e752c74e6df32ab8fa5b"), EncodeUtils.hexDecode("00e5d201c2c2acbff8154861242ba0c4")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
