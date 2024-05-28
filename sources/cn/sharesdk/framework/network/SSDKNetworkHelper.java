package cn.sharesdk.framework.network;

import android.text.TextUtils;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.network.HTTPPart;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.RawNetworkCallback;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SSDKNetworkHelper extends NetworkHelper {
    private static SSDKNetworkHelper helper;

    private SSDKNetworkHelper() {
    }

    public static SSDKNetworkHelper getInstance() {
        if (helper == null) {
            helper = new SSDKNetworkHelper();
        }
        return helper;
    }

    public String httpGet(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return httpGet(str, arrayList, null, null, str2, i);
    }

    public String httpGet(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        logEvent(str2, i);
        return super.httpGet(str, arrayList, arrayList2, networkTimeOut);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, String str2, int i) throws Throwable {
        return httpPost(str, arrayList, (KVPair<String>) null, str2, i);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, String str2, int i) throws Throwable {
        return httpPost(str, arrayList, kVPair, null, str2, i);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, String str2, int i) throws Throwable {
        return httpPost(str, arrayList, kVPair, arrayList2, (NetworkHelper.NetworkTimeOut) null, str2, i);
    }

    public String httpPost(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        logEvent(str2, i);
        return super.httpPost(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, String str2, int i) throws Throwable {
        return httpPut(str, arrayList, kVPair, null, null, str2, i);
    }

    public String httpPut(String str, ArrayList<KVPair<String>> arrayList, KVPair<String> kVPair, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        logEvent(str2, i);
        return super.httpPut(str, arrayList, kVPair, arrayList2, networkTimeOut);
    }

    public void rawPost(String str, ArrayList<KVPair<String>> arrayList, HTTPPart hTTPPart, RawNetworkCallback rawNetworkCallback, String str2, int i) throws Throwable {
        logEvent(str2, i);
        super.rawPost(str, arrayList, hTTPPart, rawNetworkCallback, (NetworkHelper.NetworkTimeOut) null);
    }

    public String jsonPost(String str, ArrayList<KVPair<String>> arrayList, ArrayList<KVPair<String>> arrayList2, NetworkHelper.NetworkTimeOut networkTimeOut, String str2, int i) throws Throwable {
        logEvent(str2, i);
        return super.jsonPost(str, arrayList, arrayList2, networkTimeOut);
    }

    private void logEvent(String str, int i) {
        if (TextUtils.isEmpty(str) || i <= 0) {
            return;
        }
        ShareSDK.logApiEvent(str, i);
    }
}
