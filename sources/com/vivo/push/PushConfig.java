package com.vivo.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PushConfig {
    private boolean mAgreePrivacyStatement;
    private boolean mOpenMultiUser;

    public boolean isAgreePrivacyStatement() {
        return this.mAgreePrivacyStatement;
    }

    public boolean isOpenMultiUser() {
        return this.mOpenMultiUser;
    }

    private PushConfig(Builder builder) {
        this.mAgreePrivacyStatement = true;
        this.mOpenMultiUser = false;
        this.mAgreePrivacyStatement = builder.mAgreePrivacyStatement;
        this.mOpenMultiUser = builder.mOpenMultiUser;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Builder {
        private boolean mAgreePrivacyStatement = true;
        private boolean mOpenMultiUser = false;

        public final Builder agreePrivacyStatement(boolean z) {
            this.mAgreePrivacyStatement = z;
            return this;
        }

        public final Builder openMultiUserMode(boolean z) {
            this.mOpenMultiUser = z;
            return this;
        }

        public final PushConfig build() {
            return new PushConfig(this);
        }
    }
}
