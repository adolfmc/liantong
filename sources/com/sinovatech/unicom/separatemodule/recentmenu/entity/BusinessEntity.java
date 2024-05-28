package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BusinessEntity {
    private List<RecentMiniEntity> appList;
    private String businessName;

    public List<RecentMiniEntity> getAppList() {
        List<RecentMiniEntity> list = this.appList;
        return list == null ? new ArrayList() : list;
    }

    public void setAppList(List<RecentMiniEntity> list) {
        this.appList = list;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String str) {
        this.businessName = str;
    }
}
