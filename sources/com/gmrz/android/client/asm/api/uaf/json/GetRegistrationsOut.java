package com.gmrz.android.client.asm.api.uaf.json;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class GetRegistrationsOut {
    @Expose
    public List<AppRegistration> appRegs = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class AppRegistration {
        @Expose
        public String appID;
        @Expose
        public List<String> keyIDs = new ArrayList();

        public AppRegistration() {
        }

        public AppRegistration(String str, String str2) {
            this.appID = str;
            this.keyIDs.add(str2);
        }
    }
}
