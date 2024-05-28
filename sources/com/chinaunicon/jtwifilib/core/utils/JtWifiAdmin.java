package com.chinaunicon.jtwifilib.core.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import com.chinaunicon.jtwifilib.jtcommon.util.FrequencyUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JtWifiAdmin {
    public static String FREQUENCYBAND;
    Context context;
    private List<WifiConfiguration> mWifiConfiguration;
    private WifiInfo mWifiInfo;
    private List<ScanResult> mWifiList;
    WifiManager.WifiLock mWifiLock;
    private WifiManager mWifiManager;

    public JtWifiAdmin(Context context) {
        this.context = context;
        getWifi();
    }

    public void getWifi() {
        this.mWifiManager = (WifiManager) this.context.getSystemService("wifi");
        this.mWifiInfo = this.mWifiManager.getConnectionInfo();
    }

    @SuppressLint({"WrongConstant"})
    public boolean openWifi(Activity activity) {
        if (!this.mWifiManager.isWifiEnabled()) {
            return this.mWifiManager.setWifiEnabled(true);
        }
        if (this.mWifiManager.getWifiState() == 2) {
            Toast.makeText(activity, "亲，Wifi正在开启，不用再开了", 0).show();
            return true;
        }
        Toast.makeText(activity, "亲，Wifi已经开启,不用再开了", 0).show();
        return true;
    }

    public boolean checkWifiIsEnable() {
        WifiManager wifiManager = this.mWifiManager;
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    @SuppressLint({"WrongConstant"})
    public void closeWifi(Context context) {
        if (this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(false);
        } else if (this.mWifiManager.getWifiState() == 1) {
            Toast.makeText(context, "亲，Wifi已经关闭，不用再关了", 0).show();
        } else if (this.mWifiManager.getWifiState() == 0) {
            Toast.makeText(context, "亲，Wifi正在关闭，不用再关了", 0).show();
        } else {
            Toast.makeText(context, "请重新关闭", 0).show();
        }
    }

    @SuppressLint({"WrongConstant"})
    public void checkState(Context context) {
        if (this.mWifiManager.getWifiState() == 0) {
            Toast.makeText(context, "Wifi正在关闭", 0).show();
        } else if (this.mWifiManager.getWifiState() == 1) {
            Toast.makeText(context, "Wifi已经关闭", 0).show();
        } else if (this.mWifiManager.getWifiState() == 2) {
            Toast.makeText(context, "Wifi正在开启", 0).show();
        } else if (this.mWifiManager.getWifiState() == 3) {
            Toast.makeText(context, "Wifi已经开启", 0).show();
        } else {
            Toast.makeText(context, "没有获取到WiFi状态", 0).show();
        }
    }

    public void acquireWifiLock() {
        this.mWifiLock.acquire();
    }

    public void releaseWifiLock() {
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.acquire();
        }
    }

    public void creatWifiLock() {
        this.mWifiLock = this.mWifiManager.createWifiLock("Test");
    }

    public List<WifiConfiguration> getConfiguration() {
        return this.mWifiConfiguration;
    }

    public void connectConfiguration(int i) {
        if (i > this.mWifiConfiguration.size()) {
            return;
        }
        this.mWifiManager.enableNetwork(this.mWifiConfiguration.get(i).networkId, true);
    }

    @SuppressLint({"WrongConstant"})
    public void startScan(Context context) {
        boolean z;
        this.mWifiManager.startScan();
        List<ScanResult> scanResults = this.mWifiManager.getScanResults();
        this.mWifiConfiguration = this.mWifiManager.getConfiguredNetworks();
        if (scanResults == null) {
            if (this.mWifiManager.getWifiState() == 3) {
                Toast.makeText(context, "当前区域没有无线网络", 0).show();
                return;
            } else if (this.mWifiManager.getWifiState() == 2) {
                Toast.makeText(context, "wifi正在开启，请稍后扫描", 0).show();
                return;
            } else {
                Toast.makeText(context, "WiFi没有开启", 0).show();
                return;
            }
        }
        this.mWifiList = new ArrayList();
        for (ScanResult scanResult : scanResults) {
            if (scanResult.SSID != null && scanResult.SSID.length() != 0 && !scanResult.capabilities.contains("[IBSS]")) {
                Log.i("MainActivity", "result= " + scanResult.SSID + " capabilities= " + scanResult.capabilities);
                Iterator<ScanResult> it = this.mWifiList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    ScanResult next = it.next();
                    Log.i("MainActivity", "item= " + next.SSID + " capabilities=" + next.capabilities);
                    if (next.SSID.equals(scanResult.SSID) && next.capabilities.equals(scanResult.capabilities)) {
                        Log.i("MainActivity", "found true");
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    this.mWifiList.add(scanResult);
                }
            }
        }
    }

    public List<ScanResult> getWifiList() {
        return this.mWifiList;
    }

    public StringBuilder lookUpScan() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < this.mWifiList.size()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Index_");
            int i2 = i + 1;
            sb2.append(new Integer(i2).toString());
            sb2.append(":");
            sb.append(sb2.toString());
            sb.append(this.mWifiList.get(i).toString());
            sb.append("/n");
            i = i2;
        }
        return sb;
    }

    public String getMacAddress() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return wifiInfo == null ? "NULL" : wifiInfo.getMacAddress();
    }

    public String getBSSID() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return wifiInfo == null ? "" : wifiInfo.getBSSID();
    }

    public String getSSID() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return wifiInfo == null ? "" : wifiInfo.getSSID();
    }

    public String getCurrentNetworkRssi() {
        return String.valueOf(this.mWifiInfo.getRssi());
    }

    public int getIPAddress() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return 0;
        }
        return wifiInfo.getIpAddress();
    }

    public int getIPAddressNew() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return 0;
        }
        return wifiInfo.getIpAddress();
    }

    public int getNetworkId() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return 0;
        }
        return wifiInfo.getNetworkId();
    }

    public String getWifiInfo() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return wifiInfo == null ? "NULL" : wifiInfo.toString();
    }

    @RequiresApi(api = 21)
    public String getWifiAgreement() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return "--";
        }
        FREQUENCYBAND = FrequencyUtil.getWifiFrequencyBand(wifiInfo.getFrequency());
        int linkSpeed = this.mWifiInfo.getLinkSpeed();
        String str = FREQUENCYBAND;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 1714) {
            if (hashCode == 1535439 && str.equals("2.4G")) {
                c = 0;
            }
        } else if (str.equals("5G")) {
            c = 1;
        }
        switch (c) {
            case 0:
                return linkSpeed <= 285 ? "WIFI 4(802.11n)" : "WIFI 6(802.11ax)";
            case 1:
                return linkSpeed < 870 ? "WIFI 5(802.11ac)" : "WIFI 6(802.11ax)";
            default:
                return "--";
        }
    }

    @RequiresApi(api = 21)
    public String getConnectFrequency() {
        WifiInfo wifiInfo = this.mWifiInfo;
        return wifiInfo == null ? "" : FrequencyUtil.getWifiFrequencyBand(wifiInfo.getFrequency());
    }

    public String getLinkSpeed() {
        if (this.mWifiInfo == null) {
            return "";
        }
        return this.mWifiInfo.getLinkSpeed() + "";
    }

    public boolean addNetwork(WifiConfiguration wifiConfiguration) {
        int i;
        WifiConfiguration IsExsits = IsExsits(wifiConfiguration.SSID.trim().substring(1, wifiConfiguration.SSID.trim().length() - 1));
        if (IsExsits == null) {
            i = this.mWifiManager.addNetwork(wifiConfiguration);
            if (i != -1) {
                this.mWifiManager.saveConfiguration();
            }
        } else {
            i = IsExsits.networkId;
        }
        JtL.m16339i("123", "wcgID=" + i);
        return this.mWifiManager.enableNetwork(i, true);
    }

    public void disconnectWifi(int i) {
        this.mWifiManager.disableNetwork(i);
        this.mWifiManager.disconnect();
    }

    public void removeWifi(int i) {
        disconnectWifi(i);
        this.mWifiManager.removeNetwork(i);
    }

    public WifiConfiguration CreateWifiInfo(String str, String str2, int i) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        WifiConfiguration IsExsits = IsExsits(str);
        if (IsExsits != null) {
            this.mWifiManager.removeNetwork(IsExsits.networkId);
        }
        if (i == 1) {
            wifiConfiguration.wepKeys[0] = "";
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 2) {
            wifiConfiguration.hiddenSSID = false;
            String[] strArr = wifiConfiguration.wepKeys;
            strArr[0] = "\"" + str2 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.wepTxKeyIndex = 0;
        }
        if (i == 3) {
            wifiConfiguration.hiddenSSID = false;
            wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedKeyManagement.set(1);
            wifiConfiguration.allowedGroupCiphers.set(2);
            wifiConfiguration.allowedGroupCiphers.set(3);
            wifiConfiguration.allowedPairwiseCiphers.set(1);
            wifiConfiguration.allowedPairwiseCiphers.set(2);
            wifiConfiguration.allowedPairwiseCiphers.set(0);
            wifiConfiguration.allowedProtocols.set(0);
            wifiConfiguration.allowedProtocols.set(1);
            wifiConfiguration.status = 2;
        }
        return wifiConfiguration;
    }

    private WifiConfiguration IsExsits(String str) {
        List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return null;
        }
        for (WifiConfiguration wifiConfiguration : configuredNetworks) {
            String str2 = wifiConfiguration.SSID;
            if (str2.equals("\"" + str + "\"")) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public WifiManager getWifiManager() {
        return this.mWifiManager;
    }
}
