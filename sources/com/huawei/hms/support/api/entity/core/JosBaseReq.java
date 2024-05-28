package com.huawei.hms.support.api.entity.core;

import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.annotation.Packed;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JosBaseReq implements IMessageEntity {
    @Packed
    private String channelId;
    @Packed
    private String cpId;
    @Packed
    private String hmsSdkVersionName;

    private static <T> T get(T t) {
        return t;
    }

    public String getChannelId() {
        return (String) get(this.channelId);
    }

    public String getCpID() {
        return (String) get(this.cpId);
    }

    public String getHmsSdkVersionName() {
        return (String) get(this.hmsSdkVersionName);
    }

    public void setChannelId(String str) {
        this.channelId = str;
    }

    public void setCpID(String str) {
        this.cpId = str;
    }

    public void setHmsSdkVersionName(String str) {
        this.hmsSdkVersionName = str;
    }
}
