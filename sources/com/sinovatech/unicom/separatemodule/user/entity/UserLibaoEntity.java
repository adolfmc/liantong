package com.sinovatech.unicom.separatemodule.user.entity;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserLibaoEntity {
    private String respCode;
    private String respDesc;
    private ResultDTO result;
    private String success;

    public ResultDTO getResult() {
        return this.result;
    }

    public void setResult(ResultDTO resultDTO) {
        this.result = resultDTO;
    }

    public String getRespDesc() {
        return this.respDesc;
    }

    public void setRespDesc(String str) {
        this.respDesc = str;
    }

    public String getSuccess() {
        return this.success;
    }

    public void setSuccess(String str) {
        this.success = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ResultDTO {
        private String allGiftIconUrl;
        private String allGiftNewly;
        private String allGiftTitle;
        private String allGiftUnit;
        private String allGiftUrl;
        private String myGiftFloorSwitch;
        private String telVoucherData;
        private String telVoucherNewly;
        private String telVoucherTitle;
        private String telVoucherUnit;
        private String telVoucherUrl;
        private String title;
        private String trafficPacketData;
        private String trafficPacketNewly;
        private String trafficPacketTitle;
        private String trafficPacketUnit;
        private String trafficPacketUrl;
        private String voicePackageData;
        private String voicePackageNewly;
        private String voicePackageTitle;
        private String voicePackageUnit;
        private String voicePackageUrl;

        public String getAllGiftIconUrl() {
            return this.allGiftIconUrl;
        }

        public void setAllGiftIconUrl(String str) {
            this.allGiftIconUrl = str;
        }

        public String getAllGiftNewly() {
            return this.allGiftNewly;
        }

        public void setAllGiftNewly(String str) {
            this.allGiftNewly = str;
        }

        public String getAllGiftTitle() {
            return this.allGiftTitle;
        }

        public void setAllGiftTitle(String str) {
            this.allGiftTitle = str;
        }

        public String getAllGiftUnit() {
            return this.allGiftUnit;
        }

        public void setAllGiftUnit(String str) {
            this.allGiftUnit = str;
        }

        public String getAllGiftUrl() {
            return this.allGiftUrl;
        }

        public void setAllGiftUrl(String str) {
            this.allGiftUrl = str;
        }

        public String getMyGiftFloorSwitch() {
            return this.myGiftFloorSwitch;
        }

        public void setMyGiftFloorSwitch(String str) {
            this.myGiftFloorSwitch = str;
        }

        public String getTelVoucherData() {
            return this.telVoucherData;
        }

        public void setTelVoucherData(String str) {
            this.telVoucherData = str;
        }

        public String getTelVoucherNewly() {
            return this.telVoucherNewly;
        }

        public void setTelVoucherNewly(String str) {
            this.telVoucherNewly = str;
        }

        public String getTelVoucherTitle() {
            return this.telVoucherTitle;
        }

        public void setTelVoucherTitle(String str) {
            this.telVoucherTitle = str;
        }

        public String getTelVoucherUnit() {
            return this.telVoucherUnit;
        }

        public void setTelVoucherUnit(String str) {
            this.telVoucherUnit = str;
        }

        public String getTelVoucherUrl() {
            return this.telVoucherUrl;
        }

        public void setTelVoucherUrl(String str) {
            this.telVoucherUrl = str;
        }

        public String getTitle() {
            if (TextUtils.isEmpty(this.title)) {
                this.title = "我的礼包";
            }
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getTrafficPacketData() {
            return this.trafficPacketData;
        }

        public void setTrafficPacketData(String str) {
            this.trafficPacketData = str;
        }

        public String getTrafficPacketNewly() {
            return this.trafficPacketNewly;
        }

        public void setTrafficPacketNewly(String str) {
            this.trafficPacketNewly = str;
        }

        public String getTrafficPacketTitle() {
            return this.trafficPacketTitle;
        }

        public void setTrafficPacketTitle(String str) {
            this.trafficPacketTitle = str;
        }

        public String getTrafficPacketUnit() {
            return this.trafficPacketUnit;
        }

        public void setTrafficPacketUnit(String str) {
            this.trafficPacketUnit = str;
        }

        public String getTrafficPacketUrl() {
            return this.trafficPacketUrl;
        }

        public void setTrafficPacketUrl(String str) {
            this.trafficPacketUrl = str;
        }

        public String getVoicePackageData() {
            return this.voicePackageData;
        }

        public void setVoicePackageData(String str) {
            this.voicePackageData = str;
        }

        public String getVoicePackageNewly() {
            return this.voicePackageNewly;
        }

        public void setVoicePackageNewly(String str) {
            this.voicePackageNewly = str;
        }

        public String getVoicePackageTitle() {
            return this.voicePackageTitle;
        }

        public void setVoicePackageTitle(String str) {
            this.voicePackageTitle = str;
        }

        public String getVoicePackageUnit() {
            return this.voicePackageUnit;
        }

        public void setVoicePackageUnit(String str) {
            this.voicePackageUnit = str;
        }

        public String getVoicePackageUrl() {
            return this.voicePackageUrl;
        }

        public void setVoicePackageUrl(String str) {
            this.voicePackageUrl = str;
        }
    }
}
