package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.adpter.OrderVideoRingAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCentersDataManager;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.NickNameEntity;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OrderVideoRingDialog {
    private static OrderVideoRingAdapter adapter;

    /* renamed from: i */
    private static int f18489i;
    private static CountDownTimer timer;
    private static TextView tvBtnAgreement;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OrderVideoRingInterface {
        void openWeb(String str);

        void orderVideoRing(String str, String str2, String str3, String str4, int i);

        void sendSms(EditText editText);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v14 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [boolean, int] */
    public static void show(final AppCompatActivity appCompatActivity, final String str, final List<VideoRingExplainEntity.DataEntity> list, final boolean z, boolean z2, final OrderVideoRingInterface orderVideoRingInterface) {
        ?? r13;
        final Dialog dialog = new Dialog(appCompatActivity, 2131951850);
        if (z2) {
            f18489i = 0;
        } else {
            f18489i = 1;
        }
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(2131493393, (ViewGroup) null);
        ImageView imageView = (ImageView) linearLayout.findViewById(2131297357);
        final TextView textView = (TextView) linearLayout.findViewById(2131299130);
        final TextView textView2 = (TextView) linearLayout.findViewById(2131298903);
        final EditText editText = (EditText) linearLayout.findViewById(2131296946);
        View findViewById = linearLayout.findViewById(2131297684);
        final ImageView imageView2 = (ImageView) linearLayout.findViewById(2131297331);
        tvBtnAgreement = (TextView) linearLayout.findViewById(2131298895);
        final LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(2131297696);
        final TextView textView3 = (TextView) linearLayout.findViewById(2131299056);
        final TextView textView4 = (TextView) linearLayout.findViewById(2131299017);
        final LinearLayout linearLayout3 = (LinearLayout) linearLayout.findViewById(2131297781);
        if (z && !"Wifi".equals(DeviceHelper.getNETType(appCompatActivity))) {
            linearLayout3.setVisibility(8);
        }
        int size = list.size();
        int i = f18489i;
        if (size > i) {
            textView3.setText(list.get(i).getPrice());
            setagreement(list.get(f18489i), orderVideoRingInterface);
            r13 = 0;
        } else {
            r13 = 0;
            textView3.setText(list.get(0).getPrice());
            setagreement(list.get(0), orderVideoRingInterface);
        }
        linearLayout2.setEnabled(r13);
        RecyclerView recyclerView = (RecyclerView) linearLayout.findViewById(2131298263);
        recyclerView.setLayoutManager(new LinearLayoutManager(appCompatActivity, r13, r13));
        adapter = new OrderVideoRingAdapter(appCompatActivity, list);
        adapter.setSetOnItemVideoRingClick(new OrderVideoRingAdapter.SetOnItemVideoRingClick() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$a7G32MVryfwd1JI2Ma-7HZnyv8A
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OrderVideoRingAdapter.SetOnItemVideoRingClick
            public final void setOnItemOrderVideoRing(int i2) {
                OrderVideoRingDialog.lambda$show$0(textView3, list, textView4, r4, orderVideoRingInterface, i2);
            }
        });
        recyclerView.setAdapter(adapter);
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        if (!TextUtils.isEmpty(currentPhoneNumber)) {
            StringBuilder sb = new StringBuilder(currentPhoneNumber);
            if (currentPhoneNumber.length() >= 11) {
                int length = (currentPhoneNumber.length() - 4) / 2;
                sb.replace(length, length + 4, "****");
            } else if (currentPhoneNumber.length() >= 6) {
                int length2 = (currentPhoneNumber.length() - 3) / 2;
                sb.replace(length2, length2 + 3, "***");
            }
            textView.setText(sb.toString());
        }
        GameCentersDataManager.getInstance().postNickName(appCompatActivity).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$SxQUmWpGtT-Z8vnt1p16jkkf0AU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                OrderVideoRingDialog.lambda$show$1(textView, (NickNameEntity) obj);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$6p5fcrqmG3olEFi9btqCiRrRz2Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialog.dismiss();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$82EzRCyof1HlJ_V9ihIVzPOt0aE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OrderVideoRingDialog.lambda$show$3(textView2, orderVideoRingInterface, editText, view);
            }
        });
        PvCurrencyLogUtils.pvLogLive("", 3, "", "获取验证码", "视频", "", "视频");
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$fa8Lhn6IOHpTaaaSsjN4qaEh23k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OrderVideoRingDialog.lambda$show$4(imageView2, z, appCompatActivity, linearLayout2, editText, view);
            }
        });
        linearLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$OrderVideoRingDialog$lhWY1jH8f9IoJ5VT-5NLInDQhgY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                OrderVideoRingDialog.lambda$show$5(linearLayout2, linearLayout3, orderVideoRingInterface, str, editText, list, appCompatActivity, dialog, imageView2, view);
            }
        });
        editText.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                linearLayout2.setEnabled(editText.getText().toString().length() > 0 && imageView2.isSelected());
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                if (OrderVideoRingDialog.timer != null) {
                    OrderVideoRingDialog.timer.cancel();
                    CountDownTimer unused = OrderVideoRingDialog.timer = null;
                }
            }
        });
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
        for (int i2 = 0; i2 < list.size(); i2++) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", list.get(f18489i).getProductName() + "-立即开通", "视频", "", "视频");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(TextView textView, List list, TextView textView2, int i, OrderVideoRingInterface orderVideoRingInterface, int i2) {
        f18489i = i2;
        textView.setText(((VideoRingExplainEntity.DataEntity) list.get(i2)).getPrice());
        textView2.setText(".00/" + ((VideoRingExplainEntity.DataEntity) list.get(i2)).getDuration());
        int i3 = i2 % i;
        if (i3 == 0) {
            ((VideoRingExplainEntity.DataEntity) list.get(i2)).setSelected(true);
            for (int i4 = 0; i4 < list.size(); i4++) {
                int i5 = i4 % 3;
                if (i5 == 1 || i5 == 2) {
                    ((VideoRingExplainEntity.DataEntity) list.get(i4)).setSelected(false);
                }
            }
        }
        if (i3 == 1) {
            ((VideoRingExplainEntity.DataEntity) list.get(i2)).setSelected(true);
            for (int i6 = 0; i6 < list.size(); i6++) {
                int i7 = i6 % 3;
                if (i7 == 0 || i7 == 2) {
                    ((VideoRingExplainEntity.DataEntity) list.get(i6)).setSelected(false);
                }
            }
        }
        if (i3 == 2) {
            ((VideoRingExplainEntity.DataEntity) list.get(i2)).setSelected(true);
            for (int i8 = 0; i8 < list.size(); i8++) {
                int i9 = i8 % 3;
                if (i9 == 0 || i9 == 1) {
                    ((VideoRingExplainEntity.DataEntity) list.get(i8)).setSelected(false);
                }
            }
        }
        adapter.UpVideoData(list);
        setagreement((VideoRingExplainEntity.DataEntity) list.get(i2), orderVideoRingInterface);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(TextView textView, NickNameEntity nickNameEntity) throws Exception {
        String charSequence = textView.getText().toString();
        textView.setText(charSequence + "(" + nickNameEntity.getRespData().getNickName() + ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r10v3, types: [com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog$1] */
    public static /* synthetic */ void lambda$show$3(final TextView textView, OrderVideoRingInterface orderVideoRingInterface, EditText editText, View view) {
        if (textView.isEnabled()) {
            textView.setEnabled(false);
            timer = new CountDownTimer(60000L, 1000L) { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.1
                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                    TextView textView2 = textView;
                    textView2.setText((j / 1000) + "s后重发");
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    textView.setEnabled(true);
                    textView.setText("获取验证码");
                    if (OrderVideoRingDialog.timer != null) {
                        OrderVideoRingDialog.timer.cancel();
                        CountDownTimer unused = OrderVideoRingDialog.timer = null;
                    }
                }
            }.start();
            orderVideoRingInterface.sendSms(editText);
            PvCurrencyLogUtils.pvLogLive("", 2, "", "获取验证码", "视频", "", "视频");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$4(ImageView imageView, boolean z, AppCompatActivity appCompatActivity, LinearLayout linearLayout, EditText editText, View view) {
        imageView.setSelected(!imageView.isSelected());
        if (z && !"Wifi".equals(DeviceHelper.getNETType(appCompatActivity))) {
            if (imageView.isSelected()) {
                linearLayout.setEnabled(true);
            } else {
                linearLayout.setEnabled(false);
            }
        } else if (imageView.isSelected() && editText.getText().toString().length() > 0) {
            linearLayout.setEnabled(true);
        } else {
            linearLayout.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$5(LinearLayout linearLayout, LinearLayout linearLayout2, OrderVideoRingInterface orderVideoRingInterface, String str, EditText editText, List list, AppCompatActivity appCompatActivity, Dialog dialog, ImageView imageView, View view) {
        if (linearLayout.isEnabled() && linearLayout2.getVisibility() == 0) {
            if (orderVideoRingInterface != null) {
                orderVideoRingInterface.orderVideoRing(str, editText.getText().toString(), ((VideoRingExplainEntity.DataEntity) list.get(f18489i)).getType(), ((VideoRingExplainEntity.DataEntity) list.get(f18489i)).getId(), f18489i);
            }
            if (UIUtils.isDismissDialog(appCompatActivity, dialog)) {
                dialog.dismiss();
            }
        } else if (linearLayout.isEnabled() && linearLayout2.getVisibility() == 8) {
            if (orderVideoRingInterface != null) {
                orderVideoRingInterface.orderVideoRing(str, "netTakeANumber", ((VideoRingExplainEntity.DataEntity) list.get(f18489i)).getType(), ((VideoRingExplainEntity.DataEntity) list.get(f18489i)).getId(), f18489i);
            }
            if (UIUtils.isDismissDialog(appCompatActivity, dialog)) {
                dialog.dismiss();
            }
        } else {
            ToastUtil.showToast(imageView.isSelected() ? "请输入短信验证码" : "请勾选购买协议");
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", ((VideoRingExplainEntity.DataEntity) list.get(f18489i)).getProductName() + "-立即开通", "视频", "", "视频");
    }

    private static void setagreement(final VideoRingExplainEntity.DataEntity dataEntity, final OrderVideoRingInterface orderVideoRingInterface) {
        String str;
        String str2 = "《" + dataEntity.getAgreementName1() + "》";
        if (TextUtils.isEmpty(dataEntity.getAgreementName2())) {
            str = "";
        } else {
            str = "《" + dataEntity.getAgreementName2() + "》";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("我已阅读并同意");
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.4
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull @NotNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#e60027"));
                textPaint.setUnderlineText(false);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull @NotNull View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                OrderVideoRingInterface.this.openWeb(dataEntity.getAgreementContent1());
                NBSActionInstrumentation.onClickEventExit();
            }
        }, 0, str2.length(), 17);
        SpannableString spannableString2 = new SpannableString(str);
        spannableString2.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.5
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(@NonNull @NotNull TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#e60027"));
                textPaint.setUnderlineText(false);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull @NotNull View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                OrderVideoRingInterface.this.openWeb(dataEntity.getAgreementContent2());
                NBSActionInstrumentation.onClickEventExit();
            }
        }, 0, str.length(), 17);
        spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2);
        tvBtnAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        tvBtnAgreement.setText(spannableStringBuilder);
    }
}
