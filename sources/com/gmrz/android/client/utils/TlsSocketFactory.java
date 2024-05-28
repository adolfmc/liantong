package com.gmrz.android.client.utils;

import android.net.SSLCertificateSocketFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TlsSocketFactory extends SSLSocketFactory {

    /* renamed from: a */
    private String[] f10183a;

    /* renamed from: b */
    private final SSLCertificateSocketFactory f10184b = null;

    /* renamed from: c */
    private final String f10185c = TlsSocketFactory.class.getSimpleName();

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return null;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return null;
    }

    public TlsSocketFactory(String[] strArr) {
        this.f10183a = null;
        this.f10183a = strArr;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.f10184b.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.f10184b.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        if (z) {
            socket.close();
        }
        SSLSocket sSLSocket = (SSLSocket) ((SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0)).createSocket(InetAddress.getByName(str), i);
        String[] strArr = {"TLSv1.2", "TLSv1.1"};
        String[] strArr2 = this.f10183a;
        if (strArr2 != null && strArr2.length > 0) {
            String str2 = this.f10185c;
            Logger.m15889i(str2, "SSL protocols' configuration is provided: " + m15880a(strArr2));
            strArr = strArr2;
        } else {
            String str3 = this.f10185c;
            Logger.m15889i(str3, "SSL default list of protocols will be used: " + m15880a(strArr));
        }
        String[] supportedProtocols = sSLSocket.getSupportedProtocols();
        String[] m15879a = m15879a(supportedProtocols, strArr);
        if (m15879a.length == 0) {
            String str4 = this.f10185c;
            Logger.m15889i(str4, "Protocols (" + m15880a(strArr) + ") are listen in mfac_config.json file are not supported.");
            String str5 = this.f10185c;
            Logger.m15889i(str5, "SSL supported protocols will be used: " + m15880a(supportedProtocols));
            sSLSocket.setEnabledProtocols(supportedProtocols);
        } else {
            sSLSocket.setEnabledProtocols(m15879a);
        }
        SSLSession session = sSLSocket.getSession();
        if (!HttpsURLConnection.getDefaultHostnameVerifier().verify(str, session)) {
            String str6 = this.f10185c;
            Logger.m15892e(str6, "Cannot verify hostname: " + str);
            throw new SSLPeerUnverifiedException("Cannot verify hostname: " + str);
        }
        String str7 = this.f10185c;
        Logger.m15889i(str7, "Established " + session.getProtocol() + " connection with " + session.getPeerHost() + " using " + session.getCipherSuite());
        return sSLSocket;
    }

    /* renamed from: a */
    private static String[] m15879a(String[] strArr, String[] strArr2) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr2) {
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                } else if (strArr[i].equalsIgnoreCase(str)) {
                    arrayList.add(str);
                    break;
                } else {
                    i++;
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: a */
    private static String m15880a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append(";");
        }
        return sb.toString();
    }
}
