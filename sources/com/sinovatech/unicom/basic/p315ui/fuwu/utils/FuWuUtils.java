package com.sinovatech.unicom.basic.p315ui.fuwu.utils;

import android.text.TextUtils;
import android.widget.TextView;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsEntity;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.utils.FuWuUtils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FuWuUtils {
    private static boolean isDefault = true;

    public static void setUpdateFuWuTitle(TextView textView, String str) {
        textView.setText(str);
    }

    public static boolean isTrue(int i) {
        ArrayList arrayList = new ArrayList();
        List<MenuEntity> list = FuWuConstant.topList;
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                String isEdit = list.get(i2).getIsEdit();
                if (i2 == 0) {
                    arrayList.add(Integer.valueOf(i2));
                } else if (!TextUtils.isEmpty(isEdit) && isEdit.equals("1")) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
        }
        if (arrayList.size() > 0) {
            if (arrayList.size() == 1) {
                return i != 0;
            } else if (arrayList.size() == 2) {
                return (i == 0 || i == 1) ? false : true;
            } else if (arrayList.size() == 3) {
                return (i == 0 || i == 1 || i == 2) ? false : true;
            } else if (arrayList.size() == 4) {
                return (i == 0 || i == 1 || i == 2 || i == 3) ? false : true;
            } else if (arrayList.size() == 5) {
                return (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) ? false : true;
            } else if (arrayList.size() == 6) {
                return (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5) ? false : true;
            } else if (arrayList.size() == 7) {
                return (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6) ? false : true;
            } else if (arrayList.size() == 8) {
                return (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6 || i == 7) ? false : true;
            } else {
                return true;
            }
        }
        return true;
    }

    public static HomeTabEntity defaultText() {
        HomeTabEntity homeTabEntity = new HomeTabEntity();
        HomeTabEntity.DataDTO.ServicePageCfgCWDTO servicePageCfgCWDTO = new HomeTabEntity.DataDTO.ServicePageCfgCWDTO();
        servicePageCfgCWDTO.setHeadTitle("服务大厅");
        servicePageCfgCWDTO.setAppMenuTip("首页应用");
        servicePageCfgCWDTO.setMoveMenuTip("（按住拖动调整顺序）");
        servicePageCfgCWDTO.setEditMenuTip("（点击编辑进入首页应用自定义）");
        HomeTabEntity.DataDTO dataDTO = new HomeTabEntity.DataDTO();
        dataDTO.setServicePageCfgCW(servicePageCfgCWDTO);
        homeTabEntity.setData(dataDTO);
        return homeTabEntity;
    }

    public static String returnNoNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static void ganZhiLog(String str, String str2, String str3, MarketingBitsEntity marketingBitsEntity) {
        clickCollect(str, str2, str3, returnNoNull(marketingBitsEntity.getGoodsId()), returnNoNull(marketingBitsEntity.getActId()), returnNoNull(marketingBitsEntity.getId()));
    }

    public static void clickCollect(String str, String str2, String str3) {
        clickCollect(str, str2, str3, "", "", "");
    }

    public static void clickCollect(String str, String str2, String str3, String str4, String str5, String str6) {
        MsLogUtil.m7979d("服务页传递的感知数据：", "transId=" + str + ",titleName=" + str2 + ",url=" + str3 + ",goodsId=" + str4 + ",actId=" + str5 + ",commodity_id=" + str6 + ",");
        String str7 = "";
        if (!TextUtils.isEmpty(str) && str.length() >= 3) {
            str7 = str.substring(0, 3);
        }
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("服务").setPbName(str2).setStoreyCode(str7).setGoodSid(str4).setCodeId(str).setActId(str5).setCommodityId(str6).setTargetUrl(str3).build());
    }

    public static void lightCollect(String str, String str2) {
        UnicomCollectManager.getInstance().lightCollect(CollectDataEntity.newBuilder().setPageName("服务").setExposure_Name(str2).setCodeId(str).build());
    }
}
