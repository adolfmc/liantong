package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.view.VideoCommonMoreDialog;
import com.sinovatech.unicom.separatemodule.videocenter.entity.NewsInfoEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class VideoCommonMoreDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBack {
        void clicked1(Dialog dialog, NewsInfoEntity newsInfoEntity);

        void clicked2(Dialog dialog, NewsInfoEntity newsInfoEntity);
    }

    public static void show(AppCompatActivity appCompatActivity, final NewsInfoEntity newsInfoEntity, final CallBack callBack) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131951850);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131493537, (ViewGroup) null);
        linearLayout.findViewById(2131297708).setVisibility(newsInfoEntity.isStick() ? 8 : 0);
        linearLayout.findViewById(2131297691).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$VideoCommonMoreDialog$0i9Bz-h2NRwBOjBZsmKGm-cIxrE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCommonMoreDialog.lambda$show$0(VideoCommonMoreDialog.CallBack.this, dialog, newsInfoEntity, view);
            }
        });
        linearLayout.findViewById(2131297732).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$VideoCommonMoreDialog$xnTHtafTN9shyaniPlJNHj7Vkk8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoCommonMoreDialog.lambda$show$1(VideoCommonMoreDialog.CallBack.this, dialog, newsInfoEntity, view);
            }
        });
        linearLayout.findViewById(2131298897).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$VideoCommonMoreDialog$gcHjwU6uDNfvBczz02nHqTSgaHo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(CallBack callBack, Dialog dialog, NewsInfoEntity newsInfoEntity, View view) {
        if (callBack != null) {
            callBack.clicked1(dialog, newsInfoEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(CallBack callBack, Dialog dialog, NewsInfoEntity newsInfoEntity, View view) {
        if (callBack != null) {
            callBack.clicked2(dialog, newsInfoEntity);
        }
    }
}
