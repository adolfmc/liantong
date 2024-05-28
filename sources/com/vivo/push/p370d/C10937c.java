package com.vivo.push.p370d;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;
import com.vivo.push.restructure.request.p379a.p380a.PushParcelable;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ProfileInfoDS.java */
/* renamed from: com.vivo.push.d.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10937c implements PushParcelable.InterfaceC10974a<ProfileInfoDS> {
    @Override // com.vivo.push.restructure.request.p379a.p380a.PushParcelable.InterfaceC10974a
    /* renamed from: a */
    public final /* synthetic */ ProfileInfoDS mo5507a(String str) {
        return m5716b(str);
    }

    /* renamed from: b */
    private static ProfileInfoDS m5716b(String str) {
        try {
            return new ProfileInfoDS(new JsonParcel(str));
        } catch (Exception e) {
            LogUtil.m5357a(8101, e.getMessage());
            return null;
        }
    }
}
