package com.loopj.android.http;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RequestParams {
    private Map<String, String> params = new HashMap();

    public Map<String, String> getRealParams() {
        return this.params;
    }

    public void put(String str, String str2) {
        this.params.put(str, str2);
    }

    public String get(String str) {
        return this.params.get(str);
    }

    public String toString() {
        String str = "";
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            str = str + entry.getKey() + "=" + entry.getValue() + ";";
        }
        return str;
    }
}
