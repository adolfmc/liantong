package com.baidu.mapapi.search.route;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndoorRoutePlanOption {
    public IndoorPlanNode mFrom = null;
    public IndoorPlanNode mTo = null;

    public IndoorRoutePlanOption from(IndoorPlanNode indoorPlanNode) {
        this.mFrom = indoorPlanNode;
        return this;
    }

    /* renamed from: to */
    public IndoorRoutePlanOption m18691to(IndoorPlanNode indoorPlanNode) {
        this.mTo = indoorPlanNode;
        return this;
    }
}
