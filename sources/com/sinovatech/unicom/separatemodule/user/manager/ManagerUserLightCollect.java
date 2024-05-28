package com.sinovatech.unicom.separatemodule.user.manager;

import android.text.TextUtils;
import android.view.View;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserLightCollect {
    private static ManagerUserLightCollect manager;
    private Map<String, List<UserLightEntity>> hashMap = new HashMap();
    private Map<String, View> viewMap = new HashMap();

    private ManagerUserLightCollect() {
    }

    public static ManagerUserLightCollect getInstance() {
        if (manager == null) {
            synchronized (ManagerUserLightCollect.class) {
                if (manager == null) {
                    manager = new ManagerUserLightCollect();
                }
            }
        }
        return manager;
    }

    public Map<String, List<UserLightEntity>> getUserMap() {
        return this.hashMap;
    }

    public Map<String, View> getViewMap() {
        return this.viewMap;
    }

    public void clearLightMap() {
        Map<String, List<UserLightEntity>> map = this.hashMap;
        if (map != null) {
            map.clear();
        }
    }

    public void lightCollect() {
        try {
            Map<String, List<UserLightEntity>> userMap = getUserMap();
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, List<UserLightEntity>> entry : userMap.entrySet()) {
                String key = entry.getKey();
                List<UserLightEntity> value = entry.getValue();
                MsLogUtil.m7979d("UnicomCollectManager", key + "楼层");
                if (isShow(key)) {
                    arrayList.addAll(value);
                    MsLogUtil.m7979d("UnicomCollectManager", key + "曝光");
                } else if (UserDataSourceManager.HEADER.equalsIgnoreCase(key)) {
                    arrayList.addAll(value);
                    MsLogUtil.m7979d("UnicomCollectManager", key + "头部布局，直接曝光");
                } else {
                    MsLogUtil.m7979d("UnicomCollectManager", key + "未曝光");
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            StringBuffer stringBuffer3 = new StringBuffer();
            StringBuffer stringBuffer4 = new StringBuffer();
            StringBuffer stringBuffer5 = new StringBuffer();
            StringBuffer stringBuffer6 = new StringBuffer();
            StringBuffer stringBuffer7 = new StringBuffer();
            StringBuffer stringBuffer8 = new StringBuffer();
            StringBuffer stringBuffer9 = new StringBuffer();
            StringBuffer stringBuffer10 = new StringBuffer();
            StringBuffer stringBuffer11 = new StringBuffer();
            StringBuffer stringBuffer12 = new StringBuffer();
            StringBuffer stringBuffer13 = new StringBuffer();
            StringBuffer stringBuffer14 = new StringBuffer();
            StringBuffer stringBuffer15 = new StringBuffer();
            StringBuffer stringBuffer16 = new StringBuffer();
            StringBuffer stringBuffer17 = new StringBuffer();
            StringBuffer stringBuffer18 = new StringBuffer();
            StringBuffer stringBuffer19 = new StringBuffer();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                UserLightEntity userLightEntity = (UserLightEntity) it.next();
                appendValues(stringBuffer, userLightEntity.getTitleName());
                appendValued(stringBuffer2, userLightEntity.getTrainsId());
                appendValue(stringBuffer3, userLightEntity.getActType());
                appendValue(stringBuffer4, userLightEntity.getActId());
                appendValue(stringBuffer5, userLightEntity.getCommodity_id());
                appendValue(stringBuffer6, userLightEntity.getBusiness_type());
                appendValue(stringBuffer7, userLightEntity.getTarget_url());
                appendValue(stringBuffer8, userLightEntity.getManrong_activity());
                appendValue(stringBuffer9, userLightEntity.getManrong_type());
                appendValue(stringBuffer10, userLightEntity.getGoods_onlyid());
                appendValue(stringBuffer11, userLightEntity.getMaintaining_information());
                appendValue(stringBuffer12, userLightEntity.getMaintenance_position_code());
                appendValue(stringBuffer13, userLightEntity.getBiz_type());
                StringBuffer stringBuffer20 = stringBuffer13;
                StringBuffer stringBuffer21 = stringBuffer14;
                appendValue(stringBuffer21, userLightEntity.getMaterial_id());
                stringBuffer14 = stringBuffer21;
                StringBuffer stringBuffer22 = stringBuffer15;
                appendValue(stringBuffer22, userLightEntity.getTemplate_id());
                stringBuffer15 = stringBuffer22;
                StringBuffer stringBuffer23 = stringBuffer16;
                appendValue(stringBuffer23, userLightEntity.getTrace_id());
                stringBuffer16 = stringBuffer23;
                StringBuffer stringBuffer24 = stringBuffer17;
                appendValue(stringBuffer24, userLightEntity.getBatch_id());
                stringBuffer17 = stringBuffer24;
                StringBuffer stringBuffer25 = stringBuffer18;
                appendValue(stringBuffer25, userLightEntity.getAlgorithm_type());
                appendValue(stringBuffer19, userLightEntity.getGoodsType());
                stringBuffer18 = stringBuffer25;
                it = it;
                stringBuffer13 = stringBuffer20;
            }
            UnicomCollectManager.getInstance().lightCollect(CollectDataEntity.newBuilder().setExposure_Name(stringBuffer.toString()).setPageName("我的").setCodeId(stringBuffer2.toString()).setActType(stringBuffer3.toString()).setActId(stringBuffer4.toString()).setCommodityId(stringBuffer5.toString()).setBusinessType(stringBuffer6.toString()).setTargetUrl(stringBuffer7.toString()).setManrongActivity(stringBuffer8.toString()).setManrongType(stringBuffer9.toString()).setGoods_onlyid(stringBuffer10.toString()).setMaintaining_information(stringBuffer11.toString()).setMaintenance_position_code(stringBuffer12.toString()).setBiz_Type(stringBuffer13.toString()).setMaterial_id(stringBuffer14.toString()).setTemplate_id(stringBuffer15.toString()).setTrace_id(stringBuffer16.toString()).setBatch_id(stringBuffer17.toString()).setAlgorithm_type(stringBuffer18.toString()).setGoodsType(stringBuffer19.toString()).build());
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("UnicomCollectManager", "我的页面曝光异常" + e.getMessage());
        }
    }

    private void appendValue(StringBuffer stringBuffer, String str) {
        try {
            str = (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) ? "-" : "-";
            if (stringBuffer.length() > 0) {
                stringBuffer.append("@");
            }
            stringBuffer.append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendValued(StringBuffer stringBuffer, String str) {
        try {
            str = (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) ? "-" : "-";
            if (stringBuffer.length() > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void appendValues(StringBuffer stringBuffer, String str) {
        try {
            str = (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) ? "-" : "-";
            if (stringBuffer.length() > 0) {
                stringBuffer.append("@@@");
            }
            stringBuffer.append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCollectData(String str, List<UserLightEntity> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getUserMap().put(str, list);
    }

    public void addView(String str, View view) {
        try {
            if (this.viewMap == null) {
                return;
            }
            this.viewMap.put(str, view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isShow(String str) {
        try {
            if (this.viewMap == null || this.viewMap.size() <= 0) {
                return false;
            }
            return UIUtils.checkIsVisible(App.getInstance(), this.viewMap.get(str)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
