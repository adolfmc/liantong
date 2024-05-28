package com.baidu.mapapi.search.route;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WalkingRoutePlanOption {
    public PlanNode mFrom = null;
    public PlanNode mTo = null;

    public WalkingRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    /* renamed from: to */
    public WalkingRoutePlanOption m18687to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }
}
