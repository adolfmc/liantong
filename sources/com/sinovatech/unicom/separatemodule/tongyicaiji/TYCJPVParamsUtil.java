package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.p318ui.App;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJPVParamsUtil {
    public static final int BAOGUANG = 3;
    public static final int DIANJI = 2;
    public static final int LIULAN = 1;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public static long stayTimeId;
    private final String TAG = "TYCJPVParamsUtil";

    public static Map<String, String> getHomeTabMap(String str, String str2, String str3) {
        if ("进入".equals(str2)) {
            stayTimeId = System.currentTimeMillis();
        }
        return getParams("13", "2", str, str2, String.valueOf(stayTimeId), "1", "首页-TAB-停留", str3, "");
    }

    public static Map<String, String> getParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        HashMap hashMap = new HashMap();
        String str10 = "";
        if (!TextUtils.isEmpty(str) && str.length() >= 3) {
            str10 = str.substring(0, 3);
        }
        String accountType = LoginManager.getAccountType();
        hashMap.put("transId", str);
        hashMap.put("actCode", str7);
        hashMap.put("titleName", str8);
        hashMap.put("version", App.getInstance().getResources().getString(2131886969));
        hashMap.put("clientType", "Android");
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        hashMap.put("urlApp", str3);
        hashMap.put("touchcode", str2);
        hashMap.put("bizcode", str10);
        hashMap.put("remark2", accountType);
        hashMap.put("biz_proecess", str6);
        hashMap.put("page_new_old_user", CacheDataCenter.getInstance().getPageNewOldUser());
        hashMap.put("upType", str9);
        hashMap.put("remark3", str4);
        hashMap.put("remark4", str5);
        return hashMap;
    }
}
