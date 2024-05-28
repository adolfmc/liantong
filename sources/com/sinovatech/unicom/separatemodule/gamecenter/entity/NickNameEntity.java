package com.sinovatech.unicom.separatemodule.gamecenter.entity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NickNameEntity {
    private String msg;
    private RespDataBean respData;
    private String respcode;

    public RespDataBean getRespData() {
        return this.respData;
    }

    public void setRespData(RespDataBean respDataBean) {
        this.respData = respDataBean;
    }

    public String getRespcode() {
        return this.respcode;
    }

    public void setRespcode(String str) {
        this.respcode = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class RespDataBean {
        private String headIcon;
        private String isShowV;
        private String level;
        private String levelimg;
        private String nickName;
        private String vImgUrl;

        public String getIsShowV() {
            return this.isShowV;
        }

        public void setIsShowV(String str) {
            this.isShowV = str;
        }

        public String getHeadIcon() {
            return this.headIcon;
        }

        public void setHeadIcon(String str) {
            this.headIcon = str;
        }

        public String getLevel() {
            return this.level;
        }

        public void setLevel(String str) {
            this.level = str;
        }

        public String getNickName() {
            return this.nickName;
        }

        public void setNickName(String str) {
            this.nickName = str;
        }

        public String getVImgUrl() {
            return this.vImgUrl;
        }

        public void setVImgUrl(String str) {
            this.vImgUrl = str;
        }

        public String getLevelimg() {
            return this.levelimg;
        }

        public void setLevelimg(String str) {
            this.levelimg = str;
        }
    }
}
