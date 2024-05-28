package com.huawei.hms.common.internal;

import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.StringUtil;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TransactionIdCreater {
    /* renamed from: a */
    private static SecureRandom m15100a() {
        try {
            return SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception unused) {
            HMSLog.m14112e("TransactionIdCreater", "SecureRandom getInstance happpened NoSuchAlgorithmException!");
            return new SecureRandom();
        }
    }

    public static String getId(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.addByteForNum(str, 9, '0'));
        sb.append(StringUtil.addByteForNum(str2, 6, '0'));
        Locale locale = Locale.ENGLISH;
        sb.append(new SimpleDateFormat("yyyyMMddHHmmssSSS", locale).format(new Date()));
        sb.append(String.format(locale, "%06d", Integer.valueOf(m15100a().nextInt(1000000))));
        return sb.toString();
    }
}
