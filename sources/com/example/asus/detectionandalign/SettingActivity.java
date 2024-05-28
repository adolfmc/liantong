package com.example.asus.detectionandalign;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SettingActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    /* renamed from: a */
    public static final String f9798a = "SettingActivity";
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private SharedPreferences f9799b = null;

    /* renamed from: c */
    private CheckBoxPreference f9800c;

    /* renamed from: d */
    private CheckBoxPreference f9801d;

    /* renamed from: e */
    private CheckBoxPreference f9802e;

    /* renamed from: f */
    private CheckBoxPreference f9803f;

    /* renamed from: g */
    private CheckBoxPreference f9804g;

    /* renamed from: h */
    private CheckBoxPreference f9805h;

    /* renamed from: i */
    private CheckBoxPreference f9806i;

    /* renamed from: j */
    private ListPreference f9807j;

    /* renamed from: k */
    private ListPreference f9808k;

    /* renamed from: l */
    private ListPreference f9809l;

    /* renamed from: m */
    private ListPreference f9810m;

    /* renamed from: n */
    private EditTextPreference f9811n;

    /* renamed from: o */
    private EditTextPreference f9812o;

    /* renamed from: p */
    private PreferenceCategory f9813p;

    /* renamed from: q */
    private boolean f9814q;

    /* renamed from: a */
    private void m16276a(boolean z) {
        CheckBoxPreference checkBoxPreference;
        boolean z2;
        if (z) {
            this.f9813p.addPreference(this.f9801d);
            checkBoxPreference = this.f9802e;
            z2 = true;
        } else {
            this.f9813p.removePreference(this.f9801d);
            checkBoxPreference = this.f9802e;
            z2 = false;
        }
        checkBoxPreference.setEnabled(z2);
        this.f9809l.setEnabled(z2);
        this.f9803f.setEnabled(z2);
        this.f9804g.setEnabled(z2);
        this.f9807j.setEnabled(z2);
        this.f9808k.setEnabled(z2);
        this.f9810m.setEnabled(z2);
        this.f9806i.setEnabled(z2);
        this.f9805h.setEnabled(z2);
        this.f9811n.setEnabled(z2);
        this.f9812o.setEnabled(z2);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        CheckBoxPreference checkBoxPreference;
        String str;
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        addPreferencesFromResource(C4243R.C4250xml.preferences);
        this.f9799b = PreferenceManager.getDefaultSharedPreferences(this);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        this.f9800c = (CheckBoxPreference) findPreference("pref_liveness_debug_mode");
        this.f9802e = (CheckBoxPreference) findPreference("pref_return_type");
        this.f9803f = (CheckBoxPreference) findPreference("pre_inspection_countdown");
        this.f9804g = (CheckBoxPreference) findPreference("pre_is_preposition");
        this.f9805h = (CheckBoxPreference) findPreference("pre_is_cache_failed_liveness");
        this.f9806i = (CheckBoxPreference) findPreference("pre_kjnova_clipper");
        this.f9807j = (ListPreference) findPreference("pref_frame_counts_list");
        this.f9808k = (ListPreference) findPreference("pref_liveness_detection_overtime_list");
        this.f9809l = (ListPreference) findPreference("pre_inspection_countdown_time_list");
        this.f9810m = (ListPreference) findPreference("pref_picture_package_counts_list");
        this.f9811n = (EditTextPreference) findPreference("pref_liveness_param");
        this.f9812o = (EditTextPreference) findPreference("pref_enfilade_param");
        this.f9801d = (CheckBoxPreference) findPreference("pref_liveness_log");
        this.f9813p = (PreferenceCategory) findPreference("pref_liveness_debug_category");
        this.f9800c.setOnPreferenceChangeListener(this);
        this.f9802e.setOnPreferenceChangeListener(this);
        this.f9803f.setOnPreferenceChangeListener(this);
        this.f9804g.setOnPreferenceChangeListener(this);
        this.f9805h.setOnPreferenceChangeListener(this);
        this.f9806i.setOnPreferenceChangeListener(this);
        this.f9807j.setOnPreferenceChangeListener(this);
        this.f9808k.setOnPreferenceChangeListener(this);
        this.f9809l.setOnPreferenceChangeListener(this);
        this.f9810m.setOnPreferenceChangeListener(this);
        this.f9811n.setOnPreferenceChangeListener(this);
        this.f9812o.setOnPreferenceChangeListener(this);
        this.f9814q = this.f9799b.getBoolean("pref_liveness_debug_mode", false);
        m16276a(this.f9814q);
        if (!this.f9814q) {
            Toast.makeText(this, "请先打开debug模式，设置才能生效", 0).show();
        }
        if (this.f9799b.getBoolean("pref_return_type", false)) {
            this.f9802e.setSummary("当前返回视频   ");
            this.f9802e.setTitle("视频");
        } else {
            this.f9802e.setTitle("照片");
            this.f9802e.setSummary("当前返回照片   ");
        }
        if (this.f9799b.getBoolean("pre_is_preposition", true)) {
            checkBoxPreference = this.f9804g;
            str = "当前前置摄像头   ";
        } else {
            checkBoxPreference = this.f9804g;
            str = "当前后置摄像头   ";
        }
        checkBoxPreference.setSummary(str);
        this.f9805h.setSummary("保存路径：sd卡根目录/FailedLiveness    ");
        EditTextPreference editTextPreference = this.f9811n;
        editTextPreference.setSummary("当前" + this.f9799b.getString("pref_liveness_param", "defult") + "   ");
        EditTextPreference editTextPreference2 = this.f9812o;
        editTextPreference2.setSummary("当前" + this.f9799b.getString("pref_enfilade_param", "defult") + "   ");
        ListPreference listPreference = this.f9807j;
        listPreference.setSummary("当前" + this.f9799b.getString("pref_frame_counts_list", "defult") + "帧   ");
        ListPreference listPreference2 = this.f9808k;
        listPreference2.setSummary("当前" + this.f9799b.getString("pref_liveness_detection_overtime_list", "defult") + "秒   ");
        ListPreference listPreference3 = this.f9809l;
        listPreference3.setSummary("当前" + this.f9799b.getString("pre_inspection_countdown_time_list", "defult") + "秒   ");
        ListPreference listPreference4 = this.f9810m;
        listPreference4.setSummary("当前" + this.f9799b.getString("pref_picture_package_counts_list", "defult") + "张   ");
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        boolean z;
        NBSActionInstrumentation.onOptionsItemSelectedEnter(menuItem, this);
        if (menuItem.getItemId() != 16908332) {
            z = super.onOptionsItemSelected(menuItem);
        } else {
            finish();
            z = true;
        }
        NBSActionInstrumentation.onOptionsItemSelectedExit();
        return z;
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object obj) {
        return true;
    }

    @Override // android.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        return true;
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        char c;
        ListPreference listPreference;
        StringBuilder sb;
        String str2;
        SharedPreferences sharedPreferences2;
        String str3;
        float floatValue;
        EditTextPreference editTextPreference;
        StringBuilder sb2;
        CheckBoxPreference checkBoxPreference;
        String str4;
        CheckBoxPreference checkBoxPreference2;
        String str5;
        switch (str.hashCode()) {
            case -2095905747:
                if (str.equals("pref_liveness_param")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1566795380:
                if (str.equals("pref_enfilade_param")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -608421869:
                if (str.equals("pre_inspection_countdown_time_list")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 118117346:
                if (str.equals("pref_liveness_detection_overtime_list")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 229945455:
                if (str.equals("pref_liveness_debug_mode")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 681605067:
                if (str.equals("pref_frame_counts_list")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1471035235:
                if (str.equals("pref_picture_package_counts_list")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1519496397:
                if (str.equals("pref_return_type")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2122692179:
                if (str.equals("pre_is_preposition")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                m16276a(this.f9799b.getBoolean("pref_liveness_debug_mode", false));
                return;
            case 1:
                int intValue = Integer.valueOf(this.f9799b.getString("pref_frame_counts_list", "defult")).intValue();
                int intValue2 = Integer.valueOf(this.f9799b.getString("pref_picture_package_counts_list", "defult")).intValue();
                if (intValue2 > intValue) {
                    Toast makeText = Toast.makeText(this, "检测帧数不能小于图片打包数量", 0);
                    makeText.setGravity(17, 0, 0);
                    View view = makeText.getView();
                    view.setBackgroundResource(17170453);
                    makeText.setView(view);
                    makeText.show();
                    this.f9807j.setValue(String.valueOf(intValue2));
                }
                listPreference = this.f9807j;
                sb = new StringBuilder();
                sb.append("当前");
                sb.append(this.f9799b.getString("pref_frame_counts_list", "defult"));
                str2 = "帧   ";
                sb.append(str2);
                listPreference.setSummary(sb.toString());
                return;
            case 2:
                listPreference = this.f9808k;
                sb = new StringBuilder();
                sb.append("当前");
                sharedPreferences2 = this.f9799b;
                str3 = "pref_liveness_detection_overtime_list";
                sb.append(sharedPreferences2.getString(str3, "defult"));
                str2 = "秒   ";
                sb.append(str2);
                listPreference.setSummary(sb.toString());
                return;
            case 3:
                listPreference = this.f9809l;
                sb = new StringBuilder();
                sb.append("当前");
                sharedPreferences2 = this.f9799b;
                str3 = "pre_inspection_countdown_time_list";
                sb.append(sharedPreferences2.getString(str3, "defult"));
                str2 = "秒   ";
                sb.append(str2);
                listPreference.setSummary(sb.toString());
                return;
            case 4:
                int intValue3 = Integer.valueOf(this.f9799b.getString("pref_frame_counts_list", "defult")).intValue();
                if (Integer.valueOf(this.f9799b.getString("pref_picture_package_counts_list", "defult")).intValue() > intValue3) {
                    Toast makeText2 = Toast.makeText(this, "图片打包数量不能大于检测帧数", 0);
                    makeText2.setGravity(17, 0, 0);
                    View view2 = makeText2.getView();
                    view2.setBackgroundResource(17170456);
                    makeText2.setView(view2);
                    makeText2.show();
                    this.f9810m.setValue(String.valueOf(intValue3));
                }
                listPreference = this.f9810m;
                sb = new StringBuilder();
                sb.append("当前");
                sb.append(this.f9799b.getString("pref_picture_package_counts_list", "defult"));
                str2 = "张   ";
                sb.append(str2);
                listPreference.setSummary(sb.toString());
                return;
            case 5:
                floatValue = Float.valueOf(this.f9799b.getString("pref_liveness_param", "defult")).floatValue();
                Log.e("pref_liveness_param", String.valueOf(floatValue));
                if (floatValue > 1.0f) {
                    Toast makeText3 = Toast.makeText(this, "活体阈值不能大于1,已设置为默认值", 0);
                    makeText3.setGravity(17, 0, 0);
                    View view3 = makeText3.getView();
                    view3.setBackgroundResource(17170456);
                    makeText3.setView(view3);
                    makeText3.show();
                    floatValue = 0.5f;
                }
                this.f9811n.setText(String.valueOf(floatValue));
                editTextPreference = this.f9811n;
                sb2 = new StringBuilder();
                sb2.append("当前");
                sb2.append(String.valueOf(floatValue));
                sb2.append("   ");
                editTextPreference.setSummary(sb2.toString());
                return;
            case 6:
                floatValue = Float.valueOf(this.f9799b.getString("pref_enfilade_param", "defult")).floatValue();
                Log.e("pref_enfilade_param", String.valueOf(floatValue));
                if (floatValue > 1.0f) {
                    Toast makeText4 = Toast.makeText(this, "阈值不能大于1,已设置为默认值", 0);
                    makeText4.setGravity(17, 0, 0);
                    View view4 = makeText4.getView();
                    view4.setBackgroundResource(17170456);
                    makeText4.setView(view4);
                    makeText4.show();
                    floatValue = 0.45f;
                }
                this.f9812o.setText(String.valueOf(floatValue));
                editTextPreference = this.f9812o;
                sb2 = new StringBuilder();
                sb2.append("当前");
                sb2.append(String.valueOf(floatValue));
                sb2.append("   ");
                editTextPreference.setSummary(sb2.toString());
                return;
            case 7:
                if (this.f9799b.getBoolean("pref_return_type", false)) {
                    this.f9802e.setSummary("当前返回视频   ");
                    checkBoxPreference = this.f9802e;
                    str4 = "视频";
                } else {
                    this.f9802e.setSummary("当前返回照片   ");
                    checkBoxPreference = this.f9802e;
                    str4 = "照片";
                }
                checkBoxPreference.setTitle(str4);
                return;
            case '\b':
                if (this.f9799b.getBoolean("pre_is_preposition", true)) {
                    checkBoxPreference2 = this.f9804g;
                    str5 = "当前前置摄像头   ";
                } else {
                    checkBoxPreference2 = this.f9804g;
                    str5 = "当前后置摄像头   ";
                }
                checkBoxPreference2.setSummary(str5);
                return;
            default:
                return;
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
