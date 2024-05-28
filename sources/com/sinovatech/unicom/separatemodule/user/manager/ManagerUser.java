package com.sinovatech.unicom.separatemodule.user.manager;

import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.hjq.gson.factory.GsonFactory;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.MyUnicomEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJAddressUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJAddressEntity;
import com.sinovatech.unicom.separatemodule.user.entity.HuaFeiEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity;
import com.sinovatech.unicom.separatemodule.user.entity.UserQuanyiEntity;
import com.sinovatech.unicom.separatemodule.user.function.UserDataTotleFunction;
import com.sinovatech.unicom.separatemodule.user.function.UserQuanYiFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerUser {
    private AppCompatActivity activityContext;

    static /* synthetic */ String access$000(ManagerUser managerUser, String str) {
        return managerUser.notNull(str);
    }

    public ManagerUser(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public void getToutiaoBlack() {
        if (App.hasLogined()) {
            UserManager userManager = UserManager.getInstance();
            HashMap hashMap = new HashMap();
            hashMap.put("methodType", "recommend");
            hashMap.put("mobile", userManager.getCurrentPhoneNumber());
            hashMap.put("cityCode", userManager.getUserAreaid());
            hashMap.put("provinceCode", userManager.getCurrentProvinceCode());
            hashMap.put("version", this.activityContext.getResources().getString(2131886969));
            App.toutiaoBalck = false;
            ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.getDataFromService(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser.3
                @Override // io.reactivex.functions.Function
                public String apply(@NonNull String str) throws Exception {
                    JSONObject jSONObject = new JSONObject(str);
                    CacheDataCenter.getInstance().setUserRecommend(str);
                    if ("Y".equals(jSONObject.optString("touTiaoScreen"))) {
                        App.toutiaoBalck = true;
                    } else {
                        App.toutiaoBalck = false;
                    }
                    return str;
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer<String>() { // from class: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser.1
                @Override // io.reactivex.functions.Consumer
                public void accept(String str) throws Exception {
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                }
            });
        }
    }

    public ObservableSubscribeProxy<HuaFeiEntity> getWoDeQianbao(boolean z) {
        Observable<String> rxPost;
        if (z) {
            rxPost = Observable.just(CacheDataCenter.getInstance().getUserQianBaoData());
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("version", this.activityContext.getResources().getString(2131886969));
            rxPost = App.getAsyncHttpClient().rxPost(URLSet.getQianaboUrl(), hashMap);
        }
        return (ObservableSubscribeProxy) rxPost.subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.user.manager.-$$Lambda$ManagerUser$h-Mc5FWfNVP_7epLhgdPB-mwWCs
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerUser.lambda$getWoDeQianbao$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ HuaFeiEntity lambda$getWoDeQianbao$0(String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return new HuaFeiEntity();
        }
        Gson singletonGson = GsonFactory.getSingletonGson();
        HuaFeiEntity huaFeiEntity = (HuaFeiEntity) (!(singletonGson instanceof Gson) ? singletonGson.fromJson(str, (Class<Object>) HuaFeiEntity.class) : NBSGsonInstrumentation.fromJson(singletonGson, str, (Class<Object>) HuaFeiEntity.class));
        if ("0000".equals(huaFeiEntity.getCode()) && !"0".equals(huaFeiEntity.getMyQueryMoneySwitch())) {
            CacheDataCenter.getInstance().setUserQianBaoData(str);
        }
        return huaFeiEntity;
    }

    public ObservableSubscribeProxy<MyUnicomEntity> getNickName(boolean z) {
        Observable<String> rxPost;
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        hashMap.put("yw_code", UserManager.getInstance().getYwCodeDefault());
        if (z) {
            rxPost = Observable.just(CacheDataCenter.getInstance().getUserInfoNew((String) hashMap.get("mobile")));
        } else {
            rxPost = App.getAsyncHttpClient(30, 30, 30).rxPost(URLSet.getMyUnicomDateTotle(), hashMap);
        }
        return (ObservableSubscribeProxy) rxPost.subscribeOn(Schedulers.m1934io())
                .observeOn(Schedulers.computation())
                .map(new UserDataTotleFunction((String) hashMap.get("mobile")))
                .observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<UserQuanyiEntity> getUserHeaderCard(boolean z, String str) {
        Observable<String> rxPost;
        if (z) {
            rxPost = Observable.just(CacheDataCenter.getInstance().getQuanyiNew(str));
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("mobile", str);
            hashMap.put("version", this.activityContext.getResources().getString(2131886969));
            hashMap.put("channelnum", "113000005");
            hashMap.put("provinceCode", UserManager.getInstance().getCurrentProvinceCode());
            rxPost = App.getAsyncHttpClient().rxPost(URLSet.getNewUserQuanyiUrl(), hashMap);
        }
        return (ObservableSubscribeProxy) rxPost.subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new UserQuanYiFunction(str)).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<UserNewTopentity> getNewUserTop(boolean z, String str) {
        Observable<String> rxGet;
        String string = App.getSharePreferenceUtil().getString(PreferenceConstUtils.getCardFloorMaterialIdKey());
        String string2 = App.getSharePreferenceUtil().getString(PreferenceConstUtils.getCardGoodsIdKey());
        if (z) {
            rxGet = Observable.just(CacheDataCenter.getInstance().getNewTopHeader(str));
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("phoneModel", DeviceHelper.getDeviceModel());
            hashMap.put("appUUID", DeviceHelper.getDeviceID(true));
            hashMap.put("randomRefreshCode", App.getPvLogSessionId());
            hashMap.put("materialId", string);
            hashMap.put("goodsId", string2);
            TYCJAddressEntity addressEntity = TYCJAddressUtil.getAddressEntity();
            rxGet = App.getAsyncHttpClient().rxGet(URLSet.getNewTopUrl() + this.activityContext.getResources().getString(2131886969) + "/" + (addressEntity.getLongitude() + "," + addressEntity.getLatitude()), hashMap);
        }
        return (ObservableSubscribeProxy) rxGet.subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new C95454(str)).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C95454 implements Function<String, UserNewTopentity> {
        final /* synthetic */ String val$mobile;

        /*  JADX ERROR: Failed to decode insn: 0x042A: UNKNOWN(0x0043), method: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser.4.apply(java.lang.String):com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x042A: UNKNOWN(0x0043)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        @Override // io.reactivex.functions.Function
        public com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity apply(java.lang.String r9) throws java.lang.Exception {
            /*
                Method dump skipped, instructions count: 1372
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.user.manager.ManagerUser.C95454.apply(java.lang.String):com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity");
        }

        C95454(String str) {
            this.val$mobile = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String notNull(String str) {
        return "null".equals(str) ? "" : str;
    }
}
