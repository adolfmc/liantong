package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ServiceMenuEvent extends EventMessage<String> {
    private MenuEntity menuEntity;

    public MenuEntity getMenuEntity() {
        return this.menuEntity;
    }

    public void setMenuEntity(MenuEntity menuEntity) {
        this.menuEntity = menuEntity;
    }

    public ServiceMenuEvent(int i) {
        super(i);
    }
}
