package com.liulishuo.okdownload.core.exception;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NetworkPolicyException extends IOException {
    public NetworkPolicyException() {
        super("Only allows downloading this task on the wifi network type!");
    }
}
