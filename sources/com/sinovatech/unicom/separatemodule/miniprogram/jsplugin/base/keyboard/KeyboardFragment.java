package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.separatemodule.keyboard.EmotiomComplateFragment;
import com.sinovatech.unicom.separatemodule.keyboard.EmotionKeyboard;
import com.sinovatech.unicom.separatemodule.keyboard.FragmentFactory;
import com.sinovatech.unicom.separatemodule.keyboard.GlobalOnItemClickManagerUtils;
import com.sinovatech.unicom.separatemodule.keyboard.HorizontalRecyclerviewAdapter;
import com.sinovatech.unicom.separatemodule.keyboard.ImageModel;
import com.sinovatech.unicom.separatemodule.keyboard.NoHorizontalScrollerVPAdapter;
import com.sinovatech.unicom.separatemodule.keyboard.NoHorizontalScrollerViewPager;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KeyboardFragment extends Fragment {
    public static final String BIND_TO_EDITTEXT = "bind_to_edittext";
    private static final String CURRENT_POSITION_FLAG = "CURRENT_POSITION_FLAG";
    public static final String HIDE_BAR_EDITTEXT_AND_BTN = "hide bar's editText and btn";
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext;
    private ImageView addPicture;
    private Button bar_btn_send;
    private EditText bar_edit_text;
    private ImageView bar_image_add_btn;
    private View contentView;
    private LinearLayout editLayout;
    private ImageView emotionBtn;
    private HorizontalRecyclerviewAdapter horizontalRecyclerviewAdapter;
    private String imagePath;
    private EmotionKeyboard mEmotionKeyboard;
    String msg;
    private OnSendMsgListener onSendMsgListener;
    private RecyclerView recyclerview_horizontal;
    private LinearLayout rl_editbar_bg;
    private NoHorizontalScrollerViewPager viewPager;
    private int CurrentPosition = 0;
    private boolean isBindToBarEditText = true;
    private boolean isHidenBarEditTextAndBtn = false;
    List<Fragment> fragments = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    interface OnSendMsgListener {
        void sendText(String str);
    }

    protected void initListener() {
    }

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment");
        Tracker.onResume(this);
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        this.activityContext = SoulPermission.getInstance().getTopActivity();
        try {
            this.msg = getArguments().getString("msg");
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment", viewGroup);
        View inflate = layoutInflater.inflate(2131493129, viewGroup, false);
        initView(inflate);
        this.mEmotionKeyboard = EmotionKeyboard.with(this.activityContext).setEmotionView(inflate.findViewById(2131297710)).bindToContent(this.contentView).bindToEditText((EditText) (!this.isBindToBarEditText ? this.contentView : inflate.findViewById(2131296505))).bindToEmotionButton((ImageView) inflate.findViewById(2131296920)).build();
        initListener();
        initDatas();
        GlobalOnItemClickManagerUtils globalOnItemClickManagerUtils = GlobalOnItemClickManagerUtils.getInstance(getActivity());
        if (this.isBindToBarEditText) {
            globalOnItemClickManagerUtils.attachToEditText(this.bar_edit_text);
        } else {
            globalOnItemClickManagerUtils.attachToEditText((EditText) this.contentView);
            this.mEmotionKeyboard.bindToEditText((EditText) this.contentView);
        }
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment");
        return inflate;
    }

    public void bindToContentView(View view) {
        this.contentView = view;
    }

    protected void initView(View view) {
        this.editLayout = (LinearLayout) view.findViewById(2131297305);
        this.addPicture = (ImageView) view.findViewById(2131296506);
        this.viewPager = (NoHorizontalScrollerViewPager) view.findViewById(2131299545);
        this.emotionBtn = (ImageView) view.findViewById(2131296920);
        this.recyclerview_horizontal = (RecyclerView) view.findViewById(2131298273);
        this.bar_edit_text = (EditText) view.findViewById(2131296505);
        this.bar_image_add_btn = (ImageView) view.findViewById(2131296507);
        this.bar_btn_send = (Button) view.findViewById(2131296504);
        this.rl_editbar_bg = (LinearLayout) view.findViewById(2131298337);
        this.bar_edit_text.setHint("");
        this.bar_edit_text.setTextColor(Color.parseColor("#000000"));
        if (this.isHidenBarEditTextAndBtn) {
            this.bar_edit_text.setVisibility(8);
            this.bar_image_add_btn.setVisibility(8);
            this.bar_btn_send.setVisibility(8);
            this.rl_editbar_bg.setBackgroundResource(2131099689);
        } else {
            this.bar_edit_text.setVisibility(0);
            this.bar_image_add_btn.setVisibility(8);
            this.bar_btn_send.setVisibility(0);
            this.rl_editbar_bg.setBackgroundResource(2131232203);
        }
        this.bar_edit_text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(300)});
        this.bar_edit_text.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (TextUtils.isEmpty(charSequence)) {
                    KeyboardFragment.this.bar_btn_send.setTextColor(-3355444);
                    KeyboardFragment.this.bar_btn_send.setClickable(false);
                    return;
                }
                KeyboardFragment.this.bar_btn_send.setTextColor(-1703896);
                KeyboardFragment.this.bar_btn_send.setClickable(true);
            }
        });
        this.bar_btn_send.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                if (!TextUtils.isEmpty(KeyboardFragment.this.bar_edit_text.getText().toString())) {
                    try {
                        if (KeyboardFragment.this.onSendMsgListener != null) {
                            KeyboardFragment.this.onSendMsgListener.sendText(KeyboardFragment.this.bar_edit_text.getText().toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void clearContent() {
        this.imagePath = "";
        this.addPicture.setImageResource(2131231274);
        this.bar_edit_text.setText("");
    }

    protected void initDatas() {
        replaceFragment();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.fragments.size(); i++) {
            if (i == 0) {
                ImageModel imageModel = new ImageModel();
                imageModel.icon = getResources().getDrawable(2131231556);
                imageModel.flag = "经典笑脸";
                imageModel.isSelected = true;
                arrayList.add(imageModel);
            } else {
                ImageModel imageModel2 = new ImageModel();
                imageModel2.icon = getResources().getDrawable(2131231589);
                imageModel2.flag = "其他笑脸" + i;
                imageModel2.isSelected = false;
                arrayList.add(imageModel2);
            }
        }
        this.CurrentPosition = 0;
        this.horizontalRecyclerviewAdapter = new HorizontalRecyclerviewAdapter(getActivity(), arrayList);
        this.recyclerview_horizontal.setHasFixedSize(true);
        this.recyclerview_horizontal.setAdapter(this.horizontalRecyclerviewAdapter);
        this.recyclerview_horizontal.setLayoutManager(new GridLayoutManager((Context) getActivity(), 1, 0, false));
        this.horizontalRecyclerviewAdapter.setOnClickItemListener(new HorizontalRecyclerviewAdapter.OnClickItemListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.keyboard.KeyboardFragment.3
            @Override // com.sinovatech.unicom.separatemodule.keyboard.HorizontalRecyclerviewAdapter.OnClickItemListener
            public void onItemLongClick(View view, int i2, List<ImageModel> list) {
            }

            @Override // com.sinovatech.unicom.separatemodule.keyboard.HorizontalRecyclerviewAdapter.OnClickItemListener
            public void onItemClick(View view, int i2, List<ImageModel> list) {
                list.get(0).isSelected = false;
                KeyboardFragment.this.CurrentPosition = i2;
                list.get(KeyboardFragment.this.CurrentPosition).isSelected = true;
                KeyboardFragment.this.horizontalRecyclerviewAdapter.notifyItemChanged(0);
                KeyboardFragment.this.horizontalRecyclerviewAdapter.notifyItemChanged(KeyboardFragment.this.CurrentPosition);
                KeyboardFragment.this.viewPager.setCurrentItem(i2, false);
            }
        });
    }

    private void replaceFragment() {
        this.fragments.add((EmotiomComplateFragment) FragmentFactory.getSingleFactoryInstance().getFragment(1));
        this.viewPager.setAdapter(new NoHorizontalScrollerVPAdapter(getActivity().getSupportFragmentManager(), this.fragments));
    }

    public void showSoftIntut(String str) {
        if ("yes".equals(str)) {
            this.emotionBtn.setVisibility(0);
            this.addPicture.setVisibility(8);
        } else {
            this.emotionBtn.setVisibility(8);
            this.addPicture.setVisibility(8);
        }
        this.editLayout.setVisibility(0);
        try {
            this.mEmotionKeyboard.showKeyboard();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.emotionBtn.setImageResource(2131231556);
    }

    public void hideSoftIntut() {
        this.editLayout.setVisibility(8);
        this.mEmotionKeyboard.hideKeyboard();
    }

    public boolean getEmotionIsshown() {
        return this.editLayout.isShown();
    }

    public static <T extends Fragment> T newInstance(Class cls, Bundle bundle) {
        T t;
        try {
            t = (T) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            t = null;
            t.setArguments(bundle);
            return t;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            t = null;
            t.setArguments(bundle);
            return t;
        }
        t.setArguments(bundle);
        return t;
    }

    public void setSendMsgListener(OnSendMsgListener onSendMsgListener) {
        this.onSendMsgListener = onSendMsgListener;
    }
}
