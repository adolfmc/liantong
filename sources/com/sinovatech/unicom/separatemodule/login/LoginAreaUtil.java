package com.sinovatech.unicom.separatemodule.login;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.datacenter.AreaDataCenter;
import com.sinovatech.unicom.basic.p314po.AreaEntity;
import com.sinovatech.unicom.p318ui.App;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LoginAreaUtil {
    public static String getAreaCode(String str, List<AreaEntity> list) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (list == null) {
            list = AreaDataCenter.getAreaList(App.getInstance());
        }
        for (int i = 0; i < list.size(); i++) {
            if (str.equals(list.get(i).getAreaid())) {
                return list.get(i).getAreacode();
            }
        }
        return null;
    }
}
