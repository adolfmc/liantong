package com.sinovatech.unicom.basic.p314po;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.MyUnicomEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyUnicomEntity {
    private String backImg;
    private String backImgURl;
    private List<UnicomBottomEntity> bottomEntityList;
    private String headImage;
    private String infoDetail;
    private String jpUrl;
    private String levelLinkedAddress;
    private String levelPic;
    private MedalWall medalWall;
    private String messageUrl;
    private String nickName;
    private String phone;
    private String qianming;
    private String thirdPictureLinkUrl;
    private List<UnicomTopEntity> topEntityList;
    private String uploadImage;

    public String getJpUrl() {
        return this.jpUrl;
    }

    public void setJpUrl(String str) {
        this.jpUrl = str;
    }

    public List<UnicomTopEntity> getTopEntityList() {
        if (this.topEntityList == null) {
            this.topEntityList = new ArrayList();
        }
        return this.topEntityList;
    }

    public void setTopEntityList(List<UnicomTopEntity> list) {
        this.topEntityList = list;
    }

    public List<UnicomBottomEntity> getBottomEntityList() {
        if (this.bottomEntityList == null) {
            this.bottomEntityList = new ArrayList();
        }
        return this.bottomEntityList;
    }

    public void setBottomEntityList(List<UnicomBottomEntity> list) {
        this.bottomEntityList = list;
    }

    public String getMessageUrl() {
        return this.messageUrl;
    }

    public void setMessageUrl(String str) {
        this.messageUrl = str;
    }

    public String getBackImg() {
        return this.backImg;
    }

    public void setBackImg(String str) {
        this.backImg = str;
    }

    public String getBackImgURl() {
        return this.backImgURl;
    }

    public void setBackImgURl(String str) {
        this.backImgURl = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getThirdPictureLinkUrl() {
        return this.thirdPictureLinkUrl;
    }

    public void setThirdPictureLinkUrl(String str) {
        this.thirdPictureLinkUrl = str;
    }

    public String getUploadImage() {
        return this.uploadImage;
    }

    public void setUploadImage(String str) {
        this.uploadImage = str;
    }

    public String getInfoDetail() {
        return this.infoDetail;
    }

    public void setInfoDetail(String str) {
        this.infoDetail = str;
    }

    public String getQianming() {
        return this.qianming;
    }

    public void setQianming(String str) {
        this.qianming = str;
    }

    public String getHeadImage() {
        return this.headImage;
    }

    public void setHeadImage(String str) {
        this.headImage = str;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getLevelPic() {
        return this.levelPic;
    }

    public void setLevelPic(String str) {
        this.levelPic = str;
    }

    public String getLevelLinkedAddress() {
        return this.levelLinkedAddress;
    }

    public void setLevelLinkedAddress(String str) {
        this.levelLinkedAddress = str;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public MedalWall getMedalWall() {
        if (this.medalWall == null) {
            this.medalWall = new MedalWall();
        }
        return this.medalWall;
    }

    public void setMedalWall(MedalWall medalWall) {
        this.medalWall = medalWall;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.po.MyUnicomEntity$MedalWall */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class MedalWall {
        private String count;
        private String grade;
        private String imgUrl;
        private String linkurl;
        private String title;

        public String getImgUrl() {
            return this.imgUrl;
        }

        public void setImgUrl(String str) {
            this.imgUrl = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getCount() {
            return this.count;
        }

        public void setCount(String str) {
            this.count = str;
        }

        public String getGrade() {
            return this.grade;
        }

        public void setGrade(String str) {
            this.grade = str;
        }

        public String getLinkurl() {
            return this.linkurl;
        }

        public void setLinkurl(String str) {
            this.linkurl = str;
        }
    }
}
