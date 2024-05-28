package com.sinovatech.unicom.separatemodule.livepinglun.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LiveReplayEntity {
    private String code;
    private CommentDetailEntity commentDetail;
    private String desc;
    private List<ReplyListEntity> replyList;

    public CommentDetailEntity getCommentDetail() {
        return this.commentDetail;
    }

    public void setCommentDetail(CommentDetailEntity commentDetailEntity) {
        this.commentDetail = commentDetailEntity;
    }

    public List<ReplyListEntity> getReplyList() {
        return this.replyList;
    }

    public void setReplyList(List<ReplyListEntity> list) {
        this.replyList = list;
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
    public static class CommentDetailEntity {
        private String cityName;
        private String commentContent;
        private String commentTime;
        private String goodFlag;
        private String headPhoto;

        /* renamed from: id */
        private String f18552id;
        private String isDelete;
        private String newsId;
        private String newsTitle;
        private String nickName;
        private Integer pageGoodNum;
        private String productImg;
        private String provinceName;
        private Integer replyNum;
        private String subTitle;
        private String topFlag;
        private String uploadImg;
        private String userNickReal;
        private String userStar;

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

        public String getId() {
            return this.f18552id;
        }

        public void setId(String str) {
            this.f18552id = str;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }

        public String getProductImg() {
            return this.productImg;
        }

        public void setProductImg(String str) {
            this.productImg = str;
        }

        public String getGoodFlag() {
            return this.goodFlag;
        }

        public void setGoodFlag(String str) {
            this.goodFlag = str;
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

        public String getCityName() {
            return this.cityName;
        }

        public void setCityName(String str) {
            this.cityName = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getCommentContent() {
            return this.commentContent;
        }

        public void setCommentContent(String str) {
            this.commentContent = str;
        }

        public String getHeadPhoto() {
            return this.headPhoto;
        }

        public void setHeadPhoto(String str) {
            this.headPhoto = str;
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

        public String getNewsTitle() {
            return this.newsTitle;
        }

        public void setNewsTitle(String str) {
            this.newsTitle = str;
        }

        public String getNewsId() {
            return this.newsId;
        }

        public void setNewsId(String str) {
            this.newsId = str;
        }

        public String getUploadImg() {
            return this.uploadImg;
        }

        public void setUploadImg(String str) {
            this.uploadImg = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ReplyListEntity {
        private String cityName;
        private String commentId;
        private String goodFlag;

        /* renamed from: id */
        private String f18553id;
        private String isDelete;
        private Integer pageGoodNum;
        private String productImg;
        private String provinceName;
        private String repalyNum;
        private String replyContent;
        private String replyTime;
        private String replyedHeadImg;
        private String replyedMobile;
        private String replyedNick;
        private String replyerHeadImg;
        private String replyerMobile;
        private String replyerNick;
        private String replyerRole;
        private String uploadImg;
        private String userStar;

        public String getRepalyNum() {
            return this.repalyNum;
        }

        public void setRepalyNum(String str) {
            this.repalyNum = str;
        }

        public String getProvinceName() {
            return this.provinceName;
        }

        public void setProvinceName(String str) {
            this.provinceName = str;
        }

        public String getCommentId() {
            return this.commentId;
        }

        public void setCommentId(String str) {
            this.commentId = str;
        }

        public String getId() {
            return this.f18553id;
        }

        public void setId(String str) {
            this.f18553id = str;
        }

        public String getProductImg() {
            return this.productImg;
        }

        public void setProductImg(String str) {
            this.productImg = str;
        }

        public String getReplyedHeadImg() {
            return this.replyedHeadImg;
        }

        public void setReplyedHeadImg(String str) {
            this.replyedHeadImg = str;
        }

        public String getReplyerMobile() {
            return this.replyerMobile;
        }

        public void setReplyerMobile(String str) {
            this.replyerMobile = str;
        }

        public String getReplyedNick() {
            return this.replyedNick;
        }

        public void setReplyedNick(String str) {
            this.replyedNick = str;
        }

        public String getGoodFlag() {
            return this.goodFlag;
        }

        public void setGoodFlag(String str) {
            this.goodFlag = str;
        }

        public String getReplyerRole() {
            return this.replyerRole;
        }

        public void setReplyerRole(String str) {
            this.replyerRole = str;
        }

        public String getReplyerHeadImg() {
            return this.replyerHeadImg;
        }

        public void setReplyerHeadImg(String str) {
            this.replyerHeadImg = str;
        }

        public String getReplyContent() {
            return this.replyContent;
        }

        public void setReplyContent(String str) {
            this.replyContent = str;
        }

        public String getCityName() {
            return this.cityName;
        }

        public void setCityName(String str) {
            this.cityName = str;
        }

        public String getReplyedMobile() {
            return this.replyedMobile;
        }

        public void setReplyedMobile(String str) {
            this.replyedMobile = str;
        }

        public Integer getPageGoodNum() {
            return this.pageGoodNum;
        }

        public void setPageGoodNum(Integer num) {
            this.pageGoodNum = num;
        }

        public String getReplyerNick() {
            return this.replyerNick;
        }

        public void setReplyerNick(String str) {
            this.replyerNick = str;
        }

        public String getReplyTime() {
            return this.replyTime;
        }

        public void setReplyTime(String str) {
            this.replyTime = str;
        }

        public String getUserStar() {
            return this.userStar;
        }

        public void setUserStar(String str) {
            this.userStar = str;
        }

        public String getUploadImg() {
            return this.uploadImg;
        }

        public void setUploadImg(String str) {
            this.uploadImg = str;
        }

        public String getIsDelete() {
            return this.isDelete;
        }

        public void setIsDelete(String str) {
            this.isDelete = str;
        }
    }
}
