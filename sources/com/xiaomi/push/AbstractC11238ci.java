package com.xiaomi.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ci */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC11238ci {

    /* renamed from: com.xiaomi.push.ci$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11239a extends AbstractC11237ch {
        public C11239a() {
            super(1);
        }

        @Override // com.xiaomi.push.AbstractC11237ch
        /* renamed from: a */
        public String mo4531a(Context context, String str, List<InterfaceC11168at> list) {
            if (list == null) {
                return C11169au.m4845a(context, new URL(str));
            }
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            for (InterfaceC11168at interfaceC11168at : list) {
                buildUpon.appendQueryParameter(interfaceC11168at.mo4858a(), interfaceC11168at.mo4857b());
            }
            return C11169au.m4845a(context, new URL(buildUpon.toString()));
        }
    }

    /* renamed from: a */
    public static String m4536a(Context context, String str, List<InterfaceC11168at> list) {
        return m4535a(context, str, list, new C11239a(), true);
    }

    /* renamed from: a */
    public static String m4535a(Context context, String str, List<InterfaceC11168at> list, AbstractC11237ch abstractC11237ch, boolean z) {
        C11228cc c11228cc;
        String str2;
        String str3;
        String str4;
        if (C11169au.m4849a(context)) {
            try {
                ArrayList<String> arrayList = new ArrayList<>();
                if (z) {
                    C11228cc m4566a = C11232cg.m4574a().m4566a(str);
                    if (m4566a != null) {
                        arrayList = m4566a.m4597a(str);
                        c11228cc = m4566a;
                    } else {
                        c11228cc = m4566a;
                    }
                } else {
                    c11228cc = null;
                }
                if (!arrayList.contains(str)) {
                    arrayList.add(str);
                }
                Iterator<String> it = arrayList.iterator();
                String str5 = null;
                while (it.hasNext()) {
                    String next = it.next();
                    ArrayList arrayList2 = list != null ? new ArrayList(list) : null;
                    long currentTimeMillis = System.currentTimeMillis();
                    try {
                    } catch (IOException e) {
                        e = e;
                        str2 = str5;
                    }
                    if (!abstractC11237ch.m4539a(context, next, (List<InterfaceC11168at>) arrayList2)) {
                        break;
                    }
                    str2 = abstractC11237ch.mo4531a(context, next, (List<InterfaceC11168at>) arrayList2);
                    try {
                    } catch (IOException e2) {
                        e = e2;
                    }
                    if (TextUtils.isEmpty(str2)) {
                        if (c11228cc != null) {
                            str4 = str2;
                            try {
                                c11228cc.m4593a(next, System.currentTimeMillis() - currentTimeMillis, m4534a(abstractC11237ch, next, arrayList2, str2), null);
                            } catch (IOException e3) {
                                e = e3;
                                str2 = str4;
                            }
                        } else {
                            str4 = str2;
                        }
                        str5 = str4;
                    } else if (c11228cc == null) {
                        return str2;
                    } else {
                        try {
                            c11228cc.m4594a(next, System.currentTimeMillis() - currentTimeMillis, m4534a(abstractC11237ch, next, arrayList2, str2));
                            return str2;
                        } catch (IOException e4) {
                            e = e4;
                        }
                    }
                    if (c11228cc != null) {
                        str3 = str2;
                        c11228cc.m4593a(next, System.currentTimeMillis() - currentTimeMillis, m4534a(abstractC11237ch, next, arrayList2, str2), e);
                    } else {
                        str3 = str2;
                    }
                    e.printStackTrace();
                    str5 = str3;
                }
                return str5;
            } catch (MalformedURLException e5) {
                e5.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    private static int m4534a(AbstractC11237ch abstractC11237ch, String str, List<InterfaceC11168at> list, String str2) {
        if (abstractC11237ch.m4540a() == 1) {
            return m4538a(str.length(), m4533a(str2));
        }
        if (abstractC11237ch.m4540a() == 2) {
            return m4537a(str.length(), m4532a(list), m4533a(str2));
        }
        return -1;
    }

    /* renamed from: a */
    static int m4532a(List<InterfaceC11168at> list) {
        int i = 0;
        for (InterfaceC11168at interfaceC11168at : list) {
            if (!TextUtils.isEmpty(interfaceC11168at.mo4858a())) {
                i += interfaceC11168at.mo4858a().length();
            }
            if (!TextUtils.isEmpty(interfaceC11168at.mo4857b())) {
                i += interfaceC11168at.mo4857b().length();
            }
        }
        return i * 2;
    }

    /* renamed from: a */
    static int m4533a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    /* renamed from: a */
    static int m4538a(int i, int i2) {
        return (((i2 + 243) / 1448) * 132) + 1080 + i + i2;
    }

    /* renamed from: a */
    static int m4537a(int i, int i2, int i3) {
        return (((i2 + 200) / 1448) * 132) + 1011 + i2 + i + i3;
    }
}
