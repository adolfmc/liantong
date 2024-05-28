package com.huawei.hms.push;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.init.AutoInitHelper;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.t */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TokenUtil {
    /* renamed from: a */
    public static ErrorEnum m14186a(Context context) {
        if (TextUtils.isEmpty(BaseUtils.getLocalToken(context, null))) {
            if (AutoInitHelper.isAutoInitEnabled(context)) {
                HMSLog.m14112e("TokenUtil", "Token not exist, try auto init");
                AutoInitHelper.doAutoInit(context);
                return ErrorEnum.ERROR_AUTO_INITIALIZING;
            }
            HMSLog.m14112e("TokenUtil", "Token not exist");
            return ErrorEnum.ERROR_NO_TOKEN;
        }
        return ErrorEnum.SUCCESS;
    }
}
