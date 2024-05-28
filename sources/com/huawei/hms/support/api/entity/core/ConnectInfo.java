package com.huawei.hms.support.api.entity.core;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.entity.auth.Scope;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ConnectInfo implements IMessageEntity {
    @Packed

    /* renamed from: a */
    private List<String> f11721a;
    @Packed

    /* renamed from: b */
    private List<Scope> f11722b;
    @Packed

    /* renamed from: c */
    private String f11723c;
    @Packed

    /* renamed from: d */
    private String f11724d;

    public ConnectInfo() {
    }

    public List<String> getApiNameList() {
        return this.f11721a;
    }

    public String getFingerprint() {
        return this.f11723c;
    }

    public List<Scope> getScopeList() {
        return this.f11722b;
    }

    public String getSubAppID() {
        return this.f11724d;
    }

    public void setApiNameList(List<String> list) {
        this.f11721a = list;
    }

    public void setFingerprint(String str) {
        this.f11723c = str;
    }

    public void setScopeList(List<Scope> list) {
        this.f11722b = list;
    }

    public void setSubAppID(String str) {
        this.f11724d = str;
    }

    public ConnectInfo(List<String> list, List<Scope> list2, String str, String str2) {
        this.f11721a = list;
        this.f11722b = list2;
        this.f11723c = str;
        this.f11724d = str2;
    }
}
