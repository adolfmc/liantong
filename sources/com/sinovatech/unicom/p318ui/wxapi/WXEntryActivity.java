package com.sinovatech.unicom.p318ui.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import cn.sharesdk.wechat.utils.WXMediaMessage;
import cn.sharesdk.wechat.utils.WechatHandlerActivity;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.WeixinAccessEvent;
import com.sinovatech.unicom.basic.eventbus.WeixinEvent;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.tencent.p348mm.opensdk.modelbase.BaseReq;
import com.tencent.p348mm.opensdk.modelbase.BaseResp;
import com.tencent.p348mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.p348mm.opensdk.modelmsg.SendAuth;
import com.tencent.p348mm.opensdk.openapi.IWXAPIEventHandler;
import org.greenrobot.eventbus.EventBus;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.ui.wxapi.WXEntryActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WXEntryActivity extends WechatHandlerActivity implements IWXAPIEventHandler {
    private static String TAG = "WXEntryActivity";
    public NBSTraceUnit _nbs_trace;

    @Override // cn.sharesdk.wechat.utils.WechatHandlerActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 355);
    }

    @Override // cn.sharesdk.wechat.utils.WechatHandlerActivity
    public void onGetMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return JniLib.m15917cZ(this, Integer.valueOf(i), keyEvent, 356);
    }

    @Override // cn.sharesdk.wechat.utils.WechatHandlerActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        JniLib.m15918cV(this, intent, 357);
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq baseReq) {
        JniLib.m15918cV(this, baseReq, 358);
    }

    @Override // android.app.Activity
    public void onRestart() {
        JniLib.m15918cV(this, 359);
    }

    @Override // android.app.Activity
    public void onResume() {
        JniLib.m15918cV(this, 360);
    }

    @Override // cn.sharesdk.wechat.utils.WechatHandlerActivity
    public void onShowMessageFromWXReq(WXMediaMessage wXMediaMessage) {
        JniLib.m15918cV(this, wXMediaMessage, 361);
    }

    @Override // android.app.Activity
    public void onStart() {
        JniLib.m15918cV(this, 362);
    }

    @Override // android.app.Activity
    public void onStop() {
        JniLib.m15918cV(this, 363);
    }

    @Override // com.tencent.p348mm.opensdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 1) {
            MsLogUtil.m7980d("微信拉起手厅-onResp-COMMAND_SENDAUTH");
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            String str = resp.code;
            int i = resp.errCode;
            String str2 = resp.state;
            if (i == 0) {
                String str3 = TAG;
                UIUtils.logD(str3, "微信Code=" + str);
                if ("Login_session".equals(str2)) {
                    EventBus.getDefault().post(new WeixinEvent(EventBusUtils.EVENT_Weixin, str));
                }
            }
            if (App.getSharePreferenceUtil().getString("wx_State").equals(str2)) {
                EventBus.getDefault().post(new WeixinAccessEvent(EventBusUtils.EVENT_Weixin, resp));
            }
        } else if (baseResp.getType() == 19) {
            MsLogUtil.m7980d("微信拉起手厅-onResp-COMMAND_LAUNCH_WX_MINIPROGRAM");
            WXLaunchMiniProgram.Resp resp2 = (WXLaunchMiniProgram.Resp) baseResp;
            String str4 = resp2.extMsg;
            if ("WXMiniProgramPay".equals(str4)) {
                int i2 = resp2.errCode;
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("miniGame", "miniGame");
                startActivity(intent);
            } else {
                String replace = str4.replace("open=", "");
                if (TextUtils.isEmpty(replace)) {
                    replace = "chinaunicom://";
                }
                App.externURLFromBrowser = replace;
                startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
            }
        }
        finish();
    }
}
