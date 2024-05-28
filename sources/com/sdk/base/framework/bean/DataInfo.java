package com.sdk.base.framework.bean;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DataInfo extends JSONObject {
    public JSONObject putData(String str, Object obj) {
        try {
            return super.put(str, obj);
        } catch (Exception unused) {
            return this;
        }
    }
}
