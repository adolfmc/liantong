package com.unionpay.tsmservice.p364mi;

import android.content.Context;
import com.unionpay.tsmservice.p364mi.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.p364mi.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.p364mi.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.p364mi.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.p364mi.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.InitRequestParams;
import com.unionpay.tsmservice.p364mi.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PayResultNotifyRequestParams;
import com.unionpay.tsmservice.p364mi.request.PinRequestRequestParams;
import com.unionpay.tsmservice.p364mi.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.p364mi.request.RequestParams;
import com.unionpay.tsmservice.p364mi.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.p364mi.utils.IUPJniInterface;

/* renamed from: com.unionpay.tsmservice.mi.SessionKeyReExchange */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SessionKeyReExchange {

    /* renamed from: a */
    private UPTsmAddon f20785a;

    /* renamed from: b */
    private int f20786b;

    /* renamed from: c */
    private RequestParams f20787c;

    /* renamed from: d */
    private ITsmCallback f20788d;

    /* renamed from: e */
    private ITsmProgressCallback f20789e;

    /* renamed from: f */
    private int f20790f;

    /* renamed from: g */
    private OnSafetyKeyboardCallback f20791g;

    /* renamed from: h */
    private Context f20792h;

    /* renamed from: i */
    private int f20793i;

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i, null, iTsmCallback);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i, requestParams, iTsmCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback, int i2) {
        this.f20786b = -1;
        this.f20790f = 1000;
        this.f20785a = uPTsmAddon;
        this.f20786b = i;
        this.f20787c = requestParams;
        this.f20788d = iTsmCallback;
        this.f20790f = i2;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        this(uPTsmAddon, i, requestParams, iTsmCallback, iTsmProgressCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback, int i2) {
        this.f20786b = -1;
        this.f20790f = 1000;
        this.f20785a = uPTsmAddon;
        this.f20786b = i;
        this.f20787c = requestParams;
        this.f20788d = iTsmCallback;
        this.f20789e = iTsmProgressCallback;
        this.f20790f = i2;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i2, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        this.f20786b = -1;
        this.f20790f = 1000;
        this.f20785a = uPTsmAddon;
        this.f20786b = i;
        this.f20793i = i2;
        this.f20787c = safetyKeyboardRequestParams;
        this.f20791g = onSafetyKeyboardCallback;
        this.f20792h = context;
    }

    public int reExchangeKey() {
        String[] strArr = new String[1];
        int pubKey = this.f20785a.getPubKey(1000, strArr);
        if (pubKey != 0) {
            return pubKey;
        }
        int exchangeKey = this.f20785a.exchangeKey(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (exchangeKey != 0) {
            return exchangeKey;
        }
        String dMG = IUPJniInterface.dMG(strArr[0]);
        IUPJniInterface.sSK(dMG);
        Context context = this.f20785a.getContext();
        if (context != null) {
            IUPJniInterface.uSKT(context.getPackageName(), dMG);
        }
        int i = this.f20786b;
        if (i == 1000) {
            return this.f20785a.showSafetyKeyboard((SafetyKeyboardRequestParams) this.f20787c, this.f20793i, this.f20791g, this.f20792h);
        }
        switch (i) {
            case 0:
                return this.f20785a.init((InitRequestParams) this.f20787c, this.f20788d);
            case 1:
                return this.f20785a.encryptData((EncryptDataRequestParams) this.f20787c, this.f20788d);
            case 2:
                return this.f20785a.getEncryptData((GetEncryptDataRequestParams) this.f20787c, this.f20788d);
            case 3:
                return this.f20785a.setSafetyKeyboardBitmap((SafetyKeyboardRequestParams) this.f20787c);
            case 4:
                return this.f20785a.clearEncryptData(this.f20793i);
            case 5:
                return this.f20785a.hideKeyboard();
            case 6:
                return this.f20785a.acquireSEAppList((AcquireSEAppListRequestParams) this.f20787c, this.f20788d);
            case 7:
                return this.f20785a.cardListStatusChanged((CardListStatusChangedRequestParams) this.f20787c, this.f20788d);
            case 8:
                return this.f20785a.queryVendorPayStatus((QueryVendorPayStatusRequestParams) this.f20787c, this.f20788d);
            case 9:
                return this.f20785a.getVendorPayStatus((GetVendorPayStatusRequestParams) this.f20787c, this.f20788d);
            case 10:
                return this.f20785a.onlinePaymentVerify((OnlinePaymentVerifyRequestParams) this.f20787c, this.f20788d);
            case 11:
                return this.f20785a.pinRequest((PinRequestRequestParams) this.f20787c, this.f20788d);
            case 12:
                return this.f20785a.payResultNotify((PayResultNotifyRequestParams) this.f20787c, this.f20788d);
            case 13:
                return this.f20785a.cancelPay();
            case 14:
                return this.f20785a.getVendorPayStatusForBankApp((GetVendorPayStatusRequestParams) this.f20787c, this.f20788d);
            case 15:
                return this.f20785a.getSeId((GetSeIdRequestParams) this.f20787c, this.f20788d);
            case 16:
                return this.f20785a.addCardToVendorPay((AddCardToVendorPayRequestParams) this.f20787c, this.f20788d, this.f20789e);
            case 17:
                return this.f20785a.getTransactionDetails((GetTransactionDetailsRequestParams) this.f20787c, this.f20788d);
            case 18:
                return this.f20785a.getMessageDetails((GetMessageDetailsRequestParams) this.f20787c, this.f20788d);
            default:
                return 0;
        }
    }
}
