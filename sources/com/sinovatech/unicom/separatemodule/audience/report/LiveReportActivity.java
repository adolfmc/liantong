package com.sinovatech.unicom.separatemodule.audience.report;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.jakewharton.rxbinding2.view.RxView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveReportEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveReportActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private BaseQuickAdapter<LiveReportEntity, BaseViewHolder> adapter;
    private EditText etInfo;
    private ImageButton ibBack;
    private String[] names = {"低俗色情", "违法违规", "线下导流", "涉嫌欺诈", "辱骂我或他人", "其他"};
    private int position;
    private RecyclerView rlvList;
    private TextView tvCount;
    private TextView tvSubmit;
    private TextView tvTitle;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 70);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @SuppressLint({"CheckResult"})
    private void initView() {
        this.position = getIntent().getIntExtra("chatDelPosition", -1);
        this.tvTitle = (TextView) findViewById(2131298800);
        this.ibBack = (ImageButton) findViewById(2131296473);
        this.tvSubmit = (TextView) findViewById(2131299113);
        this.tvTitle.setText("评论举报");
        this.tvSubmit.setVisibility(0);
        RxView.clicks(this.ibBack).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.report.-$$Lambda$LiveReportActivity$-nip7EJs0_OJ2FFKASecodb2hF0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReportActivity.this.finish();
            }
        });
        RxView.clicks(this.tvSubmit).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.report.-$$Lambda$LiveReportActivity$oD80NDzNMrZGKrGqd1RRQ5plNuU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                LiveReportActivity.this.submit();
            }
        });
        this.etInfo = (EditText) findViewById(2131296945);
        this.tvCount = (TextView) findViewById(2131298918);
        this.etInfo.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.audience.report.LiveReportActivity.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                int length = LiveReportActivity.this.etInfo.getText().length();
                TextView textView = LiveReportActivity.this.tvCount;
                textView.setText(length + "/100");
                if (length == 100) {
                    UIUtils.toastCenter("最多可输入100个字符");
                }
            }
        });
        this.rlvList = (RecyclerView) findViewById(2131298405);
        this.rlvList.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView = this.rlvList;
        BaseQuickAdapter<LiveReportEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<LiveReportEntity, BaseViewHolder>(2131493229) { // from class: com.sinovatech.unicom.separatemodule.audience.report.LiveReportActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, LiveReportEntity liveReportEntity) {
                baseViewHolder.setText(2131299021, liveReportEntity.getName()).setImageResource(2131297492, liveReportEntity.isSelection() ? 2131231640 : 2131231639);
            }
        };
        this.adapter = baseQuickAdapter;
        recyclerView.setAdapter(baseQuickAdapter);
        this.adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.report.-$$Lambda$LiveReportActivity$ODutwcAxdx0zd1AEoySS7SZtie8
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                LiveReportActivity.lambda$initView$2(LiveReportActivity.this, baseQuickAdapter2, view, i);
            }
        });
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.names.length; i++) {
            LiveReportEntity liveReportEntity = new LiveReportEntity();
            liveReportEntity.setName(this.names[i]);
            arrayList.add(liveReportEntity);
        }
        this.adapter.setNewData(arrayList);
    }

    public static /* synthetic */ void lambda$initView$2(LiveReportActivity liveReportActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        liveReportActivity.adapter.getItem(i).setSelection(!liveReportActivity.adapter.getItem(i).isSelection());
        liveReportActivity.adapter.notifyItemChanged(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submit() {
        boolean z;
        Iterator<LiveReportEntity> it = this.adapter.getData().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().isSelection()) {
                z = true;
                break;
            }
        }
        if (!z) {
            UIUtils.toastCenter("您需要选择一项原因方可提交");
            return;
        }
        UIUtils.toastCenter("提交成功");
        Intent intent = new Intent();
        intent.putExtra("chatDelPosition", this.position);
        setResult(2112, intent);
        finish();
    }
}
