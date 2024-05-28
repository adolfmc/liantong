package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceLiveInfoEvent extends EventMessage {
    public AudienceLiveInfoEvent(int i, LiveMsg liveMsg) {
        super(i, liveMsg);
    }
}
