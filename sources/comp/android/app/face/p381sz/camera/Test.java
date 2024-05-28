package comp.android.app.face.p381sz.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.p086v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import comp.android.app.face.p381sz.camera.listener.ErrorListener;
import comp.android.app.face.p381sz.camera.listener.JCameraListener;
import comp.android.app.face.p381sz.camera.listener.OnPreviewCallback;

/* renamed from: comp.android.app.face.sz.camera.Test */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Test extends AppCompatActivity implements ErrorListener, JCameraListener, OnPreviewCallback {

    /* renamed from: a */
    public JCameraView f23886a;

    /* renamed from: b */
    public TextView f23887b;

    /* renamed from: c */
    public boolean f23888c = false;

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void AudioPermissionError() {
    }

    /* renamed from: a */
    public String m2237a(Context context) {
        return (("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) ? context.getExternalCacheDir() : context.getCacheDir()).getPath();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void captureSuccess(byte[] bArr) {
        Log.e("================================  拍照图片", "  ");
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C11746R.C11750layout.test_activity);
        this.f23886a = (JCameraView) findViewById(C11746R.C11749id.surfaceView);
        this.f23887b = (TextView) findViewById(C11746R.C11749id.text);
        this.f23886a.start(0);
        JCameraView jCameraView = this.f23886a;
        jCameraView.setSaveVideoPath(m2237a(this) + "/Media");
        this.f23886a.setPlayVideo(false);
        this.f23886a.setFeatures(259);
        this.f23886a.setMediaQuality(1600000);
        this.f23886a.setCameraSettings(false);
        this.f23886a.setCaptureLayout(true);
        this.f23886a.setOnPreviewCallback(this);
        this.f23886a.setErrorLisenter(this);
        this.f23886a.setJCameraLisenter(this);
        this.f23887b.setOnClickListener(new View.OnClickListener() { // from class: comp.android.app.face.sz.camera.Test.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TextView textView;
                String str;
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (Test.this.f23888c) {
                    Test test = Test.this;
                    test.f23888c = false;
                    test.f23886a.stopRecordFrame();
                    textView = Test.this.f23887b;
                    str = "开始拍摄";
                } else {
                    Test test2 = Test.this;
                    test2.f23888c = true;
                    test2.f23886a.recordFrame();
                    textView = Test.this.f23887b;
                    str = "正在拍摄";
                }
                textView.setText(str);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.f23886a.onDestroy();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.ErrorListener
    public void onError() {
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.f23886a.onPause();
    }

    @Override // comp.android.app.face.p381sz.camera.listener.OnPreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.f23886a.onResume();
        this.f23886a.setAutoFocus(1L);
    }

    @Override // comp.android.app.face.p381sz.camera.listener.JCameraListener
    public void recordSuccess(String str, Bitmap bitmap) {
        Log.e("================================  视频路径", "  " + str);
    }
}
