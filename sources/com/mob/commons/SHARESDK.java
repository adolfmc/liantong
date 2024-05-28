package com.mob.commons;

import cn.sharesdk.framework.ShareSDK;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SHARESDK implements MobProduct {
    @Override // com.mob.commons.MobProduct
    public String getProductTag() {
        return "SHARESDK";
    }

    @Override // com.mob.commons.MobProduct
    public int getSdkver() {
        return ShareSDK.SDK_VERSION_CODE;
    }
}
