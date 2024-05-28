package com.sinovatech.unicom.basic.boxcenter;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AdvDataCenter {
    private static AdvDataCenter advDataCenter;

    public static synchronized AdvDataCenter getInstance() {
        AdvDataCenter advDataCenter2;
        synchronized (AdvDataCenter.class) {
            if (advDataCenter == null) {
                advDataCenter = new AdvDataCenter();
            }
            advDataCenter2 = advDataCenter;
        }
        return advDataCenter2;
    }

    public boolean getQiandaoHasClicked() {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return format.equals(App.getSharePreferenceUtil().getString("qiandao_click_" + UserManager.getInstance().getCurrentPhoneNumber()));
    }

    public void setQiandaoClicked(String str) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        App.getSharePreferenceUtil().putString("qiandao_click_" + str, format);
    }
}
