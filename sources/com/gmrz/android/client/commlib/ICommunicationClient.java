package com.gmrz.android.client.commlib;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ICommunicationClient {
    String[] getServiceModuleList(String str);

    void postRequest(String str, String str2, Object obj);

    void removeRequest(long j);

    long sendRequest(String str, String str2, Object obj, ICommunicationClientResponse iCommunicationClientResponse);

    void waitForResponse(long j);
}
