package com.gmrz.android.client.commlib;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ICommunicationClientResponse {
    void onError(String str);

    void onRemoveRequest();

    void onResponse(String str, Object obj);
}
