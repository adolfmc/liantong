package com.sinovatech.unicom.separatemodule.collect;

import android.content.Context;
import android.text.TextUtils;
import android.view.WindowManager;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.JSStorageBox;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomCollectManager {
    private static UnicomCollectManager unicomCollectManager;
    private CollectUtils collectUtils;
    private Context context;

    public static UnicomCollectManager getInstance() {
        if (unicomCollectManager == null) {
            synchronized (UnicomCollectManager.class) {
                if (unicomCollectManager == null) {
                    unicomCollectManager = new UnicomCollectManager();
                }
            }
        }
        return unicomCollectManager;
    }

    public void init(Context context) {
        this.context = context;
        saveSessionId();
        this.collectUtils = new CollectUtils();
        MsLogUtil.m7979d("UnicomCollectManager", "2f0f8655a7414449bcada3e4dd3daf4f");
    }

    private void saveSessionId() {
        try {
            CollectConfig.sessionId = createSessionId();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("key", "lzStorage");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("sessionId", CollectConfig.sessionId);
            jSONObject2.put("lastPageTime", String.valueOf(System.currentTimeMillis()));
            jSONObject2.put("lastPageName", "首页");
            jSONObject2.put("pagePath", getClass().getSimpleName());
            jSONObject.put("value", jSONObject2);
            StringBuilder sb = new StringBuilder();
            sb.append("创建的sid");
            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            MsLogUtil.m7979d("UnicomCollectManager", sb.toString());
            JSStorageBox.put("DefaultStorage_", "lzStorage", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String createSessionId() {
        try {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 15; i++) {
                sb.append(CollectConfig.sidArray[random.nextInt(CollectConfig.sidArray.length)]);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d(e.getMessage());
            return "";
        }
    }

    public void refreshSessionId(String str, String str2) {
        try {
            MsLogUtil.m7979d("UnicomCollectManager", "更新的数据" + str + "---" + str2);
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && "lzStorage".equals(str)) {
                JSStorageBox.put("DefaultStorage_", "lzStorage", str2);
                JSONObject optJSONObject = new JSONObject(str2).optJSONObject("value");
                if (optJSONObject != null) {
                    CollectConfig.sessionId = optJSONObject.optString("sessionId");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void collectData(CollectDataEntity collectDataEntity, int i) {
        try {
            if (collectDataEntity == null) {
                MsLogUtil.m7979d("UnicomCollectManager", "传递数据为空，不执行采集");
                return;
            }
            Map<String, Object> dataMap = getDataMap();
            if (!TextUtils.isEmpty(collectDataEntity.getItemName())) {
                dataMap.put("item_name", collectDataEntity.getItemName());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getPageName())) {
                dataMap.put("page_name", collectDataEntity.getPageName());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getPbName())) {
                dataMap.put("pb_name", collectDataEntity.getPbName());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getStoreyCode())) {
                dataMap.put("storey_code", collectDataEntity.getStoreyCode());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getGoodSid())) {
                dataMap.put("goodSid", collectDataEntity.getGoodSid());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getCodeId())) {
                dataMap.put("code_id", collectDataEntity.getCodeId());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getActType())) {
                dataMap.put("actType", collectDataEntity.getActType());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getActId())) {
                dataMap.put("actId", collectDataEntity.getActId());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getCommodityId())) {
                dataMap.put("commodity_id", collectDataEntity.getCommodityId());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getBusinessType())) {
                dataMap.put("business_type", collectDataEntity.getBusinessType());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getRecommendType())) {
                dataMap.put("recommend_type", collectDataEntity.getRecommendType());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getTargetUrl())) {
                dataMap.put("target_url", collectDataEntity.getTargetUrl());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getManrongActivity())) {
                dataMap.put("manrong_activity", collectDataEntity.getManrongActivity());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getManrongType())) {
                dataMap.put("manrong_type", collectDataEntity.getManrongType());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getExposure_name())) {
                dataMap.put("exposure_name", collectDataEntity.getExposure_name());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getGoods_onlyid())) {
                dataMap.put("goods_onlyid", collectDataEntity.getGoods_onlyid());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getMaintainingInformation())) {
                dataMap.put("maintaining_information", collectDataEntity.getMaintainingInformation());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getmaintenancePositionCode())) {
                dataMap.put("maintenance_position_code", collectDataEntity.getmaintenancePositionCode());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getMarket_provine())) {
                dataMap.put("market_provine", collectDataEntity.getMarket_provine());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getMarket_city())) {
                dataMap.put("market_city", collectDataEntity.getMarket_city());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getBiz_type())) {
                dataMap.put("biz_type", collectDataEntity.getBiz_type());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getMaterial_id())) {
                dataMap.put("material_id", collectDataEntity.getMaterial_id());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getTemplate_id())) {
                dataMap.put("template_id", collectDataEntity.getTemplate_id());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getTrace_id())) {
                dataMap.put("trace_id", collectDataEntity.getTrace_id());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getBatch_id())) {
                dataMap.put("batch_id", collectDataEntity.getBatch_id());
            }
            if (!TextUtils.isEmpty(collectDataEntity.getAlgorithm_type())) {
                dataMap.put("algorithm_type", collectDataEntity.getAlgorithm_type());
            }
            if (i == 0) {
                dataMap.put("action_id", "19");
                dataMap.put("action_type", "2");
                dataMap.put("time_spent", String.valueOf((System.currentTimeMillis() - CollectConfig.pageStartTime) * 1000));
                StringBuilder sb = new StringBuilder();
                sb.append("点击事件\n");
                JSONObject mapToJson = this.collectUtils.mapToJson(dataMap);
                sb.append(!(mapToJson instanceof JSONObject) ? mapToJson.toString() : NBSJSONObjectInstrumentation.toString(mapToJson));
                MsLogUtil.m7979d("UnicomCollectManager", sb.toString());
            } else if (i == 1) {
                dataMap.put("action_id", "28");
                dataMap.put("action_type", "9");
                dataMap.put("time_spent", "3");
                dataMap.put("storey_code", getStoryCode(collectDataEntity.getCodeId()));
                StringBuilder sb2 = new StringBuilder();
                sb2.append("曝光\n");
                JSONObject mapToJson2 = this.collectUtils.mapToJson(dataMap);
                sb2.append(!(mapToJson2 instanceof JSONObject) ? mapToJson2.toString() : NBSJSONObjectInstrumentation.toString(mapToJson2));
                MsLogUtil.m7979d("UnicomCollectManager", sb2.toString());
            } else {
                dataMap.put("action_id", "1");
                dataMap.put("action_type", "1");
                dataMap.put("time_spent", "0");
                StringBuilder sb3 = new StringBuilder();
                sb3.append("浏览\n");
                JSONObject mapToJson3 = this.collectUtils.mapToJson(dataMap);
                sb3.append(!(mapToJson3 instanceof JSONObject) ? mapToJson3.toString() : NBSJSONObjectInstrumentation.toString(mapToJson3));
                MsLogUtil.m7979d("UnicomCollectManager", sb3.toString());
            }
            NBSAppAgent.customActionStart("androidpoint");
            if (URLEnvironmentConfig.isForPublish()) {
                NBSAppAgent.customActionEnd("androidpoint", "dis", dataMap);
            } else {
                NBSAppAgent.customActionEnd("androidpoint", "test", dataMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "采集方法异常" + e.getMessage());
        }
    }

    private String getStoryCode(String str) throws Exception {
        String[] split;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String str2 : str.split(",")) {
            if (str2.length() >= 3) {
                sb.append(str2.substring(0, 3));
                sb.append(",");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public void clickCollect(CollectDataEntity collectDataEntity, boolean z) {
        try {
            MsLogUtil.m7979d("UnicomCollectManager", "点击事件采集start------------");
            if (collectDataEntity == null) {
                MsLogUtil.m7979d("UnicomCollectManager", "传递数据为空，不执行采集");
                return;
            }
            if (!TextUtils.isEmpty(collectDataEntity.getCodeId()) && z) {
                if (TextUtils.isEmpty(collectDataEntity.getPbName())) {
                    setTransId(collectDataEntity.getCodeId() + CollectConfig.montageTag1 + CollectConfig.montageTag2);
                } else {
                    MsLogUtil.m7979d("UnicomCollectManager", collectDataEntity.getPbName());
                    setTransId(collectDataEntity.getCodeId() + CollectConfig.montageTag1 + collectDataEntity.getPbName());
                }
            }
            collectData(collectDataEntity, 0);
            MsLogUtil.m7979d("UnicomCollectManager", "点击事件采集end------------");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "点击事件采集异常\n" + e.getMessage());
        }
    }

    public void clickCollect(CollectDataEntity collectDataEntity) {
        clickCollect(collectDataEntity, true);
    }

    public void lightCollect(CollectDataEntity collectDataEntity) {
        try {
            MsLogUtil.m7979d("UnicomCollectManager", "曝光事件采集start------------");
            if (collectDataEntity == null) {
                MsLogUtil.m7979d("UnicomCollectManager", "传递数据为空，不执行采集");
                return;
            }
            collectData(collectDataEntity, 1);
            MsLogUtil.m7979d("UnicomCollectManager", "曝光事件采集end------------");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "曝光事件采集异常\n" + e.getMessage());
        }
    }

    public void scanCollect(CollectDataEntity collectDataEntity) {
        try {
            MsLogUtil.m7979d("UnicomCollectManager", "浏览事件采集start------------");
            if (collectDataEntity == null) {
                MsLogUtil.m7979d("UnicomCollectManager", "传递数据为空，不执行采集");
                return;
            }
            collectData(collectDataEntity, 2);
            MsLogUtil.m7979d("UnicomCollectManager", "浏览事件采集end------------");
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "浏览事件采集异常\n" + e.getMessage());
        }
    }

    private Map<String, Object> getDataMap() throws Exception {
        String currentPhoneNumber = App.hasLogined() ? UserManager.getInstance().getCurrentPhoneNumber() : "";
        HashMap hashMap = new HashMap();
        hashMap.put("site_id", "1");
        hashMap.put("uid", currentPhoneNumber);
        hashMap.put("cid", DeviceHelper.getDeviceID(false));
        hashMap.put("client_time", this.collectUtils.formatTime(SystemTimeUtil.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        int width = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getWidth();
        int height = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getHeight();
        hashMap.put("res", width + "x" + height);
        hashMap.put("activity_id", "192");
        hashMap.put("province_code", UserManager.getInstance().getCurrentProvinceCode());
        hashMap.put("city_code", UserManager.getInstance().getCurrentCityCode());
        CollectAddressEntity addressEntity = this.collectUtils.getAddressEntity();
        hashMap.put("gps_province_code", addressEntity.getLocateProvinceCode());
        hashMap.put("gps_city_code", addressEntity.getLocateCityCode());
        hashMap.put("is_outside", "N");
        hashMap.put("business_name", "原生客户端");
        hashMap.put("session_id", CollectConfig.sessionId);
        String str = "";
        String accountTypeUser = LoginManager.getAccountTypeUser();
        if (!TextUtils.isEmpty(accountTypeUser)) {
            if (accountTypeUser.equals("1")) {
                str = "1";
            } else if (accountTypeUser.equals("2")) {
                str = "4";
            } else if (accountTypeUser.equals("3")) {
                str = "2";
            } else if (accountTypeUser.equals("4")) {
                str = "3";
            } else {
                str = accountTypeUser.equals("0") ? "6" : "5";
            }
        }
        hashMap.put("network_type", str);
        hashMap.put("app_version", App.getInstance().getVersion());
        hashMap.put("Install_new_users", "Y".equalsIgnoreCase(CacheDataCenter.getInstance().getPageNewOldUser()) ? "新用户" : "老用户");
        hashMap.put("data_identification", "LZPT");
        return hashMap;
    }

    public void pageOnResume() {
        try {
            CollectConfig.pageStartTime = System.currentTimeMillis();
            MsLogUtil.m7979d("UnicomCollectManager", "页面展示时间" + this.collectUtils.formatTime(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "刷新页面展示时间异常" + e.getMessage());
        }
    }

    public void clearTransId() {
        try {
            setTransId("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTransId(String str) {
        MsLogUtil.m7979d("UnicomCollectManager更新的id", "---\n" + str);
        this.collectUtils.saveTransId(str);
    }

    public String getTransId() {
        return this.collectUtils.getTransId();
    }
}
