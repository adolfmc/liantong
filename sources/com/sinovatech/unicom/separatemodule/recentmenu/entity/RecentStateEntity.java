package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RecentStateEntity {
    private String body;
    private String respCode;
    private String respMsg;
    private String seq;

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

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public boolean isSuccess() {
        return "0000".equals(this.respCode);
    }

    public static RecentStateEntity getInstance(String str) {
        JSONObject optJSONObject;
        JSONObject optJSONObject2;
        RecentStateEntity recentStateEntity = new RecentStateEntity();
        try {
            JSONObject optJSONObject3 = new JSONObject(str).optJSONObject("response");
            if (optJSONObject3 != null && (optJSONObject = optJSONObject3.optJSONObject("head")) != null) {
                recentStateEntity.setRespCode(optJSONObject.optString("respCode"));
                recentStateEntity.setRespMsg(optJSONObject.optString("respMsg"));
                recentStateEntity.setSeq(optJSONObject.optString("seq"));
                if ("0000".equals(recentStateEntity.getRespCode()) && (optJSONObject2 = optJSONObject3.optJSONObject("body")) != null) {
                    recentStateEntity.setBody(!(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recentStateEntity;
    }
}
