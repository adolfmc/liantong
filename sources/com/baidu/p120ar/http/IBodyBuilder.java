package com.baidu.p120ar.http;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.http.IBodyBuilder */
/* loaded from: E:\10201592_dexfile_execute.dex */
interface IBodyBuilder {
    InputStream[] build() throws FileNotFoundException;

    String getContentType();

    int getSize();

    void setCharset(Charset charset);
}
