package com.sinovatech.unicom.basic.p315ui.home.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Process;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.CleanUtils;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.p315ui.activity.JianBanActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.InitUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.util.DialogUtils */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DialogUtils {
    private static final String TAG = "DialogUtils";

    public static void checkYinsiTiShiDialog(final Context context, final String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String str2 = "";
            String str3 = "";
            String str4 = "";
            String str5 = "";
            if ("1".equals(str)) {
                str2 = "温馨提示";
                str3 = "需同意<font color='#e60028'><a href='baohuzhiying'>《个人信息保护指引》</a>后我们才能继续为您提供完整服务。<br />我们尊重您的选择权，如您仅希望向中国联通提供必要授权，如网络权限，中国联通APP仅能向您提供快讯信息等基本展示功能。您可点击“<a href='jibengongneng'>了解基本功能模式</a>”了解该模式并选择进行设置。<br />如果您不同意向中国联通提供必要授权，很遗憾，您将无法继续使用中国联通APP。";
                str4 = "同意并继续";
                str5 = "暂不使用，并退出APP";
            } else if ("2".equals(str)) {
                str2 = "温馨提示";
                str3 = "开启基本功能模式，将会退出登录状态，同时中国联通只能提供基础的业务浏览及部分无需登录的功能，是否开启基础功能模式。";
                str4 = "暂不开启";
                str5 = "开启基本功能模式";
            } else if ("3".equals(str)) {
                str2 = "温馨提示";
                str3 = "关闭基本功能模式，发现更多有趣内容，开启更多体验。<br />关闭前，请先自行阅读<font color='#e60028'><a href='yinsi'>《中国联通APP用户隐私政策》</a></font>、<font color='#e60028'><a href='liantongyinsi'>《中国联通用户隐私政策》</a></font>及<font color='#e60028'><a href='xieyi'>《中国联通APP用户服务协议》</a></font>。同意后，可关闭基本功能模式并使用全部功能。";
                str4 = "关闭";
                str5 = "取消";
            } else if ("4".equals(str)) {
                str2 = "温馨提示";
                str3 = "您将解除本设备对中国联通APP相关协议的授权，解除后将清空本设备所记录的相关数据，并退出APP，您需再次同意协议方可使用中国联通APP标准版。";
                str4 = "暂不解除";
                str5 = "解除授权";
            }
            final Dialog dialog = new Dialog(SoulPermission.getInstance().getTopActivity(), 2131952273);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(17);
            View inflate = ((Activity) context).getLayoutInflater().inflate(2131493030, (ViewGroup) null);
            ((LinearLayout) inflate.findViewById(2131296883)).setVisibility(8);
            ((LinearLayout) inflate.findViewById(2131296867)).setVisibility(0);
            ((TextView) inflate.findViewById(2131296870)).setText(str2);
            inflate.findViewById(2131296871).setVisibility(8);
            TextView textView = (TextView) inflate.findViewById(2131296868);
            textView.setText(str4);
            TextView textView2 = (TextView) inflate.findViewById(2131296866);
            textView2.setText(str5);
            final EditText editText = (EditText) inflate.findViewById(2131296869);
            editText.setText(Html.fromHtml(str3));
            editText.setHighlightColor(Color.parseColor("#00000000"));
            editText.setMovementMethod(LinkMovementMethod.getInstance());
            Editable text = editText.getText();
            if (text instanceof Spannable) {
                int length = text.length();
                Editable text2 = editText.getText();
                URLSpan[] uRLSpanArr = (URLSpan[]) text2.getSpans(0, length, URLSpan.class);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                spannableStringBuilder.clearSpans();
                for (final URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.1
                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#ffe60028"));
                            textPaint.setUnderlineText(false);
                        }

                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            try {
                                if ("baohuzhiying".equals(uRLSpan.getURL())) {
                                    ((WelcomeClient) context).checkYinsiDialog();
                                    dialog.cancel();
                                } else if ("jibengongneng".equals(uRLSpan.getURL())) {
                                    DialogUtils.checkYinsiTiShiDialog(context, "2");
                                    dialog.cancel();
                                } else if ("yinsi".equals(uRLSpan.getURL())) {
                                    DialogUtils.toXieyi(context, URLSet.getUserPrivacy());
                                } else if ("xieyi".equals(uRLSpan.getURL())) {
                                    DialogUtils.toXieyi(context, URLSet.getUserserver());
                                } else if ("liantongyinsi".equals(uRLSpan.getURL())) {
                                    DialogUtils.toXieyi(context, URLSet.getunicom_yinsizhengce());
                                }
                            } catch (Exception unused) {
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }, text2.getSpanStart(uRLSpan), text2.getSpanEnd(uRLSpan), 33);
                }
                editText.setText(spannableStringBuilder);
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                editText.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.2
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        NBSActionInstrumentation.onLongClickEventEnter(view, this);
                        editText.setVisibility(0);
                        NBSActionInstrumentation.onLongClickEventExit();
                        return true;
                    }
                });
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.-$$Lambda$DialogUtils$HW0zXkOjH6UCeB-Gdkf3NAdSVlk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DialogUtils.lambda$checkYinsiTiShiDialog$0(str, context, dialog, view);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        if ("1".equals(str)) {
                            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    EventBusUtils.post(new FinishActivityEvent(0));
                                    ((WelcomeClient) context).finish();
                                    Process.killProcess(Process.myPid());
                                    System.exit(0);
                                }
                            }, 1000L);
                        } else if ("2".equals(str)) {
                            PvCurrencyLogUtils.pvJianBanZDLog("1");
                            MsLogUtil.m7979d("APPSESSIONID", "弹窗进入简版");
                            App.setPvLogSessionId();
                            context.startActivity(new Intent(context, JianBanActivity.class));
                            dialog.cancel();
                            ((WelcomeClient) context).finish();
                            App.getSharePreferenceUtil().putBoolean("BasicCustom", true);
                            App.getSharePreferenceUtil().putBoolean("hasShowYinsi", false);
                            LoginManager.logout((WelcomeClient) context);
                        } else if ("3".equals(str)) {
                            dialog.cancel();
                        } else if ("4".equals(str)) {
                            dialog.cancel();
                            PvCurrencyLogUtils.pvJianBanZDLog("2");
                            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.4.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    CleanUtils.cleanAppUserData();
                                }
                            }, 1000L);
                        }
                    } catch (Exception unused) {
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = (int) (UIUtils.getScreenWidth(context) * 0.8d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$checkYinsiTiShiDialog$0(String str, final Context context, Dialog dialog, View view) {
        try {
            if ("1".equals(str)) {
                new Handler().post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            InitUtils.initApp(((WelcomeClient) context).getApplication());
                        } catch (Exception unused) {
                        }
                    }
                });
                ((WelcomeClient) context).init();
            } else if ("2".equals(str)) {
                checkYinsiTiShiDialog(context, "1");
            } else if ("3".equals(str)) {
                PvCurrencyLogUtils.pvJianBanZDLog("3");
                App.getSharePreferenceUtil().putBoolean("BasicCustom", false);
                App.getSharePreferenceUtil().putBoolean("hasShowYinsi", false);
                App.getSharePreferenceUtil().putBoolean("IsBasicCustom", true);
                UIUtils.relaunchApp();
            } else {
                "4".equals(str);
            }
            dialog.cancel();
        } catch (Exception unused) {
        }
    }

    public static void showDialog(Activity activity) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493445, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(3);
            textView.setText(Html.fromHtml("您将解除本设备对中国联通APP相关协议的授权，解除后将清空本设备所记录的相关数据您需再次同意协议方可使用中国联通APP标准版。"));
            textView.setHighlightColor(Color.parseColor("#00000000"));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            CharSequence text = textView.getText();
            if (text instanceof Spannable) {
                int length = text.length();
                Spannable spannable = (Spannable) textView.getText();
                URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                spannableStringBuilder.clearSpans();
                for (URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.5
                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#ffe60028"));
                            textPaint.setUnderlineText(false);
                        }

                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 33);
                }
                textView.setText(spannableStringBuilder);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("解除授权");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setText("取消");
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.ui.home.util.DialogUtils.8
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    return i4 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void toXieyi(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(Html.fromHtml(str).toString()));
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        try {
            context.startActivity(intent);
        } catch (Exception unused) {
            intent.setComponent(null);
            context.startActivity(intent);
        }
    }
}
