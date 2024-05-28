package com.sinovatech.unicom.basic.p315ui.manager;

import android.support.p086v7.app.AppCompatActivity;
import com.baidu.location.BDLocation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerHomeCommon */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerHomeCommon {
    private AppCompatActivity activityContext;

    public ManagerHomeCommon(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void pushFujinyineyeting(BDLocation bDLocation) {
        HashMap hashMap = new HashMap();
        hashMap.put("jingdu", bDLocation.getLongitude() + "");
        hashMap.put("weidu", bDLocation.getLatitude() + "");
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        App.getAsyncHttpClient().rxGet(URLSet.getYingyetingHuodongInfo(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$ManagerHomeCommon$qmQNXV6B_stGdRSbeuSOAdCdTLY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("pushFujinyineyeting", "---->" + ((String) obj));
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.manager.-$$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }
}
