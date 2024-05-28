package com.unicom.pay.utils.buried;

import com.unicom.pay.C10546a;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WPBusinessInfoBean {
    public static final String SPLIT = "||";
    private String site_id = "1";
    private String activity_id = "170";
    private String client_time = "-";
    private String local_time = "-";
    private String page_name = "-";
    private String url = "-";
    private String pb_name = "-";
    private String triggerCodeBtn = "-";
    private String action_type = "-";
    private String action_id = "-";
    private String action_name = "-";
    private String time_spent = "-";
    private String page_code = "-";
    private String page_short_code = "-";
    private String sort_code = "-";
    private String code_name = "-";
    private String code_id = "-";
    private String urlref = "-";
    private String last_page_name = "-";
    private String target_page_url = "-";
    private String target_page_name = "-";
    private String exposure_name = "-";
    private String storey = "-";
    private String page_access_source = "-";
    private String source_page = "-";
    private String storey_code = "-";
    private String order_amount = "-";
    private String preferential_amount = "-";
    private String payment_amount = "-";
    private String merchant = "-";
    private String business_type = "-";
    private String payment_method = "-";
    private String payment_results = "-";
    private String cashier_type = "-";
    private String cashier_version = "-";
    private String businessChannel = "-";
    private String channel = "-";
    private String yh_amount = "-";
    private String hb_amount = "-";
    private String fqNum = "-";
    private String fqApr = "-";

    public static WPBusinessInfoBean generateButtonEntity() {
        WPBusinessInfoBean wPBusinessInfoBean = new WPBusinessInfoBean();
        wPBusinessInfoBean.setAction_type("2");
        wPBusinessInfoBean.setAction_id("19");
        wPBusinessInfoBean.setAction_name("点击按钮");
        wPBusinessInfoBean.setCashier_type("sdk");
        wPBusinessInfoBean.setCashier_version("1.8.0");
        wPBusinessInfoBean.setChannel(C10546a.C10576i.f20125a.f20060j);
        return wPBusinessInfoBean;
    }

    public static WPBusinessInfoBean generatePageEntity() {
        WPBusinessInfoBean wPBusinessInfoBean = new WPBusinessInfoBean();
        wPBusinessInfoBean.setAction_type("1");
        wPBusinessInfoBean.setAction_id("1");
        wPBusinessInfoBean.setAction_name("浏览页面");
        wPBusinessInfoBean.setCashier_type("sdk");
        wPBusinessInfoBean.setCashier_version("1.8.0");
        wPBusinessInfoBean.setChannel(C10546a.C10576i.f20125a.f20060j);
        return wPBusinessInfoBean;
    }

    public String getAction_id() {
        return this.action_id;
    }

    public String getAction_name() {
        return this.action_name;
    }

    public String getAction_type() {
        return this.action_type;
    }

    public String getActivity_id() {
        return this.activity_id;
    }

    public String getBusinessChannel() {
        return this.businessChannel;
    }

    public String getBusiness_type() {
        return this.business_type;
    }

    public String getCashier_type() {
        return this.cashier_type;
    }

    public String getCashier_version() {
        return this.cashier_version;
    }

    public String getChannel() {
        return this.channel;
    }

    public String getClient_time() {
        return this.client_time;
    }

    public String getCode_id() {
        return this.code_id;
    }

    public String getCode_name() {
        return this.code_name;
    }

    public String getExposure_name() {
        return this.exposure_name;
    }

    public String getFqApr() {
        return this.fqApr;
    }

    public String getFqNum() {
        return this.fqNum;
    }

    public String getHb_amount() {
        return this.hb_amount;
    }

    public String getLast_page_name() {
        return this.last_page_name;
    }

    public String getLocal_time() {
        return this.local_time;
    }

    public String getMerchant() {
        return this.merchant;
    }

    public String getOrder_amount() {
        return this.order_amount;
    }

    public String getPage_access_source() {
        return this.page_access_source;
    }

    public String getPage_code() {
        return this.page_code;
    }

    public String getPage_name() {
        return this.page_name;
    }

    public String getPage_short_code() {
        return this.page_short_code;
    }

    public String getPayment_amount() {
        return this.payment_amount;
    }

    public String getPayment_method() {
        return this.payment_method;
    }

    public String getPayment_results() {
        return this.payment_results;
    }

    public String getPb_name() {
        return this.pb_name;
    }

    public String getPreferential_amount() {
        return this.preferential_amount;
    }

    public String getSite_id() {
        return this.site_id;
    }

    public String getSort_code() {
        return this.sort_code;
    }

    public String getSource_page() {
        return this.source_page;
    }

    public String getStorey() {
        return this.storey;
    }

    public String getStorey_code() {
        return this.storey_code;
    }

    public String getTarget_page_name() {
        return this.target_page_name;
    }

    public String getTarget_page_url() {
        return this.target_page_url;
    }

    public String getTime_spent() {
        return this.time_spent;
    }

    public String getTriggerCodeBtn() {
        return this.triggerCodeBtn;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrlref() {
        return this.urlref;
    }

    public String getYh_amount() {
        return this.yh_amount;
    }

    public void setAction_id(String str) {
        this.action_id = str;
    }

    public void setAction_name(String str) {
        this.action_name = str;
    }

    public void setAction_type(String str) {
        this.action_type = str;
    }

    public void setActivity_id(String str) {
        this.activity_id = str;
    }

    public void setBusinessChannel(String str) {
        this.businessChannel = str;
    }

    public void setBusiness_type(String str) {
        this.business_type = str;
    }

    public void setCashier_type(String str) {
        this.cashier_type = str;
    }

    public void setCashier_version(String str) {
        this.cashier_version = str;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public void setClient_time(String str) {
        this.client_time = str;
    }

    public void setCode_id(String str) {
        this.code_id = str;
    }

    public void setCode_name(String str) {
        this.code_name = str;
    }

    public void setExposure_name(String str) {
        this.exposure_name = str;
    }

    public void setFqApr(String str) {
        this.fqApr = str;
    }

    public void setFqNum(String str) {
        this.fqNum = str;
    }

    public void setHb_amount(String str) {
        this.hb_amount = str;
    }

    public void setLast_page_name(String str) {
        this.last_page_name = str;
    }

    public void setLocal_time(String str) {
        this.local_time = str;
    }

    public void setMerchant(String str) {
        this.merchant = str;
    }

    public void setOrder_amount(String str) {
        this.order_amount = str;
    }

    public void setPage_access_source(String str) {
        this.page_access_source = str;
    }

    public void setPage_code(String str) {
        this.page_code = str;
    }

    public void setPage_name(String str) {
        this.page_name = str;
    }

    public void setPage_short_code(String str) {
        this.page_short_code = str;
    }

    public void setPayment_amount(String str) {
        this.payment_amount = str;
    }

    public void setPayment_method(String str) {
        this.payment_method = str;
    }

    public void setPayment_results(String str) {
        this.payment_results = str;
    }

    public void setPb_name(String str) {
        this.pb_name = str;
    }

    public void setPreferential_amount(String str) {
        this.preferential_amount = str;
    }

    public void setSite_id(String str) {
        this.site_id = str;
    }

    public void setSort_code(String str) {
        this.sort_code = str;
    }

    public void setSource_page(String str) {
        this.source_page = str;
    }

    public void setStorey(String str) {
        this.storey = str;
    }

    public void setStorey_code(String str) {
        this.storey_code = str;
    }

    public void setTarget_page_name(String str) {
        this.target_page_name = str;
    }

    public void setTarget_page_url(String str) {
        this.target_page_url = str;
    }

    public void setTime_spent(String str) {
        this.time_spent = str;
    }

    public void setTriggerCodeBtn(String str) {
        this.triggerCodeBtn = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUrlref(String str) {
        this.urlref = str;
    }

    public void setYh_amount(String str) {
        this.yh_amount = str;
    }

    public String toBusinessStr() {
        return getSite_id() + SPLIT + getActivity_id() + SPLIT + getClient_time() + SPLIT + getLocal_time() + SPLIT + getPage_name() + SPLIT + getUrl() + SPLIT + getPb_name() + SPLIT + getTriggerCodeBtn() + SPLIT + getAction_type() + SPLIT + getAction_id() + SPLIT + getAction_name() + SPLIT + getTime_spent() + SPLIT + getPage_code() + SPLIT + getPage_short_code() + SPLIT + getSort_code() + SPLIT + getCode_name() + SPLIT + getCode_id() + SPLIT + getUrlref() + SPLIT + getLast_page_name() + SPLIT + getTarget_page_url() + SPLIT + getLast_page_name() + SPLIT + getExposure_name() + SPLIT + getStorey() + SPLIT + getPage_access_source() + SPLIT + getSource_page() + SPLIT + getStorey_code() + SPLIT + getOrder_amount() + SPLIT + getPreferential_amount() + SPLIT + getPayment_amount() + SPLIT + getMerchant() + SPLIT + getBusiness_type() + SPLIT + getPayment_method() + SPLIT + getPayment_results() + SPLIT + getCashier_type() + SPLIT + getCashier_version() + SPLIT + getBusinessChannel() + SPLIT + getChannel() + SPLIT + getYh_amount() + SPLIT + getHb_amount() + SPLIT + getFqNum() + SPLIT + getFqApr();
    }
}
