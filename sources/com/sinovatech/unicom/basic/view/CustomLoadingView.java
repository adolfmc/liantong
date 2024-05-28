package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.content.Intent;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.view.RxView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.NetWorkExceptionActivity;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomLoadingView extends RelativeLayout {
    public static boolean findFlag;
    private AppCompatActivity activityContext;
    private ImageView backImage;
    private ImageView closeImage;
    private LinearLayout errorLayout;
    private LayoutInflater inflater;
    private ImageView loadingImage;
    private RelativeLayout titleLayout;
    private WebReload webReload;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WebReload {
        void reload();
    }

    public CustomLoadingView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        init();
    }

    public CustomLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        init();
    }

    private void init() {
        this.inflater = LayoutInflater.from(this.activityContext);
        View inflate = this.inflater.inflate(2131493549, (ViewGroup) this, false);
        this.loadingImage = (ImageView) inflate.findViewById(2131297809);
        this.errorLayout = (LinearLayout) inflate.findViewById(2131299566);
        Glide.with((FragmentActivity) this.activityContext).asGif().load((Integer) 2131231476).into(this.loadingImage);
        this.titleLayout = (RelativeLayout) inflate.findViewById(2131299560);
        this.backImage = (ImageView) inflate.findViewById(2131299553);
        this.closeImage = (ImageView) inflate.findViewById(2131299555);
        this.backImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomLoadingView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CustomLoadingView.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.closeImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomLoadingView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CustomLoadingView.this.activityContext.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        RxView.clicks((TextView) inflate.findViewById(2131299562)).throttleFirst(1000L, TimeUnit.MILLISECONDS).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.basic.view.CustomLoadingView.3
            @Override // io.reactivex.functions.Consumer
            public void accept(Object obj) throws Exception {
                if (CustomLoadingView.this.webReload != null) {
                    CustomLoadingView.this.webReload.reload();
                }
            }
        });
        ((TextView) inflate.findViewById(2131299561)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomLoadingView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CustomLoadingView.this.activityContext.startActivity(new Intent(CustomLoadingView.this.activityContext, NetWorkExceptionActivity.class));
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        addView(inflate);
    }

    public void setReloadInterface(WebReload webReload) {
        this.webReload = webReload;
    }

    public void showLoading() {
        this.loadingImage.setVisibility(0);
        this.errorLayout.setVisibility(8);
        setVisibility(0);
    }

    public void showLoadingFind() {
        findFlag = true;
        this.loadingImage.setVisibility(8);
        this.errorLayout.setVisibility(8);
        setVisibility(0);
    }

    public void dissMiss() {
        this.loadingImage.setVisibility(8);
        this.errorLayout.setVisibility(8);
        setVisibility(8);
    }

    public void showError() {
        this.loadingImage.setVisibility(8);
        this.errorLayout.setVisibility(0);
        setVisibility(0);
    }

    public void showError(boolean z) {
        this.loadingImage.setVisibility(8);
        this.errorLayout.setVisibility(0);
        this.titleLayout.setVisibility(0);
        setVisibility(0);
    }
}
