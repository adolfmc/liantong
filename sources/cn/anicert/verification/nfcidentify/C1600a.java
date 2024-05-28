package cn.anicert.verification.nfcidentify;

import android.app.Activity;
import cn.anicert.verification.nfcidentify.bean.InputAuthIDCard;
import cn.anicert.verification.nfcidentify.bean.OutputAuthIDCard;
import com.example.sdtverify.sdtVerify;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.anicert.verification.nfcidentify.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1600a {

    /* renamed from: a */
    private final boolean f2655a = false;

    /* renamed from: b */
    C1601b f2656b = new C1601b();

    /* renamed from: c */
    sdtVerify f2657c = new sdtVerify();

    /* renamed from: d */
    OutputAuthIDCard f2658d = new OutputAuthIDCard();

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(49);
    }

    public C1600a(Activity activity) {
        sdtVerify.setEnvPath(activity.getApplicationInfo().nativeLibraryDir);
    }

    /* renamed from: a */
    private native String m22116a(String str, int i);

    /* renamed from: a */
    public native OutputAuthIDCard m22117a(InputAuthIDCard inputAuthIDCard);

    /* renamed from: b */
    public native OutputAuthIDCard m22115b(InputAuthIDCard inputAuthIDCard);
}
