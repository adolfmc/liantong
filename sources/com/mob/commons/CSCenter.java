package com.mob.commons;

import android.content.pm.PackageInfo;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import com.mob.MobCustomController;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CSCenter implements PublicMemberKeeper {

    /* renamed from: a */
    private static CSCenter f14035a;

    /* renamed from: b */
    private MobCustomController f14036b;

    /* renamed from: c */
    private C5692a f14037c = new C5692a();

    private CSCenter() {
    }

    public static CSCenter getInstance() {
        if (f14035a == null) {
            synchronized (CSCenter.class) {
                if (f14035a == null) {
                    f14035a = new CSCenter();
                }
            }
        }
        return f14035a;
    }

    public void updateCustomController(MobCustomController mobCustomController) {
        this.f14036b = mobCustomController;
    }

    public boolean isCusControllerNotNull() {
        return this.f14036b != null;
    }

    public C5692a invocationRecord() {
        return this.f14037c;
    }

    public boolean isLocationDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isLocationDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isAndroidIdEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isAndroidIdEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isOaidEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isOaidEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isAdvertisingIdEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isAdvertisingIdEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isWifiDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isWifiDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isCellLocationDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isCellLocationDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isAppListDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isAppListDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isIpAddressEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isIpAddressEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isPhoneStateDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isPhoneStateDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public boolean isSocietyPlatformDataEnable() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.isSocietyPlatformDataEnable();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return true;
            }
        }
        return true;
    }

    public Location getLocation() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getLocation();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getAndroidId() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getAndroidId();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getOaid() {
        if (this.f14036b != null) {
            try {
                this.f14037c.f14039b = true;
                return this.f14036b.getOaid();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getAdvertisingId() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getAdvertisingId();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public WifiInfo getConnectionInfo() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getConnectionInfo();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public List<ScanResult> getWifiScanResults() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getWifiScanResults();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public CellLocation getCellLocation() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getCellLocation();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public List<NeighboringCellInfo> getNeighboringCellInfo() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getNeighboringCellInfo();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public List<PackageInfo> getPackageInfos() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getPackageInfos();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getIpAddress() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getIpAddress();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getCellIpv4() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getCellIpv4();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getCellIpv6() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getCellIpv6();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public int getActiveSubscriptionInfoCount() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getActiveSubscriptionInfoCount();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return -1;
            }
        }
        return -1;
    }

    public List<SubscriptionInfo> getActiveSubscriptionInfoList() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getActiveSubscriptionInfoList();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getSimOperatorName() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getSimOperatorName();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public String getSimOperator() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getSimOperator();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    public int getNetworkType() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getNetworkType();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return -1;
            }
        }
        return -1;
    }

    public ServiceState getServiceState() {
        MobCustomController mobCustomController = this.f14036b;
        if (mobCustomController != null) {
            try {
                return mobCustomController.getServiceState();
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
                return null;
            }
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.CSCenter$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5692a {

        /* renamed from: b */
        private boolean f14039b = false;

        public C5692a() {
        }

        /* renamed from: a */
        public boolean m12826a() {
            return this.f14039b;
        }
    }
}
