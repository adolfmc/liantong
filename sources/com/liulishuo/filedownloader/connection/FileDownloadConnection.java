package com.liulishuo.filedownloader.connection;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface FileDownloadConnection {
    public static final int NO_RESPONSE_CODE = 0;
    public static final int RESPONSE_CODE_FROM_OFFSET = 1;

    void addHeader(String str, String str2);

    boolean dispatchAddResumeOffset(String str, long j);

    void ending();

    void execute() throws IOException;

    InputStream getInputStream() throws IOException;

    Map<String, List<String>> getRequestHeaderFields();

    int getResponseCode() throws IOException;

    String getResponseHeaderField(String str);

    Map<String, List<String>> getResponseHeaderFields();

    boolean setRequestMethod(String str) throws ProtocolException;
}
