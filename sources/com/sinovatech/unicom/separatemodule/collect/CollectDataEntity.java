package com.sinovatech.unicom.separatemodule.collect;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CollectDataEntity {
    private String actId;
    private String actType;
    private String algorithm_type;
    private String batch_id;
    private String biz_type;
    private String businessType;
    private String codeId;
    private String commodityId;
    private String exposure_name;
    private String goodSid;
    private String goodsType;
    private String goods_onlyid;
    private String itemName;
    private String maintaining_information;
    private String maintenance_position_code;
    private String manrongActivity;
    private String manrongType;
    private String market_city;
    private String market_provine;
    private String material_id;
    private String pageName;
    private String pbName;
    private String recommendType;
    private String storeyCode;
    private String targetUrl;
    private String template_id;
    private String trace_id;

    private CollectDataEntity(Builder builder) {
        this.itemName = builder.itemName;
        this.pageName = builder.pageName;
        this.pbName = builder.pbName;
        this.storeyCode = builder.storeyCode;
        this.goodSid = builder.goodSid;
        this.codeId = builder.codeId;
        this.actType = builder.actType;
        this.actId = builder.actId;
        this.commodityId = builder.commodityId;
        this.businessType = builder.businessType;
        this.recommendType = builder.recommendType;
        this.targetUrl = builder.targetUrl;
        this.manrongActivity = builder.manrongActivity;
        this.manrongType = builder.manrongType;
        this.exposure_name = builder.exposure_name;
        this.goods_onlyid = builder.goods_onlyid;
        this.maintaining_information = builder.maintaining_information;
        this.maintenance_position_code = builder.maintenance_position_code;
        this.market_provine = builder.market_provine;
        this.market_city = builder.market_city;
        this.biz_type = builder.biz_type;
        this.material_id = builder.material_id;
        this.template_id = builder.template_id;
        this.trace_id = builder.trace_id;
        this.batch_id = builder.batch_id;
        this.algorithm_type = builder.algorithm_type;
        this.goodsType = builder.goodsType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public String getMarket_provine() {
        return this.market_provine;
    }

    public String getMarket_city() {
        return this.market_city;
    }

    public String getItemName() {
        return this.itemName;
    }

    public String getPageName() {
        return this.pageName;
    }

    public String getPbName() {
        return this.pbName;
    }

    public String getStoreyCode() {
        return this.storeyCode;
    }

    public String getGoodSid() {
        return this.goodSid;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public String getActType() {
        return this.actType;
    }

    public String getActId() {
        return this.actId;
    }

    public String getCommodityId() {
        return this.commodityId;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public String getRecommendType() {
        return this.recommendType;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public String getManrongActivity() {
        return this.manrongActivity;
    }

    public String getManrongType() {
        return this.manrongType;
    }

    public String getGoods_onlyid() {
        return this.goods_onlyid;
    }

    public String getMaintainingInformation() {
        return this.maintaining_information;
    }

    public String getmaintenancePositionCode() {
        return this.maintenance_position_code;
    }

    public String getExposure_name() {
        return this.exposure_name;
    }

    public String getBiz_type() {
        return this.biz_type;
    }

    public String getMaterial_id() {
        return this.material_id;
    }

    public String getTemplate_id() {
        return this.template_id;
    }

    public String getTrace_id() {
        return this.trace_id;
    }

    public String getBatch_id() {
        return this.batch_id;
    }

    public String getAlgorithm_type() {
        return this.algorithm_type;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class Builder {
        private String actId;
        private String actType;
        private String algorithm_type;
        private String batch_id;
        private String biz_type;
        private String businessType;
        private String codeId;
        private String commodityId;
        private String exposure_name;
        private String goodSid;
        private String goodsType;
        private String goods_onlyid;
        private String itemName;
        private String maintaining_information;
        private String maintenance_position_code;
        private String manrongActivity;
        private String manrongType;
        private String market_city;
        private String market_provine;
        private String material_id;
        private String pageName;
        private String pbName;
        private String recommendType;
        private String storeyCode;
        private String targetUrl;
        private String template_id;
        private String trace_id;

        private Builder() {
        }

        public Builder setGoodsType(String str) {
            this.goodsType = str;
            return this;
        }

        public Builder setMarket_provine(String str) {
            this.market_provine = str;
            return this;
        }

        public Builder setMarket_city(String str) {
            this.market_city = str;
            return this;
        }

        public Builder setGoods_onlyid(String str) {
            this.goods_onlyid = str;
            return this;
        }

        public Builder setMaintaining_information(String str) {
            this.maintaining_information = str;
            return this;
        }

        public Builder setMaintenance_position_code(String str) {
            this.maintenance_position_code = str;
            return this;
        }

        public Builder setExposure_Name(String str) {
            this.exposure_name = str;
            return this;
        }

        public Builder setItemName(String str) {
            this.itemName = str;
            return this;
        }

        public Builder setPageName(String str) {
            this.pageName = str;
            return this;
        }

        public Builder setPbName(String str) {
            this.pbName = str;
            return this;
        }

        public Builder setStoreyCode(String str) {
            this.storeyCode = str;
            return this;
        }

        public Builder setGoodSid(String str) {
            this.goodSid = str;
            return this;
        }

        public Builder setCodeId(String str) {
            this.codeId = str;
            return this;
        }

        public Builder setActType(String str) {
            this.actType = str;
            return this;
        }

        public Builder setActId(String str) {
            this.actId = str;
            return this;
        }

        public Builder setCommodityId(String str) {
            this.commodityId = str;
            return this;
        }

        public Builder setBusinessType(String str) {
            this.businessType = str;
            return this;
        }

        public Builder setRecommendType(String str) {
            this.recommendType = str;
            return this;
        }

        public Builder setTargetUrl(String str) {
            this.targetUrl = str;
            return this;
        }

        public Builder setManrongActivity(String str) {
            this.manrongActivity = str;
            return this;
        }

        public Builder setBiz_Type(String str) {
            this.biz_type = str;
            return this;
        }

        public Builder setMaterial_id(String str) {
            this.material_id = str;
            return this;
        }

        public Builder setTemplate_id(String str) {
            this.template_id = str;
            return this;
        }

        public Builder setTrace_id(String str) {
            this.trace_id = str;
            return this;
        }

        public Builder setBatch_id(String str) {
            this.batch_id = str;
            return this;
        }

        public Builder setAlgorithm_type(String str) {
            this.algorithm_type = str;
            return this;
        }

        public Builder setManrongType(String str) {
            this.manrongType = str;
            return this;
        }

        public CollectDataEntity build() {
            return new CollectDataEntity(this);
        }
    }
}
