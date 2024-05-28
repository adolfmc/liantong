package com.sinovatech.unicom.separatemodule.audience.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ShopEntity {
    private DataBean data;
    private String message;
    private String statusCode;

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
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

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class DataBean {
        private GoodsBean goods;
        private String haveGoods;
        private String roundPraise;
        private String viewNumNow;

        public String getViewNumNow() {
            return this.viewNumNow;
        }

        public void setViewNumNow(String str) {
            this.viewNumNow = str;
        }

        public GoodsBean getGoods() {
            return this.goods;
        }

        public void setGoods(GoodsBean goodsBean) {
            this.goods = goodsBean;
        }

        public String getHaveGoods() {
            return this.haveGoods;
        }

        public void setHaveGoods(String str) {
            this.haveGoods = str;
        }

        public String getRoundPraise() {
            return this.roundPraise;
        }

        public void setRoundPraise(String str) {
            this.roundPraise = str;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class GoodsBean {
            private String coverImgUrl;
            private String desc;
            private String goodsUrl;

            /* renamed from: id */
            private String f18474id;
            private String name;
            private String originalPrice;
            private String price;
            private String robotLookFrequency;
            private String robotPayFrequency;
            private String robotStatus;

            public String getCoverImgUrl() {
                return this.coverImgUrl;
            }

            public void setCoverImgUrl(String str) {
                this.coverImgUrl = str;
            }

            public String getDesc() {
                return this.desc;
            }

            public void setDesc(String str) {
                this.desc = str;
            }

            public String getGoodsUrl() {
                return this.goodsUrl;
            }

            public void setGoodsUrl(String str) {
                this.goodsUrl = str;
            }

            public String getId() {
                return this.f18474id;
            }

            public void setId(String str) {
                this.f18474id = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getOriginalPrice() {
                return this.originalPrice;
            }

            public void setOriginalPrice(String str) {
                this.originalPrice = str;
            }

            public String getPrice() {
                return this.price;
            }

            public void setPrice(String str) {
                this.price = str;
            }

            public String getRobotLookFrequency() {
                return this.robotLookFrequency;
            }

            public void setRobotLookFrequency(String str) {
                this.robotLookFrequency = str;
            }

            public String getRobotPayFrequency() {
                return this.robotPayFrequency;
            }

            public void setRobotPayFrequency(String str) {
                this.robotPayFrequency = str;
            }

            public String getRobotStatus() {
                return this.robotStatus;
            }

            public void setRobotStatus(String str) {
                this.robotStatus = str;
            }
        }
    }
}
