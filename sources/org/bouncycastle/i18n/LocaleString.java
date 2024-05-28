package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LocaleString extends LocalizedMessage {
    public LocaleString(String str, String str2) {
        super(str, str2);
    }

    public LocaleString(String str, String str2, String str3) throws NullPointerException, UnsupportedEncodingException {
        super(str, str2, str3);
    }

    public LocaleString(String str, String str2, String str3, Object[] objArr) throws NullPointerException, UnsupportedEncodingException {
        super(str, str2, str3, objArr);
    }

    public String getLocaleString(Locale locale) {
        return getEntry(null, locale, null);
    }
}
