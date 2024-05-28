package cn.sharesdk.framework;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.p094a.EventManager;
import cn.sharesdk.framework.utils.SSDKHandlerThread;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareSDKCoreThread extends SSDKHandlerThread {

    /* renamed from: b */
    private EnumC1778a f2926b;

    /* renamed from: k */
    private boolean f2935k;

    /* renamed from: j */
    private boolean f2934j = true;

    /* renamed from: c */
    private HashMap<String, HashMap<String, String>> f2927c = new HashMap<>();

    /* renamed from: d */
    private ArrayList<Platform> f2928d = new ArrayList<>();

    /* renamed from: e */
    private HashMap<String, Integer> f2929e = new HashMap<>();

    /* renamed from: f */
    private HashMap<Integer, String> f2930f = new HashMap<>();

    /* renamed from: g */
    private HashMap<Integer, CustomPlatform> f2931g = new HashMap<>();

    /* renamed from: h */
    private HashMap<Integer, HashMap<String, Object>> f2932h = new HashMap<>();

    /* renamed from: i */
    private HashMap<Integer, Service> f2933i = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ShareSDKCoreThread.java */
    /* renamed from: cn.sharesdk.framework.i$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC1778a {
        INITIALIZING,
        READY
    }

    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: b */
    public void mo21689b(Message message) {
    }

    /* renamed from: a */
    public void m21780a(Activity activity) {
        ShareSDKCore.m21797a(activity);
    }

    /* renamed from: a */
    public Activity m21785a() {
        return ShareSDKCore.m21791b();
    }

    /* renamed from: a */
    public void m21767a(boolean z) {
        ShareSDKCore.m21792a(z);
    }

    /* renamed from: b */
    public boolean m21766b() {
        return ShareSDKCore.m21789c();
    }

    /* renamed from: b */
    public void m21761b(boolean z) {
        ShareSDKCore.m21790b(z);
    }

    /* renamed from: c */
    public boolean m21760c() {
        return ShareSDKCore.m21788d();
    }

    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: d */
    public void mo21687d() {
        this.f2926b = EnumC1778a.INITIALIZING;
        SSDKLog.m21746a();
        EventRecorder.prepare();
        m21748j();
        super.mo21687d();
    }

    /* renamed from: j */
    private void m21748j() {
        synchronized (this.f2927c) {
            this.f2927c.clear();
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            InputStream open = MobSDK.getContext().getAssets().open("ShareSDK.xml");
            newPullParser.setInput(open, "utf-8");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    String name = newPullParser.getName();
                    HashMap<String, String> hashMap = new HashMap<>();
                    int attributeCount = newPullParser.getAttributeCount();
                    for (int i = 0; i < attributeCount; i++) {
                        hashMap.put(newPullParser.getAttributeName(i), newPullParser.getAttributeValue(i).trim());
                    }
                    this.f2927c.put(name, hashMap);
                }
            }
            open.close();
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [cn.sharesdk.framework.i$1] */
    @Override // cn.sharesdk.framework.utils.SSDKHandlerThread
    /* renamed from: a */
    public void mo21690a(Message message) {
        synchronized (this.f2933i) {
            synchronized (this.f2928d) {
                try {
                    String checkRecord = EventRecorder.checkRecord("SHARESDK");
                    if (!TextUtils.isEmpty(checkRecord)) {
                        EventManager.m21991a().m21980a((HashMap<String, Object>) null);
                        SSDKLog m21740b = SSDKLog.m21740b();
                        m21740b.m21743a("EventRecorder checkRecord result ==" + checkRecord);
                        m21749i();
                    }
                    EventRecorder.clear();
                    this.f2928d.clear();
                    ArrayList<Platform> m21799a = ShareSDKCore.m21799a();
                    if (m21799a != null) {
                        this.f2928d.addAll(m21799a);
                    }
                    Iterator<Platform> it = this.f2928d.iterator();
                    while (it.hasNext()) {
                        Platform next = it.next();
                        this.f2930f.put(Integer.valueOf(next.getPlatformId()), next.getName());
                        this.f2929e.put(next.getName(), Integer.valueOf(next.getPlatformId()));
                    }
                    ShareSDKCore.m21796a(this.f2969a);
                    this.f2926b = EnumC1778a.READY;
                    new Thread() { // from class: cn.sharesdk.framework.i.1
                        @Override // java.lang.Thread, java.lang.Runnable
                        public void run() {
                            ShareSDKCoreThread.this.m21750h();
                        }
                    }.start();
                    this.f2926b = EnumC1778a.READY;
                    this.f2928d.notify();
                    this.f2933i.notify();
                }
            }
        }
    }

    /* renamed from: a */
    public void m21775a(Class<? extends Service> cls) {
        synchronized (this.f2933i) {
            if (this.f2933i.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            Service newInstance = cls.newInstance();
            this.f2933i.put(Integer.valueOf(cls.hashCode()), newInstance);
            newInstance.onBind();
        }
    }

    /* renamed from: b */
    public void m21764b(Class<? extends Service> cls) {
        synchronized (this.f2933i) {
            int hashCode = cls.hashCode();
            if (this.f2933i.containsKey(Integer.valueOf(hashCode))) {
                this.f2933i.get(Integer.valueOf(hashCode)).onUnbind();
                this.f2933i.remove(Integer.valueOf(hashCode));
            }
        }
    }

    /* renamed from: c */
    public <T extends Service> T m21758c(Class<T> cls) {
        T cast;
        synchronized (this.f2933i) {
            if (this.f2926b == EnumC1778a.INITIALIZING) {
                this.f2933i.wait();
            }
            cast = cls.cast(this.f2933i.get(Integer.valueOf(cls.hashCode())));
        }
        return cast;
    }

    /* renamed from: d */
    public void m21755d(Class<? extends CustomPlatform> cls) {
        synchronized (this.f2931g) {
            if (this.f2931g.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            CustomPlatform newInstance = cls.newInstance();
            this.f2931g.put(Integer.valueOf(cls.hashCode()), newInstance);
            if (newInstance != null && newInstance.m22014b()) {
                this.f2930f.put(Integer.valueOf(newInstance.getPlatformId()), newInstance.getName());
                this.f2929e.put(newInstance.getName(), Integer.valueOf(newInstance.getPlatformId()));
            }
        }
    }

    /* renamed from: e */
    public void m21753e(Class<? extends CustomPlatform> cls) {
        int hashCode = cls.hashCode();
        synchronized (this.f2931g) {
            this.f2931g.remove(Integer.valueOf(hashCode));
        }
    }

    /* renamed from: a */
    public Platform m21774a(String str) {
        Platform[] m21754e;
        if (str == null || (m21754e = m21754e()) == null) {
            return null;
        }
        for (Platform platform : m21754e) {
            if (str.equals(platform.getName())) {
                return platform;
            }
        }
        return null;
    }

    /* renamed from: e */
    public Platform[] m21754e() {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.f2928d) {
            if (this.f2926b == EnumC1778a.INITIALIZING) {
                this.f2928d.wait();
            }
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Platform> it = this.f2928d.iterator();
        while (it.hasNext()) {
            Platform next = it.next();
            if (next != null && next.m22014b()) {
                next.m22015a();
                arrayList.add(next);
            }
        }
        ShareSDKCore.m21794a(arrayList);
        for (Map.Entry<Integer, CustomPlatform> entry : this.f2931g.entrySet()) {
            CustomPlatform value = entry.getValue();
            if (value != null && value.m22014b()) {
                arrayList.add(value);
            }
        }
        if (arrayList.size() <= 0) {
            return null;
        }
        Platform[] platformArr = new Platform[arrayList.size()];
        for (int i = 0; i < platformArr.length; i++) {
            platformArr[i] = (Platform) arrayList.get(i);
        }
        SSDKLog.m21740b().m21735c("sort list use time: %s", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
        return platformArr;
    }

    /* renamed from: a */
    public void m21784a(int i) {
        NetworkHelper.connectionTimeout = i;
    }

    /* renamed from: b */
    public void m21765b(int i) {
        NetworkHelper.readTimout = i;
    }

    /* renamed from: c */
    public void m21756c(boolean z) {
        this.f2935k = z;
    }

    /* renamed from: f */
    public boolean m21752f() {
        return this.f2935k;
    }

    /* renamed from: a */
    public void m21782a(int i, Platform platform) {
        ShareSDKCore.m21798a(i, platform);
    }

    /* renamed from: a */
    public void m21773a(String str, int i) {
        ShareSDKCore.m21795a(str, i);
    }

    /* renamed from: a */
    public void m21771a(String str, HashMap<String, Object> hashMap) {
        synchronized (this.f2927c) {
            HashMap<String, String> hashMap2 = this.f2927c.get(str);
            if (hashMap2 == null) {
                hashMap2 = new HashMap<>();
            }
            synchronized (hashMap2) {
                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        hashMap2.put(key, String.valueOf(value));
                    }
                }
            }
            this.f2927c.put(str, hashMap2);
        }
        synchronized (this.f2928d) {
            if (this.f2926b == EnumC1778a.INITIALIZING) {
                this.f2928d.wait();
            }
        }
        Iterator<Platform> it = this.f2928d.iterator();
        while (it.hasNext()) {
            Platform next = it.next();
            if (next != null && next.getName().equals(str)) {
                next.m22015a();
                return;
            }
        }
    }

    /* renamed from: a */
    public void m21768a(List<HashMap<String, Object>> list) {
        synchronized (this.f2927c) {
            for (HashMap<String, Object> hashMap : list) {
                String str = null;
                HashMap<String, String> hashMap2 = new HashMap<>();
                for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (key.equals("platformName")) {
                        str = String.valueOf(entry.getValue());
                    }
                    if (value != null) {
                        hashMap2.put(key, String.valueOf(value));
                    }
                }
                this.f2927c.put(str, hashMap2);
            }
        }
        synchronized (this.f2928d) {
            if (this.f2926b == EnumC1778a.INITIALIZING) {
                this.f2928d.wait();
            }
        }
    }

    /* renamed from: c */
    public String m21759c(int i) {
        String str;
        synchronized (this.f2928d) {
            synchronized (this.f2931g) {
                str = this.f2930f.get(Integer.valueOf(i));
            }
        }
        return str;
    }

    /* renamed from: b */
    public int m21763b(String str) {
        synchronized (this.f2928d) {
            synchronized (this.f2931g) {
                if (this.f2929e.containsKey(str)) {
                    return this.f2929e.get(str).intValue();
                }
                return 0;
            }
        }
    }

    /* renamed from: a */
    public void m21772a(String str, String str2) {
        synchronized (this.f2927c) {
            this.f2927c.put(str2, this.f2927c.get(str));
        }
    }

    /* renamed from: a */
    public void m21783a(int i, int i2) {
        synchronized (this.f2932h) {
            this.f2932h.put(Integer.valueOf(i2), this.f2932h.get(Integer.valueOf(i)));
        }
    }

    /* renamed from: b */
    public String m21762b(String str, String str2) {
        synchronized (this.f2927c) {
            HashMap<String, String> hashMap = this.f2927c.get(str);
            if (hashMap == null) {
                return null;
            }
            return hashMap.get(str2);
        }
    }

    /* renamed from: a */
    public String m21781a(int i, String str) {
        synchronized (this.f2932h) {
            HashMap<String, Object> hashMap = this.f2932h.get(Integer.valueOf(i));
            String str2 = null;
            if (hashMap == null) {
                return null;
            }
            Object obj = hashMap.get(str);
            if (obj != null) {
                str2 = String.valueOf(obj);
            }
            return str2;
        }
    }

    /* renamed from: g */
    public boolean m21751g() {
        synchronized (this.f2932h) {
            return this.f2932h != null && this.f2932h.size() > 0;
        }
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [cn.sharesdk.framework.i$2] */
    /* renamed from: h */
    public boolean m21750h() {
        boolean z = false;
        if (EnumC1778a.READY != this.f2926b) {
            SSDKLog.m21740b().m21744a("Statistics module unopened", new Object[0]);
            return false;
        }
        final EventManager m21991a = EventManager.m21991a();
        HashMap<String, Object> m21778a = m21778a(m21991a, m21991a.m21973e());
        if (m21778a != null && m21778a.size() > 0) {
            z = m21769a(m21778a);
        }
        if (z) {
            new Thread() { // from class: cn.sharesdk.framework.i.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        HashMap<String, Object> m21971f = m21991a.m21971f();
                        HashMap m21778a2 = ShareSDKCoreThread.this.m21778a(m21991a, m21971f);
                        if (m21778a2 == null || m21778a2.size() <= 0 || !ShareSDKCoreThread.this.m21769a(m21778a2)) {
                            return;
                        }
                        m21991a.m21980a(m21971f);
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21737b(th);
                    }
                }
            }.start();
        } else {
            try {
                HashMap<String, Object> m21971f = m21991a.m21971f();
                HashMap<String, Object> m21778a2 = m21778a(m21991a, m21971f);
                if (m21778a2 != null && m21778a2.size() > 0 && (z = m21769a(m21778a2))) {
                    m21991a.m21980a(m21971f);
                }
            } catch (Throwable th) {
                SSDKLog.m21740b().m21737b(th);
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public HashMap<String, Object> m21778a(EventManager eventManager, HashMap<String, Object> hashMap) {
        try {
            if (hashMap.containsKey("error")) {
                SSDKLog.m21740b().m21735c("ShareSDK parse sns config ==>>", new Hashon().fromHashMap(hashMap));
                return null;
            } else if (!hashMap.containsKey("res")) {
                SSDKLog.m21740b().m21744a("ShareSDK platform config result ==>>", "SNS configuration is empty");
                return null;
            } else {
                String str = (String) hashMap.get("res");
                if (str == null) {
                    return null;
                }
                return eventManager.m21976c(str);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m21769a(HashMap<String, Object> hashMap) {
        synchronized (this.f2932h) {
            HashMap<Integer, HashMap<String, Object>> m21793a = ShareSDKCore.m21793a(hashMap);
            if (m21793a == null || m21793a.size() <= 0) {
                return false;
            }
            this.f2932h.clear();
            this.f2932h = m21793a;
            return true;
        }
    }

    /* renamed from: a */
    public String m21770a(String str, boolean z, int i, String str2) {
        return EnumC1778a.READY != this.f2926b ? str : EventManager.m21991a().m21984a(str, i, z, str2);
    }

    /* renamed from: c */
    public String m21757c(String str) {
        if (EnumC1778a.READY != this.f2926b) {
            return null;
        }
        return EventManager.m21991a().m21978b(str);
    }

    /* renamed from: a */
    public String m21779a(Bitmap bitmap) {
        if (EnumC1778a.READY != this.f2926b) {
            return null;
        }
        return EventManager.m21991a().m21990a(bitmap);
    }

    /* renamed from: i */
    public void m21749i() {
        try {
            ResHelper.clearCache(MobSDK.getContext());
        } catch (Throwable th) {
            SSDKLog.m21740b().m21737b(th);
        }
    }
}
