package com.sinovatech.unicom.separatemodule.audience.entity;

import android.text.TextUtils;
import com.sinovatech.unicom.common.URLSet;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZhuboDataEntity {
    private AnchorInfoBean anchorInfoBean;
    private String liveRoomNotice;
    private String message;
    private String statusCode;

    public String getLiveRoomNotice() {
        return this.liveRoomNotice;
    }

    public void setLiveRoomNotice(String str) {
        this.liveRoomNotice = str;
    }

    public AnchorInfoBean getAnchorInfoBean() {
        if (this.anchorInfoBean == null) {
            this.anchorInfoBean = new AnchorInfoBean();
        }
        return this.anchorInfoBean;
    }

    public void setAnchorInfoBean(AnchorInfoBean anchorInfoBean) {
        this.anchorInfoBean = anchorInfoBean;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class AnchorInfoBean {
        private String bornDate;
        private String businessId;
        private String businessName;
        private String currentUserIsLeader;
        private String dataType;
        private String fansNum;
        private String followType;
        private String forcePassword;
        private String gender;
        private String isNeedCheck;
        private String jumpLiveAddress;
        private String liveCoverImg;
        private String liveFreePullUrl;
        private String liveFreePullUrlByFlv;
        private String liveHistoryUrl;
        private String liveIndexUrl;
        private String liveNotice;
        private String livePullUrl;
        private String livePullUrlByFlv;
        private String livePvUrl;
        private String liveRoom;
        private String liveRound;
        private String liveStatus;
        private String liveTitle;
        private String living;
        private String passwordCheckNum;
        private String receiveGiftNum;
        private String shareContentDesc;
        private String showType;
        private String signature;
        private String userCity;
        private String userCityName;
        private String userId;
        private String userImg;
        private String userIndexUrl;
        private String userJumpHallUrl;
        private String userName;
        private String userPraiseNum;
        private String userProvince;
        private String userType;
        private String userTypeParse;
        private String whetherOpenPlayBack;
        private String whetherPrevue;

        public String getJumpLiveAddress() {
            return this.jumpLiveAddress;
        }

        public void setJumpLiveAddress(String str) {
            this.jumpLiveAddress = str;
        }

        public String getWhetherPrevue() {
            return this.whetherPrevue;
        }

        public void setWhetherPrevue(String str) {
            this.whetherPrevue = str;
        }

        public String getWhetherOpenPlayBack() {
            return this.whetherOpenPlayBack;
        }

        public void setWhetherOpenPlayBack(String str) {
            this.whetherOpenPlayBack = str;
        }

        public String getLiving() {
            return this.living;
        }

        public void setLiving(String str) {
            this.living = str;
        }

        public String getShareContentDesc() {
            return this.shareContentDesc;
        }

        public void setShareContentDesc(String str) {
            this.shareContentDesc = str;
        }

        public String getDataType() {
            if (this.dataType == null) {
                this.dataType = "1";
            }
            return this.dataType;
        }

        public void setDataType(String str) {
            this.dataType = str;
        }

        public String getLivePvUrl() {
            return this.livePvUrl;
        }

        public void setLivePvUrl(String str) {
            this.livePvUrl = str;
        }

        public String getBornDate() {
            return this.bornDate;
        }

        public void setBornDate(String str) {
            this.bornDate = str;
        }

        public String getBusinessId() {
            return this.businessId;
        }

        public void setBusinessId(String str) {
            this.businessId = str;
        }

        public String getBusinessName() {
            return this.businessName;
        }

        public void setBusinessName(String str) {
            this.businessName = str;
        }

        public String getCurrentUserIsLeader() {
            return this.currentUserIsLeader;
        }

        public void setCurrentUserIsLeader(String str) {
            this.currentUserIsLeader = str;
        }

        public String getFansNum() {
            return this.fansNum;
        }

        public void setFansNum(String str) {
            this.fansNum = str;
        }

        public String getFollowType() {
            return this.followType;
        }

        public void setFollowType(String str) {
            this.followType = str;
        }

        public String getGender() {
            return this.gender;
        }

        public void setGender(String str) {
            this.gender = str;
        }

        public String getLiveCoverImg() {
            return this.liveCoverImg;
        }

        public void setLiveCoverImg(String str) {
            this.liveCoverImg = str;
        }

        public String getLiveFreePullUrl() {
            return this.liveFreePullUrl;
        }

        public void setLiveFreePullUrl(String str) {
            this.liveFreePullUrl = str;
        }

        public String getLivePullUrl() {
            return this.livePullUrl;
        }

        public void setLivePullUrl(String str) {
            this.livePullUrl = str;
        }

        public String getLiveRoom() {
            return this.liveRoom;
        }

        public void setLiveRoom(String str) {
            this.liveRoom = str;
        }

        public String getLiveRound() {
            return this.liveRound;
        }

        public void setLiveRound(String str) {
            this.liveRound = str;
        }

        public String getLiveStatus() {
            return this.liveStatus;
        }

        public void setLiveStatus(String str) {
            this.liveStatus = str;
        }

        public String getLiveTitle() {
            return this.liveTitle;
        }

        public void setLiveTitle(String str) {
            this.liveTitle = str;
        }

        public String getReceiveGiftNum() {
            return this.receiveGiftNum;
        }

        public void setReceiveGiftNum(String str) {
            this.receiveGiftNum = str;
        }

        public String getShowType() {
            return this.showType;
        }

        public void setShowType(String str) {
            this.showType = str;
        }

        public String getSignature() {
            return this.signature;
        }

        public void setSignature(String str) {
            this.signature = str;
        }

        public String getUserCity() {
            return this.userCity;
        }

        public void setUserCity(String str) {
            this.userCity = str;
        }

        public String getUserCityName() {
            return this.userCityName;
        }

        public void setUserCityName(String str) {
            this.userCityName = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserImg() {
            return this.userImg;
        }

        public void setUserImg(String str) {
            this.userImg = str;
        }

        public String getUserJumpHallUrl() {
            return this.userJumpHallUrl;
        }

        public void setUserJumpHallUrl(String str) {
            this.userJumpHallUrl = str;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getUserPraiseNum() {
            return this.userPraiseNum;
        }

        public void setUserPraiseNum(String str) {
            this.userPraiseNum = str;
        }

        public String getUserProvince() {
            return this.userProvince;
        }

        public void setUserProvince(String str) {
            this.userProvince = str;
        }

        public String getUserType() {
            return this.userType;
        }

        public void setUserType(String str) {
            this.userType = str;
        }

        public String getUserTypeParse() {
            return this.userTypeParse;
        }

        public void setUserTypeParse(String str) {
            this.userTypeParse = str;
        }

        public String getLiveFreePullUrlByFlv() {
            return this.liveFreePullUrlByFlv;
        }

        public void setLiveFreePullUrlByFlv(String str) {
            this.liveFreePullUrlByFlv = str;
        }

        public String getLivePullUrlByFlv() {
            return this.livePullUrlByFlv;
        }

        public void setLivePullUrlByFlv(String str) {
            this.livePullUrlByFlv = str;
        }

        public String getUserIndexUrl() {
            return this.userIndexUrl;
        }

        public void setUserIndexUrl(String str) {
            this.userIndexUrl = str;
        }

        public String getLiveNotice() {
            return this.liveNotice;
        }

        public void setLiveNotice(String str) {
            this.liveNotice = str;
        }

        public String getLiveIndexUrl() {
            if (TextUtils.isEmpty(this.liveIndexUrl)) {
                this.liveIndexUrl = URLSet.getZhiboLiebiaoUrl();
            }
            return this.liveIndexUrl;
        }

        public void setLiveIndexUrl(String str) {
            this.liveIndexUrl = str;
        }

        public String getLiveHistoryUrl(String str) {
            if (TextUtils.isEmpty(this.liveHistoryUrl)) {
                this.liveHistoryUrl = URLSet.getZhiboHuifangUrl(str);
            }
            return this.liveHistoryUrl;
        }

        public void setLiveHistoryUrl(String str) {
            this.liveHistoryUrl = str;
        }

        public void setForcePassword(String str) {
            this.forcePassword = str;
        }

        public String getForcePassword() {
            return this.forcePassword;
        }

        public String getIsNeedCheck() {
            return this.isNeedCheck;
        }

        public void setIsNeedCheck(String str) {
            this.isNeedCheck = str;
        }

        public String getPasswordCheckNum() {
            return this.passwordCheckNum;
        }

        public void setPasswordCheckNum(String str) {
            this.passwordCheckNum = str;
        }
    }
}
