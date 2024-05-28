package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FiveGEntity {
    private String ErrorMsg;
    private List<BaseStationBean> baseStation;
    private String code;
    private String desc;
    private List<ExperienceHallBean> experienceHall;
    private String fiveGStationTips;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getFiveGStationTips() {
        return this.fiveGStationTips;
    }

    public void setFiveGStationTips(String str) {
        this.fiveGStationTips = str;
    }

    public String getErrorMsg() {
        return this.ErrorMsg;
    }

    public void setErrorMsg(String str) {
        this.ErrorMsg = str;
    }

    public List<BaseStationBean> getBaseStation() {
        return this.baseStation;
    }

    public void setBaseStation(List<BaseStationBean> list) {
        this.baseStation = list;
    }

    public List<ExperienceHallBean> getExperienceHall() {
        return this.experienceHall;
    }

    public void setExperienceHall(List<ExperienceHallBean> list) {
        this.experienceHall = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class BaseStationBean {
        private double epJingDu;
        private double epWeiDu;

        public double getEpJingDu() {
            return this.epJingDu;
        }

        public void setEpJingDu(double d) {
            this.epJingDu = d;
        }

        public double getEpWeiDu() {
            return this.epWeiDu;
        }

        public void setEpWeiDu(double d) {
            this.epWeiDu = d;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ExperienceHallBean {
        private String PinEpActImg;
        private String ehall_frontAddress;
        private double epJingDu;
        private String epName;
        private double epWeiDu;

        /* renamed from: id */
        private String f18509id;

        public String getPinEpActImg() {
            return this.PinEpActImg;
        }

        public void setPinEpActImg(String str) {
            this.PinEpActImg = str;
        }

        public double getEpJingDu() {
            return this.epJingDu;
        }

        public void setEpJingDu(double d) {
            this.epJingDu = d;
        }

        public double getEpWeiDu() {
            return this.epWeiDu;
        }

        public void setEpWeiDu(double d) {
            this.epWeiDu = d;
        }

        public String getId() {
            return this.f18509id;
        }

        public void setId(String str) {
            this.f18509id = str;
        }

        public String getEhall_frontAddress() {
            return this.ehall_frontAddress;
        }

        public void setEhall_frontAddress(String str) {
            this.ehall_frontAddress = str;
        }

        public String getEpName() {
            return this.epName;
        }

        public void setEpName(String str) {
            this.epName = str;
        }
    }
}
