package com.sinovatech.unicom.separatemodule.audience.entity;

import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AngleMoreEntity {
    private String cover;
    private boolean isVis;
    private List<SharpnessEntity.LiveUrlBean> list;
    private String name;
    private boolean selection;

    public boolean isVis() {
        return this.isVis;
    }

    public void setVis(boolean z) {
        this.isVis = z;
    }

    public boolean isSelection() {
        return this.selection;
    }

    public void setSelection(boolean z) {
        this.selection = z;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String str) {
        this.cover = str;
    }

    public List<SharpnessEntity.LiveUrlBean> getList() {
        return this.list;
    }

    public void setList(List<SharpnessEntity.LiveUrlBean> list) {
        this.list = list;
    }
}
