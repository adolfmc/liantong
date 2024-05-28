package com.sinovatech.unicom.separatemodule.notice;

import android.text.TextUtils;
import com.sinovatech.unicom.common.URLSet;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoticeConfigEntity {
    private ConfigUrlDTO configUrl;
    private List<FirstLevelDTO> firstLevel;
    private List<SecondLevelDTO> secondLevel;
    private List<ServiceIdDTO> serviceId;

    public ConfigUrlDTO getConfigUrl() {
        if (this.configUrl == null) {
            this.configUrl = new ConfigUrlDTO();
        }
        return this.configUrl;
    }

    public void setConfigUrl(ConfigUrlDTO configUrlDTO) {
        this.configUrl = configUrlDTO;
    }

    public List<FirstLevelDTO> getFirstLevel() {
        return this.firstLevel;
    }

    public void setFirstLevel(List<FirstLevelDTO> list) {
        this.firstLevel = list;
    }

    public List<SecondLevelDTO> getSecondLevel() {
        return this.secondLevel;
    }

    public void setSecondLevel(List<SecondLevelDTO> list) {
        this.secondLevel = list;
    }

    public List<ServiceIdDTO> getServiceId() {
        return this.serviceId;
    }

    public void setServiceId(List<ServiceIdDTO> list) {
        this.serviceId = list;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class ConfigUrlDTO {
        private String collect;
        private String kuaixun;
        private String liuyan;
        private String setting;
        private String xiaoxi;

        public String getCollect() {
            if (TextUtils.isEmpty(this.collect)) {
                this.collect = URLSet.getNoticeCollect();
            }
            return this.collect;
        }

        public void setCollect(String str) {
            this.collect = str;
        }

        public String getKuaixun() {
            if (TextUtils.isEmpty(this.kuaixun)) {
                this.kuaixun = URLSet.getNoticKuaixun();
            }
            return this.kuaixun;
        }

        public void setKuaixun(String str) {
            this.kuaixun = str;
        }

        public String getXiaoxi() {
            if (TextUtils.isEmpty(this.xiaoxi)) {
                this.xiaoxi = URLSet.getNoticXiaoxi();
            }
            return this.xiaoxi;
        }

        public void setXiaoxi(String str) {
            this.xiaoxi = str;
        }

        public String getLiuyan() {
            if (TextUtils.isEmpty(this.liuyan)) {
                this.liuyan = URLSet.getNoticLiuyan();
            }
            return this.liuyan;
        }

        public void setLiuyan(String str) {
            this.liuyan = str;
        }

        public String getSetting() {
            if (TextUtils.isEmpty(this.setting)) {
                return URLSet.getNoticeSetting();
            }
            return this.setting;
        }

        public void setSetting(String str) {
            this.setting = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class FirstLevelDTO {
        private boolean checked;
        private String firstClassificationName;
        private String firstLevelImg;
        private String firstLevelNo;

        public String getFirstLevelNo() {
            return this.firstLevelNo;
        }

        public void setFirstLevelNo(String str) {
            this.firstLevelNo = str;
        }

        public String getFirstLevelImg() {
            return this.firstLevelImg;
        }

        public void setFirstLevelImg(String str) {
            this.firstLevelImg = str;
        }

        public String getFirstClassificationName() {
            return this.firstClassificationName;
        }

        public void setFirstClassificationName(String str) {
            this.firstClassificationName = str;
        }

        public boolean isChecked() {
            return this.checked;
        }

        public void setChecked(boolean z) {
            this.checked = z;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class SecondLevelDTO {
        private boolean checked = true;
        private String secondClassificationName;
        private String secondLevelImg;
        private String secondLevelNo;

        public String getSecondClassificationName() {
            return this.secondClassificationName;
        }

        public void setSecondClassificationName(String str) {
            this.secondClassificationName = str;
        }

        public String getSecondLevelImg() {
            return this.secondLevelImg;
        }

        public void setSecondLevelImg(String str) {
            this.secondLevelImg = str;
        }

        public String getSecondLevelNo() {
            return this.secondLevelNo;
        }

        public void setSecondLevelNo(String str) {
            this.secondLevelNo = str;
        }

        public boolean isChecked() {
            return this.checked;
        }

        public void setChecked(boolean z) {
            this.checked = z;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ServiceIdDTO {
        private boolean checked;
        private String serviceId;
        private String serviceName;

        public String getServiceId() {
            return this.serviceId;
        }

        public void setServiceId(String str) {
            this.serviceId = str;
        }

        public String getServiceName() {
            return this.serviceName;
        }

        public void setServiceName(String str) {
            this.serviceName = str;
        }

        public boolean isChecked() {
            return this.checked;
        }

        public void setChecked(boolean z) {
            this.checked = z;
        }
    }
}
