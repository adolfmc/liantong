package com.baidu.p122b;

import android.content.Context;
import android.os.Environment;
import android.os.Process;
import android.provider.Settings;
import android.util.Log;
import com.baidu.p122b.p125c.p126a.C2396c;
import com.baidu.p122b.p125c.p126a.C2400g;
import com.baidu.p122b.p130d.C2415a;
import com.baidu.p122b.p132f.C2424c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2425g {

    /* renamed from: a */
    private Context f4277a;

    /* renamed from: b */
    private C2393c f4278b;

    public C2425g(Context context, C2393c c2393c) {
        this.f4277a = context;
        this.f4278b = c2393c;
    }

    /* renamed from: a */
    private C2421f m20176a() {
        File file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid2");
        if (file.exists()) {
            return C2421f.m20186b(C2424c.m20178a(file));
        }
        return null;
    }

    /* renamed from: a */
    private C2421f m20175a(Context context) {
        List<C2371b> m20292b = this.f4278b.m20292b(context);
        C2421f c2421f = null;
        if (m20292b != null) {
            String str = "files";
            File filesDir = context.getFilesDir();
            if (!"files".equals(filesDir.getName())) {
                Log.e("CuidV266Manager", "fetal error:: app files dir name is unexpectedly :: " + filesDir.getAbsolutePath());
                str = filesDir.getName();
            }
            for (C2371b c2371b : m20292b) {
                if (!c2371b.f4126d) {
                    File file = new File(new File(c2371b.f4123a.dataDir, str), "libcuid.so");
                    if (file.exists() && (c2421f = C2421f.m20186b(C2424c.m20178a(file))) != null) {
                        break;
                    }
                }
            }
        }
        return c2421f;
    }

    /* renamed from: b */
    private C2421f m20173b() {
        return C2421f.m20188a(m20171c("com.baidu.deviceid"), m20171c("bd_setting_i"));
    }

    /* renamed from: b */
    private boolean m20172b(String str) {
        return this.f4277a.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    /* renamed from: c */
    private String m20171c(String str) {
        try {
            return Settings.System.getString(this.f4277a.getContentResolver(), str);
        } catch (Exception e) {
            C2424c.m20177a(e);
            return null;
        }
    }

    /* renamed from: d */
    private String m20170d(String str) {
        return "0";
    }

    /* renamed from: e */
    private C2421f m20169e(String str) {
        String str2 = "";
        String str3 = "";
        File file = new File(Environment.getExternalStorageDirectory(), "baidu/.cuid");
        if (!file.exists()) {
            file = new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig/.cuid");
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append("\r\n");
            }
            bufferedReader.close();
            byte[] m20265a = C2400g.m20265a();
            String[] split = new String(C2396c.m20279a(m20265a, m20265a, C2415a.m20212a(sb.toString().getBytes()))).split("=");
            if (split != null && split.length == 2) {
                str3 = split[0];
                str2 = split[1];
            }
        } catch (FileNotFoundException | IOException | Exception unused) {
        }
        return C2421f.m20188a(str2, str3);
    }

    /* renamed from: a */
    public C2421f m20174a(String str) {
        C2421f m20175a = m20175a(this.f4277a);
        if (m20175a == null) {
            m20175a = C2421f.m20186b(m20171c("com.baidu.deviceid.v2"));
        }
        boolean m20172b = m20172b("android.permission.READ_EXTERNAL_STORAGE");
        if (m20175a == null && m20172b) {
            m20175a = m20176a();
        }
        if (m20175a == null) {
            m20175a = m20173b();
        }
        boolean z = false;
        if (m20175a == null && m20172b) {
            z = true;
            m20175a = m20169e(m20170d(""));
        }
        if (!z) {
            m20170d("");
        }
        if (m20175a != null) {
            m20175a.m20185c();
        }
        return m20175a;
    }
}
