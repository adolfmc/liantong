package cn.sharesdk.framework;

import android.text.TextUtils;
import cn.sharesdk.framework.network.CommonNetworkApi;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CheckAppKeyRequestUrl {

    /* renamed from: a */
    private static String f2870a = "";

    /* renamed from: b */
    private static volatile CheckAppKeyRequestUrl f2871b;

    /* renamed from: c */
    private DeviceHelper f2872c = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: d */
    private NetworkHelper f2873d = new NetworkHelper();

    /* renamed from: e */
    private String f2874e = MobSDK.checkRequestUrl("api-share.mob.com");

    private CheckAppKeyRequestUrl() {
    }

    /* renamed from: a */
    public static CheckAppKeyRequestUrl m21862a() {
        synchronized (CheckAppKeyRequestUrl.class) {
            if (f2871b == null) {
                synchronized (CheckAppKeyRequestUrl.class) {
                    if (f2871b == null) {
                        f2871b = new CheckAppKeyRequestUrl();
                    }
                }
            }
        }
        return f2871b;
    }

    /* renamed from: b */
    public void m21861b() {
        try {
            ArrayList<KVPair<String>> arrayList = new ArrayList<>();
            String appkey = MobSDK.getAppkey();
            if (TextUtils.isEmpty(appkey)) {
                return;
            }
            arrayList.add(new KVPair<>("appkey", appkey));
            arrayList.add(new KVPair<>("device", this.f2872c.getDeviceKey()));
            arrayList.add(new KVPair<>("plat", String.valueOf(this.f2872c.getPlatformCode())));
            arrayList.add(new KVPair<>("apppkg", this.f2872c.getPackageName()));
            arrayList.add(new KVPair<>("appver", String.valueOf(this.f2872c.getAppVersion())));
            arrayList.add(new KVPair<>("sdkver", String.valueOf(ShareSDK.SDK_VERSION_CODE)));
            arrayList.add(new KVPair<>("networktype", this.f2872c.getDetailNetworkTypeForStatic()));
            ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
            arrayList2.add(new KVPair<>("User-Identity", CommonNetworkApi.m21747a()));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            HashMap fromJson = new Hashon().fromJson(this.f2873d.httpPost(m21860c(), arrayList, (KVPair<String>) null, arrayList2, networkTimeOut));
            if (fromJson.containsKey("error")) {
                if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    CheckAppKey.f2765a = true;
                }
            } else {
                CheckAppKey.f2766b = appkey;
            }
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("updateServerConfig " + th, new Object[0]);
        }
    }

    /* renamed from: c */
    private String m21860c() {
        return this.f2874e + "/conf5";
    }
}
