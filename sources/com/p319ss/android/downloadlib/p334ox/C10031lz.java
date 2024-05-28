package com.p319ss.android.downloadlib.p334ox;

import android.os.Build;
import android.support.annotation.NonNull;
import com.p319ss.android.downloadad.api.p324mb.InterfaceC9836mb;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.utils.C10049hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.ox.lz */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10031lz {
    /* renamed from: mb */
    public static boolean m7128mb(@NonNull InterfaceC9836mb interfaceC9836mb) {
        return C10152hj.m6577hj() && Build.VERSION.SDK_INT < 29 && C9940x.m7369jb() != null && C9940x.m7369jb().mo7935mb() && C10049hj.m7071mb(interfaceC9836mb).optInt("invoke_app_form_background_switch") == 1 && interfaceC9836mb.mo7492e();
    }
}
