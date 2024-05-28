package com.p319ss.android.socialbase.appdownloader.p338mb;

import android.content.Context;
import android.content.Intent;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/* renamed from: com.ss.android.socialbase.appdownloader.mb.jb */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10130jb extends AbstractC10134mb {
    public C10130jb(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.p319ss.android.socialbase.appdownloader.p338mb.InterfaceC10128h
    /* renamed from: ox */
    public Intent mo6729ox() {
        Intent intent = new Intent("com.android.filemanager.OPEN_FOLDER");
        intent.putExtra("com.android.filemanager.OPEN_FOLDER", this.f19541b);
        intent.putExtra("com.iqoo.secure", true);
        intent.addFlags(268435456);
        intent.addFlags(32768);
        intent.addFlags(1073741824);
        return intent;
    }
}
