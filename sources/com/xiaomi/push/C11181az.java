package com.xiaomi.push;

import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.az */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11181az {
    /* renamed from: a */
    public static String m4790a(String str) {
        try {
            return String.valueOf(C11180ay.m4795a(MessageDigest.getInstance("SHA1").digest(str.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            AbstractC11049b.m5279a("CloudCoder.hash4SHA1 ", e);
            throw new IllegalStateException("failed to SHA1");
        } catch (NoSuchAlgorithmException e2) {
            AbstractC11049b.m5279a("CloudCoder.hash4SHA1 ", e2);
            throw new IllegalStateException("failed to SHA1");
        } catch (Exception e3) {
            AbstractC11049b.m5279a("CloudCoder.hash4SHA1 ", e3);
            throw new IllegalStateException("failed to SHA1");
        }
    }

    /* renamed from: a */
    public static String m4789a(String str, String str2, Map<String, String> map, String str3) {
        if (TextUtils.isEmpty(str3)) {
            throw new InvalidParameterException("security is not nullable");
        }
        ArrayList<String> arrayList = new ArrayList();
        if (str != null) {
            arrayList.add(str.toUpperCase());
        }
        if (str2 != null) {
            arrayList.add(Uri.parse(str2).getEncodedPath());
        }
        boolean z = true;
        if (map != null && !map.isEmpty()) {
            for (Map.Entry entry : new TreeMap(map).entrySet()) {
                arrayList.add(String.format("%s=%s", entry.getKey(), entry.getValue()));
            }
        }
        arrayList.add(str3);
        StringBuilder sb = new StringBuilder();
        for (String str4 : arrayList) {
            if (!z) {
                sb.append('&');
            }
            sb.append(str4);
            z = false;
        }
        return m4790a(sb.toString());
    }
}
