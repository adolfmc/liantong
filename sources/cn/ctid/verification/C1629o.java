package cn.ctid.verification;

import android.app.Activity;
import cn.anicert.verification.lib_identify.third.ApplyData;
import cn.anicert.verification.lib_identify.third.QRCodeData;
import cn.anicert.verification.lib_identify.third.Result;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.o */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1629o {
    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(132);
    }

    /* renamed from: a */
    public static native Result<String> m22060a(Activity activity, InterfaceC1616b interfaceC1616b, ApplyData applyData, int i);

    /* renamed from: a */
    public static native Result<String> m22059a(Activity activity, InterfaceC1616b interfaceC1616b, QRCodeData qRCodeData);

    /* renamed from: a */
    public static native Result<String> m22058a(Activity activity, InterfaceC1616b interfaceC1616b, C1628n c1628n);
}
