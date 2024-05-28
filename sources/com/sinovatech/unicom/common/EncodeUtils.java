package com.sinovatech.unicom.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import org.apache.commons.codec.binary.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class EncodeUtils {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    public static String hexEncode(byte[] bArr) {
        return new String(Hex.encodeHex(bArr));
    }

    public static byte[] hexDecode(String str) {
        try {
            return Hex.decodeHex(str.toCharArray());
        } catch (Exception e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    public static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }

    public static String urlDecode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Unsupported Encoding Exception", e);
        }
    }
}
