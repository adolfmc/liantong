package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SignInHistoryEntity {
    private String respCode;
    private List<HistoryEntity> signInHistoryList;

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public List<HistoryEntity> getSignInHistoryList() {
        return this.signInHistoryList;
    }

    public void setSignInHistoryList(List<HistoryEntity> list) {
        this.signInHistoryList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class HistoryEntity {
        private int dayIndex;
        private String igmUrl;
        private String rewardInfo;
        private String rewardType;
        private String rewardVal;
        private String signState;
        private String userId;

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getRewardType() {
            return this.rewardType;
        }

        public void setRewardType(String str) {
            this.rewardType = str;
        }

        public String getRewardInfo() {
            return this.rewardInfo;
        }

        public void setRewardInfo(String str) {
            this.rewardInfo = str;
        }

        public String getSignState() {
            return this.signState;
        }

        public void setSignState(String str) {
            this.signState = str;
        }

        public String getIgmUrl() {
            return this.igmUrl;
        }

        public void setIgmUrl(String str) {
            this.igmUrl = str;
        }

        public int getDayIndex() {
            return this.dayIndex;
        }

        public void setDayIndex(int i) {
            this.dayIndex = i;
        }

        public String getRewardVal() {
            return this.rewardVal;
        }

        public void setRewardVal(String str) {
            this.rewardVal = str;
        }
    }
}
