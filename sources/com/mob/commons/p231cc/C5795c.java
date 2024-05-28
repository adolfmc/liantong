package com.mob.commons.p231cc;

import com.mob.commons.p229a.C5731l;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.network.NetworkHelper;
import java.io.OutputStream;
import java.util.HashMap;

/* renamed from: com.mob.commons.cc.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5795c implements InterfaceC5812q<C5795c> {

    /* renamed from: a */
    private static final NetworkHelper f14279a = new NetworkHelper();

    /* renamed from: a */
    public static String m12451a(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        return f14279a.httpGet(str, hashMap, hashMap2);
    }

    /* renamed from: a */
    public static String m12450a(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        return f14279a.httpPostNew(str, hashMap, hashMap2, networkTimeOut);
    }

    /* renamed from: a */
    public static String m12449a(String str, byte[] bArr, HashMap<String, String> hashMap, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        return f14279a.httpPostWithBytes(str, bArr, hashMap, networkTimeOut);
    }

    /* renamed from: a */
    public static void m12452a(String str, OutputStream outputStream, NetworkHelper.NetworkTimeOut networkTimeOut) throws Throwable {
        f14279a.download(str, outputStream, networkTimeOut);
    }

    /* renamed from: a */
    public static <T> T m12453a(NetCommunicator netCommunicator, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z) throws Throwable {
        return (T) netCommunicator.requestSynchronized(false, hashMap, hashMap2, str, z);
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5795c c5795c, Class<C5795c> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("hGet".equals(str)) {
            try {
                objArr2[0] = m12451a((String) objArr[0], (HashMap) objArr[1], (HashMap) objArr[2]);
            } catch (Throwable th) {
                thArr[0] = th;
                objArr2[0] = null;
            }
            return true;
        } else if ("pst".equals(str)) {
            try {
                objArr2[0] = m12450a((String) objArr[0], (HashMap) objArr[1], (HashMap) objArr[2], (NetworkHelper.NetworkTimeOut) objArr[3]);
            } catch (Throwable th2) {
                thArr[0] = th2;
                objArr2[0] = null;
            }
            return true;
        } else if (C5731l.m12674a("013kUfegi,jThfej9ji1gjelKjgMgi").equals(str)) {
            try {
                objArr2[0] = m12449a((String) objArr[0], (byte[]) objArr[1], (HashMap) objArr[2], (NetworkHelper.NetworkTimeOut) objArr[3]);
            } catch (Throwable th3) {
                thArr[0] = th3;
                objArr2[0] = null;
            }
            return true;
        } else if (C5731l.m12674a("008Jedfegg3fh?fe[eMed").equals(str)) {
            try {
                m12452a((String) objArr[0], (OutputStream) objArr[1], (NetworkHelper.NetworkTimeOut) objArr[2]);
            } catch (Throwable th4) {
                thArr[0] = th4;
                objArr2[0] = null;
            }
            return true;
        } else if (C5731l.m12674a("007Lek3g5effkel-fd").equals(str)) {
            try {
                objArr2[0] = m12453a((NetCommunicator) objArr[0], (HashMap) objArr[1], (HashMap) objArr[2], (String) objArr[3], ((Boolean) objArr[4]).booleanValue());
            } catch (Throwable th5) {
                thArr[0] = th5;
                objArr2[0] = null;
            }
            return true;
        } else {
            return false;
        }
    }
}
