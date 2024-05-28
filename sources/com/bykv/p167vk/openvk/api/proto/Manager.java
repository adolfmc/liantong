package com.bykv.p167vk.openvk.api.proto;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bykv.vk.openvk.api.proto.Manager */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Manager {
    Loader createLoader(Context context);

    Bridge getBridge(int i);

    ValueSet values();
}
