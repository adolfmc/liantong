package com.sina.weibo.sdk.net;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.net.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7105h {

    /* renamed from: q */
    private HashMap<String, String> f18326q = new HashMap<>();

    public final void put(String str, String str2) {
        this.f18326q.put(str, str2);
    }

    /* renamed from: g */
    public final String m8049g() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : this.f18326q.keySet()) {
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            String str2 = this.f18326q.get(str);
            if (!TextUtils.isEmpty(str2)) {
                try {
                    sb.append(URLEncoder.encode(str, "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(str2, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}
