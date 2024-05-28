package com.sinovatech.unicom.separatemodule.recentmenu.function;

import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import io.reactivex.functions.Function;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeleteItemFunction implements Function<String, Boolean> {
    @Override // io.reactivex.functions.Function
    public Boolean apply(String str) {
        try {
            if (RecentStateEntity.getInstance(str).isSuccess()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
