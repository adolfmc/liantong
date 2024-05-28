package com.sinovatech.unicom.basic.p315ui.home.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.ServiceViewEntity */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ServiceViewEntity {
    private String code;
    private List<Datas> datas;
    private String message;

    public void setCode(String str) {
        this.code = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setDatas(List<Datas> list) {
        this.datas = list;
    }

    public List<Datas> getDatas() {
        return this.datas;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.ServiceViewEntity$Datas */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class Datas {
        private String actId;
        private String actType;
        private String goodsId;
        private String goodsType;
        private String goodsUrl;

        /* renamed from: id */
        private String f18421id;
        private String imgSrc;
        private String position;
        private String recommendContent;
        private String recommended;
        private String showTab;
        private String title;

        public Datas() {
        }

        public String getActType() {
            return this.actType;
        }

        public void setActType(String str) {
            this.actType = str;
        }

        public String getActId() {
            return this.actId;
        }

        public void setActId(String str) {
            this.actId = str;
        }

        public String getId() {
            return this.f18421id;
        }

        public void setId(String str) {
            this.f18421id = str;
        }

        public String getPosition() {
            return this.position;
        }

        public void setPosition(String str) {
            this.position = str;
        }

        public void setShowTab(String str) {
            this.showTab = str;
        }

        public String getShowTab() {
            return this.showTab;
        }

        public void setGoodsUrl(String str) {
            this.goodsUrl = str;
        }

        public String getGoodsUrl() {
            return this.goodsUrl;
        }

        public void setGoodsId(String str) {
            this.goodsId = str;
        }

        public String getGoodsId() {
            return this.goodsId;
        }

        public void setRecommendContent(String str) {
            this.recommendContent = str;
        }

        public String getRecommendContent() {
            return this.recommendContent;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setImgSrc(String str) {
            this.imgSrc = str;
        }

        public String getImgSrc() {
            return this.imgSrc;
        }

        public void setGoodsType(String str) {
            this.goodsType = str;
        }

        public String getGoodsType() {
            return this.goodsType;
        }

        public void setRecommended(String str) {
            this.recommended = str;
        }

        public String getRecommended() {
            return this.recommended;
        }
    }
}
