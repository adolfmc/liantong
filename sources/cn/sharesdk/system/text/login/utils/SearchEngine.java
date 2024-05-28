package cn.sharesdk.system.text.login.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.system.text.login.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SearchEngine {

    /* renamed from: a */
    private static HashMap<String, Object> f3148a;

    /* renamed from: b */
    private boolean f3149b;

    /* renamed from: c */
    private ArrayList<C1848a> f3150c;

    /* renamed from: a */
    public static void m21454a() {
        if (f3148a == null) {
            f3148a = new HashMap<>();
        }
    }

    /* renamed from: a */
    public void m21452a(ArrayList<String> arrayList) {
        this.f3150c = new ArrayList<>();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            this.f3150c.add(new C1848a(it.next(), f3148a));
        }
    }

    /* renamed from: a */
    public ArrayList<String> m21453a(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<C1848a> arrayList2 = this.f3150c;
        if (arrayList2 == null) {
            return arrayList;
        }
        Iterator<C1848a> it = arrayList2.iterator();
        while (it.hasNext()) {
            C1848a next = it.next();
            if (next.m21449a(str, this.f3149b)) {
                arrayList.add(next.m21451a());
            }
        }
        return arrayList;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: SearchEngine.java */
    /* renamed from: cn.sharesdk.system.text.login.utils.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class C1848a {

        /* renamed from: a */
        private String f3151a;

        /* renamed from: b */
        private ArrayList<String> f3152b = new ArrayList<>();

        /* renamed from: c */
        private ArrayList<String> f3153c = new ArrayList<>();

        public C1848a(String str, HashMap<String, Object> hashMap) {
            this.f3151a = str;
        }

        /* renamed from: a */
        public String m21451a() {
            return this.f3151a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public boolean m21449a(String str, boolean z) {
            if (str == null || str.trim().length() <= 0) {
                return true;
            }
            if (!z) {
                str = str.toLowerCase();
            }
            String str2 = this.f3151a;
            if (str2 == null || !str2.toLowerCase().contains(str)) {
                Iterator<String> it = this.f3152b.iterator();
                while (it.hasNext()) {
                    if (it.next().contains(str)) {
                        return true;
                    }
                }
                Iterator<String> it2 = this.f3153c.iterator();
                while (it2.hasNext()) {
                    if (it2.next().contains(str)) {
                        return true;
                    }
                }
                return false;
            }
            return true;
        }

        public String toString() {
            HashMap hashMap = new HashMap();
            hashMap.put("text", this.f3151a);
            hashMap.put("pinyin", this.f3152b);
            hashMap.put("firstLatters", this.f3153c);
            return hashMap.toString();
        }
    }
}
