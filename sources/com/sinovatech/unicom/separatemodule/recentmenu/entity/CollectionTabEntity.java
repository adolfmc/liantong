package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectionTabEntity {
    private String categoryId;
    private String categoryName;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18599id;
    private boolean isSelect;
    private String sort;

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String str) {
        this.categoryName = str;
    }

    public String getSort() {
        return this.sort;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public long getId() {
        return this.f18599id;
    }

    public void setId(long j) {
        this.f18599id = j;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }
}
