package com.sinovatech.unicom.separatemodule.search;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SearchHuoDongEntity implements Serializable {
    private String actType;
    private String hallId;
    private String huodongId;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18608id;
    private String isNeedLogin;
    private String linkUrl;
    private String searchWordName;
    private String time;
    private String mobile = "";
    private String proviceCode = "";
    private String cityCode = "";

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

    public long getId() {
        return this.f18608id;
    }

    public void setId(long j) {
        this.f18608id = j;
    }

    public String getActType() {
        return this.actType;
    }

    public void setActType(String str) {
        this.actType = str;
    }

    public String getHallId() {
        return this.hallId;
    }

    public void setHallId(String str) {
        this.hallId = str;
    }

    public String getHuodongId() {
        return this.huodongId;
    }

    public void setHuodongId(String str) {
        this.huodongId = str;
    }

    public String getIsNeedLogin() {
        return this.isNeedLogin;
    }

    public void setIsNeedLogin(String str) {
        this.isNeedLogin = str;
    }

    public String getLinkUrl() {
        return this.linkUrl;
    }

    public void setLinkUrl(String str) {
        this.linkUrl = str;
    }

    public String getSearchWordName() {
        return this.searchWordName;
    }

    public void setSearchWordName(String str) {
        this.searchWordName = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }
}
