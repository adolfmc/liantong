package com.heytap.mcssdk.utils;

import android.content.Context;
import android.content.Intent;
import com.heytap.mcssdk.PushService;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DataMessageUtil {
    private static final String TYPE = "type";

    public void appArrive(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction(PushService.getInstance().getReceiveSdkAction(context));
            intent.setPackage(PushService.getInstance().getMcsPackageName(context));
            intent.putExtra("appPackage", context.getPackageName());
            intent.putExtra("messageID", str);
            intent.putExtra("type", 12312);
            context.startService(intent);
        } catch (Exception e) {
            C4746d.m15482e("statisticMessage--Exception" + e.getMessage());
        }
    }
}
