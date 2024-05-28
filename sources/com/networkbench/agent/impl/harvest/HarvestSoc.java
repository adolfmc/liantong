package com.networkbench.agent.impl.harvest;

import com.networkbench.agent.impl.harvest.p260a.C6450m;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p262i.C6466c;
import com.networkbench.agent.impl.p262i.C6468e;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonObject;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestSoc implements HarvestDataSend {
    private final InterfaceC6393e log = C6394f.m10150a();

    public void setSocketInfo(HarvestResponse harvestResponse) {
        if (harvestResponse != null) {
            C6468e.m9913a(harvestResponse.getSoHost());
            C6466c.m9921a(C6450m.m9963a().f16296b);
            C6466c.m9919b(C6450m.m9963a().f16295a);
        }
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestDataSend
    public HarvestResponse sendConnect(ConnectInformation connectInformation) {
        try {
            HarvestResponse harvestResponse = new HarvestResponse();
            if (connectInformation == null) {
                throw new IllegalArgumentException();
            }
            JsonObject asSocketJsonObject = connectInformation.asSocketJsonObject();
            InterfaceC6393e interfaceC6393e = this.log;
            interfaceC6393e.mo10122a("init mobile agent  :" + asSocketJsonObject.toString());
            byte[] sendData = sendData(asSocketJsonObject.toString().getBytes("UTF-8"), 2, C6653u.m8697h(), "sign=");
            if (sendData != null) {
                int m9902c = C6468e.m9902c(sendData);
                InterfaceC6393e interfaceC6393e2 = this.log;
                interfaceC6393e2.mo10122a("init mobile agent response code :" + m9902c);
                harvestResponse.setStatusCode(m9902c);
                if (m9902c == 0) {
                    harvestResponse.setStatus("success");
                    JSONObject m9899d = C6468e.m9899d(sendData);
                    if (m9899d != null) {
                        HarvestConfiguration harvestConfiguration = new HarvestConfiguration();
                        harvestConfiguration.reconfigurePb(m9899d);
                        harvestResponse.setConfiguration(harvestConfiguration);
                    }
                } else {
                    harvestResponse.setErrorCode(m9902c);
                    harvestResponse.setStatus("error");
                }
            }
            return harvestResponse;
        } catch (Throwable th) {
            C6396h.m10131k("HarvestSoc sendConnect has  an error  : " + th);
            return null;
        }
    }

    public static HarvestResponse sendDataInfo(String str, int i, String str2, String str3) {
        HarvestResponse harvestResponse = new HarvestResponse();
        if (str == null) {
            return harvestResponse;
        }
        try {
            byte[] sendData = sendData(str.getBytes("UTF-8"), i, str2, str3);
            if (sendData != null) {
                int m9902c = C6468e.m9902c(sendData);
                harvestResponse.setStatusCode(m9902c);
                if (m9902c == 0) {
                    harvestResponse.setStatus("success");
                } else {
                    harvestResponse.setErrorCode(m9902c);
                    harvestResponse.setStatus("error");
                }
            }
        } catch (Exception e) {
            C6396h.m10134h("sendDataInfo error" + e.getMessage());
        }
        return harvestResponse;
    }

    public static byte[] sendData(byte[] bArr, int i, String str, String str2) {
        return C6468e.m9910a(bArr, i, str, str2);
    }
}
