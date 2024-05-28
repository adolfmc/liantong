package com.megvii.lv5.sdk.detect.distance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.SurfaceTexture;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5462h2;
import com.megvii.lv5.C5469i0;
import com.megvii.lv5.C5492l;
import com.megvii.lv5.C5515n0;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5538q0;
import com.megvii.lv5.C5553s1;
import com.megvii.lv5.C5627v1;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.EnumC5548r2;
import com.megvii.lv5.InterfaceC5526o1;
import com.megvii.lv5.InterfaceC5618u;
import com.megvii.lv5.sdk.C5559R;
import com.megvii.lv5.sdk.base.DetectBaseActivity;
import com.megvii.lv5.sdk.detect.MegliveModeImpl;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import com.megvii.lv5.sdk.view.CameraGLSurfaceViewNew;
import com.megvii.lv5.sdk.view.EggView;
import com.megvii.lv5.sdk.view.RadarView;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DistanceFlashDetectActivity extends DetectBaseActivity<C5469i0> implements InterfaceC5526o1, InterfaceC5618u {

    /* renamed from: D */
    public static final /* synthetic */ int f13444D = 0;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: f */
    public String f13453f;

    /* renamed from: g */
    public String f13454g;

    /* renamed from: h */
    public LinearLayout f13455h;

    /* renamed from: i */
    public ImageView f13456i;

    /* renamed from: j */
    public ImageView f13457j;

    /* renamed from: k */
    public TextView f13458k;

    /* renamed from: l */
    public ImageView f13459l;

    /* renamed from: m */
    public ImageView f13460m;

    /* renamed from: n */
    public ImageView f13461n;

    /* renamed from: o */
    public ImageView f13462o;

    /* renamed from: p */
    public TextView f13463p;

    /* renamed from: q */
    public TextView f13464q;

    /* renamed from: r */
    public Context f13465r;

    /* renamed from: s */
    public View.OnClickListener f13466s;

    /* renamed from: t */
    public SurfaceTexture f13467t;

    /* renamed from: u */
    public CameraGLSurfaceViewNew f13468u;

    /* renamed from: v */
    public RadarView f13469v;

    /* renamed from: w */
    public EggView f13470w;

    /* renamed from: x */
    public C5515n0 f13471x;

    /* renamed from: a */
    public int f13448a = 1;

    /* renamed from: b */
    public boolean f13449b = false;

    /* renamed from: c */
    public boolean f13450c = false;

    /* renamed from: d */
    public int f13451d = 0;

    /* renamed from: e */
    public String f13452e = "";

    /* renamed from: y */
    public volatile boolean f13472y = false;

    /* renamed from: z */
    public boolean f13473z = false;

    /* renamed from: A */
    public boolean f13445A = false;

    /* renamed from: B */
    public Handler f13446B = new HandlerC5593a(Looper.getMainLooper());

    /* renamed from: C */
    public boolean f13447C = false;

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.distance.DistanceFlashDetectActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class HandlerC5593a extends Handler {
        public HandlerC5593a(Looper looper) {
            super(looper);
        }

        /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
            jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fe: MOVE  (r0 I:??[long, double]) = (r347 I:??[long, double]), expected to be less than 11
            	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
            	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
            */
        @Override // android.os.Handler
        public void handleMessage(@android.support.annotation.NonNull android.os.Message r10) {
            /*
                Method dump skipped, instructions count: 1090
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.distance.DistanceFlashDetectActivity.HandlerC5593a.handleMessage(android.os.Message):void");
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.sdk.detect.distance.DistanceFlashDetectActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class DialogInterface$OnClickListenerC5594b implements DialogInterface.OnClickListener {
        public DialogInterface$OnClickListenerC5594b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            DistanceFlashDetectActivity.this.isRequestingScreenRecordPermission = false;
            dialogInterface.dismiss();
            DistanceFlashDetectActivity distanceFlashDetectActivity = DistanceFlashDetectActivity.this;
            distanceFlashDetectActivity.m13052a(EnumC5548r2.SCRN_AUTHORIZATION_FAIL, distanceFlashDetectActivity.f13453f);
        }
    }

    /* compiled from: Proguard */
    @NBSInstrumented
    /* renamed from: com.megvii.lv5.sdk.detect.distance.DistanceFlashDetectActivity$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class View$OnClickListenerC5595c implements View.OnClickListener {

        /* renamed from: a */
        public WeakReference<DistanceFlashDetectActivity> f13476a;

        public View$OnClickListenerC5595c(DistanceFlashDetectActivity distanceFlashDetectActivity) {
            this.f13476a = new WeakReference<>(distanceFlashDetectActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            DistanceFlashDetectActivity distanceFlashDetectActivity = this.f13476a.get();
            if (distanceFlashDetectActivity != null) {
                if (distanceFlashDetectActivity.isFinishing()) {
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                } else if (view.getId() == C5559R.C5562id.ll_detect_close) {
                    distanceFlashDetectActivity.m13043q();
                } else {
                    JSONObject jSONObject = null;
                    if (view.getId() == C5559R.C5562id.tv_megvii_dialog_left) {
                        int i = DistanceFlashDetectActivity.f13444D;
                        AlertDialog alertDialog = distanceFlashDetectActivity.alertDialog;
                        if (alertDialog != null) {
                            alertDialog.dismiss();
                        }
                        C5402d.f12429a = distanceFlashDetectActivity.f13452e;
                        String str = distanceFlashDetectActivity.f13453f;
                        int i2 = distanceFlashDetectActivity.f13448a;
                        if (!C5402d.f12432d) {
                            try {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("type", "track");
                                jSONObject2.put("project", C5402d.f12429a);
                                jSONObject2.put("event_id", UUID.randomUUID().toString());
                                jSONObject2.put("time", System.currentTimeMillis());
                                jSONObject2.put("event", "click_cancel_quit");
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("liveness", i2);
                                jSONObject3.put("biz_token", str);
                                jSONObject3.put("try_times", 0);
                                int i3 = C5402d.f12431c + 1;
                                C5402d.f12431c = i3;
                                jSONObject3.put("index", i3);
                                jSONObject2.put("properties", jSONObject3);
                                C5402d.f12430b = "click_cancel_quit";
                                jSONObject = jSONObject2;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                        distanceFlashDetectActivity.f13451d++;
                        C5469i0 presenter = distanceFlashDetectActivity.getPresenter();
                        presenter.getModel().m13151e();
                        presenter.f12727B.clear();
                        presenter.f12728C.clear();
                        presenter.f12772k = -1;
                        presenter.f12774l = -1;
                        presenter.getModel().m13149g();
                        presenter.getModel().m13150f();
                        presenter.m13469l();
                        File file = new File(presenter.f12787w);
                        if (file.exists()) {
                            C5527o2.m13247a(file);
                        }
                        file.mkdir();
                        C5627v1 c5627v1 = distanceFlashDetectActivity.f13468u.f13540c;
                        if (c5627v1 != null) {
                            c5627v1.f13757b.f12846a.mo13271a(c5627v1);
                            c5627v1.f13757b.f12846a.mo13273a(c5627v1.f13762g);
                        }
                        distanceFlashDetectActivity.getPresenter().m13466o();
                        C5538q0.m13181b((Activity) distanceFlashDetectActivity);
                    } else if (view.getId() == C5559R.C5562id.tv_megvii_dialog_right) {
                        int i4 = DistanceFlashDetectActivity.f13444D;
                        AlertDialog alertDialog2 = distanceFlashDetectActivity.alertDialog;
                        if (alertDialog2 != null) {
                            alertDialog2.dismiss();
                        }
                        C5402d.f12429a = distanceFlashDetectActivity.f13452e;
                        String str2 = distanceFlashDetectActivity.f13453f;
                        int i5 = distanceFlashDetectActivity.f13448a;
                        if (!C5402d.f12432d) {
                            try {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("type", "track");
                                jSONObject4.put("project", C5402d.f12429a);
                                jSONObject4.put("event_id", UUID.randomUUID().toString());
                                jSONObject4.put("time", System.currentTimeMillis());
                                jSONObject4.put("event", "click_confirm_quit");
                                JSONObject jSONObject5 = new JSONObject();
                                jSONObject5.put("liveness", i5);
                                jSONObject5.put("biz_token", str2);
                                jSONObject5.put("try_times", 0);
                                int i6 = C5402d.f12431c + 1;
                                C5402d.f12431c = i6;
                                jSONObject5.put("index", i6);
                                jSONObject4.put("properties", jSONObject5);
                                C5402d.f12430b = "click_confirm_quit";
                                jSONObject = jSONObject4;
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                        C5399c3.m13606a(jSONObject);
                        distanceFlashDetectActivity.m13052a(EnumC5548r2.USER_CANCEL, distanceFlashDetectActivity.f13453f);
                    }
                }
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: a */
    public static void m13051a(DistanceFlashDetectActivity distanceFlashDetectActivity, TextView textView, String str, int i) {
        distanceFlashDetectActivity.getClass();
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
        } else {
            textView.setText(str);
        }
        textView.setTextColor(i);
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m13050a(DistanceFlashDetectActivity distanceFlashDetectActivity, boolean z) {
        distanceFlashDetectActivity.f13472y = z;
        return z;
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m13049b(DistanceFlashDetectActivity distanceFlashDetectActivity, boolean z) {
        distanceFlashDetectActivity.isRequestingScreenRecordPermission = z;
        return z;
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12988a() {
        m13054a(109, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12986a(int i, float f) {
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        bundle.putFloat("progress", f);
        m13054a(106, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12985a(int i, float f, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("color", str);
        bundle.putInt("curStep", i);
        bundle.putFloat("progress", f);
        m13054a(103, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12984a(int i, int i2) {
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        bundle.putInt("qualityResult", i2);
        m13054a(102, bundle);
    }

    /* renamed from: a */
    public final void m13054a(int i, Bundle bundle) {
        Message message = new Message();
        message.what = i;
        message.setData(bundle);
        this.f13446B.sendMessage(message);
    }

    /* renamed from: a */
    public final void m13053a(int i, String str, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", i == 1 ? EnumC5548r2.LIVENESS_TIME_OUT : i == 0 ? EnumC5548r2.LIVENESS_FINISH : i == 5 ? EnumC5548r2.SCRN_RECORD_FAIL : i == 7 ? EnumC5548r2.VIDEO_SAVE_FAIL : EnumC5548r2.LIVENESS_FAILURE);
        bundle.putByteArray("delta", bArr);
        m13054a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12983a(SurfaceTexture surfaceTexture) {
        synchronized (this) {
            this.f13467t = surfaceTexture;
            if (this.f13450c) {
                m13042r();
            }
        }
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12981a(boolean z) {
        if (!z) {
            m13052a(EnumC5548r2.DEVICE_NOT_SUPPORT, this.f13453f);
            return;
        }
        synchronized (this) {
            this.f13450c = true;
            if (this.f13467t == null) {
                return;
            }
            m13042r();
        }
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: b */
    public String mo12980b() {
        return this.apiKey;
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: b */
    public void mo12979b(int i) {
        if (this.f13473z) {
            return;
        }
        C5469i0 presenter = getPresenter();
        presenter.getClass();
        try {
            byte[] m13474g = presenter.m13474g();
            String m13228d = C5527o2.m13228d();
            C5402d.f12429a = presenter.f12768i;
            String str = presenter.f12778n;
            int i2 = presenter.f12779o;
            JSONObject jSONObject = null;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "pass_detect");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i2);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject3.put("index", i3);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "pass_detect";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            String m13607a = C5399c3.m13607a();
            MegliveModeImpl model = presenter.getModel();
            boolean z = i == 0;
            byte[] bytes = "".getBytes();
            byte[] bytes2 = "".getBytes();
            byte[] bytes3 = "".getBytes();
            byte[] bytes4 = "".getBytes();
            C5492l c5492l = presenter.mCameraManager;
            model.m13159a(m13228d, z, false, m13607a, "", bytes, m13474g, bytes2, bytes3, bytes4, c5492l.f12850e, c5492l.f12851f);
        } catch (Exception e2) {
            e2.printStackTrace();
            "".getBytes();
        }
        getPresenter().getClass();
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: c */
    public void mo12978c() {
        m13054a(111, new Bundle());
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: c */
    public void mo12977c(int i) {
        if (i == 14 || i == 17) {
            this.f13472y = true;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("curStep", i);
        m13054a(115, bundle);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public C5469i0 createPresenter() {
        return new C5469i0();
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: e */
    public String mo12976e() {
        return this.videoKey;
    }

    /* renamed from: e */
    public final void m13048e(int i) {
        String mirroFailedMsg = getMirroFailedMsg(i);
        this.f13454g = mirroFailedMsg;
        TextView textView = this.f13463p;
        int color = getResources().getColor(this.livenessHomeNormalRemindTextColor);
        if (TextUtils.isEmpty(mirroFailedMsg)) {
            mirroFailedMsg = "";
        }
        textView.setText(mirroFailedMsg);
        textView.setTextColor(color);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public int getLayoutResId() {
        return C5559R.C5563layout.activity_distance_flash_liveness;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MediaProjection getScreenRecordContext() {
        return C5462h2.C5464b.f12721a.f12717d;
    }

    @Override // com.megvii.lv5.sdk.base.BaseView
    public MegLiveDetectConfig getUserDetectConfig() {
        return null;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initData() {
        this.f13473z = true;
        this.f13453f = C5527o2.m13256a(getContext());
        C5515n0 m13223h = C5527o2.m13223h(this);
        this.f13471x = m13223h;
        this.f13448a = m13223h.f12973b;
        this.f13463p.setTextSize(0, getResources().getDimension(this.livenessHomeRemindSize));
        this.f13464q.setTextSize(0, getResources().getDimension(this.livenessHomeLoadingTextSize));
        this.f13464q.setTextColor(-1);
        if (this.mIsShowLogo) {
            this.f13457j.setImageDrawable(getResources().getDrawable(this.livenessLogoDrawableId));
            this.f13457j.setVisibility(0);
        } else {
            this.f13457j.setVisibility(8);
        }
        C5515n0 c5515n0 = this.f13471x;
        if (c5515n0.f12913E1 == 1) {
            String string = this.f13473z ? getResources().getString(C5667z2.m12879a(this.f13465r).m12875d(getString(C5559R.string.key_liveness_home_prompt_text))) : c5515n0.f12916F1;
            if (!TextUtils.isEmpty(string)) {
                this.f13458k.setVisibility(0);
                this.f13458k.setText(string);
                if (this.livenessHomeCustomPromptBackgroundColor != 0) {
                    this.f13458k.setBackgroundColor(getResources().getColor(this.livenessHomeCustomPromptBackgroundColor));
                }
                if (this.livenessHomeCustomPromptTextColor != 0) {
                    this.f13458k.setTextColor(getResources().getColor(this.livenessHomeCustomPromptTextColor));
                }
            }
        }
        m13044p();
        this.f13449b = C5527o2.m13223h(this).f13020q1;
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity
    public void initView() {
        this.f13465r = getApplicationContext();
        this.f13468u = (CameraGLSurfaceViewNew) findViewById(C5559R.C5562id.liveness_layout_cameraView);
        this.f13469v = (RadarView) findViewById(C5559R.C5562id.radar_view);
        EggView eggView = (EggView) findViewById(C5559R.C5562id.egg_view);
        this.f13470w = eggView;
        eggView.setProgressCallback(this);
        this.f13466s = new View$OnClickListenerC5595c(this);
        this.f13458k = (TextView) findViewById(C5559R.C5562id.tv_liveness_top_tips);
        this.f13463p = (TextView) findViewById(C5559R.C5562id.tv_status_tips);
        this.f13464q = (TextView) findViewById(C5559R.C5562id.tv_finish_tips);
        this.f13457j = (ImageView) findViewById(C5559R.C5562id.iv_megvii_powerby);
        this.f13456i = (ImageView) findViewById(C5559R.C5562id.iv_liveness_homepage_close);
        LinearLayout linearLayout = (LinearLayout) findViewById(C5559R.C5562id.ll_detect_close);
        this.f13455h = linearLayout;
        linearLayout.setOnClickListener(this.f13466s);
        ImageView imageView = (ImageView) findViewById(C5559R.C5562id.tv_circle_topleft);
        this.f13459l = imageView;
        imageView.setImageBitmap(this.bitmapTop);
        ImageView imageView2 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomright);
        this.f13460m = imageView2;
        imageView2.setImageBitmap(this.bitmapBottom);
        ImageView imageView3 = (ImageView) findViewById(C5559R.C5562id.tv_circle_topright);
        this.f13461n = imageView3;
        imageView3.setImageBitmap(this.bitmapTop);
        ImageView imageView4 = (ImageView) findViewById(C5559R.C5562id.tv_circle_bottomleft);
        this.f13462o = imageView4;
        imageView4.setImageBitmap(this.bitmapBottom);
    }

    @Override // com.megvii.lv5.InterfaceC5526o1
    /* renamed from: l */
    public void mo13047l() {
        getPresenter().m13477e();
    }

    /* renamed from: n */
    public final void m13046n() {
        if (this.f13447C) {
            return;
        }
        getPresenter().m13463r();
        getPresenter().closeCamera();
        getPresenter().detach();
        this.f13468u.onPause();
        Handler handler = this.f13446B;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        EggView eggView = this.f13470w;
        if (eggView != null) {
            eggView.m13033c();
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        C5462h2.C5464b.f12721a.m13492a((Context) this);
        MediaProjection mediaProjection = this.mediaProjection;
        if (mediaProjection != null) {
            if (Build.VERSION.SDK_INT >= 21) {
                mediaProjection.stop();
            }
            this.mediaProjection = null;
        }
        this.f13447C = true;
    }

    /* renamed from: o */
    public final void m13045o() {
        this.f13446B.removeCallbacksAndMessages(null);
        C5469i0 presenter = getPresenter();
        presenter.getClass();
        try {
            C5492l c5492l = presenter.mCameraManager;
            if (c5492l != null) {
                c5492l.f12846a.mo13262c();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        getPresenter().m13463r();
        this.f13470w.m13033c();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013d  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActivityResult(int r11, int r12, android.content.Intent r13) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.lv5.sdk.detect.distance.DistanceFlashDetectActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 20);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        m13046n();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m13043q();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.megvii.lv5.sdk.base.DetectBaseActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (this.isRequestingScreenRecordPermission) {
            return;
        }
        try {
            m13045o();
            if (!isFinishing() && !this.f13472y) {
                C5402d.f12432d = true;
                m13052a(EnumC5548r2.GO_TO_BACKGROUND, this.f13453f);
            }
            if (isFinishing()) {
                m13046n();
            }
            super.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (this.f13472y) {
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        int i = this.f13471x.f12952R1;
        if (i != 0) {
            C5462h2 c5462h2 = C5462h2.C5464b.f12721a;
            if (c5462h2.f12717d == null && (!this.f13445A || this.isRequestingScreenRecordPermission)) {
                if (i == 1 && !C5538q0.m13193a((Context) this)) {
                    m13052a(EnumC5548r2.NO_AUDIO_RECORD_PERMISSION, this.f13453f);
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else if (this.f13445A) {
                    NBSAppInstrumentation.activityResumeEndIns();
                    return;
                } else {
                    C5402d.f12429a = "liveness-sdk";
                    String str = this.f13453f;
                    int i2 = this.f13448a;
                    JSONObject jSONObject = null;
                    if (!C5402d.f12432d) {
                        try {
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("type", "track");
                            jSONObject2.put("project", C5402d.f12429a);
                            jSONObject2.put("event_id", UUID.randomUUID().toString());
                            jSONObject2.put("time", System.currentTimeMillis());
                            jSONObject2.put("event", "scrn_enter_permissions_detect");
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("liveness", i2);
                            jSONObject3.put("biz_token", str);
                            jSONObject3.put("try_times", 0);
                            int i3 = C5402d.f12431c + 1;
                            C5402d.f12431c = i3;
                            jSONObject3.put("index", i3);
                            jSONObject2.put("properties", jSONObject3);
                            C5402d.f12430b = "scrn_enter_permissions_detect";
                            jSONObject = jSONObject2;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    C5399c3.m13606a(jSONObject);
                    this.f13445A = true;
                    this.isRequestingScreenRecordPermission = true;
                    c5462h2.m13493a((Activity) this);
                    NBSAppInstrumentation.activityResumeEndIns();
                }
            }
        }
        C5462h2 c5462h22 = C5462h2.C5464b.f12721a;
        if (c5462h22.f12717d != null) {
            c5462h22.m13493a((Activity) this);
        }
        getPresenter().openCamera();
        Bundle bundle = new Bundle();
        Message message = new Message();
        message.what = 100;
        message.setData(bundle);
        this.f13446B.sendMessageDelayed(message, 200L);
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

    /* renamed from: p */
    public final void m13044p() {
        LinearLayout linearLayout;
        int i;
        if (this.f13471x.f13044y1) {
            this.f13456i.setImageDrawable(getResources().getDrawable(this.livenessCloseDrawableId));
            linearLayout = this.f13455h;
            i = 0;
        } else {
            linearLayout = this.f13455h;
            i = 8;
        }
        linearLayout.setVisibility(i);
    }

    /* renamed from: q */
    public void m13043q() {
        if (this.f13472y || !this.f13471x.f13044y1) {
            return;
        }
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            this.alertDialog = this.mDialogUtil.m13172a(this.f13473z, this.f13466s);
            C5402d.f12429a = this.f13452e;
            String str = this.f13453f;
            int i = this.f13448a;
            JSONObject jSONObject = null;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "click_quit_icon");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i2 = C5402d.f12431c + 1;
                    C5402d.f12431c = i2;
                    jSONObject3.put("index", i2);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "click_quit_icon";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            m13045o();
        }
    }

    /* renamed from: r */
    public final void m13042r() {
        C5402d.f12429a = "liveness-sdk";
        String str = this.f13453f;
        int i = this.f13448a;
        JSONObject jSONObject = null;
        if (!C5402d.f12432d) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "track");
                jSONObject2.put("project", C5402d.f12429a);
                jSONObject2.put("event_id", UUID.randomUUID().toString());
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("event", "enter_page_success");
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("liveness", i);
                jSONObject3.put("biz_token", str);
                jSONObject3.put("try_times", 0);
                int i2 = C5402d.f12431c + 1;
                C5402d.f12431c = i2;
                jSONObject3.put("index", i2);
                jSONObject2.put("properties", jSONObject3);
                C5402d.f12430b = "enter_page_success";
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        C5399c3.m13606a(jSONObject);
        getPresenter().m13466o();
        C5627v1 c5627v1 = this.f13468u.f13540c;
        if (c5627v1 != null) {
            c5627v1.f13757b.f12846a.mo13271a(c5627v1);
            C5492l c5492l = c5627v1.f13757b;
            c5492l.f12846a.mo13273a(c5627v1.f13762g);
        }
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12987a(int i) {
        if (!C5527o2.m13221j(this) && !C5527o2.m13226e(this)) {
            m13054a(108, new Bundle());
        }
        if (i != 0) {
            JSONObject jSONObject = null;
            if (i == 1) {
                C5402d.f12429a = this.f13452e;
                String str = this.f13453f;
                int i2 = this.f13448a;
                boolean z = C5402d.f12432d;
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "fail_detect:time_out");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i2);
                    jSONObject3.put("biz_token", str);
                    jSONObject3.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject3.put("index", i3);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "fail_detect:time_out";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (i == 5) {
                C5402d.f12429a = this.f13452e;
                String str2 = this.f13453f;
                int i4 = this.f13448a;
                boolean z2 = C5402d.f12432d;
                try {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("type", "track");
                    jSONObject4.put("project", C5402d.f12429a);
                    jSONObject4.put("event_id", UUID.randomUUID().toString());
                    jSONObject4.put("time", System.currentTimeMillis());
                    jSONObject4.put("event", "fail_detect:scrn_video_generate_fail");
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("liveness", i4);
                    jSONObject5.put("biz_token", str2);
                    jSONObject5.put("try_times", 0);
                    int i5 = C5402d.f12431c + 1;
                    C5402d.f12431c = i5;
                    jSONObject5.put("index", i5);
                    jSONObject4.put("properties", jSONObject5);
                    C5402d.f12430b = "fail_detect:scrn_video_generate_fail";
                    jSONObject = jSONObject4;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            } else if (i == 7) {
                C5402d.f12429a = this.f13452e;
                String str3 = this.f13453f;
                int i6 = this.f13448a;
                boolean z3 = C5402d.f12432d;
                try {
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("type", "track");
                    jSONObject6.put("project", C5402d.f12429a);
                    jSONObject6.put("event_id", UUID.randomUUID().toString());
                    jSONObject6.put("time", System.currentTimeMillis());
                    jSONObject6.put("event", "fail_detect:scrn_video_save_fail");
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put("liveness", i6);
                    jSONObject7.put("biz_token", str3);
                    jSONObject7.put("try_times", 0);
                    int i7 = C5402d.f12431c + 1;
                    C5402d.f12431c = i7;
                    jSONObject7.put("index", i7);
                    jSONObject6.put("properties", jSONObject7);
                    C5402d.f12430b = "fail_detect:scrn_video_save_fail";
                    jSONObject = jSONObject6;
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            } else {
                C5402d.f12429a = this.f13452e;
                String str4 = this.f13453f;
                int i8 = this.f13448a;
                boolean z4 = C5402d.f12432d;
                try {
                    JSONObject jSONObject8 = new JSONObject();
                    jSONObject8.put("type", "track");
                    jSONObject8.put("project", C5402d.f12429a);
                    jSONObject8.put("event_id", UUID.randomUUID().toString());
                    jSONObject8.put("time", System.currentTimeMillis());
                    jSONObject8.put("event", "fail_detect:action_fail");
                    JSONObject jSONObject9 = new JSONObject();
                    jSONObject9.put("liveness", i8);
                    jSONObject9.put("biz_token", str4);
                    jSONObject9.put("try_times", 0);
                    int i9 = C5402d.f12431c + 1;
                    C5402d.f12431c = i9;
                    jSONObject9.put("index", i9);
                    jSONObject8.put("properties", jSONObject9);
                    C5402d.f12430b = "fail_detect:action_fail";
                    jSONObject = jSONObject8;
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
        }
        byte[] m13490a = getPresenter().m13490a(i);
        if (this.f13473z) {
            String str5 = this.f13453f;
            if (i != 0) {
                m13490a = "".getBytes();
            }
            m13053a(i, str5, m13490a);
            return;
        }
        getPresenter().getClass();
        if (i != 0) {
            m13053a(i, this.f13453f, "".getBytes());
        } else {
            m13054a(110, new Bundle());
        }
    }

    /* renamed from: a */
    public final void m13052a(EnumC5548r2 enumC5548r2, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("bizToken", str);
        bundle.putSerializable("failedType", enumC5548r2);
        bundle.putByteArray("delta", "".getBytes());
        String str2 = "";
        if (enumC5548r2 == EnumC5548r2.DEVICE_NOT_SUPPORT) {
            str2 = "camera_fail";
        } else if (enumC5548r2 == EnumC5548r2.GO_TO_BACKGROUND) {
            str2 = "go_to_background";
        } else if (enumC5548r2 == EnumC5548r2.USER_CANCEL) {
            str2 = "user_cancel";
        } else if (enumC5548r2 == EnumC5548r2.SCRN_AUTHORIZATION_FAIL) {
            str2 = "scrn_authorized_fail";
        }
        C5402d.f12429a = this.f13452e;
        String str3 = "fail_detect:" + str2;
        String str4 = this.f13453f;
        int i = this.f13448a;
        JSONObject jSONObject = null;
        if (!C5402d.f12432d || str3.contains("fail_detect")) {
            try {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("type", "track");
                jSONObject2.put("project", C5402d.f12429a);
                jSONObject2.put("event_id", UUID.randomUUID().toString());
                jSONObject2.put("time", System.currentTimeMillis());
                jSONObject2.put("event", str3);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("liveness", i);
                jSONObject3.put("biz_token", str4);
                jSONObject3.put("try_times", 0);
                int i2 = C5402d.f12431c + 1;
                C5402d.f12431c = i2;
                jSONObject3.put("index", i2);
                jSONObject2.put("properties", jSONObject3);
                C5402d.f12430b = str3;
                jSONObject = jSONObject2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        C5399c3.m13606a(jSONObject);
        if (!this.f13473z) {
            getPresenter().m13490a(4);
            getPresenter().getClass();
        }
        m13054a(104, bundle);
    }

    @Override // com.megvii.lv5.InterfaceC5618u
    /* renamed from: a */
    public void mo12982a(C5492l c5492l, C5553s1.InterfaceC5554a interfaceC5554a, C5553s1.InterfaceC5555b interfaceC5555b) {
        CameraGLSurfaceViewNew cameraGLSurfaceViewNew = this.f13468u;
        cameraGLSurfaceViewNew.f13539b = c5492l;
        cameraGLSurfaceViewNew.f13541d = interfaceC5554a;
        cameraGLSurfaceViewNew.f13542e = interfaceC5555b;
        C5627v1 c5627v1 = new C5627v1(cameraGLSurfaceViewNew.f13538a, c5492l, interfaceC5554a, interfaceC5555b);
        cameraGLSurfaceViewNew.f13540c = c5627v1;
        c5627v1.f13758c = cameraGLSurfaceViewNew.f13543f;
        cameraGLSurfaceViewNew.setPreserveEGLContextOnPause(true);
        cameraGLSurfaceViewNew.setEGLContextClientVersion(2);
        cameraGLSurfaceViewNew.setRenderer(cameraGLSurfaceViewNew.f13540c);
        cameraGLSurfaceViewNew.setRenderMode(1);
    }
}
