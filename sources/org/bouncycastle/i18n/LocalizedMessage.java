package org.bouncycastle.i18n;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;
import org.bouncycastle.i18n.filter.Filter;
import org.bouncycastle.i18n.filter.TrustedInput;
import org.bouncycastle.i18n.filter.UntrustedInput;
import org.bouncycastle.i18n.filter.UntrustedUrlInput;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LocalizedMessage {
    public static final String DEFAULT_ENCODING = "ISO-8859-1";
    protected FilteredArguments arguments;
    protected String encoding;
    protected FilteredArguments extraArgs;

    /* renamed from: filter  reason: collision with root package name */
    protected Filter f27872filter;

    /* renamed from: id */
    protected final String f26860id;
    protected ClassLoader loader;
    protected final String resource;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class FilteredArguments {
        protected static final int FILTER = 1;
        protected static final int FILTER_URL = 2;
        protected static final int NO_FILTER = 0;
        protected int[] argFilterType;
        protected Object[] arguments;

        /* renamed from: filter  reason: collision with root package name */
        protected Filter f27873filter;
        protected Object[] filteredArgs;
        protected boolean[] isLocaleSpecific;
        protected Object[] unpackedArgs;

        FilteredArguments() {
            this(new Object[0]);
        }

        FilteredArguments(Object[] objArr) {
            this.f27873filter = null;
            this.arguments = objArr;
            this.unpackedArgs = new Object[objArr.length];
            this.filteredArgs = new Object[objArr.length];
            this.isLocaleSpecific = new boolean[objArr.length];
            this.argFilterType = new int[objArr.length];
            for (int i = 0; i < objArr.length; i++) {
                if (objArr[i] instanceof TrustedInput) {
                    this.unpackedArgs[i] = ((TrustedInput) objArr[i]).getInput();
                    this.argFilterType[i] = 0;
                } else if (objArr[i] instanceof UntrustedInput) {
                    this.unpackedArgs[i] = ((UntrustedInput) objArr[i]).getInput();
                    if (objArr[i] instanceof UntrustedUrlInput) {
                        this.argFilterType[i] = 2;
                    } else {
                        this.argFilterType[i] = 1;
                    }
                } else {
                    this.unpackedArgs[i] = objArr[i];
                    this.argFilterType[i] = 1;
                }
                this.isLocaleSpecific[i] = this.unpackedArgs[i] instanceof LocaleString;
            }
        }

        private Object filter(int i, Object obj) {
            if (this.f27873filter != null) {
                if (obj == null) {
                    obj = "null";
                }
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        return this.f27873filter.doFilter(obj.toString());
                    case 2:
                        return this.f27873filter.doFilterUrl(obj.toString());
                    default:
                        return null;
                }
            }
            return obj;
        }

        public Object[] getArguments() {
            return this.arguments;
        }

        public Filter getFilter() {
            return this.f27873filter;
        }

        public Object[] getFilteredArgs(Locale locale) {
            Object filter2;
            Object[] objArr = new Object[this.unpackedArgs.length];
            int i = 0;
            while (true) {
                Object[] objArr2 = this.unpackedArgs;
                if (i >= objArr2.length) {
                    return objArr;
                }
                Object[] objArr3 = this.filteredArgs;
                if (objArr3[i] != null) {
                    filter2 = objArr3[i];
                } else {
                    Object obj = objArr2[i];
                    if (this.isLocaleSpecific[i]) {
                        filter2 = filter(this.argFilterType[i], ((LocaleString) obj).getLocaleString(locale));
                    } else {
                        filter2 = filter(this.argFilterType[i], obj);
                        this.filteredArgs[i] = filter2;
                    }
                }
                objArr[i] = filter2;
                i++;
            }
        }

        public boolean isEmpty() {
            return this.unpackedArgs.length == 0;
        }

        public void setFilter(Filter filter2) {
            if (filter2 != this.f27873filter) {
                for (int i = 0; i < this.unpackedArgs.length; i++) {
                    this.filteredArgs[i] = null;
                }
            }
            this.f27873filter = filter2;
        }
    }

    public LocalizedMessage(String str, String str2) throws NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.f27872filter = null;
        this.loader = null;
        if (str == null || str2 == null) {
            throw new NullPointerException();
        }
        this.f26860id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments();
    }

    public LocalizedMessage(String str, String str2, String str3) throws NullPointerException, UnsupportedEncodingException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.f27872filter = null;
        this.loader = null;
        if (str == null || str2 == null) {
            throw new NullPointerException();
        }
        this.f26860id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments();
        if (Charset.isSupported(str3)) {
            this.encoding = str3;
            return;
        }
        throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
    }

    public LocalizedMessage(String str, String str2, String str3, Object[] objArr) throws NullPointerException, UnsupportedEncodingException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.f27872filter = null;
        this.loader = null;
        if (str == null || str2 == null || objArr == null) {
            throw new NullPointerException();
        }
        this.f26860id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
        if (Charset.isSupported(str3)) {
            this.encoding = str3;
            return;
        }
        throw new UnsupportedEncodingException("The encoding \"" + str3 + "\" is not supported.");
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) throws NullPointerException {
        this.encoding = "ISO-8859-1";
        this.extraArgs = null;
        this.f27872filter = null;
        this.loader = null;
        if (str == null || str2 == null || objArr == null) {
            throw new NullPointerException();
        }
        this.f26860id = str2;
        this.resource = str;
        this.arguments = new FilteredArguments(objArr);
    }

    protected String addExtraArgs(String str, Locale locale) {
        if (this.extraArgs != null) {
            StringBuffer stringBuffer = new StringBuffer(str);
            Object[] filteredArgs = this.extraArgs.getFilteredArgs(locale);
            for (Object obj : filteredArgs) {
                stringBuffer.append(obj);
            }
            return stringBuffer.toString();
        }
        return str;
    }

    protected String formatWithTimeZone(String str, Object[] objArr, Locale locale, TimeZone timeZone) {
        MessageFormat messageFormat = new MessageFormat(" ");
        messageFormat.setLocale(locale);
        messageFormat.applyPattern(str);
        if (!timeZone.equals(TimeZone.getDefault())) {
            Format[] formats = messageFormat.getFormats();
            for (int i = 0; i < formats.length; i++) {
                if (formats[i] instanceof DateFormat) {
                    DateFormat dateFormat = (DateFormat) formats[i];
                    dateFormat.setTimeZone(timeZone);
                    messageFormat.setFormat(i, dateFormat);
                }
            }
        }
        return messageFormat.format(objArr);
    }

    public Object[] getArguments() {
        return this.arguments.getArguments();
    }

    public ClassLoader getClassLoader() {
        return this.loader;
    }

    public String getEntry(String str, Locale locale, TimeZone timeZone) throws MissingEntryException {
        String str2 = this.f26860id;
        if (str != null) {
            str2 = str2 + "." + str;
        }
        String str3 = str2;
        try {
            String string = (this.loader == null ? ResourceBundle.getBundle(this.resource, locale) : ResourceBundle.getBundle(this.resource, locale, this.loader)).getString(str3);
            if (!this.encoding.equals("ISO-8859-1")) {
                string = new String(string.getBytes("ISO-8859-1"), this.encoding);
            }
            if (!this.arguments.isEmpty()) {
                string = formatWithTimeZone(string, this.arguments.getFilteredArgs(locale), locale, timeZone);
            }
            return addExtraArgs(string, locale);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (MissingResourceException unused) {
            String str4 = "Can't find entry " + str3 + " in resource file " + this.resource + ".";
            String str5 = this.resource;
            ClassLoader classLoader = this.loader;
            if (classLoader == null) {
                classLoader = getClassLoader();
            }
            throw new MissingEntryException(str4, str5, str3, locale, classLoader);
        }
    }

    public Object[] getExtraArgs() {
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments == null) {
            return null;
        }
        return filteredArguments.getArguments();
    }

    public Filter getFilter() {
        return this.f27872filter;
    }

    public String getId() {
        return this.f26860id;
    }

    public String getResource() {
        return this.resource;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.loader = classLoader;
    }

    public void setExtraArgument(Object obj) {
        setExtraArguments(new Object[]{obj});
    }

    public void setExtraArguments(Object[] objArr) {
        if (objArr == null) {
            this.extraArgs = null;
            return;
        }
        this.extraArgs = new FilteredArguments(objArr);
        this.extraArgs.setFilter(this.f27872filter);
    }

    public void setFilter(Filter filter2) {
        this.arguments.setFilter(filter2);
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null) {
            filteredArguments.setFilter(filter2);
        }
        this.f27872filter = filter2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Resource: \"");
        stringBuffer.append(this.resource);
        stringBuffer.append("\" Id: \"");
        stringBuffer.append(this.f26860id);
        stringBuffer.append("\"");
        stringBuffer.append(" Arguments: ");
        stringBuffer.append(this.arguments.getArguments().length);
        stringBuffer.append(" normal");
        FilteredArguments filteredArguments = this.extraArgs;
        if (filteredArguments != null && filteredArguments.getArguments().length > 0) {
            stringBuffer.append(", ");
            stringBuffer.append(this.extraArgs.getArguments().length);
            stringBuffer.append(" extra");
        }
        stringBuffer.append(" Encoding: ");
        stringBuffer.append(this.encoding);
        stringBuffer.append(" ClassLoader: ");
        stringBuffer.append(this.loader);
        return stringBuffer.toString();
    }
}
