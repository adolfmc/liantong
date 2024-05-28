package cn.ctid.verification;

import android.app.Activity;
import cn.anicert.verification.beans.Result;
import com.tfd.sdk.LF8bOvWP4;
import java.util.concurrent.CountDownLatch;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.w */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC1638w<T> implements InterfaceC1641z<Result<T>> {

    /* renamed from: a */
    protected Activity f2702a;

    /* renamed from: b */
    protected C1639x f2703b = new C1639x();

    /* renamed from: c */
    protected Result<T> f2704c = new Result<>();

    /* renamed from: d */
    protected CountDownLatch f2705d;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(235);
    }

    public AbstractC1638w(Activity activity) {
        this.f2702a = (Activity) C1611H.m22085b(activity);
    }

    @Override // cn.ctid.verification.InterfaceC1641z
    /* renamed from: a */
    public native C1639x mo22040a();

    /* renamed from: b */
    protected native void m22051b();

    /* renamed from: c */
    public native Result<T> m22050c();

    /* renamed from: d */
    protected native void m22049d();
}
