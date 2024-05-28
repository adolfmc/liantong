package cn.ctid.verification;

import android.app.Activity;
import android.content.Context;
import cn.anicert.verification.lib_identify.encryptenvelop.EncryptUtil;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1615a implements InterfaceC1616b {

    /* renamed from: a */
    private InterfaceC1625k f2669a;

    /* renamed from: b */
    private EncryptUtil f2670b;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(221);
    }

    public C1615a(Activity activity, InterfaceC1625k interfaceC1625k) {
        this.f2670b = new EncryptUtil((Context) C1611H.m22085b(activity));
        this.f2669a = interfaceC1625k;
    }

    /* renamed from: a */
    public native InterfaceC1625k m22075a();

    @Override // cn.ctid.verification.InterfaceC1616b
    /* renamed from: a */
    public native String mo22074a(Object obj);
}
