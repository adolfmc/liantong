package com.xuhao.didi.core.protocol;

import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IReaderProtocol {
    int getBodyLength(byte[] bArr, ByteOrder byteOrder);

    int getHeaderLength();
}
