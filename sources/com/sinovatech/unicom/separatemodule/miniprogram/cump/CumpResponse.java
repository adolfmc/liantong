package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.support.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpResponse {
    private JSONObject body;
    private CumpEntity cumpEntity;
    private boolean requestUpdateCache = false;
    private String respCode = "";
    private String respMsg = "";
    private String seq = "";

    @NonNull
    public String toString() {
        if (this.body != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("respCode=");
            sb.append(this.respCode);
            sb.append(" respMsg=");
            sb.append(this.respMsg);
            sb.append(" seq=");
            sb.append(this.seq);
            sb.append(" body=");
            JSONObject jSONObject = this.body;
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            return sb.toString();
        }
        return "respCode=" + this.respCode + " respMsg=" + this.respMsg + " seq=" + this.seq + " body=null";
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getRespMsg() {
        return this.respMsg;
    }

    public void setRespMsg(String str) {
        this.respMsg = str;
    }

    public String getSeq() {
        return this.seq;
    }

    public void setSeq(String str) {
        this.seq = str;
    }

    public JSONObject getBody() {
        return this.body;
    }

    public void setBody(JSONObject jSONObject) {
        this.body = jSONObject;
    }

    public boolean isRequestUpdateCache() {
        return this.requestUpdateCache;
    }

    public void setRequestUpdateCache(boolean z) {
        this.requestUpdateCache = z;
    }

    public CumpEntity getCumpEntity() {
        return this.cumpEntity;
    }

    public void setCumpEntity(CumpEntity cumpEntity) {
        this.cumpEntity = cumpEntity;
    }
}
