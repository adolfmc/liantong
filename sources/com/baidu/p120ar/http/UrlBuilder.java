package com.baidu.p120ar.http;

import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.UrlBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
class UrlBuilder {
    private Charset mCharset = StandardCharsets.UTF_8;
    private StringBuilder mQueryBuilder;
    private String mUrlPre;

    public void setUrlPre(String str) {
        this.mUrlPre = str;
    }

    public void setCharset(Charset charset) {
        this.mCharset = charset;
    }

    public void addQueryField(String str, String str2) {
        if (str2 == null || TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder sb = this.mQueryBuilder;
        if (sb == null) {
            this.mQueryBuilder = new StringBuilder();
        } else if (sb.length() > 0) {
            this.mQueryBuilder.append("&");
        }
        this.mQueryBuilder.append(HttpUtil.encodeUrlComponent(str, this.mCharset));
        this.mQueryBuilder.append("=");
        this.mQueryBuilder.append(HttpUtil.encodeUrlComponent(str2, this.mCharset));
    }

    public URL build() throws MalformedURLException {
        String str = this.mUrlPre;
        if (str == null) {
            str = "";
        }
        StringBuilder sb = this.mQueryBuilder;
        if (sb != null && sb.length() > 0) {
            str = str + (str.contains("?") ? "&" : "?") + this.mQueryBuilder.toString();
        }
        return new URL(str);
    }
}
