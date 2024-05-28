package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJtBoxEntity {
    private String apiName;
    private String channelTopic;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18616id;
    private String json;

    public long getId() {
        return this.f18616id;
    }

    public void setId(long j) {
        this.f18616id = j;
    }

    public String getJson() {
        return this.json;
    }

    public void setJson(String str) {
        this.json = str;
    }

    public String getChannelTopic() {
        return this.channelTopic;
    }

    public void setChannelTopic(String str) {
        this.channelTopic = str;
    }

    public String getApiName() {
        return this.apiName;
    }

    public void setApiName(String str) {
        this.apiName = str;
    }
}
