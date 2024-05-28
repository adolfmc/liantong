package android.net.http;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpAuthHeader {
    private static final String ALGORITHM_TOKEN = "algorithm";
    public static final int BASIC = 1;
    public static final String BASIC_TOKEN = "Basic";
    public static final int DIGEST = 2;
    public static final String DIGEST_TOKEN = "Digest";
    private static final String NONCE_TOKEN = "nonce";
    private static final String OPAQUE_TOKEN = "opaque";
    private static final String QOP_TOKEN = "qop";
    private static final String REALM_TOKEN = "realm";
    private static final String STALE_TOKEN = "stale";
    public static final int UNKNOWN = 0;
    private String mAlgorithm;
    private boolean mIsProxy;
    private String mNonce;
    private String mOpaque;
    private String mPassword;
    private String mQop;
    private String mRealm;
    private int mScheme;
    private boolean mStale;
    private String mUsername;

    public HttpAuthHeader(String str) {
        if (str != null) {
            parseHeader(str);
        }
    }

    public boolean isProxy() {
        return this.mIsProxy;
    }

    public void setProxy() {
        this.mIsProxy = true;
    }

    public String getUsername() {
        return this.mUsername;
    }

    public void setUsername(String str) {
        this.mUsername = str;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String str) {
        this.mPassword = str;
    }

    public boolean isBasic() {
        return this.mScheme == 1;
    }

    public boolean isDigest() {
        return this.mScheme == 2;
    }

    public int getScheme() {
        return this.mScheme;
    }

    public boolean getStale() {
        return this.mStale;
    }

    public String getRealm() {
        return this.mRealm;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getOpaque() {
        return this.mOpaque;
    }

    public String getQop() {
        return this.mQop;
    }

    public String getAlgorithm() {
        return this.mAlgorithm;
    }

    public boolean isSupportedScheme() {
        if (this.mRealm != null) {
            int i = this.mScheme;
            if (i == 1) {
                return true;
            }
            if (i == 2 && this.mAlgorithm.equals("md5")) {
                String str = this.mQop;
                return str == null || str.equals("auth");
            }
            return false;
        }
        return false;
    }

    private void parseHeader(String str) {
        String parseScheme;
        if (str != null && (parseScheme = parseScheme(str)) != null && this.mScheme != 0) {
            parseParameters(parseScheme);
        }
    }

    private String parseScheme(String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(32)) >= 0) {
            String trim = str.substring(0, indexOf).trim();
            if (trim.equalsIgnoreCase("Digest")) {
                this.mScheme = 2;
                this.mAlgorithm = "md5";
            } else if (trim.equalsIgnoreCase("Basic")) {
                this.mScheme = 1;
            }
            return str.substring(indexOf + 1);
        }
        return null;
    }

    private void parseParameters(String str) {
        int indexOf;
        if (str != null) {
            do {
                indexOf = str.indexOf(44);
                if (indexOf < 0) {
                    parseParameter(str);
                    continue;
                } else {
                    parseParameter(str.substring(0, indexOf));
                    str = str.substring(indexOf + 1);
                    continue;
                }
            } while (indexOf >= 0);
        }
    }

    private void parseParameter(String str) {
        int indexOf;
        if (str != null && (indexOf = str.indexOf(61)) >= 0) {
            String trim = str.substring(0, indexOf).trim();
            String trimDoubleQuotesIfAny = trimDoubleQuotesIfAny(str.substring(indexOf + 1).trim());
            if (trim.equalsIgnoreCase("realm")) {
                this.mRealm = trimDoubleQuotesIfAny;
            } else if (this.mScheme == 2) {
                parseParameter(trim, trimDoubleQuotesIfAny);
            }
        }
    }

    private void parseParameter(String str, String str2) {
        if (str != null && str2 != null) {
            if (str.equalsIgnoreCase("nonce")) {
                this.mNonce = str2;
            } else if (str.equalsIgnoreCase("stale")) {
                parseStale(str2);
            } else if (str.equalsIgnoreCase("opaque")) {
                this.mOpaque = str2;
            } else if (str.equalsIgnoreCase("qop")) {
                this.mQop = str2.toLowerCase(Locale.ROOT);
            } else if (str.equalsIgnoreCase("algorithm")) {
                this.mAlgorithm = str2.toLowerCase(Locale.ROOT);
            }
        }
    }

    private void parseStale(String str) {
        if (str != null && str.equalsIgnoreCase("true")) {
            this.mStale = true;
        }
    }

    private static String trimDoubleQuotesIfAny(String str) {
        int length;
        if (str != null && (length = str.length()) > 2 && str.charAt(0) == '\"') {
            int i = length - 1;
            if (str.charAt(i) == '\"') {
                return str.substring(1, i);
            }
        }
        return str;
    }
}
