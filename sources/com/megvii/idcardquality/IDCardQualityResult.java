package com.megvii.idcardquality;

import android.graphics.Bitmap;
import android.util.Log;
import com.megvii.idcard.sdk.C5293a;
import com.megvii.idcard.sdk.jni.IDCardApi;
import com.megvii.idcardquality.bean.IDCardAttr;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IDCardQualityResult {

    /* renamed from: a */
    private byte[] f12268a;
    public IDCardAttr attr;

    /* renamed from: b */
    private int f12269b;

    /* renamed from: c */
    private int f12270c;

    /* renamed from: d */
    private C5293a f12271d;
    public List<IDCardFailedType> fails;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum IDCardFailedType {
        QUALITY_FAILED_TYPE_NONE,
        QUALITY_FAILED_TYPE_ERRORARGUMENT,
        QUALITY_FAILED_TYPE_NOIDCARD,
        QUALITY_FAILED_TYPE_WRONGSIDE,
        QUALITY_FAILED_TYPE_TILT,
        QUALITY_FAILED_TYPE_BLUR,
        QUALITY_FAILED_TYPE_SIZETOOSMALL,
        QUALITY_FAILED_TYPE_SIZETOOLARGE,
        QUALITY_FAILED_TYPE_SPECULARHIGHLIGHT,
        QUALITY_FAILED_TYPE_OUTSIDETHEROI,
        QUALITY_FAILED_TYPE_BRIGHTNESSTOOHIGH,
        QUALITY_FAILED_TYPE_BRIGHTNESSTOOLOW,
        QUALITY_FAILED_TYPE_SHADOW
    }

    public IDCardQualityResult(C5293a c5293a, byte[] bArr, int i, int i2) {
        this.f12271d = c5293a;
        this.f12268a = bArr;
        this.f12270c = i2;
        this.f12269b = i;
    }

    public boolean isValid() {
        List<IDCardFailedType> list;
        return (this.attr == null || (list = this.fails) == null || list.size() != 0) ? false : true;
    }

    public Bitmap croppedImageOfIDCard(int i) {
        IDCardAttr iDCardAttr = this.attr;
        if (iDCardAttr == null || iDCardAttr.cornerPoints == null) {
            return null;
        }
        return C5293a.m13716a(this.f12268a, this.f12269b, this.f12270c, C5293a.m13713a(this.attr.cornerPoints, 0.05f), i);
    }

    public Bitmap croppedImageOfPortrait(int i) {
        Log.w("ceshi", "attr===" + this.attr + ", " + this.attr.portraitPoints + ", " + this.attr.side);
        IDCardAttr iDCardAttr = this.attr;
        if (iDCardAttr == null || iDCardAttr.portraitPoints == null || this.attr.side != IDCardAttr.IDCardSide.IDCARD_SIDE_FRONT) {
            return null;
        }
        return C5293a.m13716a(this.f12268a, this.f12269b, this.f12270c, C5293a.m13714a(this.attr.portraitPoints), i);
    }

    public Bitmap croppedImageOfIDCard() {
        return croppedImageOfIDCard(-1);
    }

    public Bitmap croppedImageOfPortrait() {
        return croppedImageOfPortrait(-1);
    }

    public byte[] getImageData(boolean z, int i, int i2, boolean z2, boolean z3, int i3) {
        Bitmap croppedImageOfIDCard;
        if (((z2 || z3) && i3 < 0) || i < 0 || i > 100) {
            return null;
        }
        if (z) {
            croppedImageOfIDCard = croppedImageOfPortrait(i2);
        } else {
            croppedImageOfIDCard = croppedImageOfIDCard(i2);
        }
        if (croppedImageOfIDCard == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        croppedImageOfIDCard.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            byteArrayOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (byteArray == null || byteArray.length == 0) {
                return null;
            }
            if (z2 || z3) {
                if (i3 < 0) {
                    return null;
                }
                return IDCardApi.embedToJpgImgData(byteArray, z2 ? 1 : 0, z3 ? 1 : 0, i3);
            }
            return byteArray;
        } catch (IOException unused) {
            return null;
        }
    }
}
