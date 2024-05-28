package cn.sharesdk.system.text.login.gui;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.system.text.login.LoginActionListener;
import cn.sharesdk.system.text.login.gui.GroupListView;
import cn.sharesdk.system.text.login.p098a.CountryListPageLayout;
import cn.sharesdk.system.text.login.utils.SearchEngine;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CountryPage extends FakeActivity implements TextWatcher, View.OnClickListener, GroupListView.OnItemClickListener {
    private String country;
    private EditText etSearch;
    private CountryListView listView;
    private LoginActionListener listener;
    private String zone;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void setSMSLoginActionListener(LoginActionListener loginActionListener) {
        this.listener = loginActionListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        SearchEngine.m21454a();
        initPage();
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        super.onResume();
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        super.onPause();
    }

    private void initPage() {
        LinearLayout m21562a = new CountryListPageLayout(this.activity).m21562a();
        if (m21562a != null) {
            this.activity.setContentView(m21562a);
        }
        this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_ll_back")).setOnClickListener(this);
        this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_ivSearch")).setOnClickListener(this);
        this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_iv_clear")).setOnClickListener(this);
        this.listView = (CountryListView) this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_clCountry"));
        this.listView.m21501a(this);
        this.etSearch = (EditText) this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_et_put_identify"));
        this.etSearch.addTextChangedListener(this);
    }

    @Override // cn.sharesdk.system.text.login.gui.GroupListView.OnItemClickListener
    public void onItemClick(GroupListView groupListView, View view, int i, int i2) {
        if (i2 >= 0) {
            String[] m21504a = this.listView.m21504a(i, i2);
            this.zone = m21504a[1];
            this.country = m21504a[0];
            finish();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        int idRes = ResHelper.getIdRes(this.activity, "ssdk_sms_id_ll_back");
        int idRes2 = ResHelper.getIdRes(this.activity, "ssdk_sms_id_ivSearch");
        int idRes3 = ResHelper.getIdRes(this.activity, "ssdk_sms_id_iv_clear");
        if (id == idRes) {
            finish();
        } else if (id == idRes2) {
            this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_llTitle")).setVisibility(8);
            this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_llSearch")).setVisibility(0);
            this.etSearch.getText().clear();
            this.etSearch.requestFocus();
        } else if (id == idRes3) {
            this.etSearch.getText().clear();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        try {
            int idRes = ResHelper.getIdRes(this.activity, "ssdk_sms_id_llSearch");
            if (i == 4 && keyEvent.getAction() == 0 && this.activity.findViewById(idRes).getVisibility() == 0) {
                this.activity.findViewById(idRes).setVisibility(8);
                this.activity.findViewById(ResHelper.getIdRes(this.activity, "ssdk_sms_id_llTitle")).setVisibility(0);
                this.etSearch.setText("");
                return true;
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
        }
        return super.onKeyEvent(i, keyEvent);
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("zone", this.zone);
        hashMap.put("country", this.country);
        hashMap.put("listener", this.listener);
        hashMap.put("page", 1);
        setResult(hashMap);
        return super.onFinish();
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.listView.m21500a(charSequence.toString().toLowerCase());
    }
}
