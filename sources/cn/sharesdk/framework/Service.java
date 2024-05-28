package cn.sharesdk.framework;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.AppUtils;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class Service {
    protected abstract int getServiceVersionInt();

    public abstract String getServiceVersionName();

    public void onBind() {
    }

    public void onUnbind() {
    }

    public String getDeviceKey() {
        return DeviceHelper.getInstance(MobSDK.getContext()).getDeviceKey();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class ServiceEvent {
        private static final int PLATFORM = 1;
        protected Service service;

        public ServiceEvent(Service service) {
            this.service = service;
        }

        protected HashMap<String, Object> toMap() {
            HashMap<String, Object> hashMap = new HashMap<>();
            DeviceHelper deviceHelper = DeviceHelper.getInstance(MobSDK.getContext());
            hashMap.put("deviceid", deviceHelper.getDeviceKey());
            hashMap.put("appkey", MobSDK.getAppkey());
            hashMap.put("apppkg", deviceHelper.getPackageName());
            hashMap.put("appver", Integer.valueOf(deviceHelper.getAppVersion()));
            hashMap.put("sdkver", Integer.valueOf(this.service.getServiceVersionInt()));
            hashMap.put("plat", 1);
            hashMap.put("networktype", deviceHelper.getDetailNetworkTypeForStatic());
            hashMap.put("deviceData", AppUtils.m21712d());
            return hashMap;
        }

        public final String toString() {
            return new Hashon().fromHashMap(toMap());
        }

        protected HashMap<String, Object> filterShareContent(int i, Platform.ShareParams shareParams, HashMap<String, Object> hashMap) {
            Platform platform;
            try {
                platform = ShareSDK.getPlatform(ShareSDK.platformIdToName(i));
            } catch (Throwable th) {
                SSDKLog m21740b = SSDKLog.m21740b();
                m21740b.m21744a("ShareSDK Service filterShareContent catch: " + th, new Object[0]);
                platform = null;
            }
            if (platform == null) {
                return null;
            }
            ShareEvent.C1746a filterShareContent = platform.filterShareContent(shareParams, hashMap);
            HashMap<String, Object> hashMap2 = new HashMap<>();
            hashMap2.put("shareID", filterShareContent.f2815a);
            hashMap2.put("shareContent", new Hashon().fromJson(filterShareContent.toString()));
            SSDKLog.m21740b().m21735c("filterShareContent ==>>%s", hashMap2);
            return hashMap2;
        }
    }
}
