package org.fidoalliance.uaf.client;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class AdditionData {
    public static final String ADD_TAG_GESTURE = "GESTURE_UVT";
    public static final String ADD_TAG_MATCHER = "MATCH_UI";
    public static final String ADD_TAG_USERNAME = "USERNAME";
    private String data;
    private String tag;

    public String getId() {
        return this.tag;
    }

    public void setId(String str) {
        this.tag = str;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String str) {
        this.data = str;
    }
}
