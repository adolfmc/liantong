package com.networkbench.agent.impl.harvest;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.instrumentation.NBSTransactionStateUtil;
import com.networkbench.agent.impl.p243c.C6305i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ActionData extends HarvestableArray {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private String appData;
    private String appDataNew;
    private long bytesReceived;
    private long bytesSent;
    private String carrier;
    private String cdnVendorName;
    private int connectType;
    private String contentType;
    private boolean controllerDispatch;
    private Map dataTag;
    private int errorCode;
    private HttpLibType httpLibType;
    private int statusCode;
    private Long timestamp;
    private int totalTime;
    private String url;
    private String urlParams;

    /* renamed from: IP */
    private String f16266IP = "";
    private int time_to_dns = -1;
    private int time_to_connect = -1;
    private int time_ssl_handshake = -1;
    private int time_first_package = 0;
    private int timeToQueueTime = 0;
    private int remainPkTime = 0;
    private HashMap<String, JsonObject> unknown = new HashMap<>();
    private int app_phase = 0;
    private RequestMethodType requestMethod = RequestMethodType.GET;
    public HashMap<String, String> requestHeaderParam = new HashMap<>();
    public HashMap<String, String> responseHeaderParam = new HashMap<>();
    private JsonObject nshHeader = new JsonObject();

    public Map getDataTag() {
        return this.dataTag;
    }

    public void setDataTag(Map map) {
        this.dataTag = map;
    }

    public void setRemainPkTime(int i) {
        this.remainPkTime = i;
    }

    public ActionData() {
        this.httpLibType = HttpLibType.URLConnection;
        this.httpLibType = HttpLibType.URLConnection;
    }

    public String getAppDataNew() {
        return this.appDataNew;
    }

    private String check(String str) {
        return str.length() >= 1024 ? str.substring(0, 1024) : str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(check(this.url)));
        if (TextUtils.isEmpty(this.urlParams)) {
            jsonArray.add(new JsonPrimitive(""));
        } else if (!C6638h.m8963w().f17182z) {
            jsonArray.add(new JsonPrimitive(this.urlParams));
        } else {
            jsonArray.add(new JsonPrimitive(Harvest.getInstance().getConfiguration().encryptContentAES(this.urlParams)));
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.requestMethod.ordinal())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.httpLibType.ordinal())));
        jsonArray.add(new JsonPrimitive(this.f16266IP));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(calcTotalTime())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.time_to_dns)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.time_to_connect)));
        int i = this.time_first_package;
        if (i == -1) {
            i = 0;
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(i)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.time_ssl_handshake)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.statusCode)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(caclErrorCode())));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.bytesSent)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.bytesReceived)));
        String str = this.appData;
        jsonArray.add(str == null ? null : new JsonPrimitive(str));
        String str2 = this.contentType;
        if (str2 == null) {
            str2 = "";
        }
        jsonArray.add(new JsonPrimitive(str2));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.controllerDispatch ? 1 : 0)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.connectType)));
        String str3 = this.cdnVendorName;
        if (str3 == null) {
            str3 = "";
        }
        jsonArray.add(new JsonPrimitive(str3));
        if (C6638h.m8963w().m9038ah()) {
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.app_phase)));
        } else {
            jsonArray.add(new JsonPrimitive((Number) 0));
        }
        int i2 = this.timeToQueueTime;
        if (i2 == -1) {
            i2 = 0;
        }
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(i2)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(calcRemainPackageTime() != -1 ? calcRemainPackageTime() : 0)));
        HashMap<String, JsonObject> hashMap = this.unknown;
        if (hashMap != null) {
            jsonArray.add(C6653u.m8716c((Map<String, JsonObject>) hashMap));
        } else {
            jsonArray.add(new JsonObject());
        }
        if (C6638h.m8963w().m9065V()) {
            String str4 = this.appDataNew;
            if (str4 == null) {
                str4 = "";
            }
            jsonArray.add(new JsonPrimitive(str4));
            jsonArray.add(new JsonPrimitive(""));
            jsonArray.add(new JsonPrimitive(getHttpData()));
        }
        jsonArray.add(new JsonPrimitive(C6653u.m8701f(this.dataTag)));
        return jsonArray;
    }

    private String getHttpData() {
        return (getHeaderData() || getResHeaderData()) ? this.nshHeader.toString() : "";
    }

    private boolean getResHeaderData() {
        C6396h.m10126p("getResHeaderData :  " + this.responseHeaderParam.size());
        JsonObject jsonObject = new JsonObject();
        boolean z = false;
        z = false;
        z = false;
        if (this.responseHeaderParam.size() <= 0) {
            return false;
        }
        C6305i.C6308c avalidUrlParam = NBSTransactionStateUtil.getAvalidUrlParam(HarvestConfiguration.getDefaultHarvestConfiguration().getUrlParamArray(), this.url);
        if (avalidUrlParam != null) {
            C6396h.m10126p("getResHeaderData httpUrlParam:  " + avalidUrlParam.toString());
            String[] strArr = avalidUrlParam.f15830d;
            if (strArr != null && strArr.length > 0) {
                boolean z2 = false;
                for (int i = 0; i < strArr.length; i++) {
                    for (String str : this.responseHeaderParam.keySet()) {
                        C6396h.m10126p("getResHeaderData responseHeaderParam   key:  " + str);
                        if (str.toLowerCase().equalsIgnoreCase(strArr[i])) {
                            String str2 = this.responseHeaderParam.get(str);
                            if (!TextUtils.isEmpty(str2)) {
                                jsonObject.addProperty(strArr[i], str2);
                                z2 = true;
                            }
                        }
                    }
                }
                z = z2;
            }
        }
        if (z) {
            this.nshHeader.add("responseHeader", jsonObject);
        }
        return z;
    }

    private boolean getHeaderData() {
        C6396h.m10126p("getHeaderData :  " + this.requestHeaderParam.size());
        JsonObject jsonObject = new JsonObject();
        boolean z = false;
        z = false;
        z = false;
        if (this.requestHeaderParam.size() <= 0) {
            return false;
        }
        C6305i.C6308c avalidUrlParam = NBSTransactionStateUtil.getAvalidUrlParam(HarvestConfiguration.getDefaultHarvestConfiguration().getUrlParamArray(), this.url);
        if (avalidUrlParam != null) {
            C6396h.m10126p("getHeaderData httpUrlParam:  " + avalidUrlParam.toString());
            String[] strArr = avalidUrlParam.f15829c;
            if (strArr != null && strArr.length > 0) {
                boolean z2 = false;
                for (int i = 0; i < strArr.length; i++) {
                    for (String str : this.requestHeaderParam.keySet()) {
                        C6396h.m10126p("getHeaderData requestHeaderParam   key:  " + str);
                        if (str.toLowerCase().equalsIgnoreCase(strArr[i])) {
                            String str2 = this.requestHeaderParam.get(str);
                            C6396h.m10126p("getHeaderData requestHeaderParam   value:  " + str2);
                            if (!TextUtils.isEmpty(str2)) {
                                jsonObject.addProperty(strArr[i], str2);
                                z2 = true;
                            }
                        }
                    }
                }
                z = z2;
            }
        }
        if (z) {
            this.nshHeader.add("requestHeader", jsonObject);
        }
        return z;
    }

    private int caclErrorCode() {
        int i = this.statusCode;
        if (i >= 400 && i <= 600) {
            this.errorCode = 0;
        }
        return this.errorCode;
    }

    public void correctDataInfo() {
        log.mo10122a(String.format("before connecttime:%d, ssl:%d, remainpktime:%d, firstpk:%d, dns:%d, alltime:%d, queuetime:%d", Integer.valueOf(this.time_to_connect), Integer.valueOf(this.time_ssl_handshake), Integer.valueOf(this.remainPkTime), Integer.valueOf(this.time_first_package), Integer.valueOf(this.time_to_dns), Integer.valueOf(this.totalTime), Integer.valueOf(this.timeToQueueTime)));
        checkActionData();
        calcFirstpackage();
        calcQueueTime();
        log.mo10122a(String.format("after connecttime:%d, ssl:%d, remainpktime:%d, firstpk:%d, dns:%d, alltime:%d, queuetime:%d", Integer.valueOf(this.time_to_connect), Integer.valueOf(this.time_ssl_handshake), Integer.valueOf(this.remainPkTime), Integer.valueOf(this.time_first_package), Integer.valueOf(this.time_to_dns), Integer.valueOf(this.totalTime), Integer.valueOf(this.timeToQueueTime)));
    }

    public void calcQueueTime() {
        if (this.time_first_package <= 0) {
            this.time_first_package = C6653u.m8713d(((((this.totalTime - C6653u.m8713d(this.time_ssl_handshake)) - C6653u.m8713d(this.time_to_connect)) - C6653u.m8713d(this.time_first_package)) - C6653u.m8713d(this.time_to_dns)) - C6653u.m8713d(this.remainPkTime));
            this.timeToQueueTime = 0;
            return;
        }
        this.timeToQueueTime = C6653u.m8713d(((((this.totalTime - C6653u.m8713d(this.time_ssl_handshake)) - C6653u.m8713d(this.time_to_connect)) - C6653u.m8713d(this.time_first_package)) - C6653u.m8713d(this.time_to_dns)) - C6653u.m8713d(this.remainPkTime));
    }

    public void checkActionData() {
        if (conutHttpTime()) {
            if (this.time_ssl_handshake >= this.totalTime) {
                this.time_ssl_handshake = -1;
            }
            if (this.time_to_connect >= this.totalTime) {
                this.time_to_connect = -1;
            }
            if (this.time_to_dns >= this.totalTime) {
                this.time_to_dns = -1;
            }
            if (this.remainPkTime >= this.totalTime) {
                this.remainPkTime = -1;
            }
            if (this.time_first_package >= this.totalTime) {
                this.time_first_package = -1;
            }
            if (conutHttpTime()) {
                this.time_to_connect = -1;
            }
            if (conutHttpTime()) {
                this.time_ssl_handshake = -1;
            }
            if (conutHttpTime()) {
                this.remainPkTime = -1;
            }
            if (conutHttpTime()) {
                this.time_first_package = -1;
            }
            if (conutHttpTime()) {
                this.timeToQueueTime = 0;
            }
        }
    }

    private boolean conutHttpTime() {
        return ((((C6653u.m8713d(this.time_ssl_handshake) + C6653u.m8713d(this.time_to_connect)) + C6653u.m8713d(this.time_first_package)) + C6653u.m8713d(this.time_to_dns)) + C6653u.m8713d(this.remainPkTime)) + C6653u.m8713d(this.timeToQueueTime) >= this.totalTime;
    }

    public void calcFirstpackage() {
        if (C6653u.m8713d(this.time_first_package) == 0) {
            this.time_first_package = C6653u.m8713d((((this.totalTime - C6653u.m8713d(this.time_ssl_handshake)) - C6653u.m8713d(this.time_to_connect)) - C6653u.m8713d(this.time_to_dns)) - C6653u.m8713d(this.remainPkTime));
        }
    }

    private int calcRemainPackageTime() {
        int i = this.remainPkTime;
        if (i > 0) {
            return i;
        }
        return 0;
    }

    public int getAllTime() {
        return C6653u.m8713d(this.time_to_dns) + C6653u.m8713d(this.time_to_connect) + C6653u.m8713d(this.time_ssl_handshake) + C6653u.m8713d(this.time_first_package) + C6653u.m8713d(this.remainPkTime) + C6653u.m8713d(this.timeToQueueTime);
    }

    public int calcTotalTime() {
        return this.totalTime;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUrlParams() {
        return this.urlParams;
    }

    public void setUrlParams(String str) {
        this.urlParams = str;
    }

    public RequestMethodType getRequestMethod() {
        return this.requestMethod;
    }

    public void setRequestMethod(RequestMethodType requestMethodType) {
        this.requestMethod = requestMethodType;
    }

    public void setCarrier(String str) {
        this.carrier = str;
    }

    public void setTotalTime(int i) {
        this.totalTime = i;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public void setErrorCode(int i) {
        int i2 = this.statusCode;
        if (i2 >= 400 && i2 <= 600) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("setErrorCode :  重置为0 ... url + " + this.url);
            this.errorCode = 0;
            return;
        }
        InterfaceC6393e interfaceC6393e2 = log;
        interfaceC6393e2.mo10122a("setErrorCode  : " + i + " ......url" + this.url);
        this.errorCode = i;
    }

    public void setBytesSent(long j) {
        this.bytesSent = j;
    }

    public void setBytesReceived(long j) {
        this.bytesReceived = j;
    }

    public void setAppData(String str) {
        this.appData = str;
    }

    public void setAppDataNew(String str) {
        this.appDataNew = str;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public String getUrl() {
        return this.url;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public double getTotalTime() {
        return this.totalTime;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public long getBytesSent() {
        return this.bytesSent;
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public String getAppData() {
        return this.appData;
    }

    public HttpLibType getHttpLibType() {
        return this.httpLibType;
    }

    public void setHttpLibType(HttpLibType httpLibType) {
        this.httpLibType = httpLibType;
    }

    public void setIP(String str) {
        this.f16266IP = str;
    }

    public String getIP() {
        return this.f16266IP;
    }

    public void setTime_to_dns(int i) {
        this.time_to_dns = i;
    }

    public void setTime_to_connect(int i) {
        this.time_to_connect = i;
    }

    public void setTime_ssl_handshake(int i) {
        this.time_ssl_handshake = i;
    }

    public void setTime_first_package(int i) {
        this.time_first_package = i;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public void setConnectType(int i) {
        this.connectType = i;
    }

    public String getCdnVendorName() {
        return this.cdnVendorName;
    }

    public void setCdnVendorName(String str) {
        this.cdnVendorName = str;
    }

    public void setTimeToQueueTime(int i) {
        this.timeToQueueTime = i;
    }

    public int getAppPhase() {
        return this.app_phase;
    }

    public void setAppPhase(int i) {
        this.app_phase = i;
    }

    public HashMap<String, JsonObject> getUnknown() {
        return this.unknown;
    }

    public void setUnknown(HashMap<String, JsonObject> hashMap) {
        this.unknown = hashMap;
    }

    public int getTime_to_dns() {
        return this.time_to_dns;
    }

    public int getTime_to_connect() {
        return this.time_to_connect;
    }

    public int getTime_ssl_handshake() {
        return this.time_ssl_handshake;
    }

    public int getTime_first_package() {
        return this.time_first_package;
    }

    public String toString() {
        return "[url=" + this.url + ", carrier=" + this.carrier + ", totalTime(include timeQueueTime)=" + calcTotalTime() + ", statusCode=" + this.statusCode + ", errorCode=" + this.errorCode + ", bytesSent=" + this.bytesSent + ", bytesReceived=" + this.bytesReceived + ", appData=" + this.appData + ", timestamp=" + this.timestamp + ", totalTime=" + this.totalTime + ", urlParams=" + this.urlParams + ", requestMethod=" + this.requestMethod + ", httpLibType=" + this.httpLibType + ", IP = " + this.f16266IP + ", time_to_dns = " + this.time_to_dns + ", time_to_connect = " + this.time_to_connect + ",time_to_ssl= " + this.time_ssl_handshake + ", time_first_package =" + this.time_first_package + ",content_type=" + this.contentType + ",ctl_flag=" + (this.controllerDispatch ? 1 : 0) + ",connectType=" + this.connectType + ",cdnVendorName=" + this.cdnVendorName + ",app_phase=" + this.app_phase + ", timeQueueTime:" + this.timeToQueueTime + ", remainPkTime:" + calcRemainPackageTime() + "]";
    }
}
