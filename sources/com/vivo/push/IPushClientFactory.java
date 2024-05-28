package com.vivo.push;

import android.content.Intent;
import com.vivo.push.p373f.OnReceiveTask;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IPushClientFactory {
    OnReceiveTask createReceiveTask(PushCommand pushCommand);

    PushCommand createReceiverCommand(Intent intent);

    PushClientTask createTask(PushCommand pushCommand);
}
