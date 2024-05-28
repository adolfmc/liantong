package com.sinovatech.unicom.separatemodule.templateholder;

import android.app.Activity;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import com.sinovatech.unicom.basic.eventbus.UserNoptifyEventBus;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class RVItemViewHolder extends RecyclerView.ViewHolder {
    public Activity activityContext;
    private Disposable mDisposable;

    public abstract void bindData(Object obj);

    public RVItemViewHolder(Activity activity, View view) {
        super(view);
        this.activityContext = activity;
    }

    public void setVisibility(boolean z) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.itemView.getLayoutParams();
        if (z) {
            layoutParams.height = -2;
            layoutParams.width = -1;
            this.itemView.setVisibility(0);
        } else {
            this.itemView.setVisibility(8);
            layoutParams.height = 0;
            layoutParams.width = 0;
        }
        this.itemView.setLayoutParams(layoutParams);
    }

    public void sendEventBus() {
        EventBusUtils.post(new UserNoptifyEventBus(0));
    }

    public void baoguang(String str, String str2) {
        baoguang(str, str2, "", "");
    }

    public void baoguang(final String str, final String str2, final String str3, final String str4) {
        Observable.timer(3L, TimeUnit.SECONDS).subscribe(new Observer<Long>() { // from class: com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(@NonNull Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NonNull Disposable disposable) {
                RVItemViewHolder.this.mDisposable = disposable;
            }

            @Override // io.reactivex.Observer
            public void onNext(@NonNull Long l) {
                UIUtils.logD("baoguang", "0000开始曝光结果" + UserFragment.scrollState + 0);
                if (UserFragment.scrollState && RVItemViewHolder.this.itemView.getVisibility() == 0) {
                    PvCurrencyLogUtils.pvlogUserBG(str, str2, str3, str4);
                }
            }
        });
    }
}
