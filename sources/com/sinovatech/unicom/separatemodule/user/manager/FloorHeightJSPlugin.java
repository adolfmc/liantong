package com.sinovatech.unicom.separatemodule.user.manager;

import android.content.Context;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.eventbus.XiaoheitiaoEvent;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;

@Route(path = "/MsJSPlugin/floorHeight")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FloorHeightJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.SingletonPattern = true;
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (this.parameterJO == null) {
            return;
        }
        String optString = this.parameterJO.optString("packageFloorHeight");
        XiaoheitiaoEvent xiaoheitiaoEvent = new XiaoheitiaoEvent(0);
        xiaoheitiaoEvent.setData(optString);
        EventBusUtils.post(xiaoheitiaoEvent);
    }
}
