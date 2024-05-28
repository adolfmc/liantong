package com.sinovatech.unicom.separatemodule.baidumap.entity;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CouponsListEntity {
    private String backgroundColor;
    private String backgroundNoColor;
    private String code;
    private List<DataBean> data;
    private String defaultImg;
    private String detailUrl;
    private String msg;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public String getDefaultImg() {
        return this.defaultImg;
    }

    public void setDefaultImg(String str) {
        this.defaultImg = str;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public void setBackgroundColor(String str) {
        this.backgroundColor = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getBackgroundNoColor() {
        return this.backgroundNoColor;
    }

    public void setBackgroundNoColor(String str) {
        this.backgroundNoColor = str;
    }

    public List<DataBean> getData() {
        return this.data;
    }

    public void setData(List<DataBean> list) {
        this.data = list;
    }

    public String getDetailUrl() {
        return this.detailUrl;
    }

    public void setDetailUrl(String str) {
        this.detailUrl = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataBean {
        private String actImg;
        private String cost;
        private String couponId;
        private boolean isMore;
        private PaymentListBean paymentList;
        private String paymentType;
        private String state;
        private String stateDesc;
        private String subTitle;
        private String terminalDesc;
        private String terminalId;
        private String terminalName;
        private String title;

        public String getActImg() {
            return this.actImg;
        }

        public void setActImg(String str) {
            this.actImg = str;
        }

        public String getCost() {
            return this.cost;
        }

        public void setCost(String str) {
            this.cost = str;
        }

        public String getCouponId() {
            return this.couponId;
        }

        public void setCouponId(String str) {
            this.couponId = str;
        }

        public PaymentListBean getPaymentList() {
            return this.paymentList;
        }

        public void setPaymentList(PaymentListBean paymentListBean) {
            this.paymentList = paymentListBean;
        }

        public String getPaymentType() {
            return this.paymentType;
        }

        public void setPaymentType(String str) {
            this.paymentType = str;
        }

        public String getState() {
            return this.state;
        }

        public void setState(String str) {
            this.state = str;
        }

        public String getStateDesc() {
            return this.stateDesc;
        }

        public void setStateDesc(String str) {
            this.stateDesc = str;
        }

        public String getSubTitle() {
            return this.subTitle;
        }

        public void setSubTitle(String str) {
            this.subTitle = str;
        }

        public String getTerminalDesc() {
            return this.terminalDesc;
        }

        public void setTerminalDesc(String str) {
            this.terminalDesc = str;
        }

        public String getTerminalId() {
            return this.terminalId;
        }

        public void setTerminalId(String str) {
            this.terminalId = str;
        }

        public String getTerminalName() {
            return this.terminalName;
        }

        public void setTerminalName(String str) {
            this.terminalName = str;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public boolean isMore() {
            return this.isMore;
        }

        public void setMore(boolean z) {
            this.isMore = z;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class PaymentListBean {
            @SerializedName("01")
            private CouponsListEntity$DataBean$PaymentListBean$_$01Bean _$01;
            @SerializedName("02")
            private CouponsListEntity$DataBean$PaymentListBean$_$02Bean _$02;
            @SerializedName("03")
            private CouponsListEntity$DataBean$PaymentListBean$_$03Bean _$03;
            @SerializedName("04")
            private CouponsListEntity$DataBean$PaymentListBean$_$04Bean _$04;
            @SerializedName("05")
            private CouponsListEntity$DataBean$PaymentListBean$_$05Bean _$05;

            public CouponsListEntity$DataBean$PaymentListBean$_$01Bean get_$01() {
                return this._$01;
            }

            public void set_$01(CouponsListEntity$DataBean$PaymentListBean$_$01Bean couponsListEntity$DataBean$PaymentListBean$_$01Bean) {
                this._$01 = couponsListEntity$DataBean$PaymentListBean$_$01Bean;
            }

            public CouponsListEntity$DataBean$PaymentListBean$_$02Bean get_$02() {
                return this._$02;
            }

            public void set_$02(CouponsListEntity$DataBean$PaymentListBean$_$02Bean couponsListEntity$DataBean$PaymentListBean$_$02Bean) {
                this._$02 = couponsListEntity$DataBean$PaymentListBean$_$02Bean;
            }

            public CouponsListEntity$DataBean$PaymentListBean$_$03Bean get_$03() {
                return this._$03;
            }

            public void set_$03(CouponsListEntity$DataBean$PaymentListBean$_$03Bean couponsListEntity$DataBean$PaymentListBean$_$03Bean) {
                this._$03 = couponsListEntity$DataBean$PaymentListBean$_$03Bean;
            }

            public CouponsListEntity$DataBean$PaymentListBean$_$04Bean get_$04() {
                return this._$04;
            }

            public void set_$04(CouponsListEntity$DataBean$PaymentListBean$_$04Bean couponsListEntity$DataBean$PaymentListBean$_$04Bean) {
                this._$04 = couponsListEntity$DataBean$PaymentListBean$_$04Bean;
            }

            public CouponsListEntity$DataBean$PaymentListBean$_$05Bean get_$05() {
                return this._$05;
            }

            public void set_$05(CouponsListEntity$DataBean$PaymentListBean$_$05Bean couponsListEntity$DataBean$PaymentListBean$_$05Bean) {
                this._$05 = couponsListEntity$DataBean$PaymentListBean$_$05Bean;
            }
        }
    }
}
