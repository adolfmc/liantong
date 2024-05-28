package com.alipay.sdk.app;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.app.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum EnumC1999k {
    SUCCEEDED(9000, "处理成功"),
    FAILED(4000, "系统繁忙，请稍后再试"),
    CANCELED(6001, "用户取消"),
    NETWORK_ERROR(6002, "网络连接异常"),
    PARAMS_ERROR(4001, "参数错误"),
    DOUBLE_REQUEST(5000, "重复请求"),
    PAY_WAITTING(8000, "支付结果确认中");
    

    /* renamed from: h */
    private int f3597h;

    /* renamed from: i */
    private String f3598i;

    EnumC1999k(int i, String str) {
        this.f3597h = i;
        this.f3598i = str;
    }

    /* renamed from: a */
    public void m20906a(int i) {
        this.f3597h = i;
    }

    /* renamed from: a */
    public int m20907a() {
        return this.f3597h;
    }

    /* renamed from: a */
    public void m20905a(String str) {
        this.f3598i = str;
    }

    /* renamed from: b */
    public String m20904b() {
        return this.f3598i;
    }

    /* renamed from: b */
    public static EnumC1999k m20903b(int i) {
        if (i != 4001) {
            if (i != 5000) {
                if (i != 8000) {
                    if (i == 9000) {
                        return SUCCEEDED;
                    }
                    switch (i) {
                        case 6001:
                            return CANCELED;
                        case 6002:
                            return NETWORK_ERROR;
                        default:
                            return FAILED;
                    }
                }
                return PAY_WAITTING;
            }
            return DOUBLE_REQUEST;
        }
        return PARAMS_ERROR;
    }
}
