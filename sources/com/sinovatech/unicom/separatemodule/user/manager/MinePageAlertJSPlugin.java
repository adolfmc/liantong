package com.sinovatech.unicom.separatemodule.user.manager;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.eventbus.XiaoheitiaoEvent;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/minePageAlert")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MinePageAlertJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (this.parameterJO == null) {
            return;
        }
        String optString = this.parameterJO.optString("type");
        String optString2 = this.parameterJO.optString("title");
        String optString3 = this.parameterJO.optString("msg");
        String optString4 = this.parameterJO.optString("cancel");
        String optString5 = this.parameterJO.optString("confirm");
        XiaoheitiaoEvent xiaoheitiaoEvent = new XiaoheitiaoEvent(1);
        xiaoheitiaoEvent.setTitle(optString2);
        xiaoheitiaoEvent.setMsg(optString3);
        xiaoheitiaoEvent.setCancel(optString4);
        xiaoheitiaoEvent.setConfirm(optString5);
        if ("comon".equals(optString)) {
            xiaoheitiaoEvent.setCode(1);
            EventBusUtils.post(xiaoheitiaoEvent);
        } else if ("cloud".equals(optString)) {
            xiaoheitiaoEvent.setCode(2);
            EventBusUtils.post(xiaoheitiaoEvent);
        }
    }
}
