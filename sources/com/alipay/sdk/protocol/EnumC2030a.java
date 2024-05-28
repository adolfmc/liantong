package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.protocol.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum EnumC2030a {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update"),
    OpenWeb("loc:openweb"),
    SetResult("loc:setResult"),
    Exit("loc:exit");
    

    /* renamed from: g */
    private String f3808g;

    EnumC2030a(String str) {
        this.f3808g = str;
    }

    /* renamed from: a */
    public static EnumC2030a m20788a(String str) {
        EnumC2030a[] values;
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        EnumC2030a enumC2030a = None;
        for (EnumC2030a enumC2030a2 : values()) {
            if (str.startsWith(enumC2030a2.f3808g)) {
                return enumC2030a2;
            }
        }
        return enumC2030a;
    }
}
