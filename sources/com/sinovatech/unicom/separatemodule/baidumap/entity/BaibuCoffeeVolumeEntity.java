package com.sinovatech.unicom.separatemodule.baidumap.entity;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class BaibuCoffeeVolumeEntity {
    private List<TitleListBean> titleList;

    public List<TitleListBean> getTitleList() {
        return this.titleList;
    }

    public void setTitleList(List<TitleListBean> list) {
        this.titleList = list;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class TitleListBean {
        private String icon;
        private String title;

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
    }
}
