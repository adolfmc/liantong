package com.huawei.agconnect.core;

import android.content.Context;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ServiceRegistrar {
    List<Service> getServices(Context context);

    void initialize(Context context);
}
