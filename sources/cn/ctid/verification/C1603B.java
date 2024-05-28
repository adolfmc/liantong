package cn.ctid.verification;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.B */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1603B {

    /* renamed from: a */
    private FragmentC1634t f2663a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: cn.ctid.verification.B$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC1604a {
        void onActivityResult(int i, int i2, Intent intent);
    }

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(87);
    }

    public C1603B(Activity activity) {
        this.f2663a = m22111a(activity);
    }

    /* renamed from: a */
    private native FragmentC1634t m22111a(Activity activity);

    /* renamed from: a */
    private native FragmentC1634t m22110a(FragmentManager fragmentManager);

    /* renamed from: a */
    public native void m22109a(Intent intent, InterfaceC1604a interfaceC1604a);
}
