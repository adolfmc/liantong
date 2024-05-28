package cn.sharesdk.sina.weibo.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.res.ColorStateList;
import android.util.StateSet;
import cn.sharesdk.framework.utils.AppUtils;
import com.mob.tools.utils.Data;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Utils.java */
/* renamed from: cn.sharesdk.sina.weibo.sdk.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1809a {
    /* renamed from: a */
    public static ColorStateList m21613a(int i, int i2) {
        return new ColorStateList(new int[][]{new int[]{16842919}, new int[]{16842913}, new int[]{16842908}, StateSet.WILD_CARD}, new int[]{i2, i2, i2, i});
    }

    /* renamed from: a */
    public static String m21612a(Context context, String str) {
        try {
            PackageInfo m21715b = AppUtils.m21715b(str, 64);
            for (int i = 0; i < m21715b.signatures.length; i++) {
                byte[] byteArray = m21715b.signatures[i].toByteArray();
                if (byteArray != null) {
                    return Data.MD5(byteArray);
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m21611a(byte[] bArr) {
        boolean z;
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        byte[] bArr2 = new byte[((bArr.length + 2) / 3) * 4];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            int i3 = (bArr[i] & 255) << 8;
            int i4 = i + 1;
            boolean z2 = true;
            if (i4 < bArr.length) {
                i3 |= bArr[i4] & 255;
                z = true;
            } else {
                z = false;
            }
            int i5 = i3 << 8;
            int i6 = i + 2;
            if (i6 < bArr.length) {
                i5 |= bArr[i6] & 255;
            } else {
                z2 = false;
            }
            int i7 = 64;
            bArr2[i2 + 3] = (byte) charArray[z2 ? i5 & 63 : 64];
            int i8 = i5 >> 6;
            int i9 = i2 + 2;
            if (z) {
                i7 = i8 & 63;
            }
            bArr2[i9] = (byte) charArray[i7];
            int i10 = i8 >> 6;
            bArr2[i2 + 1] = (byte) charArray[i10 & 63];
            bArr2[i2 + 0] = (byte) charArray[(i10 >> 6) & 63];
            i += 3;
            i2 += 4;
        }
        return bArr2;
    }
}
