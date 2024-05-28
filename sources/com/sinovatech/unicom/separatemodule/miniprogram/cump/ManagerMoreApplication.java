package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.Glide;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerMoreApplication {
    private static boolean flag;

    public static void handleMore(final Activity activity, final ViewGroup viewGroup, final ViewGroup viewGroup2) {
        try {
            viewGroup.setVisibility(8);
            Observable.create(new ObservableOnSubscribe<List<String>>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.ManagerMoreApplication.3
                @Override // io.reactivex.ObservableOnSubscribe
                public void subscribe(ObservableEmitter<List<String>> observableEmitter) throws Exception {
                    try {
                        observableEmitter.onNext(ManagerMoreApplication.parseServerData(App.getSharePreferenceUtil().getString("edop-more-cache")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        if (!ManagerMoreApplication.flag) {
                            String syncPost = App.getAsyncHttpClient().syncPost(URLSet.getEdopMoreUrl(), new HashMap());
                            List<String> parseServerData = ManagerMoreApplication.parseServerData(syncPost);
                            App.getSharePreferenceUtil().putString("edop-more-cache", syncPost);
                            observableEmitter.onNext(parseServerData);
                            boolean unused = ManagerMoreApplication.flag = true;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    observableEmitter.onComplete();
                }
            }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<String>>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.ManagerMoreApplication.1
                @Override // io.reactivex.functions.Consumer
                public void accept(List<String> list) throws Exception {
                    if (list.size() > 0) {
                        viewGroup2.removeAllViews();
                        for (int i = 0; i < list.size(); i++) {
                            RelativeLayout relativeLayout = new RelativeLayout(activity);
                            ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(new ViewGroup.LayoutParams(UIUtils.dip2px(28.0f), UIUtils.dip2px(28.0f)));
                            marginLayoutParams.leftMargin = -UIUtils.dip2px(8.0f);
                            relativeLayout.setLayoutParams(marginLayoutParams);
                            relativeLayout.setBackgroundResource(2131231174);
                            relativeLayout.setPadding(UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f), UIUtils.dip2px(5.0f));
                            CircularImage circularImage = new CircularImage(activity);
                            circularImage.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                            circularImage.setScaleType(ImageView.ScaleType.FIT_XY);
                            circularImage.setImageResource(2131231164);
                            relativeLayout.addView(circularImage);
                            Glide.with(activity).load(list.get(i)).into(circularImage);
                            viewGroup2.addView(relativeLayout);
                        }
                        viewGroup.setVisibility(0);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.ManagerMoreApplication.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    viewGroup.setVisibility(8);
                    th.printStackTrace();
                    MsLogUtil.m7980d("加载更多小程序错误：" + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<String> parseServerData(String str) throws Exception {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = new JSONObject(str).getJSONObject("response").getJSONObject("body").getJSONArray("appInfo");
        if (jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getJSONObject(i).getString("appImg"));
            }
        }
        return arrayList;
    }
}
