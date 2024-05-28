package com.xuhao.didi.socket.common.interfaces.common_interfacies.server;

import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.client.IDisConnectable;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.client.ISender;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IClient extends IDisConnectable, ISender<IClient>, Serializable {
    void addIOCallback(IClientIOCallback iClientIOCallback);

    String getHostIp();

    String getHostName();

    String getUniqueTag();

    void removeAllIOCallback();

    void removeIOCallback(IClientIOCallback iClientIOCallback);

    void setReaderProtocol(IReaderProtocol iReaderProtocol);
}
