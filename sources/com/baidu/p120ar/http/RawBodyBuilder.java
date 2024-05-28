package com.baidu.p120ar.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.RawBodyBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RawBodyBuilder implements IBodyBuilder {
    private byte[] mData;

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public String getContentType() {
        return null;
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public void setCharset(Charset charset) {
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public InputStream[] build() {
        byte[] bArr = this.mData;
        return (bArr == null || bArr.length <= 0) ? new InputStream[0] : new InputStream[]{new ByteArrayInputStream(bArr)};
    }

    @Override // com.baidu.p120ar.http.IBodyBuilder
    public int getSize() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }
}
