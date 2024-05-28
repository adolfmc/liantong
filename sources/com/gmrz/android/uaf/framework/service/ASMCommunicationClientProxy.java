package com.gmrz.android.uaf.framework.service;

import com.gmrz.android.client.commlib.ICommunicationClient;
import com.gmrz.android.client.commlib.ICommunicationClientResponse;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ASMCommunicationClientProxy implements ICommunicationClient {
    private static final String TAG = "ASMCommunicationClientProxy";
    private final Map<String, ICommunicationClient> mCommunicationClientMap;
    private Boolean mUseService = Boolean.FALSE;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ASMDestinationType {
        public static final String ASM_INTENT = "INTENT";
        public static final String ASM_LOCAL = "LOCAL";
        public static final String ASM_SERVICE = "SERVICE";
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void postRequest(String str, String str2, Object obj) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void removeRequest(long j) {
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public void waitForResponse(long j) {
    }

    public void setUseService(Boolean bool) {
        this.mUseService = bool;
    }

    public ASMCommunicationClientProxy(Map<String, ICommunicationClient> map) {
        this.mCommunicationClientMap = map;
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public long sendRequest(String str, String str2, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        String[] split = str.split("/");
        return this.mCommunicationClientMap.get(split[0]).sendRequest(split[1], "", obj, iCommunicationClientResponse);
    }

    @Override // com.gmrz.android.client.commlib.ICommunicationClient
    public String[] getServiceModuleList(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, ICommunicationClient>> it = this.mCommunicationClientMap.entrySet().iterator();
        while (true) {
            if (it.hasNext()) {
                try {
                    Map.Entry<String, ICommunicationClient> next = it.next();
                    String key = next.getKey();
                    ICommunicationClient value = next.getValue();
                    if (!key.equals("INTENT") || !this.mUseService.booleanValue()) {
                        String[] serviceModuleList = value.getServiceModuleList("ASM");
                        if (serviceModuleList != null) {
                            for (String str2 : serviceModuleList) {
                                arrayList.add(key + "/" + str2);
                            }
                        }
                    }
                } catch (Exception e) {
                    Logger.m15891e(TAG, "getServiceModuleList error ", e);
                }
            } else {
                return (String[]) arrayList.toArray(new String[0]);
            }
        }
    }
}
