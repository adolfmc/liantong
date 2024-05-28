package com.networkbench.agent.impl.p262i;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.InitUrlConnection;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.i.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6468e {

    /* renamed from: a */
    public static final int f16356a = 1000;

    /* renamed from: b */
    public static final int f16357b = 1001;

    /* renamed from: c */
    public static final int f16358c = 1002;

    /* renamed from: d */
    private static String f16359d = null;

    /* renamed from: e */
    private static final int f16360e = 40026;

    /* renamed from: f */
    private static volatile Socket f16361f;

    /* renamed from: d */
    private static final Socket m9901d() throws Exception {
        if (f16361f == null || f16361f.isClosed()) {
            synchronized (C6468e.class) {
                if (f16361f == null || f16361f.isClosed()) {
                    f16361f = null;
                    f16361f = new Socket(f16359d, 40026);
                }
            }
        }
        return f16361f;
    }

    /* renamed from: a */
    public static void m9914a() throws IOException {
        if (f16361f != null && f16361f.isConnected()) {
            f16361f.close();
        }
        f16361f = null;
    }

    /* renamed from: a */
    public static void m9913a(String str) {
        f16359d = str;
    }

    /* renamed from: b */
    public static final void m9907b() throws Exception {
        Socket m9901d;
        if (TextUtils.isEmpty(f16359d) || (m9901d = m9901d()) == null) {
            return;
        }
        m9901d.setSoTimeout(20000);
    }

    /* renamed from: a */
    public static void m9912a(byte[] bArr) throws Exception {
        if (bArr != null) {
            m9911a(bArr, 0, bArr.length);
        }
    }

    /* renamed from: a */
    private static void m9911a(byte[] bArr, int i, int i2) throws Exception {
        if (bArr == null || f16361f == null) {
            return;
        }
        try {
            f16361f.sendUrgentData(255);
        } catch (Exception unused) {
            f16361f.close();
            m9907b();
        }
        if (i2 <= 0 || i < 0 || i >= bArr.length) {
            throw new IndexOutOfBoundsException("my SocketProcess sendBytes Out of bounds: " + i);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(f16361f.getOutputStream());
        dataOutputStream.write(bArr, i, i2);
        dataOutputStream.flush();
    }

    /* renamed from: c */
    public static byte[] m9904c() throws Exception {
        if (f16361f == null) {
            return C6653u.m8755a(1002, 2);
        }
        if (f16361f.isClosed()) {
            C6638h.f17124y.mo10122a("client is closed and init Socket");
            return C6653u.m8755a(1002, 2);
        }
        C6638h.f17124y.mo10122a("begin read input Stream");
        DataInputStream dataInputStream = new DataInputStream(f16361f.getInputStream());
        byte[] bArr = new byte[2];
        int read = dataInputStream.read(bArr);
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("input Stream returnVal:" + read);
        int m8733a = C6653u.m8733a(bArr);
        InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
        interfaceC6393e2.mo10122a("input Stream len:" + m8733a);
        if (m8733a == 0) {
            C6638h.f17124y.mo10116d("input Stream len is 0");
            return C6653u.m8755a(1002, 2);
        }
        byte[] bArr2 = new byte[m8733a];
        dataInputStream.read(bArr2);
        C6464a m9929a = C6464a.m9929a(bArr2[0]);
        byte[] bArr3 = {bArr2[1], bArr2[2]};
        int length = (bArr2.length - 2) - 1;
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr2, 3, bArr4, 0, length);
        if (m9929a.m9927c() == 1) {
            bArr4 = C6466c.m9918b(bArr4);
            if (bArr4.length == 2 && C6653u.m8733a(bArr4) == 1001) {
                return bArr4;
            }
        }
        if (m9929a.m9928b() == 1) {
            bArr4 = C6466c.m9916d(bArr4);
        }
        byte[] bArr5 = new byte[bArr4.length + 2];
        bArr5[0] = bArr3[0];
        bArr5[1] = bArr3[1];
        System.arraycopy(bArr4, 0, bArr5, 2, bArr4.length);
        return bArr5;
    }

    /* renamed from: e */
    private static byte[] m9898e(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return bArr;
        }
        try {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("process byte arr--before compress!length is:" + bArr.length);
            byte[] m9917c = C6466c.m9917c(bArr);
            InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
            interfaceC6393e2.mo10122a("process byte arr--after compress! length is:" + m9917c.length);
            return C6466c.m9920a(m9917c);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
            interfaceC6393e3.mo10115e("encrypt array data error!" + e.toString());
            return bArr;
        }
    }

    /* renamed from: b */
    private static byte[] m9906b(String str) {
        boolean isEmpty = TextUtils.isEmpty(str);
        byte[] bytes = isEmpty ? new byte[0] : str.getBytes();
        if (isEmpty) {
            return bytes;
        }
        try {
            return C6466c.m9920a(bytes);
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10115e("encrypt array params error!" + e.toString());
            return bytes;
        }
    }

    /* renamed from: c */
    private static byte[] m9903c(String str) {
        byte[] m8702f = C6653u.m8702f(str);
        try {
            m8702f = C6466c.m9920a(m8702f);
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("licenseBytes:" + C6653u.m8723b(C6466c.m9918b(m8702f)) + ", len:" + m8702f.length);
            return m8702f;
        } catch (Exception unused) {
            return m8702f;
        }
    }

    /* renamed from: a */
    public static byte[] m9909a(byte[] bArr, int i, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || bArr == null) {
            C6638h.f17124y.mo10122a("processByteArr param is error !");
            return null;
        }
        InterfaceC6393e interfaceC6393e = C6638h.f17124y;
        interfaceC6393e.mo10122a("methodtype:" + i + ", params:" + str + ", licenseKey:" + str2 + ", data_ver:" + str3);
        byte[] m9898e = m9898e(bArr);
        byte[] m9906b = m9906b(str);
        byte[] m9903c = m9903c(str2);
        byte[] m8702f = TextUtils.isEmpty(C6466c.m9922a()) ? new byte[8] : C6653u.m8702f(C6466c.m9922a());
        InterfaceC6393e interfaceC6393e2 = C6638h.f17124y;
        interfaceC6393e2.mo10122a("accessKey:" + C6466c.m9922a());
        byte[] m8755a = C6653u.m8755a(i, 1);
        InterfaceC6393e interfaceC6393e3 = C6638h.f17124y;
        interfaceC6393e3.mo10122a("method byte:" + ((int) m8755a[0]) + ", methodtype:" + i);
        byte[] m9926a = new C6465b((byte) 1, (byte) 1, (byte) 0, m9898e.length, m9906b.length, m9903c.length).m9926a();
        byte[] m8743a = C6653u.m8743a(str3, 4);
        InterfaceC6393e interfaceC6393e4 = C6638h.f17124y;
        interfaceC6393e4.mo10122a("versionBytes:" + ((int) m8743a[0]) + ((int) m8743a[1]) + ((int) m8743a[2]) + ((int) m8743a[3]));
        int length = m8743a.length + m9926a.length + 1 + m8702f.length + m9903c.length + m9906b.length + m9898e.length;
        byte[] m8755a2 = C6653u.m8755a(length, 4);
        InterfaceC6393e interfaceC6393e5 = C6638h.f17124y;
        interfaceC6393e5.mo10122a("lengthBytes:" + ((int) m8755a2[0]) + ((int) m8755a2[1]) + ((int) m8755a2[2]) + ((int) m8755a2[3]));
        byte[] bArr2 = new byte[m8755a2.length + length];
        InterfaceC6393e interfaceC6393e6 = C6638h.f17124y;
        interfaceC6393e6.mo10122a("total:" + length + ", len:" + m8755a2.length);
        m9908a(m8755a2, bArr2, 0);
        m9908a(m8743a, bArr2, m8755a2.length);
        m9908a(m9926a, bArr2, m8755a2.length + m8743a.length);
        m9908a(m8755a, bArr2, m8755a2.length + m9926a.length + m8743a.length);
        m9908a(m8702f, bArr2, m8755a2.length + m9926a.length + 1 + m8743a.length);
        m9908a(m9903c, bArr2, m8755a2.length + m9926a.length + 1 + m8702f.length + m8743a.length);
        m9908a(m9906b, bArr2, m8755a2.length + m9926a.length + 1 + m9903c.length + m8702f.length + m8743a.length);
        System.arraycopy(m9898e, 0, bArr2, m8755a2.length + m8743a.length + m9926a.length + 1 + m8702f.length + m9903c.length + m9906b.length, m9898e.length);
        return bArr2;
    }

    /* renamed from: b */
    public static byte[] m9905b(byte[] bArr) {
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        return bArr2;
    }

    /* renamed from: c */
    public static int m9902c(byte[] bArr) {
        if (bArr[0] == 255 && bArr[1] == 255) {
            return -1;
        }
        return C6653u.m8733a(bArr[0], bArr[1]);
    }

    /* renamed from: d */
    public static JSONObject m9899d(byte[] bArr) {
        try {
            return new JSONObject(new String(m9905b(bArr), "UTF-8"));
        } catch (UnsupportedEncodingException | JSONException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m9908a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length > bArr2.length) {
            return;
        }
        int length = bArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            bArr2[i2 + i] = bArr[i2];
        }
    }

    /* renamed from: a */
    public static byte[] m9910a(byte[] bArr, int i, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            str = str2 + str;
        }
        byte[] m9909a = m9909a(bArr, i, m9900d(str), C6638h.m8963w().m9086A(), NBSAgent.getSocketDataVersion());
        try {
            m9907b();
            m9912a(m9909a);
            return m9904c();
        } catch (Exception unused) {
            return C6653u.m8755a(1000, 2);
        }
    }

    /* renamed from: d */
    private static String m9900d(String str) {
        return str + "&protocol=2&X-App-Process=" + InitUrlConnection.isMainProcess();
    }
}
