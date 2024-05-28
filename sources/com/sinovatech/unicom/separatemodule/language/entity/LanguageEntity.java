package com.sinovatech.unicom.separatemodule.language.entity;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Entity
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LanguageEntity {
    private String cancelBtnPop;
    private String confirmBtnPop;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18543id;
    public boolean isSelect;
    public String languageCode;
    private String languageDesc;
    public String languageName;
    private String reminderPop;
    public String rightToleft;
    private String showDirectionFlag;
    private String switchLanguagePop;
    private String url;
    private String ywshow;

    public long getId() {
        return this.f18543id;
    }

    public void setId(long j) {
        this.f18543id = j;
    }

    public String getLanguageName() {
        return this.languageName;
    }

    public void setLanguageName(String str) {
        this.languageName = str;
    }

    public String getLanguageCode() {
        return this.languageCode;
    }

    public void setLanguageCode(String str) {
        this.languageCode = str;
    }

    public String getRightToleft() {
        return this.rightToleft;
    }

    public void setRightToleft(String str) {
        this.rightToleft = str;
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setSelect(boolean z) {
        this.isSelect = z;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getYwshow() {
        return this.ywshow;
    }

    public void setYwshow(String str) {
        this.ywshow = str;
    }

    public String getLanguageDesc() {
        return this.languageDesc;
    }

    public void setLanguageDesc(String str) {
        this.languageDesc = str;
    }

    public String getReminderPop() {
        return this.reminderPop;
    }

    public void setReminderPop(String str) {
        this.reminderPop = str;
    }

    public String getSwitchLanguagePop() {
        return this.switchLanguagePop;
    }

    public void setSwitchLanguagePop(String str) {
        this.switchLanguagePop = str;
    }

    public String getCancelBtnPop() {
        return this.cancelBtnPop;
    }

    public void setCancelBtnPop(String str) {
        this.cancelBtnPop = str;
    }

    public String getConfirmBtnPop() {
        return this.confirmBtnPop;
    }

    public void setConfirmBtnPop(String str) {
        this.confirmBtnPop = str;
    }

    public String getShowDirectionFlag() {
        return this.showDirectionFlag;
    }

    public void setShowDirectionFlag(String str) {
        this.showDirectionFlag = str;
    }
}
