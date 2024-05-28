package com.gmrz.appsdk.util;

import android.text.TextUtils;
import com.gmrz.appsdk.FidoReInfo;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.UACPlugin;
import com.mabeijianxi.smallvideorecord2.MediaRecorderBase;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CoreComparator {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static FidoReInfo compareFidoOriginalAuthenticatorAAIDs(String str, String str2, HashMap<String, HashSet<String>> hashMap, FidoReInfo fidoReInfo, HashMap<String, HashSet<String>> hashMap2, HashMap<String, HashSet<String>> hashMap3) {
        boolean z;
        String str3;
        boolean z2;
        fidoReInfo.setStatus(FidoStatus.SUCCESS);
        str.hashCode();
        switch (str.hashCode()) {
            case MediaRecorderBase.VIDEO_BITRATE_MEDIUM /* 1536 */:
                if (str.equals("00")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case 1537:
                if (str.equals("01")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1538:
                if (str.equals("02")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1539:
                if (str.equals("03")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        HashSet<String> hashSet = null;
        switch (z) {
            case false:
                str3 = "2";
                break;
            case true:
                str3 = "64";
                break;
            case true:
                str3 = "16";
                break;
            case true:
                str3 = "128";
                break;
            default:
                str3 = null;
                break;
        }
        HashSet<String> hashSet2 = (hashMap == null || hashMap.size() == 0) ? null : hashMap.get(str3);
        if (!TextUtils.isEmpty(str2) && !str2.equals("00")) {
            if (str2.equals("01")) {
                hashSet = hashMap3.get(str);
            }
        } else {
            hashSet = hashMap2.get(str);
        }
        if (hashSet != null && hashSet.size() != 0 && hashSet2 != null && hashSet2.size() != 0) {
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                if (hashSet2.contains(it.next())) {
                    str.hashCode();
                    switch (str.hashCode()) {
                        case MediaRecorderBase.VIDEO_BITRATE_MEDIUM /* 1536 */:
                            if (str.equals("00")) {
                                z2 = false;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1537:
                            if (str.equals("01")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1538:
                            if (str.equals("02")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1539:
                            if (str.equals("03")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        default:
                            z2 = true;
                            break;
                    }
                    switch (z2) {
                        case false:
                            fidoReInfo.setFpStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setIrisStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setFidoFaceStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setFidoGestureStatus(FidoStatus.SUCCESS);
                            continue;
                    }
                }
            }
        }
        return fidoReInfo;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static FidoReInfo compareGmrzPluginAuthenticatorsAAIDs(String str, String str2, FidoReInfo fidoReInfo, HashMap<String, HashSet<String>> hashMap, HashMap<String, HashSet<String>> hashMap2, Set<UACPlugin> set) {
        boolean z;
        String str3;
        boolean z2;
        fidoReInfo.setStatus(FidoStatus.SUCCESS);
        str.hashCode();
        switch (str.hashCode()) {
            case 1567:
                if (str.equals("10")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case 1568:
                if (str.equals("11")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1569:
                if (str.equals("12")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1570:
                if (str.equals("13")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1571:
                if (str.equals("14")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        HashSet<String> hashSet = null;
        switch (z) {
            case false:
                str3 = null;
                for (UACPlugin uACPlugin : set) {
                    if (uACPlugin == UACPlugin.REMOTEFACEPLUGIN) {
                        str3 = "004A#01B0";
                    }
                }
                break;
            case true:
                str3 = null;
                for (UACPlugin uACPlugin2 : set) {
                    if (uACPlugin2 == UACPlugin.REMOTEVOICEPLUGIN) {
                        str3 = "004A#01B1";
                    }
                }
                break;
            case true:
                str3 = "004A#01B2";
                break;
            case true:
                str3 = null;
                for (UACPlugin uACPlugin3 : set) {
                    if (uACPlugin3 == UACPlugin.REMOTEFACEPLUGIN) {
                        str3 = "004A#01B3";
                    }
                }
                break;
            case true:
                str3 = "004A#01B4";
                break;
            default:
                str3 = null;
                break;
        }
        if (!TextUtils.isEmpty(str2) && !str2.equals("00")) {
            if (str2.equals("01")) {
                hashSet = hashMap2.get(str);
            }
        } else {
            hashSet = hashMap.get(str);
        }
        if (hashSet != null && hashSet.size() != 0) {
            Iterator<String> it = hashSet.iterator();
            while (it.hasNext()) {
                if (it.next().equals(str3)) {
                    str.hashCode();
                    switch (str.hashCode()) {
                        case 1567:
                            if (str.equals("10")) {
                                z2 = false;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1568:
                            if (str.equals("11")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1569:
                            if (str.equals("12")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1570:
                            if (str.equals("13")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        case 1571:
                            if (str.equals("14")) {
                                z2 = true;
                                break;
                            }
                            z2 = true;
                            break;
                        default:
                            z2 = true;
                            break;
                    }
                    switch (z2) {
                        case false:
                            fidoReInfo.setFaceStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setVoiceStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setRealNameStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setRealNameFaceStatus(FidoStatus.SUCCESS);
                            continue;
                        case true:
                            fidoReInfo.setScanQRCodeStatus(FidoStatus.SUCCESS);
                            continue;
                    }
                }
            }
        }
        return fidoReInfo;
    }
}
