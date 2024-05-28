package com.chinaunicon.jtwifilib.jtcommon.model;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface JtCommand {
    public static final String LANNetInfoApi = "GET_LAN_NET_INFO_EX";
    public static final String Plugin_Name = "";
    public static final String RPCMethod = "Post1";
    public static final String SSIDDisableApi = "SET_WLAN_SSID_DISABLE";
    public static final String SSIDEnableApi = "SET_WLAN_SSID_ENABLE";
    public static final String Version = "1.0";
    public static final String WANInfoApi = "QUERY_WAN_INFO";
    public static final String WANNumApi = "QUERY_WAN_NUM";
    public static final String WLANInfoApi = "GET_WLAN_INTF_INFO";
    public static final String broadBandBusinessConnectStatus = "PPPOE_DIAG_REQ_BY_WAN";
    public static final String broadBandBusinessGetAccount = "GET_PPPOE_ACCOUNT";
    public static final String broadBandBusinessSetAccount = "SET_PPPOE_ACCOUNT";
    public static final String challengeCodeApi = "BIND_SEARCH";
    public static final String cloudStatusApi = "GET_CLOUD_STATUS";
    public static final String commandRebootApi = "HG_COMMAND_REBOOT";
    public static final String cpuInfoApi = "QUERY_CPU_INFO";
    public static final String deviceInfoApi = "GET_HG_SYSTEM_INFO";
    public static final String dhcpManage = "GET_LAN_PARAMETER";
    public static final String dialStateInfoApi = "PPPOE_DIAG_REQ_BY_WAN";
    public static final String flowHistoryApi = "GET_PON_HISTORY_TRAFFIC";
    public static final String getAttachDeviceRightApi = "GET_ATTACH_DEVICE_RIGHT";
    public static final String getServiceApi = "GET_SERVICE";
    public static final String getSetHgWifiTimerApi = "SET_HG_WIFI_TIMER";
    public static final String getSleepM = "GET_SLEEP_MODE";
    public static final String getUpgradeVersionApi = "GET_UPGRADE_VERSION";
    public static final String getWifiAdv = "GET_WIFI_ADV";
    public static final String httpSpeedTestOverTestServerApi = "HTTP_SPEED_TEST_OVER_TESTSERVER";
    public static final String informUpgradeApi = "INFORM_UPGRADE";
    public static final boolean keepAlive = false;
    public static final int linkTimeOut = 10000;
    public static final String localRecoveryApi = "HG_LOCAL_RECOVERY";
    public static final String loginApi = "CHECK_PASSWD_PARAM";
    public static final String mem_InfoApi = "QUERY_MEM_INFO";
    public static final String ponStatusApi = "GET_PON_STATUS";
    public static final String poninformReqApi = "GET_PONINFORM_REQ";
    public static final int port = 17998;
    public static final String querySpeedtestStatOverTestserverApi = "QUERY_SPEEDTEST_STAT_OVER_TESTSERVER";
    public static final int readTimeOut = 10000;
    public static final String recoverSetApi = "HG_LOCAL_RECOVERY";
    public static final String registerGetstatApi = "REGISTER_GETSTAT";
    public static final String saveDHCPManage = "SET_LAN_PARAMETER";
    public static final String setAttachDeviceRightApi = "SET_ATTACH_DEVICE_RIGHT";
    public static final String setAttachSpeedupApi = "SET_ATTACH_SPEEDUP";
    public static final String setAttchNameApi = "SET_ATTCH_NAME";
    public static final String setConnectStatus = "SET_PPPOE_ACTION";
    public static final String setLedStatusApi = "SET_LED_STATUS";
    public static final String setSleepM = "SET_SLEEP_MODE";
    public static final String setWifiAdv = "SET_WIFI_ADV";
    public static final String setWifiHideApi = "SET_WIFI_HIDE";
    public static final String setWifiInfo = "SET_WIFI_INFO";
    public static final String setWifiOptimizeApi = "SET_WIFI_OPTIMIZE";
    public static final String speedStartApi = "HTTP_SPEED_TEST_OVER_TESTSERVER";
    public static final String speedStatusApi = "QUERY_SPEEDTEST_STAT_OVER_TESTSERVER";
    public static final String staTrafficApi = "GET_STA_TRAFFIC";
    public static final String terminalCheckResult = "CHECK_HARDWARE_STATUS";
    public static final String terminalRestartApi = "HG_COMMAND_REBOOT";
    public static final String terminalStartCheck = "CHECK_HARDWARE_START";
    public static final String trafficMonitoring = "GET_PON_TRAFFIC";
    public static final String visitorNetworkBandwidth = "GET_WIFI_ADV";
    public static final String visitorNetworkSetBandwidth = "SET_WIFI_ADV";
    public static final String voiceBusinessInfoApi = "GET_VOICE_CONFGI_INFO";
    public static final String voiceRegistStateApi = "GET_VOIP_REG_STATUS";
    public static final String wifiHideApi = "GET_WIFI_HIDE";
    public static final String wifiInfoApi = "GET_WIFI_INFO";
}
