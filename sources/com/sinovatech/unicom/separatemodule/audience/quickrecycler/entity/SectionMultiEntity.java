package com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class SectionMultiEntity<T> implements MultiItemEntity, Serializable {
    public String header;
    public boolean isHeader;

    /* renamed from: t */
    public T f18484t;

    public SectionMultiEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.f18484t = null;
    }

    public SectionMultiEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.f18484t = t;
    }
}
