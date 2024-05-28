package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class Network {
    private JSONObject json;
    private String networkType;
    private String operator;
    private String operatorName;

    public Network(Context context) {
        init(context);
    }

    private void init(Context context) {
        try {
            this.networkType = getNetworkState(context);
            this.operator = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            this.operatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            Log.d("BaseInfo", "" + e.getMessage());
        }
    }

    public String getNetworkState(Context context) {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return "NONE";
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null) {
                NetworkInfo.State state2 = networkInfo2.getState();
                networkInfo2.getSubtypeName();
                if (state2 != null) {
                    if (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING) {
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                            case 11:
                                return "2G";
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 12:
                            case 14:
                            case 15:
                                return "3G";
                            case 13:
                                return "4G";
                            default:
                                return "UNKNOWN";
                        }
                    }
                    return "";
                }
                return "";
            }
            return "";
        }
        return "WIFI";
    }

    public JSONObject toJson() {
        if (this.json == null) {
            try {
                this.json = new JSONObject();
                this.json.put("operator", this.operator);
                this.json.put("operatorName", this.operatorName);
                this.json.put("networkType", this.networkType);
            } catch (Exception e) {
                Log.d("BaseInfo", "" + e.getMessage());
            }
        }
        return this.json;
    }
}
