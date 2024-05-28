package com.sinovatech.unicom.separatemodule.notice.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EncodeUtils {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    public static byte[] hexDecode(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    public static String hexEncode(byte[] bArr) {
        return new String(Hex.encodeHex(bArr));
    }
}
