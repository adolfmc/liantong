package com.mob.tools.network;

import android.content.Context;
import android.os.Build;
import com.mob.commons.C5868q;
import com.mob.commons.C5871t;
import com.mob.commons.C5873u;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NetworkHelper implements EverythingKeeper {
    public static int connectionTimeout = 0;
    private static boolean followRedirects = true;
    public static int readTimout;
    protected boolean instanceFollowRedirects = followRedirects;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class NetworkTimeOut implements PublicMemberKeeper {
        public int connectionTimeout;
        public int readTimout;
    }

    @Deprecated
    public String httpGet(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpGetNew(str, kvPairsToObjHashMap(arrayList), kvPairsToStrHashMap(arrayList2), networkTimeOut);
    }

    @Deprecated
    public void rawGet(String str, ArrayList<KVPair<String>> arrayList, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, kvPairsToStrHashMap(arrayList), rawNetworkCallback, networkTimeOut);
    }

    @Deprecated
    public void rawGet(String str, ArrayList<KVPair<String>> arrayList, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, kvPairsToStrHashMap(arrayList), httpResponseCallback, networkTimeOut);
    }

    @Deprecated
    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawPost(str, arrayList, hTTPPart, 0, httpResponseCallback, networkTimeOut);
    }

    @Deprecated
    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, int i, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawPost(str, kvPairsToStrHashMap(arrayList), hTTPPart, 0, httpResponseCallback, networkTimeOut);
    }

    @Deprecated
    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPostNew(str, kvPairsToObjHashMap(arrayList), kvPairsToStrHashMap(arrayList2), networkTimeOut);
    }

    @Deprecated
    private HashMap<String, String> kvPairsToStrHashMap(ArrayList<KVPair<String>> arrayList) throws Throwable {
        if (arrayList == null) {
            return null;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            hashMap.put(next.name, next.value);
        }
        return hashMap;
    }

    @Deprecated
    private HashMap<String, Object> kvPairsToObjHashMap(ArrayList<KVPair<String>> arrayList) throws Throwable {
        if (arrayList == null) {
            return null;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            hashMap.put(next.name, next.value);
        }
        return hashMap;
    }

    public String httpGet(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2) throws Throwable {
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 10000;
        return httpGetNew(str, hashMap, hashMap2, networkTimeOut);
    }

    public String httpGetNew(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, NetworkTimeOut networkTimeOut) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader2;
        MobLog.getInstance().m11342d(String.format("hgt: %s", str) + "\n" + String.format("hd: %s", hashMap2), new Object[0]);
        long currentTimeMillis = System.currentTimeMillis();
        if (hashMap != null) {
            String requestParamsToUrl = requestParamsToUrl(hashMap);
            if (requestParamsToUrl.length() > 0) {
                str = str + "?" + requestParamsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        setHeader(connection, hashMap2);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader3 = null;
        if (responseCode == 200) {
            StringBuilder sb = new StringBuilder();
            try {
                inputStreamReader2 = new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8"));
                try {
                    bufferedReader2 = new BufferedReader(inputStreamReader2);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStreamReader2 = null;
            }
            try {
                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                    if (sb.length() > 0) {
                        sb.append('\n');
                    }
                    sb.append(readLine);
                }
                C5873u.m12179a(bufferedReader2, inputStreamReader2);
                connection.disconnect();
                String sb2 = sb.toString();
                MobLog.getInstance().m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                return sb2;
            } catch (Throwable th3) {
                th = th3;
                bufferedReader3 = bufferedReader2;
                C5873u.m12179a(bufferedReader3, inputStreamReader2);
                throw th;
            }
        }
        StringBuilder sb3 = new StringBuilder();
        try {
            inputStreamReader = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (Throwable th4) {
                th = th4;
            }
            try {
                for (String readLine2 = bufferedReader.readLine(); readLine2 != null; readLine2 = bufferedReader.readLine()) {
                    if (sb3.length() > 0) {
                        sb3.append('\n');
                    }
                    sb3.append(readLine2);
                }
                C5873u.m12179a(bufferedReader, inputStreamReader);
                connection.disconnect();
                HashMap hashMap3 = new HashMap();
                hashMap3.put(C5868q.m12203b("005eEcicidcci"), sb3.toString());
                hashMap3.put(C5868q.m12203b("006Deg>hch)cfeg"), Integer.valueOf(responseCode));
                throw new Throwable(HashonHelper.fromHashMap(hashMap3));
            } catch (Throwable th5) {
                th = th5;
                bufferedReader3 = bufferedReader;
                C5873u.m12179a(bufferedReader3, inputStreamReader);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
        }
    }

    public String httpPostNew(String str, HashMap<String, Object> hashMap, HashMap<String, String> hashMap2, NetworkTimeOut networkTimeOut) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11342d("hpt: " + str + "\nhd: " + hashMap2, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        setHeader(connection, hashMap2);
        connection.setRequestProperty(C5868q.m12203b("010*fidc,ddebhXchdc,d"), "Keep-Alive");
        connection.setRequestProperty("Content-Type", C5868q.m12203b("033ciifCchNbchWchdcZdkUdbgjeeeeeegjdedccicegjcfciNfedbWdccb4e>cb"));
        StringPart stringPart = new StringPart();
        if (hashMap != null) {
            stringPart.append(requestParamsToUrl(hashMap));
        }
        connection.setFixedLengthStreamingMode((int) stringPart.mo11310b());
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        BufferedReader bufferedReader2 = null;
        try {
            outputStream = connection.getOutputStream();
            try {
                inputStream = stringPart.toInputStream();
                try {
                    byte[] bArr = new byte[65536];
                    for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                        outputStream.write(bArr, 0, read);
                    }
                    outputStream.flush();
                    int responseCode = connection.getResponseCode();
                    if (responseCode != 200 && responseCode >= 300) {
                        StringBuilder sb = new StringBuilder();
                        try {
                            inputStreamReader2 = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
                            try {
                                bufferedReader = new BufferedReader(inputStreamReader2);
                            } catch (Throwable th) {
                                th = th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader2 = null;
                        }
                        try {
                            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                                if (sb.length() > 0) {
                                    sb.append('\n');
                                }
                                sb.append(readLine);
                            }
                            C5873u.m12179a(bufferedReader, inputStreamReader2);
                            HashMap hashMap3 = new HashMap();
                            hashMap3.put(C5868q.m12203b("005e,cicidcci"), sb.toString());
                            hashMap3.put(C5868q.m12203b("006FegQhchOcfeg"), Integer.valueOf(responseCode));
                            throw new Throwable(HashonHelper.fromHashMap(hashMap3));
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedReader2 = bufferedReader;
                            C5873u.m12179a(bufferedReader2, inputStreamReader2);
                            throw th;
                        }
                    }
                    StringBuilder sb2 = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8"));
                        try {
                            BufferedReader bufferedReader3 = new BufferedReader(inputStreamReader);
                            try {
                                for (String readLine2 = bufferedReader3.readLine(); readLine2 != null; readLine2 = bufferedReader3.readLine()) {
                                    if (sb2.length() > 0) {
                                        sb2.append('\n');
                                    }
                                    sb2.append(readLine2);
                                }
                                C5873u.m12179a(bufferedReader3, inputStreamReader);
                                String sb3 = sb2.toString();
                                connection.disconnect();
                                C5873u.m12179a(inputStream, outputStream);
                                MobLog.getInstance().m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                                return sb3;
                            } catch (Throwable th4) {
                                th = th4;
                                bufferedReader2 = bufferedReader3;
                                C5873u.m12179a(bufferedReader2, inputStreamReader);
                                throw th;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                        }
                    } catch (Throwable th6) {
                        th = th6;
                        inputStreamReader = null;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    connection.disconnect();
                    C5873u.m12179a(inputStream, outputStream);
                    MobLog.getInstance().m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                    throw th;
                }
            } catch (Throwable th8) {
                th = th8;
                inputStream = null;
            }
        } catch (Throwable th9) {
            th = th9;
            outputStream = null;
            inputStream = null;
        }
    }

    public String httpPostWithBytes(String str, byte[] bArr, HashMap<String, String> hashMap, NetworkTimeOut networkTimeOut) throws Throwable {
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        byte[] bytes;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        InputStreamReader inputStreamReader2;
        long currentTimeMillis = System.currentTimeMillis();
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("hpt: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        setHeader(connection, hashMap);
        connection.setRequestProperty(C5868q.m12203b("010VfidcCddebh chdc@d"), "Keep-Alive");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        try {
            outputStream = connection.getOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            String m12196a = C5871t.m12196a();
            if (m12196a == null) {
                m12196a = "";
            }
            bytes = m12196a.getBytes("utf-8");
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            dataOutputStream = null;
            byteArrayInputStream = null;
            connection.disconnect();
            C5873u.m12179a(byteArrayInputStream, outputStream, dataOutputStream, byteArrayOutputStream);
            NLog mobLog2 = MobLog.getInstance();
            mobLog2.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            throw th;
        }
        try {
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            byteArrayInputStream = null;
            connection.disconnect();
            C5873u.m12179a(byteArrayInputStream, outputStream, dataOutputStream, byteArrayOutputStream);
            NLog mobLog22 = MobLog.getInstance();
            mobLog22.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            throw th;
        }
        try {
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.write(bArr);
            dataOutputStream.flush();
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = null;
            connection.disconnect();
            C5873u.m12179a(byteArrayInputStream, outputStream, dataOutputStream, byteArrayOutputStream);
            NLog mobLog222 = MobLog.getInstance();
            mobLog222.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            throw th;
        }
        try {
            byte[] bArr2 = new byte[65536];
            for (int read = byteArrayInputStream.read(bArr2); read > 0; read = byteArrayInputStream.read(bArr2)) {
                outputStream.write(bArr2, 0, read);
            }
            outputStream.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200 && responseCode >= 300) {
                StringBuilder sb = new StringBuilder();
                try {
                    inputStreamReader2 = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader2);
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader2 = null;
                    }
                    try {
                        for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5868q.m12203b("005e?cicidcci"), sb.toString());
                        hashMap2.put(C5868q.m12203b("006!egQhchMcfeg"), Integer.valueOf(responseCode));
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th6) {
                        th = th6;
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                    bufferedReader2 = null;
                    inputStreamReader2 = null;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            try {
                inputStreamReader = new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8"));
            } catch (Throwable th8) {
                th = th8;
                inputStreamReader = null;
            }
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
            } catch (Throwable th9) {
                th = th9;
                bufferedReader = null;
                C5873u.m12179a(bufferedReader, inputStreamReader);
                throw th;
            }
            try {
                for (String readLine2 = bufferedReader.readLine(); readLine2 != null; readLine2 = bufferedReader.readLine()) {
                    if (sb2.length() > 0) {
                        sb2.append('\n');
                    }
                    sb2.append(readLine2);
                }
                C5873u.m12179a(bufferedReader, inputStreamReader);
                String sb3 = sb2.toString();
                connection.disconnect();
                C5873u.m12179a(byteArrayInputStream, outputStream, dataOutputStream, byteArrayOutputStream);
                NLog mobLog3 = MobLog.getInstance();
                mobLog3.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
                return sb3;
            } catch (Throwable th10) {
                th = th10;
                C5873u.m12179a(bufferedReader, inputStreamReader);
                throw th;
            }
        } catch (Throwable th11) {
            th = th11;
            connection.disconnect();
            C5873u.m12179a(byteArrayInputStream, outputStream, dataOutputStream, byteArrayOutputStream);
            NLog mobLog2222 = MobLog.getInstance();
            mobLog2222.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
            throw th;
        }
    }

    public void download(String str, final OutputStream outputStream, NetworkTimeOut networkTimeOut) throws Throwable {
        final byte[] bArr = new byte[1024];
        rawGet(str, new RawNetworkCallback() { // from class: com.mob.tools.network.NetworkHelper.1
            @Override // com.mob.tools.network.RawNetworkCallback
            public void onResponse(InputStream inputStream) throws Throwable {
                int read = inputStream.read(bArr);
                while (read != -1) {
                    outputStream.write(bArr, 0, read);
                    read = inputStream.read(bArr);
                }
            }
        }, networkTimeOut);
        outputStream.flush();
    }

    public void rawGet(String str, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, new HashMap<>(), rawNetworkCallback, networkTimeOut);
    }

    public void rawGet(String str, HashMap<String, String> hashMap, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11342d("rawGet: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        setHeader(connection, hashMap);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            if (rawNetworkCallback != null) {
                InputStream inputStream = connection.getInputStream();
                try {
                    rawNetworkCallback.onResponse(inputStream);
                    C5873u.m12179a(inputStream);
                    connection.disconnect();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        C5873u.m12179a(inputStream);
                        connection.disconnect();
                        throw th2;
                    }
                }
            } else {
                connection.disconnect();
            }
            MobLog.getInstance().m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        } else if (isRedirects(connection)) {
            rawGet(connection.getHeaderField(C5868q.m12203b("008Vebdc>bchHchdcTd")), new HashMap<>(), rawNetworkCallback, networkTimeOut);
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader2 = null;
            try {
                inputStreamReader = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
                try {
                    bufferedReader = new BufferedReader(inputStreamReader);
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStreamReader = null;
            }
            try {
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    if (sb.length() > 0) {
                        sb.append('\n');
                    }
                    sb.append(readLine);
                }
                C5873u.m12179a(bufferedReader, inputStreamReader);
                connection.disconnect();
                HashMap hashMap2 = new HashMap();
                hashMap2.put(C5868q.m12203b("005e9cicidcci"), sb.toString());
                hashMap2.put(C5868q.m12203b("006Ceg;hch@cfeg"), Integer.valueOf(responseCode));
                throw new Throwable(HashonHelper.fromHashMap(hashMap2));
            } catch (Throwable th5) {
                th = th5;
                bufferedReader2 = bufferedReader;
                C5873u.m12179a(bufferedReader2, inputStreamReader);
                throw th;
            }
        }
    }

    public void rawGet(String str, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        rawGet(str, new HashMap<>(), httpResponseCallback, networkTimeOut);
    }

    public void rawGet(String str, HashMap<String, String> hashMap, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        long currentTimeMillis = System.currentTimeMillis();
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("rawGet: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        setHeader(connection, hashMap);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        if (isRedirects(connection)) {
            rawGet(connection.getHeaderField(C5868q.m12203b("008Cebdc0bch^chdc4d")), new HashMap<>(), httpResponseCallback, networkTimeOut);
        } else if (httpResponseCallback != null) {
            try {
                httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                connection.disconnect();
            } finally {
            }
        }
        NLog mobLog2 = MobLog.getInstance();
        mobLog2.m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
    }

    public void rawPost(String str, HashMap<String, String> hashMap, HTTPPart hTTPPart, int i, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        OutputStream outputStream;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11342d("hptr: " + str, new Object[0]);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        if (i >= 0) {
            connection.setChunkedStreamingMode(0);
        }
        setHeader(connection, hashMap);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        InputStream inputStream = null;
        try {
            outputStream = connection.getOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            inputStream = hTTPPart.toInputStream();
            byte[] bArr = new byte[65536];
            for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(inputStream, outputStream);
            if (httpResponseCallback != null) {
                try {
                    httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                    connection.disconnect();
                } finally {
                }
            }
            MobLog.getInstance().m11342d("use time: " + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(inputStream, outputStream);
            throw th;
        }
    }

    private String requestParamsToUrl(HashMap<String, Object> hashMap) throws Throwable {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            String urlEncode = Data.urlEncode(entry.getKey(), "utf-8");
            String urlEncode2 = entry.getValue() == null ? "" : Data.urlEncode(String.valueOf(entry.getValue()), "utf-8");
            if (sb.length() > 0) {
                sb.append('&');
            }
            sb.append(urlEncode);
            sb.append('=');
            sb.append(urlEncode2);
        }
        return sb.toString();
    }

    private HttpURLConnection getConnection(String str, NetworkTimeOut networkTimeOut) throws Throwable {
        Object obj;
        String str2;
        boolean z;
        TrustManager[] trustManagerArr;
        HttpURLConnection httpURLConnection = (HttpURLConnection) NBSInstrumentation.openConnection(new URL(str).openConnection());
        String m12203b = C5868q.m12203b("012?ce6ehg-dccbdjdcckAedGeg");
        try {
            obj = ReflectHelper.getInstanceField(httpURLConnection, m12203b);
        } catch (Throwable unused) {
            obj = null;
        }
        if (obj == null) {
            try {
                obj = ReflectHelper.getStaticField("HttpURLConnection", "PERMITTED_USER_METHODS");
                str2 = "PERMITTED_USER_METHODS";
                z = true;
            } catch (Throwable unused2) {
                str2 = "PERMITTED_USER_METHODS";
                z = true;
            }
        } else {
            str2 = m12203b;
            z = false;
        }
        if (obj != null) {
            String[] strArr = (String[]) obj;
            String[] strArr2 = new String[strArr.length + 1];
            System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
            strArr2[strArr.length] = C5868q.m12203b("005Zfjdkdjfiei");
            if (z) {
                ReflectHelper.setStaticField("HttpURLConnection", str2, strArr2);
            } else {
                ReflectHelper.setInstanceField(httpURLConnection, str2, strArr2);
            }
        }
        System.setProperty("http.keepAlive", "false");
        if (httpURLConnection instanceof HttpsURLConnection) {
            X509HostnameVerifier x509HostnameVerifier = SSLSocketFactory.STRICT_HOSTNAME_VERIFIER;
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            SSLContext sSLContext = SSLContext.getInstance(C5868q.m12203b("003Wdjebdi"));
            TrustManager[] trustManagerArr2 = new TrustManager[0];
            try {
                trustManagerArr = new TrustManager[]{(TrustManager) getTrustManager(httpsURLConnection.getURL().getHost())};
            } catch (Throwable th) {
                MobLog.getInstance().m11336e(th);
                trustManagerArr = trustManagerArr2;
            }
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(x509HostnameVerifier);
        }
        int i = networkTimeOut == null ? connectionTimeout : networkTimeOut.connectionTimeout;
        if (i > 0) {
            httpURLConnection.setConnectTimeout(i);
        }
        int i2 = networkTimeOut == null ? readTimout : networkTimeOut.readTimout;
        if (i2 > 0) {
            httpURLConnection.setReadTimeout(i2);
        }
        return httpURLConnection;
    }

    private void setHeader(URLConnection uRLConnection, HashMap<String, String> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            uRLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    @Deprecated
    public static String checkHttpRequestUrl(String str) {
        return NetCommunicator.checkHttpRequestUrl(str);
    }

    @Deprecated
    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        return httpPut(str, kvPairsToObjHashMap(arrayList), kVPair, arrayList2, networkTimeOut, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated
    public String httpPut(String str, HashMap<String, Object> hashMap, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList, NetworkTimeOut networkTimeOut, OnReadListener onReadListener) throws Throwable {
        OutputStream outputStream;
        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader2;
        BufferedReader bufferedReader2;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("httpPut: " + str);
        if (hashMap != null) {
            String requestParamsToUrl = requestParamsToUrl(hashMap);
            if (requestParamsToUrl.length() > 0) {
                str = str + "?" + requestParamsToUrl;
            }
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/octet-stream");
        setHeader(connection, kvPairsToStrHashMap(arrayList));
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        InputStream inputStream2 = null;
        try {
            outputStream = connection.getOutputStream();
            try {
                FilePart filePart = new FilePart();
                if (onReadListener != null) {
                    filePart.setOnReadListener(onReadListener);
                }
                filePart.setFile(kVPair.value);
                inputStream = filePart.toInputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
        try {
            byte[] bArr = new byte[65536];
            for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(inputStream, outputStream);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200 || responseCode == 201) {
                StringBuilder sb = new StringBuilder();
                try {
                    inputStreamReader = new InputStreamReader(connection.getInputStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader = null;
                }
                try {
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append('\n');
                        }
                        sb.append(readLine);
                    }
                    C5873u.m12179a(bufferedReader, inputStreamReader);
                    connection.disconnect();
                    String sb2 = sb.toString();
                    MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
                    return sb2;
                } catch (Throwable th5) {
                    th = th5;
                    inputStream2 = bufferedReader;
                    C5873u.m12179a(inputStream2, inputStreamReader);
                    throw th;
                }
            }
            StringBuilder sb3 = new StringBuilder();
            try {
                inputStreamReader2 = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
                try {
                    bufferedReader2 = new BufferedReader(inputStreamReader2);
                } catch (Throwable th6) {
                    th = th6;
                }
            } catch (Throwable th7) {
                th = th7;
                inputStreamReader2 = null;
            }
            try {
                for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                    if (sb3.length() > 0) {
                        sb3.append('\n');
                    }
                    sb3.append(readLine2);
                }
                C5873u.m12179a(bufferedReader2, inputStreamReader2);
                HashMap hashMap2 = new HashMap();
                hashMap2.put(C5868q.m12203b("005e*cicidcci"), sb3.toString());
                hashMap2.put(C5868q.m12203b("006$eg)hch8cfeg"), Integer.valueOf(responseCode));
                new HashonHelper();
                throw new Throwable(HashonHelper.fromHashMap(hashMap2));
            } catch (Throwable th8) {
                th = th8;
                inputStream2 = bufferedReader2;
                C5873u.m12179a(inputStream2, inputStreamReader2);
                throw th;
            }
        } catch (Throwable th9) {
            th = th9;
            inputStream2 = inputStream;
            C5873u.m12179a(inputStream2, outputStream);
            throw th;
        }
    }

    @Deprecated
    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, RawNetworkCallback rawNetworkCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        InputStream inputStream;
        OutputStream outputStream;
        InputStreamReader inputStreamReader;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("rawpost: " + str);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                connection.setRequestProperty(next.name, next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        BufferedReader bufferedReader = null;
        try {
            outputStream = connection.getOutputStream();
            try {
                inputStream = hTTPPart.toInputStream();
                try {
                    byte[] bArr = new byte[65536];
                    for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                        outputStream.write(bArr, 0, read);
                    }
                    outputStream.flush();
                    C5873u.m12179a(inputStream, outputStream);
                    int responseCode = connection.getResponseCode();
                    if (responseCode == 200) {
                        if (rawNetworkCallback != null) {
                            InputStream inputStream2 = connection.getInputStream();
                            try {
                                rawNetworkCallback.onResponse(inputStream2);
                                C5873u.m12179a(inputStream2);
                                connection.disconnect();
                            } finally {
                            }
                        } else {
                            connection.disconnect();
                        }
                        MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
                        try {
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                            try {
                                for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                                    if (sb.length() > 0) {
                                        sb.append('\n');
                                    }
                                    sb.append(readLine);
                                }
                                C5873u.m12179a(bufferedReader2, inputStreamReader);
                                connection.disconnect();
                                HashMap hashMap = new HashMap();
                                hashMap.put(C5868q.m12203b("005e1cicidcci"), sb.toString());
                                hashMap.put(C5868q.m12203b("006WegZhchFcfeg"), Integer.valueOf(responseCode));
                                new HashonHelper();
                                throw new Throwable(HashonHelper.fromHashMap(hashMap));
                            } catch (Throwable th) {
                                th = th;
                                bufferedReader = bufferedReader2;
                                C5873u.m12179a(bufferedReader, inputStreamReader);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStreamReader = null;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    C5873u.m12179a(inputStream, outputStream);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
            }
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            outputStream = null;
        }
    }

    @Deprecated
    public String jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        jsonPost(str, arrayList, arrayList2, networkTimeOut, new HttpResponseCallback() { // from class: com.mob.tools.network.NetworkHelper.2
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                InputStreamReader inputStreamReader;
                BufferedReader bufferedReader;
                InputStreamReader inputStreamReader2;
                BufferedReader bufferedReader2;
                int responseCode = httpConnection.getResponseCode();
                BufferedReader bufferedReader3 = null;
                if (responseCode == 200 || responseCode == 201) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8"));
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader);
                        hashMap.put(C5868q.m12203b("003Kci'e=eg"), sb.toString());
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader3 = bufferedReader;
                        C5873u.m12179a(bufferedReader3, inputStreamReader);
                        throw th;
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                try {
                    inputStreamReader2 = new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader2);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                            if (sb2.length() > 0) {
                                sb2.append('\n');
                            }
                            sb2.append(readLine2);
                        }
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5868q.m12203b("005eXcicidcci"), sb2.toString());
                        hashMap2.put(C5868q.m12203b("006'eg$hch7cfeg"), Integer.valueOf(responseCode));
                        new HashonHelper();
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader3 = bufferedReader2;
                        C5873u.m12179a(bufferedReader3, inputStreamReader2);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStreamReader2 = null;
                }
            }
        });
        if (hashMap.containsKey(C5868q.m12203b("003Fci:e7eg"))) {
            return (String) hashMap.get(C5868q.m12203b("0039ci=e>eg"));
        }
        return null;
    }

    @Deprecated
    private void jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkTimeOut networkTimeOut, HttpResponseCallback httpResponseCallback) throws Throwable {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            hashMap.put(next.name, next.value);
        }
        jsonPost(str, hashMap, arrayList2, networkTimeOut, httpResponseCallback);
    }

    @Deprecated
    public void jsonPost(String str, HashMap<String, Object> hashMap, ArrayList<KVPair<String>> arrayList, NetworkTimeOut networkTimeOut, HttpResponseCallback httpResponseCallback) throws Throwable {
        OutputStream outputStream;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("jsonPost: " + str);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestProperty("content-type", "application/json");
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                connection.setRequestProperty(next.name, next.value);
            }
        }
        StringPart stringPart = new StringPart();
        if (hashMap != null) {
            new HashonHelper();
            stringPart.append(HashonHelper.fromHashMap(hashMap));
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        InputStream inputStream = null;
        try {
            outputStream = connection.getOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            inputStream = stringPart.toInputStream();
            byte[] bArr = new byte[65536];
            for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(inputStream, outputStream);
            if (httpResponseCallback != null) {
                try {
                    httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                    connection.disconnect();
                } finally {
                }
            }
            MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(inputStream, outputStream);
            throw th;
        }
    }

    @Deprecated
    public String httpPostFiles(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, arrayList2, arrayList3, i, new HttpResponseCallback() { // from class: com.mob.tools.network.NetworkHelper.3
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                InputStreamReader inputStreamReader;
                BufferedReader bufferedReader;
                InputStreamReader inputStreamReader2;
                BufferedReader bufferedReader2;
                int responseCode = httpConnection.getResponseCode();
                BufferedReader bufferedReader3 = null;
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8"));
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader);
                        hashMap.put("resp", sb.toString());
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader3 = bufferedReader;
                        C5873u.m12179a(bufferedReader3, inputStreamReader);
                        throw th;
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                try {
                    inputStreamReader2 = new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader2);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                            if (sb2.length() > 0) {
                                sb2.append('\n');
                            }
                            sb2.append(readLine2);
                        }
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5868q.m12203b("005e%cicidcci"), sb2.toString());
                        hashMap2.put(C5868q.m12203b("0066egThchNcfeg"), Integer.valueOf(responseCode));
                        new HashonHelper();
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader3 = bufferedReader2;
                        C5873u.m12179a(bufferedReader3, inputStreamReader2);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStreamReader2 = null;
                }
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    @Deprecated
    public void httpPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, ArrayList<KVPair<String>> arrayList3, int i, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        HTTPPart textPostHTTPPart;
        OutputStream outputStream;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("httpPost: " + str);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setRequestProperty(C5868q.m12203b("010DfidcFddebhBchdcAd"), "Keep-Alive");
        if (arrayList2 != null && arrayList2.size() > 0) {
            textPostHTTPPart = getFilePostHTTPPart(connection, str, arrayList, arrayList2);
            if (i >= 0) {
                connection.setChunkedStreamingMode(i);
            }
        } else {
            textPostHTTPPart = getTextPostHTTPPart(connection, str, arrayList);
            connection.setFixedLengthStreamingMode((int) textPostHTTPPart.mo11310b());
        }
        if (arrayList3 != null) {
            Iterator<KVPair<String>> it = arrayList3.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                connection.setRequestProperty(next.name, next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        InputStream inputStream = null;
        try {
            outputStream = connection.getOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            inputStream = textPostHTTPPart.toInputStream();
            byte[] bArr = new byte[65536];
            for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(inputStream, outputStream);
            if (httpResponseCallback != null) {
                try {
                    httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                    connection.disconnect();
                } finally {
                }
            }
            MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(inputStream, outputStream);
            throw th;
        }
    }

    @Deprecated
    private HTTPPart getFilePostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2) throws Throwable {
        FileInputStream fileInputStream;
        String uuid = UUID.randomUUID().toString();
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + uuid);
        MultiPart multiPart = new MultiPart();
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                stringPart.append("--").append(uuid).append("\r\n");
                stringPart.append("Content-Disposition: form-data; name=\"").append(next.name).append("\"\r\n\r\n");
                stringPart.append(next.value).append("\r\n");
            }
        }
        multiPart.append(stringPart);
        Iterator<KVPair<String>> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            KVPair<String> next2 = it2.next();
            StringPart stringPart2 = new StringPart();
            File file = new File(next2.value);
            stringPart2.append("--").append(uuid).append("\r\n");
            stringPart2.append("Content-Disposition: form-data; name=\"").append(next2.name).append("\"; filename=\"").append(file.getName()).append("\"\r\n");
            String contentTypeFor = URLConnection.getFileNameMap().getContentTypeFor(next2.value);
            if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                if (next2.value.toLowerCase().endsWith("jpg") || next2.value.toLowerCase().endsWith("jpeg")) {
                    contentTypeFor = "image/jpeg";
                } else if (next2.value.toLowerCase().endsWith("png")) {
                    contentTypeFor = "image/png";
                } else if (next2.value.toLowerCase().endsWith("gif")) {
                    contentTypeFor = "image/gif";
                } else {
                    FileInputStream fileInputStream2 = null;
                    try {
                        fileInputStream = new FileInputStream(next2.value);
                    } catch (Throwable th) {
                        th = th;
                    }
                    try {
                        contentTypeFor = URLConnection.guessContentTypeFromStream(fileInputStream);
                        C5873u.m12179a(fileInputStream);
                        if (contentTypeFor == null || contentTypeFor.length() <= 0) {
                            contentTypeFor = "application/octet-stream";
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        C5873u.m12179a(fileInputStream2);
                        throw th;
                    }
                }
            }
            stringPart2.append("Content-Type: ").append(contentTypeFor).append("\r\n\r\n");
            multiPart.append(stringPart2);
            FilePart filePart = new FilePart();
            filePart.setFile(next2.value);
            multiPart.append(filePart);
            StringPart stringPart3 = new StringPart();
            stringPart3.append("\r\n");
            multiPart.append(stringPart3);
        }
        StringPart stringPart4 = new StringPart();
        stringPart4.append("--").append(uuid).append("--\r\n");
        multiPart.append(stringPart4);
        return multiPart;
    }

    @Deprecated
    private HTTPPart getTextPostHTTPPart(HttpURLConnection httpURLConnection, String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        httpURLConnection.setRequestProperty("Content-Type", C5868q.m12203b("033ciif)ch?bchMchdcJdk4dbgjeeeeeegjdedccicegjcfci5fedb:dccb4eYcb"));
        StringPart stringPart = new StringPart();
        if (arrayList != null) {
            stringPart.append(requestParamsToUrl(kvPairsToObjHashMap(arrayList)));
        }
        return stringPart;
    }

    public String downloadCache(Context context, String str, String str2, boolean z, NetworkTimeOut networkTimeOut) throws Throwable {
        return downloadCache(context, str, str2, z, networkTimeOut, null);
    }

    public String downloadCache(Context context, String str, String str2, boolean z, NetworkTimeOut networkTimeOut, FileDownloadListener fileDownloadListener) throws Throwable {
        NetworkTimeOut networkTimeOut2;
        InputStreamReader inputStreamReader;
        String str3;
        String str4;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        int contentLength;
        byte[] bArr;
        int i;
        List<String> list;
        int lastIndexOf;
        List<String> list2;
        String[] split;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("downloading: " + str);
        if (z) {
            File file = new File(ResHelper.getCachePath(context, str2), Data.MD5(str));
            if (z && file.exists()) {
                MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
                if (fileDownloadListener != null) {
                    fileDownloadListener.onProgress(100, file.length(), file.length());
                }
                return file.getAbsolutePath();
            }
            networkTimeOut2 = networkTimeOut;
        } else {
            networkTimeOut2 = networkTimeOut;
        }
        HttpURLConnection connection = getConnection(str, networkTimeOut2);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        int responseCode = connection.getResponseCode();
        int i2 = 0;
        if (responseCode == 200) {
            Map<String, List<String>> headerFields = connection.getHeaderFields();
            if (headerFields == null || (list2 = headerFields.get("Content-Disposition")) == null || list2.size() <= 0) {
                str3 = null;
            } else {
                str3 = null;
                for (String str5 : list2.get(0).split(";")) {
                    if (str5.trim().startsWith("filename")) {
                        String str6 = str5.split("=")[1];
                        str3 = (str6.startsWith("\"") && str6.endsWith("\"")) ? str6.substring(1, str6.length() - 1) : str6;
                    }
                }
            }
            if (str3 == null) {
                str4 = Data.MD5(str);
                if (headerFields != null && (list = headerFields.get("Content-Type")) != null && list.size() > 0) {
                    String str7 = list.get(0);
                    String trim = str7 == null ? "" : str7.trim();
                    if (trim.startsWith("image/")) {
                        String substring = trim.substring(6);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str4);
                        sb.append(".");
                        if ("jpeg".equals(substring)) {
                            substring = "jpg";
                        }
                        sb.append(substring);
                        str4 = sb.toString();
                    } else {
                        int lastIndexOf2 = str.lastIndexOf(47);
                        String substring2 = lastIndexOf2 > 0 ? str.substring(lastIndexOf2 + 1) : null;
                        if (substring2 != null && substring2.length() > 0 && (lastIndexOf = substring2.lastIndexOf(46)) > 0 && substring2.length() - lastIndexOf < 10) {
                            str4 = str4 + substring2.substring(lastIndexOf);
                        }
                    }
                }
            } else {
                str4 = str3;
            }
            File file2 = new File(ResHelper.getCachePath(context, str2), str4);
            if (z && file2.exists()) {
                connection.disconnect();
                MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
                if (fileDownloadListener != null) {
                    fileDownloadListener.onProgress(100, file2.length(), file2.length());
                }
                return file2.getAbsolutePath();
            }
            if (!file2.getParentFile().exists()) {
                file2.getParentFile().mkdirs();
            }
            if (file2.exists()) {
                file2.delete();
            }
            if (fileDownloadListener != null) {
                try {
                    if (fileDownloadListener.isCanceled()) {
                        if (file2.exists()) {
                            file2.delete();
                            return null;
                        }
                        return null;
                    }
                } finally {
                    if (file2.exists()) {
                        file2.delete();
                    }
                }
            }
            try {
                inputStream = connection.getInputStream();
                try {
                    contentLength = connection.getContentLength();
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
                fileOutputStream = null;
            }
            try {
                byte[] bArr2 = new byte[1024];
                int read = inputStream.read(bArr2);
                int i3 = 0;
                while (read > 0) {
                    fileOutputStream.write(bArr2, i2, read);
                    int i4 = i3 + read;
                    if (fileDownloadListener != null) {
                        bArr = bArr2;
                        i = i4;
                        fileDownloadListener.onProgress(contentLength <= 0 ? 100 : (i4 * 100) / contentLength, i4, contentLength);
                        if (fileDownloadListener.isCanceled()) {
                            break;
                        }
                    } else {
                        bArr = bArr2;
                        i = i4;
                    }
                    read = inputStream.read(bArr);
                    bArr2 = bArr;
                    i3 = i;
                    i2 = 0;
                }
                if (fileDownloadListener != null) {
                    if (fileDownloadListener.isCanceled()) {
                        fileOutputStream.flush();
                        C5873u.m12179a(inputStream, fileOutputStream);
                        return null;
                    }
                    fileDownloadListener.onProgress(100, file2.length(), file2.length());
                }
                fileOutputStream.flush();
                C5873u.m12179a(inputStream, fileOutputStream);
                connection.disconnect();
                MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
                return file2.getAbsolutePath();
            } catch (Throwable th3) {
                th = th3;
                C5873u.m12179a(inputStream, fileOutputStream);
                throw th;
            }
        }
        BufferedReader bufferedReader = null;
        if (isRedirects(connection)) {
            return downloadCache(context, connection.getHeaderField(C5868q.m12203b("008-ebdcWbchVchdc9d")), str2, z, networkTimeOut, fileDownloadListener);
        }
        StringBuilder sb2 = new StringBuilder();
        try {
            inputStreamReader = new InputStreamReader(connection.getErrorStream(), Charset.forName("utf-8"));
            try {
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader);
                try {
                    for (String readLine = bufferedReader2.readLine(); readLine != null; readLine = bufferedReader2.readLine()) {
                        if (sb2.length() > 0) {
                            sb2.append('\n');
                        }
                        sb2.append(readLine);
                    }
                    C5873u.m12179a(bufferedReader2, inputStreamReader);
                    connection.disconnect();
                    HashMap hashMap = new HashMap();
                    hashMap.put(C5868q.m12203b("005e5cicidcci"), sb2.toString());
                    hashMap.put(C5868q.m12203b("0068egRhch;cfeg"), Integer.valueOf(responseCode));
                    new HashonHelper();
                    throw new Throwable(HashonHelper.fromHashMap(hashMap));
                } catch (Throwable th4) {
                    th = th4;
                    bufferedReader = bufferedReader2;
                    C5873u.m12179a(bufferedReader, inputStreamReader);
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } catch (Throwable th6) {
            th = th6;
            inputStreamReader = null;
        }
    }

    @Deprecated
    public void httpPost(String str, ArrayList<KVPair<String>> arrayList, byte[] bArr, ArrayList<KVPair<String>> arrayList2, int i, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        HTTPPart textPostHTTPPart;
        OutputStream outputStream;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("httpPost: " + str);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setRequestProperty(C5868q.m12203b("0105fidc3ddebhAchdcJd"), "Keep-Alive");
        if (bArr != null && bArr.length > 0) {
            textPostHTTPPart = getDataPostHttpPart(connection, str, bArr);
            if (i >= 0) {
                connection.setChunkedStreamingMode(i);
            }
        } else {
            textPostHTTPPart = getTextPostHTTPPart(connection, str, arrayList);
            connection.setFixedLengthStreamingMode((int) textPostHTTPPart.mo11310b());
        }
        if (arrayList2 != null) {
            Iterator<KVPair<String>> it = arrayList2.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                connection.setRequestProperty(next.name, next.value);
            }
        }
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        InputStream inputStream = null;
        try {
            outputStream = connection.getOutputStream();
            try {
                inputStream = textPostHTTPPart.toInputStream();
                byte[] bArr2 = new byte[65536];
                for (int read = inputStream.read(bArr2); read > 0; read = inputStream.read(bArr2)) {
                    outputStream.write(bArr2, 0, read);
                }
                outputStream.flush();
                C5873u.m12179a(inputStream, outputStream);
                if (httpResponseCallback != null) {
                    try {
                        httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                        connection.disconnect();
                    } finally {
                    }
                }
                MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Throwable th) {
                th = th;
                C5873u.m12179a(inputStream, outputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStream = null;
        }
    }

    @Deprecated
    public String httpPostFilesChecked(String str, ArrayList<KVPair<String>> arrayList, byte[] bArr, ArrayList<KVPair<String>> arrayList2, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, bArr, arrayList2, i, new HttpResponseCallback() { // from class: com.mob.tools.network.NetworkHelper.4
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                InputStreamReader inputStreamReader;
                BufferedReader bufferedReader;
                InputStreamReader inputStreamReader2;
                BufferedReader bufferedReader2;
                int responseCode = httpConnection.getResponseCode();
                BufferedReader bufferedReader3 = null;
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8"));
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader);
                        hashMap.put("resp", sb.toString());
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader3 = bufferedReader;
                        C5873u.m12179a(bufferedReader3, inputStreamReader);
                        throw th;
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                try {
                    inputStreamReader2 = new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader2);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                            if (sb2.length() > 0) {
                                sb2.append('\n');
                            }
                            sb2.append(readLine2);
                        }
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5868q.m12203b("005eOcicidcci"), sb2.toString());
                        hashMap2.put(C5868q.m12203b("0061eg?hchNcfeg"), Integer.valueOf(responseCode));
                        new HashonHelper();
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader3 = bufferedReader2;
                        C5873u.m12179a(bufferedReader3, inputStreamReader2);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStreamReader2 = null;
                }
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    @Deprecated
    private HTTPPart getDataPostHttpPart(HttpURLConnection httpURLConnection, String str, byte[] bArr) throws Throwable {
        ByteArrayPart byteArrayPart = new ByteArrayPart();
        byteArrayPart.append(bArr);
        return byteArrayPart;
    }

    @Deprecated
    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, int i, NetworkTimeOut networkTimeOut) throws Throwable {
        final HashMap hashMap = new HashMap();
        httpPost(str, arrayList, i, new HttpResponseCallback() { // from class: com.mob.tools.network.NetworkHelper.5
            @Override // com.mob.tools.network.HttpResponseCallback
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                InputStreamReader inputStreamReader;
                BufferedReader bufferedReader;
                InputStreamReader inputStreamReader2;
                BufferedReader bufferedReader2;
                int responseCode = httpConnection.getResponseCode();
                BufferedReader bufferedReader3 = null;
                if (responseCode == 200 || responseCode < 300) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        inputStreamReader = new InputStreamReader(httpConnection.getInputStream(), Charset.forName("utf-8"));
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                        } catch (Throwable th) {
                            th = th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStreamReader = null;
                    }
                    try {
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                        }
                        C5873u.m12179a(bufferedReader, inputStreamReader);
                        hashMap.put("resp", sb.toString());
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        bufferedReader3 = bufferedReader;
                        C5873u.m12179a(bufferedReader3, inputStreamReader);
                        throw th;
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                try {
                    inputStreamReader2 = new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8"));
                    try {
                        bufferedReader2 = new BufferedReader(inputStreamReader2);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        for (String readLine2 = bufferedReader2.readLine(); readLine2 != null; readLine2 = bufferedReader2.readLine()) {
                            if (sb2.length() > 0) {
                                sb2.append('\n');
                            }
                            sb2.append(readLine2);
                        }
                        C5873u.m12179a(bufferedReader2, inputStreamReader2);
                        HashMap hashMap2 = new HashMap();
                        hashMap2.put(C5868q.m12203b("005e.cicidcci"), sb2.toString());
                        hashMap2.put(C5868q.m12203b("006*egKhch;cfeg"), Integer.valueOf(responseCode));
                        new HashonHelper();
                        throw new Throwable(HashonHelper.fromHashMap(hashMap2));
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader3 = bufferedReader2;
                        C5873u.m12179a(bufferedReader3, inputStreamReader2);
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStreamReader2 = null;
                }
            }
        }, networkTimeOut);
        return (String) hashMap.get("resp");
    }

    @Deprecated
    public void httpPost(String str, ArrayList<KVPair<String>> arrayList, int i, HttpResponseCallback httpResponseCallback, NetworkTimeOut networkTimeOut) throws Throwable {
        OutputStream outputStream;
        long currentTimeMillis = System.currentTimeMillis();
        MobLog.getInstance().m11333i("httpPost: " + str);
        HttpURLConnection connection = getConnection(str, networkTimeOut);
        connection.setDoOutput(true);
        connection.setRequestProperty(C5868q.m12203b("0102fidc<ddebhYchdc)d"), "Keep-Alive");
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                connection.setRequestProperty(next.name, next.value);
            }
        }
        StringPart stringPart = new StringPart();
        InputStream inputStream = null;
        stringPart.append(null);
        connection.setInstanceFollowRedirects(this.instanceFollowRedirects);
        connection.connect();
        try {
            outputStream = connection.getOutputStream();
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            inputStream = stringPart.toInputStream();
            byte[] bArr = new byte[65536];
            for (int read = inputStream.read(bArr); read > 0; read = inputStream.read(bArr)) {
                outputStream.write(bArr, 0, read);
            }
            outputStream.flush();
            C5873u.m12179a(inputStream, outputStream);
            if (httpResponseCallback != null) {
                try {
                    httpResponseCallback.onResponse(new HttpConnectionImpl23(connection));
                    connection.disconnect();
                } finally {
                }
            }
            MobLog.getInstance().m11333i("use time: " + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th2) {
            th = th2;
            C5873u.m12179a(inputStream, outputStream);
            throw th;
        }
    }

    public static Object getTrustManager(String str) throws Throwable {
        Class<?> cls = Class.forName(C5868q.m12203b("030GggCc:cc)c>dbecTdeh]ecegeg@fFecfeghefgidjcicfegQhLgb*cdcJdd:e<ci"));
        return Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{cls}, new C6141a(str));
    }

    private static boolean isRedirects(HttpURLConnection httpURLConnection) {
        try {
            if (httpURLConnection.getResponseCode() != 301 && httpURLConnection.getResponseCode() != 302 && httpURLConnection.getResponseCode() != 304 && httpURLConnection.getResponseCode() != 307) {
                if (httpURLConnection.getResponseCode() != 308) {
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.tools.network.NetworkHelper$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C6141a implements InvocationHandler {

        /* renamed from: a */
        private Object f15037a;

        /* renamed from: b */
        private String f15038b;

        private C6141a(String str) {
            try {
                this.f15038b = str;
                Method declaredMethod = Class.forName(C5868q.m12203b("0339gg_cGccFc dbec<deh=ecegegTfVecdjcicfeg[hIgb(cdcGdd2e3ciek9cbhJdccicj")).getDeclaredMethod(C5868q.m12203b("011Idd4eh$dh?dQegYhcdbe"), String.class);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(null, C5868q.m12203b("004,feghefgi"));
                Method method = invoke.getClass().getMethod(C5868q.m12203b("004Dch^dKch)h"), Class.forName(C5868q.m12203b("022Xgg1c?ccLc9eceg>eb*cfcichBh5cjechb.e4cjdiPhPdcci>e")));
                method.setAccessible(true);
                method.invoke(invoke, null);
                Method method2 = invoke.getClass().getMethod(C5868q.m12203b("016)ddAeh=djcicfeg6h<gbKcdcAdd!e6cieg"), new Class[0]);
                method2.setAccessible(true);
                Object[] objArr = (Object[]) method2.invoke(invoke, new Object[0]);
                if (objArr == null || objArr.length == 0) {
                    throw new NoSuchAlgorithmException("no trust manager found.");
                }
                this.f15037a = objArr[0];
            } catch (Exception e) {
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("failed to initialize the standard trust manager: " + e.getMessage(), new Object[0]);
                this.f15037a = null;
            }
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            String name = method.getName();
            if (name.equals(C5868q.m12203b("018bgeb$ckfiSfJchVedh+djcicfeg5heOcb"))) {
                return null;
            }
            if (name.equals(C5868q.m12203b("018bgebXckdi.eVcicc+eXcidjcicfegBheQcb"))) {
                Object[] objArr2 = (Object[]) objArr[0];
                String str = (String) objArr[1];
                if (objArr2 == null) {
                    throw new IllegalArgumentException("there were no certificates.");
                }
                if (objArr2.length == 1) {
                    try {
                        Method declaredMethod = objArr2[0].getClass().getDeclaredMethod(C5868q.m12203b("013bgebWckfh?cf>chcbchMhScj"), new Class[0]);
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(objArr2[0], new Object[0]);
                        return null;
                    } catch (Throwable th) {
                        MobLog.getInstance().m11336e(th);
                        return null;
                    }
                } else if (this.f15037a != null) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        try {
                            Object newInstance = Class.forName("android.net.http.X509TrustManagerExtensions").getConstructor(Class.forName(C5868q.m12203b("030Ugg+cNccBc3dbec.dehLecegeg-f]ecfeghefgidjcicfeg0h7gbYcdc dd+eVci"))).newInstance(this.f15037a);
                            Method declaredMethod2 = newInstance.getClass().getDeclaredMethod(C5868q.m12203b("018bgeb4ckdi3eXciccOe7cidjcicfeg heIcb"), Array.newInstance(Class.forName(C5868q.m12203b("034*gg)cTcc$c$ecegSebIcfcichYhXcjec4be_ci6h!ecfeghefgifi*eXci-hNchdechKbche")), 0).getClass(), String.class, String.class);
                            declaredMethod2.setAccessible(true);
                            declaredMethod2.invoke(newInstance, objArr2, str, this.f15038b);
                            return null;
                        } catch (Throwable th2) {
                            MobLog.getInstance().m11336e(th2);
                            return null;
                        }
                    }
                    try {
                        Method declaredMethod3 = this.f15037a.getClass().getDeclaredMethod(C5868q.m12203b("018bgeb-ckdi;e7cicc[e;cidjcicfegYhe[cb"), Array.newInstance(Class.forName(C5868q.m12203b("034LggJcYcc]cHeceg[eb+cfcich]h-cjec%be7ciDh'ecfeghefgifiZeNciQhXchdechWbche")), 0).getClass(), String.class);
                        declaredMethod3.setAccessible(true);
                        declaredMethod3.invoke(this.f15037a, objArr2, str);
                        return null;
                    } catch (Throwable th3) {
                        MobLog.getInstance().m11336e(th3);
                        return null;
                    }
                } else {
                    throw new CertificateException("there were one more certificates but no trust manager found.");
                }
            } else if (name.equals(C5868q.m12203b("018KddFehCdk9bbeihe[cbdhegegcfVe5cieg"))) {
                try {
                    return Array.newInstance(Class.forName(C5868q.m12203b("034HggDc@ccWc ecegGebKcfcich>h$cjec'be]ci]h'ecfeghefgifiWeQciYhZchdechFbche")), 0);
                } catch (Throwable th4) {
                    MobLog.getInstance().m11336e(th4);
                    return null;
                }
            } else if (name.equals(C5868q.m12203b("008gc?egMg;fidccb9e"))) {
                return Integer.valueOf(hashCode());
            } else {
                if (name.equals("toString")) {
                    return toString();
                }
                return null;
            }
        }
    }
}
