package com.baidu.p120ar.ihttp;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.IHttpResponse */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IHttpResponse {
    int getCode();

    String getContent() throws IOException;

    int getContentLength();

    String getHeader(String str);

    String getMessage();

    InputStream getStream() throws IOException;

    boolean isSuccess();
}
