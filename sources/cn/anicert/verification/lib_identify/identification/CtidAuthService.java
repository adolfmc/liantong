package cn.anicert.verification.lib_identify.identification;

import android.app.Activity;
import android.graphics.Bitmap;
import cn.anicert.verification.lib_identify.identification.IctidAuthService;
import cn.anicert.verification.lib_identify.third.CtidNum;
import cn.anicert.verification.lib_identify.third.Result;
import cn.ctid.verification.C1611H;
import cn.ctid.verification.C1615a;
import cn.ctid.verification.C1624j;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CtidAuthService implements IctidAuthService {

    /* renamed from: a */
    private Activity f2600a;

    /* renamed from: b */
    private C1615a f2601b;

    /* renamed from: c */
    public IctidAuthService.OnCallBack f2602c;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(166);
    }

    public CtidAuthService(Activity activity) {
        this.f2600a = (Activity) C1611H.m22085b(activity);
        this.f2601b = new C1615a(activity, new C1624j(activity.getApplicationContext()));
    }

    /* renamed from: a */
    private static native Bitmap m22155a(int i, float f, String str, Bitmap bitmap);

    /* renamed from: a */
    private static native boolean m22154a(String str);

    public static native Result<CtidNum> getCtidNum(String str);

    public native Result<Bitmap> createQRCodeImage(String str, float f, int i);

    public native Result<String> getApplyData(IctidAuthService.ApplyData applyData);

    public native Result<String> getAuthCodeData(String str);

    public native Result<String> getAuthCodeData(String str, int i);

    public native Result<String> getAuthIDCardData(String str, IctidAuthService.IdCardData idCardData);

    public native Result<String> getAuthIDCardDataVer();

    public native Result<String> getAuthQRCodeData(String str, IctidAuthService.QRCodeData qRCodeData);

    public native Result<String> getEnrollmentIDCardData(String str, IctidAuthService.IdCardData idCardData);

    public native void getLocalCtid(IctidAuthService.ApplyIDCardData applyIDCardData, IctidAuthService.OnCallBack onCallBack);

    public native Result<String> getReqQRCodeData(String str, IctidAuthService.ReqCodeData reqCodeData);
}
