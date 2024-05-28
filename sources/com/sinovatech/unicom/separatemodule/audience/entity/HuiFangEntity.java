package com.sinovatech.unicom.separatemodule.audience.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HuiFangEntity {
    private List<HuiFangItem> data;
    private String message;
    private String nextPageNum;
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

    public String getNextPageNum() {
        return this.nextPageNum;
    }

    public void setNextPageNum(String str) {
        this.nextPageNum = str;
    }

    public List<HuiFangItem> getData() {
        return this.data;
    }

    public void setData(List<HuiFangItem> list) {
        this.data = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class HuiFangItem {
        private String releaseTime;
        private String userId;
        private String userImg;
        private String userName;
        private String videoId;
        private String videoImg;
        private String videoLink;
        private String videoLiveRound;
        private String videoPraiseNum;
        private String videoTitle;
        private String viewNum;

        public String getVideoImg() {
            return this.videoImg;
        }

        public void setVideoImg(String str) {
            this.videoImg = str;
        }

        public String getVideoTitle() {
            return this.videoTitle;
        }

        public void setVideoTitle(String str) {
            this.videoTitle = str;
        }

        public String getVideoId() {
            return this.videoId;
        }

        public void setVideoId(String str) {
            this.videoId = str;
        }

        public String getVideoLink() {
            return this.videoLink;
        }

        public void setVideoLink(String str) {
            this.videoLink = str;
        }

        public String getViewNum() {
            return this.viewNum;
        }

        public void setViewNum(String str) {
            this.viewNum = str;
        }

        public String getVideoPraiseNum() {
            return this.videoPraiseNum;
        }

        public void setVideoPraiseNum(String str) {
            this.videoPraiseNum = str;
        }

        public String getReleaseTime() {
            return this.releaseTime;
        }

        public void setReleaseTime(String str) {
            this.releaseTime = str;
        }

        public String getVideoLiveRound() {
            return this.videoLiveRound;
        }

        public void setVideoLiveRound(String str) {
            this.videoLiveRound = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getUserImg() {
            return this.userImg;
        }

        public void setUserImg(String str) {
            this.userImg = str;
        }
    }
}
