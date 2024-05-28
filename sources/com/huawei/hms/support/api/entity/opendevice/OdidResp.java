package com.huawei.hms.support.api.entity.opendevice;

import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class OdidResp extends AbstractMessageEntity {
    @Packed

    /* renamed from: id */
    private String f11726id;

    public String getId() {
        return this.f11726id;
    }

    public void setId(String str) {
        this.f11726id = str;
    }
}
