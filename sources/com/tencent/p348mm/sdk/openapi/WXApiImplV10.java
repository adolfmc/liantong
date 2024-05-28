package com.tencent.p348mm.sdk.openapi;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.p348mm.sdk.modelbase.BaseReq;
import com.tencent.p348mm.sdk.modelbase.BaseResp;
import com.tencent.p348mm.sdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.p348mm.sdk.modelbiz.CreateChatroom;
import com.tencent.p348mm.sdk.modelbiz.JoinChatroom;
import com.tencent.p348mm.sdk.modelmsg.GetMessageFromWX;
import com.tencent.p348mm.sdk.modelmsg.LaunchFromWX;
import com.tencent.p348mm.sdk.modelmsg.SendAuth;
import com.tencent.p348mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.p348mm.sdk.modelmsg.ShowMessageFromWX;
import com.tencent.p348mm.sdk.modelpay.PayResp;
import com.tencent.p348mm.sdk.p352a.C10387a;
import com.tencent.p348mm.sdk.p352a.p353a.C10389a;
import com.tencent.p348mm.sdk.p352a.p353a.C10391b;
import com.tencent.p348mm.sdk.p354b.C10393b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.openapi.WXApiImplV10 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class WXApiImplV10 implements IWXAPI {
    private static final String TAG = "MicroMsg.SDK.WXApiImplV10";
    private static String wxappPayEntryClassname;
    private String appId;
    private boolean checkSignature;
    private Context context;
    private boolean detached = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WXApiImplV10(Context context, String str, boolean z) {
        this.checkSignature = false;
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "<init>, appId = " + str + ", checkSignature = " + z);
        this.context = context;
        this.appId = str;
        this.checkSignature = z;
    }

    private boolean checkSumConsistent(byte[] bArr, byte[] bArr2) {
        String str;
        String str2;
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "checkSumConsistent fail, invalid arguments";
        } else if (bArr.length == bArr2.length) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        } else {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "checkSumConsistent fail, length is different";
        }
        C10393b.m6195a(str, str2);
        return false;
    }

    private boolean createChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_create_chatroom_group_id"), bundle.getString("_wxapi_create_chatroom_chatroom_name"), bundle.getString("_wxapi_create_chatroom_chatroom_nickname"), bundle.getString("_wxapi_create_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean joinChatroom(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_join_chatroom_group_id"), bundle.getString("_wxapi_join_chatroom_chatroom_nickname"), bundle.getString("_wxapi_join_chatroom_ext_msg")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendAddCardToWX(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[]{this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizProfileReq(Context context, Bundle bundle) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_profile_req_scene"));
        StringBuilder sb2 = new StringBuilder();
        sb2.append(bundle.getInt("_wxapi_jump_to_biz_profile_req_profile_type"));
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg"), sb.toString(), sb2.toString()}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizTempSessionReq(Context context, Bundle bundle) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizTempSession");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_webview_req_show_type"));
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_session_from"), sb.toString()}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToBizWebviewReq(Context context, Bundle bundle) {
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToBizProfile");
        StringBuilder sb = new StringBuilder();
        sb.append(bundle.getInt("_wxapi_jump_to_biz_webview_req_scene"));
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_biz_webview_req_to_user_name"), bundle.getString("_wxapi_jump_to_biz_webview_req_ext_msg"), sb.toString()}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), null, null, new String[]{this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), null, null, new String[0], null);
        if (query != null) {
            query.close();
            return true;
        }
        return true;
    }

    private boolean sendOpenWebview(Context context, Bundle bundle) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_webview_url")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayReq(Context context, Bundle bundle) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context).getString("_wxapp_pay_entry_classname_", null);
            C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        C10387a.C10388a c10388a = new C10387a.C10388a();
        c10388a.f19996e = bundle;
        c10388a.f19993b = "com.tencent.mm";
        c10388a.f19994c = wxappPayEntryClassname;
        return C10387a.m6201a(context, c10388a);
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final void detach() {
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "detach");
        this.detached = true;
        this.context = null;
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
        if (isWXAppInstalled()) {
            return new MMSharedPreferences(this.context).getInt("_build_info_sdk_int_", 0);
        }
        C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
        return 0;
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        if (!WXApiImplComm.isIntentFromWx(intent, "com.tencent.mm.openapi.token")) {
            C10393b.m6192b("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, intent not from weixin msg");
            return false;
        } else if (this.detached) {
            throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
        } else {
            String stringExtra = intent.getStringExtra("_mmessage_content");
            int intExtra = intent.getIntExtra("_mmessage_sdkVersion", 0);
            String stringExtra2 = intent.getStringExtra("_mmessage_appPackage");
            if (stringExtra2 == null || stringExtra2.length() == 0) {
                C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "invalid argument");
                return false;
            } else if (!checkSumConsistent(intent.getByteArrayExtra("_mmessage_checksum"), C10391b.m6199a(stringExtra, intExtra, stringExtra2))) {
                C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "checksum fail");
                return false;
            } else {
                int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
                if (intExtra2 == 9) {
                    iWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(intent.getExtras()));
                    return true;
                }
                switch (intExtra2) {
                    case 1:
                        iWXAPIEventHandler.onResp(new SendAuth.Resp(intent.getExtras()));
                        return true;
                    case 2:
                        iWXAPIEventHandler.onResp(new SendMessageToWX.Resp(intent.getExtras()));
                        return true;
                    case 3:
                        iWXAPIEventHandler.onReq(new GetMessageFromWX.Req(intent.getExtras()));
                        return true;
                    case 4:
                        iWXAPIEventHandler.onReq(new ShowMessageFromWX.Req(intent.getExtras()));
                        return true;
                    case 5:
                        iWXAPIEventHandler.onResp(new PayResp(intent.getExtras()));
                        return true;
                    case 6:
                        iWXAPIEventHandler.onReq(new LaunchFromWX.Req(intent.getExtras()));
                        return true;
                    default:
                        switch (intExtra2) {
                            case 14:
                                iWXAPIEventHandler.onResp(new CreateChatroom.Resp(intent.getExtras()));
                                return true;
                            case 15:
                                iWXAPIEventHandler.onResp(new JoinChatroom.Resp(intent.getExtras()));
                                return true;
                            default:
                                C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "unknown cmd = " + intExtra2);
                                return false;
                        }
                }
            }
        }
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean isWXAppInstalled() {
        if (this.detached) {
            throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
            if (packageInfo == null) {
                return false;
            }
            return WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean isWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("isWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
        return getWXAppSupportAPI() >= 570490883;
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean openWXApp() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        }
        if (!isWXAppInstalled()) {
            C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return false;
        }
        try {
            this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
            return true;
        } catch (Exception e) {
            C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "startActivity fail, exception = " + e.getMessage());
            return false;
        }
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean registerApp(String str) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        }
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
        if (str != null) {
            this.appId = str;
        }
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "register app " + this.context.getPackageName());
        C10389a.C10390a c10390a = new C10389a.C10390a();
        c10390a.f19999f = "com.tencent.mm";
        c10390a.f20000g = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
        c10390a.f19997d = "weixin://registerapp?appid=" + this.appId;
        return C10389a.m6200a(this.context, c10390a);
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean sendReq(BaseReq baseReq) {
        String str;
        String str2;
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "sendReq failed for wechat app signature check failed";
        } else if (baseReq.checkArgs()) {
            C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "sendReq, req type = " + baseReq.getType());
            Bundle bundle = new Bundle();
            baseReq.toBundle(bundle);
            if (baseReq.getType() == 5) {
                return sendPayReq(this.context, bundle);
            }
            if (baseReq.getType() == 7) {
                return sendJumpToBizProfileReq(this.context, bundle);
            }
            if (baseReq.getType() == 8) {
                return sendJumpToBizWebviewReq(this.context, bundle);
            }
            if (baseReq.getType() == 10) {
                return sendJumpToBizTempSessionReq(this.context, bundle);
            }
            if (baseReq.getType() == 9) {
                return sendAddCardToWX(this.context, bundle);
            }
            if (baseReq.getType() == 11) {
                return sendOpenRankListReq(this.context, bundle);
            }
            if (baseReq.getType() == 12) {
                return sendOpenWebview(this.context, bundle);
            }
            if (baseReq.getType() == 13) {
                return sendOpenBusiLuckyMoney(this.context, bundle);
            }
            if (baseReq.getType() == 14) {
                return createChatroom(this.context, bundle);
            }
            if (baseReq.getType() == 15) {
                return joinChatroom(this.context, bundle);
            }
            C10387a.C10388a c10388a = new C10387a.C10388a();
            c10388a.f19996e = bundle;
            c10388a.f19995d = "weixin://sendreq?appid=" + this.appId;
            c10388a.f19993b = "com.tencent.mm";
            c10388a.f19994c = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            return C10387a.m6201a(this.context, c10388a);
        } else {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "sendReq checkArgs fail";
        }
        C10393b.m6195a(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final boolean sendResp(BaseResp baseResp) {
        String str;
        String str2;
        if (this.detached) {
            throw new IllegalStateException("sendResp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "sendResp failed for wechat app signature check failed";
        } else if (baseResp.checkArgs()) {
            Bundle bundle = new Bundle();
            baseResp.toBundle(bundle);
            C10387a.C10388a c10388a = new C10387a.C10388a();
            c10388a.f19996e = bundle;
            c10388a.f19995d = "weixin://sendresp?appid=" + this.appId;
            c10388a.f19993b = "com.tencent.mm";
            c10388a.f19994c = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            return C10387a.m6201a(this.context, c10388a);
        } else {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "sendResp checkArgs fail";
        }
        C10393b.m6195a(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.sdk.openapi.IWXAPI
    public final void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
            return;
        }
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "unregisterApp, appId = " + this.appId);
        String str = this.appId;
        if (str == null || str.length() == 0) {
            C10393b.m6195a("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
            return;
        }
        C10393b.m6191c("MicroMsg.SDK.WXApiImplV10", "unregister app " + this.context.getPackageName());
        C10389a.C10390a c10390a = new C10389a.C10390a();
        c10390a.f19999f = "com.tencent.mm";
        c10390a.f20000g = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_UNREGISTER";
        c10390a.f19997d = "weixin://unregisterapp?appid=" + this.appId;
        C10389a.m6200a(this.context, c10390a);
    }
}
