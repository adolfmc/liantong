package com.sinovatech.unicom.basic.p315ui.home.function;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBusinessEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.live.capture.util.TimeUtil;
import io.reactivex.functions.Function;
import org.json.JSONObject;

/* renamed from: com.sinovatech.unicom.basic.ui.home.function.HomeCardBgFunction */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeCardBgFunction implements Function<String, Object> {
    private static final String TAG = "HomeCardBgFunction";
    private String serviceCode;

    public HomeCardBgFunction(String str) {
        this.serviceCode = "";
        this.serviceCode = str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x008d, code lost:
        r0.add(getCardBgObj(r2));
     */
    @Override // io.reactivex.functions.Function
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object apply(@io.reactivex.annotations.NonNull java.lang.String r6) throws java.lang.Exception {
        /*
            r5 = this;
            java.lang.String r0 = "HomeCardBgFunction"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "首页卡片动态感知接口数据:serviceCode = "
            r1.append(r2)
            java.lang.String r2 = r5.serviceCode
            r1.append(r2)
            java.lang.String r2 = " \n"
            r1.append(r2)
            r1.append(r6)
            java.lang.String r1 = r1.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r1)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc9
            r1.<init>(r6)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r6 = "respCode"
            java.lang.String r6 = r1.optString(r6)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r2 = "0000"
            boolean r6 = android.text.TextUtils.equals(r2, r6)     // Catch: java.lang.Exception -> Lc9
            if (r6 == 0) goto Le5
            java.lang.String r6 = "isNew"
            org.json.JSONObject r6 = r1.optJSONObject(r6)     // Catch: java.lang.Exception -> Lc9
            if (r6 == 0) goto L44
            com.sinovatech.unicom.basic.p315ui.manager.HomeSearchNewUserManager.setNewUserData(r6)     // Catch: java.lang.Exception -> Lc9
        L44:
            java.lang.String r6 = "result"
            org.json.JSONArray r6 = r1.optJSONArray(r6)     // Catch: java.lang.Exception -> Lc9
            if (r6 == 0) goto Lc8
            int r1 = r6.length()     // Catch: java.lang.Exception -> Lc9
            if (r1 <= 0) goto Lc8
            r1 = 0
        L53:
            int r2 = r6.length()     // Catch: java.lang.Exception -> Lc9
            if (r1 >= r2) goto Lc8
            org.json.JSONObject r2 = r6.optJSONObject(r1)     // Catch: java.lang.Exception -> Lc9
            if (r2 == 0) goto Lae
            java.lang.String r3 = "1000230060"
            java.lang.String r4 = r5.serviceCode     // Catch: java.lang.Exception -> Lc9
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Exception -> Lc9
            if (r3 == 0) goto L71
            com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntity r2 = r5.getYwblObj(r2)     // Catch: java.lang.Exception -> Lc9
            r0.add(r2)     // Catch: java.lang.Exception -> Lc9
            goto Lae
        L71:
            java.lang.String r3 = "1000230058"
            java.lang.String r4 = r5.serviceCode     // Catch: java.lang.Exception -> Lc9
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Exception -> Lc9
            if (r3 == 0) goto L83
            com.sinovatech.unicom.basic.ui.home.entity.HomeYwJingZhunEntity r2 = r5.getYwspObj(r2)     // Catch: java.lang.Exception -> Lc9
            r0.add(r2)     // Catch: java.lang.Exception -> Lc9
            goto Lae
        L83:
            java.lang.String r3 = "1000230057"
            java.lang.String r4 = r5.serviceCode     // Catch: java.lang.Exception -> Lc9
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.Exception -> Lc9
            if (r3 == 0) goto L95
            com.sinovatech.unicom.basic.ui.home.entity.HomeCardBgEntity r6 = r5.getCardBgObj(r2)     // Catch: java.lang.Exception -> Lc9
            r0.add(r6)     // Catch: java.lang.Exception -> Lc9
            goto Lc8
        L95:
            java.lang.String r2 = "HomeCardBgFunction"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc9
            r3.<init>()     // Catch: java.lang.Exception -> Lc9
            java.lang.String r4 = "无用的servicecode="
            r3.append(r4)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r4 = r5.serviceCode     // Catch: java.lang.Exception -> Lc9
            r3.append(r4)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Lc9
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r2, r3)     // Catch: java.lang.Exception -> Lc9
        Lae:
            java.lang.String r2 = "HomeCardBgFunction"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc9
            r3.<init>()     // Catch: java.lang.Exception -> Lc9
            java.lang.String r4 = "遍历 = "
            r3.append(r4)     // Catch: java.lang.Exception -> Lc9
            r3.append(r1)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> Lc9
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r2, r3)     // Catch: java.lang.Exception -> Lc9
            int r1 = r1 + 1
            goto L53
        Lc8:
            return r0
        Lc9:
            r6 = move-exception
            java.lang.String r0 = "HomeCardBgFunction"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "精准营销接口解析数据异常"
            r1.append(r2)
            java.lang.String r6 = r6.getMessage()
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r0, r6)
        Le5:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.home.function.HomeCardBgFunction.apply(java.lang.String):java.lang.Object");
    }

    private HomeBusinessEntity getYwblObj(JSONObject jSONObject) {
        HomeBusinessEntity homeBusinessEntity = new HomeBusinessEntity();
        String optString = jSONObject.optString("productName");
        String optString2 = jSONObject.optString("productRedirecturl");
        String optString3 = jSONObject.optString("productUrl");
        String optString4 = jSONObject.optString("isLogin");
        homeBusinessEntity.setProductName(optString);
        homeBusinessEntity.setIsLogin(optString4);
        homeBusinessEntity.setProductRedirecturl(optString2);
        homeBusinessEntity.setProductUrl(optString3);
        return homeBusinessEntity;
    }

    private HomeYwJingZhunEntity getYwspObj(JSONObject jSONObject) {
        HomeYwJingZhunEntity homeYwJingZhunEntity = new HomeYwJingZhunEntity();
        String optString = jSONObject.optString("productName");
        String optString2 = jSONObject.optString("productDesc");
        String optString3 = jSONObject.optString("productRedirecturl");
        String optString4 = jSONObject.optString("productUrl");
        String optString5 = jSONObject.optString("isLogin");
        homeYwJingZhunEntity.setProductName(optString);
        homeYwJingZhunEntity.setIsLogin(optString5);
        homeYwJingZhunEntity.setProductRedirecturl(optString3);
        homeYwJingZhunEntity.setProductUrl(optString4);
        homeYwJingZhunEntity.setProductDesc(optString2);
        homeYwJingZhunEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
        return homeYwJingZhunEntity;
    }

    private HomeCardBgEntity getCardBgObj(JSONObject jSONObject) {
        HomeCardBgEntity homeCardBgEntity = new HomeCardBgEntity();
        String optString = jSONObject.optString("productName");
        String optString2 = jSONObject.optString("productDesc");
        String optString3 = jSONObject.optString("productRedirecturl");
        String optString4 = jSONObject.optString("productUrl");
        String optString5 = jSONObject.optString("isLogin");
        String optString6 = jSONObject.optString("cornerDesc");
        String optString7 = jSONObject.optString("productCode");
        JSONObject optJSONObject = jSONObject.optJSONObject("elementMap");
        String optString8 = optJSONObject != null ? optJSONObject.optString("nine_fgsyhfzs") : "";
        homeCardBgEntity.setProductName(optString);
        homeCardBgEntity.setIsLogin(optString5);
        homeCardBgEntity.setProductRedirecturl(optString3);
        homeCardBgEntity.setProductUrl(optString4);
        homeCardBgEntity.setProductDesc(optString2);
        homeCardBgEntity.setCornerDesc(optString6);
        homeCardBgEntity.setProductCode(optString7);
        homeCardBgEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
        homeCardBgEntity.setCacheTime(TimeUtil.getCurrentDate());
        homeCardBgEntity.setBackgroundPriority(optString8);
        return homeCardBgEntity;
    }
}
