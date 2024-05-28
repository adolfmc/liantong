package com.sinovatech.unicom.basic.p315ui.entity;

import com.sinovatech.unicom.basic.p314po.MenuEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.entity.MainBean */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MainBean {
    private List<MenuEntity> list;
    private String title;
    private String transId;

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    public MainBean(String str, List<MenuEntity> list) {
        this.title = str;
        this.list = list;
    }

    public List<MenuEntity> getList() {
        return this.list;
    }

    public void setList(List<MenuEntity> list) {
        this.list = list;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
