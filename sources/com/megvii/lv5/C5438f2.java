package com.megvii.lv5;

import android.media.MediaRecorder;
import com.megvii.lv5.sdk.screen.service.MediaProjectionService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.f2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5438f2 implements MediaRecorder.OnErrorListener {

    /* renamed from: a */
    public final /* synthetic */ MediaProjectionService f12598a;

    public C5438f2(MediaProjectionService mediaProjectionService) {
        this.f12598a = mediaProjectionService;
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        AbstractC5421e2 abstractC5421e2 = this.f12598a.f13529k;
        if (abstractC5421e2 != null) {
            abstractC5421e2.mo12888a();
        }
    }
}
