package cn.sharesdk.framework.p094a;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.CheckAppKey;
import cn.sharesdk.framework.CheckAppKeyRequestUrl;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.network.CommonNetworkApi;
import cn.sharesdk.framework.p094a.p095a.MessageModel;
import cn.sharesdk.framework.p094a.p095a.MessageUtils;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.p094a.p096b.BaseEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Protocols {

    /* renamed from: i */
    private static MobCommunicator f2822i;

    /* renamed from: a */
    private SharePrefrenceUtil f2823a = SharePrefrenceUtil.m21961a();

    /* renamed from: b */
    private DeviceHelper f2824b = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: c */
    private NetworkHelper f2825c = new NetworkHelper();

    /* renamed from: d */
    private Hashon f2826d = new Hashon();

    /* renamed from: e */
    private String f2827e;

    /* renamed from: f */
    private String f2828f;

    /* renamed from: g */
    private boolean f2829g;

    /* renamed from: h */
    private HashMap<String, String> f2830h;

    /* renamed from: g */
    private static synchronized MobCommunicator m21903g() {
        MobCommunicator mobCommunicator;
        synchronized (Protocols.class) {
            if (f2822i == null) {
                f2822i = new MobCommunicator(1024, "bb7addd7e33383b74e82aba9b1d274c73aea6c0c71fcc88730270f630dbe490e1d162004f74e9532f98e17004630fbea9b346de63c23e83a7dfad70dd47cebfd", "288e7c44e01569a905386e6341baabfcde63ec37d0f0835cc662c299a5d0072970808a7fa434f0a51fa581d09d5ec4350ba5d548eafbe1fd956fb3afd678c1fb6134c904668652ec5cceb5d85da337a0f2f13ea457cca74a01b3ba0f4c809ad30d382bba2562ec9b996ae44c3700731c1b914997ef826331759e4084a019a03f");
            }
            mobCommunicator = f2822i;
        }
        return mobCommunicator;
    }

    /* renamed from: h */
    private static synchronized MobCommunicator m21902h() {
        MobCommunicator mobCommunicator;
        synchronized (Protocols.class) {
            if (f2822i == null) {
                f2822i = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
            }
            mobCommunicator = f2822i;
        }
        return mobCommunicator;
    }

    public Protocols() {
        try {
            this.f2830h = (HashMap) this.f2823a.m21931k("buffered_server_paths");
        } catch (Throwable unused) {
            this.f2830h = new HashMap<>();
        }
        m21901i();
    }

    /* renamed from: i */
    private void m21901i() {
        this.f2827e = (this.f2824b.getPackageName() + "/" + this.f2824b.getAppVersionName()) + " ShareSDK/3.10.3 " + ("Android/" + this.f2824b.getOSVersionInt());
        try {
            this.f2828f = MobSDK.dynamicModifyUrl("api-share.mob.com");
        } catch (Throwable th) {
            this.f2828f = MobSDK.checkRequestUrl("api-share.mob.com");
            SSDKLog.m21740b().m21744a("001 dynamicModifyUrl catch, no problem " + th, new Object[0]);
        }
        this.f2829g = true;
    }

    /* renamed from: a */
    public void m21918a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SSDKLog.m21740b().m21744a("duid === " + str, new Object[0]);
        this.f2827e += " " + str;
    }

    /* renamed from: b */
    public void m21912b(String str) {
        this.f2828f = str;
    }

    /* renamed from: a */
    public void m21914a(HashMap<String, String> hashMap) {
        this.f2830h = hashMap;
        this.f2823a.m21956a("buffered_server_paths", this.f2830h);
    }

    /* renamed from: j */
    private String m21900j() {
        return this.f2828f + "/conn";
    }

    /* renamed from: a */
    public HashMap<String, Object> m21920a() throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("appkey", MobSDK.getAppkey()));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Identity", CommonNetworkApi.m21747a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        String httpPost = this.f2825c.httpPost(m21900j(), arrayList, (KVPair<String>) null, arrayList2, networkTimeOut);
        SSDKLog.m21740b().m21735c(" isConnectToServer response == %s", httpPost);
        return this.f2826d.fromJson(httpPost);
    }

    /* renamed from: k */
    private String m21899k() {
        HashMap<String, String> hashMap = this.f2830h;
        if (hashMap != null && hashMap.containsKey("/date")) {
            return this.f2830h.get("/date") + "/date";
        }
        return this.f2828f + "/date";
    }

    /* renamed from: b */
    public long m21913b() throws Throwable {
        if (this.f2823a.m21934j()) {
            String str = "{}";
            try {
                str = this.f2825c.httpGet(m21899k(), null, null, null);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
            HashMap fromJson = this.f2826d.fromJson(str);
            if (fromJson.containsKey("timestamp")) {
                try {
                    long currentTimeMillis = System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(fromJson.get("timestamp")));
                    this.f2823a.m21957a("service_time", Long.valueOf(currentTimeMillis));
                    return currentTimeMillis;
                } catch (Throwable th2) {
                    SSDKLog.m21740b().m21742a(th2);
                    return this.f2823a.m21954b();
                }
            }
            return this.f2823a.m21954b();
        }
        return 0L;
    }

    /* renamed from: l */
    private String m21898l() {
        return this.f2828f + "/conf5";
    }

    /* renamed from: c */
    public HashMap<String, Object> m21910c() throws Throwable {
        String appkey = MobSDK.getAppkey();
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("appkey", appkey));
        arrayList.add(new KVPair<>("device", this.f2824b.getDeviceKey()));
        arrayList.add(new KVPair<>("plat", String.valueOf(this.f2824b.getPlatformCode())));
        arrayList.add(new KVPair<>("apppkg", this.f2824b.getPackageName()));
        arrayList.add(new KVPair<>("appver", String.valueOf(this.f2824b.getAppVersion())));
        arrayList.add(new KVPair<>("sdkver", String.valueOf(ShareSDK.SDK_VERSION_CODE)));
        arrayList.add(new KVPair<>("networktype", this.f2824b.getDetailNetworkTypeForStatic()));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Identity", CommonNetworkApi.m21747a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        String httpPost = this.f2825c.httpPost(m21898l(), arrayList, (KVPair<String>) null, arrayList2, networkTimeOut);
        try {
            HashMap fromJson = new Hashon().fromJson(httpPost);
            if (fromJson.containsKey("error")) {
                if (String.valueOf(fromJson.get("error")).contains("'appkey' is illegal")) {
                    if (TextUtils.isEmpty(appkey)) {
                        CheckAppKeyRequestUrl.m21862a().m21861b();
                    } else {
                        CheckAppKey.f2765a = true;
                    }
                }
            } else if (!TextUtils.isEmpty(appkey)) {
                CheckAppKey.f2766b = appkey;
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        SSDKLog.m21740b().m21735c(" get server config response == %s", httpPost);
        return this.f2826d.fromJson(httpPost);
    }

    /* renamed from: m */
    private String m21897m() {
        try {
            return MobSDK.dynamicModifyUrl("up.mob.com/upload/image");
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("002 dynamicModifyUrl catch, no problem " + th, new Object[0]);
            return MobSDK.checkRequestUrl("up.mob.com/upload/image");
        }
    }

    /* renamed from: c */
    public HashMap<String, Object> m21909c(String str) throws Throwable {
        KVPair<String> kVPair = new KVPair<>("file", str);
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("User-Identity", CommonNetworkApi.m21747a()));
        String httpPost = this.f2825c.httpPost(m21897m(), (ArrayList<KVPair<String>>) null, kVPair, arrayList, (NetworkHelper.NetworkTimeOut) null);
        SSDKLog.m21740b().m21735c("upload file response == %s", httpPost);
        return this.f2826d.fromJson(httpPost);
    }

    /* renamed from: n */
    private String m21896n() {
        HashMap<String, String> hashMap = this.f2830h;
        if (hashMap != null && hashMap.containsKey("/log5")) {
            return this.f2830h.get("/log5") + "/log5";
        }
        return this.f2828f + "/log5";
    }

    /* renamed from: a */
    public boolean m21916a(String str, boolean z) {
        try {
            if (MobSDK.isMob()) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("m", str);
                hashMap.put("t", z ? "1" : "0");
                String str2 = (String) m21902h().requestSynchronized(hashMap, m21896n(), false);
                SSDKLog.m21740b().m21735c("> Upload All Log  resp: %s", str2);
                if (!TextUtils.isEmpty(str2)) {
                    if (((Integer) this.f2826d.fromJson(str2).get("status")).intValue() != 200) {
                        return false;
                    }
                }
                return true;
            }
            return true;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return false;
        }
    }

    /* renamed from: o */
    private String m21895o() {
        try {
            return MobSDK.dynamicModifyUrl("l.mob.com/url/shareSdkEncryptMapping.do");
        } catch (Throwable th) {
            SSDKLog m21740b = SSDKLog.m21740b();
            m21740b.m21744a("003 dynamicModifyUrl catch, no problem " + th, new Object[0]);
            return MobSDK.checkRequestUrl("l.mob.com/url/shareSdkEncryptMapping.do");
        }
    }

    /* renamed from: a */
    public HashMap<String, Object> m21917a(String str, ArrayList<String> arrayList, int i, String str2) throws Throwable {
        if (this.f2829g) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("key", MobSDK.getAppkey()));
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                arrayList2.add(new KVPair("urls", arrayList.get(i2).toString()));
            }
            arrayList2.add(new KVPair("deviceid", this.f2824b.getDeviceKey()));
            arrayList2.add(new KVPair("snsplat", String.valueOf(i)));
            String m21905e = m21905e(str2);
            if (TextUtils.isEmpty(m21905e)) {
                return null;
            }
            arrayList2.add(new KVPair("m", m21905e));
            new ArrayList().add(new KVPair("User-Identity", CommonNetworkApi.m21747a()));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 5000;
            networkTimeOut.connectionTimeout = 5000;
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("key", MobSDK.getAppkey());
            ArrayList arrayList3 = new ArrayList();
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                arrayList3.add(URLEncoder.encode(arrayList.get(i3), "UTF-8"));
            }
            hashMap.put("urls", arrayList3);
            hashMap.put("deviceid", this.f2824b.getDeviceKey());
            hashMap.put("snsplat", Integer.valueOf(i));
            if (TextUtils.isEmpty(m21905e)) {
                return null;
            }
            hashMap.put("m", m21905e);
            HashMap<String, Object> hashMap2 = (HashMap) m21903g().requestSynchronized(hashMap, m21895o(), false);
            SSDKLog.m21740b().m21735c("> SERVER_SHORT_LINK_URL  resp: %s", hashMap2);
            if (hashMap2.size() == 0) {
                this.f2829g = false;
                return null;
            } else if (hashMap2.get("data") == null) {
                return null;
            } else {
                return hashMap2;
            }
        }
        return null;
    }

    /* renamed from: e */
    private String m21905e(String str) throws Throwable {
        boolean m21950c = this.f2823a.m21950c();
        boolean m21947d = this.f2823a.m21947d();
        StringBuilder sb = new StringBuilder();
        sb.append(Data.urlEncode(this.f2824b.getPackageName(), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(this.f2824b.getAppVersionName(), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(String.valueOf(ShareSDK.SDK_VERSION_CODE), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(String.valueOf(this.f2824b.getPlatformCode()), "utf-8"));
        sb.append("|");
        sb.append(Data.urlEncode(this.f2824b.getDetailNetworkTypeForStatic(), "utf-8"));
        sb.append("|");
        if (m21950c) {
            sb.append(Data.urlEncode(String.valueOf(this.f2824b.getOSVersionInt()), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f2824b.getScreenSize(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f2824b.getManufacturer(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f2824b.getModel(), "utf-8"));
            sb.append("|");
            sb.append(Data.urlEncode(this.f2824b.getCarrier(), "utf-8"));
            sb.append("|");
        } else {
            sb.append("|||||");
        }
        if (m21947d) {
            sb.append(str);
        } else {
            sb.append(str.split("\\|")[0]);
            sb.append("|||||");
        }
        String sb2 = sb.toString();
        SSDKLog.m21740b().m21735c("shorLinkMsg ===>>>>", sb2);
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", this.f2824b.getDeviceKey(), MobSDK.getAppkey())), sb2), 2);
    }

    /* renamed from: p */
    private String m21894p() {
        HashMap<String, String> hashMap = this.f2830h;
        if (hashMap != null && hashMap.containsKey("/snsconf")) {
            return this.f2830h.get("/snsconf") + "/snsconf";
        }
        return this.f2828f + "/snsconf";
    }

    /* renamed from: d */
    public HashMap<String, Object> m21908d() throws Throwable {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("appkey", MobSDK.getAppkey()));
        arrayList.add(new KVPair<>("device", this.f2824b.getDeviceKey()));
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("User-Identity", CommonNetworkApi.m21747a()));
        NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        return this.f2826d.fromJson(this.f2825c.httpPost(m21894p(), arrayList, (KVPair<String>) null, arrayList2, networkTimeOut));
    }

    /* renamed from: a */
    public void m21919a(BaseEvent baseEvent) throws Throwable {
        MessageUtils.m21964a(baseEvent.toString(), baseEvent.f2791e);
    }

    /* renamed from: e */
    public ArrayList<MessageModel> m21906e() throws Throwable {
        ArrayList<MessageModel> m21965a = MessageUtils.m21965a();
        return m21965a == null ? new ArrayList<>() : m21965a;
    }

    /* renamed from: a */
    public void m21915a(ArrayList<String> arrayList) throws Throwable {
        MessageUtils.m21962a(arrayList);
    }

    /* renamed from: f */
    public HashMap<String, Object> m21904f() throws Throwable {
        return this.f2826d.fromJson(this.f2823a.m21938h());
    }

    /* renamed from: b */
    public void m21911b(HashMap<String, Object> hashMap) throws Throwable {
        this.f2823a.m21939g(this.f2826d.fromHashMap(hashMap));
    }

    /* renamed from: d */
    public HashMap<String, Object> m21907d(String str) throws Throwable {
        byte[] decode = Base64.decode(str, 2);
        String deviceKey = this.f2824b.getDeviceKey();
        return this.f2826d.fromJson(new String(Data.AES128Decode(Data.rawMD5(MobSDK.getAppkey() + ":" + deviceKey), decode), "UTF-8").trim());
    }
}
