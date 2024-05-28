package com.gmrz.android.uaf.framework.service;

import android.content.Context;
import com.fido.android.framework.service.Mfac;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.IUafAsmBinder;
import com.gmrz.android.client.commlib.ICommunicationClient;
import com.gmrz.android.client.commlib.ICommunicationClientResponse;
import com.gmrz.android.client.mfac.ASMCommunicationClientRequest;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UAFLocalASMCommunicationClient implements ICommunicationClient {
    private static final String TAG = "UAFLocalASMCommunicationClient";
    private final String[] authClassNames = {"com.android.client.asm.api.uaf.UafLocalAsmAgent"};
    private Map<String, IUafAsmBinder> embeddedAuthenticatorsMap;

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void postRequest(String str, String str2, Object obj) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void removeRequest(long j) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void waitForResponse(long j) {
    }

    public UAFLocalASMCommunicationClient() {
        this.embeddedAuthenticatorsMap = null;
        this.embeddedAuthenticatorsMap = new ConcurrentHashMap();
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public long sendRequest(String str, String str2, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        ASMCommunicationClientRequest aSMCommunicationClientRequest = (ASMCommunicationClientRequest) obj;
        String str3 = "";
        try {
            str3 = this.embeddedAuthenticatorsMap.get(str).getAsmApi().process(aSMCommunicationClientRequest.sRequest, aSMCommunicationClientRequest.callerActivityContext != null ? aSMCommunicationClientRequest.callerActivityContext : null);
        } catch (AsmException e) {
            Logger.m15891e(TAG, "Error while sending request", e);
        }
        iCommunicationClientResponse.onResponse(null, str3);
        return 0L;
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public String[] getServiceModuleList(String str) {
        ArrayList arrayList = new ArrayList();
        Context applicationContext = Mfac.Instance().getContext().getApplicationContext();
        for (String str2 : this.authClassNames) {
            try {
                IUafAsmBinder iUafAsmBinder = (IUafAsmBinder) Class.forName(str2).asSubclass(IUafAsmBinder.class).getMethod("instance", Context.class).invoke(null, applicationContext);
                String str3 = iUafAsmBinder.getComponentName().getPackageName() + "," + iUafAsmBinder.getComponentName().getClassName();
                arrayList.add(str3);
                this.embeddedAuthenticatorsMap.put(str3, iUafAsmBinder);
            } catch (Exception e) {
                Logger.m15891e(TAG, "Error while getting service module list", e);
            }
        }
        Logger.m15889i(TAG, "UAF Embedded ASM list: " + arrayList);
        return (String[]) arrayList.toArray(new String[0]);
    }
}
