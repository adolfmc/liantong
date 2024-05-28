package com.vivo.push;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ClientSdkQueryResultDS.java */
/* renamed from: com.vivo.push.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10961h implements PushParcelable.InterfaceC10974a<ClientSdkQueryResultDS> {
    @Override // com.vivo.push.restructure.request.p379a.p380a.PushParcelable.InterfaceC10974a
    /* renamed from: a */
    public final /* synthetic */ ClientSdkQueryResultDS mo5507a(String str) {
        return m5661b(str);
    }

    /* renamed from: b */
    private static ClientSdkQueryResultDS m5661b(String str) {
        try {
            return new ClientSdkQueryResultDS(new JsonParcel(str));
        } catch (Exception e) {
            LogUtil.m5357a(8101, e.getMessage());
            return null;
        }
    }
}
