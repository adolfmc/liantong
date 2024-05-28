package com.baidu.rtc;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import com.webrtc.Logging;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class KcpClient {
    public static final int KCP_STREAM_IOCTL_TIMEOUT = 101;
    public static final int LASTMILE_EVENT_RESULT = 102;
    public static final int RTC_EVENT_GATEWAY_RTT = 103;
    private static final String TAG = "KcpClient";
    private BaiduRtcInterface delegate;

    public native void nativeExit();

    public native void nativeMain(String str, int i, int i2);

    public native void nativeStartLastmile(String str, int i, int i2, int i3, long j);

    public native void nativeStartPingRtt(String str, String str2);

    public native void nativeStopLastmile();

    public native void nativeStopPingRtt();

    static {
        System.loadLibrary("kcpclient");
    }

    public KcpClient(BaiduRtcInterface baiduRtcInterface) {
        this.delegate = baiduRtcInterface;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PLastmileResult extends Marshallable {
        public LastmileOneWayResult downlinkReport;
        public int quality;
        public int rtt;
        public short state;
        public LastmileOneWayResult uplinkReport;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        public static class LastmileOneWayResult {
            public int availableBandwidth;
            public int jitter;
            public int packetLossRate;
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] marshall() {
            return super.marshall();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(int[] iArr) {
            super.pushIntArray(iArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(Integer[] numArr) {
            super.pushIntArray(numArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void unmarshall(ByteBuffer byteBuffer) {
            super.unmarshall(byteBuffer);
        }

        @Override // com.baidu.rtc.Marshallable
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.state = popShort();
            this.uplinkReport = new LastmileOneWayResult();
            this.downlinkReport = new LastmileOneWayResult();
            this.uplinkReport.packetLossRate = popInt();
            this.uplinkReport.jitter = popInt();
            this.uplinkReport.availableBandwidth = popInt();
            this.downlinkReport.packetLossRate = popInt();
            this.downlinkReport.jitter = popInt();
            this.downlinkReport.availableBandwidth = popInt();
            this.rtt = popInt();
            this.quality = popInt();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MediaNetworkInfo extends Marshallable {
        int networkSubtype;
        int networkType;
        String localIp4 = "";
        String gatewayIp4 = "";
        String localIp6 = "";
        String gatewayIp6 = "";

        @Override // com.baidu.rtc.Marshallable
        public void unmarshall(byte[] bArr) {
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(int[] iArr) {
            super.pushIntArray(iArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(Integer[] numArr) {
            super.pushIntArray(numArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void unmarshall(ByteBuffer byteBuffer) {
            super.unmarshall(byteBuffer);
        }

        public void marshall(Marshallable marshallable) {
            marshallable.pushBytes(this.localIp4.getBytes());
            marshallable.pushBytes(this.gatewayIp4.getBytes());
            marshallable.pushBytes(this.localIp6.getBytes());
            marshallable.pushBytes(this.gatewayIp6.getBytes());
            marshallable.pushInt(this.networkType);
            marshallable.pushInt(this.networkSubtype);
        }

        @Override // com.baidu.rtc.Marshallable
        public byte[] marshall() {
            marshall(this);
            return super.marshall();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class PGatewayRtt extends Marshallable {
        public int rtt;
        public int type;

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void clear() {
            super.clear();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ ByteBuffer getBuffer() {
            return super.getBuffer();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void marshall(ByteBuffer byteBuffer) {
            super.marshall(byteBuffer);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popAll() {
            return super.popAll();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ Boolean popBool() {
            return super.popBool();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte popByte() {
            return super.popByte();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes() {
            return super.popBytes();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ byte[] popBytes32() {
            return super.popBytes32();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int popInt() {
            return super.popInt();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ long popInt64() {
            return super.popInt64();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ int[] popIntArray() {
            return super.popIntArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short popShort() {
            return super.popShort();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ short[] popShortArray() {
            return super.popShortArray();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16() {
            return super.popString16();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ String popString16UTF8() {
            return super.popString16UTF8();
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBool(Boolean bool) {
            super.pushBool(bool);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushByte(byte b) {
            super.pushByte(b);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes(byte[] bArr) {
            super.pushBytes(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushBytes32(byte[] bArr) {
            super.pushBytes32(bArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushDouble(double d) {
            super.pushDouble(d);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt(int i) {
            super.pushInt(i);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushInt64(long j) {
            super.pushInt64(j);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(int[] iArr) {
            super.pushIntArray(iArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushIntArray(Integer[] numArr) {
            super.pushIntArray(numArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShort(short s) {
            super.pushShort(s);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushShortArray(short[] sArr) {
            super.pushShortArray(sArr);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushString16(String str) {
            super.pushString16(str);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void pushStringArray(ArrayList arrayList) {
            super.pushStringArray(arrayList);
        }

        @Override // com.baidu.rtc.Marshallable
        public /* bridge */ /* synthetic */ void unmarshall(ByteBuffer byteBuffer) {
            super.unmarshall(byteBuffer);
        }

        @Override // com.baidu.rtc.Marshallable
        public byte[] marshall() {
            return super.marshall();
        }

        @Override // com.baidu.rtc.Marshallable
        public void unmarshall(byte[] bArr) {
            super.unmarshall(bArr);
            this.type = popInt();
            this.rtt = popInt();
        }
    }

    private static boolean checkAccessNetworkState(Context context) {
        return context != null && context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
    }

    private static boolean checkAccessWifiState(Context context) {
        return context != null && context.checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0;
    }

    private static String inetAddressToIpAddress(InetAddress inetAddress) {
        if (inetAddress.isLoopbackAddress() || !(inetAddress instanceof Inet4Address)) {
            return null;
        }
        return ((Inet4Address) inetAddress).getHostAddress();
    }

    public static String getLocalHost() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.getName().startsWith("usb")) {
                    for (InetAddress inetAddress : Collections.list(networkInterface.getInetAddresses())) {
                        String inetAddressToIpAddress = inetAddressToIpAddress(inetAddress);
                        if (inetAddressToIpAddress != null && !inetAddressToIpAddress.isEmpty()) {
                            return inetAddressToIpAddress;
                        }
                    }
                    continue;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static InetAddress intToInetAddress(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    public MediaNetworkInfo getNetworkInfo(Context context) {
        InetAddress intToInetAddress;
        if (context == null) {
            return null;
        }
        MediaNetworkInfo mediaNetworkInfo = new MediaNetworkInfo();
        if (checkAccessNetworkState(context)) {
            String localHost = getLocalHost();
            if (localHost != null) {
                mediaNetworkInfo.localIp4 = localHost;
            }
            NetworkInfo networkInfo = Connectivity.getNetworkInfo(context);
            mediaNetworkInfo.networkType = Connectivity.getNetworkType(networkInfo);
            if (networkInfo != null) {
                mediaNetworkInfo.networkSubtype = networkInfo.getSubtype();
            }
            if (mediaNetworkInfo.networkType == 2 && checkAccessWifiState(context)) {
                WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
                DhcpInfo dhcpInfo = wifiManager.getDhcpInfo();
                if (dhcpInfo != null && (intToInetAddress = intToInetAddress(dhcpInfo.gateway)) != null) {
                    mediaNetworkInfo.gatewayIp4 = intToInetAddress.getHostAddress();
                }
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (connectionInfo != null && Build.VERSION.SDK_INT >= 21) {
                    int frequency = connectionInfo.getFrequency();
                    if (frequency >= 5000) {
                        mediaNetworkInfo.networkSubtype = 101;
                    } else if (frequency >= 2400) {
                        mediaNetworkInfo.networkSubtype = 100;
                    }
                }
                return mediaNetworkInfo;
            }
            return mediaNetworkInfo;
        }
        return mediaNetworkInfo;
    }

    public void onEvent(int i, byte[] bArr) {
        BaiduRtcInterface baiduRtcInterface = this.delegate;
        if (baiduRtcInterface != null) {
            baiduRtcInterface.onEvent(i, bArr);
        }
    }

    public void main(String str, int i, int i2) {
        nativeMain(str, i, i2);
    }

    public void exit() {
        nativeExit();
    }

    public void startLastmile(String str, int i, int i2, int i3, long j) {
        nativeStartLastmile(str, i, i2, i3, j);
    }

    public void stopLastmile() {
        nativeStopLastmile();
    }

    public void startPingRtt(Context context, String str) {
        MediaNetworkInfo networkInfo = getNetworkInfo(context);
        String str2 = "";
        if (networkInfo != null && networkInfo.networkType == 2 && networkInfo.gatewayIp4.length() > 0) {
            str2 = networkInfo.gatewayIp4;
        }
        if (str2.length() > 0 || str.length() > 0) {
            Logging.m5305d("KcpClient", "ping wifiIp:" + str2 + " wanIp:" + str);
            nativeStartPingRtt(str2, str);
        }
    }

    public void stopPingRtt() {
        nativeStopPingRtt();
    }
}
