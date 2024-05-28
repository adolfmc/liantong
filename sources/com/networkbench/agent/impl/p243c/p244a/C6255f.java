package com.networkbench.agent.impl.p243c.p244a;

import android.content.Context;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.crash.C6325e;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.p243c.p247d.C6269a;
import com.networkbench.agent.impl.p243c.p247d.C6270b;
import com.networkbench.agent.impl.p243c.p248e.C6292j;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p252e.C6388z;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.util.C6638h;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.a.f */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6255f {

    /* renamed from: d */
    public static C6292j f15555d = null;

    /* renamed from: h */
    private static Method f15559h = null;

    /* renamed from: i */
    private static final String f15560i = "Touch on ";

    /* renamed from: j */
    private static final String f15561j = "Resource Id: ";

    /* renamed from: k */
    private static final String f15562k = "Initiate ";

    /* renamed from: g */
    private static final InterfaceC6393e f15558g = C6394f.m10150a();

    /* renamed from: a */
    public static String f15552a = "";

    /* renamed from: b */
    public static String f15553b = "";

    /* renamed from: c */
    public static C6270b f15554c = new C6269a();

    /* renamed from: l */
    private static Context f15563l = C6638h.m8963w().m9076K();

    /* renamed from: e */
    public static boolean f15556e = false;

    /* renamed from: f */
    public static ConcurrentLinkedQueue<C6253e> f15557f = new ConcurrentLinkedQueue<>();

    /* renamed from: m */
    private static Map<String, String> f15564m = new C6325e.C6326a();

    /* renamed from: a */
    public static String m10820a() {
        return f15553b;
    }

    /* renamed from: a */
    private static String m10811a(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() <= 0) {
            return null;
        }
        String charSequence2 = charSequence.toString();
        return charSequence2.length() > 50 ? charSequence2.substring(0, 50) : charSequence2;
    }

    /* renamed from: a */
    public static String m10818a(View view) {
        String str = null;
        if (view != null && (view instanceof LinearLayout)) {
            LinearLayout linearLayout = (LinearLayout) view;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                str = m10797g(linearLayout.getChildAt(i));
                if (str != null) {
                    return str;
                }
            }
            return str;
        }
        return null;
    }

    /* renamed from: g */
    private static String m10797g(View view) {
        if (view == null) {
            return null;
        }
        String m10811a = m10811a(view.getContentDescription());
        return (m10811a == null && (view instanceof TextView)) ? m10811a(((TextView) view).getText()) : m10811a;
    }

    /* renamed from: a */
    private static String m10817a(View view, C6295m.EnumC6298c enumC6298c) {
        if (view == null) {
            return "Initiate " + enumC6298c.toString();
        }
        String m10797g = m10797g(view);
        if (m10797g == null) {
            m10797g = m10818a(view);
        }
        if (m10797g == null) {
            String m10796h = m10796h(view);
            if (m10796h == null) {
                return "Touch on " + view.getClass().getSimpleName();
            }
            return "Resource Id: " + m10796h;
        }
        return m10797g;
    }

    /* renamed from: h */
    private static String m10796h(View view) {
        if (view == null || view.getId() == -1) {
            return null;
        }
        String str = "0x" + Integer.toHexString(view.getId());
        if (C6638h.m8963w().m9076K() != null) {
            try {
                return C6638h.m8963w().m9076K().getResources().getResourceEntryName(view.getId());
            } catch (Exception unused) {
                f15558g.mo10115e("view getViewEntryNameOrID not find");
                return str;
            }
        }
        return str;
    }

    /* renamed from: i */
    private static View m10795i(View view) {
        View m10180c;
        return (m10794j(view) == null && (m10180c = C6388z.m10180c(view)) != null) ? m10180c : view;
    }

    /* renamed from: j */
    private static Object m10794j(View view) {
        if (view == null) {
            return null;
        }
        return view.getTag(214748364);
    }

    /* renamed from: b */
    public static String m10805b(View view) {
        Object tag;
        if (view == null) {
            return null;
        }
        int id = view.getId();
        String str = "0x" + Integer.toHexString(id);
        if (view.getTag() != null) {
            return str + "#" + tag.toString();
        }
        return str;
    }

    /* renamed from: a */
    public static View m10810a(Object obj) {
        String str;
        CharSequence charSequence;
        if (obj == null || f15563l == null) {
            return null;
        }
        if (obj instanceof MenuItem) {
            str = obj.toString();
        } else {
            try {
                if (f15559h == null) {
                    f15559h = obj.getClass().getMethod("getTitle", new Class[0]);
                }
                str = (f15559h == null || (charSequence = (CharSequence) f15559h.invoke(obj, null)) == null) ? null : charSequence.toString();
            } catch (Exception unused) {
                str = null;
            }
        }
        if (str == null) {
            return null;
        }
        View view = new View(f15563l);
        NBSAppAgent.setViewId(view, str);
        return view;
    }

    /* renamed from: c */
    public static String m10802c(View view) {
        String m10796h;
        if (view == null) {
            throw new NullPointerException("getUniqueId view is null");
        }
        if (m10794j(view) == null || TextUtils.isEmpty(m10794j(view).toString())) {
            m10796h = m10796h(view);
        } else {
            m10796h = m10794j(view) == null ? null : m10794j(view).toString();
        }
        if (TextUtils.isEmpty(m10796h)) {
            m10796h = m10800d(view);
        }
        InterfaceC6393e interfaceC6393e = f15558g;
        interfaceC6393e.mo10122a("id:" + view.getId() + ", result:" + m10796h);
        return m10796h;
    }

    /* renamed from: d */
    public static String m10800d(View view) {
        if (view == null) {
            return "";
        }
        String simpleName = view.getClass().getSimpleName();
        if (view.getParent() == null || !(view.getParent() instanceof ViewGroup)) {
            return simpleName;
        }
        return m10800d((ViewGroup) view.getParent()) + "/" + simpleName;
    }

    /* renamed from: e */
    public static String m10799e(View view) {
        if (view == null) {
            return null;
        }
        String str = (String) view.getTag(214748366);
        return (str == null && view.getParent() != null && (view.getParent() instanceof ViewGroup)) ? m10799e((ViewGroup) view.getParent()) : str;
    }

    /* renamed from: a */
    public static void m10812a(NBSTraceUnit nBSTraceUnit) {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = f15555d) != null) {
            c6292j.m10583a(nBSTraceUnit);
        }
    }

    /* renamed from: b */
    public static void m10806b() {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = f15555d) != null) {
            c6292j.m10572d();
        }
    }

    /* renamed from: a */
    public static void m10809a(String str) {
        C6292j c6292j;
        if (C6638h.m8963w().m9038ah() && (c6292j = f15555d) != null) {
            c6292j.m10580a(str);
        }
    }

    /* renamed from: a */
    public static void m10813a(C6410a c6410a) {
        C6292j c6292j = f15555d;
        if (c6292j == null) {
            return;
        }
        c6292j.m10584a(c6410a);
    }

    /* renamed from: b */
    public static String m10804b(NBSTraceUnit nBSTraceUnit) {
        C6292j c6292j;
        return (C6638h.m8963w().m9038ah() && (c6292j = f15555d) != null) ? c6292j.m10575b(nBSTraceUnit) : "";
    }

    /* renamed from: a */
    public static void m10815a(C6295m.EnumC6298c enumC6298c, View view, boolean z, int i) {
        if (z) {
            Harvest.addActionAndInteraction("on" + enumC6298c.name(), m10817a(view, enumC6298c), m10805b(view));
            if (C6638h.m8963w().m9038ah()) {
                String str = null;
                if (view != null) {
                    try {
                        str = m10802c(m10795i(view));
                    } catch (Throwable unused) {
                    }
                }
                f15553b = m10793k(view) + "#" + str;
                m10807a(enumC6298c.name(), f15553b, m10798f(view), i);
                f15555d = C6292j.m10578a(f15553b, C6295m.EnumC6301f.eventAction);
            }
        }
    }

    /* renamed from: a */
    public static void m10814a(C6295m.EnumC6298c enumC6298c, String str, boolean z, int i, long j) {
        f15556e = true;
        if (z) {
            Harvest.addActionAndInteraction("on" + enumC6298c.name(), str, str);
            if (C6638h.m8963w().m9038ah()) {
                f15553b = str;
                m10807a(enumC6298c.name(), f15553b, str, i);
                if (f15557f.isEmpty()) {
                    f15557f.offer(new C6253e(j, -1L, str, true));
                }
            }
        }
    }

    /* renamed from: c */
    public static void m10803c() {
        if (f15557f.size() > 0) {
            Harvest.getInstance().getHarvestData().getNbsEventActions().m10791a(f15557f.poll());
        }
    }

    /* renamed from: a */
    public static void m10819a(long j, long j2, String str) {
        if (C6638h.m8963w().m9038ah()) {
            if (f15557f.peek().m10841a() == j && f15557f.peek().f15528c.equals(str)) {
                f15557f.peek().m10839a(j2);
            } else {
                new C6253e(j, j2, str, true);
            }
            f15553b = "";
            f15556e = false;
        }
    }

    /* renamed from: a */
    public static void m10807a(String str, String str2, String str3, int i) {
        if (C6638h.m8963w().m9037ai()) {
            f15554c = new C6270b(str, str2, str3, i);
        }
    }

    /* renamed from: a */
    public static void m10816a(C6295m.EnumC6298c enumC6298c, View view, boolean z) {
        m10815a(enumC6298c, view, z, -1);
    }

    /* renamed from: k */
    private static String m10793k(View view) {
        String m10799e = m10799e(view);
        return !TextUtils.isEmpty(m10799e) ? m10799e : f15552a;
    }

    /* renamed from: f */
    public static String m10798f(View view) {
        return (view == null || !(view instanceof TextView)) ? "" : ((TextView) view).getText().toString();
    }

    /* renamed from: d */
    public static void m10801d() {
        C6292j c6292j;
        C6293k m10573c;
        if (!C6638h.m8963w().m9038ah() || (c6292j = f15555d) == null || (m10573c = c6292j.m10573c()) == null) {
            return;
        }
        C6253e c6253e = new C6253e(m10573c.m10563b(), f15553b);
        c6253e.f15530e.putAll(f15564m);
        f15564m.clear();
        c6253e.m10836a(m10573c);
        if (Harvest.getInstance().getHarvestData() != null) {
            Harvest.getInstance().getHarvestData().getNbsEventActions().m10791a(c6253e);
        }
        f15553b = "";
    }

    /* renamed from: a */
    public static void m10808a(String str, String str2) {
        if (TextUtils.isEmpty(f15553b)) {
            return;
        }
        f15564m.put(str, str2);
    }
}
