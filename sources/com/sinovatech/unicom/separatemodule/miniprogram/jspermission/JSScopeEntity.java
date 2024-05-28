package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JSScopeEntity {
    private String desc;
    private String hint;
    private String scope;

    public JSScopeEntity(String str, String str2, String str3) {
        this.scope = str;
        this.desc = str2;
        this.hint = str3;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String str) {
        this.scope = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String str) {
        this.hint = str;
    }

    public String toString() {
        return "JSScopeEntity{scope='" + this.scope + "', desc='" + this.desc + "', hint='" + this.hint + "'}";
    }
}
