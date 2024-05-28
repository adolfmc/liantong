package com.sinovatech.unicom.separatemodule.user.entity;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HuaFeiEntity {
    private String code;
    private DataEntity data;
    private String myQueryMoneySwitch;
    private String tip;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public DataEntity getData() {
        return this.data;
    }

    public void setData(DataEntity dataEntity) {
        this.data = dataEntity;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String str) {
        this.tip = str;
    }

    public String getMyQueryMoneySwitch() {
        return this.myQueryMoneySwitch;
    }

    public void setMyQueryMoneySwitch(String str) {
        this.myQueryMoneySwitch = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataEntity {
        private BorrowMoneyEntity borrowMoney;
        private CreditPointEntity creditPoint;
        private MyMoneyBagEntity myMoneyBag;
        private PhoneCostPayEntity phoneCostPay;
        private RemainPhoneCostEntity remainPhoneCost;

        public PhoneCostPayEntity getPhoneCostPay() {
            return this.phoneCostPay;
        }

        public void setPhoneCostPay(PhoneCostPayEntity phoneCostPayEntity) {
            this.phoneCostPay = phoneCostPayEntity;
        }

        public CreditPointEntity getCreditPoint() {
            return this.creditPoint;
        }

        public void setCreditPoint(CreditPointEntity creditPointEntity) {
            this.creditPoint = creditPointEntity;
        }

        public BorrowMoneyEntity getBorrowMoney() {
            return this.borrowMoney;
        }

        public void setBorrowMoney(BorrowMoneyEntity borrowMoneyEntity) {
            this.borrowMoney = borrowMoneyEntity;
        }

        public MyMoneyBagEntity getMyMoneyBag() {
            return this.myMoneyBag;
        }

        public void setMyMoneyBag(MyMoneyBagEntity myMoneyBagEntity) {
            this.myMoneyBag = myMoneyBagEntity;
        }

        public RemainPhoneCostEntity getRemainPhoneCost() {
            return this.remainPhoneCost;
        }

        public void setRemainPhoneCost(RemainPhoneCostEntity remainPhoneCostEntity) {
            this.remainPhoneCost = remainPhoneCostEntity;
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class PhoneCostPayEntity {
            private String bubblePicUrl;
            private String bubbleTag;
            private String datacode;
            private String desc;
            private String link;
            private String money;
            private String title;

            public String getBubbleTag() {
                return this.bubbleTag;
            }

            public void setBubbleTag(String str) {
                this.bubbleTag = str;
            }

            public String getBubblePicUrl() {
                return this.bubblePicUrl;
            }

            public void setBubblePicUrl(String str) {
                this.bubblePicUrl = str;
            }

            public String getMoney() {
                if (TextUtils.isEmpty(this.money) || "null".equals(this.money)) {
                    this.money = "--";
                }
                return this.money;
            }

            public void setMoney(String str) {
                this.money = str;
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String str) {
                this.link = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDatacode() {
                return this.datacode;
            }

            public void setDatacode(String str) {
                this.datacode = str;
            }

            public String getDesc() {
                return this.desc;
            }

            public void setDesc(String str) {
                this.desc = str;
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class CreditPointEntity {
            private String bubblePicUrl;
            private String bubbleTag;
            private String datacode;
            private String link;
            private String point;
            private String title;

            public String getBubbleTag() {
                return this.bubbleTag;
            }

            public void setBubbleTag(String str) {
                this.bubbleTag = str;
            }

            public String getBubblePicUrl() {
                return this.bubblePicUrl;
            }

            public void setBubblePicUrl(String str) {
                this.bubblePicUrl = str;
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String str) {
                this.link = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getPoint() {
                if (TextUtils.isEmpty(this.point) || "null".equals(this.point)) {
                    this.point = "--";
                }
                return this.point;
            }

            public void setPoint(String str) {
                this.point = str;
            }

            public String getDatacode() {
                return this.datacode;
            }

            public void setDatacode(String str) {
                this.datacode = str;
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class BorrowMoneyEntity {
            private String bubblePicUrl;
            private String bubbleTag;
            private String datacode;
            private String link;
            private String money;
            private String title;

            public String getBubbleTag() {
                return this.bubbleTag;
            }

            public void setBubbleTag(String str) {
                this.bubbleTag = str;
            }

            public String getBubblePicUrl() {
                return this.bubblePicUrl;
            }

            public void setBubblePicUrl(String str) {
                this.bubblePicUrl = str;
            }

            public String getMoney() {
                if (TextUtils.isEmpty(this.money) || "null".equals(this.money)) {
                    this.money = "--";
                }
                return this.money;
            }

            public void setMoney(String str) {
                this.money = str;
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String str) {
                this.link = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDatacode() {
                return this.datacode;
            }

            public void setDatacode(String str) {
                this.datacode = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class MyMoneyBagEntity {
            private String bubblePicUrl;
            private String bubbleTag;
            private String datacode;
            private String link;
            private String picUrl;
            private String title;

            public String getBubbleTag() {
                return this.bubbleTag;
            }

            public void setBubbleTag(String str) {
                this.bubbleTag = str;
            }

            public String getBubblePicUrl() {
                return this.bubblePicUrl;
            }

            public void setBubblePicUrl(String str) {
                this.bubblePicUrl = str;
            }

            public String getPicUrl() {
                return this.picUrl;
            }

            public void setPicUrl(String str) {
                this.picUrl = str;
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String str) {
                this.link = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDatacode() {
                return this.datacode;
            }

            public void setDatacode(String str) {
                this.datacode = str;
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class RemainPhoneCostEntity {
            private String bubblePicUrl;
            private String bubbleTag;
            private String datacode;
            private String desc;
            private String link;
            private String money;
            private String title;

            public String getBubbleTag() {
                return this.bubbleTag;
            }

            public void setBubbleTag(String str) {
                this.bubbleTag = str;
            }

            public String getBubblePicUrl() {
                return this.bubblePicUrl;
            }

            public void setBubblePicUrl(String str) {
                this.bubblePicUrl = str;
            }

            public String getMoney() {
                if (TextUtils.isEmpty(this.money) || "null".equals(this.money)) {
                    this.money = "--";
                }
                return this.money;
            }

            public void setMoney(String str) {
                this.money = str;
            }

            public String getLink() {
                return this.link;
            }

            public void setLink(String str) {
                this.link = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDatacode() {
                return this.datacode;
            }

            public void setDatacode(String str) {
                this.datacode = str;
            }

            public String getDesc() {
                return this.desc;
            }

            public void setDesc(String str) {
                this.desc = str;
            }
        }
    }
}
