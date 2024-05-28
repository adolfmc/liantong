package com.gmrz.asm.p198fp.authui.params;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Handler;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.authui.params.FpParameter */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpParameter {
    private boolean isTransaction;
    private FingerprintManager.CryptoObject mCryptoObject;
    private Handler mResultHandler;
    private int maxMiss;
    private String title;
    private String transaction;

    public static FpParameter Builder() {
        return new FpParameter();
    }

    public boolean isTransaction() {
        return this.isTransaction;
    }

    public FpParameter setTransaction(boolean z) {
        this.isTransaction = z;
        return this;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public FpParameter setTransaction(String str) {
        this.transaction = str;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public FpParameter setTitle(String str) {
        this.title = str;
        return this;
    }

    public Handler getResultHandler() {
        return this.mResultHandler;
    }

    public FpParameter setResultHandler(Handler handler) {
        this.mResultHandler = handler;
        return this;
    }

    public FingerprintManager.CryptoObject getCryptoObject() {
        return this.mCryptoObject;
    }

    public FpParameter setCryptoObject(FingerprintManager.CryptoObject cryptoObject) {
        this.mCryptoObject = cryptoObject;
        return this;
    }

    public int getMaxMiss() {
        return this.maxMiss;
    }

    public void setMaxMiss(int i) {
        this.maxMiss = i;
    }
}
