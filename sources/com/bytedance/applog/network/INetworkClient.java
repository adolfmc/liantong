package com.bytedance.applog.network;

import android.util.Pair;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface INetworkClient {
    String get(String str, Map<String, String> map);

    String post(String str, List<Pair<String, String>> list);

    String post(String str, byte[] bArr, String str2);

    String post(String str, byte[] bArr, Map<String, String> map);

    byte[] postStream(String str, byte[] bArr, Map<String, String> map);
}
