package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LabelDataEntity {
    private String chargeFlag;
    private String titleDetial;
    private String titleFlag;
    private List<UnifiedLabelBean> unifiedLabel;

    public String getTitleFlag() {
        return this.titleFlag;
    }

    public void setTitleFlag(String str) {
        this.titleFlag = str;
    }

    public String getTitleDetial() {
        return this.titleDetial;
    }

    public void setTitleDetial(String str) {
        this.titleDetial = str;
    }

    public String getChargeFlag() {
        return this.chargeFlag;
    }

    public void setChargeFlag(String str) {
        this.chargeFlag = str;
    }

    public List<UnifiedLabelBean> getUnifiedLabel() {
        return this.unifiedLabel;
    }

    public void setUnifiedLabel(List<UnifiedLabelBean> list) {
        this.unifiedLabel = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class UnifiedLabelBean {

        /* renamed from: id */
        private String f18510id;
        private String labelFlag;
        private String releaseScope;
        private String serviceLabel;

        public String getId() {
            return this.f18510id;
        }

        public void setId(String str) {
            this.f18510id = str;
        }

        public String getReleaseScope() {
            return this.releaseScope;
        }

        public void setReleaseScope(String str) {
            this.releaseScope = str;
        }

        public String getLabelFlag() {
            return this.labelFlag;
        }

        public void setLabelFlag(String str) {
            this.labelFlag = str;
        }

        public String getServiceLabel() {
            return this.serviceLabel;
        }

        public void setServiceLabel(String str) {
            this.serviceLabel = str;
        }
    }
}
