package cn.sharesdk.framework.p094a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.p094a.p095a.MessageModel;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import cn.sharesdk.framework.p094a.p096b.AuthEvent;
import cn.sharesdk.framework.p094a.p096b.BaseEvent;
import cn.sharesdk.framework.p094a.p096b.ShareEvent;
import cn.sharesdk.framework.utils.SSDKLog;
import com.mob.MobSDK;
import com.mob.tools.utils.BitmapHelper;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EventManager {

    /* renamed from: a */
    private static EventManager f2767a;

    /* renamed from: e */
    private boolean f2771e = true;

    /* renamed from: b */
    private Protocols f2768b = new Protocols();

    /* renamed from: d */
    private SharePrefrenceUtil f2770d = SharePrefrenceUtil.m21961a();

    /* renamed from: c */
    private DeviceHelper f2769c = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: a */
    public static EventManager m21991a() {
        if (f2767a == null) {
            f2767a = new EventManager();
        }
        return f2767a;
    }

    private EventManager() {
    }

    /* renamed from: a */
    public void m21985a(String str) {
        if (this.f2768b == null || TextUtils.isEmpty(str)) {
            return;
        }
        this.f2768b.m21918a(str);
    }

    /* renamed from: b */
    public void m21979b() {
        try {
            String networkType = this.f2769c.getNetworkType();
            if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType)) {
                long longValue = this.f2770d.m21932k().longValue();
                long currentTimeMillis = System.currentTimeMillis();
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(longValue);
                int i = calendar.get(5);
                calendar.setTimeInMillis(currentTimeMillis);
                int i2 = calendar.get(5);
                if (currentTimeMillis - longValue >= 86400000 || i != i2) {
                    HashMap<String, Object> m21920a = this.f2768b.m21920a();
                    this.f2770d.m21948c(m21920a.containsKey("res") ? "true".equals(String.valueOf(m21920a.get("res"))) : true);
                    if (m21920a == null || m21920a.size() <= 0) {
                        return;
                    }
                    this.f2770d.m21953b(System.currentTimeMillis());
                }
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    /* renamed from: c */
    public void m21977c() {
        HashMap hashMap;
        HashMap hashMap2;
        try {
            String networkType = this.f2769c.getNetworkType();
            if ("none".equals(networkType) || TextUtils.isEmpty(networkType) || !this.f2770d.m21934j()) {
                return;
            }
            this.f2770d.m21960a(System.currentTimeMillis());
            HashMap<String, Object> m21910c = this.f2768b.m21910c();
            if (m21910c.containsKey("status") && ResHelper.parseInt(String.valueOf(m21910c.get("status"))) == -200) {
                SSDKLog.m21740b().m21744a((String) m21910c.get("error"), new Object[0]);
                return;
            }
            if (m21910c.containsKey("timestamp")) {
                this.f2770d.m21957a("service_time", Long.valueOf(System.currentTimeMillis() - ResHelper.parseLong(String.valueOf(m21910c.get("timestamp")))));
            }
            if (m21910c.containsKey("switchs") && (hashMap2 = (HashMap) m21910c.get("switchs")) != null) {
                String valueOf = String.valueOf(hashMap2.get("device"));
                String valueOf2 = String.valueOf(hashMap2.get("share"));
                String valueOf3 = String.valueOf(hashMap2.get("auth"));
                String valueOf4 = String.valueOf(hashMap2.get("backflow"));
                String valueOf5 = String.valueOf(hashMap2.get("loginplus"));
                String valueOf6 = String.valueOf(hashMap2.get("linkcard"));
                this.f2770d.m21952b(valueOf);
                this.f2770d.m21946d(valueOf2);
                this.f2770d.m21949c(valueOf3);
                this.f2770d.m21959a(valueOf4);
                this.f2770d.m21943e(valueOf5);
                this.f2770d.m21941f(valueOf6);
            }
            if (!m21910c.containsKey("serpaths") || (hashMap = (HashMap) m21910c.get("serpaths")) == null) {
                return;
            }
            String valueOf7 = String.valueOf(hashMap.get("defhost"));
            String valueOf8 = String.valueOf(hashMap.get("defport"));
            if (!TextUtils.isEmpty(valueOf7) && !TextUtils.isEmpty(valueOf8)) {
                if (!"443".equals(valueOf8) && !"80".equals(valueOf8)) {
                    Protocols protocols = this.f2768b;
                    protocols.m21912b(MobSDK.checkRequestUrl(valueOf7) + ":" + valueOf8);
                }
                this.f2768b.m21912b(MobSDK.checkRequestUrl(valueOf7));
            }
            HashMap<String, String> hashMap3 = new HashMap<>();
            if (hashMap.containsKey("assigns")) {
                HashMap hashMap4 = (HashMap) hashMap.get("assigns");
                if (hashMap4 != null && hashMap4.size() != 0) {
                    for (String str : hashMap4.keySet()) {
                        HashMap hashMap5 = (HashMap) hashMap4.get(str);
                        String valueOf9 = String.valueOf(hashMap5.get("host"));
                        String valueOf10 = String.valueOf(hashMap5.get("port"));
                        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(valueOf9) && !TextUtils.isEmpty(valueOf10)) {
                            hashMap3.put(str, "http://" + valueOf9 + ":" + valueOf10);
                        }
                    }
                    this.f2768b.m21914a(hashMap3);
                    return;
                }
                this.f2768b.m21914a((HashMap<String, String>) null);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    /* renamed from: a */
    public void m21987a(BaseEvent baseEvent) {
        try {
            if (this.f2770d.m21934j()) {
                if (baseEvent instanceof AuthEvent) {
                    m21988a((AuthEvent) baseEvent);
                } else if (baseEvent instanceof ShareEvent) {
                    m21986a((ShareEvent) baseEvent);
                }
                if (!this.f2770d.m21950c()) {
                    baseEvent.f2798l = null;
                }
                long m21954b = this.f2770d.m21954b();
                if (m21954b == 0) {
                    m21954b = this.f2768b.m21913b();
                }
                baseEvent.f2791e = System.currentTimeMillis() - m21954b;
                this.f2768b.m21919a(baseEvent);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    /* renamed from: a */
    private void m21988a(AuthEvent authEvent) throws Throwable {
        boolean m21947d = this.f2770d.m21947d();
        String str = authEvent.f2789c;
        if (m21947d && !TextUtils.isEmpty(str)) {
            authEvent.f2789c = Data.Base64AES(str, authEvent.f2792f.substring(0, 16));
            return;
        }
        authEvent.f2790d = null;
        authEvent.f2789c = null;
    }

    /* renamed from: a */
    private void m21986a(ShareEvent shareEvent) throws Throwable {
        int m21942f = this.f2770d.m21942f();
        boolean m21947d = this.f2770d.m21947d();
        ShareEvent.C1746a c1746a = shareEvent.f2812d;
        if (m21942f == 1) {
            int size = (c1746a == null || c1746a.f2819e == null) ? 0 : c1746a.f2819e.size();
            for (int i = 0; i < size; i++) {
                String m21983a = m21983a(c1746a.f2819e.get(i), PicUploadType.FINISH_SHARE);
                if (!TextUtils.isEmpty(m21983a)) {
                    c1746a.f2818d.add(m21983a);
                }
            }
            int size2 = (c1746a == null || c1746a.f2820f == null) ? 0 : c1746a.f2820f.size();
            for (int i2 = 0; i2 < size2; i2++) {
                String m21989a = m21989a(c1746a.f2820f.get(i2), PicUploadType.FINISH_SHARE);
                if (!TextUtils.isEmpty(m21989a)) {
                    c1746a.f2818d.add(m21989a);
                }
            }
        } else {
            shareEvent.f2812d = null;
        }
        if (m21947d) {
            return;
        }
        shareEvent.f2813m = null;
    }

    /* renamed from: a */
    private String m21983a(String str, PicUploadType picUploadType) throws Throwable {
        int ceil;
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return null;
        }
        String networkType = this.f2769c.getNetworkType();
        if ("none".equals(networkType) || TextUtils.isEmpty(networkType)) {
            return null;
        }
        Bitmap.CompressFormat bmpFormat = BitmapHelper.getBmpFormat(str);
        float f = picUploadType == PicUploadType.BEFORE_SHARE ? 600.0f : 200.0f;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        options.inJustDecodeBounds = false;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i >= i2 && i2 > f) {
            ceil = (int) Math.ceil(options.outHeight / f);
        } else if (i < i2 && i > f) {
            ceil = (int) Math.ceil(options.outWidth / f);
        } else {
            return m21974d(str);
        }
        if (ceil <= 0) {
            ceil = 1;
        }
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inSampleSize = ceil;
        options2.inPurgeable = true;
        options2.inInputShareable = true;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options2);
        decodeFile.getHeight();
        decodeFile.getWidth();
        File createTempFile = File.createTempFile("bm_tmp2", "." + bmpFormat.name().toLowerCase());
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        decodeFile.compress(bmpFormat, 80, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m21974d(createTempFile.getAbsolutePath());
    }

    /* renamed from: d */
    private String m21974d(String str) throws Throwable {
        HashMap<String, Object> m21909c = this.f2768b.m21909c(str);
        if (m21909c != null && m21909c.size() > 0 && m21909c.containsKey("status") && ResHelper.parseInt(String.valueOf(m21909c.get("status"))) == 200 && m21909c.containsKey("url")) {
            return (String) m21909c.get("url");
        }
        return null;
    }

    /* renamed from: a */
    private String m21989a(Bitmap bitmap, PicUploadType picUploadType) throws Throwable {
        File createTempFile = File.createTempFile("bm_tmp", ".png");
        FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return m21983a(createTempFile.getAbsolutePath(), picUploadType);
    }

    /* renamed from: d */
    public void m21975d() {
        boolean m21981a;
        try {
            String networkType = this.f2769c.getNetworkType();
            if ("none".equals(networkType) || TextUtils.isEmpty(networkType) || !this.f2770d.m21934j()) {
                return;
            }
            ArrayList<MessageModel> m21906e = this.f2768b.m21906e();
            for (int i = 0; i < m21906e.size(); i++) {
                MessageModel messageModel = m21906e.get(i);
                if (messageModel.f2775b.size() == 1) {
                    m21981a = m21981a(messageModel.f2774a, false);
                } else {
                    m21981a = m21981a(m21972e(messageModel.f2774a), true);
                }
                if (m21981a) {
                    this.f2768b.m21915a(messageModel.f2775b);
                }
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    /* renamed from: e */
    private String m21972e(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(byteArray, 2);
            }
        }
    }

    /* renamed from: a */
    private boolean m21981a(String str, boolean z) throws Throwable {
        return this.f2768b.m21916a(str, z);
    }

    /* renamed from: a */
    public String m21984a(String str, int i, boolean z, String str2) {
        String m21982a;
        try {
            if (this.f2770d.m21934j() && this.f2770d.m21944e()) {
                String networkType = this.f2769c.getNetworkType();
                if (!"none".equals(networkType) && !TextUtils.isEmpty(networkType)) {
                    if (!z || (m21982a = m21982a(str, "<a[^>]*?href[\\s]*=[\\s]*[\"']?([^'\">]+?)['\"]?>", i, str2)) == null || m21982a.equals(str)) {
                        String m21982a2 = m21982a(str, "(http://|https://){1}[\\w\\.\\-/:\\?&%=,;\\[\\]\\{\\}`~!@#\\$\\^\\*\\(\\)_\\+\\\\\\|]+", i, str2);
                        if (m21982a2 != null) {
                            if (!m21982a2.equals(str)) {
                                return m21982a2;
                            }
                        }
                        return str;
                    }
                    return m21982a;
                }
                return str;
            }
            return str;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return str;
        }
    }

    /* renamed from: a */
    private String m21982a(String str, String str2, int i, String str3) throws Throwable {
        HashMap<String, Object> m21917a;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Pattern compile = Pattern.compile(str2);
        Matcher matcher = compile.matcher(str);
        while (matcher.find()) {
            String group2 = matcher.group();
            if (group2 != null && group2.length() > 0) {
                arrayList.add(group2);
            }
        }
        if (arrayList.size() != 0 && (m21917a = this.f2768b.m21917a(str, arrayList, i, str3)) != null && m21917a.size() > 0 && m21917a.containsKey("data")) {
            HashMap hashMap = new HashMap();
            Iterator it = ((ArrayList) m21917a.get("data")).iterator();
            while (it.hasNext()) {
                HashMap hashMap2 = (HashMap) it.next();
                hashMap.put(String.valueOf(hashMap2.get("source")), String.valueOf(hashMap2.get("surl")));
            }
            Matcher matcher2 = compile.matcher(str);
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (matcher2.find()) {
                sb.append(str.substring(i2, matcher2.start()));
                sb.append((String) hashMap.get(matcher2.group()));
                i2 = matcher2.end();
            }
            sb.append(str.substring(i2, str.length()));
            String sb2 = sb.toString();
            SSDKLog.m21740b().m21735c("> SERVER_SHORT_LINK_URL content after replace link ===  %s", sb2);
            return sb2;
        }
        return str;
    }

    /* renamed from: b */
    public String m21978b(String str) {
        if (this.f2770d.m21934j()) {
            try {
                return m21983a(str, PicUploadType.BEFORE_SHARE);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    public String m21990a(Bitmap bitmap) {
        if (this.f2770d.m21934j()) {
            try {
                return m21989a(bitmap, PicUploadType.BEFORE_SHARE);
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
                return null;
            }
        }
        return null;
    }

    /* renamed from: e */
    public HashMap<String, Object> m21973e() {
        try {
            return this.f2768b.m21904f();
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return new HashMap<>();
        }
    }

    /* renamed from: f */
    public HashMap<String, Object> m21971f() {
        if (!this.f2770d.m21934j() && this.f2770d.m21930l()) {
            return new HashMap<>();
        }
        try {
            HashMap<String, Object> m21908d = this.f2768b.m21908d();
            this.f2770d.m21945d(true);
            return m21908d;
        } catch (Throwable th) {
            this.f2770d.m21945d(false);
            SSDKLog.m21740b().m21742a(th);
            return new HashMap<>();
        }
    }

    /* renamed from: a */
    public void m21980a(HashMap<String, Object> hashMap) {
        try {
            this.f2768b.m21911b(hashMap);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
    }

    /* renamed from: c */
    public HashMap<String, Object> m21976c(String str) {
        try {
            return this.f2768b.m21907d(str);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }
}
