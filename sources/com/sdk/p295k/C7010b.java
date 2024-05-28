package com.sdk.p295k;

import android.annotation.SuppressLint;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"SimpleDateFormat"})
/* renamed from: com.sdk.k.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7010b {

    /* renamed from: a */
    public static SimpleDateFormat f18160a = new SimpleDateFormat();

    /* renamed from: a */
    public static String m8152a(String str) {
        if ("".equals(str.trim())) {
            f18160a.applyPattern(JtDateUtil.dateFormatYMDHM);
        } else {
            f18160a.applyPattern(str);
        }
        return f18160a.format(new Date());
    }
}
