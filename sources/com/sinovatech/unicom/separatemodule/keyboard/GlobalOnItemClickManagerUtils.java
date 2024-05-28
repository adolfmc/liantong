package com.sinovatech.unicom.separatemodule.keyboard;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class GlobalOnItemClickManagerUtils {
    private static GlobalOnItemClickManagerUtils instance;
    private static Context mContext;
    private EditText mEditText;

    public static GlobalOnItemClickManagerUtils getInstance(Context context) {
        mContext = context;
        if (instance == null) {
            synchronized (GlobalOnItemClickManagerUtils.class) {
                if (instance == null) {
                    instance = new GlobalOnItemClickManagerUtils();
                }
            }
        }
        return instance;
    }

    public void attachToEditText(EditText editText) {
        this.mEditText = editText;
    }

    public AdapterView.OnItemClickListener getOnItemClickListener(final int i) {
        return new AdapterView.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.keyboard.GlobalOnItemClickManagerUtils.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
                NBSActionInstrumentation.onItemClickEnter(view, i2, this);
                Tracker.onItemClick(adapterView, view, i2, j);
                Object adapter = adapterView.getAdapter();
                if (adapter instanceof EmotionGridViewAdapter) {
                    EmotionGridViewAdapter emotionGridViewAdapter = (EmotionGridViewAdapter) adapter;
                    if (i2 == emotionGridViewAdapter.getCount() - 1) {
                        GlobalOnItemClickManagerUtils.this.mEditText.dispatchKeyEvent(new KeyEvent(0, 67));
                    } else {
                        try {
                            String item = emotionGridViewAdapter.getItem(i2);
                            int selectionStart = GlobalOnItemClickManagerUtils.this.mEditText.getSelectionStart();
                            if (selectionStart > 295) {
                                NBSActionInstrumentation.onItemClickExit();
                                return;
                            }
                            StringBuilder sb = new StringBuilder(GlobalOnItemClickManagerUtils.this.mEditText.getText().toString());
                            sb.insert(selectionStart, item);
                            GlobalOnItemClickManagerUtils.this.mEditText.setText(SpanStringUtils.getEmotionContent(i, GlobalOnItemClickManagerUtils.mContext, GlobalOnItemClickManagerUtils.this.mEditText, sb.toString()));
                            GlobalOnItemClickManagerUtils.this.mEditText.setSelection(selectionStart + item.length());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                NBSActionInstrumentation.onItemClickExit();
            }
        };
    }
}
