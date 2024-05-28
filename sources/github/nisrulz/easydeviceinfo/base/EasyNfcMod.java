package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.nfc.NfcAdapter;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyNfcMod {
    private NfcAdapter nfcAdapter;

    public EasyNfcMod(Context context) {
        this.nfcAdapter = null;
        this.nfcAdapter = NfcAdapter.getDefaultAdapter(context);
    }

    public final boolean isNfcPresent() {
        return this.nfcAdapter != null;
    }

    public final boolean isNfcEnabled() {
        NfcAdapter nfcAdapter = this.nfcAdapter;
        return nfcAdapter != null && nfcAdapter.isEnabled();
    }
}
