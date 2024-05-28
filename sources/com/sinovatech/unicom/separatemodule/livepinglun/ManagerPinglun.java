package com.sinovatech.unicom.separatemodule.livepinglun;

import android.support.p086v7.app.AppCompatActivity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveReplayEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSaveCommentEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveSvaeReplayEntity;
import com.sinovatech.unicom.separatemodule.livepinglun.function.LiveCommentFunction;
import com.sinovatech.unicom.separatemodule.livepinglun.function.LiveReplayFunction;
import com.sinovatech.unicom.separatemodule.livepinglun.function.LiveSaveReplayFunction;
import com.sinovatech.unicom.separatemodule.livepinglun.function.LiveSveCommentFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerPinglun {
    private AppCompatActivity activityContext;
    private UserManager userManager = UserManager.getInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getDel$1(String str) throws Exception {
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$getUploadImage$0(String str) throws Exception {
        return str;
    }

    public ManagerPinglun(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
    }

    public ObservableSubscribeProxy<LiveCommentEntity> getCommentList(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        hashMap.put("pageSize", "20");
        hashMap.put("pageNum", str2);
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunCommentList(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LiveCommentFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LiveReplayEntity> getReplayList(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("commentId", str);
        hashMap.put("pageSize", "20");
        hashMap.put("pageNum", str2);
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunReplayList(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LiveReplayFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LiveSaveCommentEntity> getSaveComment(String str, String str2, String str3, String str4, String str5) {
        HashMap hashMap = new HashMap();
        hashMap.put("id", str);
        hashMap.put("newsTitle", str2);
        hashMap.put("subTitle", str3);
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("commentContent", str4);
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        hashMap.put("upLoadImgName", str5);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunSaveComment(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LiveSveCommentFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<LiveSvaeReplayEntity> getSaveReplay(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10) {
        HashMap hashMap = new HashMap();
        hashMap.put("newsTitle", str);
        hashMap.put("newsId", str2);
        hashMap.put("commentType", str10);
        hashMap.put("replyCommentId", str3);
        hashMap.put("replyId", str5);
        hashMap.put("replyCommentPhone", str4);
        hashMap.put("repledRole", str6);
        hashMap.put("upLoadImgName", str7);
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("commentContent", str9);
        hashMap.put("replayCommentContent", str8);
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunSaveReplay(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new LiveSaveReplayFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> getDianzan(String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put("reqId", str3);
        hashMap.put("praisedMobile", str4);
        hashMap.put("pointChannel", str);
        hashMap.put("pointType", str2);
        hashMap.put("reqChannel", "svideo");
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPingluncsPrice(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, String>() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.ManagerPinglun.1
            @Override // io.reactivex.functions.Function
            public String apply(@NonNull String str5) throws Exception {
                return str5;
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> getUploadImage(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("imgBase", str);
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunUploadImage(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$ManagerPinglun$fEDLXtO8u68zYsFanDfIiA08SKM
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerPinglun.lambda$getUploadImage$0((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    public ObservableSubscribeProxy<String> getDel(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("reqId", str2);
        hashMap.put("reqChannel", "sVideo");
        hashMap.put("belongPro", this.userManager.getCurrentProvinceCode());
        hashMap.put("belongCity", this.userManager.getUserAreaid());
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.getPinglunDeleteCommentOrDelete(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.livepinglun.-$$Lambda$ManagerPinglun$nFAkqnQ7HkuJUUF7uS6R7usGVgk
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ManagerPinglun.lambda$getDel$1((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }
}
