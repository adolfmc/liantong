package com.sinovatech.unicom.separatemodule.user.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserServerRootEntity {
    private String code;
    private String mobile;
    private String parentCode;
    private String reminderTitle;
    private List<UserJingZhunYingXiaoEntity> userJingZhunYingXiaoEntities;
    private List<UserServerEntity> userServerEntities;

    public String getReminderTitle() {
        return this.reminderTitle;
    }

    public void setReminderTitle(String str) {
        this.reminderTitle = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public List<UserServerEntity> getUserServerEntities() {
        return this.userServerEntities;
    }

    public void setUserServerEntities(List<UserServerEntity> list) {
        this.userServerEntities = list;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public List<UserJingZhunYingXiaoEntity> getUserJingZhunYingXiaoEntities() {
        return this.userJingZhunYingXiaoEntities;
    }

    public void setUserJingZhunYingXiaoEntities(List<UserJingZhunYingXiaoEntity> list) {
        this.userJingZhunYingXiaoEntities = list;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String str) {
        this.parentCode = str;
    }
}
