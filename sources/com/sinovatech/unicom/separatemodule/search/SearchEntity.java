package com.sinovatech.unicom.separatemodule.search;

import android.text.TextUtils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import java.io.Serializable;

@Entity
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SearchEntity implements Serializable {
    private String actType;
    private String huodong_hallId;
    private String huodong_id;
    private String huodong_time;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18606id;
    public int sectionsIndex;
    private String subtitle;
    private String mobile = "";
    private String commandCode = "";
    private String proviceCode = "";
    private String cityCode = "";
    private String searchId = "";
    private String title = "";
    private String url = "";
    private String imageURL = "";
    private String desc = "";
    private String price = "";
    private String needLogin = "";
    public String backMode = "";
    public boolean isGroup = false;
    public String sectionsTitle = "";
    private String type = "";
    private boolean isFooter = false;
    private int hisBg = 0;
    private String goodsImage = "";
    private String goodsName = "";
    private String goodsPrice = "";
    private String goodsUrl = "";
    private String activityName = "";
    private String NAME = "";
    private String IS_BEST = "";
    private String IS_HOT = "";
    private String WOZHIDAOURL = "";
    private String floorPrice = "";
    private String marketPrice = "";
    private String best_img = "";
    private String hot_img = "";
    private String templateTitle = "";
    private String searchKey = "";
    private String describeInfo = "";
    private String templateName = "";
    private String dataId = "";
    private String gameType = "";
    private String clientVersion = "";
    public String browseNum = "";
    public String author = "";

    public String getHuodong_hallId() {
        return this.huodong_hallId;
    }

    public void setHuodong_hallId(String str) {
        this.huodong_hallId = str;
    }

    public String getActType() {
        return this.actType;
    }

    public void setActType(String str) {
        this.actType = str;
    }

    public String getHuodong_time() {
        return this.huodong_time;
    }

    public void setHuodong_time(String str) {
        this.huodong_time = str;
    }

    public String getHuodong_id() {
        return this.huodong_id;
    }

    public void setHuodong_id(String str) {
        this.huodong_id = str;
    }

    public String getGameType() {
        return this.gameType;
    }

    public void setGameType(String str) {
        this.gameType = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String str) {
        this.templateName = str;
    }

    public String getDataId() {
        return this.dataId;
    }

    public void setDataId(String str) {
        this.dataId = str;
    }

    public String getDescribeInfo() {
        return this.describeInfo;
    }

    public void setDescribeInfo(String str) {
        this.describeInfo = str;
    }

    public String getSearchKey() {
        return this.searchKey;
    }

    public void setSearchKey(String str) {
        this.searchKey = str;
    }

    public String getTemplateTitle() {
        return this.templateTitle;
    }

    public void setTemplateTitle(String str) {
        this.templateTitle = str;
    }

    public String getBest_img() {
        return this.best_img;
    }

    public void setBest_img(String str) {
        this.best_img = str;
    }

    public String getHot_img() {
        return this.hot_img;
    }

    public void setHot_img(String str) {
        this.hot_img = str;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public String getFloorPrice() {
        return this.floorPrice;
    }

    public void setFloorPrice(String str) {
        this.floorPrice = str;
    }

    public String getMarketPrice() {
        return this.marketPrice;
    }

    public void setMarketPrice(String str) {
        this.marketPrice = str;
    }

    public String getNAME() {
        return this.NAME;
    }

    public void setNAME(String str) {
        this.NAME = str;
    }

    public String getIS_BEST() {
        return this.IS_BEST;
    }

    public void setIS_BEST(String str) {
        this.IS_BEST = str;
    }

    public String getIS_HOT() {
        return this.IS_HOT;
    }

    public void setIS_HOT(String str) {
        this.IS_HOT = str;
    }

    public String getWOZHIDAOURL() {
        return this.WOZHIDAOURL;
    }

    public void setWOZHIDAOURL(String str) {
        this.WOZHIDAOURL = str;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public void setActivityName(String str) {
        this.activityName = str;
    }

    public String getGoodsImage() {
        return this.goodsImage;
    }

    public void setGoodsImage(String str) {
        this.goodsImage = str;
    }

    public String getGoodsName() {
        return this.goodsName;
    }

    public void setGoodsName(String str) {
        this.goodsName = str;
    }

    public String getGoodsPrice() {
        return this.goodsPrice;
    }

    public void setGoodsPrice(String str) {
        this.goodsPrice = str;
    }

    public String getGoodsUrl() {
        return this.goodsUrl;
    }

    public void setGoodsUrl(String str) {
        this.goodsUrl = str;
    }

    public int getHisBg() {
        return this.hisBg;
    }

    public void setHisBg(int i) {
        this.hisBg = i;
    }

    public String getBrowseNum() {
        return this.browseNum;
    }

    public void setBrowseNum(String str) {
        this.browseNum = str;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public long getId() {
        return this.f18606id;
    }

    public void setId(long j) {
        this.f18606id = j;
    }

    public String getType() {
        if (TextUtils.isEmpty(this.type)) {
            this.type = "";
        }
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getSearchId() {
        return this.searchId;
    }

    public void setSearchId(String str) {
        this.searchId = str;
    }

    public String getTitle() {
        if (TextUtils.isEmpty(this.title)) {
            this.title = "";
        }
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        if (TextUtils.isEmpty(this.url)) {
            this.url = "";
        }
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getImageURL() {
        if (TextUtils.isEmpty(this.imageURL)) {
            this.imageURL = "";
        }
        return this.imageURL;
    }

    public void setImageURL(String str) {
        this.imageURL = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getNeedLogin() {
        return this.needLogin;
    }

    public void setNeedLogin(String str) {
        this.needLogin = str;
    }

    public String getBackMode() {
        if (TextUtils.isEmpty(this.backMode)) {
            this.backMode = "1";
        }
        return this.backMode;
    }

    public void setBackMode(String str) {
        this.backMode = str;
    }

    public boolean isGroup() {
        return this.isGroup;
    }

    public void setGroup(boolean z) {
        this.isGroup = z;
    }

    public String getSectionsTitle() {
        if (TextUtils.isEmpty(this.sectionsTitle)) {
            this.sectionsTitle = "";
        }
        return this.sectionsTitle;
    }

    public void setSectionsTitle(String str) {
        this.sectionsTitle = str;
    }

    public int getSectionsIndex() {
        return this.sectionsIndex;
    }

    public void setSectionsIndex(int i) {
        this.sectionsIndex = i;
    }

    public boolean isFooter() {
        return this.isFooter;
    }

    public void setFooter(boolean z) {
        this.isFooter = z;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getProviceCode() {
        return this.proviceCode;
    }

    public void setProviceCode(String str) {
        this.proviceCode = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public String getCommandCode() {
        return this.commandCode;
    }

    public void setCommandCode(String str) {
        this.commandCode = str;
    }
}
