package com.baidu.mapapi.navi;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WayPoint {

    /* renamed from: a */
    private List<WayPointInfo> f6632a;

    public WayPoint(List<WayPointInfo> list) {
        if (list == null) {
            return;
        }
        this.f6632a = list;
    }

    public List<WayPointInfo> getViaPoints() {
        return this.f6632a;
    }
}
