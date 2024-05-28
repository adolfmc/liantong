package com.huawei.agconnect;

import java.util.Arrays;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class AGCRoutePolicy {
    private final int route;
    public static final AGCRoutePolicy UNKNOWN = new AGCRoutePolicy(0);
    public static final AGCRoutePolicy CHINA = new AGCRoutePolicy(1);
    public static final AGCRoutePolicy GERMANY = new AGCRoutePolicy(2);
    public static final AGCRoutePolicy RUSSIA = new AGCRoutePolicy(3);
    public static final AGCRoutePolicy SINGAPORE = new AGCRoutePolicy(4);

    private AGCRoutePolicy(int i) {
        this.route = i;
    }

    private int hash(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.route == ((AGCRoutePolicy) obj).route;
    }

    public String getRouteName() {
        switch (this.route) {
            case 1:
                return "CN";
            case 2:
                return "DE";
            case 3:
                return "RU";
            case 4:
                return "SG";
            default:
                return "UNKNOWN";
        }
    }

    public int hashCode() {
        return hash(Integer.valueOf(this.route));
    }
}
