package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.annotations.Expose;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AuthenticateIn {
    @Expose
    public String appID;
    @Expose
    public String finalChallenge;
    @Expose
    public List<String> keyIDs;
    @Expose
    public List<Transaction> transaction;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Transaction {
        @Expose
        public String content;
        @Expose
        public String contentType;
        @Expose
        public DisplayPNGCharacteristicsDescriptor tcDisplayPNGCharacteristics;
    }
}
