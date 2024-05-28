package com.chinaunicon.jtwifilib.core.net.parser;

import android.os.Build;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.IOException;
import java.lang.reflect.Type;
import okhttp3.Response;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class OkJsonParser<T> extends OkBaseJsonParser<T> {
    private static final String TAG = "OkJsonParser";
    protected Gson mGson;

    public OkJsonParser() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.mGson = new GsonBuilder().excludeFieldsWithModifiers(16, 128, 8).create();
        } else {
            this.mGson = new Gson();
        }
    }

    @Override // com.chinaunicon.jtwifilib.core.net.parser.OkBaseJsonParser, com.chinaunicon.jtwifilib.core.net.parser.OkBaseParser
    public T parse(Response response) throws IOException {
        String string = response.body().string();
        Log.i(TAG, "parse: body=" + string);
        Gson gson = this.mGson;
        Type type = this.mType;
        return !(gson instanceof Gson) ? (T) gson.fromJson(string, type) : (T) NBSGsonInstrumentation.fromJson(gson, string, type);
    }
}
