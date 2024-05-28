package com.sinovatech.unicom.separatemodule.audience.entity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZhuangXiuEntity {
    private Renovation adRenovation01;
    private Renovation adRenovation02;
    private Renovation adRenovation03;
    private Renovation floatImg01;
    private String message;
    private Renovation pasterRenovation01;
    private Renovation pasterRenovation02;
    private Renovation pasterRenovation03;
    private String statusCode;

    public Renovation getFloatImg01() {
        return this.floatImg01;
    }

    public void setFloatImg01(Renovation renovation) {
        this.floatImg01 = renovation;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public Renovation getAdRenovation01() {
        if (this.adRenovation01 == null) {
            this.adRenovation01 = new Renovation();
        }
        return this.adRenovation01;
    }

    public void setAdRenovation01(Renovation renovation) {
        this.adRenovation01 = renovation;
    }

    public Renovation getAdRenovation02() {
        return this.adRenovation02;
    }

    public void setAdRenovation02(Renovation renovation) {
        this.adRenovation02 = renovation;
    }

    public Renovation getAdRenovation03() {
        return this.adRenovation03;
    }

    public void setAdRenovation03(Renovation renovation) {
        this.adRenovation03 = renovation;
    }

    public Renovation getPasterRenovation01() {
        return this.pasterRenovation01;
    }

    public void setPasterRenovation01(Renovation renovation) {
        this.pasterRenovation01 = renovation;
    }

    public Renovation getPasterRenovation02() {
        return this.pasterRenovation02;
    }

    public void setPasterRenovation02(Renovation renovation) {
        this.pasterRenovation02 = renovation;
    }

    public Renovation getPasterRenovation03() {
        return this.pasterRenovation03;
    }

    public void setPasterRenovation03(Renovation renovation) {
        this.pasterRenovation03 = renovation;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Renovation {
        private String anchorId;

        /* renamed from: id */
        private String f18478id;
        private String imgSrc;
        private String linkUrl;
        private String title;

        public String getAnchorId() {
            return this.anchorId;
        }

        public void setAnchorId(String str) {
            this.anchorId = str;
        }

        public String getId() {
            return this.f18478id;
        }

        public void setId(String str) {
            this.f18478id = str;
        }

        public String getImgSrc() {
            return this.imgSrc;
        }

        public void setImgSrc(String str) {
            this.imgSrc = str;
        }

        public String getLinkUrl() {
            return this.linkUrl;
        }

        public void setLinkUrl(String str) {
            this.linkUrl = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }
    }
}
