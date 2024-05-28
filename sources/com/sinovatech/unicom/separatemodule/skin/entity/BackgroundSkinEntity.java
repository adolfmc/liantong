package com.sinovatech.unicom.separatemodule.skin.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BackgroundSkinEntity {
    private String groupName;
    private String showType;
    private List<BackgroundSkinBean> skinList;

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String str) {
        this.groupName = str;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String str) {
        this.showType = str;
    }

    public List<BackgroundSkinBean> getSkinList() {
        return this.skinList;
    }

    public void setSkinList(List<BackgroundSkinBean> list) {
        this.skinList = list;
    }
}
