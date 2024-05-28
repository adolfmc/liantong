package com.tydic.softphone.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.tydic.softphone.CallActivity;
import com.tydic.softphone.Configs;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Util {
    public static final String REQUEST_URL_PROD = "https://omo.10010.com/udbh/common/call/getTokenNew";
    public static final String REQUEST_URL_RECORDDIAL = "https://omo.10010.com/udbh/common/call/recordDial";
    public static final String REQUEST_URL_STARTCALL = "https://omo.10010.com/udbh/common/call/startCall";
    public static final String REQUEST_URL_TEL_NUMBER = "https://omo.10010.com:443/udbh/rest/customPermissionToVerify";
    public static final String REQUEST_URL_TEST = "https://10.242.31.203:30051/service/common/call/getToken";
    public static final String REQUEST_URL_TEST1 = "https://v0.yiketianqi.com/api";
    public static final String REQUEST_URL_TEST2 = "https://10.242.31.203:30051/service/common/call/getToken";
    private static Context context;
    private static Handler mHandler;
    private static String registerToken;
    private String isOther = "";

    public Util(Context context2, Handler handler) {
        context = context2;
        mHandler = handler;
    }

    public void sendRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) throws Exception {
        JSONObject jSONObject = new JSONObject();
        this.isOther = str6;
        jSONObject.put("channelId", str);
        jSONObject.put("phoneNumber", Sm4Util.encryptEcb("c27aa727b678ab002df558ed22f3311c", str2));
        jSONObject.put("callMode", str3);
        jSONObject.put("dockingBusiSys", str4);
        jSONObject.put("busiEntrance", str5);
        jSONObject.put("isOther", str6);
        jSONObject.put("destNumber", str8);
        String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        Log.i("tydic", format);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("phone", str2);
        jSONObject2.put("timestamp", format);
        jSONObject.put("checkData", Sm4Util.encryptEcb("c27aa727b678ab002df558ed22f3311c", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2)));
        getToken(jSONObject);
    }

    public void requestSendIsNumber(String str, String str2, String str3) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("txid", str2);
        jSONObject.put("permissionCode", "virtual_number");
        jSONObject.put("busiPhone", str3);
        jSONObject.put("userPhone", str);
        jSONObject.put("functionType", "virtual_number");
        String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        Log.i("tydic222", "isNumberJson:" + jSONObject2);
        sendIsNumber(jSONObject2);
    }

    public static void dealResponse(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.i("tydic", "error");
            Message message = new Message();
            message.what = 1;
            Bundle bundle = new Bundle();
            bundle.putString("text1", "大明的消息传递参数的例子！");
            bundle.putString("text2", "Time：2011-09-05");
            message.setData(bundle);
            mHandler.handleMessage(message);
            return;
        }
        try {
            Log.i("tydic222", str);
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("code");
            String string2 = jSONObject.getString("message");
            Log.i("tydiccode", "code:" + string);
            if (string.equals("0000")) {
                String[] split = jSONObject.getJSONObject("data").getString("additiona").split("\\|");
                if (split.length == 2) {
                    CallActivity.JANUS_URL = split[1];
                } else if (split.length == 1) {
                    CallActivity.JANUS_URL = split[0];
                }
                String[] split2 = jSONObject.getJSONObject("data").getString("additionb").split("\\|");
                if (split2.length == 4) {
                    System.out.println(split2[0]);
                    System.out.println(split2[1]);
                    System.out.println(split2[2]);
                    System.out.println(split2[3]);
                    Configs.TURN_URI = split2[0] + ":" + split2[1];
                    Configs.TURN_USER_NAME = split2[2];
                    Configs.TURN_USER_PASSWORD = split2[3];
                }
                String string3 = jSONObject.getJSONObject("data").getString("phoneNumber");
                registerToken = jSONObject.getJSONObject("data").getString("registerToken");
                String string4 = jSONObject.getJSONObject("data").getString("callVerify");
                String string5 = jSONObject.getJSONObject("data").getString("sipip");
                String string6 = jSONObject.getJSONObject("data").getString("sipport");
                String aesDecrypt = AESUtils.aesDecrypt(string5, "5811e1a58f90ddd0");
                Log.i("tydic", aesDecrypt);
                Message message2 = new Message();
                message2.what = 2;
                Bundle bundle2 = new Bundle();
                bundle2.putString("phoneNumber", Sm4Util.decryptEcb("c27aa727b678ab002df558ed22f3311c", string3));
                bundle2.putString("registerToken", registerToken);
                bundle2.putString("callVerify", string4);
                bundle2.putString("sipip", aesDecrypt);
                bundle2.putString("sipport", string6);
                message2.setData(bundle2);
                mHandler.handleMessage(message2);
                return;
            }
            Message message3 = new Message();
            message3.what = 9;
            Bundle bundle3 = new Bundle();
            bundle3.putString("message", string2 + "");
            bundle3.putString("text2", "Time：2011-09-05");
            message3.setData(bundle3);
            mHandler.handleMessage(message3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dealNumberResponse(String str) {
        Log.i("tydic222", str);
        Log.i("tydic222", "q2");
        if (TextUtils.isEmpty(str)) {
            Log.i("tydic", "error");
            Log.i("tydic222", "q12");
            Message message = new Message();
            message.what = 7;
            Bundle bundle = new Bundle();
            bundle.putString("text1", "大明的消息传递参数的例子！");
            bundle.putString("text2", "Time：2011-09-05");
            message.setData(bundle);
            mHandler.handleMessage(message);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("code");
            Log.i("tydic222", "q");
            if (string.equals("0000")) {
                if (jSONObject.getJSONObject("rsp").getString("rsp_code").equals("0000")) {
                    String string2 = jSONObject.getJSONObject("rsp").getJSONObject("data").getString("accessPermission");
                    Log.i("tydic222", "qa");
                    if (string2.equals("1")) {
                        Log.i("tydic222", "qd");
                        Message message2 = new Message();
                        message2.what = 8;
                        message2.setData(new Bundle());
                        mHandler.handleMessage(message2);
                    } else {
                        Log.i("tydic222", "qb");
                        Message message3 = new Message();
                        message3.what = 7;
                        message3.setData(new Bundle());
                        mHandler.handleMessage(message3);
                    }
                } else {
                    Log.i("tydic222", "qc");
                    Message message4 = new Message();
                    message4.what = 7;
                    message4.setData(new Bundle());
                    mHandler.handleMessage(message4);
                }
            } else {
                Log.i("tydic222", "q1");
                Message message5 = new Message();
                message5.what = 7;
                Bundle bundle2 = new Bundle();
                bundle2.putString("text1", "大明的消息传递参数的例子！");
                bundle2.putString("text2", "Time：2011-09-05");
                message5.setData(bundle2);
                mHandler.handleMessage(message5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getToken(JSONObject jSONObject) {
        String jSONObject2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        Log.i("tydic", "获取token-->" + jSONObject2);
        createHttpsPostByjson(REQUEST_URL_PROD, jSONObject2, "application/json");
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            return sSLContext.getSocketFactory();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }

        private TrustAllHostnameVerifier() {
        }
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.tydic.softphone.utils.Util.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(socketFactory, new X509TrustManager() { // from class: com.tydic.softphone.utils.Util.2
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            });
            builder.hostnameVerifier(new HostnameVerifier() { // from class: com.tydic.softphone.utils.Util.3
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            return !(builder instanceof OkHttpClient.Builder) ? builder.build() : NBSOkHttp3Instrumentation.builderInit(builder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createHttpsPostByjson(String str, String str2, String str3) {
        if (context == null) {
            return;
        }
        getUnsafeOkHttpClient().newCall(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), str2)).build()).enqueue(new Callback() { // from class: com.tydic.softphone.utils.Util.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.i("tydic", "请求失败");
                Log.i("tydic", String.valueOf(iOException.toString()));
                Util.dealResponse("");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tydic", "请求成功");
                String string = response.body().string();
                Log.i("tydiccode", string);
                Util.dealResponse(string);
            }
        });
    }

    public static void sendIsNumber(String str) {
        Log.i("tydic", "--->" + str);
        if (context == null) {
            return;
        }
        OkHttpClient unsafeOkHttpClient = getUnsafeOkHttpClient();
        Request build = new Request.Builder().url(REQUEST_URL_TEL_NUMBER).post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), str)).build();
        Log.i("tydic222", "请求zhong ");
        unsafeOkHttpClient.newCall(build).enqueue(new Callback() { // from class: com.tydic.softphone.utils.Util.5
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.i("tydic222", "请求失败");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tydic222", "请求成功11");
                Util.dealNumberResponse(response.body().string());
            }
        });
    }

    public static void sendCallId(String str) {
        Log.i("tydic", "tydic666" + str);
        if (context == null) {
            return;
        }
        getUnsafeOkHttpClient().newCall(new Request.Builder().url(REQUEST_URL_STARTCALL).post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), str)).build()).enqueue(new Callback() { // from class: com.tydic.softphone.utils.Util.6
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.i("tydic666", "请求失败");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tydic666", "请求成功" + response.body().string());
            }
        });
    }

    public static void sendRecordDial(String str) {
        Log.i("tydic333", "--->" + str);
        if (context == null) {
            return;
        }
        getUnsafeOkHttpClient().newCall(new Request.Builder().url(REQUEST_URL_RECORDDIAL).post(RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), str)).build()).enqueue(new Callback() { // from class: com.tydic.softphone.utils.Util.7
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                Log.i("tydic333", "请求失败");
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("tydic333", "请求成功" + response.body().string());
            }
        });
    }
}
