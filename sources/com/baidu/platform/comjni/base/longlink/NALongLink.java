package com.baidu.platform.comjni.base.longlink;

import android.util.Log;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.longlink.ELongLinkStatus;
import com.baidu.platform.comapi.longlink.LongLinkDataCallback;
import com.baidu.platform.comapi.longlink.LongLinkFileData;
import com.baidu.platform.comjni.JNIBaseApi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NALongLink extends JNIBaseApi {

    /* renamed from: a */
    private static Map<Integer, LinkedList<Object>> f8073a = new ConcurrentHashMap();

    /* renamed from: b */
    private static ELongLinkStatus[] f8074b = {ELongLinkStatus.OK, ELongLinkStatus.SendFormatError, ELongLinkStatus.SendUnRegistered, ELongLinkStatus.SendLimited, ELongLinkStatus.SendDataLenLimited, ELongLinkStatus.SendInvalidReqID, ELongLinkStatus.ResultConnectError, ELongLinkStatus.ResultSendError, ELongLinkStatus.ResultTimeout, ELongLinkStatus.ResultServerError, ELongLinkStatus.CloudStop, ELongLinkStatus.CloudRestart};

    public static long create() {
        return nativeCreate();
    }

    public static boolean init(long j, String str, String str2) {
        return nativeInit(j, str, str2);
    }

    private static native long nativeCreate();

    private static native boolean nativeInit(long j, String str, String str2);

    private static native boolean nativeRegister(long j, int i);

    private static native int nativeRelease(long j);

    private static native int nativeSendData(long j, int i, int i2, byte[] bArr);

    private static native int nativeSendFileData(long j, int i, int i2, String str, ArrayList<LongLinkFileData> arrayList);

    private static native boolean nativeStart(long j);

    private static native boolean nativeStop(long j);

    private static native boolean nativeUnRegister(long j, int i);

    public static boolean onJNILongLinkDataCallback(int i, int i2, int i3, byte[] bArr, boolean z) {
        LinkedList linkedList;
        Log.e("JNILongLink", "onJNILongLinkDataCallback:" + i + " status:" + i2 + " reqId:" + i3 + " isPush:" + z);
        if (i2 < 0 || i2 >= f8074b.length) {
            Log.e("JNILongLink", "invalid status = " + i2);
            if (C2981b.m18067e()) {
                throw new IndexOutOfBoundsException();
            }
            return false;
        }
        if (bArr == null || bArr.length <= 0) {
            bArr = new byte[0];
        }
        synchronized (NALongLink.class) {
            LinkedList<Object> linkedList2 = f8073a.get(Integer.valueOf(i));
            linkedList = linkedList2 != null ? new LinkedList(linkedList2) : null;
        }
        if (linkedList == null || linkedList.size() <= 0) {
            return true;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next != null) {
                try {
                    if (next instanceof LongLinkDataCallback) {
                        ELongLinkStatus eLongLinkStatus = f8074b[i2];
                        eLongLinkStatus.setRequestId(i3);
                        String name = next.getClass().getName();
                        Log.d("JNILongLink", "className = " + name);
                        ((LongLinkDataCallback) next).onReceiveData(eLongLinkStatus, i3, bArr, z);
                        Log.d("JNILongLink", "className = " + name + "done");
                    }
                } catch (Exception e) {
                    Log.e("JNILongLink", "className = " + next.getClass().getName() + ",exception = " + e.toString());
                    if (C2981b.m18067e()) {
                        throw e;
                    }
                }
            }
        }
        return true;
    }

    public static boolean register(long j, int i, Object obj) {
        String str;
        StringBuilder sb;
        if (obj != null) {
            str = "JNILongLink";
            sb = new StringBuilder();
            sb.append("register moduleId = ");
            sb.append(i);
            sb.append(", callback = ");
            sb.append(obj.getClass().getName());
        } else {
            str = "JNILongLink";
            sb = new StringBuilder();
            sb.append("register moduleId = ");
            sb.append(i);
            sb.append(", callback = ");
            sb.append(obj);
        }
        Log.e(str, sb.toString());
        boolean z = false;
        synchronized (NALongLink.class) {
            LinkedList<Object> linkedList = f8073a.get(Integer.valueOf(i));
            if (linkedList == null) {
                LinkedList<Object> linkedList2 = new LinkedList<>();
                linkedList2.add(obj);
                f8073a.put(Integer.valueOf(i), linkedList2);
                z = true;
            } else if (!linkedList.contains(obj)) {
                linkedList.add(obj);
                f8073a.put(Integer.valueOf(i), linkedList);
            }
        }
        if (z) {
            return nativeRegister(j, i);
        }
        return true;
    }

    public static int release(long j) {
        return nativeRelease(j);
    }

    public static int sendData(long j, int i, int i2, byte[] bArr) {
        return nativeSendData(j, i, i2, bArr);
    }

    public static int sendFileData(long j, int i, int i2, String str, ArrayList<LongLinkFileData> arrayList) {
        return nativeSendFileData(j, i, i2, str, arrayList);
    }

    public static boolean start(long j) {
        return nativeStart(j);
    }

    public static boolean stop(long j) {
        return nativeStop(j);
    }

    public static boolean unRegister(long j, int i, Object obj) {
        String str;
        StringBuilder sb;
        LinkedList<Object> linkedList;
        if (obj != null) {
            str = "JNILongLink";
            sb = new StringBuilder();
            sb.append("unegister moduleId = ");
            sb.append(i);
            sb.append(", callback = ");
            sb.append(obj.getClass().getName());
        } else {
            str = "JNILongLink";
            sb = new StringBuilder();
            sb.append("unregister moduleId = ");
            sb.append(i);
            sb.append(", callback = ");
            sb.append(obj);
        }
        Log.e(str, sb.toString());
        synchronized (NALongLink.class) {
            linkedList = f8073a.get(Integer.valueOf(i));
            if (linkedList != null) {
                if (obj != null) {
                    linkedList.remove(obj);
                }
                if (linkedList.isEmpty()) {
                    f8073a.remove(Integer.valueOf(i));
                }
            }
        }
        if (linkedList != null) {
            if (linkedList.isEmpty()) {
                return nativeUnRegister(j, i);
            }
            return true;
        }
        return false;
    }
}
