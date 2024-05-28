package com.huawei.hms.support.api.entity.core;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DisconnectInfo implements IMessageEntity {
    @Packed
    public List<String> apiNameList;
    @Packed
    public List<Scope> scopeList;

    public DisconnectInfo() {
    }

    public List<String> getApiNameList() {
        return this.apiNameList;
    }

    public List<Scope> getScopeList() {
        return this.scopeList;
    }

    public DisconnectInfo(List<Scope> list, List<String> list2) {
        this.scopeList = list;
        this.apiNameList = list2;
    }
}
