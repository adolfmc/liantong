package cn.sharesdk.system.text.login;

import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.network.SSDKNetworkHelper;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.commons.SHARESDK;
import com.mob.commons.authorize.DeviceAuthorizer;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Protocol {

    /* renamed from: d */
    private String f3075d = "api.share.mob.com:80";

    /* renamed from: a */
    private Hashon f3072a = new Hashon();

    /* renamed from: c */
    private SSDKNetworkHelper f3074c = SSDKNetworkHelper.getInstance();

    /* renamed from: b */
    private DeviceHelper f3073b = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: a */
    private String m21536a() {
        return "http://" + this.f3075d + "/sendCode";
    }

    /* renamed from: b */
    private String m21532b() {
        return "http://" + this.f3075d + "/verifyCode";
    }

    /* renamed from: a */
    public HashMap<String, Object> m21534a(String str, String str2, int i) {
        try {
            HashMap<String, Object> m21531c = m21531c();
            m21531c.put("zone", str);
            m21531c.put("phone", str2);
            m21531c.put("type", Integer.valueOf(i));
            String m21535a = m21535a(this.f3072a.fromHashMap(m21531c));
            ArrayList<KVPair<String>> arrayList = new ArrayList<>();
            arrayList.add(new KVPair<>("m", m21535a));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            String httpPost = this.f3074c.httpPost(m21536a(), arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) null, networkTimeOut);
            if (TextUtils.isEmpty(httpPost)) {
                return null;
            }
            return this.f3072a.fromJson(httpPost);
        } catch (ConnectException e) {
            SSDKLog.m21740b().m21737b(e);
            return null;
        } catch (TimeoutException e2) {
            SSDKLog.m21740b().m21737b(e2);
            return null;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* renamed from: a */
    public HashMap<String, Object> m21533a(String str, String str2, int i, String str3) {
        try {
            HashMap<String, Object> m21531c = m21531c();
            m21531c.put("zone", str);
            m21531c.put("phone", str2);
            m21531c.put("type", Integer.valueOf(i));
            m21531c.put("code", str3);
            String m21535a = m21535a(this.f3072a.fromHashMap(m21531c));
            ArrayList<KVPair<String>> arrayList = new ArrayList<>();
            arrayList.add(new KVPair<>("m", m21535a));
            NetworkHelper.NetworkTimeOut networkTimeOut = new NetworkHelper.NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            String httpPost = this.f3074c.httpPost(m21532b(), arrayList, (KVPair<String>) null, (ArrayList<KVPair<String>>) null, networkTimeOut);
            if (TextUtils.isEmpty(httpPost)) {
                return null;
            }
            return this.f3072a.fromJson(httpPost);
        } catch (ConnectException e) {
            SSDKLog.m21740b().m21737b(e);
            return null;
        } catch (TimeoutException e2) {
            SSDKLog.m21740b().m21737b(e2);
            return null;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* renamed from: a */
    private String m21535a(String str) throws Throwable {
        Random random = new Random();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeLong(random.nextLong());
        dataOutputStream.writeLong(random.nextLong());
        dataOutputStream.flush();
        dataOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byte[] AES128Encode = Data.AES128Encode(byteArray, str);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
        dataOutputStream2.write(byteArray);
        dataOutputStream2.write(AES128Encode);
        dataOutputStream2.flush();
        dataOutputStream2.close();
        return Base64.encodeToString(byteArrayOutputStream2.toByteArray(), 2);
    }

    /* renamed from: c */
    private HashMap<String, Object> m21531c() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("deviceid", this.f3073b.getDeviceKey());
        hashMap.put("duid", DeviceAuthorizer.authorize(new SHARESDK()));
        hashMap.put("appkey", MobSDK.getAppkey());
        hashMap.put("apppkg", this.f3073b.getPackageName());
        hashMap.put("appver", Integer.valueOf(this.f3073b.getAppVersion()));
        hashMap.put("plat", Integer.valueOf(this.f3073b.getPlatformCode()));
        hashMap.put("sdkver", Integer.valueOf(ShareSDK.SDK_VERSION_CODE));
        hashMap.put("factory", this.f3073b.getManufacturer());
        hashMap.put("model", this.f3073b.getModel());
        hashMap.put("simserial", this.f3073b.getSimSerialNumber());
        hashMap.put("carrier", this.f3073b.getCarrier());
        hashMap.put("imei", this.f3073b.getIMEI());
        try {
            hashMap.put("adsid", this.f3073b.getAdvertisingID());
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        return hashMap;
    }
}
