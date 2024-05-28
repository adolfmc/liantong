package com.megvii.lv5;

import com.megvii.lv5.sdk.detect.actionflash.ActionFlashLivenessActivity;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.e0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC5419e0 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ ActionFlashLivenessActivity f12538a;

    public RunnableC5419e0(ActionFlashLivenessActivity actionFlashLivenessActivity) {
        this.f12538a = actionFlashLivenessActivity;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f12538a.f13354H.setVisibility(0);
    }
}
