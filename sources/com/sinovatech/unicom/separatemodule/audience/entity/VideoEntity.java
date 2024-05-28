package com.sinovatech.unicom.separatemodule.audience.entity;

import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoEntity {
    private String contentType;
    private String favFlag;
    private String favNum;
    private String headimg;

    /* renamed from: id */
    private String f18476id;
    private String identification;
    private String label;
    private String likeFlag;
    private String likeNum;
    private String name;
    private String nickname;
    private String picpath;
    private String price;
    private String singername;
    private String singleTag;
    private String tjpara;
    private String viewNum;

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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLikeFlag() {
        return this.likeFlag;
    }

    public void setLikeFlag(String str) {
        this.likeFlag = str;
    }

    public String getFavNum() {
        return this.favNum;
    }

    public void setFavNum(String str) {
        this.favNum = str;
    }

    public String getFavFlag() {
        return this.favFlag;
    }

    public void setFavFlag(String str) {
        this.favFlag = str;
    }

    public String getId() {
        return this.f18476id;
    }

    public void setId(String str) {
        this.f18476id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPicpath() {
        return this.picpath;
    }

    public void setPicpath(String str) {
        this.picpath = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public String getLikeNum() {
        return this.likeNum;
    }

    public void setLikeNum(String str) {
        this.likeNum = str;
    }

    public String getViewNum() {
        return this.viewNum;
    }

    public void setViewNum(String str) {
        this.viewNum = str;
    }

    public String getHeadimg() {
        if (this.headimg == null) {
            this.headimg = "";
        }
        return this.headimg;
    }

    public void setHeadimg(String str) {
        this.headimg = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getSingername() {
        return this.singername;
    }

    public void setSingername(String str) {
        this.singername = str;
    }
}
