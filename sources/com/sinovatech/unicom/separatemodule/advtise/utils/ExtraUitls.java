package com.sinovatech.unicom.separatemodule.advtise.utils;

import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ExtraUitls {
    public static String getExtarString(AdConfigEntity adConfigEntity) {
        String passBackDesmobile = UserManager.getInstance().getPassBackDesmobile();
        String ecsToken = UserManager.getInstance().getEcsToken(passBackDesmobile);
        String acId = adConfigEntity.getAcId();
        String taskId = adConfigEntity.getTaskId();
        String codeId = adConfigEntity.getCodeId();
        String orderId = adConfigEntity.getOrderId();
        String encodeByAES_touchaun = EncodeHelper.encodeByAES_touchaun(passBackDesmobile);
        MsLogUtil.m7979d("mobbileAes", ":   " + encodeByAES_touchaun);
        try {
            return URLEncoder.encode(ecsToken + "|" + encodeByAES_touchaun + "|android|" + acId + "|" + taskId + "|" + orderId + "|" + codeId + "|" + adConfigEntity.getChannelName() + "|" + DeviceHelper.getNETType(App.getInstance()) + "|newVersion", "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
