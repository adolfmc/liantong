package com.sinovatech.unicom.separatemodule.user.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserServiceOrderEntity {
    String code;
    List<Data> dataList;
    String loginState;
    String message;
    String myOrderSwitch;
    String myOrderTip;

    public String getMyOrderTip() {
        return this.myOrderTip;
    }

    public void setMyOrderTip(String str) {
        this.myOrderTip = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getLoginState() {
        return this.loginState;
    }

    public void setLoginState(String str) {
        this.loginState = str;
    }

    public String getMyOrderSwitch() {
        return this.myOrderSwitch;
    }

    public void setMyOrderSwitch(String str) {
        this.myOrderSwitch = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public List<Data> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<Data> list) {
        this.dataList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class Data {
        String icon;
        String num;
        String status;
        String url;

        public String getNum() {
            return this.num;
        }

        public void setNum(String str) {
            this.num = str;
        }

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

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String str) {
            this.status = str;
        }
    }
}
