package com.sinovatech.unicom.basic.p314po;

import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeTabEntity {
    private Integer cacheTime;
    private DataDTO data;
    private String respCode;
    private String respMessage;
    private String respTime;
    private String transId;

    public Integer getCacheTime() {
        return this.cacheTime;
    }

    public void setCacheTime(Integer num) {
        this.cacheTime = num;
    }

    public DataDTO getData() {
        if (this.data == null) {
            this.data = new DataDTO();
        }
        return this.data;
    }

    public void setData(DataDTO dataDTO) {
        this.data = dataDTO;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String str) {
        this.respCode = str;
    }

    public String getRespMessage() {
        return this.respMessage;
    }

    public void setRespMessage(String str) {
        this.respMessage = str;
    }

    public String getRespTime() {
        return this.respTime;
    }

    public void setRespTime(String str) {
        this.respTime = str;
    }

    public String getTransId() {
        return this.transId;
    }

    public void setTransId(String str) {
        this.transId = str;
    }

    /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class DataDTO {
        private boolean blackBarOpenFlag;
        private String blackBarUrl;
        private String cityPageSwitch;
        private String edopAppidList;
        private boolean guidPageOpenFlag;
        private int h5CDNCacheTime;
        private IndexHeadTabDTO indexHeadTab;
        private int indexHeadTabTimeout;
        private IndexSelectedTabDTO indexSelectedTab;
        private String jsWhiteDoman;
        private String noLoginBallFeeUrl;
        private String noLoginBallFlowUrl;
        private String noLoginBallIntegral;
        private String noLoginBallVoiceUrl;
        private ServicePageCfgCWDTO servicePageCfgCW;
        private boolean slipperyFlag;
        private boolean vConsoleFlag;
        private String vpnBlackDomain;
        private String watermarkFlag;
        private boolean indexHeadTabOpenFlag = true;
        private boolean indexSelectedTabOpenFlag = true;
        private boolean miniProgramOpenUrlFlag = true;
        private String signDiffImg = "";
        private String signImg = "";
        private String signedImg = "";
        private String signUrl = "";
        private String indexSelectedTabDefaultUrl = "";

        public String getWatermarkFlag() {
            return this.watermarkFlag;
        }

        public void setWatermarkFlag(String str) {
            this.watermarkFlag = str;
        }

        public boolean isBlackBarOpenFlag() {
            return this.blackBarOpenFlag;
        }

        public void setBlackBarOpenFlag(boolean z) {
            this.blackBarOpenFlag = z;
        }

        public String getBlackBarUrl() {
            return this.blackBarUrl;
        }

        public void setBlackBarUrl(String str) {
            this.blackBarUrl = str;
        }

        public boolean isSlipperyFlag() {
            return this.slipperyFlag;
        }

        public void setSlipperyFlag(boolean z) {
            this.slipperyFlag = z;
        }

        public String getVpnBlackDomain() {
            return this.vpnBlackDomain;
        }

        public void setVpnBlackDomain(String str) {
            this.vpnBlackDomain = str;
        }

        public String getJsWhiteDoman() {
            return this.jsWhiteDoman;
        }

        public void setJsWhiteDoman(String str) {
            this.jsWhiteDoman = str;
        }

        public String getCityPageSwitch() {
            return this.cityPageSwitch;
        }

        public void setCityPageSwitch(String str) {
            this.cityPageSwitch = str;
        }

        public String getNoLoginBallFeeUrl() {
            return this.noLoginBallFeeUrl;
        }

        public void setNoLoginBallFeeUrl(String str) {
            this.noLoginBallFeeUrl = str;
        }

        public String getNoLoginBallFlowUrl() {
            return this.noLoginBallFlowUrl;
        }

        public void setNoLoginBallFlowUrl(String str) {
            this.noLoginBallFlowUrl = str;
        }

        public String getNoLoginBallVoiceUrl() {
            return this.noLoginBallVoiceUrl;
        }

        public void setNoLoginBallVoiceUrl(String str) {
            this.noLoginBallVoiceUrl = str;
        }

        public String getNoLoginBallIntegral() {
            return this.noLoginBallIntegral;
        }

        public void setNoLoginBallIntegral(String str) {
            this.noLoginBallIntegral = str;
        }

        public int getIndexHeadTabTimeout() {
            return this.indexHeadTabTimeout;
        }

        public void setIndexHeadTabTimeout(int i) {
            this.indexHeadTabTimeout = i;
        }

        public boolean getGuidPageOpenFlag() {
            return this.guidPageOpenFlag;
        }

        public void setGuidPageOpenFlag(boolean z) {
            this.guidPageOpenFlag = z;
        }

        public IndexHeadTabDTO getIndexHeadTab() {
            if (this.indexHeadTab == null) {
                this.indexHeadTab = new IndexHeadTabDTO();
            }
            return this.indexHeadTab;
        }

        public void setIndexHeadTab(IndexHeadTabDTO indexHeadTabDTO) {
            this.indexHeadTab = indexHeadTabDTO;
        }

        public boolean getIndexHeadTabOpenFlag() {
            return this.indexHeadTabOpenFlag;
        }

        public void setIndexHeadTabOpenFlag(boolean z) {
            this.indexHeadTabOpenFlag = z;
        }

        public IndexSelectedTabDTO getIndexSelectedTab() {
            if (this.indexSelectedTab == null) {
                this.indexSelectedTab = new IndexSelectedTabDTO();
            }
            return this.indexSelectedTab;
        }

        public void setIndexSelectedTab(IndexSelectedTabDTO indexSelectedTabDTO) {
            this.indexSelectedTab = indexSelectedTabDTO;
        }

        public boolean getIndexSelectedTabOpenFlag() {
            return this.indexSelectedTabOpenFlag;
        }

        public void setIndexSelectedTabOpenFlag(boolean z) {
            this.indexSelectedTabOpenFlag = z;
        }

        public boolean getMiniProgramOpenUrlFlag() {
            return this.miniProgramOpenUrlFlag;
        }

        public void setMiniProgramOpenUrlFlag(boolean z) {
            this.miniProgramOpenUrlFlag = z;
        }

        public ServicePageCfgCWDTO getServicePageCfgCW() {
            if (this.servicePageCfgCW == null) {
                this.servicePageCfgCW = new ServicePageCfgCWDTO();
            }
            return this.servicePageCfgCW;
        }

        public void setServicePageCfgCW(ServicePageCfgCWDTO servicePageCfgCWDTO) {
            this.servicePageCfgCW = servicePageCfgCWDTO;
        }

        public String getSignDiffImg() {
            return this.signDiffImg;
        }

        public void setSignDiffImg(String str) {
            this.signDiffImg = str;
        }

        public String getSignImg() {
            return this.signImg;
        }

        public void setSignImg(String str) {
            this.signImg = str;
        }

        public String getSignedImg() {
            return this.signedImg;
        }

        public String getSignUrl() {
            return this.signUrl;
        }

        public void setSignUrl(String str) {
            this.signUrl = str;
        }

        public void setSignedImg(String str) {
            this.signedImg = str;
        }

        public boolean getVConsoleFlag() {
            return this.vConsoleFlag;
        }

        public void setVConsoleFlag(boolean z) {
            this.vConsoleFlag = z;
        }

        public String getIndexSelectedTabDefaultUrl() {
            return this.indexSelectedTabDefaultUrl;
        }

        public void setIndexSelectedTabDefaultUrl(String str) {
            this.indexSelectedTabDefaultUrl = str;
        }

        public int getH5CDNCacheTime() {
            return this.h5CDNCacheTime;
        }

        public void setH5CDNCacheTime(int i) {
            this.h5CDNCacheTime = i;
        }

        public String getEdopAppidList() {
            return this.edopAppidList;
        }

        public void setEdopAppidList(String str) {
            this.edopAppidList = str;
        }

        /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexHeadTabDTO */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class IndexHeadTabDTO {
            private List<TabCfgArrayDTO> tabCfgArray;

            public List<TabCfgArrayDTO> getTabCfgArray() {
                if (this.tabCfgArray == null) {
                    this.tabCfgArray = new ArrayList();
                }
                return this.tabCfgArray;
            }

            public void setTabCfgArray(List<TabCfgArrayDTO> list) {
                this.tabCfgArray = list;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexHeadTabDTO$TabCfgArrayDTO */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class TabCfgArrayDTO {
                private String miniProgramUrl = "";
                private String title = "";
                private String url = "";

                public String getMiniProgramUrl() {
                    return this.miniProgramUrl;
                }

                public void setMiniProgramUrl(String str) {
                    this.miniProgramUrl = str;
                }

                public String getTitle() {
                    return this.title;
                }

                public void setTitle(String str) {
                    this.title = str;
                }

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }

        /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexSelectedTabDTO */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static class IndexSelectedTabDTO {
            private List<TabCfgArrayDTO> tabCfgArray;

            public List<TabCfgArrayDTO> getTabCfgArray() {
                if (this.tabCfgArray == null) {
                    this.tabCfgArray = new ArrayList();
                }
                return this.tabCfgArray;
            }

            public void setTabCfgArray(List<TabCfgArrayDTO> list) {
                this.tabCfgArray = list;
            }

            /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
            /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$IndexSelectedTabDTO$TabCfgArrayDTO */
            /* loaded from: E:\11480076_dexfile_execute.dex */
            public static class TabCfgArrayDTO {
                private String h5Url = "";
                private String navCode = "";
                private String navDesc = "";
                private String navIdx = "";
                private String navName = "";
                private String navUrl = "";
                private String appletUrl = "";

                public String getH5Url() {
                    return this.h5Url;
                }

                public void setH5Url(String str) {
                    this.h5Url = str;
                }

                public String getNavCode() {
                    return this.navCode;
                }

                public void setNavCode(String str) {
                    this.navCode = str;
                }

                public String getNavDesc() {
                    return this.navDesc;
                }

                public void setNavDesc(String str) {
                    this.navDesc = str;
                }

                public String getNavIdx() {
                    return this.navIdx;
                }

                public void setNavIdx(String str) {
                    this.navIdx = str;
                }

                public String getNavName() {
                    return this.navName;
                }

                public void setNavName(String str) {
                    this.navName = str;
                }

                public String getNavUrl() {
                    return this.navUrl;
                }

                public void setNavUrl(String str) {
                    this.navUrl = str;
                }

                public String getAppletUrl() {
                    return this.appletUrl;
                }

                public void setAppletUrl(String str) {
                    this.appletUrl = str;
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.basic.po.HomeTabEntity$DataDTO$ServicePageCfgCWDTO */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public static class ServicePageCfgCWDTO {
            private String editMenuTip = "";
            private String headTitle = "";
            private String moveMenuTip = "";
            private String appMenuTip = "";

            public String getAppMenuTip() {
                return this.appMenuTip;
            }

            public void setAppMenuTip(String str) {
                this.appMenuTip = str;
            }

            public String getEditMenuTip() {
                return this.editMenuTip;
            }

            public void setEditMenuTip(String str) {
                this.editMenuTip = str;
            }

            public String getHeadTitle() {
                return this.headTitle;
            }

            public void setHeadTitle(String str) {
                this.headTitle = str;
            }

            public String getMoveMenuTip() {
                return this.moveMenuTip;
            }

            public void setMoveMenuTip(String str) {
                this.moveMenuTip = str;
            }
        }
    }
}
