package com.xuhao.didi.socket.client.sdk.client.action;

import com.xuhao.didi.core.iocore.interfaces.IOAction;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IAction extends IOAction {
    public static final String ACTION_CONNECTION_FAILED = "action_connection_failed";
    public static final String ACTION_CONNECTION_SUCCESS = "action_connection_success";
    public static final String ACTION_DATA = "action_data";
    public static final String ACTION_DISCONNECTION = "action_disconnection";
    public static final String ACTION_READ_THREAD_SHUTDOWN = "action_read_thread_shutdown";
    public static final String ACTION_READ_THREAD_START = "action_read_thread_start";
    public static final String ACTION_WRITE_THREAD_SHUTDOWN = "action_write_thread_shutdown";
    public static final String ACTION_WRITE_THREAD_START = "action_write_thread_start";
}
