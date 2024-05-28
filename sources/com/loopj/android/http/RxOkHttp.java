package com.loopj.android.http;

import android.text.TextUtils;
import com.loopj.android.http.AsyncHttpClient;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RxOkHttp {
    private static final String TAG = "RxOkHttp";

    public static Observable<String> execute(final OkHttpClient okHttpClient, final String str, final AsyncHttpClient.RequestBodyWay requestBodyWay, final String str2, final Map<String, String> map, final String str3, final Map<String, String> map2, final int i, final int i2) {
        return Observable.create(new ObservableOnSubscribe<Response>() { // from class: com.loopj.android.http.RxOkHttp.3
            @Override // io.reactivex.ObservableOnSubscribe
            public void subscribe(ObservableEmitter<Response> observableEmitter) throws Exception {
                String str4 = str2;
                Request.Builder builder = new Request.Builder();
                if ("GET".equalsIgnoreCase(str.toString())) {
                    Map map3 = map;
                    if (map3 != null) {
                        for (Map.Entry entry : map3.entrySet()) {
                            String str5 = (String) entry.getKey();
                            String str6 = (String) entry.getValue();
                            if (!TextUtils.isEmpty(str5)) {
                                String trim = str5.trim();
                                if (str4.contains("?")) {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str4);
                                    sb.append("&");
                                    sb.append(trim);
                                    sb.append("=");
                                    sb.append(TextUtils.isEmpty(str6) ? "" : str6.trim());
                                    str4 = sb.toString();
                                } else {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str4);
                                    sb2.append("?");
                                    sb2.append(trim);
                                    sb2.append("=");
                                    sb2.append(TextUtils.isEmpty(str6) ? "" : str6.trim());
                                    str4 = sb2.toString();
                                }
                            }
                        }
                    }
                } else if (requestBodyWay == AsyncHttpClient.RequestBodyWay.Form) {
                    FormBody.Builder builder2 = new FormBody.Builder();
                    Map map4 = map;
                    if (map4 != null) {
                        for (Map.Entry entry2 : map4.entrySet()) {
                            String str7 = (String) entry2.getKey();
                            String str8 = (String) entry2.getValue();
                            if (!TextUtils.isEmpty(str7)) {
                                builder2.add(str7.trim(), TextUtils.isEmpty(str8) ? "" : str8.trim());
                            }
                        }
                    }
                    builder.post(builder2.build());
                } else if (requestBodyWay == AsyncHttpClient.RequestBodyWay.JSON) {
                    builder.post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), TextUtils.isEmpty(str3) ? "" : str3));
                } else if (requestBodyWay == AsyncHttpClient.RequestBodyWay.TextXJSON) {
                    builder.post(RequestBody.create(MediaType.parse("text/x-json; charset=utf-8"), TextUtils.isEmpty(str3) ? "" : str3));
                } else {
                    throw new RuntimeException("RequestBodyWay参数设置错误，请检查！");
                }
                builder.url(str4);
                Map map5 = map2;
                if (map5 != null) {
                    for (Map.Entry entry3 : map5.entrySet()) {
                        String str9 = (String) entry3.getKey();
                        String str10 = (String) entry3.getValue();
                        if (!TextUtils.isEmpty(str9) && !TextUtils.isEmpty(str10)) {
                            builder.header(str9.trim(), str10.trim());
                        }
                    }
                }
                observableEmitter.onNext(okHttpClient.newCall(builder.build()).execute());
                observableEmitter.onComplete();
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() { // from class: com.loopj.android.http.RxOkHttp.2
            private int retryNum = 0;

            static /* synthetic */ int access$004(C52522 c52522) {
                int i3 = c52522.retryNum + 1;
                c52522.retryNum = i3;
                return i3;
            }

            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                return observable.flatMap(new Function<Throwable, ObservableSource<?>>() { // from class: com.loopj.android.http.RxOkHttp.2.1
                    @Override // io.reactivex.functions.Function
                    public ObservableSource<?> apply(Throwable th) throws Exception {
                        if (C52522.access$004(C52522.this) <= i && ((th instanceof UnknownHostException) || (th instanceof IOException))) {
                            return Observable.timer(i2, TimeUnit.MILLISECONDS);
                        }
                        if ((th instanceof UnknownHostException) || (th instanceof IOException)) {
                            return Observable.error(new NetworkErrorException(TextUtils.isEmpty(th.getMessage()) ? "OkHttp请求错误" : th.getMessage(), th));
                        }
                        return Observable.error(th);
                    }
                });
            }
        }).flatMap(new Function<Response, ObservableSource<String>>() { // from class: com.loopj.android.http.RxOkHttp.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<String> apply(Response response) throws Exception {
                if (response.isSuccessful()) {
                    return Observable.just(response.body().string());
                }
                int code = response.code();
                response.body().close();
                return Observable.error(new ErrorStatusCodeException("请求失败，状态码：" + code, code));
            }
        });
    }
}
