package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.user.entity.UserFootPrintEntity;
import com.sinovatech.unicom.separatemodule.user.function.UserFootPrintFuction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUserFootPrint {
    private static ManagerUserFootPrint managerUserFootPrint;

    public static ManagerUserFootPrint getInstance() {
        if (managerUserFootPrint == null) {
            synchronized (ManagerUserFootPrint.class) {
                if (managerUserFootPrint == null) {
                    managerUserFootPrint = new ManagerUserFootPrint();
                }
            }
        }
        return managerUserFootPrint;
    }

    public ObservableSubscribeProxy<UserFootPrintEntity> getUserFootPrint(AppCompatActivity appCompatActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", App.getInstance().getVersion());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getFootPrint(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new UserFootPrintFuction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(appCompatActivity));
    }

    public void saveCache(String str) {
        CacheDataCenter.getInstance().saveFootPrintData(str);
    }

    private String getCache() {
        return CacheDataCenter.getInstance().getFootPrintData();
    }

    public UserFootPrintEntity cacheData() {
        JSONObject optJSONObject;
        try {
            String cache = getCache();
            if (!TextUtils.isEmpty(cache) && (optJSONObject = new JSONObject(cache).optJSONObject("data")) != null) {
                UserFootPrintEntity userFootPrintEntity = new UserFootPrintEntity();
                String optString = optJSONObject.optString("footMarkFloorTitle");
                String optString2 = optJSONObject.optString("footMarkSwitch");
                String optString3 = optJSONObject.optString("shoppingCartImgSrc");
                optJSONObject.optString("shoppingCartNum");
                String optString4 = optJSONObject.optString("shoppingCartTitle");
                String optString5 = optJSONObject.optString("shoppingCartUrls");
                if (!TextUtils.isEmpty(optString3) && !TextUtils.isEmpty(optString5) && !TextUtils.isEmpty(optString4)) {
                    userFootPrintEntity.setShow(true);
                } else {
                    userFootPrintEntity.setShow(false);
                }
                userFootPrintEntity.setFootMarkFloorTitle(optString);
                userFootPrintEntity.setFootMarkSwitch(optString2);
                userFootPrintEntity.setShoppingCartImgSrc(optString3);
                userFootPrintEntity.setShoppingCartNum("");
                userFootPrintEntity.setShoppingCartTitle(optString4);
                userFootPrintEntity.setShoppingCartUrls(optString5);
                return userFootPrintEntity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserFootPrintEntity getDefaultData() {
        try {
            UserFootPrintEntity userFootPrintEntity = new UserFootPrintEntity();
            userFootPrintEntity.setFootMarkSwitch("1");
            userFootPrintEntity.setShoppingCartTitle("购物车");
            userFootPrintEntity.setShoppingCartImgSrc("https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20230831/jpg/1693452290985.jpg");
            userFootPrintEntity.setShoppingCartUrls("https://card.10010.com/terminal/myCart?homePageFlag=1");
            userFootPrintEntity.setShow(true);
            return userFootPrintEntity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
