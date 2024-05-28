package com.gmrz.fido.offline;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.gmrz.appsdk.util.Logger;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;

/* renamed from: com.gmrz.fido.offline.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class StrongBox {

    /* renamed from: a */
    private final KeyStoreHelper f10368a;

    /* renamed from: b */
    private InterfaceC4438a f10369b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: StrongBox.java */
    /* renamed from: com.gmrz.fido.offline.l$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4438a {
        /* renamed from: a */
        void mo15712a(Exception exc);

        /* renamed from: a */
        void mo15711a(String str);

        /* renamed from: a */
        void mo15710a(String str, String str2);
    }

    public StrongBox(String str) {
        this.f10368a = KeyStoreHelper.m15716a(str);
    }

    /* renamed from: a */
    public void m15715a() {
        Logger.m15756i("StrongBox", String.format("aes secret key generate complete: %s", Boolean.valueOf(this.f10368a.m15718a())));
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public void m15714a(int i, String str, String str2) {
        if (this.f10369b == null) {
            Logger.wtf("StrongBox", "need set callback to return process result");
            return;
        }
        try {
            if (this.f10368a != null) {
                if (i != 1 && i != 2) {
                    throw new Exception("purpose is illegal");
                }
                if (TextUtils.isEmpty(str)) {
                    throw new Exception("data source is null");
                }
                if (i == 1) {
                    Cipher m15717a = this.f10368a.m15717a(1, null);
                    this.f10369b.mo15710a(Converter.m15724a(m15717a.doFinal(str.getBytes(StandardCharsets.UTF_8))), Converter.m15724a(m15717a.getIV()));
                    return;
                } else if (i != 2) {
                    return;
                } else {
                    this.f10369b.mo15711a(new String(this.f10368a.m15717a(2, Converter.m15725a(str2)).doFinal(Converter.m15725a(str))));
                    return;
                }
            }
            throw new Exception("keystore instance is null");
        } catch (Exception e) {
            this.f10369b.mo15712a(e);
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m15713a(InterfaceC4438a interfaceC4438a) {
        this.f10369b = interfaceC4438a;
    }
}
