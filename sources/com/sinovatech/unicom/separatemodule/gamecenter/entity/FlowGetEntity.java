package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FlowGetEntity {
    private String code;
    private ConfigBean config;
    private String msg;
    private List<PopularListBean> popularList;
    private String receiveFlag;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

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

    public ConfigBean getConfig() {
        return this.config;
    }

    public void setConfig(ConfigBean configBean) {
        this.config = configBean;
    }

    public List<PopularListBean> getPopularList() {
        return this.popularList;
    }

    public void setPopularList(List<PopularListBean> list) {
        this.popularList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ConfigBean {

        /* renamed from: id */
        private int f18527id;
        private String img_url;
        private String left_text;
        private String right_text;

        public String getLeft_text() {
            return this.left_text;
        }

        public void setLeft_text(String str) {
            this.left_text = str;
        }

        public String getRight_text() {
            return this.right_text;
        }

        public void setRight_text(String str) {
            this.right_text = str;
        }

        public String getImg_url() {
            return this.img_url;
        }

        public void setImg_url(String str) {
            this.img_url = str;
        }

        public int getId() {
            return this.f18527id;
        }

        public void setId(int i) {
            this.f18527id = i;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class PopularListBean {
        private String androidVersion;
        private String backGroundImg;
        private String bigImage;
        private String boutiqueAndroidSort;
        private String boutiqueIosSort;
        private String company;
        private String copywriting;
        private long createTime;
        private int currentMinute;
        private String deviceType;
        private String downloadValue;
        private String endTime;
        private String fileSize;
        private String flow;
        private String freeFlow;
        private String galleryId;
        private String gameChoice;
        private String gameCode;
        private String gameCodeEncrypt;
        private String gameDesc;
        private String gameIap;
        private String gameLabel;
        private String gameLabelState;
        private String gamePersonsSum;
        private String gamePersonsTrue;
        private String gameState;
        private String gameType;
        private String game_id;
        private String game_name;
        private String groupCode;
        private String h5Url;
        private long hotFirstFive;
        private String hotImgUrl;
        private String hotMark;
        private String hotWeight;

        /* renamed from: id */
        private String f18528id;
        private String iosHotWeight;
        private String iosVersion;
        private String isBoutique;
        private String isNewGame;
        private String laceImgType;
        private String laceImgUrl;
        private String minute;
        private String name;
        private String newGameAndroidSort;
        private String newGameIosSort;
        private String operator;
        private Double personNum;
        private String qqClassify;
        private String qqMark;
        private String qqTags;
        private String qq_mark;
        private String redLabel;
        private String resourceId;
        private String resource_id;
        private String showPlace;
        private String slogan;
        private String sloganIcon;
        private String smallImage;
        private int sortNum;
        private String startTime;
        private String state;
        private String tagCode;
        private String tagColor;
        private String tagName;
        private String topShow;
        private String twoGameType;
        private String upShelfTime;
        private long updateTime;
        private String url;
        private String whiteLabel;

        public String getGroupCode() {
            return this.groupCode;
        }

        public void setGroupCode(String str) {
            this.groupCode = str;
        }

        public String getGame_id() {
            return this.game_id;
        }

        public void setGame_id(String str) {
            this.game_id = str;
        }

        public String getResource_id() {
            return this.resource_id;
        }

        public void setResource_id(String str) {
            this.resource_id = str;
        }

        public String getQq_mark() {
            return this.qq_mark;
        }

        public void setQq_mark(String str) {
            this.qq_mark = str;
        }

        public String getGame_name() {
            return this.game_name;
        }

        public void setGame_name(String str) {
            this.game_name = str;
        }

        public String getGamePersonsSum() {
            return this.gamePersonsSum;
        }

        public void setGamePersonsSum(String str) {
            this.gamePersonsSum = str;
        }

        public String getNewGameAndroidSort() {
            return this.newGameAndroidSort;
        }

        public void setNewGameAndroidSort(String str) {
            this.newGameAndroidSort = str;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setDeviceType(String str) {
            this.deviceType = str;
        }

        public String getFileSize() {
            return this.fileSize;
        }

        public void setFileSize(String str) {
            this.fileSize = str;
        }

        public String getIosVersion() {
            return this.iosVersion;
        }

        public void setIosVersion(String str) {
            this.iosVersion = str;
        }

        public String getBoutiqueAndroidSort() {
            return this.boutiqueAndroidSort;
        }

        public void setBoutiqueAndroidSort(String str) {
            this.boutiqueAndroidSort = str;
        }

        public String getGameState() {
            return this.gameState;
        }

        public void setGameState(String str) {
            this.gameState = str;
        }

        public String getIosHotWeight() {
            return this.iosHotWeight;
        }

        public void setIosHotWeight(String str) {
            this.iosHotWeight = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public Double getPersonNum() {
            return this.personNum;
        }

        public void setPersonNum(Double d) {
            this.personNum = d;
        }

        public int getCurrentMinute() {
            return this.currentMinute;
        }

        public void setCurrentMinute(int i) {
            this.currentMinute = i;
        }

        public String getGameCode() {
            return this.gameCode;
        }

        public void setGameCode(String str) {
            this.gameCode = str;
        }

        public String getDownloadValue() {
            return this.downloadValue;
        }

        public void setDownloadValue(String str) {
            this.downloadValue = str;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void setTagName(String str) {
            this.tagName = str;
        }

        public String getMinute() {
            return this.minute;
        }

        public void setMinute(String str) {
            this.minute = str;
        }

        public String getBoutiqueIosSort() {
            return this.boutiqueIosSort;
        }

        public void setBoutiqueIosSort(String str) {
            this.boutiqueIosSort = str;
        }

        public String getTwoGameType() {
            return this.twoGameType;
        }

        public void setTwoGameType(String str) {
            this.twoGameType = str;
        }

        public String getLaceImgUrl() {
            return this.laceImgUrl;
        }

        public void setLaceImgUrl(String str) {
            this.laceImgUrl = str;
        }

        public String getFlow() {
            return this.flow;
        }

        public void setFlow(String str) {
            this.flow = str;
        }

        public String getWhiteLabel() {
            return this.whiteLabel;
        }

        public void setWhiteLabel(String str) {
            this.whiteLabel = str;
        }

        public String getBackGroundImg() {
            return this.backGroundImg;
        }

        public void setBackGroundImg(String str) {
            this.backGroundImg = str;
        }

        public String getQqClassify() {
            return this.qqClassify;
        }

        public void setQqClassify(String str) {
            this.qqClassify = str;
        }

        public String getFreeFlow() {
            return this.freeFlow;
        }

        public void setFreeFlow(String str) {
            this.freeFlow = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getHotImgUrl() {
            return this.hotImgUrl;
        }

        public void setHotImgUrl(String str) {
            this.hotImgUrl = str;
        }

        public String getIsNewGame() {
            return this.isNewGame;
        }

        public void setIsNewGame(String str) {
            this.isNewGame = str;
        }

        public String getCopywriting() {
            return this.copywriting;
        }

        public void setCopywriting(String str) {
            this.copywriting = str;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long j) {
            this.updateTime = j;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long j) {
            this.createTime = j;
        }

        public String getId() {
            return this.f18528id;
        }

        public void setId(String str) {
            this.f18528id = str;
        }

        public String getTagCode() {
            return this.tagCode;
        }

        public void setTagCode(String str) {
            this.tagCode = str;
        }

        public String getTagColor() {
            return this.tagColor;
        }

        public void setTagColor(String str) {
            this.tagColor = str;
        }

        public String getSloganIcon() {
            return this.sloganIcon;
        }

        public void setSloganIcon(String str) {
            this.sloganIcon = str;
        }

        public String getSmallImage() {
            return this.smallImage;
        }

        public void setSmallImage(String str) {
            this.smallImage = str;
        }

        public String getGameIap() {
            return this.gameIap;
        }

        public void setGameIap(String str) {
            this.gameIap = str;
        }

        public String getSlogan() {
            return this.slogan;
        }

        public void setSlogan(String str) {
            this.slogan = str;
        }

        public String getResourceId() {
            return this.resourceId;
        }

        public void setResourceId(String str) {
            this.resourceId = str;
        }

        public String getGameChoice() {
            return this.gameChoice;
        }

        public void setGameChoice(String str) {
            this.gameChoice = str;
        }

        public String getRedLabel() {
            return this.redLabel;
        }

        public void setRedLabel(String str) {
            this.redLabel = str;
        }

        public String getGalleryId() {
            return this.galleryId;
        }

        public void setGalleryId(String str) {
            this.galleryId = str;
        }

        public String getQqMark() {
            return this.qqMark;
        }

        public void setQqMark(String str) {
            this.qqMark = str;
        }

        public String getHotWeight() {
            return this.hotWeight;
        }

        public void setHotWeight(String str) {
            this.hotWeight = str;
        }

        public String getHotMark() {
            return this.hotMark;
        }

        public void setHotMark(String str) {
            this.hotMark = str;
        }

        public String getUpShelfTime() {
            return this.upShelfTime;
        }

        public void setUpShelfTime(String str) {
            this.upShelfTime = str;
        }

        public String getGameLabel() {
            return this.gameLabel;
        }

        public void setGameLabel(String str) {
            this.gameLabel = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public String getNewGameIosSort() {
            return this.newGameIosSort;
        }

        public void setNewGameIosSort(String str) {
            this.newGameIosSort = str;
        }

        public String getLaceImgType() {
            return this.laceImgType;
        }

        public void setLaceImgType(String str) {
            this.laceImgType = str;
        }

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String str) {
            this.company = str;
        }

        public String getGameLabelState() {
            return this.gameLabelState;
        }

        public void setGameLabelState(String str) {
            this.gameLabelState = str;
        }

        public String getGamePersonsTrue() {
            return this.gamePersonsTrue;
        }

        public void setGamePersonsTrue(String str) {
            this.gamePersonsTrue = str;
        }

        public String getGameType() {
            return this.gameType;
        }

        public void setGameType(String str) {
            this.gameType = str;
        }

        public String getAndroidVersion() {
            return this.androidVersion;
        }

        public void setAndroidVersion(String str) {
            this.androidVersion = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getGameDesc() {
            return this.gameDesc;
        }

        public void setGameDesc(String str) {
            this.gameDesc = str;
        }

        public String getQqTags() {
            return this.qqTags;
        }

        public void setQqTags(String str) {
            this.qqTags = str;
        }

        public String getIsBoutique() {
            return this.isBoutique;
        }

        public void setIsBoutique(String str) {
            this.isBoutique = str;
        }

        public int getSortNum() {
            return this.sortNum;
        }

        public void setSortNum(int i) {
            this.sortNum = i;
        }

        public String getTopShow() {
            return this.topShow;
        }

        public void setTopShow(String str) {
            this.topShow = str;
        }

        public String getH5Url() {
            return this.h5Url;
        }

        public void setH5Url(String str) {
            this.h5Url = str;
        }

        public String getBigImage() {
            return this.bigImage;
        }

        public void setBigImage(String str) {
            this.bigImage = str;
        }

        public String getOperator() {
            return this.operator;
        }

        public void setOperator(String str) {
            this.operator = str;
        }

        public String getShowPlace() {
            return this.showPlace;
        }

        public void setShowPlace(String str) {
            this.showPlace = str;
        }

        public String getGameCodeEncrypt() {
            return this.gameCodeEncrypt;
        }

        public void setGameCodeEncrypt(String str) {
            this.gameCodeEncrypt = str;
        }

        public long getHotFirstFive() {
            return this.hotFirstFive;
        }

        public void setHotFirstFive(long j) {
            this.hotFirstFive = j;
        }
    }
}
