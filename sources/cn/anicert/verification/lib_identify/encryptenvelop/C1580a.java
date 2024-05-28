package cn.anicert.verification.lib_identify.encryptenvelop;

import android.content.Context;
import com.example.sdtverify.sdtVerify;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.anicert.verification.lib_identify.encryptenvelop.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1580a {

    /* renamed from: a */
    sdtVerify f2599a = new sdtVerify();

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(8);
    }

    public C1580a(Context context) {
        sdtVerify.setEnvPath(context.getApplicationInfo().nativeLibraryDir);
    }

    /* renamed from: a */
    private native byte[] m22162a(String str) throws Exception;

    /* renamed from: b */
    private native byte[] m22160b(String str);

    /* renamed from: a */
    public native String m22161a(byte[] bArr);

    /* renamed from: a */
    public native byte[] m22163a(int i, byte[] bArr, byte[] bArr2);
}
