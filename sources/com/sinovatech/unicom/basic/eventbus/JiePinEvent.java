package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JiePinEvent extends EventMessage<String> {
    private boolean isSecure;

    public JiePinEvent(int i) {
        super(i);
    }

    public boolean isSecure() {
        return this.isSecure;
    }

    public void setSecure(boolean z) {
        this.isSecure = z;
    }
}
