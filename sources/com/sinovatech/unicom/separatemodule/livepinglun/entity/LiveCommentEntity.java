package com.sinovatech.unicom.separatemodule.livepinglun.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveCommentEntity {
    private String code;
    private List<CommentListBean> commentList;
    private Integer commentTotleCount;
    private String desc;

    public List<CommentListBean> getCommentList() {
        return this.commentList;
    }

    public void setCommentList(List<CommentListBean> list) {
        this.commentList = list;
    }

    public Integer getCommentTotleCount() {
        return this.commentTotleCount;
    }

    public void setCommentTotleCount(Integer num) {
        this.commentTotleCount = num;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class CommentListBean {
        private String checkFlag;
        private String cityName;
        private String commentContent;
        private String commentTime;
        private String goodFlag;
        private String headPhoto;
        private String highQualityFlag;

        /* renamed from: id */
        private String f18551id;
        private String is5GUser;
        private String isDelete;
        private String isPLUSUser;
        private String isSVUser;
        private String memberlev;
        private String newsId;
        private String nickName;
        private Integer pageGoodNum;
        private String productImg;
        private String provinceName;
        private Integer replyNum;
        private String topFlag;
        private String uploadImg;
        private String userNickReal;
        private String userStar;

        public String getId() {
            return this.f18551id;
        }

        public void setId(String str) {
            this.f18551id = str;
        }

        public String getProductImg() {
            return this.productImg;
        }

        public void setProductImg(String str) {
            this.productImg = str;
        }

        public String getHighQualityFlag() {
            return this.highQualityFlag;
        }

        public void setHighQualityFlag(String str) {
            this.highQualityFlag = str;
        }

        public String getMemberlev() {
            return this.memberlev;
        }

        public void setMemberlev(String str) {
            this.memberlev = str;
        }

        public String getGoodFlag() {
            return this.goodFlag;
        }

        public void setGoodFlag(String str) {
            this.goodFlag = str;
        }

        public String getCommentContent() {
            return this.commentContent;
        }

        public void setCommentContent(String str) {
            this.commentContent = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getIsPLUSUser() {
            return this.isPLUSUser;
        }

        public void setIsPLUSUser(String str) {
            this.isPLUSUser = str;
        }

        public String getUserNickReal() {
            return this.userNickReal;
        }

        public void setUserNickReal(String str) {
            this.userNickReal = str;
        }

        public String getTopFlag() {
            return this.topFlag;
        }

        public void setTopFlag(String str) {
            this.topFlag = str;
        }

        public String getCommentTime() {
            return this.commentTime;
        }

        public void setCommentTime(String str) {
            this.commentTime = str;
        }

        public String getUserStar() {
            return this.userStar;
        }

        public void setUserStar(String str) {
            this.userStar = str;
        }

        public String getIs5GUser() {
            return this.is5GUser;
        }

        public void setIs5GUser(String str) {
            this.is5GUser = str;
        }

        public String getCityName() {
            return this.cityName;
        }

        public void setCityName(String str) {
            this.cityName = str;
        }

        public String getIsSVUser() {
            return this.isSVUser;
        }

        public void setIsSVUser(String str) {
            this.isSVUser = str;
        }

        public String getNewsId() {
            return this.newsId;
        }

        public void setNewsId(String str) {
            this.newsId = str;
        }

        public String getCheckFlag() {
            return this.checkFlag;
        }

        public void setCheckFlag(String str) {
            this.checkFlag = str;
        }

        public Integer getPageGoodNum() {
            return this.pageGoodNum;
        }

        public void setPageGoodNum(Integer num) {
            this.pageGoodNum = num;
        }

        public Integer getReplyNum() {
            return this.replyNum;
        }

        public void setReplyNum(Integer num) {
            this.replyNum = num;
        }

        public String getHeadPhoto() {
            return this.headPhoto;
        }

        public void setHeadPhoto(String str) {
            this.headPhoto = str;
        }

        public String getIsDelete() {
            return this.isDelete;
        }

        public void setIsDelete(String str) {
            this.isDelete = str;
        }

        public String getProvinceName() {
            return this.provinceName;
        }

        public void setProvinceName(String str) {
            this.provinceName = str;
        }

        public String getUploadImg() {
            return this.uploadImg;
        }

        public void setUploadImg(String str) {
            this.uploadImg = str;
        }
    }
}
