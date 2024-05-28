package com.p281qq.p282e.ads.rewardvideo;

import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.ads.rewardvideo.ServerSideVerificationOptions */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ServerSideVerificationOptions {
    public static final String TRANS_ID = "transId";

    /* renamed from: a */
    private String f17873a;

    /* renamed from: b */
    private String f17874b;

    /* renamed from: c */
    private final JSONObject f17875c;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.qq.e.ads.rewardvideo.ServerSideVerificationOptions$Builder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Builder {

        /* renamed from: a */
        private String f17876a;

        /* renamed from: b */
        private String f17877b;

        public ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this);
        }

        public Builder setCustomData(String str) {
            this.f17876a = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.f17877b = str;
            return this;
        }
    }

    private ServerSideVerificationOptions(Builder builder) {
        this.f17875c = new JSONObject();
        this.f17873a = builder.f17876a;
        this.f17874b = builder.f17877b;
    }

    public String getCustomData() {
        return this.f17873a;
    }

    public JSONObject getOptions() {
        return this.f17875c;
    }

    public String getUserId() {
        return this.f17874b;
    }
}
