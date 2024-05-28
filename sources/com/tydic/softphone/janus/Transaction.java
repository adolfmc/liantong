package com.tydic.softphone.janus;

import java.math.BigInteger;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Transaction {
    private BigInteger feedId;
    private String tid;

    public void onError() {
    }

    public void onSuccess(JSONObject jSONObject) throws Exception {
    }

    public void onSuccess(JSONObject jSONObject, BigInteger bigInteger) throws Exception {
    }

    public Transaction(String str) {
        this.tid = str;
    }

    public Transaction(String str, BigInteger bigInteger) {
        this.tid = str;
        this.feedId = bigInteger;
    }

    public String getTid() {
        return this.tid;
    }

    public BigInteger getFeedId() {
        return this.feedId;
    }

    public void setFeedId(BigInteger bigInteger) {
        this.feedId = bigInteger;
    }
}
