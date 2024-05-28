package com.sinovatech.unicom.separatemodule.miniprogram.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Md5FileNameGenerator {
    public static String generate(String str) {
        return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
    }

    private static byte[] getMD5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
