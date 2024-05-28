package com.unionpay.tsmservice;

import android.content.Context;
import com.unionpay.tsmservice.request.AcquireSEAppListRequestParams;
import com.unionpay.tsmservice.request.ActivateVendorPayRequestParams;
import com.unionpay.tsmservice.request.AddCardToVendorPayRequestParams;
import com.unionpay.tsmservice.request.AppDataUpdateRequestParams;
import com.unionpay.tsmservice.request.AppDeleteRequestParams;
import com.unionpay.tsmservice.request.AppDownloadApplyRequestParams;
import com.unionpay.tsmservice.request.AppDownloadRequestParams;
import com.unionpay.tsmservice.request.AppLockRequestParams;
import com.unionpay.tsmservice.request.AppUnlockRequestParams;
import com.unionpay.tsmservice.request.CardListStatusChangedRequestParams;
import com.unionpay.tsmservice.request.CheckSSamsungPayRequestParams;
import com.unionpay.tsmservice.request.CloseChannelRequestParams;
import com.unionpay.tsmservice.request.ECashTopUpRequestParams;
import com.unionpay.tsmservice.request.EncryptDataRequestParams;
import com.unionpay.tsmservice.request.ExecuteCmdRequestParams;
import com.unionpay.tsmservice.request.GetAccountBalanceRequestParams;
import com.unionpay.tsmservice.request.GetAccountInfoRequestParams;
import com.unionpay.tsmservice.request.GetAppDetailRequestParams;
import com.unionpay.tsmservice.request.GetAppListRequestParams;
import com.unionpay.tsmservice.request.GetAppStatusRequestParams;
import com.unionpay.tsmservice.request.GetAssociatedAppRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoBySpayRequestParams;
import com.unionpay.tsmservice.request.GetCardInfoRequestParams;
import com.unionpay.tsmservice.request.GetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.GetEncryptDataRequestParams;
import com.unionpay.tsmservice.request.GetMessageDetailsRequestParams;
import com.unionpay.tsmservice.request.GetSMSAuthCodeRequestParams;
import com.unionpay.tsmservice.request.GetSeAppListRequestParams;
import com.unionpay.tsmservice.request.GetSeIdRequestParams;
import com.unionpay.tsmservice.request.GetTransElementsRequestParams;
import com.unionpay.tsmservice.request.GetTransRecordRequestParams;
import com.unionpay.tsmservice.request.GetTransactionDetailsRequestParams;
import com.unionpay.tsmservice.request.GetVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.HideAppApplyRequestParams;
import com.unionpay.tsmservice.request.InitRequestParams;
import com.unionpay.tsmservice.request.OnlinePaymentVerifyRequestParams;
import com.unionpay.tsmservice.request.OpenChannelRequestParams;
import com.unionpay.tsmservice.request.PreDownloadRequestParams;
import com.unionpay.tsmservice.request.QueryVendorPayStatusRequestParams;
import com.unionpay.tsmservice.request.RequestParams;
import com.unionpay.tsmservice.request.SafetyKeyboardRequestParams;
import com.unionpay.tsmservice.request.SendApduRequestParams;
import com.unionpay.tsmservice.request.SendCustomDataRequestParams;
import com.unionpay.tsmservice.request.SetDefaultCardRequestParams;
import com.unionpay.tsmservice.request.SetSamsungDefWalletRequestParams;
import com.unionpay.tsmservice.request.UniteRequestParams;
import com.unionpay.tsmservice.utils.IUPJniInterface;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SessionKeyReExchange {

    /* renamed from: a */
    private UPTsmAddon f20707a;

    /* renamed from: b */
    private int f20708b;

    /* renamed from: c */
    private RequestParams f20709c;

    /* renamed from: d */
    private ITsmCallback f20710d;

    /* renamed from: e */
    private ITsmProgressCallback f20711e;

    /* renamed from: f */
    private int f20712f;

    /* renamed from: g */
    private OnSafetyKeyboardCallback f20713g;

    /* renamed from: h */
    private Context f20714h;

    /* renamed from: i */
    private int f20715i;

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i, null, iTsmCallback);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback) {
        this(uPTsmAddon, i, requestParams, iTsmCallback, null);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback) {
        this(uPTsmAddon, i, requestParams, iTsmCallback, iTsmProgressCallback, 1000);
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, RequestParams requestParams, ITsmCallback iTsmCallback, ITsmProgressCallback iTsmProgressCallback, int i2) {
        this.f20708b = -1;
        this.f20712f = 1000;
        this.f20707a = uPTsmAddon;
        this.f20708b = i;
        this.f20709c = requestParams;
        this.f20710d = iTsmCallback;
        this.f20711e = iTsmProgressCallback;
        this.f20712f = i2;
    }

    public SessionKeyReExchange(UPTsmAddon uPTsmAddon, int i, SafetyKeyboardRequestParams safetyKeyboardRequestParams, int i2, OnSafetyKeyboardCallback onSafetyKeyboardCallback, Context context) {
        this.f20708b = -1;
        this.f20712f = 1000;
        this.f20707a = uPTsmAddon;
        this.f20708b = i;
        this.f20715i = i2;
        this.f20709c = safetyKeyboardRequestParams;
        this.f20713g = onSafetyKeyboardCallback;
        this.f20714h = context;
    }

    public int reExchangeKey() {
        String[] strArr = new String[1];
        int pubKey = this.f20707a.getPubKey(1000, strArr);
        if (pubKey != 0) {
            return pubKey;
        }
        int exchangeKey = this.f20707a.exchangeKey(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (exchangeKey != 0) {
            return exchangeKey;
        }
        String dMG = IUPJniInterface.dMG(strArr[0], this.f20707a.getCryptType());
        IUPJniInterface.sSK(dMG);
        Context context = this.f20707a.getContext();
        if (context != null) {
            IUPJniInterface.uSKT(context.getPackageName(), dMG);
        }
        int i = this.f20708b;
        if (i == 1000) {
            return this.f20707a.showSafetyKeyboard((SafetyKeyboardRequestParams) this.f20709c, this.f20715i, this.f20713g, this.f20714h);
        }
        switch (i) {
            case 0:
                return this.f20707a.init((InitRequestParams) this.f20709c, this.f20710d);
            case 1:
                return this.f20707a.getAssociatedApp((GetAssociatedAppRequestParams) this.f20709c, this.f20710d);
            case 2:
                return this.f20707a.getAppList((GetAppListRequestParams) this.f20709c, this.f20710d);
            case 3:
                return this.f20707a.getSEAppList((GetSeAppListRequestParams) this.f20709c, this.f20710d);
            case 4:
                return this.f20707a.getAppDetail((GetAppDetailRequestParams) this.f20709c, this.f20710d);
            case 5:
                return this.f20707a.getAppStatus((GetAppStatusRequestParams) this.f20709c, this.f20710d);
            case 6:
                return this.f20707a.getCardInfo((GetCardInfoRequestParams) this.f20709c, this.f20710d);
            case 7:
                return this.f20707a.getAccountInfo((GetAccountInfoRequestParams) this.f20709c, this.f20710d);
            case 8:
                return this.f20707a.getAccountBalance((GetAccountBalanceRequestParams) this.f20709c, this.f20710d);
            case 9:
                return this.f20707a.getTransElements((GetTransElementsRequestParams) this.f20709c, this.f20710d);
            case 10:
                return this.f20707a.getTransRecord((GetTransRecordRequestParams) this.f20709c, this.f20710d);
            case 11:
                return this.f20707a.getSMSAuthCode((GetSMSAuthCodeRequestParams) this.f20709c, this.f20710d);
            case 12:
                return this.f20707a.getSeId((GetSeIdRequestParams) this.f20709c, this.f20710d);
            case 13:
                return this.f20707a.getDefaultCard((GetDefaultCardRequestParams) this.f20709c, this.f20710d);
            case 14:
                return this.f20707a.setDefaultCard((SetDefaultCardRequestParams) this.f20709c, this.f20710d);
            case 15:
                return this.f20707a.appDownloadApply((AppDownloadApplyRequestParams) this.f20709c, this.f20710d);
            case 16:
                return this.f20707a.appDownload((AppDownloadRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 17:
                return this.f20707a.appDelete((AppDeleteRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 18:
                return this.f20707a.appDataUpdate((AppDataUpdateRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 19:
                return this.f20707a.eCashTopUp((ECashTopUpRequestParams) this.f20709c, this.f20710d);
            case 20:
                return this.f20707a.openChannel((OpenChannelRequestParams) this.f20709c, this.f20710d);
            case 21:
                return this.f20707a.closeChannel((CloseChannelRequestParams) this.f20709c, this.f20710d);
            case 22:
                return this.f20707a.sendApdu((SendApduRequestParams) this.f20709c, this.f20710d);
            case 23:
                return this.f20707a.encryptData((EncryptDataRequestParams) this.f20709c, this.f20710d);
            case 24:
                return this.f20707a.hideAppApply((HideAppApplyRequestParams) this.f20709c, this.f20710d);
            case 25:
                return this.f20707a.executeCmd((ExecuteCmdRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 26:
                return this.f20707a.appLock((AppLockRequestParams) this.f20709c, this.f20710d);
            case 27:
                return this.f20707a.appUnlock((AppUnlockRequestParams) this.f20709c, this.f20710d);
            case 28:
                return this.f20707a.getCardInfoBySamsungPay((GetCardInfoBySpayRequestParams) this.f20709c, this.f20710d);
            case 29:
                return this.f20707a.checkSSamsungPay((CheckSSamsungPayRequestParams) this.f20709c, this.f20710d);
            case 30:
                return this.f20707a.setSamsungDefaultWallet((SetSamsungDefWalletRequestParams) this.f20709c, this.f20710d);
            case 31:
                return this.f20707a.getEncryptData((GetEncryptDataRequestParams) this.f20709c, this.f20710d);
            case 32:
                return this.f20707a.setSafetyKeyboardBitmap((SafetyKeyboardRequestParams) this.f20709c);
            case 33:
                return this.f20707a.clearEncryptData(this.f20715i);
            case 34:
                return this.f20707a.hideKeyboard();
            case 35:
                return this.f20707a.cardListStatusChanged((CardListStatusChangedRequestParams) this.f20709c, this.f20710d);
            case 36:
                return this.f20707a.getVendorPayStatus((GetVendorPayStatusRequestParams) this.f20709c, this.f20710d);
            case 37:
                return this.f20707a.activateVendorPay((ActivateVendorPayRequestParams) this.f20709c, this.f20710d);
            case 38:
                return this.f20707a.addCardToVendorPay((AddCardToVendorPayRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 39:
                return this.f20707a.onlinePaymentVerify((OnlinePaymentVerifyRequestParams) this.f20709c, this.f20710d);
            case 40:
                return this.f20707a.preDownload((PreDownloadRequestParams) this.f20709c, this.f20710d, this.f20711e);
            case 41:
                return this.f20707a.queryVendorPayStatus((QueryVendorPayStatusRequestParams) this.f20709c, this.f20710d);
            case 42:
                return this.f20707a.acquireSEAppList((AcquireSEAppListRequestParams) this.f20709c, this.f20710d);
            case 43:
                return this.f20707a.getTransactionDetails((GetTransactionDetailsRequestParams) this.f20709c, this.f20710d);
            case 44:
                return this.f20707a.getMessageDetails((GetMessageDetailsRequestParams) this.f20709c, this.f20710d);
            case 45:
                return this.f20707a.sendCustomData((SendCustomDataRequestParams) this.f20709c, this.f20710d);
            case 46:
                return this.f20707a.createSSD((UniteRequestParams) this.f20709c, this.f20710d);
            default:
                return 0;
        }
    }
}
