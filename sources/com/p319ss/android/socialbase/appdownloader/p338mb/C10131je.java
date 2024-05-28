package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.je */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10131je extends AbstractC10134mb {
    public C10131je(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        String optString = this.f19543ox.optString("s");
        String m6594mb = C10150b.m6594mb(this.f19543ox.optString("ag"), optString);
        String m6594mb2 = C10150b.m6594mb(this.f19543ox.optString("ah"), optString);
        String m6594mb3 = C10150b.m6594mb(this.f19543ox.optString("ai"), optString);
        String m6594mb4 = C10150b.m6594mb(this.f19543ox.optString("aj"), optString);
        Intent intent = new Intent();
        intent.putExtra(m6594mb, this.f19541b);
        intent.putExtra(m6594mb2, "*/*");
        intent.putExtra(m6594mb3, true);
        intent.setAction(m6594mb4);
        intent.addFlags(268435456);
        intent.addFlags(32768);
        return intent;
    }
}
