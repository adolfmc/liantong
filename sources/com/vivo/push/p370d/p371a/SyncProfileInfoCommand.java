package com.vivo.push.p370d.p371a;

import com.vivo.push.restructure.request.BaseCommand;
import com.vivo.push.restructure.request.p379a.p380a.JsonParcel;

/* renamed from: com.vivo.push.d.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SyncProfileInfoCommand extends BaseCommand<SyncProfileInfoInputDS, SyncProfileInfoInputDS> {
    public SyncProfileInfoCommand(SyncProfileInfoInputDS syncProfileInfoInputDS) {
        super(syncProfileInfoInputDS);
    }

    @Override // com.vivo.push.restructure.request.BaseCommand
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ SyncProfileInfoInputDS mo5521a(JsonParcel jsonParcel) {
        return SyncProfileInfoInputDS.f20938a.mo5505a(jsonParcel);
    }
}
