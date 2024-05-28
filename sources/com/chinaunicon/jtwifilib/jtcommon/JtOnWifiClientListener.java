package com.chinaunicon.jtwifilib.jtcommon;

import android.content.Context;
import com.chinaunicon.jtwifilib.core.utils.JtGsonUtil;
import com.chinaunicon.jtwifilib.jtcommon.model.Register1Bean;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtOnWifiClientListener implements JtOnConnectWifiListener {
    private Context context;

    public JtOnWifiClientListener(Context context) {
        this.context = context;
    }

    @Override // com.chinaunicon.jtwifilib.jtcommon.JtOnConnectWifiListener
    public void onConnect(String str) {
        Register1Bean register1Bean = new Register1Bean();
        register1Bean.setCmdType("wifi_connect_state");
        register1Bean.setStatus("0");
        if ("1".equals(str)) {
            register1Bean.setMsg("连接WIFI成功");
        } else {
            register1Bean.setMsg("连接WIFI失败");
        }
        JtUploadLog.getInstance(this.context).updateData("1", JtGsonUtil.getInstance().toJson(register1Bean), "wifi_connect_state");
    }
}
