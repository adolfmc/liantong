package com.baidu.mapsdkplatform.comapi.map;

import android.os.Handler;
import com.baidu.platform.comjni.engine.MessageProxy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MessageCenter {
    public static void registMessage(int i, Handler handler) {
        MessageProxy.registerMessageHandler(i, handler);
    }

    public static void unregistMessage(int i, Handler handler) {
        MessageProxy.unRegisterMessageHandler(i, handler);
    }
}
