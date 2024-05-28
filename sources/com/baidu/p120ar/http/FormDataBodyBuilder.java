package com.baidu.p120ar.http;

import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.FormDataBodyBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
class FormDataBodyBuilder implements IBodyBuilder {
    private Charset mCharset;
    private StringBuilder mDataBuilder = new StringBuilder();

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public String getContentType() {
        return "application/x-www-form-urlencoded";
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public int getSize() {
        return 0;
    }

    public void appendField(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            return;
        }
        if (this.mDataBuilder.length() > 0) {
            this.mDataBuilder.append("&");
        }
        StringBuilder sb = this.mDataBuilder;
        sb.append(HttpUtil.encodeUrlComponent(str, this.mCharset));
        sb.append("=");
        sb.append(HttpUtil.encodeUrlComponent(str2, this.mCharset));
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public void setCharset(Charset charset) {
        this.mCharset = charset;
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public InputStream[] build() {
        return this.mDataBuilder.length() > 0 ? new InputStream[]{new ByteArrayInputStream(this.mDataBuilder.toString().getBytes(this.mCharset))} : new InputStream[0];
    }
}
