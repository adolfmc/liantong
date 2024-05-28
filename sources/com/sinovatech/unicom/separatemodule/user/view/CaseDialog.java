package com.sinovatech.unicom.separatemodule.user.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.user.adapter.CaseAdapter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CaseDialog extends Dialog {
    private List<String> list;
    private CaseAdapter mAdapter;

    /* renamed from: rV */
    private RecyclerView f18627rV;
    private TextView tvConfirm;
    private TextView tvTitle;

    public CaseDialog(Context context) {
        super(context, 2131951623);
        setContentView(2131493095);
        initView();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(false);
    }

    private void initView() {
        this.f18627rV = (RecyclerView) findViewById(2131298424);
        this.tvConfirm = (TextView) findViewById(2131298926);
        this.tvTitle = (TextView) findViewById(2131298929);
        this.list = new ArrayList();
        this.mAdapter = new CaseAdapter(this.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.f18627rV.setLayoutManager(linearLayoutManager);
        this.f18627rV.setAdapter(this.mAdapter);
        this.tvConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.CaseDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                CaseDialog.this.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public CaseDialog refreshView(String str, String str2, String str3) {
        this.tvTitle.setText(str);
        this.list.clear();
        this.list.add(str2);
        this.list.add(str3);
        this.mAdapter.notifyDataSetChanged();
        return this;
    }
}
