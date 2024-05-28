package com.sinovatech.unicom.separatemodule.audience.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.applog.tracker.Tracker;
import com.dueeeke.videoplayer.util.NetworkUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.audience.adpter.EasyResponseAdapter;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class InputDialog extends Dialog implements View.OnClickListener {
    private static final int MSG_NO_INTERNET = 2;
    private static final int MSG_OVER_MAX = 3;
    private static final int MSG_SEND = 1;
    private static final int MSG_SHOW_KEYBOARD = 4;
    private int MAX_CHAT_INPUT_LENGTH;
    private EasyResponseAdapter adapter;
    private String atUserId;
    private boolean isMgr;
    private List<String> list;
    private Activity mActivity;
    private EditText mEditText;
    private InputMethodManager mInputMethodManager;
    private OnTextSendListener mOnTextSendListener;
    private TextView mSendBtn;
    private Toast mToast;
    private final String mUserId;
    private Handler mWeakHandler;
    private String mWillSendMsg;
    private RecyclerView rlvUseFul;
    private TextView tvAtUser;
    private TextView tvBtnAll;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnTextSendListener {
        void onTextSend(String str);
    }

    public InputDialog(Activity activity, String str) {
        super(activity, 2131952211);
        this.MAX_CHAT_INPUT_LENGTH = 600;
        this.mWeakHandler = new Handler(new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.util.InputDialog.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        if (InputDialog.this.mWillSendMsg.isEmpty() || InputDialog.this.mWillSendMsg.trim().isEmpty()) {
                            InputDialog.this.showTextToast("请输入聊天内容");
                            return false;
                        }
                        InputDialog inputDialog = InputDialog.this;
                        inputDialog.sendMessage(inputDialog.mWillSendMsg);
                        break;
                        break;
                    case 2:
                        InputDialog.this.showTextToast("当前无网络连接");
                        break;
                    case 3:
                        InputDialog.this.showTextToast("聊天字数超过限制");
                        break;
                    case 4:
                        if (InputDialog.this.mEditText != null) {
                            InputDialog.this.mEditText.requestFocus();
                            break;
                        }
                        break;
                }
                return false;
            }
        });
        this.mUserId = str;
        this.mInputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        this.mWillSendMsg = "";
        this.mActivity = activity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTextToast(String str) {
        Toast toast = this.mToast;
        if (toast == null) {
            Activity activity = this.mActivity;
            if (activity != null) {
                this.mToast = Toast.makeText(activity, str, 0);
            }
        } else {
            toast.setText(str);
        }
        Toast toast2 = this.mToast;
        if (toast2 != null) {
            toast2.show();
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        setWindow();
    }

    private void setWindow() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        window.setLayout(displayMetrics.widthPixels, getWindow().getAttributes().height);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        attributes.dimAmount = 0.0f;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setSoftInputMode(52);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(2131492971);
        this.mSendBtn = (TextView) findViewById(2131298252);
        this.mEditText = (EditText) findViewById(2131296944);
        this.mEditText.requestFocus();
        this.mWeakHandler.sendEmptyMessageDelayed(4, 500L);
        this.mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sinovatech.unicom.separatemodule.audience.util.InputDialog.2
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 4) {
                    InputDialog.this.preSendMessage();
                    return true;
                }
                return false;
            }
        });
        this.mSendBtn.setOnClickListener(this);
        this.rlvUseFul = (RecyclerView) findViewById(2131298413);
        this.tvAtUser = (TextView) findViewById(2131298872);
        this.tvAtUser.setOnClickListener(this);
        this.tvBtnAll = (TextView) findViewById(2131298973);
        this.tvBtnAll.setOnClickListener(this);
        int i = 8;
        this.tvBtnAll.setVisibility(this.isMgr ? 0 : 8);
        this.tvAtUser.setVisibility(this.isMgr ? 0 : 8);
        this.rlvUseFul.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.adapter = new EasyResponseAdapter(this.list, new EasyResponseAdapter.OnItemClickedListener() { // from class: com.sinovatech.unicom.separatemodule.audience.util.InputDialog.3
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.EasyResponseAdapter.OnItemClickedListener
            public void onItemClick(View view) {
                String charSequence = ((TextView) view).getText().toString();
                InputDialog.this.mEditText.setText(charSequence);
                InputDialog.this.mEditText.setSelection(charSequence.length());
            }
        });
        this.rlvUseFul.setAdapter(this.adapter);
        RecyclerView recyclerView = this.rlvUseFul;
        List<String> list = this.list;
        if (list != null && list.size() > 0) {
            i = 0;
        }
        recyclerView.setVisibility(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131298252) {
            preSendMessage();
        } else if (id == 2131298872) {
            this.tvAtUser.setVisibility(8);
            this.atUserId = null;
            setEditMaxLength(35);
        } else if (id == 2131298973) {
            atUser("@所有人", "all");
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void atUser(String str, String str2) {
        this.tvAtUser.setText(str);
        this.tvAtUser.setVisibility(0);
        setEditMaxLength(34 - this.tvAtUser.getText().length());
        this.atUserId = str2;
    }

    public void setList(List<String> list) {
        this.list = list;
        EasyResponseAdapter easyResponseAdapter = this.adapter;
        if (easyResponseAdapter != null) {
            easyResponseAdapter.setList(list);
            this.adapter.notifyDataSetChanged();
        }
    }

    private void setEditMaxLength(int i) {
        this.mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void preSendMessage() {
        Message obtain = Message.obtain();
        int networkType = NetworkUtil.getNetworkType(this.mActivity);
        if (networkType == 0 || networkType == 1) {
            obtain.what = 2;
            this.mWeakHandler.sendMessage(obtain);
            return;
        }
        this.mWillSendMsg = this.mEditText.getText().toString();
        obtain.what = 1;
        this.mWeakHandler.sendMessage(obtain);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        InputMethodManager inputMethodManager = this.mInputMethodManager;
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mEditText.getWindowToken(), 0);
        }
        super.dismiss();
    }

    public void setmOnTextSendListener(OnTextSendListener onTextSendListener) {
        this.mOnTextSendListener = onTextSendListener;
    }

    public void sendMessage(String str) {
        if (this.mOnTextSendListener != null) {
            if (TextUtils.isEmpty(this.atUserId)) {
                this.mOnTextSendListener.onTextSend(str);
            } else {
                OnTextSendListener onTextSendListener = this.mOnTextSendListener;
                onTextSendListener.onTextSend(((Object) this.tvAtUser.getText()) + " |*|" + str);
            }
            this.mEditText.setText("");
            this.tvAtUser.setVisibility(8);
            setEditMaxLength(35);
            this.atUserId = null;
            dismiss();
        }
    }

    public void setAtUser(String str, String str2) {
        atUser("@" + str, str2);
    }

    public void setCanAtUser(boolean z) {
        this.isMgr = z;
        if (!z) {
            this.atUserId = null;
        }
        TextView textView = this.tvBtnAll;
        if (textView != null) {
            textView.setVisibility(z ? 0 : 8);
            this.tvAtUser.setVisibility(z ? 0 : 8);
        }
    }
}
