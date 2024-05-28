package com.mob.commons.p229a;

import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C5741aa;
import com.mob.commons.C5747b;
import com.mob.commons.C5857m;
import com.mob.tools.C6122c;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.mob.commons.a.k */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5728k extends AbstractRunnableC5704c {

    /* renamed from: c */
    private String f14113c;

    /* renamed from: d */
    private long f14114d;

    /* renamed from: e */
    private ArrayList<HashMap<String, String>> f14115e;

    public C5728k() {
        super(C5857m.m12226a("002hb"), 0L, C5857m.m12226a("004hb;dfbh"), 300L);
        this.f14113c = null;
        this.f14114d = 0L;
        this.f14115e = null;
        m12757c();
        try {
            File dataCacheFile = ResHelper.getDataCacheFile(MobSDK.getContext(), C5857m.f14431c, true);
            if (!dataCacheFile.getParentFile().exists()) {
                dataCacheFile.getParentFile().mkdirs();
            }
            if (!dataCacheFile.exists()) {
                dataCacheFile.createNewFile();
            }
            this.f14113c = dataCacheFile.getAbsolutePath();
            this.f14114d = C5741aa.m12650a().m12634b(C5741aa.f14135b, -1L);
        } catch (Throwable unused) {
        }
    }

    @Override // com.mob.commons.p229a.AbstractRunnableC5704c
    /* renamed from: a */
    protected void mo12656a() {
        C6152DH.requester(MobSDK.getContext()).getIAForce(false, false).request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.k.1
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) throws Throwable {
                ArrayList m12690a = C5728k.this.m12690a(dHResponse.getIAForce(new int[0]));
                if (!TextUtils.isEmpty(C5728k.this.f14113c)) {
                    C5728k c5728k = C5728k.this;
                    c5728k.m12688a(m12690a, c5728k.f14113c, C6152DH.SyncMtd.getModel());
                }
                if (System.currentTimeMillis() - C5728k.this.f14114d < ((Long) C5728k.this.m12764a(C5857m.m12226a("005hb]cc@bh"), (String) 3600L)).longValue() * 1000 || !C5728k.this.m12684b((ArrayList<HashMap<String, String>>) m12690a)) {
                    return;
                }
                C5728k.this.f14114d = C5741aa.m12650a().m12634b(C5741aa.f14135b, -1L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public ArrayList<HashMap<String, String>> m12690a(ArrayList<HashMap<String, String>> arrayList) throws Throwable {
        ArrayList<HashMap<String, String>> m12693a = !TextUtils.isEmpty(this.f14113c) ? m12693a(this.f14113c, C6152DH.SyncMtd.getModel()) : null;
        if (m12693a == null) {
            m12693a = new ArrayList<>();
        }
        if (m12693a.isEmpty()) {
            C5741aa.m12650a().m12643a("key_rcdat", System.currentTimeMillis());
        }
        ArrayList<HashMap<String, String>> arrayList2 = this.f14115e;
        if (arrayList2 == null || arrayList2.isEmpty() || C5747b.f14167b) {
            C5747b.f14167b = false;
            this.f14115e = arrayList;
        }
        ArrayList<HashMap<String, String>> arrayList3 = this.f14115e;
        if (arrayList3 != null) {
            for (int i = 0; i < arrayList3.size(); i++) {
                HashMap<String, String> hashMap = arrayList3.get(i);
                String str = hashMap != null ? hashMap.get(C5857m.m12226a("003h5bjcc")) : null;
                if (!TextUtils.isEmpty(str) && m12694a(str)) {
                    HashMap<String, String> m12683b = m12683b(m12693a, str);
                    m12683b.put(C5857m.m12226a("003h3bjcc"), str);
                    m12683b.put(C5857m.m12226a("004cb=bd^d"), hashMap.get(C5857m.m12226a("004cb=bd^d")));
                    m12683b.put(C5857m.m12226a("007 bb=d%bhdfbgcb0c"), hashMap.get(C5857m.m12226a("007 bb=d%bhdfbgcb0c")));
                    int parseInt = m12683b.get(C5857m.m12226a("008*bhbe7cg?bgbdGdFdf")) == null ? 0 : Integer.parseInt(String.valueOf(m12683b.get(C5857m.m12226a("008*bhbe7cg?bgbdGdFdf"))));
                    m12683b.put(C5857m.m12226a("008ZbhbeKcgObgbd_d-df"), (parseInt + m12750j()) + "");
                    if (!m12689a(m12693a, str)) {
                        m12693a.add(m12683b);
                    }
                }
            }
        }
        return m12693a;
    }

    /* renamed from: a */
    private boolean m12694a(final String str) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        C6152DH.requester(MobSDK.getContext()).getMpfof(true, str, 0).request(new C6152DH.DHResponder() { // from class: com.mob.commons.a.k.2
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                boolean z = false;
                ApplicationInfo m11360a = C6122c.m11360a(dHResponse.getMpfof(new int[0]), str);
                if (m11360a != null) {
                    boolean z2 = (m11360a.flags & 1) == 0 && (m11360a.flags & 128) == 0;
                    boolean z3 = (m11360a.flags & 2097152) == 0;
                    AtomicBoolean atomicBoolean2 = atomicBoolean;
                    if (z2 && z3) {
                        z = true;
                    }
                    atomicBoolean2.set(z);
                }
            }
        });
        return atomicBoolean.get();
    }

    /* renamed from: a */
    private boolean m12689a(ArrayList<HashMap<String, String>> arrayList, String str) {
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().get(C5857m.m12226a("003h]bjcc")))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private HashMap<String, String> m12683b(ArrayList<HashMap<String, String>> arrayList, String str) {
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap<String, String> next = it.next();
            if (str.equals(next.get(C5857m.m12226a("003hYbjcc")))) {
                return next;
            }
        }
        return new HashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public boolean m12684b(ArrayList<HashMap<String, String>> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return false;
        }
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put(C5857m.m12226a("008;bhRda<cbbhba5bg"), Long.valueOf(C5741aa.m12650a().m12634b("key_rcdat", -1L)));
            m12771a(0L, "PRTMT", arrayList, hashMap, false);
        } catch (Throwable unused) {
        }
        C5741aa.m12650a().m12643a(C5741aa.f14135b, System.currentTimeMillis());
        return m12682l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12688a(ArrayList<HashMap<String, String>> arrayList, String str, String str2) {
        ResHelper.writeToFileNoCompress(new File(str), m12692a(str2, arrayList));
    }

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> m12693a(String str, String str2) {
        return m12691a(str2, ResHelper.readFromFileNoCompress(new File(str)));
    }

    /* renamed from: a */
    private static byte[] m12692a(String str, ArrayList<HashMap<String, String>> arrayList) {
        new HashonHelper();
        String fromObject = HashonHelper.fromObject(arrayList);
        try {
            return Data.AES128Encode(str, fromObject);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return fromObject.getBytes();
        }
    }

    /* renamed from: a */
    private static ArrayList<HashMap<String, String>> m12691a(String str, byte[] bArr) {
        try {
            return m12685b(Data.AES128Decode(str, bArr));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return new ArrayList<>();
        }
    }

    /* renamed from: b */
    private static ArrayList<HashMap<String, String>> m12685b(String str) {
        try {
            ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
            if (TextUtils.isEmpty(str)) {
                return arrayList;
            }
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                arrayList.add(HashonHelper.fromJson(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
            }
            return arrayList;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return new ArrayList<>();
        }
    }

    /* renamed from: l */
    private boolean m12682l() {
        try {
            File file = new File(this.f14113c);
            file.delete();
            file.createNewFile();
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return false;
        }
    }
}
