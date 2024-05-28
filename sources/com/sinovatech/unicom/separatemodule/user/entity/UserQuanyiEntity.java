package com.sinovatech.unicom.separatemodule.user.entity;

import android.graphics.Color;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserQuanyiEntity {
    private String code;
    private String desc;
    private List<EquityArrayBean> equityArray;
    private List<IdentifyArrayBean> identifyArray;
    private MemberBean member;
    private List<PrivilegeArrayBean> privilegeArray;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public MemberBean getMember() {
        return this.member;
    }

    public void setMember(MemberBean memberBean) {
        this.member = memberBean;
    }

    public List<EquityArrayBean> getEquityArray() {
        if (this.equityArray == null) {
            this.equityArray = new ArrayList();
        }
        return this.equityArray;
    }

    public void setEquityArray(List<EquityArrayBean> list) {
        this.equityArray = list;
    }

    public List<IdentifyArrayBean> getIdentifyArray() {
        if (this.identifyArray == null) {
            this.identifyArray = new ArrayList();
        }
        return this.identifyArray;
    }

    public void setIdentifyArray(List<IdentifyArrayBean> list) {
        this.identifyArray = list;
    }

    public List<PrivilegeArrayBean> getPrivilegeArray() {
        if (this.privilegeArray == null) {
            this.privilegeArray = new ArrayList();
        }
        return this.privilegeArray;
    }

    public void setPrivilegeArray(List<PrivilegeArrayBean> list) {
        this.privilegeArray = list;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class MemberBean {
        private String arrowUrl;
        private String cardBackgroundIcon;
        private String cardTextColor;
        private int color;
        private String currentGrowth;
        private String mainTitleColor;
        private String nextGrowth;
        private String packageLinkUrl;
        private String packageName;
        private String plusflag;
        private String scanIcon;
        private String subTitleColor;
        private String vipIcon;
        private String vipLevel;
        private String vipLinkUrl;
        private String vipName;

        public String getPackageLinkUrl() {
            return this.packageLinkUrl;
        }

        public void setPackageLinkUrl(String str) {
            this.packageLinkUrl = str;
        }

        public String getVipName() {
            return this.vipName;
        }

        public void setVipName(String str) {
            this.vipName = str;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public void setPackageName(String str) {
            this.packageName = str;
        }

        public String getVipLinkUrl() {
            return this.vipLinkUrl;
        }

        public void setVipLinkUrl(String str) {
            this.vipLinkUrl = str;
        }

        public String getScanIcon() {
            return this.scanIcon;
        }

        public void setScanIcon(String str) {
            this.scanIcon = str;
        }

        public String getArrowUrl() {
            return this.arrowUrl;
        }

        public void setArrowUrl(String str) {
            this.arrowUrl = str;
        }

        public String getVipIcon() {
            return this.vipIcon;
        }

        public void setVipIcon(String str) {
            this.vipIcon = str;
        }

        public String getCardTextColor() {
            return this.cardTextColor;
        }

        public void setCardTextColor(String str) {
            this.cardTextColor = str;
        }

        public String getMainTitleColor() {
            return this.mainTitleColor;
        }

        public void setMainTitleColor(String str) {
            this.mainTitleColor = str;
        }

        public String getSubTitleColor() {
            return this.subTitleColor;
        }

        public void setSubTitleColor(String str) {
            this.subTitleColor = str;
        }

        public int getColor() {
            if (!TextUtils.isEmpty(this.mainTitleColor)) {
                try {
                    if (this.cardTextColor.contains("#")) {
                        this.color = Color.parseColor(this.mainTitleColor);
                    } else {
                        this.color = Color.parseColor("#" + this.mainTitleColor);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.color = 0;
                }
            } else {
                this.color = 0;
            }
            return this.color;
        }

        public int getSubColor() {
            if (!TextUtils.isEmpty(this.subTitleColor)) {
                try {
                    if (this.subTitleColor.contains("#")) {
                        this.color = Color.parseColor(this.subTitleColor);
                    } else {
                        this.color = Color.parseColor("#" + this.subTitleColor);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    this.color = 0;
                }
            } else {
                this.color = 0;
            }
            return this.color;
        }

        public void setColor(int i) {
            this.color = i;
        }

        public String getNextGrowth() {
            return this.nextGrowth;
        }

        public void setNextGrowth(String str) {
            this.nextGrowth = str;
        }

        public String getVipLevel() {
            return this.vipLevel;
        }

        public void setVipLevel(String str) {
            this.vipLevel = str;
        }

        public String getCardBackgroundIcon() {
            return this.cardBackgroundIcon;
        }

        public void setCardBackgroundIcon(String str) {
            this.cardBackgroundIcon = str;
        }

        public String getCurrentGrowth() {
            return this.currentGrowth;
        }

        public void setCurrentGrowth(String str) {
            this.currentGrowth = str;
        }

        public String getPlusflag() {
            return this.plusflag;
        }

        public void setPlusflag(String str) {
            this.plusflag = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class EquityArrayBean {
        private String icon;
        private String imgc;
        private String title;
        private String url;

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
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

        public String getImgc() {
            return this.imgc;
        }

        public void setImgc(String str) {
            this.imgc = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class IdentifyArrayBean {
        private String icon;
        private String url;

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class PrivilegeArrayBean {
        private String arrowImg;
        private String content;
        private String icon;
        private String title;
        private String url;

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getIcon() {
            return this.icon;
        }

        public void setIcon(String str) {
            this.icon = str;
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

        public String getArrowImg() {
            return this.arrowImg;
        }

        public void setArrowImg(String str) {
            this.arrowImg = str;
        }
    }
}
