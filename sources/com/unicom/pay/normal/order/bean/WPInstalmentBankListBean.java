package com.unicom.pay.normal.order.bean;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class WPInstalmentBankListBean {
    private List<WPFqInfoBean> merBankList;
    private List<WPFqInfoBean> userBankList;

    public List<WPFqInfoBean> getMerBankList() {
        return this.merBankList;
    }

    public List<WPFqInfoBean> getUserBankList() {
        return this.userBankList;
    }

    public void setMerBankList(List<WPFqInfoBean> list) {
        this.merBankList = list;
    }

    public void setUserBankList(List<WPFqInfoBean> list) {
        this.userBankList = list;
    }
}
