package com.mob.commons.dialog.entity;

import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.utils.HashonHelper;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BaseEntity implements EverythingKeeper, Serializable {
    public String toJSONString() {
        return HashonHelper.fromObject(this);
    }
}
