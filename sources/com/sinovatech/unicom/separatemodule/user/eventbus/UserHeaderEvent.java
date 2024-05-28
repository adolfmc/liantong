package com.sinovatech.unicom.separatemodule.user.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserHeaderEvent extends EventMessage<String> {
    private String nickName;
    private String qiandaoUrl;

    public UserHeaderEvent(int i) {
        super(i);
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getQiandaoUrl() {
        return this.qiandaoUrl;
    }

    public void setQiandaoUrl(String str) {
        this.qiandaoUrl = str;
    }
}
