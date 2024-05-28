package com.baidu.location.p137b;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import com.baidu.location.Jni;
import com.baidu.location.ServiceC2737f;
import com.baidu.location.p138c.C2689b;
import com.baidu.location.p138c.C2696e;
import com.baidu.location.p140e.AbstractC2729f;
import com.baidu.location.p140e.C2721b;
import com.baidu.location.p140e.C2734j;
import com.baidu.location.p140e.C2735k;
import com.networkbench.agent.impl.instrumentation.NBSInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.location.b.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2644h {

    /* renamed from: f */
    public static String f5232f = "0";

    /* renamed from: j */
    private static C2644h f5233j;

    /* renamed from: I */
    private Handler f5242I;

    /* renamed from: k */
    private int f5256k = 1;

    /* renamed from: l */
    private double f5257l = 0.699999988079071d;

    /* renamed from: m */
    private String f5258m = "3G|4G";

    /* renamed from: n */
    private int f5259n = 1;

    /* renamed from: o */
    private int f5260o = 307200;

    /* renamed from: p */
    private int f5261p = 15;

    /* renamed from: q */
    private int f5262q = 1;

    /* renamed from: r */
    private double f5263r = 3.5d;

    /* renamed from: s */
    private double f5264s = 3.0d;

    /* renamed from: t */
    private double f5265t = 0.5d;

    /* renamed from: u */
    private int f5266u = 300;

    /* renamed from: v */
    private int f5267v = 60;

    /* renamed from: w */
    private int f5268w = 0;

    /* renamed from: x */
    private int f5269x = 60;

    /* renamed from: y */
    private int f5270y = 0;

    /* renamed from: z */
    private long f5271z = 0;

    /* renamed from: A */
    private C2646b f5234A = null;

    /* renamed from: B */
    private boolean f5235B = false;

    /* renamed from: C */
    private boolean f5236C = false;

    /* renamed from: D */
    private int f5237D = 0;

    /* renamed from: E */
    private float f5238E = 0.0f;

    /* renamed from: F */
    private float f5239F = 0.0f;

    /* renamed from: G */
    private long f5240G = 0;

    /* renamed from: H */
    private int f5241H = 500;

    /* renamed from: a */
    long f5248a = 0;

    /* renamed from: b */
    Location f5249b = null;

    /* renamed from: c */
    Location f5250c = null;

    /* renamed from: d */
    StringBuilder f5251d = null;

    /* renamed from: e */
    long f5252e = 0;

    /* renamed from: J */
    private byte[] f5243J = new byte[4];

    /* renamed from: K */
    private byte[] f5244K = null;

    /* renamed from: L */
    private int f5245L = 0;

    /* renamed from: M */
    private List<Byte> f5246M = null;

    /* renamed from: N */
    private boolean f5247N = false;

    /* renamed from: g */
    int f5253g = 0;

    /* renamed from: h */
    double f5254h = 116.22345545d;

    /* renamed from: i */
    double f5255i = 40.245667323d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.location.b.h$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2645a implements HostnameVerifier {

        /* renamed from: a */
        private URL f5272a;

        public C2645a(URL url) {
            this.f5272a = url;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return this.f5272a.getHost().equals(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.baidu.location.b.h$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2646b extends AbstractC2729f {

        /* renamed from: a */
        String f5273a = null;

        public C2646b() {
            this.f5727j = new HashMap();
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19077a() {
            this.f5724g = "https://loc.map.baidu.com/cc.php";
            String encode = Jni.encode(this.f5273a);
            this.f5273a = null;
            this.f5727j.put("q", encode);
        }

        /* renamed from: a */
        public void m19444a(String str) {
            this.f5273a = str;
            m19073b("https://loc.map.baidu.com/cc.php");
        }

        @Override // com.baidu.location.p140e.AbstractC2729f
        /* renamed from: a */
        public void mo19074a(boolean z) {
            if (z && this.f5726i != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.f5726i);
                    jSONObject.put("prod", C2721b.f5690e);
                    jSONObject.put("uptime", System.currentTimeMillis());
                    C2644h.this.m19451e(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                } catch (Exception unused) {
                }
            }
            if (this.f5727j != null) {
                this.f5727j.clear();
            }
        }
    }

    private C2644h() {
        this.f5242I = null;
        this.f5242I = new Handler();
    }

    /* renamed from: a */
    public static C2644h m19473a() {
        if (f5233j == null) {
            f5233j = new C2644h();
        }
        return f5233j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m19467a(File file, String str) {
        String uuid = UUID.randomUUID().toString();
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(C2735k.m19037k());
            URL url = new URL(str);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) NBSInstrumentation.openConnection(url.openConnection());
            httpsURLConnection.setReadTimeout(10000);
            httpsURLConnection.setConnectTimeout(10000);
            httpsURLConnection.setDoInput(true);
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setUseCaches(false);
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setRequestProperty("Charset", "utf-8");
            httpsURLConnection.setHostnameVerifier(new C2645a(url));
            httpsURLConnection.setRequestProperty("connection", "close");
            httpsURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + uuid);
            if (file == null || !file.exists()) {
                return "0";
            }
            OutputStream outputStream = httpsURLConnection.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("--");
            stringBuffer.append(uuid);
            stringBuffer.append("\r\n");
            stringBuffer.append("Content-Disposition: form-data; name=\"location_dat\"; filename=\"" + file.getName() + "\"\r\n");
            StringBuilder sb = new StringBuilder();
            sb.append("Content-Type: application/octet-stream; charset=utf-8");
            sb.append("\r\n");
            stringBuffer.append(sb.toString());
            stringBuffer.append("\r\n");
            dataOutputStream.write(stringBuffer.toString().getBytes());
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                dataOutputStream.write(bArr, 0, read);
            }
            fileInputStream.close();
            dataOutputStream.write("\r\n".getBytes());
            dataOutputStream.write(("--" + uuid + "--\r\n").getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();
            int responseCode = httpsURLConnection.getResponseCode();
            outputStream.close();
            httpsURLConnection.disconnect();
            this.f5270y += 400;
            m19459c(this.f5270y);
            return responseCode == 200 ? "1" : "0";
        } catch (MalformedURLException | IOException unused) {
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /* renamed from: a */
    private boolean m19465a(String str, Context context) {
        return true;
    }

    /* renamed from: a */
    private byte[] m19472a(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & (-16777216)) >> 24)};
    }

    /* renamed from: a */
    private byte[] m19466a(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new SecureRandom().nextInt(255);
        byte nextInt2 = (byte) new SecureRandom().nextInt(255);
        byte[] bArr = new byte[bytes.length + 2];
        int length = bytes.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2++;
        }
        bArr[i2] = nextInt;
        bArr[i2 + 1] = nextInt2;
        return bArr;
    }

    /* renamed from: b */
    private String m19461b(String str) {
        Calendar calendar = Calendar.getInstance();
        return String.format(str, Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5)));
    }

    /* renamed from: b */
    private void m19463b(int i) {
        byte[] m19472a = m19472a(i);
        for (int i2 = 0; i2 < 4; i2++) {
            this.f5246M.add(Byte.valueOf(m19472a[i2]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19462b(Location location) {
        m19458c(location);
        m19448h();
    }

    /* renamed from: c */
    private void m19460c() {
        if (this.f5247N) {
            return;
        }
        this.f5247N = true;
        m19454d(C2721b.f5690e);
        m19446j();
        m19456d();
    }

    /* renamed from: c */
    private void m19459c(int i) {
        if (i == 0) {
            return;
        }
        try {
            File file = new File(C2734j.f5740a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(C2734j.f5740a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                randomAccessFile.writeInt(0);
                randomAccessFile.seek(8L);
                byte[] bytes = "1980_01_01:0".getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes);
                randomAccessFile.seek(200L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.seek(800L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(8L);
            byte[] bytes2 = (m19461b("%d_%02d_%02d") + ":" + i).getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    /* renamed from: c */
    private void m19458c(Location location) {
        if (System.currentTimeMillis() - this.f5248a < this.f5241H || location == null) {
            return;
        }
        if (location != null && location.hasSpeed() && location.getSpeed() > this.f5238E) {
            this.f5238E = location.getSpeed();
        }
        try {
            if (this.f5246M == null) {
                this.f5246M = new ArrayList();
                m19447i();
                m19455d(location);
            } else {
                m19452e(location);
            }
        } catch (Exception unused) {
        }
        this.f5245L++;
    }

    /* renamed from: c */
    private void m19457c(String str) {
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("on")) {
                    this.f5256k = jSONObject.getInt("on");
                }
                if (jSONObject.has("bash")) {
                    this.f5257l = jSONObject.getDouble("bash");
                }
                if (jSONObject.has("net")) {
                    this.f5258m = jSONObject.getString("net");
                }
                if (jSONObject.has("tcon")) {
                    this.f5259n = jSONObject.getInt("tcon");
                }
                if (jSONObject.has("tcsh")) {
                    this.f5260o = jSONObject.getInt("tcsh");
                }
                if (jSONObject.has("per")) {
                    this.f5261p = jSONObject.getInt("per");
                }
                if (jSONObject.has("chdron")) {
                    this.f5262q = jSONObject.getInt("chdron");
                }
                if (jSONObject.has("spsh")) {
                    this.f5263r = jSONObject.getDouble("spsh");
                }
                if (jSONObject.has("acsh")) {
                    this.f5264s = jSONObject.getDouble("acsh");
                }
                if (jSONObject.has("stspsh")) {
                    this.f5265t = jSONObject.getDouble("stspsh");
                }
                if (jSONObject.has("drstsh")) {
                    this.f5266u = jSONObject.getInt("drstsh");
                }
                if (jSONObject.has("stper")) {
                    this.f5267v = jSONObject.getInt("stper");
                }
                if (jSONObject.has("nondron")) {
                    this.f5268w = jSONObject.getInt("nondron");
                }
                if (jSONObject.has("nondrper")) {
                    this.f5269x = jSONObject.getInt("nondrper");
                }
                if (jSONObject.has("uptime")) {
                    this.f5271z = jSONObject.getLong("uptime");
                }
                m19445k();
            } catch (JSONException unused) {
            }
        }
    }

    /* renamed from: d */
    private void m19456d() {
        String[] split = "9.3.3.3".split("\\.");
        int length = split.length;
        byte[] bArr = this.f5243J;
        bArr[0] = 0;
        bArr[1] = 0;
        bArr[2] = 0;
        bArr[3] = 0;
        if (length >= 4) {
            length = 4;
        }
        for (int i = 0; i < length; i++) {
            try {
                this.f5243J[i] = (byte) (Integer.valueOf(split[i]).intValue() & 255);
            } catch (Exception unused) {
            }
        }
        this.f5244K = m19466a(C2721b.f5690e + ":" + C2721b.m19096a().f5699c);
    }

    /* renamed from: d */
    private void m19455d(Location location) {
        this.f5252e = System.currentTimeMillis();
        m19463b((int) (location.getTime() / 1000));
        m19463b((int) (location.getLongitude() * 1000000.0d));
        m19463b((int) (location.getLatitude() * 1000000.0d));
        int i = !location.hasBearing();
        int i2 = !location.hasSpeed();
        this.f5246M.add(Byte.valueOf(i > 0 ? (byte) 32 : (byte) (((byte) (((int) (location.getBearing() / 15.0f)) & 255)) & (-33))));
        this.f5246M.add(Byte.valueOf(i2 > 0 ? Byte.MIN_VALUE : (byte) (((byte) (((int) ((location.getSpeed() * 3.6d) / 4.0d)) & 255)) & Byte.MAX_VALUE)));
        this.f5249b = location;
    }

    /* renamed from: d */
    private void m19454d(String str) {
        try {
            File file = new File(C2734j.f5740a + "/grtcf.dat");
            if (file.exists()) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                int readInt = randomAccessFile.readInt();
                randomAccessFile.seek(8L);
                int readInt2 = randomAccessFile.readInt();
                int i = 1;
                if (readInt2 < 4096) {
                    byte[] bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str2 = new String(bArr);
                    if (str2.contains(m19461b("%d_%02d_%02d")) && str2.contains(":")) {
                        try {
                            String[] split = str2.split(":");
                            if (split.length > 1) {
                                this.f5270y = Integer.valueOf(split[1]).intValue();
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
                while (true) {
                    if (i > readInt) {
                        break;
                    }
                    randomAccessFile.seek(i * 2048);
                    int readInt3 = randomAccessFile.readInt();
                    if (readInt3 <= 4096) {
                        byte[] bArr2 = new byte[readInt3];
                        randomAccessFile.read(bArr2, 0, readInt3);
                        String str3 = new String(bArr2);
                        if (str != null && str3.contains(str)) {
                            m19457c(str3);
                            break;
                        }
                    }
                    i++;
                }
                randomAccessFile.close();
            }
        } catch (Exception unused2) {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0101, code lost:
        if (r8 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0117, code lost:
        if (r8 > 0) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0119, code lost:
        r2 = (byte) (r2 | Byte.MIN_VALUE);
     */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m19452e(android.location.Location r22) {
        /*
            Method dump skipped, instructions count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.p137b.C2644h.m19452e(android.location.Location):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m19451e(String str) {
        try {
            File file = new File(C2734j.f5740a + "/grtcf.dat");
            if (!file.exists()) {
                File file2 = new File(C2734j.f5740a);
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                if (!file.createNewFile()) {
                    return;
                }
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(2L);
                randomAccessFile.writeInt(0);
                randomAccessFile.seek(8L);
                byte[] bytes = "1980_01_01:0".getBytes();
                randomAccessFile.writeInt(bytes.length);
                randomAccessFile.write(bytes);
                randomAccessFile.seek(200L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.seek(800L);
                randomAccessFile.writeBoolean(false);
                randomAccessFile.close();
            }
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
            randomAccessFile2.seek(2L);
            int readInt = randomAccessFile2.readInt();
            int i = 1;
            while (i <= readInt) {
                randomAccessFile2.seek(i * 2048);
                int readInt2 = randomAccessFile2.readInt();
                byte[] bArr = new byte[readInt2];
                randomAccessFile2.read(bArr, 0, readInt2);
                if (new String(bArr).contains(C2721b.f5690e)) {
                    break;
                }
                i++;
            }
            if (i >= readInt) {
                randomAccessFile2.seek(2L);
                randomAccessFile2.writeInt(i);
            }
            randomAccessFile2.seek(i * 2048);
            byte[] bytes2 = str.getBytes();
            randomAccessFile2.writeInt(bytes2.length);
            randomAccessFile2.write(bytes2);
            randomAccessFile2.close();
        } catch (Exception unused) {
        }
    }

    /* renamed from: e */
    private boolean m19453e() {
        RandomAccessFile randomAccessFile;
        FileChannel fileChannel = null;
        FileLock fileLock = null;
        fileChannel = null;
        RandomAccessFile randomAccessFile2 = null;
        boolean z = false;
        try {
            try {
                File file = new File(C2735k.m19043f() + File.separator + "gflk.dat");
                if (!file.exists()) {
                    file.createNewFile();
                }
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
            }
            try {
                FileChannel channel = randomAccessFile.getChannel();
                try {
                    fileLock = channel.tryLock();
                } catch (Exception unused2) {
                    z = true;
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = channel;
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (Exception unused3) {
                            throw th;
                        }
                    }
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    throw th;
                }
                if (fileLock != null) {
                    fileLock.release();
                }
                if (channel != null) {
                    channel.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Exception unused4) {
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception unused5) {
        }
        return z;
    }

    /* renamed from: f */
    private boolean m19450f() {
        if (this.f5235B) {
            if (!this.f5236C) {
                if (this.f5238E < this.f5265t) {
                    this.f5236C = true;
                    this.f5237D = 0;
                    this.f5237D += this.f5261p;
                    return true;
                }
                return true;
            } else if (this.f5238E >= this.f5265t) {
                this.f5237D = 0;
                this.f5236C = false;
                return true;
            } else {
                this.f5237D += this.f5261p;
                if (this.f5237D <= this.f5266u || System.currentTimeMillis() - this.f5240G > this.f5267v * 1000) {
                    return true;
                }
            }
        } else if (this.f5238E >= this.f5263r || this.f5239F >= this.f5264s) {
            this.f5235B = true;
            return true;
        } else if (this.f5268w == 1 && System.currentTimeMillis() - this.f5240G > this.f5269x * 1000) {
            return true;
        }
        return false;
    }

    /* renamed from: g */
    private void m19449g() {
        this.f5246M = null;
        this.f5252e = 0L;
        this.f5245L = 0;
        this.f5249b = null;
        this.f5250c = null;
        this.f5238E = 0.0f;
        this.f5239F = 0.0f;
    }

    /* renamed from: h */
    private void m19448h() {
        if (this.f5252e == 0 || System.currentTimeMillis() - this.f5252e < this.f5261p * 1000) {
            return;
        }
        if (ServiceC2737f.getServiceContext().getSharedPreferences("loc_navi_mode", 4).getBoolean("is_navi_on", false)) {
            m19449g();
        } else if (this.f5259n == 1 && !m19450f()) {
            m19449g();
        } else {
            if (C2721b.f5690e.equals("com.ubercab.driver")) {
                if (m19453e()) {
                    m19449g();
                    return;
                }
            } else if (!m19465a(C2721b.f5690e, ServiceC2737f.getServiceContext())) {
                m19449g();
                return;
            }
            List<Byte> list = this.f5246M;
            if (list != null) {
                try {
                    int size = list.size();
                    this.f5246M.set(0, Byte.valueOf((byte) (size & 255)));
                    this.f5246M.set(1, Byte.valueOf((byte) ((65280 & size) >> 8)));
                    this.f5246M.set(3, Byte.valueOf((byte) (this.f5245L & 255)));
                    byte[] bArr = new byte[size];
                    for (int i = 0; i < size; i++) {
                        bArr[i] = this.f5246M.get(i).byteValue();
                    }
                    File file = new File(C2735k.m19040h(), "baidu/tempdata");
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    if (file.exists()) {
                        File file2 = new File(file, "intime.dat");
                        if (file2.exists()) {
                            file2.delete();
                        }
                        try {
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                            bufferedOutputStream.write(bArr);
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            new C2648j(this).start();
                        } catch (Exception unused) {
                        }
                    }
                    m19449g();
                    this.f5240G = System.currentTimeMillis();
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* renamed from: i */
    private void m19447i() {
        List<Byte> list;
        byte b;
        this.f5246M.add((byte) 0);
        this.f5246M.add((byte) 0);
        if (f5232f.equals("0")) {
            list = this.f5246M;
            b = -82;
        } else {
            list = this.f5246M;
            b = -66;
        }
        list.add(Byte.valueOf(b));
        this.f5246M.add((byte) 0);
        this.f5246M.add(Byte.valueOf(this.f5243J[0]));
        this.f5246M.add(Byte.valueOf(this.f5243J[1]));
        this.f5246M.add(Byte.valueOf(this.f5243J[2]));
        this.f5246M.add(Byte.valueOf(this.f5243J[3]));
        int length = this.f5244K.length;
        this.f5246M.add(Byte.valueOf((byte) ((length + 1) & 255)));
        for (int i = 0; i < length; i++) {
            this.f5246M.add(Byte.valueOf(this.f5244K[i]));
        }
    }

    /* renamed from: j */
    private void m19446j() {
        if (System.currentTimeMillis() - this.f5271z > 86400000) {
            if (this.f5234A == null) {
                this.f5234A = new C2646b();
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(C2721b.m19096a().m19093a(false));
            stringBuffer.append(C2628b.m19560a().m19548c());
            this.f5234A.m19444a(stringBuffer.toString());
        }
        m19445k();
    }

    /* renamed from: k */
    private void m19445k() {
    }

    /* renamed from: a */
    public void m19471a(Location location) {
        if (!this.f5247N) {
            m19460c();
        }
        if (this.f5256k == 1 && this.f5258m.contains(C2696e.m19229a(C2689b.m19259a().m19238e()))) {
            if (this.f5259n != 1 || this.f5270y <= this.f5260o) {
                this.f5242I.post(new RunnableC2647i(this, location));
            }
        }
    }

    /* renamed from: b */
    public void m19464b() {
        if (this.f5247N) {
            this.f5247N = false;
            m19449g();
        }
    }
}
