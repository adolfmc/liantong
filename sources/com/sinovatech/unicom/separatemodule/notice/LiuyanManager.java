package com.sinovatech.unicom.separatemodule.notice;

import android.support.p086v7.app.AppCompatActivity;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiuyanManager {
    private AppCompatActivity activityContext;

    public LiuyanManager(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void queryLiuyan(final Consumer<Integer> consumer) {
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.liuyanUrl(), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, Integer>() { // from class: com.sinovatech.unicom.separatemodule.notice.LiuyanManager.1
            @Override // io.reactivex.functions.Function
            public Integer apply(String str) throws Exception {
                JSONObject jSONObject = new JSONObject(str);
                if ("0000".equals(jSONObject.optString("code"))) {
                    int parseInt = Integer.parseInt(jSONObject.optString("data"));
                    if (parseInt < 0) {
                        parseInt = 0;
                    }
                    return Integer.valueOf(parseInt);
                }
                return 0;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$LiuyanManager$-PZO5gYItXKuSgk_DLgZzUYWJ1M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiuyanManager.lambda$queryLiuyan$0(Consumer.this, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queryLiuyan$0(Consumer consumer, Throwable th) throws Exception {
        consumer.accept(0);
        th.printStackTrace();
    }

    public void clearLiuyanHongian(final Consumer<String> consumer) {
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.clearLiuyan(), new HashMap()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.notice.LiuyanManager.2
            @Override // io.reactivex.functions.Function
            public String apply(String str) throws Exception {
                return str;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(consumer, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.notice.-$$Lambda$LiuyanManager$q7flQpL-gcQpJaaZNFaMVuuqgKU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiuyanManager.lambda$clearLiuyanHongian$1(Consumer.this, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clearLiuyanHongian$1(Consumer consumer, Throwable th) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", "9999");
        consumer.accept(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
    }
}
