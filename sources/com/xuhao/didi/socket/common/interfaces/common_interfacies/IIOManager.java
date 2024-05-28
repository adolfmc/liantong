package com.xuhao.didi.socket.common.interfaces.common_interfacies;

import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import com.xuhao.didi.core.iocore.interfaces.ISendable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IIOManager<E extends IIOCoreOptions> {
    void close();

    void close(Exception exc);

    void send(ISendable iSendable);

    void setOkOptions(E e);

    void startEngine();
}
