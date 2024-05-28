package com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class SectionEntity<T> implements Serializable {
    public String header;
    public boolean isHeader;

    /* renamed from: t */
    public T f18483t;

    public SectionEntity(boolean z, String str) {
        this.isHeader = z;
        this.header = str;
        this.f18483t = null;
    }

    public SectionEntity(T t) {
        this.isHeader = false;
        this.header = null;
        this.f18483t = t;
    }
}
