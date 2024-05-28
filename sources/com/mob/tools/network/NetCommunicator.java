package com.mob.tools.network;

import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import com.mob.MobSDK;
import com.mob.commons.C5831e;
import com.mob.commons.C5871t;
import com.mob.commons.C5873u;
import com.mob.commons.C5895z;
import com.mob.commons.MobProduct;
import com.mob.commons.p229a.C5731l;
import com.mob.mcl.p234b.C5906a;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.MobRSA;
import com.mob.tools.utils.UIHandler;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class NetCommunicator implements PublicMemberKeeper {
    public static final String KEY_DUID_PREVIOUS = "duidPrevious";
    public static final String KEY_IS_MODIFIED = "isModified";

    /* renamed from: b */
    private BigInteger f15006b;

    /* renamed from: c */
    private BigInteger f15007c;

    /* renamed from: d */
    private MobRSA f15008d;

    /* renamed from: e */
    private NetworkHelper f15009e = new NetworkHelper();

    /* renamed from: f */
    private NetworkHelper.NetworkTimeOut f15010f = new NetworkHelper.NetworkTimeOut();

    /* renamed from: g */
    private ThreadPoolExecutor f15011g;
    public static final String KEY_DUID = C5731l.m12674a("0040edehejed");

    /* renamed from: a */
    private static final ThreadPoolExecutor f15005a = new ThreadPoolExecutor(3, 20, 60, TimeUnit.SECONDS, new LinkedBlockingDeque());

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Callback<T> implements PublicMemberKeeper {
        public void onResultError(Throwable th) {
        }

        public void onResultOk(T t) {
        }
    }

    public NetCommunicator(int i, String str, String str2) {
        this.f15008d = new MobRSA(i);
        this.f15006b = new BigInteger(str, 16);
        this.f15007c = new BigInteger(str2, 16);
        NetworkHelper.NetworkTimeOut networkTimeOut = this.f15010f;
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 5000;
        this.f15011g = f15005a;
    }

    public void addTcpIntercept(String str) {
        try {
            C5906a.m12085a(str);
        } catch (Throwable unused) {
        }
    }

    public void removeTcpIntercept(String str) {
        try {
            C5906a.m12073b(str);
        } catch (Throwable unused) {
        }
    }

    public void setThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        this.f15011g = threadPoolExecutor;
    }

    public <T> void request(HashMap<String, Object> hashMap, String str, boolean z, Callback<T> callback) {
        request(true, null, hashMap, str, z, callback);
    }

    public <T> void request(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z, Callback<T> callback) {
        request(true, hashMap, hashMap2, str, z, callback);
    }

    public <T> void request(final boolean z, final HashMap<String, String> hashMap, final HashMap<String, Object> hashMap2, final String str, final boolean z2, final Callback<T> callback) {
        this.f15011g.execute(new AbstractRunnableC6217h() { // from class: com.mob.tools.network.NetCommunicator.1
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                try {
                    final Object requestSynchronized = NetCommunicator.this.requestSynchronized(z, hashMap, hashMap2, str, z2);
                    if (callback != null) {
                        UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.network.NetCommunicator.1.1
                            @Override // android.os.Handler.Callback
                            public boolean handleMessage(Message message) {
                                callback.onResultOk(requestSynchronized);
                                return false;
                            }
                        });
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                    if (callback != null) {
                        UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.tools.network.NetCommunicator.1.2
                            @Override // android.os.Handler.Callback
                            public boolean handleMessage(Message message) {
                                callback.onResultError(th);
                                return false;
                            }
                        });
                    }
                }
            }
        });
    }

    public <T> T requestSynchronized(HashMap<String, Object> hashMap, String str, boolean z) throws Throwable {
        return (T) requestSynchronized((HashMap<String, String>) null, hashMap, str, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z) throws Throwable {
        return (T) requestSynchronized(true, hashMap, hashMap2, str, z);
    }

    public <T> T requestSynchronized(String str, String str2, boolean z) throws Throwable {
        return (T) requestSynchronized((HashMap<String, String>) null, str, str2, z);
    }

    public <T> T requestSynchronized(HashMap<String, String> hashMap, String str, String str2, boolean z) throws Throwable {
        return (T) requestSynchronized(true, hashMap, str, str2, z);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, HashMap<String, Object> hashMap2, String str, boolean z2) throws Throwable {
        String str2;
        if (hashMap2 == null) {
            str2 = "{}";
        } else {
            String fromHashMap = HashonHelper.fromHashMap(hashMap2);
            str2 = fromHashMap.length() == 0 ? "{}" : fromHashMap;
        }
        return (T) requestSynchronized(z, hashMap, str2, str, z2);
    }

    public <T> T requestSynchronized(boolean z, HashMap<String, String> hashMap, String str, String str2, boolean z2) throws Throwable {
        byte[] m12174c = C5873u.m12174c();
        String m11314a = m11314a(m12174c, str, z2);
        HashMap<String, String> m11316a = m11316a(z, hashMap, str, m11314a.getBytes("utf-8").length);
        boolean z3 = true;
        String[] strArr = new String[1];
        HttpResponseCallback m11312a = m11312a(m12174c, strArr);
        StringPart stringPart = new StringPart();
        stringPart.append(m11314a);
        MobLog.getInstance().m11342d(">>>  request(" + str2 + "): " + str + "\nheader = " + m11316a.toString(), new Object[0]);
        try {
            C5906a.m12085a((String) null);
        } catch (Throwable unused) {
            z3 = false;
        }
        if (z3) {
            C5906a.m12079a(false, str2, m11316a, stringPart, -1, m11312a, this.f15010f);
        } else {
            this.f15009e.rawPost(str2, m11316a, stringPart, -1, m11312a, this.f15010f);
        }
        if (strArr[0] != null) {
            MobLog.getInstance().m11342d(">>> response(" + str2 + "): " + strArr[0], new Object[0]);
            return (T) m11318a(strArr[0]);
        }
        return null;
    }

    public String requestSynchronizedGet(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        try {
            C5906a.m12085a((String) null);
            String m12078a = C5906a.m12078a(false, str, hashMap, hashMap2, this.f15010f);
            NLog mobLog = MobLog.getInstance();
            mobLog.m11342d(">>> gt res:  " + m12078a, new Object[0]);
            return m12078a;
        } catch (Throwable unused) {
            return this.f15009e.httpGetNew(str, hashMap, hashMap2, this.f15010f);
        }
    }

    /* renamed from: a */
    private byte[] m11315a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        GZIPOutputStream gZIPOutputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    bufferedOutputStream = new BufferedOutputStream(gZIPOutputStream);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                gZIPOutputStream = null;
            }
            try {
                bufferedOutputStream.write(bArr);
                bufferedOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                C5873u.m12179a(bufferedOutputStream, gZIPOutputStream, byteArrayOutputStream);
                return byteArray;
            } catch (Throwable th3) {
                th = th3;
                bufferedOutputStream2 = bufferedOutputStream;
                C5873u.m12179a(bufferedOutputStream2, gZIPOutputStream, byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            byteArrayOutputStream = null;
            gZIPOutputStream = null;
        }
    }

    /* renamed from: a */
    private String m11314a(byte[] bArr, String str, boolean z) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        byte[] bytes = str.getBytes("utf-8");
        if (z) {
            bytes = m11315a(bytes);
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
                dataOutputStream = null;
            }
            try {
                byte[] encode = this.f15008d.encode(bArr, this.f15006b, this.f15007c);
                dataOutputStream.writeInt(encode.length);
                dataOutputStream.write(encode);
                byte[] AES128Encode = Data.AES128Encode(bArr, bytes);
                dataOutputStream.writeInt(AES128Encode.length);
                dataOutputStream.write(AES128Encode);
                dataOutputStream.flush();
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            } catch (Throwable th2) {
                th = th2;
                C5873u.m12179a(dataOutputStream, byteArrayOutputStream);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            byteArrayOutputStream = null;
            dataOutputStream = null;
        }
    }

    /* renamed from: a */
    private HashMap<String, String> m11316a(boolean z, HashMap<String, String> hashMap, String str, int i) throws Throwable {
        HashMap<String, String> m11317a = z ? m11317a(str, i) : null;
        if (m11317a == null) {
            m11317a = new HashMap<>();
        }
        if (hashMap != null) {
            m11317a.putAll(hashMap);
        }
        return m11317a;
    }

    /* renamed from: a */
    private HashMap<String, String> m11317a(String str, int i) throws Throwable {
        HashMap<String, String> commonDefaultHeaders = getCommonDefaultHeaders();
        String m12674a = C5731l.m12674a("004'giejff9f");
        commonDefaultHeaders.put(m12674a, Data.MD5(str + MobSDK.getAppSecret()));
        commonDefaultHeaders.put(C5731l.m12674a("003=emUgEel"), C5871t.m12196a());
        commonDefaultHeaders.put(C5731l.m12674a("014Xhkfe7fjgfjIilgdNgfRff+ji"), String.valueOf(i));
        return commonDefaultHeaders;
    }

    /* renamed from: a */
    private HttpResponseCallback m11312a(final byte[] bArr, final String[] strArr) {
        return new HttpResponseCallback() { // from class: com.mob.tools.network.NetCommunicator.2
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                InputStream inputStream;
                ByteArrayOutputStream byteArrayOutputStream;
                int responseCode = httpConnection.getResponseCode();
                try {
                    inputStream = responseCode == 200 ? httpConnection.getInputStream() : httpConnection.getErrorStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    inputStream = null;
                    byteArrayOutputStream = null;
                }
                try {
                    byte[] bArr2 = new byte[1024];
                    for (int read = inputStream.read(bArr2); read != -1; read = inputStream.read(bArr2)) {
                        byteArrayOutputStream.write(bArr2, 0, read);
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    if (responseCode == 200) {
                        long m11322a = NetCommunicator.this.m11322a(httpConnection);
                        if (m11322a != -1 && m11322a == byteArray.length) {
                            strArr[0] = NetCommunicator.this.m11313a(bArr, byteArray);
                            C5873u.m12179a(byteArrayOutputStream, inputStream);
                            return;
                        }
                        HashMap hashMap = new HashMap();
                        hashMap.put(C5731l.m12674a("010ijjk%fkTjejBehgi"), Integer.valueOf(responseCode));
                        hashMap.put(C5731l.m12674a("006Ngi*jejNehgi"), -2);
                        hashMap.put(C5731l.m12674a("005g ekekfeek"), "Illegal content length");
                        throw new NetworkError(HashonHelper.fromHashMap(hashMap));
                    }
                    HashMap fromJson = HashonHelper.fromJson(new String(byteArray, "utf-8"));
                    fromJson.put(C5731l.m12674a("010ijjk8fk5jej8ehgi"), Integer.valueOf(responseCode));
                    throw new NetworkError(HashonHelper.fromHashMap(fromJson));
                } catch (Throwable th3) {
                    th = th3;
                    C5873u.m12179a(byteArrayOutputStream, inputStream);
                    throw th;
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public long m11322a(HttpConnection httpConnection) throws Throwable {
        List<String> m11321a = m11321a(httpConnection, C5731l.m12674a("0146hkfe=fjgfj)ilgd:gf9ff;ji"));
        if (m11321a == null || m11321a.size() <= 0) {
            return -1L;
        }
        return Long.parseLong(m11321a.get(0));
    }

    /* renamed from: a */
    private List<String> m11321a(HttpConnection httpConnection, String str) throws Throwable {
        Map<String, List<String>> headerFields = httpConnection.getHeaderFields();
        if (headerFields == null || headerFields.isEmpty()) {
            return null;
        }
        for (String str2 : headerFields.keySet()) {
            if (str2 != null && str2.equals(str)) {
                return headerFields.get(str2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m11313a(byte[] bArr, byte[] bArr2) throws Throwable {
        return new String(Data.AES128Decode(bArr, Base64.decode(bArr2, 2)), "utf-8");
    }

    /* renamed from: a */
    private Object m11318a(String str) throws Throwable {
        if (str == null) {
            HashMap hashMap = new HashMap();
            hashMap.put(C5731l.m12674a("006+gi[jej ehgi"), -1);
            hashMap.put(C5731l.m12674a("005gBekekfeek"), "RS is empty");
            throw new NetworkError(HashonHelper.fromHashMap(hashMap));
        }
        HashMap fromJson = HashonHelper.fromJson(str.trim());
        if (fromJson.isEmpty()) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(C5731l.m12674a("0062gi%jej(ehgi"), -1);
            hashMap2.put(C5731l.m12674a("005g^ekekfeek"), "RS is empty");
            throw new NetworkError(HashonHelper.fromHashMap(hashMap2));
        }
        Object obj = fromJson.get(C5731l.m12674a("003 ekQgEgi"));
        return obj == null ? fromJson.get(C5731l.m12674a("004!ed]eje")) : obj;
    }

    public static HashMap<String, String> getCommonDefaultHeaders() throws Throwable {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(C5731l.m12674a("003$emFg;el"), C5871t.m12196a());
        hashMap.put(C5731l.m12674a("013[fhgi g4ekilfjedCgfj ej_j8el"), C5895z.m12120c());
        hashMap.put(C5731l.m12674a("004Cegfeejed"), C6031c.m11708a(MobSDK.getContext()).m11704d().mo11562ai());
        return hashMap;
    }

    public static String dynamicModifyUrl(String str) {
        return C5873u.m12181a(str);
    }

    public static String checkHttpRequestUrl(String str) {
        return C5873u.m12175b(str);
    }

    public static synchronized String getDUID(MobProduct mobProduct) {
        String m12319a;
        synchronized (NetCommunicator.class) {
            m12319a = C5831e.m12319a(mobProduct);
        }
        return m12319a;
    }

    public static synchronized HashMap<String, Object> getDUIDWithModifyInfo(MobProduct mobProduct) {
        HashMap<String, Object> m12316b;
        synchronized (NetCommunicator.class) {
            m12316b = C5831e.m12316b(mobProduct);
        }
        return m12316b;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class NetworkError extends Exception implements PublicMemberKeeper {
        private static final long serialVersionUID = -8447657431687664787L;

        public NetworkError(String str) {
            super(str);
        }
    }
}
