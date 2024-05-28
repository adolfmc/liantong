package com.sinovatech.unicom.separatemodule.audience.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoRingExplainEntity {
    private DataEntity data;
    private String message;
    private String statusCode;

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public DataEntity getData() {
        return this.data;
    }

    public void setData(DataEntity dataEntity) {
        this.data = dataEntity;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataEntity {
        private String agreementContent1;
        private String agreementContent2;
        private String agreementName1;
        private String agreementName2;
        private String confirmTips;
        private String duration;

        /* renamed from: id */
        private String f18477id;
        private boolean isSelected;
        private String price;
        private String productName;
        private String recommendedStatus;
        private String successTips;
        private String type;
        private String unsubscribeMethod;

        public boolean isSelected() {
            return this.isSelected;
        }

        public void setSelected(boolean z) {
            this.isSelected = z;
        }

        public String getRecommendedStatus() {
            return this.recommendedStatus;
        }

        public void setRecommendedStatus(String str) {
            this.recommendedStatus = str;
        }

        public String getDuration() {
            return this.duration;
        }

        public void setDuration(String str) {
            this.duration = str;
        }

        public String getPrice() {
            return this.price;
        }

        public void setPrice(String str) {
            this.price = str;
        }

        public String getSuccessTips() {
            return this.successTips;
        }

        public void setSuccessTips(String str) {
            this.successTips = str;
        }

        public String getProductName() {
            return this.productName;
        }

        public void setProductName(String str) {
            this.productName = str;
        }

        public String getAgreementName2() {
            return this.agreementName2;
        }

        public void setAgreementName2(String str) {
            this.agreementName2 = str;
        }

        public String getAgreementContent2() {
            return this.agreementContent2;
        }

        public void setAgreementContent2(String str) {
            this.agreementContent2 = str;
        }

        public String getConfirmTips() {
            return this.confirmTips;
        }

        public void setConfirmTips(String str) {
            this.confirmTips = str;
        }

        public String getUnsubscribeMethod() {
            return this.unsubscribeMethod;
        }

        public void setUnsubscribeMethod(String str) {
            this.unsubscribeMethod = str;
        }

        public String getAgreementName1() {
            return this.agreementName1;
        }

        public void setAgreementName1(String str) {
            this.agreementName1 = str;
        }

        public String getAgreementContent1() {
            return this.agreementContent1;
        }

        public void setAgreementContent1(String str) {
            this.agreementContent1 = str;
        }

        public String getId() {
            return this.f18477id;
        }

        public void setId(String str) {
            this.f18477id = str;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String str) {
            this.type = str;
        }
    }
}
