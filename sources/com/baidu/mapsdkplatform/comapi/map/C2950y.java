package com.baidu.mapsdkplatform.comapi.map;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.util.AlgorithmUtil;
import com.baidu.mapsdkplatform.comapi.util.SysOSAPI;
import com.baidu.mapsdkplatform.comjni.tools.JNITools;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.map.y */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2950y {
    /* renamed from: b */
    private String m18188b(String str) {
        String str2 = SysOSAPI.f7443c;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        String aESSaltKey = JNITools.getAESSaltKey(str2);
        String aESViKey = JNITools.getAESViKey(str2);
        if (!TextUtils.isEmpty(aESSaltKey) && !TextUtils.isEmpty(aESViKey) && !TextUtils.isEmpty(str)) {
            try {
                return new String(AlgorithmUtil.getDecryptInfo(aESViKey, aESSaltKey, m18187c(str))).trim();
            } catch (Exception unused) {
                Log.e("PrismBuildingInfo", "getBuildingGeom Decrypt failed");
            }
        }
        return null;
    }

    /* renamed from: c */
    private byte[] m18187c(String str) {
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 1;
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i2, i3), 16) * 16) + Integer.parseInt(str.substring(i3, i2 + 2), 16));
        }
        return bArr;
    }

    /* renamed from: a */
    public ArrayList<LatLng> m18189a(String str) {
        String m18188b = m18188b(str);
        ArrayList<LatLng> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(m18188b)) {
            for (String str2 : m18188b.split(";")) {
                String[] split = str2.split(",");
                if (split.length == 2) {
                    try {
                        arrayList.add(new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }
}
