package com.sinovatech.unicom.separatemodule.user.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserLightEntity {
    String actId;
    String actType;
    private String algorithm_type;
    private String batch_id;
    private String biz_type;
    String business_type;
    String commodity_id;
    String goodsType;
    String goods_onlyid;
    String maintaining_information;
    String maintenance_position_code;
    String manrong_activity;
    String manrong_type;
    private String material_id;
    String target_url;
    private String template_id;
    String titleName;
    private String trace_id;
    String trainsId;

    public UserLightEntity() {
    }

    public UserLightEntity(String str, String str2) {
        this.trainsId = str;
        this.titleName = str2;
    }

    public UserLightEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.trainsId = str;
        this.titleName = str2;
        this.actType = str3;
        this.actId = str4;
        this.commodity_id = str5;
        this.business_type = str6;
        this.target_url = str7;
        this.manrong_activity = str8;
        this.manrong_type = str9;
        this.goods_onlyid = str10;
        this.maintaining_information = str11;
        this.maintenance_position_code = str12;
    }

    public UserLightEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19) {
        this.trainsId = str;
        this.titleName = str2;
        this.actType = str3;
        this.actId = str4;
        this.commodity_id = str5;
        this.business_type = str6;
        this.target_url = str7;
        this.manrong_activity = str8;
        this.manrong_type = str9;
        this.goods_onlyid = str10;
        this.maintaining_information = str11;
        this.maintenance_position_code = str12;
        this.biz_type = str13;
        this.material_id = str14;
        this.template_id = str15;
        this.trace_id = str16;
        this.batch_id = str17;
        this.algorithm_type = str18;
        this.goodsType = str19;
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

    public String getCommodity_id() {
        return this.commodity_id;
    }

    public void setCommodity_id(String str) {
        this.commodity_id = str;
    }

    public String getBusiness_type() {
        return this.business_type;
    }

    public void setBusiness_type(String str) {
        this.business_type = str;
    }

    public String getTarget_url() {
        return this.target_url;
    }

    public void setTarget_url(String str) {
        this.target_url = str;
    }

    public String getManrong_activity() {
        return this.manrong_activity;
    }

    public void setManrong_activity(String str) {
        this.manrong_activity = str;
    }

    public String getManrong_type() {
        return this.manrong_type;
    }

    public void setManrong_type(String str) {
        this.manrong_type = str;
    }

    public String getGoods_onlyid() {
        return this.goods_onlyid;
    }

    public void setGoods_onlyid(String str) {
        this.goods_onlyid = str;
    }

    public String getMaintaining_information() {
        return this.maintaining_information;
    }

    public void setMaintaining_information(String str) {
        this.maintaining_information = str;
    }

    public String getMaintenance_position_code() {
        return this.maintenance_position_code;
    }

    public void setMaintenance_position_code(String str) {
        this.maintenance_position_code = str;
    }

    public String getTrainsId() {
        return this.trainsId;
    }

    public void setTrainsId(String str) {
        this.trainsId = str;
    }

    public String getTitleName() {
        return this.titleName;
    }

    public void setTitleName(String str) {
        this.titleName = str;
    }

    public String getBiz_type() {
        return this.biz_type;
    }

    public void setBiz_type(String str) {
        this.biz_type = str;
    }

    public String getMaterial_id() {
        return this.material_id;
    }

    public void setMaterial_id(String str) {
        this.material_id = str;
    }

    public String getTemplate_id() {
        return this.template_id;
    }

    public void setTemplate_id(String str) {
        this.template_id = str;
    }

    public String getTrace_id() {
        return this.trace_id;
    }

    public void setTrace_id(String str) {
        this.trace_id = str;
    }

    public String getBatch_id() {
        return this.batch_id;
    }

    public void setBatch_id(String str) {
        this.batch_id = str;
    }

    public String getAlgorithm_type() {
        return this.algorithm_type;
    }

    public void setAlgorithm_type(String str) {
        this.algorithm_type = str;
    }

    public String getGoodsType() {
        return this.goodsType;
    }

    public void setGoodsType(String str) {
        this.goodsType = str;
    }
}
