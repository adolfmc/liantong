package com.megvii.idcardquality;

import android.content.Context;
import com.megvii.idcardquality.IDCardQualityResult;
import com.megvii.licencemanage.sdk.C5332a;
import com.megvii.licensemanager.ILicenseManager;
import java.security.InvalidParameterException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDCardQualityLicenseManager implements ILicenseManager {

    /* renamed from: a */
    private Context f12266a;

    /* renamed from: b */
    private C5332a f12267b;

    public IDCardQualityLicenseManager(Context context) {
        if (context == null) {
            throw new InvalidParameterException("mContext cannot be null");
        }
        this.f12266a = context.getApplicationContext();
        this.f12267b = new C5332a(this.f12266a);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [long, java.lang.String] */
    public long checkCachedLicense() {
        return IDCardQualityAssessment.getVersion();
    }

    public String getContext(String str) {
        return IDCardQualityAssessment.getVersion();
    }

    public String getLastError() {
        return IDCardQualityAssessment.getVersion();
    }

    public String getVersion() {
        return IDCardQualityAssessment.getVersion();
    }

    public long setLicense(String str) {
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_NONE = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_NOIDCARD = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_TILT = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BLUR = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SIZETOOSMALL = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SIZETOOLARGE = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SHADOW = new IDCardQualityResult.IDCardFailedType();
        IDCardQualityResult.IDCardFailedType.$VALUES = new IDCardQualityResult.IDCardFailedType[]{IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_NONE, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_ERRORARGUMENT, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_NOIDCARD, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_WRONGSIDE, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_TILT, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BLUR, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SIZETOOSMALL, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SIZETOOLARGE, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_OUTSIDETHEROI, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW, IDCardQualityResult.IDCardFailedType.QUALITY_FAILED_TYPE_SHADOW};
        return;
    }
}
