package com.sinovatech.unicom.separatemodule.recentmenu;

import com.sinovatech.unicom.basic.myinterface.MyLocationInterface;
import com.sinovatech.unicom.basic.server.ManagerLocation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* compiled from: lambda */
/* renamed from: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$CustomRecentActivity$IMEitwEXUom11j8Ltv-QnC6I-Sg  reason: invalid class name */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final /* synthetic */ class $$Lambda$CustomRecentActivity$IMEitwEXUom11j8LtvQnC6ISg implements MyLocationInterface.CallBack {
    private final /* synthetic */ CustomRecentActivity f$0;

    @Override // com.sinovatech.unicom.basic.myinterface.MyLocationInterface.CallBack
    public final void Success(ManagerLocation.LocationEntity locationEntity) {
        CustomRecentActivity.lambda$onCreate$0(this.f$0, locationEntity);
    }
}
