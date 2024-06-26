package com.tencent.p348mm.opensdk.openapi;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.tencent.p348mm.opensdk.channel.MMessageActV2;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.modelbiz.AddCardToWXCardPackage;
import com.tencent.p348mm.opensdk.modelbiz.ChooseCardFromWXCardPackage;
import com.tencent.p348mm.opensdk.modelbiz.CreateChatroom;
import com.tencent.p348mm.opensdk.modelbiz.HandleScanResult;
import com.tencent.p348mm.opensdk.modelbiz.IWXChannelJumpInfo;
import com.tencent.p348mm.opensdk.modelbiz.JoinChatroom;
import com.tencent.p348mm.opensdk.modelbiz.OpenWebview;
import com.tencent.p348mm.opensdk.modelbiz.SubscribeMessage;
import com.tencent.p348mm.opensdk.modelbiz.SubscribeMiniProgramMsg;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelBaseJumpInfo;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelJumpMiniProgramInfo;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelJumpUrlInfo;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelOpenFeed;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelOpenLive;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelOpenProfile;
import com.tencent.p348mm.opensdk.modelbiz.WXChannelShareVideo;
import com.tencent.p348mm.opensdk.modelbiz.WXInvoiceAuthInsert;
import com.tencent.p348mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.p348mm.opensdk.modelbiz.WXLaunchMiniProgramWithToken;
import com.tencent.p348mm.opensdk.modelbiz.WXLaunchWxaRedirectingPage;
import com.tencent.p348mm.opensdk.modelbiz.WXNontaxPay;
import com.tencent.p348mm.opensdk.modelbiz.WXOpenBusinessView;
import com.tencent.p348mm.opensdk.modelbiz.WXOpenBusinessWebview;
import com.tencent.p348mm.opensdk.modelbiz.WXOpenCustomerServiceChat;
import com.tencent.p348mm.opensdk.modelbiz.WXPayInsurance;
import com.tencent.p348mm.opensdk.modelbiz.WXPreloadMiniProgram;
import com.tencent.p348mm.opensdk.modelbiz.WXPreloadMiniProgramEnvironment;
import com.tencent.p348mm.opensdk.modelbiz.WXQRCodePay;
import com.tencent.p348mm.opensdk.modelmsg.GetMessageFromWX;
import com.tencent.p348mm.opensdk.modelmsg.LaunchFromWX;
import com.tencent.p348mm.opensdk.modelmsg.SendAuth;
import com.tencent.p348mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.p348mm.opensdk.modelmsg.SendTdiAuth;
import com.tencent.p348mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.p348mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.p348mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.p348mm.opensdk.modelmsg.WXStateSceneDataObject;
import com.tencent.p348mm.opensdk.modelmsg.WXTextObject;
import com.tencent.p348mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.p348mm.opensdk.modelpay.JumpToOfflinePay;
import com.tencent.p348mm.opensdk.modelpay.PayResp;
import com.tencent.p348mm.opensdk.modelpay.WXJointPay;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;
import com.tencent.p348mm.opensdk.utils.ILog;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.tencent.mm.opensdk.openapi.BaseWXApiImplV10 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class BaseWXApiImplV10 implements IWXAPI {
    protected static final String TAG = "MicroMsg.SDK.WXApiImplV10";
    private static String wxappPayEntryClassname;
    protected String appId;
    protected boolean checkSignature;
    protected Context context;
    protected boolean detached = false;
    private int launchMode;
    private int wxSdkVersion;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseWXApiImplV10(Context context, String str, boolean z, int i) {
        this.checkSignature = false;
        this.launchMode = 2;
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "<init>, appId = " + str + ", checkSignature = " + z + ", launchMode = " + i);
        this.context = context;
        this.appId = str;
        this.checkSignature = z;
        this.launchMode = i;
        C10386b.f19988a = context.getApplicationContext();
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
        C10384Log.m6210e(str, str2);
        return false;
    }

    private boolean createChatroom(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/createChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_create_chatroom_group_id", ""), bundle.getString("_wxapi_create_chatroom_chatroom_name", ""), bundle.getString("_wxapi_create_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_create_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private String finderShareVideoJumpInfoToString(IWXChannelJumpInfo iWXChannelJumpInfo) {
        String str;
        String str2;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("jumpType", iWXChannelJumpInfo.type());
            if (iWXChannelJumpInfo instanceof WXChannelBaseJumpInfo) {
                jSONObject.put("wording", ((WXChannelBaseJumpInfo) iWXChannelJumpInfo).wording);
                jSONObject.put("extra", ((WXChannelBaseJumpInfo) iWXChannelJumpInfo).extra);
                if (iWXChannelJumpInfo instanceof WXChannelJumpMiniProgramInfo) {
                    jSONObject.put("username", ((WXChannelJumpMiniProgramInfo) iWXChannelJumpInfo).username);
                    str = "path";
                    str2 = ((WXChannelJumpMiniProgramInfo) iWXChannelJumpInfo).path;
                } else if (iWXChannelJumpInfo instanceof WXChannelJumpUrlInfo) {
                    str = "url";
                    str2 = ((WXChannelJumpUrlInfo) iWXChannelJumpInfo).url;
                }
                jSONObject.put(str, str2);
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception unused) {
            return "";
        }
    }

    private String getTokenFromWX(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/genTokenForOpenSdk"), null, null, new String[]{this.appId, "638058496"}, null);
        if (query == null || !query.moveToFirst()) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX , token is null , if your app targetSdkVersion >= 30, include 'com.tencent.mm' in a set of <package> elements inside the <queries> element");
            return null;
        }
        String string = query.getString(0);
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX token is " + string);
        query.close();
        return string;
    }

    private boolean handleWxInternalRespType(String str, IWXAPIEventHandler iWXAPIEventHandler) {
        Uri parse;
        String queryParameter;
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "handleWxInternalRespType, extInfo = " + str);
        try {
            parse = Uri.parse(str);
            queryParameter = parse.getQueryParameter("wx_internal_resptype");
            C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "handleWxInternalRespType, respType = " + queryParameter);
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "handleWxInternalRespType fail, ex = " + e.getMessage());
        }
        if (C10386b.m6202b(queryParameter)) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "handleWxInternalRespType fail, respType is null");
            return false;
        } else if (queryParameter.equals("subscribemessage")) {
            SubscribeMessage.Resp resp = new SubscribeMessage.Resp();
            String queryParameter2 = parse.getQueryParameter("ret");
            if (queryParameter2 != null && queryParameter2.length() > 0) {
                resp.errCode = C10386b.m6203a(queryParameter2, 0);
            }
            resp.openId = parse.getQueryParameter("openid");
            resp.templateID = parse.getQueryParameter("template_id");
            resp.scene = C10386b.m6203a(parse.getQueryParameter("scene"), 0);
            resp.action = parse.getQueryParameter("action");
            resp.reserved = parse.getQueryParameter("reserved");
            iWXAPIEventHandler.onResp(resp);
            return true;
        } else if (queryParameter.contains("invoice_auth_insert")) {
            WXInvoiceAuthInsert.Resp resp2 = new WXInvoiceAuthInsert.Resp();
            String queryParameter3 = parse.getQueryParameter("ret");
            if (queryParameter3 != null && queryParameter3.length() > 0) {
                resp2.errCode = C10386b.m6203a(queryParameter3, 0);
            }
            resp2.wxOrderId = parse.getQueryParameter("wx_order_id");
            iWXAPIEventHandler.onResp(resp2);
            return true;
        } else if (queryParameter.contains("payinsurance")) {
            WXPayInsurance.Resp resp3 = new WXPayInsurance.Resp();
            String queryParameter4 = parse.getQueryParameter("ret");
            if (queryParameter4 != null && queryParameter4.length() > 0) {
                resp3.errCode = C10386b.m6203a(queryParameter4, 0);
            }
            resp3.wxOrderId = parse.getQueryParameter("wx_order_id");
            iWXAPIEventHandler.onResp(resp3);
            return true;
        } else if (queryParameter.contains("nontaxpay")) {
            WXNontaxPay.Resp resp4 = new WXNontaxPay.Resp();
            String queryParameter5 = parse.getQueryParameter("ret");
            if (queryParameter5 != null && queryParameter5.length() > 0) {
                resp4.errCode = C10386b.m6203a(queryParameter5, 0);
            }
            resp4.wxOrderId = parse.getQueryParameter("wx_order_id");
            iWXAPIEventHandler.onResp(resp4);
            return true;
        } else {
            if (!"subscribeminiprogrammsg".equals(queryParameter) && !"5".equals(queryParameter)) {
                C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "this open sdk version not support the request type");
                return false;
            }
            SubscribeMiniProgramMsg.Resp resp5 = new SubscribeMiniProgramMsg.Resp();
            String queryParameter6 = parse.getQueryParameter("ret");
            if (queryParameter6 != null && queryParameter6.length() > 0) {
                resp5.errCode = C10386b.m6203a(queryParameter6, 0);
            }
            resp5.openId = parse.getQueryParameter("openid");
            resp5.unionId = parse.getQueryParameter("unionid");
            resp5.nickname = parse.getQueryParameter("nickname");
            resp5.errStr = parse.getQueryParameter("errmsg");
            iWXAPIEventHandler.onResp(resp5);
            return true;
        }
    }

    private boolean joinChatroom(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/joinChatroom"), null, null, new String[]{this.appId, bundle.getString("_wxapi_basereq_transaction", ""), bundle.getString("_wxapi_join_chatroom_group_id", ""), bundle.getString("_wxapi_join_chatroom_chatroom_nickname", ""), bundle.getString("_wxapi_join_chatroom_ext_msg", ""), bundle.getString("_wxapi_basereq_openid", "")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private void launchWXIfNeed() {
        if (Build.VERSION.SDK_INT < 29 || this.launchMode != 2) {
            openWXApp();
        } else {
            launchWXUsingPendingIntent();
        }
    }

    private void launchWXUsingPendingIntent() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        }
        if (!isWXAppInstalled()) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "openWXApp failed, not installed or signature check failed");
            return;
        }
        try {
            C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "launchWXUsingPendingIntent");
            PendingIntent.getActivity(this.context, 1, this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"), 134217728).send(this.context, 2, null, new PendingIntent.OnFinished() { // from class: com.tencent.mm.opensdk.openapi.BaseWXApiImplV10.2
                @Override // android.app.PendingIntent.OnFinished
                public void onSendFinished(PendingIntent pendingIntent, Intent intent, int i, String str, Bundle bundle) {
                    C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "onSendFinished resultCode: " + i + ", resultData: " + str);
                }
            }, null);
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "launchWXUsingPendingIntent pendingIntent send failed: " + e.getMessage());
            openWXApp();
        }
    }

    private boolean sendAddCardToWX(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/addCardToWX"), null, null, new String[]{this.appId, bundle.getString("_wxapi_add_card_to_wx_card_list"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendChooseCardFromWX(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/chooseCardFromWX"), null, null, new String[]{bundle.getString("_wxapi_choose_card_from_wx_card_app_id"), bundle.getString("_wxapi_choose_card_from_wx_card_location_id"), bundle.getString("_wxapi_choose_card_from_wx_card_sign_type"), bundle.getString("_wxapi_choose_card_from_wx_card_card_sign"), bundle.getString("_wxapi_choose_card_from_wx_card_time_stamp"), bundle.getString("_wxapi_choose_card_from_wx_card_nonce_str"), bundle.getString("_wxapi_choose_card_from_wx_card_card_id"), bundle.getString("_wxapi_choose_card_from_wx_card_card_type"), bundle.getString("_wxapi_choose_card_from_wx_card_can_multi_select"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendFinderOpenFeed(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenFeed");
        WXChannelOpenFeed.Req req = (WXChannelOpenFeed.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenFeed"), null, null, new String[]{this.appId, req.feedID, req.nonceID, String.valueOf(req.notGetReleatedList)}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendFinderOpenLive(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenLive");
        WXChannelOpenLive.Req req = (WXChannelOpenLive.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenLive"), null, null, new String[]{this.appId, req.feedID, req.nonceID}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendFinderOpenProfile(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "sendFinderOpenProfile");
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderOpenProfile"), null, null, new String[]{this.appId, ((WXChannelOpenProfile.Req) baseReq).userName}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendFinderShareVideo(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "sendFinderShareVideo");
        WXChannelShareVideo.Req req = (WXChannelShareVideo.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/finderShareVideo"), null, null, new String[]{this.appId, req.videoPath, "", "", req.extData, finderShareVideoJumpInfoToString(req.jumpInfo)}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendHandleScanResult(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/handleScanResult"), null, null, new String[]{this.appId, bundle.getString("_wxapi_scan_qrcode_result")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendInvoiceAuthInsert(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, null, new String[]{this.appId, String.valueOf(2), URLEncoder.encode(String.format("url=%s", URLEncoder.encode(((WXInvoiceAuthInsert.Req) baseReq).url)))}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendJumpToOfflinePayReq(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/jumpToOfflinePay"), null, null, new String[]{this.appId}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendLaunchWXMiniprogram(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXLaunchMiniProgram.Req req = (WXLaunchMiniProgram.Req) baseReq;
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogram");
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, req.userName, req.path, req.miniprogramType + "", req.extData}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendLaunchWXMiniprogramWithToken(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWXMiniprogramWithToken"), null, null, new String[]{this.appId, ((WXLaunchMiniProgramWithToken.Req) baseReq).token}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendNonTaxPay(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, null, new String[]{this.appId, String.valueOf(3), URLEncoder.encode(String.format("url=%s", URLEncoder.encode(((WXNontaxPay.Req) baseReq).url)))}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusiLuckyMoney(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusiLuckyMoney"), null, null, new String[]{this.appId, bundle.getString("_wxapi_open_busi_lucky_money_timeStamp"), bundle.getString("_wxapi_open_busi_lucky_money_nonceStr"), bundle.getString("_wxapi_open_busi_lucky_money_signType"), bundle.getString("_wxapi_open_busi_lucky_money_signature"), bundle.getString("_wxapi_open_busi_lucky_money_package")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusinessView(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXOpenBusinessView.Req req = (WXOpenBusinessView.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessView"), null, null, new String[]{this.appId, req.businessType, req.query, req.extInfo, req.transaction, req.openId}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenBusinessWebview(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXOpenBusinessWebview.Req req = (WXOpenBusinessWebview.Req) baseReq;
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/openBusinessWebview");
        String str = "";
        HashMap<String, String> hashMap = req.queryInfo;
        if (hashMap != null && hashMap.size() > 0) {
            JSONObject jSONObject = new JSONObject(req.queryInfo);
            str = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        }
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, req.businessType + "", str}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenCustomerServiceChat(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXOpenCustomerServiceChat.Req req = (WXOpenCustomerServiceChat.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openCustomerServiceChat"), null, null, new String[]{this.appId, req.corpId, req.url}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendOpenRankListReq(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openRankList"), null, null, new String[0], null);
        if (query != null) {
            query.close();
            return true;
        }
        return true;
    }

    private boolean sendOpenWebview(Context context, Bundle bundle) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openWebview"), null, null, new String[]{this.appId, bundle.getString("_wxapi_jump_to_webview_url"), bundle.getString("_wxapi_basereq_transaction")}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayInSurance(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, null, new String[]{this.appId, String.valueOf(4), URLEncoder.encode(String.format("url=%s", URLEncoder.encode(((WXPayInsurance.Req) baseReq).url)))}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPayReq(Context context, Bundle bundle) {
        if (wxappPayEntryClassname == null) {
            wxappPayEntryClassname = new MMSharedPreferences(context).getString("_wxapp_pay_entry_classname_", null);
            C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "pay, set wxappPayEntryClassname = " + wxappPayEntryClassname);
            if (wxappPayEntryClassname == null) {
                try {
                    wxappPayEntryClassname = context.getPackageManager().getApplicationInfo("com.tencent.mm", 128).metaData.getString("com.tencent.mm.BuildInfo.OPEN_SDK_PAY_ENTRY_CLASSNAME", null);
                } catch (Exception e) {
                    C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "get from metaData failed : " + e.getMessage());
                }
            }
            if (wxappPayEntryClassname == null) {
                C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "pay fail, wxappPayEntryClassname is null");
                return false;
            }
        }
        MMessageActV2.Args args = new MMessageActV2.Args();
        args.bundle = bundle;
        args.targetPkgName = "com.tencent.mm";
        args.targetClassName = wxappPayEntryClassname;
        args.launchMode = this.launchMode;
        try {
            String tokenFromWX = getTokenFromWX(context);
            if (tokenFromWX != null) {
                args.token = tokenFromWX;
            }
        } catch (Exception e2) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX fail, exception = " + e2);
        }
        return MMessageActV2.send(context, args);
    }

    private boolean sendPreloadWXMiniProgramEnvironment(Context context, BaseReq baseReq) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/preloadWXMiniprogramEnvironment"), null, null, new String[]{this.appId, ((WXPreloadMiniProgramEnvironment.Req) baseReq).extData}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendPreloadWXMiniprogram(Context context, BaseReq baseReq) {
        WXPreloadMiniProgram.Req req = (WXPreloadMiniProgram.Req) baseReq;
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/preloadWXMiniprogram");
        Cursor query = contentResolver.query(parse, null, null, new String[]{this.appId, req.userName, req.path, req.miniprogramType + "", req.extData}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendQRCodePayReq(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXQRCodePay.Req req = (WXQRCodePay.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/QRCodePay"), null, null, new String[]{this.appId, req.codeContent, req.extraMsg}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendSubscribeMessage(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        SubscribeMessage.Req req = (SubscribeMessage.Req) baseReq;
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, null, new String[]{this.appId, String.valueOf(1), String.valueOf(req.scene), req.templateID, req.reserved}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendSubscribeMiniProgramMsg(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.tencent.mm.sdk.comm.provider/openTypeWebview"), null, null, new String[]{this.appId, String.valueOf(5), ((SubscribeMiniProgramMsg.Req) baseReq).miniProgramAppId}, null);
        if (query != null) {
            query.close();
        }
        return true;
    }

    private boolean sendToWxaRedirectingPage(Context context, BaseReq baseReq) {
        launchWXIfNeed();
        WXLaunchWxaRedirectingPage.Req req = (WXLaunchWxaRedirectingPage.Req) baseReq;
        ContentResolver contentResolver = context.getContentResolver();
        Uri parse = Uri.parse("content://com.tencent.mm.sdk.comm.provider/launchWxaOpenApiRedirectingPage");
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, this.appId);
        arrayList.addAll(Arrays.asList(req.toArray()));
        Cursor query = contentResolver.query(parse, null, null, (String[]) arrayList.toArray(new String[0]), null);
        if (query != null) {
            query.close();
            return true;
        }
        return true;
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public void detach() {
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "detach");
        this.detached = true;
        this.context = null;
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public int getWXAppSupportAPI() {
        if (this.detached) {
            throw new IllegalStateException("getWXAppSupportAPI fail, WXMsgImpl has been detached");
        }
        if (!isWXAppInstalled()) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return 0;
        }
        this.wxSdkVersion = 0;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        C10386b.f19992e.submit(new Runnable() { // from class: com.tencent.mm.opensdk.openapi.BaseWXApiImplV10.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    MMSharedPreferences mMSharedPreferences = new MMSharedPreferences(BaseWXApiImplV10.this.context);
                    BaseWXApiImplV10.this.wxSdkVersion = mMSharedPreferences.getInt("_build_info_sdk_int_", 0);
                } catch (Exception e) {
                    C10384Log.m6207w("MicroMsg.SDK.WXApiImplV10", e.getMessage());
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            C10384Log.m6207w("MicroMsg.SDK.WXApiImplV10", e.getMessage());
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "wxSdkVersion = " + this.wxSdkVersion);
        if (this.wxSdkVersion == 0) {
            try {
                this.wxSdkVersion = this.context.getPackageManager().getApplicationInfo("com.tencent.mm", 128).metaData.getInt("com.tencent.mm.BuildInfo.OPEN_SDK_VERSION", 0);
                C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "OPEN_SDK_VERSION = " + this.wxSdkVersion);
            } catch (Exception e2) {
                C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "get from metaData failed : " + e2.getMessage());
            }
        }
        return this.wxSdkVersion;
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean handleIntent(Intent intent, IWXAPIEventHandler iWXAPIEventHandler) {
        try {
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, ex = " + e.getMessage());
        }
        if (!WXApiImplComm.isIntentFromWx(intent, "com.tencent.mm.openapi.token")) {
            C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "handleIntent fail, intent not from weixin msg");
            return false;
        } else if (this.detached) {
            throw new IllegalStateException("handleIntent fail, WXMsgImpl has been detached");
        } else {
            String stringExtra = intent.getStringExtra("_mmessage_content");
            int intExtra = intent.getIntExtra("_mmessage_sdkVersion", 0);
            String stringExtra2 = intent.getStringExtra("_mmessage_appPackage");
            if (stringExtra2 != null && stringExtra2.length() != 0) {
                if (!checkSumConsistent(intent.getByteArrayExtra("_mmessage_checksum"), C10372a.m6223a(stringExtra, intExtra, stringExtra2))) {
                    C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "checksum fail");
                    return false;
                }
                int intExtra2 = intent.getIntExtra("_wxapi_command_type", 0);
                C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "handleIntent, cmd = " + intExtra2);
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
                        ShowMessageFromWX.Req req = new ShowMessageFromWX.Req(intent.getExtras());
                        String str = req.message.messageExt;
                        if (str != null && str.contains("wx_internal_resptype")) {
                            boolean handleWxInternalRespType = handleWxInternalRespType(str, iWXAPIEventHandler);
                            C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "handleIntent, extInfo contains wx_internal_resptype, ret = " + handleWxInternalRespType);
                            return handleWxInternalRespType;
                        }
                        if (str != null && str.contains("openbusinesswebview")) {
                            try {
                                Uri parse = Uri.parse(str);
                                if (parse != null && "openbusinesswebview".equals(parse.getHost())) {
                                    WXOpenBusinessWebview.Resp resp = new WXOpenBusinessWebview.Resp();
                                    String queryParameter = parse.getQueryParameter("ret");
                                    if (queryParameter != null && queryParameter.length() > 0) {
                                        resp.errCode = C10386b.m6203a(queryParameter, 0);
                                    }
                                    resp.resultInfo = parse.getQueryParameter("resultInfo");
                                    resp.errStr = parse.getQueryParameter("errmsg");
                                    String queryParameter2 = parse.getQueryParameter("type");
                                    if (queryParameter2 != null && queryParameter2.length() > 0) {
                                        resp.businessType = C10386b.m6203a(queryParameter2, 0);
                                    }
                                    iWXAPIEventHandler.onResp(resp);
                                    return true;
                                }
                                C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "not openbusinesswebview %" + str);
                            } catch (Exception e2) {
                                C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "parse fail, ex = " + e2.getMessage());
                            }
                        }
                        iWXAPIEventHandler.onReq(req);
                        return true;
                    case 5:
                        iWXAPIEventHandler.onResp(new PayResp(intent.getExtras()));
                        return true;
                    case 6:
                        iWXAPIEventHandler.onReq(new LaunchFromWX.Req(intent.getExtras()));
                        return true;
                    case 7:
                    case 8:
                    case 10:
                    case 11:
                    case 13:
                    case 18:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    default:
                        C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "unknown cmd = " + intExtra2);
                        break;
                    case 9:
                        iWXAPIEventHandler.onResp(new AddCardToWXCardPackage.Resp(intent.getExtras()));
                        return true;
                    case 12:
                        iWXAPIEventHandler.onResp(new OpenWebview.Resp(intent.getExtras()));
                        return true;
                    case 14:
                        iWXAPIEventHandler.onResp(new CreateChatroom.Resp(intent.getExtras()));
                        return true;
                    case 15:
                        iWXAPIEventHandler.onResp(new JoinChatroom.Resp(intent.getExtras()));
                        return true;
                    case 16:
                        iWXAPIEventHandler.onResp(new ChooseCardFromWXCardPackage.Resp(intent.getExtras()));
                        return true;
                    case 17:
                        iWXAPIEventHandler.onResp(new HandleScanResult.Resp(intent.getExtras()));
                        return true;
                    case 19:
                        iWXAPIEventHandler.onResp(new WXLaunchMiniProgram.Resp(intent.getExtras()));
                        return true;
                    case 24:
                        iWXAPIEventHandler.onResp(new JumpToOfflinePay.Resp(intent.getExtras()));
                        return true;
                    case 25:
                        iWXAPIEventHandler.onResp(new WXOpenBusinessWebview.Resp(intent.getExtras()));
                        return true;
                    case 26:
                        iWXAPIEventHandler.onResp(new WXOpenBusinessView.Resp(intent.getExtras()));
                        return true;
                    case 27:
                        iWXAPIEventHandler.onResp(new WXJointPay.JointPayResp(intent.getExtras()));
                        return true;
                    case 28:
                        iWXAPIEventHandler.onResp(new WXPreloadMiniProgram.Resp(intent.getExtras()));
                        return true;
                    case 29:
                        iWXAPIEventHandler.onResp(new WXLaunchMiniProgramWithToken.Resp(intent.getExtras()));
                        return true;
                    case 30:
                        iWXAPIEventHandler.onResp(new WXLaunchWxaRedirectingPage.Resp(intent.getExtras()));
                        return true;
                    case 31:
                        iWXAPIEventHandler.onResp(new SendTdiAuth.Resp(intent.getExtras()));
                        return true;
                    case 32:
                        iWXAPIEventHandler.onResp(new WXPreloadMiniProgramEnvironment.Resp(intent.getExtras()));
                        return true;
                    case 33:
                        iWXAPIEventHandler.onResp(new WXChannelShareVideo.Resp(intent.getExtras()));
                        return true;
                    case 34:
                        iWXAPIEventHandler.onResp(new WXChannelOpenProfile.Resp(intent.getExtras()));
                        return true;
                    case 35:
                        iWXAPIEventHandler.onResp(new WXChannelOpenLive.Resp(intent.getExtras()));
                        return true;
                    case 36:
                        iWXAPIEventHandler.onResp(new WXChannelOpenFeed.Resp(intent.getExtras()));
                        return true;
                    case 37:
                        iWXAPIEventHandler.onResp(new WXOpenCustomerServiceChat.Resp(intent.getExtras()));
                        return true;
                    case 38:
                        iWXAPIEventHandler.onResp(new WXQRCodePay.Resp(intent.getExtras()));
                        return true;
                }
                return false;
            }
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "invalid argument");
            return false;
        }
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean isWXAppInstalled() {
        if (this.detached) {
            throw new IllegalStateException("isWXAppInstalled fail, WXMsgImpl has been detached");
        }
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo("com.tencent.mm", 64);
            if (packageInfo == null) {
                return false;
            }
            return WXApiImplComm.validateAppSignature(this.context, packageInfo.signatures, this.checkSignature);
        } catch (Exception unused) {
            return false;
        }
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean openWXApp() {
        if (this.detached) {
            throw new IllegalStateException("openWXApp fail, WXMsgImpl has been detached");
        }
        if (!isWXAppInstalled()) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "open wx app failed, not installed or signature check failed");
            return false;
        }
        try {
            this.context.startActivity(this.context.getPackageManager().getLaunchIntentForPackage("com.tencent.mm"));
            return true;
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "startActivity fail, exception = " + e.getMessage());
            return false;
        }
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean registerApp(String str) {
        return registerApp(str, 0L);
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean registerApp(String str, long j) {
        if (this.detached) {
            throw new IllegalStateException("registerApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "register app failed for wechat app signature check failed");
            return false;
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
        if (str != null) {
            this.appId = str;
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "registerApp, appId = " + str);
        if (str != null) {
            this.appId = str;
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "register app " + this.context.getPackageName());
        C10372a.C10373a c10373a = new C10372a.C10373a();
        c10373a.f19947a = "com.tencent.mm";
        c10373a.f19948b = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_REGISTER";
        c10373a.f19949c = "weixin://registerapp?appid=" + this.appId;
        c10373a.f19950d = j;
        return C10372a.m6227a(this.context, c10373a);
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean sendReq(BaseReq baseReq) {
        StringBuilder sb;
        String str;
        WXWebpageObject wXWebpageObject;
        int i;
        String str2;
        String str3;
        String str4;
        if (this.detached) {
            throw new IllegalStateException("sendReq fail, WXMsgImpl has been detached");
        }
        if (WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            if (baseReq.getType() == 2) {
                SendMessageToWX.Req req = (SendMessageToWX.Req) baseReq;
                if (req.scene == 4) {
                    SendMessageToWX.IWXSceneDataObject iWXSceneDataObject = req.sceneDataObject;
                    if (iWXSceneDataObject instanceof WXStateSceneDataObject) {
                        WXStateSceneDataObject wXStateSceneDataObject = (WXStateSceneDataObject) iWXSceneDataObject;
                        WXMediaMessage wXMediaMessage = req.message;
                        if (wXMediaMessage.mediaObject == null) {
                            wXMediaMessage.mediaObject = new WXTextObject();
                        }
                        if (req.message.getType() == 1 && ((str4 = wXStateSceneDataObject.stateTitle) == null || str4.length() <= 0)) {
                            wXStateSceneDataObject.stateTitle = ((WXTextObject) req.message.mediaObject).text;
                        }
                    }
                }
            }
            if (baseReq.checkArgs()) {
                C10384Log.m6209i("MicroMsg.SDK.WXApiImplV10", "sendReq, req type = " + baseReq.getType());
                Bundle bundle = new Bundle();
                baseReq.toBundle(bundle);
                if (baseReq.getType() == 5 || baseReq.getType() == 27) {
                    return sendPayReq(this.context, bundle);
                }
                if (baseReq.getType() == 9) {
                    return sendAddCardToWX(this.context, bundle);
                }
                if (baseReq.getType() == 16) {
                    return sendChooseCardFromWX(this.context, bundle);
                }
                if (baseReq.getType() == 11) {
                    return sendOpenRankListReq(this.context, bundle);
                }
                if (baseReq.getType() == 12) {
                    return sendOpenWebview(this.context, bundle);
                }
                if (baseReq.getType() == 25) {
                    return sendOpenBusinessWebview(this.context, baseReq);
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
                if (baseReq.getType() == 17) {
                    return sendHandleScanResult(this.context, bundle);
                }
                if (baseReq.getType() == 18) {
                    return sendSubscribeMessage(this.context, baseReq);
                }
                if (baseReq.getType() == 28) {
                    return sendPreloadWXMiniprogram(this.context, baseReq);
                }
                if (baseReq.getType() == 29) {
                    return sendLaunchWXMiniprogramWithToken(this.context, baseReq);
                }
                if (baseReq.getType() == 23) {
                    return sendSubscribeMiniProgramMsg(this.context, baseReq);
                }
                if (baseReq.getType() == 19) {
                    return sendLaunchWXMiniprogram(this.context, baseReq);
                }
                if (baseReq.getType() == 32) {
                    return sendPreloadWXMiniProgramEnvironment(this.context, baseReq);
                }
                if (baseReq.getType() == 30) {
                    return sendToWxaRedirectingPage(this.context, baseReq);
                }
                if (baseReq.getType() == 26) {
                    return sendOpenBusinessView(this.context, baseReq);
                }
                if (baseReq.getType() == 33) {
                    return sendFinderShareVideo(this.context, baseReq);
                }
                if (baseReq.getType() == 34) {
                    return sendFinderOpenProfile(this.context, baseReq);
                }
                if (baseReq.getType() == 35) {
                    return sendFinderOpenLive(this.context, baseReq);
                }
                if (baseReq.getType() == 36) {
                    return sendFinderOpenFeed(this.context, baseReq);
                }
                if (baseReq.getType() == 37) {
                    return sendOpenCustomerServiceChat(this.context, baseReq);
                }
                if (baseReq.getType() == 38) {
                    return sendQRCodePayReq(this.context, baseReq);
                }
                if (baseReq.getType() == 20) {
                    return sendInvoiceAuthInsert(this.context, baseReq);
                }
                if (baseReq.getType() == 21) {
                    return sendNonTaxPay(this.context, baseReq);
                }
                if (baseReq.getType() == 22) {
                    return sendPayInSurance(this.context, baseReq);
                }
                if (baseReq.getType() == 24) {
                    return sendJumpToOfflinePayReq(this.context, bundle);
                }
                if (baseReq.getType() == 2) {
                    SendMessageToWX.Req req2 = (SendMessageToWX.Req) baseReq;
                    int type = req2.message.getType();
                    if (C10386b.m6206a(type)) {
                        if (getWXAppSupportAPI() < 620756993) {
                            wXWebpageObject = new WXWebpageObject();
                        } else if (type != 46 || getWXAppSupportAPI() >= 620953856) {
                            WXMiniProgramObject wXMiniProgramObject = (WXMiniProgramObject) req2.message.mediaObject;
                            wXMiniProgramObject.userName += "@app";
                            String str5 = wXMiniProgramObject.path;
                            if (!C10386b.m6202b(str5)) {
                                String[] split = str5.split("\\?");
                                if (split.length > 1) {
                                    sb = new StringBuilder();
                                    sb.append(split[0]);
                                    sb.append(".html?");
                                    str = split[1];
                                } else {
                                    sb = new StringBuilder();
                                    sb.append(split[0]);
                                    str = ".html";
                                }
                                sb.append(str);
                                wXMiniProgramObject.path = sb.toString();
                            }
                            i = req2.scene;
                            if (i != 3 && i != 1) {
                                req2.scene = 0;
                            }
                            baseReq.toBundle(bundle);
                        } else {
                            wXWebpageObject = new WXWebpageObject();
                        }
                        wXWebpageObject.webpageUrl = bundle.getString("_wxminiprogram_webpageurl");
                        req2.message.mediaObject = wXWebpageObject;
                        i = req2.scene;
                        if (i != 3) {
                            req2.scene = 0;
                        }
                        baseReq.toBundle(bundle);
                    }
                }
                MMessageActV2.Args args = new MMessageActV2.Args();
                args.bundle = bundle;
                args.content = "weixin://sendreq?appid=" + this.appId;
                args.targetPkgName = "com.tencent.mm";
                args.targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
                args.launchMode = this.launchMode;
                try {
                    String tokenFromWX = getTokenFromWX(this.context);
                    if (tokenFromWX != null) {
                        args.token = tokenFromWX;
                    }
                } catch (Exception e) {
                    C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX fail, exception = " + e);
                }
                return MMessageActV2.send(this.context, args);
            }
            str2 = "MicroMsg.SDK.WXApiImplV10";
            str3 = "sendReq checkArgs fail";
        } else {
            str2 = "MicroMsg.SDK.WXApiImplV10";
            str3 = "sendReq failed for wechat app signature check failed";
        }
        C10384Log.m6210e(str2, str3);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public boolean sendResp(BaseResp baseResp) {
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
            MMessageActV2.Args args = new MMessageActV2.Args();
            args.bundle = bundle;
            args.content = "weixin://sendresp?appid=" + this.appId;
            args.targetPkgName = "com.tencent.mm";
            args.targetClassName = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
            try {
                String tokenFromWX = getTokenFromWX(this.context);
                if (tokenFromWX != null) {
                    args.token = tokenFromWX;
                }
            } catch (Exception e) {
                C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "getTokenFromWX fail, exception = " + e);
            }
            return MMessageActV2.send(this.context, args);
        } else {
            str = "MicroMsg.SDK.WXApiImplV10";
            str2 = "sendResp checkArgs fail";
        }
        C10384Log.m6210e(str, str2);
        return false;
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public void setLogImpl(ILog iLog) {
        C10384Log.setLogImpl(iLog);
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPI
    public void unregisterApp() {
        if (this.detached) {
            throw new IllegalStateException("unregisterApp fail, WXMsgImpl has been detached");
        }
        if (!WXApiImplComm.validateAppSignatureForPackage(this.context, "com.tencent.mm", this.checkSignature)) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "unregister app failed for wechat app signature check failed");
            return;
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "unregisterApp, appId = " + this.appId);
        String str = this.appId;
        if (str == null || str.length() == 0) {
            C10384Log.m6210e("MicroMsg.SDK.WXApiImplV10", "unregisterApp fail, appId is empty");
            return;
        }
        C10384Log.m6211d("MicroMsg.SDK.WXApiImplV10", "unregister app " + this.context.getPackageName());
        C10372a.C10373a c10373a = new C10372a.C10373a();
        c10373a.f19947a = "com.tencent.mm";
        c10373a.f19948b = "com.tencent.mm.plugin.openapi.Intent.ACTION_HANDLE_APP_UNREGISTER";
        c10373a.f19949c = "weixin://unregisterapp?appid=" + this.appId;
        C10372a.m6227a(this.context, c10373a);
    }
}
