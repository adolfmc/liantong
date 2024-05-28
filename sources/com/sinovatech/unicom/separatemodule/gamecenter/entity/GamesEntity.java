package com.sinovatech.unicom.separatemodule.gamecenter.entity;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GamesEntity {
    private List<GamesDataEntity> banner;
    private String code;
    private List<GamesDataEntity> data;
    private GamesDataEntity fristItem;
    private String hotUrl;
    private String msg;
    private List<PosterData> poster;
    private List<GamesDataEntity> recommendList;
    private String rightImg;
    private String rightUrl;
    private String signInImg;
    private String title;

    public String toString() {
        return "GamesEntity{code='" + this.code + "', msg='" + this.msg + "', data=" + this.data + ", title='" + this.title + "', banner=" + this.banner + ", hotUrl='" + this.hotUrl + "', signInImg='" + this.signInImg + "', fristItem=" + this.fristItem + ", rightImg='" + this.rightImg + "', rightUrl='" + this.rightUrl + "', poster=" + this.poster + ", recommendList=" + this.recommendList + '}';
    }

    public String getRightUrl() {
        return this.rightUrl;
    }

    public void setRightUrl(String str) {
        this.rightUrl = str;
    }

    public String getRightImg() {
        return this.rightImg;
    }

    public void setRightImg(String str) {
        this.rightImg = str;
    }

    public String getSignInImg() {
        return this.signInImg;
    }

    public void setSignInImg(String str) {
        this.signInImg = str;
    }

    public List<GamesDataEntity> getBanner() {
        return this.banner;
    }

    public void setBanner(List<GamesDataEntity> list) {
        this.banner = list;
    }

    public String getHotUrl() {
        return this.hotUrl;
    }

    public void setHotUrl(String str) {
        this.hotUrl = str;
    }

    public GamesDataEntity getFristItem() {
        return this.fristItem;
    }

    public void setFristItem(GamesDataEntity gamesDataEntity) {
        this.fristItem = gamesDataEntity;
    }

    public List<GamesDataEntity> getRecommendList() {
        if (this.recommendList == null) {
            this.recommendList = new ArrayList();
        }
        return this.recommendList;
    }

    public void setRecommendList(List<GamesDataEntity> list) {
        this.recommendList = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public List<GamesDataEntity> getData() {
        return this.data;
    }

    public void setData(List<GamesDataEntity> list) {
        this.data = list;
    }

    public List<PosterData> getPoster() {
        return this.poster;
    }

    public void setPoster(List<PosterData> list) {
        this.poster = list;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class GamesDataEntity {
        private String android_version;
        private String attach_title;
        private String bag_name;
        private String bottom_icon;
        private String boutiqueAndroidSort;
        private String boutiqueFlag;
        private String boutiqueIosSort;
        private String bubble;
        private String company;
        private long createTime;
        private String del_status;
        private String deviceType;
        private String diamond_type;
        private String endTime;
        private String end_date;
        private String freeFlow;
        private String galleryId;
        private String gameChoice;
        private String gameCode;
        private String gameDesc;
        private String gameIap;
        private String gameLabel;
        private String gameLabelState;
        private String gamePersonsBase;
        private String gamePersonsSum;
        private String gameType;
        private String game_id;
        private String game_name;
        private String groupCode;
        private String hotImgUrl;
        private String hotMark;
        private String hotWeight;
        private String icon;

        /* renamed from: id */
        private String f18529id;
        private String iosHotWeight;
        private String ios_version;
        private String isBoutique;
        private String isNewGame;
        private String laceImgType;
        private String laceImgUrl;
        private Object location_id;
        private String name;
        private String newGameAndroidSort;
        private String newGameIosSort;
        private String play_url;
        private String qqMark;
        private String qq_mark;
        private String redLabel;
        private String resourceId;
        private String resource_id;
        private String slogan;
        private String sloganIcon;
        private String smallImage;
        private int sort;
        private int sortNum;
        private String startTime;
        private String start_date;
        private String state;
        private String tagCode;
        private String tagColor;
        private String tagName;
        private String title;
        private String twoGameType;
        private String upShelfTime;
        private String up_status;
        private long updateTime;
        private String update_time;
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

        public String getGame_name() {
            return this.game_name;
        }

        public void setGame_name(String str) {
            this.game_name = str;
        }

        public String getQq_mark() {
            return this.qq_mark;
        }

        public void setQq_mark(String str) {
            this.qq_mark = str;
        }

        public String toString() {
            return "GamesDataEntity{createTime=" + this.createTime + ", gameType='" + this.gameType + "', newGameIosSort='" + this.newGameIosSort + "', gameDesc='" + this.gameDesc + "', deviceType='" + this.deviceType + "', galleryId='" + this.galleryId + "', url='" + this.url + "', twoGameType='" + this.twoGameType + "', gameCode='" + this.gameCode + "', laceImgUrl='" + this.laceImgUrl + "', sloganIcon='" + this.sloganIcon + "', name='" + this.name + "', upShelfTime='" + this.upShelfTime + "', gameLabelState='" + this.gameLabelState + "', tagCode='" + this.tagCode + "', smallImage='" + this.smallImage + "', slogan='" + this.slogan + "', freeFlow='" + this.freeFlow + "', id='" + this.f18529id + "', redLabel='" + this.redLabel + "', resourceId='" + this.resourceId + "', resource_id='" + this.resource_id + "', newGameAndroidSort='" + this.newGameAndroidSort + "', gameChoice='" + this.gameChoice + "', updateTime=" + this.updateTime + ", startTime='" + this.startTime + "', tagColor='" + this.tagColor + "', state='" + this.state + "', gameLabel='" + this.gameLabel + "', laceImgType='" + this.laceImgType + "', gamePersonsSum='" + this.gamePersonsSum + "', tagName='" + this.tagName + "', gameIap='" + this.gameIap + "', sortNum=" + this.sortNum + ", hotImgUrl='" + this.hotImgUrl + "', company='" + this.company + "', endTime='" + this.endTime + "', gamePersonsBase='" + this.gamePersonsBase + "', boutiqueAndroidSort='" + this.boutiqueAndroidSort + "', iosHotWeight='" + this.iosHotWeight + "', hotMark='" + this.hotMark + "', hotWeight='" + this.hotWeight + "', isBoutique='" + this.isBoutique + "', isNewGame='" + this.isNewGame + "', whiteLabel='" + this.whiteLabel + "', qqMark='" + this.qqMark + "', boutiqueIosSort='" + this.boutiqueIosSort + "', boutiqueFlag='" + this.boutiqueFlag + "', update_time='" + this.update_time + "', attach_title='" + this.attach_title + "', bubble='" + this.bubble + "', del_status='" + this.del_status + "', diamond_type='" + this.diamond_type + "', sort=" + this.sort + ", icon='" + this.icon + "', location_id=" + this.location_id + ", android_version='" + this.android_version + "', up_status='" + this.up_status + "', play_url='" + this.play_url + "', title='" + this.title + "', end_date='" + this.end_date + "', bottom_icon='" + this.bottom_icon + "', bag_name='" + this.bag_name + "', start_date='" + this.start_date + "', ios_version='" + this.ios_version + "'}";
        }

        public String getResource_id() {
            return this.resource_id;
        }

        public void setResource_id(String str) {
            this.resource_id = str;
        }

        public String getBoutiqueFlag() {
            return this.boutiqueFlag;
        }

        public void setBoutiqueFlag(String str) {
            this.boutiqueFlag = str;
        }

        public long getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(long j) {
            this.createTime = j;
        }

        public String getGameType() {
            return this.gameType;
        }

        public void setGameType(String str) {
            this.gameType = str;
        }

        public String getNewGameIosSort() {
            return this.newGameIosSort;
        }

        public void setNewGameIosSort(String str) {
            this.newGameIosSort = str;
        }

        public String getGameDesc() {
            return this.gameDesc;
        }

        public void setGameDesc(String str) {
            this.gameDesc = str;
        }

        public String getDeviceType() {
            return this.deviceType;
        }

        public void setDeviceType(String str) {
            this.deviceType = str;
        }

        public String getGalleryId() {
            return this.galleryId;
        }

        public void setGalleryId(String str) {
            this.galleryId = str;
        }

        public String getTwoGameType() {
            return this.twoGameType;
        }

        public void setTwoGameType(String str) {
            this.twoGameType = str;
        }

        public String getGameCode() {
            return this.gameCode;
        }

        public void setGameCode(String str) {
            this.gameCode = str;
        }

        public String getLaceImgUrl() {
            return this.laceImgUrl;
        }

        public void setLaceImgUrl(String str) {
            this.laceImgUrl = str;
        }

        public String getSloganIcon() {
            return this.sloganIcon;
        }

        public void setSloganIcon(String str) {
            this.sloganIcon = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getUpShelfTime() {
            return this.upShelfTime;
        }

        public void setUpShelfTime(String str) {
            this.upShelfTime = str;
        }

        public String getGameLabelState() {
            return this.gameLabelState;
        }

        public void setGameLabelState(String str) {
            this.gameLabelState = str;
        }

        public String getTagCode() {
            return this.tagCode;
        }

        public void setTagCode(String str) {
            this.tagCode = str;
        }

        public String getSmallImage() {
            return this.smallImage;
        }

        public void setSmallImage(String str) {
            this.smallImage = str;
        }

        public String getSlogan() {
            return this.slogan;
        }

        public void setSlogan(String str) {
            this.slogan = str;
        }

        public String getFreeFlow() {
            return this.freeFlow;
        }

        public void setFreeFlow(String str) {
            this.freeFlow = str;
        }

        public String getRedLabel() {
            return this.redLabel;
        }

        public void setRedLabel(String str) {
            this.redLabel = str;
        }

        public String getResourceId() {
            return this.resourceId;
        }

        public void setResourceId(String str) {
            this.resourceId = str;
        }

        public String getNewGameAndroidSort() {
            return this.newGameAndroidSort;
        }

        public void setNewGameAndroidSort(String str) {
            this.newGameAndroidSort = str;
        }

        public String getGameChoice() {
            return this.gameChoice;
        }

        public void setGameChoice(String str) {
            this.gameChoice = str;
        }

        public long getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(long j) {
            this.updateTime = j;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public String getTagColor() {
            return this.tagColor;
        }

        public void setTagColor(String str) {
            this.tagColor = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getGameLabel() {
            return this.gameLabel;
        }

        public void setGameLabel(String str) {
            this.gameLabel = str;
        }

        public String getLaceImgType() {
            return this.laceImgType;
        }

        public void setLaceImgType(String str) {
            this.laceImgType = str;
        }

        public String getGamePersonsSum() {
            if (TextUtils.isEmpty(this.gamePersonsSum)) {
                this.gamePersonsSum = "0";
            }
            return this.gamePersonsSum;
        }

        public void setGamePersonsSum(String str) {
            this.gamePersonsSum = str;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void setTagName(String str) {
            this.tagName = str;
        }

        public String getGameIap() {
            return this.gameIap;
        }

        public void setGameIap(String str) {
            this.gameIap = str;
        }

        public int getSortNum() {
            return this.sortNum;
        }

        public void setSortNum(int i) {
            this.sortNum = i;
        }

        public String getHotImgUrl() {
            return this.hotImgUrl;
        }

        public void setHotImgUrl(String str) {
            this.hotImgUrl = str;
        }

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String str) {
            this.company = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public String getGamePersonsBase() {
            return this.gamePersonsBase;
        }

        public void setGamePersonsBase(String str) {
            this.gamePersonsBase = str;
        }

        public String getBoutiqueAndroidSort() {
            return this.boutiqueAndroidSort;
        }

        public void setBoutiqueAndroidSort(String str) {
            this.boutiqueAndroidSort = str;
        }

        public String getIosHotWeight() {
            return this.iosHotWeight;
        }

        public void setIosHotWeight(String str) {
            this.iosHotWeight = str;
        }

        public String getHotMark() {
            return this.hotMark;
        }

        public void setHotMark(String str) {
            this.hotMark = str;
        }

        public String getHotWeight() {
            return this.hotWeight;
        }

        public void setHotWeight(String str) {
            this.hotWeight = str;
        }

        public String getIsBoutique() {
            return this.isBoutique;
        }

        public void setIsBoutique(String str) {
            this.isBoutique = str;
        }

        public String getIsNewGame() {
            return this.isNewGame;
        }

        public void setIsNewGame(String str) {
            this.isNewGame = str;
        }

        public String getWhiteLabel() {
            return this.whiteLabel;
        }

        public void setWhiteLabel(String str) {
            this.whiteLabel = str;
        }

        public String getQqMark() {
            return this.qqMark;
        }

        public void setQqMark(String str) {
            this.qqMark = str;
        }

        public String getBoutiqueIosSort() {
            return this.boutiqueIosSort;
        }

        public void setBoutiqueIosSort(String str) {
            this.boutiqueIosSort = str;
        }

        public String getUpdate_time() {
            return this.update_time;
        }

        public void setUpdate_time(String str) {
            this.update_time = str;
        }

        public String getId() {
            return this.f18529id;
        }

        public void setId(String str) {
            this.f18529id = str;
        }

        public String getAttach_title() {
            return this.attach_title;
        }

        public void setAttach_title(String str) {
            this.attach_title = str;
        }

        public String getBubble() {
            return this.bubble;
        }

        public void setBubble(String str) {
            this.bubble = str;
        }

        public String getDel_status() {
            return this.del_status;
        }

        public void setDel_status(String str) {
            this.del_status = str;
        }

        public String getDiamond_type() {
            return this.diamond_type;
        }

        public void setDiamond_type(String str) {
            this.diamond_type = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public int getSort() {
            return this.sort;
        }

        public void setSort(int i) {
            this.sort = i;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public Object getLocation_id() {
            return this.location_id;
        }

        public void setLocation_id(Object obj) {
            this.location_id = obj;
        }

        public String getAndroid_version() {
            return this.android_version;
        }

        public void setAndroid_version(String str) {
            this.android_version = str;
        }

        public String getUp_status() {
            return this.up_status;
        }

        public void setUp_status(String str) {
            this.up_status = str;
        }

        public String getPlay_url() {
            return this.play_url;
        }

        public void setPlay_url(String str) {
            this.play_url = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getEnd_date() {
            return this.end_date;
        }

        public void setEnd_date(String str) {
            this.end_date = str;
        }

        public String getBottom_icon() {
            return this.bottom_icon;
        }

        public void setBottom_icon(String str) {
            this.bottom_icon = str;
        }

        public String getBag_name() {
            return this.bag_name;
        }

        public void setBag_name(String str) {
            this.bag_name = str;
        }

        public String getStart_date() {
            return this.start_date;
        }

        public void setStart_date(String str) {
            this.start_date = str;
        }

        public String getIos_version() {
            return this.ios_version;
        }

        public void setIos_version(String str) {
            this.ios_version = str;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class PosterData {
        private String android_version;
        private String bubble;
        private String company;
        private String del_status;
        private String deploy_game;
        private String end_time;
        private String floor_type;
        private String free_flow;
        private String gameType;
        private String gameUrl;
        private String game_id;
        private String game_name;
        private String game_persons_base;

        /* renamed from: id */
        private String f18530id;
        private String ios_version;
        private String is_action;
        private String location;
        private String main_title;
        private String name;
        private String prop_code;
        private String qq_mark;
        private String resource_id;
        private String start_time;
        private String status;
        private String two_game_type;
        private String url;
        private String vice_title;

        public String toString() {
            return "PosterData{floor_type='" + this.floor_type + "', id='" + this.f18530id + "', bubble='" + this.bubble + "', del_status='" + this.del_status + "', free_flow='" + this.free_flow + "', company='" + this.company + "', vice_title='" + this.vice_title + "', url='" + this.url + "', main_title='" + this.main_title + "', gameType='" + this.gameType + "', two_game_type='" + this.two_game_type + "', game_persons_base='" + this.game_persons_base + "', deploy_game='" + this.deploy_game + "', gameUrl='" + this.gameUrl + "', game_id='" + this.game_id + "', status='" + this.status + "', location='" + this.location + "', android_version='" + this.android_version + "', prop_code='" + this.prop_code + "', start_time='" + this.start_time + "', end_time='" + this.end_time + "', is_action='" + this.is_action + "', ios_version='" + this.ios_version + "', game_name='" + this.game_name + "', qq_mark='" + this.qq_mark + "', name='" + this.name + "', resource_id='" + this.resource_id + "'}";
        }

        public String getFloor_type() {
            return this.floor_type;
        }

        public void setFloor_type(String str) {
            this.floor_type = str;
        }

        public String getId() {
            return this.f18530id;
        }

        public void setId(String str) {
            this.f18530id = str;
        }

        public String getBubble() {
            return this.bubble;
        }

        public void setBubble(String str) {
            this.bubble = str;
        }

        public String getDel_status() {
            return this.del_status;
        }

        public void setDel_status(String str) {
            this.del_status = str;
        }

        public String getFree_flow() {
            return this.free_flow;
        }

        public void setFree_flow(String str) {
            this.free_flow = str;
        }

        public String getCompany() {
            return this.company;
        }

        public void setCompany(String str) {
            this.company = str;
        }

        public String getVice_title() {
            return this.vice_title;
        }

        public void setVice_title(String str) {
            this.vice_title = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getMain_title() {
            return this.main_title;
        }

        public void setMain_title(String str) {
            this.main_title = str;
        }

        public String getGameType() {
            return this.gameType;
        }

        public void setGameType(String str) {
            this.gameType = str;
        }

        public String getTwo_game_type() {
            return this.two_game_type;
        }

        public void setTwo_game_type(String str) {
            this.two_game_type = str;
        }

        public String getGame_persons_base() {
            return this.game_persons_base;
        }

        public void setGame_persons_base(String str) {
            this.game_persons_base = str;
        }

        public String getDeploy_game() {
            return this.deploy_game;
        }

        public void setDeploy_game(String str) {
            this.deploy_game = str;
        }

        public String getGameUrl() {
            return this.gameUrl;
        }

        public void setGameUrl(String str) {
            this.gameUrl = str;
        }

        public String getGame_id() {
            return this.game_id;
        }

        public void setGame_id(String str) {
            this.game_id = str;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String str) {
            this.location = str;
        }

        public String getAndroid_version() {
            return this.android_version;
        }

        public void setAndroid_version(String str) {
            this.android_version = str;
        }

        public String getProp_code() {
            return this.prop_code;
        }

        public void setProp_code(String str) {
            this.prop_code = str;
        }

        public String getStart_time() {
            return this.start_time;
        }

        public void setStart_time(String str) {
            this.start_time = str;
        }

        public String getEnd_time() {
            return this.end_time;
        }

        public void setEnd_time(String str) {
            this.end_time = str;
        }

        public String getIs_action() {
            return this.is_action;
        }

        public void setIs_action(String str) {
            this.is_action = str;
        }

        public String getIos_version() {
            return this.ios_version;
        }

        public void setIos_version(String str) {
            this.ios_version = str;
        }

        public String getGame_name() {
            return this.game_name;
        }

        public void setGame_name(String str) {
            this.game_name = str;
        }

        public String getQq_mark() {
            return this.qq_mark;
        }

        public void setQq_mark(String str) {
            this.qq_mark = str;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getResource_id() {
            return this.resource_id;
        }

        public void setResource_id(String str) {
            this.resource_id = str;
        }
    }
}
