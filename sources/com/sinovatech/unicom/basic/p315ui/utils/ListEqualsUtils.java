package com.sinovatech.unicom.basic.p315ui.utils;

import android.text.TextUtils;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.utils.ListEqualsUtils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ListEqualsUtils {
    public static boolean isEquals(List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> list, List<HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO> list2) {
        if (list == null || list2 == null || list.size() == 0 || list2.size() == 0 || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO tabCfgArrayDTO = list.get(i);
            HomeTabEntity.DataDTO.IndexSelectedTabDTO.TabCfgArrayDTO tabCfgArrayDTO2 = list2.get(i);
            if (tabCfgArrayDTO == null || tabCfgArrayDTO2 == null || !TextUtils.equals(tabCfgArrayDTO.getNavName(), tabCfgArrayDTO2.getNavName()) || !TextUtils.equals(tabCfgArrayDTO.getH5Url(), tabCfgArrayDTO2.getH5Url()) || !TextUtils.equals(tabCfgArrayDTO.getNavCode(), tabCfgArrayDTO2.getNavCode()) || !TextUtils.equals(tabCfgArrayDTO.getNavUrl(), tabCfgArrayDTO2.getNavUrl())) {
                return false;
            }
        }
        return true;
    }
}
