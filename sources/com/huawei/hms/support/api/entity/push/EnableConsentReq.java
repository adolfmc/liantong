package com.huawei.hms.support.api.entity.push;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EnableConsentReq implements IMessageEntity {
    @Packed
    private boolean enable;
    @Packed
    private String packageName;

    public String getPackageName() {
        return this.packageName;
    }

    public boolean isEnable() {
        return this.enable;
    }

    public void setEnable(boolean z) {
        this.enable = z;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String toString() {
        return "ConsentReq{packageName:" + this.packageName + ", enable:" + this.enable + '}';
    }
}
