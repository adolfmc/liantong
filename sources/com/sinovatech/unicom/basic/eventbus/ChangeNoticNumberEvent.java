package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ChangeNoticNumberEvent extends EventMessage<String> {
    public int liuyanNum;
    public int messageCount;

    public ChangeNoticNumberEvent(int i, int i2, int i3) {
        super(i);
        this.messageCount = 0;
        this.liuyanNum = 0;
        this.messageCount = i2;
        this.liuyanNum = i3;
    }

    public int getNumber() {
        return this.messageCount;
    }

    public int getLiuyanNum() {
        return this.liuyanNum;
    }
}
