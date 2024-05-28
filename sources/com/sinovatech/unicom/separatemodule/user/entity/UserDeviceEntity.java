package com.sinovatech.unicom.separatemodule.user.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserDeviceEntity {
    private String cacheTime;
    private Data data;
    private String respCode;
    private String respMessage;
    private String respTime;
    private String transId;

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    public String getRespTime() {
        return this.respTime;
    }

    public void setRespTime(String str) {
        this.respTime = str;
    }

    public String getRespMessage() {
        return this.respMessage;
    }

    public void setRespMessage(String str) {
        this.respMessage = str;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getCacheTime() {
        return this.cacheTime;
    }

    public void setCacheTime(String str) {
        this.cacheTime = str;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Data {
        private DefaultData defaultData;
        private String deviceInfoDefaultData;
        private String feedBackContent;
        private LeftData leftData;
        private List<RightData> rightData;
        private String showType;

        public DefaultData getDefaultData() {
            return this.defaultData;
        }

        public void setDefaultData(DefaultData defaultData) {
            this.defaultData = defaultData;
        }

        public String getDeviceInfoDefaultData() {
            return this.deviceInfoDefaultData;
        }

        public void setDeviceInfoDefaultData(String str) {
            this.deviceInfoDefaultData = str;
        }

        public String getFeedBackContent() {
            return this.feedBackContent;
        }

        public void setFeedBackContent(String str) {
            this.feedBackContent = str;
        }

        public String getShowType() {
            return this.showType;
        }

        public void setShowType(String str) {
            this.showType = str;
        }

        public LeftData getLeftData() {
            return this.leftData;
        }

        public void setLeftData(LeftData leftData) {
            this.leftData = leftData;
        }

        public List<RightData> getRightData() {
            return this.rightData;
        }

        public void setRightData(List<RightData> list) {
            this.rightData = list;
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class DefaultData {
            private String bannerIcon;
            private String bannerUrls;

            public String getBannerIcon() {
                return this.bannerIcon;
            }

            public void setBannerIcon(String str) {
                this.bannerIcon = str;
            }

            public String getBannerUrls() {
                return this.bannerUrls;
            }

            public void setBannerUrls(String str) {
                this.bannerUrls = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class RightData {
            private String actId;
            private String actType;
            private String blackViceTitle;
            private String floorAlgorithmType;
            private String floorBatchId;
            private String floorDataType;
            private String floorId;
            private String floorMaterialId;
            private String floorTemplateType;
            private String floorTraceId;
            private String goodIcon;
            private String goodInfoStr;
            private String goodLabel;
            private String goodType;
            private String goodTypeCode;
            private String goodUrls;
            private String goodsId;
            private String label;
            private String maintaining_information;
            private String maintenance_position_code;
            private String redViceTitle;
            private String target_url;

            public String getGoodsId() {
                return this.goodsId;
            }

            public void setGoodsId(String str) {
                this.goodsId = str;
            }

            public String getGoodTypeCode() {
                return this.goodTypeCode;
            }

            public void setGoodTypeCode(String str) {
                this.goodTypeCode = str;
            }

            public String getActId() {
                return this.actId;
            }

            public void setActId(String str) {
                this.actId = str;
            }

            public String getActType() {
                return this.actType;
            }

            public void setActType(String str) {
                this.actType = str;
            }

            public String getFloorAlgorithmType() {
                return this.floorAlgorithmType;
            }

            public void setFloorAlgorithmType(String str) {
                this.floorAlgorithmType = str;
            }

            public String getFloorBatchId() {
                return this.floorBatchId;
            }

            public void setFloorBatchId(String str) {
                this.floorBatchId = str;
            }

            public String getFloorDataType() {
                return this.floorDataType;
            }

            public void setFloorDataType(String str) {
                this.floorDataType = str;
            }

            public String getFloorId() {
                return this.floorId;
            }

            public void setFloorId(String str) {
                this.floorId = str;
            }

            public String getFloorMaterialId() {
                return this.floorMaterialId;
            }

            public void setFloorMaterialId(String str) {
                this.floorMaterialId = str;
            }

            public String getFloorTemplateType() {
                return this.floorTemplateType;
            }

            public void setFloorTemplateType(String str) {
                this.floorTemplateType = str;
            }

            public String getFloorTraceId() {
                return this.floorTraceId;
            }

            public void setFloorTraceId(String str) {
                this.floorTraceId = str;
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

            public String getTarget_url() {
                return this.target_url;
            }

            public void setTarget_url(String str) {
                this.target_url = str;
            }

            public String getGoodUrls() {
                return this.goodUrls;
            }

            public void setGoodUrls(String str) {
                this.goodUrls = str;
            }

            public String getBlackViceTitle() {
                return this.blackViceTitle;
            }

            public void setBlackViceTitle(String str) {
                this.blackViceTitle = str;
            }

            public String getGoodIcon() {
                return this.goodIcon;
            }

            public void setGoodIcon(String str) {
                this.goodIcon = str;
            }

            public String getGoodInfoStr() {
                return this.goodInfoStr;
            }

            public void setGoodInfoStr(String str) {
                this.goodInfoStr = str;
            }

            public String getGoodLabel() {
                return this.goodLabel;
            }

            public void setGoodLabel(String str) {
                this.goodLabel = str;
            }

            public String getGoodType() {
                return this.goodType;
            }

            public void setGoodType(String str) {
                this.goodType = str;
            }

            public String getLabel() {
                return this.label;
            }

            public void setLabel(String str) {
                this.label = str;
            }

            public String getRedViceTitle() {
                return this.redViceTitle;
            }

            public void setRedViceTitle(String str) {
                this.redViceTitle = str;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class LeftData {
            private String actId;
            private String actType;
            private String devcideIcon;
            private String floorAlgorithmType;
            private String floorBatchId;
            private String floorDataType;
            private String floorId;
            private String floorMaterialId;
            private String floorTemplateType;
            private String floorTraceId;
            private String goodsId;
            private String labelStr;
            private String maintaining_information;
            private String maintenance_position_code;
            private String moreTitle;
            private String moreTitleUrls;
            private String showIcon;
            private String target_url;
            private String title;
            private String useDeviceStr;
            private String useDeviceTimeStr;

            public String getActId() {
                return this.actId;
            }

            public void setActId(String str) {
                this.actId = str;
            }

            public String getActType() {
                return this.actType;
            }

            public void setActType(String str) {
                this.actType = str;
            }

            public String getFloorAlgorithmType() {
                return this.floorAlgorithmType;
            }

            public void setFloorAlgorithmType(String str) {
                this.floorAlgorithmType = str;
            }

            public String getFloorBatchId() {
                return this.floorBatchId;
            }

            public void setFloorBatchId(String str) {
                this.floorBatchId = str;
            }

            public String getFloorDataType() {
                return this.floorDataType;
            }

            public void setFloorDataType(String str) {
                this.floorDataType = str;
            }

            public String getFloorId() {
                return this.floorId;
            }

            public void setFloorId(String str) {
                this.floorId = str;
            }

            public String getFloorMaterialId() {
                return this.floorMaterialId;
            }

            public void setFloorMaterialId(String str) {
                this.floorMaterialId = str;
            }

            public String getFloorTemplateType() {
                return this.floorTemplateType;
            }

            public void setFloorTemplateType(String str) {
                this.floorTemplateType = str;
            }

            public String getFloorTraceId() {
                return this.floorTraceId;
            }

            public void setFloorTraceId(String str) {
                this.floorTraceId = str;
            }

            public String getGoodsId() {
                return this.goodsId;
            }

            public void setGoodsId(String str) {
                this.goodsId = str;
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

            public String getTarget_url() {
                return this.target_url;
            }

            public void setTarget_url(String str) {
                this.target_url = str;
            }

            public String getDevcideIcon() {
                return this.devcideIcon;
            }

            public void setDevcideIcon(String str) {
                this.devcideIcon = str;
            }

            public String getLabelStr() {
                return this.labelStr;
            }

            public void setLabelStr(String str) {
                this.labelStr = str;
            }

            public String getMoreTitle() {
                return this.moreTitle;
            }

            public void setMoreTitle(String str) {
                this.moreTitle = str;
            }

            public String getMoreTitleUrls() {
                return this.moreTitleUrls;
            }

            public void setMoreTitleUrls(String str) {
                this.moreTitleUrls = str;
            }

            public String getShowIcon() {
                return this.showIcon;
            }

            public void setShowIcon(String str) {
                this.showIcon = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getUseDeviceStr() {
                return this.useDeviceStr;
            }

            public void setUseDeviceStr(String str) {
                this.useDeviceStr = str;
            }

            public String getUseDeviceTimeStr() {
                return this.useDeviceTimeStr;
            }

            public void setUseDeviceTimeStr(String str) {
                this.useDeviceTimeStr = str;
            }
        }
    }
}
