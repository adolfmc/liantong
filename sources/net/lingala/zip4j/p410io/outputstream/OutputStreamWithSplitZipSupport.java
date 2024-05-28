package net.lingala.zip4j.p410io.outputstream;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: net.lingala.zip4j.io.outputstream.OutputStreamWithSplitZipSupport */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface OutputStreamWithSplitZipSupport {
    int getCurrentSplitFileCounter();

    long getFilePointer() throws IOException;
}
