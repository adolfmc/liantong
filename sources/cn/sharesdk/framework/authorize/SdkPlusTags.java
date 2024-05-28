package cn.sharesdk.framework.authorize;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobCommunicator;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.authorize.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SdkPlusTags {

    /* renamed from: a */
    private static volatile SdkPlusTags f2862a;

    /* renamed from: b */
    private MobCommunicator f2863b;

    /* renamed from: h */
    private HashMap<String, Object> f2869h;

    /* renamed from: g */
    private boolean f2868g = false;

    /* renamed from: e */
    private DeviceHelper f2866e = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: c */
    private String f2864c = MobSDK.getAppkey();

    /* renamed from: d */
    private String f2865d = this.f2866e.getDeviceKey();

    /* renamed from: f */
    private SSDKNetworkHelper f2867f = SSDKNetworkHelper.getInstance();

    /* renamed from: a */
    public boolean m21869a() {
        return this.f2868g;
    }

    /* renamed from: a */
    public void m21867a(boolean z) {
        this.f2868g = z;
    }

    /* renamed from: b */
    public HashMap<String, Object> m21866b() {
        return this.f2869h;
    }

    /* renamed from: e */
    private synchronized MobCommunicator m21863e() {
        if (this.f2863b == null) {
            this.f2863b = new MobCommunicator(1024, "009cbd92ccef123be840deec0c6ed0547194c1e471d11b6f375e56038458fb18833e5bab2e1206b261495d7e2d1d9e5aa859e6d4b671a8ca5d78efede48e291a3f", "1dfd1d615cb891ce9a76f42d036af7fce5f8b8efaa11b2f42590ecc4ea4cff28f5f6b0726aeb76254ab5b02a58c1d5b486c39d9da1a58fa6ba2f22196493b3a4cbc283dcf749bf63679ee24d185de70c8dfe05605886c9b53e9f569082eabdf98c4fb0dcf07eb9bb3e647903489ff0b5d933bd004af5be4a1022fdda41f347f1");
        }
        return this.f2863b;
    }

    /* renamed from: c */
    public static SdkPlusTags m21865c() {
        synchronized (SdkPlusTags.class) {
            if (f2862a == null) {
                synchronized (SdkPlusTags.class) {
                    if (f2862a == null) {
                        f2862a = new SdkPlusTags();
                    }
                }
            }
        }
        return f2862a;
    }

    /* renamed from: d */
    public void m21864d() throws Throwable {
        SharePrefrenceUtil m21961a = SharePrefrenceUtil.m21961a();
        if (this.f2868g && m21961a.m21940g()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("Content-type", "application/json"));
            arrayList.add(new KVPair("sign", m21868a(this.f2864c, this.f2865d)));
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("appkey", this.f2864c);
            hashMap.put("deviceId", this.f2865d);
            String authorize = DeviceAuthorizer.authorize(new SHARESDK());
            hashMap.put("duid", authorize);
            try {
                if (!TextUtils.isEmpty(this.f2864c) && !TextUtils.isEmpty(this.f2865d) && !TextUtils.isEmpty(authorize)) {
                    this.f2869h = (HashMap) m21863e().requestSynchronized(hashMap, "http://p.share.mob.com/tags/getTagList", false);
                } else {
                    SSDKLog.m21740b().m21744a("SdkPlusTags request userTags that appkey or deviceId or duid is null", new Object[0]);
                }
            } catch (Exception e) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("SdkPlusTags request userTags is error T===> " + e, new Object[0]);
            }
        }
    }

    /* renamed from: a */
    private String m21868a(String str, String str2) throws Throwable {
        byte[] rawMD5 = Data.rawMD5(String.format("%s:%s", this.f2866e.getDeviceKey(), MobSDK.getAppkey()));
        return Base64.encodeToString(Data.AES128Encode(rawMD5, str + str2), 2);
    }
}
