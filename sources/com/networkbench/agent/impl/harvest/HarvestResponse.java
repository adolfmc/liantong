package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.p260a.C6450m;
import com.networkbench.agent.impl.p243c.p246c.C6264a;
import com.networkbench.com.google.gson.GsonBuilder;
import com.networkbench.com.google.gson.reflect.TypeToken;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestResponse {
    private HarvestConfiguration configuration;
    private int errorCode;
    private C6264a extensionConfig;
    private String responseBody;
    private long responseTime;
    private String resultMessage;
    private String status;
    private int statusCode;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum Code {
        OK(200),
        UNAUTHORIZED(401),
        FORBIDDEN(403),
        ENTITY_TOO_LARGE(413),
        UNSUPPORTED_MEDIA_TYPE(415),
        INVALID_AGENT_ID(450),
        INVALID_LICENSE(460),
        INVALID_DATA_TOKEN(461),
        INVALID_DATA(462),
        INVALID_DEVICE_ID(463),
        INVALID_ENCRYPT_KEY(464),
        DECODE_FAILED(465),
        EXPIRE_CONFIGURATION(470),
        INVALID_METHOD_API(480),
        DATA_LEN_OVER(481),
        INTERNAL_SERVER_ERROR(500),
        UNKNOWN(-1);
        

        /* renamed from: a */
        int f16273a;

        Code(int i) {
            this.f16273a = i;
        }

        public int getStatusCode() {
            return this.f16273a;
        }

        public boolean isError() {
            return this != OK;
        }

        public boolean isOK() {
            return !isError();
        }
    }

    public Code getResponseCode() {
        Code[] values;
        if (isOK()) {
            return Code.OK;
        }
        for (Code code : Code.values()) {
            if (code.getStatusCode() == this.statusCode) {
                return code;
            }
        }
        return Code.UNKNOWN;
    }

    public boolean isSuccInStatusCode() {
        int i;
        return !isError() && (i = this.statusCode) < 400 && i > 0;
    }

    public boolean isStatusCode200() {
        return this.statusCode == 200;
    }

    public boolean isError() {
        return "error".equals(this.status) && this.errorCode > 0;
    }

    public boolean isUnknown() {
        return getResponseCode() == Code.UNKNOWN;
    }

    public boolean isOK() {
        return !isError();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public Code getErrorCode() {
        Code[] values;
        if (isOK()) {
            return Code.OK;
        }
        for (Code code : Code.values()) {
            if (code.getStatusCode() == this.errorCode) {
                return code;
            }
        }
        return Code.UNKNOWN;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String str) {
        this.resultMessage = str;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public boolean isSoDisabled() {
        return C6450m.m9963a().f16297c;
    }

    public String getSoHost() {
        return C6450m.m9963a().f16298d;
    }

    public void parseResult(String str) throws Exception {
        if (str == null) {
            throw new IllegalArgumentException("parseResult result is null");
        }
        Map<String, Object> jsonObject = getJsonObject(str);
        if (jsonObject == null || jsonObject.size() == 0) {
            return;
        }
        this.status = (String) jsonObject.get("status");
        if ("success".equals(this.status)) {
            this.resultMessage = jsonObject.containsKey("result") ? jsonObject.get("result").toString() : "";
        } else if ("error".equals(this.status) && (jsonObject.get("result") instanceof Map)) {
            Map map = (Map) jsonObject.get("result");
            this.errorCode = ((Double) map.get("errorCode")).intValue();
            this.resultMessage = map.get("errorMessage").toString();
        }
    }

    private Map<String, Object> getJsonObject(String str) {
        return (Map) new GsonBuilder().create().fromJson(str, new TypeToken<Map<String, Object>>() { // from class: com.networkbench.agent.impl.harvest.HarvestResponse.1
        }.getType());
    }

    public String toString() {
        return " {  statusCode=" + this.statusCode + ", status='" + this.status + "', errorCode=" + this.errorCode + ", resultMessage='" + this.resultMessage + "', responseTime=" + this.responseTime + ",\n responseBody='" + this.responseBody + "', \n configuration=" + this.configuration + '}';
    }

    public HarvestConfiguration getConfiguration() {
        return this.configuration;
    }

    public void setConfiguration(HarvestConfiguration harvestConfiguration) {
        this.configuration = harvestConfiguration;
    }

    public C6264a getExtensionConfig() {
        return this.extensionConfig;
    }

    public void setExtensionConfig(C6264a c6264a) {
        this.extensionConfig = c6264a;
    }
}
