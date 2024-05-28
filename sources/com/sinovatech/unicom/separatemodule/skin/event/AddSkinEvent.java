package com.sinovatech.unicom.separatemodule.skin.event;

import com.sinovatech.unicom.common.EventMessage;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AddSkinEvent extends EventMessage<String> {
    private BackgroundSkinBean skinBean;

    public AddSkinEvent(int i) {
        super(i);
    }

    public BackgroundSkinBean getSkinBean() {
        return this.skinBean;
    }

    public void setSkinBean(BackgroundSkinBean backgroundSkinBean) {
        this.skinBean = backgroundSkinBean;
    }
}
