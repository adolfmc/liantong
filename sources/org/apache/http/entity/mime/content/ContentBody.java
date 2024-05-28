package org.apache.http.entity.mime.content;

import java.io.IOException;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ContentBody extends ContentDescriptor {
    String getFilename();

    void writeTo(OutputStream outputStream) throws IOException;
}
