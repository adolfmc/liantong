package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectionEvent extends EventMessage<String> {
    private List<RecentMiniEntity> recentMiniEntities;

    public CollectionEvent(int i) {
        super(i);
    }

    public List<RecentMiniEntity> getRecentMiniEntities() {
        return this.recentMiniEntities;
    }

    public void setRecentMiniEntities(List<RecentMiniEntity> list) {
        this.recentMiniEntities = list;
    }

    public List<RecentMiniEntity> getMiniEntity() {
        return this.recentMiniEntities;
    }
}
