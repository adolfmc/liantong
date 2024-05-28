package com.xuhao.didi.socket.common.interfaces.common_interfacies.server;

import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.pojo.OriginalData;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IClientIOCallback {
    void onClientRead(OriginalData originalData, IClient iClient, IClientPool<IClient, String> iClientPool);

    void onClientWrite(ISendable iSendable, IClient iClient, IClientPool<IClient, String> iClientPool);
}
