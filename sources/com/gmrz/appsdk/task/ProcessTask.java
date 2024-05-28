package com.gmrz.appsdk.task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.UAFIntentType;
import com.gmrz.appsdk.commlib.UAFMessage;
import com.gmrz.appsdk.commlib.UacSDKProxy;
import com.gmrz.appsdk.commlib.UafLocalSDKProxy;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.UACType;
import com.gmrz.appsdk.recorder.api.Record;
import com.gmrz.appsdk.util.Logger;
import com.gmrz.appsdk.util.UACUtil;
import com.gmrz.fido.offline.Noter;
import com.gmrz.fido.offline.SnGenerator;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ProcessTask {
    /* renamed from: b */
    private List<String> m15764b(Activity activity, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(m15768a(activity, activity.getResources().getIdentifier("orderinauth", "raw", activity.getPackageName()))).getJSONObject("fingerprint").getJSONObject("reg").getJSONArray(str.equalsIgnoreCase("00") ? "login" : "pay");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (Exception e) {
            Logger.m15756i("ProcessTask", "Warning to load OrderAuth : " + e);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:118:?, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:?, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0189, code lost:
        if (r5 == 1) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x018b, code lost:
        if (r5 == 2) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x018f, code lost:
        r10.removeAll(java.util.Arrays.asList("004A#01B0", "004A#01B1", "004A#01B2", "004A#01B3"));
        r3 = r10.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x01aa, code lost:
        if (r3.hasNext() == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ac, code lost:
        r0 = m15769a((android.app.Activity) r14, r1, r15.getTransType(), r15.getFidoParam().getLocation(), java.util.Arrays.asList(r3.next())).process(r14, m15765a(r15));
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01dc, code lost:
        r0.fidoStatus = com.gmrz.appsdk.commlib.api.FidoStatus.SUCCESS;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.gmrz.appsdk.FidoOut m15767a(android.content.Context r14, com.gmrz.appsdk.FidoIn r15) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.task.ProcessTask.m15767a(android.content.Context, com.gmrz.appsdk.FidoIn):com.gmrz.appsdk.FidoOut");
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x01e8, code lost:
        r8 = new com.gmrz.appsdk.commlib.UafAppSDKProxy();
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.gmrz.appsdk.commlib.api.IAppSDK m15769a(android.app.Activity r19, java.lang.String r20, java.lang.String r21, com.gmrz.appsdk.commlib.api.IAppSDK.ClientLocation r22, java.util.List<java.lang.String>... r23) {
        /*
            Method dump skipped, instructions count: 564
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.task.ProcessTask.m15769a(android.app.Activity, java.lang.String, java.lang.String, com.gmrz.appsdk.commlib.api.IAppSDK$ClientLocation, java.util.List[]):com.gmrz.appsdk.commlib.api.IAppSDK");
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m15768a(android.content.Context r6, int r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            android.content.res.Resources r6 = r6.getResources()     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.io.InputStream r6 = r6.openRawResource(r7)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r2.<init>(r6)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
            r7.<init>(r2)     // Catch: java.lang.Throwable -> L90 java.lang.Exception -> L93
        L18:
            java.lang.String r6 = r7.readLine()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            if (r6 == 0) goto L3b
            java.lang.String r2 = "[`~!@#$%^&*()+=|';'<>/?~！@#￥%……&*（）——+|【】‘；：”“’。，、？]"
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.util.regex.Matcher r6 = r2.matcher(r6)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r2 = ""
            java.lang.String r6 = r6.replaceAll(r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r6 = r6.trim()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r0.append(r6)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r6 = 10
            r0.append(r6)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            goto L18
        L3b:
            r7.close()     // Catch: java.io.IOException -> L3f java.lang.Throwable -> L8c java.lang.Exception -> L8e
            goto L56
        L3f:
            r6 = move-exception
            java.lang.String r2 = "ProcessTask"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r3.<init>()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r4 = "readRawTextFile: failed to close buffered reader."
            r3.append(r4)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r3.append(r6)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r6 = r3.toString()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            com.gmrz.appsdk.util.Logger.m15757e(r2, r6)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
        L56:
            java.lang.String r6 = "ProcessTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r2.<init>()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r3 = "readRawTextFile: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r2.append(r0)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            com.gmrz.appsdk.util.Logger.m15756i(r6, r2)     // Catch: java.lang.Throwable -> L8c java.lang.Exception -> L8e
            r7.close()     // Catch: java.io.IOException -> L70
            goto L87
        L70:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "readRawTextFile: failed to close buffered reader."
            r7.append(r1)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "ProcessTask"
            com.gmrz.appsdk.util.Logger.m15757e(r7, r6)
        L87:
            java.lang.String r6 = r0.toString()
            return r6
        L8c:
            r6 = move-exception
            goto Lca
        L8e:
            r6 = move-exception
            goto L95
        L90:
            r6 = move-exception
            r7 = r1
            goto Lca
        L93:
            r6 = move-exception
            r7 = r1
        L95:
            java.lang.String r0 = "ProcessTask"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lc9
            r2.<init>()     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r3 = "Error reading raw text file"
            r2.append(r3)     // Catch: java.lang.Throwable -> Lc9
            r2.append(r6)     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> Lc9
            com.gmrz.appsdk.util.Logger.m15757e(r0, r6)     // Catch: java.lang.Throwable -> Lc9
            if (r7 == 0) goto Lc8
            r7.close()     // Catch: java.io.IOException -> Lb1
            goto Lc8
        Lb1:
            r6 = move-exception
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "readRawTextFile: failed to close buffered reader."
            r7.append(r0)
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            java.lang.String r7 = "ProcessTask"
            com.gmrz.appsdk.util.Logger.m15757e(r7, r6)
        Lc8:
            return r1
        Lc9:
            r6 = move-exception
        Lca:
            if (r7 == 0) goto Le7
            r7.close()     // Catch: java.io.IOException -> Ld0
            goto Le7
        Ld0:
            r7 = move-exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "readRawTextFile: failed to close buffered reader."
            r0.append(r1)
            r0.append(r7)
            java.lang.String r7 = r0.toString()
            java.lang.String r0 = "ProcessTask"
            com.gmrz.appsdk.util.Logger.m15757e(r0, r7)
        Le7:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gmrz.appsdk.task.ProcessTask.m15768a(android.content.Context, int):java.lang.String");
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private FidoOut m15766a(Context context, String str, FidoIn fidoIn) {
        FidoOut fidoOut = new FidoOut();
        fidoOut.fidoStatus = FidoStatus.FAILED;
        Map<String, JSONObject> uACResponse = UACUtil.setUACResponse(context, fidoIn.getFidoIn(), str);
        if (uACResponse != null && !uACResponse.containsKey("errorInfo")) {
            try {
                fidoIn.setCryptoKey(uACResponse.get("jsonCryptoKey").getString("cryptoKey"), context.getPackageName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fidoIn.uacType == UACType.REMOTEVOICE) {
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONArray jSONArray2 = new JSONObject(new JSONArray(fidoIn.getFidoIn()).get(0).toString()).getJSONObject("policy").getJSONArray("accepted").getJSONArray(0);
                    JSONObject jSONObject = new JSONArray(!(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2)).getJSONObject(0);
                    jSONArray = new JSONObject(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getJSONArray("exts");
                    for (int i = 0; i < jSONArray.length(); i++) {
                        if (jSONArray.getJSONObject(i).getString("id").equalsIgnoreCase("voice_text")) {
                            jSONArray.getJSONObject(i).put("data", new String(Base64.decode(jSONArray.getJSONObject(i).getString("data").getBytes(), 11), StandardCharsets.UTF_8));
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    fidoIn.setRequestVoiceData(null);
                }
                fidoIn.setRequestVoiceData(!(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
            }
            UACType uACType = fidoIn.uacType;
            UACType uACType2 = UACType.REMOTEFACE;
            if (uACType == uACType2 || uACType == UACType.REMOTEVOICE || uACType == UACType.REALNAME_EXT) {
                fidoOut = new UacSDKProxy().process(context, fidoIn);
                if (fidoOut.fidoStatus != FidoStatus.SUCCESS) {
                    return fidoOut;
                }
            }
            Logger.m15756i("ProcessTask", "registerAuthenticator response:" + fidoOut);
            try {
                JSONArray jSONArray3 = new JSONArray();
                JSONObject jSONObject2 = new JSONObject();
                JSONArray jSONArray4 = new JSONArray();
                String str2 = uACResponse.get("jsonUafResponse").getJSONObject("header").getString("op").equalsIgnoreCase("Reg") ? "registration" : "authentication";
                UACType uACType3 = fidoIn.uacType;
                if (uACType3 == uACType2) {
                    jSONObject2.put("id", "photo");
                    jSONObject2.put("data", fidoOut.responseParams);
                    jSONArray3.put(0, jSONObject2);
                    uACResponse.get("jsonUafResponse").put(str2, uACResponse.get("jsonContentData"));
                } else if (uACType3 == UACType.REMOTEVOICE) {
                    FileInputStream openFileInput = context.openFileInput(new String(Base64.decode(fidoOut.responseParams, 11), StandardCharsets.UTF_8));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = openFileInput.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    JSONArray jSONArray5 = new JSONArray(byteArrayOutputStream.toString());
                    uACResponse.get("jsonUafResponse").put(str2, uACResponse.get("jsonContentData"));
                    jSONArray3 = jSONArray5;
                } else {
                    if (uACType3 != UACType.REALNAME && uACType3 != UACType.REALNAME_EXT) {
                        if (uACType3 == UACType.QRCODE) {
                            uACResponse.get("jsonUafResponse").put("authentication", uACResponse.get("jsonContentData"));
                        }
                    }
                    Logger.m15756i("ProcessTask", "registerAuthenticator response:" + fidoOut);
                    String encodeToString = Base64.encodeToString(fidoIn.getCardName().getBytes(), 11);
                    String encodeToString2 = Base64.encodeToString(fidoIn.getCardNo().getBytes(), 11);
                    String encodeToString3 = Base64.encodeToString(fidoIn.getCardStartDate().getBytes(), 11);
                    String encodeToString4 = Base64.encodeToString(fidoIn.getCardEndDate().getBytes(), 11);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    JSONObject jSONObject5 = new JSONObject();
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject3.put("id", "cardname");
                    jSONObject3.put("data", encodeToString);
                    jSONObject4.put("id", "cardno");
                    jSONObject4.put("data", encodeToString2);
                    jSONObject5.put("id", "starttime");
                    jSONObject5.put("data", encodeToString3);
                    jSONObject6.put("id", "endtime");
                    jSONObject6.put("data", encodeToString4);
                    jSONArray3.put(0, jSONObject3);
                    jSONArray3.put(1, jSONObject4);
                    jSONArray3.put(2, jSONObject5);
                    jSONArray3.put(3, jSONObject6);
                    if (fidoIn.uacType == UACType.REALNAME_EXT && fidoOut.responseParams != null) {
                        JSONObject jSONObject7 = new JSONObject();
                        jSONObject7.put("id", "photo");
                        jSONObject7.put("data", fidoOut.responseParams);
                        jSONArray3.put(4, jSONObject7);
                    }
                    uACResponse.get("jsonUafResponse").put("authentication", uACResponse.get("jsonContentData"));
                }
                uACResponse.get("jsonContentData").put("exts", jSONArray3);
                jSONArray4.put(0, uACResponse.get("jsonUafResponse"));
                fidoOut.fidoResponse = !(jSONArray4 instanceof JSONArray) ? jSONArray4.toString() : NBSJSONArrayInstrumentation.toString(jSONArray4);
                fidoOut.fidoStatus = FidoStatus.SUCCESS;
                return fidoOut;
            } catch (IOException | JSONException e3) {
                e3.printStackTrace();
                fidoOut.fidoStatus = FidoStatus.FAILED;
                return fidoOut;
            }
        }
        Noter.m15736a(context, Record.OPERATION.Process, Record.ExcType.UNKNOWN, "remote_Authenticator_Operation failed", null, SnGenerator.m15726b());
        try {
            fidoOut.fidoStatus = (FidoStatus) uACResponse.get("errorInfo").get("errorCode");
            fidoOut.fidoStatusMsg = uACResponse.get("errorInfo").getString("errorMsg");
        } catch (JSONException e4) {
            e4.printStackTrace();
        }
        return fidoOut;
    }

    /* renamed from: a */
    private boolean m15770a(Activity activity, String str) {
        char c;
        int i;
        try {
            switch (str.hashCode()) {
                case -670122701:
                    if (str.equals("001A#3333")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1992297478:
                    if (str.equals("004A#01A0")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 1992297495:
                    if (str.equals("004A#01AA")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1992297498:
                    if (str.equals("004A#01AD")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1992297499:
                    if (str.equals("004A#01AE")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    i = 0;
                    break;
                case 1:
                    i = 1;
                    break;
                case 2:
                default:
                    i = 4;
                    break;
                case 3:
                    i = 7;
                    break;
                case 4:
                    i = 5;
                    break;
            }
            Class<?> cls = Class.forName("com.android.client.asm.core.uaf.AsmFactory");
            Object invoke = cls.getMethod("createAsmInstance", String.class).invoke(cls.getConstructor(Context.class).newInstance(activity.getApplicationContext()), activity.getPackageName());
            Method method = invoke.getClass().getMethod("process", String.class, Activity.class);
            Object[] objArr = new Object[2];
            objArr[0] = String.format("{\"asmVersion\":{\"major\":1,\"minor\":0}, \"authenticatorIndex\":%1$s, \"requestType\":\"GetRegistrations\"}", Integer.valueOf(i));
            objArr[1] = activity;
            String str2 = (String) method.invoke(invoke, objArr);
            Logger.m15756i("ProcessTask", "getreg Response was returned" + str2);
            if (new JSONObject(str2).getInt("statusCode") == 0) {
                JSONArray jSONArray = new JSONObject(str2).getJSONObject("responseData").getJSONArray("appRegs");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    FidoOut process = new UafLocalSDKProxy().process(activity, m15765a(new FidoIn().setFidoIn(String.format("[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Auth\",\"appID\":\"%1$s\",\"serverData\":\"c6GW4L_0W2q_5bzt3wZNrtRZxPcpdFNp2m5lFnggId6uFImdqwh5g0B_Vp-EK0IEkLI5SzkMOAOup6CtLqThKcLHWaGfEHPd5svRGfXhLbqxliKSwMbOwBehf6oZyOGON2az2hWZLouS0zBGSnSYFmhJyfZRLDT3U-DKIgeUKn50Yz4rjUR3Cc5eb8QQ1GlSaWBwvffWvG_bd4VAyMKlhY5IsumqylXtjU47OYie9sheEYZY_th6JEbf1F-RMeq8IqXrEK74uOFP0ShV3ERQh7mcrNTu3N-9mUk\"},\"challenge\":\"SLqANoSWDlIumRWjvVg7VrNXOJ-02nBiAt5VNNXNOdQ\",\"policy\":{\"accepted\":[[{\"aaid\":[\"%2$s\"],\"keyIDs\":[\"%3$s\"]}]]}}]", jSONArray.getJSONObject(i2).getString("appID"), str, jSONArray.getJSONObject(i2).getJSONArray("keyIDs").getString(0))).setCheckpolicy(true)));
                    Logger.m15756i("ProcessTask", "isCanAuth returned getreg out" + process);
                    if (process.fidoStatus == FidoStatus.SUCCESS) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            try {
                Noter.m15736a(activity, Record.OPERATION.Reg, Record.ExcType.UNKNOWN, "check current keyid can auth failed", null, SnGenerator.m15726b());
                Logger.m15757e("ProcessTask", "Failed to receive Uaf response from service  " + e);
                return false;
            } catch (Exception e2) {
                Noter.m15736a(activity, Record.OPERATION.Reg, Record.ExcType.UNKNOWN, "check current keyid can auth failed", null, SnGenerator.m15726b());
                Logger.m15757e("ProcessTask", "Failed to process Uaf response" + e2);
                return false;
            }
        }
    }

    /* renamed from: a */
    private Intent m15765a(FidoIn fidoIn) {
        Intent intent = new Intent();
        HashMap hashMap = new HashMap();
        if (fidoIn.getChannelBinding() != null) {
            String str = fidoIn.getChannelBinding().get("serverEndPoint");
            if (str != null && !TextUtils.isEmpty(str)) {
                hashMap.put("serverEndPoint", str);
            }
            String str2 = fidoIn.getChannelBinding().get("tlsServerCertificate");
            if (str2 != null && !TextUtils.isEmpty(str2)) {
                hashMap.put("tlsServerCertificate", str2);
            }
            String str3 = fidoIn.getChannelBinding().get("cid_pubkey");
            if (str3 != null && !TextUtils.isEmpty(str3)) {
                hashMap.put("cid_pubkey", str3);
            }
            String str4 = fidoIn.getChannelBinding().get("tlsUnique");
            if (str4 != null && !TextUtils.isEmpty(str4)) {
                hashMap.put("tlsUnique", str4);
            }
        } else {
            hashMap.put("serverEndPoint", null);
            hashMap.put("tlsServerCertificate", null);
            hashMap.put("cid_pubkey", null);
            hashMap.put("tlsUnique", null);
        }
        JSONObject jSONObject = new JSONObject(hashMap);
        intent.putExtra("channelBindings", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        if (fidoIn.isCheckpolicy()) {
            intent.putExtra("UAFIntentType", UAFIntentType.CHECK_POLICY.toString());
        } else {
            intent.putExtra("UAFIntentType", UAFIntentType.UAF_OPERATION.toString());
        }
        UAFMessage uAFMessage = new UAFMessage();
        uAFMessage.f10272a = fidoIn.getFidoIn();
        if (!TextUtils.isEmpty(fidoIn.getGestureUVT())) {
            Logger.m15756i("ProcessTask", "tmpRequest gestureUVT is not null");
            String str5 = fidoIn.getGestureUVT() + fidoIn.getUsername();
            if (uAFMessage.f10273b != null) {
                Log.wtf("ProcessTask", "uafMessage.additionalData is not null");
                JSONArray jSONArray = new JSONArray(uAFMessage.f10273b);
                jSONArray.put(new JSONObject().put("tag", "GESTURE_UVT").put("data", new JSONObject().put("gestureUVT", str5)).put("fail_if_unknown", false));
                jSONArray.put(new JSONObject().put("tag", "USERNAME").put("data", new JSONObject().put("username", fidoIn.getUsername())).put("fail_if_unknown", false));
                uAFMessage.f10273b = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
            } else {
                Log.wtf("ProcessTask", "uafMessage.additionalData is null");
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(new JSONObject().put("tag", "GESTURE_UVT").put("data", new JSONObject().put("gestureUVT", str5)).put("fail_if_unknown", false));
                jSONArray2.put(new JSONObject().put("tag", "USERNAME").put("data", new JSONObject().put("username", fidoIn.getUsername())).put("fail_if_unknown", false));
                uAFMessage.f10273b = !(jSONArray2 instanceof JSONArray) ? jSONArray2.toString() : NBSJSONArrayInstrumentation.toString(jSONArray2);
            }
        }
        intent.putExtra("message", uAFMessage.m15847a());
        intent.putExtra("offline", fidoIn.isOfflineEnable());
        intent.putExtra("exact", fidoIn.isExactMatch());
        intent.putExtra("cacheFileName", fidoIn.getCacheFileName());
        return intent;
    }
}
