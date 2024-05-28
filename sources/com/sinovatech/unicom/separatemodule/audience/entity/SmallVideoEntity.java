package com.sinovatech.unicom.separatemodule.audience.entity;

import android.text.TextUtils;
import android.view.View;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.MultiItemEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SmallVideoEntity {
    private List<Data> data;
    private String message;
    private String nextPageNum;
    private String showLiveEntrance;
    private String statusCode;

    public List<Data> getData() {
        return this.data;
    }

    public void setData(List<Data> list) {
        this.data = list;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public String getShowLiveEntrance() {
        return this.showLiveEntrance;
    }

    public void setShowLiveEntrance(String str) {
        this.showLiveEntrance = str;
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

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Data implements MultiItemEntity {
        private View adView;
        private String commentNum;
        private String contentType;
        private int fromType;
        private List<GoodListEntity> goodsData;
        private String goodsDesc;
        private String goodsId;
        private String goodsImg;
        private String goodsLink;
        private String goodsName;
        private String goodsNum;
        private String goodsPrice;
        private boolean hasZan;
        private String identification;
        private int imgHeight;
        private int imgWidth;
        private int index;
        private boolean isFocusOn;
        private boolean isHasAddCount;
        private boolean isLandscape;
        private String isShow = "N";
        private boolean isShowFull;
        private String isShowGoods;
        private String outSideCanJump;
        private String shareNum;
        private String singleTag;
        private String tjpara;
        private String transcodeImg;
        private String userId;
        private String userImg;
        private String userIndexUrl;
        private String userName;
        private String videoAdvert;
        private String videoId;
        private String videoImg;
        private String videoPraiseNum;
        private String videoTag;
        private int videoType;
        private String videoUrl;
        private String viewNum;
        private String viewTitle;

        public int getImgWidth() {
            return this.imgWidth;
        }

        public void setImgWidth(int i) {
            this.imgWidth = i;
        }

        public int getImgHeight() {
            return this.imgHeight;
        }

        public void setImgHeight(int i) {
            this.imgHeight = i;
        }

        public int getFromType() {
            return this.fromType;
        }

        public void setFromType(int i) {
            this.fromType = i;
        }

        public String getSingleTag() {
            return this.singleTag;
        }

        public void setSingleTag(String str) {
            this.singleTag = str;
        }

        public String getIdentification() {
            return this.identification;
        }

        public void setIdentification(String str) {
            this.identification = str;
        }

        public List<GoodListEntity> getGoodsData() {
            return this.goodsData;
        }

        public void setGoodsData(List<GoodListEntity> list) {
            this.goodsData = list;
        }

        public String getContentType() {
            return this.contentType;
        }

        public void setContentType(String str) {
            this.contentType = str;
        }

        public String getTjpara() {
            return this.tjpara;
        }

        public void setTjpara(String str) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            this.tjpara = str;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public View getAdView() {
            return this.adView;
        }

        public void setAdView(View view) {
            this.adView = view;
        }

        public boolean isHasAddCount() {
            return this.isHasAddCount;
        }

        public void setHasAddCount(boolean z) {
            this.isHasAddCount = z;
        }

        public String getIsShow() {
            return this.isShow;
        }

        public void setIsShow(String str) {
            this.isShow = str;
        }

        public int getVideoType() {
            return this.videoType;
        }

        public void setVideoType(int i) {
            this.videoType = i;
        }

        public boolean isShowFull() {
            return this.isShowFull;
        }

        public void setShowFull(boolean z) {
            this.isShowFull = z;
        }

        public boolean isLandscape() {
            return this.isLandscape;
        }

        public void setLandscape(boolean z) {
            this.isLandscape = z;
        }

        public String getTranscodeImg() {
            return this.transcodeImg;
        }

        public void setTranscodeImg(String str) {
            this.transcodeImg = str;
        }

        public boolean isHasZan() {
            return this.hasZan;
        }

        public void setHasZan(boolean z) {
            this.hasZan = z;
        }

        public boolean isFocusOn() {
            return this.isFocusOn;
        }

        public void setFocusOn(boolean z) {
            this.isFocusOn = z;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserIndexUrl() {
            return this.userIndexUrl;
        }

        public void setUserIndexUrl(String str) {
            this.userIndexUrl = str;
        }

        public String getGoodsNum() {
            return this.goodsNum;
        }

        public void setGoodsNum(String str) {
            this.goodsNum = str;
        }

        public String getIsShowGoods() {
            return this.isShowGoods;
        }

        public void setIsShowGoods(String str) {
            this.isShowGoods = str;
        }

        public String getGoodsId() {
            return this.goodsId;
        }

        public void setGoodsId(String str) {
            this.goodsId = str;
        }

        public String getGoodsImg() {
            return this.goodsImg;
        }

        public void setGoodsImg(String str) {
            this.goodsImg = str;
        }

        public String getGoodsName() {
            return this.goodsName;
        }

        public void setGoodsName(String str) {
            this.goodsName = str;
        }

        public String getGoodsDesc() {
            return this.goodsDesc;
        }

        public void setGoodsDesc(String str) {
            this.goodsDesc = str;
        }

        public String getOutSideCanJump() {
            return this.outSideCanJump;
        }

        public void setOutSideCanJump(String str) {
            this.outSideCanJump = str;
        }

        public String getGoodsLink() {
            return this.goodsLink;
        }

        public void setGoodsLink(String str) {
            this.goodsLink = str;
        }

        public String getGoodsPrice() {
            return this.goodsPrice;
        }

        public void setGoodsPrice(String str) {
            this.goodsPrice = str;
        }

        public String getVideoId() {
            return this.videoId;
        }

        public void setVideoId(String str) {
            this.videoId = str;
        }

        public String getVideoUrl() {
            return this.videoUrl;
        }

        public void setVideoUrl(String str) {
            this.videoUrl = str;
        }

        public String getVideoImg() {
            return this.videoImg;
        }

        public void setVideoImg(String str) {
            this.videoImg = str;
        }

        public String getViewTitle() {
            return this.viewTitle;
        }

        public void setViewTitle(String str) {
            this.viewTitle = str;
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
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
            this.videoPraiseNum = str;
        }

        public String getCommentNum() {
            return this.commentNum;
        }

        public void setCommentNum(String str) {
            this.commentNum = str;
        }

        public String getShareNum() {
            return this.shareNum;
        }

        public void setShareNum(String str) {
            this.shareNum = str;
        }

        public String getUserImg() {
            return this.userImg;
        }

        public void setUserImg(String str) {
            this.userImg = str;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getVideoAdvert() {
            return this.videoAdvert;
        }

        public void setVideoAdvert(String str) {
            this.videoAdvert = str;
        }

        public String getVideoTag() {
            return this.videoTag;
        }

        public void setVideoTag(String str) {
            this.videoTag = str;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.MultiItemEntity
        public int getItemType() {
            if (this.videoType == 2) {
                return 2;
            }
            return this.fromType;
        }
    }
}
