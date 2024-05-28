package com.networkbench.agent.impl.harvest.p261b;

import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6459b {

    /* renamed from: a */
    private static int f16328a = -1;

    /* renamed from: a */
    public static void m9941a() {
        m9939b();
        if (C6638h.m8963w().m9009f() != 0) {
            C6638h.m8963w().m9012e(C6458a.m9943b());
        } else {
            m9938b(1);
        }
    }

    /* renamed from: a */
    public static void m9940a(int i) {
        C6396h.m10125q("首次启动设置opt , setModuleSwitch :" + i);
        f16328a = i;
    }

    /* renamed from: b */
    public static void m9939b() {
        C6638h.m8963w().m9021c(C6458a.m9945a(C6458a.f16323b));
        C6638h.m8963w().m9026b(C6458a.m9945a(C6458a.f16324c));
        C6638h.m8963w().m9059a(C6458a.m9945a(C6458a.f16322a));
        C6638h.m8963w().m9016d(C6458a.m9945a(C6458a.f16325d));
    }

    /* renamed from: b */
    public static void m9938b(int i) {
        C6458a.m9944a(C6458a.f16325d, i);
    }

    /* renamed from: c */
    public static boolean m9937c() {
        C6396h.m10125q("            ");
        C6396h.m10125q("checkSwitchOfError ----------");
        if (C6638h.m8963w().m9009f() == 0) {
            C6396h.m10125q("自定义错误 判断,上次init成功..根据下发开关, 采集数据 ....");
            return C6638h.m8963w().m9045aa();
        } else if (C6638h.m8963w().m9009f() == 2) {
            if (f16328a == -1) {
                return C6638h.m8963w().m9013e();
            }
            return C6638h.m8963w().m9013e() && (f16328a & 128) != 0;
        } else {
            int m9005g = C6638h.m8963w().m9005g();
            C6396h.m10125q("自定义错误判断,上次没有init成功...开关受历史开关 和 崩溃文件开关控制....");
            return (m9005g & 128) != 0 && C6638h.m8963w().m9013e();
        }
    }

    /* renamed from: d */
    public static boolean m9936d() {
        C6396h.m10125q("            ");
        C6396h.m10125q("checkSwitchOfCrash ----------");
        if (C6638h.m8963w().m9009f() == 0) {
            C6396h.m10125q("崩溃采集 判断,上次init成功..根据下发开关, 采集数据 ....");
            return C6638h.m8963w().m9042ad();
        } else if (C6638h.m8963w().m9009f() == 2) {
            if (f16328a == -1) {
                return C6638h.m8963w().m9022c();
            }
            return C6638h.m8963w().m9022c() && (f16328a & 4) != 0;
        } else {
            int m9005g = C6638h.m8963w().m9005g();
            C6396h.m10125q("自定义事件判断,上次没有init成功...开关受历史开关 和 崩溃文件开关控制....");
            return (m9005g & 4) != 0 && C6638h.m8963w().m9022c();
        }
    }

    /* renamed from: e */
    public static boolean m9935e() {
        C6396h.m10125q("            ");
        C6396h.m10125q("checkSwitchOfAction ----------");
        if (C6638h.m8963w().m9009f() == 0) {
            C6396h.m10125q("自定义事件判断,上次init成功..根据下发开关, 采集数据 ....");
            return C6638h.m8963w().m9045aa();
        } else if (C6638h.m8963w().m9009f() == 2) {
            C6396h.m10125q("按照首次启动方式开启开关...");
            if (f16328a != -1) {
                return C6638h.m8963w().m9017d() && (f16328a & 128) != 0;
            }
            C6396h.m10125q("setStartOption 没有被调用过...只判断tag值...");
            return C6638h.m8963w().m9017d();
        } else {
            int m9005g = C6638h.m8963w().m9005g();
            C6396h.m10125q("oldFeature : " + m9005g);
            C6396h.m10125q("自定义事件判断,上次没有init成功...开关受历史开关 和 崩溃文件开关控制....");
            return (m9005g & 128) != 0 && C6638h.m8963w().m9017d();
        }
    }

    /* renamed from: f */
    public static boolean m9934f() {
        C6396h.m10125q("            ");
        C6396h.m10125q("checkSwitchOfWebView ----------");
        if (C6638h.m8963w().m9009f() == 0) {
            C6396h.m10125q("自定义事件判断,上次init成功..根据下发开关, 采集数据 ....");
            return C6638h.m8963w().m9040af();
        } else if (C6638h.m8963w().m9009f() == 2) {
            C6396h.m10125q("按照首次启动方式开启开关...");
            int i = f16328a;
            if (i != -1) {
                return (i & 8) != 0;
            }
            C6396h.m10125q("setStartOption 没有被调用过...默认开启 WebView");
            return true;
        } else {
            int m9005g = C6638h.m8963w().m9005g();
            C6396h.m10125q("oldFeature : " + m9005g);
            C6396h.m10125q("自定义事件判断,上次没有init成功...开关受历史开关 和 崩溃文件开关控制....");
            return (m9005g & 8) != 0;
        }
    }
}
