package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FragmentOnResumeEvent extends EventMessage<String> {
    public static int HOME = 1001;
    public static int HOME_TJ = 1002;
    public static int USER = 1004;
    public int type;

    public FragmentOnResumeEvent(int i) {
        super(i);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
