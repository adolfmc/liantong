package cn.sharesdk.wework.utils;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import cn.sharesdk.framework.utils.SSDKLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.DeflaterInputStream;
import java.util.zip.InflaterInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OpenDataUtils {
    /* renamed from: a */
    public static byte[] m21161a(String str, String str2) {
        try {
            return m21157b(m21160a(str.getBytes("UTF-8")), str2);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* renamed from: a */
    public static String m21159a(byte[] bArr, String str) {
        try {
            return new String(m21158b(m21156c(bArr, str)), "UTF-8");
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return "";
        }
    }

    /* renamed from: b */
    public static byte[] m21157b(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(m21162a(str).getBytes("UTF-8"), C0108a.f85c);
            Cipher cipher = Cipher.getInstance(C0108a.f87e);
            cipher.init(1, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* renamed from: a */
    private static String m21162a(String str) {
        if (str.length() >= 32) {
            return str.substring(0, 32);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < 32) {
            sb.append('0');
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static byte[] m21156c(byte[] bArr, String str) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(m21162a(str).getBytes("UTF-8"), C0108a.f85c);
            Cipher cipher = Cipher.getInstance(C0108a.f87e);
            cipher.init(2, secretKeySpec);
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            SSDKLog.m21740b().m21737b(e);
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m21160a(byte[] bArr) throws IOException {
        Closeable[] closeableArr = {null, null, null};
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            closeableArr[1] = byteArrayInputStream;
            DeflaterInputStream deflaterInputStream = new DeflaterInputStream(byteArrayInputStream);
            closeableArr[0] = deflaterInputStream;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closeableArr[2] = byteArrayOutputStream;
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = deflaterInputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (Closeable closeable : closeableArr) {
                closeable.close();
            }
            return byteArray;
        } catch (Throwable th) {
            for (Closeable closeable2 : closeableArr) {
                try {
                    closeable2.close();
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21737b(th2);
                }
            }
            throw th;
        }
    }

    /* renamed from: b */
    public static byte[] m21158b(byte[] bArr) throws IOException {
        Closeable[] closeableArr = {null, null, null};
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            closeableArr[1] = byteArrayInputStream;
            InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
            closeableArr[0] = inflaterInputStream;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closeableArr[2] = byteArrayOutputStream;
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = inflaterInputStream.read(bArr2);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
            byteArrayOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (Closeable closeable : closeableArr) {
                closeable.close();
            }
            return byteArray;
        } catch (Throwable th) {
            for (Closeable closeable2 : closeableArr) {
                try {
                    closeable2.close();
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21737b(th2);
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public static long m21164a(Context context, String str, String str2, Bundle bundle) {
        Uri.Builder scheme = new Uri.Builder().scheme("content");
        Uri build = scheme.authority(str2 + ".wwapi").appendPath("bundle").appendQueryParameter("pakage", str).build();
        SSDKLog.m21740b().m21744a("wwapi addBundle", build.toString());
        ContentValues contentValues = new ContentValues();
        contentValues.put("data", m21163a(bundle));
        return ContentUris.parseId(context.getContentResolver().insert(build, contentValues));
    }

    /* renamed from: a */
    public static byte[] m21163a(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return marshall;
    }
}
