package com.xuhao.didi.core.iocore.interfaces;

import com.xuhao.didi.core.protocol.IReaderProtocol;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IIOCoreOptions {
    int getMaxReadDataMB();

    ByteOrder getReadByteOrder();

    int getReadPackageBytes();

    IReaderProtocol getReaderProtocol();

    ByteOrder getWriteByteOrder();

    int getWritePackageBytes();

    boolean isDebug();
}
