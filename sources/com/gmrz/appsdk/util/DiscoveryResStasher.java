package com.gmrz.appsdk.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DiscoveryResStasher {
    private static final String SP_NAME = "discovery_res";

    public static HashMap<String, HashSet<String>> getResultFromSp(Context context) {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        for (Map.Entry<String, ?> entry : context.getSharedPreferences("discovery_res", 0).getAll().entrySet()) {
            HashSet<String> hashSet = new HashSet<>();
            String replace = entry.getValue().toString().replace(" ", "");
            if (!TextUtils.isEmpty(replace)) {
                if (replace.contains(",")) {
                    hashSet.addAll(Arrays.asList(replace.split(",")));
                } else {
                    hashSet.add(replace);
                }
            }
            if (hashSet.size() > 0) {
                hashMap.put(entry.getKey(), hashSet);
            }
        }
        return hashMap;
    }

    public static void saveIntoSp(Context context, HashMap<String, HashSet<String>> hashMap) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("discovery_res", 0);
        sharedPreferences.edit().clear().apply();
        for (String str : hashMap.keySet()) {
            String hashSet = hashMap.get(str).toString();
            sharedPreferences.edit().putString(str, hashSet.substring(1, hashSet.length() - 1)).apply();
        }
    }
}
