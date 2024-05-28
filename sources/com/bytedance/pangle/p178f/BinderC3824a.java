package com.bytedance.pangle.p178f;

import com.bytedance.pangle.InterfaceC3780c;
import com.bytedance.pangle.plugin.PluginManager;
import java.io.File;

/* renamed from: com.bytedance.pangle.f.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BinderC3824a extends InterfaceC3780c.AbstractBinderC3781a {

    /* renamed from: a */
    private static volatile BinderC3824a f9148a;

    /* renamed from: b */
    public static BinderC3824a m16876b() {
        if (f9148a == null) {
            synchronized (BinderC3824a.class) {
                if (f9148a == null) {
                    f9148a = new BinderC3824a();
                }
            }
        }
        return f9148a;
    }

    @Override // com.bytedance.pangle.InterfaceC3780c
    /* renamed from: a */
    public final boolean mo16878a(String str) {
        return PluginManager.getInstance().checkPluginInstalled(str);
    }

    @Override // com.bytedance.pangle.InterfaceC3780c
    /* renamed from: b */
    public final int mo16874b(String str) {
        return PluginManager.getInstance().getPlugin(str).getVersion();
    }

    @Override // com.bytedance.pangle.InterfaceC3780c
    /* renamed from: a */
    public final boolean mo16877a(String str, String str2) {
        return PluginManager.getInstance().syncInstall(str, new File(str2));
    }
}
