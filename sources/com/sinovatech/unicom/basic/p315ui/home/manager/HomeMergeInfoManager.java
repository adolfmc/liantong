package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity_;
import com.sinovatech.unicom.basic.p315ui.home.function.HomeMergeFunction;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeMergeInfoManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeMergeInfoManager {
    public static final String TAG = "HomeMergeInfoManager";
    private AppCompatActivity activityContext;
    private Box<HomeMergeFuChuangEntity> fuChuangEntityBox = App.getBoxStore().boxFor(HomeMergeFuChuangEntity.class);

    public HomeMergeInfoManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<HomeMergeEntity> getHomeMerge() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("login_type", getAccountType() + "");
            hashMap.put("clientVersion", this.activityContext.getString(2131886969));
            hashMap.put("provinceCode", UserManager.getInstance().getLocateProvinceCode());
            hashMap.put("cityCode", UserManager.getInstance().getLocateCityCode());
            hashMap.put("userMobile", UserManager.getInstance().getCurrentPhoneNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getHomeMergeUrl(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new HomeMergeFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public String getAccountType() {
        if (App.hasLogined()) {
            String currentPhoneType = UserManager.getInstance().getCurrentPhoneType();
            return UserManager.getInstance().isYiwang() ? "2" : "01".equals(currentPhoneType) ? "1" : "02".equals(currentPhoneType) ? "4" : "03".equals(currentPhoneType) ? "3" : "5";
        }
        return "0";
    }

    public void setXuanFuChuangCache(HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
        try {
            List<HomeMergeFuChuangEntity> find = this.fuChuangEntityBox.query().equal(HomeMergeFuChuangEntity_.ywCode, UserManager.getInstance().isYiwang() ? "0" : "1").and().equal(HomeMergeFuChuangEntity_.cityCode, UserManager.getInstance().getLocateCityCode()).and().equal(HomeMergeFuChuangEntity_.provinceCode, UserManager.getInstance().getLocateProvinceCode()).build().find();
            if (find != null) {
                this.fuChuangEntityBox.remove(find);
            }
            if (homeMergeFuChuangEntity != null) {
                this.fuChuangEntityBox.put((Box<HomeMergeFuChuangEntity>) homeMergeFuChuangEntity);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7979d(str, "缓存浮窗数据异常" + e.getMessage());
        }
    }

    public HomeMergeFuChuangEntity getXuanFuChuangCache() {
        try {
            return this.fuChuangEntityBox.query().equal(HomeMergeFuChuangEntity_.ywCode, UserManager.getInstance().isYiwang() ? "0" : "1").and().equal(HomeMergeFuChuangEntity_.cityCode, UserManager.getInstance().getLocateCityCode()).and().equal(HomeMergeFuChuangEntity_.provinceCode, UserManager.getInstance().getLocateProvinceCode()).build().findFirst();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "获取浮窗数据异常:" + e.getMessage());
            return null;
        }
    }
}
