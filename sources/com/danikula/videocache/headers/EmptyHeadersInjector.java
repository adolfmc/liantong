package com.danikula.videocache.headers;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EmptyHeadersInjector implements HeaderInjector {
    @Override // com.danikula.videocache.headers.HeaderInjector
    public Map<String, String> addHeaders(String str) {
        return new HashMap();
    }
}
