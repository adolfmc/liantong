package com.chinaunicon.jtwifilib.core.net.parser;

import com.chinaunicon.jtwifilib.core.utils.JtL;
import java.io.IOException;
import okhttp3.Response;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class OkBaseParser<T> {
    protected int code;

    protected abstract T parse(Response response) throws IOException;

    public T parseResponse(Response response) throws IOException {
        JtL.m16339i("PAS", "+++parseResponse");
        this.code = response.code();
        return parse(response);
    }

    public int getCode() {
        return this.code;
    }
}
