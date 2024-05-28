package com.mob.commons;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6031c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.HashonHelper;
import com.mob.tools.utils.ResHelper;
import com.sdk.p285a.C6960d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.commons.j */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5849j {

    /* renamed from: b */
    private static C5849j f14402b = new C5849j();

    /* renamed from: c */
    private volatile boolean f14404c = false;

    /* renamed from: d */
    private volatile long f14405d = 0;

    /* renamed from: e */
    private final ConcurrentHashMap<String, Object> f14406e = new ConcurrentHashMap<>();

    /* renamed from: f */
    private final ConcurrentHashMap<String, Object> f14407f = new ConcurrentHashMap<>();

    /* renamed from: a */
    public final AtomicBoolean f14403a = new AtomicBoolean(false);

    private C5849j() {
    }

    /* renamed from: a */
    public static C5849j m12264a() {
        return f14402b;
    }

    /* renamed from: b */
    public boolean m12253b() {
        return m12254a(false);
    }

    /* renamed from: a */
    public synchronized boolean m12254a(boolean z) {
        return !m12250b(z);
    }

    /* renamed from: c */
    public ConcurrentHashMap<String, Object> m12249c() {
        return this.f14406e;
    }

    /* renamed from: b */
    private synchronized boolean m12250b(boolean z) {
        long longValue;
        String str;
        try {
            if (z) {
                HashMap fromJson = HashonHelper.fromJson(C5741aa.m12650a().m12625d());
                if (fromJson.isEmpty()) {
                    fromJson = HashonHelper.fromJson(C5741aa.m12650a().m12629c());
                }
                longValue = ((Long) ResHelper.forceCast(fromJson.get(C5731l.m12674a("004gdg8fd")), 5L)).longValue() * 1000;
                str = (String) ResHelper.forceCast(fromJson.get(C5731l.m12674a("002d.ed")), C5731l.m12674a("0065ififigigigig"));
            } else {
                longValue = ((Long) C5747b.m12583a(C5731l.m12674a("004gdg<fd"), 5L)).longValue() * 1000;
                str = (String) C5747b.m12583a(C5731l.m12674a("002dGed"), C5731l.m12674a("0069ififigigigig"));
            }
            if (this.f14405d != 0 && System.currentTimeMillis() - this.f14405d <= longValue) {
                return this.f14404c;
            }
            boolean m12259a = m12259a(str);
            if (this.f14405d == 0 || m12259a != this.f14404c) {
                m12248c(m12259a);
            }
            this.f14405d = System.currentTimeMillis();
            this.f14404c = m12259a;
            return m12259a;
        } catch (Throwable th) {
            MobLog.getInstance().m11336e(th);
            return true;
        }
    }

    /* renamed from: a */
    private boolean m12259a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            char[] charArray = str.toCharArray();
            HashMap hashMap = new HashMap();
            boolean z = false;
            for (int i = 0; i < charArray.length; i++) {
                if (charArray[i] == '1') {
                    z |= m12263a(i);
                } else if (charArray[i] != '0') {
                    List list = (List) hashMap.get(Character.valueOf(charArray[i]));
                    if (list == null) {
                        list = new ArrayList();
                    }
                    list.add(Integer.valueOf(i));
                    hashMap.put(Character.valueOf(charArray[i]), list);
                }
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                boolean z2 = true;
                for (Integer num : (List) entry.getValue()) {
                    z2 &= m12263a(num.intValue());
                }
                z |= z2;
            }
            return z;
        } catch (Throwable th) {
            MobLog.getInstance().m11336e(th);
            return true;
        }
    }

    /* renamed from: a */
    private boolean m12263a(int i) {
        boolean[] zArr = {true};
        C6152DH.RequestBuilder requester = C6152DH.requester(MobSDK.getContext());
        switch (i) {
            case 0:
                requester.checkUA();
                break;
            case 1:
                requester.usbEnable();
                break;
            case 2:
                requester.vpn();
                break;
            case 3:
                requester.isMwpy();
                break;
            case 4:
                requester.isRooted();
                break;
            case 5:
                requester.m11278cx();
                break;
        }
        requester.request(new C58501(i, zArr));
        return zArr[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.j$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C58501 implements C6152DH.DHResponder {

        /* renamed from: a */
        final /* synthetic */ int f14408a;

        /* renamed from: b */
        final /* synthetic */ boolean[] f14409b;

        C58501(int i, boolean[] zArr) {
            this.f14408a = i;
            this.f14409b = zArr;
        }

        @Override // com.mob.tools.utils.C6152DH.DHResponder
        public void onResponse(C6152DH.DHResponse dHResponse) {
            switch (this.f14408a) {
                case 0:
                    this.f14409b[0] = dHResponse.checkUA();
                    C5849j.this.f14406e.put(C5731l.m12674a("002Neh!d"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                case 1:
                    this.f14409b[0] = dHResponse.usbEnable();
                    C5849j.this.f14406e.put(C5731l.m12674a("002;ehed"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                case 2:
                    this.f14409b[0] = dHResponse.vpn();
                    C5849j.this.f14406e.put(C5731l.m12674a("002(eeWk"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                case 3:
                    this.f14409b[0] = dHResponse.isMwpy();
                    C5849j.this.f14406e.put(C5731l.m12674a("002:gg(k"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                case 4:
                    this.f14409b[0] = dHResponse.isRooted();
                    C5849j.this.f14406e.put(C5731l.m12674a("002$ek)j"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                case 5:
                    this.f14409b[0] = dHResponse.m11284cx();
                    C5849j.this.f14406e.put(C5731l.m12674a("002.fd9k"), Integer.valueOf(this.f14409b[0] ? 1 : 0));
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    private void m12248c(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(C5731l.m12674a("005dhgeTek"), Integer.valueOf(!z ? 1 : 0));
        hashMap.put(C5731l.m12674a("002+eh)d"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("002+eh)d")), 0));
        hashMap.put(C5731l.m12674a("002'ehed"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("002'ehed")), 0));
        hashMap.put(C5731l.m12674a("002Nee5k"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("002Nee5k")), 0));
        hashMap.put(C5731l.m12674a("002ZggUk"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("002ZggUk")), 0));
        hashMap.put(C5731l.m12674a("0020ek%j"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("0020ek%j")), 0));
        hashMap.put(C5731l.m12674a("002=fdYk"), ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("002=fdYk")), 0));
        long currentTimeMillis = System.currentTimeMillis();
        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put(C5731l.m12674a("004jLelKkg"), "ECMT");
        hashMap2.put(C5731l.m12674a("004UedWeje"), hashMap);
        hashMap2.put(C5731l.m12674a("0081ed4ejgjXejegNg"), Long.valueOf(currentTimeMillis));
        C5782c.m12489a().m12488a(currentTimeMillis, hashMap2);
    }

    /* renamed from: a */
    public boolean m12256a(HashMap<String, Object> hashMap) {
        try {
            List<String> list = (List) ResHelper.forceCast(hashMap.get("j"), null);
            if (list != null && list.size() > 0) {
                boolean z = false;
                for (String str : list) {
                    if (str.contains(",")) {
                        boolean z2 = true;
                        for (String str2 : str.split(",")) {
                            z2 &= m12258a(str2, hashMap);
                        }
                        z |= z2;
                    } else {
                        z |= m12258a(str, hashMap);
                    }
                }
                this.f14406e.put(C5731l.m12674a("006[ff^k:eiek[gOgi"), Boolean.valueOf(!z));
                return !z;
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11336e(th);
        }
        return true;
    }

    /* renamed from: a */
    private boolean m12258a(String str, HashMap<String, Object> hashMap) {
        boolean z = true;
        if (TextUtils.equals(str, "a")) {
            if (((Integer) ResHelper.forceCast(hashMap.get("a"), 0)).intValue() != 1 || !m12247d()) {
                z = false;
            }
            this.f14407f.put("a", Boolean.valueOf(z));
            return z;
        } else if (TextUtils.equals(str, "p")) {
            ArrayList<Boolean> arrayList = new ArrayList<>();
            boolean m12251b = m12251b(arrayList, (List) ResHelper.forceCast(hashMap.get("p"), null));
            this.f14407f.put("p", arrayList);
            return m12251b;
        } else if (TextUtils.equals(str, "fp")) {
            ArrayList<Boolean> arrayList2 = new ArrayList<>();
            boolean m12251b2 = m12251b(arrayList2, (List) ResHelper.forceCast(hashMap.get("fp"), null));
            this.f14407f.put("fp", arrayList2);
            return m12251b2;
        } else if (TextUtils.equals(str, "s")) {
            boolean m12257a = m12257a(new ArrayList<>(), (List) ResHelper.forceCast(hashMap.get("s"), null));
            this.f14407f.put("s", Boolean.valueOf(m12257a));
            return m12257a;
        } else if (TextUtils.equals(str, "fs")) {
            boolean m12257a2 = m12257a(new ArrayList<>(), (List) ResHelper.forceCast(hashMap.get("fs"), null));
            this.f14407f.put("fs", Boolean.valueOf(m12257a2));
            return m12257a2;
        } else if (TextUtils.equals(str, C6960d.f18019d)) {
            if (((Integer) ResHelper.forceCast(hashMap.get(C6960d.f18019d), 0)).intValue() != 1 || !C6031c.m11708a(MobSDK.getContext()).m11704d().mo11548aw()) {
                z = false;
            }
            this.f14407f.put(C6960d.f18019d, Boolean.valueOf(z));
            return z;
        } else if (TextUtils.equals(str, "bl")) {
            boolean m12252b = m12252b((String) ResHelper.forceCast(hashMap.get("bl"), ""));
            this.f14407f.put("bl", Boolean.valueOf(m12252b));
            return m12252b;
        } else {
            return false;
        }
    }

    /* renamed from: b */
    private boolean m12252b(String str) {
        String m12172d = C5873u.m12172d();
        if (TextUtils.isEmpty(m12172d) || m12172d.length() < str.length()) {
            return false;
        }
        String[] split = m12172d.split("");
        char[] charArray = str.toCharArray();
        ArrayList<Integer> arrayList = new ArrayList();
        boolean z = false;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                z |= TextUtils.equals(split[i], "1");
            } else if (charArray[i] == '2') {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (arrayList.size() > 0) {
            boolean z2 = true;
            for (Integer num : arrayList) {
                z2 &= TextUtils.equals(split[num.intValue()], "1");
            }
            return z | z2;
        }
        return z;
    }

    /* renamed from: d */
    private boolean m12247d() {
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        C6152DH.requester(MobSDK.getContext()).getCarrier().request(new C6152DH.DHResponder() { // from class: com.mob.commons.j.2
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                String carrier = dHResponse.getCarrier();
                if (!TextUtils.isEmpty(carrier) && !TextUtils.equals("-1", carrier)) {
                    linkedBlockingQueue.offer(Boolean.valueOf(!carrier.startsWith("460")));
                }
                linkedBlockingQueue.offer(Boolean.valueOf(!C5849j.this.m12262a(MobSDK.getContext())));
            }
        });
        try {
            Boolean bool = (Boolean) linkedBlockingQueue.poll(120L, TimeUnit.MILLISECONDS);
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public boolean m12262a(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        return locale.getLanguage().startsWith("zh") && TextUtils.equals(locale.getCountry(), "CN");
    }

    /* renamed from: a */
    private boolean m12257a(ArrayList<Boolean> arrayList, final List<String> list) {
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        if (list != null && list.size() > 0) {
            C6152DH.RequestBuilder requester = C6152DH.requester(MobSDK.getContext());
            for (int i = 0; i < list.size(); i++) {
                requester.queryIntentServices(new Intent(list.get(i)), 0);
            }
            requester.request(new C6152DH.DHResponder() { // from class: com.mob.commons.j.3
                @Override // com.mob.tools.utils.C6152DH.DHResponder
                public void onResponse(C6152DH.DHResponse dHResponse) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        List<ResolveInfo> queryIntentServices = dHResponse.queryIntentServices(i2);
                        if (queryIntentServices != null && queryIntentServices.size() > 0) {
                            linkedBlockingQueue.offer(true);
                        }
                    }
                    linkedBlockingQueue.offer(false);
                }
            });
        }
        try {
            Boolean bool = (Boolean) linkedBlockingQueue.poll(150L, TimeUnit.MILLISECONDS);
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    private boolean m12251b(final ArrayList<Boolean> arrayList, final List<String> list) {
        C6152DH.RequestBuilder requester = C6152DH.requester(MobSDK.getContext());
        if (list == null || list.size() == 0) {
            return false;
        }
        for (String str : list) {
            requester.isPackageInstalled(str);
        }
        final boolean[] zArr = {false};
        requester.request(new C6152DH.DHResponder() { // from class: com.mob.commons.j.4
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                for (int i = 0; i < list.size(); i++) {
                    boolean isPackageInstalled = dHResponse.isPackageInstalled(i);
                    arrayList.add(Boolean.valueOf(isPackageInstalled));
                    boolean[] zArr2 = zArr;
                    zArr2[0] = isPackageInstalled | zArr2[0];
                    if (zArr2[0]) {
                        return;
                    }
                }
            }
        });
        return zArr[0];
    }

    /* renamed from: a */
    public void m12255a(HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2, HashMap<String, Object> hashMap3) {
        try {
            boolean booleanValue = ((Boolean) ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("006g,egeiek3g7gi")), false)).booleanValue();
            boolean booleanValue2 = ((Boolean) ResHelper.forceCast(this.f14406e.get(C5731l.m12674a("0063ffLk^eiek4g@gi")), false)).booleanValue();
            HashMap hashMap4 = new HashMap(4);
            hashMap4.put(C5731l.m12674a("0035ekZg4gi"), Boolean.valueOf(booleanValue));
            hashMap4.put(C5731l.m12674a("003Yekejed"), ResHelper.forceCast(hashMap.get(C5731l.m12674a("003Yekejed")), null));
            if (!booleanValue && hashMap2 != null) {
                hashMap4.put(C5731l.m12674a("003^giejed"), ResHelper.forceCast(hashMap2.get(C5731l.m12674a("003^giejed")), null));
            } else {
                hashMap4.put(C5731l.m12674a("003'giejed"), ResHelper.forceCast(hashMap.get(C5731l.m12674a("003'giejed")), null));
            }
            this.f14406e.put(C5731l.m12674a("006g7egeiekVgCgi"), HashonHelper.fromHashMap(hashMap4));
            if (booleanValue) {
                HashMap hashMap5 = new HashMap(4);
                hashMap5.put(C5731l.m12674a("0030ek.g%gi"), Boolean.valueOf(booleanValue2));
                hashMap5.put(C5731l.m12674a("003Gekejed"), ResHelper.forceCast(hashMap.get(C5731l.m12674a("003Gekejed")), null));
                if (!booleanValue2 && hashMap3 != null) {
                    hashMap5.put(C5731l.m12674a("003Wgiejed"), ResHelper.forceCast(hashMap3.get(C5731l.m12674a("003Wgiejed")), null));
                } else {
                    hashMap5.put(C5731l.m12674a("003!giejed"), ResHelper.forceCast(hashMap.get(C5731l.m12674a("003!giejed")), null));
                }
                hashMap5.putAll(this.f14407f);
                this.f14406e.put(C5731l.m12674a("006_ffRkXeiekRg!gi"), HashonHelper.fromHashMap(hashMap5));
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
