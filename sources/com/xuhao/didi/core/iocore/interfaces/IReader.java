package com.xuhao.didi.core.iocore.interfaces;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IReader<T extends IIOCoreOptions> {
    void close();

    void initialize(InputStream inputStream, IStateSender iStateSender);

    void read() throws RuntimeException;

    void setOption(T t);
}
