package com.bytedance.applog;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.bytedance.applog.h2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3596h2 {

    /* renamed from: a */
    public final ArrayList<String> f8491a = new ArrayList<>();

    /* renamed from: a */
    public final void m17285a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        StringBuilder m17349a = C3535a.m17349a(str);
        m17349a.append(File.separator);
        m17349a.append(str2);
        m17349a.append(".dat");
        File file = new File(m17349a.toString());
        if (file.exists()) {
            file.delete();
        }
    }
}
