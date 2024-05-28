package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectEntity {
    private List<RecentMiniEntity> appList;
    private String collectCount;
    private String collectName;
    private String collectPageCount;
    private boolean isSuccess = false;

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(boolean z) {
        this.isSuccess = z;
    }

    public List<RecentMiniEntity> getAppList() {
        if (this.appList == null) {
            this.appList = new ArrayList();
        }
        return this.appList;
    }

    public void setAppList(List<RecentMiniEntity> list) {
        this.appList = list;
    }

    public String getCollectPageCount() {
        return this.collectPageCount;
    }

    public void setCollectPageCount(String str) {
        this.collectPageCount = str;
    }

    public String getCollectName() {
        return this.collectName;
    }

    public void setCollectName(String str) {
        this.collectName = str;
    }

    public String getCollectCount() {
        return this.collectCount;
    }

    public void setCollectCount(String str) {
        this.collectCount = str;
    }
}
