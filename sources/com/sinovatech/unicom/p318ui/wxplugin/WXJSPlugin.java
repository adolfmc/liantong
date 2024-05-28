package com.sinovatech.unicom.p318ui.wxplugin;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fort.andjni.JniLib;
import com.sinovatech.unicom.basic.eventbus.WeixinAccessEvent;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.wxplugin.WXInformation;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tencent.p348mm.opensdk.modelmsg.SendAuth;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/wx")
/* renamed from: com.sinovatech.unicom.ui.wxplugin.WXJSPlugin */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WXJSPlugin extends BaseJSPlugin {
    public String state = "wxWebLogin";
    private String scope = "snsapi_userinfo";

    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXJSPlugin$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97421 implements WXInformation.WXInterface {
        final /* synthetic */ WXJSPlugin this$0;

        C97421(WXJSPlugin wXJSPlugin) {
            JniLib.m15918cV(this, wXJSPlugin, 388);
        }

        @Override // com.sinovatech.unicom.p318ui.wxplugin.WXInformation.WXInterface
        public void fail(String str) {
            JniLib.m15918cV(this, str, 386);
        }

        @Override // com.sinovatech.unicom.p318ui.wxplugin.WXInformation.WXInterface
        public void success(JSONObject jSONObject) {
            JniLib.m15918cV(this, jSONObject, 387);
        }
    }

    /* renamed from: com.sinovatech.unicom.ui.wxplugin.WXJSPlugin$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C97432 implements WXInformation.WXInterface {
        final /* synthetic */ WXJSPlugin this$0;

        C97432(WXJSPlugin wXJSPlugin) {
            JniLib.m15918cV(this, wXJSPlugin, 391);
        }

        @Override // com.sinovatech.unicom.p318ui.wxplugin.WXInformation.WXInterface
        public void fail(String str) {
            JniLib.m15918cV(this, str, 389);
        }

        @Override // com.sinovatech.unicom.p318ui.wxplugin.WXInformation.WXInterface
        public void success(JSONObject jSONObject) {
            JniLib.m15918cV(this, jSONObject, 390);
        }
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        JniLib.m15918cV(this, context, 392);
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        JniLib.m15918cV(this, 393);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void accessWeixinCode(WeixinAccessEvent weixinAccessEvent) {
        if (weixinAccessEvent.getCode() == EventBusUtils.EVENT_Weixin && !TextUtils.isEmpty(weixinAccessEvent.getData().toString())) {
            try {
                SendAuth.Resp resp = (SendAuth.Resp) weixinAccessEvent.getData();
                JSONObject jSONObject = new JSONObject();
                jSONObject.putOpt("errCode", Integer.valueOf(resp.errCode));
                jSONObject.putOpt("code", resp.code);
                jSONObject.putOpt("state", resp.state);
                jSONObject.putOpt("lang", resp.lang);
                jSONObject.putOpt("country", resp.country);
                callbackSuccess(jSONObject);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                callbackFail(e.getMessage());
                return;
            }
        }
        callbackFail("获取失败!!");
    }
}
