package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveOrFengGuangInfoEntity {
    private List<LiveInfoItem> data;
    private String message;
    private String nextPageNum;
    private String statusCode;

    public List<LiveInfoItem> getData() {
        return this.data;
    }

    public void setData(List<LiveInfoItem> list) {
        this.data = list;
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

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class LiveInfoItem {
        private String moreViewAngle;
        private String userHeadImg;
        private String userId;
        private String userLinkUrl;
        private String userNickName;
        private String videoBgImg;
        private String videoCloseState;
        private String videoId;
        private String videoLinkUrl;
        private String videoNum;
        private String videoTime;
        private String videoTitle;
        private String videoType;

        public String getMoreViewAngle() {
            return this.moreViewAngle;
        }

        public void setMoreViewAngle(String str) {
            this.moreViewAngle = str;
        }

        public String getVideoType() {
            return this.videoType;
        }

        public void setVideoType(String str) {
            this.videoType = str;
        }

        public String getUserLinkUrl() {
            return this.userLinkUrl;
        }

        public void setUserLinkUrl(String str) {
            this.userLinkUrl = str;
        }

        public String getVideoId() {
            return this.videoId;
        }

        public void setVideoId(String str) {
            this.videoId = str;
        }

        public String getVideoTitle() {
            return this.videoTitle;
        }

        public void setVideoTitle(String str) {
            this.videoTitle = str;
        }

        public String getVideoBgImg() {
            return this.videoBgImg;
        }

        public void setVideoBgImg(String str) {
            this.videoBgImg = str;
        }

        public String getVideoCloseState() {
            return this.videoCloseState;
        }

        public void setVideoCloseState(String str) {
            this.videoCloseState = str;
        }

        public String getVideoLinkUrl() {
            return this.videoLinkUrl;
        }

        public void setVideoLinkUrl(String str) {
            this.videoLinkUrl = str;
        }

        public String getVideoTime() {
            return this.videoTime;
        }

        public void setVideoTime(String str) {
            this.videoTime = str;
        }

        public String getVideoNum() {
            return this.videoNum;
        }

        public void setVideoNum(String str) {
            this.videoNum = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserNickName() {
            return this.userNickName;
        }

        public void setUserNickName(String str) {
            this.userNickName = str;
        }

        public String getUserHeadImg() {
            return this.userHeadImg;
        }

        public void setUserHeadImg(String str) {
            this.userHeadImg = str;
        }
    }
}
