package com.sinovatech.unicom.separatemodule.baidumap.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.baidumap.BaiduSearchDao;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduHistoryrecordAadapter;
import com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduSearchwordAdapter;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiduSearchEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchEntity;
import com.sinovatech.unicom.separatemodule.baidumap.entity.SearchTagEntity;
import com.sinovatech.unicom.separatemodule.baidumap.parser.SearchJsonDataParser;
import com.sinovatech.unicom.separatemodule.baidumap.parser.SearchTagFunction;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduSearchAcitivty extends Activity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private BaiduSearchDao baiduSearchDao;
    private BaiduSearchEntity baiduSearchEntity;
    private Button mCancle;
    private TextView mComplete;
    private ImageButton mDelete;
    private RecyclerView mHistoryRecord;
    private BaiduHistoryrecordAadapter mHistoryrecordAadapter;
    private LinearLayout mLishi;
    private RecyclerView mRecyclersearch;
    private EditText mSearch;
    private RecyclerView mSearchWord;
    private LinearLayout mSearcherror;
    private LinearLayout mSearchhint;
    private BaiduSearchwordAdapter mSearchwordAdapter;

    /* renamed from: sp */
    private SharePreferenceUtil f18499sp;
    private Activity activityContext = this;
    private List<String> searchList = new ArrayList();
    private String cityCode = "";
    private String latitude = "";
    private String longitude = "";

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 76);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    static /* synthetic */ Activity access$2300(BaiduSearchAcitivty baiduSearchAcitivty) {
        return baiduSearchAcitivty.activityContext;
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC85911 implements View.OnClickListener {
        View$OnClickListenerC85911() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            BaiduSearchAcitivty.this.mSearch.setFocusable(true);
            BaiduSearchAcitivty.this.mSearch.setFocusableInTouchMode(true);
            BaiduSearchAcitivty.this.mSearch.requestFocus();
            BaiduSearchAcitivty.this.mSearch.findFocus();
            ((InputMethodManager) BaiduSearchAcitivty.this.mSearch.getContext().getSystemService("input_method")).showSoftInput(BaiduSearchAcitivty.this.mSearch, 0);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnFocusChangeListenerC85922 implements View.OnFocusChangeListener {
        View$OnFocusChangeListenerC85922() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            Tracker.onFocusChange(view, z);
            if (z) {
                return;
            }
            BaiduSearchAcitivty.this.mSearch.requestFocus();
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C85933 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C85933() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString().trim())) {
                return;
            }
            BaiduSearchAcitivty.this.mSearch.setSelection(editable.length());
            BaiduSearchAcitivty.this.getSearchData(editable.toString().trim());
            BaiduSearchAcitivty.this.mSearchhint.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSearchData(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("cityCode", this.cityCode);
        hashMap.put("destination_jd", this.longitude);
        hashMap.put("destination_wd", this.latitude);
        hashMap.put("key", str);
        hashMap.put("version", this.activityContext.getString(2131886969));
        UIUtils.logD("搜索结果URL：" + URLSet.getStateion_Search());
        UIUtils.logD("搜索结果入参：" + hashMap);
        App.getAsyncHttpClient().rxGet(URLSet.getStateion_Search(), hashMap, 1, 0).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function<String, ArrayList<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.5
            @Override // io.reactivex.functions.Function
            public ArrayList<SearchEntity> apply(String str2) throws Exception {
                UIUtils.logD("搜索结果报文：" + str2);
                ArrayList<SearchEntity> arrayList = new ArrayList<>();
                arrayList.add(SearchJsonDataParser.parseSingleSearch(new JSONObject(str2)));
                return arrayList;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(ArrayList<SearchEntity> arrayList) {
                if (arrayList.get(0).getEhall().size() != 0) {
                    BaiduSearchAcitivty.this.mSearcherror.setVisibility(8);
                    BaiduSearchAcitivty.this.mRecyclersearch.setVisibility(0);
                    BaiduSearchAcitivty.this.addRecyclersearch(arrayList);
                    return;
                }
                BaiduSearchAcitivty.this.mRecyclersearch.setVisibility(8);
                BaiduSearchAcitivty.this.mSearcherror.setVisibility(0);
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                UIUtils.logD("搜索结果报错：" + th.getMessage());
                BaiduSearchAcitivty.this.mRecyclersearch.setVisibility(8);
                BaiduSearchAcitivty.this.mSearcherror.setVisibility(0);
            }
        });
    }

    private void getRecomeend() {
        App.getAsyncHttpClient().rxGet(URLSet.getStation_search_recomend(), new HashMap(), 3, 3).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new SearchTagFunction()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ArrayList<SearchTagEntity>>() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.6
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(ArrayList<SearchTagEntity> arrayList) {
                BaiduSearchAcitivty.this.setSearch(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addRecyclersearch(ArrayList<SearchEntity> arrayList) {
        this.mRecyclersearch.setLayoutManager(new LinearLayoutManager(this.activityContext));
        this.mRecyclersearch.setAdapter(new BaiduSearchApadter(this.activityContext, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSearch(final ArrayList<SearchTagEntity> arrayList) {
        this.mSearchwordAdapter = new BaiduSearchwordAdapter(this.activityContext, arrayList);
        this.mSearchWord.setLayoutManager(new GridLayoutManager(this.activityContext, 3));
        this.mSearchWord.setAdapter(this.mSearchwordAdapter);
        this.mSearchwordAdapter.setOnItemListClickListener(new BaiduSearchwordAdapter.OnItemListClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.7
            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduSearchwordAdapter.OnItemListClickListener
            public void onItemListClick(int i) {
                String str = ((SearchTagEntity) arrayList.get(i)).gettABNAME();
                BaiduSearchAcitivty.this.mSearch.setText("");
                BaiduSearchAcitivty.this.mSearch.setText(str);
            }
        });
    }

    private void historyRecord() {
        List<BaiduSearchEntity> history = this.baiduSearchDao.getHistory();
        for (int i = 0; i < history.size(); i++) {
            this.searchList.add(history.get(i).getTitle());
        }
        if (this.searchList.size() > 0) {
            this.mLishi.setVisibility(0);
        } else {
            this.mLishi.setVisibility(8);
        }
        this.mHistoryrecordAadapter = new BaiduHistoryrecordAadapter(this.activityContext, this.searchList);
        this.mHistoryRecord.setLayoutManager(new GridLayoutManager(this.activityContext, 2));
        this.mHistoryRecord.setAdapter(this.mHistoryrecordAadapter);
        this.mHistoryrecordAadapter.setOnItemListClickListener(new BaiduHistoryrecordAadapter.OnItemListClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.8
            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduHistoryrecordAadapter.OnItemListClickListener
            public void onItemListClick(int i2) {
                BaiduSearchAcitivty.this.mSearch.setText("");
                BaiduSearchAcitivty.this.mSearch.setText((String) BaiduSearchAcitivty.this.searchList.get(i2));
            }
        });
        this.mHistoryrecordAadapter.setOnDeleteClickListener(new BaiduHistoryrecordAadapter.OnDeleteClickListener() { // from class: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.9
            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduHistoryrecordAadapter.OnDeleteClickListener
            public void onDeleteClick(int i2) {
                BaiduSearchAcitivty.this.baiduSearchDao.deleteHistory((String) BaiduSearchAcitivty.this.searchList.get(i2));
                if (BaiduSearchAcitivty.this.searchList.size() > 1) {
                    BaiduSearchAcitivty.this.mLishi.setVisibility(0);
                } else {
                    BaiduSearchAcitivty.this.mLishi.setVisibility(8);
                }
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131298484) {
            finish();
        } else {
            switch (id) {
                case 2131298491:
                    this.mDelete.setVisibility(8);
                    this.mComplete.setVisibility(0);
                    this.mHistoryrecordAadapter.delete(true);
                    break;
                case 2131298492:
                    this.mDelete.setVisibility(0);
                    this.mComplete.setVisibility(8);
                    this.mHistoryrecordAadapter.delete(false);
                    break;
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BaiduSearchApadter extends RecyclerView.Adapter<MyHolder> {
        private Context mContext;
        private ArrayList<SearchEntity> mList;

        /*  JADX ERROR: Dependency scan failed at insn: 0x049C: INVOKE_CUSTOM_RANGE r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22
            jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:84)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:82)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:67)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:56)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:41)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:282)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0460: UNKNOWN(0x13E4), method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0460: UNKNOWN(0x13E4)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x046C: UNKNOWN(0x13E9), method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x046C: UNKNOWN(0x13E9)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0478: UNKNOWN(0x13EE), method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0478: UNKNOWN(0x13EE)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0484: UNKNOWN(0x13F3), method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0484: UNKNOWN(0x13F3)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0490: UNKNOWN(0x13F8), method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0490: UNKNOWN(0x13F8)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x049C: INVOKE_CUSTOM_RANGE r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, method: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0xa
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0xa
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 12 more
            */
        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@android.support.annotation.NonNull com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.MyHolder r12, int r13) {
            /*
                Method dump skipped, instructions count: 1264
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty.BaiduSearchApadter.onBindViewHolder(com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$MyHolder, int):void");
        }

        public BaiduSearchApadter(Context context, ArrayList<SearchEntity> arrayList) {
            this.mContext = context;
            this.mList = arrayList;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyHolder(LayoutInflater.from(this.mContext).inflate(2131493021, viewGroup, false));
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$3 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        class C86023 implements BaiduBusinessimageAdapter.OnItemClickListener {
            final /* synthetic */ MyHolder val$holder;

            C86023(MyHolder myHolder) {
                this.val$holder = myHolder;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduBusinessimageAdapter.OnItemClickListener
            public void onItemClick() {
                this.val$holder.itemView.performClick();
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$4 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        class C86034 implements BaiduCoffeeVolumeAdapter.OnItemClickListener {
            final /* synthetic */ MyHolder val$holder;

            C86034(MyHolder myHolder) {
                this.val$holder = myHolder;
            }

            @Override // com.sinovatech.unicom.separatemodule.baidumap.adapter.BaiduCoffeeVolumeAdapter.OnItemClickListener
            public void onItemClick() {
                this.val$holder.itemView.performClick();
            }
        }

        @NBSInstrumented
        /* renamed from: com.sinovatech.unicom.separatemodule.baidumap.activity.BaiduSearchAcitivty$BaiduSearchApadter$5 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class View$OnClickListenerC86045 implements View.OnClickListener {
            final /* synthetic */ List val$ehallList;
            final /* synthetic */ int val$position;

            View$OnClickListenerC86045(List list, int i) {
                this.val$ehallList = list;
                this.val$position = i;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                BaiduSearchAcitivty.this.baiduSearchDao.insertHistory(((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEpName());
                BaiduSearchAcitivty.this.mHistoryrecordAadapter.notifyDataSetChanged();
                if (!TextUtils.isEmpty(((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEhall_frontAddress())) {
                    try {
                        str = Base64.encodeToString(((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEhall_frontAddress().getBytes("UTF-8"), 2);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        str = "";
                    }
                    StatisticsUploadUtils.upload(BaiduSearchAcitivty.this.activityContext, "FJ0001", "点击详情", ((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getTypeIdentifier(), ((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getId(), ((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEpName(), str);
                    IntentManager.generateIntentAndGo(BaiduSearchAcitivty.this.activityContext, ((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEhall_frontAddress(), ((SearchEntity.EhallBean) this.val$ehallList.get(this.val$position)).getEpName(), false, "get");
                }
                InputMethodManager inputMethodManager = (InputMethodManager) BaiduSearchAcitivty.this.getSystemService("input_method");
                if (BaiduSearchAcitivty.this.getWindow().peekDecorView() != null) {
                    inputMethodManager.hideSoftInputFromWindow(BaiduSearchAcitivty.this.getWindow().peekDecorView().getWindowToken(), 0);
                }
                BaiduSearchAcitivty.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        }

        private String conversion(String str) {
            DecimalFormat decimalFormat;
            float f = 0.0f;
            try {
                f = Float.valueOf(str).floatValue() / 1000.0f;
                decimalFormat = new DecimalFormat("0.00");
            } catch (NumberFormatException e) {
                e.printStackTrace();
                decimalFormat = null;
            }
            return decimalFormat.format(f);
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mList.get(0).getEhall().size();
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public class MyHolder extends RecyclerView.ViewHolder {
            private final ImageView live_image;
            private final LinearLayout mAct;
            private final TextView mActhint;
            private final TextView mActtitle;
            private final LinearLayout mAreaLayout;
            private final RecyclerView mBusinessImage;
            private final TextView mBusinessName;
            private final ImageView mCoupons;
            private final TextView mDistance;
            private final View mDivider;
            private final TextView mGrade;
            private final LinearLayout mLive;
            private final TextView mLivetitle;
            private final ImageView mPicture;
            private final RecyclerView mRecycler_volume;
            private final TextView mRegion;
            private final RecyclerView mServiceLabel;
            private final TextView mSite;
            private final ImageView mStar;
            private final TextView mTime;
            private final TextView mTypeName;

            static /* synthetic */ TextView access$1000(MyHolder myHolder) {
                return myHolder.mBusinessName;
            }

            static /* synthetic */ LinearLayout access$1100(MyHolder myHolder) {
                return myHolder.mAreaLayout;
            }

            static /* synthetic */ TextView access$1200(MyHolder myHolder) {
                return myHolder.mTime;
            }

            static /* synthetic */ TextView access$1300(MyHolder myHolder) {
                return myHolder.mSite;
            }

            static /* synthetic */ TextView access$1400(MyHolder myHolder) {
                return myHolder.mDistance;
            }

            static /* synthetic */ ImageView access$1500(MyHolder myHolder) {
                return myHolder.mStar;
            }

            static /* synthetic */ TextView access$1600(MyHolder myHolder) {
                return myHolder.mGrade;
            }

            static /* synthetic */ ImageView access$1700(MyHolder myHolder) {
                return myHolder.mPicture;
            }

            static /* synthetic */ ImageView access$1800(MyHolder myHolder) {
                return myHolder.mCoupons;
            }

            static /* synthetic */ View access$1900(MyHolder myHolder) {
                return myHolder.mDivider;
            }

            static /* synthetic */ TextView access$2000(MyHolder myHolder) {
                return myHolder.mRegion;
            }

            static /* synthetic */ TextView access$2100(MyHolder myHolder) {
                return myHolder.mTypeName;
            }

            static /* synthetic */ RecyclerView access$2200(MyHolder myHolder) {
                return myHolder.mServiceLabel;
            }

            static /* synthetic */ LinearLayout access$2400(MyHolder myHolder) {
                return myHolder.mLive;
            }

            static /* synthetic */ ImageView access$2500(MyHolder myHolder) {
                return myHolder.live_image;
            }

            static /* synthetic */ TextView access$2600(MyHolder myHolder) {
                return myHolder.mLivetitle;
            }

            static /* synthetic */ LinearLayout access$2700(MyHolder myHolder) {
                return myHolder.mAct;
            }

            static /* synthetic */ TextView access$2800(MyHolder myHolder) {
                return myHolder.mActtitle;
            }

            static /* synthetic */ TextView access$2900(MyHolder myHolder) {
                return myHolder.mActhint;
            }

            public MyHolder(View view) {
                super(view);
                this.mPicture = (ImageView) view.findViewById(2131297278);
                this.mBusinessName = (TextView) view.findViewById(2131296556);
                this.mTime = (TextView) view.findViewById(2131298781);
                this.mSite = (TextView) view.findViewById(2131298631);
                this.mCoupons = (ImageView) view.findViewById(2131296721);
                this.mDistance = (TextView) view.findViewById(2131296888);
                this.mStar = (ImageView) view.findViewById(2131298678);
                this.mGrade = (TextView) view.findViewById(2131297094);
                this.mLive = (LinearLayout) view.findViewById(2131297633);
                this.mLivetitle = (TextView) view.findViewById(2131297682);
                this.live_image = (ImageView) view.findViewById(2131297639);
                this.mAct = (LinearLayout) view.findViewById(2131296288);
                this.mActtitle = (TextView) view.findViewById(2131296319);
                this.mActhint = (TextView) view.findViewById(2131296291);
                this.mBusinessImage = (RecyclerView) view.findViewById(2131296555);
                this.mRecycler_volume = (RecyclerView) view.findViewById(2131298272);
                this.mAreaLayout = (LinearLayout) view.findViewById(2131296375);
                this.mRegion = (TextView) view.findViewById(2131298276);
                this.mTypeName = (TextView) view.findViewById(2131299164);
                this.mServiceLabel = (RecyclerView) view.findViewById(2131298518);
                this.mDivider = view.findViewById(2131296889);
            }
        }
    }
}
