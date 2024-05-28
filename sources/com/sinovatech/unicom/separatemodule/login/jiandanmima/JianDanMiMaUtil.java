package com.sinovatech.unicom.separatemodule.login.jiandanmima;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import io.reactivex.functions.Consumer;
import java.util.Map;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class JianDanMiMaUtil {
    public static final String CODE_11 = "11";
    public static final String CODE_12 = "12";
    public static final String CODE_13 = "13";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface JDMMDialogListener {
        void onCancel();

        void onClickOk();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$show$3(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return false;
    }

    public static void handlerFirstResult(final Activity activity, String str, final Map<String, Object> map, String str2, String str3, final Consumer<Boolean> consumer) {
        show(activity, str, str2, str3, new JDMMDialogListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.1
            @Override // com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.JDMMDialogListener
            public void onCancel() {
                try {
                    Consumer.this.accept(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.JDMMDialogListener
            public void onClickOk() {
                JianDanMiMaUtil.gotoWebiview(activity, map, Consumer.this);
            }
        });
    }

    public static void handlerSecondResult(final Activity activity, String str, final Map<String, Object> map, String str2, String str3, final Consumer<Boolean> consumer) {
        show(activity, str, str2, str3, new JDMMDialogListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.2
            @Override // com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.JDMMDialogListener
            public void onCancel() {
                try {
                    Consumer.this.accept(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.JDMMDialogListener
            public void onClickOk() {
                JianDanMiMaUtil.gotoWebiview(activity, map, Consumer.this);
            }
        });
    }

    public static void show(Activity activity, String str, String str2, String str3, final JDMMDialogListener jDMMDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493334, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131297893);
            TextView textView2 = (TextView) inflate.findViewById(2131297894);
            TextView textView3 = (TextView) inflate.findViewById(2131297891);
            ImageView imageView = (ImageView) inflate.findViewById(2131297892);
            textView3.setVisibility(0);
            if (!TextUtils.isEmpty(str3)) {
                textView.setText(str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                if ("1".equals(str)) {
                    textView3.setText("验证码登录");
                }
                if ("2".equals(str)) {
                    if ("on".equals(LoginConfigDataCenter.getInstance().getEntity().getBroadLoginSwitch())) {
                        textView3.setText("身份证登录");
                    } else {
                        textView3.setVisibility(8);
                    }
                }
                if ("4".equals(str)) {
                    textView3.setVisibility(8);
                }
            } else {
                textView3.setText("稍后修改");
            }
            if (TextUtils.isEmpty(str2)) {
                imageView.setVisibility(8);
            } else {
                imageView.setVisibility(0);
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.-$$Lambda$JianDanMiMaUtil$EuHZHXstr182zL_mNnQEXBIayho
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JianDanMiMaUtil.lambda$show$0(dialog, jDMMDialogListener, view);
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.-$$Lambda$JianDanMiMaUtil$WsjjmwwoW-ce5RTiZIygKmx4pTA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    JianDanMiMaUtil.lambda$show$1(dialog, jDMMDialogListener, view);
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.-$$Lambda$JianDanMiMaUtil$tot8Urbe3aKecJI4GJxH2dfXtns
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dialog.cancel();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.-$$Lambda$JianDanMiMaUtil$wN6Dgxfqj6vmokBjy8oJZY1c56Q
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return JianDanMiMaUtil.lambda$show$3(dialogInterface, i2, keyEvent);
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(Dialog dialog, JDMMDialogListener jDMMDialogListener, View view) {
        dialog.cancel();
        jDMMDialogListener.onClickOk();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(Dialog dialog, JDMMDialogListener jDMMDialogListener, View view) {
        dialog.cancel();
        jDMMDialogListener.onCancel();
    }

    public static void gotoWebiview(Activity activity, Map<String, Object> map, final Consumer<Boolean> consumer) {
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(URLSet.getForgetpassword() + "?logintype=" + map.get("logintype"));
        webParamsEntity.setTitle("");
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        String uuid = UUID.randomUUID().toString();
        App.navigateParamsCacheMap.put(uuid, map);
        webParamsEntity.setNavigateParamsUUID(uuid);
        Intent intent = new Intent(activity, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        new AvoidOnResult(activity).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.login.jiandanmima.JianDanMiMaUtil.3
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent2) {
                try {
                    if (Consumer.this != null) {
                        Consumer.this.accept(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
