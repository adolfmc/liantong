package com.gmrz.appsdk.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SignUtil {
    public static String getSHA256(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() < 2) {
                    sb.append(0);
                }
                sb.append(hexString);
            }
            return sb.substring(0, 32);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String makeSignData(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            arrayList.add(keys.next());
        }
        Collections.sort(arrayList);
        for (String str : arrayList) {
            try {
                Object obj = jSONObject.get(str);
                if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    for (int i = 0; i < jSONArray.length(); i++) {
                        Object obj2 = jSONArray.get(i);
                        if (obj2 instanceof JSONObject) {
                            sb.append(makeSignData((JSONObject) obj2));
                        } else {
                            sb.append(obj2);
                        }
                    }
                } else if (obj instanceof JSONObject) {
                    sb.append(makeSignData((JSONObject) obj));
                } else {
                    sb.append(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String makeSignData(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList = new ArrayList(map.size());
        arrayList.addAll(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            if (map.get(str) instanceof Map) {
                sb.append(makeSignData((Map) map.get(str)));
            } else if (map.get(str) instanceof List) {
                List list = (List) map.get(str);
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) instanceof Map) {
                        sb.append(makeSignData((Map) list.get(i)));
                    } else {
                        sb.append(list.get(i));
                    }
                }
            } else {
                sb.append(map.get(str));
            }
        }
        return sb.toString();
    }
}
