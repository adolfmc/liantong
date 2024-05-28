package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HotGamesEntity {
    private String code;
    private List<HotGame> popularList;
    private String receiveFlag;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getReceiveFlag() {
        return this.receiveFlag;
    }

    public void setReceiveFlag(String str) {
        this.receiveFlag = str;
    }

    public List<HotGame> getPopularList() {
        return this.popularList;
    }

    public void setPopularList(List<HotGame> list) {
        this.popularList = list;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class HotGame {
        private String backGroundImg;
        private String boutiqueFlag;
        private String company;
        private String copywriting;
        private int currentMinute;
        private String freeFlow;
        private String gameIap;
        private String gameLabel;
        private String gameType;

        /* renamed from: id */
        private String f18531id;
        private String laceImgUrl;
        private String minute;
        private String name;
        private String personNum;
        private String qqMark;
        private String resourceId;
        private String resource_id;
        private String smallImage;
        private String state;
        private String twoGameType;
        private String url;

        public String toString() {
            return "HotGame{name='" + this.name + "', url='" + this.url + "', resourceId='" + this.resourceId + "', resource_id='" + this.resource_id + "', id='" + this.f18531id + "', company='" + this.company + "', smallImage='" + this.smallImage + "', freeFlow='" + this.freeFlow + "', gameIap='" + this.gameIap + "', qqMark='" + this.qqMark + "', minute='" + this.minute + "', currentMinute=" + this.currentMinute + ", state='" + this.state + "', twoGameType='" + this.twoGameType + "', gameType='" + this.gameType + "', copywriting='" + this.copywriting + "', boutiqueFlag='" + this.boutiqueFlag + "', laceImgUrl='" + this.laceImgUrl + "', gameLabel='" + this.gameLabel + "', backGroundImg='" + this.backGroundImg + "', personNum='" + this.personNum + "'}";
        }

        public String getBackGroundImg() {
            return this.backGroundImg;
        }

        public void setBackGroundImg(String str) {
            this.backGroundImg = str;
        }

        public String getPersonNum() {
            return this.personNum;
        }

        public void setPersonNum(String str) {
            this.personNum = str;
        }

        public String getResource_id() {
            return this.resource_id;
        }

        public void setResource_id(String str) {
            this.resource_id = str;
        }

        public String getGameLabel() {
            return this.gameLabel;
        }

        public void setGameLabel(String str) {
            this.gameLabel = str;
        }

        public String getLaceImgUrl() {
            return this.laceImgUrl;
        }

        public void setLaceImgUrl(String str) {
            this.laceImgUrl = str;
        }

        public String getBoutiqueFlag() {
            return this.boutiqueFlag;
        }

        public void setBoutiqueFlag(String str) {
            this.boutiqueFlag = str;
        }

        public String getCopywriting() {
            return this.copywriting;
        }

        public void setCopywriting(String str) {
            this.copywriting = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getResourceId() {
            return this.resourceId;
        }

        public void setResourceId(String str) {
            this.resourceId = str;
        }

        public String getId() {
            return this.f18531id;
        }

        public void setId(String str) {
            this.f18531id = str;
        }

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String str) {
            this.company = str;
        }

        public String getSmallImage() {
            return this.smallImage;
        }

        public void setSmallImage(String str) {
            this.smallImage = str;
        }

        public String getFreeFlow() {
            return this.freeFlow;
        }

        public void setFreeFlow(String str) {
            this.freeFlow = str;
        }

        public String getGameIap() {
            return this.gameIap;
        }

        public void setGameIap(String str) {
            this.gameIap = str;
        }

        public String getQqMark() {
            return this.qqMark;
        }

        public void setQqMark(String str) {
            this.qqMark = str;
        }

        public String getMinute() {
            return this.minute;
        }

        public void setMinute(String str) {
            this.minute = str;
        }

        public int getCurrentMinute() {
            return this.currentMinute;
        }

        public void setCurrentMinute(int i) {
            this.currentMinute = i;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getTwoGameType() {
            return this.twoGameType;
        }

        public void setTwoGameType(String str) {
            this.twoGameType = str;
        }

        public String getGameType() {
            return this.gameType;
        }

        public void setGameType(String str) {
            this.gameType = str;
        }
    }
}
