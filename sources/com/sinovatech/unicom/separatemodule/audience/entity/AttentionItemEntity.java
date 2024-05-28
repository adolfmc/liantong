package com.sinovatech.unicom.separatemodule.audience.entity;

import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.MultiItemEntity;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AttentionItemEntity implements MultiItemEntity {
    private int Type;
    private ShopEntity.DataBean goods;
    private boolean isMoreViewAngle;
    private ListDataEntity liveData;
    private List<GoodListEntity> shopList;
    private SmallVideoEntity.Data smallVideo;

    public List<GoodListEntity> getShopList() {
        return this.shopList;
    }

    public void setShopList(List<GoodListEntity> list) {
        this.shopList = list;
    }

    public boolean isMoreViewAngle() {
        return this.isMoreViewAngle;
    }

    public void setMoreViewAngle(boolean z) {
        this.isMoreViewAngle = z;
    }

    public ShopEntity.DataBean getGoods() {
        return this.goods;
    }

    public void setGoods(ShopEntity.DataBean dataBean) {
        this.goods = dataBean;
    }

    public int getType() {
        return this.Type;
    }

    public void setType(int i) {
        this.Type = i;
    }

    public ListDataEntity getLiveData() {
        return this.liveData;
    }

    public void setLiveData(ListDataEntity listDataEntity) {
        this.liveData = listDataEntity;
    }

    public SmallVideoEntity.Data getSmallVideo() {
        return this.smallVideo;
    }

    public void setSmallVideo(SmallVideoEntity.Data data) {
        this.smallVideo = data;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.entity.MultiItemEntity
    public int getItemType() {
        return this.Type;
    }
}
