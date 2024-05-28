package com.gmrz.appsdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex */
public class UACUtil {
    private static final String TAG = "UACUtil";

    public static List<String> getAAid(JSONArray jSONArray, String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(jSONArray.get(0).toString()).getJSONObject("policy");
            if (str.equalsIgnoreCase("accepted")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("accepted");
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONArray optJSONArray2 = optJSONArray.optJSONArray(i);
                    JSONObject optJSONObject = new JSONArray(!(optJSONArray2 instanceof JSONArray) ? optJSONArray2.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray2)).optJSONObject(0);
                    JSONArray optJSONArray3 = new JSONObject(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject)).optJSONArray("aaid");
                    if (optJSONArray3 != null) {
                        arrayList.add(optJSONArray3.optString(0));
                    }
                }
            }
            if (str.equalsIgnoreCase("disallowed") && jSONObject.has("disallowed")) {
                JSONArray optJSONArray4 = jSONObject.optJSONArray("disallowed");
                for (int i2 = 0; i2 < optJSONArray4.length(); i2++) {
                    JSONArray optJSONArray5 = ((JSONObject) optJSONArray4.get(i2)).optJSONArray("aaid");
                    for (int i3 = 0; i3 < optJSONArray5.length(); i3++) {
                        arrayList.add(optJSONArray5.get(i3).toString());
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static List<String> getDregAAID(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray2 = new JSONObject(jSONArray.get(0).toString()).getJSONArray("authenticators");
            for (int i = 0; i < jSONArray2.length(); i++) {
                arrayList.add(jSONArray2.getJSONObject(i).getString("aaid"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public static String getFacetId(Context context) {
        String str = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray());
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            str = "android:apk-key-hash:" + Base64.encodeToString(messageDigest.digest(((X509Certificate) CertificateFactory.getInstance("X509").generateCertificate(byteArrayInputStream)).getEncoded()), 3);
            Logger.m15756i("UACUtil", "FacetID: " + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getFcparams(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            str = getFacetId(context);
        }
        String str4 = null;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("appID", str);
            jSONObject.put("facetID", getFacetId(context));
            jSONObject.put("challenge", str2);
            jSONObject.put("channelBinding", str3);
            str4 = Base64.encodeToString((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes(), 1);
            Logger.m15756i("uac fcParams :", str4);
            return str4;
        } catch (JSONException e) {
            e.printStackTrace();
            return str4;
        }
    }

    public static byte[] getSHA256(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, JSONObject> setUACResponse(Context context, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        HashMap hashMap = new HashMap();
        try {
            if (str2 == null) {
                jSONObject3.put("errorMsg", "You must define plugins aaid");
                jSONObject3.put("errorCode", FidoStatus.INVALID_PARAM);
                hashMap.put("errorInfo", jSONObject3);
                return hashMap;
            }
            JSONArray jSONArray = new JSONArray(str);
            JSONObject jSONObject5 = new JSONObject(new JSONArray(str).get(0).toString()).getJSONObject("header");
            String string = new JSONObject(new JSONArray(str).get(0).toString()).getString("challenge");
            String string2 = jSONObject5.getString("appID");
            JSONObject jSONObject6 = new JSONObject();
            String fcparams = getFcparams(context, string2, string, !(jSONObject6 instanceof JSONObject) ? jSONObject6.toString() : NBSJSONObjectInstrumentation.toString(jSONObject6));
            jSONObject4.put("cryptoKey", Base64.encodeToString(getSHA256((jSONObject5.getString("appID") + string).getBytes()), 2));
            if (getAAid(jSONArray, "disallowed").contains(str2)) {
                jSONObject3.put("errorMsg", "current aaid disallowed");
                jSONObject3.put("errorCode", FidoStatus.NO_MATCH);
                hashMap.put("errorInfo", jSONObject3);
                return hashMap;
            }
            getAAid(jSONArray, "accepted").size();
            jSONObject.put("aaid", str2);
            jSONObject2.put("header", jSONObject5);
            jSONObject2.put("fcParams", fcparams);
            hashMap.put("jsonContentData", jSONObject);
            hashMap.put("jsonUafResponse", jSONObject2);
            hashMap.put("jsonSendData", jSONObject3);
            hashMap.put("jsonCryptoKey", jSONObject4);
            return hashMap;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
