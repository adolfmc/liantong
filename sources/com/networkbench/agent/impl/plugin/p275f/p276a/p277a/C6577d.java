package com.networkbench.agent.impl.plugin.p275f.p276a.p277a;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6577d {

    /* renamed from: b */
    private static final Random f16854b = new Random();

    /* renamed from: a */
    final InetAddress f16855a;

    public C6577d(InetAddress inetAddress) {
        this.f16855a = inetAddress;
    }

    /* renamed from: a */
    public C6576c[] m9330a(String str) throws IOException {
        int nextInt;
        synchronized (f16854b) {
            nextInt = f16854b.nextInt() & 255;
        }
        byte[] m9329a = m9329a(C6574b.m9339a(str, nextInt));
        if (m9329a == null) {
            throw new C6573a(str, "cant get answer");
        }
        return C6574b.m9338a(m9329a, nextInt, str);
    }

    /* renamed from: a */
    private byte[] m9329a(byte[] bArr) throws IOException {
        DatagramSocket datagramSocket;
        try {
            datagramSocket = new DatagramSocket();
        } catch (Throwable th) {
            th = th;
            datagramSocket = null;
        }
        try {
            DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length, this.f16855a, 53);
            datagramSocket.setSoTimeout(3000);
            datagramSocket.send(datagramPacket);
            DatagramPacket datagramPacket2 = new DatagramPacket(new byte[1500], 1500);
            datagramSocket.receive(datagramPacket2);
            byte[] data = datagramPacket2.getData();
            datagramSocket.close();
            return data;
        } catch (Throwable th2) {
            th = th2;
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            throw th;
        }
    }
}
