package cn.sharesdk.framework.utils;

import android.util.Base64;
import com.mob.tools.network.KVPair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Oauth1Signer {

    /* renamed from: a */
    private C1782b f2954a = new C1782b();

    /* renamed from: b */
    private PercentEscaper f2955b = new PercentEscaper("-._~", false);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Oauth1Signer.java */
    /* renamed from: cn.sharesdk.framework.utils.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum EnumC1781a {
        HMAC_SHA1,
        PLAINTEXT
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Oauth1Signer.java */
    /* renamed from: cn.sharesdk.framework.utils.c$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C1782b {

        /* renamed from: a */
        public String f2960a;

        /* renamed from: b */
        public String f2961b;

        /* renamed from: c */
        public String f2962c;

        /* renamed from: d */
        public String f2963d;

        /* renamed from: e */
        public String f2964e;
    }

    /* renamed from: a */
    public void m21703a(String str, String str2, String str3) {
        C1782b c1782b = this.f2954a;
        c1782b.f2960a = str;
        c1782b.f2961b = str2;
        c1782b.f2964e = str3;
    }

    /* renamed from: a */
    public C1782b m21708a() {
        return this.f2954a;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m21701a(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return m21700a(str, arrayList, EnumC1781a.HMAC_SHA1);
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m21700a(String str, ArrayList<KVPair<String>> arrayList, EnumC1781a enumC1781a) throws Throwable {
        return m21702a(str, "POST", arrayList, enumC1781a);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> m21698b(String str, ArrayList<KVPair<String>> arrayList) throws Throwable {
        return m21697b(str, arrayList, EnumC1781a.HMAC_SHA1);
    }

    /* renamed from: b */
    public ArrayList<KVPair<String>> m21697b(String str, ArrayList<KVPair<String>> arrayList, EnumC1781a enumC1781a) throws Throwable {
        return m21702a(str, "GET", arrayList, enumC1781a);
    }

    /* renamed from: c */
    public ArrayList<KVPair<String>> m21695c(String str, ArrayList<KVPair<String>> arrayList, EnumC1781a enumC1781a) throws Throwable {
        return m21702a(str, "PUT", arrayList, enumC1781a);
    }

    /* renamed from: a */
    public void m21704a(String str, String str2) {
        C1782b c1782b = this.f2954a;
        c1782b.f2962c = str;
        c1782b.f2963d = str2;
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m21702a(String str, String str2, ArrayList<KVPair<String>> arrayList, EnumC1781a enumC1781a) throws Throwable {
        String trim;
        long currentTimeMillis = System.currentTimeMillis();
        String str3 = null;
        switch (enumC1781a) {
            case HMAC_SHA1:
                str3 = "HMAC-SHA1";
                SecretKeySpec secretKeySpec = new SecretKeySpec((m21705a(this.f2954a.f2961b) + '&' + m21705a(this.f2954a.f2963d)).getBytes("utf-8"), "HMAC-SHA1");
                Mac mac = Mac.getInstance("HMAC-SHA1");
                mac.init(secretKeySpec);
                String m21696b = m21696b(m21706a(currentTimeMillis, arrayList, "HMAC-SHA1"));
                trim = new String(Base64.encode(mac.doFinal((str2 + '&' + m21705a(str) + '&' + m21705a(m21696b)).getBytes("utf-8")), 0)).trim();
                break;
            case PLAINTEXT:
                str3 = "PLAINTEXT";
                trim = m21705a(this.f2954a.f2961b) + '&' + m21705a(this.f2954a.f2963d);
                break;
            default:
                trim = null;
                break;
        }
        ArrayList<KVPair<String>> m21707a = m21707a(currentTimeMillis, str3);
        m21707a.add(new KVPair<>("oauth_signature", trim));
        return m21707a;
    }

    /* renamed from: a */
    public String m21705a(String str) {
        return str == null ? "" : this.f2955b.escape(str);
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m21706a(long j, ArrayList<KVPair<String>> arrayList, String str) {
        HashMap hashMap = new HashMap();
        if (arrayList != null) {
            Iterator<KVPair<String>> it = arrayList.iterator();
            while (it.hasNext()) {
                KVPair<String> next = it.next();
                hashMap.put(m21705a(next.name), m21705a(next.value));
            }
        }
        ArrayList<KVPair<String>> m21707a = m21707a(j, str);
        if (m21707a != null) {
            Iterator<KVPair<String>> it2 = m21707a.iterator();
            while (it2.hasNext()) {
                KVPair<String> next2 = it2.next();
                hashMap.put(m21705a(next2.name), m21705a(next2.value));
            }
        }
        String[] strArr = new String[hashMap.size()];
        int i = 0;
        for (Map.Entry entry : hashMap.entrySet()) {
            strArr[i] = (String) entry.getKey();
            i++;
        }
        Arrays.sort(strArr);
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        for (String str2 : strArr) {
            arrayList2.add(new KVPair<>(str2, hashMap.get(str2)));
        }
        return arrayList2;
    }

    /* renamed from: b */
    private String m21696b(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            if (i > 0) {
                sb.append('&');
            }
            sb.append(next.name);
            sb.append('=');
            sb.append(next.value);
            i++;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private ArrayList<KVPair<String>> m21707a(long j, String str) {
        ArrayList<KVPair<String>> arrayList = new ArrayList<>();
        arrayList.add(new KVPair<>("oauth_consumer_key", this.f2954a.f2960a));
        arrayList.add(new KVPair<>("oauth_signature_method", str));
        arrayList.add(new KVPair<>("oauth_timestamp", String.valueOf(j / 1000)));
        arrayList.add(new KVPair<>("oauth_nonce", String.valueOf(j)));
        arrayList.add(new KVPair<>("oauth_version", "1.0"));
        String str2 = this.f2954a.f2962c;
        if (str2 != null && str2.length() > 0) {
            arrayList.add(new KVPair<>("oauth_token", str2));
        }
        return arrayList;
    }

    /* renamed from: a */
    public ArrayList<KVPair<String>> m21699a(ArrayList<KVPair<String>> arrayList) {
        StringBuilder sb = new StringBuilder("OAuth ");
        Iterator<KVPair<String>> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            KVPair<String> next = it.next();
            if (i > 0) {
                sb.append(',');
            }
            String m21705a = m21705a(next.value);
            sb.append(next.name);
            sb.append("=\"");
            sb.append(m21705a);
            sb.append("\"");
            i++;
        }
        ArrayList<KVPair<String>> arrayList2 = new ArrayList<>();
        arrayList2.add(new KVPair<>("Authorization", sb.toString()));
        arrayList2.add(new KVPair<>("Content-Type", "application/x-www-form-urlencoded"));
        return arrayList2;
    }
}
