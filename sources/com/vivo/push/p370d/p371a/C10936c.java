package com.vivo.push.p370d.p371a;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SyncProfileInfoInputDS.java */
/* renamed from: com.vivo.push.d.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10936c implements JsonParcelable.InterfaceC10973a<SyncProfileInfoInputDS> {
    @Override // com.vivo.push.restructure.request.p379a.p380a.JsonParcelable.InterfaceC10973a
    /* renamed from: a */
    public final /* synthetic */ SyncProfileInfoInputDS mo5505a(JsonParcel jsonParcel) {
        return m5718b(jsonParcel);
    }

    /* renamed from: b */
    private static SyncProfileInfoInputDS m5718b(JsonParcel jsonParcel) {
        try {
            return new SyncProfileInfoInputDS(jsonParcel);
        } catch (Exception e) {
            LogUtil.m5357a(8101, e.getMessage());
            return null;
        }
    }
}
