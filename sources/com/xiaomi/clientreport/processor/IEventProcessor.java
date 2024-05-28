package com.xiaomi.clientreport.processor;

import com.xiaomi.clientreport.data.C11052a;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IEventProcessor extends InterfaceC11064c, InterfaceC11065d {
    String bytesToString(byte[] bArr);

    void setEventMap(HashMap<String, ArrayList<C11052a>> hashMap);

    byte[] stringToBytes(String str);
}
