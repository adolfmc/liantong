package cn.anicert.verification.lib_identify.authcode;

import android.app.Activity;
import cn.anicert.verification.lib_identify.third.Result;
import cn.ctid.verification.InterfaceC1625k;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Service2AuthCode {
    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(0);
    }

    private Service2AuthCode() {
    }

    public static native Result<byte[]> getAuthCodeData(Activity activity, String str, InterfaceC1625k interfaceC1625k);

    public static native Result<byte[]> getAuthCodeData(Activity activity, String str, InterfaceC1625k interfaceC1625k, int i);

    public static native Result<byte[]> onAuth(Activity activity, String str, byte[] bArr);

    public static native Result<String> onOpen(Activity activity, String str);
}
