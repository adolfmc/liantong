package org.apache.http.conn;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public interface ConnectionReleaseTrigger {
    void abortConnection() throws IOException;

    void releaseConnection() throws IOException;
}
