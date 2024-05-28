package com.sinovatech.unicom.separatemodule.notice.utils;

import java.io.Serializable;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Message implements Serializable {
    private static final long serialVersionUID = 1;
    private String audience;
    private String authorId;
    private String content;
    private Date createTime;
    private Date endTime;
    private Object extNode;

    /* renamed from: id */
    private String f18592id;
    private String msgType;
    private int sendScope;
    private int sendType;
    private Date startTime;
    private String title;
    private Date updateTime;
    private String url;

    public Object getExtNode() {
        return this.extNode;
    }

    public void setExtNode(Object obj) {
        this.extNode = obj;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public String getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(String str) {
        this.authorId = str;
    }

    public int getSendType() {
        return this.sendType;
    }

    public void setSendType(int i) {
        this.sendType = i;
    }

    public int getSendScope() {
        return this.sendScope;
    }

    public void setSendScope(int i) {
        this.sendScope = i;
    }

    public String getAudience() {
        return this.audience;
    }

    public void setAudience(String str) {
        this.audience = str;
    }

    public String getId() {
        return this.f18592id;
    }

    public void setId(String str) {
        this.f18592id = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date date) {
        this.endTime = date;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date date) {
        this.updateTime = date;
    }

    public String getMsgType() {
        return this.msgType;
    }

    public void setMsgType(String str) {
        this.msgType = str;
    }
}
