package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface HttpConnection extends PublicMemberKeeper {
    InputStream getErrorStream() throws IOException;

    Map<String, List<String>> getHeaderFields() throws IOException;

    InputStream getInputStream() throws IOException;

    int getResponseCode() throws IOException;
}
