package com.huawei.agconnect.config.impl;

import android.content.Context;
import android.util.Log;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.huawei.agconnect.config.impl.k */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class C4781k extends C4780j {

    /* renamed from: a */
    private final Map<String, String> f10783a;

    /* renamed from: b */
    private final Object f10784b;

    /* renamed from: c */
    private InterfaceC4777g f10785c;

    /* renamed from: d */
    private boolean f10786d;

    /* renamed from: e */
    private final String f10787e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4781k(Context context, String str) {
        super(context, str);
        this.f10783a = new HashMap();
        this.f10784b = new Object();
        this.f10786d = true;
        this.f10787e = str;
        try {
            String m15401a = m15401a("/AD91D45E3E72DB6989DDCB13287E75061FABCB933D886E6C6ABEF0939B577138");
            String m15401a2 = m15401a("/B314B3BF013DF5AC4134E880AF3D2B7C9FFBE8F0305EAC1C898145E2BCF1F21C");
            String m15401a3 = m15401a("/C767BD8FDF53E53D059BE95B09E2A71056F5F180AECC62836B287ACA5793421B");
            String m15401a4 = m15401a("/DCB3E6D4C2CF80F30D89CDBC412C964DA8381BB84668769391FBCC3E329AD0FD");
            if (m15401a == null || m15401a2 == null || m15401a3 == null || m15401a4 == null) {
                this.f10786d = false;
            } else {
                this.f10785c = new C4776f(m15401a, m15401a2, m15401a3, m15401a4);
            }
        } catch (IllegalArgumentException | NoSuchAlgorithmException | InvalidKeySpecException unused) {
            Log.e("SecurityResourcesReader", "Exception when reading the 'K&I' for 'Config'.");
            this.f10785c = null;
        }
    }

    /* renamed from: a */
    private String m15401a(String str) {
        return super.mo15400a(str, null);
    }

    @Override // com.huawei.agconnect.config.impl.C4780j, com.huawei.agconnect.config.impl.InterfaceC4774d
    /* renamed from: a */
    public String mo15400a(String str, String str2) {
        if (!this.f10786d) {
            String m15401a = m15401a(str);
            return m15401a != null ? m15401a : str2;
        } else if (this.f10785c == null) {
            Log.e("SecurityResourcesReader", "KEY is null return def directly");
            return str2;
        } else {
            synchronized (this.f10784b) {
                String str3 = this.f10783a.get(str);
                if (str3 != null) {
                    return str3;
                }
                String m15401a2 = m15401a(str);
                if (m15401a2 == null) {
                    return str2;
                }
                String mo15413a = this.f10785c.mo15413a(m15401a2, str2);
                this.f10783a.put(str, mo15413a);
                return mo15413a;
            }
        }
    }

    public String toString() {
        return "SecurityResourcesReader{mKey=, encrypt=" + this.f10786d + '}';
    }
}
