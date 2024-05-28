package com.networkbench.agent.impl.plugin.p272c;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6395g;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.p272c.C6547c;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.c.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6551e {

    /* renamed from: a */
    public static int f16742a = 10;

    /* renamed from: c */
    private static final String f16744c = "ping -c %d -s %d %s";

    /* renamed from: d */
    private static final String f16745d = "ping -c 1 -t %d -s %d %s";

    /* renamed from: e */
    private static final String f16746e = "(\\d+) packets transmitted, (\\d+) received[^\\n]*";

    /* renamed from: f */
    private static final String f16747f = "[fF]rom (.*\\(.*\\)|.*): .*";

    /* renamed from: g */
    private static final String f16748g = ".*\\((\\d+\\.\\d+\\.\\d+\\.\\d+).*";

    /* renamed from: h */
    private static final String f16749h = "\\d+ bytes from (\\d+\\.\\d+.\\d+.\\d+):.*";

    /* renamed from: i */
    private static final int f16750i = 30;

    /* renamed from: j */
    private static final int f16751j = 56;

    /* renamed from: k */
    private static final int f16752k = 3;

    /* renamed from: l */
    private static final String f16753l = "e";

    /* renamed from: b */
    protected static final InterfaceC6393e f16743b = new C6395g(f16753l);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.c.e$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6552a {
        /* renamed from: a */
        void mo9441a(C6547c c6547c);

        /* renamed from: a */
        void mo9440a(C6547c c6547c, String str);

        /* renamed from: b */
        void mo9439b(C6547c c6547c);
    }

    private C6551e() {
    }

    /* renamed from: a */
    public static void m9442a(List<String> list, C6547c c6547c, InterfaceC6552a interfaceC6552a) {
        if (list == null) {
            return;
        }
        for (String str : list) {
            try {
                m9444a(str, 56, c6547c, interfaceC6552a);
                if (interfaceC6552a != null) {
                    interfaceC6552a.mo9439b(c6547c);
                }
            } catch (IOException e) {
                InterfaceC6393e interfaceC6393e = f16743b;
                interfaceC6393e.mo10122a("error trace:" + e.getMessage());
                if (interfaceC6552a != null) {
                    interfaceC6552a.mo9440a(c6547c, e.getMessage());
                }
            }
        }
    }

    /* renamed from: a */
    private static void m9444a(String str, int i, C6547c c6547c, InterfaceC6552a interfaceC6552a) throws IOException {
        C6547c.C6549b c6549b = new C6547c.C6549b();
        c6547c.f16727a.add(c6549b);
        c6549b.f16738b = str;
        int i2 = 1;
        do {
            C6547c.C6548a m9445a = m9445a(str, i2, i, c6549b, true);
            f16743b.mo10122a("result ip:" + m9445a.f16729b + ", url：" + str);
            if (i2 == 1 && !TextUtils.isEmpty(c6549b.f16737a)) {
                str = c6549b.f16737a;
            }
            c6549b.f16739c.add(m9445a);
            if (interfaceC6552a != null && !TextUtils.isEmpty(c6549b.f16741e)) {
                interfaceC6552a.mo9440a(c6547c, c6549b.f16741e);
            } else if (interfaceC6552a != null) {
                interfaceC6552a.mo9441a(c6547c);
            }
            if (m9445a.f16729b != null && m9445a.f16729b.equals(c6549b.f16737a)) {
                c6549b.f16740d = true;
                return;
            }
            i2++;
        } while (i2 <= 30);
        c6549b.f16740d = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01b6 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.networkbench.agent.impl.plugin.p272c.C6547c.C6548a m9445a(java.lang.String r11, int r12, int r13, com.networkbench.agent.impl.plugin.p272c.C6547c.C6549b r14, boolean r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 641
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.plugin.p272c.C6551e.m9445a(java.lang.String, int, int, com.networkbench.agent.impl.plugin.c.c$b, boolean):com.networkbench.agent.impl.plugin.c.c$a");
    }

    /* renamed from: a */
    private static List<String> m9447a(BufferedReader bufferedReader) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
    }

    /* renamed from: a */
    private static String m9443a(List<String> list) throws Throwable {
        if (list != null) {
            Iterator<String> it = list.iterator();
            return it.hasNext() ? it.next() : "";
        }
        return "";
    }

    /* renamed from: a */
    private static void m9446a(Process process) {
        try {
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
