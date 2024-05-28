package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGuanzhuDialog;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceGuanzhuDialog {
    private static Dialog guanzhuDialog;
    public static boolean isLandscape;
    private static Disposable subscribe;
    private static String zhuboName;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface GuanzhuClick {
        void onClick();
    }

    public static void show(final AppCompatActivity appCompatActivity, final ZhuboDataEntity zhuboDataEntity, final GuanzhuClick guanzhuClick) {
        cancelGuanzhusub();
        subscribe = Observable.timer(10000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGuanzhuDialog$2xJSD_c6Zf6RT2S8zleaUadsgZs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceGuanzhuDialog.lambda$show$2(AppCompatActivity.this, zhuboDataEntity, guanzhuClick, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$2(AppCompatActivity appCompatActivity, ZhuboDataEntity zhuboDataEntity, final GuanzhuClick guanzhuClick, Long l) throws Exception {
        guanzhuDialog = new Dialog(appCompatActivity, 2131952244);
        RelativeLayout relativeLayout = (RelativeLayout) appCompatActivity.getLayoutInflater().inflate(isLandscape ? 2131492984 : 2131492983, (ViewGroup) null);
        GlideApp.with((FragmentActivity) appCompatActivity).load(zhuboDataEntity.getAnchorInfoBean().getUserImg()).placeholder(2131231806).error(2131231806).into((CircularImage) relativeLayout.findViewById(2131296422));
        ((TextView) relativeLayout.findViewById(2131296423)).setText(zhuboDataEntity.getAnchorInfoBean().getUserName());
        ((TextView) relativeLayout.findViewById(2131296421)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGuanzhuDialog$_I4c69Ydq8gCYWhLRwDTP-PUBaE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGuanzhuDialog.lambda$show$0(AudienceGuanzhuDialog.GuanzhuClick.this, view);
            }
        });
        relativeLayout.findViewById(2131299476).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceGuanzhuDialog$tLQsilkAUHauk6yKFultJYAbv0Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceGuanzhuDialog.guanzhuDialog.dismiss();
            }
        });
        guanzhuDialog.setContentView(relativeLayout);
        guanzhuDialog.setCancelable(true);
        guanzhuDialog.setCanceledOnTouchOutside(true);
        Window window = guanzhuDialog.getWindow();
        window.setWindowAnimations(2131952266);
        window.setDimAmount(0.0f);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        try {
            if (zhuboDataEntity.getAnchorInfoBean().getLiveRoom().equals(zhuboName)) {
                guanzhuDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Disposable disposable = subscribe;
        if (disposable != null) {
            disposable.dispose();
            subscribe = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(GuanzhuClick guanzhuClick, View view) {
        guanzhuDialog.dismiss();
        guanzhuClick.onClick();
    }

    public static void cancelGuanzhusub() {
        Dialog dialog = guanzhuDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        Disposable disposable = subscribe;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    public static void setLiveRoom(String str) {
        zhuboName = str;
    }
}
