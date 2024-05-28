package org.apache.http.p453io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: org.apache.http.io.HttpMessageWriter */
/* loaded from: E:\452516_dexfile_execute.dex */
public interface HttpMessageWriter {
    void write(HttpMessage httpMessage) throws IOException, HttpException;
}
