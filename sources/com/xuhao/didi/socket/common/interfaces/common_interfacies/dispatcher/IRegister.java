package com.xuhao.didi.socket.common.interfaces.common_interfacies.dispatcher;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IRegister<T, E> {
    E registerReceiver(T t);

    E unRegisterReceiver(T t);
}
