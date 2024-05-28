package cn.anicert.verification.lib_identify.encryptenvelop;

import android.content.Context;
import com.example.sdtverify.sdtVerify;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EncryptUtil {
    sdtVerify sdt = new sdtVerify();

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(4);
    }

    public EncryptUtil(Context context) {
        sdtVerify.setEnvPath(context.getApplicationInfo().nativeLibraryDir);
    }

    public native String getSM2EncryptBase64(byte[] bArr, byte[] bArr2);

    public native byte[] getSM3Hash(byte[] bArr);

    public native String getSM3HashBase64(byte[] bArr);

    public native int signVerify(byte[] bArr, int i, byte[] bArr2);
}
