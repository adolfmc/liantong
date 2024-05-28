package com.sinovatech.unicom.separatemodule.quanxianshuoming.utils;

import android.util.Log;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthRecord;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Utils {
    public static List<H5AuthRecord> resultList(List<H5AuthRecord> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.size() <= 0) {
            return list;
        }
        for (int i = 0; i < list.size(); i++) {
            H5AuthRecord h5AuthRecord = list.get(i);
            Log.d("这是获取到的业务数据：", i + "：" + h5AuthRecord.toString());
            if (arrayList.size() > 0) {
                boolean z = false;
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (((H5AuthRecord) arrayList.get(i2)).getAppId().equals(h5AuthRecord.getAppId())) {
                        z = true;
                    }
                }
                if (!z) {
                    arrayList.add(h5AuthRecord);
                }
            } else {
                arrayList.add(h5AuthRecord);
            }
        }
        return arrayList;
    }
}
