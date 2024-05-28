package com.sinovatech.unicom.separatemodule.comonjsplugin;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.notice.PushMessageEntity;
import com.sinovatech.unicom.separatemodule.notice.PushServer;
import java.util.List;

@Route(path = "/MsJSPlugin/businessExpert")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BusinessExpertJSPlugin extends BaseJSPlugin {
    private void getMsgAlertStatus() {
    }

    private void updateMsgAlertStatus() {
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("type");
        if ("getBusinessExpertListUrl".equals(optString)) {
            getBusinessExpertListUrl();
        }
        if ("getMsgAlertStatus".equals(optString)) {
            getMsgAlertStatus();
        }
        if ("updateMsgAlertStatus".equals(optString)) {
            updateMsgAlertStatus();
        }
    }

    private void getBusinessExpertListUrl() {
        try {
            List list = (List) PushServer.parseBackData(CacheDataCenter.getInstance().getKefuPushData())[1];
            if (list.size() >= 1) {
                callbackSuccess(((PushMessageEntity) list.get(0)).getSaleMessListUrl());
            } else {
                callbackSuccess("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackSuccess("");
        }
    }
}
