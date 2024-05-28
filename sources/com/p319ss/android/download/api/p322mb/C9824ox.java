package com.p319ss.android.download.api.p322mb;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.ContextCompat;
import com.p319ss.android.download.api.config.InterfaceC9795gm;
import com.p319ss.android.download.api.config.InterfaceC9804lz;

/* renamed from: com.ss.android.download.api.mb.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9824ox implements InterfaceC9804lz {

    /* renamed from: mb */
    private InterfaceC9795gm f18769mb;

    @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
    /* renamed from: mb */
    public void mo7901mb(@NonNull Activity activity, @NonNull String[] strArr, InterfaceC9795gm interfaceC9795gm) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.f18769mb = interfaceC9795gm;
            activity.requestPermissions(strArr, 1);
        } else if (interfaceC9795gm != null) {
            interfaceC9795gm.mo7541mb();
        }
    }

    @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
    /* renamed from: mb */
    public boolean mo7900mb(@Nullable Context context, @NonNull String str) {
        return context != null && ContextCompat.checkSelfPermission(context, str) == 0;
    }

    @Override // com.p319ss.android.download.api.config.InterfaceC9804lz
    /* renamed from: mb */
    public void mo7902mb(@NonNull Activity activity, int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        InterfaceC9795gm interfaceC9795gm;
        if (iArr.length <= 0 || (interfaceC9795gm = this.f18769mb) == null) {
            return;
        }
        if (iArr[0] == -1) {
            interfaceC9795gm.mo7540mb(strArr[0]);
        } else if (iArr[0] == 0) {
            interfaceC9795gm.mo7541mb();
        }
    }
}
