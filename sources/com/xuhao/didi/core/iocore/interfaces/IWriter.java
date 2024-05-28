package com.xuhao.didi.core.iocore.interfaces;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IWriter<T extends IIOCoreOptions> {
    void close();

    void initialize(OutputStream outputStream, IStateSender iStateSender);

    void offer(ISendable iSendable);

    void setOption(T t);

    boolean write() throws RuntimeException;
}
