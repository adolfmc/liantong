package com.chinaunicon.jtwifilib.core.net.callback;

import android.os.Handler;
import android.os.Looper;
import com.chinaunicon.jtwifilib.core.net.parser.OkBaseParser;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class OkCallback<T> implements Callback {
    private static final String TAG = "OkCallback";
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private OkBaseParser<T> mParser;

    public abstract void onFailure(Exception exc);

    public void onStart() {
    }

    public abstract void onSuccess(int i, String str);

    public OkCallback(OkBaseParser<T> okBaseParser) {
        if (okBaseParser == null) {
            throw new IllegalArgumentException("Parser can't be null");
        }
        this.mParser = okBaseParser;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, final IOException iOException) {
        mHandler.post(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.net.callback.OkCallback.1
            @Override // java.lang.Runnable
            public void run() {
                OkCallback.this.onFailure(iOException);
            }
        });
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws IOException {
        final String string = response.body().string();
        JtL.m16341e(TAG, "onResponse=" + string);
        JtL.m16339i(TAG, "response.isSuccessful()=" + response.isSuccessful());
        JtL.m16339i(TAG, "response.isSuccessful()=" + response.code());
        try {
            if (response.isSuccessful()) {
                mHandler.post(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.net.callback.OkCallback.2
                    @Override // java.lang.Runnable
                    public void run() {
                        OkCallback.this.onSuccess(0, string);
                    }
                });
            } else if (response.code() == 401) {
                mHandler.post(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.net.callback.OkCallback.3
                    @Override // java.lang.Runnable
                    public void run() {
                        OkCallback.this.onSuccess(0, string);
                    }
                });
            } else {
                mHandler.post(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.net.callback.OkCallback.4
                    @Override // java.lang.Runnable
                    public void run() {
                        OkCallback.this.onSuccess(-1, "服务器错误");
                    }
                });
            }
        } catch (Exception e) {
            mHandler.post(new Runnable() { // from class: com.chinaunicon.jtwifilib.core.net.callback.OkCallback.5
                @Override // java.lang.Runnable
                public void run() {
                    OkCallback.this.onFailure(e);
                }
            });
        }
    }
}
