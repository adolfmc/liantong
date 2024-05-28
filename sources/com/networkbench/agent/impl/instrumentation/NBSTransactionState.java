package com.networkbench.agent.impl.instrumentation;

import android.os.Looper;
import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.p239a.p240a.C6225a;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.socket.C6617n;
import com.networkbench.agent.impl.socket.C6621r;
import com.networkbench.agent.impl.socket.C6624s;
import com.networkbench.agent.impl.socket.p278a.EnumC6602b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6648q;
import com.networkbench.agent.impl.util.C6653u;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class NBSTransactionState {
    public static final int URLLIMIT = 1024;
    private static final InterfaceC6393e log = C6394f.m10150a();
    private String allGetRequestParams;
    private ArrayList<String> apmsList;
    private String appData;
    private int appPhase;
    private long bytesReceived;
    private long bytesSent;
    private String carrier;
    private String cdnVendorName;
    private String contentType;
    private Map dataTag;
    private int dnsElapse;
    private long endTime;
    private int errorCode;
    private C6225a errorData;
    private HttpLibType httpLibType;
    public int remainPackage;
    private RequestMethodType requestMethod;
    private int sslHandShakeTime;
    private long startTime;
    private EnumC6475a state;
    private int statusCode;
    private int tcpHandShakeTime;
    public C6226b transactionData;
    private String url;
    private String userActionId;
    public volatile boolean hasParseUrlParams = false;
    private String appDataNew = "";
    private boolean isNetworkErrorExist = false;
    private String requestHeaderIdValue = "";
    public boolean isConnect = false;
    private String exception = null;
    private String formattedUrlParams = null;
    private String urlParams = null;
    private C6624s urlBuilder = new C6624s();
    private String addressAllStr = "";
    private boolean isOk2DnsFromThread = false;
    private boolean hasInvokeSetTrace = false;
    private String ipAddress = "";
    private int firstPacketPeriod = -1;
    public HashMap<String, String> requestHeaderParam = new HashMap<>();
    public HashMap<String, String> responseHeaderParam = new HashMap<>();

    /* renamed from: a */
    String f16373a = "";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.instrumentation.NBSTransactionState$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6475a {
        READY,
        SENT,
        COMPLETE
    }

    public ArrayList<String> getApmsList() {
        ArrayList<String> arrayList = this.apmsList;
        if (arrayList != null) {
            return arrayList;
        }
        this.apmsList = new ArrayList<>();
        return this.apmsList;
    }

    public void setRequestHeaderIdValue(String str) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("setRequestHeaderIdValue:" + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.requestHeaderIdValue = str;
    }

    private boolean isEqualsToNBSRequestHeader(String str) {
        return !TextUtils.isEmpty(str) && str.equalsIgnoreCase(C6638h.m8963w().f17178d);
    }

    public void setNBSRequestHeader(String str, String str2) {
        try {
            if (C6638h.m8963w().m9036aj() && isEqualsToNBSRequestHeader(str)) {
                setRequestHeaderIdValue(str2);
            }
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("setting value error:" + th.getMessage());
        }
    }

    public void setErrorDataInfo(String str, Map<String, Object> map, String str2) {
        C6225a c6225a = this.errorData;
        c6225a.f15365a = str;
        c6225a.f15366b = map;
        c6225a.f15367c = str2;
        this.isNetworkErrorExist = true;
    }

    public boolean isNetworkExist() {
        return this.isNetworkErrorExist;
    }

    public C6226b getTransactionData() {
        return this.transactionData;
    }

    public C6225a getErrorData() {
        return this.errorData;
    }

    public void setOk2DnsFromThread(boolean z) {
        this.isOk2DnsFromThread = z;
    }

    public void setDnsElapse(int i) {
        this.dnsElapse = i;
    }

    public int getDnsElapse() {
        return this.dnsElapse;
    }

    public void resetStartTimeStamp(long j) {
        this.startTime = j;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String str) {
        if (str == null) {
            str = "";
        }
        this.ipAddress = str;
    }

    public void setTcpHandShakeTime(int i) {
        this.tcpHandShakeTime = i;
    }

    public void setFirstPacketPeriod(int i) {
        this.firstPacketPeriod = i;
    }

    public void setRemainPackage(int i) {
        this.remainPackage = i;
    }

    public String getAllGetRequestParams() {
        return this.allGetRequestParams;
    }

    public void setAllGetRequestParams(String str) {
        this.allGetRequestParams = str;
    }

    public String getUserActionId() {
        return this.userActionId;
    }

    public void setUserActionId(String str) {
        this.userActionId = str;
    }

    public int getAppPhase() {
        return this.appPhase;
    }

    public void setAppPhase(int i) {
        this.appPhase = i;
    }

    public String getCdnVendorName() {
        return this.cdnVendorName;
    }

    public void setCdnVendorName(String str) {
        this.cdnVendorName = str;
    }

    public void setRequestMethod(RequestMethodType requestMethodType) {
        this.requestMethod = requestMethodType;
    }

    public String getRequestMethod() {
        return this.requestMethod.name();
    }

    public RequestMethodType getRequestMethodType() {
        return this.requestMethod;
    }

    public String getUrlParams() {
        return this.urlParams;
    }

    public void setUrlParams(String str) {
        this.urlParams = str;
    }

    public String getFormattedUrlParams() {
        return this.formattedUrlParams;
    }

    public void setFormattedUrlParams(String str) {
        String str2 = this.urlParams;
        if (str2 != null && !str2.isEmpty()) {
            if (!str.isEmpty()) {
                str = this.urlParams + "&" + str;
            } else {
                str = this.urlParams;
            }
        }
        if (str.endsWith("&")) {
            str = str.substring(0, str.length() - 1);
        }
        if (str != null && str.length() > 1024) {
            this.formattedUrlParams = str.substring(0, 1024);
        } else {
            this.formattedUrlParams = str;
        }
        this.hasParseUrlParams = true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("url:" + this.url);
        sb.append(",statusCode:" + this.statusCode);
        sb.append(",errorCode:" + this.errorCode);
        sb.append(",bytesSent:" + this.bytesSent);
        sb.append(",bytesReceived:" + this.bytesReceived);
        sb.append(",startTime:" + this.startTime);
        sb.append(",endTime:" + this.endTime);
        sb.append(",appData:" + this.appData);
        sb.append(",appDataNew:" + this.appDataNew);
        sb.append(",carrier:" + this.carrier);
        sb.append(",state:" + this.state.ordinal());
        sb.append(",contentType:" + this.contentType);
        if (this.transactionData != null) {
            sb.append(",trancastionData:" + this.transactionData.toString());
        }
        if (this.formattedUrlParams != null) {
            sb.append(",formattedUrlParams:" + this.formattedUrlParams);
        }
        sb.append(",Requestmethodtype:" + this.requestMethod);
        sb.append(",httplibType:" + this.httpLibType);
        sb.append(",urlBuilder:" + this.urlBuilder);
        return sb.toString();
    }

    public NBSTransactionState() {
        try {
            this.dataTag = C6638h.m8963w().m9001h();
            initMembers();
            setTraces();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("error init NBSTransactionState e:" + th.getMessage());
        }
    }

    public NBSTransactionState(boolean z) {
        try {
            this.dataTag = C6638h.m8963w().m9001h();
            initMembers();
            if (z) {
                return;
            }
            setTraces();
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("error init NBSTransactionState e:" + th.getMessage());
        }
    }

    private void initMembers() {
        this.startTime = System.currentTimeMillis();
        this.tcpHandShakeTime = -1;
        this.sslHandShakeTime = -1;
        this.carrier = "Other";
        this.state = EnumC6475a.READY;
        this.errorCode = EnumC6602b.OK.m9275a();
        this.requestMethod = RequestMethodType.GET;
        this.httpLibType = HttpLibType.URLConnection;
        this.dnsElapse = -1;
        this.ipAddress = "";
        this.errorData = new C6225a();
    }

    public void setTraces() {
        if (this.hasInvokeSetTrace) {
            return;
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            NBSTraceUnit nBSTraceUnit = new NBSTraceUnit("", C6295m.EnumC6300e.NETWORK.ordinal());
            this.userActionId = C6255f.m10804b(nBSTraceUnit);
            NBSTraceEngine.notifyObserverAsyncEnterMethod(nBSTraceUnit);
            if (TextUtils.isEmpty(this.userActionId) && C6255f.f15556e) {
                this.userActionId = C6255f.m10820a();
            }
        } else {
            this.userActionId = "";
        }
        this.hasInvokeSetTrace = true;
    }

    public EnumC6475a getState() {
        return this.state;
    }

    public void setState(int i) {
        if (i == EnumC6475a.READY.ordinal()) {
            this.state = EnumC6475a.READY;
        } else if (i == EnumC6475a.SENT.ordinal()) {
            this.state = EnumC6475a.SENT;
        } else if (i == EnumC6475a.COMPLETE.ordinal()) {
            this.state = EnumC6475a.COMPLETE;
        }
    }

    public void setCarrier(String str) {
        if (!isSent()) {
            this.carrier = str;
            return;
        }
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10115e("setCarrier(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setAppData(String str) {
        if (!isComplete()) {
            this.appData = str;
            if ("".equals(str)) {
                return;
            }
            return;
        }
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10115e("setAppData(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setAppDataNew(String str) {
        if (!isComplete()) {
            this.appDataNew = checkAppDataNew(str);
            return;
        }
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10115e("setAppDataNew(...) called on TransactionState in " + this.state.toString() + " state");
    }

    private String checkAppDataNew(String str) {
        JSONObject jSONObject;
        try {
            if (TextUtils.isEmpty(this.f16373a)) {
                return str;
            }
            C6396h.m10126p("  setAppDataNew  isApms :   " + C6638h.m8963w().m9027b());
            if (C6638h.m8963w().m9027b()) {
                if (TextUtils.isEmpty(str)) {
                    C6396h.m10126p("  setAppDataNew  appDataNew : 为空 ");
                    jSONObject = new JSONObject();
                } else {
                    C6396h.m10126p("  setAppDataNew  appDataNew : " + str);
                    jSONObject = new JSONObject(str);
                }
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray = new JSONArray(C6638h.m8963w().m9060a().toString());
                C6396h.m10126p("  setAppDataNew  appDataNew  apmsIssue: " + jSONArray);
                for (int i = 0; i < checkSize(jSONArray.length()); i++) {
                    String string = jSONArray.getString(i);
                    if (checkRepetition(string)) {
                        C6396h.m10126p("  setAppDataNew  header和用户重复, 不添加 : " + string);
                    } else {
                        jSONObject2.put(string, this.f16373a);
                        C6396h.m10126p("  setAppDataNew  apms add : " + string + " ------- " + this.f16373a);
                    }
                }
                jSONObject.put("apms", jSONObject2);
                C6396h.m10126p("setAppDataNew  apmsJson :  " + jSONObject);
                return jSONObject.toString();
            }
            return str;
        } catch (Throwable th) {
            C6396h.m10126p("setAppDataNew JSONException has error " + th.getLocalizedMessage());
            return "";
        }
    }

    private int checkSize(int i) {
        return i < 10 ? i : C6638h.f17106c;
    }

    private boolean checkRepetition(String str) {
        ArrayList<String> arrayList = this.apmsList;
        if (arrayList == null) {
            return false;
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public void setAddress(String str) {
        this.urlBuilder.m9194a(str);
    }

    public void setAddressAllStr(String str) {
        this.addressAllStr = str;
    }

    public void setPort(int i) {
        this.urlBuilder.m9196a(i);
    }

    public void setUrl(String str) {
        String m8744a = C6653u.m8744a(str);
        if (m8744a == null) {
            return;
        }
        if (m8744a != null && m8744a.length() > 1024) {
            m8744a = m8744a.substring(0, 1024);
        }
        if (!isSent()) {
            this.url = m8744a;
            return;
        }
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10115e("setUrl(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isSent() {
        return this.state.ordinal() >= EnumC6475a.SENT.ordinal();
    }

    public boolean isComplete() {
        return this.state.ordinal() >= EnumC6475a.COMPLETE.ordinal();
    }

    public void setStatusCode(int i) {
        this.isConnect = true;
        if (!isComplete()) {
            this.statusCode = i;
            if (i == 200) {
                InterfaceC6393e interfaceC6393e = log;
                interfaceC6393e.mo10117c("set status code:" + i);
            }
        } else {
            this.statusCode = i;
            InterfaceC6393e interfaceC6393e2 = log;
            interfaceC6393e2.mo10115e("setStatusCode(...) called on TransactionState in " + this.state.toString() + " state");
        }
        if (this.statusCode == 0) {
            this.statusCode = -1;
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean isError() {
        int i = this.statusCode;
        return i >= 400 || i == -1;
    }

    public void setErrorCode(int i, String str) {
        if (!isComplete()) {
            this.errorCode = i;
            this.exception = str;
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("errorCode:" + this.errorCode + ", errorInfo:" + this.exception);
            return;
        }
        this.errorCode = i;
        this.exception = str;
        InterfaceC6393e interfaceC6393e2 = log;
        interfaceC6393e2.mo10122a("errorCode:" + this.errorCode + ", errorInfo:" + this.exception);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setBytesSent(long j) {
        if (!isComplete()) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10117c(j + " bytes sent");
            this.bytesSent = j;
            this.state = EnumC6475a.SENT;
            return;
        }
        InterfaceC6393e interfaceC6393e2 = log;
        interfaceC6393e2.mo10115e("setBytesSent(...) called on TransactionState in " + this.state.toString() + " state");
    }

    public void setBytesSentAfterComplete(long j) {
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10117c("After Complete " + j + " bytes sent.");
        this.bytesSent = j;
        this.state = EnumC6475a.SENT;
    }

    public void setBytesReceived(long j) {
        this.bytesReceived = j;
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10117c(j + "bytes received");
    }

    public void setBytesReceivedContent(long j) {
        if (this.bytesReceived <= 0) {
            this.bytesReceived = j;
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10117c(j + "bytes received");
        }
    }

    public long getBytesReceived() {
        return this.bytesReceived;
    }

    public HttpLibType getHttpLibType() {
        return this.httpLibType;
    }

    public void setHttpLibType(HttpLibType httpLibType) {
        this.httpLibType = httpLibType;
    }

    public void setEndState() {
        if (isComplete()) {
            return;
        }
        this.state = EnumC6475a.COMPLETE;
        this.endTime = System.currentTimeMillis();
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public C6226b end() {
        setEndState();
        return toTransactionData();
    }

    public C6226b endWithEndTimeStamp(long j) {
        setEndState();
        setEndTime(j);
        return toTransactionData();
    }

    private C6226b toTransactionData() {
        if (!isComplete()) {
            log.mo10115e("toTransactionData() called on incomplete TransactionState");
        }
        if (this.url == null) {
            log.mo10116d("Attempted to convert a TransactionState instance with no URL into a TransactionData");
            return null;
        }
        C6396h.m10126p("requestHeaderParam : " + this.requestHeaderParam.size());
        C6396h.m10126p("responseHeaderParam : " + this.responseHeaderParam.size());
        InterfaceC6393e interfaceC6393e = log;
        interfaceC6393e.mo10122a("firstpktime:" + this.firstPacketPeriod + ", remainPackage:" + this.remainPackage);
        if (this.transactionData == null) {
            String trimmedUrl = getTrimmedUrl(this.url);
            String str = this.carrier;
            long j = this.endTime;
            long j2 = this.startTime;
            this.transactionData = new C6226b(trimmedUrl, str, (int) (j - j2), this.statusCode, this.errorCode, this.bytesSent, this.bytesReceived, this.appData, this.formattedUrlParams, this.urlParams, this.requestMethod, this.httpLibType, this.dnsElapse, this.ipAddress, this.tcpHandShakeTime, this.sslHandShakeTime, this.firstPacketPeriod, this.cdnVendorName, this.contentType, this.appPhase, this.userActionId, this.allGetRequestParams, this.remainPackage, this.requestHeaderParam, this.responseHeaderParam, this.appDataNew, j2, this.requestHeaderIdValue, j);
        }
        this.transactionData.m10981a(this.dataTag);
        checkAppPhase(this.transactionData);
        if (!isWebViewData()) {
            setOtherTimeInfo(this.transactionData);
        }
        return this.transactionData;
    }

    private void checkAppPhase(C6226b c6226b) {
        if (this.appPhase != C6638h.f17113m.intValue()) {
            c6226b.m10962i(0);
        }
    }

    private String getTrimmedUrl(String str) {
        int indexOf = str.indexOf(63);
        if (indexOf < 0 && (indexOf = str.indexOf(59)) < 0) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    private boolean isWebViewData() {
        return this.httpLibType == HttpLibType.Webview || this.httpLibType == HttpLibType.WebviewAJAX || this.httpLibType == HttpLibType.WebViewResource;
    }

    public String getConfigIpAddress() {
        String str = this.url;
        if (str == null) {
            return "";
        }
        String m8957a = C6640i.m8957a(str);
        if (TextUtils.isEmpty(m8957a)) {
            return "";
        }
        String m8776c = C6648q.m8776c(m8957a);
        if (!TextUtils.isEmpty(m8776c)) {
            this.ipAddress = m8776c;
        }
        if (this.httpLibType == HttpLibType.HttpClient) {
            String m8772e = C6648q.m8772e(this.addressAllStr);
            if (!TextUtils.isEmpty(m8772e)) {
                this.ipAddress = m8772e;
            }
        }
        return this.ipAddress;
    }

    private void setInfoIfOkhttpLib(C6226b c6226b, String str) {
        if (this.isOk2DnsFromThread) {
            c6226b.m10964h(C6621r.m9199b(str) + c6226b.m10988B());
        }
        if (c6226b.m10958m() <= 0) {
            c6226b.m10973d(C6648q.m8780a(str));
        }
        if (c6226b.m10956o() <= 0) {
            c6226b.m10970e(C6621r.m9203a().m9225c(str));
        }
        if (c6226b.m10980b() <= 0) {
            c6226b.m10986a(C6621r.m9203a().m9224d(str));
        }
    }

    private void setInfoIfHttpClientLib(C6226b c6226b) {
        c6226b.m10973d(C6648q.m8774d(this.addressAllStr));
        C6617n m9223e = C6621r.m9203a().m9223e(this.addressAllStr);
        if (m9223e != null) {
            c6226b.m10970e(m9223e.m9232a());
            c6226b.m10986a(m9223e.m9230b());
        }
        String m8772e = C6648q.m8772e(this.addressAllStr);
        if (!TextUtils.isEmpty(m8772e)) {
            this.ipAddress = m8772e;
        }
        c6226b.m10972d(this.ipAddress);
    }

    public void setOtherTimeInfo(C6226b c6226b) {
        String str;
        if (c6226b == null || (str = this.url) == null) {
            return;
        }
        String m8957a = C6640i.m8957a(str);
        if (TextUtils.isEmpty(m8957a)) {
            return;
        }
        if (this.url.startsWith("https")) {
            c6226b.m10976c(C6621r.m9202a(m8957a));
        }
        if (this.statusCode != 901) {
            String m8776c = C6648q.m8776c(m8957a);
            if (!TextUtils.isEmpty(m8776c)) {
                this.ipAddress = m8776c;
            } else {
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(m8957a);
                    if (allByName != null) {
                        this.ipAddress = allByName[0].getHostAddress();
                    }
                } catch (Throwable unused) {
                }
            }
            if (TextUtils.isEmpty(this.ipAddress) && c6226b.m10945z() == HttpLibType.OkHttp) {
                this.ipAddress = C6621r.f17047b.get(m8957a) == null ? "" : C6621r.f17047b.get(m8957a);
            }
            c6226b.m10972d(this.ipAddress);
        } else {
            this.ipAddress = "";
        }
        if (this.httpLibType == HttpLibType.OkHttp) {
            setInfoIfOkhttpLib(c6226b, m8957a);
        } else {
            c6226b.m10973d(C6648q.m8780a(m8957a));
            c6226b.m10970e(C6621r.m9203a().m9225c(m8957a));
            c6226b.m10986a(C6621r.m9203a().m9224d(m8957a));
        }
        if (this.httpLibType == HttpLibType.HttpClient) {
            setInfoIfHttpClientLib(c6226b);
        }
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String str) {
        this.contentType = str;
    }

    public String getException() {
        return this.exception;
    }

    public void setUUid(String str) {
        this.f16373a = str;
    }

    public String getUuid() {
        return this.f16373a;
    }
}
