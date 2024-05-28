package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShareEvent2 extends EventMessage<String> {
    private String shareContent;
    private String shareList;

    public ShareEvent2(int i, String str) {
        super(i, str);
    }

    public String getShareList() {
        return this.shareList;
    }

    public void setShareList(String str) {
        this.shareList = str;
    }

    public String getShareContent() {
        return this.shareContent;
    }

    public void setShareContent(String str) {
        this.shareContent = str;
    }
}
