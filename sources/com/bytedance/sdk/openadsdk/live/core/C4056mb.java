package com.bytedance.sdk.openadsdk.live.core;

import com.bytedance.android.live.base.api.ILiveHostActionParam;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.sdk.openadsdk.live.core.mb */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4056mb implements ILiveHostActionParam {

    /* renamed from: mb */
    private ITTLiveHostAction f9659mb;

    public C4056mb(ITTLiveHostAction iTTLiveHostAction) {
        this.f9659mb = iTTLiveHostAction;
    }

    @Override // com.bytedance.android.live.base.api.ILiveHostActionParam
    public void logEvent(boolean z, String str, String str2, Map<String, String> map) {
        ITTLiveHostAction iTTLiveHostAction = this.f9659mb;
        if (iTTLiveHostAction != null) {
            iTTLiveHostAction.logEvent(z, str, str2, map);
        }
    }
}
