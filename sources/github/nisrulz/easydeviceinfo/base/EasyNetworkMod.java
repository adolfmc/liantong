package github.nisrulz.easydeviceinfo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyNetworkMod {
    private static final String SOCKET_EXCEPTION = "Socket Exception";
    private final Context context;

    public EasyNetworkMod(Context context) {
        this.context = context;
    }

    public final boolean isWifiEnabled() {
        WifiManager wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            return wifiManager.isWifiEnabled();
        }
        return false;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"})
    public final boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return PermissionUtil.hasPermission(this.context, "android.permission.INTERNET") && PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) this.context.getApplicationContext().getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected();
    }

    public final String getIPv4Address() {
        String str = null;
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String upperCase = inetAddress.getHostAddress().toUpperCase(Locale.getDefault());
                        if (inetAddress instanceof Inet4Address) {
                            str = upperCase;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e("EasyDeviceInfo", SOCKET_EXCEPTION, e);
            }
        }
        return CheckValidityUtil.checkValidData(str);
    }

    public final String getIPv6Address() {
        String str = null;
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                    if (!inetAddress.isLoopbackAddress()) {
                        String upperCase = inetAddress.getHostAddress().toUpperCase(Locale.getDefault());
                        if (!(inetAddress instanceof Inet4Address)) {
                            int indexOf = upperCase.indexOf(37);
                            str = indexOf < 0 ? upperCase : upperCase.substring(0, indexOf);
                        }
                    }
                }
            }
        } catch (SocketException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e("EasyDeviceInfo", SOCKET_EXCEPTION, e);
            }
        }
        return CheckValidityUtil.checkValidData(str);
    }

    @NetworkType
    @RequiresPermission(allOf = {"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"})
    public final int getNetworkType() {
        ConnectivityManager connectivityManager;
        TelephonyManager telephonyManager;
        if (PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_NETWORK_STATE") && (connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity")) != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return 0;
            }
            if (activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 6) {
                return 1;
            }
            if (activeNetworkInfo.getType() == 0 && (telephonyManager = (TelephonyManager) this.context.getSystemService("phone")) != null && telephonyManager.getSimState() == 5) {
                switch (telephonyManager.getNetworkType()) {
                    case 0:
                        return 2;
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                    case 11:
                        return 3;
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 15:
                        return 4;
                    case 13:
                        return 5;
                    case 14:
                    default:
                        return 6;
                }
            }
        }
        return 0;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public final String getWifiMAC() {
        String str = "02:00:00:00:00:00";
        if (PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_WIFI_STATE")) {
            if (Build.VERSION.SDK_INT >= 23) {
                Enumeration<NetworkInterface> enumeration = null;
                try {
                    enumeration = NetworkInterface.getNetworkInterfaces();
                } catch (SocketException e) {
                    if (EasyDeviceInfo.debuggable) {
                        Log.e("EasyDeviceInfo", SOCKET_EXCEPTION, e);
                    }
                }
                while (enumeration != null && enumeration.hasMoreElements()) {
                    NetworkInterface nextElement = enumeration.nextElement();
                    byte[] bArr = new byte[0];
                    try {
                        bArr = nextElement.getHardwareAddress();
                    } catch (SocketException e2) {
                        if (EasyDeviceInfo.debuggable) {
                            Log.e("EasyDeviceInfo", SOCKET_EXCEPTION, e2);
                        }
                    }
                    if (bArr != null && bArr.length != 0) {
                        StringBuilder sb = new StringBuilder();
                        int length = bArr.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Byte.valueOf(bArr[i])));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        String sb2 = sb.toString();
                        if ("wlan0".equals(nextElement.getName())) {
                            str = sb2;
                        }
                    }
                }
            } else {
                WifiManager wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null) {
                    str = wifiManager.getConnectionInfo().getMacAddress();
                }
            }
        }
        return CheckValidityUtil.checkValidData(str);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"})
    public final String getWifiSSID() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String str = null;
        if (PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_WIFI_STATE") && (connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && (wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi")) != null && (connectionInfo = wifiManager.getConnectionInfo()) != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
            str = connectionInfo.getSSID();
        }
        return CheckValidityUtil.checkValidData(str);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"})
    public final String getWifiBSSID() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String str = null;
        if (PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_WIFI_STATE") && (connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && (wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi")) != null && (connectionInfo = wifiManager.getConnectionInfo()) != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
            str = connectionInfo.getBSSID();
        }
        return CheckValidityUtil.checkValidData(str);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_NETWORK_STATE"})
    public final String getWifiLinkSpeed() {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        WifiManager wifiManager;
        WifiInfo connectionInfo;
        String str = null;
        if (PermissionUtil.hasPermission(this.context, "android.permission.ACCESS_WIFI_STATE") && (connectivityManager = (ConnectivityManager) this.context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected() && (wifiManager = (WifiManager) this.context.getApplicationContext().getSystemService("wifi")) != null && (connectionInfo = wifiManager.getConnectionInfo()) != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
            str = connectionInfo.getLinkSpeed() + " Mbps";
        }
        return CheckValidityUtil.checkValidData(str);
    }

    public final boolean isWifiAwareAvailable() {
        return Build.VERSION.SDK_INT >= 26 && this.context.getPackageManager().hasSystemFeature("android.hardware.wifi.aware");
    }
}
