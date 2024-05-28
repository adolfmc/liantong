package com.networkbench.agent.impl.p241b;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.j */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6240j<String> extends ArrayList<String> {

    /* renamed from: a */
    public final int f15455a = 10;

    /* renamed from: a */
    public void m10903a(String string) {
        if (string == null || string == "") {
            return;
        }
        if (size() > 10) {
            remove(0);
        }
        add(string);
    }
}
