package org.apache.http.client.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class UrlEncodedFormEntity extends StringEntity {
    public UrlEncodedFormEntity(List<? extends NameValuePair> list, String str) throws UnsupportedEncodingException {
        super(URLEncodedUtils.format(list, str), str);
        setContentType("application/x-www-form-urlencoded");
    }

    public UrlEncodedFormEntity(List<? extends NameValuePair> list) throws UnsupportedEncodingException {
        super(URLEncodedUtils.format(list, "ISO-8859-1"), "ISO-8859-1");
        setContentType("application/x-www-form-urlencoded");
    }
}
