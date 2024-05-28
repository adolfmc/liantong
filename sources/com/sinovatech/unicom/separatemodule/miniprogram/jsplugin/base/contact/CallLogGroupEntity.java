package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.contact;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CallLogGroupEntity {
    private int GroupId;
    private String GroupName;
    private ArrayList<CallLogEntity> arrayList;

    public String getGroupName() {
        return this.GroupName;
    }

    public void setGroupName(String str) {
        this.GroupName = str;
    }

    public int getGroupId() {
        return this.GroupId;
    }

    public void setGroupId(int i) {
        this.GroupId = i;
    }

    public ArrayList<CallLogEntity> getArrayList() {
        return this.arrayList;
    }

    public void setArrayList(ArrayList<CallLogEntity> arrayList) {
        this.arrayList = arrayList;
    }
}
