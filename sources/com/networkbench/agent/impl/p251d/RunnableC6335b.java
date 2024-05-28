package com.networkbench.agent.impl.p251d;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.background.C6246a;
import com.networkbench.agent.impl.background.InterfaceC6247b;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.harvest.DeviceData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.harvest.p260a.EnumC6455q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6644m;
import com.networkbench.agent.impl.util.C6648q;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.d.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class RunnableC6335b implements InterfaceC6247b, Runnable {

    /* renamed from: d */
    private static final InterfaceC6393e f15950d = C6394f.m10150a();

    /* renamed from: e */
    private static RunnableC6335b f15951e;

    /* renamed from: a */
    public ConcurrentHashMap<String, String[]> f15952a = new ConcurrentHashMap<>();

    /* renamed from: b */
    public ConcurrentHashMap<String, String> f15953b = new ConcurrentHashMap<>();

    /* renamed from: c */
    private ScheduledExecutorService f15954c;

    /* renamed from: f */
    private boolean f15955f;

    /* renamed from: g */
    private JSONObject f15956g;

    /* renamed from: h */
    private long f15957h;

    /* renamed from: i */
    private int f15958i;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.d.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6336a {

        /* renamed from: a */
        public SocketAddress f15959a;

        /* renamed from: b */
        public boolean f15960b = false;
    }

    @Override // com.networkbench.agent.impl.background.InterfaceC6247b
    /* renamed from: a */
    public void mo9174a(C6246a c6246a) {
    }

    private RunnableC6335b() {
    }

    /* renamed from: a */
    public static RunnableC6335b m10355a() {
        if (f15951e == null) {
            f15951e = new RunnableC6335b();
            NBSApplicationStateMonitor.getInstance().addApplicationStateListener(f15951e);
        }
        return f15951e;
    }

    /* renamed from: b */
    public void m10348b() {
        if (m10343f()) {
            return;
        }
        HarvestConfiguration configuration = Harvest.getInstance().getConfiguration();
        if (configuration.getControllerEnable()) {
            this.f15958i = configuration.getControllerInterval();
            if (this.f15958i <= 0) {
                this.f15958i = 60;
            }
            this.f15954c = Executors.newScheduledThreadPool(1);
            this.f15954c.scheduleAtFixedRate(this, 0L, this.f15958i, TimeUnit.SECONDS);
            this.f15955f = true;
        }
    }

    /* renamed from: a */
    public String m10354a(String str) {
        if (!Harvest.getInstance().getConfiguration().getControllerEnable() || str == null || this.f15957h + ((this.f15958i + 1) * 1000) < System.currentTimeMillis() || str == null || !this.f15953b.containsKey(str)) {
            return null;
        }
        return this.f15953b.get(str);
    }

    /* renamed from: b */
    private String m10347b(String str) {
        ConcurrentHashMap<String, String[]> concurrentHashMap;
        String[] strArr;
        if (str != null && this.f15957h + ((this.f15958i + 1) * 1000) >= System.currentTimeMillis() && (concurrentHashMap = this.f15952a) != null && concurrentHashMap.containsKey(str) && (strArr = this.f15952a.get(str)) != null && strArr.length > 0) {
            return strArr[0];
        }
        return null;
    }

    /* renamed from: a */
    public C6336a m10351a(SocketAddress socketAddress) {
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
        String m8958a = C6640i.m8958a(inetSocketAddress);
        if (!C6644m.m8892a(m8958a)) {
            m8958a = C6642k.m8909b(m8958a);
        }
        String m10347b = m10347b(m8958a);
        C6336a c6336a = new C6336a();
        c6336a.f15960b = !TextUtils.isEmpty(m10347b);
        if (c6336a.f15960b) {
            c6336a.f15959a = m10353a(m10347b, inetSocketAddress.getPort());
        } else {
            c6336a.f15959a = socketAddress;
        }
        return c6336a;
    }

    /* renamed from: a */
    public void m10352a(String str, String str2) {
        ConcurrentHashMap<String, String[]> concurrentHashMap;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || (concurrentHashMap = this.f15952a) == null || !concurrentHashMap.contains(str)) {
            return;
        }
        this.f15952a.remove(str);
    }

    @Override // java.lang.Runnable
    public void run() {
        m10345d();
    }

    /* renamed from: d */
    private void m10345d() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        DeviceData deviceData = NBSAgent.getDeviceData();
        try {
            jSONArray.put(deviceData.getCarrier());
            jSONArray.put(deviceData.getConnectType());
            jSONArray.put(deviceData.getNetwrokType());
            jSONArray.put(deviceData.getLatitude());
            jSONArray.put(deviceData.getLongitude());
            jSONObject.put("dev", jSONArray);
            HarvestResponse response = Harvest.getInstance().getHarvestConnection().getResponse(jSONObject.toString(), EnumC6455q.CONTROLLER_DATA);
            if (response.getStatusCode() == 200) {
                JSONObject jSONObject2 = new JSONObject(response.getResponseBody());
                if (jSONObject2.getInt("status") == 200) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("data");
                    this.f15957h = System.currentTimeMillis();
                    if (jSONObject3 != null) {
                        if (this.f15956g == null || !this.f15956g.equals(jSONObject3)) {
                            this.f15953b.clear();
                            this.f15952a.clear();
                            this.f15956g = jSONObject3;
                            m10349a(this.f15956g);
                        }
                    }
                }
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15950d;
            interfaceC6393e.mo10116d("controller request error! " + e.getMessage());
        }
    }

    /* renamed from: a */
    public static String[] m10350a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                String string = jSONArray.getString(i);
                if (!TextUtils.isEmpty(string)) {
                    strArr[i] = string;
                }
            } catch (Exception unused) {
                C6638h.f17124y.mo10115e("parseJsonArray error when get controller!");
            }
        }
        return strArr;
    }

    /* renamed from: a */
    public void m10349a(JSONObject jSONObject) {
        String string;
        if (jSONObject == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("nodes");
            Iterator<String> keys = jSONObject2.keys();
            int i = 0;
            if (jSONObject2 != null && keys != null) {
                int i2 = 0;
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    if (obj != null) {
                        JSONArray jSONArray = jSONObject2.getJSONArray(obj);
                        if (m10350a(jSONArray) != null) {
                            this.f15952a.put(obj, m10350a(jSONArray));
                        }
                    }
                    int i3 = i2 + 1;
                    if (i2 == 100) {
                        break;
                    }
                    i2 = i3;
                }
            }
            JSONObject jSONObject3 = jSONObject.getJSONObject("alias");
            Iterator<String> keys2 = jSONObject3.keys();
            if (jSONObject3 == null || keys2 == null) {
                return;
            }
            while (keys2.hasNext()) {
                String obj2 = keys2.next().toString();
                if (obj2 != null && (string = jSONObject3.getString(obj2)) != null) {
                    this.f15953b.put(obj2, string);
                }
                int i4 = i + 1;
                if (i == 100) {
                    return;
                }
                i = i4;
            }
        } catch (Exception unused) {
            C6638h.f17124y.mo10115e("parseResult error when get controller!");
        }
    }

    /* renamed from: e */
    private String m10344e() {
        if (TextUtils.isEmpty(C6640i.f17186a)) {
            return null;
        }
        return C6640i.f17186a + "/ctl/optimus?version=" + NBSAgent.getVersion() + "&token=" + C6638h.m8963w().m9075L();
    }

    /* renamed from: a */
    private InetSocketAddress m10353a(String str, int i) {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getByName(str);
        } catch (UnknownHostException e) {
            InterfaceC6393e interfaceC6393e = f15950d;
            interfaceC6393e.mo10122a("dispatchControllerDispatch error!" + e.getMessage());
            inetAddress = null;
        }
        if (i == 443 && inetAddress != null) {
            C6648q.f17234d.add(inetAddress.getHostAddress());
        }
        return new InetSocketAddress(inetAddress, i);
    }

    @Override // com.networkbench.agent.impl.background.InterfaceC6247b
    /* renamed from: b */
    public void mo9166b(C6246a c6246a) {
        m10346c();
    }

    /* renamed from: f */
    private boolean m10343f() {
        return this.f15955f;
    }

    /* renamed from: c */
    protected synchronized void m10346c() {
        this.f15955f = false;
        this.f15956g = null;
        if (this.f15954c == null) {
            return;
        }
        try {
            this.f15954c.shutdown();
            if (!this.f15954c.awaitTermination(3L, TimeUnit.SECONDS)) {
                this.f15954c.shutdownNow();
                this.f15954c.awaitTermination(3L, TimeUnit.SECONDS);
            }
        } catch (Exception unused) {
            this.f15954c.shutdownNow();
        }
        this.f15954c = null;
    }
}
