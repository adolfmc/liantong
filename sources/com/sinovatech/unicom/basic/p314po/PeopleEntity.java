package com.sinovatech.unicom.basic.p314po;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.PeopleEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PeopleEntity {
    List<String> list;
    String name;
    String pingyin;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<String> getList() {
        if (this.list == null) {
            this.list = new ArrayList();
        }
        return this.list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getPingyin() {
        return this.pingyin;
    }

    public void setPingyin(String str) {
        this.pingyin = str;
    }

    public String toString() {
        return "PeopleEntity{name='" + this.name + "', pingyin='" + this.pingyin + "', list=" + this.list + '}';
    }
}
