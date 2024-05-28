package com.sinovatech.unicom.basic.p314po;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* renamed from: com.sinovatech.unicom.basic.po.RightMenuEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RightMenuEntity {
    private String cid;
    private String classifyCode;
    private String desc_info;
    private String icon_url;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18395id;
    private String interfaceUrl;
    private boolean isNeed;
    private String isVideo;
    private String mobile;
    private int state;
    private String title;
    private String typeCode;
    private String unchecked_url;
    private String url;

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public void setId(long j) {
        this.f18395id = j;
    }

    public long getId() {
        return this.f18395id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getDesc_info() {
        return this.desc_info;
    }

    public void setDesc_info(String str) {
        this.desc_info = str;
    }

    public String getIcon_url() {
        return this.icon_url;
    }

    public void setIcon_url(String str) {
        this.icon_url = str;
    }

    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String str) {
        this.typeCode = str;
    }

    public String getInterfaceUrl() {
        return this.interfaceUrl;
    }

    public void setInterfaceUrl(String str) {
        this.interfaceUrl = str;
    }

    public String getIsVideo() {
        return this.isVideo;
    }

    public void setIsVideo(String str) {
        this.isVideo = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public boolean isNeed() {
        return this.isNeed;
    }

    public void setNeed(boolean z) {
        this.isNeed = z;
    }

    public String getUnchecked_url() {
        return this.unchecked_url;
    }

    public void setUnchecked_url(String str) {
        this.unchecked_url = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getClassifyCode() {
        return this.classifyCode;
    }

    public void setClassifyCode(String str) {
        this.classifyCode = str;
    }
}
