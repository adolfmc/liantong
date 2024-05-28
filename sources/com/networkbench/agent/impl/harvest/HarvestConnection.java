package com.networkbench.agent.impl.harvest;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.p260a.C6448l;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.p252e.C6360k;
import com.networkbench.agent.impl.p252e.C6373t;
import com.networkbench.agent.impl.p252e.C6377w;
import com.networkbench.agent.impl.p252e.InterfaceC6368p;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6646o;
import com.networkbench.agent.impl.util.C6653u;
import java.io.File;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestConnection {
    private static final String DEFAULT_HOST_REDIRECT_NAME = "redirect.networkbench.com";
    public static final int HOST_ERROR = 8888;
    public static final String INIT_SP_TAG = "initresult";
    private String actionDefinerUrl;
    private String applicationToken;
    private String collectorHost;
    private ConnectInformation connectInformation;
    private HarvestConnectionInterface connectionInterface;
    private HarvestSoc harvestSoc;
    private String resourceHost;
    private boolean useSsl;
    private static final InterfaceC6393e log = C6394f.m10150a();
    public static String[] redirectHostList = {"redirect.networkbench.com", "tyrd1.networkbench.com", "tyrd2.networkbench.com", "tyrd1.tingyun.com", "tyrd2.tingyun.com"};
    private static int redirectHostIndex = 1;
    public static boolean isRedirectSuccess = false;
    public static String redirectHost = "redirect.networkbench.com";
    public static boolean IsCertEnabled = true;
    private static boolean isSoDisable = true;

    public HarvestConnection() {
        if (C6638h.m8963w().m9069R()) {
            this.useSsl = true;
        }
        isSoDisable = true;
        this.harvestSoc = new HarvestSoc();
        this.connectionInterface = new HarvestURLConnection(C6653u.m8697h(), NBSAgent.getDeviceInformation().initUserHeaderValue(), C6638h.m8963w().m9086A());
    }

    public HarvestResponse getRedirectHost() {
        HarvestResponse harvestResponse = null;
        try {
            harvestResponse = this.connectionInterface.sendDataStr(null, C6448l.m9964a(EnumC6455q.REDIRECT, redirectHost, this.useSsl));
        } catch (Exception e) {
            log.mo10121a("send init info failed", e);
        }
        if (harvestResponse != null && !harvestResponse.isSoDisabled()) {
            this.harvestSoc.setSocketInfo(harvestResponse);
        }
        if (harvestResponse != null && harvestResponse.isOK() && !TextUtils.isEmpty(redirectHost)) {
            isRedirectSuccess = true;
        }
        if (harvestResponse == null || !harvestResponse.isOK()) {
            String redirectResult = getRedirectResult();
            if (!TextUtils.isEmpty(redirectResult)) {
                HarvestResponse mo9947a = C6448l.m9964a(EnumC6455q.REDIRECT, redirectHost, this.useSsl).mo9947a(redirectResult, new HarvestResponse());
                mo9947a.setStatusCode(200);
                return mo9947a;
            }
            redirectHost = m9985a();
            return harvestResponse;
        }
        return harvestResponse;
    }

    /* renamed from: a */
    protected static String m9985a() {
        String[] strArr = redirectHostList;
        if (strArr == null || strArr.length == 0) {
            return redirectHost;
        }
        redirectHostIndex %= strArr.length;
        int i = redirectHostIndex;
        redirectHostIndex = i + 1;
        return strArr[i];
    }

    private String getRedirectResult() {
        String m8842c = new C6646o(C6638h.m8963w().m9076K()).m8842c("initresult");
        return !TextUtils.isEmpty(m8842c) ? m8842c : "";
    }

    public void getActionDefinerHost() {
        try {
            this.actionDefinerUrl = this.connectionInterface.sendDataGet(C6448l.m9964a(EnumC6455q.ACTION_SELECTED, redirectHost, this.useSsl)).getResultMessage();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("getActionDefinerHost error:" + e);
        }
    }

    public void getResourceBmp() {
        try {
            this.resourceHost = this.connectionInterface.sendDataGet(C6448l.m9964a(EnumC6455q.RESOURE_HOST, redirectHost, this.useSsl)).getResultMessage();
            if (TextUtils.isEmpty(this.resourceHost)) {
                return;
            }
            new C6360k(getHttpScheme(this.resourceHost), C6638h.m8963w().m9076K()).m10295a();
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("getResourceHost error:" + e);
        }
    }

    public void sendActionDefinerData(InterfaceC6368p interfaceC6368p, C6377w c6377w) {
        try {
            if (TextUtils.isEmpty(this.actionDefinerUrl)) {
                log.mo10122a("sendActionDefinerData report url is empty");
                interfaceC6368p.mo10188a(8888);
                return;
            }
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("selectInfo appId:" + c6377w.m10237a() + ", className:" + c6377w.m10233c() + " , methodName:" + c6377w.m10231d() + ", vcName:" + c6377w.m10225g() + ", acName:" + c6377w.m10235b() + ", token:" + c6377w.m10227f() + ", filePart:" + c6377w.m10223h());
            StringBuilder sb = new StringBuilder();
            sb.append(this.actionDefinerUrl);
            sb.append("/mobile/operate/api/produceAppData");
            String httpScheme = getHttpScheme(sb.toString());
            InterfaceC6393e interfaceC6393e2 = log;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("action definer url:");
            sb2.append(httpScheme);
            interfaceC6393e2.mo10122a(sb2.toString());
            HttpMultiPart httpMultiPart = new HttpMultiPart(httpScheme, "utf-8");
            httpMultiPart.addFormField("appId", c6377w.m10237a());
            httpMultiPart.addFormField("className", c6377w.m10233c());
            httpMultiPart.addFormField("methodName", c6377w.m10231d());
            httpMultiPart.addFormField("optTypeId", "64");
            httpMultiPart.addFormField("vcName", c6377w.m10225g());
            httpMultiPart.addFormField("acName", c6377w.m10235b());
            httpMultiPart.addFormField("token", c6377w.m10227f());
            httpMultiPart.addFilePart("files", new File(c6377w.m10223h()));
            String finish = httpMultiPart.finish();
            log.mo10120a("upload bitmap result is : ", finish);
            if (TextUtils.isEmpty(finish)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(finish);
            int i = jSONObject.getInt("status");
            String string = jSONObject.getString("message");
            showUploadActionDefinerLog(i, interfaceC6368p);
            InterfaceC6393e interfaceC6393e3 = log;
            interfaceC6393e3.mo10122a("upload info statusCode:" + i + ", message:" + string);
        } catch (Exception e) {
            log.mo10121a("error process part", e);
        }
    }

    private String getHttpScheme(String str) {
        if (str.startsWith("http")) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.useSsl ? "https://" : "http://");
        sb.append(str);
        return sb.toString();
    }

    public void sendVisualInfo(InterfaceC6368p interfaceC6368p, C6373t c6373t) {
        try {
            if (TextUtils.isEmpty(this.actionDefinerUrl)) {
                log.mo10122a("sendActionDefinerData report url is empty");
                interfaceC6368p.mo10188a(8888);
                return;
            }
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("selectInfo appId:" + c6373t.m10261a() + ", name:" + c6373t.m10259b() + ", features:" + c6373t.m10257c() + ", token:" + c6373t.m10255d() + ", filePart:" + c6373t.m10253e());
            StringBuilder sb = new StringBuilder();
            sb.append(this.actionDefinerUrl);
            sb.append("/mobile/api/ux/page/uploadVisualInfo");
            String httpScheme = getHttpScheme(sb.toString());
            InterfaceC6393e interfaceC6393e2 = log;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("action definer url:");
            sb2.append(httpScheme);
            interfaceC6393e2.mo10122a(sb2.toString());
            HttpMultiPart httpMultiPart = new HttpMultiPart(httpScheme, "utf-8");
            httpMultiPart.addFormField("appId", c6373t.m10261a());
            httpMultiPart.addFormField("name", c6373t.m10259b());
            httpMultiPart.addFormField("features", c6373t.m10257c());
            httpMultiPart.addFormField("token", c6373t.m10255d());
            httpMultiPart.addFilePart("files", new File(c6373t.m10253e()));
            String finish = httpMultiPart.finish();
            log.mo10120a("upload bitmap result is : ", finish);
            if (TextUtils.isEmpty(finish)) {
                return;
            }
            JSONObject jSONObject = new JSONObject(finish);
            int i = jSONObject.getInt("status");
            String string = jSONObject.getString("message");
            showUploadActionDefinerLog(i, interfaceC6368p);
            InterfaceC6393e interfaceC6393e3 = log;
            interfaceC6393e3.mo10122a("upload info statusCode:" + i + ", message:" + string);
        } catch (Exception e) {
            log.mo10121a("error process part", e);
        }
    }

    private void showUploadActionDefinerLog(int i, InterfaceC6368p interfaceC6368p) {
        if (i != 200) {
            switch (i) {
                case 500:
                    log.mo10115e("statusCode is 500, dc error");
                    break;
                case 501:
                    log.mo10115e("statusCode is 501, token error");
                    break;
                case 502:
                    log.mo10115e("statusCode is 502, appid error");
                    break;
                case 503:
                    log.mo10115e("statusCode is 503, bitmap error");
                    break;
                default:
                    InterfaceC6393e interfaceC6393e = log;
                    interfaceC6393e.mo10122a("upload ActionDefiner statusCode:" + i);
                    break;
            }
        } else {
            log.mo10122a("upload ActionDefiner success");
        }
        interfaceC6368p.mo10188a(i);
    }

    public HarvestResponse sendConnect() {
        if (!isSoDisable) {
            return this.harvestSoc.sendConnect(this.connectInformation);
        }
        ConnectInformation connectInformation = this.connectInformation;
        if (connectInformation == null) {
            throw new IllegalArgumentException();
        }
        HarvestResponse response = getResponse(connectInformation.toJsonString(), EnumC6455q.INIT_MOBILE);
        if (response == null || response.getErrorCode().f16273a == -1) {
            redirectHost = m9985a();
        }
        return response;
    }

    public HarvestResponse getResponse(String str, EnumC6455q enumC6455q) {
        try {
            return this.connectionInterface.sendDataStr(str, C6448l.m9964a(enumC6455q, this.collectorHost, this.useSsl));
        } catch (Exception e) {
            log.mo10121a("send init info failed", e);
            return null;
        }
    }

    public HarvestResponse sendData(String str) {
        return getResponse(str, EnumC6455q.METIRC_DATA);
    }

    public HarvestResponse sendData(String str, EnumC6455q enumC6455q) {
        return getResponse(str, enumC6455q);
    }

    public HarvestResponse sendDataPb(String str, int i, String str2, String str3) {
        HarvestSoc harvestSoc = this.harvestSoc;
        return HarvestSoc.sendDataInfo(str, i, str2, str3);
    }

    public void useSsl(boolean z) {
        this.useSsl = z;
    }

    public String getApplicationToken() {
        return this.applicationToken;
    }

    public void setApplicationToken(String str) {
        this.applicationToken = str;
    }

    public void setCollectorHost(String str) {
        this.collectorHost = str;
    }

    public void setConnectInformation(ConnectInformation connectInformation) {
        this.connectInformation = connectInformation;
    }

    public void setSoDisable(boolean z) {
        isSoDisable = z;
    }

    public static boolean isSoDisable() {
        return isSoDisable;
    }

    public ConnectInformation getConnectInformation() {
        return this.connectInformation;
    }
}
