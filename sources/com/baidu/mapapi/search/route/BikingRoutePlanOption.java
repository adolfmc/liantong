package com.baidu.mapapi.search.route;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BikingRoutePlanOption {
    public PlanNode mFrom = null;
    public PlanNode mTo = null;
    public int mRidingType = 0;

    public BikingRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    /* renamed from: to */
    public BikingRoutePlanOption m18694to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    public BikingRoutePlanOption ridingType(int i) {
        this.mRidingType = i;
        return this;
    }
}
