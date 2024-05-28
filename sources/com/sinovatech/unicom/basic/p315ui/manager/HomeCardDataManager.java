package com.sinovatech.unicom.basic.p315ui.manager;

import android.graphics.Color;
import android.text.TextUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.HeaderChildEntity;
import com.sinovatech.unicom.basic.p314po.HeaderConfigData;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.manager.HomeCardDataManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeCardDataManager {
    private static final String TAG = "HomeCardDataManager";
    private static HomeCardDataManager instance;
    private UserManager userManager = UserManager.getInstance();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.manager.HomeCardDataManager$HomeInfoInterface */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface HomeInfoInterface {
        void onFinish();

        void onStart();
    }

    private HomeCardDataManager() {
    }

    public static synchronized HomeCardDataManager getInstance() {
        HomeCardDataManager homeCardDataManager;
        synchronized (HomeCardDataManager.class) {
            if (instance == null) {
                synchronized (HomeCardDataManager.class) {
                    if (instance == null) {
                        instance = new HomeCardDataManager();
                    }
                }
            }
            homeCardDataManager = instance;
        }
        return homeCardDataManager;
    }

    public void loadData(final HomeInfoInterface homeInfoInterface) {
        String homeUserInfoType3;
        if (!App.hasLogined()) {
            homeInfoInterface.onFinish();
            return;
        }
        final RequestParams requestParams = new RequestParams();
        requestParams.put("showType", "0");
        requestParams.put("version", App.getInstance().getString(2131886969));
        requestParams.put("desmobile", this.userManager.getPassBackDesmobile());
        requestParams.put("currentPhone", this.userManager.getCurrentPhoneNumber());
        if ("03".equals(this.userManager.getLoginType()) || "04".equals(this.userManager.getLoginType()) || "02".equals(this.userManager.getLoginType())) {
            homeUserInfoType3 = URLSet.getHomeUserInfoType3();
        } else {
            homeUserInfoType3 = URLSet.getHomeUserInfo();
        }
        App.getAsyncHttpClient(20, 20, 20).get(homeUserInfoType3, requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.manager.HomeCardDataManager.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
                homeInfoInterface.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                if (i != 200 || TextUtils.isEmpty(str)) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (TextUtils.isEmpty(jSONObject.optString("flush_date_time")) || !"Y".equalsIgnoreCase(jSONObject.getString("code"))) {
                        return;
                    }
                    CacheDataCenter.getInstance().setHomeInfo(str, requestParams.getRealParams().get("currentPhone"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFinish() {
                super.onFinish();
                homeInfoInterface.onFinish();
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x00e2, code lost:
        r3 = r6;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0163 A[Catch: Exception -> 0x02c4, TryCatch #6 {Exception -> 0x02c4, blocks: (B:3:0x0005, B:5:0x001b, B:40:0x00ea, B:41:0x0104, B:43:0x0163, B:45:0x0176, B:49:0x01eb, B:60:0x0291, B:67:0x02c0, B:56:0x027b, B:48:0x01e4, B:44:0x0169, B:62:0x0297, B:64:0x02a3, B:65:0x02bc, B:57:0x0282, B:50:0x024a, B:52:0x0259, B:53:0x0261, B:46:0x01d8), top: B:83:0x0005, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0169 A[Catch: Exception -> 0x02c4, TryCatch #6 {Exception -> 0x02c4, blocks: (B:3:0x0005, B:5:0x001b, B:40:0x00ea, B:41:0x0104, B:43:0x0163, B:45:0x0176, B:49:0x01eb, B:60:0x0291, B:67:0x02c0, B:56:0x027b, B:48:0x01e4, B:44:0x0169, B:62:0x0297, B:64:0x02a3, B:65:0x02bc, B:57:0x0282, B:50:0x024a, B:52:0x0259, B:53:0x0261, B:46:0x01d8), top: B:83:0x0005, inners: #0, #1, #2, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0259 A[Catch: Exception -> 0x027a, TryCatch #2 {Exception -> 0x027a, blocks: (B:50:0x024a, B:52:0x0259, B:53:0x0261), top: B:77:0x024a, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0261 A[Catch: Exception -> 0x027a, TRY_LEAVE, TryCatch #2 {Exception -> 0x027a, blocks: (B:50:0x024a, B:52:0x0259, B:53:0x0261), top: B:77:0x024a, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02a3 A[Catch: Exception -> 0x02c0, TryCatch #0 {Exception -> 0x02c0, blocks: (B:62:0x0297, B:64:0x02a3, B:65:0x02bc), top: B:73:0x0297, outer: #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02bc A[Catch: Exception -> 0x02c0, TRY_LEAVE, TryCatch #0 {Exception -> 0x02c0, blocks: (B:62:0x0297, B:64:0x02a3, B:65:0x02bc), top: B:73:0x0297, outer: #6 }] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x027b -> B:75:0x0282). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.sinovatech.unicom.basic.p314po.HeaderEntity getHeaderEntity() {
        /*
            Method dump skipped, instructions count: 713
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager.getHeaderEntity():com.sinovatech.unicom.basic.po.HeaderEntity");
    }

    private HeaderConfigData getHeaderConfigData(JSONObject jSONObject) {
        HeaderConfigData headerConfigData = new HeaderConfigData();
        headerConfigData.setSearchButtnImageUrl(notNull(jSONObject.optString("searchButtnImageUrl")));
        headerConfigData.setScanCodeButtonImageUrl(notNull(jSONObject.optString("scanCodeButtonImageUrl")));
        try {
            headerConfigData.setCityTextColor(Color.parseColor("#" + jSONObject.optString("cityTextColor")));
        } catch (Exception unused) {
            headerConfigData.setCityTextColor(-1);
        }
        try {
            headerConfigData.setRecommendBacColor(Color.parseColor("#" + jSONObject.optString("recommendBacColor")));
        } catch (Exception unused2) {
            headerConfigData.setRecommendBacColor(570425344);
        }
        headerConfigData.setRecommendCloseImageUrl(notNull(jSONObject.optString("recommendCloseImageUrl")));
        headerConfigData.setRecommendIconUrl(notNull(jSONObject.optString("recommendIconUrl")));
        try {
            headerConfigData.setRecommendTextColor(Color.parseColor("#" + jSONObject.optString("recommendTextColor")));
        } catch (Exception unused3) {
            headerConfigData.setRecommendTextColor(-1342177281);
        }
        return headerConfigData;
    }

    private List<HeaderChildEntity> getHeaderChildList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            try {
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    HeaderChildEntity headerChildEntity = new HeaderChildEntity();
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    try {
                        if (!"03".equals(this.userManager.getLoginType()) && !"04".equals(this.userManager.getLoginType()) && !"02".equals(this.userManager.getLoginType()) && !UserManager.getInstance().isYiwang()) {
                            String optString = optJSONObject.optString("type");
                            if (TextUtils.equals(optJSONObject.optString("codeDetail"), "0")) {
                                CacheDataCenter.getInstance().setHomeInfoCard(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject), UserManager.getInstance().getCurrentPhoneNumber() + optString);
                            } else {
                                String homeInfoCard = CacheDataCenter.getInstance().getHomeInfoCard(UserManager.getInstance().getCurrentPhoneNumber() + optString);
                                if (!TextUtils.isEmpty(homeInfoCard)) {
                                    optJSONObject = new JSONObject(homeInfoCard);
                                }
                            }
                        }
                    } catch (Exception unused) {
                        optJSONObject = jSONArray.optJSONObject(i);
                    }
                    try {
                        headerChildEntity.setPsersent(Float.valueOf(optJSONObject.optString("persent")).intValue());
                    } catch (Exception unused2) {
                        headerChildEntity.setPsersent(0);
                    }
                    String notNull = notNull(optJSONObject.optString("button"));
                    String notNull2 = notNull(optJSONObject.optString("buttonAddress"));
                    String notNull3 = notNull(optJSONObject.optString("remainTitle"));
                    headerChildEntity.setButtonAddress(notNull2);
                    headerChildEntity.setButton(notNull);
                    String notNull4 = notNull(optJSONObject.optString("dynamicFlowTitle"));
                    String notNull5 = notNull(optJSONObject.optString("flowPersent"));
                    String notNull6 = notNull(optJSONObject.optString("dynamicVoiceTitle"));
                    String notNull7 = notNull(optJSONObject.optString("voicePersent"));
                    if (!TextUtils.isEmpty(notNull4)) {
                        headerChildEntity.setRemianTitle(notNull4);
                        headerChildEntity.setNumber(notNull5);
                        if (notNull4.contains("超")) {
                            headerChildEntity.setIsWarn("1");
                        } else {
                            headerChildEntity.setIsWarn(optJSONObject.optString("isWarn"));
                        }
                        headerChildEntity.setUnit(optJSONObject.optString("newUnit"));
                    } else if (!TextUtils.isEmpty(notNull6)) {
                        headerChildEntity.setNumber(notNull7);
                        headerChildEntity.setRemianTitle(notNull6);
                        if (notNull6.contains("超")) {
                            headerChildEntity.setIsWarn("1");
                        } else {
                            headerChildEntity.setIsWarn(optJSONObject.optString("isWarn"));
                        }
                        headerChildEntity.setUnit(optJSONObject.optString("newUnit"));
                    } else {
                        headerChildEntity.setRemianTitle(notNull3);
                        headerChildEntity.setNumber(notNull(optJSONObject.optString("number")));
                        headerChildEntity.setIsWarn(optJSONObject.optString("isWarn"));
                        headerChildEntity.setUnit(optJSONObject.optString("unit"));
                    }
                    headerChildEntity.setIsRedMark(optJSONObject.optString("isRedMark"));
                    headerChildEntity.setUrl(notNull(optJSONObject.optString("url")));
                    headerChildEntity.setUseditle(optJSONObject.optString("usedTitle"));
                    headerChildEntity.setIsShake(optJSONObject.optString("isShake"));
                    headerChildEntity.setMarkerImg(optJSONObject.optString("markerImg"));
                    try {
                        headerChildEntity.setDisplayTime(Integer.parseInt(optJSONObject.optString("displayTime")));
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        headerChildEntity.setDisplayTime(4);
                    }
                    headerChildEntity.setPointUpdateTimeStamp(notNull(optJSONObject.optString("pointUpdateTimeStamp")));
                    headerChildEntity.setButtonBacImageUrl(notNull(optJSONObject.optString("buttonBacImageUrl")));
                    try {
                        String optString2 = optJSONObject.optString("warningPointColor");
                        if (TextUtils.isEmpty(optString2) || "null".equals(optString2)) {
                            headerChildEntity.setWarningPointColor(-65536);
                        } else {
                            headerChildEntity.setWarningPointColor(Color.parseColor("#" + optString2));
                        }
                    } catch (Exception e3) {
                        headerChildEntity.setWarningPointColor(-65536);
                        e3.printStackTrace();
                    }
                    try {
                        String optString3 = optJSONObject.optString("remainTitleColoer");
                        if (TextUtils.isEmpty(optString3) || "null".equals(optString3)) {
                            headerChildEntity.setRemainTitleColoer(-1);
                        } else {
                            headerChildEntity.setRemainTitleColoer(Color.parseColor("#" + optString3));
                        }
                    } catch (Exception unused3) {
                        headerChildEntity.setRemainTitleColoer(-1);
                    }
                    try {
                        String optString4 = optJSONObject.optString("buttonTextColor");
                        if (TextUtils.isEmpty(optString4) || "null".equals(optString4)) {
                            headerChildEntity.setButtonTextColor(-1);
                        } else {
                            headerChildEntity.setButtonTextColor(Color.parseColor("#" + optString4));
                        }
                    } catch (Exception unused4) {
                        headerChildEntity.setButtonTextColor(-1);
                    }
                    try {
                        String optString5 = optJSONObject.optString("persentColor");
                        if (TextUtils.isEmpty(optString5) || "null".equals(optString5)) {
                            headerChildEntity.setPersentColor(-1);
                        } else {
                            headerChildEntity.setPersentColor(Color.parseColor("#" + optString5));
                        }
                    } catch (Exception unused5) {
                        headerChildEntity.setPersentColor(-1);
                    }
                    try {
                        String optString6 = optJSONObject.optString("paperwork4Coloer");
                        if (TextUtils.isEmpty(optString6) || "null".equals(optString6)) {
                            headerChildEntity.setPaperwork4Coloer(-1);
                        } else {
                            headerChildEntity.setPaperwork4Coloer(Color.parseColor("#" + optString6));
                        }
                    } catch (Exception unused6) {
                        headerChildEntity.setPaperwork4Coloer(-1);
                    }
                    try {
                        String optString7 = optJSONObject.optString("ballRippleColor1");
                        if (TextUtils.isEmpty(optString7) || "null".equals(optString7)) {
                            headerChildEntity.setBallRippleColor1(436207615);
                        } else {
                            headerChildEntity.setBallRippleColor1(Color.parseColor("#" + optString7));
                        }
                    } catch (Exception unused7) {
                        headerChildEntity.setBallRippleColor1(436207615);
                    }
                    headerChildEntity.setPaperwork4(optJSONObject.optString("paperwork4"));
                    headerChildEntity.setButtonUrl7(optJSONObject.optString("buttonUrl7"));
                    headerChildEntity.setButtonText7(optJSONObject.optString("buttonText7"));
                    headerChildEntity.setButtonLinkMode(optJSONObject.optString("buttonLinkMode"));
                    headerChildEntity.setButton7LinkMode(optJSONObject.optString("button7LinkMode"));
                    arrayList.add(headerChildEntity);
                }
                return arrayList;
            }
        }
        return arrayList;
    }

    private String notNull(String str) {
        return "null".equals(str) ? "" : str;
    }

    public void updateJSON(List<HeaderChildEntity> list) {
        JSONArray jSONArray = new JSONArray();
        for (HeaderChildEntity headerChildEntity : list) {
            jSONArray.put(headerChildEntity.getRemianTitle());
        }
        CacheDataCenter.getInstance().setHomeCardShunxu(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray), UserManager.getInstance().getCurrentPhoneNumber());
    }

    public List<HeaderChildEntity> getChangedList(List<HeaderChildEntity> list) {
        String homeCardShunxu = CacheDataCenter.getInstance().getHomeCardShunxu(UserManager.getInstance().getCurrentPhoneNumber());
        if (TextUtils.isEmpty(homeCardShunxu)) {
            return list;
        }
        try {
            JSONArray jSONArray = new JSONArray(homeCardShunxu);
            if (list.size() != jSONArray.length()) {
                return list;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                for (HeaderChildEntity headerChildEntity : list) {
                    if (jSONArray.getString(i).equals(headerChildEntity.getRemianTitle())) {
                        arrayList.add(headerChildEntity);
                    }
                }
            }
            return list.size() == arrayList.size() ? arrayList : list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }
}
