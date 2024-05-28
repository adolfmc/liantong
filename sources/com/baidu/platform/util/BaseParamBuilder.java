package com.baidu.platform.util;

import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.util.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseParamBuilder implements ParamBuilder<BaseParamBuilder> {

    /* renamed from: a */
    protected Map<String, String> f8160a;

    /* renamed from: a */
    public BaseParamBuilder m17443a(String str, String str2) {
        if (this.f8160a == null) {
            this.f8160a = new LinkedHashMap();
        }
        this.f8160a.put(str, str2);
        return this;
    }

    /* renamed from: a */
    public String m17444a() {
        Map<String, String> map = this.f8160a;
        if (map == null || map.isEmpty()) {
            return null;
        }
        String str = new String();
        int i = 0;
        for (String str2 : this.f8160a.keySet()) {
            String encodeUrlParamsValue = AppMD5.encodeUrlParamsValue(this.f8160a.get(str2));
            str = i == 0 ? str + str2 + "=" + encodeUrlParamsValue : str + "&" + str2 + "=" + encodeUrlParamsValue;
            i++;
        }
        return str;
    }
}
