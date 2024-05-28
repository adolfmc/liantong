package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import com.xuhao.didi.core.iocore.interfaces.IReader;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbsReader implements IReader<IIOCoreOptions> {
    protected InputStream mInputStream;
    protected volatile IIOCoreOptions mOkOptions;
    protected IStateSender mStateSender;

    @Override // com.xuhao.didi.core.iocore.interfaces.IReader
    public void initialize(InputStream inputStream, IStateSender iStateSender) {
        this.mStateSender = iStateSender;
        this.mInputStream = inputStream;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IReader
    public void setOption(IIOCoreOptions iIOCoreOptions) {
        this.mOkOptions = iIOCoreOptions;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IReader
    public void close() {
        InputStream inputStream = this.mInputStream;
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
