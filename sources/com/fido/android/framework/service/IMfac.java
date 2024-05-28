package com.fido.android.framework.service;

import android.content.Context;
import com.gmrz.android.client.asm.api.AsmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IMfac {
    String[] getAllowedSSLProtocols();

    Context getContext();

    boolean isEmbedded();

    boolean isEnabled();

    void showEulaDialog() throws AsmException;

    boolean useAIDLService();

    boolean usePreloadedOnly();

    boolean useSafetyNet();
}
