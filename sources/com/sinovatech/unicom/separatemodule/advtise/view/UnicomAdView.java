package com.sinovatech.unicom.separatemodule.advtise.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.view.UnicomAdView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomAdView extends RelativeLayout {
    private AppCompatActivity activityContext;
    private ImageView imageView;
    private TextView textView;

    public UnicomAdView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        initView();
    }

    public UnicomAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activityContext = (AppCompatActivity) context;
        initView();
    }

    private void initView() {
        View inflate = LayoutInflater.from(this.activityContext).inflate(2131493489, (ViewGroup) this, true);
        this.imageView = (ImageView) inflate.findViewById(2131299602);
        this.textView = (TextView) inflate.findViewById(2131299601);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.advtise.view.UnicomAdView$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C82071 extends SimpleTarget<Drawable> {
        final /* synthetic */ AdConfigEntity val$entity;

        C82071(AdConfigEntity adConfigEntity) {
            this.val$entity = adConfigEntity;
        }

        @Override // com.bumptech.glide.request.target.Target
        public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
            onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
        }

        public void onResourceReady(@NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
            ImageView imageView = UnicomAdView.this.imageView;
            final AdConfigEntity adConfigEntity = this.val$entity;
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.advtise.view.-$$Lambda$UnicomAdView$1$Fbp5cCSWacYfEH4MGy6evSuzLWk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UnicomAdView.C82071.lambda$onResourceReady$0(UnicomAdView.C82071.this, adConfigEntity, view);
                }
            });
        }

        public static /* synthetic */ void lambda$onResourceReady$0(C82071 c82071, AdConfigEntity adConfigEntity, View view) {
            UnicomAdView.this.activityContext.startActivity(new Intent(UnicomAdView.this.activityContext, MainActivity.class));
            IntentManager.generateIntentAndGo(UnicomAdView.this.activityContext, adConfigEntity.getAdvertiseTargetURL(), adConfigEntity.getAdvertiseTitle(), false, "get");
            UnicomAdView.this.activityContext.finish();
        }

        @Override // com.bumptech.glide.request.target.BaseTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
            super.onLoadFailed(drawable);
        }
    }

    public void initData(AdConfigEntity adConfigEntity) {
        Glide.with(this.activityContext.getApplicationContext()).load(adConfigEntity.getAdvertiseImageURL()).into((RequestBuilder<Drawable>) new C82071(adConfigEntity));
    }
}
