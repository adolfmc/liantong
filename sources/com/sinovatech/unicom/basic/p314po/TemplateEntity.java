package com.sinovatech.unicom.basic.p314po;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.TemplateEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class TemplateEntity {
    private List<AdvertiseEntity> advertiseEntityList;
    private String background;
    private boolean haveSpace;
    private String idx;
    private boolean isIndexFrom1;
    private boolean showTitle;
    private String targetUrl;
    private String templateId;
    private String templateTitle;
    private String templateTitleColorId;
    private String titltImg;
    private String updateCount;
    private String viceTitle;

    public String getTemplateId() {
        return this.templateId;
    }

    public void setTemplateId(String str) {
        this.templateId = str;
    }

    public String getTemplateTitle() {
        return this.templateTitle;
    }

    public void setTemplateTitle(String str) {
        this.templateTitle = str;
    }

    public String getTemplateTitleColorId() {
        return this.templateTitleColorId;
    }

    public void setTemplateTitleColorId(String str) {
        this.templateTitleColorId = str;
    }

    public boolean isHaveSpace() {
        return this.haveSpace;
    }

    public void setHaveSpace(boolean z) {
        this.haveSpace = z;
    }

    public List<AdvertiseEntity> getAdvertiseEntityList() {
        if (this.advertiseEntityList == null) {
            this.advertiseEntityList = new ArrayList();
        }
        return this.advertiseEntityList;
    }

    public void setAdvertiseEntityList(List<AdvertiseEntity> list) {
        this.advertiseEntityList = list;
    }

    public boolean isShowTitle() {
        return this.showTitle;
    }

    public void setShowTitle(boolean z) {
        this.showTitle = z;
    }

    public String getTitltImg() {
        return this.titltImg;
    }

    public void setTitltImg(String str) {
        this.titltImg = str;
    }

    public boolean isIndexFrom1() {
        return this.isIndexFrom1;
    }

    public void setIndexFrom1(boolean z) {
        this.isIndexFrom1 = z;
    }

    public String getUpdateCount() {
        return this.updateCount;
    }

    public void setUpdateCount(String str) {
        this.updateCount = str;
    }

    public String getViceTitle() {
        return this.viceTitle;
    }

    public void setViceTitle(String str) {
        this.viceTitle = str;
    }

    public String getBackground() {
        return this.background;
    }

    public void setBackground(String str) {
        this.background = str;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public void setTargetUrl(String str) {
        this.targetUrl = str;
    }

    public String getIdx() {
        return this.idx;
    }

    public void setIdx(String str) {
        this.idx = str;
    }
}
