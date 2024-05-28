package com.baidu.mapapi.search.route;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class TransitRoutePlanOption {
    public PlanNode mFrom = null;
    public PlanNode mTo = null;
    public String mCityName = null;
    public TransitPolicy mPolicy = TransitPolicy.EBUS_TIME_FIRST;

    public TransitRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    /* renamed from: to */
    public TransitRoutePlanOption m18688to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    public TransitRoutePlanOption city(String str) {
        this.mCityName = str;
        return this;
    }

    public TransitRoutePlanOption policy(TransitPolicy transitPolicy) {
        this.mPolicy = transitPolicy;
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TransitPolicy {
        EBUS_TIME_FIRST(0),
        EBUS_TRANSFER_FIRST(2),
        EBUS_WALK_FIRST(3),
        EBUS_NO_SUBWAY(4);
        

        /* renamed from: a */
        private int f6894a;

        TransitPolicy(int i) {
            this.f6894a = 0;
            this.f6894a = i;
        }

        public int getInt() {
            return this.f6894a;
        }
    }
}
