package com.tencent.p348mm.opensdk.modelpay;

import android.os.Bundle;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.utils.C10384Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelpay.PayReq */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PayReq extends BaseReq {
    private static final int EXTDATA_MAX_LENGTH = 1024;
    private static final String TAG = "MicroMsg.PaySdk.PayReq";
    public String appId;
    public String extData;
    public String nonceStr;
    public Options options;
    public String packageValue;
    public String partnerId;
    public String prepayId;
    public String sign;
    public String signType;
    public String timeStamp;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.modelpay.PayReq$Options */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Options {
        public static final int INVALID_FLAGS = -1;
        public String callbackClassName;
        public int callbackFlags = -1;

        public void fromBundle(Bundle bundle) {
            this.callbackClassName = C10372a.m6226a(bundle, "_wxapi_payoptions_callback_classname");
            this.callbackFlags = C10372a.m6225a(bundle, "_wxapi_payoptions_callback_flags", -1);
        }

        public void toBundle(Bundle bundle) {
            bundle.putString("_wxapi_payoptions_callback_classname", this.callbackClassName);
            bundle.putInt("_wxapi_payoptions_callback_flags", this.callbackFlags);
        }
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
    public boolean checkArgs() {
        String str;
        String str2;
        String str3 = this.appId;
        if (str3 == null || str3.length() == 0) {
            str = "MicroMsg.PaySdk.PayReq";
            str2 = "checkArgs fail, invalid appId";
        } else {
            String str4 = this.partnerId;
            if (str4 == null || str4.length() == 0) {
                str = "MicroMsg.PaySdk.PayReq";
                str2 = "checkArgs fail, invalid partnerId";
            } else {
                String str5 = this.prepayId;
                if (str5 == null || str5.length() == 0) {
                    str = "MicroMsg.PaySdk.PayReq";
                    str2 = "checkArgs fail, invalid prepayId";
                } else {
                    String str6 = this.nonceStr;
                    if (str6 == null || str6.length() == 0) {
                        str = "MicroMsg.PaySdk.PayReq";
                        str2 = "checkArgs fail, invalid nonceStr";
                    } else {
                        String str7 = this.timeStamp;
                        if (str7 == null || str7.length() == 0) {
                            str = "MicroMsg.PaySdk.PayReq";
                            str2 = "checkArgs fail, invalid timeStamp";
                        } else {
                            String str8 = this.packageValue;
                            if (str8 == null || str8.length() == 0) {
                                str = "MicroMsg.PaySdk.PayReq";
                                str2 = "checkArgs fail, invalid packageValue";
                            } else {
                                String str9 = this.sign;
                                if (str9 == null || str9.length() == 0) {
                                    str = "MicroMsg.PaySdk.PayReq";
                                    str2 = "checkArgs fail, invalid sign";
                                } else {
                                    String str10 = this.extData;
                                    if (str10 == null || str10.length() <= 1024) {
                                        return true;
                                    }
                                    str = "MicroMsg.PaySdk.PayReq";
                                    str2 = "checkArgs fail, extData length too long";
                                }
                            }
                        }
                    }
                }
            }
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
    public void fromBundle(Bundle bundle) {
        super.fromBundle(bundle);
        this.appId = C10372a.m6226a(bundle, "_wxapi_payreq_appid");
        this.partnerId = C10372a.m6226a(bundle, "_wxapi_payreq_partnerid");
        this.prepayId = C10372a.m6226a(bundle, "_wxapi_payreq_prepayid");
        this.nonceStr = C10372a.m6226a(bundle, "_wxapi_payreq_noncestr");
        this.timeStamp = C10372a.m6226a(bundle, "_wxapi_payreq_timestamp");
        this.packageValue = C10372a.m6226a(bundle, "_wxapi_payreq_packagevalue");
        this.sign = C10372a.m6226a(bundle, "_wxapi_payreq_sign");
        this.extData = C10372a.m6226a(bundle, "_wxapi_payreq_extdata");
        this.signType = C10372a.m6226a(bundle, "_wxapi_payreq_sign_type");
        Options options = new Options();
        this.options = options;
        options.fromBundle(bundle);
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
    public int getType() {
        return 5;
    }

    @Override // com.tencent.p348mm.opensdk.modelbase.BaseReq
    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putString("_wxapi_payreq_appid", this.appId);
        bundle.putString("_wxapi_payreq_partnerid", this.partnerId);
        bundle.putString("_wxapi_payreq_prepayid", this.prepayId);
        bundle.putString("_wxapi_payreq_noncestr", this.nonceStr);
        bundle.putString("_wxapi_payreq_timestamp", this.timeStamp);
        bundle.putString("_wxapi_payreq_packagevalue", this.packageValue);
        bundle.putString("_wxapi_payreq_sign", this.sign);
        bundle.putString("_wxapi_payreq_extdata", this.extData);
        bundle.putString("_wxapi_payreq_sign_type", this.signType);
        Options options = this.options;
        if (options != null) {
            options.toBundle(bundle);
        }
    }
}
