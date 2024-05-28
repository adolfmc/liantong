package cn.ctid.verification;

import android.app.Activity;
import android.content.Intent;
import cn.anicert.verification.lib_identify.identification.IctidAuthService;
import cn.anicert.verification.lib_identify.third.IdCardData;
import cn.anicert.verification.lib_identify.third.Result;
import cn.ctid.verification.C1603B;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1620f implements C1603B.InterfaceC1604a {

    /* renamed from: a */
    final /* synthetic */ Result f2678a;

    /* renamed from: b */
    final /* synthetic */ IctidAuthService.OnCallBack f2679b;

    /* renamed from: c */
    final /* synthetic */ Activity f2680c;

    /* renamed from: d */
    final /* synthetic */ InterfaceC1616b f2681d;

    /* renamed from: e */
    final /* synthetic */ IdCardData f2682e;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(228);
    }

    C1620f(Result result, IctidAuthService.OnCallBack onCallBack, Activity activity, InterfaceC1616b interfaceC1616b, IdCardData idCardData) {
        this.f2678a = result;
        this.f2679b = onCallBack;
        this.f2680c = activity;
        this.f2681d = interfaceC1616b;
        this.f2682e = idCardData;
    }

    @Override // cn.ctid.verification.C1603B.InterfaceC1604a
    public native void onActivityResult(int i, int i2, Intent intent);
}
