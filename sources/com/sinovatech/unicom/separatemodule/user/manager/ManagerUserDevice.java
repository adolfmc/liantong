package com.sinovatech.unicom.separatemodule.user.manager;

import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserDevice {
    public static final String DEVICE_TIME = "user_device_time";
    private static ManagerUserDevice managerUserDevice;

    private ManagerUserDevice() {
    }

    public static ManagerUserDevice getInstance() {
        if (managerUserDevice == null) {
            synchronized (ManagerUserDevice.class) {
                if (managerUserDevice == null) {
                    managerUserDevice = new ManagerUserDevice();
                }
            }
        }
        return managerUserDevice;
    }

    public void setDeviceTime(long j) {
        App.getSharePreferenceUtil().putLong(DEVICE_TIME, j);
    }

    public long getDeviceTime() {
        return App.getSharePreferenceUtil().getLong(DEVICE_TIME);
    }

    public void simpleClickLog(String str, String str2, String str3, String str4) {
        PvCurrencyLogUtils.userDevicePvLog(str, str2, str3, str4, "");
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("我的").setPbName(str4).setCodeId(str).setStoreyCode(str.substring(0, 3)).build());
    }

    public void complexClickLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20) {
        PvCurrencyLogUtils.userDevicePvLog(str, str2, str3, str4, str20);
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("我的").setPbName(str4).setCodeId(str).setStoreyCode(str.substring(0, 3)).setItemName(str5).setGoodSid(str6).setActType(str7).setActId(str8).setTargetUrl(str9).setCommodityId(str10).setGoods_onlyid(str11).setMaintenance_position_code(str13).setMaintaining_information(str12).setBiz_Type(str14).setMaterial_id(str15).setTemplate_id(str16).setTrace_id(str17).setBatch_id(str18).setAlgorithm_type(str19).setGoodsType(str20).build(), true);
    }
}
