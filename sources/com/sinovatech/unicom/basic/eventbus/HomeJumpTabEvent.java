package com.sinovatech.unicom.basic.eventbus;

import com.sinovatech.unicom.common.EventMessage;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HomeJumpTabEvent extends EventMessage {
    private JSONObject jsonObject;
    private String navCode;
    private String url;

    public HomeJumpTabEvent(int i) {
        super(i);
    }

    public String getNavCode() {
        return this.navCode;
    }

    public void setNavCode(String str) {
        this.navCode = str;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }

    public void setJsonObject(JSONObject jSONObject) {
        this.jsonObject = jSONObject;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
