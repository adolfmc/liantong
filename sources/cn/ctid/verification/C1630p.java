package cn.ctid.verification;

import android.app.Activity;
import cn.anicert.verification.lib_identify.third.IdCardData;
import cn.anicert.verification.lib_identify.third.Result;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.p */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1630p {
    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(135);
    }

    /* renamed from: a */
    public static native Result<String> m22056a(Activity activity, InterfaceC1625k interfaceC1625k, byte[] bArr, IdCardData idCardData);

    /* renamed from: a */
    public static native String m22057a();
}
