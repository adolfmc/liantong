package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareOnClickEventBus extends EventMessage<String> {
    private String shareName;

    public ShareOnClickEventBus(int i) {
        super(i);
    }

    public String getShareName() {
        return this.shareName;
    }

    public void setShareName(String str) {
        this.shareName = str;
    }
}
