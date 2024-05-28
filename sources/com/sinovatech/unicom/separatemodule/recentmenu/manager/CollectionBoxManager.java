package com.sinovatech.unicom.separatemodule.recentmenu.manager;

import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity;
import io.objectbox.Box;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectionBoxManager {
    private static final String TAG = "CollectionDataManager";

    public static void saveTabListBox(Box<CollectionTabEntity> box, List<CollectionTabEntity> list) {
        if (box != null) {
            try {
                if (box.query().build().count() > 0) {
                    box.query().build().remove();
                }
                if (list == null || list.size() <= 0) {
                    return;
                }
                box.put(list);
            } catch (Exception e) {
                MsLogUtil.m7977e("CollectionDataManager", "将网络数据保存到本地异常:" + e.getMessage());
            }
        }
    }

    public static List<CollectionTabEntity> getTabListBox(Box<CollectionTabEntity> box) {
        ArrayList arrayList = new ArrayList();
        if (box != null) {
            try {
                return box.query().build().find();
            } catch (Exception e) {
                MsLogUtil.m7977e("CollectionDataManager", "获取本地存储的分类数据异常:" + e.getMessage());
                return arrayList;
            }
        }
        return arrayList;
    }
}
