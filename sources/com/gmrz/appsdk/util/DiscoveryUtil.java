package com.gmrz.appsdk.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.gmrz.appsdk.FidoAppSDK;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.entity.Discovery;
import com.gmrz.fido.offline.Noter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DiscoveryUtil {
    private static final String TAG = "DiscoveryUtil";

    public static HashMap<String, HashSet<String>> clientDiscovery(Context context) {
        if (!EnvObserver.isEnvChange(context) && DiscoveryResStasher.getResultFromSp(context).size() > 0) {
            return DiscoveryResStasher.getResultFromSp(context);
        }
        HashMap<String, HashSet<String>> clientDiscoveryNoCache = clientDiscoveryNoCache(context, null);
        DiscoveryResStasher.saveIntoSp(context, clientDiscoveryNoCache);
        Noter.m15737a(context);
        return clientDiscoveryNoCache;
    }

    public static JSONArray clientDiscoveryDetailInfo(Context context) {
        String uniqueID;
        JSONArray jSONArray = new JSONArray();
        FidoReInfo uniquePsuedoID = FidoAppSDK.getInstance().getUniquePsuedoID(context);
        FidoStatus status = uniquePsuedoID.getStatus();
        FidoStatus fidoStatus = FidoStatus.SUCCESS;
        if (status == fidoStatus) {
            uniqueID = uniquePsuedoID.getUniqueID();
        } else {
            FidoReInfo uniquePsuedoID2 = FidoAppSDK.getInstance().setUniquePsuedoID(context);
            uniqueID = uniquePsuedoID2.getStatus() == fidoStatus ? uniquePsuedoID2.getUniqueID() : null;
        }
        String str = FidoAppSDK.getInstance().clientDiscover(context, IAppSDK.ClientLocation.LOCAL_CLIENT).discoveryData;
        int i = 0;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("availableAuthenticators");
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject jSONObject = new JSONObject();
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    jSONObject.put("aaid", optJSONObject.optString("aaid"));
                    jSONObject.put("userVerification", optJSONObject.optString("userVerification"));
                    jSONObject.put("authenticationAlgorithm", optJSONObject.optString("authenticationAlgorithm"));
                    jSONObject.put("deviceId", uniqueID);
                    jSONArray.put(jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str2 = FidoAppSDK.getInstance().clientDiscover(context, IAppSDK.ClientLocation.REMOTE_CLIENT).discoveryData;
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONArray optJSONArray2 = new JSONObject(str2).optJSONArray("availableAuthenticators");
                if (optJSONArray2 == null) {
                    List<Discovery.Authenticator> discoverAuthList = getDiscoverAuthList(str2);
                    while (i < discoverAuthList.size()) {
                        JSONObject jSONObject2 = new JSONObject();
                        try {
                            jSONObject2.put("aaid", discoverAuthList.get(i).aaid);
                            jSONObject2.put("userVerification", discoverAuthList.get(i).userVerification);
                            jSONObject2.put("authenticationAlgorithm", discoverAuthList.get(i).authenticationAlgorithm);
                            jSONObject2.put("deviceId", uniqueID);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        jSONArray.put(jSONObject2);
                        i++;
                    }
                } else {
                    while (i < optJSONArray2.length()) {
                        JSONObject jSONObject3 = new JSONObject();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                        jSONObject3.put("aaid", optJSONObject2.optString("aaid"));
                        jSONObject3.put("userVerification", optJSONObject2.optString("userVerification"));
                        jSONObject3.put("authenticationAlgorithm", optJSONObject2.optString("authenticationAlgorithm"));
                        jSONObject3.put("deviceId", uniqueID);
                        jSONArray.put(jSONObject3);
                        i++;
                    }
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        return jSONArray;
    }

    public static HashMap<String, HashSet<String>> clientDiscoveryNoCache(Context... contextArr) {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        hashMap.put("2", new HashSet<>());
        hashMap.put("16", new HashSet<>());
        hashMap.put("64", new HashSet<>());
        hashMap.put("128", new HashSet<>());
        String str = FidoAppSDK.getInstance().clientDiscover(contextArr[0], IAppSDK.ClientLocation.LOCAL_CLIENT).discoveryData;
        Logger.wtf("DiscoveryUtil", "localDiscoveryData:" + str);
        boolean checkSupport = Build.VERSION.SDK_INT >= 23 ? FpUtil.checkSupport(contextArr[0], UUID.randomUUID().toString()) : false;
        if (!TextUtils.isEmpty(str) && checkSupport) {
            try {
                Discovery discovery = new Discovery();
                JSONArray optJSONArray = new JSONObject(str).optJSONArray("availableAuthenticators");
                if (optJSONArray == null) {
                    Logger.wtf("DiscoveryUtil", "DiscoveryUtil.clientDiscovery parser failed");
                    return hashMap;
                }
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    Discovery.Authenticator authenticator = new Discovery.Authenticator();
                    authenticator.aaid = optJSONObject.optString("aaid");
                    authenticator.description = optJSONObject.optString("description");
                    authenticator.userVerification = optJSONObject.optString("userVerification");
                    authenticator.authenticationAlgorithm = optJSONObject.optString("authenticationAlgorithm");
                    arrayList.add(authenticator);
                }
                discovery.setAvailableAuthenticators(arrayList);
                if (discovery.availableAuthenticators.size() > 0) {
                    for (Discovery.Authenticator authenticator2 : discovery.availableAuthenticators) {
                        HashSet<String> hashSet = hashMap.get(authenticator2.userVerification);
                        if (hashSet == null || hashSet.size() == 0) {
                            hashMap.put(authenticator2.userVerification, new HashSet<>());
                        }
                        HashSet<String> hashSet2 = hashMap.get(authenticator2.userVerification);
                        if (contextArr.length > 1) {
                            if (authenticator2.aaid.equalsIgnoreCase("004A#01AE")) {
                                hashSet2.add(authenticator2.aaid + "_3");
                            } else {
                                if (!authenticator2.aaid.equalsIgnoreCase("004A#01A0") && !authenticator2.aaid.equalsIgnoreCase("004A#01AD")) {
                                    hashSet2.add(authenticator2.aaid + "_2");
                                }
                                hashSet2.add(authenticator2.aaid + "_1");
                            }
                        } else {
                            hashSet2.add(authenticator2.aaid);
                        }
                    }
                } else {
                    Logger.m15756i("DiscoveryUtil", "No local authenticators found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String str2 = FidoAppSDK.getInstance().clientDiscover(contextArr[0], IAppSDK.ClientLocation.REMOTE_CLIENT).discoveryData;
        if (!TextUtils.isEmpty(str2)) {
            Discovery discovery2 = new Discovery();
            List<Discovery.Authenticator> arrayList2 = new ArrayList<>();
            try {
                JSONArray optJSONArray2 = new JSONObject(str2).optJSONArray("availableAuthenticators");
                if (optJSONArray2 == null) {
                    arrayList2 = getDiscoverAuthList(str2);
                } else {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        Discovery.Authenticator authenticator3 = new Discovery.Authenticator();
                        authenticator3.aaid = optJSONObject2.optString("aaid");
                        authenticator3.description = optJSONObject2.optString("description");
                        authenticator3.userVerification = optJSONObject2.optString("userVerification");
                        authenticator3.authenticationAlgorithm = optJSONObject2.optString("authenticationAlgorithm");
                        arrayList2.add(authenticator3);
                    }
                }
                discovery2.setAvailableAuthenticators(arrayList2);
                if (discovery2.availableAuthenticators.size() > 0) {
                    for (Discovery.Authenticator authenticator4 : discovery2.availableAuthenticators) {
                        HashSet<String> hashSet3 = hashMap.get(authenticator4.userVerification);
                        if (hashSet3 == null || hashSet3.size() == 0) {
                            hashMap.put(authenticator4.userVerification, new HashSet<>());
                        }
                        HashSet<String> hashSet4 = hashMap.get(authenticator4.userVerification);
                        if (contextArr.length > 1) {
                            hashSet4.add(authenticator4.aaid + "_0");
                        } else {
                            hashSet4.add(authenticator4.aaid);
                        }
                    }
                } else {
                    Logger.m15756i("DiscoveryUtil", "No remote authenticators found");
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (hashMap.size() > 0) {
            for (String str3 : hashMap.keySet()) {
                Logger.wtf("DiscoveryUtil", "Fido userVerification= " + str3 + " aaids= " + hashMap.get(str3).toString());
            }
        } else {
            Logger.wtf("DiscoveryUtil", "DiscoveryUtil.clientDiscoveryResults Map is null");
        }
        return hashMap;
    }

    private static List<Discovery.Authenticator> getDiscoverAuthList(String str) {
        ArrayList arrayList = new ArrayList();
        Discovery.Authenticator[] authenticatorArr = {new Discovery.Authenticator(), new Discovery.Authenticator(), new Discovery.Authenticator(), new Discovery.Authenticator()};
        String[] strArr = {"\"aaid\":\"[a-zA-Z0-9]{4}#[a-zA-Z0-9]{4}\"", "\"userVerification\":[0-9]*", "\"authenticationAlgorithm\":[0-9]*", "\"description\":\"([^\"]*)\""};
        for (int i = 0; i < 4; i++) {
            try {
                Matcher matcher = Pattern.compile(strArr[i]).matcher(str);
                int i2 = 0;
                while (matcher.find()) {
                    String group2 = matcher.group(matcher.groupCount());
                    switch (i) {
                        case 0:
                            authenticatorArr[i2].aaid = group2.replace("\"aaid\":", "").replace("\"", "");
                            i2++;
                            break;
                        case 1:
                            authenticatorArr[i2].userVerification = group2.replace("\"userVerification\":", "").replace("\"", "");
                            i2++;
                            break;
                        case 2:
                            authenticatorArr[i2].authenticationAlgorithm = group2.replace("\"authenticationAlgorithm\":", "").replace("\"", "");
                            i2++;
                            break;
                        case 3:
                            authenticatorArr[i2].description = group2.replace("\"description\":", "").replace("\"", "");
                            i2++;
                            break;
                    }
                }
            } catch (Exception e) {
                Logger.m15757e("DiscoveryUtil", "getLists failed e:" + e.getMessage());
                arrayList.clear();
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            Discovery.Authenticator authenticator = authenticatorArr[i3];
            if (!TextUtils.isEmpty(authenticator.aaid)) {
                arrayList.add(authenticator);
            }
        }
        return arrayList;
    }
}
