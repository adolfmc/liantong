package com.sinovatech.unicom.separatemodule.user.entity;

import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserJingZhunYingXiaoEntity {
    private String actId;
    private String actType;
    private String goodsId;
    private String goodsType;
    private String goodsUrl;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18625id;
    private String imgSrc;
    private String isLogin;
    private String jId;
    private String position;
    private String title;
    private String viceTitle;

    public long getId() {
        return this.f18625id;
    }

    public void setId(long j) {
        this.f18625id = j;
    }

    public String getjId() {
        return this.jId;
    }

    public void setjId(String str) {
        this.jId = str;
    }

    public String getIsLogin() {
        return this.isLogin;
    }

    public void setIsLogin(String str) {
        this.isLogin = str;
    }

    public String getGoodsUrl() {
        return this.goodsUrl;
    }

    public void setGoodsUrl(String str) {
        this.goodsUrl = str;
    }

    public String getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(String str) {
        this.goodsId = str;
    }

    public String getActId() {
        return this.actId;
    }

    public void setActId(String str) {
        this.actId = str;
    }

    public String getViceTitle() {
        return this.viceTitle;
    }

    public void setViceTitle(String str) {
        this.viceTitle = str;
    }

    public String getActType() {
        return this.actType;
    }

    public void setActType(String str) {
        this.actType = str;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String str) {
        this.position = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public void setImgSrc(String str) {
        this.imgSrc = str;
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(String str) {
        this.goodsType = str;
    }
}
