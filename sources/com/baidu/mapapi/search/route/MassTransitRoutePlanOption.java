package com.baidu.mapapi.search.route;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MassTransitRoutePlanOption {
    public PlanNode mFrom = null;
    public PlanNode mTo = null;
    public String mCoordType = "bd09ll";
    public TacticsIncity mTacticsIncity = TacticsIncity.ETRANS_SUGGEST;
    public TacticsIntercity mTacticsIntercity = TacticsIntercity.ETRANS_LEAST_TIME;
    public TransTypeIntercity mTransTypeIntercity = TransTypeIntercity.ETRANS_TRAIN_FIRST;
    public int mPageSize = 10;
    public int mPageIndex = 1;

    public MassTransitRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    /* renamed from: to */
    public MassTransitRoutePlanOption m18689to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    @Deprecated
    public MassTransitRoutePlanOption coordType(String str) {
        this.mCoordType = str;
        return this;
    }

    public MassTransitRoutePlanOption tacticsIncity(TacticsIncity tacticsIncity) {
        this.mTacticsIncity = tacticsIncity;
        return this;
    }

    public MassTransitRoutePlanOption tacticsIntercity(TacticsIntercity tacticsIntercity) {
        this.mTacticsIntercity = tacticsIntercity;
        return this;
    }

    public MassTransitRoutePlanOption transtypeintercity(TransTypeIntercity transTypeIntercity) {
        this.mTransTypeIntercity = transTypeIntercity;
        return this;
    }

    public MassTransitRoutePlanOption pageSize(int i) {
        if (i >= 1 && i <= 10) {
            this.mPageSize = i;
        }
        return this;
    }

    public MassTransitRoutePlanOption pageIndex(int i) {
        if (i >= 0 && i <= 2147483646) {
            this.mPageIndex = i + 1;
        }
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TacticsIncity {
        ETRANS_SUGGEST(0),
        ETRANS_LEAST_TRANSFER(1),
        ETRANS_LEAST_WALK(2),
        ETRANS_NO_SUBWAY(3),
        ETRANS_LEAST_TIME(4),
        ETRANS_SUBWAY_FIRST(5);
        

        /* renamed from: a */
        private int f6864a;

        TacticsIncity(int i) {
            this.f6864a = 0;
            this.f6864a = i;
        }

        public int getInt() {
            return this.f6864a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TacticsIntercity {
        ETRANS_LEAST_TIME(0),
        ETRANS_START_EARLY(1),
        ETRANS_LEAST_PRICE(2);
        

        /* renamed from: a */
        private int f6866a;

        TacticsIntercity(int i) {
            this.f6866a = 0;
            this.f6866a = i;
        }

        public int getInt() {
            return this.f6866a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum TransTypeIntercity {
        ETRANS_TRAIN_FIRST(0),
        ETRANS_PLANE_FIRST(1),
        ETRANS_COACH_FIRST(2);
        

        /* renamed from: a */
        private int f6868a;

        TransTypeIntercity(int i) {
            this.f6868a = 0;
            this.f6868a = i;
        }

        public int getInt() {
            return this.f6868a;
        }
    }
}
