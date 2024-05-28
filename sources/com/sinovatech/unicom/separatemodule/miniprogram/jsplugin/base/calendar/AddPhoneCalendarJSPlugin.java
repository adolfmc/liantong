package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.calendar;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/addPhoneCalendar")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AddPhoneCalendarJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("title", "");
            long optLong = this.parameterJO.optLong("startTime", System.currentTimeMillis());
            long optLong2 = this.parameterJO.optLong("endTime", System.currentTimeMillis());
            String optString2 = this.parameterJO.optString("description", "");
            String optString3 = this.parameterJO.optString("location", "");
            String optString4 = this.parameterJO.optString("allDay", "no");
            String optString5 = this.parameterJO.optString("alarm", "yes");
            boolean z = true;
            Intent putExtra = new Intent("android.intent.action.INSERT").setData(CalendarContract.Events.CONTENT_URI).putExtra("beginTime", optLong).putExtra("endTime", optLong2).putExtra("allDay", optString4.equals("yes")).putExtra("eventLocation", optString3).putExtra("title", optString).putExtra("description", optString2);
            if (!optString5.equals("yes")) {
                z = false;
            }
            Intent putExtra2 = putExtra.putExtra("hasAlarm", z);
            if (putExtra2.resolveActivity(this.activityContext.getPackageManager()) != null) {
                this.activityContext.startActivity(putExtra2);
                callbackSuccess(new JSONObject());
            } else {
                callbackFail("10", "程序错误：系统不支持添加日历提醒");
            }
        } catch (Exception e) {
            e.printStackTrace();
            callbackFail("10", "程序错误：" + e.getMessage());
        }
    }
}
