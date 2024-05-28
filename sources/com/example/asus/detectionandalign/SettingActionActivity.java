package com.example.asus.detectionandalign;

import android.content.SharedPreferences;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.view.KeyEvent;
import android.view.MenuItem;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SettingActionActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    /* renamed from: a */
    public static final String f9762a = "SettingActionActivity";

    /* renamed from: A */
    private EditTextPreference f9763A;

    /* renamed from: B */
    private EditTextPreference f9764B;

    /* renamed from: C */
    private EditTextPreference f9765C;

    /* renamed from: D */
    private EditTextPreference f9766D;

    /* renamed from: E */
    private PreferenceCategory f9767E;

    /* renamed from: F */
    private PreferenceCategory f9768F;

    /* renamed from: G */
    private PreferenceCategory f9769G;

    /* renamed from: H */
    private boolean f9770H;

    /* renamed from: I */
    private boolean f9771I;

    /* renamed from: J */
    private String f9772J;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private SharedPreferences f9773b = null;

    /* renamed from: c */
    private PreferenceScreen f9774c;

    /* renamed from: d */
    private CheckBoxPreference f9775d;

    /* renamed from: e */
    private CheckBoxPreference f9776e;

    /* renamed from: f */
    private CheckBoxPreference f9777f;

    /* renamed from: g */
    private CheckBoxPreference f9778g;

    /* renamed from: h */
    private CheckBoxPreference f9779h;

    /* renamed from: i */
    private CheckBoxPreference f9780i;

    /* renamed from: j */
    private CheckBoxPreference f9781j;

    /* renamed from: k */
    private CheckBoxPreference f9782k;

    /* renamed from: l */
    private ListPreference f9783l;

    /* renamed from: m */
    private ListPreference f9784m;

    /* renamed from: n */
    private ListPreference f9785n;

    /* renamed from: o */
    private ListPreference f9786o;

    /* renamed from: p */
    private ListPreference f9787p;

    /* renamed from: q */
    private ListPreference f9788q;

    /* renamed from: r */
    private ListPreference f9789r;

    /* renamed from: s */
    private ListPreference f9790s;

    /* renamed from: t */
    private ListPreference f9791t;

    /* renamed from: u */
    private ListPreference f9792u;

    /* renamed from: v */
    private ListPreference f9793v;

    /* renamed from: w */
    private ListPreference f9794w;

    /* renamed from: x */
    private EditTextPreference f9795x;

    /* renamed from: y */
    private EditTextPreference f9796y;

    /* renamed from: z */
    private EditTextPreference f9797z;

    /* renamed from: a */
    private void m16278a(String str) {
        if (str.equals("1")) {
            this.f9769G.addPreference(this.f9786o);
            this.f9769G.removePreference(this.f9787p);
        } else if (!str.equals("2")) {
            if (str.equals("3")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.removePreference(this.f9789r);
                this.f9769G.removePreference(this.f9790s);
                this.f9769G.removePreference(this.f9791t);
                this.f9769G.removePreference(this.f9792u);
                this.f9769G.removePreference(this.f9793v);
                this.f9769G.removePreference(this.f9794w);
            } else if (str.equals("4")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.addPreference(this.f9789r);
                this.f9769G.removePreference(this.f9790s);
                this.f9769G.removePreference(this.f9791t);
                this.f9769G.removePreference(this.f9792u);
                this.f9769G.removePreference(this.f9793v);
                this.f9769G.removePreference(this.f9794w);
            } else if (str.equals("5")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.addPreference(this.f9789r);
                this.f9769G.addPreference(this.f9790s);
                this.f9769G.removePreference(this.f9791t);
                this.f9769G.removePreference(this.f9792u);
                this.f9769G.removePreference(this.f9793v);
                this.f9769G.removePreference(this.f9794w);
            } else if (str.equals("6") || str.equals("7")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.addPreference(this.f9789r);
                this.f9769G.addPreference(this.f9790s);
                this.f9769G.addPreference(this.f9791t);
                this.f9769G.removePreference(this.f9792u);
                this.f9769G.removePreference(this.f9793v);
                this.f9769G.removePreference(this.f9794w);
            } else if (str.equals("8")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.addPreference(this.f9789r);
                this.f9769G.addPreference(this.f9790s);
                this.f9769G.addPreference(this.f9791t);
                this.f9769G.addPreference(this.f9792u);
                this.f9769G.addPreference(this.f9793v);
                this.f9769G.removePreference(this.f9794w);
            } else if (str.equals("9")) {
                this.f9769G.addPreference(this.f9786o);
                this.f9769G.addPreference(this.f9787p);
                this.f9769G.addPreference(this.f9788q);
                this.f9769G.addPreference(this.f9789r);
                this.f9769G.addPreference(this.f9790s);
                this.f9769G.addPreference(this.f9791t);
                this.f9769G.addPreference(this.f9792u);
                this.f9769G.addPreference(this.f9793v);
                this.f9769G.addPreference(this.f9794w);
                return;
            } else {
                return;
            }
        } else {
            this.f9769G.addPreference(this.f9786o);
            this.f9769G.addPreference(this.f9787p);
        }
        this.f9769G.removePreference(this.f9788q);
        this.f9769G.removePreference(this.f9789r);
        this.f9769G.removePreference(this.f9790s);
        this.f9769G.removePreference(this.f9791t);
        this.f9769G.removePreference(this.f9792u);
        this.f9769G.removePreference(this.f9793v);
        this.f9769G.removePreference(this.f9794w);
    }

    /* renamed from: a */
    private void m16277a(boolean z) {
        CheckBoxPreference checkBoxPreference;
        boolean z2;
        if (z) {
            this.f9768F.addPreference(this.f9781j);
            checkBoxPreference = this.f9776e;
            z2 = true;
        } else {
            this.f9768F.removePreference(this.f9781j);
            checkBoxPreference = this.f9776e;
            z2 = false;
        }
        checkBoxPreference.setEnabled(z2);
        this.f9785n.setEnabled(z2);
        this.f9777f.setEnabled(z2);
        this.f9780i.setEnabled(z2);
        this.f9778g.setEnabled(z2);
        this.f9779h.setEnabled(z2);
        this.f9782k.setEnabled(z2);
        this.f9783l.setEnabled(z2);
        this.f9784m.setEnabled(z2);
        this.f9795x.setEnabled(z2);
        this.f9796y.setEnabled(z2);
        this.f9797z.setEnabled(z2);
        this.f9763A.setEnabled(z2);
        this.f9764B.setEnabled(z2);
        this.f9765C.setEnabled(z2);
        this.f9766D.setEnabled(z2);
        this.f9786o.setEnabled(z2);
        this.f9787p.setEnabled(z2);
        this.f9788q.setEnabled(z2);
        this.f9789r.setEnabled(z2);
        this.f9790s.setEnabled(z2);
        this.f9791t.setEnabled(z2);
        this.f9792u.setEnabled(z2);
        this.f9793v.setEnabled(z2);
        this.f9794w.setEnabled(z2);
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ProcessInstructionsVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Payload for switch not found at 0x0990
        	at jadx.core.dex.visitors.ProcessInstructionsVisitor.attachSwitchData(ProcessInstructionsVisitor.java:123)
        	at jadx.core.dex.visitors.ProcessInstructionsVisitor.initJumps(ProcessInstructionsVisitor.java:53)
        	at jadx.core.dex.visitors.ProcessInstructionsVisitor.visit(ProcessInstructionsVisitor.java:40)
        */
    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 1368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.SettingActionActivity.onCreate(android.os.Bundle):void");
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

    /*  JADX ERROR: Failed to decode insn: 0x04A8: UNKNOWN(0x023E), method: com.example.asus.detectionandalign.SettingActionActivity.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04A8: UNKNOWN(0x023E)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(android.content.SharedPreferences r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 1430
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.SettingActionActivity.onSharedPreferenceChanged(android.content.SharedPreferences, java.lang.String):void");
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
