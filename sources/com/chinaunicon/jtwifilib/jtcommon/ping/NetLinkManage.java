package com.chinaunicon.jtwifilib.jtcommon.ping;

import android.util.Log;
import com.chinaunicon.jtwifilib.jtcommon.ping.bean.PingResBean;
import com.chinaunicon.jtwifilib.jtcommon.ping.manage.PingManage;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NetLinkManage {
    private static NetLinkManage netLinkManage;

    public static NetLinkManage getIntance() {
        if (netLinkManage == null) {
            netLinkManage = new NetLinkManage();
        }
        return netLinkManage;
    }

    public String getLossPacket() throws Exception {
        PingResBean pingForResult = PingManage.getIntance().pingForResult("61.148.239.2", 5);
        if (pingForResult != null) {
            return pingForResult.getLossPacket();
        }
        Log.i("Ping", "0%");
        return "0%";
    }

    public PingResBean getNetTimeTelay(String str) throws Exception {
        return PingManage.getIntance().pingForResult(str, 3);
    }

    public void stopPing() {
        PingManage.getIntance().setStop(true);
    }
}
