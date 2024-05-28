package android.net.compatibility;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class WebAddress {
    static final int MATCH_GROUP_AUTHORITY = 2;
    static final int MATCH_GROUP_HOST = 3;
    static final int MATCH_GROUP_PATH = 5;
    static final int MATCH_GROUP_PORT = 4;
    static final int MATCH_GROUP_SCHEME = 1;
    static Pattern sAddressPattern = Pattern.compile("(?:(http|https|file)\\:\\/\\/)?(?:([-A-Za-z0-9$_.+!*'(),;?&=]+(?:\\:[-A-Za-z0-9$_.+!*'(),;?&=]+)?)@)?([a-zA-Z0-9 -\ud7ff豈-\ufdcfﷰ-\uffef%_-][a-zA-Z0-9 -\ud7ff豈-\ufdcfﷰ-\uffef%_\\.-]*|\\[[0-9a-fA-F:\\.]+\\])?(?:\\:([0-9]*))?(\\/?[^#]*)?.*", 2);
    private String mAuthInfo;
    private String mHost;
    private String mPath;
    private int mPort;
    private String mScheme;

    public WebAddress(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new NullPointerException();
        }
        this.mScheme = "";
        this.mHost = "";
        this.mPort = -1;
        this.mPath = "/";
        this.mAuthInfo = "";
        Matcher matcher = sAddressPattern.matcher(str);
        if (matcher.matches()) {
            String group2 = matcher.group(1);
            if (group2 != null) {
                this.mScheme = group2.toLowerCase(Locale.ROOT);
            }
            String group3 = matcher.group(2);
            if (group3 != null) {
                this.mAuthInfo = group3;
            }
            String group4 = matcher.group(3);
            if (group4 != null) {
                this.mHost = group4;
            }
            String group5 = matcher.group(4);
            if (group5 != null && group5.length() > 0) {
                try {
                    this.mPort = Integer.parseInt(group5);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Bad port");
                }
            }
            String group6 = matcher.group(5);
            if (group6 != null && group6.length() > 0) {
                if (group6.charAt(0) != '/') {
                    this.mPath = "/" + group6;
                } else {
                    this.mPath = group6;
                }
            }
            if (this.mPort != 443 || !this.mScheme.equals("")) {
                if (this.mPort == -1) {
                    if (this.mScheme.equals("https")) {
                        this.mPort = 443;
                    } else {
                        this.mPort = 80;
                    }
                }
            } else {
                this.mScheme = "https";
            }
            if (this.mScheme.equals("")) {
                this.mScheme = "http";
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Bad address");
    }

    public String toString() {
        String str;
        if ((this.mPort != 443 && this.mScheme.equals("https")) || (this.mPort != 80 && this.mScheme.equals("http"))) {
            str = ":" + Integer.toString(this.mPort);
        } else {
            str = "";
        }
        return this.mScheme + "://" + (this.mAuthInfo.length() > 0 ? this.mAuthInfo + "@" : "") + this.mHost + str + this.mPath;
    }

    public void setScheme(String str) {
        this.mScheme = str;
    }

    public String getScheme() {
        return this.mScheme;
    }

    public void setHost(String str) {
        this.mHost = str;
    }

    public String getHost() {
        return this.mHost;
    }

    public void setPort(int i) {
        this.mPort = i;
    }

    public int getPort() {
        return this.mPort;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public String getPath() {
        return this.mPath;
    }

    public void setAuthInfo(String str) {
        this.mAuthInfo = str;
    }

    public String getAuthInfo() {
        return this.mAuthInfo;
    }
}
