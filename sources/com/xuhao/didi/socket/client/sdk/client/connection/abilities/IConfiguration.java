package com.xuhao.didi.socket.client.sdk.client.connection.abilities;

import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IConfiguration {
    OkSocketOptions getOption();

    IConnectionManager option(OkSocketOptions okSocketOptions);
}
