package p001a.p058b.p059a.p060a.p061a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.a.a.a.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0706d {

    /* renamed from: a */
    public String f2131a;

    /* renamed from: b */
    public String f2132b;

    /* renamed from: c */
    public String f2133c;

    /* renamed from: d */
    public JSONObject f2134d;

    public C0706d(Context context) {
        NetworkInfo activeNetworkInfo;
        String str;
        NetworkInfo.State state;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
                if (networkInfo == null || (state = networkInfo.getState()) == null || !(state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
                    NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                    if (networkInfo2 != null) {
                        NetworkInfo.State state2 = networkInfo2.getState();
                        networkInfo2.getSubtypeName();
                        if (state2 != null && (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING)) {
                            switch (activeNetworkInfo.getSubtype()) {
                                case 1:
                                case 2:
                                case 4:
                                case 7:
                                case 11:
                                    str = "2G";
                                    break;
                                case 3:
                                case 5:
                                case 6:
                                case 8:
                                case 9:
                                case 10:
                                case 12:
                                case 14:
                                case 15:
                                    str = "3G";
                                    break;
                                case 13:
                                    str = "4G";
                                    break;
                                default:
                                    str = "UNKNOWN";
                                    break;
                            }
                        }
                    }
                    str = "";
                } else {
                    str = "WIFI";
                }
                this.f2133c = str;
                this.f2131a = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
                this.f2132b = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
            }
            str = "NONE";
            this.f2133c = str;
            this.f2131a = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperator();
            this.f2132b = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        } catch (Exception e) {
            outline.m24438a(e, outline.m24437a(""), "BaseInfo");
        }
    }

    /* renamed from: a */
    public static String m22340a() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress() && !nextElement.isLinkLocalAddress() && (nextElement instanceof Inet4Address)) {
                        return nextElement.getHostAddress().toString();
                    }
                }
            }
            return "";
        } catch (Exception e) {
            Log.e("BaseInfo", e.toString());
            return "";
        }
    }

    /* renamed from: b */
    public JSONObject m22339b() {
        if (this.f2134d == null) {
            try {
                this.f2134d = new JSONObject();
                this.f2134d.put("operator", this.f2131a);
                this.f2134d.put("operatorName", this.f2132b);
                this.f2134d.put("networkType", this.f2133c);
            } catch (Exception e) {
                outline.m24438a(e, outline.m24437a(""), "BaseInfo");
            }
        }
        return this.f2134d;
    }
}
