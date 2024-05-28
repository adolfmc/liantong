package p000;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: x */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NfcUtil {
    /* renamed from: a */
    public static boolean m20a(Context context) {
        return (context == null || ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter() == null) ? false : true;
    }

    /* renamed from: b */
    public static boolean m19b(Context context) {
        NfcManager nfcManager;
        NfcAdapter defaultAdapter;
        return (context == null || (nfcManager = (NfcManager) context.getSystemService("nfc")) == null || (defaultAdapter = nfcManager.getDefaultAdapter()) == null || !defaultAdapter.isEnabled()) ? false : true;
    }
}
