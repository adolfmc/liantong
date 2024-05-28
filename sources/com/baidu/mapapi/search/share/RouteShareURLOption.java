package com.baidu.mapapi.search.share;

import com.baidu.mapapi.search.route.PlanNode;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RouteShareURLOption {
    public RouteShareMode mMode;
    public PlanNode mFrom = null;
    public PlanNode mTo = null;
    public int mPn = 0;
    public int mCityCode = -1;

    public RouteShareMode getmMode() {
        return this.mMode;
    }

    public RouteShareURLOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    /* renamed from: to */
    public RouteShareURLOption m18645to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    /* renamed from: pn */
    public RouteShareURLOption m18646pn(int i) {
        this.mPn = i;
        return this;
    }

    public RouteShareURLOption cityCode(int i) {
        this.mCityCode = i;
        return this;
    }

    public RouteShareURLOption routMode(RouteShareMode routeShareMode) {
        this.mMode = routeShareMode;
        return this;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RouteShareMode {
        CAR_ROUTE_SHARE_MODE(0),
        FOOT_ROUTE_SHARE_MODE(1),
        CYCLE_ROUTE_SHARE_MODE(2),
        BUS_ROUTE_SHARE_MODE(3);
        

        /* renamed from: a */
        private int f6909a;

        RouteShareMode(int i) {
            this.f6909a = -1;
            this.f6909a = i;
        }

        public int getRouteShareMode() {
            return this.f6909a;
        }
    }
}
