package com.chinaunicon.jtwifilib.jtcommon.util;

import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class IpUtil {
    private static final String TAG = "IpUtil";

    public static String getPublicIp() {
        return "";
    }

    public static String getIpByFormat(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String getIPv6() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    Log.i("TAG", "--" + nextElement.getHostAddress().toString());
                    if (!nextElement.isLoopbackAddress()) {
                        return nextElement.getHostAddress().toString().length() > 13 ? nextElement.getHostAddress().toString().substring(0, 20) : "--:--:--:--";
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static String getIPv4() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    Log.i("TAG", "==" + nextElement.getHostAddress().toString());
                    if (!nextElement.isLoopbackAddress() && !nextElement.isLinkLocalAddress()) {
                        return nextElement.getHostAddress().toString().replace("%9", "");
                    }
                }
            }
            return null;
        } catch (SocketException unused) {
            return null;
        }
    }

    public static boolean resolveHost(String str, int i, int i2) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(str, i), i2);
            return socket.isConnected();
        } catch (IOException unused) {
            return true;
        }
    }

    public static void resolveHost2(String str, int i, int i2) throws IOException {
        new Socket().connect(new InetSocketAddress(str, i), i2);
    }

    public static boolean isIpAdress(String str) {
        try {
            InetAddress.getByName(str);
            return true;
        } catch (UnknownHostException unused) {
            return false;
        }
    }

    public static boolean isIp(String str) {
        return str.split(".").length == 4;
    }
}
