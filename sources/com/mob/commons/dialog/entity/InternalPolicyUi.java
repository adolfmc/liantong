package com.mob.commons.dialog.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class InternalPolicyUi extends BaseEntity {
    private String contentText;
    private String negativeBtnText;
    private String positiveBtnText;
    private String titleText;

    private InternalPolicyUi(Builder builder) {
        this.titleText = builder.titleText;
        this.contentText = builder.contentText;
        this.positiveBtnText = builder.positiveBtnText;
        this.negativeBtnText = builder.negativeBtnText;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public String getContentText() {
        return this.contentText;
    }

    public String getPositiveBtnText() {
        return this.positiveBtnText;
    }

    public String getNegativeBtnText() {
        return this.negativeBtnText;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class Builder extends BaseEntity {
        private String contentText;
        private String negativeBtnText;
        private String positiveBtnText;
        private String titleText;

        public InternalPolicyUi build() {
            return new InternalPolicyUi(this);
        }

        public Builder setTitleText(String str) {
            this.titleText = str;
            return this;
        }

        public Builder setContentText(String str) {
            this.contentText = str;
            return this;
        }

        public Builder setPositiveBtnText(String str) {
            this.positiveBtnText = str;
            return this;
        }

        public Builder setNegativeBtnText(String str) {
            this.negativeBtnText = str;
            return this;
        }
    }
}
