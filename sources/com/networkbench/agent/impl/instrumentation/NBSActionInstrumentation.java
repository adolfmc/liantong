package com.networkbench.agent.impl.instrumentation;

import android.view.View;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p243c.p247d.C6270b;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6638h;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSActionInstrumentation {
    private static boolean isPageSelectedEnterSuccess;

    public static void onClickEventEnter(View view, Object obj) {
        try {
            C6255f.m10816a(C6295m.EnumC6298c.Clicked, view, true);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onClickEventEnter()  has an error : " + th);
        }
    }

    public static void onClickEventExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onClickEventExit()  has an error : " + th);
        }
    }

    public static void onLongClickEventEnter(View view, Object obj) {
        try {
            C6255f.m10816a(C6295m.EnumC6298c.LongClicked, view, true);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation onLongClickEventEnter()  has an error : " + th);
        }
    }

    public static void onLongClickEventExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onLongClickEventExit()  has an error : " + th);
        }
    }

    public static void onKeyDownAction(int i, String str) {
        if (i == 4) {
            try {
                if (C6638h.m8963w().m9037ai()) {
                    C6255f.f15554c = new C6270b(C6295m.EnumC6298c.BackBtnPress.name(), "KeyEvent.KEYCODE_BACK", "", -1);
                }
            } catch (Throwable th) {
                C6396h.m10131k("NBSActionInstrumentation onKeyDownAction() has an error : " + th);
            }
        }
    }

    public static void onItemClickEnter(View view, int i, Object obj) {
        try {
            C6255f.m10815a(C6295m.EnumC6298c.ItemClicked, view, true, i);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onItemClickEnter()  has an error : " + th);
        }
    }

    public static void onItemClickExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onItemClickExit()  has an error : " + th);
        }
    }

    public static void onItemSelectedEnter(View view, int i, Object obj) {
        try {
            C6255f.m10815a(C6295m.EnumC6298c.ItemSelected, view, true, i);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onItemSelectedEnter()  has an error : " + th);
        }
    }

    public static void onItemSelectedExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onItemSelectedExit()  has an error : " + th);
        }
    }

    public static void onPageSelectedEnter(int i, Object obj) {
        try {
            if (C6255f.f15555d == null || C6255f.f15555d.f15738d) {
                isPageSelectedEnterSuccess = true;
                C6255f.m10815a(C6295m.EnumC6298c.PageSelected, (View) null, true, i);
            }
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onPageSelectedEnter()  has an error : " + th);
        }
    }

    public static void onPageSelectedExit() {
        try {
            if (isPageSelectedEnterSuccess) {
                isPageSelectedEnterSuccess = false;
                C6255f.m10801d();
            }
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onPageSelectedExit()  has an error : " + th);
        }
    }

    public static void onMenuItemClickEnter(Object obj, Object obj2) {
        try {
            C6255f.m10816a(C6295m.EnumC6298c.MenuItemClick, C6255f.m10810a(obj), true);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onMenuItemClickEnter()  has an error : " + th);
        }
    }

    public static void onMenuItemClickExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onMenuItemClickExit()  has an error : " + th);
        }
    }

    public static void onOptionsItemSelectedEnter(Object obj, Object obj2) {
        try {
            C6255f.m10816a(C6295m.EnumC6298c.OptionsItemSelected, C6255f.m10810a(obj), true);
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onOptionsItemSelectedEnter()  has an error : " + th);
        }
    }

    public static void onOptionsItemSelectedExit() {
        try {
            C6255f.m10801d();
        } catch (Throwable th) {
            C6396h.m10131k("NBSActionInstrumentation   onOptionsItemSelectedExit()  has an error : " + th);
        }
    }
}
