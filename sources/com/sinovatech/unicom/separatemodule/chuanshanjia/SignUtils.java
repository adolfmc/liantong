package com.sinovatech.unicom.separatemodule.chuanshanjia;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import java.util.Map;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SignUtils {
    private static String secret = "integraltest&";
    private static final String videoKey = "sinovatechrewordvideokey";

    private static String getSecret() {
        return URLEnvironmentConfig.isProductionEnvironment() ? "integralofficial&" : "integraltest&";
    }

    public static String getSign(Map<String, String> map) {
        try {
            int size = map.size();
            String secret2 = getSecret();
            for (int i = 1; i <= size; i++) {
                String str = "arguments" + i;
                String str2 = map.get(str);
                if (!TextUtils.isEmpty(str2)) {
                    secret2 = secret2 + str + str2;
                    if (i != size) {
                        secret2 = secret2 + "&";
                    }
                }
            }
            return EncodeHelper.encoderByMd5(secret2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getWebSign() {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            UIUtils.logD("getWebSign", "sinovatechrewordvideokey" + UserManager.getInstance().getCurrentPhoneNumber() + currentTimeMillis);
            return EncodeHelper.encoderByMd5("sinovatechrewordvideokey" + UserManager.getInstance().getCurrentPhoneNumber() + currentTimeMillis);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTouchuanId() {
        String str = "";
        for (Cookie cookie : App.getPersistentCookiesList()) {
            if ("ecs_token".equals(cookie.name())) {
                str = cookie.value();
            }
        }
        return str;
    }

    public static String getEcsAcc() {
        String str = "";
        for (Cookie cookie : App.getPersistentCookiesList()) {
            if ("ecs_acc".equals(cookie.name())) {
                str = cookie.value();
            }
        }
        return str;
    }
}
