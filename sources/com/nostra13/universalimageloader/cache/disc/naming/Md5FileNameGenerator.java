package com.nostra13.universalimageloader.cache.disc.naming;

import com.nostra13.universalimageloader.utils.C6846L;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Md5FileNameGenerator implements FileNameGenerator {
    private static final String HASH_ALGORITHM = "MD5";
    private static final int RADIX = 36;

    @Override // com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator
    public String generate(String str) {
        return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
    }

    private byte[] getMD5(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            C6846L.m8368e(e);
            return null;
        }
    }
}
