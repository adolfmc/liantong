package com.mob.commons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.AbstractC6201c;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.C6200b;
import com.mob.tools.utils.ReflectHelper;
import com.mob.tools.utils.UIHandler;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.commons.u */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5873u {

    /* renamed from: a */
    private static volatile String f14484a;

    /* renamed from: b */
    private static final byte[] f14485b = new byte[0];

    /* renamed from: a */
    public static Context m12188a() {
        try {
            Object m12178b = m12178b();
            if (m12178b != null) {
                return (Context) ReflectHelper.invokeInstanceMethod(m12178b, C5868q.m12203b("014Tdd=ehAdk0iifGchEbchAchdcXd"), new Object[0]);
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* renamed from: b */
    public static Object m12178b() {
        Object mo11056a;
        final ReflectHelper.InterfaceC6184a<Void, Object> interfaceC6184a = new ReflectHelper.InterfaceC6184a<Void, Object>() { // from class: com.mob.commons.u.1
            @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
            /* renamed from: a  reason: avoid collision after fix types in other method */
            public Object mo11056a(Void r4) {
                return ReflectHelper.invokeStaticMethodNoThrow(ReflectHelper.importClassNoThrow(C5868q.m12203b("026cd2cbcidcchcbecNciiKecdk1bhZchccch2h+cjdjQg]ci]ec+cb"), null), C5868q.m12203b("021bQcfciciHedhTdkEbh]chccchZh=cjdj[gPci.ec@cb"), null, new Object[0]);
            }
        };
        if ((Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId() || Build.VERSION.SDK_INT >= 18) && (mo11056a = interfaceC6184a.mo11056a(null)) != null) {
            return mo11056a;
        }
        final Object obj = new Object();
        final Object[] objArr = new Object[1];
        synchronized (obj) {
            UIHandler.sendEmptyMessage(0, new Handler.Callback() { // from class: com.mob.commons.u.2
                @Override // android.os.Handler.Callback
                public boolean handleMessage(Message message) {
                    synchronized (obj) {
                        objArr[0] = interfaceC6184a.mo11056a(null);
                        obj.notify();
                    }
                    return false;
                }
            });
            obj.wait();
        }
        return objArr[0];
    }

    /* renamed from: a */
    public static void m12179a(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable th) {
                    MobLog.getInstance().m11341d(th);
                }
            }
        }
    }

    /* renamed from: a */
    public static void m12183a(final AbstractC6201c<ArrayList<HashMap<String, Object>>> abstractC6201c) {
        C6152DH.requester(MobSDK.getContext()).getMwlfo().getMbcdi().request(new C6152DH.DHResponder() { // from class: com.mob.commons.u.3
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                ArrayList arrayList = new ArrayList();
                try {
                    ArrayList<HashMap<String, Object>> mwlfo = dHResponse.getMwlfo();
                    if (mwlfo != null && !mwlfo.isEmpty()) {
                        ArrayList<String> m12550g = C5747b.m12550g();
                        if (m12550g != null && !m12550g.isEmpty()) {
                            String mbcdi = dHResponse.getMbcdi();
                            Iterator<HashMap<String, Object>> it = mwlfo.iterator();
                            while (it.hasNext()) {
                                HashMap<String, Object> next = it.next();
                                Object obj = next.get(C5868q.m12203b("005Gehdididhej"));
                                if (obj != null && String.valueOf(obj).equals(mbcdi)) {
                                    next.put(C5868q.m12203b("010Hcgcgcg1bIcfcifidcIdd"), true);
                                    mbcdi = null;
                                }
                                HashMap hashMap = new HashMap();
                                Iterator<String> it2 = m12550g.iterator();
                                while (it2.hasNext()) {
                                    String next2 = it2.next();
                                    Object obj2 = next.get(next2);
                                    if (obj2 != null) {
                                        hashMap.put(next2, obj2);
                                    }
                                }
                                arrayList.add(hashMap);
                            }
                        }
                        AbstractC6201c.this.mo11088a(null);
                        return;
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().m11325w(th);
                }
                AbstractC6201c abstractC6201c2 = AbstractC6201c.this;
                if (arrayList.isEmpty()) {
                    arrayList = null;
                }
                abstractC6201c2.mo11088a(arrayList);
            }
        });
    }

    /* renamed from: a */
    public static String m12181a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(C5868q.m12203b("007ghhijkk"))) {
            str = str.replace(C5868q.m12203b("007ghhijkk"), "");
        }
        if (str.startsWith("https://")) {
            str = str.replace("https://", "");
        }
        if (MobSDK.checkV6()) {
            str2 = C5868q.m12203b("002^ccgf");
        } else {
            switch (MobSDK.getDomain()) {
                case JP:
                    str2 = "jp";
                    break;
                case US:
                    str2 = C5868q.m12203b("002Dcfeg");
                    break;
                default:
                    str2 = "";
                    break;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return m12175b(C5868q.m12203b("007ghhijkk") + str);
        }
        if (str.startsWith(str2 + ".")) {
            return m12175b(C5868q.m12203b("007ghhijkk") + str);
        }
        return m12175b(C5868q.m12203b("007ghhijkk") + str2 + "-" + str);
    }

    /* renamed from: b */
    public static String m12175b(String str) {
        Uri parse;
        String scheme;
        String str2;
        String str3;
        try {
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        boolean checkForceHttps = MobSDK.checkForceHttps();
        if (checkForceHttps || (Build.VERSION.SDK_INT >= 23 && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted())) {
            str = str.trim();
            if (str.startsWith(C5868q.m12203b("007ghhijkk")) && (parse = Uri.parse(str.trim())) != null && (scheme = parse.getScheme()) != null && scheme.equals(C5868q.m12203b("004ghhi"))) {
                String host = parse.getHost();
                String path = parse.getPath();
                String query = parse.getQuery();
                if (host != null) {
                    int port = parse.getPort();
                    StringBuilder sb = new StringBuilder();
                    sb.append(host);
                    if (port > 0 && port != 80) {
                        str3 = ":" + port;
                        sb.append(str3);
                        host = sb.toString();
                        if (!checkForceHttps && Build.VERSION.SDK_INT >= 24 && ((Boolean) ReflectHelper.invokeInstanceMethod(NetworkSecurityPolicy.getInstance(), C5868q.m12203b("027Qchegfi^fec]ciYhe@dbPhJdjci!cBdedech;b;fj[e'cicech'hheDcb"), host)).booleanValue()) {
                            return str;
                        }
                    }
                    str3 = "";
                    sb.append(str3);
                    host = sb.toString();
                    if (!checkForceHttps) {
                        return str;
                    }
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("https://");
                sb2.append(host);
                if (path == null) {
                    path = "";
                }
                sb2.append(path);
                if (query == null) {
                    str2 = "";
                } else {
                    str2 = "?" + query;
                }
                sb2.append(str2);
                return sb2.toString();
            }
        }
        return str;
    }

    /* renamed from: a */
    public static void m12182a(File file) throws Throwable {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile()) {
            m12176b(file);
            return;
        }
        String[] list = file.list();
        if (list == null || list.length == 0) {
            m12176b(file);
            return;
        }
        for (String str : list) {
            File file2 = new File(file, str);
            if (file2.isDirectory()) {
                m12182a(file2);
            } else {
                m12176b(file2);
            }
        }
        m12176b(file);
    }

    /* renamed from: c */
    public static Object m12173c(String str) throws Throwable {
        return ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(C5868q.m12203b("017KggLc-ccScPecGfcdDddecfgcf(dh<chce>e")), C5868q.m12203b("010OddUehSfgcf-dhHchce4e"), new Object[0]), C5868q.m12203b("004eWdb9eb"), new Object[]{str}, new Class[]{String.class});
    }

    /* renamed from: a */
    public static void m12184a(View view) {
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe("input_method");
        if (systemServiceSafe == null) {
            return;
        }
        ((InputMethodManager) systemServiceSafe).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /* renamed from: b */
    public static void m12177b(View view) {
        Object systemServiceSafe = C6152DH.SyncMtd.getSystemServiceSafe("input_method");
        if (systemServiceSafe == null) {
            return;
        }
        ((InputMethodManager) systemServiceSafe).toggleSoftInputFromWindow(view.getWindowToken(), 2, 0);
    }

    /* renamed from: a */
    public static Intent m12185a(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return Build.VERSION.SDK_INT < 33 ? (Intent) ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), C5868q.m12203b("016,ciJeWddcheg6he$cifg%ebe<chcc)eRci"), new Object[]{broadcastReceiver, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class}, null) : (Intent) ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), C5868q.m12203b("0164ci7eTddchegZhe3cifgIebe'chcc;e1ci"), new Object[]{broadcastReceiver, intentFilter, 4}, new Class[]{BroadcastReceiver.class, IntentFilter.class, Integer.TYPE}, null);
    }

    /* renamed from: a */
    public static void m12186a(BroadcastReceiver broadcastReceiver) {
        ReflectHelper.invokeInstanceMethod(MobSDK.getContext(), C5868q.m12203b("018Dcf:d'ci1eFddcheg8he2cifg@ebeIchcc;e)ci"), new Object[]{broadcastReceiver}, new Class[]{BroadcastReceiver.class}, null);
    }

    /* renamed from: c */
    public static byte[] m12174c() throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        DataOutputStream dataOutputStream;
        Throwable th;
        SecureRandom secureRandom;
        try {
            secureRandom = new SecureRandom();
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            dataOutputStream = null;
        }
        try {
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        } catch (Throwable th3) {
            th = th3;
            dataOutputStream = null;
            th = th;
            m12179a(dataOutputStream, byteArrayOutputStream);
            throw th;
        }
        try {
            dataOutputStream.writeLong(secureRandom.nextLong());
            dataOutputStream.writeLong(secureRandom.nextLong());
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            m12179a(dataOutputStream, byteArrayOutputStream);
            return byteArray;
        } catch (Throwable th4) {
            th = th4;
            m12179a(dataOutputStream, byteArrayOutputStream);
            throw th;
        }
    }

    /* renamed from: d */
    public static Object m12171d(String str) {
        try {
            return MobSDK.getContext().getSystemService(str);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m12187a(long j, long j2) {
        if (j <= 0 || j2 <= 0) {
            return false;
        }
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.format(new Date(j)).equals(simpleDateFormat.format(new Date(j2)));
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return false;
        }
    }

    /* renamed from: b */
    private static void m12176b(File file) {
        ReflectHelper.invokeInstanceMethod(file, C5868q.m12203b("0068cbEefehe"), null, null, null);
    }

    /* renamed from: d */
    public static String m12172d() {
        if (TextUtils.isEmpty(f14484a)) {
            synchronized (f14485b) {
                try {
                    if (TextUtils.isEmpty(f14484a)) {
                        f14484a = new C6200b(MobSDK.getContext()).m11095a();
                    }
                }
            }
        }
        return f14484a;
    }

    /* renamed from: a */
    public static String m12180a(String str, int i) {
        int parseInt;
        int i2 = 3;
        if (str.startsWith("00")) {
            parseInt = Integer.parseInt(str.substring(2, 3));
        } else if (str.startsWith("0")) {
            parseInt = Integer.parseInt(str.substring(1, 3));
        } else {
            parseInt = Integer.parseInt(str.substring(0, 3));
        }
        char[] charArray = str.toCharArray();
        int[] iArr = new int[parseInt];
        StringBuilder sb = new StringBuilder();
        int i3 = 0;
        boolean z = true;
        while (i2 < charArray.length) {
            if (charArray[i2] < 'a') {
                z = !z;
            } else {
                if (z) {
                    iArr[i3] = charArray[i2] - i;
                } else {
                    iArr[i3] = (charArray[i2] - i) * 10;
                    i2++;
                    iArr[i3] = iArr[i3] + (charArray[i2] - i);
                }
                sb.append(iArr[i3]);
                i3++;
            }
            i2++;
        }
        return C5782c.m12484a(iArr);
    }
}
