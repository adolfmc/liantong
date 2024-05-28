package com.sinovatech.unicom.separatemodule.gamecenter.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterSignInFunction;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SignInResultDialog extends Dialog {
    private GameCenterActivity activity;
    private GameAdapter adapter;
    private CallBack callBack;
    private ImageView ivClose;
    private ImageView ivGetMore;
    private List<GamesEntity.GamesDataEntity> list;
    private LinearLayout llExtra;
    private Context mContext;
    private SignInResultEntity resultEntity;
    private RecyclerView rlvNewGames;
    private TextView tvExtraValue;
    private TextView tvTipsTitle;
    private TextView tvTomorrowValue;
    private TextView tvtipsValus;
    private View vInterval;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBack {
        void showVideo();

        void startGame(GamesEntity.GamesDataEntity gamesDataEntity);
    }

    public SignInResultDialog(Context context) {
        super(context, 2131952214);
        this.list = new ArrayList();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493277);
        initView();
    }

    private void initView() {
        this.ivClose = (ImageView) findViewById(2131297357);
        this.rlvNewGames = (RecyclerView) findViewById(2131298407);
        this.ivGetMore = (ImageView) findViewById(2131297387);
        this.tvTomorrowValue = (TextView) findViewById(2131299115);
        this.tvExtraValue = (TextView) findViewById(2131298938);
        this.llExtra = (LinearLayout) findViewById(2131297712);
        this.tvtipsValus = (TextView) findViewById(2131299107);
        this.vInterval = findViewById(2131299479);
        this.tvTipsTitle = (TextView) findViewById(2131299106);
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.-$$Lambda$SignInResultDialog$o8I2f3nzrtXaJ8o8px9dMl2idCA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SignInResultDialog.this.dismiss();
            }
        });
        this.ivGetMore.setVisibility(isShowBtn() ? 4 : 0);
        this.ivGetMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.-$$Lambda$SignInResultDialog$izyZin6pQIXz4xVktOfyjxb2hfA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SignInResultDialog.lambda$initView$1(SignInResultDialog.this, view);
            }
        });
        this.rlvNewGames.setLayoutManager(new LinearLayoutManager(this.activity));
        RecyclerView recyclerView = this.rlvNewGames;
        GameAdapter gameAdapter = new GameAdapter(this.activity, this.list, 2131493209);
        this.adapter = gameAdapter;
        recyclerView.setAdapter(gameAdapter);
        this.adapter.setListener(new GameAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.SignInResultDialog.1
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameAdapter.GameClickedListener
            public void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
                if (SignInResultDialog.this.callBack != null) {
                    SignInResultDialog.this.callBack.startGame(gamesDataEntity);
                }
            }
        });
    }

    public static /* synthetic */ void lambda$initView$1(SignInResultDialog signInResultDialog, View view) {
        CallBack callBack = signInResultDialog.callBack;
        if (callBack != null) {
            callBack.showVideo();
        }
    }

    private boolean isShowBtn() {
        return UserManager.getInstance().isYiwang() || LoginManager.isKuandaiOrGuhua();
    }

    @Override // android.app.Dialog
    public void show() {
        String str;
        super.show();
        getExtraAward(this.activity);
        this.llExtra.setVisibility(8);
        this.vInterval.setVisibility(0);
        TextView textView = this.tvTipsTitle;
        if (this.resultEntity.getCurrentIntegral().equals("1")) {
            str = "打卡获得";
        } else {
            str = "连续打卡" + this.resultEntity.getCurrentIntegral() + "天获得";
        }
        textView.setText(str);
        this.tvtipsValus.setText(this.resultEntity.getCurrentIntegral() + "积分");
        this.tvTomorrowValue.setText("明天打卡可获得" + this.resultEntity.getTomorrowIntegral() + "积分");
    }

    public void setList(List<GamesEntity.GamesDataEntity> list) {
        if (list != null) {
            this.list.clear();
            this.list.addAll(list);
        }
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    private void getExtraAward(GameCenterActivity gameCenterActivity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "signin_history");
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.gameSignInHistory(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterSignInFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(gameCenterActivity))).subscribe(new Observer<SignInHistoryEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.view.SignInResultDialog.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            /* JADX WARN: Code restructure failed: missing block: B:28:0x00a1, code lost:
                if (r1.equals("flow") != false) goto L24;
             */
            /* JADX WARN: Removed duplicated region for block: B:42:0x00aa A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:43:0x00d1 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0014 A[SYNTHETIC] */
            @Override // io.reactivex.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onNext(com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity r7) {
                /*
                    Method dump skipped, instructions count: 284
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.gamecenter.view.SignInResultDialog.C87722.onNext(com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity):void");
            }
        });
    }
}
