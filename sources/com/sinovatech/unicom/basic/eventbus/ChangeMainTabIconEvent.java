package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ChangeMainTabIconEvent extends EventMessage<String> {
    public static int TAB_HOME = 1001;
    public String iconUrl;
    private int tab_code;
    public String textPictureColor;

    public ChangeMainTabIconEvent(int i) {
        super(i);
        this.iconUrl = "";
        this.textPictureColor = "";
        this.tab_code = 1001;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String str) {
        this.iconUrl = str;
    }

    public String getTextPictureColor() {
        return this.textPictureColor;
    }

    public void setTextPictureColor(String str) {
        this.textPictureColor = str;
    }

    public int getTab_code() {
        return this.tab_code;
    }

    public void setTab_code(int i) {
        this.tab_code = i;
    }
}
